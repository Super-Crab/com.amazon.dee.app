package com.amazon.dee.app.services.messaging;

import com.amazon.dee.app.services.logging.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.messaging.-$$Lambda$MessagingSettings$tj5cdcAGYWF6BVZ12WXlMagX3J0  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$MessagingSettings$tj5cdcAGYWF6BVZ12WXlMagX3J0 implements Action1 {
    public static final /* synthetic */ $$Lambda$MessagingSettings$tj5cdcAGYWF6BVZ12WXlMagX3J0 INSTANCE = new $$Lambda$MessagingSettings$tj5cdcAGYWF6BVZ12WXlMagX3J0();

    private /* synthetic */ $$Lambda$MessagingSettings$tj5cdcAGYWF6BVZ12WXlMagX3J0() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(MessagingSettings.TAG, "Error occurred in updating notification status to DMPS", (Throwable) obj);
    }
}
