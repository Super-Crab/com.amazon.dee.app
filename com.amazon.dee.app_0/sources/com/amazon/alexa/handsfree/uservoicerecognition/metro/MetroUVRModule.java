package com.amazon.alexa.handsfree.uservoicerecognition.metro;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.connection.MetroUVRConnector;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.enrollment.MetroUVREnroller;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.tuningsetting.MetroUVRTuningSettings;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.vendorsetting.MetroUVRVendorSettings;
/* loaded from: classes8.dex */
public class MetroUVRModule {
    private static MetroUVRModule sInstance;
    private UVRContract mUVRContract;

    @VisibleForTesting
    MetroUVRModule() {
    }

    public static synchronized MetroUVRModule getInstance() {
        MetroUVRModule metroUVRModule;
        synchronized (MetroUVRModule.class) {
            if (sInstance == null) {
                sInstance = new MetroUVRModule();
            }
            metroUVRModule = sInstance;
        }
        return metroUVRModule;
    }

    @NonNull
    public UVRContract getUVRContract(@NonNull Context context) {
        if (this.mUVRContract == null) {
            MetroUVRConnector metroUVRConnector = new MetroUVRConnector(new EnrollmentStateProvider(context));
            this.mUVRContract = new UVRContract(new MetroUVREnroller(metroUVRConnector), new MetroUVRTuningSettings(metroUVRConnector), new MetroUVRVendorSettings(metroUVRConnector), metroUVRConnector);
        }
        return this.mUVRContract;
    }
}
