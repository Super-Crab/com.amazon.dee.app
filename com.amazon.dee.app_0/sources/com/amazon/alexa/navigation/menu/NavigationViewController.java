package com.amazon.alexa.navigation.menu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.blueprints.api.BlueprintsEndpoint;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.growth.CoachMarkFactory;
import com.amazon.alexa.growth.coachmark.CoachMark;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.navigation.menu.service.AppOnlyService;
import com.amazon.alexa.navigation.menu.service.FeatureCheckService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import com.amazon.alexa.viewmanagement.api.ViewController;
import javax.inject.Provider;
import rx.Subscription;
import rx.functions.Action1;
/* loaded from: classes9.dex */
public class NavigationViewController implements ViewController {
    private static final String FEATURE_NAME = "more-menu";
    @VisibleForTesting
    AppOnlyService appOnlyService;
    private Provider<BlueprintsEndpoint> blueprintsEndpoint;
    @VisibleForTesting
    BroadcastReceiver broadcastReceiver;
    private Provider<CoachMarkFactory> coachMarkFactory;
    private Context context;
    private Provider<DeviceInformation> deviceInformation;
    private Provider<EnvironmentService> environmentService;
    @VisibleForTesting
    FeatureCheckService featureCheckService;
    private Provider<FeatureServiceV2> featureServiceV2;
    private Provider<IdentityService> identityService;
    @VisibleForTesting
    IntentFilter intentFilter;
    @VisibleForTesting
    float maxWidth;
    private Provider<Mobilytics> mobilyticsProvider;
    @VisibleForTesting
    NavigationAdapter navigationAdapter;
    private Provider<NetworkService> networkService;
    @VisibleForTesting
    Subscription networkServiceSubscription;
    private View offlineBanner;
    @VisibleForTesting
    RecyclerView recyclerView;
    private Provider<RoutingService> routingService;
    private Provider<TTCFCheckpoint> ttcfCheckpoint;
    @VisibleForTesting
    ViewTreeObserver.OnGlobalLayoutListener viewChangeListener;

    public NavigationViewController() {
        this.maxWidth = 600.0f;
    }

    private void setThemeForOfflineBanner() {
        ThemeUtil.setTheme(this.context);
        this.offlineBanner.setBackgroundColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
        this.offlineBanner.getBackground().setAlpha(50);
        ((TextView) this.offlineBanner.findViewById(R.id.banner_text)).setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
    }

    private void setUpOfflineBanner(View view) {
        this.offlineBanner = view.findViewById(R.id.offline_banner);
        if (this.offlineBanner != null) {
            setThemeForOfflineBanner();
            if (!this.networkService.mo10268get().isConnected()) {
                this.offlineBanner.setVisibility(0);
            }
            this.networkServiceSubscription = this.networkService.mo10268get().onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.alexa.navigation.menu.-$$Lambda$eAnrgr1APYtvJ4pHOry8JdF_0FY
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    NavigationViewController.this.onNetworkChanged(((Boolean) obj).booleanValue());
                }
            });
        }
    }

    @VisibleForTesting
    void calculateWidth(Context context, View view) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float f = displayMetrics.widthPixels / displayMetrics.density;
        float f2 = this.maxWidth;
        if (f >= f2) {
            view.setLayoutParams(new LinearLayout.LayoutParams((int) (f2 * context.getResources().getDisplayMetrics().density), -2));
        } else {
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public View makeView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.navigation_menu, viewGroup, false);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.menu_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        this.navigationAdapter = new NavigationAdapter(new MenuListing(this.context, this.identityService, this.mobilyticsProvider, this.routingService, this.blueprintsEndpoint, this.environmentService, this.featureServiceV2).getMenu());
        this.recyclerView.setAdapter(this.navigationAdapter);
        this.mobilyticsProvider.mo10268get().recordUserInteractionEvent(this.mobilyticsProvider.mo10268get().createUserInteractionEvent("MORE_MENU_VERSION_TWO_IMPRESSION", InteractionType.PAGE_VIEW, "RightMenu", NavigationViewController.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        setUpOfflineBanner(inflate);
        if (this.viewChangeListener == null) {
            this.viewChangeListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.alexa.navigation.menu.NavigationViewController.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    NavigationViewController navigationViewController = NavigationViewController.this;
                    navigationViewController.showCoachMarks(navigationViewController.context, NavigationViewController.this.recyclerView);
                    ((TTCFCheckpoint) NavigationViewController.this.ttcfCheckpoint.mo10268get()).markComplete(NavigationViewController.FEATURE_NAME);
                    NavigationViewController.this.recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            };
        }
        this.recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(this.viewChangeListener);
        return inflate;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDestroyView(View view) {
        Subscription subscription = this.networkServiceSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.networkServiceSubscription = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void onNetworkChanged(boolean z) {
        if (z) {
            this.offlineBanner.setVisibility(8);
        } else {
            this.offlineBanner.setVisibility(0);
        }
    }

    @VisibleForTesting
    void registerOrientationChangeListener(Context context, final View view) {
        if (this.intentFilter == null) {
            this.intentFilter = new IntentFilter();
        }
        this.intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        if (this.broadcastReceiver == null) {
            this.broadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.alexa.navigation.menu.NavigationViewController.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    NavigationViewController.this.calculateWidth(context2, view);
                }
            };
        }
        context.registerReceiver(this.broadcastReceiver, this.intentFilter);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void showAppOnlyCoachMarks(android.content.Context r8, android.view.View r9) {
        /*
            r7 = this;
            com.amazon.alexa.navigation.menu.service.FeatureCheckService r0 = r7.featureCheckService
            java.lang.String r0 = r0.getAppOnlyCoachMarksTreatment()
            int r1 = r0.hashCode()
            r2 = -514320859(0xffffffffe1581625, float:-2.4913078E20)
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == r2) goto L43
            r2 = 67
            if (r1 == r2) goto L39
            switch(r1) {
                case 2653: goto L2f;
                case 2654: goto L25;
                case 2655: goto L1b;
                default: goto L1a;
            }
        L1a:
            goto L4d
        L1b:
            java.lang.String r1 = "T3"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L4d
            r0 = r3
            goto L4e
        L25:
            java.lang.String r1 = "T2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L4d
            r0 = r4
            goto L4e
        L2f:
            java.lang.String r1 = "T1"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L4d
            r0 = r5
            goto L4e
        L39:
            java.lang.String r1 = "C"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L4d
            r0 = 0
            goto L4e
        L43:
            java.lang.String r1 = "C_DEFAULT"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L4d
            r0 = r6
            goto L4e
        L4d:
            r0 = -1
        L4e:
            java.lang.String r1 = ""
            if (r0 == 0) goto Lb0
            if (r0 == r6) goto Lac
            if (r0 == r5) goto L91
            if (r0 == r4) goto L76
            if (r0 == r3) goto L5b
            goto Lb3
        L5b:
            int r0 = com.amazon.alexa.navigation.menu.R.id.more_lists_and_notes_icon
            android.view.View r9 = r9.findViewById(r0)
            android.content.res.Resources r0 = r8.getResources()
            int r1 = com.amazon.alexa.navigation.menu.R.id.CoachMark_more_lists_and_notes_icon
            java.lang.String r1 = r0.getResourceEntryName(r1)
            android.content.res.Resources r8 = r8.getResources()
            int r0 = com.amazon.alexa.navigation.menu.R.string.coachmark_apponly_lists
            java.lang.String r8 = r8.getString(r0)
            goto Lb5
        L76:
            int r0 = com.amazon.alexa.navigation.menu.R.id.more_alarms_timers_icon
            android.view.View r9 = r9.findViewById(r0)
            android.content.res.Resources r0 = r8.getResources()
            int r1 = com.amazon.alexa.navigation.menu.R.id.CoachMark_more_alarms_timers_icon
            java.lang.String r1 = r0.getResourceEntryName(r1)
            android.content.res.Resources r8 = r8.getResources()
            int r0 = com.amazon.alexa.navigation.menu.R.string.coachmark_apponly_alarms_and_timers
            java.lang.String r8 = r8.getString(r0)
            goto Lb5
        L91:
            int r0 = com.amazon.alexa.navigation.menu.R.id.more_reminders_icon
            android.view.View r9 = r9.findViewById(r0)
            android.content.res.Resources r0 = r8.getResources()
            int r1 = com.amazon.alexa.navigation.menu.R.id.CoachMark_more_reminders_icon
            java.lang.String r1 = r0.getResourceEntryName(r1)
            android.content.res.Resources r8 = r8.getResources()
            int r0 = com.amazon.alexa.navigation.menu.R.string.coachmark_apponly_reminders
            java.lang.String r8 = r8.getString(r0)
            goto Lb5
        Lac:
            r7.showSeeMoreCoachMark(r8, r9)
            goto Lb3
        Lb0:
            r7.showSeeMoreCoachMark(r8, r9)
        Lb3:
            r9 = 0
            r8 = r1
        Lb5:
            if (r9 == 0) goto Lc9
            javax.inject.Provider<com.amazon.alexa.growth.CoachMarkFactory> r0 = r7.coachMarkFactory
            java.lang.Object r0 = r0.mo10268get()
            com.amazon.alexa.growth.CoachMarkFactory r0 = (com.amazon.alexa.growth.CoachMarkFactory) r0
            com.amazon.alexa.growth.coachmark.CoachMark r9 = r0.getCoachMark(r9, r1)
            r9.setText(r8)
            r9.show()
        Lc9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.navigation.menu.NavigationViewController.showAppOnlyCoachMarks(android.content.Context, android.view.View):void");
    }

    @VisibleForTesting
    void showCoachMarks(Context context, View view) {
        if (this.appOnlyService.shouldShowAppOnlyCoachMarks()) {
            showAppOnlyCoachMarks(context, view);
        } else {
            showSeeMoreCoachMark(context, view);
        }
    }

    @VisibleForTesting
    void showSeeMoreCoachMark(Context context, View view) {
        if (!this.featureCheckService.isSeeMoreCoachMarkEnabled()) {
            return;
        }
        View findViewById = view.findViewById(R.id.more_see_more_icon);
        View findViewById2 = view.findViewById(R.id.more_care_hub);
        if (findViewById == null || findViewById2 == null) {
            return;
        }
        CoachMark coachMark = this.coachMarkFactory.mo10268get().getCoachMark(findViewById, context.getResources().getResourceEntryName(R.id.CoachMark_more_see_more_icon));
        coachMark.setText(context.getResources().getString(R.string.coachmark_see_more));
        coachMark.show();
    }

    public NavigationViewController(Context context, ComponentRegistry componentRegistry) {
        this.maxWidth = 600.0f;
        this.context = context;
        this.blueprintsEndpoint = componentRegistry.getLazy(BlueprintsEndpoint.class);
        this.coachMarkFactory = componentRegistry.getLazy(CoachMarkFactory.class);
        this.deviceInformation = componentRegistry.getLazy(DeviceInformation.class);
        this.environmentService = componentRegistry.getLazy(EnvironmentService.class);
        this.identityService = componentRegistry.getLazy(IdentityService.class);
        this.mobilyticsProvider = componentRegistry.getLazy(Mobilytics.class);
        this.networkService = componentRegistry.getLazy(NetworkService.class);
        this.routingService = componentRegistry.getLazy(RoutingService.class);
        this.ttcfCheckpoint = componentRegistry.getLazy(TTCFCheckpoint.class);
        this.featureServiceV2 = componentRegistry.getLazy(FeatureServiceV2.class);
        this.featureCheckService = FeatureCheckService.getInstance();
        this.appOnlyService = AppOnlyService.getInstance(context);
    }
}
