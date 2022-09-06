package com.amazon.rtcsc.capabilityagent.common.core;

import com.amazon.rtcsc.utils.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
/* loaded from: classes13.dex */
public class PayloadObject {
    private static final String SESSION_ID_KEY = "sessionId";
    private RtcscLogger mLog = RtcscLogger.getLogger(PayloadObject.class);
    private String mSessionId;

    public PayloadObject(String str) {
        this.mLog.i("PayloadObject constructor");
        extractSessionId(str);
    }

    private void extractSessionId(String str) {
        this.mSessionId = ((JsonObject) new Gson().fromJson(str, (Class<Object>) JsonObject.class)).get("sessionId").getAsString();
        RtcscLogger rtcscLogger = this.mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sessionId found in the payload: ");
        outline107.append(this.mSessionId);
        rtcscLogger.i(outline107.toString());
    }

    public String getSessionId() {
        return this.mSessionId;
    }
}
