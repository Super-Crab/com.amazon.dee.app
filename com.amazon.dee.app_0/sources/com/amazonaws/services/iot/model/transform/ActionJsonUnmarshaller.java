package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazonaws.regions.ServiceAbbreviations;
import com.amazonaws.services.iot.model.Action;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ActionJsonUnmarshaller implements Unmarshaller<Action, JsonUnmarshallerContext> {
    private static ActionJsonUnmarshaller instance;

    ActionJsonUnmarshaller() {
    }

    public static ActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ActionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Action unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Action action = new Action();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("dynamoDB")) {
                action.setDynamoDB(DynamoDBActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("dynamoDBv2")) {
                action.setDynamoDBv2(DynamoDBv2ActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("lambda")) {
                action.setLambda(LambdaActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(ServiceAbbreviations.SNS)) {
                action.setSns(SnsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(ServiceAbbreviations.SQS)) {
                action.setSqs(SqsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(AlexaMetricsConstants.MetricsComponents.KINESIS)) {
                action.setKinesis(KinesisActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("republish")) {
                action.setRepublish(RepublishActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("s3")) {
                action.setS3(S3ActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("firehose")) {
                action.setFirehose(FirehoseActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("cloudwatchMetric")) {
                action.setCloudwatchMetric(CloudwatchMetricActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("cloudwatchAlarm")) {
                action.setCloudwatchAlarm(CloudwatchAlarmActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("elasticsearch")) {
                action.setElasticsearch(ElasticsearchActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("salesforce")) {
                action.setSalesforce(SalesforceActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("iotAnalytics")) {
                action.setIotAnalytics(IotAnalyticsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("iotEvents")) {
                action.setIotEvents(IotEventsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("stepFunctions")) {
                action.setStepFunctions(StepFunctionsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return action;
    }
}
