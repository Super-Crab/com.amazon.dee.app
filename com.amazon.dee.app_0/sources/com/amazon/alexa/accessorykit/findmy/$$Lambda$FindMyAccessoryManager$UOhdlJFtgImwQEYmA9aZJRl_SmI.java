package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$UOhdlJFtgImwQEYmA9aZJRl_SmI  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FindMyAccessoryManager$UOhdlJFtgImwQEYmA9aZJRl_SmI implements Consumer {
    public static final /* synthetic */ $$Lambda$FindMyAccessoryManager$UOhdlJFtgImwQEYmA9aZJRl_SmI INSTANCE = new $$Lambda$FindMyAccessoryManager$UOhdlJFtgImwQEYmA9aZJRl_SmI();

    private /* synthetic */ $$Lambda$FindMyAccessoryManager$UOhdlJFtgImwQEYmA9aZJRl_SmI() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s: monitorStatusForSession error querying deviceInformation", (Throwable) obj, FindMyAccessoryManager.TAG);
    }
}
