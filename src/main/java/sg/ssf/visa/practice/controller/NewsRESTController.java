package sg.ssf.visa.practice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.ssf.visa.practice.model.Article;
import sg.ssf.visa.practice.service.NewsService;

@RestController
public class NewsRESTController {
    @Autowired
    NewsService newsService;

    @GetMapping(path="/news/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getArticle(@RequestParam String id) {
        Optional<Article> art = newsService.getArticleById(id);
        if(art.isEmpty()) {
            JsonObject error = Json.createObjectBuilder()
                .add("message", "Cannot find news article %s".formatted(id))
                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error.toString());
        }
        return ResponseEntity.ok(art.get().toString());
    }
}
