package com.amazon.communication;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
/* loaded from: classes12.dex */
public class ConnectivityManagerWrapperImpl implements ConnectivityManagerWrapper {
    private final ConnectivityManager mConnectivityManager;
    private final TelephonyManager mTelephonyManager;

    public ConnectivityManagerWrapperImpl(ConnectivityManager connectivityManager, TelephonyManager telephonyManager) {
        this.mConnectivityManager = connectivityManager;
        this.mTelephonyManager = telephonyManager;
    }

    @Override // com.amazon.communication.ConnectivityManagerWrapper
    public NetworkInfo getActiveNetworkInfo() {
        return this.mConnectivityManager.getActiveNetworkInfo();
    }

    @Override // com.amazon.communication.ConnectivityManagerWrapper
    public NetworkInfo getNetworkInfo(int i) {
        return this.mConnectivityManager.getNetworkInfo(i);
    }

    @Override // com.amazon.communication.ConnectivityManagerWrapper
    public String getSimCountryAndNetworkCodes() {
        return this.mTelephonyManager.getSimOperator();
    }

    @Override // com.amazon.communication.ConnectivityManagerWrapper
    public String getTowerCountryAndNetworkCodes() {
        return this.mTelephonyManager.getNetworkOperator();
    }

    @Override // com.amazon.communication.ConnectivityManagerWrapper
    public void setNetworkPreference(int i) {
        this.mConnectivityManager.setNetworkPreference(i);
    }
}
