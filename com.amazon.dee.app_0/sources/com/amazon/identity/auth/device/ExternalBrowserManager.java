package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class ExternalBrowserManager {
    private static final String BROWSER_ID_SUFFIX = ".amazon.auth";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.ExternalBrowserManager";

    private Intent getIntent(String str, Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(268435456);
        intent.addFlags(1073741824);
        intent.putExtra("com.android.browser.application_id", context.getPackageName() + BROWSER_ID_SUFFIX);
        return intent;
    }

    public void openUrl(AbstractRequest abstractRequest, String str, Context context) throws AuthError {
        CompatibilityUtil.assertCorrectManifestIntegration(context);
        Intent intent = getIntent(str, context);
        MAPLog.i(LOG_TAG, "Starting External Browser");
        try {
            abstractRequest.onStart();
            context.startActivity(intent);
        } catch (Exception e) {
            String str2 = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to Launch Browser: ");
            outline107.append(e.getMessage());
            MAPLog.e(str2, outline107.toString());
            throw new AuthError("Unable to Launch Browser.", e, AuthError.ERROR_TYPE.ERROR_UNKNOWN);
        }
    }
}
