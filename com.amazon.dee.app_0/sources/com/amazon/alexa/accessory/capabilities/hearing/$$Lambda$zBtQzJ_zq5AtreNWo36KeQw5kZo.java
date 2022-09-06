package com.amazon.alexa.accessory.capabilities.hearing;

import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.hearing.-$$Lambda$zBtQzJ_zq5AtreNWo36KeQw5kZo  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$zBtQzJ_zq5AtreNWo36KeQw5kZo implements SuccessResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$zBtQzJ_zq5AtreNWo36KeQw5kZo INSTANCE = new $$Lambda$zBtQzJ_zq5AtreNWo36KeQw5kZo();

    private /* synthetic */ $$Lambda$zBtQzJ_zq5AtreNWo36KeQw5kZo() {
    }

    @Override // com.amazon.alexa.accessory.internal.SuccessResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return response.getAudiogram();
    }
}
