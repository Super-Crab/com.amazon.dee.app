package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$hzzhMkQ-J4PCnxbsEaZxXoBk3fs  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryInteractor$hzzhMkQJ4PCnxbsEaZxXoBk3fs implements Function {
    public static final /* synthetic */ $$Lambda$AccessoryInteractor$hzzhMkQJ4PCnxbsEaZxXoBk3fs INSTANCE = new $$Lambda$AccessoryInteractor$hzzhMkQJ4PCnxbsEaZxXoBk3fs();

    private /* synthetic */ $$Lambda$AccessoryInteractor$hzzhMkQJ4PCnxbsEaZxXoBk3fs() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Boolean valueOf;
        Device.DeviceConfiguration deviceConfiguration = (Device.DeviceConfiguration) obj;
        valueOf = Boolean.valueOf(!deviceConfiguration.getNeedsAssistantOverride());
        return valueOf;
    }
}
