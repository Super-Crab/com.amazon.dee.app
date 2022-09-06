package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListStreamsResult implements Serializable {
    private String nextToken;
    private List<StreamSummary> streams;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListStreamsResult)) {
            return false;
        }
        ListStreamsResult listStreamsResult = (ListStreamsResult) obj;
        if ((listStreamsResult.getStreams() == null) ^ (getStreams() == null)) {
            return false;
        }
        if (listStreamsResult.getStreams() != null && !listStreamsResult.getStreams().equals(getStreams())) {
            return false;
        }
        if ((listStreamsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listStreamsResult.getNextToken() == null || listStreamsResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<StreamSummary> getStreams() {
        return this.streams;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStreams() == null ? 0 : getStreams().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setStreams(Collection<StreamSummary> collection) {
        if (collection == null) {
            this.streams = null;
        } else {
            this.streams = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreams() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("streams: ");
            outline1072.append(getStreams());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListStreamsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListStreamsResult withStreams(StreamSummary... streamSummaryArr) {
        if (getStreams() == null) {
            this.streams = new ArrayList(streamSummaryArr.length);
        }
        for (StreamSummary streamSummary : streamSummaryArr) {
            this.streams.add(streamSummary);
        }
        return this;
    }

    public ListStreamsResult withStreams(Collection<StreamSummary> collection) {
        setStreams(collection);
        return this;
    }
}
