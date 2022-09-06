package com.amazon.alexa.fitness.view.ftue;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.amazon.alexa.fitness.api.LocationService;
import com.amazon.alexa.fitness.api.UserPreferenceKey;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FtueMetrics;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FtueRouteMappingViewController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/view/ftue/FtueRouteMappingViewController;", "Lcom/amazon/alexa/viewmanagement/api/ViewController;", "()V", "locationService", "Lcom/amazon/alexa/fitness/api/LocationService;", "kotlin.jvm.PlatformType", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "userPreferenceStore", "Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "makeView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "viewGroup", "Landroid/view/ViewGroup;", "onAttach", "", "view", "setTextFont", "setupRouteMappingLayout", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FtueRouteMappingViewController implements ViewController {
    private final LocationService locationService = (LocationService) GeneratedOutlineSupport1.outline20(LocationService.class);
    private final Mobilytics metrics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    private final UserPreferenceStore userPreferenceStore = (UserPreferenceStore) GeneratedOutlineSupport1.outline20(UserPreferenceStore.class);

    private final void setTextFont(View view) {
        if (((TextView) view.findViewById(R.id.route_tracking_title)) != null) {
            Fonts fonts = Fonts.EMBER_BOLD;
        }
        if (((TextView) view.findViewById(R.id.route_tracking_message1)) != null) {
            Fonts fonts2 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.learn_more)) != null) {
            Fonts fonts3 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.route_tracking_warning)) != null) {
            Fonts fonts4 = Fonts.EMBER_REGULAR;
        }
        if (((Button) view.findViewById(R.id.route_mapping_enable)) != null) {
            Fonts fonts5 = Fonts.EMBER_BOLD;
        }
        if (((Button) view.findViewById(R.id.route_mapping_later)) != null) {
            Fonts fonts6 = Fonts.EMBER_BOLD;
        }
    }

    private final void setupRouteMappingLayout(View view) {
        setTextFont(view);
        Button button = (Button) view.findViewById(R.id.route_mapping_enable);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.ftue.FtueRouteMappingViewController$setupRouteMappingLayout$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    Mobilytics metrics;
                    UserPreferenceStore userPreferenceStore;
                    UserPreferenceStore userPreferenceStore2;
                    LocationService locationService;
                    metrics = FtueRouteMappingViewController.this.metrics;
                    Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
                    MetricHelperKt.recordUserInteractionEvent(metrics, FtueMetrics.RouteTracking.INSTANCE.getEnable(), EventType.TAP);
                    userPreferenceStore = FtueRouteMappingViewController.this.userPreferenceStore;
                    userPreferenceStore.set(UserPreferenceKey.IsRouteTrackingEnabled, true);
                    userPreferenceStore2 = FtueRouteMappingViewController.this.userPreferenceStore;
                    userPreferenceStore2.set(UserPreferenceKey.CompletedRouteTrackingFtue, true);
                    locationService = FtueRouteMappingViewController.this.locationService;
                    if (!locationService.getAuthorizationStatus()) {
                        FullScreenUtil.Companion.goFtueLocationPermissions();
                    } else {
                        FullScreenUtil.Companion.goPreviousView();
                    }
                    Log.i("AFX-RouteMappingView", "user enabled route tracking");
                }
            });
        }
        Button button2 = (Button) view.findViewById(R.id.route_mapping_later);
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.ftue.FtueRouteMappingViewController$setupRouteMappingLayout$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    Mobilytics metrics;
                    UserPreferenceStore userPreferenceStore;
                    UserPreferenceStore userPreferenceStore2;
                    metrics = FtueRouteMappingViewController.this.metrics;
                    Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
                    MetricHelperKt.recordUserInteractionEvent(metrics, FtueMetrics.RouteTracking.INSTANCE.getLater(), EventType.TAP);
                    FullScreenUtil.Companion.goPreviousView();
                    userPreferenceStore = FtueRouteMappingViewController.this.userPreferenceStore;
                    userPreferenceStore.set(UserPreferenceKey.CompletedRouteTrackingFtue, true);
                    userPreferenceStore2 = FtueRouteMappingViewController.this.userPreferenceStore;
                    userPreferenceStore2.set(UserPreferenceKey.IsRouteTrackingEnabled, false);
                    Log.i("AFX-RouteMappingView", "user tapped 'later' on route mapping ftue");
                }
            });
        }
        TextView textView = (TextView) view.findViewById(R.id.learn_more);
        if (textView != null) {
            textView.setOnClickListener(FtueRouteMappingViewController$setupRouteMappingLayout$3.INSTANCE);
        }
        Button button3 = (Button) view.findViewById(R.id.closeButton);
        if (button3 != null) {
            button3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.ftue.FtueRouteMappingViewController$setupRouteMappingLayout$4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    UserPreferenceStore userPreferenceStore;
                    UserPreferenceStore userPreferenceStore2;
                    FullScreenUtil.Companion.goPreviousView();
                    userPreferenceStore = FtueRouteMappingViewController.this.userPreferenceStore;
                    userPreferenceStore.set(UserPreferenceKey.CompletedRouteTrackingFtue, true);
                    userPreferenceStore2 = FtueRouteMappingViewController.this.userPreferenceStore;
                    userPreferenceStore2.set(UserPreferenceKey.IsRouteTrackingEnabled, false);
                    Log.i("AFX-RouteMappingView", "user tapped 'close' on route mapping ftue");
                }
            });
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NotNull
    public View makeView(@NotNull LayoutInflater inflater, @NotNull ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Intrinsics.checkParameterIsNotNull(viewGroup, "viewGroup");
        View view = inflater.inflate(R.layout.ftue_route_mapping, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        setupRouteMappingLayout(view);
        return view;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@Nullable View view) {
        if (this.userPreferenceStore.get(UserPreferenceKey.CompletedRouteTrackingFtue)) {
            Log.i("AFX-RouteMappingView", "dismissing as route mapping ftue is done");
            FullScreenUtil.Companion.goPreviousView();
        }
    }
}
