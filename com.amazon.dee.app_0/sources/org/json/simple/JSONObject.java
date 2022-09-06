package org.json.simple;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes5.dex */
public class JSONObject extends HashMap implements Map, JSONAware, JSONStreamAware {
    private static final long serialVersionUID = -503443796854799292L;

    public static String escape(String str) {
        return JSONValue.escape(str);
    }

    private static String toJSONString(String str, Object obj, StringBuffer stringBuffer) {
        stringBuffer.append('\"');
        if (str == null) {
            stringBuffer.append("null");
        } else {
            JSONValue.escape(str, stringBuffer);
        }
        stringBuffer.append('\"');
        stringBuffer.append(JsonReaderKt.COLON);
        stringBuffer.append(JSONValue.toJSONString(obj));
        return stringBuffer.toString();
    }

    public static String toJSONString(Map map) {
        if (map == null) {
            return "null";
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        stringBuffer.append(JsonReaderKt.BEGIN_OBJ);
        for (Map.Entry entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(JsonReaderKt.COMMA);
            }
            toJSONString(String.valueOf(entry.getKey()), entry.getValue(), stringBuffer);
        }
        stringBuffer.append(JsonReaderKt.END_OBJ);
        return stringBuffer.toString();
    }

    public static String toString(String str, Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        toJSONString(str, obj, stringBuffer);
        return stringBuffer.toString();
    }

    public static void writeJSONString(Map map, Writer writer) throws IOException {
        if (map == null) {
            writer.write("null");
            return;
        }
        boolean z = true;
        writer.write(123);
        for (Map.Entry entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                writer.write(44);
            }
            writer.write(34);
            writer.write(escape(String.valueOf(entry.getKey())));
            writer.write(34);
            writer.write(58);
            JSONValue.writeJSONString(entry.getValue(), writer);
        }
        writer.write(125);
    }

    @Override // org.json.simple.JSONAware
    public String toJSONString() {
        return toJSONString(this);
    }

    @Override // java.util.AbstractMap
    public String toString() {
        return toJSONString();
    }

    @Override // org.json.simple.JSONStreamAware
    public void writeJSONString(Writer writer) throws IOException {
        writeJSONString(this, writer);
    }
}
