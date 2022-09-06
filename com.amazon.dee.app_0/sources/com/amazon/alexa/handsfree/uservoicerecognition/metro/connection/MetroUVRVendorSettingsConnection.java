package com.amazon.alexa.handsfree.uservoicerecognition.metro.connection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.EnrollmentStatusCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.MetroResponseCallback;
import com.magiear.handsfree.util.IUVRVendorSettings;
import com.magiear.handsfree.util.ParamDefinition;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class MetroUVRVendorSettingsConnection implements ServiceConnection {
    private static final String VENDOR_SETTINGS_SERVICE_NOT_CONNECTED = "%s: vendor settings service not connected";
    private final EnrollmentStateProvider mEnrollmentStateProvider;
    private IUVRVendorSettings mIUVRVendorSettings;
    private boolean mIsVendorSettingsServiceBound;
    private static final String TAG = MetroUVRVendorSettingsConnection.class.getSimpleName();
    private static final String SERVICE_NAME = IUVRVendorSettings.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    public MetroUVRVendorSettingsConnection(@NonNull EnrollmentStateProvider enrollmentStateProvider) {
        this.mEnrollmentStateProvider = enrollmentStateProvider;
    }

    @NonNull
    private Bundle getUserInfoBundle(@NonNull UserInfo userInfo) {
        Bundle bundle = new Bundle();
        bundle.putString(ParamDefinition.KEY_ENROLL_USER_INFO_ID, userInfo.getUserId());
        return bundle;
    }

    public void disableAntiSpoofVerification(@NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            metroResponseCallback.onServiceNotConnected("disableAntiSpoofVerification", SERVICE_NAME);
        } else {
            this.mIUVRVendorSettings.disableAntiSpoofVerification(metroResponseCallback);
        }
    }

    public void disableUVR(@NonNull UserInfo userInfo, @NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            metroResponseCallback.onServiceNotConnected("disableUVR", SERVICE_NAME);
        } else {
            this.mIUVRVendorSettings.disableUVR(getUserInfoBundle(userInfo), metroResponseCallback);
        }
    }

    public void enableAntiSpoofVerification(@NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            metroResponseCallback.onServiceNotConnected("enableAntiSpoofVerification", SERVICE_NAME);
        } else {
            this.mIUVRVendorSettings.enableAntiSpoofVerification(metroResponseCallback);
        }
    }

    public void enableUVR(@NonNull UserInfo userInfo, @NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            metroResponseCallback.onServiceNotConnected("enableUVR", SERVICE_NAME);
        } else {
            this.mIUVRVendorSettings.enableUVR(getUserInfoBundle(userInfo), metroResponseCallback);
        }
    }

    public List<Bundle> getEnrolledUsers() throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            Log.e(TAG, String.format(VENDOR_SETTINGS_SERVICE_NOT_CONNECTED, "getEnrolledUsers"));
            return new ArrayList();
        }
        return this.mIUVRVendorSettings.getEnrolledUsers();
    }

    @VisibleForTesting
    protected IUVRVendorSettings getServiceFromBinder(@NonNull IBinder iBinder) {
        return IUVRVendorSettings.Stub.asInterface(iBinder);
    }

    public boolean isAntiSpoofEnabled() throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            Log.e(TAG, String.format(VENDOR_SETTINGS_SERVICE_NOT_CONNECTED, "isAntiSpoofEnabled"));
            return false;
        }
        return this.mIUVRVendorSettings.isAntiSpoofEnabled();
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
        boolean isUVREnrolled = this.mIUVRVendorSettings.isUVREnrolled(getUserInfoBundle(userInfo));
        this.mEnrollmentStateProvider.setUserInfo(userInfo);
        this.mEnrollmentStateProvider.setEnrollmentState(isUVREnrolled);
        return isUVREnrolled;
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

    public void removeUVRModel(@NonNull UserInfo userInfo, @NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsVendorSettingsServiceBound) {
            metroResponseCallback.onServiceNotConnected("removeUVRModel", SERVICE_NAME);
            return;
        }
        this.mEnrollmentStateProvider.setUserInfo(userInfo);
        this.mIUVRVendorSettings.removeUVRModel(getUserInfoBundle(userInfo), new EnrollmentStatusCallback(metroResponseCallback, this.mEnrollmentStateProvider, false));
    }
}
