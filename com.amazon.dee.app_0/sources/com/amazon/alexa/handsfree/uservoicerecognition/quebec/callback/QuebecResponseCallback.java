package com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.amazon.alexa.handsfree.quebec.QuebecAPIDefaultValues;
import com.quicinc.voice.activation.IResultCallback;
/* loaded from: classes8.dex */
public class QuebecResponseCallback extends IResultCallback.Stub {
    @VisibleForTesting
    static final String ERROR_MESSAGE_FORMAT = "Error %d: %s.";
    private static final String SERVICE_NOT_CONNECTED = "%s: %s not connected";
    private static final String TAG = QuebecResponseCallback.class.getSimpleName();
    private final ResponseCallback mResponseCallback;

    public QuebecResponseCallback(@NonNull ResponseCallback responseCallback) {
        this.mResponseCallback = responseCallback;
    }

    @Override // com.quicinc.voice.activation.IResultCallback
    public void onFailure(@NonNull Bundle bundle) {
        String string = bundle.getString(QuebecAPIConstants.RESULT_CALLBACK_ERROR_MESSAGE, QuebecAPIDefaultValues.RESULT_CALLBACK_DEFAULT_ERROR_MESSAGE);
        int i = bundle.getInt(QuebecAPIConstants.RESULT_CALLBACK_ERROR_CODE, QuebecAPIDefaultValues.RESULT_CALLBACK_DEFAULT_ERROR_CODE.intValue());
        Log.d(TAG, "Callback called with Failure state.");
        if (string != null) {
            this.mResponseCallback.onError(new CallbackErrorMetadata(i, string));
        } else {
            this.mResponseCallback.onError(new CallbackErrorMetadata(i));
        }
    }

    public void onServiceNotConnected(@NonNull String str, @NonNull String str2) {
        Log.e(TAG, String.format(SERVICE_NOT_CONNECTED, str, str2));
        this.mResponseCallback.onError(new CallbackErrorMetadata());
    }

    @Override // com.quicinc.voice.activation.IResultCallback
    public void onSuccess(@NonNull Bundle bundle) {
        Log.d(TAG, "Callback called with Success state.");
        this.mResponseCallback.onSuccess();
    }
}
