package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import java.io.IOException;
/* loaded from: classes2.dex */
public interface LoadErrorHandlingPolicy {

    /* loaded from: classes2.dex */
    public static final class LoadErrorInfo {
        public final int errorCount;
        public final IOException exception;
        public final LoadEventInfo loadEventInfo;
        public final MediaLoadData mediaLoadData;

        public LoadErrorInfo(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, int i) {
            this.loadEventInfo = loadEventInfo;
            this.mediaLoadData = mediaLoadData;
            this.exception = iOException;
            this.errorCount = i;
        }
    }

    @Deprecated
    default long getBlacklistDurationMsFor(int i, long j, IOException iOException, int i2) {
        throw new UnsupportedOperationException();
    }

    int getMinimumLoadableRetryCount(int i);

    @Deprecated
    default long getRetryDelayMsFor(int i, long j, IOException iOException, int i2) {
        throw new UnsupportedOperationException();
    }

    default void onLoadTaskConcluded(long j) {
    }

    default long getBlacklistDurationMsFor(LoadErrorInfo loadErrorInfo) {
        return getBlacklistDurationMsFor(loadErrorInfo.mediaLoadData.dataType, loadErrorInfo.loadEventInfo.loadDurationMs, loadErrorInfo.exception, loadErrorInfo.errorCount);
    }

    default long getRetryDelayMsFor(LoadErrorInfo loadErrorInfo) {
        return getRetryDelayMsFor(loadErrorInfo.mediaLoadData.dataType, loadErrorInfo.loadEventInfo.loadDurationMs, loadErrorInfo.exception, loadErrorInfo.errorCount);
    }
}
