package com.amazon.alexa.biloba.view.dashboard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.CareDashboardBinding;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.utils.CommsHelper;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.utils.RemoteAssistHelper;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.biloba.view.common.recycler.RecyclerViewAdapter;
import com.amazon.alexa.growth.CoachMarkFactory;
import com.amazon.alexa.growth.coachmark.CoachMark;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class BilobaDashboardView extends BilobaViewController {
    private static final int MAX_CARDS_TO_SHOW = 2;
    private static final String SHARED_PREF_FILE = "com.amazon.alexa.biloba.view.Dashboard";
    private static final String TAG = "BilobaDashboardView";
    private CareDashboardBinding binding;
    @Inject
    Lazy<CoachMarkFactory> coachMarkFactory;
    @Inject
    Lazy<CommsHelper> commsHelper;
    private final Context context;
    private RecyclerViewAdapter dashboardActivityAdapter;
    private RecyclerView dashboardActivityView;
    private RecyclerViewAdapter dashboardCardsAdapter;
    private RecyclerView dashboardCardsRecyclerView;
    private final MobilyticsMetricsTimer dashboardInitializationTimer;
    private View dashboardView;
    @Inject
    Lazy<RemoteAssistHelper> remoteAssistHelper;
    private String rmRoutingContextJson = "";
    @Inject
    Lazy<RoutingService> routingService;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewSwitcher switcher;
    @Inject
    Lazy<UrlHelper> urlHelper;
    private final BilobaDashboardViewModel viewModel;

    public BilobaDashboardView(Context context, Bundle bundle) {
        long time = new Date().getTime();
        BilobaDependencies.inject(this);
        this.context = context;
        this.viewModel = new BilobaDashboardViewModel(context.getSharedPreferences(SHARED_PREF_FILE, 0));
        this.viewModel.create(bundle);
        this.dashboardInitializationTimer = this.viewModel.startTimer("DashboardViewBuilt.Time", time);
    }

    public void clearTTCFOnError(Throwable th) {
        if (th != null) {
            this.viewModel.resetTTCFTimers();
        }
    }

    private LinearLayoutManager getLinearLayoutManager(final String str) {
        return new LinearLayoutManager(this.context, 1, false) { // from class: com.amazon.alexa.biloba.view.dashboard.BilobaDashboardView.1
            {
                BilobaDashboardView.this = this;
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onLayoutCompleted(RecyclerView.State state) {
                super.onLayoutCompleted(state);
                BilobaDashboardView.this.viewModel.stopRecordingTTCF(str);
            }
        };
    }

    private void showCoachMark(Context context, View view, @StringRes int i) {
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        CoachMark coachMark = this.coachMarkFactory.mo358get().getCoachMark(view);
        CareActor value = this.viewModel.getCareContact().getValue();
        coachMark.setText(String.format(context.getResources().getString(i), TextUtils.isEmpty(CareActorUtil.getDisplayName(value)) ? context.getString(R.string.your_loved_one_lowercase) : CareActorUtil.getDisplayName(value)));
        coachMark.show();
    }

    public void updateCards(List<BaseRecyclerItem> list) {
        LogUtils.i(TAG, "updateCards for dashboard");
        this.dashboardCardsAdapter = new RecyclerViewAdapter();
        this.dashboardCardsRecyclerView.setAdapter(this.dashboardCardsAdapter);
        List<BaseRecyclerItem> subList = list.subList(0, Math.min(list.size(), 2));
        this.dashboardCardsAdapter.updateItems(subList);
        this.dashboardCardsAdapter.notifyDataSetChanged();
        recordCardsViewMetric(subList, MetricsConstants.UserInteractionMetrics.DASHBOARD_VIEW);
    }

    public void updateListView(List<BaseRecyclerItem> list) {
        LogUtils.i(TAG, "updateActivitiesView for dashboard");
        this.dashboardActivityAdapter = new RecyclerViewAdapter();
        this.dashboardActivityView.setAdapter(this.dashboardActivityAdapter);
        this.dashboardActivityAdapter.updateItems(list);
        this.dashboardActivityAdapter.notifyDataSetChanged();
    }

    public void updateRefreshing(Boolean bool) {
        this.swipeRefreshLayout.setRefreshing(bool.booleanValue());
    }

    public Drawable bottomLeftButtonIconSrc() {
        return this.context.getResources().getDrawable(this.viewModel.isCareGiverMultiCG() ? R.drawable.ic_mosaic_settings : R.drawable.ic_mosaic_call_emergency, this.context.getTheme());
    }

    public void bottomLeftButtonOnClick(View view) {
        if (this.viewModel.isCareGiverMultiCG()) {
            navigateToSettingsPage(view);
        } else {
            navigateToEmergencyContactsPage(view);
        }
    }

    public String bottomLeftButtonText() {
        return this.context.getResources().getString(this.viewModel.isCareGiverMultiCG() ? R.string.care_settings : R.string.emergency_settings_button);
    }

    public String getCommsHeader() {
        if (this.viewModel.isCareRecipientMultiCG()) {
            String string = this.context.getResources().getString(R.string.comms_panel_header_cr_multi_cg);
            BilobaDashboardViewModel bilobaDashboardViewModel = this.viewModel;
            return String.format(string, bilobaDashboardViewModel.getDisplayName(bilobaDashboardViewModel.getCareRecipient()));
        }
        String string2 = this.context.getResources().getString(R.string.comms_panel_header_cg_multi_cg);
        BilobaDashboardViewModel bilobaDashboardViewModel2 = this.viewModel;
        return String.format(string2, bilobaDashboardViewModel2.getDisplayName(bilobaDashboardViewModel2.getCareContact()));
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getString(R.string.dashboard_title);
    }

    public /* synthetic */ void lambda$makeView$0$BilobaDashboardView() {
        this.viewModel.sendRequest();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.binding = (CareDashboardBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(viewGroup.getContext())), R.layout.care_dashboard, viewGroup, false);
        this.binding.setLifecycleOwner((LifecycleOwner) viewGroup.getContext());
        this.binding.setViewmodel(this.viewModel);
        this.binding.setHandler(this);
        this.dashboardView = this.binding.getRoot();
        if (this.dashboardView == null) {
            LogUtils.e(TAG, "Couldn't inflate dashboard view");
            return this.dashboardView;
        }
        MobilyticsMetricsTimer mobilyticsMetricsTimer = this.dashboardInitializationTimer;
        if (mobilyticsMetricsTimer != null) {
            this.viewModel.finishTimer(mobilyticsMetricsTimer);
        }
        this.viewModel.stopRecordingTTCF(MetricsConstants.TTCFMetrics.BILOBA_DASHBOARD);
        this.swipeRefreshLayout = this.binding.dashboardRefreshContainer;
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardView$9zcEfgsXIooO_mm-qL9igxDPL8I
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                BilobaDashboardView.this.lambda$makeView$0$BilobaDashboardView();
            }
        });
        this.swipeRefreshLayout.setColorSchemeColors(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicBackground));
        this.switcher = this.binding.switcher;
        Animation loadAnimation = AnimationUtils.loadAnimation(this.context, 17432578);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.context, 17432579);
        this.switcher.setInAnimation(loadAnimation);
        this.switcher.setOutAnimation(loadAnimation2);
        this.dashboardActivityView = this.binding.dashboardActivitiesListView;
        if (this.dashboardActivityView != null) {
            LinearLayoutManager linearLayoutManager = getLinearLayoutManager(MetricsConstants.TTCFMetrics.TODAY_ACTIVITY_RENDER);
            LogUtils.d(TAG, "setting layout manage to  activityRecyclerView");
            this.dashboardActivityView.setLayoutManager(linearLayoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.context, linearLayoutManager.getOrientation());
            LogUtils.d(TAG, "adding decoration to activityRecyclerView");
            this.dashboardActivityView.addItemDecoration(dividerItemDecoration);
        }
        this.dashboardCardsRecyclerView = this.binding.dashboardCards;
        RecyclerView recyclerView = this.dashboardCardsRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(getLinearLayoutManager(MetricsConstants.TTCFMetrics.DASHBOARD_CARDS_RENDER));
        }
        registerViewAttributes((LinearLayout) this.dashboardView.findViewById(R.id.no_connection_banner), this.viewModel);
        return this.dashboardView;
    }

    public void navigateToAlertsPage(View view) {
        BilobaRouteUtil.routeToWithAddToBackStack(this.routingService.mo358get(), Routes.BILOBA_ALERTS_MANAGE);
    }

    public void navigateToAllActivitiesPage(View view) {
        BilobaRouteUtil.routeToWithAddToBackStack(this.routingService.mo358get(), Routes.BILOBA_ACTIVITY_RECENT);
    }

    public void navigateToEmergencyContactsPage(View view) {
        String str;
        String str2;
        if (this.viewModel.shouldShowCarePlusEmergencyView()) {
            str = Routes.BILOBA_EMERGENCY;
            str2 = MetricsConstants.UserInteractionMetrics.ES_EMERGENCY_VIEW_CARE_PLUS;
        } else {
            str = Routes.BILOBA_EMERGENCY_CONTACT;
            str2 = MetricsConstants.UserInteractionMetrics.ES_EMERGENCY_VIEW;
        }
        recordClickMetric(str2, MetricsConstants.CLICK_EVENT);
        BilobaRouteUtil.routeToWithAddToBackStack(this.routingService.mo358get(), str);
    }

    public void navigateToSettingsPage(View view) {
        BilobaRouteUtil.routeToWithAddToBackStack(this.routingService.mo358get(), Routes.BILOBA_PROFILE_SETTINGS);
    }

    public void navigateToTimeZonePicker(View view) {
        BilobaRouteUtil.routeToWithAddToBackStack(this.routingService.mo358get(), Routes.TIMEZONE_PICKER);
    }

    public void navigateToTipsPage(View view) {
        BilobaRouteUtil.routeToWithAddToBackStack(this.routingService.mo358get(), Routes.BILOBA_TIPS_PAGE);
    }

    public void onAssistClicked(View view) {
        LogUtils.d(TAG, "Remote Assist Button clicked from Dashboard");
        this.remoteAssistHelper.mo358get().startRemoteAssist(this.context);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric(MetricsConstants.UserInteractionMetrics.DASHBOARD_VIEW, MetricsConstants.ENTER_EVENT);
        this.viewModel.create(null);
        subscribeToNetworkChange();
        this.viewModel.getTodaysActivities().observe((LifecycleOwner) this.context, new $$Lambda$BilobaDashboardView$rKurQ_gVxlIGx7r0BYNvl0lLhc(this));
        this.viewModel.getLiveDashboardCards().observe((LifecycleOwner) this.context, new $$Lambda$BilobaDashboardView$YfaclD3wm8fzRL2DWklzBKatHJs(this));
        this.viewModel.getIsLoading().observe((LifecycleOwner) this.context, new $$Lambda$BilobaDashboardView$P01iZ5ifwk1QDur55W5R6h7IA(this));
        this.viewModel.getIsRemoteManagementEnabled().observe((LifecycleOwner) this.context, new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$hwugYTjZf7C_2a5OhjymJTCYqc0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardView.this.showRemoteManagementCoachMark(((Boolean) obj).booleanValue());
            }
        });
        this.viewModel.getError().observe((LifecycleOwner) this.context, new $$Lambda$BilobaDashboardView$ZP2REWhBvtESjXRhOEDRiRWyIwY(this));
        this.viewModel.sendRequest();
    }

    public void onCallClicked(View view) {
        LogUtils.d(TAG, "Call Button clicked from Dashboard");
        this.commsHelper.mo358get().onCallClicked(this.context);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
        this.viewModel.getTodaysActivities().removeObserver(new $$Lambda$BilobaDashboardView$rKurQ_gVxlIGx7r0BYNvl0lLhc(this));
        this.viewModel.getLiveDashboardCards().removeObserver(new $$Lambda$BilobaDashboardView$YfaclD3wm8fzRL2DWklzBKatHJs(this));
        this.viewModel.getIsLoading().removeObserver(new $$Lambda$BilobaDashboardView$P01iZ5ifwk1QDur55W5R6h7IA(this));
        this.viewModel.getError().removeObserver(new $$Lambda$BilobaDashboardView$ZP2REWhBvtESjXRhOEDRiRWyIwY(this));
        this.viewModel.destroy();
        recordViewMetric(MetricsConstants.UserInteractionMetrics.DASHBOARD_VIEW, MetricsConstants.EXIT_EVENT);
    }

    public void onDropinClicked(View view) {
        LogUtils.d(TAG, "Drop-in Button clicked from Dashboard");
        this.commsHelper.mo358get().onDropinClicked(this.context);
    }

    public void onMessageClicked(View view) {
        LogUtils.d(TAG, "Share Button clicked from Dashboard");
        this.commsHelper.mo358get().onMessageClicked(this.context);
    }

    @VisibleForTesting
    public void showRemoteManagementCoachMark(boolean z) {
        if (z) {
            showCoachMark(this.context, this.binding.assistButton, R.string.remote_management_coachmark);
        }
    }

    public void startCareAdminWebview(View view) {
        recordClickMetric("Dashboard.AdminMemberProfileWebview", MetricsConstants.CLICK_EVENT);
        AndroidUtils.startWebview(this.urlHelper.mo358get().getAdminProfileUrl(this.context.getResources().getConfiguration()), this.context);
    }

    public void startCircleMembersWebview(View view) {
        recordClickMetric("Dashboard.YourCircleWebview", MetricsConstants.CLICK_EVENT);
        AndroidUtils.startWebview(this.urlHelper.mo358get().getUrl("/groups/care/dashboard", this.context.getResources().getConfiguration()), this.context);
    }

    public Drawable topLeftButtonIconSrc() {
        return this.context.getResources().getDrawable(this.viewModel.shouldShowAlertButton() ? R.drawable.ic_mosaic_notifications : R.drawable.ic_mosaic_flagged, this.context.getTheme());
    }

    public void topLeftButtonOnClick(View view) {
        if (this.viewModel.shouldShowAlertButton()) {
            navigateToAlertsPage(view);
        } else {
            navigateToTipsPage(view);
        }
    }

    public String topLeftButtonText() {
        return this.context.getResources().getString(this.viewModel.shouldShowAlertButton() ? R.string.alert_settings : R.string.tips);
    }

    public Drawable topRightButtonIconSrc() {
        return this.context.getResources().getDrawable(this.viewModel.isCareRecipientMultiCG() ? R.drawable.ic_mosaic_settings : R.drawable.ic_mosaic_flagged, this.context.getTheme());
    }

    public void topRightButtonOnClick(View view) {
        if (this.viewModel.isCareRecipientMultiCG()) {
            navigateToSettingsPage(view);
        } else {
            navigateToTipsPage(view);
        }
    }

    public String topRightButtonText() {
        return this.context.getResources().getString(this.viewModel.isCareRecipientMultiCG() ? R.string.care_settings : R.string.tips);
    }
}
