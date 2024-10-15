package edu.octavio.url_shortner.controllers;

import com.google.gson.Gson;
import edu.octavio.url_shortner.dtos.UrlDtoIn;
import edu.octavio.url_shortner.dtos.UrlDtoOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return Id not found")
    void redirectToUrlLongCase1() throws Exception{
        mockMvc.perform(get("/abcddcba"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("ID not found"));

    }

    @Test
    @DisplayName("Should redirect to page with the specified id")
    void redirectToUrlLongCase2() throws Exception{
        String url = "https://www.github.com/Pira4Ever";
        UrlDtoOut createdUrl = createEntity(url);
        mockMvc.perform(get("/{id}", createdUrl.id()))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", url));

    }

    @Test
    @DisplayName("Should return HTTP status 201 created and the information about the url")
    void createUrl() throws Exception {
        UrlDtoIn urlData = new UrlDtoIn(null, "https://www.github.com/Pira4Ever");
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"urlLong\": \"%s\"}", urlData.urlLong()))
        )
                .andExpect(status().isCreated());
    }

    private UrlDtoOut createEntity(String urlLong) throws Exception {
        MvcResult returned = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"urlLong\": \"%s\"}", urlLong))
        ).andReturn();

        int split_nums = returned.getResponse().getHeader("Location").split("/").length;

        return new UrlDtoOut(returned.getResponse().getHeader("Location").split("/")[split_nums - 1], returned.getResponse().getContentAsByteArray());
    }
}