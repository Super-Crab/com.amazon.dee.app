package org.webrtc;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes5.dex */
public class RTCStats {
    private final String id;
    private final Map<String, Object> members;
    private final long timestampUs;
    private final String type;

    public RTCStats(long j, String str, String str2, Map<String, Object> map) {
        this.timestampUs = j;
        this.type = str;
        this.id = str2;
        this.members = map;
    }

    private static void appendValue(StringBuilder sb, Object obj) {
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            sb.append(JsonReaderKt.BEGIN_LIST);
            for (int i = 0; i < objArr.length; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                appendValue(sb, objArr[i]);
            }
            sb.append(JsonReaderKt.END_LIST);
        } else if (obj instanceof String) {
            sb.append('\"');
            sb.append(obj);
            sb.append('\"');
        } else {
            sb.append(obj);
        }
    }

    public String getId() {
        return this.id;
    }

    public Map<String, Object> getMembers() {
        return this.members;
    }

    public double getTimestampUs() {
        return this.timestampUs;
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("{ timestampUs: ");
        outline107.append(this.timestampUs);
        outline107.append(", type: ");
        outline107.append(this.type);
        outline107.append(", id: ");
        outline107.append(this.id);
        for (Map.Entry<String, Object> entry : this.members.entrySet()) {
            outline107.append(", ");
            outline107.append(entry.getKey());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            appendValue(outline107, entry.getValue());
        }
        outline107.append(" }");
        return outline107.toString();
    }
}
