package edu.octavio.url_shortner.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import edu.octavio.url_shortner.dtos.UrlDtoIn;
import edu.octavio.url_shortner.dtos.UrlDtoOut;
import edu.octavio.url_shortner.exceptions.IdNotFoundException;
import edu.octavio.url_shortner.models.Url;
import edu.octavio.url_shortner.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.secure;

/**
 * Class containing the application's business rule
 * @author octavio
 */
@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Value("${config.url.minSize:5}")
    Integer idMinSize;

    @Value("${config.url.maxSize:10}")
    Integer idMaxSize;

    /**
     * create url and save it in db
     * @param urlData the data used to create an url
     * @return return an UrlDtoOut instance containing the generated id and the QRCode
     */
    public UrlDtoOut createUrl(UrlDtoIn urlData) {
        UrlDtoIn newUrlData = urlData.withId(generateRandomId());
        Url newUrl = new Url(newUrlData);
        urlRepository.save(newUrl);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(generateBarCode(newUrlData.id()), "jpg", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new UrlDtoOut(newUrlData.id(), baos.toByteArray());
    }

    /**
     * An auxiliary method to generate a random id
     * @return a random generated id
     */
    private String generateRandomId() {
        return secure().nextAlphanumeric(idMinSize, idMaxSize);
    }

    /**
     * An auxiliary method to generate a RQCode
     * @param shortUrl the short url
     * @return a QRCode
     */
    private BufferedImage generateBarCode(String shortUrl) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        String location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(shortUrl)
                .toUri()
                .toString();

        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(location, BarcodeFormat.QR_CODE, 400, 400, hints);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

        // Converter o BitMatrix em uma imagem BufferedImage
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Criar uma imagem nova que inclui espaço para o texto
        int textHeight = 50; // Altura reservada para o texto
        BufferedImage combinedImage = new BufferedImage(400, 400 + textHeight, BufferedImage.TYPE_INT_RGB);

        // Obter o Graphics2D da imagem combinada
        Graphics2D g = combinedImage.createGraphics();

        // Preencher o fundo com cor branca (opcional)
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 400, 400 + textHeight);

        // Desenhar o QR Code na imagem combinada
        g.drawImage(qrImage, 0, textHeight, null);

        // Configurar fonte e cor do texto
        g.setFont(new Font("Verdana", Font.BOLD, 15));
        g.setColor(Color.BLACK);

        // Centralizar o texto horizontalmente
        int textWidth = g.getFontMetrics().stringWidth(location);
        int xPosition = (400 - textWidth) / 2;
        int yPosition = (textHeight / 2) + (g.getFontMetrics().getAscent() / 2) - 5;

        // Desenhar o texto na imagem
        g.drawString(location, xPosition, yPosition);

        // Liberar os recursos do Graphics2D
        g.dispose();

        return combinedImage;
    }

    /**
     * return the urlLong with specified id, if it doesn't exist throw {@link IdNotFoundException}
     * @param id the id that will be searched
     * @return urlLong with the specified id, if exists
     */
    public String findById(String id) {
        Url url = urlRepository.findById(id).orElseThrow(IdNotFoundException::new);
        return url.getUrlLong();
    }
}
