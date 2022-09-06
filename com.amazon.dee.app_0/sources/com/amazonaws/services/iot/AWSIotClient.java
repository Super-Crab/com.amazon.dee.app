package com.amazonaws.services.iot;

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
import com.amazonaws.services.iot.model.AcceptCertificateTransferRequest;
import com.amazonaws.services.iot.model.AddThingToBillingGroupRequest;
import com.amazonaws.services.iot.model.AddThingToBillingGroupResult;
import com.amazonaws.services.iot.model.AddThingToThingGroupRequest;
import com.amazonaws.services.iot.model.AddThingToThingGroupResult;
import com.amazonaws.services.iot.model.AssociateTargetsWithJobRequest;
import com.amazonaws.services.iot.model.AssociateTargetsWithJobResult;
import com.amazonaws.services.iot.model.AttachPolicyRequest;
import com.amazonaws.services.iot.model.AttachPrincipalPolicyRequest;
import com.amazonaws.services.iot.model.AttachSecurityProfileRequest;
import com.amazonaws.services.iot.model.AttachSecurityProfileResult;
import com.amazonaws.services.iot.model.AttachThingPrincipalRequest;
import com.amazonaws.services.iot.model.AttachThingPrincipalResult;
import com.amazonaws.services.iot.model.CancelAuditTaskRequest;
import com.amazonaws.services.iot.model.CancelAuditTaskResult;
import com.amazonaws.services.iot.model.CancelCertificateTransferRequest;
import com.amazonaws.services.iot.model.CancelJobExecutionRequest;
import com.amazonaws.services.iot.model.CancelJobRequest;
import com.amazonaws.services.iot.model.CancelJobResult;
import com.amazonaws.services.iot.model.ClearDefaultAuthorizerRequest;
import com.amazonaws.services.iot.model.ClearDefaultAuthorizerResult;
import com.amazonaws.services.iot.model.CreateAuthorizerRequest;
import com.amazonaws.services.iot.model.CreateAuthorizerResult;
import com.amazonaws.services.iot.model.CreateBillingGroupRequest;
import com.amazonaws.services.iot.model.CreateBillingGroupResult;
import com.amazonaws.services.iot.model.CreateCertificateFromCsrRequest;
import com.amazonaws.services.iot.model.CreateCertificateFromCsrResult;
import com.amazonaws.services.iot.model.CreateDynamicThingGroupRequest;
import com.amazonaws.services.iot.model.CreateDynamicThingGroupResult;
import com.amazonaws.services.iot.model.CreateJobRequest;
import com.amazonaws.services.iot.model.CreateJobResult;
import com.amazonaws.services.iot.model.CreateKeysAndCertificateRequest;
import com.amazonaws.services.iot.model.CreateKeysAndCertificateResult;
import com.amazonaws.services.iot.model.CreateOTAUpdateRequest;
import com.amazonaws.services.iot.model.CreateOTAUpdateResult;
import com.amazonaws.services.iot.model.CreatePolicyRequest;
import com.amazonaws.services.iot.model.CreatePolicyResult;
import com.amazonaws.services.iot.model.CreatePolicyVersionRequest;
import com.amazonaws.services.iot.model.CreatePolicyVersionResult;
import com.amazonaws.services.iot.model.CreateRoleAliasRequest;
import com.amazonaws.services.iot.model.CreateRoleAliasResult;
import com.amazonaws.services.iot.model.CreateScheduledAuditRequest;
import com.amazonaws.services.iot.model.CreateScheduledAuditResult;
import com.amazonaws.services.iot.model.CreateSecurityProfileRequest;
import com.amazonaws.services.iot.model.CreateSecurityProfileResult;
import com.amazonaws.services.iot.model.CreateStreamRequest;
import com.amazonaws.services.iot.model.CreateStreamResult;
import com.amazonaws.services.iot.model.CreateThingGroupRequest;
import com.amazonaws.services.iot.model.CreateThingGroupResult;
import com.amazonaws.services.iot.model.CreateThingRequest;
import com.amazonaws.services.iot.model.CreateThingResult;
import com.amazonaws.services.iot.model.CreateThingTypeRequest;
import com.amazonaws.services.iot.model.CreateThingTypeResult;
import com.amazonaws.services.iot.model.CreateTopicRuleRequest;
import com.amazonaws.services.iot.model.DeleteAccountAuditConfigurationRequest;
import com.amazonaws.services.iot.model.DeleteAccountAuditConfigurationResult;
import com.amazonaws.services.iot.model.DeleteAuthorizerRequest;
import com.amazonaws.services.iot.model.DeleteAuthorizerResult;
import com.amazonaws.services.iot.model.DeleteBillingGroupRequest;
import com.amazonaws.services.iot.model.DeleteBillingGroupResult;
import com.amazonaws.services.iot.model.DeleteCACertificateRequest;
import com.amazonaws.services.iot.model.DeleteCACertificateResult;
import com.amazonaws.services.iot.model.DeleteCertificateRequest;
import com.amazonaws.services.iot.model.DeleteDynamicThingGroupRequest;
import com.amazonaws.services.iot.model.DeleteDynamicThingGroupResult;
import com.amazonaws.services.iot.model.DeleteJobExecutionRequest;
import com.amazonaws.services.iot.model.DeleteJobRequest;
import com.amazonaws.services.iot.model.DeleteOTAUpdateRequest;
import com.amazonaws.services.iot.model.DeleteOTAUpdateResult;
import com.amazonaws.services.iot.model.DeletePolicyRequest;
import com.amazonaws.services.iot.model.DeletePolicyVersionRequest;
import com.amazonaws.services.iot.model.DeleteRegistrationCodeRequest;
import com.amazonaws.services.iot.model.DeleteRegistrationCodeResult;
import com.amazonaws.services.iot.model.DeleteRoleAliasRequest;
import com.amazonaws.services.iot.model.DeleteRoleAliasResult;
import com.amazonaws.services.iot.model.DeleteScheduledAuditRequest;
import com.amazonaws.services.iot.model.DeleteScheduledAuditResult;
import com.amazonaws.services.iot.model.DeleteSecurityProfileRequest;
import com.amazonaws.services.iot.model.DeleteSecurityProfileResult;
import com.amazonaws.services.iot.model.DeleteStreamRequest;
import com.amazonaws.services.iot.model.DeleteStreamResult;
import com.amazonaws.services.iot.model.DeleteThingGroupRequest;
import com.amazonaws.services.iot.model.DeleteThingGroupResult;
import com.amazonaws.services.iot.model.DeleteThingRequest;
import com.amazonaws.services.iot.model.DeleteThingResult;
import com.amazonaws.services.iot.model.DeleteThingTypeRequest;
import com.amazonaws.services.iot.model.DeleteThingTypeResult;
import com.amazonaws.services.iot.model.DeleteTopicRuleRequest;
import com.amazonaws.services.iot.model.DeleteV2LoggingLevelRequest;
import com.amazonaws.services.iot.model.DeprecateThingTypeRequest;
import com.amazonaws.services.iot.model.DeprecateThingTypeResult;
import com.amazonaws.services.iot.model.DescribeAccountAuditConfigurationRequest;
import com.amazonaws.services.iot.model.DescribeAccountAuditConfigurationResult;
import com.amazonaws.services.iot.model.DescribeAuditTaskRequest;
import com.amazonaws.services.iot.model.DescribeAuditTaskResult;
import com.amazonaws.services.iot.model.DescribeAuthorizerRequest;
import com.amazonaws.services.iot.model.DescribeAuthorizerResult;
import com.amazonaws.services.iot.model.DescribeBillingGroupRequest;
import com.amazonaws.services.iot.model.DescribeBillingGroupResult;
import com.amazonaws.services.iot.model.DescribeCACertificateRequest;
import com.amazonaws.services.iot.model.DescribeCACertificateResult;
import com.amazonaws.services.iot.model.DescribeCertificateRequest;
import com.amazonaws.services.iot.model.DescribeCertificateResult;
import com.amazonaws.services.iot.model.DescribeDefaultAuthorizerRequest;
import com.amazonaws.services.iot.model.DescribeDefaultAuthorizerResult;
import com.amazonaws.services.iot.model.DescribeEndpointRequest;
import com.amazonaws.services.iot.model.DescribeEndpointResult;
import com.amazonaws.services.iot.model.DescribeEventConfigurationsRequest;
import com.amazonaws.services.iot.model.DescribeEventConfigurationsResult;
import com.amazonaws.services.iot.model.DescribeIndexRequest;
import com.amazonaws.services.iot.model.DescribeIndexResult;
import com.amazonaws.services.iot.model.DescribeJobExecutionRequest;
import com.amazonaws.services.iot.model.DescribeJobExecutionResult;
import com.amazonaws.services.iot.model.DescribeJobRequest;
import com.amazonaws.services.iot.model.DescribeJobResult;
import com.amazonaws.services.iot.model.DescribeRoleAliasRequest;
import com.amazonaws.services.iot.model.DescribeRoleAliasResult;
import com.amazonaws.services.iot.model.DescribeScheduledAuditRequest;
import com.amazonaws.services.iot.model.DescribeScheduledAuditResult;
import com.amazonaws.services.iot.model.DescribeSecurityProfileRequest;
import com.amazonaws.services.iot.model.DescribeSecurityProfileResult;
import com.amazonaws.services.iot.model.DescribeStreamRequest;
import com.amazonaws.services.iot.model.DescribeStreamResult;
import com.amazonaws.services.iot.model.DescribeThingGroupRequest;
import com.amazonaws.services.iot.model.DescribeThingGroupResult;
import com.amazonaws.services.iot.model.DescribeThingRegistrationTaskRequest;
import com.amazonaws.services.iot.model.DescribeThingRegistrationTaskResult;
import com.amazonaws.services.iot.model.DescribeThingRequest;
import com.amazonaws.services.iot.model.DescribeThingResult;
import com.amazonaws.services.iot.model.DescribeThingTypeRequest;
import com.amazonaws.services.iot.model.DescribeThingTypeResult;
import com.amazonaws.services.iot.model.DetachPolicyRequest;
import com.amazonaws.services.iot.model.DetachPrincipalPolicyRequest;
import com.amazonaws.services.iot.model.DetachSecurityProfileRequest;
import com.amazonaws.services.iot.model.DetachSecurityProfileResult;
import com.amazonaws.services.iot.model.DetachThingPrincipalRequest;
import com.amazonaws.services.iot.model.DetachThingPrincipalResult;
import com.amazonaws.services.iot.model.DisableTopicRuleRequest;
import com.amazonaws.services.iot.model.EnableTopicRuleRequest;
import com.amazonaws.services.iot.model.GetEffectivePoliciesRequest;
import com.amazonaws.services.iot.model.GetEffectivePoliciesResult;
import com.amazonaws.services.iot.model.GetIndexingConfigurationRequest;
import com.amazonaws.services.iot.model.GetIndexingConfigurationResult;
import com.amazonaws.services.iot.model.GetJobDocumentRequest;
import com.amazonaws.services.iot.model.GetJobDocumentResult;
import com.amazonaws.services.iot.model.GetLoggingOptionsRequest;
import com.amazonaws.services.iot.model.GetLoggingOptionsResult;
import com.amazonaws.services.iot.model.GetOTAUpdateRequest;
import com.amazonaws.services.iot.model.GetOTAUpdateResult;
import com.amazonaws.services.iot.model.GetPolicyRequest;
import com.amazonaws.services.iot.model.GetPolicyResult;
import com.amazonaws.services.iot.model.GetPolicyVersionRequest;
import com.amazonaws.services.iot.model.GetPolicyVersionResult;
import com.amazonaws.services.iot.model.GetRegistrationCodeRequest;
import com.amazonaws.services.iot.model.GetRegistrationCodeResult;
import com.amazonaws.services.iot.model.GetTopicRuleRequest;
import com.amazonaws.services.iot.model.GetTopicRuleResult;
import com.amazonaws.services.iot.model.GetV2LoggingOptionsRequest;
import com.amazonaws.services.iot.model.GetV2LoggingOptionsResult;
import com.amazonaws.services.iot.model.ListActiveViolationsRequest;
import com.amazonaws.services.iot.model.ListActiveViolationsResult;
import com.amazonaws.services.iot.model.ListAttachedPoliciesRequest;
import com.amazonaws.services.iot.model.ListAttachedPoliciesResult;
import com.amazonaws.services.iot.model.ListAuditFindingsRequest;
import com.amazonaws.services.iot.model.ListAuditFindingsResult;
import com.amazonaws.services.iot.model.ListAuditTasksRequest;
import com.amazonaws.services.iot.model.ListAuditTasksResult;
import com.amazonaws.services.iot.model.ListAuthorizersRequest;
import com.amazonaws.services.iot.model.ListAuthorizersResult;
import com.amazonaws.services.iot.model.ListBillingGroupsRequest;
import com.amazonaws.services.iot.model.ListBillingGroupsResult;
import com.amazonaws.services.iot.model.ListCACertificatesRequest;
import com.amazonaws.services.iot.model.ListCACertificatesResult;
import com.amazonaws.services.iot.model.ListCertificatesByCARequest;
import com.amazonaws.services.iot.model.ListCertificatesByCAResult;
import com.amazonaws.services.iot.model.ListCertificatesRequest;
import com.amazonaws.services.iot.model.ListCertificatesResult;
import com.amazonaws.services.iot.model.ListIndicesRequest;
import com.amazonaws.services.iot.model.ListIndicesResult;
import com.amazonaws.services.iot.model.ListJobExecutionsForJobRequest;
import com.amazonaws.services.iot.model.ListJobExecutionsForJobResult;
import com.amazonaws.services.iot.model.ListJobExecutionsForThingRequest;
import com.amazonaws.services.iot.model.ListJobExecutionsForThingResult;
import com.amazonaws.services.iot.model.ListJobsRequest;
import com.amazonaws.services.iot.model.ListJobsResult;
import com.amazonaws.services.iot.model.ListOTAUpdatesRequest;
import com.amazonaws.services.iot.model.ListOTAUpdatesResult;
import com.amazonaws.services.iot.model.ListOutgoingCertificatesRequest;
import com.amazonaws.services.iot.model.ListOutgoingCertificatesResult;
import com.amazonaws.services.iot.model.ListPoliciesRequest;
import com.amazonaws.services.iot.model.ListPoliciesResult;
import com.amazonaws.services.iot.model.ListPolicyPrincipalsRequest;
import com.amazonaws.services.iot.model.ListPolicyPrincipalsResult;
import com.amazonaws.services.iot.model.ListPolicyVersionsRequest;
import com.amazonaws.services.iot.model.ListPolicyVersionsResult;
import com.amazonaws.services.iot.model.ListPrincipalPoliciesRequest;
import com.amazonaws.services.iot.model.ListPrincipalPoliciesResult;
import com.amazonaws.services.iot.model.ListPrincipalThingsRequest;
import com.amazonaws.services.iot.model.ListPrincipalThingsResult;
import com.amazonaws.services.iot.model.ListRoleAliasesRequest;
import com.amazonaws.services.iot.model.ListRoleAliasesResult;
import com.amazonaws.services.iot.model.ListScheduledAuditsRequest;
import com.amazonaws.services.iot.model.ListScheduledAuditsResult;
import com.amazonaws.services.iot.model.ListSecurityProfilesForTargetRequest;
import com.amazonaws.services.iot.model.ListSecurityProfilesForTargetResult;
import com.amazonaws.services.iot.model.ListSecurityProfilesRequest;
import com.amazonaws.services.iot.model.ListSecurityProfilesResult;
import com.amazonaws.services.iot.model.ListStreamsRequest;
import com.amazonaws.services.iot.model.ListStreamsResult;
import com.amazonaws.services.iot.model.ListTagsForResourceRequest;
import com.amazonaws.services.iot.model.ListTagsForResourceResult;
import com.amazonaws.services.iot.model.ListTargetsForPolicyRequest;
import com.amazonaws.services.iot.model.ListTargetsForPolicyResult;
import com.amazonaws.services.iot.model.ListTargetsForSecurityProfileRequest;
import com.amazonaws.services.iot.model.ListTargetsForSecurityProfileResult;
import com.amazonaws.services.iot.model.ListThingGroupsForThingRequest;
import com.amazonaws.services.iot.model.ListThingGroupsForThingResult;
import com.amazonaws.services.iot.model.ListThingGroupsRequest;
import com.amazonaws.services.iot.model.ListThingGroupsResult;
import com.amazonaws.services.iot.model.ListThingPrincipalsRequest;
import com.amazonaws.services.iot.model.ListThingPrincipalsResult;
import com.amazonaws.services.iot.model.ListThingRegistrationTaskReportsRequest;
import com.amazonaws.services.iot.model.ListThingRegistrationTaskReportsResult;
import com.amazonaws.services.iot.model.ListThingRegistrationTasksRequest;
import com.amazonaws.services.iot.model.ListThingRegistrationTasksResult;
import com.amazonaws.services.iot.model.ListThingTypesRequest;
import com.amazonaws.services.iot.model.ListThingTypesResult;
import com.amazonaws.services.iot.model.ListThingsInBillingGroupRequest;
import com.amazonaws.services.iot.model.ListThingsInBillingGroupResult;
import com.amazonaws.services.iot.model.ListThingsInThingGroupRequest;
import com.amazonaws.services.iot.model.ListThingsInThingGroupResult;
import com.amazonaws.services.iot.model.ListThingsRequest;
import com.amazonaws.services.iot.model.ListThingsResult;
import com.amazonaws.services.iot.model.ListTopicRulesRequest;
import com.amazonaws.services.iot.model.ListTopicRulesResult;
import com.amazonaws.services.iot.model.ListV2LoggingLevelsRequest;
import com.amazonaws.services.iot.model.ListV2LoggingLevelsResult;
import com.amazonaws.services.iot.model.ListViolationEventsRequest;
import com.amazonaws.services.iot.model.ListViolationEventsResult;
import com.amazonaws.services.iot.model.RegisterCACertificateRequest;
import com.amazonaws.services.iot.model.RegisterCACertificateResult;
import com.amazonaws.services.iot.model.RegisterCertificateRequest;
import com.amazonaws.services.iot.model.RegisterCertificateResult;
import com.amazonaws.services.iot.model.RegisterThingRequest;
import com.amazonaws.services.iot.model.RegisterThingResult;
import com.amazonaws.services.iot.model.RejectCertificateTransferRequest;
import com.amazonaws.services.iot.model.RemoveThingFromBillingGroupRequest;
import com.amazonaws.services.iot.model.RemoveThingFromBillingGroupResult;
import com.amazonaws.services.iot.model.RemoveThingFromThingGroupRequest;
import com.amazonaws.services.iot.model.RemoveThingFromThingGroupResult;
import com.amazonaws.services.iot.model.ReplaceTopicRuleRequest;
import com.amazonaws.services.iot.model.SearchIndexRequest;
import com.amazonaws.services.iot.model.SearchIndexResult;
import com.amazonaws.services.iot.model.SetDefaultAuthorizerRequest;
import com.amazonaws.services.iot.model.SetDefaultAuthorizerResult;
import com.amazonaws.services.iot.model.SetDefaultPolicyVersionRequest;
import com.amazonaws.services.iot.model.SetLoggingOptionsRequest;
import com.amazonaws.services.iot.model.SetV2LoggingLevelRequest;
import com.amazonaws.services.iot.model.SetV2LoggingOptionsRequest;
import com.amazonaws.services.iot.model.StartOnDemandAuditTaskRequest;
import com.amazonaws.services.iot.model.StartOnDemandAuditTaskResult;
import com.amazonaws.services.iot.model.StartThingRegistrationTaskRequest;
import com.amazonaws.services.iot.model.StartThingRegistrationTaskResult;
import com.amazonaws.services.iot.model.StopThingRegistrationTaskRequest;
import com.amazonaws.services.iot.model.StopThingRegistrationTaskResult;
import com.amazonaws.services.iot.model.TagResourceRequest;
import com.amazonaws.services.iot.model.TagResourceResult;
import com.amazonaws.services.iot.model.TestAuthorizationRequest;
import com.amazonaws.services.iot.model.TestAuthorizationResult;
import com.amazonaws.services.iot.model.TestInvokeAuthorizerRequest;
import com.amazonaws.services.iot.model.TestInvokeAuthorizerResult;
import com.amazonaws.services.iot.model.TransferCertificateRequest;
import com.amazonaws.services.iot.model.TransferCertificateResult;
import com.amazonaws.services.iot.model.UntagResourceRequest;
import com.amazonaws.services.iot.model.UntagResourceResult;
import com.amazonaws.services.iot.model.UpdateAccountAuditConfigurationRequest;
import com.amazonaws.services.iot.model.UpdateAccountAuditConfigurationResult;
import com.amazonaws.services.iot.model.UpdateAuthorizerRequest;
import com.amazonaws.services.iot.model.UpdateAuthorizerResult;
import com.amazonaws.services.iot.model.UpdateBillingGroupRequest;
import com.amazonaws.services.iot.model.UpdateBillingGroupResult;
import com.amazonaws.services.iot.model.UpdateCACertificateRequest;
import com.amazonaws.services.iot.model.UpdateCertificateRequest;
import com.amazonaws.services.iot.model.UpdateDynamicThingGroupRequest;
import com.amazonaws.services.iot.model.UpdateDynamicThingGroupResult;
import com.amazonaws.services.iot.model.UpdateEventConfigurationsRequest;
import com.amazonaws.services.iot.model.UpdateEventConfigurationsResult;
import com.amazonaws.services.iot.model.UpdateIndexingConfigurationRequest;
import com.amazonaws.services.iot.model.UpdateIndexingConfigurationResult;
import com.amazonaws.services.iot.model.UpdateJobRequest;
import com.amazonaws.services.iot.model.UpdateRoleAliasRequest;
import com.amazonaws.services.iot.model.UpdateRoleAliasResult;
import com.amazonaws.services.iot.model.UpdateScheduledAuditRequest;
import com.amazonaws.services.iot.model.UpdateScheduledAuditResult;
import com.amazonaws.services.iot.model.UpdateSecurityProfileRequest;
import com.amazonaws.services.iot.model.UpdateSecurityProfileResult;
import com.amazonaws.services.iot.model.UpdateStreamRequest;
import com.amazonaws.services.iot.model.UpdateStreamResult;
import com.amazonaws.services.iot.model.UpdateThingGroupRequest;
import com.amazonaws.services.iot.model.UpdateThingGroupResult;
import com.amazonaws.services.iot.model.UpdateThingGroupsForThingRequest;
import com.amazonaws.services.iot.model.UpdateThingGroupsForThingResult;
import com.amazonaws.services.iot.model.UpdateThingRequest;
import com.amazonaws.services.iot.model.UpdateThingResult;
import com.amazonaws.services.iot.model.ValidateSecurityProfileBehaviorsRequest;
import com.amazonaws.services.iot.model.ValidateSecurityProfileBehaviorsResult;
import com.amazonaws.services.iot.model.transform.AcceptCertificateTransferRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AddThingToBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AddThingToBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.AddThingToThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AddThingToThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.AssociateTargetsWithJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AssociateTargetsWithJobResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.AttachPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AttachPrincipalPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AttachSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AttachSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.AttachThingPrincipalRequestMarshaller;
import com.amazonaws.services.iot.model.transform.AttachThingPrincipalResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CancelAuditTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CancelAuditTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CancelCertificateTransferRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CancelJobExecutionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CancelJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CancelJobResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CertificateConflictExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.CertificateStateExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.CertificateValidationExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.ClearDefaultAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ClearDefaultAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ConflictingResourceUpdateExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateCertificateFromCsrRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateCertificateFromCsrResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateDynamicThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateDynamicThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateJobResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateKeysAndCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateKeysAndCertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateOTAUpdateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateOTAUpdateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreatePolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreatePolicyResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreatePolicyVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreatePolicyVersionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateRoleAliasRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateRoleAliasResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateScheduledAuditRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateScheduledAuditResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateStreamRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateStreamResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingTypeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.CreateThingTypeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.CreateTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteAccountAuditConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteAccountAuditConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteCACertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteCACertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteConflictExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteDynamicThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteDynamicThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteJobExecutionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteOTAUpdateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteOTAUpdateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeletePolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeletePolicyVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteRegistrationCodeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteRegistrationCodeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteRoleAliasRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteRoleAliasResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteScheduledAuditRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteScheduledAuditResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteStreamRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteStreamResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingTypeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteThingTypeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DeleteTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeleteV2LoggingLevelRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeprecateThingTypeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DeprecateThingTypeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAccountAuditConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAccountAuditConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuditTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuditTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeCACertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeCACertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeCertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeDefaultAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeDefaultAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeEndpointRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeEndpointResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeEventConfigurationsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeEventConfigurationsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeIndexRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeIndexResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeJobExecutionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeJobExecutionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeJobResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeRoleAliasRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeRoleAliasResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeScheduledAuditRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeScheduledAuditResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeStreamRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeStreamResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingRegistrationTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingRegistrationTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingTypeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DescribeThingTypeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DetachPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DetachPrincipalPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DetachSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DetachSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DetachThingPrincipalRequestMarshaller;
import com.amazonaws.services.iot.model.transform.DetachThingPrincipalResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.DisableTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.EnableTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetEffectivePoliciesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetEffectivePoliciesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetIndexingConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetIndexingConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetJobDocumentRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetJobDocumentResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetLoggingOptionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetLoggingOptionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetOTAUpdateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetOTAUpdateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetPolicyResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetPolicyVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetPolicyVersionResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetRegistrationCodeRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetRegistrationCodeResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetTopicRuleResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.GetV2LoggingOptionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.GetV2LoggingOptionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.IndexNotReadyExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InternalExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InternalFailureExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InvalidQueryExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InvalidRequestExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InvalidResponseExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.InvalidStateTransitionExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.LimitExceededExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListActiveViolationsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListActiveViolationsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAttachedPoliciesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAttachedPoliciesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditFindingsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditFindingsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditTasksRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAuditTasksResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListAuthorizersRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListAuthorizersResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListBillingGroupsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListBillingGroupsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListCACertificatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListCACertificatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListCertificatesByCARequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListCertificatesByCAResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListCertificatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListCertificatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListIndicesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListIndicesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListJobExecutionsForJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListJobExecutionsForJobResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListJobExecutionsForThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListJobExecutionsForThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListJobsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListJobsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListOTAUpdatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListOTAUpdatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListOutgoingCertificatesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListOutgoingCertificatesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListPoliciesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListPoliciesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListPolicyPrincipalsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListPolicyPrincipalsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListPolicyVersionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListPolicyVersionsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListPrincipalPoliciesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListPrincipalPoliciesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListPrincipalThingsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListPrincipalThingsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListRoleAliasesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListRoleAliasesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListScheduledAuditsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListScheduledAuditsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListSecurityProfilesForTargetRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListSecurityProfilesForTargetResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListSecurityProfilesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListSecurityProfilesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListStreamsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListStreamsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListTagsForResourceRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListTagsForResourceResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListTargetsForPolicyRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListTargetsForPolicyResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListTargetsForSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListTargetsForSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingGroupsForThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingGroupsForThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingGroupsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingGroupsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingPrincipalsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingPrincipalsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingRegistrationTaskReportsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingRegistrationTaskReportsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingRegistrationTasksRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingRegistrationTasksResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingTypesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingTypesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsInBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsInBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsInThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsInThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListThingsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListTopicRulesRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListTopicRulesResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListV2LoggingLevelsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListV2LoggingLevelsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ListViolationEventsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ListViolationEventsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.MalformedPolicyExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.NotConfiguredExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.RegisterCACertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RegisterCACertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.RegisterCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RegisterCertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.RegisterThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RegisterThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.RegistrationCodeValidationExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.RejectCertificateTransferRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RemoveThingFromBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RemoveThingFromBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.RemoveThingFromThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.RemoveThingFromThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ReplaceTopicRuleRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ResourceAlreadyExistsExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.ResourceNotFoundExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.ResourceRegistrationFailureExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.SearchIndexRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SearchIndexResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ServiceUnavailableExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.SetDefaultAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SetDefaultAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.SetDefaultPolicyVersionRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SetLoggingOptionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SetV2LoggingLevelRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SetV2LoggingOptionsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.SqlParseExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.StartOnDemandAuditTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.StartOnDemandAuditTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.StartThingRegistrationTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.StartThingRegistrationTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.StopThingRegistrationTaskRequestMarshaller;
import com.amazonaws.services.iot.model.transform.StopThingRegistrationTaskResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.TagResourceRequestMarshaller;
import com.amazonaws.services.iot.model.transform.TagResourceResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.TestAuthorizationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.TestAuthorizationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.TestInvokeAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.TestInvokeAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ThrottlingExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.TransferAlreadyCompletedExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.TransferCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.TransferCertificateResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.TransferConflictExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.UnauthorizedExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.UntagResourceRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UntagResourceResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateAccountAuditConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateAccountAuditConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateAuthorizerRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateAuthorizerResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateBillingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateBillingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateCACertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateCertificateRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateDynamicThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateDynamicThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateEventConfigurationsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateEventConfigurationsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateIndexingConfigurationRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateIndexingConfigurationResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateJobRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateRoleAliasRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateRoleAliasResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateScheduledAuditRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateScheduledAuditResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateSecurityProfileRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateSecurityProfileResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateStreamRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateStreamResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingGroupRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingGroupResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingGroupsForThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingGroupsForThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingRequestMarshaller;
import com.amazonaws.services.iot.model.transform.UpdateThingResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.ValidateSecurityProfileBehaviorsRequestMarshaller;
import com.amazonaws.services.iot.model.transform.ValidateSecurityProfileBehaviorsResultJsonUnmarshaller;
import com.amazonaws.services.iot.model.transform.VersionConflictExceptionUnmarshaller;
import com.amazonaws.services.iot.model.transform.VersionsLimitExceededExceptionUnmarshaller;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.util.AWSRequestMetrics;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public class AWSIotClient extends AmazonWebServiceClient implements AWSIot {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected List<JsonErrorUnmarshaller> jsonErrorUnmarshallers;

    @Deprecated
    public AWSIotClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration clientConfiguration) {
        return clientConfiguration;
    }

    private void init() {
        this.jsonErrorUnmarshallers = new ArrayList();
        this.jsonErrorUnmarshallers.add(new CertificateConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CertificateStateExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new CertificateValidationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ConflictingResourceUpdateExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new DeleteConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new IndexNotReadyExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InternalExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InternalFailureExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidQueryExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidRequestExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidResponseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidStateTransitionExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new LimitExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new MalformedPolicyExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new NotConfiguredExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new RegistrationCodeValidationExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceAlreadyExistsExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceRegistrationFailureExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ServiceUnavailableExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new SqlParseExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ThrottlingExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new TransferAlreadyCompletedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new TransferConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new UnauthorizedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new VersionConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new VersionsLimitExceededExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new JsonErrorUnmarshaller());
        setEndpoint("iot.us-east-1.amazonaws.com");
        this.endpointPrefix = "iot";
        HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandlerChain("/com/amazonaws/services/iot/request.handlers"));
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandler2Chain("/com/amazonaws/services/iot/request.handler2s"));
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
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AcceptCertificateTransferRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void acceptCertificateTransfer(AcceptCertificateTransferRequest acceptCertificateTransferRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(acceptCertificateTransferRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<AcceptCertificateTransferRequest> marshall = new AcceptCertificateTransferRequestMarshaller().marshall((AcceptCertificateTransferRequest) acceptCertificateTransferRequest);
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
                endClientExecution(awsRequestMetrics, acceptCertificateTransferRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            acceptCertificateTransferRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, acceptCertificateTransferRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AddThingToBillingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public AddThingToBillingGroupResult addThingToBillingGroup(AddThingToBillingGroupRequest addThingToBillingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(addThingToBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<AddThingToBillingGroupRequest> marshall = new AddThingToBillingGroupRequestMarshaller().marshall((AddThingToBillingGroupRequest) addThingToBillingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new AddThingToBillingGroupResultJsonUnmarshaller()), createExecutionContext);
                        AddThingToBillingGroupResult addThingToBillingGroupResult = (AddThingToBillingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return addThingToBillingGroupResult;
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
                endClientExecution(awsRequestMetrics, addThingToBillingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            addThingToBillingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, addThingToBillingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AddThingToThingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public AddThingToThingGroupResult addThingToThingGroup(AddThingToThingGroupRequest addThingToThingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(addThingToThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<AddThingToThingGroupRequest> marshall = new AddThingToThingGroupRequestMarshaller().marshall((AddThingToThingGroupRequest) addThingToThingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new AddThingToThingGroupResultJsonUnmarshaller()), createExecutionContext);
                        AddThingToThingGroupResult addThingToThingGroupResult = (AddThingToThingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return addThingToThingGroupResult;
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
                endClientExecution(awsRequestMetrics, addThingToThingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            addThingToThingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, addThingToThingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AssociateTargetsWithJobRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public AssociateTargetsWithJobResult associateTargetsWithJob(AssociateTargetsWithJobRequest associateTargetsWithJobRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(associateTargetsWithJobRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<AssociateTargetsWithJobRequest> marshall = new AssociateTargetsWithJobRequestMarshaller().marshall((AssociateTargetsWithJobRequest) associateTargetsWithJobRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new AssociateTargetsWithJobResultJsonUnmarshaller()), createExecutionContext);
                        AssociateTargetsWithJobResult associateTargetsWithJobResult = (AssociateTargetsWithJobResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return associateTargetsWithJobResult;
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
                endClientExecution(awsRequestMetrics, associateTargetsWithJobRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            associateTargetsWithJobRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, associateTargetsWithJobRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AttachPolicyRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void attachPolicy(AttachPolicyRequest attachPolicyRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(attachPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<AttachPolicyRequest> marshall = new AttachPolicyRequestMarshaller().marshall((AttachPolicyRequest) attachPolicyRequest);
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
                endClientExecution(awsRequestMetrics, attachPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            attachPolicyRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, attachPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AttachPrincipalPolicyRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    @Deprecated
    public void attachPrincipalPolicy(AttachPrincipalPolicyRequest attachPrincipalPolicyRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(attachPrincipalPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<AttachPrincipalPolicyRequest> marshall = new AttachPrincipalPolicyRequestMarshaller().marshall((AttachPrincipalPolicyRequest) attachPrincipalPolicyRequest);
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
                endClientExecution(awsRequestMetrics, attachPrincipalPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            attachPrincipalPolicyRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, attachPrincipalPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AttachSecurityProfileRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public AttachSecurityProfileResult attachSecurityProfile(AttachSecurityProfileRequest attachSecurityProfileRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(attachSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<AttachSecurityProfileRequest> marshall = new AttachSecurityProfileRequestMarshaller().marshall((AttachSecurityProfileRequest) attachSecurityProfileRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new AttachSecurityProfileResultJsonUnmarshaller()), createExecutionContext);
                        AttachSecurityProfileResult attachSecurityProfileResult = (AttachSecurityProfileResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return attachSecurityProfileResult;
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
                endClientExecution(awsRequestMetrics, attachSecurityProfileRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            attachSecurityProfileRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, attachSecurityProfileRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.AttachThingPrincipalRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public AttachThingPrincipalResult attachThingPrincipal(AttachThingPrincipalRequest attachThingPrincipalRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(attachThingPrincipalRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<AttachThingPrincipalRequest> marshall = new AttachThingPrincipalRequestMarshaller().marshall((AttachThingPrincipalRequest) attachThingPrincipalRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new AttachThingPrincipalResultJsonUnmarshaller()), createExecutionContext);
                        AttachThingPrincipalResult attachThingPrincipalResult = (AttachThingPrincipalResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return attachThingPrincipalResult;
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
                endClientExecution(awsRequestMetrics, attachThingPrincipalRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            attachThingPrincipalRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, attachThingPrincipalRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CancelAuditTaskRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CancelAuditTaskResult cancelAuditTask(CancelAuditTaskRequest cancelAuditTaskRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(cancelAuditTaskRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CancelAuditTaskRequest> marshall = new CancelAuditTaskRequestMarshaller().marshall((CancelAuditTaskRequest) cancelAuditTaskRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CancelAuditTaskResultJsonUnmarshaller()), createExecutionContext);
                        CancelAuditTaskResult cancelAuditTaskResult = (CancelAuditTaskResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return cancelAuditTaskResult;
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
                endClientExecution(awsRequestMetrics, cancelAuditTaskRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            cancelAuditTaskRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, cancelAuditTaskRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.model.CancelCertificateTransferRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void cancelCertificateTransfer(CancelCertificateTransferRequest cancelCertificateTransferRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(cancelCertificateTransferRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CancelCertificateTransferRequest> marshall = new CancelCertificateTransferRequestMarshaller().marshall((CancelCertificateTransferRequest) cancelCertificateTransferRequest);
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
                endClientExecution(awsRequestMetrics, cancelCertificateTransferRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            cancelCertificateTransferRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, cancelCertificateTransferRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CancelJobRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CancelJobResult cancelJob(CancelJobRequest cancelJobRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(cancelJobRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CancelJobRequest> marshall = new CancelJobRequestMarshaller().marshall((CancelJobRequest) cancelJobRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CancelJobResultJsonUnmarshaller()), createExecutionContext);
                        CancelJobResult cancelJobResult = (CancelJobResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return cancelJobResult;
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
                endClientExecution(awsRequestMetrics, cancelJobRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            cancelJobRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, cancelJobRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.model.CancelJobExecutionRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void cancelJobExecution(CancelJobExecutionRequest cancelJobExecutionRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(cancelJobExecutionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CancelJobExecutionRequest> marshall = new CancelJobExecutionRequestMarshaller().marshall((CancelJobExecutionRequest) cancelJobExecutionRequest);
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
                endClientExecution(awsRequestMetrics, cancelJobExecutionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            cancelJobExecutionRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, cancelJobExecutionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ClearDefaultAuthorizerRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ClearDefaultAuthorizerResult clearDefaultAuthorizer(ClearDefaultAuthorizerRequest clearDefaultAuthorizerRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(clearDefaultAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ClearDefaultAuthorizerRequest> marshall = new ClearDefaultAuthorizerRequestMarshaller().marshall((ClearDefaultAuthorizerRequest) clearDefaultAuthorizerRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ClearDefaultAuthorizerResultJsonUnmarshaller()), createExecutionContext);
                        ClearDefaultAuthorizerResult clearDefaultAuthorizerResult = (ClearDefaultAuthorizerResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return clearDefaultAuthorizerResult;
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
                endClientExecution(awsRequestMetrics, clearDefaultAuthorizerRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            clearDefaultAuthorizerRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, clearDefaultAuthorizerRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateAuthorizerRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateAuthorizerResult createAuthorizer(CreateAuthorizerRequest createAuthorizerRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateAuthorizerRequest> marshall = new CreateAuthorizerRequestMarshaller().marshall((CreateAuthorizerRequest) createAuthorizerRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateAuthorizerResultJsonUnmarshaller()), createExecutionContext);
                        CreateAuthorizerResult createAuthorizerResult = (CreateAuthorizerResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createAuthorizerResult;
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
                endClientExecution(awsRequestMetrics, createAuthorizerRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createAuthorizerRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createAuthorizerRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateBillingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateBillingGroupResult createBillingGroup(CreateBillingGroupRequest createBillingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateBillingGroupRequest> marshall = new CreateBillingGroupRequestMarshaller().marshall((CreateBillingGroupRequest) createBillingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateBillingGroupResultJsonUnmarshaller()), createExecutionContext);
                        CreateBillingGroupResult createBillingGroupResult = (CreateBillingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createBillingGroupResult;
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
                endClientExecution(awsRequestMetrics, createBillingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createBillingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createBillingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateCertificateFromCsrRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateCertificateFromCsrResult createCertificateFromCsr(CreateCertificateFromCsrRequest createCertificateFromCsrRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createCertificateFromCsrRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateCertificateFromCsrRequest> marshall = new CreateCertificateFromCsrRequestMarshaller().marshall((CreateCertificateFromCsrRequest) createCertificateFromCsrRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateCertificateFromCsrResultJsonUnmarshaller()), createExecutionContext);
                        CreateCertificateFromCsrResult createCertificateFromCsrResult = (CreateCertificateFromCsrResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createCertificateFromCsrResult;
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
                endClientExecution(awsRequestMetrics, createCertificateFromCsrRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createCertificateFromCsrRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createCertificateFromCsrRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateDynamicThingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateDynamicThingGroupResult createDynamicThingGroup(CreateDynamicThingGroupRequest createDynamicThingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createDynamicThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateDynamicThingGroupRequest> marshall = new CreateDynamicThingGroupRequestMarshaller().marshall((CreateDynamicThingGroupRequest) createDynamicThingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateDynamicThingGroupResultJsonUnmarshaller()), createExecutionContext);
                        CreateDynamicThingGroupResult createDynamicThingGroupResult = (CreateDynamicThingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createDynamicThingGroupResult;
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
                endClientExecution(awsRequestMetrics, createDynamicThingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createDynamicThingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createDynamicThingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateJobRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateJobResult createJob(CreateJobRequest createJobRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createJobRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateJobRequest> marshall = new CreateJobRequestMarshaller().marshall((CreateJobRequest) createJobRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateJobResultJsonUnmarshaller()), createExecutionContext);
                        CreateJobResult createJobResult = (CreateJobResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createJobResult;
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
                endClientExecution(awsRequestMetrics, createJobRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createJobRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createJobRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateKeysAndCertificateRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateKeysAndCertificateResult createKeysAndCertificate(CreateKeysAndCertificateRequest createKeysAndCertificateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createKeysAndCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateKeysAndCertificateRequest> marshall = new CreateKeysAndCertificateRequestMarshaller().marshall((CreateKeysAndCertificateRequest) createKeysAndCertificateRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateKeysAndCertificateResultJsonUnmarshaller()), createExecutionContext);
                        CreateKeysAndCertificateResult createKeysAndCertificateResult = (CreateKeysAndCertificateResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createKeysAndCertificateResult;
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
                endClientExecution(awsRequestMetrics, createKeysAndCertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createKeysAndCertificateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createKeysAndCertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateOTAUpdateRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateOTAUpdateResult createOTAUpdate(CreateOTAUpdateRequest createOTAUpdateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createOTAUpdateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateOTAUpdateRequest> marshall = new CreateOTAUpdateRequestMarshaller().marshall((CreateOTAUpdateRequest) createOTAUpdateRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateOTAUpdateResultJsonUnmarshaller()), createExecutionContext);
                        CreateOTAUpdateResult createOTAUpdateResult = (CreateOTAUpdateResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createOTAUpdateResult;
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
                endClientExecution(awsRequestMetrics, createOTAUpdateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createOTAUpdateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createOTAUpdateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreatePolicyRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreatePolicyResult createPolicy(CreatePolicyRequest createPolicyRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreatePolicyRequest> marshall = new CreatePolicyRequestMarshaller().marshall((CreatePolicyRequest) createPolicyRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreatePolicyResultJsonUnmarshaller()), createExecutionContext);
                        CreatePolicyResult createPolicyResult = (CreatePolicyResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createPolicyResult;
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
                endClientExecution(awsRequestMetrics, createPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createPolicyRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.CreatePolicyVersionRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreatePolicyVersionResult createPolicyVersion(CreatePolicyVersionRequest createPolicyVersionRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createPolicyVersionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreatePolicyVersionRequest> marshall = new CreatePolicyVersionRequestMarshaller().marshall((CreatePolicyVersionRequest) createPolicyVersionRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreatePolicyVersionResultJsonUnmarshaller()), createExecutionContext);
                        CreatePolicyVersionResult createPolicyVersionResult = (CreatePolicyVersionResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createPolicyVersionResult;
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
                endClientExecution(awsRequestMetrics, createPolicyVersionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createPolicyVersionRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createPolicyVersionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateRoleAliasRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateRoleAliasResult createRoleAlias(CreateRoleAliasRequest createRoleAliasRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createRoleAliasRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateRoleAliasRequest> marshall = new CreateRoleAliasRequestMarshaller().marshall((CreateRoleAliasRequest) createRoleAliasRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateRoleAliasResultJsonUnmarshaller()), createExecutionContext);
                        CreateRoleAliasResult createRoleAliasResult = (CreateRoleAliasResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createRoleAliasResult;
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
                endClientExecution(awsRequestMetrics, createRoleAliasRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createRoleAliasRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createRoleAliasRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateScheduledAuditRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateScheduledAuditResult createScheduledAudit(CreateScheduledAuditRequest createScheduledAuditRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createScheduledAuditRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateScheduledAuditRequest> marshall = new CreateScheduledAuditRequestMarshaller().marshall((CreateScheduledAuditRequest) createScheduledAuditRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateScheduledAuditResultJsonUnmarshaller()), createExecutionContext);
                        CreateScheduledAuditResult createScheduledAuditResult = (CreateScheduledAuditResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createScheduledAuditResult;
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
                endClientExecution(awsRequestMetrics, createScheduledAuditRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createScheduledAuditRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createScheduledAuditRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.CreateSecurityProfileRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateSecurityProfileResult createSecurityProfile(CreateSecurityProfileRequest createSecurityProfileRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateSecurityProfileRequest> marshall = new CreateSecurityProfileRequestMarshaller().marshall((CreateSecurityProfileRequest) createSecurityProfileRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateSecurityProfileResultJsonUnmarshaller()), createExecutionContext);
                        CreateSecurityProfileResult createSecurityProfileResult = (CreateSecurityProfileResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createSecurityProfileResult;
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
                endClientExecution(awsRequestMetrics, createSecurityProfileRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createSecurityProfileRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createSecurityProfileRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateStreamRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateStreamResult createStream(CreateStreamRequest createStreamRequest) throws AmazonServiceException, AmazonClientException {
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
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateStreamResultJsonUnmarshaller()), createExecutionContext);
                        CreateStreamResult createStreamResult = (CreateStreamResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createStreamResult;
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
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.CreateThingRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateThingResult createThing(CreateThingRequest createThingRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createThingRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateThingRequest> marshall = new CreateThingRequestMarshaller().marshall((CreateThingRequest) createThingRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateThingResultJsonUnmarshaller()), createExecutionContext);
                        CreateThingResult createThingResult = (CreateThingResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createThingResult;
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
                endClientExecution(awsRequestMetrics, createThingRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createThingRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createThingRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.CreateThingGroupRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateThingGroupResult createThingGroup(CreateThingGroupRequest createThingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateThingGroupRequest> marshall = new CreateThingGroupRequestMarshaller().marshall((CreateThingGroupRequest) createThingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateThingGroupResultJsonUnmarshaller()), createExecutionContext);
                        CreateThingGroupResult createThingGroupResult = (CreateThingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createThingGroupResult;
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
                endClientExecution(awsRequestMetrics, createThingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createThingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createThingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateThingTypeRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public CreateThingTypeResult createThingType(CreateThingTypeRequest createThingTypeRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createThingTypeRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateThingTypeRequest> marshall = new CreateThingTypeRequestMarshaller().marshall((CreateThingTypeRequest) createThingTypeRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new CreateThingTypeResultJsonUnmarshaller()), createExecutionContext);
                        CreateThingTypeResult createThingTypeResult = (CreateThingTypeResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return createThingTypeResult;
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
                endClientExecution(awsRequestMetrics, createThingTypeRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createThingTypeRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createThingTypeRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.CreateTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void createTopicRule(CreateTopicRuleRequest createTopicRuleRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(createTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<CreateTopicRuleRequest> marshall = new CreateTopicRuleRequestMarshaller().marshall((CreateTopicRuleRequest) createTopicRuleRequest);
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
                endClientExecution(awsRequestMetrics, createTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            createTopicRuleRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, createTopicRuleRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteAccountAuditConfigurationRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteAccountAuditConfigurationResult deleteAccountAuditConfiguration(DeleteAccountAuditConfigurationRequest deleteAccountAuditConfigurationRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteAccountAuditConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteAccountAuditConfigurationRequest> marshall = new DeleteAccountAuditConfigurationRequestMarshaller().marshall((DeleteAccountAuditConfigurationRequest) deleteAccountAuditConfigurationRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteAccountAuditConfigurationResultJsonUnmarshaller()), createExecutionContext);
                        DeleteAccountAuditConfigurationResult deleteAccountAuditConfigurationResult = (DeleteAccountAuditConfigurationResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteAccountAuditConfigurationResult;
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
                endClientExecution(awsRequestMetrics, deleteAccountAuditConfigurationRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteAccountAuditConfigurationRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteAccountAuditConfigurationRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.DeleteAuthorizerRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteAuthorizerResult deleteAuthorizer(DeleteAuthorizerRequest deleteAuthorizerRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteAuthorizerRequest> marshall = new DeleteAuthorizerRequestMarshaller().marshall((DeleteAuthorizerRequest) deleteAuthorizerRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteAuthorizerResultJsonUnmarshaller()), createExecutionContext);
                        DeleteAuthorizerResult deleteAuthorizerResult = (DeleteAuthorizerResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteAuthorizerResult;
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
                endClientExecution(awsRequestMetrics, deleteAuthorizerRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteAuthorizerRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteAuthorizerRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteBillingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteBillingGroupResult deleteBillingGroup(DeleteBillingGroupRequest deleteBillingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteBillingGroupRequest> marshall = new DeleteBillingGroupRequestMarshaller().marshall((DeleteBillingGroupRequest) deleteBillingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteBillingGroupResultJsonUnmarshaller()), createExecutionContext);
                        DeleteBillingGroupResult deleteBillingGroupResult = (DeleteBillingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteBillingGroupResult;
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
                endClientExecution(awsRequestMetrics, deleteBillingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteBillingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteBillingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteCACertificateRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteCACertificateResult deleteCACertificate(DeleteCACertificateRequest deleteCACertificateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteCACertificateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteCACertificateRequest> marshall = new DeleteCACertificateRequestMarshaller().marshall((DeleteCACertificateRequest) deleteCACertificateRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteCACertificateResultJsonUnmarshaller()), createExecutionContext);
                        DeleteCACertificateResult deleteCACertificateResult = (DeleteCACertificateResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteCACertificateResult;
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
                endClientExecution(awsRequestMetrics, deleteCACertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteCACertificateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteCACertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteCertificateRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteCertificate(DeleteCertificateRequest deleteCertificateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteCertificateRequest> marshall = new DeleteCertificateRequestMarshaller().marshall((DeleteCertificateRequest) deleteCertificateRequest);
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
                endClientExecution(awsRequestMetrics, deleteCertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteCertificateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteCertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteDynamicThingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteDynamicThingGroupResult deleteDynamicThingGroup(DeleteDynamicThingGroupRequest deleteDynamicThingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteDynamicThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteDynamicThingGroupRequest> marshall = new DeleteDynamicThingGroupRequestMarshaller().marshall((DeleteDynamicThingGroupRequest) deleteDynamicThingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteDynamicThingGroupResultJsonUnmarshaller()), createExecutionContext);
                        DeleteDynamicThingGroupResult deleteDynamicThingGroupResult = (DeleteDynamicThingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteDynamicThingGroupResult;
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
                endClientExecution(awsRequestMetrics, deleteDynamicThingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteDynamicThingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteDynamicThingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteJobRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteJob(DeleteJobRequest deleteJobRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteJobRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteJobRequest> marshall = new DeleteJobRequestMarshaller().marshall((DeleteJobRequest) deleteJobRequest);
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
                endClientExecution(awsRequestMetrics, deleteJobRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteJobRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteJobRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteJobExecutionRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteJobExecution(DeleteJobExecutionRequest deleteJobExecutionRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteJobExecutionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteJobExecutionRequest> marshall = new DeleteJobExecutionRequestMarshaller().marshall((DeleteJobExecutionRequest) deleteJobExecutionRequest);
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
                endClientExecution(awsRequestMetrics, deleteJobExecutionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteJobExecutionRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteJobExecutionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteOTAUpdateRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteOTAUpdateResult deleteOTAUpdate(DeleteOTAUpdateRequest deleteOTAUpdateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteOTAUpdateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteOTAUpdateRequest> marshall = new DeleteOTAUpdateRequestMarshaller().marshall((DeleteOTAUpdateRequest) deleteOTAUpdateRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteOTAUpdateResultJsonUnmarshaller()), createExecutionContext);
                        DeleteOTAUpdateResult deleteOTAUpdateResult = (DeleteOTAUpdateResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteOTAUpdateResult;
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
                endClientExecution(awsRequestMetrics, deleteOTAUpdateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteOTAUpdateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteOTAUpdateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeletePolicyRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deletePolicy(DeletePolicyRequest deletePolicyRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deletePolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeletePolicyRequest> marshall = new DeletePolicyRequestMarshaller().marshall((DeletePolicyRequest) deletePolicyRequest);
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
                endClientExecution(awsRequestMetrics, deletePolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deletePolicyRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deletePolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeletePolicyVersionRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deletePolicyVersion(DeletePolicyVersionRequest deletePolicyVersionRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deletePolicyVersionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeletePolicyVersionRequest> marshall = new DeletePolicyVersionRequestMarshaller().marshall((DeletePolicyVersionRequest) deletePolicyVersionRequest);
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
                endClientExecution(awsRequestMetrics, deletePolicyVersionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deletePolicyVersionRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deletePolicyVersionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteRegistrationCodeRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteRegistrationCodeResult deleteRegistrationCode(DeleteRegistrationCodeRequest deleteRegistrationCodeRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteRegistrationCodeRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteRegistrationCodeRequest> marshall = new DeleteRegistrationCodeRequestMarshaller().marshall((DeleteRegistrationCodeRequest) deleteRegistrationCodeRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteRegistrationCodeResultJsonUnmarshaller()), createExecutionContext);
                        DeleteRegistrationCodeResult deleteRegistrationCodeResult = (DeleteRegistrationCodeResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteRegistrationCodeResult;
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
                endClientExecution(awsRequestMetrics, deleteRegistrationCodeRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteRegistrationCodeRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteRegistrationCodeRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteRoleAliasRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteRoleAliasResult deleteRoleAlias(DeleteRoleAliasRequest deleteRoleAliasRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteRoleAliasRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteRoleAliasRequest> marshall = new DeleteRoleAliasRequestMarshaller().marshall((DeleteRoleAliasRequest) deleteRoleAliasRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteRoleAliasResultJsonUnmarshaller()), createExecutionContext);
                        DeleteRoleAliasResult deleteRoleAliasResult = (DeleteRoleAliasResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteRoleAliasResult;
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
                endClientExecution(awsRequestMetrics, deleteRoleAliasRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteRoleAliasRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteRoleAliasRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteScheduledAuditRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteScheduledAuditResult deleteScheduledAudit(DeleteScheduledAuditRequest deleteScheduledAuditRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteScheduledAuditRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteScheduledAuditRequest> marshall = new DeleteScheduledAuditRequestMarshaller().marshall((DeleteScheduledAuditRequest) deleteScheduledAuditRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteScheduledAuditResultJsonUnmarshaller()), createExecutionContext);
                        DeleteScheduledAuditResult deleteScheduledAuditResult = (DeleteScheduledAuditResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteScheduledAuditResult;
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
                endClientExecution(awsRequestMetrics, deleteScheduledAuditRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteScheduledAuditRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteScheduledAuditRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteSecurityProfileRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteSecurityProfileResult deleteSecurityProfile(DeleteSecurityProfileRequest deleteSecurityProfileRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteSecurityProfileRequest> marshall = new DeleteSecurityProfileRequestMarshaller().marshall((DeleteSecurityProfileRequest) deleteSecurityProfileRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteSecurityProfileResultJsonUnmarshaller()), createExecutionContext);
                        DeleteSecurityProfileResult deleteSecurityProfileResult = (DeleteSecurityProfileResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteSecurityProfileResult;
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
                endClientExecution(awsRequestMetrics, deleteSecurityProfileRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteSecurityProfileRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteSecurityProfileRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.DeleteStreamRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteStreamResult deleteStream(DeleteStreamRequest deleteStreamRequest) throws AmazonServiceException, AmazonClientException {
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
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteStreamResultJsonUnmarshaller()), createExecutionContext);
                        DeleteStreamResult deleteStreamResult = (DeleteStreamResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteStreamResult;
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
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.DeleteThingRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteThingResult deleteThing(DeleteThingRequest deleteThingRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteThingRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteThingRequest> marshall = new DeleteThingRequestMarshaller().marshall((DeleteThingRequest) deleteThingRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteThingResultJsonUnmarshaller()), createExecutionContext);
                        DeleteThingResult deleteThingResult = (DeleteThingResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteThingResult;
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
                endClientExecution(awsRequestMetrics, deleteThingRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteThingRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteThingRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteThingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteThingGroupResult deleteThingGroup(DeleteThingGroupRequest deleteThingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteThingGroupRequest> marshall = new DeleteThingGroupRequestMarshaller().marshall((DeleteThingGroupRequest) deleteThingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteThingGroupResultJsonUnmarshaller()), createExecutionContext);
                        DeleteThingGroupResult deleteThingGroupResult = (DeleteThingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteThingGroupResult;
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
                endClientExecution(awsRequestMetrics, deleteThingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteThingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteThingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteThingTypeRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeleteThingTypeResult deleteThingType(DeleteThingTypeRequest deleteThingTypeRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteThingTypeRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteThingTypeRequest> marshall = new DeleteThingTypeRequestMarshaller().marshall((DeleteThingTypeRequest) deleteThingTypeRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeleteThingTypeResultJsonUnmarshaller()), createExecutionContext);
                        DeleteThingTypeResult deleteThingTypeResult = (DeleteThingTypeResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deleteThingTypeResult;
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
                endClientExecution(awsRequestMetrics, deleteThingTypeRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteThingTypeRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteThingTypeRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteTopicRule(DeleteTopicRuleRequest deleteTopicRuleRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteTopicRuleRequest> marshall = new DeleteTopicRuleRequestMarshaller().marshall((DeleteTopicRuleRequest) deleteTopicRuleRequest);
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
                endClientExecution(awsRequestMetrics, deleteTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteTopicRuleRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteTopicRuleRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeleteV2LoggingLevelRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void deleteV2LoggingLevel(DeleteV2LoggingLevelRequest deleteV2LoggingLevelRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deleteV2LoggingLevelRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeleteV2LoggingLevelRequest> marshall = new DeleteV2LoggingLevelRequestMarshaller().marshall((DeleteV2LoggingLevelRequest) deleteV2LoggingLevelRequest);
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
                endClientExecution(awsRequestMetrics, deleteV2LoggingLevelRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deleteV2LoggingLevelRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deleteV2LoggingLevelRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DeprecateThingTypeRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DeprecateThingTypeResult deprecateThingType(DeprecateThingTypeRequest deprecateThingTypeRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(deprecateThingTypeRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DeprecateThingTypeRequest> marshall = new DeprecateThingTypeRequestMarshaller().marshall((DeprecateThingTypeRequest) deprecateThingTypeRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DeprecateThingTypeResultJsonUnmarshaller()), createExecutionContext);
                        DeprecateThingTypeResult deprecateThingTypeResult = (DeprecateThingTypeResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return deprecateThingTypeResult;
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
                endClientExecution(awsRequestMetrics, deprecateThingTypeRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            deprecateThingTypeRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, deprecateThingTypeRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeAccountAuditConfigurationRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeAccountAuditConfigurationResult describeAccountAuditConfiguration(DescribeAccountAuditConfigurationRequest describeAccountAuditConfigurationRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeAccountAuditConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeAccountAuditConfigurationRequest> marshall = new DescribeAccountAuditConfigurationRequestMarshaller().marshall((DescribeAccountAuditConfigurationRequest) describeAccountAuditConfigurationRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeAccountAuditConfigurationResultJsonUnmarshaller()), createExecutionContext);
                        DescribeAccountAuditConfigurationResult describeAccountAuditConfigurationResult = (DescribeAccountAuditConfigurationResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeAccountAuditConfigurationResult;
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
                endClientExecution(awsRequestMetrics, describeAccountAuditConfigurationRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeAccountAuditConfigurationRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeAccountAuditConfigurationRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeAuditTaskRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeAuditTaskResult describeAuditTask(DescribeAuditTaskRequest describeAuditTaskRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeAuditTaskRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeAuditTaskRequest> marshall = new DescribeAuditTaskRequestMarshaller().marshall((DescribeAuditTaskRequest) describeAuditTaskRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeAuditTaskResultJsonUnmarshaller()), createExecutionContext);
                        DescribeAuditTaskResult describeAuditTaskResult = (DescribeAuditTaskResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeAuditTaskResult;
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
                endClientExecution(awsRequestMetrics, describeAuditTaskRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeAuditTaskRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeAuditTaskRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeAuthorizerRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeAuthorizerResult describeAuthorizer(DescribeAuthorizerRequest describeAuthorizerRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeAuthorizerRequest> marshall = new DescribeAuthorizerRequestMarshaller().marshall((DescribeAuthorizerRequest) describeAuthorizerRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeAuthorizerResultJsonUnmarshaller()), createExecutionContext);
                        DescribeAuthorizerResult describeAuthorizerResult = (DescribeAuthorizerResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeAuthorizerResult;
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
                endClientExecution(awsRequestMetrics, describeAuthorizerRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeAuthorizerRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeAuthorizerRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.DescribeBillingGroupRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeBillingGroupResult describeBillingGroup(DescribeBillingGroupRequest describeBillingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeBillingGroupRequest> marshall = new DescribeBillingGroupRequestMarshaller().marshall((DescribeBillingGroupRequest) describeBillingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeBillingGroupResultJsonUnmarshaller()), createExecutionContext);
                        DescribeBillingGroupResult describeBillingGroupResult = (DescribeBillingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeBillingGroupResult;
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
                endClientExecution(awsRequestMetrics, describeBillingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeBillingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeBillingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeCACertificateRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeCACertificateResult describeCACertificate(DescribeCACertificateRequest describeCACertificateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeCACertificateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeCACertificateRequest> marshall = new DescribeCACertificateRequestMarshaller().marshall((DescribeCACertificateRequest) describeCACertificateRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeCACertificateResultJsonUnmarshaller()), createExecutionContext);
                        DescribeCACertificateResult describeCACertificateResult = (DescribeCACertificateResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeCACertificateResult;
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
                endClientExecution(awsRequestMetrics, describeCACertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeCACertificateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeCACertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeCertificateRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeCertificateResult describeCertificate(DescribeCertificateRequest describeCertificateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeCertificateRequest> marshall = new DescribeCertificateRequestMarshaller().marshall((DescribeCertificateRequest) describeCertificateRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeCertificateResultJsonUnmarshaller()), createExecutionContext);
                        DescribeCertificateResult describeCertificateResult = (DescribeCertificateResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeCertificateResult;
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
                endClientExecution(awsRequestMetrics, describeCertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeCertificateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeCertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeDefaultAuthorizerRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeDefaultAuthorizerResult describeDefaultAuthorizer(DescribeDefaultAuthorizerRequest describeDefaultAuthorizerRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeDefaultAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeDefaultAuthorizerRequest> marshall = new DescribeDefaultAuthorizerRequestMarshaller().marshall((DescribeDefaultAuthorizerRequest) describeDefaultAuthorizerRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeDefaultAuthorizerResultJsonUnmarshaller()), createExecutionContext);
                        DescribeDefaultAuthorizerResult describeDefaultAuthorizerResult = (DescribeDefaultAuthorizerResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeDefaultAuthorizerResult;
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
                endClientExecution(awsRequestMetrics, describeDefaultAuthorizerRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeDefaultAuthorizerRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeDefaultAuthorizerRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeEndpointRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeEndpointResult describeEndpoint(DescribeEndpointRequest describeEndpointRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeEndpointRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeEndpointRequest> marshall = new DescribeEndpointRequestMarshaller().marshall((DescribeEndpointRequest) describeEndpointRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeEndpointResultJsonUnmarshaller()), createExecutionContext);
                        DescribeEndpointResult describeEndpointResult = (DescribeEndpointResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeEndpointResult;
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
                endClientExecution(awsRequestMetrics, describeEndpointRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeEndpointRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeEndpointRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeEventConfigurationsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeEventConfigurationsResult describeEventConfigurations(DescribeEventConfigurationsRequest describeEventConfigurationsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeEventConfigurationsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeEventConfigurationsRequest> marshall = new DescribeEventConfigurationsRequestMarshaller().marshall((DescribeEventConfigurationsRequest) describeEventConfigurationsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeEventConfigurationsResultJsonUnmarshaller()), createExecutionContext);
                        DescribeEventConfigurationsResult describeEventConfigurationsResult = (DescribeEventConfigurationsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeEventConfigurationsResult;
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
                endClientExecution(awsRequestMetrics, describeEventConfigurationsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeEventConfigurationsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeEventConfigurationsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeIndexRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeIndexResult describeIndex(DescribeIndexRequest describeIndexRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeIndexRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeIndexRequest> marshall = new DescribeIndexRequestMarshaller().marshall((DescribeIndexRequest) describeIndexRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeIndexResultJsonUnmarshaller()), createExecutionContext);
                        DescribeIndexResult describeIndexResult = (DescribeIndexResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeIndexResult;
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
                endClientExecution(awsRequestMetrics, describeIndexRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeIndexRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeIndexRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeJobRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeJobResult describeJob(DescribeJobRequest describeJobRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeJobRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeJobRequest> marshall = new DescribeJobRequestMarshaller().marshall((DescribeJobRequest) describeJobRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeJobResultJsonUnmarshaller()), createExecutionContext);
                        DescribeJobResult describeJobResult = (DescribeJobResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeJobResult;
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
                endClientExecution(awsRequestMetrics, describeJobRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeJobRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeJobRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeJobExecutionRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeJobExecutionResult describeJobExecution(DescribeJobExecutionRequest describeJobExecutionRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeJobExecutionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeJobExecutionRequest> marshall = new DescribeJobExecutionRequestMarshaller().marshall((DescribeJobExecutionRequest) describeJobExecutionRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeJobExecutionResultJsonUnmarshaller()), createExecutionContext);
                        DescribeJobExecutionResult describeJobExecutionResult = (DescribeJobExecutionResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeJobExecutionResult;
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
                endClientExecution(awsRequestMetrics, describeJobExecutionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeJobExecutionRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeJobExecutionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.DescribeRoleAliasRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeRoleAliasResult describeRoleAlias(DescribeRoleAliasRequest describeRoleAliasRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeRoleAliasRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeRoleAliasRequest> marshall = new DescribeRoleAliasRequestMarshaller().marshall((DescribeRoleAliasRequest) describeRoleAliasRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeRoleAliasResultJsonUnmarshaller()), createExecutionContext);
                        DescribeRoleAliasResult describeRoleAliasResult = (DescribeRoleAliasResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeRoleAliasResult;
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
                endClientExecution(awsRequestMetrics, describeRoleAliasRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeRoleAliasRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeRoleAliasRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeScheduledAuditRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeScheduledAuditResult describeScheduledAudit(DescribeScheduledAuditRequest describeScheduledAuditRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeScheduledAuditRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeScheduledAuditRequest> marshall = new DescribeScheduledAuditRequestMarshaller().marshall((DescribeScheduledAuditRequest) describeScheduledAuditRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeScheduledAuditResultJsonUnmarshaller()), createExecutionContext);
                        DescribeScheduledAuditResult describeScheduledAuditResult = (DescribeScheduledAuditResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeScheduledAuditResult;
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
                endClientExecution(awsRequestMetrics, describeScheduledAuditRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeScheduledAuditRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeScheduledAuditRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeSecurityProfileRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeSecurityProfileResult describeSecurityProfile(DescribeSecurityProfileRequest describeSecurityProfileRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeSecurityProfileRequest> marshall = new DescribeSecurityProfileRequestMarshaller().marshall((DescribeSecurityProfileRequest) describeSecurityProfileRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeSecurityProfileResultJsonUnmarshaller()), createExecutionContext);
                        DescribeSecurityProfileResult describeSecurityProfileResult = (DescribeSecurityProfileResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeSecurityProfileResult;
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
                endClientExecution(awsRequestMetrics, describeSecurityProfileRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeSecurityProfileRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeSecurityProfileRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeStreamRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
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
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeThingRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeThingResult describeThing(DescribeThingRequest describeThingRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeThingRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeThingRequest> marshall = new DescribeThingRequestMarshaller().marshall((DescribeThingRequest) describeThingRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeThingResultJsonUnmarshaller()), createExecutionContext);
                        DescribeThingResult describeThingResult = (DescribeThingResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeThingResult;
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
                endClientExecution(awsRequestMetrics, describeThingRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeThingRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeThingRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeThingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeThingGroupResult describeThingGroup(DescribeThingGroupRequest describeThingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeThingGroupRequest> marshall = new DescribeThingGroupRequestMarshaller().marshall((DescribeThingGroupRequest) describeThingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeThingGroupResultJsonUnmarshaller()), createExecutionContext);
                        DescribeThingGroupResult describeThingGroupResult = (DescribeThingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeThingGroupResult;
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
                endClientExecution(awsRequestMetrics, describeThingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeThingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeThingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeThingRegistrationTaskRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeThingRegistrationTaskResult describeThingRegistrationTask(DescribeThingRegistrationTaskRequest describeThingRegistrationTaskRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeThingRegistrationTaskRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeThingRegistrationTaskRequest> marshall = new DescribeThingRegistrationTaskRequestMarshaller().marshall((DescribeThingRegistrationTaskRequest) describeThingRegistrationTaskRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeThingRegistrationTaskResultJsonUnmarshaller()), createExecutionContext);
                        DescribeThingRegistrationTaskResult describeThingRegistrationTaskResult = (DescribeThingRegistrationTaskResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeThingRegistrationTaskResult;
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
                endClientExecution(awsRequestMetrics, describeThingRegistrationTaskRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeThingRegistrationTaskRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeThingRegistrationTaskRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DescribeThingTypeRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DescribeThingTypeResult describeThingType(DescribeThingTypeRequest describeThingTypeRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(describeThingTypeRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DescribeThingTypeRequest> marshall = new DescribeThingTypeRequestMarshaller().marshall((DescribeThingTypeRequest) describeThingTypeRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DescribeThingTypeResultJsonUnmarshaller()), createExecutionContext);
                        DescribeThingTypeResult describeThingTypeResult = (DescribeThingTypeResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return describeThingTypeResult;
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
                endClientExecution(awsRequestMetrics, describeThingTypeRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            describeThingTypeRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, describeThingTypeRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DetachPolicyRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void detachPolicy(DetachPolicyRequest detachPolicyRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(detachPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DetachPolicyRequest> marshall = new DetachPolicyRequestMarshaller().marshall((DetachPolicyRequest) detachPolicyRequest);
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
                endClientExecution(awsRequestMetrics, detachPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            detachPolicyRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, detachPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.model.DetachPrincipalPolicyRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    @Deprecated
    public void detachPrincipalPolicy(DetachPrincipalPolicyRequest detachPrincipalPolicyRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(detachPrincipalPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DetachPrincipalPolicyRequest> marshall = new DetachPrincipalPolicyRequestMarshaller().marshall((DetachPrincipalPolicyRequest) detachPrincipalPolicyRequest);
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
                endClientExecution(awsRequestMetrics, detachPrincipalPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            detachPrincipalPolicyRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, detachPrincipalPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DetachSecurityProfileRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DetachSecurityProfileResult detachSecurityProfile(DetachSecurityProfileRequest detachSecurityProfileRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(detachSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DetachSecurityProfileRequest> marshall = new DetachSecurityProfileRequestMarshaller().marshall((DetachSecurityProfileRequest) detachSecurityProfileRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DetachSecurityProfileResultJsonUnmarshaller()), createExecutionContext);
                        DetachSecurityProfileResult detachSecurityProfileResult = (DetachSecurityProfileResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return detachSecurityProfileResult;
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
                endClientExecution(awsRequestMetrics, detachSecurityProfileRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            detachSecurityProfileRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, detachSecurityProfileRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DetachThingPrincipalRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public DetachThingPrincipalResult detachThingPrincipal(DetachThingPrincipalRequest detachThingPrincipalRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(detachThingPrincipalRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DetachThingPrincipalRequest> marshall = new DetachThingPrincipalRequestMarshaller().marshall((DetachThingPrincipalRequest) detachThingPrincipalRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new DetachThingPrincipalResultJsonUnmarshaller()), createExecutionContext);
                        DetachThingPrincipalResult detachThingPrincipalResult = (DetachThingPrincipalResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return detachThingPrincipalResult;
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
                endClientExecution(awsRequestMetrics, detachThingPrincipalRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            detachThingPrincipalRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, detachThingPrincipalRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.DisableTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void disableTopicRule(DisableTopicRuleRequest disableTopicRuleRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(disableTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<DisableTopicRuleRequest> marshall = new DisableTopicRuleRequestMarshaller().marshall((DisableTopicRuleRequest) disableTopicRuleRequest);
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
                endClientExecution(awsRequestMetrics, disableTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            disableTopicRuleRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, disableTopicRuleRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.EnableTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void enableTopicRule(EnableTopicRuleRequest enableTopicRuleRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(enableTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<EnableTopicRuleRequest> marshall = new EnableTopicRuleRequestMarshaller().marshall((EnableTopicRuleRequest) enableTopicRuleRequest);
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
                endClientExecution(awsRequestMetrics, enableTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            enableTopicRuleRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, enableTopicRuleRequest, null, true);
            throw th;
        }
    }

    @Override // com.amazonaws.services.iot.AWSIot
    @Deprecated
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest) {
        return this.client.getResponseMetadataForRequest(amazonWebServiceRequest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.GetEffectivePoliciesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetEffectivePoliciesResult getEffectivePolicies(GetEffectivePoliciesRequest getEffectivePoliciesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getEffectivePoliciesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetEffectivePoliciesRequest> marshall = new GetEffectivePoliciesRequestMarshaller().marshall((GetEffectivePoliciesRequest) getEffectivePoliciesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetEffectivePoliciesResultJsonUnmarshaller()), createExecutionContext);
                        GetEffectivePoliciesResult getEffectivePoliciesResult = (GetEffectivePoliciesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getEffectivePoliciesResult;
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
                endClientExecution(awsRequestMetrics, getEffectivePoliciesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getEffectivePoliciesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getEffectivePoliciesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.GetIndexingConfigurationRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetIndexingConfigurationResult getIndexingConfiguration(GetIndexingConfigurationRequest getIndexingConfigurationRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getIndexingConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetIndexingConfigurationRequest> marshall = new GetIndexingConfigurationRequestMarshaller().marshall((GetIndexingConfigurationRequest) getIndexingConfigurationRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetIndexingConfigurationResultJsonUnmarshaller()), createExecutionContext);
                        GetIndexingConfigurationResult getIndexingConfigurationResult = (GetIndexingConfigurationResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getIndexingConfigurationResult;
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
                endClientExecution(awsRequestMetrics, getIndexingConfigurationRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getIndexingConfigurationRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getIndexingConfigurationRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.GetJobDocumentRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetJobDocumentResult getJobDocument(GetJobDocumentRequest getJobDocumentRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getJobDocumentRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetJobDocumentRequest> marshall = new GetJobDocumentRequestMarshaller().marshall((GetJobDocumentRequest) getJobDocumentRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetJobDocumentResultJsonUnmarshaller()), createExecutionContext);
                        GetJobDocumentResult getJobDocumentResult = (GetJobDocumentResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getJobDocumentResult;
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
                endClientExecution(awsRequestMetrics, getJobDocumentRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getJobDocumentRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getJobDocumentRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.GetLoggingOptionsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetLoggingOptionsResult getLoggingOptions(GetLoggingOptionsRequest getLoggingOptionsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getLoggingOptionsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetLoggingOptionsRequest> marshall = new GetLoggingOptionsRequestMarshaller().marshall((GetLoggingOptionsRequest) getLoggingOptionsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetLoggingOptionsResultJsonUnmarshaller()), createExecutionContext);
                        GetLoggingOptionsResult getLoggingOptionsResult = (GetLoggingOptionsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getLoggingOptionsResult;
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
                endClientExecution(awsRequestMetrics, getLoggingOptionsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getLoggingOptionsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getLoggingOptionsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.GetOTAUpdateRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetOTAUpdateResult getOTAUpdate(GetOTAUpdateRequest getOTAUpdateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getOTAUpdateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetOTAUpdateRequest> marshall = new GetOTAUpdateRequestMarshaller().marshall((GetOTAUpdateRequest) getOTAUpdateRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetOTAUpdateResultJsonUnmarshaller()), createExecutionContext);
                        GetOTAUpdateResult getOTAUpdateResult = (GetOTAUpdateResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getOTAUpdateResult;
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
                endClientExecution(awsRequestMetrics, getOTAUpdateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getOTAUpdateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getOTAUpdateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.GetPolicyRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetPolicyResult getPolicy(GetPolicyRequest getPolicyRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetPolicyRequest> marshall = new GetPolicyRequestMarshaller().marshall((GetPolicyRequest) getPolicyRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetPolicyResultJsonUnmarshaller()), createExecutionContext);
                        GetPolicyResult getPolicyResult = (GetPolicyResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getPolicyResult;
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
                endClientExecution(awsRequestMetrics, getPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getPolicyRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.GetPolicyVersionRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetPolicyVersionResult getPolicyVersion(GetPolicyVersionRequest getPolicyVersionRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getPolicyVersionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetPolicyVersionRequest> marshall = new GetPolicyVersionRequestMarshaller().marshall((GetPolicyVersionRequest) getPolicyVersionRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetPolicyVersionResultJsonUnmarshaller()), createExecutionContext);
                        GetPolicyVersionResult getPolicyVersionResult = (GetPolicyVersionResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getPolicyVersionResult;
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
                endClientExecution(awsRequestMetrics, getPolicyVersionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getPolicyVersionRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getPolicyVersionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.GetRegistrationCodeRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetRegistrationCodeResult getRegistrationCode(GetRegistrationCodeRequest getRegistrationCodeRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getRegistrationCodeRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetRegistrationCodeRequest> marshall = new GetRegistrationCodeRequestMarshaller().marshall((GetRegistrationCodeRequest) getRegistrationCodeRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetRegistrationCodeResultJsonUnmarshaller()), createExecutionContext);
                        GetRegistrationCodeResult getRegistrationCodeResult = (GetRegistrationCodeResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getRegistrationCodeResult;
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
                endClientExecution(awsRequestMetrics, getRegistrationCodeRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getRegistrationCodeRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getRegistrationCodeRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.GetTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetTopicRuleResult getTopicRule(GetTopicRuleRequest getTopicRuleRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetTopicRuleRequest> marshall = new GetTopicRuleRequestMarshaller().marshall((GetTopicRuleRequest) getTopicRuleRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetTopicRuleResultJsonUnmarshaller()), createExecutionContext);
                        GetTopicRuleResult getTopicRuleResult = (GetTopicRuleResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getTopicRuleResult;
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
                endClientExecution(awsRequestMetrics, getTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getTopicRuleRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getTopicRuleRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.GetV2LoggingOptionsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public GetV2LoggingOptionsResult getV2LoggingOptions(GetV2LoggingOptionsRequest getV2LoggingOptionsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(getV2LoggingOptionsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<GetV2LoggingOptionsRequest> marshall = new GetV2LoggingOptionsRequestMarshaller().marshall((GetV2LoggingOptionsRequest) getV2LoggingOptionsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new GetV2LoggingOptionsResultJsonUnmarshaller()), createExecutionContext);
                        GetV2LoggingOptionsResult getV2LoggingOptionsResult = (GetV2LoggingOptionsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return getV2LoggingOptionsResult;
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
                endClientExecution(awsRequestMetrics, getV2LoggingOptionsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            getV2LoggingOptionsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, getV2LoggingOptionsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListActiveViolationsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListActiveViolationsResult listActiveViolations(ListActiveViolationsRequest listActiveViolationsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listActiveViolationsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListActiveViolationsRequest> marshall = new ListActiveViolationsRequestMarshaller().marshall((ListActiveViolationsRequest) listActiveViolationsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListActiveViolationsResultJsonUnmarshaller()), createExecutionContext);
                        ListActiveViolationsResult listActiveViolationsResult = (ListActiveViolationsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listActiveViolationsResult;
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
                endClientExecution(awsRequestMetrics, listActiveViolationsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listActiveViolationsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listActiveViolationsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListAttachedPoliciesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAttachedPoliciesResult listAttachedPolicies(ListAttachedPoliciesRequest listAttachedPoliciesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listAttachedPoliciesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListAttachedPoliciesRequest> marshall = new ListAttachedPoliciesRequestMarshaller().marshall((ListAttachedPoliciesRequest) listAttachedPoliciesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListAttachedPoliciesResultJsonUnmarshaller()), createExecutionContext);
                        ListAttachedPoliciesResult listAttachedPoliciesResult = (ListAttachedPoliciesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listAttachedPoliciesResult;
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
                endClientExecution(awsRequestMetrics, listAttachedPoliciesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listAttachedPoliciesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listAttachedPoliciesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListAuditFindingsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAuditFindingsResult listAuditFindings(ListAuditFindingsRequest listAuditFindingsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listAuditFindingsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListAuditFindingsRequest> marshall = new ListAuditFindingsRequestMarshaller().marshall((ListAuditFindingsRequest) listAuditFindingsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListAuditFindingsResultJsonUnmarshaller()), createExecutionContext);
                        ListAuditFindingsResult listAuditFindingsResult = (ListAuditFindingsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listAuditFindingsResult;
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
                endClientExecution(awsRequestMetrics, listAuditFindingsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listAuditFindingsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listAuditFindingsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListAuditTasksRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAuditTasksResult listAuditTasks(ListAuditTasksRequest listAuditTasksRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listAuditTasksRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListAuditTasksRequest> marshall = new ListAuditTasksRequestMarshaller().marshall((ListAuditTasksRequest) listAuditTasksRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListAuditTasksResultJsonUnmarshaller()), createExecutionContext);
                        ListAuditTasksResult listAuditTasksResult = (ListAuditTasksResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listAuditTasksResult;
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
                endClientExecution(awsRequestMetrics, listAuditTasksRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listAuditTasksRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listAuditTasksRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListAuthorizersRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListAuthorizersResult listAuthorizers(ListAuthorizersRequest listAuthorizersRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listAuthorizersRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListAuthorizersRequest> marshall = new ListAuthorizersRequestMarshaller().marshall((ListAuthorizersRequest) listAuthorizersRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListAuthorizersResultJsonUnmarshaller()), createExecutionContext);
                        ListAuthorizersResult listAuthorizersResult = (ListAuthorizersResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listAuthorizersResult;
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
                endClientExecution(awsRequestMetrics, listAuthorizersRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listAuthorizersRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listAuthorizersRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListBillingGroupsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListBillingGroupsResult listBillingGroups(ListBillingGroupsRequest listBillingGroupsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listBillingGroupsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListBillingGroupsRequest> marshall = new ListBillingGroupsRequestMarshaller().marshall((ListBillingGroupsRequest) listBillingGroupsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListBillingGroupsResultJsonUnmarshaller()), createExecutionContext);
                        ListBillingGroupsResult listBillingGroupsResult = (ListBillingGroupsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listBillingGroupsResult;
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
                endClientExecution(awsRequestMetrics, listBillingGroupsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listBillingGroupsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listBillingGroupsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListCACertificatesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListCACertificatesResult listCACertificates(ListCACertificatesRequest listCACertificatesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listCACertificatesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListCACertificatesRequest> marshall = new ListCACertificatesRequestMarshaller().marshall((ListCACertificatesRequest) listCACertificatesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListCACertificatesResultJsonUnmarshaller()), createExecutionContext);
                        ListCACertificatesResult listCACertificatesResult = (ListCACertificatesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listCACertificatesResult;
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
                endClientExecution(awsRequestMetrics, listCACertificatesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listCACertificatesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listCACertificatesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListCertificatesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListCertificatesResult listCertificates(ListCertificatesRequest listCertificatesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listCertificatesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListCertificatesRequest> marshall = new ListCertificatesRequestMarshaller().marshall((ListCertificatesRequest) listCertificatesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListCertificatesResultJsonUnmarshaller()), createExecutionContext);
                        ListCertificatesResult listCertificatesResult = (ListCertificatesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listCertificatesResult;
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
                endClientExecution(awsRequestMetrics, listCertificatesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listCertificatesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listCertificatesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListCertificatesByCARequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListCertificatesByCAResult listCertificatesByCA(ListCertificatesByCARequest listCertificatesByCARequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listCertificatesByCARequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListCertificatesByCARequest> marshall = new ListCertificatesByCARequestMarshaller().marshall((ListCertificatesByCARequest) listCertificatesByCARequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListCertificatesByCAResultJsonUnmarshaller()), createExecutionContext);
                        ListCertificatesByCAResult listCertificatesByCAResult = (ListCertificatesByCAResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listCertificatesByCAResult;
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
                endClientExecution(awsRequestMetrics, listCertificatesByCARequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listCertificatesByCARequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listCertificatesByCARequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListIndicesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListIndicesResult listIndices(ListIndicesRequest listIndicesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listIndicesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListIndicesRequest> marshall = new ListIndicesRequestMarshaller().marshall((ListIndicesRequest) listIndicesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListIndicesResultJsonUnmarshaller()), createExecutionContext);
                        ListIndicesResult listIndicesResult = (ListIndicesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listIndicesResult;
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
                endClientExecution(awsRequestMetrics, listIndicesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listIndicesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listIndicesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.ListJobExecutionsForJobRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListJobExecutionsForJobResult listJobExecutionsForJob(ListJobExecutionsForJobRequest listJobExecutionsForJobRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listJobExecutionsForJobRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListJobExecutionsForJobRequest> marshall = new ListJobExecutionsForJobRequestMarshaller().marshall((ListJobExecutionsForJobRequest) listJobExecutionsForJobRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListJobExecutionsForJobResultJsonUnmarshaller()), createExecutionContext);
                        ListJobExecutionsForJobResult listJobExecutionsForJobResult = (ListJobExecutionsForJobResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listJobExecutionsForJobResult;
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
                endClientExecution(awsRequestMetrics, listJobExecutionsForJobRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listJobExecutionsForJobRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listJobExecutionsForJobRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.ListJobExecutionsForThingRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListJobExecutionsForThingResult listJobExecutionsForThing(ListJobExecutionsForThingRequest listJobExecutionsForThingRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listJobExecutionsForThingRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListJobExecutionsForThingRequest> marshall = new ListJobExecutionsForThingRequestMarshaller().marshall((ListJobExecutionsForThingRequest) listJobExecutionsForThingRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListJobExecutionsForThingResultJsonUnmarshaller()), createExecutionContext);
                        ListJobExecutionsForThingResult listJobExecutionsForThingResult = (ListJobExecutionsForThingResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listJobExecutionsForThingResult;
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
                endClientExecution(awsRequestMetrics, listJobExecutionsForThingRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listJobExecutionsForThingRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listJobExecutionsForThingRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListJobsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListJobsResult listJobs(ListJobsRequest listJobsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listJobsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListJobsRequest> marshall = new ListJobsRequestMarshaller().marshall((ListJobsRequest) listJobsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListJobsResultJsonUnmarshaller()), createExecutionContext);
                        ListJobsResult listJobsResult = (ListJobsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listJobsResult;
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
                endClientExecution(awsRequestMetrics, listJobsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listJobsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listJobsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListOTAUpdatesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListOTAUpdatesResult listOTAUpdates(ListOTAUpdatesRequest listOTAUpdatesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listOTAUpdatesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListOTAUpdatesRequest> marshall = new ListOTAUpdatesRequestMarshaller().marshall((ListOTAUpdatesRequest) listOTAUpdatesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListOTAUpdatesResultJsonUnmarshaller()), createExecutionContext);
                        ListOTAUpdatesResult listOTAUpdatesResult = (ListOTAUpdatesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listOTAUpdatesResult;
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
                endClientExecution(awsRequestMetrics, listOTAUpdatesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listOTAUpdatesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listOTAUpdatesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListOutgoingCertificatesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListOutgoingCertificatesResult listOutgoingCertificates(ListOutgoingCertificatesRequest listOutgoingCertificatesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listOutgoingCertificatesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListOutgoingCertificatesRequest> marshall = new ListOutgoingCertificatesRequestMarshaller().marshall((ListOutgoingCertificatesRequest) listOutgoingCertificatesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListOutgoingCertificatesResultJsonUnmarshaller()), createExecutionContext);
                        ListOutgoingCertificatesResult listOutgoingCertificatesResult = (ListOutgoingCertificatesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listOutgoingCertificatesResult;
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
                endClientExecution(awsRequestMetrics, listOutgoingCertificatesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listOutgoingCertificatesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listOutgoingCertificatesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.ListPoliciesRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListPoliciesResult listPolicies(ListPoliciesRequest listPoliciesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listPoliciesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListPoliciesRequest> marshall = new ListPoliciesRequestMarshaller().marshall((ListPoliciesRequest) listPoliciesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListPoliciesResultJsonUnmarshaller()), createExecutionContext);
                        ListPoliciesResult listPoliciesResult = (ListPoliciesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listPoliciesResult;
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
                endClientExecution(awsRequestMetrics, listPoliciesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listPoliciesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listPoliciesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.ListPolicyPrincipalsRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    @Deprecated
    public ListPolicyPrincipalsResult listPolicyPrincipals(ListPolicyPrincipalsRequest listPolicyPrincipalsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listPolicyPrincipalsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListPolicyPrincipalsRequest> marshall = new ListPolicyPrincipalsRequestMarshaller().marshall((ListPolicyPrincipalsRequest) listPolicyPrincipalsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListPolicyPrincipalsResultJsonUnmarshaller()), createExecutionContext);
                        ListPolicyPrincipalsResult listPolicyPrincipalsResult = (ListPolicyPrincipalsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listPolicyPrincipalsResult;
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
                endClientExecution(awsRequestMetrics, listPolicyPrincipalsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listPolicyPrincipalsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listPolicyPrincipalsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListPolicyVersionsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListPolicyVersionsResult listPolicyVersions(ListPolicyVersionsRequest listPolicyVersionsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listPolicyVersionsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListPolicyVersionsRequest> marshall = new ListPolicyVersionsRequestMarshaller().marshall((ListPolicyVersionsRequest) listPolicyVersionsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListPolicyVersionsResultJsonUnmarshaller()), createExecutionContext);
                        ListPolicyVersionsResult listPolicyVersionsResult = (ListPolicyVersionsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listPolicyVersionsResult;
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
                endClientExecution(awsRequestMetrics, listPolicyVersionsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listPolicyVersionsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listPolicyVersionsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListPrincipalPoliciesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    @Deprecated
    public ListPrincipalPoliciesResult listPrincipalPolicies(ListPrincipalPoliciesRequest listPrincipalPoliciesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listPrincipalPoliciesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListPrincipalPoliciesRequest> marshall = new ListPrincipalPoliciesRequestMarshaller().marshall((ListPrincipalPoliciesRequest) listPrincipalPoliciesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListPrincipalPoliciesResultJsonUnmarshaller()), createExecutionContext);
                        ListPrincipalPoliciesResult listPrincipalPoliciesResult = (ListPrincipalPoliciesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listPrincipalPoliciesResult;
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
                endClientExecution(awsRequestMetrics, listPrincipalPoliciesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listPrincipalPoliciesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listPrincipalPoliciesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListPrincipalThingsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListPrincipalThingsResult listPrincipalThings(ListPrincipalThingsRequest listPrincipalThingsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listPrincipalThingsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListPrincipalThingsRequest> marshall = new ListPrincipalThingsRequestMarshaller().marshall((ListPrincipalThingsRequest) listPrincipalThingsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListPrincipalThingsResultJsonUnmarshaller()), createExecutionContext);
                        ListPrincipalThingsResult listPrincipalThingsResult = (ListPrincipalThingsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listPrincipalThingsResult;
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
                endClientExecution(awsRequestMetrics, listPrincipalThingsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listPrincipalThingsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listPrincipalThingsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListRoleAliasesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListRoleAliasesResult listRoleAliases(ListRoleAliasesRequest listRoleAliasesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listRoleAliasesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListRoleAliasesRequest> marshall = new ListRoleAliasesRequestMarshaller().marshall((ListRoleAliasesRequest) listRoleAliasesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListRoleAliasesResultJsonUnmarshaller()), createExecutionContext);
                        ListRoleAliasesResult listRoleAliasesResult = (ListRoleAliasesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listRoleAliasesResult;
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
                endClientExecution(awsRequestMetrics, listRoleAliasesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listRoleAliasesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listRoleAliasesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListScheduledAuditsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListScheduledAuditsResult listScheduledAudits(ListScheduledAuditsRequest listScheduledAuditsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listScheduledAuditsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListScheduledAuditsRequest> marshall = new ListScheduledAuditsRequestMarshaller().marshall((ListScheduledAuditsRequest) listScheduledAuditsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListScheduledAuditsResultJsonUnmarshaller()), createExecutionContext);
                        ListScheduledAuditsResult listScheduledAuditsResult = (ListScheduledAuditsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listScheduledAuditsResult;
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
                endClientExecution(awsRequestMetrics, listScheduledAuditsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listScheduledAuditsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listScheduledAuditsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListSecurityProfilesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListSecurityProfilesResult listSecurityProfiles(ListSecurityProfilesRequest listSecurityProfilesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listSecurityProfilesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListSecurityProfilesRequest> marshall = new ListSecurityProfilesRequestMarshaller().marshall((ListSecurityProfilesRequest) listSecurityProfilesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListSecurityProfilesResultJsonUnmarshaller()), createExecutionContext);
                        ListSecurityProfilesResult listSecurityProfilesResult = (ListSecurityProfilesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listSecurityProfilesResult;
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
                endClientExecution(awsRequestMetrics, listSecurityProfilesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listSecurityProfilesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listSecurityProfilesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListSecurityProfilesForTargetRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListSecurityProfilesForTargetResult listSecurityProfilesForTarget(ListSecurityProfilesForTargetRequest listSecurityProfilesForTargetRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listSecurityProfilesForTargetRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListSecurityProfilesForTargetRequest> marshall = new ListSecurityProfilesForTargetRequestMarshaller().marshall((ListSecurityProfilesForTargetRequest) listSecurityProfilesForTargetRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListSecurityProfilesForTargetResultJsonUnmarshaller()), createExecutionContext);
                        ListSecurityProfilesForTargetResult listSecurityProfilesForTargetResult = (ListSecurityProfilesForTargetResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listSecurityProfilesForTargetResult;
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
                endClientExecution(awsRequestMetrics, listSecurityProfilesForTargetRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listSecurityProfilesForTargetRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listSecurityProfilesForTargetRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListStreamsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
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
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListTagsForResourceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListTagsForResourceResult listTagsForResource(ListTagsForResourceRequest listTagsForResourceRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listTagsForResourceRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListTagsForResourceRequest> marshall = new ListTagsForResourceRequestMarshaller().marshall((ListTagsForResourceRequest) listTagsForResourceRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListTagsForResourceResultJsonUnmarshaller()), createExecutionContext);
                        ListTagsForResourceResult listTagsForResourceResult = (ListTagsForResourceResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listTagsForResourceResult;
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
                endClientExecution(awsRequestMetrics, listTagsForResourceRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listTagsForResourceRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listTagsForResourceRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListTargetsForPolicyRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListTargetsForPolicyResult listTargetsForPolicy(ListTargetsForPolicyRequest listTargetsForPolicyRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listTargetsForPolicyRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListTargetsForPolicyRequest> marshall = new ListTargetsForPolicyRequestMarshaller().marshall((ListTargetsForPolicyRequest) listTargetsForPolicyRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListTargetsForPolicyResultJsonUnmarshaller()), createExecutionContext);
                        ListTargetsForPolicyResult listTargetsForPolicyResult = (ListTargetsForPolicyResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listTargetsForPolicyResult;
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
                endClientExecution(awsRequestMetrics, listTargetsForPolicyRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listTargetsForPolicyRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listTargetsForPolicyRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListTargetsForSecurityProfileRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListTargetsForSecurityProfileResult listTargetsForSecurityProfile(ListTargetsForSecurityProfileRequest listTargetsForSecurityProfileRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listTargetsForSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListTargetsForSecurityProfileRequest> marshall = new ListTargetsForSecurityProfileRequestMarshaller().marshall((ListTargetsForSecurityProfileRequest) listTargetsForSecurityProfileRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListTargetsForSecurityProfileResultJsonUnmarshaller()), createExecutionContext);
                        ListTargetsForSecurityProfileResult listTargetsForSecurityProfileResult = (ListTargetsForSecurityProfileResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listTargetsForSecurityProfileResult;
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
                endClientExecution(awsRequestMetrics, listTargetsForSecurityProfileRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listTargetsForSecurityProfileRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listTargetsForSecurityProfileRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListThingGroupsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingGroupsResult listThingGroups(ListThingGroupsRequest listThingGroupsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listThingGroupsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListThingGroupsRequest> marshall = new ListThingGroupsRequestMarshaller().marshall((ListThingGroupsRequest) listThingGroupsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListThingGroupsResultJsonUnmarshaller()), createExecutionContext);
                        ListThingGroupsResult listThingGroupsResult = (ListThingGroupsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listThingGroupsResult;
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
                endClientExecution(awsRequestMetrics, listThingGroupsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listThingGroupsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listThingGroupsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.ListThingGroupsForThingRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingGroupsForThingResult listThingGroupsForThing(ListThingGroupsForThingRequest listThingGroupsForThingRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listThingGroupsForThingRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListThingGroupsForThingRequest> marshall = new ListThingGroupsForThingRequestMarshaller().marshall((ListThingGroupsForThingRequest) listThingGroupsForThingRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListThingGroupsForThingResultJsonUnmarshaller()), createExecutionContext);
                        ListThingGroupsForThingResult listThingGroupsForThingResult = (ListThingGroupsForThingResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listThingGroupsForThingResult;
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
                endClientExecution(awsRequestMetrics, listThingGroupsForThingRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listThingGroupsForThingRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listThingGroupsForThingRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.ListThingPrincipalsRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingPrincipalsResult listThingPrincipals(ListThingPrincipalsRequest listThingPrincipalsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listThingPrincipalsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListThingPrincipalsRequest> marshall = new ListThingPrincipalsRequestMarshaller().marshall((ListThingPrincipalsRequest) listThingPrincipalsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListThingPrincipalsResultJsonUnmarshaller()), createExecutionContext);
                        ListThingPrincipalsResult listThingPrincipalsResult = (ListThingPrincipalsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listThingPrincipalsResult;
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
                endClientExecution(awsRequestMetrics, listThingPrincipalsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listThingPrincipalsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listThingPrincipalsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListThingRegistrationTaskReportsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingRegistrationTaskReportsResult listThingRegistrationTaskReports(ListThingRegistrationTaskReportsRequest listThingRegistrationTaskReportsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listThingRegistrationTaskReportsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListThingRegistrationTaskReportsRequest> marshall = new ListThingRegistrationTaskReportsRequestMarshaller().marshall((ListThingRegistrationTaskReportsRequest) listThingRegistrationTaskReportsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListThingRegistrationTaskReportsResultJsonUnmarshaller()), createExecutionContext);
                        ListThingRegistrationTaskReportsResult listThingRegistrationTaskReportsResult = (ListThingRegistrationTaskReportsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listThingRegistrationTaskReportsResult;
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
                endClientExecution(awsRequestMetrics, listThingRegistrationTaskReportsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listThingRegistrationTaskReportsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listThingRegistrationTaskReportsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListThingRegistrationTasksRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingRegistrationTasksResult listThingRegistrationTasks(ListThingRegistrationTasksRequest listThingRegistrationTasksRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listThingRegistrationTasksRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListThingRegistrationTasksRequest> marshall = new ListThingRegistrationTasksRequestMarshaller().marshall((ListThingRegistrationTasksRequest) listThingRegistrationTasksRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListThingRegistrationTasksResultJsonUnmarshaller()), createExecutionContext);
                        ListThingRegistrationTasksResult listThingRegistrationTasksResult = (ListThingRegistrationTasksResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listThingRegistrationTasksResult;
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
                endClientExecution(awsRequestMetrics, listThingRegistrationTasksRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listThingRegistrationTasksRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listThingRegistrationTasksRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListThingTypesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingTypesResult listThingTypes(ListThingTypesRequest listThingTypesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listThingTypesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListThingTypesRequest> marshall = new ListThingTypesRequestMarshaller().marshall((ListThingTypesRequest) listThingTypesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListThingTypesResultJsonUnmarshaller()), createExecutionContext);
                        ListThingTypesResult listThingTypesResult = (ListThingTypesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listThingTypesResult;
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
                endClientExecution(awsRequestMetrics, listThingTypesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listThingTypesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listThingTypesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListThingsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingsResult listThings(ListThingsRequest listThingsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listThingsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListThingsRequest> marshall = new ListThingsRequestMarshaller().marshall((ListThingsRequest) listThingsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListThingsResultJsonUnmarshaller()), createExecutionContext);
                        ListThingsResult listThingsResult = (ListThingsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listThingsResult;
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
                endClientExecution(awsRequestMetrics, listThingsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listThingsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listThingsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListThingsInBillingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingsInBillingGroupResult listThingsInBillingGroup(ListThingsInBillingGroupRequest listThingsInBillingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listThingsInBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListThingsInBillingGroupRequest> marshall = new ListThingsInBillingGroupRequestMarshaller().marshall((ListThingsInBillingGroupRequest) listThingsInBillingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListThingsInBillingGroupResultJsonUnmarshaller()), createExecutionContext);
                        ListThingsInBillingGroupResult listThingsInBillingGroupResult = (ListThingsInBillingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listThingsInBillingGroupResult;
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
                endClientExecution(awsRequestMetrics, listThingsInBillingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listThingsInBillingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listThingsInBillingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListThingsInThingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListThingsInThingGroupResult listThingsInThingGroup(ListThingsInThingGroupRequest listThingsInThingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listThingsInThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListThingsInThingGroupRequest> marshall = new ListThingsInThingGroupRequestMarshaller().marshall((ListThingsInThingGroupRequest) listThingsInThingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListThingsInThingGroupResultJsonUnmarshaller()), createExecutionContext);
                        ListThingsInThingGroupResult listThingsInThingGroupResult = (ListThingsInThingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listThingsInThingGroupResult;
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
                endClientExecution(awsRequestMetrics, listThingsInThingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listThingsInThingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listThingsInThingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListTopicRulesRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListTopicRulesResult listTopicRules(ListTopicRulesRequest listTopicRulesRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listTopicRulesRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListTopicRulesRequest> marshall = new ListTopicRulesRequestMarshaller().marshall((ListTopicRulesRequest) listTopicRulesRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListTopicRulesResultJsonUnmarshaller()), createExecutionContext);
                        ListTopicRulesResult listTopicRulesResult = (ListTopicRulesResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listTopicRulesResult;
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
                endClientExecution(awsRequestMetrics, listTopicRulesRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listTopicRulesRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listTopicRulesRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.ListV2LoggingLevelsRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListV2LoggingLevelsResult listV2LoggingLevels(ListV2LoggingLevelsRequest listV2LoggingLevelsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listV2LoggingLevelsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListV2LoggingLevelsRequest> marshall = new ListV2LoggingLevelsRequestMarshaller().marshall((ListV2LoggingLevelsRequest) listV2LoggingLevelsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListV2LoggingLevelsResultJsonUnmarshaller()), createExecutionContext);
                        ListV2LoggingLevelsResult listV2LoggingLevelsResult = (ListV2LoggingLevelsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listV2LoggingLevelsResult;
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
                endClientExecution(awsRequestMetrics, listV2LoggingLevelsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listV2LoggingLevelsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listV2LoggingLevelsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ListViolationEventsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ListViolationEventsResult listViolationEvents(ListViolationEventsRequest listViolationEventsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(listViolationEventsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ListViolationEventsRequest> marshall = new ListViolationEventsRequestMarshaller().marshall((ListViolationEventsRequest) listViolationEventsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ListViolationEventsResultJsonUnmarshaller()), createExecutionContext);
                        ListViolationEventsResult listViolationEventsResult = (ListViolationEventsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return listViolationEventsResult;
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
                endClientExecution(awsRequestMetrics, listViolationEventsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            listViolationEventsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, listViolationEventsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.RegisterCACertificateRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RegisterCACertificateResult registerCACertificate(RegisterCACertificateRequest registerCACertificateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(registerCACertificateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<RegisterCACertificateRequest> marshall = new RegisterCACertificateRequestMarshaller().marshall((RegisterCACertificateRequest) registerCACertificateRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new RegisterCACertificateResultJsonUnmarshaller()), createExecutionContext);
                        RegisterCACertificateResult registerCACertificateResult = (RegisterCACertificateResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return registerCACertificateResult;
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
                endClientExecution(awsRequestMetrics, registerCACertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            registerCACertificateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, registerCACertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.RegisterCertificateRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RegisterCertificateResult registerCertificate(RegisterCertificateRequest registerCertificateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(registerCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<RegisterCertificateRequest> marshall = new RegisterCertificateRequestMarshaller().marshall((RegisterCertificateRequest) registerCertificateRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new RegisterCertificateResultJsonUnmarshaller()), createExecutionContext);
                        RegisterCertificateResult registerCertificateResult = (RegisterCertificateResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return registerCertificateResult;
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
                endClientExecution(awsRequestMetrics, registerCertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            registerCertificateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, registerCertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.RegisterThingRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RegisterThingResult registerThing(RegisterThingRequest registerThingRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(registerThingRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<RegisterThingRequest> marshall = new RegisterThingRequestMarshaller().marshall((RegisterThingRequest) registerThingRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new RegisterThingResultJsonUnmarshaller()), createExecutionContext);
                        RegisterThingResult registerThingResult = (RegisterThingResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return registerThingResult;
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
                endClientExecution(awsRequestMetrics, registerThingRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            registerThingRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, registerThingRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.RejectCertificateTransferRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void rejectCertificateTransfer(RejectCertificateTransferRequest rejectCertificateTransferRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(rejectCertificateTransferRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<RejectCertificateTransferRequest> marshall = new RejectCertificateTransferRequestMarshaller().marshall((RejectCertificateTransferRequest) rejectCertificateTransferRequest);
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
                endClientExecution(awsRequestMetrics, rejectCertificateTransferRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            rejectCertificateTransferRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, rejectCertificateTransferRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.RemoveThingFromBillingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RemoveThingFromBillingGroupResult removeThingFromBillingGroup(RemoveThingFromBillingGroupRequest removeThingFromBillingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(removeThingFromBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<RemoveThingFromBillingGroupRequest> marshall = new RemoveThingFromBillingGroupRequestMarshaller().marshall((RemoveThingFromBillingGroupRequest) removeThingFromBillingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new RemoveThingFromBillingGroupResultJsonUnmarshaller()), createExecutionContext);
                        RemoveThingFromBillingGroupResult removeThingFromBillingGroupResult = (RemoveThingFromBillingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return removeThingFromBillingGroupResult;
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
                endClientExecution(awsRequestMetrics, removeThingFromBillingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            removeThingFromBillingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, removeThingFromBillingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.RemoveThingFromThingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public RemoveThingFromThingGroupResult removeThingFromThingGroup(RemoveThingFromThingGroupRequest removeThingFromThingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(removeThingFromThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<RemoveThingFromThingGroupRequest> marshall = new RemoveThingFromThingGroupRequestMarshaller().marshall((RemoveThingFromThingGroupRequest) removeThingFromThingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new RemoveThingFromThingGroupResultJsonUnmarshaller()), createExecutionContext);
                        RemoveThingFromThingGroupResult removeThingFromThingGroupResult = (RemoveThingFromThingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return removeThingFromThingGroupResult;
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
                endClientExecution(awsRequestMetrics, removeThingFromThingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            removeThingFromThingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, removeThingFromThingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ReplaceTopicRuleRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void replaceTopicRule(ReplaceTopicRuleRequest replaceTopicRuleRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(replaceTopicRuleRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ReplaceTopicRuleRequest> marshall = new ReplaceTopicRuleRequestMarshaller().marshall((ReplaceTopicRuleRequest) replaceTopicRuleRequest);
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
                endClientExecution(awsRequestMetrics, replaceTopicRuleRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            replaceTopicRuleRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, replaceTopicRuleRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.SearchIndexRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public SearchIndexResult searchIndex(SearchIndexRequest searchIndexRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(searchIndexRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<SearchIndexRequest> marshall = new SearchIndexRequestMarshaller().marshall((SearchIndexRequest) searchIndexRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new SearchIndexResultJsonUnmarshaller()), createExecutionContext);
                        SearchIndexResult searchIndexResult = (SearchIndexResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return searchIndexResult;
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
                endClientExecution(awsRequestMetrics, searchIndexRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            searchIndexRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, searchIndexRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.SetDefaultAuthorizerRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public SetDefaultAuthorizerResult setDefaultAuthorizer(SetDefaultAuthorizerRequest setDefaultAuthorizerRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(setDefaultAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<SetDefaultAuthorizerRequest> marshall = new SetDefaultAuthorizerRequestMarshaller().marshall((SetDefaultAuthorizerRequest) setDefaultAuthorizerRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new SetDefaultAuthorizerResultJsonUnmarshaller()), createExecutionContext);
                        SetDefaultAuthorizerResult setDefaultAuthorizerResult = (SetDefaultAuthorizerResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return setDefaultAuthorizerResult;
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
                endClientExecution(awsRequestMetrics, setDefaultAuthorizerRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            setDefaultAuthorizerRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setDefaultAuthorizerRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.SetDefaultPolicyVersionRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void setDefaultPolicyVersion(SetDefaultPolicyVersionRequest setDefaultPolicyVersionRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(setDefaultPolicyVersionRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<SetDefaultPolicyVersionRequest> marshall = new SetDefaultPolicyVersionRequestMarshaller().marshall((SetDefaultPolicyVersionRequest) setDefaultPolicyVersionRequest);
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
                endClientExecution(awsRequestMetrics, setDefaultPolicyVersionRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            setDefaultPolicyVersionRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setDefaultPolicyVersionRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.SetLoggingOptionsRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void setLoggingOptions(SetLoggingOptionsRequest setLoggingOptionsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(setLoggingOptionsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<SetLoggingOptionsRequest> marshall = new SetLoggingOptionsRequestMarshaller().marshall((SetLoggingOptionsRequest) setLoggingOptionsRequest);
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
                endClientExecution(awsRequestMetrics, setLoggingOptionsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            setLoggingOptionsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setLoggingOptionsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.model.SetV2LoggingLevelRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void setV2LoggingLevel(SetV2LoggingLevelRequest setV2LoggingLevelRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(setV2LoggingLevelRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<SetV2LoggingLevelRequest> marshall = new SetV2LoggingLevelRequestMarshaller().marshall((SetV2LoggingLevelRequest) setV2LoggingLevelRequest);
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
                endClientExecution(awsRequestMetrics, setV2LoggingLevelRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            setV2LoggingLevelRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setV2LoggingLevelRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.SetV2LoggingOptionsRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void setV2LoggingOptions(SetV2LoggingOptionsRequest setV2LoggingOptionsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(setV2LoggingOptionsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<SetV2LoggingOptionsRequest> marshall = new SetV2LoggingOptionsRequestMarshaller().marshall((SetV2LoggingOptionsRequest) setV2LoggingOptionsRequest);
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
                endClientExecution(awsRequestMetrics, setV2LoggingOptionsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            setV2LoggingOptionsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, setV2LoggingOptionsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.StartOnDemandAuditTaskRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public StartOnDemandAuditTaskResult startOnDemandAuditTask(StartOnDemandAuditTaskRequest startOnDemandAuditTaskRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(startOnDemandAuditTaskRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<StartOnDemandAuditTaskRequest> marshall = new StartOnDemandAuditTaskRequestMarshaller().marshall((StartOnDemandAuditTaskRequest) startOnDemandAuditTaskRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new StartOnDemandAuditTaskResultJsonUnmarshaller()), createExecutionContext);
                        StartOnDemandAuditTaskResult startOnDemandAuditTaskResult = (StartOnDemandAuditTaskResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return startOnDemandAuditTaskResult;
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
                endClientExecution(awsRequestMetrics, startOnDemandAuditTaskRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            startOnDemandAuditTaskRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, startOnDemandAuditTaskRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.StartThingRegistrationTaskRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public StartThingRegistrationTaskResult startThingRegistrationTask(StartThingRegistrationTaskRequest startThingRegistrationTaskRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(startThingRegistrationTaskRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<StartThingRegistrationTaskRequest> marshall = new StartThingRegistrationTaskRequestMarshaller().marshall((StartThingRegistrationTaskRequest) startThingRegistrationTaskRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new StartThingRegistrationTaskResultJsonUnmarshaller()), createExecutionContext);
                        StartThingRegistrationTaskResult startThingRegistrationTaskResult = (StartThingRegistrationTaskResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return startThingRegistrationTaskResult;
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
                endClientExecution(awsRequestMetrics, startThingRegistrationTaskRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            startThingRegistrationTaskRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, startThingRegistrationTaskRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.StopThingRegistrationTaskRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public StopThingRegistrationTaskResult stopThingRegistrationTask(StopThingRegistrationTaskRequest stopThingRegistrationTaskRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(stopThingRegistrationTaskRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<StopThingRegistrationTaskRequest> marshall = new StopThingRegistrationTaskRequestMarshaller().marshall((StopThingRegistrationTaskRequest) stopThingRegistrationTaskRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new StopThingRegistrationTaskResultJsonUnmarshaller()), createExecutionContext);
                        StopThingRegistrationTaskResult stopThingRegistrationTaskResult = (StopThingRegistrationTaskResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return stopThingRegistrationTaskResult;
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
                endClientExecution(awsRequestMetrics, stopThingRegistrationTaskRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            stopThingRegistrationTaskRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, stopThingRegistrationTaskRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.TagResourceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public TagResourceResult tagResource(TagResourceRequest tagResourceRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(tagResourceRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<TagResourceRequest> marshall = new TagResourceRequestMarshaller().marshall((TagResourceRequest) tagResourceRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new TagResourceResultJsonUnmarshaller()), createExecutionContext);
                        TagResourceResult tagResourceResult = (TagResourceResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return tagResourceResult;
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
                endClientExecution(awsRequestMetrics, tagResourceRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            tagResourceRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, tagResourceRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.TestAuthorizationRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public TestAuthorizationResult testAuthorization(TestAuthorizationRequest testAuthorizationRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(testAuthorizationRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<TestAuthorizationRequest> marshall = new TestAuthorizationRequestMarshaller().marshall((TestAuthorizationRequest) testAuthorizationRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new TestAuthorizationResultJsonUnmarshaller()), createExecutionContext);
                        TestAuthorizationResult testAuthorizationResult = (TestAuthorizationResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return testAuthorizationResult;
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
                endClientExecution(awsRequestMetrics, testAuthorizationRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            testAuthorizationRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, testAuthorizationRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.TestInvokeAuthorizerRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public TestInvokeAuthorizerResult testInvokeAuthorizer(TestInvokeAuthorizerRequest testInvokeAuthorizerRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(testInvokeAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<TestInvokeAuthorizerRequest> marshall = new TestInvokeAuthorizerRequestMarshaller().marshall((TestInvokeAuthorizerRequest) testInvokeAuthorizerRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new TestInvokeAuthorizerResultJsonUnmarshaller()), createExecutionContext);
                        TestInvokeAuthorizerResult testInvokeAuthorizerResult = (TestInvokeAuthorizerResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return testInvokeAuthorizerResult;
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
                endClientExecution(awsRequestMetrics, testInvokeAuthorizerRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            testInvokeAuthorizerRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, testInvokeAuthorizerRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.TransferCertificateRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public TransferCertificateResult transferCertificate(TransferCertificateRequest transferCertificateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(transferCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<TransferCertificateRequest> marshall = new TransferCertificateRequestMarshaller().marshall((TransferCertificateRequest) transferCertificateRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new TransferCertificateResultJsonUnmarshaller()), createExecutionContext);
                        TransferCertificateResult transferCertificateResult = (TransferCertificateResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return transferCertificateResult;
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
                endClientExecution(awsRequestMetrics, transferCertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            transferCertificateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, transferCertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UntagResourceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UntagResourceResult untagResource(UntagResourceRequest untagResourceRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(untagResourceRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UntagResourceRequest> marshall = new UntagResourceRequestMarshaller().marshall((UntagResourceRequest) untagResourceRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UntagResourceResultJsonUnmarshaller()), createExecutionContext);
                        UntagResourceResult untagResourceResult = (UntagResourceResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return untagResourceResult;
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
                endClientExecution(awsRequestMetrics, untagResourceRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            untagResourceRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, untagResourceRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateAccountAuditConfigurationRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateAccountAuditConfigurationResult updateAccountAuditConfiguration(UpdateAccountAuditConfigurationRequest updateAccountAuditConfigurationRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateAccountAuditConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateAccountAuditConfigurationRequest> marshall = new UpdateAccountAuditConfigurationRequestMarshaller().marshall((UpdateAccountAuditConfigurationRequest) updateAccountAuditConfigurationRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateAccountAuditConfigurationResultJsonUnmarshaller()), createExecutionContext);
                        UpdateAccountAuditConfigurationResult updateAccountAuditConfigurationResult = (UpdateAccountAuditConfigurationResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateAccountAuditConfigurationResult;
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
                endClientExecution(awsRequestMetrics, updateAccountAuditConfigurationRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateAccountAuditConfigurationRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateAccountAuditConfigurationRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateAuthorizerRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateAuthorizerResult updateAuthorizer(UpdateAuthorizerRequest updateAuthorizerRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateAuthorizerRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateAuthorizerRequest> marshall = new UpdateAuthorizerRequestMarshaller().marshall((UpdateAuthorizerRequest) updateAuthorizerRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateAuthorizerResultJsonUnmarshaller()), createExecutionContext);
                        UpdateAuthorizerResult updateAuthorizerResult = (UpdateAuthorizerResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateAuthorizerResult;
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
                endClientExecution(awsRequestMetrics, updateAuthorizerRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateAuthorizerRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateAuthorizerRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateBillingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateBillingGroupResult updateBillingGroup(UpdateBillingGroupRequest updateBillingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateBillingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateBillingGroupRequest> marshall = new UpdateBillingGroupRequestMarshaller().marshall((UpdateBillingGroupRequest) updateBillingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateBillingGroupResultJsonUnmarshaller()), createExecutionContext);
                        UpdateBillingGroupResult updateBillingGroupResult = (UpdateBillingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateBillingGroupResult;
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
                endClientExecution(awsRequestMetrics, updateBillingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateBillingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateBillingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateCACertificateRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void updateCACertificate(UpdateCACertificateRequest updateCACertificateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateCACertificateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateCACertificateRequest> marshall = new UpdateCACertificateRequestMarshaller().marshall((UpdateCACertificateRequest) updateCACertificateRequest);
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
                endClientExecution(awsRequestMetrics, updateCACertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateCACertificateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateCACertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.model.UpdateCertificateRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void updateCertificate(UpdateCertificateRequest updateCertificateRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateCertificateRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateCertificateRequest> marshall = new UpdateCertificateRequestMarshaller().marshall((UpdateCertificateRequest) updateCertificateRequest);
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
                endClientExecution(awsRequestMetrics, updateCertificateRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateCertificateRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateCertificateRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateDynamicThingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateDynamicThingGroupResult updateDynamicThingGroup(UpdateDynamicThingGroupRequest updateDynamicThingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateDynamicThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateDynamicThingGroupRequest> marshall = new UpdateDynamicThingGroupRequestMarshaller().marshall((UpdateDynamicThingGroupRequest) updateDynamicThingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateDynamicThingGroupResultJsonUnmarshaller()), createExecutionContext);
                        UpdateDynamicThingGroupResult updateDynamicThingGroupResult = (UpdateDynamicThingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateDynamicThingGroupResult;
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
                endClientExecution(awsRequestMetrics, updateDynamicThingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateDynamicThingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateDynamicThingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateEventConfigurationsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateEventConfigurationsResult updateEventConfigurations(UpdateEventConfigurationsRequest updateEventConfigurationsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateEventConfigurationsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateEventConfigurationsRequest> marshall = new UpdateEventConfigurationsRequestMarshaller().marshall((UpdateEventConfigurationsRequest) updateEventConfigurationsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateEventConfigurationsResultJsonUnmarshaller()), createExecutionContext);
                        UpdateEventConfigurationsResult updateEventConfigurationsResult = (UpdateEventConfigurationsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateEventConfigurationsResult;
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
                endClientExecution(awsRequestMetrics, updateEventConfigurationsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateEventConfigurationsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateEventConfigurationsRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateIndexingConfigurationRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateIndexingConfigurationResult updateIndexingConfiguration(UpdateIndexingConfigurationRequest updateIndexingConfigurationRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateIndexingConfigurationRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateIndexingConfigurationRequest> marshall = new UpdateIndexingConfigurationRequestMarshaller().marshall((UpdateIndexingConfigurationRequest) updateIndexingConfigurationRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateIndexingConfigurationResultJsonUnmarshaller()), createExecutionContext);
                        UpdateIndexingConfigurationResult updateIndexingConfigurationResult = (UpdateIndexingConfigurationResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateIndexingConfigurationResult;
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
                endClientExecution(awsRequestMetrics, updateIndexingConfigurationRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateIndexingConfigurationRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateIndexingConfigurationRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateJobRequest] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public void updateJob(UpdateJobRequest updateJobRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateJobRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateJobRequest> marshall = new UpdateJobRequestMarshaller().marshall((UpdateJobRequest) updateJobRequest);
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
                endClientExecution(awsRequestMetrics, updateJobRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateJobRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateJobRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateRoleAliasRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateRoleAliasResult updateRoleAlias(UpdateRoleAliasRequest updateRoleAliasRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateRoleAliasRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateRoleAliasRequest> marshall = new UpdateRoleAliasRequestMarshaller().marshall((UpdateRoleAliasRequest) updateRoleAliasRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateRoleAliasResultJsonUnmarshaller()), createExecutionContext);
                        UpdateRoleAliasResult updateRoleAliasResult = (UpdateRoleAliasResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateRoleAliasResult;
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
                endClientExecution(awsRequestMetrics, updateRoleAliasRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateRoleAliasRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateRoleAliasRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateScheduledAuditRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateScheduledAuditResult updateScheduledAudit(UpdateScheduledAuditRequest updateScheduledAuditRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateScheduledAuditRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateScheduledAuditRequest> marshall = new UpdateScheduledAuditRequestMarshaller().marshall((UpdateScheduledAuditRequest) updateScheduledAuditRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateScheduledAuditResultJsonUnmarshaller()), createExecutionContext);
                        UpdateScheduledAuditResult updateScheduledAuditResult = (UpdateScheduledAuditResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateScheduledAuditResult;
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
                endClientExecution(awsRequestMetrics, updateScheduledAuditRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateScheduledAuditRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateScheduledAuditRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateSecurityProfileRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateSecurityProfileResult updateSecurityProfile(UpdateSecurityProfileRequest updateSecurityProfileRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateSecurityProfileRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateSecurityProfileRequest> marshall = new UpdateSecurityProfileRequestMarshaller().marshall((UpdateSecurityProfileRequest) updateSecurityProfileRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateSecurityProfileResultJsonUnmarshaller()), createExecutionContext);
                        UpdateSecurityProfileResult updateSecurityProfileResult = (UpdateSecurityProfileResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateSecurityProfileResult;
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
                endClientExecution(awsRequestMetrics, updateSecurityProfileRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateSecurityProfileRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateSecurityProfileRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.UpdateStreamRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateStreamResult updateStream(UpdateStreamRequest updateStreamRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateStreamRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateStreamRequest> marshall = new UpdateStreamRequestMarshaller().marshall((UpdateStreamRequest) updateStreamRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateStreamResultJsonUnmarshaller()), createExecutionContext);
                        UpdateStreamResult updateStreamResult = (UpdateStreamResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateStreamResult;
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
                endClientExecution(awsRequestMetrics, updateStreamRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateStreamRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateStreamRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.services.iot.model.UpdateThingRequest, com.amazonaws.AmazonWebServiceRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateThingResult updateThing(UpdateThingRequest updateThingRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateThingRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateThingRequest> marshall = new UpdateThingRequestMarshaller().marshall((UpdateThingRequest) updateThingRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateThingResultJsonUnmarshaller()), createExecutionContext);
                        UpdateThingResult updateThingResult = (UpdateThingResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateThingResult;
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
                endClientExecution(awsRequestMetrics, updateThingRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateThingRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateThingRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateThingGroupRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateThingGroupResult updateThingGroup(UpdateThingGroupRequest updateThingGroupRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateThingGroupRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateThingGroupRequest> marshall = new UpdateThingGroupRequestMarshaller().marshall((UpdateThingGroupRequest) updateThingGroupRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateThingGroupResultJsonUnmarshaller()), createExecutionContext);
                        UpdateThingGroupResult updateThingGroupResult = (UpdateThingGroupResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateThingGroupResult;
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
                endClientExecution(awsRequestMetrics, updateThingGroupRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateThingGroupRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateThingGroupRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.UpdateThingGroupsForThingRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public UpdateThingGroupsForThingResult updateThingGroupsForThing(UpdateThingGroupsForThingRequest updateThingGroupsForThingRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(updateThingGroupsForThingRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<UpdateThingGroupsForThingRequest> marshall = new UpdateThingGroupsForThingRequestMarshaller().marshall((UpdateThingGroupsForThingRequest) updateThingGroupsForThingRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new UpdateThingGroupsForThingResultJsonUnmarshaller()), createExecutionContext);
                        UpdateThingGroupsForThingResult updateThingGroupsForThingResult = (UpdateThingGroupsForThingResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return updateThingGroupsForThingResult;
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
                endClientExecution(awsRequestMetrics, updateThingGroupsForThingRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            updateThingGroupsForThingRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, updateThingGroupsForThingRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazonaws.services.iot.AWSIotClient, com.amazonaws.AmazonWebServiceClient] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iot.model.ValidateSecurityProfileBehaviorsRequest] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iot.AWSIot
    public ValidateSecurityProfileBehaviorsResult validateSecurityProfileBehaviors(ValidateSecurityProfileBehaviorsRequest validateSecurityProfileBehaviorsRequest) throws AmazonServiceException, AmazonClientException {
        ExecutionContext createExecutionContext = createExecutionContext(validateSecurityProfileBehaviorsRequest);
        AWSRequestMetrics awsRequestMetrics = createExecutionContext.getAwsRequestMetrics();
        awsRequestMetrics.startEvent(AWSRequestMetrics.Field.ClientExecuteTime);
        try {
            try {
                awsRequestMetrics.startEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                try {
                    Request<ValidateSecurityProfileBehaviorsRequest> marshall = new ValidateSecurityProfileBehaviorsRequestMarshaller().marshall((ValidateSecurityProfileBehaviorsRequest) validateSecurityProfileBehaviorsRequest);
                    try {
                        marshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        Response invoke = invoke(marshall, new JsonResponseHandler(new ValidateSecurityProfileBehaviorsResultJsonUnmarshaller()), createExecutionContext);
                        ValidateSecurityProfileBehaviorsResult validateSecurityProfileBehaviorsResult = (ValidateSecurityProfileBehaviorsResult) invoke.getAwsResponse();
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                        endClientExecution(awsRequestMetrics, marshall, invoke, true);
                        return validateSecurityProfileBehaviorsResult;
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
                endClientExecution(awsRequestMetrics, validateSecurityProfileBehaviorsRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            validateSecurityProfileBehaviorsRequest = 0;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, validateSecurityProfileBehaviorsRequest, null, true);
            throw th;
        }
    }

    @Deprecated
    public AWSIotClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AWSIotClient(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public AWSIotClient(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(aWSCredentials), clientConfiguration);
    }

    public AWSIotClient(AWSCredentialsProvider aWSCredentialsProvider) {
        this(aWSCredentialsProvider, new ClientConfiguration());
    }

    public AWSIotClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(aWSCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AWSIotClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    public AWSIotClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }
}
