package com.amazon.alexa.accessory.avsclient;

import android.content.Context;
import android.telephony.TelephonyManager;
import androidx.core.content.PermissionChecker;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
/* loaded from: classes.dex */
public final class PhoneStateScoPrioritizer implements ScoPrioritizer {
    private final Context context;
    private final TelephonyManager telephonyManager;

    public PhoneStateScoPrioritizer(Context context) {
        this.context = context;
        this.telephonyManager = (TelephonyManager) context.getSystemService("phone");
    }

    @Override // com.amazon.alexa.accessory.sco.ScoPrioritizer
    public boolean shouldUseSco() {
        return PermissionChecker.checkSelfPermission(this.context, "android.permission.READ_PHONE_STATE") == 0 && this.telephonyManager.getCallState() != 0;
    }
}
