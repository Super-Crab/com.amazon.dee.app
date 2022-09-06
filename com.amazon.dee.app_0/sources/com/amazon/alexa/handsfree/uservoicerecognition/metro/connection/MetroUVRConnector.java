package com.amazon.alexa.handsfree.uservoicerecognition.metro.connection;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.magiear.handsfree.util.Common;
/* loaded from: classes8.dex */
public class MetroUVRConnector extends UVRConnector {
    private static final String TAG = "MetroUVRConnector";

    public MetroUVRConnector(@NonNull EnrollmentStateProvider enrollmentStateProvider) {
        super(new MetroUVRConnection(enrollmentStateProvider), new MetroUVRVendorSettingsConnection(enrollmentStateProvider), new MetroUVRTuningSettingsConnection());
    }

    private boolean bindToService(@NonNull Context context, @NonNull String str, @NonNull ServiceConnection serviceConnection) {
        Intent intent = new Intent(str);
        intent.setPackage(Common.HANDSFREE_ASSISTANT_PACKAGE_NAME);
        return context.bindService(intent, serviceConnection, 1);
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector
    protected void bindToEnrollmentService(@NonNull Context context) {
        Log.d(TAG, "Binding to enrollment service");
        bindToService(context, Common.INTENT_ACTION_UVR_ENROLLER, getEnrollmentConnection());
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector
    protected void bindToVendorSettingsService(@NonNull Context context) {
        Log.d(TAG, "Binding to vendor settings service");
        bindToService(context, Common.INTENT_ACTION_UVR_VENDOR_SETTINGS, getVendorSettingsConnection());
    }
}
