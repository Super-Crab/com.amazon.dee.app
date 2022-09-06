package com.amazon.alexa.drive.main.routing;

import androidx.annotation.NonNull;
import com.amazon.alexa.drive.comms.routing.CommsViewControllerFactory;
import com.amazon.alexa.drive.entertainment.routing.EntertainmentViewControllerFactory;
import com.amazon.alexa.drive.landing.HomeViewControllerFactory;
import com.amazon.alexa.drive.navigation.NavigationViewControllerFactory;
import com.amazon.alexa.drive.refactor.CommsRoutes;
import com.amazon.alexa.drive.smart.device.SmartHomeViewControllerFactory;
import com.amazon.alexa.drive.warning.WarningViewControllerFactory;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.data.RouteName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/* loaded from: classes7.dex */
public final class DriveModeRoutes {
    private static final Set<String> homeRoutes = ImmutableSet.of("drive-mode/home/index", DriveModeRoutingConstants.DRIVE_MODE_SMART_DEVICE_ROUTE_NAME, DriveModeRoutingConstants.DRIVE_MODE_LOCK_PERMISSION_WARNING_ROUTE_NAME);
    private static final Set<String> safetyWarningRoutes = ImmutableSet.of("drive-mode/driver-interaction-warning");
    private static final Set<String> navigationRoutes = ImmutableSet.of("drive-mode/navigation/index");
    private static final Set<String> entertainmentRoutes = ImmutableSet.of("drive-mode/entertainment/index", "drive-mode/entertainment/now-playing-screen");
    private static final Set<String> commsNativeRoutes = ImmutableSet.of(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ROUTE_NAME, DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ANNOUNCEMENT_ROUTE_NAME, DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ANNOUNCEMENT_FAILURE_ROUTE_NAME, DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ANNOUNCEMENT_SUCCESS_ROUTE_NAME, DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_CALL_LIST_ROUTE_NAME, DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_CONTACT_DETAILS_ROUTE_NAME, DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_DROP_IN_ROUTE_NAME);

    private DriveModeRoutes() {
    }

    public static List<Route> getAllRoutes() {
        Preconditions.checkNotNull((DriveModeService) GeneratedOutlineSupport1.outline21(DriveModeService.class));
        ArrayList<Route> allRoutesWithViewManagerAdapter = getAllRoutesWithViewManagerAdapter();
        allRoutesWithViewManagerAdapter.addAll(CommsRoutes.getRoutes());
        return allRoutesWithViewManagerAdapter;
    }

    private static ArrayList<Route> getAllRoutesWithViewManagerAdapter() {
        ArrayList<Route> arrayList = new ArrayList<>();
        arrayList.add(new Route.Builder("drive-mode/home/index", HomeViewControllerFactory.class.getCanonicalName()).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(8).withTemplate("drive-mode/home/index").withTheme(Route.Theme.DARK).build());
        String canonicalName = EntertainmentViewControllerFactory.class.getCanonicalName();
        arrayList.add(new Route.Builder("drive-mode/entertainment/index", canonicalName).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(8).withTemplate("drive-mode/entertainment/index").withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder("drive-mode/entertainment/now-playing-screen", canonicalName).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate("drive-mode/entertainment/now-playing-screen").withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder("drive-mode/navigation/index", NavigationViewControllerFactory.class.getCanonicalName()).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(8).withTemplate("drive-mode/navigation/index").withTheme(Route.Theme.DARK).build());
        String canonicalName2 = SmartHomeViewControllerFactory.class.getCanonicalName();
        arrayList.add(new Route.Builder(DriveModeRoutingConstants.DRIVE_MODE_SMART_DEVICE_ROUTE_NAME, canonicalName2).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(DriveModeRoutingConstants.DRIVE_MODE_SMART_DEVICE_ROUTE_NAME).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(DriveModeRoutingConstants.DRIVE_MODE_LOCK_PERMISSION_WARNING_ROUTE_NAME, canonicalName2).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(DriveModeRoutingConstants.DRIVE_MODE_LOCK_PERMISSION_WARNING_ROUTE_NAME).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder("drive-mode/driver-interaction-warning", WarningViewControllerFactory.class.getCanonicalName()).asRoot().withParent(RouteName.MAIN).withContentMode(2).withTemplate("drive-mode/driver-interaction-warning").withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ROUTE_NAME, CommsViewControllerFactory.class.getCanonicalName()).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(8).withTemplate(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ROUTE_NAME).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_CONTACT_DETAILS_ROUTE_NAME, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_CONTACT_DETAILS_URI).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_DROP_IN_ROUTE_NAME, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_DROP_IN_ROUTE_NAME).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ANNOUNCEMENT_ROUTE_NAME, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ANNOUNCEMENT_ROUTE_NAME).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ANNOUNCEMENT_SUCCESS_ROUTE_NAME, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ANNOUNCEMENT_SUCCESS_ROUTE_NAME).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ANNOUNCEMENT_FAILURE_ROUTE_NAME, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ANNOUNCEMENT_FAILURE_ROUTE_NAME).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_CALL_LIST_ROUTE_NAME, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_CALL_LIST_ROUTE_NAME).withTheme(Route.Theme.DARK).build());
        return arrayList;
    }

    public static boolean isCommsNativeRoute(@NonNull Route route) {
        return commsNativeRoutes.contains(route.getName());
    }

    public static boolean isDriveModeRoute(@NonNull Route route) {
        return isHomeRoute(route) || isNavigationRoute(route) || isEntertainmentRoute(route) || CommsRoutes.isCommsRoute(route);
    }

    public static boolean isDriveModeSafetyWarningRoute(@NonNull Route route) {
        return safetyWarningRoutes.contains(route.getName());
    }

    public static boolean isEntertainmentRoute(@NonNull Route route) {
        return entertainmentRoutes.contains(route.getName());
    }

    public static boolean isHomeRoute(@NonNull Route route) {
        return homeRoutes.contains(route.getName());
    }

    public static boolean isNavigationRoute(@NonNull Route route) {
        return navigationRoutes.contains(route.getName());
    }

    private static Route.Builder route(String str, int i) {
        return new Route.Builder(str, i);
    }
}
