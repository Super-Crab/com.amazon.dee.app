package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum ComparisonOperator {
    LessThan("less-than"),
    LessThanEquals("less-than-equals"),
    GreaterThan("greater-than"),
    GreaterThanEquals("greater-than-equals"),
    InCidrSet("in-cidr-set"),
    NotInCidrSet("not-in-cidr-set"),
    InPortSet("in-port-set"),
    NotInPortSet("not-in-port-set");
    
    private static final Map<String, ComparisonOperator> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("less-than", LessThan);
        enumMap.put("less-than-equals", LessThanEquals);
        enumMap.put("greater-than", GreaterThan);
        enumMap.put("greater-than-equals", GreaterThanEquals);
        enumMap.put("in-cidr-set", InCidrSet);
        enumMap.put("not-in-cidr-set", NotInCidrSet);
        enumMap.put("in-port-set", InPortSet);
        enumMap.put("not-in-port-set", NotInPortSet);
    }

    ComparisonOperator(String str) {
        this.value = str;
    }

    public static ComparisonOperator fromValue(String str) {
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
