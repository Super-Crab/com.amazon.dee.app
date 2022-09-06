package org.json;

import java.util.Iterator;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes5.dex */
public class JSONML {
    /* JADX WARN: Code restructure failed: missing block: B:109:0x015b, code lost:
        throw r9.syntaxError("Reserved attribute.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0164, code lost:
        r7 = r9.nextToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x016a, code lost:
        if ((r7 instanceof java.lang.String) == false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x017d, code lost:
        throw r9.syntaxError("Missing value");
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008c, code lost:
        throw r9.syntaxError("Expected 'CDATA['");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.Object parse(org.json.XMLTokener r9, boolean r10, org.json.JSONArray r11) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 437
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONML.parse(org.json.XMLTokener, boolean, org.json.JSONArray):java.lang.Object");
    }

    public static JSONArray toJSONArray(String str) throws JSONException {
        return toJSONArray(new XMLTokener(str));
    }

    public static JSONObject toJSONObject(XMLTokener xMLTokener) throws JSONException {
        return (JSONObject) parse(xMLTokener, false, null);
    }

    public static String toString(JSONArray jSONArray) throws JSONException {
        int i;
        StringBuffer stringBuffer = new StringBuffer();
        String string = jSONArray.getString(0);
        XML.noSpace(string);
        String escape = XML.escape(string);
        stringBuffer.append(Typography.less);
        stringBuffer.append(escape);
        Object opt = jSONArray.opt(1);
        if (opt instanceof JSONObject) {
            i = 2;
            JSONObject jSONObject = (JSONObject) opt;
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                XML.noSpace(obj);
                String optString = jSONObject.optString(obj);
                if (optString != null) {
                    stringBuffer.append(Chars.SPACE);
                    stringBuffer.append(XML.escape(obj));
                    stringBuffer.append(Chars.EQ);
                    stringBuffer.append('\"');
                    stringBuffer.append(XML.escape(optString));
                    stringBuffer.append('\"');
                }
            }
        } else {
            i = 1;
        }
        int length = jSONArray.length();
        if (i >= length) {
            stringBuffer.append('/');
            stringBuffer.append(Typography.greater);
        } else {
            stringBuffer.append(Typography.greater);
            do {
                Object obj2 = jSONArray.get(i);
                i++;
                if (obj2 != null) {
                    if (obj2 instanceof String) {
                        stringBuffer.append(XML.escape(obj2.toString()));
                        continue;
                    } else if (obj2 instanceof JSONObject) {
                        stringBuffer.append(toString((JSONObject) obj2));
                        continue;
                    } else if (obj2 instanceof JSONArray) {
                        stringBuffer.append(toString((JSONArray) obj2));
                        continue;
                    } else {
                        continue;
                    }
                }
            } while (i < length);
            stringBuffer.append(Typography.less);
            stringBuffer.append('/');
            stringBuffer.append(escape);
            stringBuffer.append(Typography.greater);
        }
        return stringBuffer.toString();
    }

    public static JSONArray toJSONArray(XMLTokener xMLTokener) throws JSONException {
        return (JSONArray) parse(xMLTokener, true, null);
    }

    public static JSONObject toJSONObject(String str) throws JSONException {
        return toJSONObject(new XMLTokener(str));
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuffer stringBuffer = new StringBuffer();
        String optString = jSONObject.optString("tagName");
        if (optString == null) {
            return XML.escape(jSONObject.toString());
        }
        XML.noSpace(optString);
        String escape = XML.escape(optString);
        stringBuffer.append(Typography.less);
        stringBuffer.append(escape);
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String obj = keys.next().toString();
            if (!obj.equals("tagName") && !obj.equals("childNodes")) {
                XML.noSpace(obj);
                String optString2 = jSONObject.optString(obj);
                if (optString2 != null) {
                    stringBuffer.append(Chars.SPACE);
                    stringBuffer.append(XML.escape(obj));
                    stringBuffer.append(Chars.EQ);
                    stringBuffer.append('\"');
                    stringBuffer.append(XML.escape(optString2));
                    stringBuffer.append('\"');
                }
            }
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("childNodes");
        if (optJSONArray == null) {
            stringBuffer.append('/');
            stringBuffer.append(Typography.greater);
        } else {
            stringBuffer.append(Typography.greater);
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                Object obj2 = optJSONArray.get(i);
                if (obj2 != null) {
                    if (obj2 instanceof String) {
                        stringBuffer.append(XML.escape(obj2.toString()));
                    } else if (obj2 instanceof JSONObject) {
                        stringBuffer.append(toString((JSONObject) obj2));
                    } else if (obj2 instanceof JSONArray) {
                        stringBuffer.append(toString((JSONArray) obj2));
                    }
                }
            }
            stringBuffer.append(Typography.less);
            stringBuffer.append('/');
            stringBuffer.append(escape);
            stringBuffer.append(Typography.greater);
        }
        return stringBuffer.toString();
    }
}
