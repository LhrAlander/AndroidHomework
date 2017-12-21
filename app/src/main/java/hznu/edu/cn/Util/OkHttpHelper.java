package hznu.edu.cn.Util;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Alander on 2017/12/7.
 */

public class OkHttpHelper {
    static OkHttpClient client;
    public static void getHttp(final String url, final okhttp3.Callback callback) {
        if (client == null) {
            client = new OkHttpClient();
        }
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                client.newCall(request).enqueue(callback);
    }


    public static void postHttp(final String url, final RequestBody formBody, final okhttp3.Callback callback) {
        if (client == null) {
            client = new OkHttpClient();
        }
                Request request = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();
                client.newCall(request).enqueue(callback);

    }

}
