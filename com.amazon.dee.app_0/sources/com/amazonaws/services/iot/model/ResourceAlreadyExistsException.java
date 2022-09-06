package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonServiceException;
/* loaded from: classes13.dex */
public class ResourceAlreadyExistsException extends AmazonServiceException {
    private static final long serialVersionUID = 1;
    private String resourceArn;
    private String resourceId;

    public ResourceAlreadyExistsException(String str) {
        super(str);
    }

    public String getResourceArn() {
        return this.resourceArn;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceArn(String str) {
        this.resourceArn = str;
    }

    public void setResourceId(String str) {
        this.resourceId = str;
    }
}
