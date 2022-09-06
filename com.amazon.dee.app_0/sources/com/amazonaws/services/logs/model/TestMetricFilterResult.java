package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class TestMetricFilterResult implements Serializable {
    private List<MetricFilterMatchRecord> matches;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TestMetricFilterResult)) {
            return false;
        }
        TestMetricFilterResult testMetricFilterResult = (TestMetricFilterResult) obj;
        if ((testMetricFilterResult.getMatches() == null) ^ (getMatches() == null)) {
            return false;
        }
        return testMetricFilterResult.getMatches() == null || testMetricFilterResult.getMatches().equals(getMatches());
    }

    public List<MetricFilterMatchRecord> getMatches() {
        return this.matches;
    }

    public int hashCode() {
        return 31 + (getMatches() == null ? 0 : getMatches().hashCode());
    }

    public void setMatches(Collection<MetricFilterMatchRecord> collection) {
        if (collection == null) {
            this.matches = null;
        } else {
            this.matches = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getMatches() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("matches: ");
            outline1072.append(getMatches());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TestMetricFilterResult withMatches(MetricFilterMatchRecord... metricFilterMatchRecordArr) {
        if (getMatches() == null) {
            this.matches = new ArrayList(metricFilterMatchRecordArr.length);
        }
        for (MetricFilterMatchRecord metricFilterMatchRecord : metricFilterMatchRecordArr) {
            this.matches.add(metricFilterMatchRecord);
        }
        return this;
    }

    public TestMetricFilterResult withMatches(Collection<MetricFilterMatchRecord> collection) {
        setMatches(collection);
        return this;
    }
}
