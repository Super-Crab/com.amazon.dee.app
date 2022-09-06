package com.amazon.deecomms.common.telemetry;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
public class TelemetryCredentials {
    @JsonProperty
    private String accessKey;
    @JsonProperty
    private String apiKey;
    @JsonProperty
    private String invokeUrl;
    @JsonProperty
    private String region;
    @JsonProperty
    private String secretKey;

    public String getAccessKey() {
        return this.accessKey;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getInvokeUrl() {
        return this.invokeUrl;
    }

    public String getRegion() {
        return this.region;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setAccessKey(String str) {
        this.accessKey = str;
    }

    public void setApiKey(String str) {
        this.apiKey = str;
    }

    public void setInvokeUrl(String str) {
        this.invokeUrl = str;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public void setSecretKey(String str) {
        this.secretKey = str;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("TelemetryCredentials{invokeUrl='");
        GeneratedOutlineSupport1.outline176(outline1, this.invokeUrl, Chars.QUOTE, ", apiKey='");
        GeneratedOutlineSupport1.outline176(outline1, this.apiKey, Chars.QUOTE, ", accessKey='");
        GeneratedOutlineSupport1.outline176(outline1, this.accessKey, Chars.QUOTE, ", secretKey='");
        GeneratedOutlineSupport1.outline176(outline1, this.secretKey, Chars.QUOTE, ", region='");
        return GeneratedOutlineSupport1.outline90(outline1, this.region, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
