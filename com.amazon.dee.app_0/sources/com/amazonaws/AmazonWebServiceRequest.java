package com.amazonaws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.metrics.RequestMetricCollector;
/* loaded from: classes13.dex */
public abstract class AmazonWebServiceRequest implements Cloneable {
    private AmazonWebServiceRequest cloneSource;
    private AWSCredentials credentials;
    private ProgressListener generalProgressListener;
    private final RequestClientOptions requestClientOptions = new RequestClientOptions();
    @Deprecated
    private RequestMetricCollector requestMetricCollector;

    private void setCloneSource(AmazonWebServiceRequest amazonWebServiceRequest) {
        this.cloneSource = amazonWebServiceRequest;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <T extends AmazonWebServiceRequest> T copyBaseTo(T t) {
        t.setGeneralProgressListener(this.generalProgressListener);
        t.setRequestMetricCollector(this.requestMetricCollector);
        return t;
    }

    public AmazonWebServiceRequest getCloneRoot() {
        AmazonWebServiceRequest amazonWebServiceRequest = this.cloneSource;
        if (amazonWebServiceRequest != null) {
            while (amazonWebServiceRequest.getCloneSource() != null) {
                amazonWebServiceRequest = amazonWebServiceRequest.getCloneSource();
            }
        }
        return amazonWebServiceRequest;
    }

    public AmazonWebServiceRequest getCloneSource() {
        return this.cloneSource;
    }

    public ProgressListener getGeneralProgressListener() {
        return this.generalProgressListener;
    }

    public RequestClientOptions getRequestClientOptions() {
        return this.requestClientOptions;
    }

    public AWSCredentials getRequestCredentials() {
        return this.credentials;
    }

    @Deprecated
    public RequestMetricCollector getRequestMetricCollector() {
        return this.requestMetricCollector;
    }

    public void setGeneralProgressListener(ProgressListener progressListener) {
        this.generalProgressListener = progressListener;
    }

    public void setRequestCredentials(AWSCredentials aWSCredentials) {
        this.credentials = aWSCredentials;
    }

    @Deprecated
    public void setRequestMetricCollector(RequestMetricCollector requestMetricCollector) {
        this.requestMetricCollector = requestMetricCollector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withGeneralProgressListener */
    public <T extends AmazonWebServiceRequest> T mo6730withGeneralProgressListener(ProgressListener progressListener) {
        setGeneralProgressListener(progressListener);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public <T extends AmazonWebServiceRequest> T withRequestMetricCollector(RequestMetricCollector requestMetricCollector) {
        setRequestMetricCollector(requestMetricCollector);
        return this;
    }

    /* renamed from: clone */
    public AmazonWebServiceRequest m6646clone() {
        try {
            AmazonWebServiceRequest amazonWebServiceRequest = (AmazonWebServiceRequest) super.clone();
            amazonWebServiceRequest.setCloneSource(this);
            return amazonWebServiceRequest;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() even though we're Cloneable!", e);
        }
    }
}
