package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ElasticsearchAction;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ElasticsearchActionJsonMarshaller {
    private static ElasticsearchActionJsonMarshaller instance;

    ElasticsearchActionJsonMarshaller() {
    }

    public static ElasticsearchActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ElasticsearchActionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ElasticsearchAction elasticsearchAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (elasticsearchAction.getRoleArn() != null) {
            String roleArn = elasticsearchAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (elasticsearchAction.getEndpoint() != null) {
            String endpoint = elasticsearchAction.getEndpoint();
            awsJsonWriter.name("endpoint");
            awsJsonWriter.value(endpoint);
        }
        if (elasticsearchAction.getIndex() != null) {
            String index = elasticsearchAction.getIndex();
            awsJsonWriter.name("index");
            awsJsonWriter.value(index);
        }
        if (elasticsearchAction.getType() != null) {
            String type = elasticsearchAction.getType();
            awsJsonWriter.name("type");
            awsJsonWriter.value(type);
        }
        if (elasticsearchAction.getId() != null) {
            String id = elasticsearchAction.getId();
            awsJsonWriter.name("id");
            awsJsonWriter.value(id);
        }
        awsJsonWriter.endObject();
    }
}
