package com.factotum.plexbackend.integration;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@ActiveProfiles({"test"})
@AutoConfigureMockMvc
class TitleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URI = "/api/v1/titles";

    @Test
    void searchTitles_GivenSearchIsValid_ThenReturnGoodTitle() throws Exception {

        this.mockMvc.perform(
                get(BASE_URI + "/search")
                .param("title", "batman"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0].Title").exists())
                .andExpect(jsonPath("$[0].Year").exists())
                .andExpect(jsonPath("$[0].imdbID").exists())
                .andExpect(jsonPath("$[0].Type").exists())
                .andExpect(jsonPath("$[0].Poster").exists());

    }

    @Test
    void searchTitles_GivenSearchHasSpaces_ThenReturnGoodTitle() throws Exception {

        this.mockMvc.perform(
                get(BASE_URI + "/search")
                        .param("title", "the flash"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0].Title").exists())
                .andExpect(jsonPath("$[0].Year").exists())
                .andExpect(jsonPath("$[0].imdbID").exists())
                .andExpect(jsonPath("$[0].Type").exists())
                .andExpect(jsonPath("$[0].Poster").exists());

    }

    @Test
    void searchTitles_GivenTooManyResults_ThenReturnEmptyArray() throws Exception {

        this.mockMvc.perform(
                get(BASE_URI + "/search")
                        .param("title", "t"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", is(empty())));

    }

    @Test
    void searchTitles_GivenSearchOptionIsEmpty_ThenReturnBadRequest() throws Exception {

        this.mockMvc.perform(
                get(BASE_URI + "/search")
                        .param("title", ""))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

}
