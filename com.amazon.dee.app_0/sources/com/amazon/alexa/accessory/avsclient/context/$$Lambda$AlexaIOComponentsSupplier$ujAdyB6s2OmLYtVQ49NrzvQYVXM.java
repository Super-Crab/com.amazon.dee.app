package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.AccessorySession;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$ujAdyB6s2OmLYtVQ49NrzvQYVXM  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaIOComponentsSupplier$ujAdyB6s2OmLYtVQ49NrzvQYVXM implements Function {
    public static final /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$ujAdyB6s2OmLYtVQ49NrzvQYVXM INSTANCE = new $$Lambda$AlexaIOComponentsSupplier$ujAdyB6s2OmLYtVQ49NrzvQYVXM();

    private /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$ujAdyB6s2OmLYtVQ49NrzvQYVXM() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        ObservableSource onErrorResumeWith;
        onErrorResumeWith = ((AccessorySession) obj).getDeviceRepositoryV2().queryDeviceInformationSet().skip(1L).onErrorResumeWith(Observable.empty());
        return onErrorResumeWith;
    }
}
