package com.amazon.alexa.handsfree.uservoicerecognition.metro.callback;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.magiear.handsfree.util.IResultCallback;
import com.magiear.handsfree.util.ParamDefinition;
/* loaded from: classes8.dex */
public class MetroResponseCallback extends IResultCallback.Stub {
    private static final String SERVICE_NOT_CONNECTED = "%s: %s not connected";
    private static final String TAG = MetroResponseCallback.class.getSimpleName();
    private final ResponseCallback mResponseCallback;

    public MetroResponseCallback(@NonNull ResponseCallback responseCallback) {
        this.mResponseCallback = responseCallback;
    }

    @Override // com.magiear.handsfree.util.IResultCallback
    public void onResult(@NonNull Bundle bundle) throws RemoteException {
        if (!bundle.containsKey(ParamDefinition.KEY_CALLBACK_RESULT)) {
            Log.e(TAG, "onResult: Bundle doesn't contain callback result.");
            return;
        }
        String string = bundle.getString(ParamDefinition.KEY_CALLBACK_RESULT);
        if ("success".equals(string)) {
            this.mResponseCallback.onSuccess();
        } else if ("fail".equals(string)) {
            if (!bundle.containsKey(ParamDefinition.KEY_CALLBACK_ERROR_MSG)) {
                Log.e(TAG, "onResult: Bundle doesn't contain callback error message.");
                return;
            }
            String string2 = bundle.getString(ParamDefinition.KEY_CALLBACK_ERROR_MSG);
            if (string2 != null) {
                this.mResponseCallback.onError(new CallbackErrorMetadata(-100, string2));
            } else {
                this.mResponseCallback.onError(new CallbackErrorMetadata(-100));
            }
        } else {
            Log.e(TAG, String.format("onResult: Unknown callback result string: %s", string));
        }
    }

    public void onServiceNotConnected(@NonNull String str, @NonNull String str2) {
        Log.e(TAG, String.format(SERVICE_NOT_CONNECTED, str, str2));
        this.mResponseCallback.onError(new CallbackErrorMetadata());
    }
}
