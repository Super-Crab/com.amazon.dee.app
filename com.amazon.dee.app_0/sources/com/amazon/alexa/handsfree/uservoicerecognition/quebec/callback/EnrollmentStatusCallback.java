package com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.quicinc.voice.activation.IResultCallback;
import com.quicinc.voice.activation.IResultCallback.Stub;
/* loaded from: classes8.dex */
public class EnrollmentStatusCallback<T extends IResultCallback.Stub> extends IResultCallback.Stub {
    private static final String TAG = EnrollmentStatusCallback.class.getSimpleName();
    private final boolean mEnrollmentState;
    private final EnrollmentStateProvider mEnrollmentStateProvider;
    private final T mQuebecCallback;

    public EnrollmentStatusCallback(@NonNull T t, @Nullable EnrollmentStateProvider enrollmentStateProvider, boolean z) {
        this.mQuebecCallback = t;
        this.mEnrollmentStateProvider = enrollmentStateProvider;
        this.mEnrollmentState = z;
    }

    @VisibleForTesting
    public boolean getEnrollmentState() {
        return this.mEnrollmentState;
    }

    @VisibleForTesting
    public EnrollmentStateProvider getEnrollmentStateProvider() {
        return this.mEnrollmentStateProvider;
    }

    @VisibleForTesting
    public T getQuebecCallbackCallback() {
        return this.mQuebecCallback;
    }

    @Override // com.quicinc.voice.activation.IResultCallback
    public void onFailure(@NonNull Bundle bundle) throws RemoteException {
        this.mQuebecCallback.onFailure(bundle);
    }

    @Override // com.quicinc.voice.activation.IResultCallback
    public void onSuccess(@NonNull Bundle bundle) throws RemoteException {
        EnrollmentStateProvider enrollmentStateProvider = this.mEnrollmentStateProvider;
        if (enrollmentStateProvider != null) {
            enrollmentStateProvider.setEnrollmentState(this.mEnrollmentState);
        } else {
            Log.e(TAG, "Enrollment state provider unavailable");
        }
        this.mQuebecCallback.onSuccess(bundle);
    }
}
