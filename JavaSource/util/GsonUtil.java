/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.util.regex.Pattern;

/**
 *
 * @author G0042204
 */
public class GsonUtil {

    private static Gson gson = new Gson();

    public static Object convert(String str, Class c) {
        JsonReader reader = new JsonReader(new StringReader(parseFakeBoolean(str)));
        reader.setLenient(true);
        return gson.fromJson(reader, c);
    }

    public static String serialize(Object ob) {
        return gson.toJson(ob, ob.getClass());
    }

    public static String parseFakeBoolean(String content) {
        Pattern pYes = Pattern.compile("(\"On\"|\"Yes\"|\"true\")", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        String yes = pYes.matcher(content).replaceAll("true");
        Pattern pNo = Pattern.compile("(\"Off\"|\"No\"|\"false\")", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        return pNo.matcher(yes).replaceAll("false");
    }

}
