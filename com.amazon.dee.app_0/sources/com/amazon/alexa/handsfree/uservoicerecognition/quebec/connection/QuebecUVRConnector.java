package com.amazon.alexa.handsfree.uservoicerecognition.quebec.connection;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IInterface;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.quicinc.voice.activation.IUVREnrollmentService;
import com.quicinc.voice.activation.IUVRSettingsService;
/* loaded from: classes8.dex */
public class QuebecUVRConnector extends UVRConnector {
    private static final String TAG = "QuebecUVRConnector";

    public QuebecUVRConnector(@NonNull EnrollmentStateProvider enrollmentStateProvider) {
        super(new QuebecUVRConnection(enrollmentStateProvider), new QuebecUVRVendorSettingsConnection(enrollmentStateProvider), null);
    }

    private boolean bindToService(@NonNull Context context, @NonNull Class<? extends IInterface> cls, @NonNull ServiceConnection serviceConnection) {
        Intent intent = new Intent(cls.getName());
        intent.setPackage(cls.getPackage().getName());
        return context.bindService(intent, serviceConnection, 4097);
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector
    protected void bindToEnrollmentService(@NonNull Context context) {
        Log.d(TAG, "Binding to enrollment service");
        bindToService(context, IUVREnrollmentService.class, getEnrollmentConnection());
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector
    protected void bindToVendorSettingsService(@NonNull Context context) {
        Log.d(TAG, "Binding to vendor settings service");
        bindToService(context, IUVRSettingsService.class, getVendorSettingsConnection());
    }
}
