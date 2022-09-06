package com.amazon.alexa.biloba.utils;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
/* loaded from: classes6.dex */
public class PermissionUtils {
    @VisibleForTesting
    PermissionsDecisionListener permissionsDecisionListener = null;

    public boolean hasAccessFineLocationPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public void locationPermissionReceived(boolean z) {
        PermissionsDecisionListener permissionsDecisionListener = this.permissionsDecisionListener;
        if (permissionsDecisionListener != null) {
            permissionsDecisionListener.onPermissionReceived(z);
        }
    }

    public void removePermissionReceiveListener() {
        this.permissionsDecisionListener = null;
    }

    public void setPermissionsDecisionListener(PermissionsDecisionListener permissionsDecisionListener) {
        this.permissionsDecisionListener = permissionsDecisionListener;
    }
}
