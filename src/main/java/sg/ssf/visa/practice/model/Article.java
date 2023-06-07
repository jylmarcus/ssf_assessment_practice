package sg.ssf.visa.practice.model;

import java.io.Serializable;

import jakarta.json.JsonObject;

public class Article implements Serializable{
    private String id;
    private String published;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories;

    public Article() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public static Article create(JsonObject jsonObject){
        Article newArticle = new Article();
        newArticle.setId(jsonObject.getString("id"));
        newArticle.setPublished(jsonObject.getJsonNumber("published_on").toString());
        newArticle.setTitle(jsonObject.getString("title"));
        newArticle.setUrl(jsonObject.getString("url"));
        newArticle.setImageurl(jsonObject.getString("imageurl"));
        newArticle.setBody(jsonObject.getString("body"));
        newArticle.setTags(jsonObject.getString("tags"));
        newArticle.setCategories(jsonObject.getString("categories"));
        return newArticle;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + this.getId() + "\",\"published\":\"" + this.getPublished() + "\",\"title\":\"" + this.getTitle() + "\",\"url\":\"" + this.getUrl() + "\",\"imageurl\":\"" + this.getImageurl() + "\",\"body\":\"" + this.getBody() + "\",\"tags\":\"" + this.getTags() + "\",\"categories\":\"" + this.getCategories() +"\"";
    }
}
