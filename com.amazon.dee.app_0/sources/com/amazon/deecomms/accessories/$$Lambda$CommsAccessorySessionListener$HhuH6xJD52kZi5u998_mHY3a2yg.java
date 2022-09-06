package com.amazon.deecomms.accessories;

import com.amazon.alexa.accessory.protocol.Device;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$HhuH6xJD52kZi5u998_mHY3a2yg  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsAccessorySessionListener$HhuH6xJD52kZi5u998_mHY3a2yg implements Comparator {
    public static final /* synthetic */ $$Lambda$CommsAccessorySessionListener$HhuH6xJD52kZi5u998_mHY3a2yg INSTANCE = new $$Lambda$CommsAccessorySessionListener$HhuH6xJD52kZi5u998_mHY3a2yg();

    private /* synthetic */ $$Lambda$CommsAccessorySessionListener$HhuH6xJD52kZi5u998_mHY3a2yg() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return CommsAccessorySessionListener.lambda$getPrimaryDeviceInformation$15((Device.DeviceInformation) obj, (Device.DeviceInformation) obj2);
    }
}
