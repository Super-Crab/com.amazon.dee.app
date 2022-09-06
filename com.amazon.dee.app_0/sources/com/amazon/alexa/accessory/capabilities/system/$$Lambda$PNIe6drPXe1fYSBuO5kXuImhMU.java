package com.amazon.alexa.accessory.capabilities.system;

import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$PNIe6drPX-e1fYSBuO5kXuImhMU  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$PNIe6drPXe1fYSBuO5kXuImhMU implements SuccessResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$PNIe6drPXe1fYSBuO5kXuImhMU INSTANCE = new $$Lambda$PNIe6drPXe1fYSBuO5kXuImhMU();

    private /* synthetic */ $$Lambda$PNIe6drPXe1fYSBuO5kXuImhMU() {
    }

    @Override // com.amazon.alexa.accessory.internal.SuccessResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return response.getUsers();
    }
}
