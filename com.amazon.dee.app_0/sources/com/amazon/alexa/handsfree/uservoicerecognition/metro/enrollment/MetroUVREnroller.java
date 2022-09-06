package com.amazon.alexa.handsfree.uservoicerecognition.metro.enrollment;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.MetroResponseCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.MetroResultCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.connection.MetroUVRConnection;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.connection.MetroUVRConnector;
import com.magiear.handsfree.util.ParamDefinition;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class MetroUVREnroller implements UVREnroller {
    private static final String TAG = "MetroUVREnroller";
    private final MetroUVRConnection mMetroUVRConnection;

    public MetroUVREnroller(@NonNull MetroUVRConnector metroUVRConnector) {
        this((MetroUVRConnection) metroUVRConnector.getEnrollmentConnection());
    }

    @NonNull
    private UtteranceInfo getUtteranceInfo(@NonNull Bundle bundle) {
        return new UtteranceInfo(bundle.getString(ParamDefinition.KEY_ENROLL_UTTERANCE_ID), bundle.getString(ParamDefinition.KEY_ENROLL_UTTERANCE_TEXT));
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void cancelUserVoiceEnrollment(@NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRConnection.cancelUserVoiceEnrollment(new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("cancelUserVoiceEnrollment: %s", message));
            if (message == null) {
                return;
            }
            responseCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void cancelUtteranceTraining(@NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRConnection.cancelUtteranceTraining(new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("cancelUtteranceTraining: %s", message));
            if (message == null) {
                return;
            }
            responseCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void finishUserVoiceEnrollment(@NonNull ResultCallback<Double> resultCallback) {
        try {
            this.mMetroUVRConnection.finishUserVoiceEnrollment(new MetroResultCallback(resultCallback, ParamDefinition.KEY_ENROLL_CALLBACK_QUALITY_SCORE));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("finishUserVoiceEnrollment: %s", message));
            if (message == null) {
                return;
            }
            resultCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    @NonNull
    public List<UtteranceInfo> getUtterances() {
        try {
            ArrayList arrayList = new ArrayList();
            for (Bundle bundle : this.mMetroUVRConnection.getUtterances()) {
                arrayList.add(getUtteranceInfo(bundle));
            }
            return arrayList;
        } catch (RemoteException e) {
            Log.e(TAG, String.format("getUtterances: %s", e.getMessage()));
            return new ArrayList();
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void startUserVoiceEnrollment(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback) {
        try {
            this.mMetroUVRConnection.startUserVoiceEnrollment(userInfo, new MetroResponseCallback(responseCallback));
        } catch (RemoteException e) {
            String message = e.getMessage();
            Log.e(TAG, String.format("startUserVoiceEnrollment: %s", message));
            if (message == null) {
                return;
            }
            responseCallback.onError(new CallbackErrorMetadata(-99, message));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void startUtteranceTraining(@NonNull UtteranceInfo utteranceInfo, @NonNull EnrollmentCallback enrollmentCallback) {
        try {
            this.mMetroUVRConnection.startUtteranceTraining(utteranceInfo, new EnrollCallback(enrollmentCallback));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("startUtteranceTraining: %s", e.getMessage()));
        }
    }

    @VisibleForTesting
    MetroUVREnroller(@NonNull MetroUVRConnection metroUVRConnection) {
        this.mMetroUVRConnection = metroUVRConnection;
    }
}
