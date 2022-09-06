package com.amazon.alexa.fitness.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/utils/NavBarMetrics;", "", "()V", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class NavBarMetrics {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final MetricComponent EXIT_BUTTON = new MetricComponent(MetricName.EXIT, Components.ROOT, SubComponents.NAV_BAR);
    @NotNull
    private static final MetricComponent HISTORY_BUTTON = new MetricComponent(MetricName.HISTORY, Components.ROOT, SubComponents.NAV_BAR);

    /* compiled from: MetricConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/utils/NavBarMetrics$Companion;", "", "()V", "EXIT_BUTTON", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "getEXIT_BUTTON", "()Lcom/amazon/alexa/fitness/utils/MetricComponent;", "HISTORY_BUTTON", "getHISTORY_BUTTON", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final MetricComponent getEXIT_BUTTON() {
            return NavBarMetrics.EXIT_BUTTON;
        }

        @NotNull
        public final MetricComponent getHISTORY_BUTTON() {
            return NavBarMetrics.HISTORY_BUTTON;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
