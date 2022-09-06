package com.amazon.alexa.accessory.capabilities.cloudpairing;

import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.cloudpairing.-$$Lambda$VSwJhX8guIwtnE-NaW4T0Ei1hdQ  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$VSwJhX8guIwtnENaW4T0Ei1hdQ implements SuccessResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$VSwJhX8guIwtnENaW4T0Ei1hdQ INSTANCE = new $$Lambda$VSwJhX8guIwtnENaW4T0Ei1hdQ();

    private /* synthetic */ $$Lambda$VSwJhX8guIwtnENaW4T0Ei1hdQ() {
    }

    @Override // com.amazon.alexa.accessory.internal.SuccessResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return response.getCloudPairingAttributes();
    }
}
