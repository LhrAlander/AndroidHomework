package hznu.edu.cn.Util;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hznu.edu.cn.blog.MainActivity;
import hznu.edu.cn.model.ArchiveModel;
import hznu.edu.cn.model.MyArticle;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;

/**
 * Created by Alander on 2017/12/2.
 */

public class GetArchive {

    public static List<ArchiveModel> getArchives(String str) {
        List<ArchiveModel> archives = new ArrayList<>();
        JSONArray ja;
        try {
            ja = new JSONArray(str);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject archive = ja.getJSONObject(i);
                ArchiveModel archiveModel = new ArchiveModel();
                archiveModel.setDate(archive.getString("date"));
                JSONArray articleJA = new JSONArray(archive.getString("articles"));
                List<MyArticle> articles = new ArrayList<>();
                for (int j = 0; j < articleJA.length(); j++) {
                    JSONObject articleJO = articleJA.getJSONObject(j);
                    String articleId = articleJO.getString("articleId");
                    MyArticle article = new MyArticle(articleJO.getString("name"), null, null, articleId, null);
                    articles.add(article);
                }
                archiveModel.setArticles(articles);
                archives.add(archiveModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return archives;
    }






}
