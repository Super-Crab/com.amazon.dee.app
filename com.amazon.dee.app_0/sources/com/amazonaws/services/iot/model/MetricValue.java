package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class MetricValue implements Serializable {
    private List<String> cidrs;
    private Long count;
    private List<Integer> ports;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MetricValue)) {
            return false;
        }
        MetricValue metricValue = (MetricValue) obj;
        if ((metricValue.getCount() == null) ^ (getCount() == null)) {
            return false;
        }
        if (metricValue.getCount() != null && !metricValue.getCount().equals(getCount())) {
            return false;
        }
        if ((metricValue.getCidrs() == null) ^ (getCidrs() == null)) {
            return false;
        }
        if (metricValue.getCidrs() != null && !metricValue.getCidrs().equals(getCidrs())) {
            return false;
        }
        if ((metricValue.getPorts() == null) ^ (getPorts() == null)) {
            return false;
        }
        return metricValue.getPorts() == null || metricValue.getPorts().equals(getPorts());
    }

    public List<String> getCidrs() {
        return this.cidrs;
    }

    public Long getCount() {
        return this.count;
    }

    public List<Integer> getPorts() {
        return this.ports;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getCount() == null ? 0 : getCount().hashCode()) + 31) * 31) + (getCidrs() == null ? 0 : getCidrs().hashCode())) * 31;
        if (getPorts() != null) {
            i = getPorts().hashCode();
        }
        return hashCode + i;
    }

    public void setCidrs(Collection<String> collection) {
        if (collection == null) {
            this.cidrs = null;
        } else {
            this.cidrs = new ArrayList(collection);
        }
    }

    public void setCount(Long l) {
        this.count = l;
    }

    public void setPorts(Collection<Integer> collection) {
        if (collection == null) {
            this.ports = null;
        } else {
            this.ports = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCount() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("count: ");
            outline1072.append(getCount());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getCidrs() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("cidrs: ");
            outline1073.append(getCidrs());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPorts() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ports: ");
            outline1074.append(getPorts());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public MetricValue withCidrs(String... strArr) {
        if (getCidrs() == null) {
            this.cidrs = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.cidrs.add(str);
        }
        return this;
    }

    public MetricValue withCount(Long l) {
        this.count = l;
        return this;
    }

    public MetricValue withPorts(Integer... numArr) {
        if (getPorts() == null) {
            this.ports = new ArrayList(numArr.length);
        }
        for (Integer num : numArr) {
            this.ports.add(num);
        }
        return this;
    }

    public MetricValue withCidrs(Collection<String> collection) {
        setCidrs(collection);
        return this;
    }

    public MetricValue withPorts(Collection<Integer> collection) {
        setPorts(collection);
        return this;
    }
}
