package com.amazon.dee.app.elements;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class ReactRouteAlias {
    static final String PRIORITY = "priority";
    static final String PROPS = "props";
    static final String TEMPLATE = "template";
    int priority;
    HashMap<String, ReactRouteAliasProp> props;
    String uri;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public ReactRouteAlias(Map map) {
        if (map.containsKey("template") && map.containsKey("priority")) {
            this.uri = ElementsUtils.getOrDefault(map, "template", "");
            this.priority = ElementsUtils.getOrDefault(map, "priority", 50);
            this.props = new HashMap<>();
            if (!map.containsKey(PROPS)) {
                return;
            }
            for (Map.Entry entry : ((Map) map.get(PROPS)).entrySet()) {
                this.props.put(entry.getKey(), new ReactRouteAliasProp((String) entry.getValue()));
            }
            return;
        }
        throw new IllegalArgumentException("Malformed route alias");
    }

    public Bundle transformProps(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putAll(bundle);
        for (Map.Entry<String, ReactRouteAliasProp> entry : this.props.entrySet()) {
            String key = entry.getKey();
            ReactRouteAliasProp value = entry.getValue();
            int i = value.type;
            if (i == 0) {
                bundle2.putString(key, value.value);
            } else if (i == 1) {
                bundle2.putString(key, bundle.getString(value.value));
            }
        }
        return bundle2;
    }
}
