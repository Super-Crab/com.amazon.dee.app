package com.amazonaws.mobile.config;

import android.content.Context;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class AWSConfiguration {
    private static final String DEFAULT = "Default";
    private static final String DEFAULT_IDENTIFIER = "awsconfiguration";
    private String configName;
    private JSONObject mJSONObject;

    public AWSConfiguration(Context context) {
        this(context, getConfigResourceId(context));
    }

    private static int getConfigResourceId(Context context) {
        try {
            return context.getResources().getIdentifier(DEFAULT_IDENTIFIER, "raw", context.getPackageName());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read awsconfiguration.json please check that it is correctly formed.", e);
        }
    }

    private void readInputJson(Context context, int i) {
        try {
            Scanner scanner = new Scanner(context.getResources().openRawResource(i));
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            scanner.close();
            this.mJSONObject = new JSONObject(sb.toString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read awsconfiguration.json please check that it is correctly formed.", e);
        }
    }

    public String getConfiguration() {
        return this.configName;
    }

    public String getUserAgent() {
        try {
            return this.mJSONObject.getString("UserAgent");
        } catch (JSONException unused) {
            return "";
        }
    }

    public JSONObject optJsonObject(String str) {
        try {
            JSONObject jSONObject = this.mJSONObject.getJSONObject(str);
            if (jSONObject.has(this.configName)) {
                jSONObject = jSONObject.getJSONObject(this.configName);
            }
            return new JSONObject(jSONObject.toString());
        } catch (JSONException unused) {
            return null;
        }
    }

    public void setConfiguration(String str) {
        this.configName = str;
    }

    public String toString() {
        return this.mJSONObject.toString();
    }

    public AWSConfiguration(Context context, int i) {
        this(context, i, "Default");
    }

    public AWSConfiguration(Context context, int i, String str) {
        this.configName = str;
        readInputJson(context, i);
    }
}
