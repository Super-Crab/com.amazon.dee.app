package com.amazon.alexa.accessory.capabilities.cloudpairing;

import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.cloudpairing.-$$Lambda$Bhk07x6J0i6B-G7AqoOJaK_TzJ8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$Bhk07x6J0i6BG7AqoOJaK_TzJ8 implements SuccessResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$Bhk07x6J0i6BG7AqoOJaK_TzJ8 INSTANCE = new $$Lambda$Bhk07x6J0i6BG7AqoOJaK_TzJ8();

    private /* synthetic */ $$Lambda$Bhk07x6J0i6BG7AqoOJaK_TzJ8() {
    }

    @Override // com.amazon.alexa.accessory.internal.SuccessResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return response.getCloudPairingStatus();
    }
}
