package com.amazon.alexa.accessorykit.metrics;

import com.amazon.alexa.accessory.repositories.device.v2.Device;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.metrics.-$$Lambda$SessionMetricsListener$IZVvp-wHEseTD95iuJji40sNEZs  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$SessionMetricsListener$IZVvpwHEseTD95iuJji40sNEZs implements Comparator {
    public static final /* synthetic */ $$Lambda$SessionMetricsListener$IZVvpwHEseTD95iuJji40sNEZs INSTANCE = new $$Lambda$SessionMetricsListener$IZVvpwHEseTD95iuJji40sNEZs();

    private /* synthetic */ $$Lambda$SessionMetricsListener$IZVvpwHEseTD95iuJji40sNEZs() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return SessionMetricsListener.lambda$null$0((Device) obj, (Device) obj2);
    }
}
