package com.amazon.alexa.applink.launcher;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
/* loaded from: classes6.dex */
public class AppLauncher {
    private static final String TAG = "AppLauncher";
    private final Context context;

    public AppLauncher(@NonNull Context context) {
        this.context = context;
    }

    public boolean launchExternalApp(@NonNull Intent intent) {
        try {
            this.context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "Activity not found");
            String str = "Error message: " + e.getMessage();
            return false;
        }
    }
}
