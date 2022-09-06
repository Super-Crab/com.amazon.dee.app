package com.amazon.alexa.api;

import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
abstract class g implements Runnable {
    private final AlexaApiCallbacks a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(@Nullable AlexaApiCallbacks alexaApiCallbacks) {
        this.a = alexaApiCallbacks;
    }

    private void a(@Nullable Exception exc) {
        if (this.a != null) {
            this.a.doOnFailure(ApiCallFailure.MessagingFailure.create(exc == null ? "Unable to execute API call" : exc.getMessage(), exc));
        }
    }

    public abstract void a() throws Exception;

    @Override // java.lang.Runnable
    public void run() {
        try {
            a();
        } catch (Exception e) {
            a(e);
        }
    }
}
