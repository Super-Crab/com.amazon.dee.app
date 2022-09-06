package com.amazon.photos.uploader.log;

import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.photos.uploader.internal.OpenForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.callercontext.ContextChain;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadLogger.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0014\b\u0007\u0018\u0000  2\u00020\u0001:\u0002 !B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\fJ%\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\fJ\u001d\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\u0010J%\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0002\b\u0012J\u001d\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\u0014J%\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0014J\u0015\u0010\u0015\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\u0016J\u0019\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0002\b\u0019J\u001d\u0010\u001a\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\u001bJ%\u0010\u001a\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u001bJ\u001d\u0010\u001c\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\u001dJ%\u0010\u001c\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u001dJ\u001d\u0010\u001e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\u001fJ%\u0010\u001e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/photos/uploader/log/UploadLogger;", "", "coreLogger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "securityLevel", "Lcom/amazon/photos/uploader/log/UploadLogger$SecurityLevel;", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/photos/uploader/log/UploadLogger$SecurityLevel;)V", "d", "", "tag", "", "message", "d$AndroidPhotosUploader_release", "t", "", "e", "e$AndroidPhotosUploader_release", "getTag", "getTag$AndroidPhotosUploader_release", ContextChain.TAG_INFRA, "i$AndroidPhotosUploader_release", "logCustomerEvent", "logCustomerEvent$AndroidPhotosUploader_release", "obfuscate", "text", "obfuscate$AndroidPhotosUploader_release", "v", "v$AndroidPhotosUploader_release", "w", "w$AndroidPhotosUploader_release", "wtf", "wtf$AndroidPhotosUploader_release", "Companion", "SecurityLevel", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadLogger {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final int MAX_TAG_LENGTH = 23;
    private static final String PREFIX = "UPLDR_";
    private final Logger coreLogger;
    private final SecurityLevel securityLevel;

    /* compiled from: UploadLogger.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/uploader/log/UploadLogger$Companion;", "", "()V", "MAX_TAG_LENGTH", "", "PREFIX", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: UploadLogger.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/log/UploadLogger$SecurityLevel;", "", "(Ljava/lang/String;I)V", "CLEAR_TEXT", "OBFUSCATED", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public enum SecurityLevel {
        CLEAR_TEXT,
        OBFUSCATED
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SecurityLevel.values().length];

        static {
            $EnumSwitchMapping$0[SecurityLevel.CLEAR_TEXT.ordinal()] = 1;
        }
    }

    public UploadLogger(@NotNull Logger coreLogger, @NotNull SecurityLevel securityLevel) {
        Intrinsics.checkParameterIsNotNull(coreLogger, "coreLogger");
        Intrinsics.checkParameterIsNotNull(securityLevel, "securityLevel");
        this.coreLogger = coreLogger;
        this.securityLevel = securityLevel;
    }

    public final void d$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.coreLogger.d(getTag$AndroidPhotosUploader_release(tag), message);
    }

    public final void e$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.coreLogger.e(getTag$AndroidPhotosUploader_release(tag), message);
    }

    @VisibleForTesting
    @NotNull
    public final String getTag$AndroidPhotosUploader_release(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        String str = PREFIX + tag;
        if (str.length() > 23) {
            String substring = str.substring(0, 23);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        return str;
    }

    public final void i$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.coreLogger.i(getTag$AndroidPhotosUploader_release(tag), message);
    }

    public final void logCustomerEvent$AndroidPhotosUploader_release(@NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.coreLogger.logCustomerEvent(message);
    }

    @Nullable
    public final String obfuscate$AndroidPhotosUploader_release(@Nullable String str) {
        if (str == null) {
            return null;
        }
        if (WhenMappings.$EnumSwitchMapping$0[this.securityLevel.ordinal()] == 1) {
            return str;
        }
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_LIST);
        outline104.append(SecurityLevel.OBFUSCATED.name());
        outline104.append(JsonReaderKt.END_LIST);
        return outline104.toString();
    }

    public final void v$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.coreLogger.v(getTag$AndroidPhotosUploader_release(tag), message);
    }

    public final void w$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.coreLogger.w(getTag$AndroidPhotosUploader_release(tag), message);
    }

    public final void wtf$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.coreLogger.wtf(getTag$AndroidPhotosUploader_release(tag), message);
    }

    public final void d$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        this.coreLogger.d(getTag$AndroidPhotosUploader_release(tag), message, t);
    }

    public final void e$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        this.coreLogger.e(getTag$AndroidPhotosUploader_release(tag), message, t);
    }

    public final void i$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        this.coreLogger.i(getTag$AndroidPhotosUploader_release(tag), message, t);
    }

    public final void v$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        this.coreLogger.v(getTag$AndroidPhotosUploader_release(tag), message, t);
    }

    public final void w$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        this.coreLogger.w(getTag$AndroidPhotosUploader_release(tag), message, t);
    }

    public final void wtf$AndroidPhotosUploader_release(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        this.coreLogger.wtf(getTag$AndroidPhotosUploader_release(tag), message, t);
    }
}
