package com.galvanize.ajkiwigames;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameDataController {
    GameDataService gameDataService;

    public GameDataController(GameDataService gameDataService) {
        this.gameDataService = gameDataService;
    }

    @GetMapping
    public List<GameData> get() {
        return this.gameDataService.getGames();
    }

    @GetMapping({"/{id}"})
    public GameData getById(@PathVariable int id) {
        return this.gameDataService.getGameById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@RequestBody String arguments) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map map = mapper.readValue(arguments, Map.class);

            int id = (int) map.get("id");
            String field = (String) map.get("field");
            Object value = map.get("value");

            this.gameDataService.updateGameById(id, field, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/add")
    public GameData addGame(@RequestBody String arguments) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map map = mapper.readValue(arguments, Map.class);

            int id = (int) map.get("id");
            String name = (String) map.get("name");

            GameData game = new GameData(id, name);

            return this.gameDataService.addGame(game);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
