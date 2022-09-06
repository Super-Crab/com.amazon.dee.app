package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.metrics;
/* loaded from: classes8.dex */
public enum MetricType {
    FETCH_FALCO_REMOTE_CONFIG_JOB_STARTED("FetchFalcoRemoteJob:Started"),
    FETCH_FALCO_REMOTE_CONFIG_JOB_DESERIALIZATION_FAILURE("FetchFalcoRemoteJob:Finished:Failure:Deserialization"),
    FETCH_FALCO_REMOTE_CONFIG_JOB_FINISHED("FetchFalcoRemoteJob:Finished"),
    FETCH_FALCO_REMOTE_CONFIG_JOB_FINISHED_SUCCESS("FetchFalcoRemoteJob:Finished:Success"),
    FETCH_FALCO_REMOTE_CONFIG_JOB_FINISHED_FAILURE("FetchFalcoRemoteJob:Finished:Failure");
    
    private String mValue;

    MetricType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }
}
