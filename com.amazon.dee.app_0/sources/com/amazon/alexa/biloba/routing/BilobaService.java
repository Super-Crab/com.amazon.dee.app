package com.amazon.alexa.biloba.routing;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.RootViewControllerFactory;
import com.amazon.alexa.biloba.view.account.ProfileSettingsViewControllerFactory;
import com.amazon.alexa.biloba.view.alerts.AlertsViewControllerFactory;
import com.amazon.alexa.biloba.view.comms.EmergencyContactViewControllerFactory;
import com.amazon.alexa.biloba.view.confirmation.ConfirmationViewControllerFactory;
import com.amazon.alexa.biloba.view.dashboard.DashboardViewControllerFactory;
import com.amazon.alexa.biloba.view.gettingStarted.GettingStartedViewControllerFactory;
import com.amazon.alexa.biloba.view.infoModal.InfoModalViewControllerFactory;
import com.amazon.alexa.biloba.view.recent.RecentActivityViewControllerFactory;
import com.amazon.alexa.biloba.view.tips.TipsViewControllerFactory;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteAlias;
import com.amazon.alexa.routing.data.RouteName;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public final class BilobaService {
    private static final String TAG = "BilobaService";

    private BilobaService() {
    }

    private static Route createFinishEmergencyAddressSetupRoute(String str, int i, String str2) {
        Route createRoute = createRoute(Routes.BILOBA_FINISH_EMERGENCY_ADDRESS_SETUP, str, i, str2, Routes.BILOBA_FINISH_EMERGENCY_ADDRESS_SETUP);
        createRoute.addAlias(new RouteAlias(createRoute, "biloba/external/emergency-address?subscriptionId={subscriptionId}&marketplaceId={marketplaceId}"));
        return createRoute;
    }

    private static Route createRoute(String str, String str2, @StringRes int i, String str3) {
        return createRoute(str, str2, i, str3, "biloba");
    }

    private static List<Route> getAlertsRoutes() {
        ArrayList arrayList = new ArrayList();
        String canonicalName = AlertsViewControllerFactory.class.getCanonicalName();
        arrayList.add(createRoute(Routes.BILOBA_ALERTS_MANAGE, canonicalName, R.string.alerts_heading, Routes.BILOBA_DASHBOARD));
        arrayList.add(createRoute(Routes.BILOBA_ALERT_FORM, canonicalName, R.string.alert_settings_heading, Routes.BILOBA_DASHBOARD));
        return arrayList;
    }

    @NonNull
    public static List<Route> getAllRoutes() {
        LogUtils.d(TAG, "Returning routes for biloba");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getStartupRoutes());
        arrayList.add(new Route.Builder(Routes.BILOBA_DASHBOARD, DashboardViewControllerFactory.class.getCanonicalName()).withParent(Routes.BILOBA_STARTUP).withTemplate("biloba").withContentMode(1).withHeaderTitle(Integer.valueOf(R.string.dashboard_title)).build());
        arrayList.addAll(getGettingStartedRoutes());
        arrayList.addAll(getAlertsRoutes());
        arrayList.addAll(getProfileSettingsRoutes());
        arrayList.addAll(getEmergencyRoutes());
        arrayList.addAll(getEmergencyHelplineRoutes());
        arrayList.addAll(getWebviewRoutes());
        arrayList.add(new Route.Builder(Routes.BILOBA_ACTIVITY_RECENT, RecentActivityViewControllerFactory.class.getCanonicalName()).withParent(Routes.BILOBA_DASHBOARD).withTemplate("biloba").withContentMode(1).withHeaderTitle(Integer.valueOf(R.string.all_activity_title)).build());
        arrayList.add(new Route.Builder(Routes.BILOBA_TIPS_PAGE, TipsViewControllerFactory.class.getCanonicalName()).withParent(Routes.BILOBA_DASHBOARD).withTemplate("biloba").withContentMode(1).withHeaderTitle(Integer.valueOf(R.string.tips)).build());
        arrayList.add(new Route.Builder(Routes.BILOBA_CONFIRMATION, ConfirmationViewControllerFactory.class.getCanonicalName()).withParent(RouteName.MAIN).withTemplate("biloba").withContentMode(1).withHeaderTitle(Integer.valueOf(R.string.main_title)).build());
        arrayList.add(new Route.Builder(Routes.BILOBA_INFO_MODAL, InfoModalViewControllerFactory.class.getCanonicalName()).withParent(RouteName.MAIN).withTemplate("biloba").withContentMode(1).withHeaderTitle(Integer.valueOf(R.string.main_title)).build());
        return arrayList;
    }

    private static List<Route> getEmergencyHelplineRoutes() {
        ArrayList arrayList = new ArrayList();
        String canonicalName = RootViewControllerFactory.class.getCanonicalName();
        arrayList.add(createRoute(Routes.BILOBA_EMERGENCY_HELPLINE_CALL_STARTED, canonicalName, R.string.emergency_helpline_call_started_title, Routes.BILOBA_DASHBOARD));
        arrayList.add(createRoute(Routes.BILOBA_EMERGENCY_HELPLINE_CALL_ENDED, canonicalName, R.string.emergency_helpline_call_ended_title, Routes.BILOBA_DASHBOARD));
        arrayList.add(createRoute(Routes.BILOBA_EMERGENCY_HELPLINE_VERIFIED_AND_UNVERIFIED_FALL, canonicalName, R.string.verified_and_unverified_fall_title, Routes.BILOBA_DASHBOARD));
        arrayList.add(createRoute(Routes.BILOBA_EMERGENCY_HELPLINE_VERIFIED_NO_FALL, canonicalName, R.string.verified_no_fall_title, Routes.BILOBA_DASHBOARD));
        return arrayList;
    }

    private static List<Route> getEmergencyRoutes() {
        ArrayList arrayList = new ArrayList();
        String canonicalName = EmergencyContactViewControllerFactory.class.getCanonicalName();
        arrayList.add(createRoute(Routes.BILOBA_EMERGENCY_CONTACT, canonicalName, R.string.emergency_heading, Routes.BILOBA_DASHBOARD));
        arrayList.add(createRoute(Routes.BILOBA_EMERGENCY, canonicalName, R.string.emergency_heading, Routes.BILOBA_DASHBOARD));
        arrayList.add(createRoute(Routes.BILOBA_COMMS_SHARE_SETUP_LINK, canonicalName, R.string.comms_share_setup_link_title, Routes.BILOBA_DASHBOARD));
        arrayList.add(createRoute(Routes.BILOBA_COMMS_SETUP_INSTRUCTIONS, canonicalName, R.string.comms_setup_instruct_title, Routes.BILOBA_DASHBOARD));
        return arrayList;
    }

    private static List<Route> getGettingStartedRoutes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(createRoute(Routes.BILOBA_GETTING_STARTED_V3, GettingStartedViewControllerFactory.class.getCanonicalName(), R.string.main_title, Routes.BILOBA_STARTUP));
        return arrayList;
    }

    private static List<Route> getProfileSettingsRoutes() {
        ArrayList arrayList = new ArrayList();
        String canonicalName = ProfileSettingsViewControllerFactory.class.getCanonicalName();
        arrayList.add(createRoute(Routes.BILOBA_PROFILE_SETTINGS, canonicalName, R.string.profile_settings_heading, Routes.BILOBA_DASHBOARD));
        arrayList.add(createRoute(Routes.BILOBA_MEMBERSHIP_MANAGE, canonicalName, R.string.membership_heading, Routes.BILOBA_PROFILE_SETTINGS));
        arrayList.add(createRoute(Routes.BILOBA_MEMBERSHIP_LEAVE, canonicalName, R.string.membership_leave_heading, Routes.BILOBA_MEMBERSHIP_MANAGE));
        arrayList.add(createRoute(Routes.BILOBA_MEMBERSHIP_LEAVE_DONE, canonicalName, R.string.membership_leave_heading, Routes.BILOBA_MEMBERSHIP_LEAVE));
        arrayList.add(createRoute(Routes.TIMEZONE_PICKER, canonicalName, R.string.membership_leave_heading, Routes.BILOBA_PROFILE_SETTINGS));
        return arrayList;
    }

    private static List<Route> getStartupRoutes() {
        ArrayList arrayList = new ArrayList();
        String canonicalName = RootViewControllerFactory.class.getCanonicalName();
        int i = R.string.main_title;
        Route createRoute = createRoute("biloba", canonicalName, i, RouteName.MAIN);
        createRoute.addAlias(new RouteAlias(createRoute, "biloba?isPersonalPasscodeVerified={passcodeVerified}"));
        arrayList.add(createRoute);
        arrayList.add(createRoute(Routes.BILOBA_STARTUP, canonicalName, i, RouteName.MAIN));
        return arrayList;
    }

    private static List<Route> getWebviewRoutes() {
        ArrayList arrayList = new ArrayList();
        String canonicalName = RootViewControllerFactory.class.getCanonicalName();
        int i = R.string.main_title;
        arrayList.add(createRoute(Routes.BILOBA_CARE_PLUS_DISCOVERY, canonicalName, i, RouteName.MAIN, Routes.BILOBA_CARE_PLUS_DISCOVERY));
        arrayList.add(createRoute(Routes.BILOBA_CARE_PLUS_UPSELL, canonicalName, i, RouteName.MAIN, Routes.BILOBA_CARE_PLUS_UPSELL));
        arrayList.add(createRoute(Routes.BILOBA_COMMS_PRIMER, canonicalName, i, RouteName.MAIN, Routes.BILOBA_COMMS_PRIMER));
        arrayList.add(createRoute("biloba/external/dashboard", canonicalName, i, RouteName.MAIN, "biloba/external/dashboard"));
        arrayList.add(createRoute("biloba/external/dashboard", canonicalName, i, RouteName.MAIN, "biloba/external/dashboard"));
        arrayList.add(createRoute(Routes.BILOBA_FINISH_COMMS_SETUP, canonicalName, i, RouteName.MAIN, Routes.BILOBA_FINISH_COMMS_SETUP));
        arrayList.add(createRoute(Routes.BILOBA_TEST_EMERGENCY_HELPLINE, canonicalName, i, RouteName.MAIN, Routes.BILOBA_TEST_EMERGENCY_HELPLINE));
        arrayList.add(createRoute(Routes.BILOBA_LONE_CR_CREATE_RELATIONSHIP, canonicalName, i, RouteName.MAIN, Routes.BILOBA_LONE_CR_CREATE_RELATIONSHIP));
        arrayList.add(createRoute(Routes.BILOBA_INVITE_CARE_GIVER, canonicalName, i, RouteName.MAIN, Routes.BILOBA_INVITE_CARE_GIVER));
        arrayList.add(createRoute(Routes.BILOBA_CARE_PLUS_WELCOME, canonicalName, i, RouteName.MAIN, Routes.BILOBA_CARE_PLUS_WELCOME));
        arrayList.add(createFinishEmergencyAddressSetupRoute(canonicalName, i, RouteName.MAIN));
        return arrayList;
    }

    private static Route createRoute(String str, String str2, @StringRes int i, String str3, String str4) {
        return new Route.Builder(str, str2).withParent(str3).withTemplate(str4).withContentMode(1).withHeaderTitle(Integer.valueOf(i)).build();
    }
}
