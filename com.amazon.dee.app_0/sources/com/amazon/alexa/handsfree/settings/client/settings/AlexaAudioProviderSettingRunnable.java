package com.amazon.alexa.handsfree.settings.client.settings;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.compat.audioproviderservice.AlexaAudioProviderServiceMessageSender;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.client.callback.SuccessCallback;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class AlexaAudioProviderSettingRunnable<T> implements Runnable {
    private static final String TAG = AlexaAudioProviderSettingRunnable.class.getSimpleName();
    private final AlexaAudioProviderSetting<T> mAlexaAudioProviderSetting;
    private Handler mHandler;
    private AlexaAudioProviderServiceMessageSender mMessageSender;
    private final SuccessCallback<Void> mSettingCompleteCb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioProviderSettingRunnable(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender, @NonNull SuccessCallback<Void> successCallback, @NonNull AlexaAudioProviderSetting<T> alexaAudioProviderSetting) {
        this(alexaAudioProviderServiceMessageSender, successCallback, alexaAudioProviderSetting, new Handler(Looper.getMainLooper()));
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                final T mo1543apply = this.mAlexaAudioProviderSetting.mo1543apply(this.mMessageSender);
                this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSettingRunnable.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AlexaAudioProviderSettingRunnable.this.mAlexaAudioProviderSetting.getCallback().onResult((T) mo1543apply);
                        AlexaAudioProviderSettingRunnable.this.mSettingCompleteCb.onSuccess(null);
                    }
                });
            } catch (RemoteException e) {
                this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSettingRunnable.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AlexaAudioProviderSettingRunnable.this.mAlexaAudioProviderSetting.getCallback().onError(new CallbackErrorMetadata(e.getMessage()));
                        AlexaAudioProviderSettingRunnable.this.mSettingCompleteCb.onSuccess(null);
                    }
                });
            } catch (Exception e2) {
                this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSettingRunnable.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.w(AlexaAudioProviderSettingRunnable.TAG, String.format("Failed to apply setting %s: %s", AlexaAudioProviderSettingRunnable.this.mAlexaAudioProviderSetting, e2));
                        AlexaAudioProviderSettingRunnable.this.mAlexaAudioProviderSetting.getCallback().onError(new CallbackErrorMetadata(e2.getMessage()));
                        AlexaAudioProviderSettingRunnable.this.mSettingCompleteCb.onSuccess(null);
                    }
                });
            }
        } finally {
            this.mMessageSender.release();
        }
    }

    @VisibleForTesting
    AlexaAudioProviderSettingRunnable(@NonNull AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender, @NonNull SuccessCallback<Void> successCallback, @NonNull AlexaAudioProviderSetting<T> alexaAudioProviderSetting, @NonNull Handler handler) {
        this.mMessageSender = alexaAudioProviderServiceMessageSender;
        this.mAlexaAudioProviderSetting = alexaAudioProviderSetting;
        this.mHandler = handler;
        this.mSettingCompleteCb = successCallback;
    }
}
