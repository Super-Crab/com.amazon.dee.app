package com.amazon.device.crashmanager.rtla;

import com.amazon.device.crashmanager.CrashDetailsCollectable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public class RtlaCrashDetailsCollectable implements CrashDetailsCollectable {
    static final String DEFAULT_SESSION_ID = "000-0000000-0000000";
    private static final String EMPTY_STRING = "";
    static final String RTLA_PAGE_TYPE_KEY = "RtlaPageType";
    static final String RTLA_SESSION_ID_KEY = "RtlaSessionId";
    static final String RTLA_SUBPAGE_TYPE_KEY = "RtlaSubPageType";
    static final String RTLA_USER_AGENT_KEY = "RtlaUserAgent";
    static final String RTLA_WEB_LAB_KEY = "RtlaWebLabs";
    private final RtlaCrashDetails mRtlaCrashDetails;

    public RtlaCrashDetailsCollectable(RtlaCrashDetails rtlaCrashDetails) {
        validateCrashDetails(rtlaCrashDetails);
        this.mRtlaCrashDetails = rtlaCrashDetails;
    }

    private String defaultOnNull(String str, String str2) {
        return str == null ? str2 : str;
    }

    private String formatWebLabs(List<String> list) {
        if (list == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    private void validateCrashDetails(RtlaCrashDetails rtlaCrashDetails) {
        if (rtlaCrashDetails != null) {
            return;
        }
        throw new IllegalArgumentException("RtlaCrashDetails must not be null.");
    }

    @Override // com.amazon.device.crashmanager.CrashDetailsCollectable
    public Map<String, String> collect(Throwable th) {
        String sessionId = this.mRtlaCrashDetails.getSessionId();
        String userAgent = this.mRtlaCrashDetails.getUserAgent();
        String pageType = this.mRtlaCrashDetails.getPageType();
        String subPageType = this.mRtlaCrashDetails.getSubPageType();
        LinkedList linkedList = new LinkedList();
        if (this.mRtlaCrashDetails.getActiveWebLabs() != null) {
            linkedList.addAll(this.mRtlaCrashDetails.getActiveWebLabs());
        }
        HashMap hashMap = new HashMap();
        hashMap.put(RTLA_SESSION_ID_KEY, defaultOnNull(sessionId, DEFAULT_SESSION_ID));
        if (userAgent != null) {
            hashMap.put(RTLA_USER_AGENT_KEY, userAgent);
        }
        if (pageType != null) {
            hashMap.put(RTLA_PAGE_TYPE_KEY, pageType);
        }
        if (subPageType != null) {
            hashMap.put(RTLA_SUBPAGE_TYPE_KEY, subPageType);
        }
        if (linkedList.size() > 0) {
            hashMap.put(RTLA_WEB_LAB_KEY, formatWebLabs(linkedList));
        }
        return hashMap;
    }
}
