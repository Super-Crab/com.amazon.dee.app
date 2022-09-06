package com.amazon.alexa.handsfree.metrics.caching;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.metrics.caching.deserializers.ClickInteractionDetailsDeserializer;
import com.amazon.alexa.handsfree.metrics.caching.serializers.ClickInteractionDetailsSerializer;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsRecordMode;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ClickInteractionDetails;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import dagger.Lazy;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class MobilyticsMetricSerializer implements MetricSerializer {
    private static final String DESERIALIZATION_FAILURE = "Deserialization Failure";
    private static final String SERIALIZATION_FAILURE = "Serialization Failure";
    private static final String TAG = "MobilyticsMetricSerializer";
    private final Context mContext;
    private final Lazy<MetricsBuilderProvider> mMetricsBuilderProviderLazy;
    private final ObjectMapper mObjectMapper;

    @Inject
    public MobilyticsMetricSerializer(@NonNull Context context, @NonNull Lazy<MetricsBuilderProvider> lazy) {
        this.mObjectMapper = new ObjectMapper().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL).configure(JsonParser.Feature.IGNORE_UNDEFINED, true).configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true).setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE).registerModule(new SimpleModule().addDeserializer(ClickInteractionDetails.class, new ClickInteractionDetailsDeserializer()).addSerializer(ClickInteractionDetails.class, new ClickInteractionDetailsSerializer()));
        this.mMetricsBuilderProviderLazy = lazy;
        this.mContext = context;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer
    public List<Metric> deserializeMetricsList(@NonNull String str) {
        try {
            return (List) this.mObjectMapper.readValue(str, new TypeReference<List<Metric>>() { // from class: com.amazon.alexa.handsfree.metrics.caching.MobilyticsMetricSerializer.1
            });
        } catch (IOException e) {
            this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPerformanceMetric(TAG, DESERIALIZATION_FAILURE).emit(MetricsRecordMode.CHECK_BEFORE_RECORD_SKIP_CACHE, this.mContext);
            String str2 = TAG;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Error while parsing JSON into metric list: ", str, " with error: ");
            outline115.append(e.getMessage());
            Log.e(str2, outline115.toString());
            return null;
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer
    public String serializeMetricsList(@NonNull List<Metric> list) {
        try {
            return this.mObjectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            this.mMetricsBuilderProviderLazy.mo358get().newBuilder().withPerformanceMetric(TAG, SERIALIZATION_FAILURE).emit(MetricsRecordMode.CHECK_BEFORE_RECORD_SKIP_CACHE, this.mContext);
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error while transforming metric list into JSON with message: ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
            return null;
        }
    }

    @VisibleForTesting
    MobilyticsMetricSerializer(@NonNull ObjectMapper objectMapper, @NonNull Lazy<MetricsBuilderProvider> lazy, @NonNull Context context) {
        this.mObjectMapper = objectMapper;
        this.mMetricsBuilderProviderLazy = lazy;
        this.mContext = context;
    }
}
