package com.amazonaws.services.kinesis;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
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
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public interface AmazonKinesis {
    void addTagsToStream(AddTagsToStreamRequest addTagsToStreamRequest) throws AmazonClientException, AmazonServiceException;

    void createStream(CreateStreamRequest createStreamRequest) throws AmazonClientException, AmazonServiceException;

    void createStream(String str, Integer num) throws AmazonClientException, AmazonServiceException;

    void decreaseStreamRetentionPeriod(DecreaseStreamRetentionPeriodRequest decreaseStreamRetentionPeriodRequest) throws AmazonClientException, AmazonServiceException;

    void deleteStream(DeleteStreamRequest deleteStreamRequest) throws AmazonClientException, AmazonServiceException;

    void deleteStream(String str) throws AmazonClientException, AmazonServiceException;

    DescribeLimitsResult describeLimits(DescribeLimitsRequest describeLimitsRequest) throws AmazonClientException, AmazonServiceException;

    DescribeStreamResult describeStream(DescribeStreamRequest describeStreamRequest) throws AmazonClientException, AmazonServiceException;

    DescribeStreamResult describeStream(String str) throws AmazonClientException, AmazonServiceException;

    DescribeStreamResult describeStream(String str, Integer num, String str2) throws AmazonClientException, AmazonServiceException;

    DescribeStreamResult describeStream(String str, String str2) throws AmazonClientException, AmazonServiceException;

    DescribeStreamSummaryResult describeStreamSummary(DescribeStreamSummaryRequest describeStreamSummaryRequest) throws AmazonClientException, AmazonServiceException;

    DisableEnhancedMonitoringResult disableEnhancedMonitoring(DisableEnhancedMonitoringRequest disableEnhancedMonitoringRequest) throws AmazonClientException, AmazonServiceException;

    EnableEnhancedMonitoringResult enableEnhancedMonitoring(EnableEnhancedMonitoringRequest enableEnhancedMonitoringRequest) throws AmazonClientException, AmazonServiceException;

    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest);

    GetRecordsResult getRecords(GetRecordsRequest getRecordsRequest) throws AmazonClientException, AmazonServiceException;

    GetShardIteratorResult getShardIterator(GetShardIteratorRequest getShardIteratorRequest) throws AmazonClientException, AmazonServiceException;

    GetShardIteratorResult getShardIterator(String str, String str2, String str3) throws AmazonClientException, AmazonServiceException;

    GetShardIteratorResult getShardIterator(String str, String str2, String str3, String str4) throws AmazonClientException, AmazonServiceException;

    void increaseStreamRetentionPeriod(IncreaseStreamRetentionPeriodRequest increaseStreamRetentionPeriodRequest) throws AmazonClientException, AmazonServiceException;

    ListShardsResult listShards(ListShardsRequest listShardsRequest) throws AmazonClientException, AmazonServiceException;

    ListStreamsResult listStreams() throws AmazonClientException, AmazonServiceException;

    ListStreamsResult listStreams(ListStreamsRequest listStreamsRequest) throws AmazonClientException, AmazonServiceException;

    ListStreamsResult listStreams(Integer num, String str) throws AmazonClientException, AmazonServiceException;

    ListStreamsResult listStreams(String str) throws AmazonClientException, AmazonServiceException;

    ListTagsForStreamResult listTagsForStream(ListTagsForStreamRequest listTagsForStreamRequest) throws AmazonClientException, AmazonServiceException;

    void mergeShards(MergeShardsRequest mergeShardsRequest) throws AmazonClientException, AmazonServiceException;

    void mergeShards(String str, String str2, String str3) throws AmazonClientException, AmazonServiceException;

    PutRecordResult putRecord(PutRecordRequest putRecordRequest) throws AmazonClientException, AmazonServiceException;

    PutRecordResult putRecord(String str, ByteBuffer byteBuffer, String str2) throws AmazonClientException, AmazonServiceException;

    PutRecordResult putRecord(String str, ByteBuffer byteBuffer, String str2, String str3) throws AmazonClientException, AmazonServiceException;

    PutRecordsResult putRecords(PutRecordsRequest putRecordsRequest) throws AmazonClientException, AmazonServiceException;

    void removeTagsFromStream(RemoveTagsFromStreamRequest removeTagsFromStreamRequest) throws AmazonClientException, AmazonServiceException;

    void setEndpoint(String str) throws IllegalArgumentException;

    void setRegion(Region region) throws IllegalArgumentException;

    void shutdown();

    void splitShard(SplitShardRequest splitShardRequest) throws AmazonClientException, AmazonServiceException;

    void splitShard(String str, String str2, String str3) throws AmazonClientException, AmazonServiceException;

    void startStreamEncryption(StartStreamEncryptionRequest startStreamEncryptionRequest) throws AmazonClientException, AmazonServiceException;

    void stopStreamEncryption(StopStreamEncryptionRequest stopStreamEncryptionRequest) throws AmazonClientException, AmazonServiceException;

    UpdateShardCountResult updateShardCount(UpdateShardCountRequest updateShardCountRequest) throws AmazonClientException, AmazonServiceException;
}
