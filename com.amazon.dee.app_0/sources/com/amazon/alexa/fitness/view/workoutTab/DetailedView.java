package com.amazon.alexa.fitness.view.workoutTab;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.components.MapViewListener;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.AfitsDataHelper;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FormatUtilKt;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.view.message.CustomToast;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import com.here.sdk.mapview.MapView;
import java.util.Locale;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DetailedView.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0018H\u0002J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0018H\u0002J\u000e\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u000e*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/DetailedView;", "Lcom/amazon/alexa/fitness/components/MapViewListener;", "afitsDataHelper", "Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "(Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;)V", "getAfitsDataHelper", "()Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "getFitnessSessionCallback", "Lcom/dee/app/http/CoralService$Callback;", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "getGetFitnessSessionCallback", "()Lcom/dee/app/http/CoralService$Callback;", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "kotlin.jvm.PlatformType", "networkService", "Lcom/amazon/alexa/protocols/network/NetworkService;", "routingService", "Lcom/amazon/alexa/routing/api/RoutingService;", "onMapOffCentered", "", "mapView", "Lcom/here/sdk/mapview/MapView;", "populateDetailViewContent", "Landroid/view/View;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", "setFontFamily", "view", "setValuesBasedOnUnits", "setupWorkoutDetailedView", "fitnessSession", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DetailedView implements MapViewListener {
    @NotNull
    private final AfitsDataHelper afitsDataHelper;
    @NotNull
    private final CoralService.Callback<FitnessSession> getFitnessSessionCallback;
    private final Mobilytics metrics;
    private final NetworkService networkService;
    private final RoutingService routingService;

    public DetailedView(@NotNull AfitsDataHelper afitsDataHelper) {
        Intrinsics.checkParameterIsNotNull(afitsDataHelper, "afitsDataHelper");
        this.afitsDataHelper = afitsDataHelper;
        this.metrics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
        this.routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
        this.networkService = (NetworkService) GeneratedOutlineSupport1.outline20(NetworkService.class);
        this.getFitnessSessionCallback = new CoralService.Callback<FitnessSession>() { // from class: com.amazon.alexa.fitness.view.workoutTab.DetailedView$getFitnessSessionCallback$1
            @Override // com.dee.app.http.CoralService.Callback
            public void onFailure(@Nullable CoralService.Call<FitnessSession> call, @Nullable CoralServiceException coralServiceException) {
                Log.e("AFX-WorkoutDetailedView", "An error occurred trying to load the fitness session", coralServiceException);
                new Handler(Looper.getMainLooper()).post(DetailedView$getFitnessSessionCallback$1$onFailure$1.INSTANCE);
            }

            @Override // com.dee.app.http.CoralService.Callback
            public void onResult(@Nullable CoralService.Call<FitnessSession> call, @Nullable FitnessSession fitnessSession) {
                if (fitnessSession != null) {
                    String id = fitnessSession.getId();
                    if (!(id == null || id.length() == 0)) {
                        DetailedView.this.setupWorkoutDetailedView(fitnessSession);
                        return;
                    }
                }
                Log.e("AFX-WorkoutDetailedView", "No fitness session was retrieved");
                new Handler(Looper.getMainLooper()).post(DetailedView$getFitnessSessionCallback$1$onResult$1.INSTANCE);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFontFamily(View view) {
        Fonts.EMBER_BOLD.apply((TextView) view.findViewById(R.id.title));
        Fonts.EMBER_BOLD.apply((TextView) view.findViewById(R.id.lbl_duration));
        Fonts.EMBER_BOLD.apply((TextView) view.findViewById(R.id.lbl_distance));
        Fonts.EMBER_BOLD.apply((TextView) view.findViewById(R.id.lbl_calories));
        Fonts.EMBER_BOLD.apply((TextView) view.findViewById(R.id.lbl_pace));
        Fonts.EMBER_BOLD.apply((TextView) view.findViewById(R.id.lbl_steps));
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.lbl_unit_pace));
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.lbl_time));
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.lbl_unit_distance));
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.lbl_unit_calories));
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.lbl_unit_pace));
        Fonts.EMBER_REGULAR.apply((TextView) view.findViewById(R.id.lbl_unit_steps));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setValuesBasedOnUnits(View view) {
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (FormatUtilKt.usesMetricsSystem(locale)) {
            View findViewById = view.findViewById(R.id.lbl_unit_distance);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextVi…>(R.id.lbl_unit_distance)");
            ((TextView) findViewById).setText(view.getResources().getString(R.string.distanceUnits_km));
            return;
        }
        View findViewById2 = view.findViewById(R.id.lbl_unit_distance);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById<TextVi…>(R.id.lbl_unit_distance)");
        ((TextView) findViewById2).setText(view.getResources().getString(R.string.distanceUnits));
    }

    @NotNull
    public final AfitsDataHelper getAfitsDataHelper() {
        return this.afitsDataHelper;
    }

    @NotNull
    public final CoralService.Callback<FitnessSession> getGetFitnessSessionCallback() {
        return this.getFitnessSessionCallback;
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

    @NotNull
    public final View populateDetailViewContent(@NotNull String sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        ViewGroup container = FullScreenUtil.Companion.getContainer();
        View view = LayoutInflater.from(container != null ? container.getContext() : null).inflate(R.layout.workout_details_layout, FullScreenUtil.Companion.getContainer(), false);
        FullScreenUtil.Companion.setFullScreenView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.loading);
        if (imageView != null) {
            Drawable background = imageView.getBackground();
            if (background == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.AnimationDrawable");
            }
            ((AnimationDrawable) background).start();
        }
        View findViewById = view.findViewById(R.id.stats);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        View findViewById2 = view.findViewById(R.id.layout_data_table);
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
        View findViewById3 = view.findViewById(R.id.divider);
        if (findViewById3 != null) {
            findViewById3.setVisibility(8);
        }
        View findViewById4 = view.findViewById(R.id.workout_detail_footer);
        if (findViewById4 != null) {
            findViewById4.setVisibility(8);
        }
        View findViewById5 = view.findViewById(R.id.header);
        if (findViewById5 != null) {
            findViewById5.setVisibility(8);
        }
        View findViewById6 = view.findViewById(R.id.map_view);
        if (findViewById6 != null) {
            findViewById6.setVisibility(8);
        }
        NetworkService networkService = this.networkService;
        Intrinsics.checkExpressionValueIsNotNull(networkService, "networkService");
        if (networkService.isConnected()) {
            this.afitsDataHelper.getAfitsClient().getFitnessSession(sessionId, this.getFitnessSessionCallback);
        } else {
            CustomToast.Companion.showNotification$default(CustomToast.Companion, R.string.no_internet_connection, null, 2, null);
            FullScreenUtil.Companion.goHistoryTab();
        }
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        return view;
    }

    public final void setupWorkoutDetailedView(@NotNull FitnessSession fitnessSession) {
        Intrinsics.checkParameterIsNotNull(fitnessSession, "fitnessSession");
        new Handler(Looper.getMainLooper()).post(new DetailedView$setupWorkoutDetailedView$1(this, fitnessSession));
    }
}
