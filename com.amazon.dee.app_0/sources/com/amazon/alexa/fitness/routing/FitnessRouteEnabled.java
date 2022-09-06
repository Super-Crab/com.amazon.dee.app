package com.amazon.alexa.fitness.routing;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessRouteEnabled.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J.\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\nH&J.\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\nH&J.\u0010\f\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\nH&¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/routing/FitnessRouteEnabled;", "", "back", "", "navigate", "route", "Lcom/amazon/alexa/fitness/routing/FitnessRoute;", "params", "", "", "Lcom/amazon/alexa/fitness/routing/RouteParams;", "navigatePopUntil", "navigateReplaceTop", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface FitnessRouteEnabled {

    /* compiled from: FitnessRouteEnabled.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void navigate$default(FitnessRouteEnabled fitnessRouteEnabled, FitnessRoute fitnessRoute, Map map, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    map = null;
                }
                fitnessRouteEnabled.navigate(fitnessRoute, map);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: navigate");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void navigatePopUntil$default(FitnessRouteEnabled fitnessRouteEnabled, FitnessRoute fitnessRoute, Map map, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    map = null;
                }
                fitnessRouteEnabled.navigatePopUntil(fitnessRoute, map);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: navigatePopUntil");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void navigateReplaceTop$default(FitnessRouteEnabled fitnessRouteEnabled, FitnessRoute fitnessRoute, Map map, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    map = null;
                }
                fitnessRouteEnabled.navigateReplaceTop(fitnessRoute, map);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: navigateReplaceTop");
        }
    }

    void back();

    void navigate(@NotNull FitnessRoute fitnessRoute, @Nullable Map<String, String> map);

    void navigatePopUntil(@NotNull FitnessRoute fitnessRoute, @Nullable Map<String, String> map);

    void navigateReplaceTop(@NotNull FitnessRoute fitnessRoute, @Nullable Map<String, String> map);
}
