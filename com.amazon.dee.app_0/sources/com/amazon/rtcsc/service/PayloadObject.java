package com.amazon.rtcsc.service;

import com.amazon.rtcsc.utils.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/* loaded from: classes13.dex */
public class PayloadObject {
    private static final String SESSION_DOMAIN_KEY = "sessionDomain";
    private static final String SESSION_ID_KEY = "sessionId";
    private static final String SH_REMOTE_MONITORING_KEY = "SmartHome-RemoteMonitoring";
    private RtcscLogger mLog = RtcscLogger.getLogger(PayloadObject.class);
    private String mSessionDomain;
    private String mSessionId;

    public PayloadObject(String str) {
        this.mLog.i("PayloadObject constructor");
        extractsessionIdAndDomain(str);
    }

    private void extractsessionIdAndDomain(String str) {
        JsonObject jsonObject = (JsonObject) new Gson().fromJson(str, (Class<Object>) JsonObject.class);
        this.mSessionId = jsonObject.get("sessionId").getAsString();
        RtcscLogger rtcscLogger = this.mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sessionId found in the payload: ");
        outline107.append(this.mSessionId);
        rtcscLogger.i(outline107.toString());
        JsonElement jsonElement = jsonObject.get(SESSION_DOMAIN_KEY);
        if (jsonElement != null) {
            this.mSessionDomain = jsonElement.getAsString();
            RtcscLogger rtcscLogger2 = this.mLog;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("SessionDomain found in the payload: ");
            outline1072.append(this.mSessionDomain);
            rtcscLogger2.i(outline1072.toString());
            return;
        }
        this.mSessionDomain = SH_REMOTE_MONITORING_KEY;
        RtcscLogger rtcscLogger3 = this.mLog;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("SessionDomain not found. Using default: ");
        outline1073.append(this.mSessionDomain);
        rtcscLogger3.i(outline1073.toString());
    }

    public String getSessionDomain() {
        return this.mSessionDomain;
    }

    public String getSessionId() {
        return this.mSessionId;
    }
}
