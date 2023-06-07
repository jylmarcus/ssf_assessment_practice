package sg.ssf.visa.practice.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.ssf.visa.practice.model.Article;
import sg.ssf.visa.practice.model.ArticlesDto;
import sg.ssf.visa.practice.service.NewsService;

@Controller
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String showArticles(Model model) throws IOException {
        List<Article> articles = newsService.getArticles();
        ArticlesDto articleForm = new ArticlesDto();
        model.addAttribute("articles", articles);
        model.addAttribute("articleForm", articleForm);
        return "index";
    }

    @PostMapping(path = "/articles")
    public String saveArticles(Model model, @ModelAttribute ArticlesDto form) {
        List<String> formArticles = form.getArticles();
        List<Article> saveArticles = new ArrayList<Article>();
        formArticles.removeAll(Collections.singleton(null));
        for(String article : formArticles) {
            JsonReader jsonReader = Json.createReader(new StringReader(article));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            Article newArticle = Article.create(jsonObject);
            saveArticles.add(newArticle);
        }

        newsService.saveArticles(saveArticles);
        return "success";
    }
}
