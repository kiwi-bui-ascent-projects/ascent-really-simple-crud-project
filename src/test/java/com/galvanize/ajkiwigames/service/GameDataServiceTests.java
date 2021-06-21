package com.galvanize.ajkiwigames.service;

import com.galvanize.ajkiwigames.model.GameData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameDataServiceTests {
    GameDataService gameDataService;

    List<GameData> gamesData;

    @BeforeEach
    void setUp() {
        gameDataService = new GameDataService();
        gamesData = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            gamesData.add(gameDataService.addGame(new GameData(i, "Game-" + i)));
        }
    }

    @Test
    void whenGetGames_multipleGames_returnAllGamesArray() {
        assertEquals(10, gameDataService.getGames().size(), "Should return length of 10");
    }

    @Test
    void whenGetGameByID_returnGame() {
        assertEquals(gamesData.get(3), gameDataService.getGameById(4), "Should return game with id 4");
    }

    @Test
    void whenAddGame_shouldReturnGame() {
        GameData newGame = new GameData(11, "Call of Duty");

        assertEquals(newGame, gameDataService.addGame(newGame), "Should return game when adding");
    }

    @Test
    void whenUpdateGame_returnUpdatedGame() {
        gameDataService.updateGameById(3, "publisher", "Atari");

        assertEquals("Atari", gameDataService.getGameById(3).getPublisher(),
                "Should update game publisher name to Atari");
    }

    @Test
    void whenDelete_deleteGame() {
        gameDataService.deleteGameById(6);

        assertEquals(null, gameDataService.getGameById(6), "Should return null");
    }
}
