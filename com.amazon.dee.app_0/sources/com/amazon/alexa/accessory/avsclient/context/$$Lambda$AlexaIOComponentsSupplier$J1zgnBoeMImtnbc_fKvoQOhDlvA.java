package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.AccessorySession;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$J1zgnBoeMImtnbc_fKvoQOhDlvA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaIOComponentsSupplier$J1zgnBoeMImtnbc_fKvoQOhDlvA implements Function {
    public static final /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$J1zgnBoeMImtnbc_fKvoQOhDlvA INSTANCE = new $$Lambda$AlexaIOComponentsSupplier$J1zgnBoeMImtnbc_fKvoQOhDlvA();

    private /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$J1zgnBoeMImtnbc_fKvoQOhDlvA() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        SingleSource map;
        map = ((AccessorySession) obj).getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).map($$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ.INSTANCE);
        return map;
    }
}
