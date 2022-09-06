package com.amazon.alexa.mobilytics.session;

import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.mobilytics.session.-$$Lambda$SessionManager$h0H1a5uzZDfkZyiKVHha-kxExmY  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$SessionManager$h0H1a5uzZDfkZyiKVHhakxExmY implements Func1 {
    public static final /* synthetic */ $$Lambda$SessionManager$h0H1a5uzZDfkZyiKVHhakxExmY INSTANCE = new $$Lambda$SessionManager$h0H1a5uzZDfkZyiKVHhakxExmY();

    private /* synthetic */ $$Lambda$SessionManager$h0H1a5uzZDfkZyiKVHhakxExmY() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Session session = (Session) obj;
        SessionManager.lambda$onSessionStateChange$1(session);
        return session;
    }
}
