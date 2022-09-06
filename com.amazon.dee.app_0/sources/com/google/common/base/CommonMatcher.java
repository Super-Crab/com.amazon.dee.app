package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
@GwtCompatible
/* loaded from: classes12.dex */
abstract class CommonMatcher {
    public abstract int end();

    public abstract boolean find();

    public abstract boolean find(int i);

    public abstract boolean matches();

    public abstract String replaceAll(String str);

    public abstract int start();
}
