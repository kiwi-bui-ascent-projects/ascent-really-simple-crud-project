package com.galvanize.ajkiwigames;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameDataService {
    private ArrayList<GameData> gameDataList;

    public GameDataService() {
        gameDataList = new ArrayList<>();
    }

    public List<GameData> getGames() {
        return this.gameDataList;
    }

    public GameData getGameById(int id) {
        for (GameData game: gameDataList) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }

    public void updateGameById(int id, String field, Object value) {
        switch (field) {
            case "name":
                getGameById(id).setName((String) value);
                break;
            case "genre":
                getGameById(id).setGenre((String) value);
                break;
            case "photoUrls":
                getGameById(id).setPhotoUrls((String[]) value);
                break;
            case "publisher":
                getGameById(id).setPublisher((String) value);
                break;
            case "releaseDate":
                getGameById(id).setReleaseDate((String) value);
                break;
            case "platforms":
                getGameById(id).setPlatforms((String[]) value);
                break;
            case "rating":
                getGameById(id).setRating((String) value);
                break;
            case "price":
                getGameById(id).setPrice((double) value);
                break;
        }
    }

    public GameData addGame(GameData game) {
        this.gameDataList.add(game);
        return game;
    }

    public void deleteGameById(int id) {
        for (GameData game: gameDataList) {
            if (game.getId() == id) {
                gameDataList.remove(game);
                break;
            }
        }

    }
}
