package com.amazon.alexa.featureservice.repo.mapper;
/* loaded from: classes7.dex */
public interface DatabaseMapper<S, T> {
    S fromDatabaseModel(T t);

    T toDatabaseModel(S s);
}
