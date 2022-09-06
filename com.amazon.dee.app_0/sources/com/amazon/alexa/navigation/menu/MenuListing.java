package com.amazon.alexa.navigation.menu;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.blueprints.api.BlueprintsEndpoint;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.navigation.menu.constants.WebConstants;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public class MenuListing {
    private static final String CARE_HUB_ROUTE = "biloba";
    private static final String FIND_MY_ROUTE = "elements-find-my/index";
    private static final String FSV2_SAMPLE_FEATURE = "FEATURE_SERVICE_V2_SAMPLE_LAUNCH_FEATURE_ANDROID";
    @VisibleForTesting
    public static final String WORKOUT_TRACK_ROUTE = "fitness/track";
    private Provider<BlueprintsEndpoint> blueprintsEndpoint;
    private Context context;
    @VisibleForTesting
    FeatureServiceV2.FeatureUpdateListener eliseFeatureListener = createEliseFeatureListener();
    private Provider<EnvironmentService> environmentService;
    private Provider<FeatureServiceV2> featureServiceV2;
    private Provider<IdentityService> identityService;
    private boolean isBlueprintsEnabled;
    @VisibleForTesting
    boolean isEliseEnabled;
    private List<MenuItem> menu;
    private Provider<Mobilytics> mobilyticsProvider;
    private Provider<RoutingService> routingService;
    private static final List<String> CARE_HUB_ALLOWED_COR = Arrays.asList("US", "GB", "CA");
    private static final String TAG = MenuListing.class.getSimpleName();

    public MenuListing(Context context, Provider<IdentityService> provider, Provider<Mobilytics> provider2, Provider<RoutingService> provider3, Provider<BlueprintsEndpoint> provider4, Provider<EnvironmentService> provider5, Provider<FeatureServiceV2> provider6) {
        this.context = context;
        this.blueprintsEndpoint = provider4;
        this.environmentService = provider5;
        this.identityService = provider;
        this.mobilyticsProvider = provider2;
        this.routingService = provider3;
        this.featureServiceV2 = provider6;
    }

    private FeatureServiceV2.FeatureUpdateListener createEliseFeatureListener() {
        return new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.alexa.navigation.menu.MenuListing.1
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public void onFeatureUpdate(String str) {
                String unused = MenuListing.TAG;
                GeneratedOutlineSupport1.outline158("onFeatureUpdate called on ", str);
                MenuListing menuListing = MenuListing.this;
                menuListing.isEliseEnabled = ((FeatureServiceV2) menuListing.featureServiceV2.mo10268get()).hasAccess("ALEXA_AUTO_ANDROID_ELISE_ENABLED", false);
            }
        };
    }

    private Map<String, String> getBlueprintsIntentExtras() {
        HashMap outline133 = GeneratedOutlineSupport1.outline133("BRIDGE_ACTION_KEY", "launchExternal");
        outline133.put("android.intent.extra.TEXT", this.blueprintsEndpoint.mo10268get().getBlueprintsHomePageURL());
        outline133.put("URL_REGEX_KEY", this.blueprintsEndpoint.mo10268get().getURLRegexKey());
        return outline133;
    }

    private boolean shouldShowFSV2TestMenu() {
        return this.featureServiceV2.mo10268get().hasAccess("FEATURE_SERVICE_V2_SAMPLE_LAUNCH_FEATURE_ANDROID", false);
    }

    private Boolean shouldShowTTT() {
        return Boolean.valueOf(!Locale.getDefault().getLanguage().equals(new Locale("ar").getLanguage()));
    }

    @VisibleForTesting
    List<MenuItem> buildMenu() {
        this.isBlueprintsEnabled = this.featureServiceV2.mo10268get().hasAccess("ALEXA_MOBILE_APP_BLUEPRINTS_LIBRARY_ENDPOINT_ANDROID", false);
        this.featureServiceV2.mo10268get().observeFeature("ALEXA_AUTO_ANDROID_ELISE_ENABLED", this.eliseFeatureListener);
        List<MenuItem> seeMoreList = getSeeMoreList();
        boolean z = seeMoreList != null && !seeMoreList.isEmpty();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RouteMenuItem(R.string.more_add_device, R.drawable.ic_menu_add_device, "v2/guided-discovery/add-devices", true, MetricsComponents.ADD_DEVICE, TestId.ADD_DEVICE, this.mobilyticsProvider, this.routingService));
        arrayList.add(new RouteMenuItem(this.featureServiceV2.mo10268get().hasAccess("ELEMENTS_REMEMBER_THIS_ANDROID", false) ? R.string.more_lists_and_notes : R.string.more_lists, R.drawable.ic_list_bullet_bitmap, RouteName.LISTS, false, MetricsComponents.LISTS_AND_NOTES, TestId.LISTS_AND_NOTES, this.mobilyticsProvider, this.routingService));
        arrayList.add(new RouteMenuItem(R.string.more_reminders, R.drawable.ic_menu_lists_notes, RouteName.REMINDERS, false, MetricsComponents.REMINDERS, TestId.REMINDERS, this.mobilyticsProvider, this.routingService));
        arrayList.add(new RouteMenuItem(R.string.more_alarms_timers, R.drawable.ic_menu_reminders_alarms, "v2/reminders-alarms-timers", false, MetricsComponents.ALARMS_TIMERS, TestId.ALARMS_TIMERS, this.mobilyticsProvider, this.routingService));
        arrayList.add(new RouteMenuItem(R.string.more_routines, R.drawable.ic_menu_routines, "v2/behaviors", false, MetricsComponents.ROUTINES, TestId.ROUTINES, this.mobilyticsProvider, this.routingService));
        arrayList.add(new RouteMenuItem(R.string.more_skills_and_games, R.drawable.ic_menu_skills_games, RouteName.ELEMENTS_A2S_FRONT_PAGE, false, MetricsComponents.SKILLS_AND_GAMES, TestId.SKILLS_AND_GAMES, this.mobilyticsProvider, this.routingService));
        if (shouldShowFSV2TestMenu()) {
            arrayList.add(new RouteMenuItem(R.string.more_fsv2_sample_test, R.drawable.ic_menu_help_feedback, "", false, MetricsComponents.FSV2_SAMPLE_TEST, TestId.FSV2_SAMPLE_TEST, this.mobilyticsProvider, this.routingService));
        }
        if (z) {
            ExpandableMenuItem expandableMenuItem = new ExpandableMenuItem(R.string.more_see_more, R.drawable.ic_caret_down, true, MetricsComponents.SEE_MORE, MetricsComponents.SEE_LESS, TestId.SEE_MORE, this.mobilyticsProvider);
            expandableMenuItem.setMenuItemList(seeMoreList);
            arrayList.add(expandableMenuItem);
        }
        arrayList.add(new RouteMenuItem(R.string.more_settings, R.drawable.ic_menu_settings, "settings", false, MetricsComponents.SETTINGS, TestId.SETTINGS, this.mobilyticsProvider, this.routingService));
        arrayList.add(new RouteMenuItem(R.string.more_activity, R.drawable.ic_menu_activity, "v2/homefeed", false, MetricsComponents.ACTIVITY, TestId.ACTIVITY, this.mobilyticsProvider, this.routingService));
        arrayList.add(new RouteMenuItem(R.string.more_help_and_feedback, R.drawable.ic_menu_help_feedback_bitmap, RouteName.HELP, false, MetricsComponents.HELP_AND_FEEDBACK, TestId.HELP_AND_FEEDBACK, this.mobilyticsProvider, this.routingService));
        return arrayList;
    }

    public List<MenuItem> getMenu() {
        if (this.menu == null) {
            this.menu = buildMenu();
        }
        return this.menu;
    }

    @VisibleForTesting
    List<MenuItem> getSeeMoreList() {
        UserIdentity user;
        ArrayList arrayList = new ArrayList();
        if (this.isEliseEnabled) {
            arrayList.add(new RouteMenuItem(R.string.more_automotive, R.drawable.ic_by_car, "elements-mobile-automotive/garage/landing-page", false, MetricsComponents.AUTOMOTIVE, TestId.AUTOMOTIVE, this.mobilyticsProvider, this.routingService));
        }
        if (this.isBlueprintsEnabled) {
            arrayList.add(new ActivityMenuItem(this.context, R.string.more_blueprints, R.drawable.ic_menu_blueprints, WebConstants.EXTERNALUIACTIVITY_CLASS, getBlueprintsIntentExtras(), false, MetricsComponents.BLUEPRINTS, TestId.BLUEPRINTS, this.mobilyticsProvider));
        }
        if (shouldShowTTT().booleanValue()) {
            arrayList.add(new RouteMenuItem(R.string.more_things_to_try, R.drawable.ic_menu_things_to_try, RouteName.THINGS_TO_TRY_ELEMENTS, false, MetricsComponents.THINGS_TO_TRY, TestId.THINGS_TO_TRY, this.mobilyticsProvider, this.routingService));
        }
        if (this.featureServiceV2.mo10268get().hasAccess("ALEXA_BILOBA_ANDROID_MENU_INGRESS", false) && (user = this.identityService.mo10268get().getUser(TAG)) != null) {
            String countryCode = user.getMarketplace().getCountryCode().toString();
            String countryOfResidence = user.getCountryOfResidence();
            if (CARE_HUB_ALLOWED_COR.contains(countryOfResidence) && countryOfResidence.equals(countryCode)) {
                arrayList.add(new RouteMenuItem(R.string.more_care_hub, R.drawable.ic_care_hub, "biloba", false, MetricsComponents.CARE_HUB, TestId.CARE_HUB, this.mobilyticsProvider, this.routingService));
            }
        }
        if (this.featureServiceV2.mo10268get().hasAccess("ELEMENTS_FIND_MY_ANDROID", false)) {
            arrayList.add(new RouteMenuItem(R.string.more_find_my, R.drawable.ic_pin_destination, FIND_MY_ROUTE, false, MetricsComponents.FIND_MY, TestId.FIND_MY, this.mobilyticsProvider, this.routingService));
        }
        if (!this.environmentService.mo10268get().getDeviceInformation().isFireOS() && this.featureServiceV2.mo10268get().hasAccess("ALEXA_FITNESS_WORKOUT_HISTORY_MORE_MENU_INGRESS_ANDROID", false) && this.routingService.mo10268get().match("fitness/track") != null) {
            arrayList.add(new RouteMenuItem(R.string.more_workout, R.drawable.ic_sport_run, "fitness/track", false, MetricsComponents.WORKOUT, TestId.WORKOUT, this.mobilyticsProvider, this.routingService));
        }
        return arrayList;
    }
}
