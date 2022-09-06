package com.amazon.dee.app.elements;

import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteFeatureGroup;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
/* loaded from: classes12.dex */
public class DynamicRouteFeatureGroup implements RouteFeatureGroup {
    private static final String TAG = "DynamicRouteFeatureGroup";
    String name = "elements-dynamic";
    ArrayList<String> features = new ArrayList<>();
    ArrayList<ReactRoute> reactRoutes = new ArrayList<>();

    public void add(ReactRoute reactRoute) {
        this.reactRoutes.add(reactRoute);
        updateFeatures();
    }

    @Override // com.amazon.alexa.routing.api.RouteFeatureGroup
    public String[] getFeatures() {
        ArrayList<String> arrayList = this.features;
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // com.amazon.alexa.routing.api.RouteFeatureGroup
    public String getName() {
        return this.name;
    }

    @Override // com.amazon.alexa.routing.api.RouteFeatureGroup
    public Route[] getRoutes(Set<String> set) {
        ArrayList arrayList = new ArrayList();
        Iterator<ReactRoute> it2 = this.reactRoutes.iterator();
        while (it2.hasNext()) {
            ReactRoute next = it2.next();
            String str = next.feature;
            if (str == null || set.contains(str)) {
                try {
                    arrayList.add(next.toRoute());
                } catch (IllegalArgumentException e) {
                    String str2 = TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IllegalArgumentException caught while adding route ");
                    outline107.append(next.uri);
                    Log.e(str2, e, outline107.toString(), new Object[0]);
                }
            }
        }
        return (Route[]) arrayList.toArray(new Route[arrayList.size()]);
    }

    public void remove(String str) {
        this.reactRoutes.remove(str);
        updateFeatures();
    }

    public void updateFeatures() {
        this.features = new ArrayList<>();
        this.features.add("ELEMENTS");
        Iterator<ReactRoute> it2 = this.reactRoutes.iterator();
        while (it2.hasNext()) {
            String str = it2.next().feature;
            if (str != null) {
                this.features.add(str);
            }
        }
    }
}
