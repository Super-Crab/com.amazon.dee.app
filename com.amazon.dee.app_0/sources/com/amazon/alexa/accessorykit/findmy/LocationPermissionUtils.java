package com.amazon.alexa.accessorykit.findmy;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes6.dex */
public final class LocationPermissionUtils {
    private LocationPermissionUtils() {
    }

    public static boolean hasNoAccessFineLocationPermission(Context context) {
        Preconditions.notNull(context, "context");
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0;
    }
}
