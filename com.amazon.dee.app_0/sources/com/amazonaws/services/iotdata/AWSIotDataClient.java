package com.amazonaws.services.iotdata;

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
import com.amazonaws.services.iotdata.model.DeleteThingShadowRequest;
import com.amazonaws.services.iotdata.model.DeleteThingShadowResult;
import com.amazonaws.services.iotdata.model.GetThingShadowRequest;
import com.amazonaws.services.iotdata.model.GetThingShadowResult;
import com.amazonaws.services.iotdata.model.PublishRequest;
import com.amazonaws.services.iotdata.model.UpdateThingShadowRequest;
import com.amazonaws.services.iotdata.model.UpdateThingShadowResult;
import com.amazonaws.services.iotdata.model.transform.ConflictExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.DeleteThingShadowRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.DeleteThingShadowResultJsonUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.GetThingShadowRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.GetThingShadowResultJsonUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.InternalFailureExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.InvalidRequestExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.MethodNotAllowedExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.PublishRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.RequestEntityTooLargeExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.ResourceNotFoundExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.ServiceUnavailableExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.ThrottlingExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.UnauthorizedExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.UnsupportedDocumentEncodingExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.UpdateThingShadowRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.UpdateThingShadowResultJsonUnmarshaller;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.util.AWSRequestMetrics;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public class AWSIotDataClient extends AmazonWebServiceClient implements AWSIotData {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected List<JsonErrorUnmarshaller> jsonErrorUnmarshallers;

    @Deprecated
    public AWSIotDataClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration clientConfiguration) {
        return clientConfiguration;
    }

    private void init() {
        this.jsonErrorUnmarshallers = new ArrayList();
        this.jsonErrorUnmarshallers.add(new ConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InternalFailureExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidRequestExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new MethodNotAllowedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new RequestEntityTooLargeExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ServiceUnavailableExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ThrottlingExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new UnauthorizedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new UnsupportedDocumentEncodingExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new JsonErrorUnmarshaller());
        setEndpoint("data.iot.us-east-1.amazonaws.com");
        this.endpointPrefix = "data.iot";
        HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandlerChain("/com/amazonaws/services/iotdata/request.handlers"));
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandler2Chain("/com/amazonaws/services/iotdata/request.handler2s"));
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
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iotdata.model.DeleteThingShadowRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public DeleteThingShadowResult deleteThingShadow(DeleteThingShadowRequest deleteThingShadowRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteThingShadowRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteThingShadowRequest> marshall = new DeleteThingShadowRequestMarshaller().marshall((DeleteThingShadowRequest) deleteThingShadowRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteThingShadowResultJsonUnmarshaller()), createExecutionContext);
                        DeleteThingShadowResult deleteThingShadowResult = (DeleteThingShadowResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteThingShadowResult;
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
                endClientExecution(awsRequestMetrics, deleteThingShadowRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteThingShadowRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteThingShadowRequest, null, true);
            throw th;
        }
    }

    @Override // com.amazonaws.services.iotdata.AWSIotData
    @Deprecated
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest) {
        return this.client.getResponseMetadataForRequest(amazonWebServiceRequest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iotdata.model.GetThingShadowRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public GetThingShadowResult getThingShadow(GetThingShadowRequest getThingShadowRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getThingShadowRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetThingShadowRequest> marshall = new GetThingShadowRequestMarshaller().marshall((GetThingShadowRequest) getThingShadowRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetThingShadowResultJsonUnmarshaller()), createExecutionContext);
                        GetThingShadowResult getThingShadowResult = (GetThingShadowResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getThingShadowResult;
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
                endClientExecution(awsRequestMetrics, getThingShadowRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getThingShadowRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getThingShadowRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iotdata.model.PublishRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public void publish(PublishRequest publishRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(publishRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<PublishRequest> marshall = new PublishRequestMarshaller().marshall((PublishRequest) publishRequest);
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
                endClientExecution(awsRequestMetrics, publishRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            publishRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, publishRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iotdata.model.UpdateThingShadowRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public UpdateThingShadowResult updateThingShadow(UpdateThingShadowRequest updateThingShadowRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateThingShadowRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateThingShadowRequest> marshall = new UpdateThingShadowRequestMarshaller().marshall((UpdateThingShadowRequest) updateThingShadowRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateThingShadowResultJsonUnmarshaller()), createExecutionContext);
                        UpdateThingShadowResult updateThingShadowResult = (UpdateThingShadowResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateThingShadowResult;
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
                endClientExecution(awsRequestMetrics, updateThingShadowRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateThingShadowRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateThingShadowRequest, null, true);
            throw th;
        }
    }

    @Deprecated
    public AWSIotDataClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AWSIotDataClient(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public AWSIotDataClient(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(aWSCredentials), clientConfiguration);
    }

    public AWSIotDataClient(AWSCredentialsProvider aWSCredentialsProvider) {
        this(aWSCredentialsProvider, new ClientConfiguration());
    }

    public AWSIotDataClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(aWSCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AWSIotDataClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    public AWSIotDataClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }
}
