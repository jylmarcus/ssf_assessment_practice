package sg.ssf.visa.practice.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.ssf.visa.practice.model.Article;
import sg.ssf.visa.practice.repository.NewsRepository;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepo;

    @Value("${spring.api.url}")
    private String API_URL;

    public List<Article> getArticles() throws IOException {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.getForEntity(API_URL, String.class);
        List<Article> articleList = new ArrayList<Article>();

        //System.out.printf("%s\n", resp.getBody());

        JsonArray data;
        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            data = o.getJsonArray("Data");
        }

        for(JsonValue article : data) {
            Article newArticle = Article.create(article.asJsonObject());
            articleList.add(newArticle);
        }

        return articleList;
    }

    public void saveArticles(List<Article> articles) {
        for(Article article: articles) newsRepo.save(article);
    }

    public Optional<Article> getArticleById(String id) {
        return newsRepo.get(id);
    }
}
