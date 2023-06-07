package sg.ssf.visa.practice.repository;

import java.io.StringReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.ssf.visa.practice.model.Article;

@Repository
public class NewsRepository {
    @Autowired
    private RedisTemplate<String, String> template;

    public void save(Article article) {
        this.template.opsForValue().set(article.getId(), article.toString());
    }

    public Optional<Article> get(String id) {
        String json = template.opsForValue().get(id);
        if((null == json || json.trim().length() <= 0)){
            return Optional.empty();
        }

        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        return Optional.of(Article.create(jsonObject));
    }
}
