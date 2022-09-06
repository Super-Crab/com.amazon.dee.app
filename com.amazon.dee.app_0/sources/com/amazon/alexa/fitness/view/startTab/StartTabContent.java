package com.amazon.alexa.fitness.view.startTab;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.fitness.api.LocationService;
import com.amazon.alexa.fitness.api.UserPreferenceKey;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.api.afx.FitnessRoutesKt;
import com.amazon.alexa.fitness.components.CenterAdapter;
import com.amazon.alexa.fitness.components.MapViewListener;
import com.amazon.alexa.fitness.components.MarkerAdapter;
import com.amazon.alexa.fitness.components.WorkoutMapView;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.utils.MapViewMetrics;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.fitness.view.fullscreen.FullScreenViewController;
import com.amazon.alexa.fitness.view.message.CustomToast;
import com.amazon.alexa.fitness.view.workoutTab.WorkoutTabContent;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.here.sdk.mapview.MapView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StartTabContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/view/startTab/StartTabContent;", "", "()V", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class StartTabContent {
    @Nullable
    private static FullScreenViewController fullScreenViewController;
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static UserPreferenceStore userPreferenceStore = (UserPreferenceStore) GeneratedOutlineSupport1.outline22(UserPreferenceStore.class, "ComponentRegistry.getIns…eStore::class.java).get()");
    @NotNull
    private static LocationService locationService = (LocationService) GeneratedOutlineSupport1.outline22(LocationService.class, "ComponentRegistry.getIns…ervice::class.java).get()");
    private static final Mobilytics metrics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    private static final CenterAdapter centerAdapter = new CenterAdapter();
    private static final MarkerAdapter markerAdapter = new MarkerAdapter();

    /* compiled from: StartTabContent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u0006\u0010$\u001a\u00020\u001fJ\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020&H\u0002J\u0010\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020*H\u0016J\u000e\u0010+\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#J\u0012\u0010,\u001a\u00020\u001f2\b\u0010-\u001a\u0004\u0018\u00010!H\u0002J\u0012\u0010.\u001a\u00020\u001f2\b\u0010-\u001a\u0004\u0018\u00010!H\u0002J\u0010\u0010/\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#H\u0002J\u0006\u00100\u001a\u00020\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0016*\u0004\u0018\u00010\u00150\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u0002\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u00061"}, d2 = {"Lcom/amazon/alexa/fitness/view/startTab/StartTabContent$Companion;", "Lcom/amazon/alexa/fitness/components/MapViewListener;", "()V", "centerAdapter", "Lcom/amazon/alexa/fitness/components/CenterAdapter;", "fullScreenViewController", "Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "getFullScreenViewController", "()Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "setFullScreenViewController", "(Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;)V", "locationService", "Lcom/amazon/alexa/fitness/api/LocationService;", "locationService$annotations", "getLocationService", "()Lcom/amazon/alexa/fitness/api/LocationService;", "setLocationService", "(Lcom/amazon/alexa/fitness/api/LocationService;)V", "markerAdapter", "Lcom/amazon/alexa/fitness/components/MarkerAdapter;", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "kotlin.jvm.PlatformType", "userPreferenceStore", "Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "userPreferenceStore$annotations", "getUserPreferenceStore", "()Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "setUserPreferenceStore", "(Lcom/amazon/alexa/fitness/api/UserPreferenceStore;)V", "handleRouteMappingBtnClick", "", "btn", "Landroid/widget/ImageButton;", "fullScreenView", "Landroid/view/View;", "hideStartTabContent", "isRouteTrackingEnabled", "", "isRouteTrackingFtueDone", "onMapOffCentered", "mapView", "Lcom/here/sdk/mapview/MapView;", "refreshUIForRouteMapping", "setMapOffIconTo", "button", "setMapOnIconTo", "setupRouteMapping", "showStartTabContent", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion implements MapViewListener {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void handleRouteMappingBtnClick(ImageButton imageButton, View view) {
            RoutingService.RoutingBuilder addToBackStack;
            if (StartTabContentKt.isRouteMappingOn()) {
                Log.i("AFX-StartTabContent", "disabling route mapping");
                Mobilytics metrics = StartTabContent.metrics;
                Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
                MetricHelperKt.recordUserInteractionEvent(metrics, MapViewMetrics.INSTANCE.getDisableRouteTracking(), EventType.TAP);
                getUserPreferenceStore().set(UserPreferenceKey.IsRouteTrackingEnabled, false);
                refreshUIForRouteMapping(view);
            } else if (isRouteTrackingFtueDone()) {
                Log.i("AFX-StartTabContent", "enabling route mapping");
                Mobilytics metrics2 = StartTabContent.metrics;
                Intrinsics.checkExpressionValueIsNotNull(metrics2, "metrics");
                MetricHelperKt.recordUserInteractionEvent(metrics2, MapViewMetrics.INSTANCE.getEnableRouteTracking(), EventType.TAP);
                getUserPreferenceStore().set(UserPreferenceKey.IsRouteTrackingEnabled, true);
                if (!getLocationService().getAuthorizationStatus()) {
                    Log.i("AFX-StartTabContent", "showing location permission education screen");
                    FullScreenUtil.Companion.goFtueLocationPermissions();
                } else if (!getLocationService().getLocationStatus()) {
                    Log.i("AFX-StartTabContent", "requesting to enable location");
                    LocationService locationService = getLocationService();
                    Context context = imageButton.getContext();
                    if (context == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                    }
                    locationService.requestToEnableLocation((Activity) context);
                } else {
                    refreshUIForRouteMapping(view);
                }
            } else {
                RoutingService.RoutingBuilder match = FullScreenUtil.Companion.getRoutingService().match(FitnessRoutesKt.FTUE_ROUTE_MAPPING_ROUTE);
                if (match != null && (addToBackStack = match.addToBackStack()) != null) {
                    addToBackStack.navigate();
                }
                Log.i("AFX-StartTabContent", "routing to route mapping FTUE view");
            }
        }

        private final boolean isRouteTrackingEnabled() {
            return getUserPreferenceStore().get(UserPreferenceKey.IsRouteTrackingEnabled);
        }

        private final boolean isRouteTrackingFtueDone() {
            return getUserPreferenceStore().get(UserPreferenceKey.CompletedRouteTrackingFtue);
        }

        @VisibleForTesting
        public static /* synthetic */ void locationService$annotations() {
        }

        private final void setMapOffIconTo(ImageButton imageButton) {
            if (imageButton != null) {
                imageButton.setBackground(imageButton.getContext().getDrawable(R.drawable.map_icon_bg));
                imageButton.setImageDrawable(imageButton.getContext().getDrawable(R.drawable.map_icon_off));
            }
        }

        private final void setMapOnIconTo(ImageButton imageButton) {
            if (imageButton != null) {
                imageButton.setBackground(imageButton.getContext().getDrawable(R.drawable.black_circle));
                imageButton.setImageDrawable(imageButton.getContext().getDrawable(R.drawable.map_icon_on));
            }
        }

        private final void setupRouteMapping(final View view) {
            refreshUIForRouteMapping(view);
            final ImageButton imageButton = (ImageButton) view.findViewById(R.id.btn_routeMapping);
            if (imageButton != null) {
                imageButton.setVisibility(0);
                imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.startTab.StartTabContent$Companion$setupRouteMapping$$inlined$let$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        StartTabContent.Companion.handleRouteMappingBtnClick(imageButton, view);
                    }
                });
            }
            if (!isRouteTrackingFtueDone()) {
                CustomToast.Companion.showNotification$default(CustomToast.Companion, R.string.enable_route_mapping_toast, null, 2, null);
            }
        }

        @VisibleForTesting
        public static /* synthetic */ void userPreferenceStore$annotations() {
        }

        @Nullable
        public final FullScreenViewController getFullScreenViewController() {
            return StartTabContent.fullScreenViewController;
        }

        @NotNull
        public final LocationService getLocationService() {
            return StartTabContent.locationService;
        }

        @NotNull
        public final UserPreferenceStore getUserPreferenceStore() {
            return StartTabContent.userPreferenceStore;
        }

        public final void hideStartTabContent() {
            View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
            if (fullScreenView != null) {
                ViewGroup viewGroup = (ViewGroup) fullScreenView.findViewById(R.id.landing_page_tab);
                if (viewGroup != null) {
                    viewGroup.setVisibility(8);
                }
                ViewGroup viewGroup2 = (ViewGroup) fullScreenView.findViewById(R.id.landing_page_big_font_tab);
                if (viewGroup2 == null) {
                    return;
                }
                viewGroup2.setVisibility(8);
            }
        }

        @Override // com.amazon.alexa.fitness.components.MapViewListener
        public void onDestroy() {
            MapViewListener.DefaultImpls.onDestroy(this);
        }

        @Override // com.amazon.alexa.fitness.components.MapViewListener
        public void onMapOffCentered(@NotNull MapView mapView) {
            Intrinsics.checkParameterIsNotNull(mapView, "mapView");
            View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
            if (fullScreenView != null) {
                ImageButton imageButton = (ImageButton) fullScreenView.findViewById(R.id.btn_recenter);
                imageButton.setImageResource(R.drawable.ic_location_searching);
                Intrinsics.checkExpressionValueIsNotNull(imageButton, "this");
                imageButton.setVisibility(0);
            }
        }

        @Override // com.amazon.alexa.fitness.components.MapViewListener
        public void onPause() {
            MapViewListener.DefaultImpls.onPause(this);
        }

        @Override // com.amazon.alexa.fitness.components.MapViewListener
        public void onResume() {
            MapViewListener.DefaultImpls.onResume(this);
        }

        public final void refreshUIForRouteMapping(@NotNull View fullScreenView) {
            ImageButton imageButton;
            WorkoutMapView workoutMapView;
            Intrinsics.checkParameterIsNotNull(fullScreenView, "fullScreenView");
            if (StartTabContentKt.isRouteMappingOn()) {
                setMapOnIconTo((ImageButton) fullScreenView.findViewById(R.id.btn_routeMapping));
                FullScreenViewController fullScreenViewController = getFullScreenViewController();
                if (fullScreenViewController != null) {
                    fullScreenViewController.showMapView(fullScreenView, true, true);
                }
                TextView textView = (TextView) fullScreenView.findViewById(R.id.lbl_duration);
                if (textView != null) {
                    textView.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_num2));
                }
                TextView textView2 = (TextView) fullScreenView.findViewById(R.id.lbl_time);
                if (textView2 != null) {
                    textView2.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary));
                }
                TextView textView3 = (TextView) fullScreenView.findViewById(R.id.lbl_utterance);
                if (textView3 != null) {
                    textView3.setVisibility(8);
                }
                FullScreenUtil.Companion.updateUIToDisableFontScaling();
                View fullScreenView2 = FullScreenUtil.Companion.getFullScreenView();
                if (fullScreenView2 != null && (workoutMapView = (WorkoutMapView) fullScreenView2.findViewById(R.id.map_view)) != null) {
                    workoutMapView.addListener(StartTabContent.Companion);
                    workoutMapView.addAdapter(StartTabContent.markerAdapter);
                    workoutMapView.addAdapter(StartTabContent.centerAdapter);
                    workoutMapView.update();
                }
                View fullScreenView3 = FullScreenUtil.Companion.getFullScreenView();
                if (fullScreenView3 == null || (imageButton = (ImageButton) fullScreenView3.findViewById(R.id.btn_recenter)) == null) {
                    return;
                }
                imageButton.setOnClickListener(StartTabContent$Companion$refreshUIForRouteMapping$2.INSTANCE);
                return;
            }
            setMapOffIconTo((ImageButton) fullScreenView.findViewById(R.id.btn_routeMapping));
            FullScreenViewController fullScreenViewController2 = getFullScreenViewController();
            if (fullScreenViewController2 != null) {
                fullScreenViewController2.showMapView(fullScreenView, false, true);
            }
            TextView textView4 = (TextView) fullScreenView.findViewById(R.id.lbl_duration);
            if (textView4 != null) {
                textView4.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_num1));
            }
            TextView textView5 = (TextView) fullScreenView.findViewById(R.id.lbl_time);
            if (textView5 != null) {
                textView5.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_headline2));
            }
            TextView textView6 = (TextView) fullScreenView.findViewById(R.id.lbl_utterance);
            if (textView6 != null) {
                textView6.setVisibility(0);
            }
            FullScreenUtil.Companion.updateUIToDefaultFontSizes();
        }

        public final void setFullScreenViewController(@Nullable FullScreenViewController fullScreenViewController) {
            StartTabContent.fullScreenViewController = fullScreenViewController;
        }

        public final void setLocationService(@NotNull LocationService locationService) {
            Intrinsics.checkParameterIsNotNull(locationService, "<set-?>");
            StartTabContent.locationService = locationService;
        }

        public final void setUserPreferenceStore(@NotNull UserPreferenceStore userPreferenceStore) {
            Intrinsics.checkParameterIsNotNull(userPreferenceStore, "<set-?>");
            StartTabContent.userPreferenceStore = userPreferenceStore;
        }

        public final void showStartTabContent() {
            ViewGroup viewGroup;
            Configuration configuration;
            View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
            if (fullScreenView != null) {
                Resources resources = fullScreenView.getResources();
                int i = (((resources == null || (configuration = resources.getConfiguration()) == null) ? 0.0f : configuration.fontScale) > 1 ? 1 : (((resources == null || (configuration = resources.getConfiguration()) == null) ? 0.0f : configuration.fontScale) == 1 ? 0 : -1));
                ViewGroup viewGroup2 = (ViewGroup) fullScreenView.findViewById(i > 0 ? R.id.landing_page_big_font_tab : R.id.landing_page_tab);
                if (viewGroup2 != null) {
                    viewGroup2.setVisibility(0);
                }
                if (i > 0 && (viewGroup = (ViewGroup) ((ViewGroup) fullScreenView).findViewById(R.id.tabs)) != null) {
                    viewGroup.removeView(fullScreenView.findViewById(R.id.landing_page_tab));
                }
                WorkoutTabContent.Companion.hideWorkoutTabContent();
                if (!FullScreenUtil.Companion.getFeatureService().isMapViewEnabled()) {
                    return;
                }
                StartTabContent.Companion.setupRouteMapping(fullScreenView);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
