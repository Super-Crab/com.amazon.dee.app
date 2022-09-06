package com.amazon.photos.autosave.internal.dagger.module;

import android.content.SharedPreferences;
import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.autosave.AutosaveFrameworkContext;
import com.amazon.photos.autosave.DefaultAutosavePreferences;
import com.amazon.photos.autosave.UploadPriorityResolver;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.coroutines.DefaultDispatcherProvider;
import com.amazon.photos.autosave.internal.coroutines.DispatcherProvider;
import com.amazon.photos.autosave.internal.dagger.PerAccount;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.metrics.AutosaveLatencyRecorder;
import com.amazon.photos.autosave.internal.metrics.MetricsHelper;
import com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier;
import com.amazon.photos.autosave.internal.observers.AutosaveObserverCoordinator;
import com.amazon.photos.autosave.internal.preferences.AutosavePreferenceChangeListener;
import com.amazon.photos.autosave.internal.preferences.AutosavePreferencesImpl;
import com.amazon.photos.autosave.internal.preferences.InternalPreferences;
import com.amazon.photos.autosave.internal.preferences.PreferenceUploadQueueHelper;
import com.amazon.photos.autosave.internal.upload.UploadHelper;
import com.amazon.photos.autosave.internal.utils.DateUtils;
import com.amazon.photos.autosave.internal.utils.SystemUtil;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.uploader.UploadManager;
import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\r\u0010\u0017\u001a\u00020\u0018H\u0001¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\u001bJ%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0018H\u0001¢\u0006\u0002\b!Je\u0010\"\u001a\u00020#2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00182\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u001dH\u0001¢\u0006\u0002\b1J%\u00102\u001a\u00020%2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0001¢\u0006\u0002\b3JE\u00104\u001a\u00020+2\u0006\u0010(\u001a\u00020)2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020%2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\u0006\u0010.\u001a\u00020/2\u0006\u0010 \u001a\u00020\u0018H\u0001¢\u0006\u0002\b<J\u001d\u0010=\u001a\u0002062\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0001¢\u0006\u0002\b>J\r\u0010?\u001a\u00020\tH\u0001¢\u0006\u0002\b@J\r\u0010A\u001a\u00020BH\u0001¢\u0006\u0002\bCJ\r\u0010D\u001a\u00020\u000fH\u0001¢\u0006\u0002\bEJ\r\u0010F\u001a\u000209H\u0001¢\u0006\u0002\bGJ\r\u0010H\u001a\u00020\u0003H\u0001¢\u0006\u0002\bIJ\r\u0010J\u001a\u00020\u0005H\u0001¢\u0006\u0002\bKJ\u0015\u0010L\u001a\u00020M2\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0002\bNJ=\u0010O\u001a\u00020;2\u0006\u0010\f\u001a\u00020\r2\u0006\u00107\u001a\u00020%2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020)2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0001¢\u0006\u0002\bPJ\r\u0010Q\u001a\u00020\u001fH\u0001¢\u0006\u0002\bRJ-\u0010S\u001a\u00020T2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010U\u001a\u00020BH\u0001¢\u0006\u0002\bVJ\r\u0010W\u001a\u00020\rH\u0001¢\u0006\u0002\bXJ\r\u0010Y\u001a\u00020\u000bH\u0001¢\u0006\u0002\bZR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006["}, d2 = {"Lcom/amazon/photos/autosave/internal/dagger/module/AutosaveModule;", "", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "autosaveFrameworkContext", "Lcom/amazon/photos/autosave/AutosaveFrameworkContext;", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "workManager", "Landroidx/work/WorkManager;", "uploadManager", "Lcom/amazon/photos/uploader/UploadManager;", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "defaultPreferences", "Lcom/amazon/photos/autosave/DefaultAutosavePreferences;", "uploadPriorityResolver", "Lcom/amazon/photos/autosave/UploadPriorityResolver;", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/autosave/AutosaveFrameworkContext;Lcom/amazon/clouddrive/cdasdk/CDClient;Landroidx/work/WorkManager;Lcom/amazon/photos/uploader/UploadManager;Lcom/amazon/photos/discovery/Discovery;Lcom/amazon/photos/autosave/DefaultAutosavePreferences;Lcom/amazon/photos/autosave/UploadPriorityResolver;)V", "getDefaultPreferences$AndroidPhotosAutosave_release", "()Lcom/amazon/photos/autosave/DefaultAutosavePreferences;", "provideAutosaveEventNotifier", "Lcom/amazon/photos/autosave/internal/observers/AutosaveEventNotifier;", "provideAutosaveEventNotifier$AndroidPhotosAutosave_release", "provideAutosaveFrameworkContext", "provideAutosaveFrameworkContext$AndroidPhotosAutosave_release", "provideAutosaveLatencyRecorder", "Lcom/amazon/photos/autosave/internal/metrics/AutosaveLatencyRecorder;", "systemUtil", "Lcom/amazon/photos/autosave/internal/utils/SystemUtil;", "autosaveEventNotifier", "provideAutosaveLatencyRecorder$AndroidPhotosAutosave_release", "provideAutosaveObserverCoordinator", "Lcom/amazon/photos/autosave/internal/observers/AutosaveObserverCoordinator;", "operations", "Lcom/amazon/photos/autosave/internal/AutosaveOperations;", "autosaveItemDao", "Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao;", "autosaveBucketDao", "Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;", "autosavePreferences", "Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;", "transactionRunner", "Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;", "sharedPreferences", "Landroid/content/SharedPreferences;", "autosaveLatencyRecorder", "provideAutosaveObserverCoordinator$AndroidPhotosAutosave_release", "provideAutosaveOperations", "provideAutosaveOperations$AndroidPhotosAutosave_release", "provideAutosavePreferences", "preferenceChangeListener", "Lcom/amazon/photos/autosave/internal/preferences/AutosavePreferenceChangeListener;", "autosaveOperations", "dispatcherProvider", "Lcom/amazon/photos/autosave/internal/coroutines/DispatcherProvider;", "queueHelper", "Lcom/amazon/photos/autosave/internal/preferences/PreferenceUploadQueueHelper;", "provideAutosavePreferences$AndroidPhotosAutosave_release", "provideAutosavePreferencesListener", "provideAutosavePreferencesListener$AndroidPhotosAutosave_release", "provideCDClient", "provideCDClient$AndroidPhotosAutosave_release", "provideDateUtils", "Lcom/amazon/photos/autosave/internal/utils/DateUtils;", "provideDateUtils$AndroidPhotosAutosave_release", "provideDiscovery", "provideDiscovery$AndroidPhotosAutosave_release", "provideDispatcherProvider", "provideDispatcherProvider$AndroidPhotosAutosave_release", "provideLogger", "provideLogger$AndroidPhotosAutosave_release", "provideMetrics", "provideMetrics$AndroidPhotosAutosave_release", "provideMetricsHelper", "Lcom/amazon/photos/autosave/internal/metrics/MetricsHelper;", "provideMetricsHelper$AndroidPhotosAutosave_release", "providePreferenceQueueHelper", "providePreferenceQueueHelper$AndroidPhotosAutosave_release", "provideSystemUtil", "provideSystemUtil$AndroidPhotosAutosave_release", "provideUploadHelper", "Lcom/amazon/photos/autosave/internal/upload/UploadHelper;", "dateUtils", "provideUploadHelper$AndroidPhotosAutosave_release", "provideUploadManager", "provideUploadManager$AndroidPhotosAutosave_release", "provideWorkManager", "provideWorkManager$AndroidPhotosAutosave_release", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes13.dex */
public final class AutosaveModule {
    private final AutosaveFrameworkContext autosaveFrameworkContext;
    private final CDClient cdClient;
    @NotNull
    private final DefaultAutosavePreferences defaultPreferences;
    private final Discovery discovery;
    private final Logger logger;
    private final Metrics metrics;
    private final UploadManager uploadManager;
    private final UploadPriorityResolver uploadPriorityResolver;
    private final WorkManager workManager;

    public AutosaveModule(@NotNull Logger logger, @NotNull Metrics metrics, @NotNull AutosaveFrameworkContext autosaveFrameworkContext, @NotNull CDClient cdClient, @NotNull WorkManager workManager, @NotNull UploadManager uploadManager, @NotNull Discovery discovery, @NotNull DefaultAutosavePreferences defaultPreferences, @NotNull UploadPriorityResolver uploadPriorityResolver) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(autosaveFrameworkContext, "autosaveFrameworkContext");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(workManager, "workManager");
        Intrinsics.checkParameterIsNotNull(uploadManager, "uploadManager");
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(defaultPreferences, "defaultPreferences");
        Intrinsics.checkParameterIsNotNull(uploadPriorityResolver, "uploadPriorityResolver");
        this.logger = logger;
        this.metrics = metrics;
        this.autosaveFrameworkContext = autosaveFrameworkContext;
        this.cdClient = cdClient;
        this.workManager = workManager;
        this.uploadManager = uploadManager;
        this.discovery = discovery;
        this.defaultPreferences = defaultPreferences;
        this.uploadPriorityResolver = uploadPriorityResolver;
    }

    @NotNull
    public final DefaultAutosavePreferences getDefaultPreferences$AndroidPhotosAutosave_release() {
        return this.defaultPreferences;
    }

    @PerAccount
    @Provides
    @NotNull
    public final AutosaveEventNotifier provideAutosaveEventNotifier$AndroidPhotosAutosave_release() {
        return new AutosaveEventNotifier();
    }

    @PerAccount
    @Provides
    @NotNull
    public final AutosaveFrameworkContext provideAutosaveFrameworkContext$AndroidPhotosAutosave_release() {
        return this.autosaveFrameworkContext;
    }

    @PerAccount
    @Provides
    @NotNull
    public final AutosaveLatencyRecorder provideAutosaveLatencyRecorder$AndroidPhotosAutosave_release(@NotNull Metrics metrics, @NotNull SystemUtil systemUtil, @NotNull AutosaveEventNotifier autosaveEventNotifier) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(autosaveEventNotifier, "autosaveEventNotifier");
        AutosaveLatencyRecorder autosaveLatencyRecorder = new AutosaveLatencyRecorder(metrics, systemUtil);
        Scheduler computation = Schedulers.computation();
        Intrinsics.checkExpressionValueIsNotNull(computation, "Schedulers.computation()");
        autosaveEventNotifier.addObserver(autosaveLatencyRecorder, computation);
        return autosaveLatencyRecorder;
    }

    @PerAccount
    @Provides
    @NotNull
    public final AutosaveObserverCoordinator provideAutosaveObserverCoordinator$AndroidPhotosAutosave_release(@NotNull Discovery discovery, @NotNull UploadManager uploadManager, @NotNull AutosaveOperations operations, @NotNull AutosaveItemDao autosaveItemDao, @NotNull AutosaveBucketDao autosaveBucketDao, @NotNull InternalPreferences autosavePreferences, @NotNull AutosaveTransactionRunner transactionRunner, @NotNull Metrics metrics, @NotNull AutosaveEventNotifier autosaveEventNotifier, @NotNull SharedPreferences sharedPreferences, @NotNull AutosaveLatencyRecorder autosaveLatencyRecorder) {
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(uploadManager, "uploadManager");
        Intrinsics.checkParameterIsNotNull(operations, "operations");
        Intrinsics.checkParameterIsNotNull(autosaveItemDao, "autosaveItemDao");
        Intrinsics.checkParameterIsNotNull(autosaveBucketDao, "autosaveBucketDao");
        Intrinsics.checkParameterIsNotNull(autosavePreferences, "autosavePreferences");
        Intrinsics.checkParameterIsNotNull(transactionRunner, "transactionRunner");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(autosaveEventNotifier, "autosaveEventNotifier");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(autosaveLatencyRecorder, "autosaveLatencyRecorder");
        return new AutosaveObserverCoordinator(discovery, uploadManager, autosaveEventNotifier, operations, autosaveItemDao, transactionRunner, metrics, autosaveBucketDao, autosavePreferences, sharedPreferences, autosaveLatencyRecorder);
    }

    @PerAccount
    @Provides
    @NotNull
    public final AutosaveOperations provideAutosaveOperations$AndroidPhotosAutosave_release(@NotNull WorkManager workManager, @NotNull AutosaveFrameworkContext autosaveFrameworkContext, @NotNull Discovery discovery) {
        Intrinsics.checkParameterIsNotNull(workManager, "workManager");
        Intrinsics.checkParameterIsNotNull(autosaveFrameworkContext, "autosaveFrameworkContext");
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        return new AutosaveOperations(workManager, autosaveFrameworkContext, discovery, this.logger);
    }

    @PerAccount
    @Provides
    @NotNull
    public final InternalPreferences provideAutosavePreferences$AndroidPhotosAutosave_release(@NotNull AutosaveBucketDao autosaveBucketDao, @NotNull AutosavePreferenceChangeListener preferenceChangeListener, @NotNull AutosaveOperations autosaveOperations, @NotNull DispatcherProvider dispatcherProvider, @NotNull PreferenceUploadQueueHelper queueHelper, @NotNull SharedPreferences sharedPreferences, @NotNull AutosaveEventNotifier autosaveEventNotifier) {
        Intrinsics.checkParameterIsNotNull(autosaveBucketDao, "autosaveBucketDao");
        Intrinsics.checkParameterIsNotNull(preferenceChangeListener, "preferenceChangeListener");
        Intrinsics.checkParameterIsNotNull(autosaveOperations, "autosaveOperations");
        Intrinsics.checkParameterIsNotNull(dispatcherProvider, "dispatcherProvider");
        Intrinsics.checkParameterIsNotNull(queueHelper, "queueHelper");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(autosaveEventNotifier, "autosaveEventNotifier");
        return new AutosavePreferencesImpl(autosaveBucketDao, this.defaultPreferences, preferenceChangeListener, this.metrics, autosaveOperations, dispatcherProvider, queueHelper, sharedPreferences, autosaveEventNotifier);
    }

    @PerAccount
    @Provides
    @NotNull
    public final AutosavePreferenceChangeListener provideAutosavePreferencesListener$AndroidPhotosAutosave_release(@NotNull DispatcherProvider dispatcherProvider, @NotNull PreferenceUploadQueueHelper queueHelper) {
        Intrinsics.checkParameterIsNotNull(dispatcherProvider, "dispatcherProvider");
        Intrinsics.checkParameterIsNotNull(queueHelper, "queueHelper");
        return new AutosavePreferenceChangeListener(this.defaultPreferences, dispatcherProvider, queueHelper);
    }

    @PerAccount
    @Provides
    @NotNull
    public final CDClient provideCDClient$AndroidPhotosAutosave_release() {
        return this.cdClient;
    }

    @PerAccount
    @Provides
    @NotNull
    public final DateUtils provideDateUtils$AndroidPhotosAutosave_release() {
        return new DateUtils();
    }

    @PerAccount
    @Provides
    @NotNull
    public final Discovery provideDiscovery$AndroidPhotosAutosave_release() {
        return this.discovery;
    }

    @PerAccount
    @Provides
    @NotNull
    public final DispatcherProvider provideDispatcherProvider$AndroidPhotosAutosave_release() {
        return new DefaultDispatcherProvider();
    }

    @PerAccount
    @Provides
    @NotNull
    public final Logger provideLogger$AndroidPhotosAutosave_release() {
        return this.logger;
    }

    @PerAccount
    @Provides
    @NotNull
    public final Metrics provideMetrics$AndroidPhotosAutosave_release() {
        return this.metrics;
    }

    @PerAccount
    @Provides
    @NotNull
    public final MetricsHelper provideMetricsHelper$AndroidPhotosAutosave_release(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        return new MetricsHelper(metrics);
    }

    @PerAccount
    @Provides
    @NotNull
    public final PreferenceUploadQueueHelper providePreferenceQueueHelper$AndroidPhotosAutosave_release(@NotNull UploadManager uploadManager, @NotNull AutosaveOperations autosaveOperations, @NotNull Discovery discovery, @NotNull AutosaveBucketDao autosaveBucketDao, @NotNull AutosaveTransactionRunner transactionRunner, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(uploadManager, "uploadManager");
        Intrinsics.checkParameterIsNotNull(autosaveOperations, "autosaveOperations");
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(autosaveBucketDao, "autosaveBucketDao");
        Intrinsics.checkParameterIsNotNull(transactionRunner, "transactionRunner");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        return new PreferenceUploadQueueHelper(uploadManager, autosaveOperations, discovery, autosaveBucketDao, transactionRunner, sharedPreferences);
    }

    @PerAccount
    @Provides
    @NotNull
    public final SystemUtil provideSystemUtil$AndroidPhotosAutosave_release() {
        return new SystemUtil();
    }

    @PerAccount
    @Provides
    @NotNull
    public final UploadHelper provideUploadHelper$AndroidPhotosAutosave_release(@NotNull SystemUtil systemUtil, @NotNull AutosaveTransactionRunner transactionRunner, @NotNull Discovery discovery, @NotNull DateUtils dateUtils) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(transactionRunner, "transactionRunner");
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(dateUtils, "dateUtils");
        return new UploadHelper(systemUtil, this.uploadPriorityResolver, transactionRunner, discovery, dateUtils);
    }

    @PerAccount
    @Provides
    @NotNull
    public final UploadManager provideUploadManager$AndroidPhotosAutosave_release() {
        return this.uploadManager;
    }

    @PerAccount
    @Provides
    @NotNull
    public final WorkManager provideWorkManager$AndroidPhotosAutosave_release() {
        return this.workManager;
    }
}
