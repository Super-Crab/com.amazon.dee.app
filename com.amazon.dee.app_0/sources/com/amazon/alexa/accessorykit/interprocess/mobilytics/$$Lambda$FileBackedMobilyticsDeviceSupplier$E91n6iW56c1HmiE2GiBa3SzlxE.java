package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$FileBackedMobilyticsDeviceSupplier$-E91n6iW56c1HmiE2GiBa3SzlxE  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FileBackedMobilyticsDeviceSupplier$E91n6iW56c1HmiE2GiBa3SzlxE implements Function {
    public static final /* synthetic */ $$Lambda$FileBackedMobilyticsDeviceSupplier$E91n6iW56c1HmiE2GiBa3SzlxE INSTANCE = new $$Lambda$FileBackedMobilyticsDeviceSupplier$E91n6iW56c1HmiE2GiBa3SzlxE();

    private /* synthetic */ $$Lambda$FileBackedMobilyticsDeviceSupplier$E91n6iW56c1HmiE2GiBa3SzlxE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Logger.e("%s Unexpected error in queryMobityticsDevice", (Throwable) obj, FileBackedMobilyticsDeviceSupplier.TAG);
    }
}
