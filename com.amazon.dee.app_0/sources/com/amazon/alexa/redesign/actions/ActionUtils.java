package com.amazon.alexa.redesign.actions;

import android.content.Context;
import android.content.Intent;
/* loaded from: classes10.dex */
public final class ActionUtils {
    private ActionUtils() {
    }

    public static boolean isIntentSafe(Intent intent, Context context) {
        return context.getPackageManager().queryIntentActivities(intent, 0).size() > 0;
    }
}
