package com.dee.app.cachemanager;

import androidx.annotation.Nullable;
import rx.Observable;
import rx.subjects.PublishSubject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class CacheEvents {
    private final PublishSubject<CacheEvent> subject = PublishSubject.create();

    public Observable<CacheEvent> getCacheEvents() {
        return this.subject;
    }

    public void logClearError(@Nullable String str, CacheMetadata cacheMetadata, @Nullable Throwable th) {
        this.subject.onNext(new CacheEvent(6, str, cacheMetadata, th));
    }

    public void logEviction(String str, CacheMetadata cacheMetadata, String str2) {
        this.subject.onNext(new CacheEvent(7, str, cacheMetadata, str2));
    }

    public void logGetError(@Nullable String str, CacheMetadata cacheMetadata, @Nullable Throwable th) {
        this.subject.onNext(new CacheEvent(3, str, cacheMetadata, th));
    }

    public void logHit(String str, CacheMetadata cacheMetadata) {
        this.subject.onNext(new CacheEvent(0, str, cacheMetadata, null));
    }

    public void logMiss(String str, CacheMetadata cacheMetadata) {
        this.subject.onNext(new CacheEvent(1, str, cacheMetadata, null));
    }

    public void logPutError(@Nullable String str, CacheMetadata cacheMetadata, @Nullable Throwable th) {
        this.subject.onNext(new CacheEvent(4, str, cacheMetadata, th));
    }

    public void logRemoveError(@Nullable String str, CacheMetadata cacheMetadata, @Nullable Throwable th) {
        this.subject.onNext(new CacheEvent(5, str, cacheMetadata, th));
    }
}
