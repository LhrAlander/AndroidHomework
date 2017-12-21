package hznu.edu.cn.model;

import android.widget.TextView;

import java.util.List;

/**
 * Created by Alander on 2017/12/1.
 */

public class MyArticle {
    private String title;
    private String markedCnt;
    private String createTime;
    private String articleId;
    List<String> types;

    public MyArticle(String title, String markedCnt, String createTime, String articleId, List<String> types) {

        this.title = title;
        this.markedCnt = markedCnt;
        this.createTime = createTime;
        this.articleId = articleId;
        this.types = types;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMarkedCnt() {
        return markedCnt;
    }

    public void setMarkedCnt(String markedCnt) {
        this.markedCnt = markedCnt;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        String str = title + " " + " " + createTime + " " + articleId;
        for (String type : types) {
            str += " " + type;
        }
        return str;
    }


}
