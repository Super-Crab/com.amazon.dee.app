package com.amazon.clouddrive.android.core.interfaces;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface ClientInfo {
    @NonNull
    String getAppVersionName();

    @NonNull
    String getClientId();

    @NonNull
    String getDeviceName();

    @NonNull
    OperatingSystem getOperatingSystem();

    @NonNull
    String getSessionId();
}
