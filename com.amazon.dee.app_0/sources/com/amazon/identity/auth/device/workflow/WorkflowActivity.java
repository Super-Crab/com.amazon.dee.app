package com.amazon.identity.auth.device.workflow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.RequestManager;
import com.amazon.identity.auth.device.ResponseManager;
import com.amazon.identity.auth.map.device.utils.MAPLog;
@SuppressLint({"Registered"})
/* loaded from: classes12.dex */
public final class WorkflowActivity extends Activity {
    private static final String LOG_TAG = WorkflowActivity.class.getName();

    public static void handleResponseUri(Uri uri, Activity activity, String str) {
        if (uri == null) {
            MAPLog.i(str, "uri is null onCreate - closing activity");
            return;
        }
        try {
            if (RequestManager.isInteractiveRequest(uri)) {
                MAPLog.d(str, "Receiving response for interactive request");
                String requestIdFromResponseUri = RequestManager.getRequestIdFromResponseUri(uri);
                MAPLog.d(str, "Receiving response for request " + requestIdFromResponseUri);
                ResponseManager.getInstance().putPendingResponse(requestIdFromResponseUri, uri);
            } else {
                MAPLog.d(str, "Receiving response for auth request");
                if (!RequestManager.getInstance().handleResponse(uri, activity.getApplicationContext())) {
                    MAPLog.pii(str, "Could not find active request for redirect URI", uri.toString());
                }
            }
        } catch (AuthError e) {
            MAPLog.pii(str, "Could not handle response URI", uri.toString(), e);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MAPLog.d(LOG_TAG, "onCreate");
        handleResponseUri(getIntent().getData(), this, LOG_TAG);
        MAPLog.d(LOG_TAG, "finish");
        finish();
    }
}
