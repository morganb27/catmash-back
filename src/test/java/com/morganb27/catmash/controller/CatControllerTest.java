package com.morganb27.catmash.controller;

import com.morganb27.catmash.domain.Cat;
import com.morganb27.catmash.service.CatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatController.class)
public class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatService catService;

    @Test
    public void getAllCats_returnsCatsList() throws Exception {
        // Arrange
        Cat cat1 = new Cat();
        cat1.setId(1L);
        cat1.setName("Tom");
        cat1.setImageURL("http://example.com/cat1.jpg");
        cat1.setVotes(5);

        Cat cat2 = new Cat();
        cat2.setId(2L);
        cat2.setName("Jerry");
        cat2.setImageURL("http://example.com/cat2.jpg");
        cat2.setVotes(3);

        List<Cat> cats = Arrays.asList(cat1, cat2);
        given(catService.findAllCats()).willReturn(cats);

        // Act & Assert
        mockMvc.perform(get("/cats")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(cats.size()))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Tom"))
                .andExpect(jsonPath("$[0].imageURL").value("http://example.com/cat1.jpg"))
                .andExpect(jsonPath("$[0].votes").value(5))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jerry"))
                .andExpect(jsonPath("$[1].imageURL").value("http://example.com/cat2.jpg"))
                .andExpect(jsonPath("$[1].votes").value(3));
    }

    @Test
    public void incrementVote_whenCatExists_returnsUpdatedCat() throws Exception {
        // Arrange
        Cat updatedCat = new Cat();
        updatedCat.setId(1L);
        updatedCat.setName("Cat 1");
        updatedCat.setImageURL("http://example.com/cat1.jpg");
        updatedCat.setVotes(6);

        given(catService.incrementVote(1L)).willReturn(updatedCat);

        // Act & Assert
        mockMvc.perform(patch("/cats/1/vote")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Cat 1"))
                .andExpect(jsonPath("$.imageURL").value("http://example.com/cat1.jpg"))
                .andExpect(jsonPath("$.votes").value(6));
    }

    @Test
    public void incrementVote_whenCatDoesNotExist_returnsNotFound() throws Exception {
        // Arrange
        given(catService.incrementVote(3L)).willReturn(null);

        // Act & Assert
        mockMvc.perform(patch("/cats/3/vote")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
