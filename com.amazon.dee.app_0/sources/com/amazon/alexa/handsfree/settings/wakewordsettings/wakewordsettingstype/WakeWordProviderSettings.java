package com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public abstract class WakeWordProviderSettings<T> {
    private static final String TAG = "WakeWordProviderSettings";
    private final ResultCallback<T> mCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WakeWordProviderSettings(@NonNull ResultCallback<T> resultCallback) {
        this.mCallback = resultCallback;
    }

    /* renamed from: apply */
    abstract T mo1548apply() throws RemoteException;

    public void applySetting() {
        try {
            this.mCallback.onResult(mo1548apply());
        } catch (Exception e) {
            Log.e(TAG, "Exception in applying wake word settings ", e, new Object[0]);
            this.mCallback.onError(new CallbackErrorMetadata(e.getMessage()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultCallback<T> getCallback() {
        return this.mCallback;
    }
}
