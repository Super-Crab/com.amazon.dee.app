package com.amazonaws.services.iotdata;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
import com.amazonaws.services.iotdata.model.DeleteThingShadowRequest;
import com.amazonaws.services.iotdata.model.DeleteThingShadowResult;
import com.amazonaws.services.iotdata.model.GetThingShadowRequest;
import com.amazonaws.services.iotdata.model.GetThingShadowResult;
import com.amazonaws.services.iotdata.model.PublishRequest;
import com.amazonaws.services.iotdata.model.UpdateThingShadowRequest;
import com.amazonaws.services.iotdata.model.UpdateThingShadowResult;
/* loaded from: classes13.dex */
public interface AWSIotData {
    DeleteThingShadowResult deleteThingShadow(DeleteThingShadowRequest deleteThingShadowRequest) throws AmazonClientException, AmazonServiceException;

    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest);

    GetThingShadowResult getThingShadow(GetThingShadowRequest getThingShadowRequest) throws AmazonClientException, AmazonServiceException;

    void publish(PublishRequest publishRequest) throws AmazonClientException, AmazonServiceException;

    void setEndpoint(String str) throws IllegalArgumentException;

    void setRegion(Region region) throws IllegalArgumentException;

    void shutdown();

    UpdateThingShadowResult updateThingShadow(UpdateThingShadowRequest updateThingShadowRequest) throws AmazonClientException, AmazonServiceException;
}
