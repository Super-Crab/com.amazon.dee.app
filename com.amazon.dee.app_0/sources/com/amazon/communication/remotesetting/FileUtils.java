package com.amazon.communication.remotesetting;

import android.content.res.AssetManager;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public final class FileUtils {
    private FileUtils() {
    }

    public static JSONObject readJsonFile(AssetManager assetManager, String str) throws IOException, JSONException {
        return new JSONObject(readTextFile(assetManager, str));
    }

    public static String readTextFile(AssetManager assetManager, String str) throws IOException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(assetManager.open(str)).useDelimiter("\\A");
            String next = scanner.hasNext() ? scanner.next() : "";
            scanner.close();
            return next;
        } catch (Throwable th) {
            if (scanner != null) {
                scanner.close();
            }
            throw th;
        }
    }
}
