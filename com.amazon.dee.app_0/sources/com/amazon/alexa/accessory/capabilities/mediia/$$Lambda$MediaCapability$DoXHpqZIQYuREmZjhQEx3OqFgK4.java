package com.amazon.alexa.accessory.capabilities.mediia;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$DoXHpqZIQYuREmZjhQEx3OqFgK4  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MediaCapability$DoXHpqZIQYuREmZjhQEx3OqFgK4 implements Consumer {
    public static final /* synthetic */ $$Lambda$MediaCapability$DoXHpqZIQYuREmZjhQEx3OqFgK4 INSTANCE = new $$Lambda$MediaCapability$DoXHpqZIQYuREmZjhQEx3OqFgK4();

    private /* synthetic */ $$Lambda$MediaCapability$DoXHpqZIQYuREmZjhQEx3OqFgK4() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Failed to send PlaybackStatusChanged event.", (Throwable) obj);
    }
}
