package com.amazon.alexa.accessory.capabilities.state;

import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$5CxEGZ1MnH-C3ydwtSvcBS_QDIE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$5CxEGZ1MnHC3ydwtSvcBS_QDIE implements SuccessResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$5CxEGZ1MnHC3ydwtSvcBS_QDIE INSTANCE = new $$Lambda$5CxEGZ1MnHC3ydwtSvcBS_QDIE();

    private /* synthetic */ $$Lambda$5CxEGZ1MnHC3ydwtSvcBS_QDIE() {
    }

    @Override // com.amazon.alexa.accessory.internal.SuccessResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return response.getState();
    }
}
