package com.amazon.photos.uploader.internal.dagger.module;

import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.util.MD5Fingerprint;
import com.amazon.imageutilities.JpegVisualDigest;
import com.amazon.photos.uploader.AbandonedRequestHandler;
import com.amazon.photos.uploader.Feature;
import com.amazon.photos.uploader.FileSizeCategoryProvider;
import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.Uploader;
import com.amazon.photos.uploader.blockers.BackoffBlockerEvaluator;
import com.amazon.photos.uploader.blockers.BlockerEvaluatorProvider;
import com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.MeteredConnectionQueueBlockerEvaluator;
import com.amazon.photos.uploader.blockers.NetworkGlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.NetworkMonitor;
import com.amazon.photos.uploader.blockers.PauseGlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.PauseResumeState;
import com.amazon.photos.uploader.blockers.QueueBlockerEvaluator;
import com.amazon.photos.uploader.blockers.RequestBlockerEvaluator;
import com.amazon.photos.uploader.blockers.unauthorizedaccess.UnauthorizedAccessBlockerEvaluator;
import com.amazon.photos.uploader.blockers.unauthorizedaccess.UnauthorizedAccessPersistence;
import com.amazon.photos.uploader.dao.EventDao;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.NotificationUpdatesNotifier;
import com.amazon.photos.uploader.internal.OpenForTesting;
import com.amazon.photos.uploader.internal.QueueManagerImpl;
import com.amazon.photos.uploader.internal.SchedulerConfiguration;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import com.amazon.photos.uploader.internal.UploadWorkerConfiguration;
import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import com.amazon.photos.uploader.internal.dagger.PerAccount;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.device.BatteryReceiverRegisterHelper;
import com.amazon.photos.uploader.internal.device.BatteryState;
import com.amazon.photos.uploader.internal.device.BatteryStateBlockerEvaluator;
import com.amazon.photos.uploader.internal.device.ChargingBlockerEvaluator;
import com.amazon.photos.uploader.internal.device.PermissionBlockerEvaluator;
import com.amazon.photos.uploader.internal.livedata.GlobalBlockerLiveDataProvider;
import com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider;
import com.amazon.photos.uploader.internal.livedata.RunningRequestProvider;
import com.amazon.photos.uploader.internal.utils.FileUtils;
import com.amazon.photos.uploader.internal.utils.PersistentLogger;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import com.amazon.photos.uploader.internal.workers.CoroutineWorkerUtil;
import com.amazon.photos.uploader.log.UploadLogger;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import com.amazon.photos.uploader.observables.UploadSummaryObservable;
import dagger.Module;
import dagger.Provides;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ConfigurationModule.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ä\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0001\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0007\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0007\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0007¢\u0006\u0002\u0010\u001cJ5\u0010#\u001a\u00020$2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-J\u0015\u0010.\u001a\u00020/2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\b0J\u001d\u00101\u001a\u0002022\u0006\u00103\u001a\u00020/2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b4J\u001d\u00105\u001a\u0002062\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b7J\u001d\u00108\u001a\u0002092\u0006\u00103\u001a\u00020/2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b:J\r\u0010;\u001a\u00020\u001eH\u0001¢\u0006\u0002\b<J\r\u0010=\u001a\u00020>H\u0001¢\u0006\u0002\b?J\r\u0010@\u001a\u00020AH\u0001¢\u0006\u0002\bBJ\r\u0010C\u001a\u00020DH\u0001¢\u0006\u0002\bEJ;\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020$2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020OH\u0001¢\u0006\u0002\bPJ\u0015\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020TH\u0001¢\u0006\u0002\bUJC\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u0002062\u0006\u0010Y\u001a\u00020Z2\u0011\u0010\u0010\u001a\r\u0012\t\u0012\u00070\u0011¢\u0006\u0002\b[0\u00072\u0011\u0010\u0012\u001a\r\u0012\t\u0012\u00070\u0013¢\u0006\u0002\b[0\u0007H\u0001¢\u0006\u0002\b\\J\r\u0010]\u001a\u00020\u0003H\u0001¢\u0006\u0002\b^J%\u0010_\u001a\u00020`2\u0006\u0010)\u001a\u00020*2\u0006\u0010%\u001a\u00020&2\u0006\u0010a\u001a\u00020bH\u0001¢\u0006\u0002\bcJ\r\u0010d\u001a\u00020\u0005H\u0001¢\u0006\u0002\beJ%\u0010f\u001a\u00020K2\u0006\u0010)\u001a\u00020*2\u0006\u0010%\u001a\u00020&2\u0006\u0010a\u001a\u00020bH\u0001¢\u0006\u0002\bgJ\r\u0010h\u001a\u00020iH\u0001¢\u0006\u0002\bjJ\u0015\u0010k\u001a\u00020l2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\bmJ\u0015\u0010n\u001a\u00020H2\u0006\u0010o\u001a\u00020lH\u0001¢\u0006\u0002\bpJ\u001d\u0010q\u001a\u00020M2\u0006\u0010)\u001a\u00020*2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\brJ\u0015\u0010s\u001a\u00020,2\u0006\u0010t\u001a\u00020uH\u0001¢\u0006\u0002\bvJ+\u0010w\u001a\b\u0012\u0004\u0012\u00020\u00130\u00072\u0006\u0010x\u001a\u00020`2\u0006\u0010y\u001a\u0002092\u0006\u0010z\u001a\u000202H\u0001¢\u0006\u0002\b{J\u0015\u0010|\u001a\u00020}2\u0006\u0010S\u001a\u00020TH\u0001¢\u0006\u0002\b~J\u001e\u0010\u007f\u001a\u00020Z2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0003\b\u0080\u0001J\u001a\u0010\u0081\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0001¢\u0006\u0003\b\u0085\u0001J\u000f\u0010\u0086\u0001\u001a\u00020 H\u0001¢\u0006\u0003\b\u0087\u0001J\u0015\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0007H\u0001¢\u0006\u0003\b\u0089\u0001J\u0017\u0010\u008a\u0001\u001a\u00020*2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0003\b\u008b\u0001J#\u0010\u008c\u0001\u001a\u00020O2\b\u0010\u008d\u0001\u001a\u00030\u008e\u00012\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0001¢\u0006\u0003\b\u008f\u0001J\u0018\u0010\u0090\u0001\u001a\u00030\u008e\u00012\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0003\b\u0091\u0001J\u000f\u0010\u0092\u0001\u001a\u00020\u0017H\u0001¢\u0006\u0003\b\u0093\u0001J\u0019\u0010\u0094\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u0095\u0001\u001a\u00020(H\u0001¢\u0006\u0003\b\u0096\u0001J\u0019\u0010\u0097\u0001\u001a\u00020(2\b\u0010\u0098\u0001\u001a\u00030\u0099\u0001H\u0001¢\u0006\u0003\b\u009a\u0001J\"\u0010\u009b\u0001\u001a\u00030\u009c\u00012\b\u0010\u009d\u0001\u001a\u00030\u009e\u00012\u0006\u0010a\u001a\u00020bH\u0001¢\u0006\u0003\b\u009f\u0001J\u0019\u0010 \u0001\u001a\u00020T2\b\u0010¡\u0001\u001a\u00030\u009c\u0001H\u0001¢\u0006\u0003\b¢\u0001J,\u0010£\u0001\u001a\u00030\u0099\u00012\b\u0010¤\u0001\u001a\u00030¥\u00012\b\u0010¦\u0001\u001a\u00030\u009c\u00012\u0006\u0010Y\u001a\u00020ZH\u0001¢\u0006\u0003\b§\u0001J\u000f\u0010¨\u0001\u001a\u00020\nH\u0001¢\u0006\u0003\b©\u0001J\u0011\u0010ª\u0001\u001a\u0004\u0018\u00010\u000fH\u0001¢\u0006\u0003\b«\u0001J \u0010¬\u0001\u001a\u00030\u009e\u00012\u0006\u00103\u001a\u00020/2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0003\b\u00ad\u0001J\u001f\u0010®\u0001\u001a\u00020b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0003\b¯\u0001J\u001f\u0010°\u0001\u001a\u00020&2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0003\b±\u0001J\u000f\u0010²\u0001\u001a\u00020\"H\u0001¢\u0006\u0003\b³\u0001J\u000f\u0010´\u0001\u001a\u00020\u0019H\u0001¢\u0006\u0003\bµ\u0001R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006¶\u0001"}, d2 = {"Lcom/amazon/photos/uploader/internal/dagger/module/ConfigurationModule;", "", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "queues", "", "Lcom/amazon/photos/uploader/Queue;", "workManager", "Landroidx/work/WorkManager;", "maxParallelUploads", "", "maxAttempts", "abandonedRequestHandler", "Lcom/amazon/photos/uploader/AbandonedRequestHandler;", "globalBlockerEvaluators", "Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "queueBlockerEvaluators", "Lcom/amazon/photos/uploader/blockers/QueueBlockerEvaluator;", "requestBlockerEvaluators", "Lcom/amazon/photos/uploader/blockers/RequestBlockerEvaluator;", "uploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "uploader", "Lcom/amazon/photos/uploader/Uploader;", "supportedFeatures", "Lcom/amazon/photos/uploader/Feature;", "(Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Ljava/util/Set;Landroidx/work/WorkManager;IILcom/amazon/photos/uploader/AbandonedRequestHandler;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Lcom/amazon/photos/uploader/UploadFrameworkContext;Lcom/amazon/photos/uploader/Uploader;Ljava/util/Set;)V", "contentSignatureProvider", "Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider;", "schedulerConfiguration", "Lcom/amazon/photos/uploader/internal/SchedulerConfiguration;", "uploadWorkerConfiguration", "Lcom/amazon/photos/uploader/internal/UploadWorkerConfiguration;", "provideBackoffBlockerEvaluator", "Lcom/amazon/photos/uploader/blockers/BackoffBlockerEvaluator;", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "uploadRequestNotifier", "Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "systemUtil", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "persistentLogger", "Lcom/amazon/photos/uploader/internal/utils/PersistentLogger;", "provideBackoffBlockerEvaluator$AndroidPhotosUploader_release", "provideBatteryState", "Lcom/amazon/photos/uploader/internal/device/BatteryState;", "provideBatteryState$AndroidPhotosUploader_release", "provideBatteryStateBlockerEvaluator", "Lcom/amazon/photos/uploader/internal/device/BatteryStateBlockerEvaluator;", "batteryState", "provideBatteryStateBlockerEvaluator$AndroidPhotosUploader_release", "provideBlockerEvaluatorProvider", "Lcom/amazon/photos/uploader/blockers/BlockerEvaluatorProvider;", "provideBlockerEvaluatorProvider$AndroidPhotosUploader_release", "provideChargingBlockerEvaluator", "Lcom/amazon/photos/uploader/internal/device/ChargingBlockerEvaluator;", "provideChargingBlockerEvaluator$AndroidPhotosUploader_release", "provideContentSignatureProvider", "provideContentSignatureProvider$AndroidPhotosUploader_release", "provideCoroutineWorkerUtil", "Lcom/amazon/photos/uploader/internal/workers/CoroutineWorkerUtil;", "provideCoroutineWorkerUtil$AndroidPhotosUploader_release", "provideFileSizeCategoryProvider", "Lcom/amazon/photos/uploader/FileSizeCategoryProvider;", "provideFileSizeCategoryProvider$AndroidPhotosUploader_release", "provideFileUtils", "Lcom/amazon/photos/uploader/internal/utils/FileUtils;", "provideFileUtils$AndroidPhotosUploader_release", "provideGlobalBlockerEvaluators", "pauseResumeGlobalBlockerEvaluator", "Lcom/amazon/photos/uploader/blockers/PauseGlobalBlockerEvaluator;", "backoffBlockerEvaluator", "networkGlobalBlockerEvaluator", "Lcom/amazon/photos/uploader/blockers/NetworkGlobalBlockerEvaluator;", "permissionBlockerEvaluator", "Lcom/amazon/photos/uploader/internal/device/PermissionBlockerEvaluator;", "unauthorizedAccessBlockerEvaluator", "Lcom/amazon/photos/uploader/blockers/unauthorizedaccess/UnauthorizedAccessBlockerEvaluator;", "provideGlobalBlockerEvaluators$AndroidPhotosUploader_release", "provideGlobalBlockerProvider", "Lcom/amazon/photos/uploader/internal/livedata/GlobalBlockerLiveDataProvider;", "uploadSummaryObservable", "Lcom/amazon/photos/uploader/observables/UploadSummaryObservable;", "provideGlobalBlockerProvider$AndroidPhotosUploader_release", "provideInternalEvaluator", "Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "blockerEvaluatorProvider", "queueManager", "Lcom/amazon/photos/uploader/QueueManager;", "Lkotlin/jvm/JvmSuppressWildcards;", "provideInternalEvaluator$AndroidPhotosUploader_release", "provideLogger", "provideLogger$AndroidPhotosUploader_release", "provideMeteredConnectionQueueBlockerEvaluator", "Lcom/amazon/photos/uploader/blockers/MeteredConnectionQueueBlockerEvaluator;", "networkMonitor", "Lcom/amazon/photos/uploader/blockers/NetworkMonitor;", "provideMeteredConnectionQueueBlockerEvaluator$AndroidPhotosUploader_release", "provideMetrics", "provideMetrics$AndroidPhotosUploader_release", "provideNetworkGlobalBlockerEvaluator", "provideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_release", "provideNotificationUpdatesNotifier", "Lcom/amazon/photos/uploader/internal/NotificationUpdatesNotifier;", "provideNotificationUpdatesNotifier$AndroidPhotosUploader_release", "providePauseResume", "Lcom/amazon/photos/uploader/blockers/PauseResumeState;", "providePauseResume$AndroidPhotosUploader_release", "providePauseResumeGlobalBlockerEvaluator", "pauseResumeState", "providePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_release", "providePermissionBlockerEvaluator", "providePermissionBlockerEvaluator$AndroidPhotosUploader_release", "providePersistentLogger", "eventDao", "Lcom/amazon/photos/uploader/dao/EventDao;", "providePersistentLogger$AndroidPhotosUploader_release", "provideQueueBlockerEvaluators", "connectionBlockerEvaluator", "chargingBlockerEvaluator", "batteryStateBlockerEvaluator", "provideQueueBlockerEvaluators$AndroidPhotosUploader_release", "provideQueueBlockerProvider", "Lcom/amazon/photos/uploader/internal/livedata/QueueBlockerLiveDataProvider;", "provideQueueBlockerProvider$AndroidPhotosUploader_release", "provideQueueManager", "provideQueueManager$AndroidPhotosUploader_release", "provideRunningRequestProvider", "Lcom/amazon/photos/uploader/internal/livedata/RunningRequestProvider;", "uploadRequestObservable", "Lcom/amazon/photos/uploader/observables/UploadRequestObservable;", "provideRunningRequestProvider$AndroidPhotosUploader_release", "provideSchedulerConfiguration", "provideSchedulerConfiguration$AndroidPhotosUploader_release", "provideSupportedFeatures", "provideSupportedFeatures$AndroidPhotosUploader_release", "provideSystemUtil", "provideSystemUtil$AndroidPhotosUploader_release", "provideUnauthorizedAccessBlockerEvaluator", "persistence", "Lcom/amazon/photos/uploader/blockers/unauthorizedaccess/UnauthorizedAccessPersistence;", "provideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_release", "provideUnauthorizedAccessPersistence", "provideUnauthorizedAccessPersistence$AndroidPhotosUploader_release", "provideUploadFrameworkContext", "provideUploadFrameworkContext$AndroidPhotosUploader_release", "provideUploadRequestObservable", "uploadRequestUpdatesNotifier", "provideUploadRequestObservable$AndroidPhotosUploader_release", "provideUploadRequestUpdatesNotifier", "uploadSummaryTracker", "Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;", "provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_release", "provideUploadSummaryNotifier", "Lcom/amazon/photos/uploader/internal/UploadSummaryNotifier;", "batteryReceiverRegisterHelper", "Lcom/amazon/photos/uploader/internal/device/BatteryReceiverRegisterHelper;", "provideUploadSummaryNotifier$AndroidPhotosUploader_release", "provideUploadSummaryObservable", "uploadSummaryNotifier", "provideUploadSummaryObservable$AndroidPhotosUploader_release", "provideUploadSummaryTracker", "requestDao", "Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "summaryNotifier", "provideUploadSummaryTracker$AndroidPhotosUploader_release", "provideWorkManager", "provideWorkManager$AndroidPhotosUploader_release", "providesAbandonedRequestHandler", "providesAbandonedRequestHandler$AndroidPhotosUploader_release", "providesBatteryBlockerMonitor", "providesBatteryBlockerMonitor$AndroidPhotosUploader_release", "providesNetworkBlockerMonitor", "providesNetworkBlockerMonitor$AndroidPhotosUploader_release", "providesSchedulingCallback", "providesSchedulingCallback$AndroidPhotosUploader_release", "providesUploadWorkerConfiguration", "providesUploadWorkerConfiguration$AndroidPhotosUploader_release", "providesUploader", "providesUploader$AndroidPhotosUploader_release", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes13.dex */
public final class ConfigurationModule {
    private final AbandonedRequestHandler abandonedRequestHandler;
    private final ContentSignatureProvider contentSignatureProvider;
    private final Set<GlobalBlockerEvaluator> globalBlockerEvaluators;
    private final UploadLogger logger;
    private final Metrics metrics;
    private final Set<QueueBlockerEvaluator> queueBlockerEvaluators;
    private final Set<Queue> queues;
    private final Set<RequestBlockerEvaluator> requestBlockerEvaluators;
    private final SchedulerConfiguration schedulerConfiguration;
    private final Set<Feature> supportedFeatures;
    private final UploadFrameworkContext uploadFrameworkContext;
    private final UploadWorkerConfiguration uploadWorkerConfiguration;
    private final Uploader uploader;
    private final WorkManager workManager;

    /* JADX WARN: Multi-variable type inference failed */
    public ConfigurationModule(@NotNull UploadLogger logger, @NotNull Metrics metrics, @NotNull Set<Queue> queues, @NotNull WorkManager workManager, int i, int i2, @Nullable AbandonedRequestHandler abandonedRequestHandler, @NotNull Set<? extends GlobalBlockerEvaluator> globalBlockerEvaluators, @NotNull Set<? extends QueueBlockerEvaluator> queueBlockerEvaluators, @NotNull Set<? extends RequestBlockerEvaluator> requestBlockerEvaluators, @NotNull UploadFrameworkContext uploadFrameworkContext, @NotNull Uploader uploader, @NotNull Set<? extends Feature> supportedFeatures) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        Intrinsics.checkParameterIsNotNull(workManager, "workManager");
        Intrinsics.checkParameterIsNotNull(globalBlockerEvaluators, "globalBlockerEvaluators");
        Intrinsics.checkParameterIsNotNull(queueBlockerEvaluators, "queueBlockerEvaluators");
        Intrinsics.checkParameterIsNotNull(requestBlockerEvaluators, "requestBlockerEvaluators");
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        Intrinsics.checkParameterIsNotNull(uploader, "uploader");
        Intrinsics.checkParameterIsNotNull(supportedFeatures, "supportedFeatures");
        this.logger = logger;
        this.metrics = metrics;
        this.queues = queues;
        this.workManager = workManager;
        this.abandonedRequestHandler = abandonedRequestHandler;
        this.globalBlockerEvaluators = globalBlockerEvaluators;
        this.queueBlockerEvaluators = queueBlockerEvaluators;
        this.requestBlockerEvaluators = requestBlockerEvaluators;
        this.uploadFrameworkContext = uploadFrameworkContext;
        this.uploader = uploader;
        this.supportedFeatures = supportedFeatures;
        this.schedulerConfiguration = new SchedulerConfiguration(i);
        this.uploadWorkerConfiguration = new UploadWorkerConfiguration(i2);
        this.contentSignatureProvider = new ContentSignatureProvider(new JpegVisualDigest(MessageDigestAlgorithms.MD5), new MD5Fingerprint(), this.metrics, this.logger);
    }

    @Provides
    @PerAccount
    @NotNull
    public final BackoffBlockerEvaluator provideBackoffBlockerEvaluator$AndroidPhotosUploader_release(@NotNull Metrics metrics, @NotNull SchedulingCallback schedulingCallback, @NotNull UploadRequestUpdatesNotifier uploadRequestNotifier, @NotNull SystemUtil systemUtil, @NotNull PersistentLogger persistentLogger) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        Intrinsics.checkParameterIsNotNull(uploadRequestNotifier, "uploadRequestNotifier");
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(persistentLogger, "persistentLogger");
        return new BackoffBlockerEvaluator(persistentLogger, metrics, schedulingCallback, uploadRequestNotifier, systemUtil);
    }

    @Provides
    @PerAccount
    @NotNull
    public final BatteryState provideBatteryState$AndroidPhotosUploader_release(@NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        return new BatteryState(this.uploadFrameworkContext.getApplicationContext(), systemUtil);
    }

    @Provides
    @PerAccount
    @NotNull
    public final BatteryStateBlockerEvaluator provideBatteryStateBlockerEvaluator$AndroidPhotosUploader_release(@NotNull BatteryState batteryState, @NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(batteryState, "batteryState");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        return new BatteryStateBlockerEvaluator(batteryState, schedulingCallback);
    }

    @Provides
    @PerAccount
    @NotNull
    public final BlockerEvaluatorProvider provideBlockerEvaluatorProvider$AndroidPhotosUploader_release(@NotNull SchedulingCallback schedulingCallback, @NotNull UploadRequestUpdatesNotifier uploadRequestNotifier) {
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        Intrinsics.checkParameterIsNotNull(uploadRequestNotifier, "uploadRequestNotifier");
        return new BlockerEvaluatorProvider(this.globalBlockerEvaluators, this.queueBlockerEvaluators, this.requestBlockerEvaluators, schedulingCallback, uploadRequestNotifier);
    }

    @Provides
    @PerAccount
    @NotNull
    public final ChargingBlockerEvaluator provideChargingBlockerEvaluator$AndroidPhotosUploader_release(@NotNull BatteryState batteryState, @NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(batteryState, "batteryState");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        return new ChargingBlockerEvaluator(batteryState, schedulingCallback);
    }

    @Provides
    @PerAccount
    @NotNull
    public final ContentSignatureProvider provideContentSignatureProvider$AndroidPhotosUploader_release() {
        return this.contentSignatureProvider;
    }

    @Provides
    @PerAccount
    @NotNull
    public final CoroutineWorkerUtil provideCoroutineWorkerUtil$AndroidPhotosUploader_release() {
        return new CoroutineWorkerUtil();
    }

    @Provides
    @PerAccount
    @NotNull
    public final FileSizeCategoryProvider provideFileSizeCategoryProvider$AndroidPhotosUploader_release() {
        return new FileSizeCategoryProvider();
    }

    @Provides
    @PerAccount
    @NotNull
    public final FileUtils provideFileUtils$AndroidPhotosUploader_release() {
        return new FileUtils();
    }

    @Provides
    @PerAccount
    @NotNull
    public final Set<GlobalBlockerEvaluator> provideGlobalBlockerEvaluators$AndroidPhotosUploader_release(@NotNull PauseGlobalBlockerEvaluator pauseResumeGlobalBlockerEvaluator, @NotNull BackoffBlockerEvaluator backoffBlockerEvaluator, @NotNull NetworkGlobalBlockerEvaluator networkGlobalBlockerEvaluator, @NotNull PermissionBlockerEvaluator permissionBlockerEvaluator, @NotNull UnauthorizedAccessBlockerEvaluator unauthorizedAccessBlockerEvaluator) {
        Set<GlobalBlockerEvaluator> of;
        Intrinsics.checkParameterIsNotNull(pauseResumeGlobalBlockerEvaluator, "pauseResumeGlobalBlockerEvaluator");
        Intrinsics.checkParameterIsNotNull(backoffBlockerEvaluator, "backoffBlockerEvaluator");
        Intrinsics.checkParameterIsNotNull(networkGlobalBlockerEvaluator, "networkGlobalBlockerEvaluator");
        Intrinsics.checkParameterIsNotNull(permissionBlockerEvaluator, "permissionBlockerEvaluator");
        Intrinsics.checkParameterIsNotNull(unauthorizedAccessBlockerEvaluator, "unauthorizedAccessBlockerEvaluator");
        of = SetsKt__SetsKt.setOf((Object[]) new GlobalBlockerEvaluator[]{pauseResumeGlobalBlockerEvaluator, backoffBlockerEvaluator, networkGlobalBlockerEvaluator, permissionBlockerEvaluator, unauthorizedAccessBlockerEvaluator});
        return of;
    }

    @Provides
    @PerAccount
    @NotNull
    public final GlobalBlockerLiveDataProvider provideGlobalBlockerProvider$AndroidPhotosUploader_release(@NotNull UploadSummaryObservable uploadSummaryObservable) {
        Intrinsics.checkParameterIsNotNull(uploadSummaryObservable, "uploadSummaryObservable");
        return new GlobalBlockerLiveDataProvider(uploadSummaryObservable);
    }

    @Provides
    @PerAccount
    @NotNull
    public final InternalEvaluator provideInternalEvaluator$AndroidPhotosUploader_release(@NotNull BlockerEvaluatorProvider blockerEvaluatorProvider, @NotNull QueueManager queueManager, @NotNull Set<GlobalBlockerEvaluator> globalBlockerEvaluators, @NotNull Set<QueueBlockerEvaluator> queueBlockerEvaluators) {
        Intrinsics.checkParameterIsNotNull(blockerEvaluatorProvider, "blockerEvaluatorProvider");
        Intrinsics.checkParameterIsNotNull(queueManager, "queueManager");
        Intrinsics.checkParameterIsNotNull(globalBlockerEvaluators, "globalBlockerEvaluators");
        Intrinsics.checkParameterIsNotNull(queueBlockerEvaluators, "queueBlockerEvaluators");
        return new InternalEvaluator(queueManager, blockerEvaluatorProvider, globalBlockerEvaluators, queueBlockerEvaluators, this.requestBlockerEvaluators);
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadLogger provideLogger$AndroidPhotosUploader_release() {
        return this.logger;
    }

    @Provides
    @PerAccount
    @NotNull
    public final MeteredConnectionQueueBlockerEvaluator provideMeteredConnectionQueueBlockerEvaluator$AndroidPhotosUploader_release(@NotNull SystemUtil systemUtil, @NotNull SchedulingCallback schedulingCallback, @NotNull NetworkMonitor networkMonitor) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        Intrinsics.checkParameterIsNotNull(networkMonitor, "networkMonitor");
        MeteredConnectionQueueBlockerEvaluator meteredConnectionQueueBlockerEvaluator = new MeteredConnectionQueueBlockerEvaluator(systemUtil, schedulingCallback);
        networkMonitor.addListener(meteredConnectionQueueBlockerEvaluator);
        return meteredConnectionQueueBlockerEvaluator;
    }

    @Provides
    @PerAccount
    @NotNull
    public final Metrics provideMetrics$AndroidPhotosUploader_release() {
        return this.metrics;
    }

    @Provides
    @PerAccount
    @NotNull
    public final NetworkGlobalBlockerEvaluator provideNetworkGlobalBlockerEvaluator$AndroidPhotosUploader_release(@NotNull SystemUtil systemUtil, @NotNull SchedulingCallback schedulingCallback, @NotNull NetworkMonitor networkMonitor) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        Intrinsics.checkParameterIsNotNull(networkMonitor, "networkMonitor");
        NetworkGlobalBlockerEvaluator networkGlobalBlockerEvaluator = new NetworkGlobalBlockerEvaluator(systemUtil, schedulingCallback);
        networkMonitor.addListener(networkGlobalBlockerEvaluator);
        return networkGlobalBlockerEvaluator;
    }

    @Provides
    @PerAccount
    @NotNull
    public final NotificationUpdatesNotifier provideNotificationUpdatesNotifier$AndroidPhotosUploader_release() {
        return new NotificationUpdatesNotifier(this.workManager, this.metrics);
    }

    @Provides
    @PerAccount
    @NotNull
    public final PauseResumeState providePauseResume$AndroidPhotosUploader_release(@NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        return new PauseResumeState(this.uploadFrameworkContext, schedulingCallback);
    }

    @Provides
    @PerAccount
    @NotNull
    public final PauseGlobalBlockerEvaluator providePauseResumeGlobalBlockerEvaluator$AndroidPhotosUploader_release(@NotNull PauseResumeState pauseResumeState) {
        Intrinsics.checkParameterIsNotNull(pauseResumeState, "pauseResumeState");
        return new PauseGlobalBlockerEvaluator(pauseResumeState);
    }

    @Provides
    @PerAccount
    @NotNull
    public final PermissionBlockerEvaluator providePermissionBlockerEvaluator$AndroidPhotosUploader_release(@NotNull SystemUtil systemUtil, @NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        return new PermissionBlockerEvaluator(systemUtil);
    }

    @Provides
    @PerAccount
    @NotNull
    public final PersistentLogger providePersistentLogger$AndroidPhotosUploader_release(@NotNull EventDao eventDao) {
        Intrinsics.checkParameterIsNotNull(eventDao, "eventDao");
        return new PersistentLogger(this.logger, eventDao);
    }

    @Provides
    @PerAccount
    @NotNull
    public final Set<QueueBlockerEvaluator> provideQueueBlockerEvaluators$AndroidPhotosUploader_release(@NotNull MeteredConnectionQueueBlockerEvaluator connectionBlockerEvaluator, @NotNull ChargingBlockerEvaluator chargingBlockerEvaluator, @NotNull BatteryStateBlockerEvaluator batteryStateBlockerEvaluator) {
        Set<QueueBlockerEvaluator> of;
        Intrinsics.checkParameterIsNotNull(connectionBlockerEvaluator, "connectionBlockerEvaluator");
        Intrinsics.checkParameterIsNotNull(chargingBlockerEvaluator, "chargingBlockerEvaluator");
        Intrinsics.checkParameterIsNotNull(batteryStateBlockerEvaluator, "batteryStateBlockerEvaluator");
        of = SetsKt__SetsKt.setOf((Object[]) new QueueBlockerEvaluator[]{connectionBlockerEvaluator, chargingBlockerEvaluator, batteryStateBlockerEvaluator});
        return of;
    }

    @Provides
    @PerAccount
    @NotNull
    public final QueueBlockerLiveDataProvider provideQueueBlockerProvider$AndroidPhotosUploader_release(@NotNull UploadSummaryObservable uploadSummaryObservable) {
        Intrinsics.checkParameterIsNotNull(uploadSummaryObservable, "uploadSummaryObservable");
        return new QueueBlockerLiveDataProvider(uploadSummaryObservable);
    }

    @Provides
    @PerAccount
    @NotNull
    public final QueueManager provideQueueManager$AndroidPhotosUploader_release(@NotNull SchedulingCallback schedulingCallback, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        return new QueueManagerImpl(schedulingCallback, this.logger, metrics, this.queues);
    }

    @Provides
    @PerAccount
    @NotNull
    public final RunningRequestProvider provideRunningRequestProvider$AndroidPhotosUploader_release(@NotNull UploadRequestObservable uploadRequestObservable) {
        Intrinsics.checkParameterIsNotNull(uploadRequestObservable, "uploadRequestObservable");
        return new RunningRequestProvider(uploadRequestObservable);
    }

    @Provides
    @PerAccount
    @NotNull
    public final SchedulerConfiguration provideSchedulerConfiguration$AndroidPhotosUploader_release() {
        return this.schedulerConfiguration;
    }

    @Provides
    @PerAccount
    @NotNull
    public final Set<Feature> provideSupportedFeatures$AndroidPhotosUploader_release() {
        return this.supportedFeatures;
    }

    @Provides
    @PerAccount
    @NotNull
    public final SystemUtil provideSystemUtil$AndroidPhotosUploader_release(@NotNull UploadFrameworkContext uploadFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        return new SystemUtil(uploadFrameworkContext.getApplicationContext(), this.supportedFeatures.contains(Feature.DEBUG_MODE));
    }

    @Provides
    @PerAccount
    @NotNull
    public final UnauthorizedAccessBlockerEvaluator provideUnauthorizedAccessBlockerEvaluator$AndroidPhotosUploader_release(@NotNull UnauthorizedAccessPersistence persistence, @NotNull UploadRequestObservable uploadRequestObservable) {
        Intrinsics.checkParameterIsNotNull(persistence, "persistence");
        Intrinsics.checkParameterIsNotNull(uploadRequestObservable, "uploadRequestObservable");
        UnauthorizedAccessBlockerEvaluator unauthorizedAccessBlockerEvaluator = new UnauthorizedAccessBlockerEvaluator(persistence, this.metrics);
        UploadRequestObservable.DefaultImpls.addObserver$default(uploadRequestObservable, unauthorizedAccessBlockerEvaluator, null, 2, null);
        return unauthorizedAccessBlockerEvaluator;
    }

    @Provides
    @PerAccount
    @NotNull
    public final UnauthorizedAccessPersistence provideUnauthorizedAccessPersistence$AndroidPhotosUploader_release(@NotNull UploadFrameworkContext uploadFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        return new UnauthorizedAccessPersistence(uploadFrameworkContext);
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadFrameworkContext provideUploadFrameworkContext$AndroidPhotosUploader_release() {
        return this.uploadFrameworkContext;
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadRequestObservable provideUploadRequestObservable$AndroidPhotosUploader_release(@NotNull UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier) {
        Intrinsics.checkParameterIsNotNull(uploadRequestUpdatesNotifier, "uploadRequestUpdatesNotifier");
        return uploadRequestUpdatesNotifier;
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadRequestUpdatesNotifier provideUploadRequestUpdatesNotifier$AndroidPhotosUploader_release(@NotNull UploadSummaryTracker uploadSummaryTracker) {
        Intrinsics.checkParameterIsNotNull(uploadSummaryTracker, "uploadSummaryTracker");
        return new UploadRequestUpdatesNotifier(uploadSummaryTracker);
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadSummaryNotifier provideUploadSummaryNotifier$AndroidPhotosUploader_release(@NotNull BatteryReceiverRegisterHelper batteryReceiverRegisterHelper, @NotNull NetworkMonitor networkMonitor) {
        Intrinsics.checkParameterIsNotNull(batteryReceiverRegisterHelper, "batteryReceiverRegisterHelper");
        Intrinsics.checkParameterIsNotNull(networkMonitor, "networkMonitor");
        UploadSummaryNotifier uploadSummaryNotifier = new UploadSummaryNotifier();
        UploadSummaryObservable.DefaultImpls.addObserver$default(uploadSummaryNotifier, batteryReceiverRegisterHelper, null, 2, null);
        UploadSummaryObservable.DefaultImpls.addObserver$default(uploadSummaryNotifier, networkMonitor, null, 2, null);
        return uploadSummaryNotifier;
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadSummaryObservable provideUploadSummaryObservable$AndroidPhotosUploader_release(@NotNull UploadSummaryNotifier uploadSummaryNotifier) {
        Intrinsics.checkParameterIsNotNull(uploadSummaryNotifier, "uploadSummaryNotifier");
        return uploadSummaryNotifier;
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadSummaryTracker provideUploadSummaryTracker$AndroidPhotosUploader_release(@NotNull UploadRequestDao requestDao, @NotNull UploadSummaryNotifier summaryNotifier, @NotNull QueueManager queueManager) {
        Intrinsics.checkParameterIsNotNull(requestDao, "requestDao");
        Intrinsics.checkParameterIsNotNull(summaryNotifier, "summaryNotifier");
        Intrinsics.checkParameterIsNotNull(queueManager, "queueManager");
        return new UploadSummaryTracker(requestDao, summaryNotifier, queueManager);
    }

    @Provides
    @PerAccount
    @NotNull
    public final WorkManager provideWorkManager$AndroidPhotosUploader_release() {
        return this.workManager;
    }

    @Provides
    @PerAccount
    @Nullable
    public final AbandonedRequestHandler providesAbandonedRequestHandler$AndroidPhotosUploader_release() {
        return this.abandonedRequestHandler;
    }

    @Provides
    @PerAccount
    @NotNull
    public final BatteryReceiverRegisterHelper providesBatteryBlockerMonitor$AndroidPhotosUploader_release(@NotNull BatteryState batteryState, @NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(batteryState, "batteryState");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        return new BatteryReceiverRegisterHelper(this.uploadFrameworkContext.getApplicationContext(), batteryState, schedulingCallback, this.logger);
    }

    @Provides
    @PerAccount
    @NotNull
    public final NetworkMonitor providesNetworkBlockerMonitor$AndroidPhotosUploader_release(@NotNull UploadFrameworkContext uploadFrameworkContext, @NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        return new NetworkMonitor(uploadFrameworkContext.getApplicationContext(), systemUtil, this.logger);
    }

    @Provides
    @PerAccount
    @NotNull
    public final SchedulingCallback providesSchedulingCallback$AndroidPhotosUploader_release(@NotNull UploadFrameworkContext uploadFrameworkContext, @NotNull WorkManager workManager) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        Intrinsics.checkParameterIsNotNull(workManager, "workManager");
        return new SchedulingCallback(uploadFrameworkContext, workManager, this.logger);
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadWorkerConfiguration providesUploadWorkerConfiguration$AndroidPhotosUploader_release() {
        return this.uploadWorkerConfiguration;
    }

    @Provides
    @PerAccount
    @NotNull
    public final Uploader providesUploader$AndroidPhotosUploader_release() {
        return this.uploader;
    }
}
