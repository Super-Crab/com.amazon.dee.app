package com.amazon.alexa.fitness.configuration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Configuration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0002\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/fitness/configuration/UserProfileServiceConfiguration;", "Lcom/amazon/alexa/fitness/configuration/Configuration;", "minimumHeightInCm", "", "maximumHeightInCm", "defaultGender", "", "defaultBirthday", "defaultHeightInCm", "defaultWeightInKg", "eventTypeUserProfileUpdate", "eventTypeUserProfileRequest", "eventTypeUserProfileResponse", "(DDLjava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDefaultBirthday", "()Ljava/lang/String;", "getDefaultGender", "getDefaultHeightInCm", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getDefaultWeightInKg", "getEventTypeUserProfileRequest", "getEventTypeUserProfileResponse", "getEventTypeUserProfileUpdate", "getMaximumHeightInCm", "()D", "getMinimumHeightInCm", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class UserProfileServiceConfiguration implements Configuration {
    @NotNull
    private final String defaultBirthday;
    @NotNull
    private final String defaultGender;
    @Nullable
    private final Double defaultHeightInCm;
    @Nullable
    private final Double defaultWeightInKg;
    @NotNull
    private final String eventTypeUserProfileRequest;
    @NotNull
    private final String eventTypeUserProfileResponse;
    @NotNull
    private final String eventTypeUserProfileUpdate;
    private final double maximumHeightInCm;
    private final double minimumHeightInCm;

    public UserProfileServiceConfiguration(double d, double d2, @NotNull String defaultGender, @NotNull String defaultBirthday, @Nullable Double d3, @Nullable Double d4, @NotNull String eventTypeUserProfileUpdate, @NotNull String eventTypeUserProfileRequest, @NotNull String eventTypeUserProfileResponse) {
        Intrinsics.checkParameterIsNotNull(defaultGender, "defaultGender");
        Intrinsics.checkParameterIsNotNull(defaultBirthday, "defaultBirthday");
        Intrinsics.checkParameterIsNotNull(eventTypeUserProfileUpdate, "eventTypeUserProfileUpdate");
        Intrinsics.checkParameterIsNotNull(eventTypeUserProfileRequest, "eventTypeUserProfileRequest");
        Intrinsics.checkParameterIsNotNull(eventTypeUserProfileResponse, "eventTypeUserProfileResponse");
        this.minimumHeightInCm = d;
        this.maximumHeightInCm = d2;
        this.defaultGender = defaultGender;
        this.defaultBirthday = defaultBirthday;
        this.defaultHeightInCm = d3;
        this.defaultWeightInKg = d4;
        this.eventTypeUserProfileUpdate = eventTypeUserProfileUpdate;
        this.eventTypeUserProfileRequest = eventTypeUserProfileRequest;
        this.eventTypeUserProfileResponse = eventTypeUserProfileResponse;
    }

    @NotNull
    public final String getDefaultBirthday() {
        return this.defaultBirthday;
    }

    @NotNull
    public final String getDefaultGender() {
        return this.defaultGender;
    }

    @Nullable
    public final Double getDefaultHeightInCm() {
        return this.defaultHeightInCm;
    }

    @Nullable
    public final Double getDefaultWeightInKg() {
        return this.defaultWeightInKg;
    }

    @NotNull
    public final String getEventTypeUserProfileRequest() {
        return this.eventTypeUserProfileRequest;
    }

    @NotNull
    public final String getEventTypeUserProfileResponse() {
        return this.eventTypeUserProfileResponse;
    }

    @NotNull
    public final String getEventTypeUserProfileUpdate() {
        return this.eventTypeUserProfileUpdate;
    }

    public final double getMaximumHeightInCm() {
        return this.maximumHeightInCm;
    }

    public final double getMinimumHeightInCm() {
        return this.minimumHeightInCm;
    }
}
