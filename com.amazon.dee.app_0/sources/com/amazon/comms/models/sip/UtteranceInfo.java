package com.amazon.comms.models.sip;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes11.dex */
public class UtteranceInfo {
    private Long endOfSpeechAbsoluteTimestampInMillis;
    private Long endOfSpeechRelativeTimestampInMillis;
    private String requestId;

    /* loaded from: classes11.dex */
    public static class UtteranceInfoBuilder {
        private Long endOfSpeechAbsoluteTimestampInMillis;
        private Long endOfSpeechRelativeTimestampInMillis;
        private String requestId;

        UtteranceInfoBuilder() {
        }

        public UtteranceInfo build() {
            return new UtteranceInfo(this.requestId, this.endOfSpeechAbsoluteTimestampInMillis, this.endOfSpeechRelativeTimestampInMillis);
        }

        public UtteranceInfoBuilder endOfSpeechAbsoluteTimestampInMillis(Long l) {
            this.endOfSpeechAbsoluteTimestampInMillis = l;
            return this;
        }

        public UtteranceInfoBuilder endOfSpeechRelativeTimestampInMillis(Long l) {
            this.endOfSpeechRelativeTimestampInMillis = l;
            return this;
        }

        public UtteranceInfoBuilder requestId(String str) {
            this.requestId = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UtteranceInfo.UtteranceInfoBuilder(requestId=");
            outline107.append(this.requestId);
            outline107.append(", endOfSpeechAbsoluteTimestampInMillis=");
            outline107.append(this.endOfSpeechAbsoluteTimestampInMillis);
            outline107.append(", endOfSpeechRelativeTimestampInMillis=");
            outline107.append(this.endOfSpeechRelativeTimestampInMillis);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public UtteranceInfo() {
    }

    public static UtteranceInfoBuilder builder() {
        return new UtteranceInfoBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof UtteranceInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UtteranceInfo)) {
            return false;
        }
        UtteranceInfo utteranceInfo = (UtteranceInfo) obj;
        if (!utteranceInfo.canEqual(this)) {
            return false;
        }
        String requestId = getRequestId();
        String requestId2 = utteranceInfo.getRequestId();
        if (requestId != null ? !requestId.equals(requestId2) : requestId2 != null) {
            return false;
        }
        Long endOfSpeechAbsoluteTimestampInMillis = getEndOfSpeechAbsoluteTimestampInMillis();
        Long endOfSpeechAbsoluteTimestampInMillis2 = utteranceInfo.getEndOfSpeechAbsoluteTimestampInMillis();
        if (endOfSpeechAbsoluteTimestampInMillis != null ? !endOfSpeechAbsoluteTimestampInMillis.equals(endOfSpeechAbsoluteTimestampInMillis2) : endOfSpeechAbsoluteTimestampInMillis2 != null) {
            return false;
        }
        Long endOfSpeechRelativeTimestampInMillis = getEndOfSpeechRelativeTimestampInMillis();
        Long endOfSpeechRelativeTimestampInMillis2 = utteranceInfo.getEndOfSpeechRelativeTimestampInMillis();
        return endOfSpeechRelativeTimestampInMillis != null ? endOfSpeechRelativeTimestampInMillis.equals(endOfSpeechRelativeTimestampInMillis2) : endOfSpeechRelativeTimestampInMillis2 == null;
    }

    public Long getEndOfSpeechAbsoluteTimestampInMillis() {
        return this.endOfSpeechAbsoluteTimestampInMillis;
    }

    public Long getEndOfSpeechRelativeTimestampInMillis() {
        return this.endOfSpeechRelativeTimestampInMillis;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public int hashCode() {
        String requestId = getRequestId();
        int i = 43;
        int hashCode = requestId == null ? 43 : requestId.hashCode();
        Long endOfSpeechAbsoluteTimestampInMillis = getEndOfSpeechAbsoluteTimestampInMillis();
        int hashCode2 = ((hashCode + 59) * 59) + (endOfSpeechAbsoluteTimestampInMillis == null ? 43 : endOfSpeechAbsoluteTimestampInMillis.hashCode());
        Long endOfSpeechRelativeTimestampInMillis = getEndOfSpeechRelativeTimestampInMillis();
        int i2 = hashCode2 * 59;
        if (endOfSpeechRelativeTimestampInMillis != null) {
            i = endOfSpeechRelativeTimestampInMillis.hashCode();
        }
        return i2 + i;
    }

    public void setEndOfSpeechAbsoluteTimestampInMillis(Long l) {
        this.endOfSpeechAbsoluteTimestampInMillis = l;
    }

    public void setEndOfSpeechRelativeTimestampInMillis(Long l) {
        this.endOfSpeechRelativeTimestampInMillis = l;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UtteranceInfo(requestId=");
        outline107.append(getRequestId());
        outline107.append(", endOfSpeechAbsoluteTimestampInMillis=");
        outline107.append(getEndOfSpeechAbsoluteTimestampInMillis());
        outline107.append(", endOfSpeechRelativeTimestampInMillis=");
        outline107.append(getEndOfSpeechRelativeTimestampInMillis());
        outline107.append(")");
        return outline107.toString();
    }

    private UtteranceInfo(String str, Long l, Long l2) {
        this.requestId = str;
        this.endOfSpeechAbsoluteTimestampInMillis = l;
        this.endOfSpeechRelativeTimestampInMillis = l2;
    }
}
