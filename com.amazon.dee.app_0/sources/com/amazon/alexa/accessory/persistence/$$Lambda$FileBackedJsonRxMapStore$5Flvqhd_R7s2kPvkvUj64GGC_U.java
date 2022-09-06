package com.amazon.alexa.accessory.persistence;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.persistence.-$$Lambda$FileBackedJsonRxMapStore$5Flvqhd_R7s2kPvkvUj64-GGC_U  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FileBackedJsonRxMapStore$5Flvqhd_R7s2kPvkvUj64GGC_U implements Consumer {
    public static final /* synthetic */ $$Lambda$FileBackedJsonRxMapStore$5Flvqhd_R7s2kPvkvUj64GGC_U INSTANCE = new $$Lambda$FileBackedJsonRxMapStore$5Flvqhd_R7s2kPvkvUj64GGC_U();

    private /* synthetic */ $$Lambda$FileBackedJsonRxMapStore$5Flvqhd_R7s2kPvkvUj64GGC_U() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s Exception cleaning up after logout", (Throwable) obj, FileBackedJsonRxMapStore.TAG);
    }
}
