package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazonaws.regions.Regions;
import com.google.common.base.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public class KinesisEndpoint implements Endpoint {
    private final String appState;
    private final Regions awsRegion;
    private final String cognitoPoolId;
    private final long flushSize;
    private final long flushTime;
    private final boolean metaMetricsEnabled;
    private final String protobufStreamName;
    private final String streamName;
    private final String tag;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface AppState {
        public static final String ANY = "Any";
        public static final String BACKGROUND = "Background";
        public static final String FOREGROUND = "Foreground";
    }

    public KinesisEndpoint(@NonNull String str, @NonNull Regions regions, @NonNull Config.Stream stream) {
        this.cognitoPoolId = (String) Preconditions.checkNotNull(str);
        this.awsRegion = (Regions) Preconditions.checkNotNull(regions);
        this.appState = (String) Preconditions.checkNotNull(stream.appState());
        this.streamName = (String) Preconditions.checkNotNull(stream.name());
        this.protobufStreamName = (String) Preconditions.checkNotNull(stream.protobufStreamName());
        this.tag = (String) Preconditions.checkNotNull(stream.tag());
        this.flushTime = stream.flushTime();
        this.flushSize = stream.flushSize();
        this.metaMetricsEnabled = stream.metaMetrics();
    }

    @NonNull
    public String appState() {
        return this.appState;
    }

    @NonNull
    public Regions awsRegion() {
        return this.awsRegion;
    }

    @NonNull
    public String cognitoIdentityPoolId() {
        return this.cognitoPoolId;
    }

    public long flushSize() {
        return this.flushSize;
    }

    public long flushTime() {
        return this.flushTime;
    }

    public boolean isMetaMetricsEnabled() {
        return this.metaMetricsEnabled;
    }

    @NonNull
    public String protobufStreamName() {
        return this.protobufStreamName;
    }

    @NonNull
    public String streamName() {
        return this.streamName;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.Endpoint
    @NonNull
    public String tag() {
        return this.tag;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.Endpoint
    public int type() {
        return 0;
    }
}
