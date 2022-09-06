package com.amazon.photos.uploader;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CrashReporter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, d2 = {"Lcom/amazon/photos/uploader/CrashReporter;", "", "reportCrash", "", "throwable", "", "samplePercentage", "", "extraCrashLog", "Lcom/amazon/photos/uploader/ExtraCrashLog;", "extraDataStr", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface CrashReporter {

    /* compiled from: CrashReporter.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void reportCrash$default(CrashReporter crashReporter, Throwable th, int i, ExtraCrashLog extraCrashLog, String str, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    i = 100;
                }
                if ((i2 & 4) != 0) {
                    extraCrashLog = ExtraCrashLog.NONE;
                }
                if ((i2 & 8) != 0) {
                    str = null;
                }
                crashReporter.reportCrash(th, i, extraCrashLog, str);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reportCrash");
        }
    }

    void reportCrash(@NotNull Throwable th, int i, @NotNull ExtraCrashLog extraCrashLog, @Nullable String str);
}
