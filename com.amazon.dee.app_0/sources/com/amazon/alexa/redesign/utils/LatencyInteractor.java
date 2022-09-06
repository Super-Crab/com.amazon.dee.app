package com.amazon.alexa.redesign.utils;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.LatencyReportingDelegate;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.latencyinfra.LatencyMarker;
import com.amazon.latencyinfra.LatencyNamespace;
import com.amazon.latencyinfra.SingleEventInputArgument;
import com.amazon.latencyinfra.SingleLatencyAction;
import com.amazon.latencyinfra.TimelineInputArgument;
import com.amazon.latencyinfra.TimelineLatencyAction;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes10.dex */
public class LatencyInteractor implements HomeContract.LatencyInteractor {
    @VisibleForTesting
    static final String APP_VERSION_NAME = "AppVersionName";
    @VisibleForTesting
    static final String COLD_START = "native.cold_start.time";
    @VisibleForTesting
    static final String FIRST_LAUNCH = "FirstLaunch";
    private static final String IN_APP_LATENCY = "home/jasper-index";
    private static final String IN_APP_LATENCY_COLD_START = "home/jasper-index-init";
    private static final String IN_APP_LATENCY_TIMELINE_NAME = "inAppLatency";
    @VisibleForTesting
    static final String LATENCY_EVENT_NAME = "JasperFirstRenderComplete";
    public static final String PROFILE_EVENT_CREATED_CARD_VIEW = "CreatedCardView";
    public static final String PROFILE_EVENT_CREATED_VIEW = "CreatedView";
    public static final String PROFILE_EVENT_FINISHED_RENDERING_SKELETON = "FinishedRenderingSkeleton";
    public static final String PROFILE_EVENT_GOT_CACHED_CARDS_COLD = "GotCachedCardsCold";
    public static final String PROFILE_EVENT_GOT_CACHED_CARDS_WARM = "GotCachedCardsWarm";
    public static final String PROFILE_EVENT_GOT_SERVER_CARDS_COLD = "GotServerCardsCold";
    public static final String PROFILE_EVENT_GOT_SERVER_CARDS_COLD_NO_CACHE = "GotServerCardsColdNoCache";
    public static final String PROFILE_EVENT_GOT_SERVER_CARDS_WARM = "GotServerCardsWarm";
    public static final String PROFILE_EVENT_GOT_SERVER_CARDS_WARM_NO_CACHE = "GotServerCardsWarmNoCache";
    public static final String PROFILE_EVENT_INITIALIZED_CLASSES = "InitializedClasses";
    public static final String PROFILE_EVENT_LAID_CACHED_CARDS_COLD = "LaidCachedCardsCold";
    public static final String PROFILE_EVENT_LAID_CACHED_CARDS_WARM = "LaidCachedCardsWarm";
    public static final String PROFILE_EVENT_LAID_SERVER_CARDS_COLD = "LaidServerCardsCold";
    public static final String PROFILE_EVENT_LAID_SERVER_CARDS_COLD_NO_CACHE = "LaidServerCardsColdNoCache";
    public static final String PROFILE_EVENT_LAID_SERVER_CARDS_WARM = "LaidServerCardsWarm";
    public static final String PROFILE_EVENT_LAID_SERVER_CARDS_WARM_NO_CACHE = "LaidServerCardsWarmNoCache";
    public static final String PROFILE_EVENT_LAID_SKELETON = "LaidSkeleton";
    public static final String PROFILE_EVENT_STARTED_PRESENTING_COLD = "StartedPresentingCold";
    public static final String PROFILE_EVENT_STARTED_PRESENTING_WARM = "StartedPresentingWarm";
    public static final String PROFILE_EVENT_TOLD_VIEW_RENDER_VOX = "ToldViewRenderVox";
    public static final String PROFILE_NAMESPACE = "home/profiling";
    public static final String PROFILE_TIMELINE_NAME = "homeProfiling";
    private final LatencyInfra latencyInfra;
    private final LatencyReportingDelegate latencyReportingDelegate;
    private final PersistentStorage.Factory persistentStorageFactory = (PersistentStorage.Factory) GeneratedOutlineSupport1.outline20(PersistentStorage.Factory.class);
    private final EnvironmentService environmentService = (EnvironmentService) GeneratedOutlineSupport1.outline20(EnvironmentService.class);
    private final TimelineInputArgument inAppLatencyColdStart = new TimelineInputArgument.Builder().withMetricsOption(true).withLogOption(true).withCustomerOption(true).withEventName(IN_APP_LATENCY_COLD_START).withNamespace(IN_APP_LATENCY_COLD_START).withTimelineName(IN_APP_LATENCY_TIMELINE_NAME).build();
    private final TimelineInputArgument inAppLatencyWarmStart = new TimelineInputArgument.Builder().withMetricsOption(true).withLogOption(true).withCustomerOption(true).withEventName(IN_APP_LATENCY).withNamespace(IN_APP_LATENCY).withTimelineName(IN_APP_LATENCY_TIMELINE_NAME).build();

    public LatencyInteractor(LatencyInfra latencyInfra, LatencyReportingDelegate latencyReportingDelegate) {
        this.latencyInfra = latencyInfra;
        this.latencyReportingDelegate = latencyReportingDelegate;
    }

    private static TimelineInputArgument buildTimelineEvent(String str, String str2) {
        return new TimelineInputArgument.Builder().withMetricsOption(true).withLogOption(true).withCustomerOption(true).withEventName(str).withNamespace(str2).withTimelineName(str).build();
    }

    public static void initProfileTimeline(LatencyInfra latencyInfra, boolean z) {
        List<String> asList;
        if (z) {
            asList = Arrays.asList(PROFILE_EVENT_INITIALIZED_CLASSES, PROFILE_EVENT_CREATED_VIEW, PROFILE_EVENT_STARTED_PRESENTING_COLD, PROFILE_EVENT_TOLD_VIEW_RENDER_VOX, PROFILE_EVENT_FINISHED_RENDERING_SKELETON, PROFILE_EVENT_GOT_CACHED_CARDS_COLD, PROFILE_EVENT_GOT_SERVER_CARDS_COLD_NO_CACHE, PROFILE_EVENT_GOT_SERVER_CARDS_COLD, PROFILE_EVENT_LAID_SKELETON, PROFILE_EVENT_LAID_CACHED_CARDS_COLD, PROFILE_EVENT_LAID_SERVER_CARDS_COLD, PROFILE_EVENT_LAID_SERVER_CARDS_COLD_NO_CACHE);
        } else {
            asList = Arrays.asList(PROFILE_EVENT_STARTED_PRESENTING_WARM, PROFILE_EVENT_GOT_CACHED_CARDS_WARM, PROFILE_EVENT_GOT_SERVER_CARDS_WARM_NO_CACHE, PROFILE_EVENT_GOT_SERVER_CARDS_WARM, PROFILE_EVENT_LAID_CACHED_CARDS_WARM, PROFILE_EVENT_LAID_SERVER_CARDS_WARM, PROFILE_EVENT_LAID_SERVER_CARDS_WARM_NO_CACHE);
        }
        for (String str : asList) {
            latencyInfra.recordTimeline(TimelineLatencyAction.START_RECORD_TIMELINE, buildTimelineEvent(str, PROFILE_NAMESPACE));
        }
    }

    @Override // com.amazon.alexa.redesign.HomeContract.LatencyInteractor
    public void dropInAppLatency() {
        this.latencyInfra.recordTimeline(TimelineLatencyAction.END_TIMELINE, this.inAppLatencyColdStart);
        this.latencyInfra.recordTimeline(TimelineLatencyAction.END_TIMELINE, this.inAppLatencyWarmStart);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.LatencyInteractor
    public void initInAppLatencyColdStart() {
        this.latencyInfra.recordTimeline(TimelineLatencyAction.START_RECORD_TIMELINE, this.inAppLatencyColdStart);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.LatencyInteractor
    public void initInAppLatencyWarmStart() {
        this.latencyInfra.recordTimeline(TimelineLatencyAction.START_RECORD_TIMELINE, this.inAppLatencyWarmStart);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.LatencyInteractor
    public void recordColdStartInAppLatency() {
        this.latencyInfra.recordTimeline(TimelineLatencyAction.RECORD_EVENT_IN_TIMELINE, this.inAppLatencyColdStart);
        this.latencyInfra.recordTimeline(TimelineLatencyAction.END_TIMELINE, this.inAppLatencyColdStart);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.LatencyInteractor
    public void recordColdStartLatency() {
        this.latencyInfra.record(SingleLatencyAction.RECORD_DURATION_FROM_APP_START, new SingleEventInputArgument.Builder().withMetricsOption(true).withLogOption(true).withCustomerOption(true).withNamespace(LatencyNamespace.HOME_COLDSTART).withEventName(LATENCY_EVENT_NAME).build());
        this.latencyInfra.record(SingleLatencyAction.RECORD_DURATION_FROM_APP_START, new SingleEventInputArgument.Builder().withMetricsOption(true).withLogOption(true).withCustomerOption(true).withNamespace(LatencyNamespace.JASPERHOME_COLDSTART).withEventName(COLD_START).build());
        recordPerformanceMetrics();
        this.latencyReportingDelegate.reportHomeLaunch();
        this.latencyInfra.blockNamespace(LatencyNamespace.HOME_COLDSTART);
        this.latencyInfra.blockNamespace(LatencyNamespace.JASPERHOME_COLDSTART);
    }

    @VisibleForTesting
    void recordPerformanceMetrics() {
        this.latencyInfra.mark(LatencyMarker.VOX_INGRESS_CSL, LatencyNamespace.PROFILE_COLDSTART);
        PersistentStorage create = this.persistentStorageFactory.create(LatencyNamespace.PROFILE_COLDSTART);
        boolean z = create.getBoolean("FirstLaunch", true);
        String string = create.getString("AppVersionName", "2.2.0.0");
        String versionName = this.environmentService.getVersionName();
        if (z) {
            this.latencyInfra.mark(LatencyMarker.APP_INSTALL_LATENCY, LatencyNamespace.PROFILE_COLDSTART);
            create.edit().set("FirstLaunch", false).set("AppVersionName", versionName).commit();
        } else if (!TextUtils.equals(string, versionName)) {
            this.latencyInfra.mark(LatencyMarker.APP_UPDATE_LATENCY, LatencyNamespace.PROFILE_COLDSTART);
            create.edit().set("AppVersionName", versionName).commit();
        } else {
            this.latencyInfra.mark(LatencyMarker.COOL_START_LATENCY, LatencyNamespace.PROFILE_COLDSTART);
        }
        this.latencyInfra.blockNamespace(LatencyNamespace.PROFILE_COLDSTART);
    }

    @Override // com.amazon.alexa.redesign.HomeContract.LatencyInteractor
    public void recordProfileEvent(String str) {
        this.latencyInfra.recordTimeline(TimelineLatencyAction.RECORD_EVENT_IN_TIMELINE, buildTimelineEvent(str, PROFILE_NAMESPACE));
        this.latencyInfra.recordTimeline(TimelineLatencyAction.END_TIMELINE, buildTimelineEvent(str, PROFILE_NAMESPACE));
    }

    @Override // com.amazon.alexa.redesign.HomeContract.LatencyInteractor
    public void recordWarmStartInAppLatency() {
        this.latencyInfra.recordTimeline(TimelineLatencyAction.RECORD_EVENT_IN_TIMELINE, this.inAppLatencyWarmStart);
        this.latencyInfra.recordTimeline(TimelineLatencyAction.END_TIMELINE, this.inAppLatencyWarmStart);
    }
}
