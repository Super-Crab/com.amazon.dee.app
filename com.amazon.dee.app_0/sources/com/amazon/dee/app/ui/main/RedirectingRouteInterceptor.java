package com.amazon.dee.app.ui.main;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RouteTemplate;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.routing.data.RouteUrls;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public class RedirectingRouteInterceptor implements RoutingService.RouteInterceptor {
    private static final String ALARMS_WEB_ROUTE = "alarms";
    private static final String DEVICE_SETTINGS_WEB_ROUTE = "settings/device/";
    private static final String GADGETS = "gadgets";
    private static final String LISTS_WEB_ROUTE = "lists";
    private static final String LIST_NL_ARCHIVED_WEB_ROUTE = "lists/namedLists/archiveLists";
    private static final String LIST_NL_WEB_ROUTE = "lists/namedListItems";
    private static final String LIST_SHOPPING_DETAIL_SECONDARY_WEB_ROUTE = "todos/shopping";
    private static final String LIST_SHOPPING_DETAIL_WEB_ROUTE = "lists/shopping";
    private static final String LIST_TODOS_DETAIL_SECONDARY_WEB_ROUTE = "todos";
    private static final String LIST_TODOS_DETAIL_WEB_ROUTE = "lists/todos";
    private static final String RAT_WEB_ROUTE = "timersandalarms";
    private static final String REMINDERS_WEB_ROUTE = "reminders";
    private static final String SETTINGS_CALENDAR = "settings/eon";
    private static final String SETTINGS_FLASH_BRIEFING = "settings/flash-briefing";
    private static final String SETTINGS_LISTS = "settings/lists";
    private static final String SETTINGS_LOCATIONS = "locations/index";
    private static final String SETTINGS_MUSICMEDIA = "settings/music-settings";
    private static final String SETTINGS_NOTIFICATIONS = "settings/notifications";
    private static final String SETTINGS_SPORTS_UPDATE = "settings/sports-update";
    private static final String SETTINGS_TERSE = "settings/voice-responses";
    private static final String SETTINGS_TRAFFIC = "settings/traffic";
    private static final String SETTINGS_VOICECAST = "settings/voicecast";
    private static final String SETTINGS_WEB_ROUTE = "settings";
    private static final String TIMERS_WEB_ROUTE = "timers";
    final Context context;
    Supplier<ElementsFeatureEnablement> elementsFeatureEnablementSupplier;
    EnvironmentService environmentService;
    RoutingService routingService;
    private static final String[] DEVICE_SETTINGS_BLACKLIST_VALUES = {"bluetoothPairing", "bluetooth", "remotePairing", "homePhoneNumber", "providerPreference", "alphada"};
    private static final Set<String> DEVICE_SETTINGS_DEEPLINK_BLACKLIST = new HashSet(Arrays.asList(DEVICE_SETTINGS_BLACKLIST_VALUES));
    private static final String TAG = Log.tag(RedirectingRouteInterceptor.class);

    public RedirectingRouteInterceptor(@NonNull Context context, @NonNull RoutingService routingService, @NonNull Supplier<ElementsFeatureEnablement> supplier, @NonNull EnvironmentService environmentService) {
        Preconditions.checkNotNull(routingService);
        Preconditions.checkNotNull(supplier);
        this.context = context;
        this.routingService = routingService;
        this.elementsFeatureEnablementSupplier = supplier;
        this.environmentService = environmentService;
    }

    private String getPath(@NonNull String str) {
        String path = Uri.parse(str).getPath();
        return path == null ? str : path;
    }

    private String getQueryString(@NonNull String str) {
        String query = Uri.parse(str).getQuery();
        return query == null ? "" : query;
    }

    private boolean handleChannels(RouteContext routeContext, ElementsFeatureEnablement elementsFeatureEnablement) {
        routeContext.getRoute();
        if (elementsFeatureEnablement.isElementsEnabled && elementsFeatureEnablement.isChannelsDevicesEnabled && routeContext.toUri().equals(RouteUrls.SMART_HOME_INDEX)) {
            this.routingService.route(RouteName.CHANNELS_DEVICES).with(RouteParameter.ROUTE, RouteUrls.CHANNELS_DEVICES).clearBackStack().forceNavigate();
            return false;
        }
        return true;
    }

    private boolean handleDeviceSettingsDeeplink(@NonNull String str) {
        if (str.startsWith(DEVICE_SETTINGS_WEB_ROUTE)) {
            String substring = str.substring(16);
            if (!shouldDeepLinkToDeviceSettings(substring)) {
                return false;
            }
            this.routingService.route(RouteName.ELEMENTS_DEVICE_SETTINGS_DEEP_LINK).with(RouteParameter.PATH, substring).addToBackStack().navigate();
            return true;
        }
        return false;
    }

    private boolean handleSettingsDeeplink(@NonNull String str, ElementsFeatureEnablement elementsFeatureEnablement) {
        if (str.equals("settings")) {
            this.routingService.route(RouteName.ELEMENTS_SETTINGS).with(RouteParameter.PATH, str).addToBackStack().navigate();
            return true;
        } else if (str.startsWith(SETTINGS_NOTIFICATIONS)) {
            if (!elementsFeatureEnablement.isSettingsNotificationsEnabled) {
                return false;
            }
            this.routingService.route(RouteName.ELEMENTS_SETTINGS_DEEP_LINK).with(RouteParameter.PATH, str).addToBackStack().navigate();
            return true;
        } else if (str.startsWith("settings/flash-briefing")) {
            if (!elementsFeatureEnablement.isSettingsFlashBriefingEnabled) {
                return false;
            }
            this.routingService.route(RouteName.ELEMENTS_SETTINGS_DEEP_LINK).with(RouteParameter.PATH, str).addToBackStack().navigate();
            return true;
        } else if (str.startsWith(SETTINGS_VOICECAST)) {
            if (!elementsFeatureEnablement.isSettingsVoicecastEnabled) {
                return false;
            }
            this.routingService.route(RouteName.ELEMENTS_SETTINGS_DEEP_LINK).with(RouteParameter.PATH, str).addToBackStack().navigate();
            return true;
        } else if (str.startsWith(SETTINGS_TERSE)) {
            if (!elementsFeatureEnablement.isSettingsTerseEnabled) {
                return false;
            }
            this.routingService.route(RouteName.ELEMENTS_SETTINGS_DEEP_LINK).with(RouteParameter.PATH, str).addToBackStack().navigate();
            return true;
        } else if (str.startsWith(SETTINGS_LISTS)) {
            if (!elementsFeatureEnablement.isSettingsListsEnabled) {
                return false;
            }
            this.routingService.route(RouteName.ELEMENTS_SETTINGS_DEEP_LINK).with(RouteParameter.PATH, str).addToBackStack().navigate();
            return true;
        } else if (str.startsWith(SETTINGS_TRAFFIC)) {
            if (!elementsFeatureEnablement.isSettingsTrafficEnabled) {
                return false;
            }
            this.routingService.route(RouteName.ELEMENTS_SETTINGS_DEEP_LINK).with(RouteParameter.PATH, str).addToBackStack().navigate();
            return true;
        } else if (str.startsWith(SETTINGS_SPORTS_UPDATE)) {
            if (!elementsFeatureEnablement.isSettingsSportsUpdateEnabled) {
                return false;
            }
            this.routingService.route(RouteName.ELEMENTS_SETTINGS_DEEP_LINK).with(RouteParameter.PATH, str).addToBackStack().navigate();
            return true;
        } else if (str.equalsIgnoreCase(SETTINGS_CALENDAR)) {
            if (elementsFeatureEnablement.isSettingsEmailAndCalendarEnabled) {
                this.routingService.route(RouteName.ELEMENTS_SETTINGS_3P_ACCOUNT).with(RouteParameter.PATH, str).addToBackStack().navigate();
                return true;
            } else if (!elementsFeatureEnablement.isSettingsCalendarEnabled) {
                return false;
            } else {
                this.routingService.route(RouteName.ELEMENTS_SETTINGS_DEEP_LINK).with(RouteParameter.PATH, str).addToBackStack().navigate();
                return true;
            }
        } else if (str.startsWith(SETTINGS_MUSICMEDIA)) {
            if (!elementsFeatureEnablement.isSettingsMusicMediaEnabled) {
                return false;
            }
            this.routingService.route(RouteName.ELEMENTS_SETTINGS_DEEP_LINK).with(RouteParameter.PATH, str).addToBackStack().navigate();
            return true;
        } else if (!str.startsWith("locations/index") || !elementsFeatureEnablement.isSettingsLocationsEnabled) {
            return false;
        } else {
            this.routingService.route("locations/index").with(RouteParameter.PATH, str).addToBackStack().navigate();
            return true;
        }
    }

    private boolean isHandledByListsRoute(@NonNull String str) {
        if (LIST_NL_ARCHIVED_WEB_ROUTE.equalsIgnoreCase(str)) {
            GeneratedOutlineSupport1.outline145(this.routingService, "v2/lists/archived");
            return true;
        } else if (!LIST_SHOPPING_DETAIL_WEB_ROUTE.equalsIgnoreCase(str) && !LIST_SHOPPING_DETAIL_SECONDARY_WEB_ROUTE.equalsIgnoreCase(str)) {
            if (!LIST_TODOS_DETAIL_WEB_ROUTE.equalsIgnoreCase(str) && !LIST_TODOS_DETAIL_SECONDARY_WEB_ROUTE.equalsIgnoreCase(str)) {
                if (str.startsWith(LIST_NL_WEB_ROUTE)) {
                    String[] split = str.split("/");
                    if (split.length >= 4 && !split[2].isEmpty()) {
                        this.routingService.route(RouteName.ELEMENTS_NAMED_LISTS_DETAIL).with(RouteParameter.PATH, split[2]).addToBackStack().navigate();
                        return true;
                    }
                    GeneratedOutlineSupport1.outline145(this.routingService, "v2/lists");
                    return false;
                } else if (!str.toLowerCase().startsWith("lists")) {
                    return false;
                } else {
                    GeneratedOutlineSupport1.outline145(this.routingService, "v2/lists");
                    return true;
                }
            }
            this.routingService.route(RouteName.ELEMENTS_DEFAULT_LISTS_DETAIL).with(RouteParameter.PATH, "TO_DO").addToBackStack().navigate();
            return true;
        } else {
            this.routingService.route(RouteName.ELEMENTS_DEFAULT_LISTS_DETAIL).with(RouteParameter.PATH, "SHOPPING_LIST").addToBackStack().navigate();
            return true;
        }
    }

    private boolean isWebListsRoute(Route route) {
        return route.is(RouteName.LISTS_NL) || route.is("lists") || route.is(RouteName.LIST_TODO) || route.is(RouteName.ELEMENTS_NAMED_LISTS_DETAIL) || route.is(RouteName.LIST_SHOPPING);
    }

    private boolean redirectFromWeb(RouteContext routeContext, ElementsFeatureEnablement elementsFeatureEnablement) {
        RouteTemplate template;
        RouteTemplate template2;
        if (isWebListsRoute(routeContext.getRoute())) {
            RouteTemplate template3 = routeContext.getRoute().getTemplate();
            String str = "Inside list cond: " + template3;
            if (template3 != null && elementsFeatureEnablement.isListsEnabled && isHandledByListsRoute(template3.toString())) {
                return false;
            }
        }
        if (routeContext.getRoute().is("web")) {
            String string = routeContext.getString(RouteParameter.ROUTE);
            if (elementsFeatureEnablement.isListsEnabled && isHandledByListsRoute(string)) {
                return false;
            }
            if (elementsFeatureEnablement.isRemindersAlarmsTimersEnabled && string.toLowerCase().startsWith(RAT_WEB_ROUTE)) {
                String[] split = string.split("/");
                if (split.length < 4) {
                    GeneratedOutlineSupport1.outline145(this.routingService, "v2/reminders-alarms-timers/index-push/push");
                    return false;
                }
                String str2 = split[1];
                if (str2.equalsIgnoreCase("reminders")) {
                    this.routingService.route(RouteName.ELEMENTS_REMINDERS_DETAIL).with(RouteParameter.PATH, split[3]).addToBackStack().navigate();
                    return false;
                } else if (str2.equalsIgnoreCase("alarms")) {
                    this.routingService.route(RouteName.ELEMENTS_ALARM_EDIT).with(RouteParameter.PATH, split[3]).addToBackStack().navigate();
                    return false;
                } else if (str2.equalsIgnoreCase("timers")) {
                    this.routingService.route(RouteName.ELEMENTS_TIMER_DETAIL).with(RouteParameter.PATH, split[3]).addToBackStack().navigate();
                    return false;
                }
            }
            if (elementsFeatureEnablement.isDeviceSettingsEnabled && string != null && handleDeviceSettingsDeeplink(string)) {
                return false;
            }
            if (elementsFeatureEnablement.isSettingsEnabled && string != null && handleSettingsDeeplink(string, elementsFeatureEnablement)) {
                return false;
            }
        }
        if (!elementsFeatureEnablement.isSettingsEnabled || (template2 = routeContext.getRoute().getTemplate()) == null || !elementsFeatureEnablement.isSettingsEnabled || !handleSettingsDeeplink(template2.toString(), elementsFeatureEnablement)) {
            return !elementsFeatureEnablement.isDeviceSettingsEnabled || (template = routeContext.getRoute().getTemplate()) == null || !elementsFeatureEnablement.isDeviceSettingsEnabled || !handleDeviceSettingsDeeplink(template.toString());
        }
        return false;
    }

    private boolean redirectRoutesToWeb(RouteContext routeContext, ElementsFeatureEnablement elementsFeatureEnablement) {
        if (!elementsFeatureEnablement.smartHome && routeContext.getRoute().is(RouteName.SMART_HOME)) {
            this.routingService.route("web").with(RouteParameter.ROUTE, "smart-home").addToBackStack().navigate();
            return false;
        } else if (elementsFeatureEnablement.isNowPlayingEnabled || !routeContext.getRoute().is(RouteName.NOW_PLAYING_ELEMENTS)) {
            return true;
        } else {
            this.routingService.navigate(RouteName.NOW_PLAYING);
            return false;
        }
    }

    private boolean shouldDeepLinkToDeviceSettings(@NonNull String str) {
        if (str != null) {
            String[] split = str.split("/");
            return split.length > 0 && !str.contains(GADGETS) && !DEVICE_SETTINGS_DEEPLINK_BLACKLIST.contains(split[0]);
        }
        return false;
    }

    @Override // com.amazon.alexa.routing.api.RoutingService.RouteInterceptor
    public boolean onRouteChanging(RouteContext routeContext) {
        Preconditions.checkNotNull(routeContext);
        ElementsFeatureEnablement mo8291get = this.elementsFeatureEnablementSupplier.mo8291get();
        if (mo8291get == null) {
            Log.w(TAG, "ElementsFeatureEnablement not available");
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Elements Enabled: ");
            outline107.append(mo8291get.isElementsEnabled);
            outline107.toString();
            String str = "SmartHome Enabled: " + mo8291get.smartHome;
            String str2 = "Lemur Enabled: " + mo8291get.lemur;
            String str3 = "Lists Enabled: " + mo8291get.isListsEnabled;
        }
        return mo8291get == null || (handleChannels(routeContext, mo8291get) && redirectFromWeb(routeContext, mo8291get) && redirectRoutesToWeb(routeContext, mo8291get));
    }
}
