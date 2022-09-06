package com.amazon.alexa.fitness.metrics;

import com.amazon.commscore.api.metrics.MobilyticsCustomEntries;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: EventMetric.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\tR\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\t¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/EventMetric;", "", "metricName", "", "componentName", "subComponentName", MobilyticsCustomEntries.CHANNEL_NAME, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannelName", "()Ljava/lang/String;", "getComponentName", "getMetricName", "qualifiedComponentName", "getQualifiedComponentName", "qualifiedMetricName", "getQualifiedMetricName", "getSubComponentName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class EventMetric {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String channelName;
    @NotNull
    private final String componentName;
    @NotNull
    private final String metricName;
    @NotNull
    private final String subComponentName;

    /* compiled from: EventMetric.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u00072\u0006\u0010\b\u001a\u00020\u0005J&\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\nj\u0002`\u000b2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/EventMetric$Companion;", "", "()V", "forComponent", "Lkotlin/Function2;", "", "Lcom/amazon/alexa/fitness/metrics/EventMetric;", "Lcom/amazon/alexa/fitness/metrics/EventMetricForSubComponentAndMetric;", "componentName", "forComponentAndSubComponent", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/metrics/EventMetricForMetricName;", "subComponentName", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Function2<String, String, EventMetric> forComponent(@NotNull String componentName) {
            Intrinsics.checkParameterIsNotNull(componentName, "componentName");
            return new EventMetric$Companion$forComponent$1(componentName);
        }

        @NotNull
        public final Function1<String, EventMetric> forComponentAndSubComponent(@NotNull String componentName, @NotNull String subComponentName) {
            Intrinsics.checkParameterIsNotNull(componentName, "componentName");
            Intrinsics.checkParameterIsNotNull(subComponentName, "subComponentName");
            return new EventMetric$Companion$forComponentAndSubComponent$1(subComponentName, componentName);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EventMetric(@NotNull String metricName, @NotNull String componentName, @NotNull String subComponentName, @NotNull String channelName) {
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        Intrinsics.checkParameterIsNotNull(componentName, "componentName");
        Intrinsics.checkParameterIsNotNull(subComponentName, "subComponentName");
        Intrinsics.checkParameterIsNotNull(channelName, "channelName");
        this.metricName = metricName;
        this.componentName = componentName;
        this.subComponentName = subComponentName;
        this.channelName = channelName;
    }

    public static /* synthetic */ EventMetric copy$default(EventMetric eventMetric, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = eventMetric.metricName;
        }
        if ((i & 2) != 0) {
            str2 = eventMetric.componentName;
        }
        if ((i & 4) != 0) {
            str3 = eventMetric.subComponentName;
        }
        if ((i & 8) != 0) {
            str4 = eventMetric.channelName;
        }
        return eventMetric.copy(str, str2, str3, str4);
    }

    @NotNull
    public final String component1() {
        return this.metricName;
    }

    @NotNull
    public final String component2() {
        return this.componentName;
    }

    @NotNull
    public final String component3() {
        return this.subComponentName;
    }

    @NotNull
    public final String component4() {
        return this.channelName;
    }

    @NotNull
    public final EventMetric copy(@NotNull String metricName, @NotNull String componentName, @NotNull String subComponentName, @NotNull String channelName) {
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        Intrinsics.checkParameterIsNotNull(componentName, "componentName");
        Intrinsics.checkParameterIsNotNull(subComponentName, "subComponentName");
        Intrinsics.checkParameterIsNotNull(channelName, "channelName");
        return new EventMetric(metricName, componentName, subComponentName, channelName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof EventMetric)) {
                return false;
            }
            EventMetric eventMetric = (EventMetric) obj;
            return Intrinsics.areEqual(this.metricName, eventMetric.metricName) && Intrinsics.areEqual(this.componentName, eventMetric.componentName) && Intrinsics.areEqual(this.subComponentName, eventMetric.subComponentName) && Intrinsics.areEqual(this.channelName, eventMetric.channelName);
        }
        return true;
    }

    @NotNull
    public final String getChannelName() {
        return this.channelName;
    }

    @NotNull
    public final String getComponentName() {
        return this.componentName;
    }

    @NotNull
    public final String getMetricName() {
        return this.metricName;
    }

    @NotNull
    public final String getQualifiedComponentName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("fitness.");
        outline107.append(this.componentName);
        outline107.append('.');
        outline107.append(this.subComponentName);
        return outline107.toString();
    }

    @NotNull
    public final String getQualifiedMetricName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("fitness.");
        outline107.append(this.componentName);
        outline107.append('.');
        outline107.append(this.subComponentName);
        outline107.append(JsonReaderKt.COLON);
        outline107.append(this.metricName);
        return outline107.toString();
    }

    @NotNull
    public final String getSubComponentName() {
        return this.subComponentName;
    }

    public int hashCode() {
        String str = this.metricName;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.componentName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.subComponentName;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.channelName;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EventMetric(metricName=");
        outline107.append(this.metricName);
        outline107.append(", componentName=");
        outline107.append(this.componentName);
        outline107.append(", subComponentName=");
        outline107.append(this.subComponentName);
        outline107.append(", channelName=");
        return GeneratedOutlineSupport1.outline91(outline107, this.channelName, ")");
    }

    public /* synthetic */ EventMetric(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? "fitness" : str4);
    }
}
