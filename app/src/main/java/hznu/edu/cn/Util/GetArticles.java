package hznu.edu.cn.Util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hznu.edu.cn.model.MyArticle;

/**
 * Created by Alander on 2017/12/1.
 */

public class GetArticles {
    public static List<MyArticle> GetArticlesByStr(String str) {
        List<MyArticle> articles = new ArrayList<>();
        try {
            JSONArray ja = new JSONArray(str);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject articleObj = ja.getJSONObject(i);
                String title = articleObj.getString("title");
                String markedCnt = articleObj.getString("markedCnt");
                String createTime = articleObj.getString("createTime");
                String articleId = articleObj.getString("articleId");
                List<String> types = getTypesByStr(articleObj.getString("types"));
                MyArticle article = new MyArticle(title, markedCnt, createTime, articleId, types);
                articles.add(article);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public static List<String> getTypesByStr (String str) {
        List<String> types = new ArrayList<>();
        str = str.substring(1, str.length() - 1);
        String[] a = str.split(",");
        for (String t : a) {
            t.trim();
            t = t.substring(1, t.length() - 1);
            types.add(t);
        }
        return types;
    }
}
