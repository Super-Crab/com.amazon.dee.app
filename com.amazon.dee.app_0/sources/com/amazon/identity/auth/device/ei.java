package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.telephony.TelephonyManager;
/* compiled from: DCP */
@SuppressLint({"MissingPermission"})
/* loaded from: classes12.dex */
public class ei {
    private TelephonyManager lu;

    public ei(ed edVar) {
        this.lu = (TelephonyManager) edVar.getSystemService("phone");
    }

    public String dY() {
        return this.lu.getNetworkCountryIso();
    }
}
