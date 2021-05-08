package com.galvanize.ajkiwigames;

import org.json.JSONObject;
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
        JSONObject argsJson = new JSONObject()
                .put("id", 3)
                .put("field", "publisher")
                .put("value", "EA");

        String argsStr = argsJson.toString();
        mockMvc.perform(put("/game/3").contentType(MediaType.APPLICATION_JSON).content(argsStr))
                .andDo(print())
                .andExpect(status().isOk());

        verify(gameDataService).updateGameById(3, "publisher", "EA");
    }

    @Test
    void addGames() throws Exception {
        GameData newGame = new GameData(11, "Donkey Kong");
        gameDataList.add(newGame);
        when(gameDataService.addGame(newGame)).thenReturn(gameDataList.get(10));
        JSONObject argsJson = new JSONObject()
                .put("id", 11)
                .put("name", "Donkey Kong");

        String argsStr = argsJson.toString();
        mockMvc.perform(post("/game/add").contentType(MediaType.APPLICATION_JSON).content(argsStr))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Donkey Kong"));
    }
}


