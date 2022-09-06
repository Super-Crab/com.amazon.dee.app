package com.amazon.alexa.featureservice.repo.mapper;
/* loaded from: classes7.dex */
public interface RemoteMapper<S, T> {
    S fromRemoteModel(T t);

    T toRemoteModel(S s);
}
