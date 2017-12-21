package hznu.edu.cn.blog;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hznu.edu.cn.Adapter.ArchiverAdapter;
import hznu.edu.cn.Util.GetArchive;
import hznu.edu.cn.Util.GetArticles;
import hznu.edu.cn.Util.OkHttpHelper;
import hznu.edu.cn.Util.StatusCode;
import hznu.edu.cn.model.ArchiveModel;
import hznu.edu.cn.model.MyArticle;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Archive extends AppCompatActivity {

    List<ArchiveModel> data;

    // 处理线程回调
    private Handler handler = new Handler(){
        public void handleMessage (Message msg) {
            switch (msg.what) {
                case StatusCode.ARCHIVE_GET:
                    updateArchivePage();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        // 设置标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        // 服务器获取数据
        getDataFromServer();
    }

    private void updateArchivePage () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(Archive.this);
        RecyclerView rv = (RecyclerView) findViewById(R.id.archive_recycle_view);
        rv.setLayoutManager(layoutManager);
        ArchiverAdapter adapter = new ArchiverAdapter(data);
        rv.setAdapter(adapter);
    }

    private void getDataFromServer () {
        String url = "http://www.alanderlight.club/api/getArchives.do";
        OkHttpHelper.getHttp(url, new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                JSONObject articlesObj = null;
                try {
                    articlesObj = new JSONObject(json);
                    data = GetArchive.getArchives(articlesObj.getString("archive"));
                    Message message = new Message();
                    message.what = StatusCode.ARCHIVE_GET;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
