package com.amazon.commscore.api.base.mapper;
/* loaded from: classes12.dex */
public interface DatabaseMapper<S, T> {
    S fromDatabaseModel(T t);

    T toDatabaseModel(S s);
}
