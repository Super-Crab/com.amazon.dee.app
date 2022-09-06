package com.amazon.alexa.devices.presentation.internal;

import android.util.Log;
import com.amazon.alexa.devices.presentation.CallStatusErrorCode;
import com.amazon.alexa.devices.sdk.presentation.ICallStatusCallback;
/* loaded from: classes6.dex */
public final class CallbackHelper {
    private static final String TAG = "CallbackHelper";

    public static ICallStatusCallback convertToAidlCallStatusCallback(final com.amazon.alexa.devices.presentation.ICallStatusCallback iCallStatusCallback) {
        return new ICallStatusCallback.Stub() { // from class: com.amazon.alexa.devices.presentation.internal.CallbackHelper.1
            @Override // com.amazon.alexa.devices.sdk.presentation.ICallStatusCallback
            public void onError(String str) {
                try {
                    com.amazon.alexa.devices.presentation.ICallStatusCallback.this.onError(CallStatusErrorCode.valueOf(str).toString());
                } catch (IllegalArgumentException | NullPointerException e) {
                    String str2 = CallbackHelper.TAG;
                    Log.e(str2, "Invalid Code: " + str, e);
                    com.amazon.alexa.devices.presentation.ICallStatusCallback.this.onError(CallStatusErrorCode.INTERNAL_ERROR.toString());
                }
            }

            @Override // com.amazon.alexa.devices.sdk.presentation.ICallStatusCallback
            public void onSuccess() {
                com.amazon.alexa.devices.presentation.ICallStatusCallback.this.onSuccess();
            }
        };
    }
}
