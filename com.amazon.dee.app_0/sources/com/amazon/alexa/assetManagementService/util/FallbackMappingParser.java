package com.amazon.alexa.assetManagementService.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/* loaded from: classes6.dex */
public class FallbackMappingParser {
    private static FallbackMappingParser instance;
    private boolean isProdBuild;

    public FallbackMappingParser(boolean z) {
        this.isProdBuild = z;
    }

    public static FallbackMappingParser getInstance(boolean z) {
        if (instance == null) {
            synchronized (FallbackMappingParser.class) {
                instance = new FallbackMappingParser(z);
            }
        }
        return instance;
    }

    public JsonObject parseJsonResponse(String str) {
        return (JsonObject) new JsonParser().parse(str);
    }

    public JsonObject readJSONStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                return parseJsonResponse(sb.toString());
            }
        }
    }

    public String stageBasedCloudFrontURL(String str) {
        if (this.isProdBuild) {
            return GeneratedOutlineSupport1.outline72("https://dttijy4dc6foz.cloudfront.net/", str);
        }
        return GeneratedOutlineSupport1.outline72("https://d132dgf6yldc6m.cloudfront.net/", str);
    }
}
