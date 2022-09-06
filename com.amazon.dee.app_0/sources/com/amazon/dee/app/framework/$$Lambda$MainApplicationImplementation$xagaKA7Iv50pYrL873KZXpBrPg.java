package com.amazon.dee.app.framework;

import com.amazon.alexa.device.api.DevicePowerMonitor;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.framework.-$$Lambda$MainApplicationImplementation$xagaKA7Iv-50pYrL873KZXpBrPg  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$MainApplicationImplementation$xagaKA7Iv50pYrL873KZXpBrPg implements Runnable {
    public static final /* synthetic */ $$Lambda$MainApplicationImplementation$xagaKA7Iv50pYrL873KZXpBrPg INSTANCE = new $$Lambda$MainApplicationImplementation$xagaKA7Iv50pYrL873KZXpBrPg();

    private /* synthetic */ $$Lambda$MainApplicationImplementation$xagaKA7Iv50pYrL873KZXpBrPg() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        ((DevicePowerMonitor) GeneratedOutlineSupport1.outline20(DevicePowerMonitor.class)).start();
    }
}
