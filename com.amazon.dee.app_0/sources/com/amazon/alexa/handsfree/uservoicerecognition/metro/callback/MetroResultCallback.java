package com.amazon.alexa.handsfree.uservoicerecognition.metro.callback;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.magiear.handsfree.util.IResultCallback;
import com.magiear.handsfree.util.ParamDefinition;
/* loaded from: classes8.dex */
public class MetroResultCallback extends IResultCallback.Stub {
    private static final String TAG = MetroResultCallback.class.getSimpleName();
    private final CallbackErrorMetadata mCallbackErrorMetadata = new CallbackErrorMetadata();
    private final ResultCallback mResultCallback;
    private final String mResultKey;

    public MetroResultCallback(@NonNull ResultCallback resultCallback, @NonNull String str) {
        this.mResultCallback = resultCallback;
        this.mResultKey = str;
    }

    private void sendResult(@NonNull Bundle bundle) {
        if (!bundle.containsKey(this.mResultKey)) {
            Log.e(TAG, String.format("onResult: Bundle doesn't contain callback result with key: %s", this.mResultKey));
        } else if (ParamDefinition.KEY_ENROLL_CALLBACK_QUALITY_SCORE.equals(this.mResultKey)) {
            this.mResultCallback.onResult(Double.valueOf(bundle.getDouble(this.mResultKey)));
        } else {
            this.mResultCallback.onResult(Integer.valueOf(bundle.getInt(this.mResultKey)));
        }
    }

    @Override // com.magiear.handsfree.util.IResultCallback
    public void onResult(@NonNull Bundle bundle) throws RemoteException {
        if (!bundle.containsKey(ParamDefinition.KEY_CALLBACK_RESULT)) {
            Log.e(TAG, "onResult: Bundle doesn't contain callback result.");
            return;
        }
        String string = bundle.getString(ParamDefinition.KEY_CALLBACK_RESULT);
        if ("success".equals(string)) {
            sendResult(bundle);
        } else if ("fail".equals(string)) {
            if (!bundle.containsKey(ParamDefinition.KEY_CALLBACK_ERROR_MSG)) {
                Log.e(TAG, "onResult: Bundle doesn't contain callback error message.");
                return;
            }
            String string2 = bundle.getString(ParamDefinition.KEY_CALLBACK_ERROR_MSG);
            if (string2 != null) {
                this.mResultCallback.onError(new CallbackErrorMetadata(-100, string2));
            } else {
                this.mResultCallback.onError(new CallbackErrorMetadata(-100));
            }
        } else {
            Log.e(TAG, String.format("onResult: Unknown callback result string: %s", string));
        }
    }
}
