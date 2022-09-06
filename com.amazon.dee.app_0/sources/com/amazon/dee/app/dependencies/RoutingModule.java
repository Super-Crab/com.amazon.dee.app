package com.amazon.dee.app.dependencies;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.routing.BilobaService;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.entertainment.devicepicker.DevicePickerViewControllerFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.RouteFeatureGroupRegistry;
import com.amazon.alexa.routing.RouteUtils;
import com.amazon.alexa.routing.RouteWatcher;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.routing.data.RouteUrls;
import com.amazon.dee.app.R;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class RoutingModule {
    static final String CANTILEVER_VIEW_FACTORY = "com.amazon.alexa.cantilever.HelpViewControllerFactory";
    @VisibleForTesting
    static final int DEFAULT_ELEMENTS_PRIORITY = 50;
    @VisibleForTesting
    static final int DEFAULT_WEB_PRIORITY = 70;
    static final String HOME_VIEW_FACTORY = "com.amazon.alexa.redesign.view.container.HomeViewControllerFactory";
    static final String NAV_MORE_MENU_VIEW_FACTORY = "com.amazon.alexa.navigation.menu.NavigationViewControllerFactory";
    @VisibleForTesting
    static final String SMART_HOME_CAMERAS_QUALIFIED_NAME = "com.amazon.alexa.smarthomecameras.routing.CamerasViewControllerFactory";

    private void registerAlexaDeviceSetupRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register("v2/alexa-oobe", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/alexa-oobe");
        routingRegistry.register("v2/alexa-oobe/setupSpeaker", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/alexa-oobe/setupSpeaker/{deviceSerial}/{deviceType}");
        routingRegistry.register("v2/alexa-oobe", 1).asRoot().withParent(RouteName.MAIN).withTemplate(RouteUrls.ELEMENTS_DEVICE_SETUP_COR);
        routingRegistry.register("v2/alexa-oobe", 1).asRoot().withParent(RouteName.MAIN).withTemplate(RouteUrls.ELEMENTS_DEVICE_SETUP_PFM);
        routingRegistry.register("v2/alexa-oobe", 1).asRoot().withParent(RouteName.MAIN).withTemplate(RouteUrls.ELEMENTS_DEVICE_SETUP_NEW_DEVICE);
        routingRegistry.register("v2/alexa-oobe", 1).asRoot().withParent(RouteName.MAIN).withTemplate(RouteUrls.ELEMENTS_DEVICE_SETUP_WELCOME);
        routingRegistry.register("v2/alexa-oobe/setupSpeakerBluetooth", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/alexa-oobe/setupSpeakerBluetooth/{deviceSerial}/{deviceType}");
        routingRegistry.register("v2/alexa-oobe/speakerOptions", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/alexa-oobe/speakerOptions/{deviceType}/{deviceSerial}");
        routingRegistry.register("v2/alexa-oobe/forcedWaitVideo", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/alexa-oobe/forcedWaitVideo/{deviceSerial}");
        routingRegistry.register("v2/alexa-oobe/ftue-startup", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/alexa-oobe/ftue-startup");
        routingRegistry.register("v2/alexa-oobe-speaker-pairing", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/alexa-oobe-speaker-pairing").withTemplate("v2/alexa-oobe-speaker-pairing/{deviceSerial}/{deviceType}");
        routingRegistry.register("v2/alexa-oobe/forcedWaitVideo", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/alexa-oobe/forcedWaitVideo/{deviceType}/{deviceSerial}/{isNewDevice}");
        routingRegistry.register("v2/alexa-oobe/postOobeFtue", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/alexa-oobe/postOobeFtue/{deviceType}/{deviceSerialNumber}/{isNewDevice}");
    }

    private void registerBatteryOptimizationRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register(route("presence-battery-optimization", 11).withTemplate("presence-battery-optimization").asRoot().isOverlay().withParent(RouteName.MAIN).withContentMode(7).build());
    }

    private void registerBilobaRoutes(RoutingRegistry routingRegistry) {
        for (Route route : BilobaService.getAllRoutes()) {
            routingRegistry.register(route);
        }
    }

    private void registerCommsReactNativeRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register(route(RouteName.COMMS_CONTACT_CARD, 1).withParent(RouteName.MAIN).withTemplate("v2/comms/contact-card/{route_parameter}").withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.COMMS_MANAGE_CONTACTS, 1).withParent("v2/comms/conversation-list").withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.COMMS_IMPORT_CONTACT, 1).withParent("v2/comms/conversation-list").withHandleHeaderTitle().build());
        routingRegistry.register(route("v2/comms/conversation", 1).withParent("v2/comms/conversation-list").withTemplate("v2/comms/conversation/{id}").withContentMode(2).withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.COMMS_ANNOUNCEMENT, 1).withParent("v2/comms/conversation-list").withTemplate(RouteName.COMMS_ANNOUNCEMENT).withContentMode(2).withHandleHeaderTitle().build());
        routingRegistry.register(route("v2/comms/image-detail-view", 1).withParent("v2/comms/conversation").withTemplate("v2/comms/image-detail-view/{conversationId}?messageId={messageId}").withContentMode(2).withHandleHeaderTitle().build());
        routingRegistry.register(route("v2/comms/emergency-contacts-manage", 1).withParent(RouteName.MANAGE_CONTACT_LIST).withTemplate("v2/comms/emergency-contacts-manage").withHandleHeaderTitle().build());
        routingRegistry.register(route("v2/comms/emergency-contact-selector", 1).withParent("v2/comms/emergency-contacts-manage").withTemplate("v2/comms/emergency-contact-selector?shouldReturnBackOnComplete={shouldReturnBackOnComplete?}&primarySourceId={primarySourceId?}&externalSelectedContactId={externalSelectedContactId?}&externalSelectedContactCommsId={externalSelectedContactCommsId?}").withHandleHeaderTitle().build());
        routingRegistry.register(route("v2/comms/conversation-list", 1).withParent("conversations").withTemplate("v2/comms/conversation-list").withHandleHeaderTitle().build());
    }

    private void registerConversationRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register(route("conversations", 2).asRoot().withParent(RouteName.MAIN).build());
        routingRegistry.register(route(RouteName.CONVERSATIONS_CONTACT_LIST, 2).withParent("conversations").withHandleHeaderTitle().withContentMode(0).build());
        routingRegistry.register(route(RouteName.CONVERSATIONS_CONTACT, 2).withParent(RouteName.CONVERSATIONS_CONTACT_LIST).withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.EDIT_CONTACT_CARD, 2).withParent(RouteName.CONVERSATIONS_CONTACT).withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.CONVERSATIONS_DIAGNOSTICS, 2).withParent(RouteName.CONVERSATIONS_CONTACT).withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.MANAGE_CONTACT_LIST, 2).withParent(RouteName.CONVERSATIONS_CONTACT_LIST).withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.BLOCK_CONTACT_LIST, 2).withParent(RouteName.MANAGE_CONTACT_LIST).withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.CHILD_CONTACT_CARD, 2).withParent(RouteName.CONVERSATIONS_CONTACT).withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.WHITELIST_CONTACT, 2).withParent(RouteName.CONVERSATIONS_CONTACT).withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.RELATIONSHIP_LIST, 2).withParent(RouteName.CHILD_CONTACT_CARD).withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.EDIT_NICKNAME, 2).withParent(RouteName.CHILD_CONTACT_CARD).withHandleHeaderTitle().build());
        routingRegistry.register(route("v2/comms/contact-list", 2).withParent("conversations").withAlias("rn-contact-list").withHandleHeaderTitle().build());
        routingRegistry.register(route(RouteName.CONVERSATIONS_PATH, 2).withParent("conversations").doNotKeepInBackStack().withTemplate("conversations/{path}/start/{start}").build());
    }

    private void registerDevicePickerRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register(route("universal-device-picker", DevicePickerViewControllerFactory.class.getCanonicalName()).withTemplate("universal-device-picker").asRoot().withParent(RouteName.MAIN).build());
    }

    private void registerDriveModeRoutes(RoutingRegistry routingRegistry) {
        Optional optional = ComponentRegistry.getInstance().get(DriveModeService.class);
        Preconditions.checkNotNull(optional);
        for (Route route : ((DriveModeService) optional.get()).getAllRoutes()) {
            routingRegistry.register(route);
        }
    }

    private void registerIdentityServicesRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register(route("v2/profile-oobe/profile-oobe-start", 1).asRoot().withParent(RouteName.MAIN).withContentMode(2).withTemplate("v2/profile-oobe/profile-oobe-start").build());
    }

    private void registerMainRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register(route(RouteName.MAIN, 3).asRoot().build());
        routingRegistry.register(route("web", 0).asRoot().withParent(RouteName.MAIN).build());
        GeneratedOutlineSupport1.outline144(route(RouteName.STARTUP, 0), RouteName.MAIN, RouteName.STARTUP, routingRegistry);
        routingRegistry.register(route("thingstotry", 0).asRoot().withHeaderTitle(Integer.valueOf((int) R.string.header_title_things_to_try)).withParent(RouteName.MAIN).withTemplate("help/thingstotry").build());
        routingRegistry.register(route(RouteName.MUSIC_BOOKS, 0).asRoot().withHeaderTitle(Integer.valueOf((int) R.string.header_title_music_video_books)).withParent(RouteName.MAIN).withTemplate("music/services").build());
        routingRegistry.register(route("settings", 0).asRoot().withHeaderTitle(Integer.valueOf((int) R.string.header_title_settings)).withParent(RouteName.MAIN).withTemplate("settings").build());
        routingRegistry.register(route(RouteName.NAV_MORE_MENU, NAV_MORE_MENU_VIEW_FACTORY).asRoot().withParent(RouteName.MAIN).withTemplate(RouteName.NAV_MORE_MENU).withContentMode(3).build());
        routingRegistry.register(route(RouteName.HELP, CANTILEVER_VIEW_FACTORY).asRoot().withParent(RouteName.MAIN).withHeaderTitle(Integer.valueOf((int) R.string.header_title_help)).withTemplate(RouteName.HELP).withAlias("help/node/{nodeId}").withAlias("help/call").withAlias("help/chat/{isChatRedirect}").withAlias("help/action/{cantileverIntent}").build());
    }

    private void registerSmartHomeCamerasRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register(route(RouteName.SMART_HOME_CAMERAS_SMART_ALERTS_EVENT, SMART_HOME_CAMERAS_QUALIFIED_NAME).asRoot().withParent(RouteName.MAIN).withAlias(RouteName.SMART_HOME_CAMERAS_SMART_ALERTS_EVENT).withContentMode(2).withTemplate(RouteUrls.SMART_HOME_CAMERAS_SMART_ALERTS_EVENT).build());
        routingRegistry.register(route(RouteName.SMART_HOME_CAMERAS_LIVEVIEW, SMART_HOME_CAMERAS_QUALIFIED_NAME).asRoot().withParent(RouteName.MAIN).withAlias(RouteName.SMART_HOME_CAMERAS_LIVEVIEW).withContentMode(1).withTemplate(RouteUrls.SMART_HOME_CAMERAS_LIVEVIEW).build());
    }

    private void registerSmartHomeRoutes(RoutingRegistry routingRegistry) {
        GeneratedOutlineSupport1.outline144(route(RouteName.SMART_HOME_DEVICE_CONTROL, 1), RouteName.MAIN, "v2/smart-home/devices/{entityId}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.SMART_HOME_DEVICE_SETTING, 1), RouteName.MAIN, "v2/smart-home/devices/{entityId}/settings", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.SMART_HOME_CMB_SETUP, 1), RouteName.MAIN, "v2/smart-home/space-setup-page/{entityId}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.SMART_HOME_NROOP_COMPLETE, 1), RouteName.MAIN, "v2/smart-home/nroop-setup-complete-page", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.SMART_HOME_FFS_WIFI_CONNECTING, 1), RouteName.MAIN, RouteUrls.WIFI_CONNECTING, routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.SMART_HOME_FFS_LANDING, 1), RouteName.MAIN, "v2/smart-home/ffs/landing/{applianceType}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.SMART_HOME_FFS_NEW_DEVICE_FOUND, 1), RouteName.MAIN, "v2/smart-home/ffs/new-device-found-page", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.SMART_HOME_RED_ROCK_HISTORY, 1), RouteName.MAIN, "v2/smart-home-redrock/profile-history-page", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.SMART_HOME_RED_ROCK_SETUP, 1), RouteName.MAIN, "v2/smart-home-redrock/{path}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route("v2/cameras/smart-alerts/dashboard-page", 1), RouteName.MAIN, "v2/cameras/smart-alerts/dashboard-page", routingRegistry);
    }

    private void registerVoiceEnrollmentRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register(route("voice-enrollment-oobe", 7).withTemplate("voice-enrollment-oobe/{enrollmentContext?}").isOverlay().asRoot().withParent(RouteName.MAIN).build());
        routingRegistry.register(route("voice-enrollment", 7).withTemplate("voice-enrollment/{enrollmentContext?}").asRoot().isOverlay().withParent(RouteName.MAIN).build());
        routingRegistry.register(route("kids-enrollment", 19).withTemplate("kids-enrollment/{enrollmentContext}/{enrollmentMetadata}").asRoot().isOverlay().withParent(RouteName.MAIN).build());
        routingRegistry.register(route("v2/settings/recognized-voices", 1).withTemplate("v2/settings/recognized-voices").asRoot().withParent(RouteName.MAIN).build());
        routingRegistry.register(route("v2/settings/your-voice", 1).withTemplate("v2/settings/your-voice?ishandsfree={ishandsfree?}").asRoot().withParent(RouteName.MAIN).build());
        routingRegistry.register(route("v2/settings/recognize-speakers", 1).withTemplate("v2/settings/recognize-speakers").asRoot().withParent(RouteName.MAIN).build());
    }

    private void registerVoiceRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register(route("voice-default-assistant", 5).withTemplate("voice-default-assistant").asRoot().isOverlay().withParent(RouteName.MAIN).withContentMode(7).build());
        routingRegistry.register(route("ampd-handsfree-settings", 10).withTemplate("ampd-handsfree-settings").asRoot().isOverlay().withParent(RouteName.MAIN).withContentMode(7).build());
        routingRegistry.register(route("ampd-handsfree-setup", 10).withTemplate("ampd-handsfree-setup").asRoot().isOverlay().withParent(RouteName.MAIN).withContentMode(7).build());
        routingRegistry.register(route("ampd-handsfree-oobe", 10).withTemplate("ampd-handsfree-oobe").asRoot().isOverlay().withParent(RouteName.MAIN).withContentMode(7).build());
        routingRegistry.register(route("ampd-handsfree-ftue", 10).withTemplate("ampd-handsfree-ftue").asRoot().isOverlay().withParent(RouteName.MAIN).withContentMode(7).build());
        routingRegistry.register(route("voice-ftue", 5).withTemplate("voice-ftue").asRoot().isOverlay().withParent(RouteName.MAIN).withContentMode(7).build());
        routingRegistry.register(route("type-to-alexa", 5).withTemplate("type-to-alexa").asRoot().isOverlay().withParent(RouteName.MAIN).withContentMode(7).build());
        routingRegistry.register(route("voice", 5).withTemplate("voice").asRoot().isOverlay().withParent(RouteName.MAIN).withContentMode(7).build());
        GeneratedOutlineSupport1.outline144(route(RouteName.HANDSFREE_SETTINGS, 1), RouteName.MAIN, RouteUrls.HANDSFREE_SETTINGS, routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.LANGUAGE_SETTINGS, 1), RouteName.MAIN, RouteUrls.LANGUAGE_SETTINGS, routingRegistry);
    }

    private void registerWebRoutes(RoutingRegistry routingRegistry) {
        routingRegistry.register(route(RouteName.NOW_PLAYING, 0).withHeaderTitle(Integer.valueOf((int) R.string.header_title_nowPlaying)).withParent(RouteName.MAIN).withSingleChildRouteInBackStack().build());
        routingRegistry.register(route(RouteName.PLAYER_CURRENT, 0).asRoot().withTemplate("player").build()).withParentDefault(RouteName.NOW_PLAYING);
        GeneratedOutlineSupport1.outline144(route(RouteName.PLAYER_QUEUE, 0), RouteName.NOW_PLAYING, "player/queue", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.PLAYER_HISTORY, 0), RouteName.NOW_PLAYING, "player/history", routingRegistry);
        Route.Builder asRoot = route(RouteName.LISTS, 0).asRoot();
        Integer valueOf = Integer.valueOf((int) R.string.header_title_lists);
        routingRegistry.register(asRoot.withHeaderTitle(valueOf).withParent(RouteName.MAIN).withTemplate(RouteName.LISTS).withSingleChildRouteInBackStack().build());
        routingRegistry.register(route(RouteName.LIST_SHOPPING, 0).asRoot().withHeaderTitle(valueOf).withParent(RouteName.LISTS).withTemplate("lists/shopping").build());
        GeneratedOutlineSupport1.outline144(route(RouteName.LISTS_NL, 0), RouteName.LISTS, "lists/namedLists", routingRegistry);
        routingRegistry.register(route(RouteName.LIST_TODO, 0).asRoot().withHeaderTitle(valueOf).withParent(RouteName.LISTS).withTemplate("lists/todos").build());
        routingRegistry.register(route(RouteName.FIRST_RUN_TUTORIAL, 0).asRoot().withParent(RouteName.MAIN).withTemplate("first-run-tutorial/{path*}").withContentMode(0).build());
    }

    @VisibleForTesting
    static Route.Builder route(String str, int i) {
        return new Route.Builder(str, i).withPriority(i == 0 ? 70 : 50);
    }

    @Provides
    @ApplicationScope
    public RouteFeatureGroupRegistry provideGroupRoutingRegistry() {
        RouteFeatureGroupRegistry routeFeatureGroupRegistry = new RouteFeatureGroupRegistry();
        Route.Builder asRoot = route(RouteName.SMART_HOME, 1).asRoot();
        Integer valueOf = Integer.valueOf((int) R.string.header_title_smartHome);
        routeFeatureGroupRegistry.register(RouteUtils.createFeatureGroup("smart-home", "ELEMENTS_SMARTHOME", new Route[]{asRoot.withHeaderTitle(valueOf).withParent(RouteName.MAIN).withTemplate(RouteUrls.SMART_HOME_INDEX).withAlias("smart-home").build(), route(RouteName.SMART_HOME_DISCOVER, 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/smart-home/indexWithDiscovery/true").withAlias("appliances/discover").build()}, new Route[]{route(RouteName.SMART_HOME, 0).asRoot().withHeaderTitle(valueOf).withParent(RouteName.MAIN).withTemplate("smart-home").withAlias(RouteUrls.SMART_HOME_INDEX).build()}));
        routeFeatureGroupRegistry.register(RouteUtils.createFeatureGroup("smart-home-lemur", new String[]{"ELEMENTS_SMARTHOME", "ELEMENTS_LEMUR"}, new Route[]{route("v2/smart-home/lemur-redirect/create", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/smart-home/lemur-redirect/create").withAlias("lemur/newlemur").build(), route("v2/smart-home/lemur-redirect/list", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/smart-home/lemur-redirect/list").withAlias("lemur/lemurclaw").build()}));
        routeFeatureGroupRegistry.register(RouteUtils.createFeatureGroup("rn-comms", "ALEXA_MOBILE_APP_GENERIC_FEATURE_3", new Route[]{route(RouteName.REACT_NATIVE_COMMS, 1).asRoot().withParent("conversations").withTemplate("v2/comms/{route_parameter*}").withPriority(66).withAlias("rn-contacts-channels").build(), route(RouteName.REACT_NATIVE_COMMS, 1).asRoot().withParent("conversations").withContentMode(2).withAlias("rn-messaging-channels").build()}, new Route[]{route(RouteName.REACT_NATIVE_COMMS, 1).asRoot().withParent("conversations").withTemplate("v2/comms/{route_parameter*}").withPriority(66).withAlias("rn-contacts").withTheme(Route.Theme.LIGHT).build(), route(RouteName.REACT_NATIVE_COMMS, 1).asRoot().withParent("conversations").withContentMode(2).withTheme(Route.Theme.LIGHT).withAlias("rn-messaging").build()}));
        routeFeatureGroupRegistry.register(RouteUtils.createFeatureGroup("elements-settings-voice-purchasing", "ELEMENTS_SETTINGS_VOICE_PURCHASING", new Route[]{route("v2/settings/voice-purchasing", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/settings/voice-purchasing").withAlias("settings/voice-purchasing").withAlias("v2/settings/voice-purchasing").build()}, new Route[]{route(RouteName.ELEMENTS_SETTINGS_SHOPPING, 0).asRoot().withParent(RouteName.MAIN).withTemplate(RouteName.ELEMENTS_SETTINGS_SHOPPING).withAlias("v2/settings/shopping").withAlias("settings/voice-purchasing").build()}));
        routeFeatureGroupRegistry.register(RouteUtils.createFeatureGroup("elements-settings-kid-skills-settings", new String[]{"ELEMENTS_SETTINGS_VOICE_PURCHASING", "MONETIZATION_KIDS_PURCHASE_SETTINGS"}, new Route[]{route("v2/settings/kid-skills-settings", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/settings/kid-skills-settings").withAlias("settings/kid-skills-settings").withAlias("v2/settings/kid-skills-settings").build()}));
        routeFeatureGroupRegistry.register(RouteUtils.createFeatureGroup("elements-settings", "ALEXA_HHO_CONNECT_FEATURE", new Route[]{route(RouteName.ELEMENTS_SETTINGS_3P_ACCOUNT, 1).asRoot().withParent(RouteName.MAIN).withTemplate(RouteUrls.ELEMENTS_SETTINGS_3P_ACCOUNT).withAlias("settings/eon").withPriority(20).build()}));
        routeFeatureGroupRegistry.register(RouteUtils.createFeatureGroup(RouteName.VIDEO, "ALEXA_VIDEO_PLATFORM_COMPANION_APP_CHANNELS_MIGRATION", new Route[]{route(RouteName.VIDEO, 1).asRoot().withParent(RouteName.MAIN).withTemplate(RouteUrls.VIDEO_INDEX).withAlias("videos/index").withPriority(5).build(), route(RouteName.VIDEO, 1).asRoot().withParent(RouteName.MAIN).withTemplate(RouteUrls.VIDEO_NESTED).withAlias("videos/{path*}").withPriority(5).build()}));
        routeFeatureGroupRegistry.register(RouteUtils.createFeatureGroup("elements-reminders-alarms-timers", "ELEMENTS_RAT_ANDROID", new Route[]{route("v2/reminders-alarms-timers", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/reminders-alarms-timers").withAlias("timersAndAlarms").build(), route(RouteName.REMINDERS, 1).asRoot().withHeaderTitle(Integer.valueOf((int) R.string.header_title_reminders)).withParent(RouteName.MAIN).withTemplate(RouteUrls.ELEMENTS_REMINDERS).withAlias("timersAndAlarms/reminders").withAlias("timersAndAlarms/reminders?ref={ref}").build(), route(RouteName.ALARMS, 1).asRoot().withHeaderTitle(Integer.valueOf((int) R.string.header_title_alarms)).withParent(RouteName.MAIN).withTemplate(RouteUrls.ELEMENTS_ALARMS).withAlias("timersAndAlarms/alarms").withAlias("timersAndAlarms/alarms?ref={ref}").build(), route("timers", 1).asRoot().withHeaderTitle(Integer.valueOf((int) R.string.header_title_timers)).withParent(RouteName.MAIN).withTemplate(RouteUrls.ELEMENTS_TIMERS).withAlias("timersAndAlarms/timers").withAlias("timersAndAlarms/timers?ref={ref}").build()}));
        routeFeatureGroupRegistry.register(RouteUtils.createFeatureGroup("elements-locations", "ELEMENTS_LOCATIONS", new Route[]{route("locations/index", 1).asRoot().withParent(RouteName.MAIN).withTemplate("locations/index").withAlias("locations").build()}));
        routeFeatureGroupRegistry.register(RouteUtils.createFeatureGroup("home", "CH_HOME_VIEW_MANAGER_ANDROID", new Route[]{route(RouteName.HOME, HOME_VIEW_FACTORY).asRoot().withHeaderTitle(Integer.valueOf((int) R.string.header_title_home)).withParent(RouteName.MAIN).withAlias("v2/home").withContentMode(3).build()}, new Route[]{route(RouteName.HOME, 6).asRoot().withHeaderTitle(Integer.valueOf((int) R.string.header_title_home)).withParent(RouteName.MAIN).withAlias("v2/home").withContentMode(3).build()}));
        return routeFeatureGroupRegistry;
    }

    @Provides
    @ApplicationScope
    public RouteWatcher provideRouteWatcher(IdentityService identityService, RoutingRegistry routingRegistry, RouteFeatureGroupRegistry routeFeatureGroupRegistry, EventBus eventBus) {
        return new RouteWatcher(identityService, routingRegistry, routeFeatureGroupRegistry, eventBus);
    }

    @Provides
    @ApplicationScope
    public RoutingRegistry provideRoutingRegistry() {
        RoutingRegistry routingRegistry = (RoutingRegistry) GeneratedOutlineSupport1.outline20(RoutingRegistry.class);
        registerMainRoutes(routingRegistry);
        registerConversationRoutes(routingRegistry);
        registerWebRoutes(routingRegistry);
        registerVoiceRoutes(routingRegistry);
        registerBatteryOptimizationRoutes(routingRegistry);
        registerVoiceEnrollmentRoutes(routingRegistry);
        registerDevicePickerRoutes(routingRegistry);
        GeneratedOutlineSupport1.outline144(route("v2/lists", 1), RouteName.MAIN, "v2/lists", routingRegistry);
        GeneratedOutlineSupport1.outline144(route("v2/lists/archived", 1), "v2/lists", "v2/lists/archived", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.ELEMENTS_NAMED_LISTS_DETAIL, 1), RouteName.MAIN, "v2/lists/namedlists/{path}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.ELEMENTS_DEFAULT_LISTS_DETAIL, 1), RouteName.MAIN, "v2/lists/redirect/{path}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route("v2/reminders-alarms-timers", 1), RouteName.MAIN, "v2/reminders-alarms-timers", routingRegistry);
        GeneratedOutlineSupport1.outline144(route("v2/reminders-alarms-timers/index-push/push", 1), RouteName.MAIN, "v2/reminders-alarms-timers/index-push/push", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.ELEMENTS_REMINDERS_DETAIL, 1), RouteName.MAIN, "v2/reminders-alarms-timers/reminders-detail/{path}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.ELEMENTS_ALARM_EDIT, 1), RouteName.MAIN, "v2/reminders-alarms-timers/alarm-edit/{path}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.ELEMENTS_TIMER_DETAIL, 1), RouteName.MAIN, "v2/reminders-alarms-timers/timer-detail/{path}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route("v2/guided-discovery/add-devices", 1), RouteName.MAIN, "v2/guided-discovery/add-devices", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.ACCESSORY_SETUP, 1), RouteName.MAIN, RouteUrls.ACCESSORY_SETUP, routingRegistry);
        GeneratedOutlineSupport1.outline144(route("v2/behaviors", 1), RouteName.MAIN, "v2/behaviors", routingRegistry);
        routingRegistry.register(route("v2/homefeed", 1).asRoot().withParent(RouteName.MAIN).withAlias("cards").withAlias(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME).withTemplate("v2/homefeed").build());
        GeneratedOutlineSupport1.outline144(route("v2/device-settings", 1), RouteName.MAIN, "v2/device-settings", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.ELEMENTS_DEVICE_SETTINGS_DEEP_LINK, 1), RouteName.MAIN, "v2/device-settings/legacy/{path}", routingRegistry);
        routingRegistry.register(route("v2/settings/notifications/drive-mode", 1).asRoot().withAlias("drive-mode/notification").withParent(RouteName.MAIN).withTemplate("v2/settings/notifications/drive-mode").build());
        registerAlexaDeviceSetupRoutes(routingRegistry);
        registerIdentityServicesRoutes(routingRegistry);
        routingRegistry.register(route(RouteName.ELEMENTS_SETTINGS, 1).asRoot().withParent(RouteName.MAIN).build());
        GeneratedOutlineSupport1.outline144(route(RouteName.ELEMENTS_SETTINGS_DEEP_LINK, 1), RouteName.MAIN, "v2/settings/legacy/{path}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.NOW_PLAYING_ELEMENTS, 1), RouteName.MAIN, RouteUrls.NOW_PLAYING, routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.MUSIC_ELEMENTS, 1), RouteName.MAIN, RouteUrls.MUSIC_ELEMENTS, routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.CHANNELS_DEVICES, 1), RouteName.MAIN, RouteUrls.CHANNELS_DEVICES, routingRegistry);
        GeneratedOutlineSupport1.outline144(route("v2/devices-channel/control-panel/space", 1), RouteName.MAIN, "v2/devices-channel/control-panel/space/{groupId}", routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.CHANNELS_ENTERTAINMENT, 1), RouteName.MAIN, RouteUrls.CHANNELS_ENTERTAINMENT, routingRegistry);
        GeneratedOutlineSupport1.outline144(route(RouteName.COMMUNICATIONS_SETTING, 1), RouteName.MAIN, "v2/device-settings/communications/{deviceSerial}/{deviceType}", routingRegistry);
        registerCommsReactNativeRoutes(routingRegistry);
        registerSmartHomeRoutes(routingRegistry);
        routingRegistry.register(route("rn", 1).asRoot().withParent(RouteName.MAIN).withTemplate("v2/{route_parameter*}").withPriority(69).build());
        registerBilobaRoutes(routingRegistry);
        registerSmartHomeCamerasRoutes(routingRegistry);
        registerDriveModeRoutes(routingRegistry);
        return routingRegistry;
    }

    @VisibleForTesting
    static Route.Builder route(String str, String str2) {
        return new Route.Builder(str, str2);
    }
}
