package com.amazon.alexa.mobilytics.session;

import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.mobilytics.session.-$$Lambda$SessionManager$yyPPwJXhURJfxy8zFepIMtoKpk8  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$SessionManager$yyPPwJXhURJfxy8zFepIMtoKpk8 implements Func1 {
    public static final /* synthetic */ $$Lambda$SessionManager$yyPPwJXhURJfxy8zFepIMtoKpk8 INSTANCE = new $$Lambda$SessionManager$yyPPwJXhURJfxy8zFepIMtoKpk8();

    private /* synthetic */ $$Lambda$SessionManager$yyPPwJXhURJfxy8zFepIMtoKpk8() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Boolean valueOf;
        Session session = (Session) obj;
        valueOf = Boolean.valueOf(r1.state() == 2);
        return valueOf;
    }
}
