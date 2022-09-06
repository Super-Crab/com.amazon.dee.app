package com.amazonaws.services.kinesis.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class GetRecordsResult implements Serializable {
    private Long millisBehindLatest;
    private String nextShardIterator;
    private List<Record> records = new ArrayList();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetRecordsResult)) {
            return false;
        }
        GetRecordsResult getRecordsResult = (GetRecordsResult) obj;
        if ((getRecordsResult.getRecords() == null) ^ (getRecords() == null)) {
            return false;
        }
        if (getRecordsResult.getRecords() != null && !getRecordsResult.getRecords().equals(getRecords())) {
            return false;
        }
        if ((getRecordsResult.getNextShardIterator() == null) ^ (getNextShardIterator() == null)) {
            return false;
        }
        if (getRecordsResult.getNextShardIterator() != null && !getRecordsResult.getNextShardIterator().equals(getNextShardIterator())) {
            return false;
        }
        if ((getRecordsResult.getMillisBehindLatest() == null) ^ (getMillisBehindLatest() == null)) {
            return false;
        }
        return getRecordsResult.getMillisBehindLatest() == null || getRecordsResult.getMillisBehindLatest().equals(getMillisBehindLatest());
    }

    public Long getMillisBehindLatest() {
        return this.millisBehindLatest;
    }

    public String getNextShardIterator() {
        return this.nextShardIterator;
    }

    public List<Record> getRecords() {
        return this.records;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getRecords() == null ? 0 : getRecords().hashCode()) + 31) * 31) + (getNextShardIterator() == null ? 0 : getNextShardIterator().hashCode())) * 31;
        if (getMillisBehindLatest() != null) {
            i = getMillisBehindLatest().hashCode();
        }
        return hashCode + i;
    }

    public void setMillisBehindLatest(Long l) {
        this.millisBehindLatest = l;
    }

    public void setNextShardIterator(String str) {
        this.nextShardIterator = str;
    }

    public void setRecords(Collection<Record> collection) {
        if (collection == null) {
            this.records = null;
        } else {
            this.records = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRecords() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Records: ");
            outline1072.append(getRecords());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextShardIterator() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("NextShardIterator: ");
            outline1073.append(getNextShardIterator());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMillisBehindLatest() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("MillisBehindLatest: ");
            outline1074.append(getMillisBehindLatest());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetRecordsResult withMillisBehindLatest(Long l) {
        this.millisBehindLatest = l;
        return this;
    }

    public GetRecordsResult withNextShardIterator(String str) {
        this.nextShardIterator = str;
        return this;
    }

    public GetRecordsResult withRecords(Record... recordArr) {
        if (getRecords() == null) {
            this.records = new ArrayList(recordArr.length);
        }
        for (Record record : recordArr) {
            this.records.add(record);
        }
        return this;
    }

    public GetRecordsResult withRecords(Collection<Record> collection) {
        setRecords(collection);
        return this;
    }
}
