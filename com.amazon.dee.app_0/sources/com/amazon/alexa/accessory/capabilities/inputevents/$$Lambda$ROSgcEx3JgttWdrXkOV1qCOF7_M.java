package com.amazon.alexa.accessory.capabilities.inputevents;

import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.inputevents.-$$Lambda$ROSgcEx3JgttWdrXkOV1qCOF7_M  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ROSgcEx3JgttWdrXkOV1qCOF7_M implements SuccessResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$ROSgcEx3JgttWdrXkOV1qCOF7_M INSTANCE = new $$Lambda$ROSgcEx3JgttWdrXkOV1qCOF7_M();

    private /* synthetic */ $$Lambda$ROSgcEx3JgttWdrXkOV1qCOF7_M() {
    }

    @Override // com.amazon.alexa.accessory.internal.SuccessResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return response.getInputBehaviorConfigurationSet();
    }
}
