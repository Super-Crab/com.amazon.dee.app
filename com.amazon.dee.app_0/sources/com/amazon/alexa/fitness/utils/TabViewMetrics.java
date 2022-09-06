package com.amazon.alexa.fitness.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/utils/TabViewMetrics;", "", "()V", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class TabViewMetrics {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final MetricComponent HISTORY_TAB = new MetricComponent(MetricName.HISTORY_TAB, Components.ROOT, SubComponents.TAB_VIEW);
    @NotNull
    private static final MetricComponent TRACK_TAB = new MetricComponent(MetricName.TRACK_TAB, Components.ROOT, SubComponents.TAB_VIEW);

    /* compiled from: MetricConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/utils/TabViewMetrics$Companion;", "", "()V", "HISTORY_TAB", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "getHISTORY_TAB", "()Lcom/amazon/alexa/fitness/utils/MetricComponent;", "TRACK_TAB", "getTRACK_TAB", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final MetricComponent getHISTORY_TAB() {
            return TabViewMetrics.HISTORY_TAB;
        }

        @NotNull
        public final MetricComponent getTRACK_TAB() {
            return TabViewMetrics.TRACK_TAB;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
