package org.json;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes5.dex */
public class XML {
    public static final Character AMP = new Character(Typography.amp);
    public static final Character APOS = new Character(Chars.QUOTE);
    public static final Character BANG = new Character('!');
    public static final Character EQ = new Character(Chars.EQ);
    public static final Character GT = new Character(Typography.greater);
    public static final Character LT = new Character(Typography.less);
    public static final Character QUEST = new Character(Constants.DEFAULT_IMAGE_CHAR);
    public static final Character QUOT = new Character('\"');
    public static final Character SLASH = new Character('/');

    public static String escape(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                stringBuffer.append("&quot;");
            } else if (charAt == '&') {
                stringBuffer.append("&amp;");
            } else if (charAt == '<') {
                stringBuffer.append("&lt;");
            } else if (charAt != '>') {
                stringBuffer.append(charAt);
            } else {
                stringBuffer.append("&gt;");
            }
        }
        return stringBuffer.toString();
    }

    public static void noSpace(String str) throws JSONException {
        int length = str.length();
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                if (Character.isWhitespace(str.charAt(i))) {
                    throw new JSONException(GeneratedOutlineSupport1.outline75("'", str, "' contains a space character."));
                }
            }
            return;
        }
        throw new JSONException("Empty string.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ef, code lost:
        r7 = r10.nextToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00f5, code lost:
        if ((r7 instanceof java.lang.String) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0107, code lost:
        throw r10.syntaxError("Missing value");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean parse(org.json.XMLTokener r10, org.json.JSONObject r11, java.lang.String r12) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.XML.parse(org.json.XMLTokener, org.json.JSONObject, java.lang.String):boolean");
    }

    public static JSONObject toJSONObject(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        XMLTokener xMLTokener = new XMLTokener(str);
        while (xMLTokener.more() && xMLTokener.skipPast(Config.Compare.LESS_THAN)) {
            parse(xMLTokener, jSONObject, null);
        }
        return jSONObject;
    }

    public static String toString(Object obj) throws JSONException {
        return toString(obj, null);
    }

    public static String toString(Object obj, String str) throws JSONException {
        StringBuffer stringBuffer = new StringBuffer();
        if (obj instanceof JSONObject) {
            if (str != null) {
                stringBuffer.append(Typography.less);
                stringBuffer.append(str);
                stringBuffer.append(Typography.greater);
            }
            JSONObject jSONObject = (JSONObject) obj;
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String obj2 = keys.next().toString();
                Object opt = jSONObject.opt(obj2);
                if (opt == null) {
                    opt = "";
                }
                if (opt instanceof String) {
                    String str2 = (String) opt;
                }
                if (obj2.equals("content")) {
                    if (opt instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) opt;
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            if (i > 0) {
                                stringBuffer.append('\n');
                            }
                            stringBuffer.append(escape(jSONArray.get(i).toString()));
                        }
                    } else {
                        stringBuffer.append(escape(opt.toString()));
                    }
                } else if (opt instanceof JSONArray) {
                    JSONArray jSONArray2 = (JSONArray) opt;
                    int length2 = jSONArray2.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        Object obj3 = jSONArray2.get(i2);
                        if (obj3 instanceof JSONArray) {
                            stringBuffer.append(Typography.less);
                            stringBuffer.append(obj2);
                            stringBuffer.append(Typography.greater);
                            stringBuffer.append(toString(obj3));
                            stringBuffer.append("</");
                            stringBuffer.append(obj2);
                            stringBuffer.append(Typography.greater);
                        } else {
                            stringBuffer.append(toString(obj3, obj2));
                        }
                    }
                } else if (opt.equals("")) {
                    stringBuffer.append(Typography.less);
                    stringBuffer.append(obj2);
                    stringBuffer.append("/>");
                } else {
                    stringBuffer.append(toString(opt, obj2));
                }
            }
            if (str != null) {
                stringBuffer.append("</");
                stringBuffer.append(str);
                stringBuffer.append(Typography.greater);
            }
            return stringBuffer.toString();
        } else if (obj instanceof JSONArray) {
            JSONArray jSONArray3 = (JSONArray) obj;
            int length3 = jSONArray3.length();
            for (int i3 = 0; i3 < length3; i3++) {
                stringBuffer.append(toString(jSONArray3.opt(i3), str == null ? "array" : str));
            }
            return stringBuffer.toString();
        } else {
            String escape = obj == null ? "null" : escape(obj.toString());
            if (str == null) {
                return GeneratedOutlineSupport1.outline75(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, escape, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            }
            return escape.length() == 0 ? GeneratedOutlineSupport1.outline75(Config.Compare.LESS_THAN, str, "/>") : GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline116(Config.Compare.LESS_THAN, str, Config.Compare.GREATER_THAN, escape, "</"), str, Config.Compare.GREATER_THAN);
        }
    }
}
