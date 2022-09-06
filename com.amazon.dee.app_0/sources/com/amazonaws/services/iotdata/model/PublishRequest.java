package com.amazonaws.services.iotdata.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class PublishRequest extends AmazonWebServiceRequest implements Serializable {
    private ByteBuffer payload;
    private Integer qos;
    private String topic;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PublishRequest)) {
            return false;
        }
        PublishRequest publishRequest = (PublishRequest) obj;
        if ((publishRequest.getTopic() == null) ^ (getTopic() == null)) {
            return false;
        }
        if (publishRequest.getTopic() != null && !publishRequest.getTopic().equals(getTopic())) {
            return false;
        }
        if ((publishRequest.getQos() == null) ^ (getQos() == null)) {
            return false;
        }
        if (publishRequest.getQos() != null && !publishRequest.getQos().equals(getQos())) {
            return false;
        }
        if ((publishRequest.getPayload() == null) ^ (getPayload() == null)) {
            return false;
        }
        return publishRequest.getPayload() == null || publishRequest.getPayload().equals(getPayload());
    }

    public ByteBuffer getPayload() {
        return this.payload;
    }

    public Integer getQos() {
        return this.qos;
    }

    public String getTopic() {
        return this.topic;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getTopic() == null ? 0 : getTopic().hashCode()) + 31) * 31) + (getQos() == null ? 0 : getQos().hashCode())) * 31;
        if (getPayload() != null) {
            i = getPayload().hashCode();
        }
        return hashCode + i;
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
    }

    public void setQos(Integer num) {
        this.qos = num;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTopic() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("topic: ");
            outline1072.append(getTopic());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getQos() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("qos: ");
            outline1073.append(getQos());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPayload() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("payload: ");
            outline1074.append(getPayload());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PublishRequest withPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
        return this;
    }

    public PublishRequest withQos(Integer num) {
        this.qos = num;
        return this;
    }

    public PublishRequest withTopic(String str) {
        this.topic = str;
        return this;
    }
}
