package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MetricValue;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;
/* loaded from: classes13.dex */
class MetricValueJsonMarshaller {
    private static MetricValueJsonMarshaller instance;

    MetricValueJsonMarshaller() {
    }

    public static MetricValueJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new MetricValueJsonMarshaller();
        }
        return instance;
    }

    public void marshall(MetricValue metricValue, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (metricValue.getCount() != null) {
            Long count = metricValue.getCount();
            awsJsonWriter.name("count");
            awsJsonWriter.value(count);
        }
        if (metricValue.getCidrs() != null) {
            List<String> cidrs = metricValue.getCidrs();
            awsJsonWriter.name("cidrs");
            awsJsonWriter.beginArray();
            for (String str : cidrs) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (metricValue.getPorts() != null) {
            List<Integer> ports = metricValue.getPorts();
            awsJsonWriter.name("ports");
            awsJsonWriter.beginArray();
            for (Integer num : ports) {
                if (num != null) {
                    awsJsonWriter.value(num);
                }
            }
            awsJsonWriter.endArray();
        }
        awsJsonWriter.endObject();
    }
}
