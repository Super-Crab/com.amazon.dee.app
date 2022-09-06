package com.amazon.clouddrive.cdasdk.cds.node;

import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public final class NodeNameFilter {
    @NonNull
    private final String filter;

    /* loaded from: classes11.dex */
    public static class Builder {
        private static final Set<Character> ESCAPED_CHARACTERS = new HashSet(Arrays.asList('+', '-', Character.valueOf(Typography.amp), '|', '!', '(', ')', Character.valueOf(JsonReaderKt.BEGIN_OBJ), Character.valueOf(JsonReaderKt.END_OBJ), Character.valueOf(JsonReaderKt.BEGIN_LIST), Character.valueOf(JsonReaderKt.END_LIST), '^', Character.valueOf(Chars.QUOTE), '\"', '~', '*', Character.valueOf(Constants.DEFAULT_IMAGE_CHAR), Character.valueOf(JsonReaderKt.COLON), '\\', Character.valueOf(Chars.SPACE)));
        private static final String NAME = "name:";
        @NonNull
        private final String nodeName;

        public Builder(String str) {
            this.nodeName = str;
        }

        private String getEscapedName() {
            char[] charArray;
            StringBuilder sb = new StringBuilder();
            for (char c : this.nodeName.toCharArray()) {
                sb.append(ESCAPED_CHARACTERS.contains(Character.valueOf(c)) ? GeneratedOutlineSupport1.outline47("\\", c) : Character.valueOf(c));
            }
            return sb.toString();
        }

        public NodeNameFilter build() {
            return new NodeNameFilter(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("name:\""), getEscapedName(), EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED));
        }
    }

    public NodeNameFilter(String str) {
        this.filter = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NodeNameFilter)) {
            return false;
        }
        String filter = getFilter();
        String filter2 = ((NodeNameFilter) obj).getFilter();
        return filter != null ? filter.equals(filter2) : filter2 == null;
    }

    public String getFilter() {
        return this.filter;
    }

    public int hashCode() {
        String filter = getFilter();
        return 59 + (filter == null ? 43 : filter.hashCode());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NodeNameFilter(filter=");
        outline107.append(getFilter());
        outline107.append(")");
        return outline107.toString();
    }
}
