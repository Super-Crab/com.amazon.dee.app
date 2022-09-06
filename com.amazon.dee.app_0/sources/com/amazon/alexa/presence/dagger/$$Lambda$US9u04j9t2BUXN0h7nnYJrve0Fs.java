package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.google.common.base.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.presence.dagger.-$$Lambda$US9u04j9t2BUXN0h7nnYJrve0Fs  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$US9u04j9t2BUXN0h7nnYJrve0Fs implements Function {
    public static final /* synthetic */ $$Lambda$US9u04j9t2BUXN0h7nnYJrve0Fs INSTANCE = new $$Lambda$US9u04j9t2BUXN0h7nnYJrve0Fs();

    private /* synthetic */ $$Lambda$US9u04j9t2BUXN0h7nnYJrve0Fs() {
    }

    @Override // com.google.common.base.Function
    /* renamed from: apply */
    public final Object mo8172apply(Object obj) {
        return ((EnvironmentService) obj).getCoralHost();
    }
}
