package com.amazon.alexa.fitness.routing;

import com.amazon.alexa.fitness.api.afx.FitnessRoutesKt;
import com.amazon.alexa.routing.data.RouteName;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessRoute.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/routing/FitnessRoute;", "", "uri", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getUri", "()Ljava/lang/String;", "Home", "ElementsSettings", "UserProfile", "WorkoutHistory", "FitnessSettings", "TrackWorkout", "WorkoutDetails", "FTUEWelcome", "FTUELocationPermission", "WorkoutLearnMore", "RouteMappingLearnMore", "LocationPermissionsLearnMore", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public enum FitnessRoute {
    Home(RouteName.HOME),
    ElementsSettings(RouteName.ELEMENTS_SETTINGS),
    UserProfile(FitnessRoutesKt.USER_PROFILE_ROUTE),
    WorkoutHistory(FitnessRoutesKt.FITNESS_HISTORY_ROUTE),
    FitnessSettings(FitnessRoutesKt.FITNESS_SETTINGS),
    TrackWorkout("fitness/track"),
    WorkoutDetails(FitnessRoutesKt.FITNESS_DETAILED_ROUTE_SESSION_ID),
    FTUEWelcome(FitnessRoutesKt.FTUE_WELCOME_ROUTE),
    FTUELocationPermission(FitnessRoutesKt.FTUE_LOCATION_PERMISSIONS_ROUTE),
    WorkoutLearnMore(StaticWebRoutes.WORKOUT_LEARN_MORE_URL),
    RouteMappingLearnMore(StaticWebRoutes.LEARN_MORE_ROUTE_MAPPING_ROUTE),
    LocationPermissionsLearnMore(StaticWebRoutes.LEARN_MORE_LOCATION_PERMISSION_ROUTE);
    
    @NotNull
    private final String uri;

    FitnessRoute(String str) {
        this.uri = str;
    }

    @NotNull
    public final String getUri() {
        return this.uri;
    }
}
