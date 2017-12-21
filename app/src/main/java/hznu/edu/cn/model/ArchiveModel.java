package hznu.edu.cn.model;

import java.util.List;

/**
 * Created by Alander on 2017/12/2.
 */

public class ArchiveModel {
    private String date;
    private List<MyArticle> articles;


    public ArchiveModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MyArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<MyArticle> articles) {
        this.articles = articles;
    }
}
