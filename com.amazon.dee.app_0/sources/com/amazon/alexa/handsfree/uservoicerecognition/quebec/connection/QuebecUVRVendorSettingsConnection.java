package com.amazon.alexa.handsfree.uservoicerecognition.quebec.connection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback.EnrollmentStatusCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback.QuebecResponseCallback;
import com.quicinc.voice.activation.IUVRSettingsService;
/* loaded from: classes8.dex */
public class QuebecUVRVendorSettingsConnection implements ServiceConnection {
    private static final String VENDOR_SETTINGS_SERVICE_CRASHED = "%s: vendor settings service has crashed";
    private static final String VENDOR_SETTINGS_SERVICE_NOT_CONNECTED = "%s: vendor settings service not connected";
    private final EnrollmentStateProvider mEnrollmentStateProvider;
    private IUVRSettingsService mIUVRVendorSettings;
    private boolean mIsVendorSettingsServiceBound;
    private static final String TAG = QuebecUVRVendorSettingsConnection.class.getSimpleName();
    private static final String SERVICE = IUVRSettingsService.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    public QuebecUVRVendorSettingsConnection(@NonNull EnrollmentStateProvider enrollmentStateProvider) {
        this.mEnrollmentStateProvider = enrollmentStateProvider;
    }

    @NonNull
    private Bundle getUserInfoBundle(@NonNull UserInfo userInfo) {
        Bundle bundle = new Bundle();
        bundle.putString(QuebecAPIConstants.UVR_USER_INFO_ID, userInfo.getUserId());
        return bundle;
    }

    public void disableUVR(@NonNull UserInfo userInfo, @NonNull QuebecResponseCallback quebecResponseCallback) throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            quebecResponseCallback.onServiceNotConnected("disableUVR", SERVICE);
        } else {
            this.mIUVRVendorSettings.disableUVR(quebecResponseCallback, getUserInfoBundle(userInfo));
        }
    }

    public void enableUVR(@NonNull UserInfo userInfo, @NonNull QuebecResponseCallback quebecResponseCallback) throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            quebecResponseCallback.onServiceNotConnected("enableUVR", SERVICE);
        } else {
            this.mIUVRVendorSettings.enableUVR(quebecResponseCallback, getUserInfoBundle(userInfo));
        }
    }

    @VisibleForTesting
    protected IUVRSettingsService getServiceFromBinder(@NonNull IBinder iBinder) {
        return IUVRSettingsService.Stub.asInterface(iBinder);
    }

    public boolean isEnrollmentResumeSupported() {
        return true;
    }

    public boolean isUVRAvailable() {
        return true;
    }

    public boolean isUVREnrolled(@NonNull UserInfo userInfo) throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            Log.d(TAG, String.format(VENDOR_SETTINGS_SERVICE_NOT_CONNECTED, "isUVREnrolled"));
            return this.mEnrollmentStateProvider.getPreviousEnrollmentState(userInfo);
        }
        try {
            boolean isUVREnrolled = this.mIUVRVendorSettings.isUVREnrolled(getUserInfoBundle(userInfo));
            this.mEnrollmentStateProvider.setUserInfo(userInfo);
            this.mEnrollmentStateProvider.setEnrollmentState(isUVREnrolled);
            return isUVREnrolled;
        } catch (DeadObjectException unused) {
            Log.d(TAG, String.format(VENDOR_SETTINGS_SERVICE_CRASHED, "isUVREnrolled"));
            return this.mEnrollmentStateProvider.getPreviousEnrollmentState(userInfo);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @NonNull IBinder iBinder) {
        Log.d(TAG, "onServiceConnected");
        this.mIUVRVendorSettings = getServiceFromBinder(iBinder);
        this.mIsVendorSettingsServiceBound = true;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@NonNull ComponentName componentName) {
        Log.d(TAG, MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED);
        this.mIUVRVendorSettings = null;
        this.mIsVendorSettingsServiceBound = false;
    }

    public void removeUVRModel(@NonNull UserInfo userInfo, @NonNull QuebecResponseCallback quebecResponseCallback) throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            quebecResponseCallback.onServiceNotConnected("removeUVRModel", SERVICE);
            return;
        }
        this.mEnrollmentStateProvider.setUserInfo(userInfo);
        this.mIUVRVendorSettings.removeUVRModel(new EnrollmentStatusCallback(quebecResponseCallback, this.mEnrollmentStateProvider, false), getUserInfoBundle(userInfo));
    }

    public void setEnrollmentTimeout(@NonNull Integer num, @NonNull QuebecResponseCallback quebecResponseCallback) throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            quebecResponseCallback.onServiceNotConnected("setEnrollmentTimeout", SERVICE);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(QuebecAPIConstants.UVR_UTTERANCE_TRAINING_TIMEOUT, num.intValue());
        this.mIUVRVendorSettings.setParams(quebecResponseCallback, bundle);
    }
}
