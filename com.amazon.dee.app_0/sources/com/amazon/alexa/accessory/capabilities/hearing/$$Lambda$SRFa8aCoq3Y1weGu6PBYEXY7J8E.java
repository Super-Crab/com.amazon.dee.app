package com.amazon.alexa.accessory.capabilities.hearing;

import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.hearing.-$$Lambda$SRFa8aCoq3Y1weGu6PBYEXY7J8E  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$SRFa8aCoq3Y1weGu6PBYEXY7J8E implements SuccessResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$SRFa8aCoq3Y1weGu6PBYEXY7J8E INSTANCE = new $$Lambda$SRFa8aCoq3Y1weGu6PBYEXY7J8E();

    private /* synthetic */ $$Lambda$SRFa8aCoq3Y1weGu6PBYEXY7J8E() {
    }

    @Override // com.amazon.alexa.accessory.internal.SuccessResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return response.getMediaEnhancementCorrectionAmount();
    }
}
