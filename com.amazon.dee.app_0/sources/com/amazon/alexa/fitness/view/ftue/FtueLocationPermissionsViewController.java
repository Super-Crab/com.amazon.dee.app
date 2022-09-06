package com.amazon.alexa.fitness.view.ftue;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.amazon.alexa.fitness.api.LocationService;
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
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FtueLocationPermissionsViewController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0002J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0002J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/fitness/view/ftue/FtueLocationPermissionsViewController;", "Lcom/amazon/alexa/viewmanagement/api/ViewController;", "()V", "locationService", "Lcom/amazon/alexa/fitness/api/LocationService;", "kotlin.jvm.PlatformType", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "makeView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "viewGroup", "Landroid/view/ViewGroup;", "onAttach", "", "view", "setTextFont", "setupLocationPermissionLayout", "updateTextBasedOnOSVersion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FtueLocationPermissionsViewController implements ViewController {
    private final LocationService locationService = (LocationService) GeneratedOutlineSupport1.outline20(LocationService.class);
    private final Mobilytics metrics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);

    private final void setTextFont(View view) {
        if (((TextView) view.findViewById(R.id.location_permission_title)) != null) {
            Fonts fonts = Fonts.EMBER_BOLD;
        }
        if (((TextView) view.findViewById(R.id.location_permission_message1)) != null) {
            Fonts fonts2 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.location_permission_learn_more)) != null) {
            Fonts fonts3 = Fonts.EMBER_BOLD;
        }
        if (((TextView) view.findViewById(R.id.location_permission_allow)) != null) {
            Fonts fonts4 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.location_permission_step1)) != null) {
            Fonts fonts5 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.location_permission_step2)) != null) {
            Fonts fonts6 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.location_permission_step3)) != null) {
            Fonts fonts7 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.location_permission_step4)) != null) {
            Fonts fonts8 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.location_permission_step5)) != null) {
            Fonts fonts9 = Fonts.EMBER_REGULAR;
        }
        if (((Button) view.findViewById(R.id.location_permission_cancel)) != null) {
            Fonts fonts10 = Fonts.EMBER_BOLD;
        }
        if (((Button) view.findViewById(R.id.location_permission_settings)) != null) {
            Fonts fonts11 = Fonts.EMBER_BOLD;
        }
    }

    private final void setupLocationPermissionLayout(View view) {
        setTextFont(view);
        Button button = (Button) view.findViewById(R.id.closeButton);
        if (button != null) {
            button.setOnClickListener(FtueLocationPermissionsViewController$setupLocationPermissionLayout$1.INSTANCE);
        }
        TextView textView = (TextView) view.findViewById(R.id.location_permission_learn_more);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.ftue.FtueLocationPermissionsViewController$setupLocationPermissionLayout$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    Mobilytics metrics;
                    metrics = FtueLocationPermissionsViewController.this.metrics;
                    Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
                    MetricHelperKt.recordUserInteractionEvent(metrics, FtueMetrics.LocationPermissions.INSTANCE.getLearnMore(), EventType.TAP);
                    FullScreenUtil.Companion.goLearnMoreLocationPermissions();
                }
            });
        }
        Button button2 = (Button) view.findViewById(R.id.location_permission_cancel);
        if (button2 != null) {
            button2.setOnClickListener(FtueLocationPermissionsViewController$setupLocationPermissionLayout$3.INSTANCE);
        }
        Button button3 = (Button) view.findViewById(R.id.location_permission_settings);
        if (button3 != null) {
            button3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.ftue.FtueLocationPermissionsViewController$setupLocationPermissionLayout$4
                @Override // android.view.View.OnClickListener
                public final void onClick(View it2) {
                    Mobilytics metrics;
                    LocationService locationService;
                    Log.i("AFX-LocPermView", "requesting location permission");
                    metrics = FtueLocationPermissionsViewController.this.metrics;
                    Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
                    MetricHelperKt.recordUserInteractionEvent(metrics, FtueMetrics.LocationPermissions.INSTANCE.getSettings(), EventType.TAP);
                    locationService = FtueLocationPermissionsViewController.this.locationService;
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    Context context = it2.getContext();
                    if (context != null) {
                        locationService.requestLocationAccess((Activity) context);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                }
            });
        }
        updateTextBasedOnOSVersion(view);
    }

    private final void updateTextBasedOnOSVersion(View view) {
        if (Build.VERSION.SDK_INT < 28) {
            TextView textView = (TextView) view.findViewById(R.id.location_permission_step3);
            if (textView == null) {
                return;
            }
            textView.setText(view.getContext().getString(R.string.location_permission_return_text, 3));
            return;
        }
        TextView textView2 = (TextView) view.findViewById(R.id.location_permission_step1);
        if (textView2 != null) {
            textView2.setText(view.getContext().getString(R.string.location_permission_settings_text_toggle_location));
        }
        TextView textView3 = (TextView) view.findViewById(R.id.location_permission_step2);
        if (textView3 != null) {
            textView3.setText(view.getContext().getString(R.string.location_permission_select_app_acess));
        }
        TextView textView4 = (TextView) view.findViewById(R.id.location_permission_step3);
        if (textView4 != null) {
            textView4.setText(view.getContext().getString(R.string.location_permission_select_amazon_app));
        }
        TextView textView5 = (TextView) view.findViewById(R.id.location_permission_step4);
        if (textView5 != null) {
            textView5.setVisibility(0);
        }
        TextView textView6 = (TextView) view.findViewById(R.id.location_permission_step5);
        if (textView6 != null) {
            textView6.setVisibility(0);
        }
        TextView textView7 = (TextView) view.findViewById(R.id.location_permission_step5);
        if (textView7 == null) {
            return;
        }
        textView7.setText(view.getContext().getString(R.string.location_permission_return_text, 5));
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NotNull
    public View makeView(@NotNull LayoutInflater inflater, @NotNull ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Intrinsics.checkParameterIsNotNull(viewGroup, "viewGroup");
        View view = inflater.inflate(R.layout.ftue_location_permissions, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        setupLocationPermissionLayout(view);
        return view;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@Nullable View view) {
        if (this.locationService.getAuthorizationStatus()) {
            Log.i("AFX-LocPermView", "dismissing as location is authorized");
            FullScreenUtil.Companion.goPreviousView();
        }
    }
}
