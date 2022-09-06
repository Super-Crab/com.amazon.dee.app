package com.amazon.clouddrive.extended.model.filters;

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
public class NodeNameFilter implements NodeFilterBuilder {
    private static final Set<Character> ESCAPED_CHARACTERS = new HashSet(Arrays.asList('+', '-', Character.valueOf(Typography.amp), '|', '!', '(', ')', Character.valueOf(JsonReaderKt.BEGIN_OBJ), Character.valueOf(JsonReaderKt.END_OBJ), Character.valueOf(JsonReaderKt.BEGIN_LIST), Character.valueOf(JsonReaderKt.END_LIST), '^', Character.valueOf(Chars.QUOTE), '\"', '~', '*', Character.valueOf(Constants.DEFAULT_IMAGE_CHAR), Character.valueOf(JsonReaderKt.COLON), '\\', Character.valueOf(Chars.SPACE)));
    private static final String NAME = "name:";
    private String nodeName;

    public static NodeNameFilter byName(String str) {
        NodeNameFilter nodeNameFilter = new NodeNameFilter();
        nodeNameFilter.nodeName = getEscapedName(str);
        return nodeNameFilter;
    }

    private static String getEscapedName(String str) {
        char[] charArray;
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(ESCAPED_CHARACTERS.contains(Character.valueOf(c)) ? GeneratedOutlineSupport1.outline47("\\", c) : Character.valueOf(c));
        }
        return sb.toString();
    }

    @Override // com.amazon.clouddrive.extended.model.filters.NodeFilterBuilder
    public String buildFilterQuery() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("name:\""), this.nodeName, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }
}
