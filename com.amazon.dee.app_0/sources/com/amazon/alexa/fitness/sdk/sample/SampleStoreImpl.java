package com.amazon.alexa.fitness.sdk.sample;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.VisibleForTesting;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.DatabaseMetrics;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SampleType;
import com.amazon.alexa.fitness.sdk.database.EchoBudSampleEntity;
import com.amazon.alexa.fitness.sdk.database.FitnessDatabase;
import com.amazon.alexa.fitness.sdk.database.FitnessDatabaseUtilKt;
import com.amazon.alexa.fitness.sdk.database.LocationSampleEntity;
import com.amazon.alexa.fitness.sdk.database.MeasurementSampleEntity;
import com.amazon.alexa.fitness.sdk.database.SampleDao;
import com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl;
import com.amazon.alexa.fitness.util.ExhaustiveKt;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SampleStore.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001@B%\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016J9\u0010)\u001a\u00020&2\u0006\u0010'\u001a\u00020(2'\u0010*\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020-0,¢\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020&0+H\u0016J3\u00101\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(2!\u00102\u001a\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020&0+H\u0016J\u0016\u00104\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u0010'\u001a\u00020(H\u0016J\u0016\u00105\u001a\b\u0012\u0004\u0012\u0002060,2\u0006\u0010'\u001a\u00020(H\u0016J9\u00105\u001a\u00020&2\u0006\u0010'\u001a\u00020(2'\u00107\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002060,¢\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020&0+H\u0016J3\u00108\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(2!\u00102\u001a\u001d\u0012\u0013\u0012\u001109¢\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020&0+H\u0016J;\u0010:\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(2\u0006\u0010;\u001a\u00020\u00192!\u00102\u001a\u001d\u0012\u0013\u0012\u00110<¢\u0006\f\b.\u0012\b\b/\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020&0+H\u0016J\u0010\u0010=\u001a\u00020&2\u0006\u0010>\u001a\u00020\u0017H\u0016J\u0010\u0010?\u001a\u00020&2\u0006\u00103\u001a\u000206H\u0016J \u0010?\u001a\u00020&2\u0016\u00100\u001a\u0012\u0012\u0004\u0012\u0002060\u001aj\b\u0012\u0004\u0012\u000206`\u001bH\u0016R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u0018\u001a*\u0012\u0004\u0012\u00020\u0019\u0012 \u0012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u001aj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f`\u001b0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u00020 8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\"\u001a\u0004\b#\u0010$¨\u0006A"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/sample/SampleStoreImpl;", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "sampleRepositoryProvider", "Ljavax/inject/Provider;", "Lcom/amazon/alexa/fitness/sdk/database/FitnessDatabase;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Ljavax/inject/Provider;Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;Lcom/amazon/alexa/fitness/logs/ILog;)V", "allSamplesObserverList", "", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStoreImpl$Observer;", "", "getLog", "()Lcom/amazon/alexa/fitness/logs/ILog;", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "kotlin.jvm.PlatformType", "getMetricsAggregator", "()Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "observers", "", "Lcom/amazon/alexa/fitness/sdk/sample/ObserverToken;", "sampleObserverMap", "Lcom/amazon/alexa/fitness/sdk/SampleType;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "sampleRepository", "workerHandler", "Landroid/os/Handler;", "workerHandlerThread", "Landroid/os/HandlerThread;", "workerHandlerThread$annotations", "()V", "getWorkerHandlerThread", "()Landroid/os/HandlerThread;", "deleteAllSamples", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "queryAllLocationSamples", Constants.BUNDLE_RECEIVER_TAG, "Lkotlin/Function1;", "", "Lcom/amazon/alexa/fitness/sdk/Sample$LocationSample;", "Lkotlin/ParameterName;", "name", "samples", "queryAllLocationSamplesSubscription", "observer", "sample", "queryAllLocationSamplesSync", "queryAllSamples", "Lcom/amazon/alexa/fitness/sdk/Sample;", "sampleReceiver", "queryEchoBudSamplesSubscription", "Lcom/amazon/alexa/fitness/sdk/Sample$EchoBudSample;", "queryMeasurementSamplesSubscription", "sampleType", "Lcom/amazon/alexa/fitness/sdk/Sample$MeasurementSample;", "removeObserver", "token", HttpClientModule.ElementsCacheKey.STORE, "Observer", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SampleStoreImpl implements SampleStore {
    private final List<Observer<Object>> allSamplesObserverList;
    @NotNull
    private final ILog log;
    private final Mobilytics metrics;
    @NotNull
    private final MetricsAggregator metricsAggregator;
    private final Map<ObserverToken, Observer<Object>> observers;
    private final Map<SampleType, ArrayList<Observer<Object>>> sampleObserverMap;
    private FitnessDatabase sampleRepository;
    private final Handler workerHandler;
    @NotNull
    private final HandlerThread workerHandlerThread;

    /* compiled from: SampleStore.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B(\u0012!\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004¢\u0006\u0002\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR,\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/sample/SampleStoreImpl$Observer;", ExifInterface.GPS_DIRECTION_TRUE, "", "observer", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "sample", "", "(Lkotlin/jvm/functions/Function1;)V", "hasDeliveredInitialBatch", "", "getHasDeliveredInitialBatch", "()Z", "setHasDeliveredInitialBatch", "(Z)V", "getObserver", "()Lkotlin/jvm/functions/Function1;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Observer<T> {
        private boolean hasDeliveredInitialBatch;
        @NotNull
        private final Function1<T, Unit> observer;

        /* JADX WARN: Multi-variable type inference failed */
        public Observer(@NotNull Function1<? super T, Unit> observer) {
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            this.observer = observer;
        }

        public final boolean getHasDeliveredInitialBatch() {
            return this.hasDeliveredInitialBatch;
        }

        @NotNull
        public final Function1<T, Unit> getObserver() {
            return this.observer;
        }

        public final void setHasDeliveredInitialBatch(boolean z) {
            this.hasDeliveredInitialBatch = z;
        }
    }

    @Inject
    public SampleStoreImpl(@NotNull Provider<FitnessDatabase> sampleRepositoryProvider, @NotNull MetricsAggregator metricsAggregator, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(sampleRepositoryProvider, "sampleRepositoryProvider");
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.metricsAggregator = metricsAggregator;
        this.log = log;
        this.workerHandlerThread = new HandlerThread("SampleStore");
        this.observers = new LinkedHashMap();
        this.allSamplesObserverList = new ArrayList();
        this.sampleObserverMap = new LinkedHashMap();
        this.metrics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
        try {
            this.sampleRepository = sampleRepositoryProvider.mo10268get();
        } catch (Exception e) {
            Mobilytics metrics = this.metrics;
            Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
            MetricHelperKt.recordError(metrics, DatabaseMetrics.INSTANCE.getInitializationError(), e);
            this.log.error("SampleStore", "Error initializing database", e);
        }
        this.workerHandlerThread.start();
        this.workerHandler = new Handler(this.workerHandlerThread.getLooper());
    }

    @VisibleForTesting
    public static /* synthetic */ void workerHandlerThread$annotations() {
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    public void deleteAllSamples(@NotNull final UUID sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        final FitnessDatabase fitnessDatabase = this.sampleRepository;
        if (fitnessDatabase == null) {
            ILog.DefaultImpls.error$default(this.log, "SampleStore", "Sample repository does not exist to delete samples", null, 4, null);
        } else {
            this.workerHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl$deleteAllSamples$1
                @Override // java.lang.Runnable
                public final void run() {
                    Mobilytics metrics;
                    Mobilytics metrics2;
                    Mobilytics metrics3;
                    Mobilytics mobilytics;
                    Mobilytics metrics4;
                    metrics = SampleStoreImpl.this.metrics;
                    Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
                    MobilyticsMetricsTimer createTimer = MetricHelperKt.createTimer(metrics, DatabaseMetrics.Sample.DeleteAll.INSTANCE.getDuration());
                    try {
                        fitnessDatabase.sampleDao().deleteAllEchoBudSamples(sessionId.toString());
                        fitnessDatabase.sampleDao().deleteAllMeasurementSamples(sessionId.toString());
                        fitnessDatabase.sampleDao().deleteAllLocationSamples(sessionId.toString());
                        mobilytics = SampleStoreImpl.this.metrics;
                        mobilytics.recordTimer(createTimer);
                        metrics4 = SampleStoreImpl.this.metrics;
                        Intrinsics.checkExpressionValueIsNotNull(metrics4, "metrics");
                        MetricHelperKt.recordCounter$default(metrics4, DatabaseMetrics.Sample.DeleteAll.INSTANCE.getSuccess(), 0L, 2, null);
                    } catch (Exception e) {
                        metrics2 = SampleStoreImpl.this.metrics;
                        Intrinsics.checkExpressionValueIsNotNull(metrics2, "metrics");
                        MetricHelperKt.recordCounter$default(metrics2, DatabaseMetrics.Sample.DeleteAll.INSTANCE.getFailure(), 0L, 2, null);
                        metrics3 = SampleStoreImpl.this.metrics;
                        Intrinsics.checkExpressionValueIsNotNull(metrics3, "metrics");
                        MetricHelperKt.recordError(metrics3, DatabaseMetrics.Sample.DeleteAll.INSTANCE.getError(), e);
                        SampleStoreImpl.this.getLog().error("SampleStore", "Failed to delete all samples", e);
                    }
                }
            });
        }
    }

    @NotNull
    public final ILog getLog() {
        return this.log;
    }

    @NotNull
    public final MetricsAggregator getMetricsAggregator() {
        return this.metricsAggregator;
    }

    @NotNull
    public final HandlerThread getWorkerHandlerThread() {
        return this.workerHandlerThread;
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    public void queryAllLocationSamples(@NotNull final UUID sessionId, @NotNull final Function1<? super List<Sample.LocationSample>, Unit> receiver) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(receiver, "receiver");
        this.workerHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl$queryAllLocationSamples$1
            @Override // java.lang.Runnable
            public final void run() {
                receiver.mo12165invoke(SampleStoreImpl.this.queryAllLocationSamplesSync(sessionId));
            }
        });
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    @NotNull
    public ObserverToken queryAllLocationSamplesSubscription(@NotNull final UUID sessionId, @NotNull Function1<? super Sample.LocationSample, Unit> observer) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        final ObserverToken observerToken = new ObserverToken(null, 1, null);
        final FitnessDatabase fitnessDatabase = this.sampleRepository;
        Observer<Object> observer2 = new Observer<>(observer);
        this.observers.put(observerToken, observer2);
        ArrayList<Observer<Object>> arrayList = this.sampleObserverMap.get(SampleType.Location);
        if (arrayList != null) {
            arrayList.add(observer2);
        } else {
            ArrayList<Observer<Object>> arrayList2 = new ArrayList<>();
            arrayList2.add(observer2);
            this.sampleObserverMap.put(SampleType.Location, arrayList2);
        }
        if (fitnessDatabase == null) {
            ILog.DefaultImpls.error$default(this.log, "SampleStore", "Sample repository does not exist to query location samples", null, 4, null);
            return observerToken;
        }
        this.workerHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl$queryAllLocationSamplesSubscription$2
            @Override // java.lang.Runnable
            public final void run() {
                Map map;
                List<LocationSampleEntity> samples = fitnessDatabase.sampleDao().getAllLocationSamples(sessionId.toString());
                map = SampleStoreImpl.this.observers;
                SampleStoreImpl.Observer observer3 = (SampleStoreImpl.Observer) map.get(observerToken);
                if (observer3 != null) {
                    Intrinsics.checkExpressionValueIsNotNull(samples, "samples");
                    for (LocationSampleEntity it2 : samples) {
                        Function1 observer4 = observer3.getObserver();
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        observer4.mo12165invoke(FitnessDatabaseUtilKt.toLocationSample(it2));
                    }
                    observer3.setHasDeliveredInitialBatch(true);
                }
            }
        });
        return observerToken;
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    @NotNull
    public List<Sample.LocationSample> queryAllLocationSamplesSync(@NotNull UUID sessionId) {
        List<Sample.LocationSample> emptyList;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        FitnessDatabase fitnessDatabase = this.sampleRepository;
        if (fitnessDatabase != null) {
            List<LocationSampleEntity> allLocationSamples = fitnessDatabase.sampleDao().getAllLocationSamples(sessionId.toString());
            Intrinsics.checkExpressionValueIsNotNull(allLocationSamples, "db.sampleDao()\n         …les(sessionId.toString())");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(allLocationSamples, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (LocationSampleEntity it2 : allLocationSamples) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                arrayList.add(FitnessDatabaseUtilKt.toLocationSample(it2));
            }
            return arrayList;
        }
        ILog.DefaultImpls.error$default(this.log, "SampleStore", "Sample repository does not exist to query samples", null, 4, null);
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    @NotNull
    public List<Sample> queryAllSamples(@NotNull UUID sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        FitnessDatabase fitnessDatabase = this.sampleRepository;
        if (fitnessDatabase == null) {
            ILog.DefaultImpls.error$default(this.log, "SampleStore", "Sample repository does not exist to query samples", null, 4, null);
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Mobilytics metrics = this.metrics;
        Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
        MobilyticsMetricsTimer createTimer = MetricHelperKt.createTimer(metrics, DatabaseMetrics.Sample.FetchAll.INSTANCE.getDuration());
        try {
            List<EchoBudSampleEntity> allEchoBudSamples = fitnessDatabase.sampleDao().getAllEchoBudSamples(sessionId.toString());
            Intrinsics.checkExpressionValueIsNotNull(allEchoBudSamples, "db.sampleDao().getAllEch…les(sessionId.toString())");
            for (EchoBudSampleEntity it2 : allEchoBudSamples) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                arrayList.add(FitnessDatabaseUtilKt.toEchoBudSample(it2));
            }
            List<MeasurementSampleEntity> allMeasurementSamples = fitnessDatabase.sampleDao().getAllMeasurementSamples(sessionId.toString());
            Intrinsics.checkExpressionValueIsNotNull(allMeasurementSamples, "db.sampleDao().getAllMea…les(sessionId.toString())");
            for (MeasurementSampleEntity it3 : allMeasurementSamples) {
                Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                arrayList.add(FitnessDatabaseUtilKt.toMeasurementSample(it3));
            }
            List<LocationSampleEntity> allLocationSamples = fitnessDatabase.sampleDao().getAllLocationSamples(sessionId.toString());
            Intrinsics.checkExpressionValueIsNotNull(allLocationSamples, "db.sampleDao().getAllLoc…les(sessionId.toString())");
            for (LocationSampleEntity it4 : allLocationSamples) {
                Intrinsics.checkExpressionValueIsNotNull(it4, "it");
                arrayList.add(FitnessDatabaseUtilKt.toLocationSample(it4));
            }
            this.metrics.recordTimer(createTimer);
            Mobilytics metrics2 = this.metrics;
            Intrinsics.checkExpressionValueIsNotNull(metrics2, "metrics");
            MetricHelperKt.recordCounter$default(metrics2, DatabaseMetrics.Sample.FetchAll.INSTANCE.getSuccess(), 0L, 2, null);
        } catch (Exception e) {
            Mobilytics metrics3 = this.metrics;
            Intrinsics.checkExpressionValueIsNotNull(metrics3, "metrics");
            MetricHelperKt.recordCounter$default(metrics3, DatabaseMetrics.Sample.FetchAll.INSTANCE.getFailure(), 0L, 2, null);
            Mobilytics metrics4 = this.metrics;
            Intrinsics.checkExpressionValueIsNotNull(metrics4, "metrics");
            MetricHelperKt.recordError(metrics4, DatabaseMetrics.Sample.FetchAll.INSTANCE.getError(), e);
            this.log.error("SampleStore", "Failed to fetch samples", e);
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    @NotNull
    public ObserverToken queryEchoBudSamplesSubscription(@NotNull final UUID sessionId, @NotNull Function1<? super Sample.EchoBudSample, Unit> observer) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        final ObserverToken observerToken = new ObserverToken(null, 1, null);
        final FitnessDatabase fitnessDatabase = this.sampleRepository;
        Observer<Object> observer2 = new Observer<>(observer);
        this.observers.put(observerToken, observer2);
        ArrayList<Observer<Object>> arrayList = this.sampleObserverMap.get(SampleType.EchoBud);
        if (arrayList != null) {
            arrayList.add(observer2);
        } else {
            ArrayList<Observer<Object>> arrayList2 = new ArrayList<>();
            arrayList2.add(observer2);
            this.sampleObserverMap.put(SampleType.EchoBud, arrayList2);
        }
        if (fitnessDatabase == null) {
            ILog.DefaultImpls.error$default(this.log, "SampleStore", "Sample repository does not exist to query echo bud samples", null, 4, null);
            return observerToken;
        }
        this.workerHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl$queryEchoBudSamplesSubscription$2
            @Override // java.lang.Runnable
            public final void run() {
                Map map;
                List<EchoBudSampleEntity> samples = fitnessDatabase.sampleDao().getAllEchoBudSamples(sessionId.toString());
                map = SampleStoreImpl.this.observers;
                SampleStoreImpl.Observer observer3 = (SampleStoreImpl.Observer) map.get(observerToken);
                if (observer3 != null) {
                    Intrinsics.checkExpressionValueIsNotNull(samples, "samples");
                    for (EchoBudSampleEntity it2 : samples) {
                        Function1 observer4 = observer3.getObserver();
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        observer4.mo12165invoke(FitnessDatabaseUtilKt.toEchoBudSample(it2));
                    }
                    observer3.setHasDeliveredInitialBatch(true);
                }
            }
        });
        return observerToken;
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    @NotNull
    public ObserverToken queryMeasurementSamplesSubscription(@NotNull final UUID sessionId, @NotNull final SampleType sampleType, @NotNull Function1<? super Sample.MeasurementSample, Unit> observer) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(sampleType, "sampleType");
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        final ObserverToken observerToken = new ObserverToken(null, 1, null);
        final FitnessDatabase fitnessDatabase = this.sampleRepository;
        Observer<Object> observer2 = new Observer<>(observer);
        this.observers.put(observerToken, observer2);
        ArrayList<Observer<Object>> arrayList = this.sampleObserverMap.get(sampleType);
        if (arrayList != null) {
            arrayList.add(observer2);
        } else {
            ArrayList<Observer<Object>> arrayList2 = new ArrayList<>();
            arrayList2.add(observer2);
            this.sampleObserverMap.put(sampleType, arrayList2);
        }
        if (fitnessDatabase == null) {
            ILog.DefaultImpls.error$default(this.log, "SampleStore", "Sample repository does not exist to query measurement samples", null, 4, null);
            return observerToken;
        }
        this.workerHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl$queryMeasurementSamplesSubscription$2
            @Override // java.lang.Runnable
            public final void run() {
                Map map;
                List<MeasurementSampleEntity> samples = fitnessDatabase.sampleDao().getAllMeasurementSamples(sessionId.toString(), sampleType.name());
                map = SampleStoreImpl.this.observers;
                SampleStoreImpl.Observer observer3 = (SampleStoreImpl.Observer) map.get(observerToken);
                if (observer3 != null) {
                    Intrinsics.checkExpressionValueIsNotNull(samples, "samples");
                    for (MeasurementSampleEntity it2 : samples) {
                        Function1 observer4 = observer3.getObserver();
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        observer4.mo12165invoke(FitnessDatabaseUtilKt.toMeasurementSample(it2));
                    }
                    observer3.setHasDeliveredInitialBatch(true);
                }
            }
        });
        return observerToken;
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    public void removeObserver(@NotNull ObserverToken token) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        Observer<Object> observer = this.observers.get(token);
        List<Observer<Object>> list = this.allSamplesObserverList;
        if (list != null) {
            TypeIntrinsics.asMutableCollection(list).remove(observer);
            Iterator<T> it2 = this.sampleObserverMap.values().iterator();
            while (it2.hasNext()) {
                Iterator it3 = ((ArrayList) it2.next()).iterator();
                Intrinsics.checkExpressionValueIsNotNull(it3, "it.iterator()");
                while (true) {
                    if (it3.hasNext()) {
                        Object next = it3.next();
                        Intrinsics.checkExpressionValueIsNotNull(next, "observerListIterator.next()");
                        if (((Observer) next).equals(observer)) {
                            it3.remove();
                            break;
                        }
                    }
                }
            }
            this.observers.remove(token);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    public void store(@NotNull Sample sample) {
        ArrayList<Sample> arrayListOf;
        Intrinsics.checkParameterIsNotNull(sample, "sample");
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(sample);
        store(arrayListOf);
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    public void store(@NotNull final ArrayList<Sample> samples) {
        Intrinsics.checkParameterIsNotNull(samples, "samples");
        final FitnessDatabase fitnessDatabase = this.sampleRepository;
        if (fitnessDatabase != null) {
            if (((Sample) CollectionsKt.firstOrNull((List<? extends Object>) samples)) == null) {
                return;
            }
            for (final Sample sample : samples) {
                this.workerHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl$store$$inlined$let$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Mobilytics metrics;
                        List<LocationSampleEntity> mutableListOf;
                        Unit unit;
                        List<MeasurementSampleEntity> mutableListOf2;
                        List<EchoBudSampleEntity> mutableListOf3;
                        try {
                            Sample sample2 = Sample.this;
                            if (sample2 instanceof Sample.EchoBudSample) {
                                SampleDao sampleDao = fitnessDatabase.sampleDao();
                                mutableListOf3 = CollectionsKt__CollectionsKt.mutableListOf(FitnessDatabaseUtilKt.toEchoBudSampleEntity((Sample.EchoBudSample) Sample.this));
                                sampleDao.insertEchobudSample(mutableListOf3);
                                unit = Unit.INSTANCE;
                            } else if (sample2 instanceof Sample.MeasurementSample) {
                                SampleDao sampleDao2 = fitnessDatabase.sampleDao();
                                mutableListOf2 = CollectionsKt__CollectionsKt.mutableListOf(FitnessDatabaseUtilKt.toMeasurementSampleEntity((Sample.MeasurementSample) Sample.this));
                                sampleDao2.insertMeasurementSample(mutableListOf2);
                                unit = Unit.INSTANCE;
                            } else if (sample2 instanceof Sample.LocationSample) {
                                SampleDao sampleDao3 = fitnessDatabase.sampleDao();
                                mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(FitnessDatabaseUtilKt.toLocationSampleEntity((Sample.LocationSample) Sample.this));
                                sampleDao3.insertLocationSample(mutableListOf);
                                unit = Unit.INSTANCE;
                            } else {
                                throw new NoWhenBranchMatchedException();
                            }
                            ExhaustiveKt.exhaustive(unit);
                            this.getMetricsAggregator().incrementCounter(DatabaseMetrics.Sample.Save.INSTANCE.getSuccess());
                        } catch (Exception e) {
                            this.getMetricsAggregator().incrementCounter(DatabaseMetrics.Sample.Save.INSTANCE.getFailure());
                            metrics = this.metrics;
                            Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
                            MetricHelperKt.recordError(metrics, DatabaseMetrics.Sample.Save.INSTANCE.getError(), e);
                            this.getLog().error("SampleStore", "Failed to store samples", e);
                        }
                    }
                });
            }
        } else {
            ILog.DefaultImpls.error$default(this.log, "SampleStore", "Sample repository does not exist to store samples", null, 4, null);
        }
        Iterator<T> it2 = this.allSamplesObserverList.iterator();
        while (it2.hasNext()) {
            final Observer observer = (Observer) it2.next();
            if (observer.getHasDeliveredInitialBatch()) {
                for (Sample sample2 : samples) {
                    observer.getObserver().mo12165invoke(sample2);
                }
            } else {
                this.workerHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl$store$$inlined$forEach$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        for (Sample sample3 : samples) {
                            SampleStoreImpl.Observer.this.getObserver().mo12165invoke(sample3);
                        }
                    }
                });
            }
        }
        for (final Sample sample3 : samples) {
            ArrayList<Observer<Object>> arrayList = this.sampleObserverMap.get(sample3.getSampleType());
            if (arrayList != null) {
                Iterator<T> it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    final Observer observer2 = (Observer) it3.next();
                    if (observer2.getHasDeliveredInitialBatch()) {
                        observer2.getObserver().mo12165invoke(sample3);
                    } else {
                        this.workerHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl$store$$inlined$forEach$lambda$2
                            @Override // java.lang.Runnable
                            public final void run() {
                                SampleStoreImpl.Observer.this.getObserver().mo12165invoke(sample3);
                            }
                        });
                    }
                }
            }
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.sample.SampleStore
    public void queryAllSamples(@NotNull final UUID sessionId, @NotNull final Function1<? super List<? extends Sample>, Unit> sampleReceiver) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(sampleReceiver, "sampleReceiver");
        this.workerHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.sdk.sample.SampleStoreImpl$queryAllSamples$4
            @Override // java.lang.Runnable
            public final void run() {
                sampleReceiver.mo12165invoke(SampleStoreImpl.this.queryAllSamples(sessionId));
            }
        });
    }
}
