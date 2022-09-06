package com.amazonaws.services.kinesis.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum MetricsName {
    IncomingBytes("IncomingBytes"),
    IncomingRecords("IncomingRecords"),
    OutgoingBytes("OutgoingBytes"),
    OutgoingRecords("OutgoingRecords"),
    WriteProvisionedThroughputExceeded("WriteProvisionedThroughputExceeded"),
    ReadProvisionedThroughputExceeded("ReadProvisionedThroughputExceeded"),
    IteratorAgeMilliseconds("IteratorAgeMilliseconds"),
    ALL("ALL");
    
    private static final Map<String, MetricsName> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("IncomingBytes", IncomingBytes);
        enumMap.put("IncomingRecords", IncomingRecords);
        enumMap.put("OutgoingBytes", OutgoingBytes);
        enumMap.put("OutgoingRecords", OutgoingRecords);
        enumMap.put("WriteProvisionedThroughputExceeded", WriteProvisionedThroughputExceeded);
        enumMap.put("ReadProvisionedThroughputExceeded", ReadProvisionedThroughputExceeded);
        enumMap.put("IteratorAgeMilliseconds", IteratorAgeMilliseconds);
        enumMap.put("ALL", ALL);
    }

    MetricsName(String str) {
        this.value = str;
    }

    public static MetricsName fromValue(String str) {
        if (str != null && !str.isEmpty()) {
            if (enumMap.containsKey(str)) {
                return enumMap.get(str);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Cannot create enum from ", str, " value!"));
        }
        throw new IllegalArgumentException("Value cannot be null or empty!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
