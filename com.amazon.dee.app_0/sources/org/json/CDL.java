package org.json;

import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes5.dex */
public class CDL {
    private static String getValue(JSONTokener jSONTokener) throws JSONException {
        char next;
        while (true) {
            next = jSONTokener.next();
            if (next != ' ' && next != '\t') {
                break;
            }
        }
        if (next != 0) {
            if (next == '\"' || next == '\'') {
                return jSONTokener.nextString(next);
            }
            if (next != ',') {
                jSONTokener.back();
                return jSONTokener.nextTo(JsonReaderKt.COMMA);
            }
            jSONTokener.back();
            return "";
        }
        return null;
    }

    public static JSONArray rowToJSONArray(JSONTokener jSONTokener) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        while (true) {
            String value = getValue(jSONTokener);
            if (value != null) {
                if (jSONArray.length() == 0 && value.length() == 0) {
                    return null;
                }
                jSONArray.put(value);
                while (true) {
                    char next = jSONTokener.next();
                    if (next == ',') {
                        break;
                    } else if (next != ' ') {
                        if (next == '\n' || next == '\r' || next == 0) {
                            return jSONArray;
                        }
                        throw jSONTokener.syntaxError("Bad character '" + next + "' (" + ((int) next) + ").");
                    }
                }
            } else {
                return null;
            }
        }
    }

    public static JSONObject rowToJSONObject(JSONArray jSONArray, JSONTokener jSONTokener) throws JSONException {
        JSONArray rowToJSONArray = rowToJSONArray(jSONTokener);
        if (rowToJSONArray != null) {
            return rowToJSONArray.toJSONObject(jSONArray);
        }
        return null;
    }

    public static String rowToString(JSONArray jSONArray) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i > 0) {
                stringBuffer.append(JsonReaderKt.COMMA);
            }
            Object opt = jSONArray.opt(i);
            if (opt != null) {
                String obj = opt.toString();
                if (obj.indexOf(44) >= 0) {
                    if (obj.indexOf(34) >= 0) {
                        stringBuffer.append(Chars.QUOTE);
                        stringBuffer.append(obj);
                        stringBuffer.append(Chars.QUOTE);
                    } else {
                        stringBuffer.append('\"');
                        stringBuffer.append(obj);
                        stringBuffer.append('\"');
                    }
                } else {
                    stringBuffer.append(obj);
                }
            }
        }
        stringBuffer.append('\n');
        return stringBuffer.toString();
    }

    public static JSONArray toJSONArray(String str) throws JSONException {
        return toJSONArray(new JSONTokener(str));
    }

    public static String toString(JSONArray jSONArray) throws JSONException {
        JSONArray names;
        JSONObject optJSONObject = jSONArray.optJSONObject(0);
        if (optJSONObject == null || (names = optJSONObject.names()) == null) {
            return null;
        }
        return rowToString(names) + toString(names, jSONArray);
    }

    public static JSONArray toJSONArray(JSONTokener jSONTokener) throws JSONException {
        return toJSONArray(rowToJSONArray(jSONTokener), jSONTokener);
    }

    public static JSONArray toJSONArray(JSONArray jSONArray, String str) throws JSONException {
        return toJSONArray(jSONArray, new JSONTokener(str));
    }

    public static JSONArray toJSONArray(JSONArray jSONArray, JSONTokener jSONTokener) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        while (true) {
            JSONObject rowToJSONObject = rowToJSONObject(jSONArray, jSONTokener);
            if (rowToJSONObject == null) {
                break;
            }
            jSONArray2.put(rowToJSONObject);
        }
        if (jSONArray2.length() != 0) {
            return jSONArray2;
        }
        return null;
    }

    public static String toString(JSONArray jSONArray, JSONArray jSONArray2) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < jSONArray2.length(); i++) {
            JSONObject optJSONObject = jSONArray2.optJSONObject(i);
            if (optJSONObject != null) {
                stringBuffer.append(rowToString(optJSONObject.toJSONArray(jSONArray)));
            }
        }
        return stringBuffer.toString();
    }
}
