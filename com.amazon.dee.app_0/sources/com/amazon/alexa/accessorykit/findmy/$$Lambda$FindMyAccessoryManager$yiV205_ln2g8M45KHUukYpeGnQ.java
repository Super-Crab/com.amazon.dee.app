package com.amazon.alexa.accessorykit.findmy;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$yiV205_ln2g8M45KHUukYpeG-nQ  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FindMyAccessoryManager$yiV205_ln2g8M45KHUukYpeGnQ implements Function {
    public static final /* synthetic */ $$Lambda$FindMyAccessoryManager$yiV205_ln2g8M45KHUukYpeGnQ INSTANCE = new $$Lambda$FindMyAccessoryManager$yiV205_ln2g8M45KHUukYpeGnQ();

    private /* synthetic */ $$Lambda$FindMyAccessoryManager$yiV205_ln2g8M45KHUukYpeGnQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        ObservableSource observable;
        observable = Observable.fromIterable((Set) obj).filter(DeviceFilterUtils.SUPPORTED_DEVICE_FILTER).toList().toObservable();
        return observable;
    }
}
