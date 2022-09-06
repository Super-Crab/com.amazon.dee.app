package com.amazon.alexa.accessory.capabilities.cbl;

import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.cbl.-$$Lambda$D92nwRpdqddfk0P8ANEmKYOcujA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$D92nwRpdqddfk0P8ANEmKYOcujA implements SuccessResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$D92nwRpdqddfk0P8ANEmKYOcujA INSTANCE = new $$Lambda$D92nwRpdqddfk0P8ANEmKYOcujA();

    private /* synthetic */ $$Lambda$D92nwRpdqddfk0P8ANEmKYOcujA() {
    }

    @Override // com.amazon.alexa.accessory.internal.SuccessResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return response.getCblInformation();
    }
}
