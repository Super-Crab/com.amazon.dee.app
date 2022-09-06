package com.amazon.alexa.accessory.avsclient.context;

import io.reactivex.rxjava3.core.Observable;
import java.util.Map;
/* loaded from: classes.dex */
public interface IOComponentsSupplier {
    void activate();

    void deactivate();

    IOComponents getIOComponentsCached(String str);

    IOComponents getIOComponentsCachedNoUtterance();

    Observable<Map<String, IOComponents>> queryIOComponents();
}
