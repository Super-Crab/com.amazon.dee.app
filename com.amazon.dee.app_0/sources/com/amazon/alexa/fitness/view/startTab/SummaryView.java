package com.amazon.alexa.fitness.view.startTab;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import com.amazon.alexa.fitness.api.LocationCoordinate;
import com.amazon.alexa.fitness.api.afx.UISessionSummary;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.components.MapViewListener;
import com.amazon.alexa.fitness.components.SinglePointRouteAdapter;
import com.amazon.alexa.fitness.components.SummaryRouteAdapter;
import com.amazon.alexa.fitness.components.WorkoutMapView;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.ButtonAnimationsUtilKt;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FormatUtilKt;
import com.amazon.alexa.fitness.utils.FullScreenUpdaterUtil;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.utils.MapViewMetrics;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.fitness.view.fullscreen.FullScreenViewController;
import com.amazon.alexa.fitness.view.workoutTab.DetailedViewKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SummaryView.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u00007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0006\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\u0006\u0010\u0013\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/fitness/view/startTab/SummaryView;", "Lcom/amazon/alexa/fitness/components/MapViewListener;", "controller", "Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "(Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;)V", "fadeOutViewsAnimatorListener", "com/amazon/alexa/fitness/view/startTab/SummaryView$fadeOutViewsAnimatorListener$1", "Lcom/amazon/alexa/fitness/view/startTab/SummaryView$fadeOutViewsAnimatorListener$1;", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "kotlin.jvm.PlatformType", "getSummaryView", "Landroid/view/View;", "initFitnessDataForSummaryView", "", "onMapOffCentered", "mapView", "Lcom/here/sdk/mapview/MapView;", "setupSummaryView", "updateToSummaryView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SummaryView implements MapViewListener {
    private final FullScreenViewController controller;
    private final SummaryView$fadeOutViewsAnimatorListener$1 fadeOutViewsAnimatorListener;
    private final Mobilytics metrics;

    /* JADX WARN: Type inference failed for: r2v4, types: [com.amazon.alexa.fitness.view.startTab.SummaryView$fadeOutViewsAnimatorListener$1] */
    public SummaryView(@NotNull FullScreenViewController controller) {
        Intrinsics.checkParameterIsNotNull(controller, "controller");
        this.controller = controller;
        this.metrics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
        this.fadeOutViewsAnimatorListener = new Animator.AnimatorListener() { // from class: com.amazon.alexa.fitness.view.startTab.SummaryView$fadeOutViewsAnimatorListener$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(@Nullable Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@Nullable Animator animator) {
                SummaryView.this.setupSummaryView();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(@Nullable Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(@Nullable Animator animator) {
            }
        };
    }

    private final View getSummaryView() {
        Resources resources;
        Configuration configuration;
        ViewGroup container = FullScreenUtil.Companion.getContainer();
        float f = (container == null || (resources = container.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? 0.0f : configuration.fontScale;
        Context context = null;
        if (f > 1) {
            Log.i("AFX-SummaryView", "adding summary view with big font for " + f);
            ViewGroup container2 = FullScreenUtil.Companion.getContainer();
            if (container2 != null) {
                context = container2.getContext();
            }
            View inflate = LayoutInflater.from(context).inflate(R.layout.workout_summary_layout_big_font, FullScreenUtil.Companion.getContainer(), false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(Full…eenUtil.container, false)");
            return inflate;
        }
        Log.i("AFX-SummaryView", "adding summary view");
        ViewGroup container3 = FullScreenUtil.Companion.getContainer();
        if (container3 != null) {
            context = container3.getContext();
        }
        View inflate2 = LayoutInflater.from(context).inflate(R.layout.workout_summary_layout, FullScreenUtil.Companion.getContainer(), false);
        Intrinsics.checkExpressionValueIsNotNull(inflate2, "LayoutInflater.from(Full…eenUtil.container, false)");
        return inflate2;
    }

    private final void initFitnessDataForSummaryView() {
        FullScreenUpdaterUtil.Companion.initFitnessData(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupSummaryView() {
        List<LocationCoordinate> coordinates;
        int collectionSizeOrDefault;
        ViewGroup container = FullScreenUtil.Companion.getContainer();
        if (container != null) {
            container.removeAllViews();
        }
        FullScreenUtil.Companion.setFullScreenView(getSummaryView());
        ViewGroup container2 = FullScreenUtil.Companion.getContainer();
        if (container2 != null) {
            container2.addView(FullScreenUtil.Companion.getFullScreenView());
        }
        final View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            FullScreenViewController.showMapView$default(this.controller, fullScreenView, true, false, 4, null);
            TextView title = (TextView) fullScreenView.findViewById(R.id.title);
            Fonts.EMBER_DISPLAY_BOLD.apply(title);
            Session session = FullScreenUtil.Companion.getSession();
            if (session != null) {
                initFitnessDataForSummaryView();
                Intrinsics.checkExpressionValueIsNotNull(title, "title");
                title.setText(FormatUtilKt.formatPrimaryText(session.getCreatedAt()));
                View findViewById = fullScreenView.findViewById(R.id.dateValue);
                Intrinsics.checkExpressionValueIsNotNull(findViewById, "it.findViewById<TextView>(R.id.dateValue)");
                ((TextView) findViewById).setText(FormatUtilKt.getFormattedDate(session.getCreatedAt()));
                String formattedTime = FormatUtilKt.getFormattedTime(session.getCreatedAt());
                DateTime endTime = session.getEndTime();
                String formattedTime2 = endTime != null ? FormatUtilKt.getFormattedTime(endTime) : null;
                View findViewById2 = fullScreenView.findViewById(R.id.timeValue);
                Intrinsics.checkExpressionValueIsNotNull(findViewById2, "it.findViewById<TextView>(R.id.timeValue)");
                ((TextView) findViewById2).setText(fullScreenView.getContext().getString(R.string.time_format, formattedTime, formattedTime2));
                String uuid = session.getConfiguration().getSessionId().toString();
                Intrinsics.checkExpressionValueIsNotNull(uuid, "session.configuration.sessionId.toString()");
                DetailedViewKt.setupSettingsButton(fullScreenView, uuid);
                View findViewById3 = fullScreenView.findViewById(R.id.deviceValue);
                Intrinsics.checkExpressionValueIsNotNull(findViewById3, "it.findViewById<TextView>(R.id.deviceValue)");
                ((TextView) findViewById3).setText(session.getDataSource().getId());
            } else {
                Log.e("AFX-SummaryView", "no session to initialize summary view");
            }
            View findViewById4 = fullScreenView.findViewById(R.id.otherData);
            Intrinsics.checkExpressionValueIsNotNull(findViewById4, "it.findViewById<TableLayout>(R.id.otherData)");
            ButtonAnimationsUtilKt.fadeInView(findViewById4);
            AppCompatImageButton appCompatImageButton = (AppCompatImageButton) fullScreenView.findViewById(R.id.btn_close);
            if (appCompatImageButton != null) {
                appCompatImageButton.setOnClickListener(SummaryView$setupSummaryView$1$2.INSTANCE);
            }
            DetailedViewKt.setupSummaryFooter(fullScreenView);
            final WorkoutMapView mapView = (WorkoutMapView) fullScreenView.findViewById(R.id.map_view);
            mapView.addListener(this);
            UISessionSummary summary = FullScreenUtil.Companion.getFitnessSessionOrchestrator().getUIState().getSummary();
            if (summary == null || (coordinates = summary.getCoordinates()) == null) {
                return;
            }
            if (coordinates.isEmpty()) {
                Intrinsics.checkExpressionValueIsNotNull(mapView, "mapView");
                mapView.setVisibility(8);
                return;
            }
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(coordinates, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (LocationCoordinate locationCoordinate : coordinates) {
                arrayList.add(new GeoCoordinates(locationCoordinate.getLatitude(), locationCoordinate.getLongitude()));
            }
            final SummaryRouteAdapter summaryRouteAdapter = new SummaryRouteAdapter();
            summaryRouteAdapter.setCoordinates(arrayList);
            mapView.addAdapter(summaryRouteAdapter);
            if (arrayList.size() == 1) {
                mapView.addAdapter(new SinglePointRouteAdapter((GeoCoordinates) arrayList.get(0)));
            }
            mapView.update();
            ((ImageButton) fullScreenView.findViewById(R.id.btn_recenter)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.startTab.SummaryView$setupSummaryView$$inlined$let$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View button) {
                    Mobilytics metrics;
                    metrics = this.metrics;
                    Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
                    MetricHelperKt.recordUserInteractionEvent(metrics, MapViewMetrics.INSTANCE.getRefocus(), EventType.TAP);
                    SummaryRouteAdapter.this.center();
                    Intrinsics.checkExpressionValueIsNotNull(button, "button");
                    button.setVisibility(8);
                }
            });
            TextView textView = (TextView) fullScreenView.findViewById(R.id.lbl_duration);
            if (textView != null) {
                textView.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_num2));
            }
            TextView textView2 = (TextView) fullScreenView.findViewById(R.id.lbl_time);
            if (textView2 != null) {
                textView2.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary));
            }
            FullScreenUtil.Companion.updateUIToDisableFontScaling();
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
            imageButton.setImageResource(R.drawable.ic_focus);
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

    public final void updateToSummaryView() {
        List listOf;
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new View[]{fullScreenView.findViewById(R.id.lbl_utterance), fullScreenView.findViewById(R.id.btn_resume), fullScreenView.findViewById(R.id.btn_stop)});
            ButtonAnimationsUtilKt.fadeOutViews(listOf, this.fadeOutViewsAnimatorListener);
        }
    }
}
