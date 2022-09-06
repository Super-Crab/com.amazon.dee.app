package com.amazonaws.services.cognitoidentity;

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
import com.amazonaws.services.cognitoidentity.model.CreateIdentityPoolRequest;
import com.amazonaws.services.cognitoidentity.model.CreateIdentityPoolResult;
import com.amazonaws.services.cognitoidentity.model.DeleteIdentitiesRequest;
import com.amazonaws.services.cognitoidentity.model.DeleteIdentitiesResult;
import com.amazonaws.services.cognitoidentity.model.DeleteIdentityPoolRequest;
import com.amazonaws.services.cognitoidentity.model.DescribeIdentityPoolRequest;
import com.amazonaws.services.cognitoidentity.model.DescribeIdentityPoolResult;
import com.amazonaws.services.cognitoidentity.model.DescribeIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.DescribeIdentityResult;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityResult;
import com.amazonaws.services.cognitoidentity.model.GetIdRequest;
import com.amazonaws.services.cognitoidentity.model.GetIdResult;
import com.amazonaws.services.cognitoidentity.model.GetIdentityPoolRolesRequest;
import com.amazonaws.services.cognitoidentity.model.GetIdentityPoolRolesResult;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityResult;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenRequest;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenResult;
import com.amazonaws.services.cognitoidentity.model.ListIdentitiesRequest;
import com.amazonaws.services.cognitoidentity.model.ListIdentitiesResult;
import com.amazonaws.services.cognitoidentity.model.ListIdentityPoolsRequest;
import com.amazonaws.services.cognitoidentity.model.ListIdentityPoolsResult;
import com.amazonaws.services.cognitoidentity.model.LookupDeveloperIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.LookupDeveloperIdentityResult;
import com.amazonaws.services.cognitoidentity.model.MergeDeveloperIdentitiesRequest;
import com.amazonaws.services.cognitoidentity.model.MergeDeveloperIdentitiesResult;
import com.amazonaws.services.cognitoidentity.model.SetIdentityPoolRolesRequest;
import com.amazonaws.services.cognitoidentity.model.UnlinkDeveloperIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.UnlinkIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.UpdateIdentityPoolRequest;
import com.amazonaws.services.cognitoidentity.model.UpdateIdentityPoolResult;
import com.amazonaws.services.cognitoidentity.model.transform.ConcurrentModificationExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.CreateIdentityPoolRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.CreateIdentityPoolResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.DeleteIdentitiesRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.DeleteIdentitiesResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.DeleteIdentityPoolRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.DescribeIdentityPoolRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.DescribeIdentityPoolResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.DescribeIdentityRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.DescribeIdentityResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.DeveloperUserAlreadyRegisteredExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.ExternalServiceExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetCredentialsForIdentityRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetCredentialsForIdentityResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetIdRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetIdResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetIdentityPoolRolesRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetIdentityPoolRolesResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetOpenIdTokenForDeveloperIdentityRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetOpenIdTokenForDeveloperIdentityResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetOpenIdTokenRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.GetOpenIdTokenResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.InternalErrorExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.InvalidIdentityPoolConfigurationExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.InvalidParameterExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.LimitExceededExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.ListIdentitiesRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.ListIdentitiesResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.ListIdentityPoolsRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.ListIdentityPoolsResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.LookupDeveloperIdentityRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.LookupDeveloperIdentityResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.MergeDeveloperIdentitiesRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.MergeDeveloperIdentitiesResultJsonUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.NotAuthorizedExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.ResourceConflictExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.ResourceNotFoundExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.SetIdentityPoolRolesRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.TooManyRequestsExceptionUnmarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.UnlinkDeveloperIdentityRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.UnlinkIdentityRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.UpdateIdentityPoolRequestMarshaller;
import com.amazonaws.services.cognitoidentity.model.transform.UpdateIdentityPoolResultJsonUnmarshaller;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.util.AWSRequestMetrics;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public class AmazonCognitoIdentityClient extends AmazonWebServiceClient implements AmazonCognitoIdentity {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected List<JsonErrorUnmarshaller> jsonErrorUnmarshallers;

    @Deprecated
    public AmazonCognitoIdentityClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration clientConfiguration) {
        return clientConfiguration;
    }

    private void init() {
        this.jsonErrorUnmarshallers = new ArrayList();
        this.jsonErrorUnmarshallers.add(new ConcurrentModificationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new DeveloperUserAlreadyRegisteredExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ExternalServiceExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InternalErrorExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidIdentityPoolConfigurationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidParameterExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new LimitExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new NotAuthorizedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new TooManyRequestsExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new JsonErrorUnmarshaller());
        setEndpoint("cognito-identity.us-east-1.amazonaws.com");
        this.endpointPrefix = "cognito-identity";
        HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandlerChain("/com/amazonaws/services/cognitoidentity/request.handlers"));
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandler2Chain("/com/amazonaws/services/cognitoidentity/request.handler2s"));
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
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.CreateIdentityPoolRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public CreateIdentityPoolResult createIdentityPool(CreateIdentityPoolRequest createIdentityPoolRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createIdentityPoolRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateIdentityPoolRequest> marshall = new CreateIdentityPoolRequestMarshaller().marshall((CreateIdentityPoolRequest) createIdentityPoolRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateIdentityPoolResultJsonUnmarshaller()), createExecutionContext);
                        CreateIdentityPoolResult createIdentityPoolResult = (CreateIdentityPoolResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createIdentityPoolResult;
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
                endClientExecution(awsRequestMetrics, createIdentityPoolRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createIdentityPoolRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createIdentityPoolRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.cognitoidentity.model.DeleteIdentitiesRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public DeleteIdentitiesResult deleteIdentities(DeleteIdentitiesRequest deleteIdentitiesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteIdentitiesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteIdentitiesRequest> marshall = new DeleteIdentitiesRequestMarshaller().marshall((DeleteIdentitiesRequest) deleteIdentitiesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteIdentitiesResultJsonUnmarshaller()), createExecutionContext);
                        DeleteIdentitiesResult deleteIdentitiesResult = (DeleteIdentitiesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteIdentitiesResult;
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
                endClientExecution(awsRequestMetrics, deleteIdentitiesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteIdentitiesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteIdentitiesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.DeleteIdentityPoolRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public void deleteIdentityPool(DeleteIdentityPoolRequest deleteIdentityPoolRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteIdentityPoolRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteIdentityPoolRequest> marshall = new DeleteIdentityPoolRequestMarshaller().marshall((DeleteIdentityPoolRequest) deleteIdentityPoolRequest);
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
                endClientExecution(awsRequestMetrics, deleteIdentityPoolRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteIdentityPoolRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteIdentityPoolRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.cognitoidentity.model.DescribeIdentityRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public DescribeIdentityResult describeIdentity(DescribeIdentityRequest describeIdentityRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeIdentityRequest> marshall = new DescribeIdentityRequestMarshaller().marshall((DescribeIdentityRequest) describeIdentityRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeIdentityResultJsonUnmarshaller()), createExecutionContext);
                        DescribeIdentityResult describeIdentityResult = (DescribeIdentityResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeIdentityResult;
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
                endClientExecution(awsRequestMetrics, describeIdentityRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeIdentityRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeIdentityRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.DescribeIdentityPoolRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public DescribeIdentityPoolResult describeIdentityPool(DescribeIdentityPoolRequest describeIdentityPoolRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeIdentityPoolRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeIdentityPoolRequest> marshall = new DescribeIdentityPoolRequestMarshaller().marshall((DescribeIdentityPoolRequest) describeIdentityPoolRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeIdentityPoolResultJsonUnmarshaller()), createExecutionContext);
                        DescribeIdentityPoolResult describeIdentityPoolResult = (DescribeIdentityPoolResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeIdentityPoolResult;
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
                endClientExecution(awsRequestMetrics, describeIdentityPoolRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeIdentityPoolRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeIdentityPoolRequest, null, true);
            throw th;
        }
    }

    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    @Deprecated
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest) {
        return this.client.getResponseMetadataForRequest(amazonWebServiceRequest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetCredentialsForIdentityResult getCredentialsForIdentity(GetCredentialsForIdentityRequest getCredentialsForIdentityRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getCredentialsForIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetCredentialsForIdentityRequest> marshall = new GetCredentialsForIdentityRequestMarshaller().marshall((GetCredentialsForIdentityRequest) getCredentialsForIdentityRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetCredentialsForIdentityResultJsonUnmarshaller()), createExecutionContext);
                        GetCredentialsForIdentityResult getCredentialsForIdentityResult = (GetCredentialsForIdentityResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getCredentialsForIdentityResult;
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
                endClientExecution(awsRequestMetrics, getCredentialsForIdentityRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getCredentialsForIdentityRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getCredentialsForIdentityRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.GetIdRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetIdResult getId(GetIdRequest getIdRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getIdRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetIdRequest> marshall = new GetIdRequestMarshaller().marshall((GetIdRequest) getIdRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetIdResultJsonUnmarshaller()), createExecutionContext);
                        GetIdResult getIdResult = (GetIdResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getIdResult;
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
                endClientExecution(awsRequestMetrics, getIdRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getIdRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getIdRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.cognitoidentity.model.GetIdentityPoolRolesRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetIdentityPoolRolesResult getIdentityPoolRoles(GetIdentityPoolRolesRequest getIdentityPoolRolesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getIdentityPoolRolesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetIdentityPoolRolesRequest> marshall = new GetIdentityPoolRolesRequestMarshaller().marshall((GetIdentityPoolRolesRequest) getIdentityPoolRolesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetIdentityPoolRolesResultJsonUnmarshaller()), createExecutionContext);
                        GetIdentityPoolRolesResult getIdentityPoolRolesResult = (GetIdentityPoolRolesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getIdentityPoolRolesResult;
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
                endClientExecution(awsRequestMetrics, getIdentityPoolRolesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getIdentityPoolRolesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getIdentityPoolRolesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetOpenIdTokenResult getOpenIdToken(GetOpenIdTokenRequest getOpenIdTokenRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getOpenIdTokenRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetOpenIdTokenRequest> marshall = new GetOpenIdTokenRequestMarshaller().marshall((GetOpenIdTokenRequest) getOpenIdTokenRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetOpenIdTokenResultJsonUnmarshaller()), createExecutionContext);
                        GetOpenIdTokenResult getOpenIdTokenResult = (GetOpenIdTokenResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getOpenIdTokenResult;
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
                endClientExecution(awsRequestMetrics, getOpenIdTokenRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getOpenIdTokenRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getOpenIdTokenRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public GetOpenIdTokenForDeveloperIdentityResult getOpenIdTokenForDeveloperIdentity(GetOpenIdTokenForDeveloperIdentityRequest getOpenIdTokenForDeveloperIdentityRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getOpenIdTokenForDeveloperIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetOpenIdTokenForDeveloperIdentityRequest> marshall = new GetOpenIdTokenForDeveloperIdentityRequestMarshaller().marshall((GetOpenIdTokenForDeveloperIdentityRequest) getOpenIdTokenForDeveloperIdentityRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetOpenIdTokenForDeveloperIdentityResultJsonUnmarshaller()), createExecutionContext);
                        GetOpenIdTokenForDeveloperIdentityResult getOpenIdTokenForDeveloperIdentityResult = (GetOpenIdTokenForDeveloperIdentityResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getOpenIdTokenForDeveloperIdentityResult;
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
                endClientExecution(awsRequestMetrics, getOpenIdTokenForDeveloperIdentityRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getOpenIdTokenForDeveloperIdentityRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getOpenIdTokenForDeveloperIdentityRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.ListIdentitiesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public ListIdentitiesResult listIdentities(ListIdentitiesRequest listIdentitiesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listIdentitiesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListIdentitiesRequest> marshall = new ListIdentitiesRequestMarshaller().marshall((ListIdentitiesRequest) listIdentitiesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListIdentitiesResultJsonUnmarshaller()), createExecutionContext);
                        ListIdentitiesResult listIdentitiesResult = (ListIdentitiesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listIdentitiesResult;
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
                endClientExecution(awsRequestMetrics, listIdentitiesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listIdentitiesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listIdentitiesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.ListIdentityPoolsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public ListIdentityPoolsResult listIdentityPools(ListIdentityPoolsRequest listIdentityPoolsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listIdentityPoolsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListIdentityPoolsRequest> marshall = new ListIdentityPoolsRequestMarshaller().marshall((ListIdentityPoolsRequest) listIdentityPoolsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListIdentityPoolsResultJsonUnmarshaller()), createExecutionContext);
                        ListIdentityPoolsResult listIdentityPoolsResult = (ListIdentityPoolsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listIdentityPoolsResult;
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
                endClientExecution(awsRequestMetrics, listIdentityPoolsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listIdentityPoolsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listIdentityPoolsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.cognitoidentity.model.LookupDeveloperIdentityRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public LookupDeveloperIdentityResult lookupDeveloperIdentity(LookupDeveloperIdentityRequest lookupDeveloperIdentityRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(lookupDeveloperIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<LookupDeveloperIdentityRequest> marshall = new LookupDeveloperIdentityRequestMarshaller().marshall((LookupDeveloperIdentityRequest) lookupDeveloperIdentityRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new LookupDeveloperIdentityResultJsonUnmarshaller()), createExecutionContext);
                        LookupDeveloperIdentityResult lookupDeveloperIdentityResult = (LookupDeveloperIdentityResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return lookupDeveloperIdentityResult;
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
                endClientExecution(awsRequestMetrics, lookupDeveloperIdentityRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            lookupDeveloperIdentityRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, lookupDeveloperIdentityRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.MergeDeveloperIdentitiesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public MergeDeveloperIdentitiesResult mergeDeveloperIdentities(MergeDeveloperIdentitiesRequest mergeDeveloperIdentitiesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(mergeDeveloperIdentitiesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<MergeDeveloperIdentitiesRequest> marshall = new MergeDeveloperIdentitiesRequestMarshaller().marshall((MergeDeveloperIdentitiesRequest) mergeDeveloperIdentitiesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new MergeDeveloperIdentitiesResultJsonUnmarshaller()), createExecutionContext);
                        MergeDeveloperIdentitiesResult mergeDeveloperIdentitiesResult = (MergeDeveloperIdentitiesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return mergeDeveloperIdentitiesResult;
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
                endClientExecution(awsRequestMetrics, mergeDeveloperIdentitiesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            mergeDeveloperIdentitiesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, mergeDeveloperIdentitiesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.cognitoidentity.model.SetIdentityPoolRolesRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public void setIdentityPoolRoles(SetIdentityPoolRolesRequest setIdentityPoolRolesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(setIdentityPoolRolesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<SetIdentityPoolRolesRequest> marshall = new SetIdentityPoolRolesRequestMarshaller().marshall((SetIdentityPoolRolesRequest) setIdentityPoolRolesRequest);
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
                endClientExecution(awsRequestMetrics, setIdentityPoolRolesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            setIdentityPoolRolesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setIdentityPoolRolesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.UnlinkDeveloperIdentityRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public void unlinkDeveloperIdentity(UnlinkDeveloperIdentityRequest unlinkDeveloperIdentityRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(unlinkDeveloperIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UnlinkDeveloperIdentityRequest> marshall = new UnlinkDeveloperIdentityRequestMarshaller().marshall((UnlinkDeveloperIdentityRequest) unlinkDeveloperIdentityRequest);
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
                endClientExecution(awsRequestMetrics, unlinkDeveloperIdentityRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            unlinkDeveloperIdentityRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, unlinkDeveloperIdentityRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.cognitoidentity.model.UnlinkIdentityRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public void unlinkIdentity(UnlinkIdentityRequest unlinkIdentityRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(unlinkIdentityRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UnlinkIdentityRequest> marshall = new UnlinkIdentityRequestMarshaller().marshall((UnlinkIdentityRequest) unlinkIdentityRequest);
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
                endClientExecution(awsRequestMetrics, unlinkIdentityRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            unlinkIdentityRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, unlinkIdentityRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.cognitoidentity.model.UpdateIdentityPoolRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
    public UpdateIdentityPoolResult updateIdentityPool(UpdateIdentityPoolRequest updateIdentityPoolRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateIdentityPoolRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateIdentityPoolRequest> marshall = new UpdateIdentityPoolRequestMarshaller().marshall((UpdateIdentityPoolRequest) updateIdentityPoolRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateIdentityPoolResultJsonUnmarshaller()), createExecutionContext);
                        UpdateIdentityPoolResult updateIdentityPoolResult = (UpdateIdentityPoolResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateIdentityPoolResult;
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
                endClientExecution(awsRequestMetrics, updateIdentityPoolRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateIdentityPoolRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateIdentityPoolRequest, null, true);
            throw th;
        }
    }

    @Deprecated
    public AmazonCognitoIdentityClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AmazonCognitoIdentityClient(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public AmazonCognitoIdentityClient(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(aWSCredentials), clientConfiguration);
    }

    public AmazonCognitoIdentityClient(AWSCredentialsProvider aWSCredentialsProvider) {
        this(aWSCredentialsProvider, new ClientConfiguration());
    }

    public AmazonCognitoIdentityClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(aWSCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AmazonCognitoIdentityClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    public AmazonCognitoIdentityClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }
}
