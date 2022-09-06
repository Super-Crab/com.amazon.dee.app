package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.utils.Provider;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
class AlexaApiCallbacksBundler extends w {
    private final AlexaApiCallbacks alexaApiCallbacks;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public enum ApiCallExtrasKeys implements Bundles.Key {
        ID,
        TIMEOUT_VALUE,
        TIMEOUT_UNIT,
        CALLBACK
    }

    private AlexaApiCallbacksBundler(Provider<MessageReceiversManager> provider, AlexaApiCallbacks alexaApiCallbacks) {
        super(provider);
        this.alexaApiCallbacks = alexaApiCallbacks;
    }

    public static AlexaApiCallbacksBundler create(Provider<MessageReceiversManager> provider, AlexaApiCallbacks alexaApiCallbacks) {
        return new AlexaApiCallbacksBundler(provider, alexaApiCallbacks);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.w
    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        if (this.alexaApiCallbacks.getTimeoutValue() != null) {
            bundle.putLong(ApiCallExtrasKeys.TIMEOUT_VALUE.name(), this.alexaApiCallbacks.getTimeoutValue().longValue());
            TimeUnit timeoutUnit = this.alexaApiCallbacks.getTimeoutUnit();
            if (timeoutUnit == null) {
                timeoutUnit = TimeUnit.MILLISECONDS;
            }
            bundle.putString(ApiCallExtrasKeys.TIMEOUT_UNIT.name(), timeoutUnit.name());
        }
        bundle.putString(ApiCallExtrasKeys.ID.name(), this.alexaApiCallbacks.getId());
        MessageReceiver messageReceiver = getMessageReceiver(AlexaApiCallbacksMessageProcessor.create(this.alexaApiCallbacks));
        if (messageReceiver != null) {
            bundle.putBinder(ApiCallExtrasKeys.CALLBACK.name(), messageReceiver.getMessenger().getBinder());
        }
        return bundle;
    }
}
