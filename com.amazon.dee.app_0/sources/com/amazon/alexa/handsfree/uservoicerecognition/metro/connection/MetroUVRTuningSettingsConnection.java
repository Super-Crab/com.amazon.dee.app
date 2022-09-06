package com.amazon.alexa.handsfree.uservoicerecognition.metro.connection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.MetroResponseCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.MetroResultCallback;
import com.magiear.handsfree.util.IUVRTuningSettings;
import com.magiear.handsfree.util.ParamDefinition;
/* loaded from: classes8.dex */
public class MetroUVRTuningSettingsConnection implements ServiceConnection {
    private static final String TAG = MetroUVRTuningSettingsConnection.class.getSimpleName();
    private static final String TUNING_SETTINGS_SERVICE_NOT_CONNECTED = "%s: tuning settings service not connected";
    private IUVRTuningSettings mIUVRTuningSettings;
    private boolean mIsTuningSettingsServiceBound;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MetroUVRTuningSettingsConnection() {
    }

    private Bundle getDoubleParamBundle(@NonNull String str, double d) {
        Bundle bundle = new Bundle();
        bundle.putDouble(str, d);
        return bundle;
    }

    private Bundle getIntParamBundle(@NonNull String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(str, i);
        return bundle;
    }

    public void getAntiSpoofThreshold(@NonNull MetroResultCallback metroResultCallback) throws RemoteException {
        if (!this.mIsTuningSettingsServiceBound) {
            Log.e(TAG, String.format(TUNING_SETTINGS_SERVICE_NOT_CONNECTED, "getAntiSpoofThreshold"));
        } else {
            this.mIUVRTuningSettings.getTuningParam(metroResultCallback);
        }
    }

    public void getSecondStageThreshold(@NonNull MetroResultCallback metroResultCallback) throws RemoteException {
        if (!this.mIsTuningSettingsServiceBound) {
            Log.e(TAG, String.format(TUNING_SETTINGS_SERVICE_NOT_CONNECTED, "getSecondStageThreshold"));
        } else {
            this.mIUVRTuningSettings.getTuningParam(metroResultCallback);
        }
    }

    public void getTrainingThreshold(@NonNull MetroResultCallback metroResultCallback) throws RemoteException {
        if (!this.mIsTuningSettingsServiceBound) {
            Log.e(TAG, String.format(TUNING_SETTINGS_SERVICE_NOT_CONNECTED, "getTrainingThreshold"));
        } else {
            this.mIUVRTuningSettings.getTuningParam(metroResultCallback);
        }
    }

    public void getUserConfidenceLevel(@NonNull MetroResultCallback metroResultCallback) throws RemoteException {
        if (!this.mIsTuningSettingsServiceBound) {
            Log.e(TAG, String.format(TUNING_SETTINGS_SERVICE_NOT_CONNECTED, "getUserConfidenceLevel"));
        } else {
            this.mIUVRTuningSettings.getTuningParam(metroResultCallback);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @NonNull IBinder iBinder) {
        Log.d(TAG, "onServiceConnected");
        this.mIUVRTuningSettings = IUVRTuningSettings.Stub.asInterface(iBinder);
        this.mIsTuningSettingsServiceBound = true;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@NonNull ComponentName componentName) {
        Log.d(TAG, MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED);
        this.mIUVRTuningSettings = null;
        this.mIsTuningSettingsServiceBound = false;
    }

    public void setAntiSpoofThreshold(double d, @NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsTuningSettingsServiceBound) {
            Log.e(TAG, String.format(TUNING_SETTINGS_SERVICE_NOT_CONNECTED, "setAntiSpoofThreshold"));
        } else {
            this.mIUVRTuningSettings.setTuningParam(getDoubleParamBundle(ParamDefinition.KEY_TUNING_PARAM_ANTI_SPOOF_THRESHOLD, d), metroResponseCallback);
        }
    }

    public void setSecondStageThreshold(int i, @NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsTuningSettingsServiceBound) {
            Log.e(TAG, String.format(TUNING_SETTINGS_SERVICE_NOT_CONNECTED, "setSecondStageThreshold"));
        } else {
            this.mIUVRTuningSettings.setTuningParam(getIntParamBundle(ParamDefinition.KEY_TUNING_PARAM_SECOND_STAGE_THRESHOLD, i), metroResponseCallback);
        }
    }

    public void setTrainingThreshold(int i, @NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsTuningSettingsServiceBound) {
            Log.e(TAG, String.format(TUNING_SETTINGS_SERVICE_NOT_CONNECTED, "setTrainingThreshold"));
        } else {
            this.mIUVRTuningSettings.setTuningParam(getIntParamBundle(ParamDefinition.KEY_TUNING_PARAM_TRAINING_THRESHOLD, i), metroResponseCallback);
        }
    }

    public void setUserConfidenceLevel(int i, @NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsTuningSettingsServiceBound) {
            Log.e(TAG, String.format(TUNING_SETTINGS_SERVICE_NOT_CONNECTED, "setUserConfidenceLevel"));
        } else {
            this.mIUVRTuningSettings.setTuningParam(getIntParamBundle(ParamDefinition.KEY_TUNING_PARAM_USER_CONF_LEVEL, i), metroResponseCallback);
        }
    }

    public void setUtteranceTrainingTimeout(int i) throws RemoteException {
        if (!this.mIsTuningSettingsServiceBound) {
            Log.e(TAG, String.format(TUNING_SETTINGS_SERVICE_NOT_CONNECTED, "setUtteranceTrainingTimeout"));
        } else {
            this.mIUVRTuningSettings.setUtteranceTrainingParam(getIntParamBundle(ParamDefinition.KEY_TRAINING_PARAM_TIMEOUT, i));
        }
    }

    MetroUVRTuningSettingsConnection(@NonNull IUVRTuningSettings iUVRTuningSettings, boolean z) {
        this.mIUVRTuningSettings = iUVRTuningSettings;
        this.mIsTuningSettingsServiceBound = z;
    }
}
