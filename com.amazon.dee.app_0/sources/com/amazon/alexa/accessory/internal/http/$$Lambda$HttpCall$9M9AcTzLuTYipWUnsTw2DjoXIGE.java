package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.http.HttpCall;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.http.-$$Lambda$HttpCall$9M9AcTzLuTYipWUnsTw2DjoXIGE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$HttpCall$9M9AcTzLuTYipWUnsTw2DjoXIGE implements Function {
    public static final /* synthetic */ $$Lambda$HttpCall$9M9AcTzLuTYipWUnsTw2DjoXIGE INSTANCE = new $$Lambda$HttpCall$9M9AcTzLuTYipWUnsTw2DjoXIGE();

    private /* synthetic */ $$Lambda$HttpCall$9M9AcTzLuTYipWUnsTw2DjoXIGE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        HttpCall.HttpResult httpResult = (HttpCall.HttpResult) obj;
        HttpCall.lambda$executeCompletable$1(httpResult);
        return httpResult;
    }
}
