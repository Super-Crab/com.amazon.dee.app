package com.amazon.dee.app.ui.main;

import com.amazon.alexa.routing.data.RouteName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
final class RouteCategories {
    static final List<String> ROUTES_WITH_CACHED_HEADERS = Arrays.asList(RouteName.NOW_PLAYING, RouteName.PLAYER_CURRENT, RouteName.PLAYER_HISTORY, RouteName.PLAYER_QUEUE, RouteName.MUSIC_BOOKS, RouteName.ALARMS, RouteName.REMINDERS, "timers", RouteName.TIMERS_ALARMS, RouteName.AMAZON_MY_MUSIC);
    static final ArrayList<String> NON_TAB_ROUTES = new ArrayList<>(Arrays.asList(RouteName.HELP, RouteName.ELEMENTS_A2S_FRONT_PAGE, "thingstotry", RouteName.LISTS, RouteName.MUSIC_BOOKS, RouteName.THINGS_TO_TRY_ELEMENTS, "v2/reminders-alarms-timers", RouteName.ELEMENTS_SETTINGS, "v2/lists", "v2/behaviors", "v2/homefeed", RouteName.SMART_HOME, RouteName.TIMERS_ALARMS));
    static final ArrayList<String> NOW_PLAYING_ROUTES = new ArrayList<>(Arrays.asList(RouteName.CHANNELS_ENTERTAINMENT, RouteName.CHANNELS_ENTERTAINMENT_BROWSE, RouteName.NOW_PLAYING_ELEMENTS, RouteName.NOW_PLAYING));

    private RouteCategories() {
    }
}
