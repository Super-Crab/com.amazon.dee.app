package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class SearchIndexResult implements Serializable {
    private String nextToken;
    private List<ThingGroupDocument> thingGroups;
    private List<ThingDocument> things;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SearchIndexResult)) {
            return false;
        }
        SearchIndexResult searchIndexResult = (SearchIndexResult) obj;
        if ((searchIndexResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (searchIndexResult.getNextToken() != null && !searchIndexResult.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((searchIndexResult.getThings() == null) ^ (getThings() == null)) {
            return false;
        }
        if (searchIndexResult.getThings() != null && !searchIndexResult.getThings().equals(getThings())) {
            return false;
        }
        if ((searchIndexResult.getThingGroups() == null) ^ (getThingGroups() == null)) {
            return false;
        }
        return searchIndexResult.getThingGroups() == null || searchIndexResult.getThingGroups().equals(getThingGroups());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<ThingGroupDocument> getThingGroups() {
        return this.thingGroups;
    }

    public List<ThingDocument> getThings() {
        return this.things;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getThings() == null ? 0 : getThings().hashCode())) * 31;
        if (getThingGroups() != null) {
            i = getThingGroups().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setThingGroups(Collection<ThingGroupDocument> collection) {
        if (collection == null) {
            this.thingGroups = null;
        } else {
            this.thingGroups = new ArrayList(collection);
        }
    }

    public void setThings(Collection<ThingDocument> collection) {
        if (collection == null) {
            this.things = null;
        } else {
            this.things = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getNextToken() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1072.append(getNextToken());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThings() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("things: ");
            outline1073.append(getThings());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingGroups() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingGroups: ");
            outline1074.append(getThingGroups());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SearchIndexResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public SearchIndexResult withThingGroups(ThingGroupDocument... thingGroupDocumentArr) {
        if (getThingGroups() == null) {
            this.thingGroups = new ArrayList(thingGroupDocumentArr.length);
        }
        for (ThingGroupDocument thingGroupDocument : thingGroupDocumentArr) {
            this.thingGroups.add(thingGroupDocument);
        }
        return this;
    }

    public SearchIndexResult withThings(ThingDocument... thingDocumentArr) {
        if (getThings() == null) {
            this.things = new ArrayList(thingDocumentArr.length);
        }
        for (ThingDocument thingDocument : thingDocumentArr) {
            this.things.add(thingDocument);
        }
        return this;
    }

    public SearchIndexResult withThingGroups(Collection<ThingGroupDocument> collection) {
        setThingGroups(collection);
        return this;
    }

    public SearchIndexResult withThings(Collection<ThingDocument> collection) {
        setThings(collection);
        return this;
    }
}
