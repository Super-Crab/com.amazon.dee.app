package com.amazon.device.crashmanager.rtla;

import java.util.List;
/* loaded from: classes12.dex */
public interface RtlaCrashDetails {
    List<String> getActiveWebLabs();

    String getPageType();

    String getSessionId();

    String getSubPageType();

    String getUserAgent();
}
