package com.amazon.communication;

import android.net.NetworkInfo;
/* loaded from: classes12.dex */
public interface ConnectivityManagerWrapper {
    NetworkInfo getActiveNetworkInfo();

    NetworkInfo getNetworkInfo(int i);

    String getSimCountryAndNetworkCodes();

    String getTowerCountryAndNetworkCodes();

    void setNetworkPreference(int i);
}
