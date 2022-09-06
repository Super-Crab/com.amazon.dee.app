package com.amazon.dee.app.elements;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.routing.api.ParameterMapper;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.data.RouteName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes12.dex */
public class ReactRoute {
    static final String ROUTE_ALIASES = "aliases";
    static final String ROUTE_FEATURE = "feature";
    static final String ROUTE_LAYER = "layer";
    static final String ROUTE_MODULE = "module";
    static final String ROUTE_NAME = "name";
    static final String ROUTE_PARENT = "parent";
    static final String ROUTE_PRIORITY = "priority";
    static final String ROUTE_SUPPORTS_DELEGATED_PROFILE = "supportsDelegatedProfile";
    static final String ROUTE_TEMPLATE = "template";
    ArrayList<ReactRouteAlias> aliases = new ArrayList<>();
    String feature;
    String layer;
    String module;
    String name;
    String parent;
    int priority;
    boolean supportsDelegatedProfile;
    String uri;

    public ReactRoute(@NonNull Map map) {
        this.module = null;
        this.feature = null;
        this.parent = null;
        String str = map.containsKey("name") ? (String) map.get("name") : null;
        String str2 = map.containsKey("template") ? (String) map.get("template") : null;
        if (str != null && str2 != null) {
            this.name = str;
            this.uri = str2;
            this.parent = ElementsUtils.getOrDefault(map, "parent", (String) null);
            this.priority = ElementsUtils.getOrDefault(map, "priority", 50);
            this.feature = ElementsUtils.getOrDefault(map, "feature", (String) null);
            this.module = ElementsUtils.getOrDefault(map, "module", "unknown");
            this.layer = ElementsUtils.getOrDefault(map, ROUTE_LAYER, "root");
            this.supportsDelegatedProfile = ElementsUtils.getOrDefault(map, ROUTE_SUPPORTS_DELEGATED_PROFILE, false);
            if (!map.containsKey(ROUTE_ALIASES)) {
                return;
            }
            Iterator it2 = ((ArrayList) map.get(ROUTE_ALIASES)).iterator();
            while (it2.hasNext()) {
                this.aliases.add(new ReactRouteAlias((Map) it2.next()));
            }
            return;
        }
        throw new IllegalArgumentException("Malformed route registration: missing name or template");
    }

    public Route toRoute() {
        int i = 1;
        Route.Builder builder = new Route.Builder(this.name, 1);
        builder.withTemplate(this.uri);
        String str = this.parent;
        if (str != null) {
            builder.withParent(str);
        }
        String str2 = this.module;
        if (str2 != null) {
            builder.withModule(str2);
        }
        builder.withParent(RouteName.MAIN);
        if (!"root".equals(this.layer)) {
            i = 2;
        }
        builder.withContentMode(i);
        builder.withPriority(this.priority);
        if (this.supportsDelegatedProfile) {
            builder.supportsDelegatedProfile();
        }
        Iterator<ReactRouteAlias> it2 = this.aliases.iterator();
        while (it2.hasNext()) {
            final ReactRouteAlias next = it2.next();
            builder.withAlias(next.uri, next.priority, new ParameterMapper() { // from class: com.amazon.dee.app.elements.-$$Lambda$ReactRoute$3J_dSnZE6mA5vOLvV_dSRRrFBls
                @Override // com.amazon.alexa.routing.api.ParameterMapper
                public final Bundle map(Bundle bundle) {
                    return ReactRouteAlias.this.transformProps(bundle);
                }
            });
        }
        return builder.build();
    }
}
