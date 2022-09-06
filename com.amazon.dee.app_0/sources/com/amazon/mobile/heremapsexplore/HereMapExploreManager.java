package com.amazon.mobile.heremapsexplore;

import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.mobile.heremapsexplore.Constants.Constants;
import com.amazon.mobile.heremapsexplore.Constants.Events;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class HereMapExploreManager extends ViewGroupManager<HereMapExploreView> {
    private static final int COMMAND_ADD_GEOFENCE = 1;
    private static final int COMMAND_NAVIGATE_TO_REGION = 3;
    private static final int COMMAND_RECENTER = 2;
    private static final String REACT_CLASS_NAME = "HereMapExplore";

    private boolean commandVerification(String str, ReadableArray readableArray, String str2, int i) {
        if (readableArray == null) {
            Log.w(Constants.LOG_TAG, String.format("Arguments of %s (%s) were not sent", str, str2));
            return false;
        } else if (readableArray.size() < i) {
            GeneratedOutlineSupport1.outline164("Too few arguments supplied for command ", str, Constants.LOG_TAG);
            return false;
        } else {
            boolean z = false;
            for (int i2 = 0; i2 < i; i2++) {
                if (readableArray.isNull(i2)) {
                    Log.w(Constants.LOG_TAG, String.format("%s called with argument %d being null", str, Integer.valueOf(i2)));
                    z = true;
                }
            }
            return !z;
        }
    }

    @ReactProp(name = ReactProperties.FOLLOWS_USER_LOCATION)
    public void followUserLocation(HereMapExploreView hereMapExploreView, boolean z) {
        if (hereMapExploreView.isMapReady()) {
            hereMapExploreView.followUserLocation(z);
        } else {
            hereMapExploreView.getInitialReactData().put(ReactProperties.FOLLOWS_USER_LOCATION, Boolean.valueOf(z));
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("addGeofenceToMap", 1);
        hashMap.put("recenter", 2);
        hashMap.put("navigateToRegion", 3);
        return hashMap;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put(Events.ON_GEOFENCE_UPDATED, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Events.ON_GEOFENCE_UPDATED))).put(Events.ON_GEOFENCE_UPDATING, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Events.ON_GEOFENCE_UPDATING))).put(Events.ON_MAP_READY, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Events.ON_MAP_READY))).put(Events.ON_REGION_CHANGE_COMPLETE, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Events.ON_REGION_CHANGE_COMPLETE))).put(Events.ON_PAN_AWAY, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Events.ON_PAN_AWAY))).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS_NAME;
    }

    @ReactProp(defaultDouble = 20.0d, name = ReactProperties.MAX_ZOOM_LEVEL)
    public void maxZoomLevel(HereMapExploreView hereMapExploreView, double d) {
        if (hereMapExploreView.isMapReady()) {
            hereMapExploreView.setMaxZoomLevel(d);
        } else {
            hereMapExploreView.getInitialReactData().put(ReactProperties.MAX_ZOOM_LEVEL, Double.valueOf(d));
        }
    }

    @ReactProp(defaultDouble = 5.0d, name = ReactProperties.MIN_ZOOM_LEVEL)
    public void minZoomLevel(HereMapExploreView hereMapExploreView, double d) {
        if (hereMapExploreView.isMapReady()) {
            hereMapExploreView.setMinZoomLevel(d);
        } else {
            hereMapExploreView.getInitialReactData().put(ReactProperties.MIN_ZOOM_LEVEL, Double.valueOf(d));
        }
    }

    @ReactProp(name = ReactProperties.GEOFENCE_RADIUS_RANGE)
    public void setGeofenceRadiusRange(HereMapExploreView hereMapExploreView, ReadableMap readableMap) {
        double d = readableMap.getDouble(ReactProperties.GEOFENCE_MINIMUM_RANGE);
        double d2 = readableMap.getDouble(ReactProperties.GEOFENCE_MAXIMUM_RANGE);
        if (hereMapExploreView.isMapReady()) {
            hereMapExploreView.setGeofenceRadiusRange(d, d2);
            return;
        }
        HashMap<String, Object> initialReactData = hereMapExploreView.getInitialReactData();
        initialReactData.put(ReactProperties.GEOFENCE_MINIMUM_RANGE, Double.valueOf(d));
        initialReactData.put(ReactProperties.GEOFENCE_MAXIMUM_RANGE, Double.valueOf(d2));
    }

    @ReactProp(name = ReactProperties.INITIAL_REGION)
    public void setInitialRegion(HereMapExploreView hereMapExploreView, ReadableMap readableMap) {
        double d = readableMap.getDouble("latitude");
        double d2 = readableMap.getDouble("longitude");
        HashMap<String, Object> initialReactData = hereMapExploreView.getInitialReactData();
        initialReactData.put("latitude", Double.valueOf(d));
        initialReactData.put("longitude", Double.valueOf(d2));
    }

    @ReactProp(name = ReactProperties.SCROLL_ENABLED)
    public void setMapScrolling(HereMapExploreView hereMapExploreView, boolean z) {
        if (hereMapExploreView.isMapReady()) {
            hereMapExploreView.setMapScrolling(z);
        } else {
            hereMapExploreView.getInitialReactData().put(ReactProperties.SCROLL_ENABLED, Boolean.valueOf(z));
        }
    }

    @ReactProp(name = "region")
    public void setRegion(HereMapExploreView hereMapExploreView, ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        if (hereMapExploreView.isMapReady()) {
            hereMapExploreView.navigateToCoordinates(readableMap);
        } else {
            hereMapExploreView.getInitialReactData().put("region", readableMap);
        }
    }

    @ReactProp(defaultDouble = 16.0d, name = ReactProperties.ZOOM_LEVEL)
    public void setZoomLevel(HereMapExploreView hereMapExploreView, double d) {
        if (hereMapExploreView.isMapReady()) {
            hereMapExploreView.setZoomLevel(d);
        } else {
            hereMapExploreView.getInitialReactData().put(ReactProperties.ZOOM_LEVEL, Double.valueOf(d));
        }
    }

    @ReactProp(name = ReactProperties.SHOWS_CENTER_MARKER)
    public void showCenterMarker(HereMapExploreView hereMapExploreView, boolean z) {
        if (hereMapExploreView.isMapReady()) {
            hereMapExploreView.showCenterMarker(z);
        } else {
            hereMapExploreView.getInitialReactData().put(ReactProperties.SHOWS_CENTER_MARKER, Boolean.valueOf(z));
        }
    }

    @ReactProp(name = ReactProperties.SHOWS_USER_LOCATION)
    public void showUserLocation(HereMapExploreView hereMapExploreView, boolean z) {
        if (hereMapExploreView.isMapReady()) {
            hereMapExploreView.showUserLocation(z);
        } else {
            hereMapExploreView.getInitialReactData().put(ReactProperties.SHOWS_USER_LOCATION, Boolean.valueOf(z));
        }
    }

    @ReactProp(name = ReactProperties.SHOWS_USER_LOCATION_ACCURACY)
    public void showUserLocationAccuracy(HereMapExploreView hereMapExploreView, boolean z) {
        if (hereMapExploreView.isMapReady()) {
            hereMapExploreView.showUserLocationAccuracy(z);
        } else {
            hereMapExploreView.getInitialReactData().put(ReactProperties.SHOWS_USER_LOCATION_ACCURACY, Boolean.valueOf(z));
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(HereMapExploreView hereMapExploreView, View view, int i) {
        hereMapExploreView.addSubView(view, i);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public HereMapExploreView mo12880createViewInstance(ThemedReactContext themedReactContext) {
        return new HereMapExploreView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(HereMapExploreView hereMapExploreView, int i) {
        return hereMapExploreView.getSubViewAt(i);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(HereMapExploreView hereMapExploreView) {
        return hereMapExploreView.getSubViewCount();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(HereMapExploreView hereMapExploreView, int i, @Nullable ReadableArray readableArray) {
        if (i == 1) {
            if (!commandVerification("AddGeofence", readableArray, "Coordinates and radius", 2)) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Adding geofence  with radius ");
            outline107.append(readableArray.getDouble(1));
            outline107.append(" by calling command ");
            outline107.append(1);
            Log.i(Constants.LOG_TAG, outline107.toString());
            hereMapExploreView.addGeofence(readableArray.mo6944getMap(0), readableArray.getDouble(1));
        } else if (i == 2) {
            if (!commandVerification("Recenter", readableArray, "", 0)) {
                return;
            }
            Log.i(Constants.LOG_TAG, "Recentering map to user location");
            hereMapExploreView.recenter();
        } else if (i != 3) {
            Log.w(Constants.LOG_TAG, "Receives unsupported command " + i);
        } else if (!commandVerification("NavigateToRegion", readableArray, "", 1)) {
        } else {
            Log.i(Constants.LOG_TAG, "Navigating to given region");
            hereMapExploreView.navigateToRegion(readableArray.mo6944getMap(0));
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(HereMapExploreView hereMapExploreView, int i) {
        hereMapExploreView.removeSubViewAt(i);
    }
}
