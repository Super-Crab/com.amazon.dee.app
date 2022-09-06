package com.amazon.alexa.fitness.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.fitness.api.afx.FeatureService;
import com.amazon.alexa.fitness.api.afx.FitnessRoutesKt;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.UserProfileRepository;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazon.alexa.fitness.routing.StaticWebRoutes;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.view.message.CustomToast;
import com.amazon.alexa.fitness.view.workoutTab.ListItem;
import com.amazon.alexa.fitness.view.workoutTab.WorkoutItem;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FullScreenUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/utils/FullScreenUtil;", "", "()V", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FullScreenUtil {
    @Nullable
    private static ViewGroup container;
    @Nullable
    private static View fullScreenView;
    @Nullable
    private static Session session;
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    @NotNull
    private static RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline22(RoutingService.class, "ComponentRegistry.getIns…ervice::class.java).get()");
    @NotNull
    private static final UserProfileRepository userProfileRepository = (UserProfileRepository) GeneratedOutlineSupport1.outline22(UserProfileRepository.class, "ComponentRegistry.getIns…sitory::class.java).get()");
    @NotNull
    private static final Mobilytics metricHelper = (Mobilytics) GeneratedOutlineSupport1.outline22(Mobilytics.class, "ComponentRegistry.getIns…lytics::class.java).get()");
    @NotNull
    private static FitnessSessionOrchestrator fitnessSessionOrchestrator = (FitnessSessionOrchestrator) GeneratedOutlineSupport1.outline22(FitnessSessionOrchestrator.class, "ComponentRegistry.getIns…trator::class.java).get()");
    @NotNull
    private static FeatureService featureService = (FeatureService) GeneratedOutlineSupport1.outline22(FeatureService.class, "ComponentRegistry.getIns…ervice::class.java).get()");
    @NotNull
    private static final IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline22(IdentityService.class, "ComponentRegistry.getIns…ervice::class.java).get()");
    private static final NetworkService networkService = (NetworkService) GeneratedOutlineSupport1.outline20(NetworkService.class);

    /* compiled from: FullScreenUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010@\u001a\u0004\u0018\u00010AJ\n\u0010B\u001a\u0004\u0018\u00010CH\u0002J\u000e\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GJ\u0006\u0010H\u001a\u00020EJ\u0006\u0010I\u001a\u00020EJ\u0006\u0010J\u001a\u00020EJ\u0006\u0010K\u001a\u00020EJ\u0006\u0010L\u001a\u00020EJ\u0006\u0010M\u001a\u00020EJ\u0006\u0010N\u001a\u00020EJ\u0006\u0010O\u001a\u00020EJ\u0006\u0010P\u001a\u00020EJ\u0006\u0010Q\u001a\u00020EJ\u0006\u0010R\u001a\u00020EJ\u0006\u0010S\u001a\u00020EJ\u0006\u0010T\u001a\u00020EJ\u0006\u0010U\u001a\u00020VJ\u0006\u0010W\u001a\u00020EJ\u0006\u0010X\u001a\u00020ER\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020$¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0019\u0010+\u001a\n -*\u0004\u0018\u00010,0,¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001a\u00100\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00106\u001a\u0004\u0018\u000107X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0011\u0010<\u001a\u00020=¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?¨\u0006Y"}, d2 = {"Lcom/amazon/alexa/fitness/utils/FullScreenUtil$Companion;", "", "()V", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "getContainer", "()Landroid/view/ViewGroup;", "setContainer", "(Landroid/view/ViewGroup;)V", "currentContext", "Landroid/content/Context;", "getCurrentContext", "()Landroid/content/Context;", "featureService", "Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "getFeatureService", "()Lcom/amazon/alexa/fitness/api/afx/FeatureService;", "setFeatureService", "(Lcom/amazon/alexa/fitness/api/afx/FeatureService;)V", "fitnessSessionOrchestrator", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "getFitnessSessionOrchestrator", "()Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "setFitnessSessionOrchestrator", "(Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;)V", "fullScreenView", "Landroid/view/View;", "getFullScreenView", "()Landroid/view/View;", "setFullScreenView", "(Landroid/view/View;)V", "identityService", "Lcom/amazon/alexa/identity/api/IdentityService;", "getIdentityService", "()Lcom/amazon/alexa/identity/api/IdentityService;", "mainHandler", "Landroid/os/Handler;", "getMainHandler", "()Landroid/os/Handler;", "metricHelper", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "getMetricHelper", "()Lcom/amazon/alexa/mobilytics/Mobilytics;", "networkService", "Lcom/amazon/alexa/protocols/network/NetworkService;", "kotlin.jvm.PlatformType", "getNetworkService", "()Lcom/amazon/alexa/protocols/network/NetworkService;", "routingService", "Lcom/amazon/alexa/routing/api/RoutingService;", "getRoutingService", "()Lcom/amazon/alexa/routing/api/RoutingService;", "setRoutingService", "(Lcom/amazon/alexa/routing/api/RoutingService;)V", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "getSession", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "setSession", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;)V", "userProfileRepository", "Lcom/amazon/alexa/fitness/api/afx/UserProfileRepository;", "getUserProfileRepository", "()Lcom/amazon/alexa/fitness/api/afx/UserProfileRepository;", "getUserFirstName", "", MetricsConstants.OperationalMetrics.GET_USER_PROFILE, "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "goDetailedView", "", "workoutItem", "Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutItem;", "goFitnessSettings", "goFtueLocationPermissions", "goFtueWelcome", "goHistoryTab", "goHome", "goLearnMoreLocationPermissions", "goLearnMoreRouteMapping", "goLearnMoreView", "goPreviousView", "goSettings", "goTrackTab", "goUserProfile", "goWorkoutHistory", "isUserProfileSet", "", "updateUIToDefaultFontSizes", "updateUIToDisableFontScaling", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        private final UserProfile getUserProfile() {
            UserProfile userProfile;
            Session session = getSession();
            return (session == null || (userProfile = session.getUserProfile()) == null) ? getUserProfileRepository().getUserProfileForCurrentUser() : userProfile;
        }

        @Nullable
        public final ViewGroup getContainer() {
            return FullScreenUtil.container;
        }

        @Nullable
        public final Context getCurrentContext() {
            ViewGroup container = FullScreenUtil.Companion.getContainer();
            if (container != null) {
                return container.getContext();
            }
            return null;
        }

        @NotNull
        public final FeatureService getFeatureService() {
            return FullScreenUtil.featureService;
        }

        @NotNull
        public final FitnessSessionOrchestrator getFitnessSessionOrchestrator() {
            return FullScreenUtil.fitnessSessionOrchestrator;
        }

        @Nullable
        public final View getFullScreenView() {
            return FullScreenUtil.fullScreenView;
        }

        @NotNull
        public final IdentityService getIdentityService() {
            return FullScreenUtil.identityService;
        }

        @NotNull
        public final Handler getMainHandler() {
            return FullScreenUtil.mainHandler;
        }

        @NotNull
        public final Mobilytics getMetricHelper() {
            return FullScreenUtil.metricHelper;
        }

        public final NetworkService getNetworkService() {
            return FullScreenUtil.networkService;
        }

        @NotNull
        public final RoutingService getRoutingService() {
            return FullScreenUtil.routingService;
        }

        @Nullable
        public final Session getSession() {
            return FullScreenUtil.session;
        }

        @Nullable
        public final String getUserFirstName() {
            com.amazon.alexa.identity.api.UserProfile userProfile;
            UserIdentity user = getIdentityService().getUser("AFX-FullScreenUtils");
            if (user == null || (userProfile = user.getUserProfile()) == null) {
                return null;
            }
            return userProfile.getFirstName();
        }

        @NotNull
        public final UserProfileRepository getUserProfileRepository() {
            return FullScreenUtil.userProfileRepository;
        }

        public final void goDetailedView(@NotNull WorkoutItem workoutItem) {
            Intrinsics.checkParameterIsNotNull(workoutItem, "workoutItem");
            NetworkService networkService = getNetworkService();
            Intrinsics.checkExpressionValueIsNotNull(networkService, "networkService");
            if (networkService.isConnected()) {
                ListItem listItem = (ListItem) workoutItem;
                RoutingService.RoutingBuilder match = getRoutingService().match(FitnessRoutesKt.FITNESS_DETAILED_ROUTE_SESSION_ID);
                if (match != null) {
                    match.with("id", listItem.getFitnessSession().getId());
                    match.addToBackStack().navigate();
                    Log.i("AFX-FullScreenUtils", "routing to detailed view");
                    return;
                }
                Log.e("AFX-FullScreenUtils", "failed to find workout detail route");
                return;
            }
            CustomToast.Companion.showNotification$default(CustomToast.Companion, R.string.no_internet_connection, null, 2, null);
        }

        public final void goFitnessSettings() {
            RoutingService.RoutingBuilder addToBackStack;
            RoutingService.RoutingBuilder match = FullScreenUtil.Companion.getRoutingService().match(FitnessRoutesKt.FITNESS_SETTINGS);
            if (match != null && (addToBackStack = match.addToBackStack()) != null) {
                addToBackStack.navigate();
            }
            Log.i("AFX-FullScreenUtils", "routing to fitness settings screen");
        }

        public final void goFtueLocationPermissions() {
            RoutingService.RoutingBuilder addToBackStack;
            RoutingService.RoutingBuilder match = getRoutingService().match(FitnessRoutesKt.FTUE_LOCATION_PERMISSIONS_ROUTE);
            if (match != null && (addToBackStack = match.addToBackStack()) != null) {
                addToBackStack.navigate();
            }
            Log.i("AFX-FullScreenUtils", "routing to ftue location permissions view ");
        }

        public final void goFtueWelcome() {
            RoutingService.RoutingBuilder addToBackStack;
            RoutingService.RoutingBuilder match = getRoutingService().match(FitnessRoutesKt.FTUE_WELCOME_ROUTE);
            if (match != null && (addToBackStack = match.addToBackStack()) != null) {
                addToBackStack.navigate();
            }
            Log.i("AFX-FullScreenUtils", "routing to ftue welcome view ");
        }

        public final void goHistoryTab() {
            RoutingService.RoutingBuilder match = getRoutingService().match(FitnessRoutesKt.FITNESS_HISTORY_ROUTE);
            if (match != null) {
                match.navigateReplaceTop();
            }
            Log.i("AFX-FullScreenUtils", "routing to history tab");
        }

        public final void goHome() {
            ViewGroup container = getContainer();
            if (container != null) {
                container.removeAllViews();
            }
            RoutingService.RoutingBuilder route = getRoutingService().route(RouteName.HOME);
            Intrinsics.checkExpressionValueIsNotNull(route, "routingService.route(RouteName.HOME)");
            route.popUntil();
            Log.i("AFX-FullScreenUtils", "routing to home screen");
        }

        public final void goLearnMoreLocationPermissions() {
            RoutingService.RoutingBuilder addToBackStack;
            RoutingService.RoutingBuilder match = getRoutingService().match(StaticWebRoutes.LEARN_MORE_LOCATION_PERMISSION_ROUTE);
            if (match != null && (addToBackStack = match.addToBackStack()) != null) {
                addToBackStack.navigate();
            }
            Log.i("AFX-FullScreenUtils", "routing to learn more about location permissions");
        }

        public final void goLearnMoreRouteMapping() {
            RoutingService.RoutingBuilder addToBackStack;
            RoutingService.RoutingBuilder match = getRoutingService().match(StaticWebRoutes.LEARN_MORE_ROUTE_MAPPING_ROUTE);
            if (match != null && (addToBackStack = match.addToBackStack()) != null) {
                addToBackStack.navigate();
            }
            Log.i("AFX-FullScreenUtils", "routing to learn more about route mapping");
        }

        public final void goLearnMoreView() {
            String string;
            Context currentContext = getCurrentContext();
            if (currentContext == null || (string = currentContext.getString(R.string.learn_more_url)) == null) {
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(string));
            intent.setFlags(268435456);
            Context currentContext2 = FullScreenUtil.Companion.getCurrentContext();
            if (currentContext2 != null) {
                currentContext2.startActivity(intent);
            }
            Log.i("AFX-FullScreenUtils", "routing to learn more view ");
        }

        public final void goPreviousView() {
            getRoutingService().navigateBackward();
            Log.i("AFX-FullScreenUtils", "routing to previous view ");
        }

        public final void goSettings() {
            Log.i("AFX-FullScreenUtils", "routing to settings page");
            RoutingService.RoutingBuilder match = getRoutingService().match(RouteName.ELEMENTS_SETTINGS);
            if (match != null) {
                match.navigate();
            }
        }

        public final void goTrackTab() {
            RoutingService.RoutingBuilder match = getRoutingService().match("fitness/track");
            if (match != null) {
                match.navigateReplaceTop();
            }
            Log.i("AFX-FullScreenUtils", "routing to track tab");
        }

        public final void goUserProfile() {
            RoutingService.RoutingBuilder addToBackStack;
            RoutingService.RoutingBuilder match = getRoutingService().match(FitnessRoutesKt.USER_PROFILE_ROUTE);
            if (match != null && (addToBackStack = match.addToBackStack()) != null) {
                addToBackStack.navigate();
            }
            Log.i("AFX-FullScreenUtils", "routing to react native user profile screen");
        }

        public final void goWorkoutHistory() {
            RoutingService.RoutingBuilder addToBackStack;
            RoutingService.RoutingBuilder match = getRoutingService().match(FitnessRoutesKt.WORKOUT_HISTORY_ROUTE);
            if (match != null && (addToBackStack = match.addToBackStack()) != null) {
                addToBackStack.navigate();
            }
            Log.i("AFX-FullScreenUtils", "routing to react native workout history page");
        }

        public final boolean isUserProfileSet() {
            UserProfile userProfile = getUserProfile();
            return (userProfile != null ? Double.valueOf(userProfile.getHeightInCentimetres()) : null) != null;
        }

        public final void setContainer(@Nullable ViewGroup viewGroup) {
            FullScreenUtil.container = viewGroup;
        }

        public final void setFeatureService(@NotNull FeatureService featureService) {
            Intrinsics.checkParameterIsNotNull(featureService, "<set-?>");
            FullScreenUtil.featureService = featureService;
        }

        public final void setFitnessSessionOrchestrator(@NotNull FitnessSessionOrchestrator fitnessSessionOrchestrator) {
            Intrinsics.checkParameterIsNotNull(fitnessSessionOrchestrator, "<set-?>");
            FullScreenUtil.fitnessSessionOrchestrator = fitnessSessionOrchestrator;
        }

        public final void setFullScreenView(@Nullable View view) {
            FullScreenUtil.fullScreenView = view;
        }

        public final void setRoutingService(@NotNull RoutingService routingService) {
            Intrinsics.checkParameterIsNotNull(routingService, "<set-?>");
            FullScreenUtil.routingService = routingService;
        }

        public final void setSession(@Nullable Session session) {
            FullScreenUtil.session = session;
        }

        public final void updateUIToDefaultFontSizes() {
            View fullScreenView;
            Resources resources;
            Configuration configuration;
            ViewGroup container = FullScreenUtil.Companion.getContainer();
            if (((container == null || (resources = container.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? 0.0f : configuration.fontScale) >= 1 && (fullScreenView = getFullScreenView()) != null) {
                TextView textView = (TextView) fullScreenView.findViewById(R.id.lbl_duration);
                if (textView != null) {
                    textView.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_num1));
                }
                TextView textView2 = (TextView) fullScreenView.findViewById(R.id.lbl_time);
                if (textView2 != null) {
                    textView2.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_headline2));
                }
                TextView textView3 = (TextView) fullScreenView.findViewById(R.id.lbl_utterance);
                if (textView3 != null) {
                    textView3.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_hintSecondary));
                }
                TextView textView4 = (TextView) fullScreenView.findViewById(R.id.lbl_distance);
                if (textView4 != null) {
                    textView4.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_headline2));
                }
                TextView textView5 = (TextView) fullScreenView.findViewById(R.id.lbl_calories);
                if (textView5 != null) {
                    textView5.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_headline2));
                }
                TextView textView6 = (TextView) fullScreenView.findViewById(R.id.lbl_steps);
                if (textView6 != null) {
                    textView6.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_headline2));
                }
                TextView textView7 = (TextView) fullScreenView.findViewById(R.id.lbl_pace);
                if (textView7 != null) {
                    textView7.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_headline2));
                }
                TextView textView8 = (TextView) fullScreenView.findViewById(R.id.lbl_unit_distance);
                if (textView8 != null) {
                    textView8.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary));
                }
                TextView textView9 = (TextView) fullScreenView.findViewById(R.id.lbl_unit_calories);
                if (textView9 != null) {
                    textView9.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary));
                }
                TextView textView10 = (TextView) fullScreenView.findViewById(R.id.lbl_unit_steps);
                if (textView10 != null) {
                    textView10.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary));
                }
                TextView textView11 = (TextView) fullScreenView.findViewById(R.id.lbl_unit_pace);
                if (textView11 != null) {
                    textView11.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary));
                }
                TableLayout tableLayout = (TableLayout) fullScreenView.findViewById(R.id.measurements);
                if (tableLayout == null) {
                    return;
                }
                ViewGroup.LayoutParams layoutParams = tableLayout.getLayoutParams();
                if (layoutParams == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                }
                ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = tableLayout.getResources().getDimensionPixelSize(R.dimen.action_button_end_margin);
            }
        }

        public final void updateUIToDisableFontScaling() {
            View fullScreenView;
            Resources resources;
            Configuration configuration;
            ViewGroup container = FullScreenUtil.Companion.getContainer();
            if (((container == null || (resources = container.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? 0.0f : configuration.fontScale) >= 1 && (fullScreenView = getFullScreenView()) != null) {
                TextView textView = (TextView) fullScreenView.findViewById(R.id.lbl_duration);
                if (textView != null) {
                    textView.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_num2_font_scale_disabled));
                }
                TextView textView2 = (TextView) fullScreenView.findViewById(R.id.lbl_time);
                if (textView2 != null) {
                    textView2.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary_font_scale_disabled));
                }
                TextView textView3 = (TextView) fullScreenView.findViewById(R.id.lbl_utterance);
                if (textView3 != null) {
                    textView3.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_hintSecondary_font_scale_disabled));
                }
                TextView textView4 = (TextView) fullScreenView.findViewById(R.id.lbl_distance);
                if (textView4 != null) {
                    textView4.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_headline2_font_scale_disabled));
                }
                TextView textView5 = (TextView) fullScreenView.findViewById(R.id.lbl_calories);
                if (textView5 != null) {
                    textView5.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_headline2_font_scale_disabled));
                }
                TextView textView6 = (TextView) fullScreenView.findViewById(R.id.lbl_steps);
                if (textView6 != null) {
                    textView6.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_headline2_font_scale_disabled));
                }
                TextView textView7 = (TextView) fullScreenView.findViewById(R.id.lbl_pace);
                if (textView7 != null) {
                    textView7.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_headline2_font_scale_disabled));
                }
                TextView textView8 = (TextView) fullScreenView.findViewById(R.id.lbl_unit_distance);
                if (textView8 != null) {
                    textView8.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary_font_scale_disabled));
                }
                TextView textView9 = (TextView) fullScreenView.findViewById(R.id.lbl_unit_calories);
                if (textView9 != null) {
                    textView9.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary_font_scale_disabled));
                }
                TextView textView10 = (TextView) fullScreenView.findViewById(R.id.lbl_unit_steps);
                if (textView10 != null) {
                    textView10.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary_font_scale_disabled));
                }
                TextView textView11 = (TextView) fullScreenView.findViewById(R.id.lbl_unit_pace);
                if (textView11 != null) {
                    textView11.setTextSize(0, fullScreenView.getResources().getDimension(R.dimen.text_size_secondary_font_scale_disabled));
                }
                TableLayout tableLayout = (TableLayout) fullScreenView.findViewById(R.id.measurements);
                if (tableLayout == null) {
                    return;
                }
                ViewGroup.LayoutParams layoutParams = tableLayout.getLayoutParams();
                if (layoutParams == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                }
                ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = tableLayout.getResources().getDimensionPixelSize(R.dimen.margin_table_spacing);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
