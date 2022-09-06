package com.amazon.deecomms.util;

import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.subjects.PublishSubject;
/* loaded from: classes12.dex */
public final class StopWatch implements RemovalListener<String, Long> {
    public static final long DEFAULT_TIME_WHEN_MISSING_KEY = -1;
    private static final int MAX_DEFAULT_DURATION_IN_SECONDS = 3600;
    private static final int MAX_NUMBER_OF_STOP_WATCHES = 20;
    private Handler mHandler;
    private int mMaxDuration;
    private PublishSubject<String> mRemovableNotificationObservable;
    private Cache<String, Long> mStopWatchCache;

    public StopWatch(Handler handler) {
        this(handler, 3600);
    }

    public long elapsedTime(@NonNull String str) {
        Long ifPresent = this.mStopWatchCache.getIfPresent(str);
        if (ifPresent == null) {
            return -1L;
        }
        return SystemClock.elapsedRealtime() - ifPresent.longValue();
    }

    public Observable<String> getRemovalObservable() {
        if (this.mRemovableNotificationObservable == null) {
            this.mRemovableNotificationObservable = PublishSubject.create();
        }
        return this.mRemovableNotificationObservable;
    }

    @Override // com.google.common.cache.RemovalListener
    public void onRemoval(RemovalNotification<String, Long> removalNotification) {
        if (!removalNotification.wasEvicted() || this.mRemovableNotificationObservable == null) {
            return;
        }
        this.mRemovableNotificationObservable.onNext(removalNotification.getKey());
    }

    public void start(@NonNull String str) {
        this.mStopWatchCache.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
        this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.deecomms.util.StopWatch.1
            @Override // java.lang.Runnable
            public void run() {
                StopWatch.this.mStopWatchCache.cleanUp();
            }
        }, this.mMaxDuration * 1000);
    }

    public long startTime(@NonNull String str) {
        Long ifPresent = this.mStopWatchCache.getIfPresent(str);
        if (ifPresent == null) {
            return -1L;
        }
        return ifPresent.longValue();
    }

    public void stop(@NonNull String str) {
        this.mStopWatchCache.invalidate(str);
    }

    public StopWatch(Handler handler, int i) {
        this.mHandler = handler;
        this.mMaxDuration = i;
        this.mStopWatchCache = CacheBuilder.newBuilder().maximumSize(20L).expireAfterWrite(i, TimeUnit.SECONDS).removalListener(this).build();
    }
}
