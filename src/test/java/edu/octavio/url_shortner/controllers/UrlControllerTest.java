package edu.octavio.url_shortner.controllers;

import com.google.gson.Gson;
import edu.octavio.url_shortner.dtos.UrlDto;
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
        UrlDto createdUrl = createEntity();
        mockMvc.perform(get("/{id}", createdUrl.id()))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", createdUrl.urlLong()));

    }

    @Test
    @DisplayName("Should return HTTP status 201 created and the information about the url")
    void createUrl() throws Exception {
        UrlDto urlData = new UrlDto(null, "https://www.github.com/Pira4Ever");
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"urlLong\": \"%s\"}", urlData.urlLong()))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.urlLong").exists())
                .andExpect(jsonPath("$.urlLong").value(urlData.urlLong()));
    }

    private UrlDto createEntity() throws Exception {
        Gson gson = new Gson();
        UrlDto urlData = new UrlDto(null, "https://www.github.com/Pira4Ever");
        MvcResult returned = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"urlLong\": \"%s\"}", urlData.urlLong()))
        ).andReturn();

        return gson.fromJson(returned.getResponse().getContentAsString(), UrlDto.class);
    }
}