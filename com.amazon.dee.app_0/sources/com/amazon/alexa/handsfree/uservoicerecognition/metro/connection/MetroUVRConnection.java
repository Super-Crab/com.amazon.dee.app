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
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UtteranceInfo;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.EnrollmentStatusCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.MetroResponseCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.callback.MetroResultCallback;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.enrollment.EnrollCallback;
import com.magiear.handsfree.util.IUVREnroller;
import com.magiear.handsfree.util.ParamDefinition;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class MetroUVRConnection implements ServiceConnection {
    private static final String ENROLLER_SERVICE_NOT_CONNECTED = "%s: enroller service not connected";
    private static final String TAG = MetroUVRConnection.class.getSimpleName();
    private final EnrollmentStateProvider mEnrollmentStateProvider;
    private IUVREnroller mIUVREnroller;
    private boolean mIsEnrollServiceBound;

    public MetroUVRConnection(@NonNull EnrollmentStateProvider enrollmentStateProvider) {
        this.mEnrollmentStateProvider = enrollmentStateProvider;
    }

    @NonNull
    private Bundle getUserInfoBundle(@NonNull UserInfo userInfo) {
        Bundle bundle = new Bundle();
        bundle.putString(ParamDefinition.KEY_ENROLL_USER_INFO_ID, userInfo.getUserId());
        return bundle;
    }

    private Bundle getUtteranceInfoBundle(@NonNull UtteranceInfo utteranceInfo) {
        Bundle bundle = new Bundle();
        bundle.putString(ParamDefinition.KEY_ENROLL_UTTERANCE_ID, utteranceInfo.getUtteranceId());
        bundle.putString(ParamDefinition.KEY_ENROLL_UTTERANCE_TEXT, utteranceInfo.getText());
        return bundle;
    }

    public void cancelUserVoiceEnrollment(@NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "cancelUserVoiceEnrollment"));
        } else {
            this.mIUVREnroller.cancelUserVoiceEnrollment(metroResponseCallback);
        }
    }

    public void cancelUtteranceTraining(@NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "cancelUtteranceTraining"));
        } else {
            this.mIUVREnroller.cancelUtteranceTraining(metroResponseCallback);
        }
    }

    public void finishUserVoiceEnrollment(@NonNull MetroResultCallback metroResultCallback) throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "finishUserVoiceEnrollment"));
        } else {
            this.mIUVREnroller.finishUserVoiceEnrollment(new EnrollmentStatusCallback(metroResultCallback, this.mEnrollmentStateProvider, true));
        }
    }

    public List<Bundle> getUtterances() throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "getUtterances"));
            return new ArrayList();
        }
        return this.mIUVREnroller.getUtterances();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @NonNull IBinder iBinder) {
        Log.d(TAG, "onServiceConnected");
        this.mIUVREnroller = IUVREnroller.Stub.asInterface(iBinder);
        this.mIsEnrollServiceBound = true;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@NonNull ComponentName componentName) {
        Log.d(TAG, MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED);
        this.mIUVREnroller = null;
        this.mIsEnrollServiceBound = false;
    }

    public void startUserVoiceEnrollment(@NonNull UserInfo userInfo, @NonNull MetroResponseCallback metroResponseCallback) throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "startUserVoiceEnrollment"));
            return;
        }
        EnrollmentStateProvider enrollmentStateProvider = this.mEnrollmentStateProvider;
        if (enrollmentStateProvider != null) {
            enrollmentStateProvider.setUserInfo(userInfo);
        }
        this.mIUVREnroller.startUserVoiceEnrollment(getUserInfoBundle(userInfo), metroResponseCallback);
    }

    public void startUtteranceTraining(@NonNull UtteranceInfo utteranceInfo, @NonNull EnrollCallback enrollCallback) throws RemoteException {
        if (!this.mIsEnrollServiceBound) {
            Log.e(TAG, String.format(ENROLLER_SERVICE_NOT_CONNECTED, "startUtteranceTraining"));
        } else {
            this.mIUVREnroller.startUtteranceTraining(getUtteranceInfoBundle(utteranceInfo), enrollCallback);
        }
    }

    @VisibleForTesting
    MetroUVRConnection(@NonNull EnrollmentStateProvider enrollmentStateProvider, @NonNull IUVREnroller iUVREnroller, boolean z) {
        this.mEnrollmentStateProvider = enrollmentStateProvider;
        this.mIUVREnroller = iUVREnroller;
        this.mIsEnrollServiceBound = z;
    }
}
