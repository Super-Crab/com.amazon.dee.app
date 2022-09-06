package com.amazon.alexa.fitness.utils;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/utils/MetricComponent;", "", "metricName", "", "componentName", "subComponentName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getComponentName", "()Ljava/lang/String;", "getMetricName", "qualifiedComponentName", "getQualifiedComponentName", "qualifiedMetricName", "getQualifiedMetricName", "getSubComponentName", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class MetricComponent {
    @NotNull
    private final String componentName;
    @NotNull
    private final String metricName;
    @NotNull
    private final String subComponentName;

    public MetricComponent(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        GeneratedOutlineSupport1.outline169(str, "metricName", str2, "componentName", str3, "subComponentName");
        this.metricName = str;
        this.componentName = str2;
        this.subComponentName = str3;
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
}
