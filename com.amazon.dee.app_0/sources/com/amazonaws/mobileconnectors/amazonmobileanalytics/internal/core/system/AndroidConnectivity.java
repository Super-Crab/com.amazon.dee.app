package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import com.android.tools.r8.GeneratedOutlineSupport1;
@Deprecated
/* loaded from: classes13.dex */
public class AndroidConnectivity implements Connectivity {
    private static final String TAG = "AndroidConnectivity";
    private Context context;
    protected boolean hasMobile;
    protected boolean hasWifi;
    protected boolean hasWired;
    protected boolean inAirplaneMode;

    public AndroidConnectivity(Context context) {
        this.context = context;
    }

    private void determineAvailability() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
        this.inAirplaneMode = Settings.System.getInt(this.context.getContentResolver(), "airplane_mode_on", 0) != 0;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Airplane mode: ");
        outline107.append(this.inAirplaneMode);
        outline107.toString();
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        this.hasWifi = false;
        this.hasWired = false;
        this.hasMobile = connectivityManager != null;
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.isConnectedOrConnecting()) {
                int type = activeNetworkInfo.getType();
                this.hasWired = type == 9;
                this.hasWifi = type == 1 || type == 6;
                this.hasMobile = type == 0 || type == 4 || type == 5 || type == 2 || type == 3;
            } else {
                this.hasMobile = false;
            }
        }
        Object[] objArr = new Object[1];
        objArr[0] = this.hasWifi ? "On Wifi" : this.hasMobile ? "On Mobile" : "No network connectivity";
        String.format("Device Connectivity (%s)", objArr);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Connectivity
    public boolean hasWAN() {
        return this.hasMobile && !this.inAirplaneMode;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Connectivity
    public boolean hasWifi() {
        return this.hasWifi;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Connectivity
    public boolean hasWired() {
        return this.hasWired;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Connectivity
    public boolean isConnected() {
        determineAvailability();
        return hasWifi() || hasWAN() || hasWired();
    }
}
