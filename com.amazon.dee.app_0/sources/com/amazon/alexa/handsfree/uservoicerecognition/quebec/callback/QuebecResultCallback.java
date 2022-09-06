package com.amazon.alexa.handsfree.uservoicerecognition.quebec.callback;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.amazon.alexa.handsfree.quebec.QuebecAPIDefaultValues;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.quicinc.voice.activation.IResultCallback;
/* loaded from: classes8.dex */
public class QuebecResultCallback extends IResultCallback.Stub {
    @VisibleForTesting
    static final String ERROR_MESSAGE_FORMAT = "Error %d: %s.";
    @VisibleForTesting
    static final String ERROR_RESULT_KEY_MESSAGE = "Result key missing";
    private static final String TAG = QuebecResultCallback.class.getSimpleName();
    @VisibleForTesting
    static final String UNSUPPORTED_RESULT_KEY_MESSAGE = "Unsupported key";
    private final ResultCallback mResultCallback;
    private final String mResultKey;

    public QuebecResultCallback(@NonNull ResultCallback resultCallback, @NonNull String str) {
        this.mResultCallback = resultCallback;
        this.mResultKey = str;
    }

    private void sendResult(@NonNull Bundle bundle) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Attempting to deserialize Bundle: ");
        outline107.append(bundle.toString());
        Log.d(str, outline107.toString());
        if (!bundle.containsKey(this.mResultKey)) {
            Log.e(TAG, String.format("onResult: Bundle doesn't contain callback result with key: %s", this.mResultKey));
            this.mResultCallback.onError(new CallbackErrorMetadata(QuebecAPIDefaultValues.RESULT_CALLBACK_DEFAULT_ERROR_CODE.intValue(), ERROR_RESULT_KEY_MESSAGE));
        } else if (QuebecAPIConstants.UVR_ENROLLMENT_QUALITY_SCORE.equals(this.mResultKey)) {
            this.mResultCallback.onResult(Double.valueOf(bundle.getInt(this.mResultKey, QuebecAPIDefaultValues.UVR_INVALID_QUALITY_SCORE.intValue())));
        } else {
            Log.e(TAG, String.format("onResult: Unsupported key: %s", this.mResultKey));
            this.mResultCallback.onError(new CallbackErrorMetadata(QuebecAPIDefaultValues.RESULT_CALLBACK_DEFAULT_ERROR_CODE.intValue(), UNSUPPORTED_RESULT_KEY_MESSAGE));
        }
    }

    @Override // com.quicinc.voice.activation.IResultCallback
    public void onFailure(@NonNull Bundle bundle) {
        String string = bundle.getString(QuebecAPIConstants.RESULT_CALLBACK_ERROR_MESSAGE, QuebecAPIDefaultValues.RESULT_CALLBACK_DEFAULT_ERROR_MESSAGE);
        int i = bundle.getInt(QuebecAPIConstants.RESULT_CALLBACK_ERROR_CODE, QuebecAPIDefaultValues.RESULT_CALLBACK_DEFAULT_ERROR_CODE.intValue());
        Log.d(TAG, "Callback called with Failure state.");
        if (string != null) {
            this.mResultCallback.onError(new CallbackErrorMetadata(i, string));
        } else {
            this.mResultCallback.onError(new CallbackErrorMetadata(i));
        }
    }

    @Override // com.quicinc.voice.activation.IResultCallback
    public void onSuccess(@NonNull Bundle bundle) {
        sendResult(bundle);
    }
}
