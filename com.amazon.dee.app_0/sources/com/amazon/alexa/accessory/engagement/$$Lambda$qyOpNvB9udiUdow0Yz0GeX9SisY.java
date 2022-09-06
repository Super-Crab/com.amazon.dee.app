package com.amazon.alexa.accessory.engagement;

import com.amazon.alexa.accessory.AccessorySession;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.engagement.-$$Lambda$qyOpNvB9udiUdow0Yz0GeX9SisY  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$qyOpNvB9udiUdow0Yz0GeX9SisY implements AdapterFactory {
    public static final /* synthetic */ $$Lambda$qyOpNvB9udiUdow0Yz0GeX9SisY INSTANCE = new $$Lambda$qyOpNvB9udiUdow0Yz0GeX9SisY();

    private /* synthetic */ $$Lambda$qyOpNvB9udiUdow0Yz0GeX9SisY() {
    }

    @Override // com.amazon.alexa.accessory.engagement.AdapterFactory
    public final Object createFrom(Object obj) {
        return new AccessorySessionAttributes((AccessorySession) obj);
    }
}
