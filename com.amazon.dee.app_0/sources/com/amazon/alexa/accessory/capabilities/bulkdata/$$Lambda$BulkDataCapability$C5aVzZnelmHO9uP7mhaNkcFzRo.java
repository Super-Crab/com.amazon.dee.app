package com.amazon.alexa.accessory.capabilities.bulkdata;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.bulkdata.-$$Lambda$BulkDataCapability$C5aVzZnelmHO9uP7mhaNkcF-zRo  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$BulkDataCapability$C5aVzZnelmHO9uP7mhaNkcFzRo implements Consumer {
    public static final /* synthetic */ $$Lambda$BulkDataCapability$C5aVzZnelmHO9uP7mhaNkcFzRo INSTANCE = new $$Lambda$BulkDataCapability$C5aVzZnelmHO9uP7mhaNkcFzRo();

    private /* synthetic */ $$Lambda$BulkDataCapability$C5aVzZnelmHO9uP7mhaNkcFzRo() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Failed to get device information. Bulk data capability will not handle messages", (Throwable) obj);
    }
}
