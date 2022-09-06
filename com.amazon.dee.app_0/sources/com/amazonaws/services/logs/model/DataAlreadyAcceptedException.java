package com.amazonaws.services.logs.model;

import com.amazonaws.AmazonServiceException;
/* loaded from: classes13.dex */
public class DataAlreadyAcceptedException extends AmazonServiceException {
    private static final long serialVersionUID = 1;
    private String expectedSequenceToken;

    public DataAlreadyAcceptedException(String str) {
        super(str);
    }

    public String getExpectedSequenceToken() {
        return this.expectedSequenceToken;
    }

    public void setExpectedSequenceToken(String str) {
        this.expectedSequenceToken = str;
    }
}
