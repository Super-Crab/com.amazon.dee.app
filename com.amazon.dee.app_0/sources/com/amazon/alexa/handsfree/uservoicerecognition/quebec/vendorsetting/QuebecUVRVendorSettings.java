package com.amazon.alexa.handsfree.uservoicerecognition.quebec.vendorsetting;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback.QuebecResponseCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.connection.QuebecUVRConnector;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.connection.QuebecUVRVendorSettingsConnection;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class QuebecUVRVendorSettings implements UVRVendorSettings {
    private static final String TAG = "QuebecUVRVendorSettings";
    private final QuebecUVRVendorSettingsConnection mQuebecUVRVendorSettingsConnection;

    public QuebecUVRVendorSettings(@NonNull QuebecUVRConnector quebecUVRConnector) {
        this((QuebecUVRVendorSettingsConnection) quebecUVRConnector.getVendorSettingsConnection());
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void disableAntiSpoofVerification(@NonNull ResponseCallback responseCallback) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void disableUVR(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback) {
        try {
            this.mQuebecUVRVendorSettingsConnection.disableUVR(userInfo, new QuebecResponseCallback(responseCallback));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("disableUVR RemoteException: %s", message));
            if (message == null) {
                return;
            }
            responseCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void enableAntiSpoofVerification(@NonNull ResponseCallback responseCallback) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void enableUVR(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback) {
        try {
            this.mQuebecUVRVendorSettingsConnection.enableUVR(userInfo, new QuebecResponseCallback(responseCallback));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("enableUVR RemoteException: %s", message));
            if (message == null) {
                return;
            }
            responseCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    @NonNull
    public List<UserInfo> getEnrolledUsers() {
        return new ArrayList();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isAntiSpoofEnabled() {
        return true;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isEnrollmentResumeSupported() {
        return this.mQuebecUVRVendorSettingsConnection.isEnrollmentResumeSupported();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isUVRAvailable() {
        return this.mQuebecUVRVendorSettingsConnection.isUVRAvailable();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isUVREnrolled(@NonNull UserInfo userInfo) {
        try {
            return this.mQuebecUVRVendorSettingsConnection.isUVREnrolled(userInfo);
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
            this.mQuebecUVRVendorSettingsConnection.removeUVRModel(userInfo, new QuebecResponseCallback(responseCallback));
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
    QuebecUVRVendorSettings(@NonNull QuebecUVRVendorSettingsConnection quebecUVRVendorSettingsConnection) {
        this.mQuebecUVRVendorSettingsConnection = quebecUVRVendorSettingsConnection;
    }
}
