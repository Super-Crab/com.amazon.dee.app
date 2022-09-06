package com.amazon.alexa.fitness.configuration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Configuration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/configuration/HdsClientConfiguration;", "Lcom/amazon/alexa/fitness/configuration/Configuration;", "endpoint", "", "timeshiftKey", "connectTimeout", "", "readTimeout", "maxRetries", "(Ljava/lang/String;Ljava/lang/String;III)V", "getConnectTimeout", "()I", "getEndpoint", "()Ljava/lang/String;", "getMaxRetries", "getReadTimeout", "getTimeshiftKey", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HdsClientConfiguration implements Configuration {
    private final int connectTimeout;
    @NotNull
    private final String endpoint;
    private final int maxRetries;
    private final int readTimeout;
    @NotNull
    private final String timeshiftKey;

    public HdsClientConfiguration(@NotNull String endpoint, @NotNull String timeshiftKey, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(endpoint, "endpoint");
        Intrinsics.checkParameterIsNotNull(timeshiftKey, "timeshiftKey");
        this.endpoint = endpoint;
        this.timeshiftKey = timeshiftKey;
        this.connectTimeout = i;
        this.readTimeout = i2;
        this.maxRetries = i3;
    }

    public final int getConnectTimeout() {
        return this.connectTimeout;
    }

    @NotNull
    public final String getEndpoint() {
        return this.endpoint;
    }

    public final int getMaxRetries() {
        return this.maxRetries;
    }

    public final int getReadTimeout() {
        return this.readTimeout;
    }

    @NotNull
    public final String getTimeshiftKey() {
        return this.timeshiftKey;
    }
}
