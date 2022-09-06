package com.amazon.alexa.voice.ui.driveMode.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class ResourceUtils {
    private static final int bufferSize = 1024;

    private ResourceUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static JSONObject getCardPayload(String str) throws IOException, JSONException {
        return getJsonObject(str).getJSONObject("directive").getJSONObject("payload");
    }

    public static JSONObject getJsonObject(String str) throws IOException, JSONException {
        return new JSONObject(getString(str));
    }

    public static String getString(String str) throws IOException {
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
