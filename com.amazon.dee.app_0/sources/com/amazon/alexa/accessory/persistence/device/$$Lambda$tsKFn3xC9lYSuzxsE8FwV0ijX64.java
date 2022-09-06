package com.amazon.alexa.accessory.persistence.device;

import android.database.Cursor;
import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.persistence.device.-$$Lambda$tsKFn3xC9lYSuzxsE8FwV0ijX64  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$tsKFn3xC9lYSuzxsE8FwV0ijX64 implements Function {
    public static final /* synthetic */ $$Lambda$tsKFn3xC9lYSuzxsE8FwV0ijX64 INSTANCE = new $$Lambda$tsKFn3xC9lYSuzxsE8FwV0ijX64();

    private /* synthetic */ $$Lambda$tsKFn3xC9lYSuzxsE8FwV0ijX64() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return DeviceDatabaseUtils.mapToDeviceGroup((Cursor) obj);
    }
}
