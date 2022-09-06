package com.amazon.alexa.handsfree.uservoicerecognition.metro.tuningsetting;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.MetroResponseCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.MetroResultCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.connection.MetroUVRConnector;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.connection.MetroUVRTuningSettingsConnection;
import com.magiear.handsfree.util.ParamDefinition;
/* loaded from: classes8.dex */
public class MetroUVRTuningSettings implements UVRTuningSettings {
    private static final String TAG = "MetroUVRTuningSettings";
    private final MetroUVRTuningSettingsConnection mMetroUVRTuningSettingsConnection;

    public MetroUVRTuningSettings(@NonNull MetroUVRConnector metroUVRConnector) {
        this((MetroUVRTuningSettingsConnection) metroUVRConnector.getTuningSettingsConnection());
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getAntiSpoofThreshold(@NonNull ResultCallback<Double> resultCallback) {
        try {
            this.mMetroUVRTuningSettingsConnection.getAntiSpoofThreshold(new MetroResultCallback(resultCallback, ParamDefinition.KEY_TUNING_PARAM_ANTI_SPOOF_THRESHOLD));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("getAntiSpoofThreshold: %s", e.getMessage()));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getSecondStageThreshold(@NonNull ResultCallback<Integer> resultCallback) {
        try {
            this.mMetroUVRTuningSettingsConnection.getSecondStageThreshold(new MetroResultCallback(resultCallback, ParamDefinition.KEY_TUNING_PARAM_SECOND_STAGE_THRESHOLD));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("getSecondStageThreshold: %s", e.getMessage()));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getTrainingThreshold(@NonNull ResultCallback<Integer> resultCallback) {
        try {
            this.mMetroUVRTuningSettingsConnection.getTrainingThreshold(new MetroResultCallback(resultCallback, ParamDefinition.KEY_TUNING_PARAM_TRAINING_THRESHOLD));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("getTrainingThreshold: %s", e.getMessage()));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void getUserConfidenceLevel(@NonNull ResultCallback<Integer> resultCallback) {
        try {
            this.mMetroUVRTuningSettingsConnection.getUserConfidenceLevel(new MetroResultCallback(resultCallback, ParamDefinition.KEY_TUNING_PARAM_USER_CONF_LEVEL));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("getUserConfidenceLevel: %s", e.getMessage()));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setAntiSpoofThreshold(double d, @NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRTuningSettingsConnection.setAntiSpoofThreshold(d, new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("setAntiSpoofThreshold: %s", e.getMessage()));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setSecondStageThreshold(int i, @NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRTuningSettingsConnection.setSecondStageThreshold(i, new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("setSecondStageThreshold: %s", e.getMessage()));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setTrainingThreshold(int i, @NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRTuningSettingsConnection.setTrainingThreshold(i, new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("setTrainingThreshold: %s", e.getMessage()));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setUserConfidenceLevel(int i, @NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRTuningSettingsConnection.setUserConfidenceLevel(i, new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("setUserConfidenceLevel: %s", e.getMessage()));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRTuningSettings
    public void setUtteranceTrainingTimeout(int i) {
        try {
            this.mMetroUVRTuningSettingsConnection.setUtteranceTrainingTimeout(i);
        } catch (RemoteException e) {
            Log.e(TAG, String.format("setUtteranceTrainingTimeout: %s", e.getMessage()));
        }
    }

    @VisibleForTesting
    MetroUVRTuningSettings(@NonNull MetroUVRTuningSettingsConnection metroUVRTuningSettingsConnection) {
        this.mMetroUVRTuningSettingsConnection = metroUVRTuningSettingsConnection;
    }
}
