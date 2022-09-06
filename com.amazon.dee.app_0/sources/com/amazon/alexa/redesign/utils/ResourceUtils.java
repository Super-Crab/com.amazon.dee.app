package com.amazon.alexa.redesign.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public final class ResourceUtils {
    private ResourceUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static JSONArray getJsonArray(InputStream inputStream) throws IOException, JSONException {
        return new JSONArray(getString(inputStream));
    }

    public static JSONArray getJsonArrayFromResources(String str) throws IOException, JSONException {
        return new JSONArray(getStringFromResources(str));
    }

    public static JSONObject getJsonObject(InputStream inputStream) throws IOException, JSONException {
        return new JSONObject(getString(inputStream));
    }

    public static JSONObject getJsonObjectFromResources(String str) throws IOException, JSONException {
        return new JSONObject(getStringFromResources(str));
    }

    public static String getString(InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return byteArrayOutputStream.toString("UTF-8");
                }
            }
        } finally {
            inputStream.close();
        }
    }

    public static String getStringFromResources(String str) throws IOException {
        InputStream resourceAsStream = ResourceUtils.class.getClassLoader().getResourceAsStream(str);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = resourceAsStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return byteArrayOutputStream.toString("UTF-8");
                }
            }
        } finally {
            resourceAsStream.close();
        }
    }
}
