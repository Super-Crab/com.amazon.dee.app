package com.amazon.alexa.crashreporting;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
/* loaded from: classes6.dex */
public final class IntentionalCrashActivity extends Activity {
    private static final String TAG = IntentionalCrashActivity.class.getSimpleName();

    /* loaded from: classes6.dex */
    static final class IntentionalCrashException extends RuntimeException {
        IntentionalCrashException() {
            super("I'm crashing, because you asked me to do so!");
        }
    }

    private boolean isAllowedToIntentionallyCrash() {
        EnvironmentService environmentService = (EnvironmentService) GeneratedOutlineSupport1.outline20(EnvironmentService.class);
        if (environmentService.isDebugBuild()) {
            return true;
        }
        for (String str : Arrays.asList("prod", "prodPhoenix")) {
            if (str.equalsIgnoreCase(environmentService.getBuildFlavor())) {
                Log.w(TAG, "Test crashes are not allowed on production release builds.");
                return false;
            }
        }
        return true;
    }

    @Override // android.app.Activity
    @SuppressLint({"MissingSuperCall"})
    protected void onCreate(Bundle bundle) {
        if (!isAllowedToIntentionallyCrash()) {
            finish();
            return;
        }
        throw new IntentionalCrashException();
    }
}
