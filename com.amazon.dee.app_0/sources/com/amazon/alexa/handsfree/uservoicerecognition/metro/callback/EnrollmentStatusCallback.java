package com.amazon.alexa.handsfree.uservoicerecognition.metro.callback;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.magiear.handsfree.util.IResultCallback;
import com.magiear.handsfree.util.IResultCallback.Stub;
import com.magiear.handsfree.util.ParamDefinition;
/* loaded from: classes8.dex */
public class EnrollmentStatusCallback<T extends IResultCallback.Stub> extends IResultCallback.Stub {
    private static final String TAG = EnrollmentStatusCallback.class.getSimpleName();
    private final boolean mEnrollmentState;
    private final EnrollmentStateProvider mEnrollmentStateProvider;
    private final T mMetroCallback;

    public EnrollmentStatusCallback(@NonNull T t, @Nullable EnrollmentStateProvider enrollmentStateProvider, boolean z) {
        this.mMetroCallback = t;
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
    public T getMetroResultCallback() {
        return this.mMetroCallback;
    }

    @Override // com.magiear.handsfree.util.IResultCallback
    public void onResult(@NonNull Bundle bundle) throws RemoteException {
        if (this.mEnrollmentStateProvider != null) {
            if (!bundle.containsKey(ParamDefinition.KEY_CALLBACK_RESULT)) {
                Log.e(TAG, "onResult: Bundle doesn't contain callback result.");
                return;
            } else if ("success".equals(bundle.getString(ParamDefinition.KEY_CALLBACK_RESULT))) {
                this.mEnrollmentStateProvider.setEnrollmentState(this.mEnrollmentState);
            }
        } else {
            Log.e(TAG, "Enrollment state provider unavailable");
        }
        this.mMetroCallback.onResult(bundle);
    }
}
