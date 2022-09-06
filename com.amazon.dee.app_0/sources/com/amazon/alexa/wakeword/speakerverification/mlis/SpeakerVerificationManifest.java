package com.amazon.alexa.wakeword.speakerverification.mlis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class SpeakerVerificationManifest {
    private static final String AUDIO_TYPE = "AUDIO";
    private static final int DEFAULT_VERSION = 1;
    private static final String KEY_CONTENT_TYPE = "contentType";
    private static final String KEY_DATA = "data";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMESPACE = "namespace";
    private static final String KEY_PART = "part";
    private static final String KEY_PARTS = "parts";
    private static final String KEY_TYPE = "type";
    private static final String KEY_UPLOAD_TYPE = "uploadType";
    private static final String KEY_VERSION = "version";
    private static final String METADATA_TYPE = "ALEXA_1PSV_ENROLLMENT_METADATA";
    private static final String NAMESPACE = "ALEXA_1PSV";
    private static final String TYPE_UNSPECIFIED = "UNSPECIFIED";
    private static final String UPLOAD_TYPE = "ALEXA_1PSV_ENROLLMENT";

    private JSONArray getPartsList() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", TYPE_UNSPECIFIED);
        jSONObject2.put("id", 1);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(KEY_PART, jSONObject2);
        jSONObject.put("contentType", METADATA_TYPE);
        jSONObject.put("data", jSONObject3);
        JSONObject jSONObject4 = new JSONObject();
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("type", TYPE_UNSPECIFIED);
        jSONObject5.put("id", 2);
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put(KEY_PART, jSONObject5);
        jSONObject4.put("contentType", "AUDIO");
        jSONObject4.put("data", jSONObject6);
        jSONArray.put(jSONObject);
        jSONArray.put(jSONObject4);
        return jSONArray;
    }

    public JSONObject createSVManifest() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KEY_UPLOAD_TYPE, UPLOAD_TYPE);
        jSONObject.put("namespace", NAMESPACE);
        jSONObject.put("version", 1);
        jSONObject.put(KEY_PARTS, getPartsList());
        return jSONObject;
    }
}
