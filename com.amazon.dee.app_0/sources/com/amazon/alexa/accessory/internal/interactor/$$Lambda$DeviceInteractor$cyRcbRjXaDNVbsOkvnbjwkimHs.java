package com.amazon.alexa.accessory.internal.interactor;

import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$cyRcb-RjXaDNVbsOkvnbjwkimHs  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceInteractor$cyRcbRjXaDNVbsOkvnbjwkimHs implements Function {
    public static final /* synthetic */ $$Lambda$DeviceInteractor$cyRcbRjXaDNVbsOkvnbjwkimHs INSTANCE = new $$Lambda$DeviceInteractor$cyRcbRjXaDNVbsOkvnbjwkimHs();

    private /* synthetic */ $$Lambda$DeviceInteractor$cyRcbRjXaDNVbsOkvnbjwkimHs() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        List deviceInformationSetToDevices;
        deviceInformationSetToDevices = DeviceInteractor.deviceInformationSetToDevices((Set) obj);
        return deviceInformationSetToDevices;
    }
}
