package com.amazon.alexa.wakeword.speakerverification.profile.appupdate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes11.dex */
public class AppUpdateBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = AppUpdateBroadcastReceiver.class.getSimpleName();
    private final Initializer mInitializer = InitializerProvider.getInitializer();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "receive app update");
        if (!AMPDInformationProvider.getInstance(context).isEdgeSV()) {
            Log.i(TAG, "SV Profile Regeneration shouldn't start on not an edge SV device");
            return;
        }
        this.mInitializer.initialize(context);
        if (((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context.getApplicationContext(), FalcoProtocolComponent.class)).enrollmentTypeResolverLazy().mo358get().getSpeakerVerificationEnrollmentType() != EnrollmentType._1PSV) {
            Log.i(TAG, "SV Profile Regeneration shouldn't start for 3PSV enrolled customer");
        } else {
            AppUpdateService.enqueueWork(context, intent);
        }
    }
}
