package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.AccessoryScannerListener;
import com.amazon.alexa.accessory.internal.util.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$JhHUOaPpmqzL2q8WgnhfCfYHwHg  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$JhHUOaPpmqzL2q8WgnhfCfYHwHg implements Consumer {
    public static final /* synthetic */ $$Lambda$JhHUOaPpmqzL2q8WgnhfCfYHwHg INSTANCE = new $$Lambda$JhHUOaPpmqzL2q8WgnhfCfYHwHg();

    private /* synthetic */ $$Lambda$JhHUOaPpmqzL2q8WgnhfCfYHwHg() {
    }

    @Override // com.amazon.alexa.accessory.internal.util.functions.Consumer
    public final void accept(Object obj) {
        ((AccessoryScannerListener) obj).onAccessoryScanCancelled();
    }
}
