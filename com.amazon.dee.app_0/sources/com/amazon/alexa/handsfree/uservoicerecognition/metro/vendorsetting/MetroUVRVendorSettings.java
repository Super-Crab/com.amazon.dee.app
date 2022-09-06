package com.amazon.alexa.handsfree.uservoicerecognition.metro.vendorsetting;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.MetroResponseCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.connection.MetroUVRConnector;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.connection.MetroUVRVendorSettingsConnection;
import com.magiear.handsfree.util.ParamDefinition;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class MetroUVRVendorSettings implements UVRVendorSettings {
    private static final String TAG = "MetroUVRVendorSettings";
    private final MetroUVRVendorSettingsConnection mMetroUVRVendorSettingsConnection;

    public MetroUVRVendorSettings(@NonNull MetroUVRConnector metroUVRConnector) {
        this((MetroUVRVendorSettingsConnection) metroUVRConnector.getVendorSettingsConnection());
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void disableAntiSpoofVerification(@NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRVendorSettingsConnection.disableAntiSpoofVerification(new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("disableAntiSpoofVerification: %s", message));
            if (message == null) {
                return;
            }
            responseCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void disableUVR(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRVendorSettingsConnection.disableUVR(userInfo, new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("disableUVR: %s", message));
            if (message == null) {
                return;
            }
            responseCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void enableAntiSpoofVerification(@NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRVendorSettingsConnection.enableAntiSpoofVerification(new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("enableAntiSpoofVerification: %s", message));
            if (message == null) {
                return;
            }
            responseCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void enableUVR(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRVendorSettingsConnection.enableUVR(userInfo, new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("enableUVR: %s", message));
            if (message == null) {
                return;
            }
            responseCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    @NonNull
    public List<UserInfo> getEnrolledUsers() {
        try {
            ArrayList arrayList = new ArrayList();
            for (Bundle bundle : this.mMetroUVRVendorSettingsConnection.getEnrolledUsers()) {
                String string = bundle.getString(ParamDefinition.KEY_ENROLL_USER_INFO_ID);
                if (string != null) {
                    arrayList.add(new UserInfo(string));
                }
            }
            return arrayList;
        } catch (RemoteException e) {
            Log.e(TAG, String.format("getEnrolledUsers: %s", e.getMessage()));
            return new ArrayList();
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isAntiSpoofEnabled() {
        try {
            return this.mMetroUVRVendorSettingsConnection.isAntiSpoofEnabled();
        } catch (RemoteException e) {
            Log.e(TAG, String.format("isAntiSpoofEnabled: %s", e.getMessage()));
            return false;
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isEnrollmentResumeSupported() {
        return this.mMetroUVRVendorSettingsConnection.isEnrollmentResumeSupported();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isUVRAvailable() {
        return this.mMetroUVRVendorSettingsConnection.isUVRAvailable();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isUVREnrolled(@NonNull UserInfo userInfo) {
        try {
            return this.mMetroUVRVendorSettingsConnection.isUVREnrolled(userInfo);
        } catch (RemoteException e) {
            Log.e(TAG, String.format("isUVREnrolled: %s", e.getMessage()));
            return false;
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isUVRMandatory() {
        return true;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void removeUVRModel(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRVendorSettingsConnection.removeUVRModel(userInfo, new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("removeUVRModel: %s", message));
            if (message == null) {
                return;
            }
            responseCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean shouldShowContinueConfirmationDialog() {
        return true;
    }

    @VisibleForTesting
    MetroUVRVendorSettings(@NonNull MetroUVRVendorSettingsConnection metroUVRVendorSettingsConnection) {
        this.mMetroUVRVendorSettingsConnection = metroUVRVendorSettingsConnection;
    }
}
