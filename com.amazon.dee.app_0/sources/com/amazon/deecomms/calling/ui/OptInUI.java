package com.amazon.deecomms.calling.ui;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
/* loaded from: classes12.dex */
public class OptInUI {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OptInUI.class);

    public boolean isNeeded(@NonNull SipClientState sipClientState, @NonNull boolean z) {
        if (z) {
            return !sipClientState.isEnhancedProcessingSettingEnabled();
        }
        return false;
    }

    public void show(@NonNull String str, @NonNull Activity activity) {
        LOG.i("Showing opt-in screen");
        Intent intent = new Intent(activity, CallPermissionActivity.class);
        intent.putExtra(Constants.SHOW_CALL_UI, str);
        activity.startActivityForResult(intent, 200);
    }
}
