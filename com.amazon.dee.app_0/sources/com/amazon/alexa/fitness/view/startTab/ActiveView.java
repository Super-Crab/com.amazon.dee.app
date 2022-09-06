package com.amazon.alexa.fitness.view.startTab;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import com.airbnb.lottie.LottieAnimationView;
import com.amazon.alexa.fitness.api.LocationCoordinate;
import com.amazon.alexa.fitness.api.UserPreferenceKey;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.api.afx.FitnessRoutesKt;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.fitnessSdk.Coaching;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionCommandSource;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.fitnessSdk.WorkoutType;
import com.amazon.alexa.fitness.components.CenterAdapter;
import com.amazon.alexa.fitness.components.MapViewListener;
import com.amazon.alexa.fitness.components.MarkerAdapter;
import com.amazon.alexa.fitness.components.RouteAdapter;
import com.amazon.alexa.fitness.components.WorkoutMapView;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.ActivityViewMetrics;
import com.amazon.alexa.fitness.utils.ButtonAnimationsUtilKt;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FormatUtilKt;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.utils.MapViewMetrics;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.fitness.view.animations.StopButtonAnimation;
import com.amazon.alexa.fitness.view.fullscreen.FullScreenViewController;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ActiveView.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001cH\u0002J\u000e\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001cH\u0002J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001cH\u0002J\b\u0010'\u001a\u00020\u0017H\u0002J\b\u0010(\u001a\u00020\u0017H\u0002J\u000e\u0010)\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!J\b\u0010*\u001a\u00020\u0017H\u0002J\u0006\u0010+\u001a\u00020\u0017J\u0006\u0010,\u001a\u00020\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \u000b*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006-"}, d2 = {"Lcom/amazon/alexa/fitness/view/startTab/ActiveView;", "Lcom/amazon/alexa/fitness/components/MapViewListener;", "controller", "Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;", "(Lcom/amazon/alexa/fitness/view/fullscreen/FullScreenViewController;)V", "centerAdapter", "Lcom/amazon/alexa/fitness/components/CenterAdapter;", "markerAdapter", "Lcom/amazon/alexa/fitness/components/MarkerAdapter;", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "kotlin.jvm.PlatformType", "routeAdapter", "Lcom/amazon/alexa/fitness/components/RouteAdapter;", "stopButtonAnimation", "Lcom/amazon/alexa/fitness/view/animations/StopButtonAnimation;", "userPreferenceStore", "Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "utterances", "", "", "[Ljava/lang/Integer;", "changeButtonVisibility", "", "visibility", "", "fadeInViews", "getInProgressView", "Landroid/view/View;", "hideSettingsButton", "view", "inflateAndSetupInProgressView", "currentState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "onMapOffCentered", "mapView", "Lcom/here/sdk/mapview/MapView;", "setUIVisibility", "setupActiveView", "setupPauseButton", "setupStagingView", "setupViews", "startFitnessSession", "updateToActiveView", "updateToPausedView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ActiveView implements MapViewListener {
    private final CenterAdapter centerAdapter;
    private final FullScreenViewController controller;
    private final MarkerAdapter markerAdapter;
    private final Mobilytics metrics;
    private final RouteAdapter routeAdapter;
    private final StopButtonAnimation stopButtonAnimation;
    private final UserPreferenceStore userPreferenceStore;
    private final Integer[] utterances;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FitnessSessionState.values().length];

        static {
            $EnumSwitchMapping$0[FitnessSessionState.ACTIVE.ordinal()] = 1;
            $EnumSwitchMapping$0[FitnessSessionState.PAUSED.ordinal()] = 2;
            $EnumSwitchMapping$0[FitnessSessionState.IDLE.ordinal()] = 3;
            $EnumSwitchMapping$0[FitnessSessionState.FAILED_TO_START.ordinal()] = 4;
        }
    }

    public ActiveView(@NotNull FullScreenViewController controller) {
        Intrinsics.checkParameterIsNotNull(controller, "controller");
        this.controller = controller;
        this.stopButtonAnimation = new StopButtonAnimation();
        this.utterances = new Integer[]{Integer.valueOf(R.string.alexa_hint_suggestion1), Integer.valueOf(R.string.alexa_hint_suggestion2), Integer.valueOf(R.string.alexa_hint_suggestion3), Integer.valueOf(R.string.alexa_hint_suggestion4), Integer.valueOf(R.string.alexa_hint_suggestion5), Integer.valueOf(R.string.alexa_hint_suggestion6), Integer.valueOf(R.string.alexa_hint_suggestion7)};
        this.userPreferenceStore = (UserPreferenceStore) GeneratedOutlineSupport1.outline20(UserPreferenceStore.class);
        this.metrics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
        this.centerAdapter = new CenterAdapter();
        this.markerAdapter = new MarkerAdapter();
        this.routeAdapter = new RouteAdapter();
    }

    private final void fadeInViews() {
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        TextView textView = null;
        ImageButton imageButton = fullScreenView != null ? (ImageButton) fullScreenView.findViewById(R.id.btn_pause) : null;
        if (imageButton != null) {
            imageButton.setVisibility(8);
        }
        View fullScreenView2 = FullScreenUtil.Companion.getFullScreenView();
        View findViewById = fullScreenView2 != null ? fullScreenView2.findViewById(R.id.stats) : null;
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        View fullScreenView3 = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView3 != null) {
            textView = (TextView) fullScreenView3.findViewById(R.id.lbl_utterance);
        }
        if (textView != null) {
            textView.setVisibility(8);
        }
        ViewGroup container = FullScreenUtil.Companion.getContainer();
        if (container != null) {
            container.addView(FullScreenUtil.Companion.getFullScreenView());
        }
        if (findViewById != null) {
            ButtonAnimationsUtilKt.fadeInViewWithoutDelay(findViewById);
        }
        if (textView != null) {
            ButtonAnimationsUtilKt.fadeInViewWithoutDelay(textView);
        }
        if (imageButton != null) {
            ButtonAnimationsUtilKt.showCustomButton(imageButton);
        }
    }

    private final void hideSettingsButton(View view) {
        View findViewById = view.findViewById(R.id.btn_settings);
        if (findViewById != null) {
            findViewById.setVisibility(4);
        } else {
            Log.e("AFX-InProgressView", "unable to hide settings button, component not found in view");
        }
    }

    private final void setUIVisibility(View view) {
        if (!FullScreenUtil.Companion.getFeatureService().isWorkoutHistoryTabEnabled()) {
            view.setSystemUiVisibility(5894);
        }
    }

    private final void setupActiveView(View view) {
        Log.i("AFX-InProgressView", "updating to active view");
        setupPauseButton();
        View findViewById = view.findViewById(R.id.lbl_unit_distance);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextVi…>(R.id.lbl_unit_distance)");
        ((TextView) findViewById).setText(FormatUtilKt.getDistanceUnit());
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.btn_recenter);
        if (imageButton != null) {
            imageButton.setVisibility(8);
        }
        int nextInt = new Random(System.currentTimeMillis()).nextInt(this.utterances.length);
        TextView textView = (TextView) view.findViewById(R.id.lbl_utterance);
        if (textView != null) {
            textView.setText(view.getContext().getString(this.utterances[nextInt].intValue()));
        }
        View findViewById2 = view.findViewById(R.id.btn_stop);
        if (findViewById2 != null) {
            ButtonAnimationsUtilKt.hideStopButton(findViewById2);
        }
        View findViewById3 = view.findViewById(R.id.btn_resume);
        if (findViewById3 != null) {
            ButtonAnimationsUtilKt.hideResumeButton(findViewById3);
        }
        TextView textView2 = (TextView) view.findViewById(R.id.lbl_duration);
        if (textView2 != null) {
            textView2.setTextSize(0, view.getResources().getDimension(R.dimen.text_size_num1));
        }
        TextView textView3 = (TextView) view.findViewById(R.id.lbl_time);
        if (textView3 != null) {
            textView3.setTextSize(0, view.getResources().getDimension(R.dimen.text_size_headline2));
        }
        FullScreenUtil.Companion.updateUIToDefaultFontSizes();
    }

    private final void setupPauseButton() {
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            FullScreenViewController.showMapView$default(this.controller, fullScreenView, false, false, 4, null);
            hideSettingsButton(fullScreenView);
            TextView textView = (TextView) fullScreenView.findViewById(R.id.lbl_utterance);
            if (textView != null) {
                Fonts.BOOKERLY_REGULAR_ITALIC.apply(textView);
            }
            View findViewById = fullScreenView.findViewById(R.id.lbl_unit_distance);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextVi…>(R.id.lbl_unit_distance)");
            ((TextView) findViewById).setText(FormatUtilKt.getDistanceUnit());
            ImageButton imageButton = (ImageButton) fullScreenView.findViewById(R.id.btn_pause);
            if (imageButton == null) {
                return;
            }
            imageButton.setOnClickListener(ActiveView$setupPauseButton$1$2.INSTANCE);
        }
    }

    private final void setupStagingView() {
        AppCompatImageButton appCompatImageButton;
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null && (appCompatImageButton = (AppCompatImageButton) fullScreenView.findViewById(R.id.btn_settings)) != null) {
            appCompatImageButton.setOnClickListener(ActiveView$setupStagingView$1.INSTANCE);
        }
        View fullScreenView2 = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView2 != null) {
            View findViewById = fullScreenView2.findViewById(R.id.lbl_unit_distance);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "fullScreenView.findViewB…>(R.id.lbl_unit_distance)");
            ((TextView) findViewById).setText(FormatUtilKt.getDistanceUnit());
            TextView textView = (TextView) fullScreenView2.findViewById(R.id.lbl_utterance);
            if (textView != null) {
                Fonts.BOOKERLY_REGULAR_ITALIC.apply(textView);
            }
            Button button = (Button) fullScreenView2.findViewById(R.id.btn_start);
            if (button != null) {
                Fonts.EMBER_DISPLAY_BOLD.apply(button);
                button.setEnabled(FullScreenUtil.Companion.getFitnessSessionOrchestrator().allRequiredSensorsAvailable());
                button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.startTab.ActiveView$setupStagingView$$inlined$let$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UserPreferenceStore userPreferenceStore;
                        RoutingService.RoutingBuilder addToBackStack;
                        view.performHapticFeedback(16);
                        MetricHelperKt.recordUserInteractionEvent(FullScreenUtil.Companion.getMetricHelper(), ActivityViewMetrics.Companion.getSTART_BUTTON(), EventType.TAP);
                        if (FullScreenUtil.Companion.getUserProfileRepository().getUserProfileForCurrentUser() == null) {
                            ActiveView.this.startFitnessSession();
                            return;
                        }
                        if (FullScreenUtil.Companion.getFeatureService().isMapViewEnabled()) {
                            userPreferenceStore = ActiveView.this.userPreferenceStore;
                            if (!userPreferenceStore.get(UserPreferenceKey.CompletedRouteTrackingFtue)) {
                                RoutingService.RoutingBuilder match = FullScreenUtil.Companion.getRoutingService().match(FitnessRoutesKt.FTUE_ROUTE_MAPPING_ROUTE);
                                if (match != null && (addToBackStack = match.addToBackStack()) != null) {
                                    addToBackStack.navigate();
                                }
                                Log.i("AFX-InProgressView", "routing to route mapping FTUE view");
                                return;
                            }
                        }
                        ViewGroup container = FullScreenUtil.Companion.getContainer();
                        if (container != null) {
                            container.removeAllViews();
                        }
                        FullScreenUtil.Companion companion = FullScreenUtil.Companion;
                        companion.setFullScreenView(LayoutInflater.from(companion.getCurrentContext()).inflate(R.layout.start_animation_layout, FullScreenUtil.Companion.getContainer(), false));
                        View fullScreenView3 = FullScreenUtil.Companion.getFullScreenView();
                        Boolean bool = null;
                        LottieAnimationView lottieAnimationView = fullScreenView3 != null ? (LottieAnimationView) fullScreenView3.findViewById(R.id.countdown_animation) : null;
                        Context currentContext = FullScreenUtil.Companion.getCurrentContext();
                        if (currentContext != null) {
                            bool = Boolean.valueOf(CustomStopButtonViewKt.isDarkThemeOn(currentContext));
                        }
                        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                            if (lottieAnimationView != null) {
                                lottieAnimationView.setAnimation(R.raw.countdown_dark);
                            }
                        } else if (Intrinsics.areEqual((Object) bool, (Object) false) && lottieAnimationView != null) {
                            lottieAnimationView.setAnimation(R.raw.countdown_light);
                        }
                        ViewGroup container2 = FullScreenUtil.Companion.getContainer();
                        if (container2 != null) {
                            container2.addView(FullScreenUtil.Companion.getFullScreenView());
                        }
                        FullScreenUtil.Companion.getMainHandler().postDelayed(new Runnable() { // from class: com.amazon.alexa.fitness.view.startTab.ActiveView$setupStagingView$$inlined$let$lambda$1.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                ActiveView.this.startFitnessSession();
                            }
                        }, 3500L);
                    }
                });
            }
        }
        if (StartTabContentKt.isRouteMappingOn()) {
            FullScreenUtil.Companion.updateUIToDisableFontScaling();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startFitnessSession() {
        FitnessSessionOrchestrator fitnessSessionOrchestrator = FullScreenUtil.Companion.getFitnessSessionOrchestrator();
        SessionCommandSource sessionCommandSource = SessionCommandSource.GUI;
        UUID randomUUID = UUID.randomUUID();
        Intrinsics.checkExpressionValueIsNotNull(randomUUID, "UUID.randomUUID()");
        fitnessSessionOrchestrator.receive(new Command.Start(sessionCommandSource, new SessionConfiguration(randomUUID, FullScreenUtil.Companion.getUserProfileRepository().getUserProfileForCurrentUser(), WorkoutType.WalkRun, Coaching.None.INSTANCE)), ActiveView$startFitnessSession$1.INSTANCE);
    }

    public final void changeButtonVisibility(boolean z) {
        ImageButton imageButton;
        Button button;
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null && (button = (Button) fullScreenView.findViewById(R.id.btn_start)) != null) {
            button.setEnabled(z);
        }
        View fullScreenView2 = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView2 == null || (imageButton = (ImageButton) fullScreenView2.findViewById(R.id.btn_resume)) == null) {
            return;
        }
        imageButton.setEnabled(z);
    }

    @NotNull
    public final View getInProgressView() {
        Resources resources;
        Configuration configuration;
        ViewGroup container = FullScreenUtil.Companion.getContainer();
        float f = (container == null || (resources = container.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? 0.0f : configuration.fontScale;
        Context context = null;
        if (f > 1) {
            Log.i("AFX-InProgressView", "adding active view with big font for " + f);
            ViewGroup container2 = FullScreenUtil.Companion.getContainer();
            if (container2 != null) {
                context = container2.getContext();
            }
            View inflate = LayoutInflater.from(context).inflate(R.layout.in_progress_workout_layout_big_font, FullScreenUtil.Companion.getContainer(), false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(Full…eenUtil.container, false)");
            return inflate;
        }
        Log.i("AFX-InProgressView", "adding active view");
        ViewGroup container3 = FullScreenUtil.Companion.getContainer();
        if (container3 != null) {
            context = container3.getContext();
        }
        View inflate2 = LayoutInflater.from(context).inflate(R.layout.in_progress_workout_layout, FullScreenUtil.Companion.getContainer(), false);
        Intrinsics.checkExpressionValueIsNotNull(inflate2, "LayoutInflater.from(Full…eenUtil.container, false)");
        return inflate2;
    }

    public final void inflateAndSetupInProgressView(@NotNull FitnessSessionState currentState) {
        Intrinsics.checkParameterIsNotNull(currentState, "currentState");
        ViewGroup container = FullScreenUtil.Companion.getContainer();
        if (container != null) {
            container.removeAllViews();
        }
        FullScreenUtil.Companion.setFullScreenView(getInProgressView());
        fadeInViews();
        setupViews(currentState);
    }

    @Override // com.amazon.alexa.fitness.components.MapViewListener
    public void onDestroy() {
        MapViewListener.DefaultImpls.onDestroy(this);
    }

    @Override // com.amazon.alexa.fitness.components.MapViewListener
    public void onMapOffCentered(@NotNull MapView mapView) {
        ImageButton imageButton;
        Intrinsics.checkParameterIsNotNull(mapView, "mapView");
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView == null || (imageButton = (ImageButton) fullScreenView.findViewById(R.id.btn_recenter)) == null) {
            return;
        }
        imageButton.setImageResource(R.drawable.ic_location_searching);
        imageButton.setVisibility(0);
    }

    @Override // com.amazon.alexa.fitness.components.MapViewListener
    public void onPause() {
        MapViewListener.DefaultImpls.onPause(this);
    }

    @Override // com.amazon.alexa.fitness.components.MapViewListener
    public void onResume() {
        MapViewListener.DefaultImpls.onResume(this);
    }

    public final void setupViews(@NotNull FitnessSessionState currentState) {
        Intrinsics.checkParameterIsNotNull(currentState, "currentState");
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            TextView textView = (TextView) fullScreenView.findViewById(R.id.title);
            if (textView != null) {
                Fonts.EMBER_DISPLAY_BOLD.apply(textView);
            }
            AppCompatImageButton appCompatImageButton = (AppCompatImageButton) fullScreenView.findViewById(R.id.btn_close);
            if (appCompatImageButton != null) {
                appCompatImageButton.setOnClickListener(ActiveView$setupViews$1$2$1.INSTANCE);
            }
            setUIVisibility(fullScreenView);
            int i = WhenMappings.$EnumSwitchMapping$0[currentState.ordinal()];
            if (i == 1) {
                setupPauseButton();
            } else if (i == 2) {
                updateToPausedView();
            } else if (i != 3 && i != 4) {
            } else {
                setupStagingView();
            }
        }
    }

    public final void updateToActiveView() {
        Session session = FullScreenUtil.Companion.getSession();
        FitnessSessionState previousState = session != null ? SessionDataModelsKt.previousState(session) : null;
        if (previousState != FitnessSessionState.STARTING && previousState != FitnessSessionState.RECOVERING) {
            View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
            if (fullScreenView == null) {
                return;
            }
            setupActiveView(fullScreenView);
            return;
        }
        inflateAndSetupInProgressView(FitnessSessionState.ACTIVE);
    }

    public final void updateToPausedView() {
        List<LocationCoordinate> coordinates;
        int collectionSizeOrDefault;
        View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
        if (fullScreenView != null) {
            hideSettingsButton(fullScreenView);
            TextView textView = (TextView) fullScreenView.findViewById(R.id.lbl_utterance);
            if (textView != null) {
                Fonts.BOOKERLY_ITALIC.apply(textView);
            }
            View findViewById = fullScreenView.findViewById(R.id.btn_pause);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            View findViewById2 = fullScreenView.findViewById(R.id.lbl_unit_distance);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById<TextVi…>(R.id.lbl_unit_distance)");
            ((TextView) findViewById2).setText(FormatUtilKt.getDistanceUnit());
            ImageButton imageButton = (ImageButton) fullScreenView.findViewById(R.id.btn_resume);
            if (imageButton != null) {
                imageButton.setVisibility(0);
                imageButton.setOnClickListener(ActiveView$updateToPausedView$1$3$1.INSTANCE);
                ButtonAnimationsUtilKt.showButton(imageButton);
            }
            TextView textView2 = (TextView) fullScreenView.findViewById(R.id.lbl_utterance);
            if (textView2 != null) {
                textView2.setText(fullScreenView.getContext().getString(R.string.alexa_hint_end_workout));
            }
            CustomStopButtonView customStopButtonView = (CustomStopButtonView) fullScreenView.findViewById(R.id.btn_stop);
            if (customStopButtonView != null) {
                ButtonAnimationsUtilKt.showButton(customStopButtonView);
                this.stopButtonAnimation.setup(customStopButtonView);
            }
            FullScreenViewController.showMapView$default(this.controller, fullScreenView, true, false, 4, null);
            WorkoutMapView workoutMapView = (WorkoutMapView) fullScreenView.findViewById(R.id.map_view);
            if (workoutMapView != null) {
                workoutMapView.addListener(this);
                Session activeSession = FullScreenUtil.Companion.getFitnessSessionOrchestrator().getUIState().getActiveSession();
                if (activeSession != null && (coordinates = activeSession.getCoordinates()) != null && workoutMapView.getVisibility() == 0) {
                    collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(coordinates, 10);
                    ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                    for (LocationCoordinate locationCoordinate : coordinates) {
                        arrayList.add(new GeoCoordinates(locationCoordinate.getLatitude(), locationCoordinate.getLongitude()));
                    }
                    this.routeAdapter.setCoordinates(arrayList);
                    workoutMapView.addAdapter(this.routeAdapter);
                    workoutMapView.addAdapter(this.markerAdapter);
                    workoutMapView.addAdapter(this.centerAdapter);
                    workoutMapView.update();
                    TextView textView3 = (TextView) fullScreenView.findViewById(R.id.lbl_duration);
                    if (textView3 != null) {
                        textView3.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_num2));
                    }
                    TextView textView4 = (TextView) fullScreenView.findViewById(R.id.lbl_time);
                    if (textView4 != null) {
                        textView4.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary));
                    }
                    FullScreenUtil.Companion.updateUIToDisableFontScaling();
                }
            }
            ImageButton imageButton2 = (ImageButton) fullScreenView.findViewById(R.id.btn_recenter);
            if (imageButton2 == null) {
                return;
            }
            imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.startTab.ActiveView$updateToPausedView$$inlined$let$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View button) {
                    Mobilytics metrics;
                    CenterAdapter centerAdapter;
                    MarkerAdapter markerAdapter;
                    metrics = ActiveView.this.metrics;
                    Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
                    MetricHelperKt.recordUserInteractionEvent(metrics, MapViewMetrics.INSTANCE.getRecenter(), EventType.TAP);
                    centerAdapter = ActiveView.this.centerAdapter;
                    centerAdapter.adapt();
                    markerAdapter = ActiveView.this.markerAdapter;
                    markerAdapter.adapt();
                    Intrinsics.checkExpressionValueIsNotNull(button, "button");
                    button.setVisibility(8);
                }
            });
        }
    }
}
