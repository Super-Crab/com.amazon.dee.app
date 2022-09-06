package com.amazon.alexa.identity;

import com.amazon.alexa.identity.MAPIdentityService;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$TplG8dtESPIIjeJO04bsKfvFa4I  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$MAPIdentityService$TplG8dtESPIIjeJO04bsKfvFa4I implements Func1 {
    public static final /* synthetic */ $$Lambda$MAPIdentityService$TplG8dtESPIIjeJO04bsKfvFa4I INSTANCE = new $$Lambda$MAPIdentityService$TplG8dtESPIIjeJO04bsKfvFa4I();

    private /* synthetic */ $$Lambda$MAPIdentityService$TplG8dtESPIIjeJO04bsKfvFa4I() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        String[] strArr;
        strArr = ((MAPIdentityService.DomainCookie) obj).cookies;
        return strArr;
    }
}
