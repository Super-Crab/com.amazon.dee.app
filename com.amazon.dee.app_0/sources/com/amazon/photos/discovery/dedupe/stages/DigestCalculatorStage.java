package com.amazon.photos.discovery.dedupe.stages;

import android.content.ContentResolver;
import android.net.Uri;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.clouddrive.cdasdk.util.MD5Fingerprint;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.imageutilities.DigestResult;
import com.amazon.imageutilities.JpegFormatException;
import com.amazon.imageutilities.JpegVisualDigest;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.dedupe.DeduplicationRequest;
import com.amazon.photos.discovery.dedupe.Deduplicator;
import com.amazon.photos.discovery.dedupe.DeduplicatorResult;
import com.amazon.photos.discovery.dedupe.RetryDedupeException;
import com.amazon.photos.discovery.internal.ConstantsKt;
import com.amazon.photos.discovery.internal.Injectable;
import com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent;
import com.amazon.photos.discovery.internal.model.Digest;
import com.amazon.photos.discovery.internal.util.AbortableCountDownLatch;
import com.amazon.photos.discovery.internal.util.FileUtils;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DigestCalculatorStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0002J \u0010E\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\u0006\u0010F\u001a\u00020G2\u0006\u0010A\u001a\u00020BH\u0002J \u0010H\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\u0006\u0010F\u001a\u00020G2\u0006\u0010A\u001a\u00020BH\u0002J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020LH\u0016J \u0010M\u001a\u0004\u0018\u00010>2\u0006\u0010N\u001a\u00020O2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020G0QH\u0002J\u0010\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020UH\u0016J\b\u0010V\u001a\u00020SH\u0002J\u0014\u0010W\u001a\u00020S2\n\u0010X\u001a\u00060Yj\u0002`ZH\u0002J\u0018\u0010[\u001a\u00020S2\u0006\u0010A\u001a\u00020B2\u0006\u0010F\u001a\u00020GH\u0002R\u001e\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001f\u001a\u00020 8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010%\u001a\u00020&8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001e\u0010+\u001a\u00020,8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001e\u00101\u001a\u0002028\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001e\u00107\u001a\u0002088\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\\"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/stages/DigestCalculatorStage;", "Lcom/amazon/photos/discovery/dedupe/Deduplicator;", "Lcom/amazon/photos/discovery/internal/Injectable;", "()V", "threadPoolExecutor", "Ljava/util/concurrent/ThreadPoolExecutor;", "(Ljava/util/concurrent/ThreadPoolExecutor;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "getConfiguration$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "setConfiguration$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/DiscoveryConfiguration;)V", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver$AndroidPhotosDiscovery_release", "()Landroid/content/ContentResolver;", "setContentResolver$AndroidPhotosDiscovery_release", "(Landroid/content/ContentResolver;)V", "fileUtils", "Lcom/amazon/photos/discovery/internal/util/FileUtils;", "getFileUtils$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/FileUtils;", "setFileUtils$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/FileUtils;)V", "jpegVisualDigest", "Lcom/amazon/imageutilities/JpegVisualDigest;", "getJpegVisualDigest", "()Lcom/amazon/imageutilities/JpegVisualDigest;", "jpegVisualDigest$delegate", "Lkotlin/Lazy;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "getLogger$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "setLogger$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "md5Fingerprint", "Lcom/amazon/clouddrive/cdasdk/util/MD5Fingerprint;", "getMd5Fingerprint$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/cdasdk/util/MD5Fingerprint;", "setMd5Fingerprint$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/cdasdk/util/MD5Fingerprint;)V", "mediaStoreUtil", "Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "getMediaStoreUtil$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "setMediaStoreUtil$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;)V", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getMetrics$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "systemUtil", "Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "getSystemUtil$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "setSystemUtil$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;)V", "calculateDigestValues", "Lcom/amazon/photos/discovery/internal/model/Digest;", "inputStream", "Ljava/io/InputStream;", "itemType", "Lcom/amazon/photos/discovery/model/ItemType;", "fileExtension", "", "calculateJpegDigest", "startTime", "", "calculateMD5Digest", "deduplicate", "Lcom/amazon/photos/discovery/dedupe/DeduplicatorResult;", "request", "Lcom/amazon/photos/discovery/dedupe/DeduplicationRequest;", "getDigestOrAddToSkipped", "localItem", "Lcom/amazon/photos/discovery/model/LocalItem;", "itemsSkipped", "", "inject", "", JsonFields.COMPONENT, "Lcom/amazon/photos/discovery/internal/dagger/component/DiscoveryComponent;", "onDigestPersistenceError", "recordExceptionMetricsForDigestCalculation", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "recordMd5CalculationMetric", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DigestCalculatorStage implements Deduplicator, Injectable {
    @Inject
    @NotNull
    public DiscoveryConfiguration configuration;
    @Inject
    @NotNull
    public ContentResolver contentResolver;
    @Inject
    @NotNull
    public FileUtils fileUtils;
    private final Lazy jpegVisualDigest$delegate;
    @Inject
    @NotNull
    public Logger logger;
    @Inject
    @NotNull
    public MD5Fingerprint md5Fingerprint;
    @Inject
    @NotNull
    public MediaStoreUtil mediaStoreUtil;
    @Inject
    @NotNull
    public Metrics metrics;
    @Inject
    @NotNull
    public SystemUtil systemUtil;
    private final ThreadPoolExecutor threadPoolExecutor;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ItemType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[ItemType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$0[ItemType.VIDEO.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[ItemType.values().length];
            $EnumSwitchMapping$1[ItemType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$1[ItemType.VIDEO.ordinal()] = 2;
        }
    }

    public DigestCalculatorStage(@NotNull ThreadPoolExecutor threadPoolExecutor) {
        Intrinsics.checkParameterIsNotNull(threadPoolExecutor, "threadPoolExecutor");
        this.threadPoolExecutor = threadPoolExecutor;
        this.jpegVisualDigest$delegate = LazyKt.lazy(new DigestCalculatorStage$jpegVisualDigest$2(this));
    }

    private final Digest calculateDigestValues(InputStream inputStream, ItemType itemType, String str) {
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        long elapsedRealTimeMillis = systemUtil.elapsedRealTimeMillis();
        DigestCalculatorStage$calculateDigestValues$1 digestCalculatorStage$calculateDigestValues$1 = DigestCalculatorStage$calculateDigestValues$1.INSTANCE;
        DigestCalculatorStage$calculateDigestValues$2 digestCalculatorStage$calculateDigestValues$2 = new DigestCalculatorStage$calculateDigestValues$2(this);
        try {
            DiscoveryConfiguration discoveryConfiguration = this.configuration;
            if (discoveryConfiguration == null) {
                Intrinsics.throwUninitializedPropertyAccessException(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
            }
            if (discoveryConfiguration.getUseVisualDigest() && digestCalculatorStage$calculateDigestValues$1.invoke2(str)) {
                return calculateJpegDigest(inputStream, elapsedRealTimeMillis, itemType);
            }
            return calculateMD5Digest(inputStream, elapsedRealTimeMillis, itemType);
        } catch (IOException e) {
            digestCalculatorStage$calculateDigestValues$2.invoke2((Exception) e);
            return new Digest(null, null, 3, null);
        } catch (NoSuchAlgorithmException e2) {
            digestCalculatorStage$calculateDigestValues$2.invoke2((Exception) e2);
            return new Digest(null, null, 3, null);
        }
    }

    private final Digest calculateJpegDigest(InputStream inputStream, long j, ItemType itemType) throws IOException {
        try {
            DigestResult digestResult = getJpegVisualDigest().digest(inputStream);
            Metrics metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            DiscoveryMetrics discoveryMetrics = DiscoveryMetrics.DigestCalculation;
            SystemUtil systemUtil = this.systemUtil;
            if (systemUtil == null) {
                Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
            }
            metrics.recordSimpleDuration(ConstantsKt.DIGEST_CALCULATOR_STAGE, discoveryMetrics, systemUtil.elapsedRealTimeMillis() - j);
            Intrinsics.checkExpressionValueIsNotNull(digestResult, "digestResult");
            return new Digest(digestResult.getFileDigest(), digestResult.getVisualDigest());
        } catch (JpegFormatException e) {
            Logger logger = this.logger;
            if (logger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            logger.w(ConstantsKt.DIGEST_CALCULATOR_STAGE, "Encountered JpegFormatException, falling back to plain MD5", e);
            Metrics metrics2 = this.metrics;
            if (metrics2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics2.recordSimpleErrorEvent(ConstantsKt.DIGEST_CALCULATOR_STAGE, DiscoveryMetrics.DigestCalculationError, e);
            return calculateMD5Digest(inputStream, j, itemType);
        }
    }

    private final Digest calculateMD5Digest(InputStream inputStream, long j, ItemType itemType) throws IOException, InterruptedException, NoSuchAlgorithmException {
        MD5Fingerprint mD5Fingerprint = this.md5Fingerprint;
        if (mD5Fingerprint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("md5Fingerprint");
        }
        String calculate = mD5Fingerprint.calculate(inputStream);
        recordMd5CalculationMetric(itemType, j);
        return new Digest(calculate, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Digest getDigestOrAddToSkipped(LocalItem localItem, List<Long> list) {
        Uri imageContentUri;
        Digest digest;
        if (localItem.getMd5() != null) {
            Logger logger = this.logger;
            if (logger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MD5 already present for ");
            outline107.append(localItem.getId());
            logger.d(ConstantsKt.DIGEST_CALCULATOR_STAGE, outline107.toString());
            return null;
        }
        long id = localItem.getId();
        String filePath = localItem.getFilePath();
        if (filePath == null) {
            list.add(Long.valueOf(localItem.getId()));
            Metrics metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            metrics.recordSimpleEvent(ConstantsKt.DIGEST_CALCULATOR_STAGE, DiscoveryMetrics.DigestCalculationFilePathMissingError, MetricRecordingType.STANDARD);
            Logger logger2 = this.logger;
            if (logger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("File path not present for local id: ");
            outline1072.append(localItem.getId());
            outline1072.append(", skipping digest calculation");
            logger2.w(ConstantsKt.DIGEST_CALCULATOR_STAGE, outline1072.toString());
            return null;
        }
        FileUtils fileUtils = this.fileUtils;
        if (fileUtils == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fileUtils");
        }
        String fileExtension = fileUtils.getFileExtension(new File(filePath));
        try {
            int i = WhenMappings.$EnumSwitchMapping$0[localItem.getType().ordinal()];
            if (i == 1) {
                MediaStoreUtil mediaStoreUtil = this.mediaStoreUtil;
                if (mediaStoreUtil == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaStoreUtil");
                }
                imageContentUri = mediaStoreUtil.getImageContentUri(id);
            } else if (i != 2) {
                throw new NoWhenBranchMatchedException();
            } else {
                MediaStoreUtil mediaStoreUtil2 = this.mediaStoreUtil;
                if (mediaStoreUtil2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mediaStoreUtil");
                }
                imageContentUri = mediaStoreUtil2.getVideoContentUri(id);
            }
            ContentResolver contentResolver = this.contentResolver;
            if (contentResolver == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentResolver");
            }
            InputStream openInputStream = contentResolver.openInputStream(imageContentUri);
            if (openInputStream == null) {
                list.add(Long.valueOf(id));
                Metrics metrics2 = this.metrics;
                if (metrics2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics2.recordSimpleEvent(ConstantsKt.DIGEST_CALCULATOR_STAGE, DiscoveryMetrics.DigestCalculationFileDoesNotExist, MetricRecordingType.STANDARD);
                Logger logger3 = this.logger;
                if (logger3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logger");
                }
                logger3.w(ConstantsKt.DIGEST_CALCULATOR_STAGE, "Unable to open file content Uri for local id: " + id);
            }
            if (openInputStream != null) {
                digest = calculateDigestValues(new BufferedInputStream(openInputStream), localItem.getType(), fileExtension);
                Unit unit = Unit.INSTANCE;
            } else {
                digest = null;
            }
            CloseableKt.closeFinally(openInputStream, null);
            return digest;
        } catch (Exception e) {
            if (!(e instanceof InterruptedException)) {
                list.add(Long.valueOf(localItem.getId()));
                recordExceptionMetricsForDigestCalculation(e);
                Logger logger4 = this.logger;
                if (logger4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logger");
                }
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Access file for local id: ");
                outline1073.append(localItem.getId());
                outline1073.append(" met with ");
                outline1073.append(e.getClass());
                outline1073.append(", skipping digest calculation");
                logger4.w(ConstantsKt.DIGEST_CALCULATOR_STAGE, outline1073.toString());
                return null;
            }
            throw e;
        }
    }

    private final JpegVisualDigest getJpegVisualDigest() {
        return (JpegVisualDigest) this.jpegVisualDigest$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDigestPersistenceError() {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        logger.d(ConstantsKt.DIGEST_CALCULATOR_STAGE, "Failed to persist Digest values.");
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleEvent(ConstantsKt.DIGEST_CALCULATOR_STAGE, DiscoveryMetrics.DigestPersistenceFailure, MetricRecordingType.STANDARD);
    }

    private final void recordExceptionMetricsForDigestCalculation(final Exception exc) {
        MetricName metricName;
        if (exc instanceof FileNotFoundException) {
            metricName = DiscoveryMetrics.DigestCalculationFileDoesNotExist;
        } else if (exc instanceof IOException) {
            metricName = DiscoveryMetrics.DigestCalculationIOException;
        } else {
            metricName = exc instanceof IllegalStateException ? DiscoveryMetrics.DigestCalculationIllegalState : new MetricName() { // from class: com.amazon.photos.discovery.dedupe.stages.DigestCalculatorStage$recordExceptionMetricsForDigestCalculation$metrics$1
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    return DiscoveryMetrics.DigestCalculationError.name() + exc.getClass();
                }
            };
        }
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordSimpleEvent(ConstantsKt.DIGEST_CALCULATOR_STAGE, metricName, MetricRecordingType.STANDARD);
    }

    private final void recordMd5CalculationMetric(ItemType itemType, long j) {
        DiscoveryMetrics discoveryMetrics;
        int i = WhenMappings.$EnumSwitchMapping$1[itemType.ordinal()];
        if (i == 1) {
            discoveryMetrics = DiscoveryMetrics.DigestPhotoMd5Calculation;
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            discoveryMetrics = DiscoveryMetrics.DigestVideoMd5Calculation;
        }
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        metrics.recordSimpleDuration(ConstantsKt.DIGEST_CALCULATOR_STAGE, discoveryMetrics, systemUtil.elapsedRealTimeMillis() - j);
    }

    @Override // com.amazon.photos.discovery.dedupe.Deduplicator
    @NotNull
    public DeduplicatorResult deduplicate(@NotNull final DeduplicationRequest request) throws RetryDedupeException {
        Intrinsics.checkParameterIsNotNull(request, "request");
        final ArrayList arrayList = new ArrayList();
        final AtomicInteger atomicInteger = new AtomicInteger();
        Collection<UnifiedItem> unifiedItems = request.getUnifiedItems();
        ArrayList<LocalItem> arrayList2 = new ArrayList();
        for (UnifiedItem unifiedItem : unifiedItems) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, unifiedItem.getLocalItems());
        }
        final AbortableCountDownLatch abortableCountDownLatch = new AbortableCountDownLatch(arrayList2.size());
        for (final LocalItem localItem : arrayList2) {
            this.threadPoolExecutor.execute(new Runnable() { // from class: com.amazon.photos.discovery.dedupe.stages.DigestCalculatorStage$deduplicate$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    Digest digestOrAddToSkipped;
                    Unit unit;
                    try {
                        if (abortableCountDownLatch.isAborted()) {
                            return;
                        }
                        try {
                            digestOrAddToSkipped = this.getDigestOrAddToSkipped(LocalItem.this, arrayList);
                            if (digestOrAddToSkipped != null) {
                                Metrics metrics$AndroidPhotosDiscovery_release = this.getMetrics$AndroidPhotosDiscovery_release();
                                try {
                                    request.getDedupeDao().updateLocalDigest(LocalItem.this.getId(), digestOrAddToSkipped.getMd5(), digestOrAddToSkipped.getVisualDigest());
                                    unit = Unit.INSTANCE;
                                } catch (Exception e) {
                                    metrics$AndroidPhotosDiscovery_release.recordSimpleErrorEvent(DiscoveryMetricsKt.DB_ERROR_COMPONENT, new MetricName() { // from class: com.amazon.photos.discovery.dedupe.stages.DigestCalculatorStage$deduplicate$$inlined$forEach$lambda$1.1
                                        @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                                        @NotNull
                                        public final String getEventName() {
                                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(DiscoveryMetricsKt.DB_ERROR_PREFIX);
                                            outline107.append(r1);
                                            return outline107.toString();
                                        }
                                    }, e);
                                    unit = null;
                                }
                                if (unit == null) {
                                    this.onDigestPersistenceError();
                                    Unit unit2 = Unit.INSTANCE;
                                }
                                atomicInteger.incrementAndGet();
                            }
                        } catch (InterruptedException e2) {
                            this.getLogger$AndroidPhotosDiscovery_release().e(ConstantsKt.DIGEST_CALCULATOR_STAGE, "Digest calculator threadpool interrupted.", e2);
                            abortableCountDownLatch.abort(e2);
                        }
                    } finally {
                        abortableCountDownLatch.countDown();
                    }
                }
            });
        }
        abortableCountDownLatch.await();
        ClientMetric withTagName = new ClientMetric().addCounter(DiscoveryMetrics.DigestLocalItemsSkipped, arrayList.size()).withTagName(ConstantsKt.DIGEST_CALCULATOR_STAGE);
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordCustomMetric(ConstantsKt.DIGEST_CALCULATOR_STAGE, withTagName, MetricRecordingType.STANDARD);
        return new DeduplicatorResult(atomicInteger.get(), ConstantsKt.DIGEST_CALCULATOR_STAGE);
    }

    @NotNull
    public final DiscoveryConfiguration getConfiguration$AndroidPhotosDiscovery_release() {
        DiscoveryConfiguration discoveryConfiguration = this.configuration;
        if (discoveryConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
        }
        return discoveryConfiguration;
    }

    @NotNull
    public final ContentResolver getContentResolver$AndroidPhotosDiscovery_release() {
        ContentResolver contentResolver = this.contentResolver;
        if (contentResolver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentResolver");
        }
        return contentResolver;
    }

    @NotNull
    public final FileUtils getFileUtils$AndroidPhotosDiscovery_release() {
        FileUtils fileUtils = this.fileUtils;
        if (fileUtils == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fileUtils");
        }
        return fileUtils;
    }

    @NotNull
    public final Logger getLogger$AndroidPhotosDiscovery_release() {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return logger;
    }

    @NotNull
    public final MD5Fingerprint getMd5Fingerprint$AndroidPhotosDiscovery_release() {
        MD5Fingerprint mD5Fingerprint = this.md5Fingerprint;
        if (mD5Fingerprint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("md5Fingerprint");
        }
        return mD5Fingerprint;
    }

    @NotNull
    public final MediaStoreUtil getMediaStoreUtil$AndroidPhotosDiscovery_release() {
        MediaStoreUtil mediaStoreUtil = this.mediaStoreUtil;
        if (mediaStoreUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaStoreUtil");
        }
        return mediaStoreUtil;
    }

    @NotNull
    public final Metrics getMetrics$AndroidPhotosDiscovery_release() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @NotNull
    public final SystemUtil getSystemUtil$AndroidPhotosDiscovery_release() {
        SystemUtil systemUtil = this.systemUtil;
        if (systemUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("systemUtil");
        }
        return systemUtil;
    }

    @Override // com.amazon.photos.discovery.internal.Injectable
    public void inject(@NotNull DiscoveryComponent component) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        component.inject(this);
    }

    public final void setConfiguration$AndroidPhotosDiscovery_release(@NotNull DiscoveryConfiguration discoveryConfiguration) {
        Intrinsics.checkParameterIsNotNull(discoveryConfiguration, "<set-?>");
        this.configuration = discoveryConfiguration;
    }

    public final void setContentResolver$AndroidPhotosDiscovery_release(@NotNull ContentResolver contentResolver) {
        Intrinsics.checkParameterIsNotNull(contentResolver, "<set-?>");
        this.contentResolver = contentResolver;
    }

    public final void setFileUtils$AndroidPhotosDiscovery_release(@NotNull FileUtils fileUtils) {
        Intrinsics.checkParameterIsNotNull(fileUtils, "<set-?>");
        this.fileUtils = fileUtils;
    }

    public final void setLogger$AndroidPhotosDiscovery_release(@NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "<set-?>");
        this.logger = logger;
    }

    public final void setMd5Fingerprint$AndroidPhotosDiscovery_release(@NotNull MD5Fingerprint mD5Fingerprint) {
        Intrinsics.checkParameterIsNotNull(mD5Fingerprint, "<set-?>");
        this.md5Fingerprint = mD5Fingerprint;
    }

    public final void setMediaStoreUtil$AndroidPhotosDiscovery_release(@NotNull MediaStoreUtil mediaStoreUtil) {
        Intrinsics.checkParameterIsNotNull(mediaStoreUtil, "<set-?>");
        this.mediaStoreUtil = mediaStoreUtil;
    }

    public final void setMetrics$AndroidPhotosDiscovery_release(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }

    public final void setSystemUtil$AndroidPhotosDiscovery_release(@NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "<set-?>");
        this.systemUtil = systemUtil;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DigestCalculatorStage() {
        /*
            r10 = this;
            java.util.concurrent.ThreadPoolExecutor r7 = new java.util.concurrent.ThreadPoolExecutor
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
            int r0 = r0.availableProcessors()
            r1 = 2
            int r2 = kotlin.ranges.RangesKt.coerceAtMost(r1, r0)
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
            int r0 = r0.availableProcessors()
            int r3 = kotlin.ranges.RangesKt.coerceAtMost(r1, r0)
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MINUTES
            java.util.concurrent.LinkedBlockingDeque r6 = new java.util.concurrent.LinkedBlockingDeque
            r6.<init>()
            r8 = 10
            r0 = r7
            r1 = r2
            r2 = r3
            r3 = r8
            r0.<init>(r1, r2, r3, r5, r6)
            r10.<init>(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.dedupe.stages.DigestCalculatorStage.<init>():void");
    }
}
