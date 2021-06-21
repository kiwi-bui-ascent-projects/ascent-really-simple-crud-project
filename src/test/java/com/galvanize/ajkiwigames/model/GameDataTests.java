package com.galvanize.ajkiwigames.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameDataTests {
    GameData game;

    @BeforeEach
    void setUp() {
        game = new GameData(1, "Tetris");
    }

    @Test
    void whenDefault_twoArgs_returnsGameData() {
        assertEquals("Tetris", game.getName(), "Should return name");
        assertEquals(1, game.getId(), "Should return Id");
    }

    @Test
    void idSetterGetter() {
        game.setId(17);

        assertEquals(17, game.getId(), "Should return 17");
    }

    @Test
    void nameSetterGetter() {
        game.setName("Mario");

        assertEquals("Mario", game.getName(), "Should return Mario");
    }

    @Test
    void genreSetterGetter() {
        game.setGenre("Retro");

        assertEquals("Retro", game.getGenre(), "Should return Retro");
    }

    @Test
    void photoUrlsSetterGetter() {
        game.setPhotoUrls(new String[]{"imgur.com/tetris"});

        assertArrayEquals(new String[]{"imgur.com/tetris"}, game.getPhotoUrls(), "Should return array of urls");
    }

    @Test
    void publisherSetterGetter() {
        game.setPublisher("Ubisoft");

        assertEquals("Ubisoft", game.getPublisher(), "Should return Ubisoft");
    }

    @Test
    void releaseDateSetterGetter() {
        game.setReleaseDate("March 20, 2021");

        assertEquals("March 20, 2021", game.getReleaseDate(), "Should return March 20, 2021");
    }

    @Test
    void platformSetterGetter() {
        game.setPlatforms(new String[]{"Windows", "MacOS"});

        assertArrayEquals(new String[]{"Windows", "MacOS"}, game.getPlatforms(), "Should return platforms \"Windows\", \"MacOS\"");
    }

    @Test
    void ratingSetterGetter() {
        game.setRating("E");

        assertEquals("E", game.getRating(), "Should return rating E");
    }

    @Test
    void priceSetterGetter() {
        game.setPrice(19.99);

        assertEquals(19.99, game.getPrice(), "Should return price 19.99");
    }

    @Test
    void whenMainConstructor_allArgs_returnsGameData() {
        game = new GameData(1,"Tetris", "Retro", new String[]{"imgur.com/tetris"},
                "Ubisoft", "March 20, 2021", new String[]{"Windows", "MacOS"}, "E", 19.99);

        assertEquals(1, game.getId(), "Should return id 1");
        assertEquals(19.99, game.getPrice(), "Should return price 19.99");
    }
}
