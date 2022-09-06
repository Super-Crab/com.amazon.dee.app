package com.amazon.alexa.handsfree.uservoicerecognition.quebec.connection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceInfo;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback.EnrollmentStatusCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback.QuebecResponseCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback.QuebecResultCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.enrollment.QuebecUtteranceTrainingCallback;
import com.quicinc.voice.activation.IUVREnrollmentService;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class QuebecUVRConnection implements ServiceConnection {
    private static final String ENROLLER_SERVICE_NOT_CONNECTED = "%s: enroller service not connected";
    private static final String TAG = QuebecUVRConnection.class.getSimpleName();
    private final EnrollmentStateProvider mEnrollmentStateProvider;
    private IUVREnrollmentService mIUVREnrollmentService;
    private boolean mIsEnrollServiceBound;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QuebecUVRConnection(@NonNull EnrollmentStateProvider enrollmentStateProvider) {
        this.mEnrollmentStateProvider = enrollmentStateProvider;
    }

    @NonNull
    private Bundle getUserInfoBundle(@NonNull UserInfo userInfo) {
        Bundle bundle = new Bundle();
        bundle.putString(QuebecAPIConstants.UVR_USER_INFO_ID, userInfo.getUserId());
        return bundle;
    }

    @NonNull
    private Bundle getUtteranceInfoBundle(@NonNull UtteranceInfo utteranceInfo) {
        Bundle bundle = new Bundle();
        bundle.putString(QuebecAPIConstants.UVR_UTTERANCE_INFO_ID, utteranceInfo.getUtteranceId());
        bundle.putString(QuebecAPIConstants.UVR_UTTERANCE_INFO_TEXT, utteranceInfo.getText());
        return bundle;
    }

    public void cancelUserVoiceEnrollment(@NonNull QuebecResponseCallback quebecResponseCallback) throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "cancelUserVoiceEnrollment"));
        } else {
            this.mIUVREnrollmentService.cancelUserVoiceEnrollment(quebecResponseCallback, Bundle.EMPTY);
        }
    }

    public void cancelUtteranceTraining(@NonNull QuebecResponseCallback quebecResponseCallback) throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "cancelUtteranceTraining"));
        } else {
            this.mIUVREnrollmentService.cancelUtteranceTraining(quebecResponseCallback, Bundle.EMPTY);
        }
    }

    public void finishUserVoiceEnrollment(@NonNull QuebecResultCallback quebecResultCallback) throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "finishUserVoiceEnrollment"));
        } else {
            this.mIUVREnrollmentService.finishUserVoiceEnrollment(new EnrollmentStatusCallback(quebecResultCallback, this.mEnrollmentStateProvider, true), Bundle.EMPTY);
        }
    }

    public List<Bundle> getUtterances() throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "getUtterances"));
            return new ArrayList();
        }
        return this.mIUVREnrollmentService.getUtterancesInfo(Bundle.EMPTY);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @NonNull IBinder iBinder) {
        Log.d(TAG, "onServiceConnected");
        this.mIUVREnrollmentService = IUVREnrollmentService.Stub.asInterface(iBinder);
        this.mIsEnrollServiceBound = true;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@NonNull ComponentName componentName) {
        Log.d(TAG, MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED);
        this.mIUVREnrollmentService = null;
        this.mIsEnrollServiceBound = false;
    }

    public void startUserVoiceEnrollment(@NonNull UserInfo userInfo, @NonNull QuebecResponseCallback quebecResponseCallback) throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "startUserVoiceEnrollment"));
            return;
        }
        this.mEnrollmentStateProvider.setUserInfo(userInfo);
        this.mIUVREnrollmentService.startUserVoiceEnrollment(quebecResponseCallback, getUserInfoBundle(userInfo));
    }

    public void startUtteranceTraining(@NonNull UtteranceInfo utteranceInfo, @NonNull QuebecUtteranceTrainingCallback quebecUtteranceTrainingCallback) throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "startUtteranceTraining"));
        } else {
            this.mIUVREnrollmentService.startUtteranceTraining(quebecUtteranceTrainingCallback, getUtteranceInfoBundle(utteranceInfo));
        }
    }

    @VisibleForTesting
    QuebecUVRConnection(@NonNull EnrollmentStateProvider enrollmentStateProvider, @NonNull IUVREnrollmentService iUVREnrollmentService, boolean z) {
        this.mEnrollmentStateProvider = enrollmentStateProvider;
        this.mIUVREnrollmentService = iUVREnrollmentService;
        this.mIsEnrollServiceBound = z;
    }
}
