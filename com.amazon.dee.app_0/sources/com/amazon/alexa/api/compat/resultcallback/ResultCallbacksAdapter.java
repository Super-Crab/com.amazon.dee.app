package com.amazon.alexa.api.compat.resultcallback;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ResultCallbacksAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/api/compat/resultcallback/ResultCallbacksAdapter;", "Lcom/amazon/alexa/api/ResultCallbacks;", "compatListener", "Lcom/amazon/alexa/api/compat/resultcallback/ResultCallbacks;", "(Lcom/amazon/alexa/api/compat/resultcallback/ResultCallbacks;)V", "getCompatListener", "()Lcom/amazon/alexa/api/compat/resultcallback/ResultCallbacks;", "onFailure", "", "failureMessage", "", "onSuccess", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ResultCallbacksAdapter implements com.amazon.alexa.api.ResultCallbacks {
    @NotNull
    private final ResultCallbacks compatListener;

    public ResultCallbacksAdapter(@NotNull ResultCallbacks compatListener) {
        Intrinsics.checkParameterIsNotNull(compatListener, "compatListener");
        this.compatListener = compatListener;
    }

    @NotNull
    public final ResultCallbacks getCompatListener() {
        return this.compatListener;
    }

    @Override // com.amazon.alexa.api.ResultCallbacks
    public void onFailure(@Nullable String str) {
        this.compatListener.onFailure(str);
    }

    @Override // com.amazon.alexa.api.ResultCallbacks
    public void onSuccess() {
        this.compatListener.onSuccess();
    }
}
