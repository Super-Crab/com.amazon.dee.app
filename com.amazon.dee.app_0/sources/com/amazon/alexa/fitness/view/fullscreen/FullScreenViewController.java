package com.amazon.alexa.fitness.view.fullscreen;

import amazon.speech.simclient.settings.Settings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.afx.FitnessRoutesKt;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.afx.SensorUnavailabilityReason;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.ActivityViewMetrics;
import com.amazon.alexa.fitness.utils.AfitsDataHelper;
import com.amazon.alexa.fitness.utils.DirectedIDBackfillService;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.FullScreenUpdaterUtil;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.fitness.view.message.CustomToast;
import com.amazon.alexa.fitness.view.startTab.ActiveView;
import com.amazon.alexa.fitness.view.startTab.LandingView;
import com.amazon.alexa.fitness.view.startTab.StartTabContent;
import com.amazon.alexa.fitness.view.startTab.StartTabContentKt;
import com.amazon.alexa.fitness.view.startTab.SummaryView;
import com.amazon.alexa.fitness.view.workoutTab.DetailedView;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FullScreenViewController.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009f\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0011\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0019H\u0016J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u000fH\u0002J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\"H\u0016J\u0012\u0010)\u001a\u00020\u00192\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0012\u0010,\u001a\u00020\u00192\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0010\u0010-\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\"H\u0016J\u0012\u0010.\u001a\u00020\u00192\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\u0012\u00101\u001a\u00020\u00192\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u001c\u00102\u001a\u00020\u00192\b\u00103\u001a\u0004\u0018\u0001042\b\u00105\u001a\u0004\u0018\u000106H\u0016J\u0018\u00107\u001a\u00020\u00192\u0006\u00108\u001a\u0002042\u0006\u00109\u001a\u000200H\u0016J\b\u0010:\u001a\u00020\u0019H\u0002J\u0010\u0010;\u001a\u00020\u00192\u0006\u0010<\u001a\u00020=H\u0016J\u0010\u0010>\u001a\u00020\u00192\u0006\u0010?\u001a\u00020@H\u0016J\"\u0010A\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\"2\u0006\u0010B\u001a\u00020\u000f2\b\b\u0002\u0010C\u001a\u00020\u000fH\u0016J\b\u0010D\u001a\u00020\u0019H\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "Lcom/amazon/alexa/viewmanagement/api/ViewController;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestratorDelegate;", "routeContext", "Lcom/amazon/alexa/routing/api/RouteContext;", "afitsDataHelper", "Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "featureService", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "directedIDBackfillService", "Lcom/amazon/alexa/fitness/utils/DirectedIDBackfillService;", "(Lcom/amazon/alexa/routing/api/RouteContext;Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;Lcom/amazon/alexa/fitness/api/afx/FeatureService;Lcom/amazon/alexa/fitness/utils/DirectedIDBackfillService;)V", "activeView", "Lcom/amazon/alexa/fitness/view/startTab/ActiveView;", "isStagingViewSetup", "", "providersChangedReceiver", "com/amazon/alexa/fitness/view/fullscreen/FullScreenViewController$providersChangedReceiver$1", "Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController$providersChangedReceiver$1;", "showUnavailableMessage", "summaryView", "Lcom/amazon/alexa/fitness/view/startTab/SummaryView;", "tabController", "Lcom/amazon/alexa/fitness/view/fullscreen/FitnessTabController;", "displayErrorToast", "", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/alexa/fitness/api/afx/SensorUnavailabilityReason;", "hideBanner", "inflateAndSetupViews", "currentState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "isUserOnStartTab", "makeView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "viewGroup", "Landroid/view/ViewGroup;", "onAttach", "view", "onCreate", "context", "Landroid/content/Context;", "onDestroy", "onDetach", "onMetricsUpdated", "metrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "onResume", "onSessionChanged", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "error", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "onSessionEnded", "endedSession", "finalMetrics", "refreshUIForRouteMappingIfApplicable", "sensorAvailabilityChanged", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "showBanner", "messageId", "", "showMapView", "shouldShowMapView", "shouldShowRouteMapping", "update", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class FullScreenViewController implements ViewController, FitnessSessionOrchestratorDelegate {
    private final ActiveView activeView;
    private final AfitsDataHelper afitsDataHelper;
    private final DirectedIDBackfillService directedIDBackfillService;
    private final FeatureService featureService;
    private boolean isStagingViewSetup;
    private final FullScreenViewController$providersChangedReceiver$1 providersChangedReceiver;
    private final RouteContext routeContext;
    private boolean showUnavailableMessage;
    private final SummaryView summaryView;
    private final FitnessTabController tabController;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FitnessSessionState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[FitnessSessionState.ACTIVE.ordinal()] = 1;
            $EnumSwitchMapping$0[FitnessSessionState.PAUSED.ordinal()] = 2;
            $EnumSwitchMapping$0[FitnessSessionState.STOPPING.ordinal()] = 3;
            $EnumSwitchMapping$0[FitnessSessionState.IDLE.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[FitnessSessionState.values().length];
            $EnumSwitchMapping$1[FitnessSessionState.ACTIVE.ordinal()] = 1;
            $EnumSwitchMapping$1[FitnessSessionState.PAUSED.ordinal()] = 2;
            $EnumSwitchMapping$1[FitnessSessionState.IDLE.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[SensorUnavailabilityReason.values().length];
            $EnumSwitchMapping$2[SensorUnavailabilityReason.FwIsNotFitnessEnabled.ordinal()] = 1;
            $EnumSwitchMapping$2[SensorUnavailabilityReason.Disconnected.ordinal()] = 2;
            $EnumSwitchMapping$2[SensorUnavailabilityReason.EarBudsOutOfEar.ordinal()] = 3;
        }
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [com.amazon.alexa.fitness.view.fullscreen.FullScreenViewController$providersChangedReceiver$1] */
    public FullScreenViewController(@NotNull RouteContext routeContext, @NotNull AfitsDataHelper afitsDataHelper, @NotNull FeatureService featureService, @NotNull DirectedIDBackfillService directedIDBackfillService) {
        Intrinsics.checkParameterIsNotNull(routeContext, "routeContext");
        Intrinsics.checkParameterIsNotNull(afitsDataHelper, "afitsDataHelper");
        Intrinsics.checkParameterIsNotNull(featureService, "featureService");
        Intrinsics.checkParameterIsNotNull(directedIDBackfillService, "directedIDBackfillService");
        this.routeContext = routeContext;
        this.afitsDataHelper = afitsDataHelper;
        this.featureService = featureService;
        this.directedIDBackfillService = directedIDBackfillService;
        this.showUnavailableMessage = true;
        this.tabController = new FitnessTabController(this.afitsDataHelper, this);
        this.activeView = new ActiveView(this);
        this.summaryView = new SummaryView(this);
        this.providersChangedReceiver = new BroadcastReceiver() { // from class: com.amazon.alexa.fitness.view.fullscreen.FullScreenViewController$providersChangedReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(@Nullable Context context, @Nullable Intent intent) {
                FullScreenViewController.this.refreshUIForRouteMappingIfApplicable();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayErrorToast(SensorUnavailabilityReason sensorUnavailabilityReason) {
        if (this.showUnavailableMessage) {
            this.showUnavailableMessage = false;
            int i = WhenMappings.$EnumSwitchMapping$2[sensorUnavailabilityReason.ordinal()];
            if (i == 1) {
                CustomToast.Companion.showNotification$default(CustomToast.Companion, R.string.buds_fw_update_required, null, 2, null);
            } else if (i != 2) {
                if (i != 3) {
                    return;
                }
                showBanner(R.string.buds_out_of_ears_msg);
            } else if (!isUserOnStartTab()) {
            } else {
                showBanner(R.string.buds_disconnected_msg);
            }
        }
    }

    private final void inflateAndSetupViews(FitnessSessionState fitnessSessionState) {
        int i = WhenMappings.$EnumSwitchMapping$0[fitnessSessionState.ordinal()];
        if (i == 1 || i == 2) {
            this.activeView.inflateAndSetupInProgressView(fitnessSessionState);
            FullScreenUpdaterUtil.Companion.initFitnessData(true);
        } else if (i != 3 && i != 4) {
        } else {
            this.summaryView.updateToSummaryView();
        }
    }

    private final boolean isUserOnStartTab() {
        View fullScreenView;
        ViewGroup viewGroup;
        FitnessSessionState currentState;
        if (this.featureService.isWorkoutHistoryTabEnabled()) {
            Session session = FullScreenUtil.Companion.getSession();
            if ((session != null && (currentState = SessionDataModelsKt.currentState(session)) != null && currentState.isSessionInProgress()) || (fullScreenView = FullScreenUtil.Companion.getFullScreenView()) == null) {
                return false;
            }
            ViewGroup viewGroup2 = (ViewGroup) fullScreenView.findViewById(R.id.landing_page_tab);
            return (viewGroup2 != null && viewGroup2.getVisibility() == 0) || ((viewGroup = (ViewGroup) fullScreenView.findViewById(R.id.landing_page_big_font_tab)) != null && viewGroup.getVisibility() == 0);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshUIForRouteMappingIfApplicable() {
        if (isUserOnStartTab()) {
            Log.i("AFX-FullScreenView", "refreshing ui for route mapping");
            View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
            if (fullScreenView == null) {
                return;
            }
            StartTabContent.Companion.refreshUIForRouteMapping(fullScreenView);
        }
    }

    public static /* synthetic */ void showMapView$default(FullScreenViewController fullScreenViewController, View view, boolean z, boolean z2, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                z2 = false;
            }
            fullScreenViewController.showMapView(view, z, z2);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showMapView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void update() {
        Session session = FullScreenUtil.Companion.getSession();
        FitnessSessionState fitnessSessionState = null;
        FitnessSessionState currentState = session != null ? SessionDataModelsKt.currentState(session) : null;
        String str = "update(): session = " + currentState;
        if (currentState == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[currentState.ordinal()];
        if (i == 1) {
            this.isStagingViewSetup = false;
            this.activeView.updateToActiveView();
        } else if (i == 2) {
            this.isStagingViewSetup = false;
            this.activeView.updateToPausedView();
        } else if (i != 3) {
        } else {
            Session session2 = FullScreenUtil.Companion.getSession();
            if (session2 != null) {
                fitnessSessionState = SessionDataModelsKt.previousState(session2);
            }
            if (fitnessSessionState != FitnessSessionState.STOPPING) {
                return;
            }
            this.summaryView.updateToSummaryView();
            this.isStagingViewSetup = false;
        }
    }

    public void hideBanner() {
        TextView textView;
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView == null || (textView = (TextView) fullScreenView.findViewById(R.id.banner)) == null) {
            return;
        }
        textView.setVisibility(8);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NotNull
    public View makeView(@NotNull LayoutInflater inflater, @NotNull ViewGroup viewGroup) {
        View landingPageView;
        FitnessSessionState currentState;
        FitnessSessionState currentState2;
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Intrinsics.checkParameterIsNotNull(viewGroup, "viewGroup");
        StringBuilder sb = new StringBuilder();
        sb.append("state = ");
        Session session = FullScreenUtil.Companion.getSession();
        sb.append(session != null ? SessionDataModelsKt.currentState(session) : null);
        Log.i("AFX-FullScreenView", sb.toString());
        MetricHelperKt.recordUserInteractionEvent(FullScreenUtil.Companion.getMetricHelper(), ActivityViewMetrics.Companion.getVIEW(), EventType.VIEW);
        ConstraintLayout constraintLayout = new ConstraintLayout(viewGroup.getContext());
        constraintLayout.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        FullScreenUtil.Companion.setContainer(constraintLayout);
        if (FullScreenUtil.Companion.getFeatureService().isWorkoutHistoryTabEnabled()) {
            this.isStagingViewSetup = true;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("routing to ");
            Route route = this.routeContext.getRoute();
            Intrinsics.checkExpressionValueIsNotNull(route, "routeContext.route");
            outline107.append(route.getName());
            outline107.toString();
            this.directedIDBackfillService.processBackfillStatus();
            if (this.routeContext.getRoute().is(FitnessRoutesKt.FITNESS_DETAILED_ROUTE_SESSION_ID)) {
                DetailedView detailedView = new DetailedView(this.afitsDataHelper);
                Object obj = this.routeContext.get("id");
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                landingPageView = detailedView.populateDetailViewContent((String) obj);
            } else if (this.routeContext.getRoute().is("fitness/track")) {
                Session session2 = FullScreenUtil.Companion.getSession();
                if (session2 != null && (currentState2 = SessionDataModelsKt.currentState(session2)) != null && currentState2.isSessionInProgress()) {
                    this.isStagingViewSetup = false;
                    landingPageView = this.activeView.getInProgressView();
                } else {
                    landingPageView = this.tabController.getTabViewLayout(TabPosition.TRACK);
                    showMapView(landingPageView, true, true);
                }
            } else {
                landingPageView = this.tabController.getTabViewLayout(TabPosition.HISTORY);
            }
        } else {
            Session session3 = FullScreenUtil.Companion.getSession();
            if (session3 != null && (currentState = SessionDataModelsKt.currentState(session3)) != null && currentState.isSessionInProgress()) {
                this.isStagingViewSetup = false;
                landingPageView = this.activeView.getInProgressView();
            } else {
                this.isStagingViewSetup = true;
                landingPageView = LandingView.Companion.getLandingPageView();
                showMapView$default(this, landingPageView, true, false, 4, null);
            }
        }
        FullScreenUtil.Companion.setFullScreenView(landingPageView);
        constraintLayout.addView(landingPageView);
        return constraintLayout;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NotNull View view) {
        FitnessSessionState fitnessSessionState;
        Intrinsics.checkParameterIsNotNull(view, "view");
        ViewGroup viewGroup = (ViewGroup) (!(view instanceof ViewGroup) ? null : view);
        if (viewGroup != null) {
            if (!Intrinsics.areEqual(FullScreenUtil.Companion.getContainer(), viewGroup)) {
                Log.e("AFX-FullScreenView", "unexpected view received in onAttach");
                FullScreenUtil.Companion.setContainer(viewGroup);
            }
            if (viewGroup.getChildCount() > 0) {
                FullScreenUtil.Companion.setFullScreenView(viewGroup.getChildAt(0));
            }
        } else {
            Log.e("AFX-FullScreenView", "view received in onAttach is not a ViewGroup!");
            FullScreenUtil.Companion.setFullScreenView(view);
        }
        view.getContext().registerReceiver(this.providersChangedReceiver, new IntentFilter("android.location.PROVIDERS_CHANGED"));
        if (this.routeContext.getRoute().is("fitness/track")) {
            this.showUnavailableMessage = true;
            FullScreenUtil.Companion.getFitnessSessionOrchestrator().registerDelegate(new WeakReference<>(this));
            Session session = FullScreenUtil.Companion.getSession();
            if (session == null || (fitnessSessionState = SessionDataModelsKt.currentState(session)) == null) {
                fitnessSessionState = FitnessSessionState.IDLE;
            }
            boolean isSessionInProgress = fitnessSessionState.isSessionInProgress();
            if ((isSessionInProgress && this.isStagingViewSetup) || (!isSessionInProgress && !this.isStagingViewSetup)) {
                Log.i("AFX-FullScreenView", "updating to " + fitnessSessionState);
                inflateAndSetupViews(fitnessSessionState);
                return;
            }
            Log.i("AFX-FullScreenView", "setting up for " + fitnessSessionState);
            FullScreenUpdaterUtil.Companion.initFitnessData(isSessionInProgress);
            this.activeView.setupViews(fitnessSessionState);
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@Nullable Context context) {
        if (this.routeContext.getRoute().is("fitness/track")) {
            FullScreenUtil.Companion.getFitnessSessionOrchestrator().registerDelegate(new WeakReference<>(this));
        }
        StartTabContent.Companion.setFullScreenViewController(this);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDestroy(@Nullable Context context) {
        FullScreenUtil.Companion.getFitnessSessionOrchestrator().removeDelegate(new WeakReference<>(this));
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.showUnavailableMessage = false;
        view.getContext().unregisterReceiver(this.providersChangedReceiver);
        FullScreenUtil.Companion.getFitnessSessionOrchestrator().removeDelegate(new WeakReference<>(this));
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onMetricsUpdated(@Nullable FitnessMetrics fitnessMetrics) {
        FullScreenUpdaterUtil.Companion.setFitnessMetrics(fitnessMetrics);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onResume(@Nullable Context context) {
        refreshUIForRouteMappingIfApplicable();
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionChanged(@Nullable Session session, @Nullable FitnessSessionOrchestrator.CommandProcessingError commandProcessingError) {
        FitnessSessionState currentState;
        GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("session changed: "), (session == null || (currentState = SessionDataModelsKt.currentState(session)) == null) ? null : currentState.name(), "AFX-FullScreenView");
        FullScreenUtil.Companion.setSession(session);
        FullScreenUtil.Companion.getMainHandler().post(new Runnable() { // from class: com.amazon.alexa.fitness.view.fullscreen.FullScreenViewController$onSessionChanged$1
            @Override // java.lang.Runnable
            public final void run() {
                FullScreenViewController.this.update();
            }
        });
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionEnded(@NotNull Session endedSession, @NotNull FitnessMetrics finalMetrics) {
        Intrinsics.checkParameterIsNotNull(endedSession, "endedSession");
        Intrinsics.checkParameterIsNotNull(finalMetrics, "finalMetrics");
        FullScreenUtil.Companion.setSession(endedSession);
        FullScreenUpdaterUtil.Companion.setFitnessMetrics(finalMetrics);
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void sensorAvailabilityChanged(@NotNull final SensorAvailability availability) {
        Intrinsics.checkParameterIsNotNull(availability, "availability");
        FullScreenUtil.Companion.getMainHandler().post(new Runnable() { // from class: com.amazon.alexa.fitness.view.fullscreen.FullScreenViewController$sensorAvailabilityChanged$1
            @Override // java.lang.Runnable
            public final void run() {
                ActiveView activeView;
                ActiveView activeView2;
                if (!FullScreenUtil.Companion.getFitnessSessionOrchestrator().allRequiredSensorsAvailable()) {
                    activeView2 = FullScreenViewController.this.activeView;
                    activeView2.changeButtonVisibility(false);
                    SensorAvailability sensorAvailability = availability;
                    if (!(sensorAvailability instanceof SensorAvailability.Unavailable)) {
                        return;
                    }
                    FullScreenViewController.this.displayErrorToast(((SensorAvailability.Unavailable) sensorAvailability).getReason());
                    return;
                }
                FullScreenViewController.this.showUnavailableMessage = true;
                activeView = FullScreenViewController.this.activeView;
                activeView.changeButtonVisibility(true);
                FullScreenViewController.this.hideBanner();
            }
        });
    }

    public void showBanner(int i) {
        TextView textView;
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView == null || (textView = (TextView) fullScreenView.findViewById(R.id.banner)) == null) {
            return;
        }
        textView.setVisibility(0);
        textView.setText(textView.getContext().getString(i));
    }

    public void showMapView(@NotNull View view, boolean z, boolean z2) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        Intrinsics.checkParameterIsNotNull(view, "view");
        View findViewById = view.findViewById(R.id.map_view);
        int i = 0;
        if (findViewById != null) {
            findViewById.setVisibility((!z || !StartTabContentKt.isRouteMappingOn() || !this.featureService.isMapViewEnabled()) ? 8 : 0);
        }
        View findViewById2 = view.findViewById(R.id.btn_recenter);
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
        View findViewById3 = view.findViewById(R.id.btn_routeMapping);
        if (findViewById3 != null) {
            if (!z2 || !this.featureService.isMapViewEnabled() || (((viewGroup = (ViewGroup) view.findViewById(R.id.landing_page_tab)) == null || viewGroup.getVisibility() != 0) && ((viewGroup2 = (ViewGroup) view.findViewById(R.id.landing_page_big_font_tab)) == null || viewGroup2.getVisibility() != 0))) {
                i = 8;
            }
            findViewById3.setVisibility(i);
        }
    }
}
