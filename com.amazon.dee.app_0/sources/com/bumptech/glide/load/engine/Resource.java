package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
/* loaded from: classes6.dex */
public interface Resource<Z> {
    @NonNull
    /* renamed from: get */
    Z mo6789get();

    @NonNull
    Class<Z> getResourceClass();

    int getSize();

    void recycle();
}
