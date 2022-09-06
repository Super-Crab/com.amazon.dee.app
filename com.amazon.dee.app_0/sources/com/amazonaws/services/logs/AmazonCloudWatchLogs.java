package com.amazonaws.services.logs;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
import com.amazonaws.services.logs.model.AssociateKmsKeyRequest;
import com.amazonaws.services.logs.model.CancelExportTaskRequest;
import com.amazonaws.services.logs.model.CreateExportTaskRequest;
import com.amazonaws.services.logs.model.CreateExportTaskResult;
import com.amazonaws.services.logs.model.CreateLogGroupRequest;
import com.amazonaws.services.logs.model.CreateLogStreamRequest;
import com.amazonaws.services.logs.model.DeleteDestinationRequest;
import com.amazonaws.services.logs.model.DeleteLogGroupRequest;
import com.amazonaws.services.logs.model.DeleteLogStreamRequest;
import com.amazonaws.services.logs.model.DeleteMetricFilterRequest;
import com.amazonaws.services.logs.model.DeleteResourcePolicyRequest;
import com.amazonaws.services.logs.model.DeleteRetentionPolicyRequest;
import com.amazonaws.services.logs.model.DeleteSubscriptionFilterRequest;
import com.amazonaws.services.logs.model.DescribeDestinationsRequest;
import com.amazonaws.services.logs.model.DescribeDestinationsResult;
import com.amazonaws.services.logs.model.DescribeExportTasksRequest;
import com.amazonaws.services.logs.model.DescribeExportTasksResult;
import com.amazonaws.services.logs.model.DescribeLogGroupsRequest;
import com.amazonaws.services.logs.model.DescribeLogGroupsResult;
import com.amazonaws.services.logs.model.DescribeLogStreamsRequest;
import com.amazonaws.services.logs.model.DescribeLogStreamsResult;
import com.amazonaws.services.logs.model.DescribeMetricFiltersRequest;
import com.amazonaws.services.logs.model.DescribeMetricFiltersResult;
import com.amazonaws.services.logs.model.DescribeResourcePoliciesRequest;
import com.amazonaws.services.logs.model.DescribeResourcePoliciesResult;
import com.amazonaws.services.logs.model.DescribeSubscriptionFiltersRequest;
import com.amazonaws.services.logs.model.DescribeSubscriptionFiltersResult;
import com.amazonaws.services.logs.model.DisassociateKmsKeyRequest;
import com.amazonaws.services.logs.model.FilterLogEventsRequest;
import com.amazonaws.services.logs.model.FilterLogEventsResult;
import com.amazonaws.services.logs.model.GetLogEventsRequest;
import com.amazonaws.services.logs.model.GetLogEventsResult;
import com.amazonaws.services.logs.model.ListTagsLogGroupRequest;
import com.amazonaws.services.logs.model.ListTagsLogGroupResult;
import com.amazonaws.services.logs.model.PutDestinationPolicyRequest;
import com.amazonaws.services.logs.model.PutDestinationRequest;
import com.amazonaws.services.logs.model.PutDestinationResult;
import com.amazonaws.services.logs.model.PutLogEventsRequest;
import com.amazonaws.services.logs.model.PutLogEventsResult;
import com.amazonaws.services.logs.model.PutMetricFilterRequest;
import com.amazonaws.services.logs.model.PutResourcePolicyRequest;
import com.amazonaws.services.logs.model.PutResourcePolicyResult;
import com.amazonaws.services.logs.model.PutRetentionPolicyRequest;
import com.amazonaws.services.logs.model.PutSubscriptionFilterRequest;
import com.amazonaws.services.logs.model.TagLogGroupRequest;
import com.amazonaws.services.logs.model.TestMetricFilterRequest;
import com.amazonaws.services.logs.model.TestMetricFilterResult;
import com.amazonaws.services.logs.model.UntagLogGroupRequest;
/* loaded from: classes13.dex */
public interface AmazonCloudWatchLogs {
    void associateKmsKey(AssociateKmsKeyRequest associateKmsKeyRequest) throws AmazonClientException, AmazonServiceException;

    void cancelExportTask(CancelExportTaskRequest cancelExportTaskRequest) throws AmazonClientException, AmazonServiceException;

    CreateExportTaskResult createExportTask(CreateExportTaskRequest createExportTaskRequest) throws AmazonClientException, AmazonServiceException;

    void createLogGroup(CreateLogGroupRequest createLogGroupRequest) throws AmazonClientException, AmazonServiceException;

    void createLogStream(CreateLogStreamRequest createLogStreamRequest) throws AmazonClientException, AmazonServiceException;

    void deleteDestination(DeleteDestinationRequest deleteDestinationRequest) throws AmazonClientException, AmazonServiceException;

    void deleteLogGroup(DeleteLogGroupRequest deleteLogGroupRequest) throws AmazonClientException, AmazonServiceException;

    void deleteLogStream(DeleteLogStreamRequest deleteLogStreamRequest) throws AmazonClientException, AmazonServiceException;

    void deleteMetricFilter(DeleteMetricFilterRequest deleteMetricFilterRequest) throws AmazonClientException, AmazonServiceException;

    void deleteResourcePolicy(DeleteResourcePolicyRequest deleteResourcePolicyRequest) throws AmazonClientException, AmazonServiceException;

    void deleteRetentionPolicy(DeleteRetentionPolicyRequest deleteRetentionPolicyRequest) throws AmazonClientException, AmazonServiceException;

    void deleteSubscriptionFilter(DeleteSubscriptionFilterRequest deleteSubscriptionFilterRequest) throws AmazonClientException, AmazonServiceException;

    DescribeDestinationsResult describeDestinations(DescribeDestinationsRequest describeDestinationsRequest) throws AmazonClientException, AmazonServiceException;

    DescribeExportTasksResult describeExportTasks(DescribeExportTasksRequest describeExportTasksRequest) throws AmazonClientException, AmazonServiceException;

    DescribeLogGroupsResult describeLogGroups(DescribeLogGroupsRequest describeLogGroupsRequest) throws AmazonClientException, AmazonServiceException;

    DescribeLogStreamsResult describeLogStreams(DescribeLogStreamsRequest describeLogStreamsRequest) throws AmazonClientException, AmazonServiceException;

    DescribeMetricFiltersResult describeMetricFilters(DescribeMetricFiltersRequest describeMetricFiltersRequest) throws AmazonClientException, AmazonServiceException;

    DescribeResourcePoliciesResult describeResourcePolicies(DescribeResourcePoliciesRequest describeResourcePoliciesRequest) throws AmazonClientException, AmazonServiceException;

    DescribeSubscriptionFiltersResult describeSubscriptionFilters(DescribeSubscriptionFiltersRequest describeSubscriptionFiltersRequest) throws AmazonClientException, AmazonServiceException;

    void disassociateKmsKey(DisassociateKmsKeyRequest disassociateKmsKeyRequest) throws AmazonClientException, AmazonServiceException;

    FilterLogEventsResult filterLogEvents(FilterLogEventsRequest filterLogEventsRequest) throws AmazonClientException, AmazonServiceException;

    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest);

    GetLogEventsResult getLogEvents(GetLogEventsRequest getLogEventsRequest) throws AmazonClientException, AmazonServiceException;

    ListTagsLogGroupResult listTagsLogGroup(ListTagsLogGroupRequest listTagsLogGroupRequest) throws AmazonClientException, AmazonServiceException;

    PutDestinationResult putDestination(PutDestinationRequest putDestinationRequest) throws AmazonClientException, AmazonServiceException;

    void putDestinationPolicy(PutDestinationPolicyRequest putDestinationPolicyRequest) throws AmazonClientException, AmazonServiceException;

    PutLogEventsResult putLogEvents(PutLogEventsRequest putLogEventsRequest) throws AmazonClientException, AmazonServiceException;

    void putMetricFilter(PutMetricFilterRequest putMetricFilterRequest) throws AmazonClientException, AmazonServiceException;

    PutResourcePolicyResult putResourcePolicy(PutResourcePolicyRequest putResourcePolicyRequest) throws AmazonClientException, AmazonServiceException;

    void putRetentionPolicy(PutRetentionPolicyRequest putRetentionPolicyRequest) throws AmazonClientException, AmazonServiceException;

    void putSubscriptionFilter(PutSubscriptionFilterRequest putSubscriptionFilterRequest) throws AmazonClientException, AmazonServiceException;

    void setEndpoint(String str) throws IllegalArgumentException;

    void setRegion(Region region) throws IllegalArgumentException;

    void shutdown();

    void tagLogGroup(TagLogGroupRequest tagLogGroupRequest) throws AmazonClientException, AmazonServiceException;

    TestMetricFilterResult testMetricFilter(TestMetricFilterRequest testMetricFilterRequest) throws AmazonClientException, AmazonServiceException;

    void untagLogGroup(UntagLogGroupRequest untagLogGroupRequest) throws AmazonClientException, AmazonServiceException;
}
