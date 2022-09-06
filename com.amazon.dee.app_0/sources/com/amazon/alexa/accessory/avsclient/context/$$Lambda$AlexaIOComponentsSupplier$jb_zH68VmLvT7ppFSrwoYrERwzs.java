package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$jb_zH68VmLvT7ppFSrwoYrERwzs  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaIOComponentsSupplier$jb_zH68VmLvT7ppFSrwoYrERwzs implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$jb_zH68VmLvT7ppFSrwoYrERwzs INSTANCE = new $$Lambda$AlexaIOComponentsSupplier$jb_zH68VmLvT7ppFSrwoYrERwzs();

    private /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$jb_zH68VmLvT7ppFSrwoYrERwzs() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Set set = (Set) obj;
        Logger.d("AlexaIOComponentsSupplier: observed registrations changed,  IOComponents combinations cached.");
    }
}
