package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class FilterLogEventsResult implements Serializable {
    private List<FilteredLogEvent> events;
    private String nextToken;
    private List<SearchedLogStream> searchedLogStreams;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FilterLogEventsResult)) {
            return false;
        }
        FilterLogEventsResult filterLogEventsResult = (FilterLogEventsResult) obj;
        if ((filterLogEventsResult.getEvents() == null) ^ (getEvents() == null)) {
            return false;
        }
        if (filterLogEventsResult.getEvents() != null && !filterLogEventsResult.getEvents().equals(getEvents())) {
            return false;
        }
        if ((filterLogEventsResult.getSearchedLogStreams() == null) ^ (getSearchedLogStreams() == null)) {
            return false;
        }
        if (filterLogEventsResult.getSearchedLogStreams() != null && !filterLogEventsResult.getSearchedLogStreams().equals(getSearchedLogStreams())) {
            return false;
        }
        if ((filterLogEventsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return filterLogEventsResult.getNextToken() == null || filterLogEventsResult.getNextToken().equals(getNextToken());
    }

    public List<FilteredLogEvent> getEvents() {
        return this.events;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<SearchedLogStream> getSearchedLogStreams() {
        return this.searchedLogStreams;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getEvents() == null ? 0 : getEvents().hashCode()) + 31) * 31) + (getSearchedLogStreams() == null ? 0 : getSearchedLogStreams().hashCode())) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setEvents(Collection<FilteredLogEvent> collection) {
        if (collection == null) {
            this.events = null;
        } else {
            this.events = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setSearchedLogStreams(Collection<SearchedLogStream> collection) {
        if (collection == null) {
            this.searchedLogStreams = null;
        } else {
            this.searchedLogStreams = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getEvents() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("events: ");
            outline1072.append(getEvents());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSearchedLogStreams() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("searchedLogStreams: ");
            outline1073.append(getSearchedLogStreams());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1074.append(getNextToken());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public FilterLogEventsResult withEvents(FilteredLogEvent... filteredLogEventArr) {
        if (getEvents() == null) {
            this.events = new ArrayList(filteredLogEventArr.length);
        }
        for (FilteredLogEvent filteredLogEvent : filteredLogEventArr) {
            this.events.add(filteredLogEvent);
        }
        return this;
    }

    public FilterLogEventsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public FilterLogEventsResult withSearchedLogStreams(SearchedLogStream... searchedLogStreamArr) {
        if (getSearchedLogStreams() == null) {
            this.searchedLogStreams = new ArrayList(searchedLogStreamArr.length);
        }
        for (SearchedLogStream searchedLogStream : searchedLogStreamArr) {
            this.searchedLogStreams.add(searchedLogStream);
        }
        return this;
    }

    public FilterLogEventsResult withEvents(Collection<FilteredLogEvent> collection) {
        setEvents(collection);
        return this;
    }

    public FilterLogEventsResult withSearchedLogStreams(Collection<SearchedLogStream> collection) {
        setSearchedLogStreams(collection);
        return this;
    }
}
