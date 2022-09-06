package com.amazon.mobile.heremapsexplore;

import android.graphics.Rect;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.mobile.heremapsexplore.Components.HereMapCircle;
import com.amazon.mobile.heremapsexplore.Components.HereMapMarker;
import com.amazon.mobile.heremapsexplore.Constants.Constants;
import com.amazon.mobile.heremapsexplore.Constants.Events;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.mobile.heremapsexplore.Constants.ResponseFields;
import com.amazon.mobile.heremapsexplore.Listeners.GeofenceMapCameraListener;
import com.amazon.mobile.heremapsexplore.Listeners.LoadSceneListener;
import com.amazon.mobile.heremapsexplore.Listeners.OnMapViewReadyListener;
import com.amazon.mobile.heremapsexplore.Listeners.PanGestureListener;
import com.amazon.mobile.heremapsexplore.Listeners.PinchRotateGestureListener;
import com.amazon.mobile.heremapsexplore.Listeners.TapGestureListener;
import com.amazon.mobile.heremapsexplore.MapObjects.CustomMapMarker;
import com.amazon.mobile.heremapsexplore.Utilities.ReactResponses;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.here.sdk.core.Angle;
import com.here.sdk.core.GeoBox;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Point2D;
import com.here.sdk.core.engine.SDKNativeEngine;
import com.here.sdk.engine.InitProvider;
import com.here.sdk.gestures.GestureState;
import com.here.sdk.gestures.GestureType;
import com.here.sdk.mapview.MapCamera;
import com.here.sdk.mapview.MapCameraLimits;
import com.here.sdk.mapview.MapMarker;
import com.here.sdk.mapview.MapPolygon;
import com.here.sdk.mapview.MapScheme;
import com.here.sdk.mapview.MapView;
import com.here.sdk.mapview.MapViewBase;
import com.here.sdk.mapview.PickMapItemsResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class HereMapExploreView extends RelativeLayout implements LifecycleEventListener {
    static final double DEFAULT_MAXIMUM_ZOOM_LEVEL = 20.0d;
    static final double DEFAULT_MINIMUM_ZOOM_LEVEL = 5.0d;
    static final double DEFAULT_ZOOM_LEVEL = 16.0d;
    private static final String FEATURE_USE_CLD_FOR_TRACKING = "LOCATION_ANDROID_USE_CLD_IN_MAP_VIEW";
    private final float INITIAL_BOUNDING_BOX_SIZE_FACTOR;
    private final double METERS_TO_GEO;
    private double currentGeofenceRadius;
    private boolean followUserLocation;
    private GeoCoordinates geofenceAnchorPoint;
    private GeofenceHandleView geofenceHandleView;
    private GeofenceView geofenceView;
    private GeoCoordinates initialCoordinates;
    private HashMap<String, Object> initialReactData;
    private boolean isMapReady;
    private LastGesture lastGesture;
    private MapCamera mapCamera;
    private MapView mapView;
    private final Map<CustomMapMarker, HereMapMarker> markerMap;
    private double maxGeofenceRadius;
    private double maximumZoomLevel;
    private double minGeofenceRadius;
    private double minimumZoomLevel;
    private ImageView pinMarker;
    private ThemedReactContext reactContext;
    private boolean showUserLocation;
    private boolean showUserLocationAccuracy;
    private List<View> subViews;
    private UserPositionManager userPositionManager;

    /* loaded from: classes13.dex */
    private enum LastGesture {
        NONE,
        PAN
    }

    public HereMapExploreView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        this.isMapReady = false;
        this.initialReactData = new HashMap<>();
        this.lastGesture = LastGesture.NONE;
        this.showUserLocation = false;
        this.showUserLocationAccuracy = false;
        this.followUserLocation = false;
        this.geofenceView = null;
        this.geofenceHandleView = null;
        this.pinMarker = null;
        this.geofenceAnchorPoint = null;
        this.subViews = new ArrayList();
        this.markerMap = new HashMap();
        this.minimumZoomLevel = DEFAULT_MINIMUM_ZOOM_LEVEL;
        this.maximumZoomLevel = DEFAULT_MAXIMUM_ZOOM_LEVEL;
        this.METERS_TO_GEO = 111111.0d;
        this.INITIAL_BOUNDING_BOX_SIZE_FACTOR = 2.0f;
        InitProvider.initialize(themedReactContext);
        if (SDKNativeEngine.getSharedInstance() == null) {
            SDKNativeEngine.setSharedInstance(InitProvider.makeSDKNativeEngine(themedReactContext));
        }
        LayoutInflater.from(themedReactContext).inflate(R.layout.here_map_explore_view, this);
        this.reactContext = themedReactContext;
        themedReactContext.addLifecycleEventListener(this);
        this.userPositionManager = ((FeatureServiceV2) ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class).mo10268get()).hasAccess(FEATURE_USE_CLD_FOR_TRACKING, false) ? new CLDBasedUserPositionManager(this) : new CurrentUserPositionManager(this, themedReactContext);
        this.mapView = (MapView) findViewById(R.id.map_view);
        this.mapView.onCreate(null);
        this.mapView.setOnReadyListener(new OnMapViewReadyListener(this));
        initGeofence();
        setPanGestureHandler();
        setPinchRotateListener();
        setTapGestureHandler();
        initialize();
    }

    private void destroyMapView() {
        this.isMapReady = false;
        removeAllSubViews();
        MapView mapView = this.mapView;
        if (mapView != null) {
            mapView.onDestroy();
        }
    }

    private void dispatchEvent(final String str, final WritableMap writableMap) {
        final int id = getId();
        ThemedReactContext themedReactContext = this.reactContext;
        if (themedReactContext == null) {
            Log.e(Constants.LOG_TAG, "Skip dispatching event " + str + " because React context was null!");
            return;
        }
        ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher().dispatchEvent(new Event(id) { // from class: com.amazon.mobile.heremapsexplore.HereMapExploreView.1
            @Override // com.facebook.react.uimanager.events.Event
            public void dispatch(RCTEventEmitter rCTEventEmitter) {
                rCTEventEmitter.receiveEvent(id, getEventName(), writableMap);
            }

            @Override // com.facebook.react.uimanager.events.Event
            public String getEventName() {
                return str;
            }
        });
    }

    private void dispatchOnGeofenceUpdatedEvent(GeoCoordinates geoCoordinates, double d) {
        WritableMap forCoordinate = ReactResponses.forCoordinate(geoCoordinates);
        forCoordinate.putDouble("radius", d);
        dispatchEvent(Events.ON_GEOFENCE_UPDATED, forCoordinate);
    }

    private void dispatchOnGeofenceUpdatingEvent() {
        dispatchEvent(Events.ON_GEOFENCE_UPDATING, null);
    }

    private void initGeofence() {
        if (this.geofenceView == null) {
            this.geofenceView = (GeofenceView) findViewById(R.id.geofence_view);
            this.geofenceView.setMapView(this);
        }
        if (this.geofenceHandleView == null) {
            this.geofenceHandleView = (GeofenceHandleView) findViewById(R.id.geofence_handle_view);
            this.geofenceHandleView.setGeofence(this.geofenceView);
            this.geofenceHandleView.setHereMapView(this);
        }
        if (this.pinMarker == null) {
            this.pinMarker = (ImageView) findViewById(R.id.geofence_pin_marker);
            this.pinMarker.setPadding(0, 0, 0, (int) TypedValue.applyDimension(1, 36.0f, getResources().getDisplayMetrics()));
        }
    }

    private void initialize() {
        this.mapView.getMapScene().loadScene(MapScheme.NORMAL_DAY, new LoadSceneListener(this));
    }

    private boolean isInRange(double d, double d2, double d3) {
        return d2 <= d && d <= d3;
    }

    private void pickMapMarker(Point2D point2D) {
        this.mapView.pickMapItems(point2D, 2.0f, new MapViewBase.PickMapItemsCallback() { // from class: com.amazon.mobile.heremapsexplore.HereMapExploreView.2
            @Override // com.here.sdk.mapview.MapViewBase.PickMapItemsCallback
            public void onPickMapItems(@Nullable PickMapItemsResult pickMapItemsResult) {
                HereMapMarker hereMapMarker;
                if (pickMapItemsResult == null) {
                    return;
                }
                List<MapMarker> markers = pickMapItemsResult.getMarkers();
                if (markers.size() == 0 || (hereMapMarker = (HereMapMarker) HereMapExploreView.this.markerMap.get(markers.get(0).getMetadata().getCustomValue("WRAPPER"))) == null) {
                    return;
                }
                HereMapExploreView.this.dispatchEvent(hereMapMarker, Events.HereMapMarker.ON_PRESS, new WritableNativeMap());
            }
        });
    }

    private void resizeFenceToCoordinate(GeoCoordinates geoCoordinates, String str) {
        if (geoCoordinates == null) {
            Log.e(Constants.LOG_TAG, GeneratedOutlineSupport1.outline72(str, ": northCoordinate is null"), new Exception("null coordinate received"));
            return;
        }
        Point2D geoToViewCoordinates = this.mapView.geoToViewCoordinates(geoCoordinates);
        if (geoToViewCoordinates != null) {
            this.geofenceView.resizeToAnchor(geoToViewCoordinates);
        } else {
            Log.e(Constants.LOG_TAG, "Unable to get pixel of north coordinate");
        }
        this.geofenceView.invalidate();
        this.geofenceHandleView.invalidate();
    }

    private void setPanGestureHandler() {
        this.mapView.getGestures().setPanListener(new PanGestureListener(this));
    }

    private void setPinchRotateListener() {
        this.mapView.getGestures().setPinchRotateListener(new PinchRotateGestureListener(this));
    }

    private void setTapGestureHandler() {
        this.mapView.getGestures().setTapListener(new TapGestureListener(this));
    }

    private void waitForCameraToSettle() {
        SystemClock.sleep(100L);
    }

    public void addGeofence(ReadableMap readableMap, double d) {
        GeoCoordinates geoCoordinates = new GeoCoordinates(readableMap.getDouble("latitude"), readableMap.getDouble("longitude"));
        this.geofenceAnchorPoint = new GeoCoordinates((d / 111111.0d) + geoCoordinates.latitude, geoCoordinates.longitude);
        this.currentGeofenceRadius = d;
        double d2 = (d * 2.0d) / 111111.0d;
        GeoCoordinates geoCoordinates2 = new GeoCoordinates(geoCoordinates.latitude + d2, geoCoordinates.longitude + d2);
        GeoCoordinates geoCoordinates3 = new GeoCoordinates(geoCoordinates.latitude - d2, geoCoordinates.longitude - d2);
        this.geofenceView.setVisibility(0);
        this.geofenceHandleView.setVisibility(0);
        this.pinMarker.setVisibility(0);
        this.mapCamera.addObserver(new GeofenceMapCameraListener(this));
        setGeoBox(geoCoordinates3, geoCoordinates2);
        resizeFenceToCoordinate(this.geofenceAnchorPoint, "Adding Geofence");
        dispatchOnGeofenceUpdatedEvent(this.mapCamera.getState().targetCoordinates, this.currentGeofenceRadius);
    }

    public void addMapCircle(MapPolygon mapPolygon) {
        this.mapView.getMapScene().addMapPolygon(mapPolygon);
    }

    public void addMapMarker(MapMarker mapMarker) {
        this.mapView.getMapScene().addMapMarker(mapMarker);
    }

    public void addSubView(View view, int i) {
        if (view instanceof HereMapMarker) {
            HereMapMarker hereMapMarker = (HereMapMarker) view;
            hereMapMarker.addToMap(getMapView());
            this.subViews.add(i, hereMapMarker);
            this.markerMap.put((CustomMapMarker) hereMapMarker.getObject(), hereMapMarker);
        } else if (!(view instanceof HereMapCircle)) {
        } else {
            HereMapCircle hereMapCircle = (HereMapCircle) view;
            hereMapCircle.addToMap(getMapView());
            this.subViews.add(i, hereMapCircle);
        }
    }

    public double checkAndApplyRadiusRange() {
        GeoCoordinates viewToGeoCoordinates;
        GeoCoordinates geoCoordinates = this.mapCamera.getState().targetCoordinates;
        Rect bounds = this.geofenceView.getBounds();
        GeoCoordinates viewToGeoCoordinates2 = this.mapView.viewToGeoCoordinates(new Point2D(bounds.right, bounds.exactCenterY()));
        if (viewToGeoCoordinates2 == null) {
            if (geoCoordinates.latitude > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                viewToGeoCoordinates = this.mapView.viewToGeoCoordinates(new Point2D(bounds.exactCenterX(), bounds.bottom));
            } else {
                viewToGeoCoordinates = this.mapView.viewToGeoCoordinates(new Point2D(bounds.exactCenterX(), bounds.top));
            }
            viewToGeoCoordinates2 = viewToGeoCoordinates;
            if (viewToGeoCoordinates2 == null) {
                Log.e(Constants.LOG_TAG, String.format("fenceEdgePoint is null for unknown reason. center latitude is %f.Reusing last known radius %f to prevent crashing", Double.valueOf(geoCoordinates.latitude), Double.valueOf(this.currentGeofenceRadius)));
                return this.currentGeofenceRadius;
            }
        }
        double distanceTo = geoCoordinates.distanceTo(viewToGeoCoordinates2);
        if (distanceTo < this.minGeofenceRadius || distanceTo > this.maxGeofenceRadius) {
            double d = this.minGeofenceRadius;
            distanceTo = distanceTo < d ? d : this.maxGeofenceRadius;
        }
        this.currentGeofenceRadius = distanceTo;
        return distanceTo;
    }

    public void checkFenceAndPostUpdate(boolean z, String str) {
        if (this.mapView == null) {
            return;
        }
        double checkAndApplyRadiusRange = z ? checkAndApplyRadiusRange() : this.currentGeofenceRadius;
        this.geofenceView.invalidate();
        this.geofenceHandleView.invalidate();
        dispatchEvent(str, ReactResponses.forCoordinateAndRadius(this.mapCamera.getState().targetCoordinates, checkAndApplyRadiusRange));
    }

    public void configureMapGestures() {
        this.mapView.getGestures().disableDefaultAction(GestureType.DOUBLE_TAP);
        this.mapView.getGestures().disableDefaultAction(GestureType.TWO_FINGER_PAN);
        this.mapView.getGestures().disableDefaultAction(GestureType.TWO_FINGER_TAP);
    }

    public void dispatchOnMapReadyEvent() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean(Events.ON_MAP_READY, true);
        createMap.putInt("width", getWidth());
        createMap.putInt("height", getHeight());
        dispatchEvent(Events.ON_MAP_READY, createMap);
    }

    public void dispatchOnPanAwayEvent() {
        dispatchEvent(Events.ON_PAN_AWAY, Arguments.createMap());
    }

    public void dispatchOnRegionChangeCompleteEvent() {
        GeoCoordinates geoCoordinates = this.mapCamera.getState().targetCoordinates;
        GeoBox boundingBox = this.mapCamera.getBoundingBox();
        if (boundingBox == null) {
            return;
        }
        WritableMap forCoordinate = ReactResponses.forCoordinate(geoCoordinates);
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(ResponseFields.EAST, boundingBox.northEastCorner.longitude);
        createMap.putDouble(ResponseFields.SOUTH, boundingBox.southWestCorner.latitude);
        createMap.putDouble(ResponseFields.WEST, boundingBox.southWestCorner.longitude);
        createMap.putDouble(ResponseFields.NORTH, boundingBox.northEastCorner.latitude);
        forCoordinate.putMap(ResponseFields.BOUNDING_BOX_DATA, createMap);
        dispatchEvent(Events.ON_REGION_CHANGE_COMPLETE, forCoordinate);
    }

    public void followUserLocation(boolean z) {
        this.followUserLocation = z;
    }

    public HashMap<String, Object> getInitialReactData() {
        return this.initialReactData;
    }

    public MapCamera getMapCamera() {
        return this.mapCamera;
    }

    public MapView getMapView() {
        return this.mapView;
    }

    public double getMaxGeofenceRadius() {
        return this.maxGeofenceRadius;
    }

    public double getMinGeofenceRadius() {
        return this.minGeofenceRadius;
    }

    public View getSubViewAt(int i) {
        return this.subViews.get(i);
    }

    public int getSubViewCount() {
        return this.subViews.size();
    }

    public void handlePanGesture(GestureState gestureState, Point2D point2D, Point2D point2D2, double d) {
        if (gestureState == GestureState.BEGIN || gestureState == GestureState.UPDATE) {
            dispatchOnGeofenceUpdatingEvent();
            dispatchOnPanAwayEvent();
            this.lastGesture = LastGesture.PAN;
        }
    }

    public void handlePinchRotateGesture(@NonNull GestureState gestureState, @NonNull Point2D point2D, @NonNull Point2D point2D2, double d, @NonNull Angle angle) {
        resizeFenceToCoordinate(this.geofenceAnchorPoint, "Pinch Zoom");
        if (gestureState == GestureState.END) {
            dispatchOnRegionChangeCompleteEvent();
        }
    }

    public void handleTapGesture(@NonNull Point2D point2D) {
        pickMapMarker(point2D);
    }

    public boolean isMapIdle() {
        return this.lastGesture == LastGesture.NONE;
    }

    public boolean isMapReady() {
        return this.isMapReady;
    }

    public void navigateToCoordinates(ReadableMap readableMap) {
        setCoordinates(readableMap.getDouble("latitude"), readableMap.getDouble("longitude"));
        dispatchOnRegionChangeCompleteEvent();
    }

    public void navigateToRegion(ReadableMap readableMap) {
        ReadableMap mo6945getMap = readableMap.mo6945getMap(ResponseFields.SOUTHWEST_CORNER);
        ReadableMap mo6945getMap2 = readableMap.mo6945getMap(ResponseFields.NORTHEAST_CORNER);
        setGeoBox(new GeoCoordinates(mo6945getMap.getDouble("latitude"), mo6945getMap.getDouble("longitude")), new GeoCoordinates(mo6945getMap2.getDouble("latitude"), mo6945getMap2.getDouble("longitude")));
        dispatchOnRegionChangeCompleteEvent();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.userPositionManager.stopPollingForUserLocation();
        super.onDetachedFromWindow();
        destroyMapView();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        this.userPositionManager.stopPollingForUserLocation();
        destroyMapView();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.isMapReady = false;
        this.userPositionManager.stopPollingForUserLocation();
        this.mapView.onPause();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.mapView.onResume();
        this.userPositionManager.setInitialUserLocation();
        this.userPositionManager.pollForUserLocation();
        if (this.geofenceHandleView != null) {
            AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
            boolean z = accessibilityManager != null && accessibilityManager.isTouchExplorationEnabled();
            Log.i(Constants.LOG_TAG, "Host resumed, isTouchExplorationEnabled:" + z);
            this.geofenceHandleView.setExploreByTouchEnabled(z);
            if (z) {
                return;
            }
            this.geofenceHandleView.layout(0, 0, getWidth(), getHeight());
        }
    }

    public void onRegionChangeComplete() {
        if (this.lastGesture == LastGesture.PAN) {
            dispatchOnGeofenceUpdatedEvent(this.mapCamera.getState().targetCoordinates, this.currentGeofenceRadius);
            dispatchOnRegionChangeCompleteEvent();
            this.lastGesture = LastGesture.NONE;
        }
    }

    public void recenter() {
        if (this.mapCamera == null) {
            return;
        }
        this.userPositionManager.updateUserLocationMarker();
        if (this.userPositionManager.hasCoordinates()) {
            setCoordinates(this.userPositionManager.getCoordinates().latitude, this.userPositionManager.getCoordinates().longitude);
        } else {
            Log.e(Constants.LOG_TAG, "Error recentering to user's location as we have no valid position or initial coordinate");
        }
    }

    public void removeAllSubViews() {
        while (!this.subViews.isEmpty()) {
            removeSubViewAt(0);
        }
    }

    public void removeSubViewAt(int i) {
        View view = this.subViews.get(i);
        if (view instanceof HereMapMarker) {
            ((HereMapMarker) view).removeFromMap(getMapView());
            this.subViews.remove(i);
        } else if (!(view instanceof HereMapCircle)) {
        } else {
            ((HereMapCircle) view).removeFromMap(getMapView());
            this.subViews.remove(i);
        }
    }

    public void setCoordinates(double d, double d2) {
        MapCamera mapCamera = this.mapCamera;
        if (mapCamera != null) {
            mapCamera.lookAt(new GeoCoordinates(d, d2));
            waitForCameraToSettle();
        }
    }

    public void setGeoBox(GeoCoordinates geoCoordinates, GeoCoordinates geoCoordinates2) {
        MapCamera mapCamera = this.mapCamera;
        GeoBox geoBox = new GeoBox(geoCoordinates, geoCoordinates2);
        Double valueOf = Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        mapCamera.lookAt(geoBox, new MapCamera.OrientationUpdate(valueOf, valueOf));
        waitForCameraToSettle();
    }

    public void setGeofenceRadiusRange(double d, double d2) {
        if (d > d2) {
            Log.e(Constants.LOG_TAG, String.format("Minimum radius %.1f is larger than maximum radius %.1f", Double.valueOf(d), Double.valueOf(d2)));
        } else if (d < FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            Log.e(Constants.LOG_TAG, String.format("Minimum radius %.1f cannot be lower than 0", Double.valueOf(d)));
        } else {
            this.minGeofenceRadius = d;
            this.maxGeofenceRadius = d2;
        }
    }

    public void setInitialCoordinates(double d, double d2) {
        this.initialCoordinates = new GeoCoordinates(d, d2);
    }

    public void setIsMapReady(boolean z) {
        this.isMapReady = z;
    }

    public void setMapCamera(MapCamera mapCamera) {
        this.mapCamera = mapCamera;
    }

    public void setMapScrolling(boolean z) {
        if (z) {
            this.mapView.getGestures().enableDefaultAction(GestureType.PAN);
        } else {
            this.mapView.getGestures().disableDefaultAction(GestureType.PAN);
        }
    }

    public void setMaxZoomLevel(double d) {
        if (isInRange(d, DEFAULT_MINIMUM_ZOOM_LEVEL, DEFAULT_MAXIMUM_ZOOM_LEVEL)) {
            try {
                this.mapCamera.getLimits().setMaxZoomLevel(d);
                this.maximumZoomLevel = d;
            } catch (MapCameraLimits.MapCameraLimitsException e) {
                Log.w(Constants.LOG_TAG, e.toString());
            }
        }
    }

    public void setMinZoomLevel(double d) {
        if (isInRange(d, DEFAULT_MINIMUM_ZOOM_LEVEL, DEFAULT_MAXIMUM_ZOOM_LEVEL)) {
            try {
                this.mapCamera.getLimits().setMinZoomLevel(d);
                this.minimumZoomLevel = d;
            } catch (MapCameraLimits.MapCameraLimitsException e) {
                Log.w(Constants.LOG_TAG, e.toString());
            }
        }
    }

    public void setReactData() {
        if (this.initialReactData.containsKey("latitude") && this.initialReactData.containsKey("latitude")) {
            setInitialCoordinates(((Double) this.initialReactData.get("latitude")).doubleValue(), ((Double) this.initialReactData.get("longitude")).doubleValue());
            setCoordinates(((Double) this.initialReactData.get("latitude")).doubleValue(), ((Double) this.initialReactData.get("longitude")).doubleValue());
        }
        if (this.initialReactData.containsKey(ReactProperties.SCROLL_ENABLED)) {
            setMapScrolling(((Boolean) this.initialReactData.get(ReactProperties.SCROLL_ENABLED)).booleanValue());
        }
        if (this.initialReactData.containsKey(ReactProperties.MIN_ZOOM_LEVEL)) {
            setMinZoomLevel(((Double) this.initialReactData.get(ReactProperties.MIN_ZOOM_LEVEL)).doubleValue());
        } else {
            setMinZoomLevel(DEFAULT_MINIMUM_ZOOM_LEVEL);
        }
        if (this.initialReactData.containsKey(ReactProperties.MAX_ZOOM_LEVEL)) {
            setMaxZoomLevel(((Double) this.initialReactData.get(ReactProperties.MAX_ZOOM_LEVEL)).doubleValue());
        } else {
            setMaxZoomLevel(DEFAULT_MAXIMUM_ZOOM_LEVEL);
        }
        if (this.initialReactData.containsKey(ReactProperties.ZOOM_LEVEL)) {
            setZoomLevel(((Double) this.initialReactData.get(ReactProperties.ZOOM_LEVEL)).doubleValue());
        } else {
            setZoomLevel(DEFAULT_ZOOM_LEVEL);
        }
        if (this.initialReactData.containsKey(ReactProperties.GEOFENCE_MINIMUM_RANGE) && this.initialReactData.containsKey(ReactProperties.GEOFENCE_MAXIMUM_RANGE)) {
            setGeofenceRadiusRange(((Double) this.initialReactData.get(ReactProperties.GEOFENCE_MINIMUM_RANGE)).doubleValue(), ((Double) this.initialReactData.get(ReactProperties.GEOFENCE_MAXIMUM_RANGE)).doubleValue());
        }
        if (this.initialReactData.containsKey(ReactProperties.SHOWS_USER_LOCATION)) {
            showUserLocation(((Boolean) this.initialReactData.get(ReactProperties.SHOWS_USER_LOCATION)).booleanValue());
        }
        if (this.initialReactData.containsKey(ReactProperties.SHOWS_USER_LOCATION_ACCURACY)) {
            showUserLocationAccuracy(((Boolean) this.initialReactData.get(ReactProperties.SHOWS_USER_LOCATION_ACCURACY)).booleanValue());
        }
        if (this.initialReactData.containsKey(ReactProperties.FOLLOWS_USER_LOCATION)) {
            followUserLocation(((Boolean) this.initialReactData.get(ReactProperties.FOLLOWS_USER_LOCATION)).booleanValue());
        }
        if (this.initialReactData.containsKey(ReactProperties.SHOWS_CENTER_MARKER)) {
            showCenterMarker(((Boolean) this.initialReactData.get(ReactProperties.SHOWS_CENTER_MARKER)).booleanValue());
        }
        if (this.initialReactData.containsKey("region")) {
            navigateToCoordinates((ReadableMap) this.initialReactData.get("region"));
        }
        if (this.showUserLocation) {
            this.userPositionManager.displayUserLocationWithAccuracy(this.showUserLocationAccuracy);
            this.userPositionManager.pollForUserLocation();
            if (!this.followUserLocation) {
                return;
            }
            GeoCoordinates coordinates = this.userPositionManager.getCoordinates();
            setCoordinates(coordinates.latitude, coordinates.longitude);
        }
    }

    public void setZoomLevel(double d) {
        if (isInRange(d, this.minimumZoomLevel, this.maximumZoomLevel)) {
            this.mapCamera.zoomTo(d);
        }
    }

    public void showCenterMarker(boolean z) {
        if (z) {
            this.pinMarker.setVisibility(0);
        } else {
            this.pinMarker.setVisibility(4);
        }
    }

    public void showUserLocation(boolean z) {
        this.showUserLocation = z;
    }

    public void showUserLocationAccuracy(boolean z) {
        this.showUserLocationAccuracy = z;
    }

    public void updateGeofenceAnchorPoint(MapCamera.State state) {
        GeoCoordinates geoCoordinates = state.targetCoordinates;
        this.geofenceAnchorPoint = new GeoCoordinates((this.currentGeofenceRadius / 111111.0d) + geoCoordinates.latitude, geoCoordinates.longitude);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchEvent(View view, String str, WritableMap writableMap) {
        ((RCTEventEmitter) this.reactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), str, writableMap);
    }
}
