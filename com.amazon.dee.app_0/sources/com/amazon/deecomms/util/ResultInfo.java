package com.amazon.deecomms.util;

import android.util.Pair;
/* loaded from: classes12.dex */
public class ResultInfo<S, I> extends Pair<S, I> {
    public ResultInfo(S s, I i) {
        super(s, i);
    }

    public I getInfo() {
        return (I) ((Pair) this).second;
    }

    public S getStatus() {
        return (S) ((Pair) this).first;
    }
}
