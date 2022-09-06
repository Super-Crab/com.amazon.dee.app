package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.protocol.Accessories;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.-$$Lambda$mSUrIml8_VK9J0-SRYncmq0ApxE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$mSUrIml8_VK9J0SRYncmq0ApxE implements MappedResponseAction.Mapper {
    public static final /* synthetic */ $$Lambda$mSUrIml8_VK9J0SRYncmq0ApxE INSTANCE = new $$Lambda$mSUrIml8_VK9J0SRYncmq0ApxE();

    private /* synthetic */ $$Lambda$mSUrIml8_VK9J0SRYncmq0ApxE() {
    }

    @Override // com.amazon.alexa.accessory.internal.MappedResponseAction.Mapper
    public final Object map(Accessories.Response response) {
        return response.getErrorCode();
    }
}
