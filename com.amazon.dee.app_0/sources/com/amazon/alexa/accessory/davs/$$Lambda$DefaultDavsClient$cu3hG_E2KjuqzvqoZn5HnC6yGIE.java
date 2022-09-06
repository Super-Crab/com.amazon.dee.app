package com.amazon.alexa.accessory.davs;

import com.amazon.alexa.accessory.internal.http.HttpCall;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$cu3hG_E2KjuqzvqoZn5HnC6yGIE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultDavsClient$cu3hG_E2KjuqzvqoZn5HnC6yGIE implements Function {
    public static final /* synthetic */ $$Lambda$DefaultDavsClient$cu3hG_E2KjuqzvqoZn5HnC6yGIE INSTANCE = new $$Lambda$DefaultDavsClient$cu3hG_E2KjuqzvqoZn5HnC6yGIE();

    private /* synthetic */ $$Lambda$DefaultDavsClient$cu3hG_E2KjuqzvqoZn5HnC6yGIE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return DefaultDavsClient.lambda$downloadI18nConfig$16((HttpCall.HttpResult) obj);
    }
}
