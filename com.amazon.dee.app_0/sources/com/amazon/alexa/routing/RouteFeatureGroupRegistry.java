package com.amazon.alexa.routing;

import android.text.TextUtils;
import com.amazon.alexa.eventing.Event;
import com.amazon.alexa.eventing.EventArgs;
import com.amazon.alexa.routing.api.RouteFeatureGroup;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class RouteFeatureGroupRegistry {
    private static final String TAG = "RouteFeatureGroupRegistry";
    private final List<RouteFeatureGroup> groups = new ArrayList();
    private final Event<RouteFeatureGroup> onGroupRouteAdded = new Event<>();

    public void clear() {
        this.groups.clear();
    }

    public RouteFeatureGroup getGroup(String str) {
        for (RouteFeatureGroup routeFeatureGroup : this.groups) {
            if (TextUtils.equals(routeFeatureGroup.getName(), str)) {
                return routeFeatureGroup;
            }
        }
        return null;
    }

    public List<RouteFeatureGroup> getGroups() {
        return this.groups;
    }

    public Event<RouteFeatureGroup> onGroupRouteAdded() {
        return this.onGroupRouteAdded;
    }

    public void register(RouteFeatureGroup routeFeatureGroup) {
        this.groups.add(routeFeatureGroup);
        this.onGroupRouteAdded.fire(EventArgs.of(routeFeatureGroup));
    }
}
