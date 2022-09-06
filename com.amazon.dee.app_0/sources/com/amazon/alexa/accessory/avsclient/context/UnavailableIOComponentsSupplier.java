package com.amazon.alexa.accessory.avsclient.context;

import io.reactivex.rxjava3.core.Observable;
import java.util.Map;
/* loaded from: classes.dex */
public class UnavailableIOComponentsSupplier implements IOComponentsSupplier {
    @Override // com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier
    public void activate() {
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier
    public void deactivate() {
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier
    public IOComponents getIOComponentsCached(String str) {
        return IOComponents.EMPTY;
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier
    public IOComponents getIOComponentsCachedNoUtterance() {
        return IOComponents.EMPTY;
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.IOComponentsSupplier
    public Observable<Map<String, IOComponents>> queryIOComponents() {
        return Observable.never();
    }
}
