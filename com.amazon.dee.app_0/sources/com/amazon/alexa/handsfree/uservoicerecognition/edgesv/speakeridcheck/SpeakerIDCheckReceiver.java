package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.speakeridcheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class SpeakerIDCheckReceiver extends BroadcastReceiver {
    private static final String TAG = "SpeakerIDCheckReceiver";
    private final Initializer mInitializer;

    public SpeakerIDCheckReceiver() {
        this(InitializerProvider.getInitializer());
    }

    @VisibleForTesting
    void enqueueSpeakerIDCheck(@NonNull Context context) {
        SpeakerIDCheckJobIntentService.enqueueWork(context);
    }

    @NonNull
    @VisibleForTesting
    AMPDInformationProvider getAMPDInformationProvider(@NonNull Context context) {
        return AMPDInformationProvider.getInstance(context);
    }

    @Nullable
    @VisibleForTesting
    EnrollmentTypeResolver getEnrollmentTypeResolver(@NonNull Context context) {
        return ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy().mo358get();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (!getAMPDInformationProvider(context).isEdgeSV()) {
            Log.i(TAG, "onReceive: not an edgeSV device, skipping speaker id check!");
            return;
        }
        this.mInitializer.initialize(context);
        if (getEnrollmentTypeResolver(context).getSpeakerVerificationEnrollmentType() != EnrollmentType._1PSV) {
            Log.i(TAG, "onReceive: not an 1psv device, skipping speaker id check!");
        } else {
            enqueueSpeakerIDCheck(context);
        }
    }

    @VisibleForTesting
    SpeakerIDCheckReceiver(@NonNull Initializer initializer) {
        this.mInitializer = initializer;
    }
}
