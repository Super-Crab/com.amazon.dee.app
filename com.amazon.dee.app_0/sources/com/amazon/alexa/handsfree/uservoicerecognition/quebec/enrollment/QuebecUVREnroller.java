package com.amazon.alexa.handsfree.uservoicerecognition.quebec.enrollment;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.EnrollmentCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback.QuebecResponseCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback.QuebecResultCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.connection.QuebecUVRConnection;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.connection.QuebecUVRConnector;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class QuebecUVREnroller implements UVREnroller {
    private static final String TAG = "QuebecUVREnroller";
    private final QuebecUVRConnection mQuebecUVRConnection;

    public QuebecUVREnroller(@NonNull QuebecUVRConnector quebecUVRConnector) {
        this((QuebecUVRConnection) quebecUVRConnector.getEnrollmentConnection());
    }

    @Nullable
    private UtteranceInfo getUtteranceInfo(@NonNull Bundle bundle) {
        if (!bundle.containsKey(QuebecAPIConstants.UVR_UTTERANCE_INFO_ID) || !bundle.containsKey(QuebecAPIConstants.UVR_UTTERANCE_INFO_TEXT)) {
            return null;
        }
        return new UtteranceInfo(bundle.getString(QuebecAPIConstants.UVR_UTTERANCE_INFO_ID), bundle.getString(QuebecAPIConstants.UVR_UTTERANCE_INFO_TEXT));
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    public void cancelUserVoiceEnrollment(@NonNull ResponseCallback responseCallback) {
        try {
            this.mQuebecUVRConnection.cancelUserVoiceEnrollment(new QuebecResponseCallback(responseCallback));
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
            this.mQuebecUVRConnection.cancelUtteranceTraining(new QuebecResponseCallback(responseCallback));
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
            this.mQuebecUVRConnection.finishUserVoiceEnrollment(new QuebecResultCallback(resultCallback, QuebecAPIConstants.UVR_ENROLLMENT_QUALITY_SCORE));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("finishUserVoiceEnrollment: %s", e.getMessage()));
            resultCallback.onError(new CallbackErrorMetadata(-99, e.getMessage()));
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller
    @NonNull
    public List<UtteranceInfo> getUtterances() {
        try {
            ArrayList arrayList = new ArrayList();
            for (Bundle bundle : this.mQuebecUVRConnection.getUtterances()) {
                UtteranceInfo utteranceInfo = getUtteranceInfo(bundle);
                if (utteranceInfo == null) {
                    Log.e(TAG, "Invalid utterance Info");
                    return new ArrayList();
                }
                arrayList.add(utteranceInfo);
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
            this.mQuebecUVRConnection.startUserVoiceEnrollment(userInfo, new QuebecResponseCallback(responseCallback));
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
            this.mQuebecUVRConnection.startUtteranceTraining(utteranceInfo, new QuebecUtteranceTrainingCallback(enrollmentCallback));
        } catch (RemoteException e) {
            Log.e(TAG, String.format("startUtteranceTraining: %s", e.getMessage()));
        }
    }

    @VisibleForTesting
    QuebecUVREnroller(@NonNull QuebecUVRConnection quebecUVRConnection) {
        this.mQuebecUVRConnection = quebecUVRConnection;
    }
}
