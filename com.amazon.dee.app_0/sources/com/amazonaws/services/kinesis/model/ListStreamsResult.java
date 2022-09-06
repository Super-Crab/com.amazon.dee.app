package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListStreamsResult implements Serializable {
    private Boolean hasMoreStreams;
    private List<String> streamNames = new ArrayList();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListStreamsResult)) {
            return false;
        }
        ListStreamsResult listStreamsResult = (ListStreamsResult) obj;
        if ((listStreamsResult.getStreamNames() == null) ^ (getStreamNames() == null)) {
            return false;
        }
        if (listStreamsResult.getStreamNames() != null && !listStreamsResult.getStreamNames().equals(getStreamNames())) {
            return false;
        }
        if ((listStreamsResult.getHasMoreStreams() == null) ^ (getHasMoreStreams() == null)) {
            return false;
        }
        return listStreamsResult.getHasMoreStreams() == null || listStreamsResult.getHasMoreStreams().equals(getHasMoreStreams());
    }

    public Boolean getHasMoreStreams() {
        return this.hasMoreStreams;
    }

    public List<String> getStreamNames() {
        return this.streamNames;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStreamNames() == null ? 0 : getStreamNames().hashCode()) + 31) * 31;
        if (getHasMoreStreams() != null) {
            i = getHasMoreStreams().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isHasMoreStreams() {
        return this.hasMoreStreams;
    }

    public void setHasMoreStreams(Boolean bool) {
        this.hasMoreStreams = bool;
    }

    public void setStreamNames(Collection<String> collection) {
        if (collection == null) {
            this.streamNames = null;
        } else {
            this.streamNames = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStreamNames() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("StreamNames: ");
            outline1072.append(getStreamNames());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getHasMoreStreams() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("HasMoreStreams: ");
            outline1073.append(getHasMoreStreams());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListStreamsResult withHasMoreStreams(Boolean bool) {
        this.hasMoreStreams = bool;
        return this;
    }

    public ListStreamsResult withStreamNames(String... strArr) {
        if (getStreamNames() == null) {
            this.streamNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.streamNames.add(str);
        }
        return this;
    }

    public ListStreamsResult withStreamNames(Collection<String> collection) {
        setStreamNames(collection);
        return this;
    }
}
