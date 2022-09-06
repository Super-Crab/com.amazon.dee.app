package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.repositories.device.v2.Device;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryPresenceNotifier$8zelqArHmLxVyvNJCKTDG2mbOhk  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryPresenceNotifier$8zelqArHmLxVyvNJCKTDG2mbOhk implements Comparator {
    public static final /* synthetic */ $$Lambda$AccessoryPresenceNotifier$8zelqArHmLxVyvNJCKTDG2mbOhk INSTANCE = new $$Lambda$AccessoryPresenceNotifier$8zelqArHmLxVyvNJCKTDG2mbOhk();

    private /* synthetic */ $$Lambda$AccessoryPresenceNotifier$8zelqArHmLxVyvNJCKTDG2mbOhk() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return AccessoryPresenceNotifier.lambda$getPrimaryDeviceType$1((Device) obj, (Device) obj2);
    }
}
