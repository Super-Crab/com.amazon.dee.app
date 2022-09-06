package com.amazon.alexa.accessory.capabilities.mediia;

import com.amazon.alexa.accessory.protocol.Media;
import io.reactivex.rxjava3.functions.BiPredicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$8zv8oOxPppuiw1UJIKpohME0xVw  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MediaCapability$8zv8oOxPppuiw1UJIKpohME0xVw implements BiPredicate {
    public static final /* synthetic */ $$Lambda$MediaCapability$8zv8oOxPppuiw1UJIKpohME0xVw INSTANCE = new $$Lambda$MediaCapability$8zv8oOxPppuiw1UJIKpohME0xVw();

    private /* synthetic */ $$Lambda$MediaCapability$8zv8oOxPppuiw1UJIKpohME0xVw() {
    }

    @Override // io.reactivex.rxjava3.functions.BiPredicate
    public final boolean test(Object obj, Object obj2) {
        return MediaCapability.lambda$null$1((Media.PlaybackStatus) obj, (Media.PlaybackStatus) obj2);
    }
}
