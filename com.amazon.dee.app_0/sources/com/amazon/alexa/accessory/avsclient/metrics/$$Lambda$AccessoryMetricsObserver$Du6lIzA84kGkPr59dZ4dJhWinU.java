package com.amazon.alexa.accessory.avsclient.metrics;

import com.amazon.alexa.accessory.protocol.Device;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.metrics.-$$Lambda$AccessoryMetricsObserver$Du6lIzA84kGkPr59dZ-4dJhWinU  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AccessoryMetricsObserver$Du6lIzA84kGkPr59dZ4dJhWinU implements Comparator {
    public static final /* synthetic */ $$Lambda$AccessoryMetricsObserver$Du6lIzA84kGkPr59dZ4dJhWinU INSTANCE = new $$Lambda$AccessoryMetricsObserver$Du6lIzA84kGkPr59dZ4dJhWinU();

    private /* synthetic */ $$Lambda$AccessoryMetricsObserver$Du6lIzA84kGkPr59dZ4dJhWinU() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return AccessoryMetricsObserver.lambda$null$0((Device.DeviceInformation) obj, (Device.DeviceInformation) obj2);
    }
}
