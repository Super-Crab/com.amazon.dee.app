package com.amazon.alexa.fitness.view.workoutTab;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.amazon.alexa.fitness.api.afits.ActivitySummary;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.api.afits.LocationSample;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.components.SinglePointRouteAdapter;
import com.amazon.alexa.fitness.components.SummaryRouteAdapter;
import com.amazon.alexa.fitness.components.WorkoutMapView;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.FormatUtilKt;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.utils.MapViewMetrics;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.fitness.utils.NavBarMetrics;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.routing.api.RoutingService;
import com.here.sdk.core.GeoCoordinates;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DetailedView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DetailedView$setupWorkoutDetailedView$1 implements Runnable {
    final /* synthetic */ FitnessSession $fitnessSession;
    final /* synthetic */ DetailedView this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetailedView$setupWorkoutDetailedView$1(DetailedView detailedView, FitnessSession fitnessSession) {
        this.this$0 = detailedView;
        this.$fitnessSession = fitnessSession;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List<LocationSample> sortedWith;
        int collectionSizeOrDefault;
        Resources resources;
        Configuration configuration;
        final View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            final WorkoutMapView mapView = (WorkoutMapView) fullScreenView.findViewById(R.id.map_view);
            mapView.addListener(this.this$0);
            List<LocationSample> route = this.$fitnessSession.getRoute();
            if (route != null) {
                if (route.isEmpty()) {
                    Intrinsics.checkExpressionValueIsNotNull(mapView, "mapView");
                    mapView.setVisibility(8);
                } else {
                    Intrinsics.checkExpressionValueIsNotNull(mapView, "mapView");
                    mapView.setVisibility(0);
                    sortedWith = CollectionsKt___CollectionsKt.sortedWith(route, new Comparator<T>() { // from class: com.amazon.alexa.fitness.view.workoutTab.DetailedView$setupWorkoutDetailedView$1$$special$$inlined$sortedBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            int compareValues;
                            compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(FormatUtilKt.getFormattedDateInMs(((LocationSample) t).getTimestamp())), Long.valueOf(FormatUtilKt.getFormattedDateInMs(((LocationSample) t2).getTimestamp())));
                            return compareValues;
                        }
                    });
                    collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(sortedWith, 10);
                    ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                    for (LocationSample locationSample : sortedWith) {
                        arrayList.add(new GeoCoordinates(locationSample.getGeolocation().getCoordinate().getLatitudeInDegrees(), locationSample.getGeolocation().getCoordinate().getLongitudeInDegrees()));
                    }
                    final SummaryRouteAdapter summaryRouteAdapter = new SummaryRouteAdapter();
                    summaryRouteAdapter.setCoordinates(arrayList);
                    mapView.addAdapter(summaryRouteAdapter);
                    if (arrayList.size() == 1) {
                        mapView.addAdapter(new SinglePointRouteAdapter((GeoCoordinates) arrayList.get(0)));
                    }
                    mapView.update();
                    ((ImageButton) fullScreenView.findViewById(R.id.btn_recenter)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.workoutTab.DetailedView$setupWorkoutDetailedView$1$$special$$inlined$let$lambda$2
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View button) {
                            Mobilytics metrics;
                            metrics = this.this$0.metrics;
                            Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
                            MetricHelperKt.recordUserInteractionEvent(metrics, MapViewMetrics.INSTANCE.getRecenter(), EventType.TAP);
                            SummaryRouteAdapter.this.center();
                            Intrinsics.checkExpressionValueIsNotNull(button, "button");
                            button.setVisibility(8);
                        }
                    });
                    ViewGroup container = FullScreenUtil.Companion.getContainer();
                    if (((container == null || (resources = container.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? 0.0f : configuration.fontScale) > 1) {
                        FullScreenUtil.Companion.updateUIToDisableFontScaling();
                    } else {
                        TextView textView = (TextView) fullScreenView.findViewById(R.id.lbl_duration);
                        if (textView != null) {
                            textView.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_num2));
                        }
                        TextView textView2 = (TextView) fullScreenView.findViewById(R.id.lbl_time);
                        if (textView2 != null) {
                            textView2.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary));
                        }
                    }
                }
            }
            View findViewById = fullScreenView.findViewById(R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "it.findViewById<TextView>(R.id.title)");
            ((TextView) findViewById).setText(FormatUtilKt.formatPrimaryText(FormatUtilKt.convertUTCToLocalTimeZoneDateTime(this.$fitnessSession.getStartTime())));
            String formattedTime = FormatUtilKt.getFormattedTime(FormatUtilKt.convertUTCToLocalTimeZone(this.$fitnessSession.getStartTime()));
            String formattedTime2 = FormatUtilKt.getFormattedTime(FormatUtilKt.convertUTCToLocalTimeZone(this.$fitnessSession.getEndTime()));
            ActivitySummary activitySummary = this.$fitnessSession.getActivitySummary();
            View findViewById2 = fullScreenView.findViewById(R.id.lbl_duration);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "it.findViewById<TextView>(R.id.lbl_duration)");
            ((TextView) findViewById2).setText(FormatUtilKt.secondsToDurationString(Integer.valueOf(activitySummary.getDurationInMs() / 1000)));
            View findViewById3 = fullScreenView.findViewById(R.id.lbl_distance);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, "it.findViewById<TextView>(R.id.lbl_distance)");
            ((TextView) findViewById3).setText(FormatUtilKt.convertToLocalDistanceWithoutUnits(activitySummary.getDistanceInMeters()));
            View findViewById4 = fullScreenView.findViewById(R.id.lbl_calories);
            Intrinsics.checkExpressionValueIsNotNull(findViewById4, "it.findViewById<TextView>(R.id.lbl_calories)");
            ((TextView) findViewById4).setText(FitnessViewHoldersKt.getValidCalories(activitySummary.getCaloriesInKcal()));
            View findViewById5 = fullScreenView.findViewById(R.id.lbl_steps);
            Intrinsics.checkExpressionValueIsNotNull(findViewById5, "it.findViewById<TextView>(R.id.lbl_steps)");
            ((TextView) findViewById5).setText(String.valueOf(activitySummary.getStepCount()));
            View findViewById6 = fullScreenView.findViewById(R.id.lbl_pace);
            Intrinsics.checkExpressionValueIsNotNull(findViewById6, "it.findViewById<TextView>(R.id.lbl_pace)");
            ((TextView) findViewById6).setText(FormatUtilKt.getFormattedPaceInDeviceLocale$default(new Pair(Double.valueOf(activitySummary.getPaceInMinutesPerKm()), Units.MinutesPerKilometer), FullScreenUtil.Companion.getCurrentContext(), null, 2, null));
            View findViewById7 = fullScreenView.findViewById(R.id.dateValue);
            Intrinsics.checkExpressionValueIsNotNull(findViewById7, "it.findViewById<TextView>(R.id.dateValue)");
            ((TextView) findViewById7).setText(FormatUtilKt.getFormattedDate(FormatUtilKt.convertUTCToLocalTimeZone(this.$fitnessSession.getStartTime())));
            View findViewById8 = fullScreenView.findViewById(R.id.timeValue);
            Intrinsics.checkExpressionValueIsNotNull(findViewById8, "it.findViewById<TextView>(R.id.timeValue)");
            ((TextView) findViewById8).setText(fullScreenView.getContext().getString(R.string.time_format, formattedTime, formattedTime2));
            View findViewById9 = fullScreenView.findViewById(R.id.deviceValue);
            Intrinsics.checkExpressionValueIsNotNull(findViewById9, "it.findViewById<TextView>(R.id.deviceValue)");
            ((TextView) findViewById9).setText(this.$fitnessSession.getDataSource().getId());
            this.this$0.setValuesBasedOnUnits(fullScreenView);
            this.this$0.setFontFamily(fullScreenView);
            ImageButton imageButton = (ImageButton) fullScreenView.findViewById(R.id.btn_close);
            imageButton.setImageResource(R.drawable.nav_back);
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.workoutTab.DetailedView$setupWorkoutDetailedView$1$$special$$inlined$let$lambda$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RoutingService routingService;
                    view.performHapticFeedback(16);
                    MetricHelperKt.recordUserInteractionEvent(FullScreenUtil.Companion.getMetricHelper(), NavBarMetrics.Companion.getEXIT_BUTTON(), EventType.TAP);
                    routingService = DetailedView$setupWorkoutDetailedView$1.this.this$0.routingService;
                    routingService.navigateBackward();
                }
            });
            DetailedViewKt.setupSummaryFooter(fullScreenView);
            DetailedViewKt.setupSettingsButton(fullScreenView, this.$fitnessSession.getId());
            View findViewById10 = fullScreenView.findViewById(R.id.loading);
            if (findViewById10 != null) {
                findViewById10.setVisibility(8);
            }
            View findViewById11 = fullScreenView.findViewById(R.id.stats);
            if (findViewById11 != null) {
                findViewById11.setVisibility(0);
            }
            View findViewById12 = fullScreenView.findViewById(R.id.layout_data_table);
            if (findViewById12 != null) {
                findViewById12.setVisibility(0);
            }
            View findViewById13 = fullScreenView.findViewById(R.id.divider);
            if (findViewById13 != null) {
                findViewById13.setVisibility(0);
            }
            View findViewById14 = fullScreenView.findViewById(R.id.header);
            if (findViewById14 != null) {
                findViewById14.setVisibility(0);
            }
            View findViewById15 = fullScreenView.findViewById(R.id.workout_detail_footer);
            if (findViewById15 == null) {
                return;
            }
            findViewById15.setVisibility(0);
        }
    }
}
