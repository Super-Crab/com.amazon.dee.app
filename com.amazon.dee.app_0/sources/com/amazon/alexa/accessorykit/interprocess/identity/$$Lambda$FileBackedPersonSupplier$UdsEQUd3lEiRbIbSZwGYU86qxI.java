package com.amazon.alexa.accessorykit.interprocess.identity;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.identity.-$$Lambda$FileBackedPersonSupplier$-UdsEQUd3lEiRbIbSZwGYU86qxI  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FileBackedPersonSupplier$UdsEQUd3lEiRbIbSZwGYU86qxI implements Function {
    public static final /* synthetic */ $$Lambda$FileBackedPersonSupplier$UdsEQUd3lEiRbIbSZwGYU86qxI INSTANCE = new $$Lambda$FileBackedPersonSupplier$UdsEQUd3lEiRbIbSZwGYU86qxI();

    private /* synthetic */ $$Lambda$FileBackedPersonSupplier$UdsEQUd3lEiRbIbSZwGYU86qxI() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Logger.e("%s Unexpected error in queryPerson", (Throwable) obj, FileBackedPersonSupplier.TAG);
    }
}
