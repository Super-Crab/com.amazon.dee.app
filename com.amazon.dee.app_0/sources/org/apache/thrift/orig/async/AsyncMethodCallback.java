package org.apache.thrift.orig.async;
/* loaded from: classes4.dex */
public interface AsyncMethodCallback<T> {
    void onComplete(T t);

    void onError(Exception exc);
}
