package com.amazon.alexa.photos.uploadV2;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.photos.uploadV2.CdaSdkPreferences;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.SpecificEndpointConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
/* loaded from: classes9.dex */
public class CdaSdkPreferences {
    static final String CDA_SDK_PREFS_ENDPOINT = "cdaSdkPrefsEndpoint";
    @VisibleForTesting
    static final String KEY_CACHED_ENDPOINT_CONFIGURATION = "key_cached_endpoint_configuration";
    private static final String TAG = "CdaSdkPreferences";
    @NonNull
    private final Context context;
    @NonNull
    private final Logger logger;
    @NonNull
    private final Metrics metrics;
    private SharedPreferences sharedPreferences;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public enum MetricsEvent {
        CdaSdkPrefDeserializeFailed,
        CdaSdkPrefSerializeFailed,
        CdaSdkPrefRemoveCache
    }

    public CdaSdkPreferences(@NonNull Context context, @NonNull Metrics metrics, @NonNull Logger logger) {
        this.context = context;
        this.metrics = metrics;
        this.logger = logger;
    }

    private SharedPreferences getSharedPreferences() {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences == null) {
            this.sharedPreferences = this.context.getApplicationContext().getSharedPreferences(CDA_SDK_PREFS_ENDPOINT, 0);
            return this.sharedPreferences;
        }
        return sharedPreferences;
    }

    private void recordMetrics(@NonNull final MetricsEvent metricsEvent) {
        this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$CdaSdkPreferences$JJtKhdg6MqHeksKmULfJi28WXU4
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                return CdaSdkPreferences.MetricsEvent.this.name();
            }
        }, new MetricRecordingType[0]);
    }

    @Nullable
    public SpecificEndpointConfiguration getEndpointConfiguration() {
        String string = getSharedPreferences().getString(KEY_CACHED_ENDPOINT_CONFIGURATION, null);
        if (string == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return (SpecificEndpointConfiguration) objectMapper.readValue(string, SpecificEndpointConfiguration.class);
        } catch (IOException e) {
            this.logger.e(TAG, "No valid endpoint configuration in cache", e);
            recordMetrics(MetricsEvent.CdaSdkPrefDeserializeFailed);
            return null;
        }
    }

    @AnyThread
    public void removeEndpointConfiguration() {
        getSharedPreferences().edit().remove(KEY_CACHED_ENDPOINT_CONFIGURATION).apply();
        recordMetrics(MetricsEvent.CdaSdkPrefRemoveCache);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEndpointConfiguration(@NonNull SpecificEndpointConfiguration specificEndpointConfiguration) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String string = getSharedPreferences().getString(KEY_CACHED_ENDPOINT_CONFIGURATION, null);
            String writeValueAsString = objectMapper.writeValueAsString(specificEndpointConfiguration);
            if (writeValueAsString == null || writeValueAsString.equals(string)) {
                return;
            }
            getSharedPreferences().edit().putString(KEY_CACHED_ENDPOINT_CONFIGURATION, writeValueAsString).apply();
        } catch (JsonProcessingException e) {
            this.logger.e(TAG, "Failed to write endpoint configuration to shared prefs", e);
            recordMetrics(MetricsEvent.CdaSdkPrefSerializeFailed);
        }
    }
}
