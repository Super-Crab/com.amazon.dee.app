package org.webrtc;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public class StatsReport {
    public final String id;
    public final double timestamp;
    public final String type;
    public final Value[] values;

    /* loaded from: classes5.dex */
    public static class Value {
        public final String name;
        public final String value;

        public Value(String str, String str2) {
            this.name = str;
            this.value = str2;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
            outline107.append(this.name);
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            return GeneratedOutlineSupport1.outline91(outline107, this.value, "]");
        }
    }

    public StatsReport(String str, String str2, double d, Value[] valueArr) {
        this.id = str;
        this.type = str2;
        this.timestamp = d;
        this.values = valueArr;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("id: ");
        outline107.append(this.id);
        outline107.append(", type: ");
        outline107.append(this.type);
        outline107.append(", timestamp: ");
        outline107.append(this.timestamp);
        outline107.append(", values: ");
        int i = 0;
        while (true) {
            Value[] valueArr = this.values;
            if (i < valueArr.length) {
                outline107.append(valueArr[i].toString());
                outline107.append(", ");
                i++;
            } else {
                return outline107.toString();
            }
        }
    }
}
