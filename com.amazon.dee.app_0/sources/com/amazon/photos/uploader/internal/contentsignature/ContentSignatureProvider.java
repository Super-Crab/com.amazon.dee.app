package com.amazon.photos.uploader.internal.contentsignature;

import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.util.MD5Fingerprint;
import com.amazon.imageutilities.DigestResult;
import com.amazon.imageutilities.JpegFormatException;
import com.amazon.imageutilities.JpegVisualDigest;
import com.amazon.photos.uploader.internal.OpenForTesting;
import com.amazon.photos.uploader.internal.utils.FileUtilsKt;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ContentSignatureProvider.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 '2\u00020\u0001:\u0003'()B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001d\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0001¢\u0006\u0002\b\u0011J\u001d\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0001¢\u0006\u0002\b\u0013J\u0015\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0001¢\u0006\u0002\b\u0017J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0015\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0001¢\u0006\u0002\b\u001cJ\u0015\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$J\u0015\u0010%\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b&R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider;", "", "visualDigestCalculator", "Lcom/amazon/imageutilities/JpegVisualDigest;", "md5Fingerprint", "Lcom/amazon/clouddrive/cdasdk/util/MD5Fingerprint;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "(Lcom/amazon/imageutilities/JpegVisualDigest;Lcom/amazon/clouddrive/cdasdk/util/MD5Fingerprint;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/log/UploadLogger;)V", "computeAllDigests", "Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider$Digests;", "inputStream", "Ljava/io/InputStream;", "fileName", "", "computeAllDigests$AndroidPhotosUploader_release", "computeMd5", "computeMd5$AndroidPhotosUploader_release", "computeMd5FromByteArray", "byteArray", "", "computeMd5FromByteArray$AndroidPhotosUploader_release", "computeUsingMd5FingerPrint", "computeVisualDigest", "getByteInputStream", "Ljava/io/ByteArrayInputStream;", "getByteInputStream$AndroidPhotosUploader_release", "isFilePresent", "", "file", "Ljava/io/File;", "isFilePresent$AndroidPhotosUploader_release", "resetInterruptFlag", "", "resetInterruptFlag$AndroidPhotosUploader_release", "shouldComputeVisualDigest", "shouldComputeVisualDigest$AndroidPhotosUploader_release", "Companion", "ContentSignatureException", "Digests", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ContentSignatureProvider {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "ContentSignatureProvider";
    private final UploadLogger logger;
    private final MD5Fingerprint md5Fingerprint;
    private final Metrics metrics;
    private final JpegVisualDigest visualDigestCalculator;

    /* compiled from: ContentSignatureProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: ContentSignatureProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider$ContentSignatureException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class ContentSignatureException extends Exception {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ContentSignatureException(@NotNull String message, @NotNull Throwable cause) {
            super(message, cause);
            Intrinsics.checkParameterIsNotNull(message, "message");
            Intrinsics.checkParameterIsNotNull(cause, "cause");
        }
    }

    /* compiled from: ContentSignatureProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider$Digests;", "", SierraContentProviderContract.MD5_VALUE, "", "visualDigest", "(Ljava/lang/String;Ljava/lang/String;)V", "getMd5", "()Ljava/lang/String;", "getVisualDigest", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Digests {
        @NotNull
        private final String md5;
        @Nullable
        private final String visualDigest;

        public Digests(@NotNull String md5, @Nullable String str) {
            Intrinsics.checkParameterIsNotNull(md5, "md5");
            this.md5 = md5;
            this.visualDigest = str;
        }

        public static /* synthetic */ Digests copy$default(Digests digests, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = digests.md5;
            }
            if ((i & 2) != 0) {
                str2 = digests.visualDigest;
            }
            return digests.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.md5;
        }

        @Nullable
        public final String component2() {
            return this.visualDigest;
        }

        @NotNull
        public final Digests copy(@NotNull String md5, @Nullable String str) {
            Intrinsics.checkParameterIsNotNull(md5, "md5");
            return new Digests(md5, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof Digests)) {
                    return false;
                }
                Digests digests = (Digests) obj;
                return Intrinsics.areEqual(this.md5, digests.md5) && Intrinsics.areEqual(this.visualDigest, digests.visualDigest);
            }
            return true;
        }

        @NotNull
        public final String getMd5() {
            return this.md5;
        }

        @Nullable
        public final String getVisualDigest() {
            return this.visualDigest;
        }

        public int hashCode() {
            String str = this.md5;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.visualDigest;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Digests(md5=");
            outline107.append(this.md5);
            outline107.append(", visualDigest=");
            return GeneratedOutlineSupport1.outline91(outline107, this.visualDigest, ")");
        }
    }

    public ContentSignatureProvider(@NotNull JpegVisualDigest visualDigestCalculator, @NotNull MD5Fingerprint md5Fingerprint, @NotNull Metrics metrics, @NotNull UploadLogger logger) {
        Intrinsics.checkParameterIsNotNull(visualDigestCalculator, "visualDigestCalculator");
        Intrinsics.checkParameterIsNotNull(md5Fingerprint, "md5Fingerprint");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.visualDigestCalculator = visualDigestCalculator;
        this.md5Fingerprint = md5Fingerprint;
        this.metrics = metrics;
        this.logger = logger;
    }

    private final String computeUsingMd5FingerPrint(InputStream inputStream) throws IOException, InterruptedException, NoSuchAlgorithmException {
        String calculate = this.md5Fingerprint.calculate(inputStream);
        Intrinsics.checkExpressionValueIsNotNull(calculate, "md5Fingerprint.calculate(inputStream)");
        return calculate;
    }

    private final String computeVisualDigest(InputStream inputStream) throws IOException, JpegFormatException {
        DigestResult digest = this.visualDigestCalculator.digest(inputStream);
        Intrinsics.checkExpressionValueIsNotNull(digest, "visualDigestCalculator.digest(inputStream)");
        String visualDigest = digest.getVisualDigest();
        Intrinsics.checkExpressionValueIsNotNull(visualDigest, "visualDigestCalculator.d…inputStream).visualDigest");
        return visualDigest;
    }

    @WorkerThread
    @NotNull
    public final Digests computeAllDigests$AndroidPhotosUploader_release(@NotNull InputStream inputStream, @NotNull String fileName) throws ContentSignatureException {
        String str;
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        try {
            str = computeVisualDigest(inputStream);
        } catch (Exception e) {
            e = e;
            str = null;
        }
        try {
            Unit unit = Unit.INSTANCE;
        } catch (Exception e2) {
            e = e2;
            this.logger.e$AndroidPhotosUploader_release(TAG, "Could not compute VisualDigest for the given file. Continue to compute MD5", e);
            this.metrics.recordSimpleErrorEvent(TAG, ContentSignatureProvider$computeAllDigests$1.INSTANCE, e);
            Unit unit2 = Unit.INSTANCE;
            return new Digests(computeMd5$AndroidPhotosUploader_release(inputStream, fileName).getMd5(), str);
        }
        return new Digests(computeMd5$AndroidPhotosUploader_release(inputStream, fileName).getMd5(), str);
    }

    @WorkerThread
    @NotNull
    public final Digests computeMd5$AndroidPhotosUploader_release(@NotNull InputStream inputStream, @NotNull String fileName) throws ContentSignatureException {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        try {
            return new Digests(computeUsingMd5FingerPrint(inputStream), null);
        } catch (InterruptedException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Computing Md5 for ");
            outline107.append(this.logger.obfuscate$AndroidPhotosUploader_release(fileName));
            outline107.append(" got interrupted.");
            String sb = outline107.toString();
            this.logger.e$AndroidPhotosUploader_release(TAG, sb, e);
            this.metrics.recordSimpleErrorEvent(TAG, ContentSignatureProvider$computeMd5$1.INSTANCE, e);
            resetInterruptFlag$AndroidPhotosUploader_release();
            throw new ContentSignatureException(sb, e);
        } catch (Exception e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Could not compute the Md5 for ");
            outline1072.append(this.logger.obfuscate$AndroidPhotosUploader_release(fileName));
            String sb2 = outline1072.toString();
            this.logger.e$AndroidPhotosUploader_release(TAG, sb2, e2);
            this.metrics.recordSimpleErrorEvent(TAG, ContentSignatureProvider$computeMd5$2.INSTANCE, e2);
            throw new ContentSignatureException(sb2, e2);
        }
    }

    @WorkerThread
    @NotNull
    public final String computeMd5FromByteArray$AndroidPhotosUploader_release(@NotNull byte[] byteArray) throws ContentSignatureException {
        Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
        try {
            String calculate = this.md5Fingerprint.calculate(new ByteArrayInputStream(byteArray));
            Intrinsics.checkExpressionValueIsNotNull(calculate, "md5Fingerprint.calculate(byteStream)");
            return calculate;
        } catch (InterruptedException e) {
            this.logger.e$AndroidPhotosUploader_release(TAG, "Computing Md5 for byte array got interrupted.", e);
            this.metrics.recordSimpleErrorEvent(TAG, ContentSignatureProvider$computeMd5FromByteArray$1.INSTANCE, e);
            resetInterruptFlag$AndroidPhotosUploader_release();
            throw new ContentSignatureException("Computing Md5 for byte array got interrupted.", e);
        } catch (Exception e2) {
            this.logger.e$AndroidPhotosUploader_release(TAG, "Could not compute the Md5 for byte array", e2);
            this.metrics.recordSimpleErrorEvent(TAG, ContentSignatureProvider$computeMd5FromByteArray$2.INSTANCE, e2);
            throw new ContentSignatureException("Could not compute the Md5 for byte array", e2);
        }
    }

    @VisibleForTesting
    @NotNull
    public final ByteArrayInputStream getByteInputStream$AndroidPhotosUploader_release(@NotNull byte[] byteArray) {
        Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
        return new ByteArrayInputStream(byteArray);
    }

    @WorkerThread
    public final boolean isFilePresent$AndroidPhotosUploader_release(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return file.exists();
    }

    @VisibleForTesting
    public final void resetInterruptFlag$AndroidPhotosUploader_release() {
        Thread.currentThread().interrupt();
    }

    public final boolean shouldComputeVisualDigest$AndroidPhotosUploader_release(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return FileUtilsKt.isJpeg(file);
    }
}
