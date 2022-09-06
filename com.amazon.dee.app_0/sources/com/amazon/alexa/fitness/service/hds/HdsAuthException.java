package com.amazon.alexa.fitness.service.hds;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsClientImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HdsAuthException;", "Lcom/amazon/alexa/fitness/service/hds/HdsException;", "errorMessage", "", "(Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HdsAuthException extends HdsException {
    @Nullable
    private final String errorMessage;

    public HdsAuthException(@Nullable String str) {
        this.errorMessage = str;
    }

    @Nullable
    public final String getErrorMessage() {
        return this.errorMessage;
    }
}
