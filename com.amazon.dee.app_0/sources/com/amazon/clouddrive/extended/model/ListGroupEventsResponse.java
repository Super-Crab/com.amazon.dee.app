package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class ListGroupEventsResponse implements CloudDriveRequest {
    private List<GroupEventResponse> events;
    private String nextToken;
    private String previousToken;

    protected boolean canEqual(Object obj) {
        return obj instanceof ListGroupEventsResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListGroupEventsResponse)) {
            return false;
        }
        ListGroupEventsResponse listGroupEventsResponse = (ListGroupEventsResponse) obj;
        if (!listGroupEventsResponse.canEqual(this)) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = listGroupEventsResponse.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        String previousToken = getPreviousToken();
        String previousToken2 = listGroupEventsResponse.getPreviousToken();
        if (previousToken != null ? !previousToken.equals(previousToken2) : previousToken2 != null) {
            return false;
        }
        List<GroupEventResponse> events = getEvents();
        List<GroupEventResponse> events2 = listGroupEventsResponse.getEvents();
        return events != null ? events.equals(events2) : events2 == null;
    }

    public List<GroupEventResponse> getEvents() {
        return this.events;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getPreviousToken() {
        return this.previousToken;
    }

    public int hashCode() {
        String nextToken = getNextToken();
        int i = 43;
        int hashCode = nextToken == null ? 43 : nextToken.hashCode();
        String previousToken = getPreviousToken();
        int hashCode2 = ((hashCode + 59) * 59) + (previousToken == null ? 43 : previousToken.hashCode());
        List<GroupEventResponse> events = getEvents();
        int i2 = hashCode2 * 59;
        if (events != null) {
            i = events.hashCode();
        }
        return i2 + i;
    }

    public void setEvents(List<GroupEventResponse> list) {
        this.events = list;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setPreviousToken(String str) {
        this.previousToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListGroupEventsResponse(nextToken=");
        outline107.append(getNextToken());
        outline107.append(", previousToken=");
        outline107.append(getPreviousToken());
        outline107.append(", events=");
        outline107.append(getEvents());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest instanceof CloudDriveRequest) {
            return cloudDriveRequest.hashCode() - hashCode();
        }
        return -1;
    }
}
