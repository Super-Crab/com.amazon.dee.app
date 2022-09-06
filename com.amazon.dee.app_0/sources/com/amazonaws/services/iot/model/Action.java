package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class Action implements Serializable {
    private CloudwatchAlarmAction cloudwatchAlarm;
    private CloudwatchMetricAction cloudwatchMetric;
    private DynamoDBAction dynamoDB;
    private DynamoDBv2Action dynamoDBv2;
    private ElasticsearchAction elasticsearch;
    private FirehoseAction firehose;
    private IotAnalyticsAction iotAnalytics;
    private IotEventsAction iotEvents;
    private KinesisAction kinesis;
    private LambdaAction lambda;
    private RepublishAction republish;
    private S3Action s3;
    private SalesforceAction salesforce;
    private SnsAction sns;
    private SqsAction sqs;
    private StepFunctionsAction stepFunctions;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Action)) {
            return false;
        }
        Action action = (Action) obj;
        if ((action.getDynamoDB() == null) ^ (getDynamoDB() == null)) {
            return false;
        }
        if (action.getDynamoDB() != null && !action.getDynamoDB().equals(getDynamoDB())) {
            return false;
        }
        if ((action.getDynamoDBv2() == null) ^ (getDynamoDBv2() == null)) {
            return false;
        }
        if (action.getDynamoDBv2() != null && !action.getDynamoDBv2().equals(getDynamoDBv2())) {
            return false;
        }
        if ((action.getLambda() == null) ^ (getLambda() == null)) {
            return false;
        }
        if (action.getLambda() != null && !action.getLambda().equals(getLambda())) {
            return false;
        }
        if ((action.getSns() == null) ^ (getSns() == null)) {
            return false;
        }
        if (action.getSns() != null && !action.getSns().equals(getSns())) {
            return false;
        }
        if ((action.getSqs() == null) ^ (getSqs() == null)) {
            return false;
        }
        if (action.getSqs() != null && !action.getSqs().equals(getSqs())) {
            return false;
        }
        if ((action.getKinesis() == null) ^ (getKinesis() == null)) {
            return false;
        }
        if (action.getKinesis() != null && !action.getKinesis().equals(getKinesis())) {
            return false;
        }
        if ((action.getRepublish() == null) ^ (getRepublish() == null)) {
            return false;
        }
        if (action.getRepublish() != null && !action.getRepublish().equals(getRepublish())) {
            return false;
        }
        if ((action.getS3() == null) ^ (getS3() == null)) {
            return false;
        }
        if (action.getS3() != null && !action.getS3().equals(getS3())) {
            return false;
        }
        if ((action.getFirehose() == null) ^ (getFirehose() == null)) {
            return false;
        }
        if (action.getFirehose() != null && !action.getFirehose().equals(getFirehose())) {
            return false;
        }
        if ((action.getCloudwatchMetric() == null) ^ (getCloudwatchMetric() == null)) {
            return false;
        }
        if (action.getCloudwatchMetric() != null && !action.getCloudwatchMetric().equals(getCloudwatchMetric())) {
            return false;
        }
        if ((action.getCloudwatchAlarm() == null) ^ (getCloudwatchAlarm() == null)) {
            return false;
        }
        if (action.getCloudwatchAlarm() != null && !action.getCloudwatchAlarm().equals(getCloudwatchAlarm())) {
            return false;
        }
        if ((action.getElasticsearch() == null) ^ (getElasticsearch() == null)) {
            return false;
        }
        if (action.getElasticsearch() != null && !action.getElasticsearch().equals(getElasticsearch())) {
            return false;
        }
        if ((action.getSalesforce() == null) ^ (getSalesforce() == null)) {
            return false;
        }
        if (action.getSalesforce() != null && !action.getSalesforce().equals(getSalesforce())) {
            return false;
        }
        if ((action.getIotAnalytics() == null) ^ (getIotAnalytics() == null)) {
            return false;
        }
        if (action.getIotAnalytics() != null && !action.getIotAnalytics().equals(getIotAnalytics())) {
            return false;
        }
        if ((action.getIotEvents() == null) ^ (getIotEvents() == null)) {
            return false;
        }
        if (action.getIotEvents() != null && !action.getIotEvents().equals(getIotEvents())) {
            return false;
        }
        if ((action.getStepFunctions() == null) ^ (getStepFunctions() == null)) {
            return false;
        }
        return action.getStepFunctions() == null || action.getStepFunctions().equals(getStepFunctions());
    }

    public CloudwatchAlarmAction getCloudwatchAlarm() {
        return this.cloudwatchAlarm;
    }

    public CloudwatchMetricAction getCloudwatchMetric() {
        return this.cloudwatchMetric;
    }

    public DynamoDBAction getDynamoDB() {
        return this.dynamoDB;
    }

    public DynamoDBv2Action getDynamoDBv2() {
        return this.dynamoDBv2;
    }

    public ElasticsearchAction getElasticsearch() {
        return this.elasticsearch;
    }

    public FirehoseAction getFirehose() {
        return this.firehose;
    }

    public IotAnalyticsAction getIotAnalytics() {
        return this.iotAnalytics;
    }

    public IotEventsAction getIotEvents() {
        return this.iotEvents;
    }

    public KinesisAction getKinesis() {
        return this.kinesis;
    }

    public LambdaAction getLambda() {
        return this.lambda;
    }

    public RepublishAction getRepublish() {
        return this.republish;
    }

    public S3Action getS3() {
        return this.s3;
    }

    public SalesforceAction getSalesforce() {
        return this.salesforce;
    }

    public SnsAction getSns() {
        return this.sns;
    }

    public SqsAction getSqs() {
        return this.sqs;
    }

    public StepFunctionsAction getStepFunctions() {
        return this.stepFunctions;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((((((((((((getDynamoDB() == null ? 0 : getDynamoDB().hashCode()) + 31) * 31) + (getDynamoDBv2() == null ? 0 : getDynamoDBv2().hashCode())) * 31) + (getLambda() == null ? 0 : getLambda().hashCode())) * 31) + (getSns() == null ? 0 : getSns().hashCode())) * 31) + (getSqs() == null ? 0 : getSqs().hashCode())) * 31) + (getKinesis() == null ? 0 : getKinesis().hashCode())) * 31) + (getRepublish() == null ? 0 : getRepublish().hashCode())) * 31) + (getS3() == null ? 0 : getS3().hashCode())) * 31) + (getFirehose() == null ? 0 : getFirehose().hashCode())) * 31) + (getCloudwatchMetric() == null ? 0 : getCloudwatchMetric().hashCode())) * 31) + (getCloudwatchAlarm() == null ? 0 : getCloudwatchAlarm().hashCode())) * 31) + (getElasticsearch() == null ? 0 : getElasticsearch().hashCode())) * 31) + (getSalesforce() == null ? 0 : getSalesforce().hashCode())) * 31) + (getIotAnalytics() == null ? 0 : getIotAnalytics().hashCode())) * 31) + (getIotEvents() == null ? 0 : getIotEvents().hashCode())) * 31;
        if (getStepFunctions() != null) {
            i = getStepFunctions().hashCode();
        }
        return hashCode + i;
    }

    public void setCloudwatchAlarm(CloudwatchAlarmAction cloudwatchAlarmAction) {
        this.cloudwatchAlarm = cloudwatchAlarmAction;
    }

    public void setCloudwatchMetric(CloudwatchMetricAction cloudwatchMetricAction) {
        this.cloudwatchMetric = cloudwatchMetricAction;
    }

    public void setDynamoDB(DynamoDBAction dynamoDBAction) {
        this.dynamoDB = dynamoDBAction;
    }

    public void setDynamoDBv2(DynamoDBv2Action dynamoDBv2Action) {
        this.dynamoDBv2 = dynamoDBv2Action;
    }

    public void setElasticsearch(ElasticsearchAction elasticsearchAction) {
        this.elasticsearch = elasticsearchAction;
    }

    public void setFirehose(FirehoseAction firehoseAction) {
        this.firehose = firehoseAction;
    }

    public void setIotAnalytics(IotAnalyticsAction iotAnalyticsAction) {
        this.iotAnalytics = iotAnalyticsAction;
    }

    public void setIotEvents(IotEventsAction iotEventsAction) {
        this.iotEvents = iotEventsAction;
    }

    public void setKinesis(KinesisAction kinesisAction) {
        this.kinesis = kinesisAction;
    }

    public void setLambda(LambdaAction lambdaAction) {
        this.lambda = lambdaAction;
    }

    public void setRepublish(RepublishAction republishAction) {
        this.republish = republishAction;
    }

    public void setS3(S3Action s3Action) {
        this.s3 = s3Action;
    }

    public void setSalesforce(SalesforceAction salesforceAction) {
        this.salesforce = salesforceAction;
    }

    public void setSns(SnsAction snsAction) {
        this.sns = snsAction;
    }

    public void setSqs(SqsAction sqsAction) {
        this.sqs = sqsAction;
    }

    public void setStepFunctions(StepFunctionsAction stepFunctionsAction) {
        this.stepFunctions = stepFunctionsAction;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDynamoDB() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("dynamoDB: ");
            outline1072.append(getDynamoDB());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getDynamoDBv2() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("dynamoDBv2: ");
            outline1073.append(getDynamoDBv2());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLambda() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("lambda: ");
            outline1074.append(getLambda());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getSns() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("sns: ");
            outline1075.append(getSns());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getSqs() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("sqs: ");
            outline1076.append(getSqs());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getKinesis() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("kinesis: ");
            outline1077.append(getKinesis());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getRepublish() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("republish: ");
            outline1078.append(getRepublish());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getS3() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("s3: ");
            outline1079.append(getS3());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getFirehose() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("firehose: ");
            outline10710.append(getFirehose());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getCloudwatchMetric() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("cloudwatchMetric: ");
            outline10711.append(getCloudwatchMetric());
            outline10711.append(",");
            outline107.append(outline10711.toString());
        }
        if (getCloudwatchAlarm() != null) {
            StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("cloudwatchAlarm: ");
            outline10712.append(getCloudwatchAlarm());
            outline10712.append(",");
            outline107.append(outline10712.toString());
        }
        if (getElasticsearch() != null) {
            StringBuilder outline10713 = GeneratedOutlineSupport1.outline107("elasticsearch: ");
            outline10713.append(getElasticsearch());
            outline10713.append(",");
            outline107.append(outline10713.toString());
        }
        if (getSalesforce() != null) {
            StringBuilder outline10714 = GeneratedOutlineSupport1.outline107("salesforce: ");
            outline10714.append(getSalesforce());
            outline10714.append(",");
            outline107.append(outline10714.toString());
        }
        if (getIotAnalytics() != null) {
            StringBuilder outline10715 = GeneratedOutlineSupport1.outline107("iotAnalytics: ");
            outline10715.append(getIotAnalytics());
            outline10715.append(",");
            outline107.append(outline10715.toString());
        }
        if (getIotEvents() != null) {
            StringBuilder outline10716 = GeneratedOutlineSupport1.outline107("iotEvents: ");
            outline10716.append(getIotEvents());
            outline10716.append(",");
            outline107.append(outline10716.toString());
        }
        if (getStepFunctions() != null) {
            StringBuilder outline10717 = GeneratedOutlineSupport1.outline107("stepFunctions: ");
            outline10717.append(getStepFunctions());
            outline107.append(outline10717.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Action withCloudwatchAlarm(CloudwatchAlarmAction cloudwatchAlarmAction) {
        this.cloudwatchAlarm = cloudwatchAlarmAction;
        return this;
    }

    public Action withCloudwatchMetric(CloudwatchMetricAction cloudwatchMetricAction) {
        this.cloudwatchMetric = cloudwatchMetricAction;
        return this;
    }

    public Action withDynamoDB(DynamoDBAction dynamoDBAction) {
        this.dynamoDB = dynamoDBAction;
        return this;
    }

    public Action withDynamoDBv2(DynamoDBv2Action dynamoDBv2Action) {
        this.dynamoDBv2 = dynamoDBv2Action;
        return this;
    }

    public Action withElasticsearch(ElasticsearchAction elasticsearchAction) {
        this.elasticsearch = elasticsearchAction;
        return this;
    }

    public Action withFirehose(FirehoseAction firehoseAction) {
        this.firehose = firehoseAction;
        return this;
    }

    public Action withIotAnalytics(IotAnalyticsAction iotAnalyticsAction) {
        this.iotAnalytics = iotAnalyticsAction;
        return this;
    }

    public Action withIotEvents(IotEventsAction iotEventsAction) {
        this.iotEvents = iotEventsAction;
        return this;
    }

    public Action withKinesis(KinesisAction kinesisAction) {
        this.kinesis = kinesisAction;
        return this;
    }

    public Action withLambda(LambdaAction lambdaAction) {
        this.lambda = lambdaAction;
        return this;
    }

    public Action withRepublish(RepublishAction republishAction) {
        this.republish = republishAction;
        return this;
    }

    public Action withS3(S3Action s3Action) {
        this.s3 = s3Action;
        return this;
    }

    public Action withSalesforce(SalesforceAction salesforceAction) {
        this.salesforce = salesforceAction;
        return this;
    }

    public Action withSns(SnsAction snsAction) {
        this.sns = snsAction;
        return this;
    }

    public Action withSqs(SqsAction sqsAction) {
        this.sqs = sqsAction;
        return this;
    }

    public Action withStepFunctions(StepFunctionsAction stepFunctionsAction) {
        this.stepFunctions = stepFunctionsAction;
        return this;
    }
}
