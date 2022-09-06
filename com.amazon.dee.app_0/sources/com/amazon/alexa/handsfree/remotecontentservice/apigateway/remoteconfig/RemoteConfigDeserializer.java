package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.DefaultConfigProvider;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class RemoteConfigDeserializer {
    private static final String TAG = "RemoteConfigDeserializer";
    private final DefaultConfigProvider mDefaultConfigProvider;
    private final MetricsReporter mMetricsReporter;
    private final ObjectMapper mObjectMapper;

    @Inject
    public RemoteConfigDeserializer(@NonNull Context context, @NonNull MetricsReporter metricsReporter) {
        this(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).setSerializationInclusion(JsonInclude.Include.NON_NULL), metricsReporter, new DefaultConfigProvider(context));
    }

    @Nullable
    public RemoteConfig deserialize(@Nullable String str) {
        if (str != null && !str.isEmpty()) {
            try {
                String str2 = TAG;
                Log.d(str2, "Deserializing remote config: " + str);
                return (RemoteConfig) this.mObjectMapper.readValue(str, RemoteConfig.class);
            } catch (IOException | RuntimeException e) {
                Log.e(TAG, "Caught deserialization error", e, new Object[0]);
                this.mMetricsReporter.reportFetchFalcoRemoteConfigJobDeserializationFailure();
            }
        }
        return deserializeDefaultJson();
    }

    @Nullable
    @VisibleForTesting
    RemoteConfig deserializeDefaultJson() {
        try {
            String defaultConfig = this.mDefaultConfigProvider.getDefaultConfig();
            String str = TAG;
            Log.d(str, "Deserializing default config file: " + defaultConfig);
            return (RemoteConfig) this.mObjectMapper.readValue(defaultConfig, RemoteConfig.class);
        } catch (IOException e) {
            Log.e(TAG, "Caught deserialization error", e, new Object[0]);
            this.mMetricsReporter.reportFetchFalcoRemoteConfigJobDeserializationFailure();
            return null;
        }
    }

    @VisibleForTesting
    RemoteConfigDeserializer(@NonNull ObjectMapper objectMapper, @NonNull MetricsReporter metricsReporter, @NonNull DefaultConfigProvider defaultConfigProvider) {
        this.mObjectMapper = objectMapper;
        this.mMetricsReporter = metricsReporter;
        this.mDefaultConfigProvider = defaultConfigProvider;
    }
}
