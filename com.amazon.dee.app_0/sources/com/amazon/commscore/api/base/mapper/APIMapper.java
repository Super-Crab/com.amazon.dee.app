package com.amazon.commscore.api.base.mapper;
/* loaded from: classes12.dex */
public interface APIMapper<S, T> {
    S fromAPIModel(T t);

    T toAPIModel(S s);
}
