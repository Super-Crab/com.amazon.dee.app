package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class TestMetricFilterRequest extends AmazonWebServiceRequest implements Serializable {
    private String filterPattern;
    private List<String> logEventMessages;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TestMetricFilterRequest)) {
            return false;
        }
        TestMetricFilterRequest testMetricFilterRequest = (TestMetricFilterRequest) obj;
        if ((testMetricFilterRequest.getFilterPattern() == null) ^ (getFilterPattern() == null)) {
            return false;
        }
        if (testMetricFilterRequest.getFilterPattern() != null && !testMetricFilterRequest.getFilterPattern().equals(getFilterPattern())) {
            return false;
        }
        if ((testMetricFilterRequest.getLogEventMessages() == null) ^ (getLogEventMessages() == null)) {
            return false;
        }
        return testMetricFilterRequest.getLogEventMessages() == null || testMetricFilterRequest.getLogEventMessages().equals(getLogEventMessages());
    }

    public String getFilterPattern() {
        return this.filterPattern;
    }

    public List<String> getLogEventMessages() {
        return this.logEventMessages;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getFilterPattern() == null ? 0 : getFilterPattern().hashCode()) + 31) * 31;
        if (getLogEventMessages() != null) {
            i = getLogEventMessages().hashCode();
        }
        return hashCode + i;
    }

    public void setFilterPattern(String str) {
        this.filterPattern = str;
    }

    public void setLogEventMessages(Collection<String> collection) {
        if (collection == null) {
            this.logEventMessages = null;
        } else {
            this.logEventMessages = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getFilterPattern() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("filterPattern: ");
            outline1072.append(getFilterPattern());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getLogEventMessages() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("logEventMessages: ");
            outline1073.append(getLogEventMessages());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TestMetricFilterRequest withFilterPattern(String str) {
        this.filterPattern = str;
        return this;
    }

    public TestMetricFilterRequest withLogEventMessages(String... strArr) {
        if (getLogEventMessages() == null) {
            this.logEventMessages = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.logEventMessages.add(str);
        }
        return this;
    }

    public TestMetricFilterRequest withLogEventMessages(Collection<String> collection) {
        setLogEventMessages(collection);
        return this;
    }
}
