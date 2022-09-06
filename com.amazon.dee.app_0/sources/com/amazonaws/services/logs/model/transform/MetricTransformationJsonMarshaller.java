package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.MetricTransformation;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class MetricTransformationJsonMarshaller {
    private static MetricTransformationJsonMarshaller instance;

    MetricTransformationJsonMarshaller() {
    }

    public static MetricTransformationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MetricTransformationJsonMarshaller();
        }
        return instance;
    }

    public void marshall(MetricTransformation metricTransformation, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (metricTransformation.getMetricName() != null) {
            String metricName = metricTransformation.getMetricName();
            awsJsonWriter.name("metricName");
            awsJsonWriter.value(metricName);
        }
        if (metricTransformation.getMetricNamespace() != null) {
            String metricNamespace = metricTransformation.getMetricNamespace();
            awsJsonWriter.name("metricNamespace");
            awsJsonWriter.value(metricNamespace);
        }
        if (metricTransformation.getMetricValue() != null) {
            String metricValue = metricTransformation.getMetricValue();
            awsJsonWriter.name("metricValue");
            awsJsonWriter.value(metricValue);
        }
        if (metricTransformation.getDefaultValue() != null) {
            Double defaultValue = metricTransformation.getDefaultValue();
            awsJsonWriter.name("defaultValue");
            awsJsonWriter.value(defaultValue);
        }
        awsJsonWriter.endObject();
    }
}
