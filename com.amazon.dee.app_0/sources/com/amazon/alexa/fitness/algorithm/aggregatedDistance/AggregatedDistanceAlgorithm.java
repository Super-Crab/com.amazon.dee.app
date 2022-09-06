package com.amazon.alexa.fitness.algorithm.aggregatedDistance;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.algorithm.FitnessAlgorithm;
import com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.sdk.Aggregation;
import com.amazon.alexa.fitness.sdk.Measurement;
import com.amazon.alexa.fitness.sdk.RepeatingTimer;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SampleType;
import com.amazon.alexa.fitness.sdk.sample.ObserverToken;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AggregatedDistanceAlgorithm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 <2\u00020\u0001:\u0002<=B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ \u0010\u001e\u001a\u0004\u0018\u00010\u000f2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0 2\u0006\u0010!\u001a\u00020\"H\u0002J\u001e\u0010#\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000f0 H\u0002J$\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000f0 2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0 2\u0006\u0010'\u001a\u00020\"H\u0002J\b\u0010(\u001a\u00020\u001dH\u0002J\b\u0010)\u001a\u00020*H\u0002J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u00020\u000fH\u0002J\b\u00100\u001a\u00020*H\u0002J\u0010\u00101\u001a\u00020*2\u0006\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u00020*H\u0016J\u0018\u00105\u001a\u00020*2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00102\u001a\u000203H\u0016J\u0010\u00106\u001a\u00020\u00122\u0006\u00107\u001a\u000208H\u0002J \u00109\u001a\u00020*2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020,H\u0002J\u0014\u0010;\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010$*\u00020\u000fH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm;", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithm;", "messageProcessor", "Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;", "featureService", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;Lcom/amazon/alexa/fitness/api/afx/FeatureService;Lcom/amazon/alexa/fitness/logs/ILog;)V", "contiguousStepBasedDistanceDelta", "", "distanceObservationToken", "Lcom/amazon/alexa/fitness/sdk/sample/ObserverToken;", "incomingLocationDataSamples", "", "Lcom/amazon/alexa/fitness/sdk/Sample;", "incomingStepBasedSamples", "isActive", "", "isHybridDistanceEnabled", "lastLocationSampleFromPreviousLoop", "lastReportedDistance", "lastStepBasedSampleFromPreviousLoop", "locationObservationToken", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", OperationalEventType.TIMER, "Lcom/amazon/alexa/fitness/sdk/RepeatingTimer;", "getLastSeenStepBasedSample", "samples", "", "beforeOrAt", "", "getLatestAggregatedSample", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/SampleDataWithTimestamp;", "allSamples", "getUnprocessedStepDistanceSamples", TtmlNode.ANNOTATION_POSITION_AFTER, "initializeTimer", "performAggregation", "", "performRecoveryIfPossible", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult;", HttpClientModule.ElementsCacheKey.STORE, "receive", "sample", "releaseVariables", "sessionChangedState", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "sessionEnded", "setup", "shouldStartTimer", "sessionState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "subscribeToStore", "recoveryResult", "getAggregatedDistanceSample", "Companion", "FitnessAlgorithmRecoveryResult", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AggregatedDistanceAlgorithm implements FitnessAlgorithm {
    @NotNull
    public static final String AlgorithmId = "com.amazon.fitness.aggregatedDistanceAlgorithm";
    public static final Companion Companion = new Companion(null);
    private double contiguousStepBasedDistanceDelta;
    private ObserverToken distanceObservationToken;
    private List<Sample> incomingLocationDataSamples;
    private List<Sample> incomingStepBasedSamples;
    private boolean isActive;
    private final boolean isHybridDistanceEnabled;
    private Sample lastLocationSampleFromPreviousLoop;
    private double lastReportedDistance;
    private Sample lastStepBasedSampleFromPreviousLoop;
    private ObserverToken locationObservationToken;
    private final ILog log;
    private final AfxMessageProcessor messageProcessor;
    private SampleStore sampleStore;
    private UUID sessionId;
    private RepeatingTimer timer;

    /* compiled from: AggregatedDistanceAlgorithm.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$Companion;", "", "()V", "AlgorithmId", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: AggregatedDistanceAlgorithm.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult;", "", "()V", "NoRecoveryRequired", "NoUnprocessedSamples", "ProcessedAllSamples", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult$NoRecoveryRequired;", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult$NoUnprocessedSamples;", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult$ProcessedAllSamples;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    private static abstract class FitnessAlgorithmRecoveryResult {

        /* compiled from: AggregatedDistanceAlgorithm.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult$NoRecoveryRequired;", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult;", "()V", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes.dex */
        public static final class NoRecoveryRequired extends FitnessAlgorithmRecoveryResult {
            public static final NoRecoveryRequired INSTANCE = new NoRecoveryRequired();

            private NoRecoveryRequired() {
                super(null);
            }
        }

        /* compiled from: AggregatedDistanceAlgorithm.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult$NoUnprocessedSamples;", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult;", "lastProcessedTimestamp", "", "(J)V", "getLastProcessedTimestamp", "()J", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes.dex */
        public static final class NoUnprocessedSamples extends FitnessAlgorithmRecoveryResult {
            private final long lastProcessedTimestamp;

            public NoUnprocessedSamples(long j) {
                super(null);
                this.lastProcessedTimestamp = j;
            }

            public final long getLastProcessedTimestamp() {
                return this.lastProcessedTimestamp;
            }
        }

        /* compiled from: AggregatedDistanceAlgorithm.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult$ProcessedAllSamples;", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/AggregatedDistanceAlgorithm$FitnessAlgorithmRecoveryResult;", "lastProcessedTimestamp", "", "(J)V", "getLastProcessedTimestamp", "()J", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes.dex */
        public static final class ProcessedAllSamples extends FitnessAlgorithmRecoveryResult {
            private final long lastProcessedTimestamp;

            public ProcessedAllSamples(long j) {
                super(null);
                this.lastProcessedTimestamp = j;
            }

            public final long getLastProcessedTimestamp() {
                return this.lastProcessedTimestamp;
            }
        }

        private FitnessAlgorithmRecoveryResult() {
        }

        public /* synthetic */ FitnessAlgorithmRecoveryResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FitnessSessionState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[FitnessSessionState.ACTIVE.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[SampleType.values().length];
            $EnumSwitchMapping$1[SampleType.Location.ordinal()] = 1;
            $EnumSwitchMapping$1[SampleType.StepBasedDistance.ordinal()] = 2;
        }
    }

    @Inject
    public AggregatedDistanceAlgorithm(@NotNull AfxMessageProcessor messageProcessor, @NotNull FeatureService featureService, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(messageProcessor, "messageProcessor");
        Intrinsics.checkParameterIsNotNull(featureService, "featureService");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.messageProcessor = messageProcessor;
        this.log = log;
        this.isHybridDistanceEnabled = featureService.isHybridDistanceEnabled();
        this.incomingLocationDataSamples = new ArrayList();
        this.incomingStepBasedSamples = new ArrayList();
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("initializing algorithm (isHybridDistanceEnabled=");
        outline107.append(this.isHybridDistanceEnabled);
        outline107.append(')');
        ILog.DefaultImpls.debug$default(iLog, "AggregatedDistanceAlgorithm", outline107.toString(), null, 4, null);
    }

    private final SampleDataWithTimestamp<Double> getAggregatedDistanceSample(@NotNull Sample sample) {
        if (!(sample instanceof Sample.MeasurementSample)) {
            sample = null;
        }
        Sample.MeasurementSample measurementSample = (Sample.MeasurementSample) sample;
        if (measurementSample == null || measurementSample.getSampleType() != SampleType.Distance) {
            return null;
        }
        return new SampleDataWithTimestamp<>(Double.valueOf(measurementSample.getValue().getValue()), measurementSample.getReceivedAtTimestamp());
    }

    private final Sample getLastSeenStepBasedSample(List<? extends Sample> list, long j) {
        Object next;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = list.iterator();
        while (true) {
            boolean z = true;
            if (!it2.hasNext()) {
                break;
            }
            Object next2 = it2.next();
            if (StepBasedDistanceDeltaCalculatorKt.getStepDistanceSample((Sample) next2) == null) {
                z = false;
            }
            if (z) {
                arrayList.add(next2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((Sample) obj).getReceivedAtTimestamp() <= j) {
                arrayList2.add(obj);
            }
        }
        Iterator it3 = arrayList2.iterator();
        if (!it3.hasNext()) {
            next = null;
        } else {
            next = it3.next();
            if (it3.hasNext()) {
                long receivedAtTimestamp = ((Sample) next).getReceivedAtTimestamp();
                do {
                    Object next3 = it3.next();
                    long receivedAtTimestamp2 = ((Sample) next3).getReceivedAtTimestamp();
                    if (receivedAtTimestamp < receivedAtTimestamp2) {
                        next = next3;
                        receivedAtTimestamp = receivedAtTimestamp2;
                    }
                } while (it3.hasNext());
            }
        }
        return (Sample) next;
    }

    private final SampleDataWithTimestamp<Double> getLatestAggregatedSample(List<? extends Sample> list) {
        Object next;
        ArrayList arrayList = new ArrayList();
        for (Sample sample : list) {
            SampleDataWithTimestamp<Double> aggregatedDistanceSample = getAggregatedDistanceSample(sample);
            if (aggregatedDistanceSample != null) {
                arrayList.add(aggregatedDistanceSample);
            }
        }
        Iterator it2 = arrayList.iterator();
        if (!it2.hasNext()) {
            next = null;
        } else {
            next = it2.next();
            if (it2.hasNext()) {
                long timestamp = ((SampleDataWithTimestamp) next).getTimestamp();
                do {
                    Object next2 = it2.next();
                    long timestamp2 = ((SampleDataWithTimestamp) next2).getTimestamp();
                    if (timestamp < timestamp2) {
                        next = next2;
                        timestamp = timestamp2;
                    }
                } while (it2.hasNext());
            }
        }
        SampleDataWithTimestamp<Double> sampleDataWithTimestamp = (SampleDataWithTimestamp) next;
        if (sampleDataWithTimestamp != null) {
            return sampleDataWithTimestamp;
        }
        ILog.DefaultImpls.info$default(this.log, "AggregatedDistanceAlgorithm", "No aggregated samples found for this session", null, 4, null);
        return null;
    }

    private final List<Sample> getUnprocessedStepDistanceSamples(List<? extends Sample> list, long j) {
        List<Sample> sortedWith;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = list.iterator();
        while (true) {
            boolean z = true;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (StepBasedDistanceDeltaCalculatorKt.getStepDistanceSample((Sample) next) == null) {
                z = false;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((Sample) obj).getReceivedAtTimestamp() > j) {
                arrayList2.add(obj);
            }
        }
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(arrayList2, new Comparator<T>() { // from class: com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm$getUnprocessedStepDistanceSamples$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int compareValues;
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((Sample) t).getReceivedAtTimestamp()), Long.valueOf(((Sample) t2).getReceivedAtTimestamp()));
                return compareValues;
            }
        });
        return sortedWith;
    }

    private final RepeatingTimer initializeTimer() {
        ILog.DefaultImpls.info$default(this.log, "AggregatedDistanceAlgorithm", "Initializing internal clock for aggregation loop execution", null, 4, null);
        RepeatingTimer repeatingTimer = new RepeatingTimer(this.messageProcessor, this.log, 10000L);
        repeatingTimer.setRunnable(new Runnable() { // from class: com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm$initializeTimer$1
            @Override // java.lang.Runnable
            public final void run() {
                AggregatedDistanceAlgorithm.this.performAggregation();
            }
        });
        return repeatingTimer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void performAggregation() {
        double value;
        UUID uuid = this.sessionId;
        SampleStore sampleStore = this.sampleStore;
        if (uuid != null && sampleStore != null) {
            ILog.DefaultImpls.info$default(this.log, "AggregatedDistanceAlgorithm", "Starting aggregation loop", null, 4, null);
            List<Sample> list = this.incomingStepBasedSamples;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it2 = list.iterator();
            while (true) {
                boolean z = true;
                long j = 0;
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                long receivedAtTimestamp = ((Sample) next).getReceivedAtTimestamp();
                Sample sample = this.lastStepBasedSampleFromPreviousLoop;
                if (sample != null) {
                    j = sample.getReceivedAtTimestamp();
                }
                if (receivedAtTimestamp <= j) {
                    z = false;
                }
                if (z) {
                    arrayList.add(next);
                }
            }
            List<Sample> list2 = this.incomingLocationDataSamples;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : list2) {
                long receivedAtTimestamp2 = ((Sample) obj).getReceivedAtTimestamp();
                Sample sample2 = this.lastLocationSampleFromPreviousLoop;
                if (receivedAtTimestamp2 > (sample2 != null ? sample2.getReceivedAtTimestamp() : 0L)) {
                    arrayList2.add(obj);
                }
            }
            this.incomingStepBasedSamples.clear();
            this.incomingLocationDataSamples.clear();
            StepBasedDistanceDeltaCalculator stepBasedDistanceDeltaCalculator = new StepBasedDistanceDeltaCalculator(this.log, this.lastStepBasedSampleFromPreviousLoop);
            stepBasedDistanceDeltaCalculator.addSamples(arrayList);
            DistanceDelta calculate = stepBasedDistanceDeltaCalculator.calculate();
            LocationBasedDistanceDeltaCalculator locationBasedDistanceDeltaCalculator = new LocationBasedDistanceDeltaCalculator(this.log, this.lastLocationSampleFromPreviousLoop);
            locationBasedDistanceDeltaCalculator.addSamples(arrayList2);
            DistanceDelta calculate2 = locationBasedDistanceDeltaCalculator.calculate();
            if (calculate2.getConfidence() > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                ILog.DefaultImpls.debug$default(this.log, "AggregatedDistanceAlgorithm", "Choosing location based distance", null, 4, null);
                value = calculate2.getValue() - this.contiguousStepBasedDistanceDelta;
                if (value < 0) {
                    value = 0.0d;
                }
                this.contiguousStepBasedDistanceDelta = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            } else {
                ILog.DefaultImpls.debug$default(this.log, "AggregatedDistanceAlgorithm", "Choosing step based distance", null, 4, null);
                value = calculate.getValue();
                this.contiguousStepBasedDistanceDelta += value;
            }
            ILog.DefaultImpls.debug$default(this.log, "AggregatedDistanceAlgorithm", "Distance delta: " + value, null, 4, null);
            double d = this.lastReportedDistance + value;
            ILog.DefaultImpls.debug$default(this.log, "AggregatedDistanceAlgorithm", "New distance: " + d + " (ft)", null, 4, null);
            if (d >= this.lastReportedDistance) {
                Sample sample3 = (Sample) CollectionsKt.lastOrNull((List<? extends Object>) arrayList);
                Sample.MeasurementSample measurementSample = new Sample.MeasurementSample(uuid, AlgorithmId, sample3 != null ? sample3.getReceivedAtTimestamp() : System.currentTimeMillis(), SampleType.Distance, new Measurement.Discrete(d), Units.Feet, Aggregation.PointInTime);
                ILog.DefaultImpls.debug$default(this.log, "AggregatedDistanceAlgorithm", "Storing aggregated sample in store", null, 4, null);
                sampleStore.store(measurementSample);
                this.lastReportedDistance = d;
                this.lastLocationSampleFromPreviousLoop = (Sample) CollectionsKt.lastOrNull((List<? extends Object>) arrayList2);
                Sample sample4 = (Sample) CollectionsKt.lastOrNull((List<? extends Object>) arrayList);
                if (sample4 == null) {
                    sample4 = this.lastStepBasedSampleFromPreviousLoop;
                }
                this.lastStepBasedSampleFromPreviousLoop = sample4;
                ILog.DefaultImpls.debug$default(this.log, "AggregatedDistanceAlgorithm", "End aggregation loop", null, 4, null);
                return;
            }
            throw new Exception("New distance should always be greater than lastReportedDistance");
        }
        ILog.DefaultImpls.error$default(this.log, "AggregatedDistanceAlgorithm", "sessionId/sampleStore are null while attempting to generate aggregated distance", null, 4, null);
    }

    private final FitnessAlgorithmRecoveryResult performRecoveryIfPossible(SampleStore sampleStore, UUID uuid) {
        ILog.DefaultImpls.info$default(this.log, "AggregatedDistanceAlgorithm", "Attempting recovery...", null, 4, null);
        ILog.DefaultImpls.debug$default(this.log, "AggregatedDistanceAlgorithm", "Querying SampleStore for all samples", null, 4, null);
        List<Sample> queryAllSamples = sampleStore.queryAllSamples(uuid);
        SampleDataWithTimestamp<Double> latestAggregatedSample = getLatestAggregatedSample(queryAllSamples);
        if (latestAggregatedSample == null) {
            ILog.DefaultImpls.info$default(this.log, "AggregatedDistanceAlgorithm", "Recovery not needed", null, 4, null);
            return FitnessAlgorithmRecoveryResult.NoRecoveryRequired.INSTANCE;
        }
        this.lastReportedDistance = latestAggregatedSample.getSampleData().doubleValue();
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LastReportedDistance: ");
        outline107.append(this.lastReportedDistance);
        ILog.DefaultImpls.debug$default(iLog, "AggregatedDistanceAlgorithm", outline107.toString(), null, 4, null);
        long timestamp = latestAggregatedSample.getTimestamp();
        ILog.DefaultImpls.debug$default(this.log, "AggregatedDistanceAlgorithm", GeneratedOutlineSupport1.outline56("LastProcessedTimestamp: ", timestamp), null, 4, null);
        this.lastStepBasedSampleFromPreviousLoop = getLastSeenStepBasedSample(queryAllSamples, timestamp);
        ILog.DefaultImpls.info$default(this.log, "AggregatedDistanceAlgorithm", "Retrieving unprocessed .stepBased samples", null, 4, null);
        List<Sample> unprocessedStepDistanceSamples = getUnprocessedStepDistanceSamples(queryAllSamples, timestamp);
        ILog iLog2 = this.log;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Found ");
        outline1072.append(unprocessedStepDistanceSamples.size());
        outline1072.append(" unprocessed .stepBased samples");
        ILog.DefaultImpls.info$default(iLog2, "AggregatedDistanceAlgorithm", outline1072.toString(), null, 4, null);
        for (Sample sample : unprocessedStepDistanceSamples) {
            SampleDataWithTimestamp<Double> stepDistanceSample = StepBasedDistanceDeltaCalculatorKt.getStepDistanceSample(sample);
            if (stepDistanceSample != null && stepDistanceSample.getSampleData().doubleValue() > this.lastReportedDistance) {
                StepBasedDistanceDeltaCalculator stepBasedDistanceDeltaCalculator = new StepBasedDistanceDeltaCalculator(this.log, this.lastStepBasedSampleFromPreviousLoop);
                stepBasedDistanceDeltaCalculator.addSample(sample);
                DistanceDelta calculate = stepBasedDistanceDeltaCalculator.calculate();
                if (calculate.getConfidence() <= 0) {
                    this.lastStepBasedSampleFromPreviousLoop = sample;
                } else {
                    double value = this.lastReportedDistance + calculate.getValue();
                    sampleStore.store(new Sample.MeasurementSample(uuid, AlgorithmId, sample.getReceivedAtTimestamp(), SampleType.Distance, new Measurement.Discrete(value), Units.Feet, Aggregation.PointInTime));
                    ILog iLog3 = this.log;
                    ILog.DefaultImpls.debug$default(iLog3, "AggregatedDistanceAlgorithm", "Updating state variables: lastReportedDistance=" + value + ", lastStepBasedSampleFromPreviousLoop=" + sample + ", lastProcessedTimestamp=" + timestamp, null, 4, null);
                    this.lastReportedDistance = value;
                    this.lastStepBasedSampleFromPreviousLoop = sample;
                    timestamp = sample.getReceivedAtTimestamp();
                }
            } else {
                ILog iLog4 = this.log;
                ILog.DefaultImpls.info$default(iLog4, "AggregatedDistanceAlgorithm", "Ignoring " + sample + " since it is less than or equal to lastReportedDistance", null, 4, null);
            }
        }
        ILog.DefaultImpls.info$default(this.log, "AggregatedDistanceAlgorithm", "Recovery done", null, 4, null);
        return new FitnessAlgorithmRecoveryResult.ProcessedAllSamples(timestamp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void receive(final Sample sample) {
        if (!this.isActive) {
            ILog iLog = this.log;
            ILog.DefaultImpls.error$default(iLog, "AggregatedDistanceAlgorithm", "Received sample while inactive: " + sample, null, 4, null);
            return;
        }
        this.messageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm$receive$1
            @Override // java.lang.Runnable
            public final void run() {
                List list;
                ILog iLog2;
                SampleDataWithTimestamp<Double> stepDistanceSample;
                List list2;
                ILog iLog3;
                int i = AggregatedDistanceAlgorithm.WhenMappings.$EnumSwitchMapping$1[sample.getSampleType().ordinal()];
                if (i == 1) {
                    list = AggregatedDistanceAlgorithm.this.incomingLocationDataSamples;
                    list.add(sample);
                    iLog2 = AggregatedDistanceAlgorithm.this.log;
                    ILog.DefaultImpls.debug$default(iLog2, "AggregatedDistanceAlgorithm", "Received LocationSample", null, 4, null);
                } else if (i != 2 || (stepDistanceSample = StepBasedDistanceDeltaCalculatorKt.getStepDistanceSample(sample)) == null) {
                } else {
                    list2 = AggregatedDistanceAlgorithm.this.incomingStepBasedSamples;
                    list2.add(sample);
                    iLog3 = AggregatedDistanceAlgorithm.this.log;
                    ILog.DefaultImpls.debug$default(iLog3, "AggregatedDistanceAlgorithm", "Received stepBasedSample: " + stepDistanceSample, null, 4, null);
                }
            }
        });
    }

    private final void releaseVariables() {
        SampleStore sampleStore = this.sampleStore;
        if (sampleStore != null) {
            ObserverToken observerToken = this.distanceObservationToken;
            if (observerToken != null) {
                sampleStore.removeObserver(observerToken);
            }
            ObserverToken observerToken2 = this.locationObservationToken;
            if (observerToken2 != null) {
                sampleStore.removeObserver(observerToken2);
            }
        }
        this.sessionId = null;
        this.sampleStore = null;
        this.distanceObservationToken = null;
        this.locationObservationToken = null;
        RepeatingTimer repeatingTimer = this.timer;
        if (repeatingTimer != null) {
            repeatingTimer.stop();
        }
        this.timer = null;
    }

    private final boolean shouldStartTimer(FitnessSessionState fitnessSessionState) {
        return WhenMappings.$EnumSwitchMapping$0[fitnessSessionState.ordinal()] == 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void subscribeToStore(SampleStore sampleStore, UUID uuid, FitnessAlgorithmRecoveryResult fitnessAlgorithmRecoveryResult) {
        Long valueOf;
        if (fitnessAlgorithmRecoveryResult instanceof FitnessAlgorithmRecoveryResult.NoRecoveryRequired) {
            valueOf = null;
        } else if (fitnessAlgorithmRecoveryResult instanceof FitnessAlgorithmRecoveryResult.NoUnprocessedSamples) {
            valueOf = Long.valueOf(((FitnessAlgorithmRecoveryResult.NoUnprocessedSamples) fitnessAlgorithmRecoveryResult).getLastProcessedTimestamp());
        } else if (!(fitnessAlgorithmRecoveryResult instanceof FitnessAlgorithmRecoveryResult.ProcessedAllSamples)) {
            throw new NoWhenBranchMatchedException();
        } else {
            valueOf = Long.valueOf(((FitnessAlgorithmRecoveryResult.ProcessedAllSamples) fitnessAlgorithmRecoveryResult).getLastProcessedTimestamp());
        }
        AggregatedDistanceAlgorithm$subscribeToStore$1 aggregatedDistanceAlgorithm$subscribeToStore$1 = new AggregatedDistanceAlgorithm$subscribeToStore$1(this, valueOf);
        this.distanceObservationToken = sampleStore.queryMeasurementSamplesSubscription(uuid, SampleType.StepBasedDistance, aggregatedDistanceAlgorithm$subscribeToStore$1.mo12560invoke());
        this.locationObservationToken = sampleStore.queryAllLocationSamplesSubscription(uuid, aggregatedDistanceAlgorithm$subscribeToStore$1.mo12560invoke());
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithm
    public void sessionChangedState(@NotNull Session session) {
        Intrinsics.checkParameterIsNotNull(session, "session");
        if (!this.isHybridDistanceEnabled) {
            return;
        }
        final FitnessSessionState currentState = SessionDataModelsKt.currentState(session);
        ILog iLog = this.log;
        ILog.DefaultImpls.info$default(iLog, "AggregatedDistanceAlgorithm", "State changed to " + currentState, null, 4, null);
        final RepeatingTimer repeatingTimer = this.timer;
        if (repeatingTimer != null) {
            boolean shouldStartTimer = shouldStartTimer(currentState);
            if (shouldStartTimer) {
                repeatingTimer.start();
            } else {
                repeatingTimer.stop();
                this.messageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm$sessionChangedState$$inlined$let$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.performAggregation();
                    }
                });
            }
            this.isActive = shouldStartTimer;
            return;
        }
        ILog.DefaultImpls.error$default(this.log, "AggregatedDistanceAlgorithm", "Algorithm disabled, timer not initialized before session state change", null, 4, null);
        this.isActive = false;
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithm
    public void sessionEnded() {
        if (!this.isHybridDistanceEnabled) {
            return;
        }
        ILog.DefaultImpls.info$default(this.log, "AggregatedDistanceAlgorithm", "Session ended", null, 4, null);
        this.isActive = false;
        releaseVariables();
    }

    @Override // com.amazon.alexa.fitness.algorithm.FitnessAlgorithm
    public void setup(@NotNull SampleStore sampleStore, @NotNull Session session) {
        Intrinsics.checkParameterIsNotNull(sampleStore, "sampleStore");
        Intrinsics.checkParameterIsNotNull(session, "session");
        if (!this.isHybridDistanceEnabled) {
            return;
        }
        ILog.DefaultImpls.info$default(this.log, "AggregatedDistanceAlgorithm", "Setting up AggregatedDistanceAlgorithm...", null, 4, null);
        this.sessionId = session.getConfiguration().getSessionId();
        this.sampleStore = sampleStore;
        subscribeToStore(sampleStore, session.getConfiguration().getSessionId(), performRecoveryIfPossible(sampleStore, session.getConfiguration().getSessionId()));
        this.timer = initializeTimer();
    }
}
