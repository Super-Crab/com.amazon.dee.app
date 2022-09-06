package com.amazon.photos.discovery;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: CrashReporter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/photos/discovery/CrashReporter;", "", "reportCrash", "", "throwable", "", "samplePercentage", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface CrashReporter {

    /* compiled from: CrashReporter.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void reportCrash$default(CrashReporter crashReporter, Throwable th, int i, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    i = 100;
                }
                crashReporter.reportCrash(th, i);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reportCrash");
        }
    }

    void reportCrash(@NotNull Throwable th, int i);
}
