package com.amazon.alexa.wakeword.speakerverification.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes11.dex */
public class SpeakerVerificationModelDownloadReceiver extends BroadcastReceiver {
    private static final String TAG = SpeakerVerificationModelDownloadReceiver.class.getSimpleName();
    private final Initializer mInitializer;

    public SpeakerVerificationModelDownloadReceiver() {
        this(InitializerProvider.getInitializer());
    }

    @VisibleForTesting
    void enqueueWork(@NonNull Context context) {
        SpeakerVerificationModelDownloadJobIntentService.enqueueWork(context);
    }

    @VisibleForTesting
    AMPDInformationProvider getAMPDInformationProvider(@NonNull Context context) {
        return AMPDInformationProvider.getInstance(context);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (!getAMPDInformationProvider(context).isEdgeSV()) {
            Log.i(TAG, "onReceive: not an edgeSV device, skipping model download!");
            return;
        }
        this.mInitializer.initialize(context);
        enqueueWork(context);
    }

    @VisibleForTesting
    SpeakerVerificationModelDownloadReceiver(@NonNull Initializer initializer) {
        this.mInitializer = initializer;
    }
}
