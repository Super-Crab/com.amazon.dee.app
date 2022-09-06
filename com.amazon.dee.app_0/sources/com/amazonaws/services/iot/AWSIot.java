package com.amazonaws.services.iot;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
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
/* loaded from: classes13.dex */
public interface AWSIot {
    void acceptCertificateTransfer(AcceptCertificateTransferRequest acceptCertificateTransferRequest) throws AmazonClientException, AmazonServiceException;

    AddThingToBillingGroupResult addThingToBillingGroup(AddThingToBillingGroupRequest addThingToBillingGroupRequest) throws AmazonClientException, AmazonServiceException;

    AddThingToThingGroupResult addThingToThingGroup(AddThingToThingGroupRequest addThingToThingGroupRequest) throws AmazonClientException, AmazonServiceException;

    AssociateTargetsWithJobResult associateTargetsWithJob(AssociateTargetsWithJobRequest associateTargetsWithJobRequest) throws AmazonClientException, AmazonServiceException;

    void attachPolicy(AttachPolicyRequest attachPolicyRequest) throws AmazonClientException, AmazonServiceException;

    @Deprecated
    void attachPrincipalPolicy(AttachPrincipalPolicyRequest attachPrincipalPolicyRequest) throws AmazonClientException, AmazonServiceException;

    AttachSecurityProfileResult attachSecurityProfile(AttachSecurityProfileRequest attachSecurityProfileRequest) throws AmazonClientException, AmazonServiceException;

    AttachThingPrincipalResult attachThingPrincipal(AttachThingPrincipalRequest attachThingPrincipalRequest) throws AmazonClientException, AmazonServiceException;

    CancelAuditTaskResult cancelAuditTask(CancelAuditTaskRequest cancelAuditTaskRequest) throws AmazonClientException, AmazonServiceException;

    void cancelCertificateTransfer(CancelCertificateTransferRequest cancelCertificateTransferRequest) throws AmazonClientException, AmazonServiceException;

    CancelJobResult cancelJob(CancelJobRequest cancelJobRequest) throws AmazonClientException, AmazonServiceException;

    void cancelJobExecution(CancelJobExecutionRequest cancelJobExecutionRequest) throws AmazonClientException, AmazonServiceException;

    ClearDefaultAuthorizerResult clearDefaultAuthorizer(ClearDefaultAuthorizerRequest clearDefaultAuthorizerRequest) throws AmazonClientException, AmazonServiceException;

    CreateAuthorizerResult createAuthorizer(CreateAuthorizerRequest createAuthorizerRequest) throws AmazonClientException, AmazonServiceException;

    CreateBillingGroupResult createBillingGroup(CreateBillingGroupRequest createBillingGroupRequest) throws AmazonClientException, AmazonServiceException;

    CreateCertificateFromCsrResult createCertificateFromCsr(CreateCertificateFromCsrRequest createCertificateFromCsrRequest) throws AmazonClientException, AmazonServiceException;

    CreateDynamicThingGroupResult createDynamicThingGroup(CreateDynamicThingGroupRequest createDynamicThingGroupRequest) throws AmazonClientException, AmazonServiceException;

    CreateJobResult createJob(CreateJobRequest createJobRequest) throws AmazonClientException, AmazonServiceException;

    CreateKeysAndCertificateResult createKeysAndCertificate(CreateKeysAndCertificateRequest createKeysAndCertificateRequest) throws AmazonClientException, AmazonServiceException;

    CreateOTAUpdateResult createOTAUpdate(CreateOTAUpdateRequest createOTAUpdateRequest) throws AmazonClientException, AmazonServiceException;

    CreatePolicyResult createPolicy(CreatePolicyRequest createPolicyRequest) throws AmazonClientException, AmazonServiceException;

    CreatePolicyVersionResult createPolicyVersion(CreatePolicyVersionRequest createPolicyVersionRequest) throws AmazonClientException, AmazonServiceException;

    CreateRoleAliasResult createRoleAlias(CreateRoleAliasRequest createRoleAliasRequest) throws AmazonClientException, AmazonServiceException;

    CreateScheduledAuditResult createScheduledAudit(CreateScheduledAuditRequest createScheduledAuditRequest) throws AmazonClientException, AmazonServiceException;

    CreateSecurityProfileResult createSecurityProfile(CreateSecurityProfileRequest createSecurityProfileRequest) throws AmazonClientException, AmazonServiceException;

    CreateStreamResult createStream(CreateStreamRequest createStreamRequest) throws AmazonClientException, AmazonServiceException;

    CreateThingResult createThing(CreateThingRequest createThingRequest) throws AmazonClientException, AmazonServiceException;

    CreateThingGroupResult createThingGroup(CreateThingGroupRequest createThingGroupRequest) throws AmazonClientException, AmazonServiceException;

    CreateThingTypeResult createThingType(CreateThingTypeRequest createThingTypeRequest) throws AmazonClientException, AmazonServiceException;

    void createTopicRule(CreateTopicRuleRequest createTopicRuleRequest) throws AmazonClientException, AmazonServiceException;

    DeleteAccountAuditConfigurationResult deleteAccountAuditConfiguration(DeleteAccountAuditConfigurationRequest deleteAccountAuditConfigurationRequest) throws AmazonClientException, AmazonServiceException;

    DeleteAuthorizerResult deleteAuthorizer(DeleteAuthorizerRequest deleteAuthorizerRequest) throws AmazonClientException, AmazonServiceException;

    DeleteBillingGroupResult deleteBillingGroup(DeleteBillingGroupRequest deleteBillingGroupRequest) throws AmazonClientException, AmazonServiceException;

    DeleteCACertificateResult deleteCACertificate(DeleteCACertificateRequest deleteCACertificateRequest) throws AmazonClientException, AmazonServiceException;

    void deleteCertificate(DeleteCertificateRequest deleteCertificateRequest) throws AmazonClientException, AmazonServiceException;

    DeleteDynamicThingGroupResult deleteDynamicThingGroup(DeleteDynamicThingGroupRequest deleteDynamicThingGroupRequest) throws AmazonClientException, AmazonServiceException;

    void deleteJob(DeleteJobRequest deleteJobRequest) throws AmazonClientException, AmazonServiceException;

    void deleteJobExecution(DeleteJobExecutionRequest deleteJobExecutionRequest) throws AmazonClientException, AmazonServiceException;

    DeleteOTAUpdateResult deleteOTAUpdate(DeleteOTAUpdateRequest deleteOTAUpdateRequest) throws AmazonClientException, AmazonServiceException;

    void deletePolicy(DeletePolicyRequest deletePolicyRequest) throws AmazonClientException, AmazonServiceException;

    void deletePolicyVersion(DeletePolicyVersionRequest deletePolicyVersionRequest) throws AmazonClientException, AmazonServiceException;

    DeleteRegistrationCodeResult deleteRegistrationCode(DeleteRegistrationCodeRequest deleteRegistrationCodeRequest) throws AmazonClientException, AmazonServiceException;

    DeleteRoleAliasResult deleteRoleAlias(DeleteRoleAliasRequest deleteRoleAliasRequest) throws AmazonClientException, AmazonServiceException;

    DeleteScheduledAuditResult deleteScheduledAudit(DeleteScheduledAuditRequest deleteScheduledAuditRequest) throws AmazonClientException, AmazonServiceException;

    DeleteSecurityProfileResult deleteSecurityProfile(DeleteSecurityProfileRequest deleteSecurityProfileRequest) throws AmazonClientException, AmazonServiceException;

    DeleteStreamResult deleteStream(DeleteStreamRequest deleteStreamRequest) throws AmazonClientException, AmazonServiceException;

    DeleteThingResult deleteThing(DeleteThingRequest deleteThingRequest) throws AmazonClientException, AmazonServiceException;

    DeleteThingGroupResult deleteThingGroup(DeleteThingGroupRequest deleteThingGroupRequest) throws AmazonClientException, AmazonServiceException;

    DeleteThingTypeResult deleteThingType(DeleteThingTypeRequest deleteThingTypeRequest) throws AmazonClientException, AmazonServiceException;

    void deleteTopicRule(DeleteTopicRuleRequest deleteTopicRuleRequest) throws AmazonClientException, AmazonServiceException;

    void deleteV2LoggingLevel(DeleteV2LoggingLevelRequest deleteV2LoggingLevelRequest) throws AmazonClientException, AmazonServiceException;

    DeprecateThingTypeResult deprecateThingType(DeprecateThingTypeRequest deprecateThingTypeRequest) throws AmazonClientException, AmazonServiceException;

    DescribeAccountAuditConfigurationResult describeAccountAuditConfiguration(DescribeAccountAuditConfigurationRequest describeAccountAuditConfigurationRequest) throws AmazonClientException, AmazonServiceException;

    DescribeAuditTaskResult describeAuditTask(DescribeAuditTaskRequest describeAuditTaskRequest) throws AmazonClientException, AmazonServiceException;

    DescribeAuthorizerResult describeAuthorizer(DescribeAuthorizerRequest describeAuthorizerRequest) throws AmazonClientException, AmazonServiceException;

    DescribeBillingGroupResult describeBillingGroup(DescribeBillingGroupRequest describeBillingGroupRequest) throws AmazonClientException, AmazonServiceException;

    DescribeCACertificateResult describeCACertificate(DescribeCACertificateRequest describeCACertificateRequest) throws AmazonClientException, AmazonServiceException;

    DescribeCertificateResult describeCertificate(DescribeCertificateRequest describeCertificateRequest) throws AmazonClientException, AmazonServiceException;

    DescribeDefaultAuthorizerResult describeDefaultAuthorizer(DescribeDefaultAuthorizerRequest describeDefaultAuthorizerRequest) throws AmazonClientException, AmazonServiceException;

    DescribeEndpointResult describeEndpoint(DescribeEndpointRequest describeEndpointRequest) throws AmazonClientException, AmazonServiceException;

    DescribeEventConfigurationsResult describeEventConfigurations(DescribeEventConfigurationsRequest describeEventConfigurationsRequest) throws AmazonClientException, AmazonServiceException;

    DescribeIndexResult describeIndex(DescribeIndexRequest describeIndexRequest) throws AmazonClientException, AmazonServiceException;

    DescribeJobResult describeJob(DescribeJobRequest describeJobRequest) throws AmazonClientException, AmazonServiceException;

    DescribeJobExecutionResult describeJobExecution(DescribeJobExecutionRequest describeJobExecutionRequest) throws AmazonClientException, AmazonServiceException;

    DescribeRoleAliasResult describeRoleAlias(DescribeRoleAliasRequest describeRoleAliasRequest) throws AmazonClientException, AmazonServiceException;

    DescribeScheduledAuditResult describeScheduledAudit(DescribeScheduledAuditRequest describeScheduledAuditRequest) throws AmazonClientException, AmazonServiceException;

    DescribeSecurityProfileResult describeSecurityProfile(DescribeSecurityProfileRequest describeSecurityProfileRequest) throws AmazonClientException, AmazonServiceException;

    DescribeStreamResult describeStream(DescribeStreamRequest describeStreamRequest) throws AmazonClientException, AmazonServiceException;

    DescribeThingResult describeThing(DescribeThingRequest describeThingRequest) throws AmazonClientException, AmazonServiceException;

    DescribeThingGroupResult describeThingGroup(DescribeThingGroupRequest describeThingGroupRequest) throws AmazonClientException, AmazonServiceException;

    DescribeThingRegistrationTaskResult describeThingRegistrationTask(DescribeThingRegistrationTaskRequest describeThingRegistrationTaskRequest) throws AmazonClientException, AmazonServiceException;

    DescribeThingTypeResult describeThingType(DescribeThingTypeRequest describeThingTypeRequest) throws AmazonClientException, AmazonServiceException;

    void detachPolicy(DetachPolicyRequest detachPolicyRequest) throws AmazonClientException, AmazonServiceException;

    @Deprecated
    void detachPrincipalPolicy(DetachPrincipalPolicyRequest detachPrincipalPolicyRequest) throws AmazonClientException, AmazonServiceException;

    DetachSecurityProfileResult detachSecurityProfile(DetachSecurityProfileRequest detachSecurityProfileRequest) throws AmazonClientException, AmazonServiceException;

    DetachThingPrincipalResult detachThingPrincipal(DetachThingPrincipalRequest detachThingPrincipalRequest) throws AmazonClientException, AmazonServiceException;

    void disableTopicRule(DisableTopicRuleRequest disableTopicRuleRequest) throws AmazonClientException, AmazonServiceException;

    void enableTopicRule(EnableTopicRuleRequest enableTopicRuleRequest) throws AmazonClientException, AmazonServiceException;

    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest);

    GetEffectivePoliciesResult getEffectivePolicies(GetEffectivePoliciesRequest getEffectivePoliciesRequest) throws AmazonClientException, AmazonServiceException;

    GetIndexingConfigurationResult getIndexingConfiguration(GetIndexingConfigurationRequest getIndexingConfigurationRequest) throws AmazonClientException, AmazonServiceException;

    GetJobDocumentResult getJobDocument(GetJobDocumentRequest getJobDocumentRequest) throws AmazonClientException, AmazonServiceException;

    GetLoggingOptionsResult getLoggingOptions(GetLoggingOptionsRequest getLoggingOptionsRequest) throws AmazonClientException, AmazonServiceException;

    GetOTAUpdateResult getOTAUpdate(GetOTAUpdateRequest getOTAUpdateRequest) throws AmazonClientException, AmazonServiceException;

    GetPolicyResult getPolicy(GetPolicyRequest getPolicyRequest) throws AmazonClientException, AmazonServiceException;

    GetPolicyVersionResult getPolicyVersion(GetPolicyVersionRequest getPolicyVersionRequest) throws AmazonClientException, AmazonServiceException;

    GetRegistrationCodeResult getRegistrationCode(GetRegistrationCodeRequest getRegistrationCodeRequest) throws AmazonClientException, AmazonServiceException;

    GetTopicRuleResult getTopicRule(GetTopicRuleRequest getTopicRuleRequest) throws AmazonClientException, AmazonServiceException;

    GetV2LoggingOptionsResult getV2LoggingOptions(GetV2LoggingOptionsRequest getV2LoggingOptionsRequest) throws AmazonClientException, AmazonServiceException;

    ListActiveViolationsResult listActiveViolations(ListActiveViolationsRequest listActiveViolationsRequest) throws AmazonClientException, AmazonServiceException;

    ListAttachedPoliciesResult listAttachedPolicies(ListAttachedPoliciesRequest listAttachedPoliciesRequest) throws AmazonClientException, AmazonServiceException;

    ListAuditFindingsResult listAuditFindings(ListAuditFindingsRequest listAuditFindingsRequest) throws AmazonClientException, AmazonServiceException;

    ListAuditTasksResult listAuditTasks(ListAuditTasksRequest listAuditTasksRequest) throws AmazonClientException, AmazonServiceException;

    ListAuthorizersResult listAuthorizers(ListAuthorizersRequest listAuthorizersRequest) throws AmazonClientException, AmazonServiceException;

    ListBillingGroupsResult listBillingGroups(ListBillingGroupsRequest listBillingGroupsRequest) throws AmazonClientException, AmazonServiceException;

    ListCACertificatesResult listCACertificates(ListCACertificatesRequest listCACertificatesRequest) throws AmazonClientException, AmazonServiceException;

    ListCertificatesResult listCertificates(ListCertificatesRequest listCertificatesRequest) throws AmazonClientException, AmazonServiceException;

    ListCertificatesByCAResult listCertificatesByCA(ListCertificatesByCARequest listCertificatesByCARequest) throws AmazonClientException, AmazonServiceException;

    ListIndicesResult listIndices(ListIndicesRequest listIndicesRequest) throws AmazonClientException, AmazonServiceException;

    ListJobExecutionsForJobResult listJobExecutionsForJob(ListJobExecutionsForJobRequest listJobExecutionsForJobRequest) throws AmazonClientException, AmazonServiceException;

    ListJobExecutionsForThingResult listJobExecutionsForThing(ListJobExecutionsForThingRequest listJobExecutionsForThingRequest) throws AmazonClientException, AmazonServiceException;

    ListJobsResult listJobs(ListJobsRequest listJobsRequest) throws AmazonClientException, AmazonServiceException;

    ListOTAUpdatesResult listOTAUpdates(ListOTAUpdatesRequest listOTAUpdatesRequest) throws AmazonClientException, AmazonServiceException;

    ListOutgoingCertificatesResult listOutgoingCertificates(ListOutgoingCertificatesRequest listOutgoingCertificatesRequest) throws AmazonClientException, AmazonServiceException;

    ListPoliciesResult listPolicies(ListPoliciesRequest listPoliciesRequest) throws AmazonClientException, AmazonServiceException;

    @Deprecated
    ListPolicyPrincipalsResult listPolicyPrincipals(ListPolicyPrincipalsRequest listPolicyPrincipalsRequest) throws AmazonClientException, AmazonServiceException;

    ListPolicyVersionsResult listPolicyVersions(ListPolicyVersionsRequest listPolicyVersionsRequest) throws AmazonClientException, AmazonServiceException;

    @Deprecated
    ListPrincipalPoliciesResult listPrincipalPolicies(ListPrincipalPoliciesRequest listPrincipalPoliciesRequest) throws AmazonClientException, AmazonServiceException;

    ListPrincipalThingsResult listPrincipalThings(ListPrincipalThingsRequest listPrincipalThingsRequest) throws AmazonClientException, AmazonServiceException;

    ListRoleAliasesResult listRoleAliases(ListRoleAliasesRequest listRoleAliasesRequest) throws AmazonClientException, AmazonServiceException;

    ListScheduledAuditsResult listScheduledAudits(ListScheduledAuditsRequest listScheduledAuditsRequest) throws AmazonClientException, AmazonServiceException;

    ListSecurityProfilesResult listSecurityProfiles(ListSecurityProfilesRequest listSecurityProfilesRequest) throws AmazonClientException, AmazonServiceException;

    ListSecurityProfilesForTargetResult listSecurityProfilesForTarget(ListSecurityProfilesForTargetRequest listSecurityProfilesForTargetRequest) throws AmazonClientException, AmazonServiceException;

    ListStreamsResult listStreams(ListStreamsRequest listStreamsRequest) throws AmazonClientException, AmazonServiceException;

    ListTagsForResourceResult listTagsForResource(ListTagsForResourceRequest listTagsForResourceRequest) throws AmazonClientException, AmazonServiceException;

    ListTargetsForPolicyResult listTargetsForPolicy(ListTargetsForPolicyRequest listTargetsForPolicyRequest) throws AmazonClientException, AmazonServiceException;

    ListTargetsForSecurityProfileResult listTargetsForSecurityProfile(ListTargetsForSecurityProfileRequest listTargetsForSecurityProfileRequest) throws AmazonClientException, AmazonServiceException;

    ListThingGroupsResult listThingGroups(ListThingGroupsRequest listThingGroupsRequest) throws AmazonClientException, AmazonServiceException;

    ListThingGroupsForThingResult listThingGroupsForThing(ListThingGroupsForThingRequest listThingGroupsForThingRequest) throws AmazonClientException, AmazonServiceException;

    ListThingPrincipalsResult listThingPrincipals(ListThingPrincipalsRequest listThingPrincipalsRequest) throws AmazonClientException, AmazonServiceException;

    ListThingRegistrationTaskReportsResult listThingRegistrationTaskReports(ListThingRegistrationTaskReportsRequest listThingRegistrationTaskReportsRequest) throws AmazonClientException, AmazonServiceException;

    ListThingRegistrationTasksResult listThingRegistrationTasks(ListThingRegistrationTasksRequest listThingRegistrationTasksRequest) throws AmazonClientException, AmazonServiceException;

    ListThingTypesResult listThingTypes(ListThingTypesRequest listThingTypesRequest) throws AmazonClientException, AmazonServiceException;

    ListThingsResult listThings(ListThingsRequest listThingsRequest) throws AmazonClientException, AmazonServiceException;

    ListThingsInBillingGroupResult listThingsInBillingGroup(ListThingsInBillingGroupRequest listThingsInBillingGroupRequest) throws AmazonClientException, AmazonServiceException;

    ListThingsInThingGroupResult listThingsInThingGroup(ListThingsInThingGroupRequest listThingsInThingGroupRequest) throws AmazonClientException, AmazonServiceException;

    ListTopicRulesResult listTopicRules(ListTopicRulesRequest listTopicRulesRequest) throws AmazonClientException, AmazonServiceException;

    ListV2LoggingLevelsResult listV2LoggingLevels(ListV2LoggingLevelsRequest listV2LoggingLevelsRequest) throws AmazonClientException, AmazonServiceException;

    ListViolationEventsResult listViolationEvents(ListViolationEventsRequest listViolationEventsRequest) throws AmazonClientException, AmazonServiceException;

    RegisterCACertificateResult registerCACertificate(RegisterCACertificateRequest registerCACertificateRequest) throws AmazonClientException, AmazonServiceException;

    RegisterCertificateResult registerCertificate(RegisterCertificateRequest registerCertificateRequest) throws AmazonClientException, AmazonServiceException;

    RegisterThingResult registerThing(RegisterThingRequest registerThingRequest) throws AmazonClientException, AmazonServiceException;

    void rejectCertificateTransfer(RejectCertificateTransferRequest rejectCertificateTransferRequest) throws AmazonClientException, AmazonServiceException;

    RemoveThingFromBillingGroupResult removeThingFromBillingGroup(RemoveThingFromBillingGroupRequest removeThingFromBillingGroupRequest) throws AmazonClientException, AmazonServiceException;

    RemoveThingFromThingGroupResult removeThingFromThingGroup(RemoveThingFromThingGroupRequest removeThingFromThingGroupRequest) throws AmazonClientException, AmazonServiceException;

    void replaceTopicRule(ReplaceTopicRuleRequest replaceTopicRuleRequest) throws AmazonClientException, AmazonServiceException;

    SearchIndexResult searchIndex(SearchIndexRequest searchIndexRequest) throws AmazonClientException, AmazonServiceException;

    SetDefaultAuthorizerResult setDefaultAuthorizer(SetDefaultAuthorizerRequest setDefaultAuthorizerRequest) throws AmazonClientException, AmazonServiceException;

    void setDefaultPolicyVersion(SetDefaultPolicyVersionRequest setDefaultPolicyVersionRequest) throws AmazonClientException, AmazonServiceException;

    void setEndpoint(String str) throws IllegalArgumentException;

    void setLoggingOptions(SetLoggingOptionsRequest setLoggingOptionsRequest) throws AmazonClientException, AmazonServiceException;

    void setRegion(Region region) throws IllegalArgumentException;

    void setV2LoggingLevel(SetV2LoggingLevelRequest setV2LoggingLevelRequest) throws AmazonClientException, AmazonServiceException;

    void setV2LoggingOptions(SetV2LoggingOptionsRequest setV2LoggingOptionsRequest) throws AmazonClientException, AmazonServiceException;

    void shutdown();

    StartOnDemandAuditTaskResult startOnDemandAuditTask(StartOnDemandAuditTaskRequest startOnDemandAuditTaskRequest) throws AmazonClientException, AmazonServiceException;

    StartThingRegistrationTaskResult startThingRegistrationTask(StartThingRegistrationTaskRequest startThingRegistrationTaskRequest) throws AmazonClientException, AmazonServiceException;

    StopThingRegistrationTaskResult stopThingRegistrationTask(StopThingRegistrationTaskRequest stopThingRegistrationTaskRequest) throws AmazonClientException, AmazonServiceException;

    TagResourceResult tagResource(TagResourceRequest tagResourceRequest) throws AmazonClientException, AmazonServiceException;

    TestAuthorizationResult testAuthorization(TestAuthorizationRequest testAuthorizationRequest) throws AmazonClientException, AmazonServiceException;

    TestInvokeAuthorizerResult testInvokeAuthorizer(TestInvokeAuthorizerRequest testInvokeAuthorizerRequest) throws AmazonClientException, AmazonServiceException;

    TransferCertificateResult transferCertificate(TransferCertificateRequest transferCertificateRequest) throws AmazonClientException, AmazonServiceException;

    UntagResourceResult untagResource(UntagResourceRequest untagResourceRequest) throws AmazonClientException, AmazonServiceException;

    UpdateAccountAuditConfigurationResult updateAccountAuditConfiguration(UpdateAccountAuditConfigurationRequest updateAccountAuditConfigurationRequest) throws AmazonClientException, AmazonServiceException;

    UpdateAuthorizerResult updateAuthorizer(UpdateAuthorizerRequest updateAuthorizerRequest) throws AmazonClientException, AmazonServiceException;

    UpdateBillingGroupResult updateBillingGroup(UpdateBillingGroupRequest updateBillingGroupRequest) throws AmazonClientException, AmazonServiceException;

    void updateCACertificate(UpdateCACertificateRequest updateCACertificateRequest) throws AmazonClientException, AmazonServiceException;

    void updateCertificate(UpdateCertificateRequest updateCertificateRequest) throws AmazonClientException, AmazonServiceException;

    UpdateDynamicThingGroupResult updateDynamicThingGroup(UpdateDynamicThingGroupRequest updateDynamicThingGroupRequest) throws AmazonClientException, AmazonServiceException;

    UpdateEventConfigurationsResult updateEventConfigurations(UpdateEventConfigurationsRequest updateEventConfigurationsRequest) throws AmazonClientException, AmazonServiceException;

    UpdateIndexingConfigurationResult updateIndexingConfiguration(UpdateIndexingConfigurationRequest updateIndexingConfigurationRequest) throws AmazonClientException, AmazonServiceException;

    void updateJob(UpdateJobRequest updateJobRequest) throws AmazonClientException, AmazonServiceException;

    UpdateRoleAliasResult updateRoleAlias(UpdateRoleAliasRequest updateRoleAliasRequest) throws AmazonClientException, AmazonServiceException;

    UpdateScheduledAuditResult updateScheduledAudit(UpdateScheduledAuditRequest updateScheduledAuditRequest) throws AmazonClientException, AmazonServiceException;

    UpdateSecurityProfileResult updateSecurityProfile(UpdateSecurityProfileRequest updateSecurityProfileRequest) throws AmazonClientException, AmazonServiceException;

    UpdateStreamResult updateStream(UpdateStreamRequest updateStreamRequest) throws AmazonClientException, AmazonServiceException;

    UpdateThingResult updateThing(UpdateThingRequest updateThingRequest) throws AmazonClientException, AmazonServiceException;

    UpdateThingGroupResult updateThingGroup(UpdateThingGroupRequest updateThingGroupRequest) throws AmazonClientException, AmazonServiceException;

    UpdateThingGroupsForThingResult updateThingGroupsForThing(UpdateThingGroupsForThingRequest updateThingGroupsForThingRequest) throws AmazonClientException, AmazonServiceException;

    ValidateSecurityProfileBehaviorsResult validateSecurityProfileBehaviors(ValidateSecurityProfileBehaviorsRequest validateSecurityProfileBehaviorsRequest) throws AmazonClientException, AmazonServiceException;
}
