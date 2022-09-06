package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingObject {
    /* renamed from: delegate */
    protected abstract Object mo8280delegate();

    public String toString() {
        return mo8280delegate().toString();
    }
}
