package com.amazon.deecomms.conversation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.amazon.alexa.protocols.jobs.JobIDProvider;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class FireOSDirectiveReceiver extends BroadcastReceiver {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, FireOSDirectiveReceiver.class);

    private void schedule(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, FireOSDirectiveHandlerService.class, ((JobIDProvider) GeneratedOutlineSupport1.outline20(JobIDProvider.class)).getJobId(FireOSDirectiveHandlerService.class), intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LOG.d("Received FireOS Directive broadcast");
        schedule(context, intent);
    }
}
