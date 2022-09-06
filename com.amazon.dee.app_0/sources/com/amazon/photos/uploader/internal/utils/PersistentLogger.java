package com.amazon.photos.uploader.internal.utils;

import android.util.Log;
import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.photos.uploader.dao.EventDao;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.callercontext.ContextChain;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PersistentLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0080\b\u0018\u0000 '2\u00020\u0001:\u0001'B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J \u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\u0018\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J \u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011J\u0010\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J$\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u0019\u0010 \u001a\u0004\u0018\u00010\u00112\b\u0010!\u001a\u0004\u0018\u00010\u0011H\u0000¢\u0006\u0002\b\"J\t\u0010#\u001a\u00020\u0011HÖ\u0001J\u0018\u0010$\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J \u0010$\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010%\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J \u0010%\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010&\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J \u0010&\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006("}, d2 = {"Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "eventDao", "Lcom/amazon/photos/uploader/dao/EventDao;", "(Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/photos/uploader/dao/EventDao;)V", "getEventDao", "()Lcom/amazon/photos/uploader/dao/EventDao;", "getLogger", "()Lcom/amazon/photos/uploader/log/UploadLogger;", "component1", "component2", "copy", "d", "", "tag", "", "message", "t", "", "e", "equals", "", "other", "", "hashCode", "", ContextChain.TAG_INFRA, "iPersistent", "logCustomerEvent", "logPersistent", "obfuscate", "text", "obfuscate$AndroidPhotosUploader_release", "toString", "v", "w", "wtf", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PersistentLogger implements Logger {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "PersistentLogger";
    @NotNull
    private final EventDao eventDao;
    @NotNull
    private final UploadLogger logger;

    /* compiled from: PersistentLogger.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/utils/PersistentLogger$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PersistentLogger(@NotNull UploadLogger logger, @NotNull EventDao eventDao) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(eventDao, "eventDao");
        this.logger = logger;
        this.eventDao = eventDao;
    }

    public static /* synthetic */ PersistentLogger copy$default(PersistentLogger persistentLogger, UploadLogger uploadLogger, EventDao eventDao, int i, Object obj) {
        if ((i & 1) != 0) {
            uploadLogger = persistentLogger.logger;
        }
        if ((i & 2) != 0) {
            eventDao = persistentLogger.eventDao;
        }
        return persistentLogger.copy(uploadLogger, eventDao);
    }

    private final void logPersistent(String str, String str2, Throwable th) {
        String outline75 = GeneratedOutlineSupport1.outline75(str, DeviceDatabaseUtils.DELIMITER, str2);
        if (th != null) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(outline75, DeviceDatabaseUtils.DELIMITER);
            outline113.append(Log.getStackTraceString(th));
            outline75 = outline113.toString();
        }
        UploadLogger uploadLogger = this.logger;
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "Adding [" + outline75 + JsonReaderKt.END_LIST);
        this.eventDao.recordEvent$AndroidPhotosUploader_release(outline75);
    }

    static /* synthetic */ void logPersistent$default(PersistentLogger persistentLogger, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        persistentLogger.logPersistent(str, str2, th);
    }

    @NotNull
    public final UploadLogger component1() {
        return this.logger;
    }

    @NotNull
    public final EventDao component2() {
        return this.eventDao;
    }

    @NotNull
    public final PersistentLogger copy(@NotNull UploadLogger logger, @NotNull EventDao eventDao) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(eventDao, "eventDao");
        return new PersistentLogger(logger, eventDao);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void d(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.logger.d$AndroidPhotosUploader_release(tag, message);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void e(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        logPersistent$default(this, tag, message, null, 4, null);
        this.logger.e$AndroidPhotosUploader_release(tag, message);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof PersistentLogger)) {
                return false;
            }
            PersistentLogger persistentLogger = (PersistentLogger) obj;
            return Intrinsics.areEqual(this.logger, persistentLogger.logger) && Intrinsics.areEqual(this.eventDao, persistentLogger.eventDao);
        }
        return true;
    }

    @NotNull
    public final EventDao getEventDao() {
        return this.eventDao;
    }

    @NotNull
    public final UploadLogger getLogger() {
        return this.logger;
    }

    public int hashCode() {
        UploadLogger uploadLogger = this.logger;
        int i = 0;
        int hashCode = (uploadLogger != null ? uploadLogger.hashCode() : 0) * 31;
        EventDao eventDao = this.eventDao;
        if (eventDao != null) {
            i = eventDao.hashCode();
        }
        return hashCode + i;
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void i(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.logger.i$AndroidPhotosUploader_release(tag, message);
    }

    public final void iPersistent(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        i(tag, message);
        logPersistent$default(this, tag, message, null, 4, null);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void logCustomerEvent(@NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Nullable
    public final String obfuscate$AndroidPhotosUploader_release(@Nullable String str) {
        return this.logger.obfuscate$AndroidPhotosUploader_release(str);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PersistentLogger(logger=");
        outline107.append(this.logger);
        outline107.append(", eventDao=");
        outline107.append(this.eventDao);
        outline107.append(")");
        return outline107.toString();
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void v(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.logger.v$AndroidPhotosUploader_release(tag, message);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void w(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        logPersistent$default(this, tag, message, null, 4, null);
        this.logger.w$AndroidPhotosUploader_release(tag, message);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void wtf(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        logPersistent$default(this, tag, message, null, 4, null);
        this.logger.wtf$AndroidPhotosUploader_release(tag, message);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void d(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        this.logger.d$AndroidPhotosUploader_release(tag, message, t);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void i(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        logPersistent(tag, message, t);
        this.logger.i$AndroidPhotosUploader_release(tag, message, t);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void v(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        this.logger.v$AndroidPhotosUploader_release(tag, message, t);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void e(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        logPersistent(tag, message, t);
        this.logger.e$AndroidPhotosUploader_release(tag, message, t);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void w(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        logPersistent(tag, message, t);
        this.logger.w$AndroidPhotosUploader_release(tag, message, t);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void wtf(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        this.logger.wtf$AndroidPhotosUploader_release(tag, message, t);
    }
}
