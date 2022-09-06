package com.amazon.alexa.api;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.client.annotations.UiThread;
import com.amazon.alexa.utils.security.SignatureVerifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public abstract class AlexaAudioProviderService extends Service {
    private MessageReceiver<AlexaAudioProviderServiceMessageType> audioProviderMessageReceiver;
    private MessageReceiversManager messageReceiversManager;

    /* loaded from: classes6.dex */
    public static class ResultCallbacks {
        private final bp resultCallbacksSender;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ResultCallbacks(bp bpVar) {
            this.resultCallbacksSender = bpVar;
        }

        public void onFailure(String str) {
            try {
                this.resultCallbacksSender.a(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onSuccess() {
            try {
                this.resultCallbacksSender.a();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean connectForAttentionSystemUpdates() {
        return false;
    }

    @UiThread
    public void doBind() {
    }

    @UiThread
    public void doUnbind() {
    }

    public PendingIntent getCustomSettingsIntent() {
        return null;
    }

    public String getCustomSettingsTitle() {
        return null;
    }

    public abstract java.util.Locale getLocale();

    public Set<java.util.Locale> getSupportedLocales() {
        return new HashSet(Arrays.asList(java.util.Locale.US));
    }

    public Integer getWakeWordConfidenceThreshold() {
        return null;
    }

    public abstract boolean isWakeWordRecognitionEnabled();

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        doBind();
        this.audioProviderMessageReceiver = this.messageReceiversManager.createMessageReceiver(new q(this));
        return this.audioProviderMessageReceiver.getMessenger().getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        this.messageReceiversManager = new MessageReceiversManager(new SignatureVerifier(this));
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.messageReceiversManager.clear();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        doUnbind();
        MessageReceiver<AlexaAudioProviderServiceMessageType> messageReceiver = this.audioProviderMessageReceiver;
        if (messageReceiver != null) {
            this.messageReceiversManager.removeMessageReceiver(messageReceiver);
            return false;
        }
        return false;
    }

    @Deprecated
    public abstract void setLocale(java.util.Locale locale);

    public void setLocale(java.util.Locale locale, ResultCallbacks resultCallbacks) {
        setLocale(locale);
        resultCallbacks.onSuccess();
    }

    public void setWakeWordConfidenceThreshold(Integer num, ResultCallbacks resultCallbacks) {
        resultCallbacks.onSuccess();
    }

    @Deprecated
    public abstract void setWakeWordRecognitionEnabled(boolean z);

    public void setWakeWordRecognitionEnabled(boolean z, ResultCallbacks resultCallbacks) {
        setWakeWordRecognitionEnabled(z);
        resultCallbacks.onSuccess();
    }

    public boolean startDialog() {
        return false;
    }
}
