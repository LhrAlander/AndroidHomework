package hznu.edu.cn.blog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hznu.edu.cn.Adapter.MyArticleAdapter;
import hznu.edu.cn.Util.GetArticles;
import hznu.edu.cn.Util.OkHttpHelper;
import hznu.edu.cn.model.MyArticle;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final int UPDATE_ARTICLES = 1;
    private RecyclerView myRecyclerView;

    // 文章数据
    private List<MyArticle> articles;
    private MyArticleAdapter myArticleAdapter;

    // 处理线程回调
    private Handler handler = new Handler(){
      public void handleMessage (Message msg) {
          switch (msg.what) {
              case UPDATE_ARTICLES:
                  initArticles();
                  break;
          }
      }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 获取文章数据
        articles = new ArrayList<>();
        getArticlesFromServer();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    private void initArticles () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        myRecyclerView = (RecyclerView) findViewById(R.id.index_recycle_view);
        myRecyclerView.setLayoutManager(layoutManager);
        myArticleAdapter = new MyArticleAdapter(articles);
        myRecyclerView.setAdapter(myArticleAdapter);
    }

    private void getArticlesFromServer () {
        String url = "http://www.alanderlight.club/api/getDisplayArticles.do";
        FormBody.Builder builder = new FormBody.Builder();
        RequestBody requestBody = builder.add("tag", "all").add("page", "1").add("limit", "10").build();
        OkHttpHelper.postHttp(url, requestBody, new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json  = response.body().string();
                JSONObject articlesObj = null;
                try {
                    articlesObj = new JSONObject(json);
                    articles = GetArticles.GetArticlesByStr(articlesObj.getString("articles"));
                    Message message = new Message();
                    message.what = UPDATE_ARTICLES;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
