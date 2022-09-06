package com.amazonaws.services.kinesis;

import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.http.JsonResponseHandler;
import com.amazonaws.http.UrlHttpClient;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.services.kinesis.model.AddTagsToStreamRequest;
import com.amazonaws.services.kinesis.model.CreateStreamRequest;
import com.amazonaws.services.kinesis.model.DecreaseStreamRetentionPeriodRequest;
import com.amazonaws.services.kinesis.model.DeleteStreamRequest;
import com.amazonaws.services.kinesis.model.DescribeLimitsRequest;
import com.amazonaws.services.kinesis.model.DescribeLimitsResult;
import com.amazonaws.services.kinesis.model.DescribeStreamRequest;
import com.amazonaws.services.kinesis.model.DescribeStreamResult;
import com.amazonaws.services.kinesis.model.DescribeStreamSummaryRequest;
import com.amazonaws.services.kinesis.model.DescribeStreamSummaryResult;
import com.amazonaws.services.kinesis.model.DisableEnhancedMonitoringRequest;
import com.amazonaws.services.kinesis.model.DisableEnhancedMonitoringResult;
import com.amazonaws.services.kinesis.model.EnableEnhancedMonitoringRequest;
import com.amazonaws.services.kinesis.model.EnableEnhancedMonitoringResult;
import com.amazonaws.services.kinesis.model.GetRecordsRequest;
import com.amazonaws.services.kinesis.model.GetRecordsResult;
import com.amazonaws.services.kinesis.model.GetShardIteratorRequest;
import com.amazonaws.services.kinesis.model.GetShardIteratorResult;
import com.amazonaws.services.kinesis.model.IncreaseStreamRetentionPeriodRequest;
import com.amazonaws.services.kinesis.model.ListShardsRequest;
import com.amazonaws.services.kinesis.model.ListShardsResult;
import com.amazonaws.services.kinesis.model.ListStreamsRequest;
import com.amazonaws.services.kinesis.model.ListStreamsResult;
import com.amazonaws.services.kinesis.model.ListTagsForStreamRequest;
import com.amazonaws.services.kinesis.model.ListTagsForStreamResult;
import com.amazonaws.services.kinesis.model.MergeShardsRequest;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.amazonaws.services.kinesis.model.RemoveTagsFromStreamRequest;
import com.amazonaws.services.kinesis.model.SplitShardRequest;
import com.amazonaws.services.kinesis.model.StartStreamEncryptionRequest;
import com.amazonaws.services.kinesis.model.StopStreamEncryptionRequest;
import com.amazonaws.services.kinesis.model.UpdateShardCountRequest;
import com.amazonaws.services.kinesis.model.UpdateShardCountResult;
import com.amazonaws.services.kinesis.model.transform.AddTagsToStreamRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.CreateStreamRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.DecreaseStreamRetentionPeriodRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.DeleteStreamRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.DescribeLimitsRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.DescribeLimitsResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.DescribeStreamRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.DescribeStreamResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.DescribeStreamSummaryRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.DescribeStreamSummaryResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.DisableEnhancedMonitoringRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.DisableEnhancedMonitoringResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.EnableEnhancedMonitoringRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.EnableEnhancedMonitoringResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.ExpiredIteratorExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.ExpiredNextTokenExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.GetRecordsRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.GetRecordsResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.GetShardIteratorRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.GetShardIteratorResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.IncreaseStreamRetentionPeriodRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.InvalidArgumentExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.KMSAccessDeniedExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.KMSDisabledExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.KMSInvalidStateExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.KMSNotFoundExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.KMSOptInRequiredExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.KMSThrottlingExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.LimitExceededExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.ListShardsRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.ListShardsResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.ListStreamsRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.ListStreamsResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.ListTagsForStreamRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.ListTagsForStreamResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.MergeShardsRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.ProvisionedThroughputExceededExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.PutRecordRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.PutRecordResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.PutRecordsRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.PutRecordsResultJsonUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.RemoveTagsFromStreamRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.ResourceInUseExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.ResourceNotFoundExceptionUnmarshaller;
import com.amazonaws.services.kinesis.model.transform.SplitShardRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.StartStreamEncryptionRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.StopStreamEncryptionRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.UpdateShardCountRequestMarshaller;
import com.amazonaws.services.kinesis.model.transform.UpdateShardCountResultJsonUnmarshaller;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.util.AWSRequestMetrics;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public class AmazonKinesisClient extends AmazonWebServiceClient implements AmazonKinesis {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected List<JsonErrorUnmarshaller> jsonErrorUnmarshallers;

    @Deprecated
    public AmazonKinesisClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration clientConfiguration) {
        return clientConfiguration;
    }

    private void init() {
        this.jsonErrorUnmarshallers = new ArrayList();
        this.jsonErrorUnmarshallers.add(new ExpiredIteratorExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ExpiredNextTokenExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidArgumentExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSAccessDeniedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSDisabledExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSInvalidStateExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSOptInRequiredExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new KMSThrottlingExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new LimitExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ProvisionedThroughputExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceInUseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new JsonErrorUnmarshaller());
        setEndpoint("kinesis.us-east-1.amazonaws.com");
        this.endpointPrefix = AlexaMetricsConstants.MetricsComponents.KINESIS;
        HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandlerChain("/com/amazonaws/services/kinesis/request.handlers"));
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandler2Chain("/com/amazonaws/services/kinesis/request.handler2s"));
    }

    private <X, Y extends AmazonWebServiceRequest> Response<X> invoke(Request<Y> request, HttpResponseHandler<AmazonWebServiceResponse<X>> httpResponseHandler, ExecutionContext executionContext) {
        request.setEndpoint(this.endpoint);
        request.setTimeOffset(this.timeOffset);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
        try {
            AWSCredentials mo6648getCredentials = this.awsCredentialsProvider.mo6648getCredentials();
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
            AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
            if (originalRequest != null && originalRequest.getRequestCredentials() != null) {
                mo6648getCredentials = originalRequest.getRequestCredentials();
            }
            executionContext.setCredentials(mo6648getCredentials);
            return this.client.execute(request, httpResponseHandler, new JsonErrorResponseHandler(this.jsonErrorUnmarshallers), executionContext);
        } catch (Throwable th) {
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.AddTagsToStreamRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void addTagsToStream(AddTagsToStreamRequest addTagsToStreamRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(addTagsToStreamRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<AddTagsToStreamRequest> marshall = new AddTagsToStreamRequestMarshaller().marshall((AddTagsToStreamRequest) addTagsToStreamRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, null, true);
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, addTagsToStreamRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            addTagsToStreamRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, addTagsToStreamRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.CreateStreamRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void createStream(CreateStreamRequest createStreamRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createStreamRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateStreamRequest> marshall = new CreateStreamRequestMarshaller().marshall((CreateStreamRequest) createStreamRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, null, true);
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, createStreamRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createStreamRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createStreamRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.kinesis.model.DecreaseStreamRetentionPeriodRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void decreaseStreamRetentionPeriod(DecreaseStreamRetentionPeriodRequest decreaseStreamRetentionPeriodRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(decreaseStreamRetentionPeriodRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DecreaseStreamRetentionPeriodRequest> marshall = new DecreaseStreamRetentionPeriodRequestMarshaller().marshall((DecreaseStreamRetentionPeriodRequest) decreaseStreamRetentionPeriodRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, null, true);
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, decreaseStreamRetentionPeriodRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            decreaseStreamRetentionPeriodRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, decreaseStreamRetentionPeriodRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.DeleteStreamRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void deleteStream(DeleteStreamRequest deleteStreamRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteStreamRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteStreamRequest> marshall = new DeleteStreamRequestMarshaller().marshall((DeleteStreamRequest) deleteStreamRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, null, true);
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, deleteStreamRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteStreamRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteStreamRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.DescribeLimitsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public DescribeLimitsResult describeLimits(DescribeLimitsRequest describeLimitsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeLimitsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeLimitsRequest> marshall = new DescribeLimitsRequestMarshaller().marshall((DescribeLimitsRequest) describeLimitsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeLimitsResultJsonUnmarshaller()), createExecutionContext);
                        DescribeLimitsResult describeLimitsResult = (DescribeLimitsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeLimitsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, describeLimitsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeLimitsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeLimitsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.DescribeStreamRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public DescribeStreamResult describeStream(DescribeStreamRequest describeStreamRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeStreamRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeStreamRequest> marshall = new DescribeStreamRequestMarshaller().marshall((DescribeStreamRequest) describeStreamRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeStreamResultJsonUnmarshaller()), createExecutionContext);
                        DescribeStreamResult describeStreamResult = (DescribeStreamResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeStreamResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, describeStreamRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeStreamRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeStreamRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.DescribeStreamSummaryRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public DescribeStreamSummaryResult describeStreamSummary(DescribeStreamSummaryRequest describeStreamSummaryRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeStreamSummaryRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeStreamSummaryRequest> marshall = new DescribeStreamSummaryRequestMarshaller().marshall((DescribeStreamSummaryRequest) describeStreamSummaryRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeStreamSummaryResultJsonUnmarshaller()), createExecutionContext);
                        DescribeStreamSummaryResult describeStreamSummaryResult = (DescribeStreamSummaryResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeStreamSummaryResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, describeStreamSummaryRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeStreamSummaryRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeStreamSummaryRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.DisableEnhancedMonitoringRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public DisableEnhancedMonitoringResult disableEnhancedMonitoring(DisableEnhancedMonitoringRequest disableEnhancedMonitoringRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(disableEnhancedMonitoringRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DisableEnhancedMonitoringRequest> marshall = new DisableEnhancedMonitoringRequestMarshaller().marshall((DisableEnhancedMonitoringRequest) disableEnhancedMonitoringRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DisableEnhancedMonitoringResultJsonUnmarshaller()), createExecutionContext);
                        DisableEnhancedMonitoringResult disableEnhancedMonitoringResult = (DisableEnhancedMonitoringResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return disableEnhancedMonitoringResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, disableEnhancedMonitoringRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            disableEnhancedMonitoringRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, disableEnhancedMonitoringRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.EnableEnhancedMonitoringRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public EnableEnhancedMonitoringResult enableEnhancedMonitoring(EnableEnhancedMonitoringRequest enableEnhancedMonitoringRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(enableEnhancedMonitoringRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<EnableEnhancedMonitoringRequest> marshall = new EnableEnhancedMonitoringRequestMarshaller().marshall((EnableEnhancedMonitoringRequest) enableEnhancedMonitoringRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new EnableEnhancedMonitoringResultJsonUnmarshaller()), createExecutionContext);
                        EnableEnhancedMonitoringResult enableEnhancedMonitoringResult = (EnableEnhancedMonitoringResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return enableEnhancedMonitoringResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, enableEnhancedMonitoringRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            enableEnhancedMonitoringRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, enableEnhancedMonitoringRequest, null, true);
            throw th;
        }
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    @Deprecated
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest) {
        return this.client.getResponseMetadataForRequest(amazonWebServiceRequest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.GetRecordsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public GetRecordsResult getRecords(GetRecordsRequest getRecordsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getRecordsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetRecordsRequest> marshall = new GetRecordsRequestMarshaller().marshall((GetRecordsRequest) getRecordsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetRecordsResultJsonUnmarshaller()), createExecutionContext);
                        GetRecordsResult getRecordsResult = (GetRecordsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getRecordsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, getRecordsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getRecordsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getRecordsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kinesis.model.GetShardIteratorRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public GetShardIteratorResult getShardIterator(GetShardIteratorRequest getShardIteratorRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getShardIteratorRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetShardIteratorRequest> marshall = new GetShardIteratorRequestMarshaller().marshall((GetShardIteratorRequest) getShardIteratorRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetShardIteratorResultJsonUnmarshaller()), createExecutionContext);
                        GetShardIteratorResult getShardIteratorResult = (GetShardIteratorResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getShardIteratorResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, getShardIteratorRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getShardIteratorRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getShardIteratorRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.IncreaseStreamRetentionPeriodRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void increaseStreamRetentionPeriod(IncreaseStreamRetentionPeriodRequest increaseStreamRetentionPeriodRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(increaseStreamRetentionPeriodRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<IncreaseStreamRetentionPeriodRequest> marshall = new IncreaseStreamRetentionPeriodRequestMarshaller().marshall((IncreaseStreamRetentionPeriodRequest) increaseStreamRetentionPeriodRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, null, true);
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, increaseStreamRetentionPeriodRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            increaseStreamRetentionPeriodRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, increaseStreamRetentionPeriodRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.ListShardsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public ListShardsResult listShards(ListShardsRequest listShardsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listShardsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListShardsRequest> marshall = new ListShardsRequestMarshaller().marshall((ListShardsRequest) listShardsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListShardsResultJsonUnmarshaller()), createExecutionContext);
                        ListShardsResult listShardsResult = (ListShardsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listShardsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, listShardsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listShardsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listShardsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.kinesis.model.ListStreamsRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public ListStreamsResult listStreams(ListStreamsRequest listStreamsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listStreamsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListStreamsRequest> marshall = new ListStreamsRequestMarshaller().marshall((ListStreamsRequest) listStreamsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListStreamsResultJsonUnmarshaller()), createExecutionContext);
                        ListStreamsResult listStreamsResult = (ListStreamsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listStreamsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, listStreamsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listStreamsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listStreamsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.ListTagsForStreamRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public ListTagsForStreamResult listTagsForStream(ListTagsForStreamRequest listTagsForStreamRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listTagsForStreamRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListTagsForStreamRequest> marshall = new ListTagsForStreamRequestMarshaller().marshall((ListTagsForStreamRequest) listTagsForStreamRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListTagsForStreamResultJsonUnmarshaller()), createExecutionContext);
                        ListTagsForStreamResult listTagsForStreamResult = (ListTagsForStreamResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listTagsForStreamResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, listTagsForStreamRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listTagsForStreamRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listTagsForStreamRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.MergeShardsRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void mergeShards(MergeShardsRequest mergeShardsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(mergeShardsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<MergeShardsRequest> marshall = new MergeShardsRequestMarshaller().marshall((MergeShardsRequest) mergeShardsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, null, true);
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, mergeShardsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            mergeShardsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, mergeShardsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.PutRecordRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public PutRecordResult putRecord(PutRecordRequest putRecordRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(putRecordRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<PutRecordRequest> marshall = new PutRecordRequestMarshaller().marshall((PutRecordRequest) putRecordRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new PutRecordResultJsonUnmarshaller()), createExecutionContext);
                        PutRecordResult putRecordResult = (PutRecordResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return putRecordResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, putRecordRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            putRecordRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, putRecordRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.PutRecordsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public PutRecordsResult putRecords(PutRecordsRequest putRecordsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(putRecordsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<PutRecordsRequest> marshall = new PutRecordsRequestMarshaller().marshall((PutRecordsRequest) putRecordsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new PutRecordsResultJsonUnmarshaller()), createExecutionContext);
                        PutRecordsResult putRecordsResult = (PutRecordsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return putRecordsResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, putRecordsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            putRecordsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, putRecordsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.RemoveTagsFromStreamRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void removeTagsFromStream(RemoveTagsFromStreamRequest removeTagsFromStreamRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(removeTagsFromStreamRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<RemoveTagsFromStreamRequest> marshall = new RemoveTagsFromStreamRequestMarshaller().marshall((RemoveTagsFromStreamRequest) removeTagsFromStreamRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, null, true);
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, removeTagsFromStreamRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            removeTagsFromStreamRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, removeTagsFromStreamRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.SplitShardRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void splitShard(SplitShardRequest splitShardRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(splitShardRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<SplitShardRequest> marshall = new SplitShardRequestMarshaller().marshall((SplitShardRequest) splitShardRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, null, true);
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, splitShardRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            splitShardRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, splitShardRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.StartStreamEncryptionRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void startStreamEncryption(StartStreamEncryptionRequest startStreamEncryptionRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(startStreamEncryptionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<StartStreamEncryptionRequest> marshall = new StartStreamEncryptionRequestMarshaller().marshall((StartStreamEncryptionRequest) startStreamEncryptionRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, null, true);
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, startStreamEncryptionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            startStreamEncryptionRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, startStreamEncryptionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.StopStreamEncryptionRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void stopStreamEncryption(StopStreamEncryptionRequest stopStreamEncryptionRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(stopStreamEncryptionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<StopStreamEncryptionRequest> marshall = new StopStreamEncryptionRequestMarshaller().marshall((StopStreamEncryptionRequest) stopStreamEncryptionRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        invoke(marshall, new JsonResponseHandler(null), createExecutionContext);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, null, true);
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, stopStreamEncryptionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            stopStreamEncryptionRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, stopStreamEncryptionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.kinesis.AmazonKinesisClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.kinesis.model.UpdateShardCountRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public UpdateShardCountResult updateShardCount(UpdateShardCountRequest updateShardCountRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateShardCountRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateShardCountRequest> marshall = new UpdateShardCountRequestMarshaller().marshall((UpdateShardCountRequest) updateShardCountRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateShardCountResultJsonUnmarshaller()), createExecutionContext);
                        UpdateShardCountResult updateShardCountResult = (UpdateShardCountResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateShardCountResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, updateShardCountRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateShardCountRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateShardCountRequest, null, true);
            throw th;
        }
    }

    @Deprecated
    public AmazonKinesisClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AmazonKinesisClient(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public AmazonKinesisClient(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(aWSCredentials), clientConfiguration);
    }

    public AmazonKinesisClient(AWSCredentialsProvider aWSCredentialsProvider) {
        this(aWSCredentialsProvider, new ClientConfiguration());
    }

    public AmazonKinesisClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(aWSCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AmazonKinesisClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    public AmazonKinesisClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void createStream(String str, Integer num) throws AmazonServiceException, AmazonClientException {
        CreateStreamRequest createStreamRequest = new CreateStreamRequest();
        createStreamRequest.setStreamName(str);
        createStreamRequest.setShardCount(num);
        createStream(createStreamRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void deleteStream(String str) throws AmazonServiceException, AmazonClientException {
        DeleteStreamRequest deleteStreamRequest = new DeleteStreamRequest();
        deleteStreamRequest.setStreamName(str);
        deleteStream(deleteStreamRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void mergeShards(String str, String str2, String str3) throws AmazonServiceException, AmazonClientException {
        MergeShardsRequest mergeShardsRequest = new MergeShardsRequest();
        mergeShardsRequest.setStreamName(str);
        mergeShardsRequest.setShardToMerge(str2);
        mergeShardsRequest.setAdjacentShardToMerge(str3);
        mergeShards(mergeShardsRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public void splitShard(String str, String str2, String str3) throws AmazonServiceException, AmazonClientException {
        SplitShardRequest splitShardRequest = new SplitShardRequest();
        splitShardRequest.setStreamName(str);
        splitShardRequest.setShardToSplit(str2);
        splitShardRequest.setNewStartingHashKey(str3);
        splitShard(splitShardRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public DescribeStreamResult describeStream(String str) throws AmazonServiceException, AmazonClientException {
        DescribeStreamRequest describeStreamRequest = new DescribeStreamRequest();
        describeStreamRequest.setStreamName(str);
        return describeStream(describeStreamRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public GetShardIteratorResult getShardIterator(String str, String str2, String str3) throws AmazonServiceException, AmazonClientException {
        GetShardIteratorRequest getShardIteratorRequest = new GetShardIteratorRequest();
        getShardIteratorRequest.setStreamName(str);
        getShardIteratorRequest.setShardId(str2);
        getShardIteratorRequest.setShardIteratorType(str3);
        return getShardIterator(getShardIteratorRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public ListStreamsResult listStreams() throws AmazonServiceException, AmazonClientException {
        return listStreams(new ListStreamsRequest());
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public PutRecordResult putRecord(String str, ByteBuffer byteBuffer, String str2) throws AmazonServiceException, AmazonClientException {
        PutRecordRequest putRecordRequest = new PutRecordRequest();
        putRecordRequest.setStreamName(str);
        putRecordRequest.setData(byteBuffer);
        putRecordRequest.setPartitionKey(str2);
        return putRecord(putRecordRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public ListStreamsResult listStreams(String str) throws AmazonServiceException, AmazonClientException {
        ListStreamsRequest listStreamsRequest = new ListStreamsRequest();
        listStreamsRequest.setExclusiveStartStreamName(str);
        return listStreams(listStreamsRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public DescribeStreamResult describeStream(String str, String str2) throws AmazonServiceException, AmazonClientException {
        DescribeStreamRequest describeStreamRequest = new DescribeStreamRequest();
        describeStreamRequest.setStreamName(str);
        describeStreamRequest.setExclusiveStartShardId(str2);
        return describeStream(describeStreamRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public GetShardIteratorResult getShardIterator(String str, String str2, String str3, String str4) throws AmazonServiceException, AmazonClientException {
        GetShardIteratorRequest getShardIteratorRequest = new GetShardIteratorRequest();
        getShardIteratorRequest.setStreamName(str);
        getShardIteratorRequest.setShardId(str2);
        getShardIteratorRequest.setShardIteratorType(str3);
        getShardIteratorRequest.setStartingSequenceNumber(str4);
        return getShardIterator(getShardIteratorRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public ListStreamsResult listStreams(Integer num, String str) throws AmazonServiceException, AmazonClientException {
        ListStreamsRequest listStreamsRequest = new ListStreamsRequest();
        listStreamsRequest.setLimit(num);
        listStreamsRequest.setExclusiveStartStreamName(str);
        return listStreams(listStreamsRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public PutRecordResult putRecord(String str, ByteBuffer byteBuffer, String str2, String str3) throws AmazonServiceException, AmazonClientException {
        PutRecordRequest putRecordRequest = new PutRecordRequest();
        putRecordRequest.setStreamName(str);
        putRecordRequest.setData(byteBuffer);
        putRecordRequest.setPartitionKey(str2);
        putRecordRequest.setSequenceNumberForOrdering(str3);
        return putRecord(putRecordRequest);
    }

    @Override // com.amazonaws.services.kinesis.AmazonKinesis
    public DescribeStreamResult describeStream(String str, Integer num, String str2) throws AmazonServiceException, AmazonClientException {
        DescribeStreamRequest describeStreamRequest = new DescribeStreamRequest();
        describeStreamRequest.setStreamName(str);
        describeStreamRequest.setLimit(num);
        describeStreamRequest.setExclusiveStartShardId(str2);
        return describeStream(describeStreamRequest);
    }
}
