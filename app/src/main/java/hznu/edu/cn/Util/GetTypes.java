package hznu.edu.cn.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alander on 2017/12/3.
 */

public class GetTypes {

    public static List<String> getTypes (String str) {
        List<String> types = new ArrayList<>();
        try {
            JSONArray typeJA = new JSONArray(str);
            for (int i = 0; i < typeJA.length(); i++) {
                JSONObject typeJO = typeJA.getJSONObject(i);
                String type = typeJO.getString("name");
                types.add(type);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return types;
    }
}
