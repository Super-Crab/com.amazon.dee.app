package com.amazon.rtcsc.service.appclient;

import com.amazon.rtcsc.interfaces.IRtcscAppClientListener;
import com.amazon.rtcsc.service.RtcscServiceHandler;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes13.dex */
public class RtcscAppClientCommon {
    private RtcscServiceHandler mRtcscServiceHandler;
    private final RtcscLogger mLog = RtcscLogger.getLogger(RtcscAppClientCommon.class);
    private ConcurrentHashMap<String, IRtcscAppClientListener> mAppClientListenersMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> mSessionIdToDomainMap = new ConcurrentHashMap<>();
    private ArrayList<String> mAvailableSessions = new ArrayList<>();

    public RtcscAppClientCommon(RtcscServiceHandler rtcscServiceHandler) {
        this.mRtcscServiceHandler = rtcscServiceHandler;
    }

    public IRtcscAppClientListener getListenerForDomain(String str) {
        if (str == null) {
            this.mLog.e("getListenerForDomain: sessionDomain is null");
            return null;
        }
        return this.mAppClientListenersMap.get(str);
    }

    public IRtcscAppClientListener getListenerForSessionId(String str) {
        if (str == null) {
            this.mLog.e("getListenerForSessionId: sessionId is null");
            return null;
        }
        return getListenerForDomain(this.mSessionIdToDomainMap.get(str));
    }

    public ArrayList<String> getSessionsForDomain(String str) {
        this.mLog.i("getSessionsForDomain: called.");
        ArrayList<String> arrayList = new ArrayList<>();
        if (str == null) {
            this.mLog.e("getSessionsForDomain: sessionDomain is null");
            return arrayList;
        }
        if (this.mSessionIdToDomainMap.containsValue(str)) {
            for (Map.Entry<String, String> entry : this.mSessionIdToDomainMap.entrySet()) {
                if (str.equals(entry.getValue())) {
                    RtcscLogger rtcscLogger = this.mLog;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getSessionsForDomain: Found sessionId : ");
                    outline107.append(entry.getKey());
                    rtcscLogger.i(outline107.toString());
                    arrayList.add(entry.getKey());
                }
            }
        } else {
            this.mLog.e("getSessionsForDomain: sessionDomain is not actively tracked.");
        }
        return arrayList;
    }

    public boolean isAnyDomainRegistered() {
        return !this.mAppClientListenersMap.isEmpty();
    }

    public boolean isSessionDomainRegistered(String str) {
        return this.mAppClientListenersMap.containsKey(str);
    }

    public void onAudioEnabledForSession() {
        this.mLog.i("onAudioEnabledForSession");
        this.mRtcscServiceHandler.onAudioEnabledForSession();
    }

    public void onSessionAvailable(String str) {
        this.mLog.i(String.format(Locale.US, "onSessionAvailable: sessionId: %s", str));
        this.mAvailableSessions.add(str);
    }

    public void onSessionRemoved(String str) {
        this.mLog.i(String.format(Locale.US, "onSessionRemoved: sessionId: %s", str));
        removeSessionIdToDomainMapping(str);
        this.mAvailableSessions.remove(str);
        if (this.mAvailableSessions.isEmpty()) {
            this.mLog.i("Invoking onLastSessionRemoved()");
            this.mRtcscServiceHandler.onLastSessionRemoved();
        }
    }

    public void putDomainToListenerMapping(String str, IRtcscAppClientListener iRtcscAppClientListener) {
        this.mLog.i(String.format(Locale.US, "putDomainToListenerMapping: sessionDomain: %s, listener: %s", str, iRtcscAppClientListener));
        this.mAppClientListenersMap.put(str, iRtcscAppClientListener);
    }

    public void putSessionIdToDomainMapping(String str, String str2) {
        this.mLog.i(String.format(Locale.US, "putSessionIdToDomainMapping: sessionId: %s, sessionDomain: %s", str, str2));
        this.mSessionIdToDomainMap.put(str, str2);
    }

    public void removeDomainToListenerMapping(String str) {
        this.mLog.i(String.format(Locale.US, "removeDomainToListenerMapping: sessionDomain: %s", str));
        this.mAppClientListenersMap.remove(str);
    }

    public void removeSessionIdToDomainMapping(String str) {
        this.mLog.i(String.format(Locale.US, "removeSessionIdToDomainMapping: sessionId: %s", str));
        this.mSessionIdToDomainMap.remove(str);
    }
}
