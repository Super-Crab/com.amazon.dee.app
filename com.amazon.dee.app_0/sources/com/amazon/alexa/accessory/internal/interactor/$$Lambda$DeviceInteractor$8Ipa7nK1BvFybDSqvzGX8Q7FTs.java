package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$8Ipa7nK1BvFybDS-qvzGX8Q7FTs  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceInteractor$8Ipa7nK1BvFybDSqvzGX8Q7FTs implements Consumer {
    public static final /* synthetic */ $$Lambda$DeviceInteractor$8Ipa7nK1BvFybDSqvzGX8Q7FTs INSTANCE = new $$Lambda$DeviceInteractor$8Ipa7nK1BvFybDSqvzGX8Q7FTs();

    private /* synthetic */ $$Lambda$DeviceInteractor$8Ipa7nK1BvFybDSqvzGX8Q7FTs() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("An error occurred while persisting information for newly linked devices", (Throwable) obj);
    }
}
