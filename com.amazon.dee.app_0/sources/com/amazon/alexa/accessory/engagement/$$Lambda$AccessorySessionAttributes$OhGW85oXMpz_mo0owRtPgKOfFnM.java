package com.amazon.alexa.accessory.engagement;

import com.amazon.alexa.accessory.protocol.Firmware;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.engagement.-$$Lambda$AccessorySessionAttributes$OhGW85oXMpz_mo0owRtPgKOfFnM  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AccessorySessionAttributes$OhGW85oXMpz_mo0owRtPgKOfFnM implements Function {
    public static final /* synthetic */ $$Lambda$AccessorySessionAttributes$OhGW85oXMpz_mo0owRtPgKOfFnM INSTANCE = new $$Lambda$AccessorySessionAttributes$OhGW85oXMpz_mo0owRtPgKOfFnM();

    private /* synthetic */ $$Lambda$AccessorySessionAttributes$OhGW85oXMpz_mo0owRtPgKOfFnM() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        String num;
        num = Integer.toString(((Firmware.FirmwareInformation) obj).getVersion());
        return num;
    }
}
