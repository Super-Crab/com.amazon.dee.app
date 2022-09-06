package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.http.HttpCall;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$QEgt8qBAt1i70jfhNy1t_7iptHU  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultKotaDownloader$QEgt8qBAt1i70jfhNy1t_7iptHU implements Function {
    public static final /* synthetic */ $$Lambda$DefaultKotaDownloader$QEgt8qBAt1i70jfhNy1t_7iptHU INSTANCE = new $$Lambda$DefaultKotaDownloader$QEgt8qBAt1i70jfhNy1t_7iptHU();

    private /* synthetic */ $$Lambda$DefaultKotaDownloader$QEgt8qBAt1i70jfhNy1t_7iptHU() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        HttpCall.HttpResult httpResult = (HttpCall.HttpResult) obj;
        DefaultKotaDownloader.lambda$null$8(httpResult);
        return httpResult;
    }
}
