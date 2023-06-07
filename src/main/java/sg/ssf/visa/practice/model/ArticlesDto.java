package sg.ssf.visa.practice.model;

import java.util.ArrayList;
import java.util.List;

public class ArticlesDto {
    private List<String> articles = new ArrayList<String>();

    public ArticlesDto() {
    }

    public List<String> getArticles() {
        return articles;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }
}
