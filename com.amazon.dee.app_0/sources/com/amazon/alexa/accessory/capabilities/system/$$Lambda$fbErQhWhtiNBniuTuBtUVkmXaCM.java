package com.amazon.alexa.accessory.capabilities.system;

import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$fbErQhWhtiNBniuTuBtUVkmXaCM  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$fbErQhWhtiNBniuTuBtUVkmXaCM implements SuccessResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$fbErQhWhtiNBniuTuBtUVkmXaCM INSTANCE = new $$Lambda$fbErQhWhtiNBniuTuBtUVkmXaCM();

    private /* synthetic */ $$Lambda$fbErQhWhtiNBniuTuBtUVkmXaCM() {
    }

    @Override // com.amazon.alexa.accessory.internal.SuccessResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return response.getUser();
    }
}
