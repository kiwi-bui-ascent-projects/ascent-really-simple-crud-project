package com.galvanize.ajkiwigames.controller;

import com.galvanize.ajkiwigames.model.GameData;
import com.galvanize.ajkiwigames.service.GameDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GameDataControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    GameDataService gameDataService;

    List<GameData> gameDataList;

    @BeforeEach
    void setUp() {
        gameDataList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            gameDataList.add(new GameData(i, "Game-" + i));
        }
    }

    @Test
    void getGames() throws Exception {
        when(gameDataService.getGames()).thenReturn(gameDataList);

        mockMvc.perform(get("/game"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void getGameById() throws Exception {
        when(gameDataService.getGameById(3)).thenReturn(gameDataList.get(2));

        mockMvc.perform(get("/game/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(3));
    }

    @Test
    void updateGameById() throws Exception {
        String arguments = "{\"field\":\"publisher\",\"value\":\"EA\"}";
        mockMvc.perform(put("/game/3").contentType(MediaType.APPLICATION_JSON).content(arguments))
                .andDo(print())
                .andExpect(status().isOk());

        verify(gameDataService).updateGameById(3, "publisher", "EA");
    }

    @Test
    void addGame() throws Exception {
        when(gameDataService.addGame(any(gameDataList.get(1).getClass())))
                .thenReturn(new GameData(11, "Donkey Kong"));

        String arguments = "{\"id\":11,\"name\":\"Donkey Kong\"}";
        mockMvc.perform(post("/game").contentType(MediaType.APPLICATION_JSON).content(arguments))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Donkey Kong"));
    }

    @Test
    void deleteGame() throws Exception {
        mockMvc.perform(delete("/game/3"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(gameDataService).deleteGameById(3);
    }
}


