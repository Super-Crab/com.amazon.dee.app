package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$sBq35U2ViwNZl3ZuMY5BVSkzFEc  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultAccessoryExplorer$sBq35U2ViwNZl3ZuMY5BVSkzFEc implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultAccessoryExplorer$sBq35U2ViwNZl3ZuMY5BVSkzFEc INSTANCE = new $$Lambda$DefaultAccessoryExplorer$sBq35U2ViwNZl3ZuMY5BVSkzFEc();

    private /* synthetic */ $$Lambda$DefaultAccessoryExplorer$sBq35U2ViwNZl3ZuMY5BVSkzFEc() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Throwable th = (Throwable) obj;
        Logger.e("DefaultAccessoryExplorer: Failed to query expired standby accessories");
    }
}
