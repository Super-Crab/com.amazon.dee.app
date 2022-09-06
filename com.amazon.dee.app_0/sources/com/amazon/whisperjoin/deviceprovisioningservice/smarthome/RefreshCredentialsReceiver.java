package com.amazon.whisperjoin.deviceprovisioningservice.smarthome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.constant.DSHSConstants;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.commons.lang.Validate;
/* loaded from: classes13.dex */
public class RefreshCredentialsReceiver extends BroadcastReceiver {
    private static final String TAG = RefreshCredentialsReceiver.class.getSimpleName();
    private CredSyncJobScheduler mCredSyncJobScheduler;

    public RefreshCredentialsReceiver() {
        this(new CredSyncJobSchedulerImpl());
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Validate.notNull(intent, "Intent cannot be null");
        Validate.notNull(context, "Context cannot be null");
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Intent received is ");
        outline107.append(intent.getAction());
        WJLog.i(str, outline107.toString());
        if (!DSHSConstants.ZIGBEE_REFRESH_CREDENTIALS_INTENT_ACTION.equals(intent.getAction())) {
            WJLog.d(TAG, "Invalid onReceive parameters, ABORTING!");
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Intent = ");
            outline1072.append(intent.getAction());
            WJLog.d(str2, outline1072.toString());
            return;
        }
        this.mCredSyncJobScheduler.scheduleCredSyncRun(context, AssociatedCredentialsSyncService.INTENT_EXTRA_SOURCE_VAL_DSHS);
    }

    RefreshCredentialsReceiver(CredSyncJobScheduler credSyncJobScheduler) {
        this.mCredSyncJobScheduler = credSyncJobScheduler;
    }
}
