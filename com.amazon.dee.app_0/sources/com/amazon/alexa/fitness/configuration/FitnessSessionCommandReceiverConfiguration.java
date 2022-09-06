package com.amazon.alexa.fitness.configuration;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Configuration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/configuration/FitnessSessionCommandReceiverConfiguration;", "Lcom/amazon/alexa/fitness/configuration/Configuration;", "eventTypeFitnessSessionCommandReceiverUpdate", "", "eventTypeFitnessSessionCommandReceiverRequest", "eventTypeFitnessSessionCommandReceiverNotify", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEventTypeFitnessSessionCommandReceiverNotify", "()Ljava/lang/String;", "getEventTypeFitnessSessionCommandReceiverRequest", "getEventTypeFitnessSessionCommandReceiverUpdate", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSessionCommandReceiverConfiguration implements Configuration {
    @NotNull
    private final String eventTypeFitnessSessionCommandReceiverNotify;
    @NotNull
    private final String eventTypeFitnessSessionCommandReceiverRequest;
    @NotNull
    private final String eventTypeFitnessSessionCommandReceiverUpdate;

    public FitnessSessionCommandReceiverConfiguration(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        GeneratedOutlineSupport1.outline169(str, "eventTypeFitnessSessionCommandReceiverUpdate", str2, "eventTypeFitnessSessionCommandReceiverRequest", str3, "eventTypeFitnessSessionCommandReceiverNotify");
        this.eventTypeFitnessSessionCommandReceiverUpdate = str;
        this.eventTypeFitnessSessionCommandReceiverRequest = str2;
        this.eventTypeFitnessSessionCommandReceiverNotify = str3;
    }

    @NotNull
    public final String getEventTypeFitnessSessionCommandReceiverNotify() {
        return this.eventTypeFitnessSessionCommandReceiverNotify;
    }

    @NotNull
    public final String getEventTypeFitnessSessionCommandReceiverRequest() {
        return this.eventTypeFitnessSessionCommandReceiverRequest;
    }

    @NotNull
    public final String getEventTypeFitnessSessionCommandReceiverUpdate() {
        return this.eventTypeFitnessSessionCommandReceiverUpdate;
    }
}
