package org.webrtc;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedList;
import java.util.List;
/* loaded from: classes5.dex */
public class MediaConstraints {
    public final List<KeyValuePair> mandatory = new LinkedList();
    public final List<KeyValuePair> optional = new LinkedList();

    /* loaded from: classes5.dex */
    public static class KeyValuePair {
        private final String key;
        private final String value;

        public KeyValuePair(String str, String str2) {
            this.key = str;
            this.value = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || KeyValuePair.class != obj.getClass()) {
                return false;
            }
            KeyValuePair keyValuePair = (KeyValuePair) obj;
            return this.key.equals(keyValuePair.key) && this.value.equals(keyValuePair.value);
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value.hashCode() + this.key.hashCode();
        }

        public String toString() {
            return this.key + RealTimeTextConstants.COLON_SPACE + this.value;
        }
    }

    private static String stringifyKeyValuePairList(List<KeyValuePair> list) {
        StringBuilder sb = new StringBuilder("[");
        for (KeyValuePair keyValuePair : list) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(keyValuePair.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mandatory: ");
        outline107.append(stringifyKeyValuePairList(this.mandatory));
        outline107.append(", optional: ");
        outline107.append(stringifyKeyValuePairList(this.optional));
        return outline107.toString();
    }
}
