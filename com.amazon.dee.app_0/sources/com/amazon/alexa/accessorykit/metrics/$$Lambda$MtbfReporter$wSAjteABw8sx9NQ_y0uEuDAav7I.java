package com.amazon.alexa.accessorykit.metrics;

import com.amazon.alexa.accessory.protocol.Device;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.metrics.-$$Lambda$MtbfReporter$wSAjteABw8sx9NQ_y0uEuDAav7I  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$MtbfReporter$wSAjteABw8sx9NQ_y0uEuDAav7I implements Comparator {
    public static final /* synthetic */ $$Lambda$MtbfReporter$wSAjteABw8sx9NQ_y0uEuDAav7I INSTANCE = new $$Lambda$MtbfReporter$wSAjteABw8sx9NQ_y0uEuDAav7I();

    private /* synthetic */ $$Lambda$MtbfReporter$wSAjteABw8sx9NQ_y0uEuDAav7I() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return MtbfReporter.lambda$null$2((Device.DeviceInformation) obj, (Device.DeviceInformation) obj2);
    }
}
