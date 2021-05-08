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
    public void updateById(@PathVariable int id, @RequestBody String arguments) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(arguments, Map.class);

            this.gameDataService.updateGameById(id, (String) map.get("field"), map.get("value"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping
    public GameData addGame(@RequestBody String arguments) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(arguments, Map.class);

            return this.gameDataService.addGame(new GameData((int) map.get("id"), (String) map.get("name")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable int id) {
        this.gameDataService.deleteGameById(id);
    }
}
