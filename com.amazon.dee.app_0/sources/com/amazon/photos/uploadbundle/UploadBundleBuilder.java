package com.amazon.photos.uploadbundle;

import android.content.Context;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.autosave.AutosaveManager;
import com.amazon.photos.autosave.AutosaveManagerBuilder;
import com.amazon.photos.autosave.DefaultAutosavePreferences;
import com.amazon.photos.autosave.UploadPriorityResolver;
import com.amazon.photos.autosave.internal.utils.AndroidLogger;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.dedupe.DefaultDedupeStages;
import com.amazon.photos.uploadbundle.internal.FrameworkContext;
import com.amazon.photos.uploadbundle.internal.FrameworkContextKt;
import com.amazon.photos.uploadbundle.internal.UploadBundleImpl;
import com.amazon.photos.uploader.AbandonedRequestHandler;
import com.amazon.photos.uploader.Feature;
import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.UploadManager;
import com.amazon.photos.uploader.UploadManagerBuilder;
import com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.QueueBlockerEvaluator;
import com.amazon.photos.uploader.blockers.RequestBlockerEvaluator;
import com.amazon.photos.uploader.cds.CdsUploaderBuilder;
import com.amazon.photos.uploader.cds.UrlConnectionProvider;
import com.amazon.photos.uploader.cds.quota.CdsQuotaBlockerEvaluator;
import com.amazon.photos.uploader.customblockers.BlockerReevaluator;
import com.amazon.photos.uploader.log.UploadLogger;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadBundleBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u000e\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u00020.J\u000e\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u001cJ\u000e\u00106\u001a\u00020\u00002\u0006\u00107\u001a\u00020*J\u000e\u00108\u001a\u00020\u00002\u0006\u00105\u001a\u00020(J\u000e\u00109\u001a\u00020\u00002\u0006\u00105\u001a\u00020,J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010:\u001a\u00020;J\u001d\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0000¢\u0006\u0002\bBJ\r\u0010C\u001a\u00020AH\u0000¢\u0006\u0002\bDJ\r\u0010E\u001a\u00020?H\u0000¢\u0006\u0002\bFJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010G\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020 J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010H\u001a\u00020$J\u000e\u0010/\u001a\u00020\u00002\u0006\u0010/\u001a\u000200J\u000e\u00101\u001a\u00020\u00002\u0006\u0010I\u001a\u00020$J\u0014\u0010J\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\u000e\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0017J\u000e\u0010M\u001a\u00020\u00002\u0006\u0010N\u001a\u00020&R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0004\n\u0002\u0010!R\u0012\u0010\"\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0004\n\u0002\u0010!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lcom/amazon/photos/uploadbundle/UploadBundleBuilder;", "", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "directedId", "", "applicationContext", "Landroid/content/Context;", "applicationId", "applicationName", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/clouddrive/android/core/interfaces/Logger;Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V", "abandonedRequestHandler", "Lcom/amazon/photos/uploader/AbandonedRequestHandler;", "blockerReevaluator", "Lcom/amazon/photos/uploader/customblockers/BlockerReevaluator;", "dedupeStages", "", "Lcom/amazon/photos/discovery/dedupe/DedupeStage;", "defaultPreferences", "Lcom/amazon/photos/autosave/DefaultAutosavePreferences;", "frameworkContext", "Lcom/amazon/photos/uploadbundle/internal/FrameworkContext;", "globalBlockerEvaluators", "", "Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "loggingSecurityLevel", "Lcom/amazon/photos/uploader/log/UploadLogger$SecurityLevel;", "maxAttempts", "", "Ljava/lang/Integer;", "maxParallelUploads", "onlyScanCameraFolders", "", "priorityResolver", "Lcom/amazon/photos/autosave/UploadPriorityResolver;", "queueBlockerEvaluators", "Lcom/amazon/photos/uploader/blockers/QueueBlockerEvaluator;", "queues", "Lcom/amazon/photos/uploader/Queue;", "requestBlockerEvaluators", "Lcom/amazon/photos/uploader/blockers/RequestBlockerEvaluator;", "supportedFeatures", "Lcom/amazon/photos/uploader/Feature;", "urlConnectionProvider", "Lcom/amazon/photos/uploader/cds/UrlConnectionProvider;", "useDiscoveryDailyMonitor", "addFeature", "feature", "addGlobalBlockerEvaluator", "evaluator", "addQueue", "queue", "addQueueBlockerEvaluator", "addRequestBlockerEvaluator", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/photos/uploadbundle/UploadBundle;", "getAutosaveManager", "Lcom/amazon/photos/autosave/AutosaveManager;", "uploadManager", "Lcom/amazon/photos/uploader/UploadManager;", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "getAutosaveManager$AndroidPhotosUploadBundle_release", "getDiscovery", "getDiscovery$AndroidPhotosUploadBundle_release", "getUploadManager", "getUploadManager$AndroidPhotosUploadBundle_release", "securityLevel", "onlyScanCamera", "useMonitor", "withDedupeStages", "withDefaultAutosavePreferences", "defaultAutosavePreferences", "withPriorityResolver", "resolver", "AndroidPhotosUploadBundle_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadBundleBuilder {
    private AbandonedRequestHandler abandonedRequestHandler;
    private final Context applicationContext;
    private final String applicationId;
    private final String applicationName;
    private BlockerReevaluator blockerReevaluator;
    private final CDClient cdClient;
    private List<DedupeStage> dedupeStages;
    private DefaultAutosavePreferences defaultPreferences;
    private final String directedId;
    private FrameworkContext frameworkContext;
    private final Set<GlobalBlockerEvaluator> globalBlockerEvaluators;
    private final Logger logger;
    private UploadLogger.SecurityLevel loggingSecurityLevel;
    private Integer maxAttempts;
    private Integer maxParallelUploads;
    private final Metrics metrics;
    private boolean onlyScanCameraFolders;
    private UploadPriorityResolver priorityResolver;
    private final Set<QueueBlockerEvaluator> queueBlockerEvaluators;
    private final Set<Queue> queues;
    private final Set<RequestBlockerEvaluator> requestBlockerEvaluators;
    private final Set<Feature> supportedFeatures;
    private UrlConnectionProvider urlConnectionProvider;
    private boolean useDiscoveryDailyMonitor;

    public UploadBundleBuilder(@NotNull Metrics metrics, @NotNull CDClient cdClient, @NotNull Logger logger, @NotNull String directedId, @NotNull Context applicationContext, @NotNull String applicationId, @NotNull String applicationName) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(directedId, "directedId");
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(applicationId, "applicationId");
        Intrinsics.checkParameterIsNotNull(applicationName, "applicationName");
        this.metrics = metrics;
        this.cdClient = cdClient;
        this.logger = logger;
        this.directedId = directedId;
        this.applicationContext = applicationContext;
        this.applicationId = applicationId;
        this.applicationName = applicationName;
        this.queues = new LinkedHashSet();
        this.globalBlockerEvaluators = new LinkedHashSet();
        this.queueBlockerEvaluators = new LinkedHashSet();
        this.requestBlockerEvaluators = new LinkedHashSet();
        this.supportedFeatures = new LinkedHashSet();
        this.useDiscoveryDailyMonitor = true;
        this.loggingSecurityLevel = UploadLogger.SecurityLevel.OBFUSCATED;
    }

    @NotNull
    public final UploadBundleBuilder abandonedRequestHandler(@Nullable AbandonedRequestHandler abandonedRequestHandler) {
        this.abandonedRequestHandler = abandonedRequestHandler;
        return this;
    }

    @NotNull
    public final UploadBundleBuilder addFeature(@NotNull Feature feature) {
        Intrinsics.checkParameterIsNotNull(feature, "feature");
        this.supportedFeatures.add(feature);
        return this;
    }

    @NotNull
    public final UploadBundleBuilder addGlobalBlockerEvaluator(@NotNull GlobalBlockerEvaluator evaluator) {
        Intrinsics.checkParameterIsNotNull(evaluator, "evaluator");
        this.globalBlockerEvaluators.add(evaluator);
        return this;
    }

    @NotNull
    public final UploadBundleBuilder addQueue(@NotNull Queue queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        this.queues.add(queue);
        return this;
    }

    @NotNull
    public final UploadBundleBuilder addQueueBlockerEvaluator(@NotNull QueueBlockerEvaluator evaluator) {
        Intrinsics.checkParameterIsNotNull(evaluator, "evaluator");
        this.queueBlockerEvaluators.add(evaluator);
        return this;
    }

    @NotNull
    public final UploadBundleBuilder addRequestBlockerEvaluator(@NotNull RequestBlockerEvaluator evaluator) {
        Intrinsics.checkParameterIsNotNull(evaluator, "evaluator");
        this.requestBlockerEvaluators.add(evaluator);
        return this;
    }

    @NotNull
    public final UploadBundleBuilder blockerReevaluator(@NotNull BlockerReevaluator blockerReevaluator) {
        Intrinsics.checkParameterIsNotNull(blockerReevaluator, "blockerReevaluator");
        this.blockerReevaluator = blockerReevaluator;
        return this;
    }

    @NotNull
    public final UploadBundle build() {
        this.frameworkContext = new FrameworkContext(this.directedId, this.applicationContext, this.applicationId, this.applicationName);
        Discovery discovery$AndroidPhotosUploadBundle_release = getDiscovery$AndroidPhotosUploadBundle_release();
        UploadManager uploadManager$AndroidPhotosUploadBundle_release = getUploadManager$AndroidPhotosUploadBundle_release();
        return new UploadBundleImpl(uploadManager$AndroidPhotosUploadBundle_release, discovery$AndroidPhotosUploadBundle_release, getAutosaveManager$AndroidPhotosUploadBundle_release(uploadManager$AndroidPhotosUploadBundle_release, discovery$AndroidPhotosUploadBundle_release));
    }

    @NotNull
    public final AutosaveManager getAutosaveManager$AndroidPhotosUploadBundle_release(@NotNull UploadManager uploadManager, @NotNull Discovery discovery) {
        Intrinsics.checkParameterIsNotNull(uploadManager, "uploadManager");
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        FrameworkContext frameworkContext = this.frameworkContext;
        if (frameworkContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frameworkContext");
        }
        AutosaveManagerBuilder withLogger = new AutosaveManagerBuilder(FrameworkContextKt.toAutosaveFrameworkContext(frameworkContext), this.metrics, this.cdClient, discovery, uploadManager).withLogger(this.logger);
        DefaultAutosavePreferences defaultAutosavePreferences = this.defaultPreferences;
        if (defaultAutosavePreferences != null) {
            withLogger.withDefaultAutosavePreferences(defaultAutosavePreferences);
        }
        UploadPriorityResolver uploadPriorityResolver = this.priorityResolver;
        if (uploadPriorityResolver != null) {
            withLogger.withPriorityResolver(uploadPriorityResolver);
        }
        return withLogger.build();
    }

    @NotNull
    public final Discovery getDiscovery$AndroidPhotosUploadBundle_release() {
        Discovery.Companion companion = Discovery.Companion;
        FrameworkContext frameworkContext = this.frameworkContext;
        if (frameworkContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frameworkContext");
        }
        Context applicationContext = frameworkContext.getApplicationContext();
        FrameworkContext frameworkContext2 = this.frameworkContext;
        if (frameworkContext2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frameworkContext");
        }
        String directedId = frameworkContext2.getDirectedId();
        List<DedupeStage> list = this.dedupeStages;
        if (list == null) {
            list = DefaultDedupeStages.INSTANCE.create();
        }
        return companion.createInstance(new DiscoveryConfiguration(applicationContext, directedId, list, this.metrics, this.cdClient, this.logger, null, this.useDiscoveryDailyMonitor, this.onlyScanCameraFolders, this.supportedFeatures.contains(Feature.VISUAL_DIGEST)));
    }

    @NotNull
    public final UploadManager getUploadManager$AndroidPhotosUploadBundle_release() {
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        int collectionSizeOrDefault3;
        int collectionSizeOrDefault4;
        int collectionSizeOrDefault5;
        FrameworkContext frameworkContext = this.frameworkContext;
        if (frameworkContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frameworkContext");
        }
        UploadFrameworkContext uploadFrameworkContext = FrameworkContextKt.toUploadFrameworkContext(frameworkContext);
        UploadLogger uploadLogger = new UploadLogger(this.logger, this.loggingSecurityLevel);
        Metrics metrics = this.metrics;
        CdsUploaderBuilder cdsUploaderBuilder = new CdsUploaderBuilder(uploadFrameworkContext, this.cdClient, this.logger, metrics);
        UrlConnectionProvider urlConnectionProvider = this.urlConnectionProvider;
        if (urlConnectionProvider != null) {
            cdsUploaderBuilder.urlConnectionProvider(urlConnectionProvider);
        }
        cdsUploaderBuilder.uploadLogger(uploadLogger);
        UploadManagerBuilder abandonedRequestHandler = new UploadManagerBuilder(uploadFrameworkContext, metrics, cdsUploaderBuilder.build()).abandonedRequestHandler(this.abandonedRequestHandler);
        Integer num = this.maxAttempts;
        if (num != null) {
            abandonedRequestHandler.maxAttempts(num.intValue());
        }
        Integer num2 = this.maxParallelUploads;
        if (num2 != null) {
            abandonedRequestHandler.maxParallelUploads(num2.intValue());
        }
        Set<Queue> set = this.queues;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(set, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Queue queue : set) {
            arrayList.add(abandonedRequestHandler.addQueue(queue));
        }
        Set<GlobalBlockerEvaluator> set2 = this.globalBlockerEvaluators;
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
        for (GlobalBlockerEvaluator globalBlockerEvaluator : set2) {
            arrayList2.add(abandonedRequestHandler.addGlobalBlockerEvaluator(globalBlockerEvaluator));
        }
        Set<QueueBlockerEvaluator> set3 = this.queueBlockerEvaluators;
        collectionSizeOrDefault3 = CollectionsKt__IterablesKt.collectionSizeOrDefault(set3, 10);
        ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault3);
        for (QueueBlockerEvaluator queueBlockerEvaluator : set3) {
            arrayList3.add(abandonedRequestHandler.addQueueBlockerEvaluator(queueBlockerEvaluator));
        }
        Set<RequestBlockerEvaluator> set4 = this.requestBlockerEvaluators;
        collectionSizeOrDefault4 = CollectionsKt__IterablesKt.collectionSizeOrDefault(set4, 10);
        ArrayList arrayList4 = new ArrayList(collectionSizeOrDefault4);
        for (RequestBlockerEvaluator requestBlockerEvaluator : set4) {
            arrayList4.add(abandonedRequestHandler.addRequestBlockerEvaluator(requestBlockerEvaluator));
        }
        Set<Feature> set5 = this.supportedFeatures;
        collectionSizeOrDefault5 = CollectionsKt__IterablesKt.collectionSizeOrDefault(set5, 10);
        ArrayList arrayList5 = new ArrayList(collectionSizeOrDefault5);
        for (Feature feature : set5) {
            arrayList5.add(abandonedRequestHandler.addFeature(feature));
        }
        return abandonedRequestHandler.addRequestBlockerEvaluator(new CdsQuotaBlockerEvaluator(uploadFrameworkContext, this.metrics, uploadLogger, this.cdClient, this.blockerReevaluator)).uploadLogger(uploadLogger).build();
    }

    @NotNull
    public final UploadBundleBuilder loggingSecurityLevel(@NotNull UploadLogger.SecurityLevel securityLevel) {
        Intrinsics.checkParameterIsNotNull(securityLevel, "securityLevel");
        this.loggingSecurityLevel = securityLevel;
        return this;
    }

    @NotNull
    public final UploadBundleBuilder maxAttempts(int i) {
        if (i != 0) {
            this.maxAttempts = Integer.valueOf(i);
            return this;
        }
        throw new IllegalArgumentException("Max attempts must be at least 1.");
    }

    @NotNull
    public final UploadBundleBuilder maxParallelUploads(int i) {
        this.maxParallelUploads = Integer.valueOf(i);
        return this;
    }

    @NotNull
    public final UploadBundleBuilder onlyScanCameraFolders(boolean z) {
        this.onlyScanCameraFolders = z;
        return this;
    }

    @NotNull
    public final UploadBundleBuilder urlConnectionProvider(@NotNull UrlConnectionProvider urlConnectionProvider) {
        Intrinsics.checkParameterIsNotNull(urlConnectionProvider, "urlConnectionProvider");
        this.urlConnectionProvider = urlConnectionProvider;
        return this;
    }

    @NotNull
    public final UploadBundleBuilder useDiscoveryDailyMonitor(boolean z) {
        this.useDiscoveryDailyMonitor = z;
        return this;
    }

    @NotNull
    public final UploadBundleBuilder withDedupeStages(@NotNull List<DedupeStage> dedupeStages) {
        Intrinsics.checkParameterIsNotNull(dedupeStages, "dedupeStages");
        this.dedupeStages = dedupeStages;
        return this;
    }

    @NotNull
    public final UploadBundleBuilder withDefaultAutosavePreferences(@NotNull DefaultAutosavePreferences defaultAutosavePreferences) {
        Intrinsics.checkParameterIsNotNull(defaultAutosavePreferences, "defaultAutosavePreferences");
        this.defaultPreferences = defaultAutosavePreferences;
        return this;
    }

    @NotNull
    public final UploadBundleBuilder withPriorityResolver(@NotNull UploadPriorityResolver resolver) {
        Intrinsics.checkParameterIsNotNull(resolver, "resolver");
        this.priorityResolver = resolver;
        return this;
    }

    public /* synthetic */ UploadBundleBuilder(Metrics metrics, CDClient cDClient, Logger logger, String str, Context context, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(metrics, cDClient, (i & 4) != 0 ? new AndroidLogger() : logger, str, context, str2, str3);
    }
}
