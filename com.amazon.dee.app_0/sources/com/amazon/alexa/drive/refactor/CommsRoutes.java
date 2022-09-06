package com.amazon.alexa.drive.refactor;

import androidx.annotation.NonNull;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.routing.api.Route;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/* loaded from: classes7.dex */
public final class CommsRoutes {
    private static final String callListPageURI = "v2/comms/drive-mode-call-list-page/{contactId}";
    private static final String landingPage = "v2/comms/drive-mode-landing-page";
    private static final String landingPageURI = "v2/comms/drive-mode-landing-page?autoModeType={autoModeType?}";
    private static final String dropInPage = "v2/comms/drive-mode-drop-in-page";
    private static final String announcementPage = "v2/comms/drive-mode-announcements-page";
    private static final String announcementSuccessPage = "v2/comms/drive-mode-announcement-success-page";
    private static final String announcementFailurePage = "v2/comms/drive-mode-announcement-failure-page";
    private static final String callListPage = "v2/comms/drive-mode-call-list-page";
    private static final String callDetailage = "v2/comms/drive-mode-call-detail-page";
    private static final Set<String> commsRoutes = ImmutableSet.of("v2/comms/drive-mode-landing-page", dropInPage, announcementPage, announcementSuccessPage, announcementFailurePage, callListPage, callDetailage);

    private CommsRoutes() {
    }

    public static List<Route> getRoutes() {
        Preconditions.checkNotNull((DriveModeService) GeneratedOutlineSupport1.outline21(DriveModeService.class));
        return getRoutesWithDefaultAdapter();
    }

    private static List<Route> getRoutesWithDefaultAdapter() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Route.Builder("v2/comms/drive-mode-landing-page", 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(8).withTemplate("v2/comms/drive-mode-landing-page?autoModeType={autoModeType?}").withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(callListPage, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(callListPageURI).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(dropInPage, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(dropInPage).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(announcementPage, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(announcementPage).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(announcementSuccessPage, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(announcementSuccessPage).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(announcementFailurePage, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(announcementFailurePage).withTheme(Route.Theme.DARK).build());
        arrayList.add(new Route.Builder(callDetailage, 1).asRoot().withParent("alexa-oobe/drive-mode/main").withContentMode(9).withTemplate(callDetailage).withTheme(Route.Theme.DARK).build());
        return arrayList;
    }

    public static boolean isCommsRoute(@NonNull Route route) {
        return commsRoutes.contains(route.getName());
    }
}
