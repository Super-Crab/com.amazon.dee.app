package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DescribeDestinationsResult implements Serializable {
    private List<Destination> destinations;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeDestinationsResult)) {
            return false;
        }
        DescribeDestinationsResult describeDestinationsResult = (DescribeDestinationsResult) obj;
        if ((describeDestinationsResult.getDestinations() == null) ^ (getDestinations() == null)) {
            return false;
        }
        if (describeDestinationsResult.getDestinations() != null && !describeDestinationsResult.getDestinations().equals(getDestinations())) {
            return false;
        }
        if ((describeDestinationsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return describeDestinationsResult.getNextToken() == null || describeDestinationsResult.getNextToken().equals(getNextToken());
    }

    public List<Destination> getDestinations() {
        return this.destinations;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getDestinations() == null ? 0 : getDestinations().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setDestinations(Collection<Destination> collection) {
        if (collection == null) {
            this.destinations = null;
        } else {
            this.destinations = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDestinations() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("destinations: ");
            outline1072.append(getDestinations());
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

    public DescribeDestinationsResult withDestinations(Destination... destinationArr) {
        if (getDestinations() == null) {
            this.destinations = new ArrayList(destinationArr.length);
        }
        for (Destination destination : destinationArr) {
            this.destinations.add(destination);
        }
        return this;
    }

    public DescribeDestinationsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public DescribeDestinationsResult withDestinations(Collection<Destination> collection) {
        setDestinations(collection);
        return this;
    }
}
