package org.json.simple;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes5.dex */
public class JSONArray extends ArrayList implements List, JSONAware, JSONStreamAware {
    private static final long serialVersionUID = 3957988303675231981L;

    public static String toJSONString(List list) {
        if (list == null) {
            return "null";
        }
        boolean z = true;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(JsonReaderKt.BEGIN_LIST);
        for (Object obj : list) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(JsonReaderKt.COMMA);
            }
            if (obj == null) {
                stringBuffer.append("null");
            } else {
                stringBuffer.append(JSONValue.toJSONString(obj));
            }
        }
        stringBuffer.append(JsonReaderKt.END_LIST);
        return stringBuffer.toString();
    }

    public static void writeJSONString(List list, Writer writer) throws IOException {
        if (list == null) {
            writer.write("null");
            return;
        }
        boolean z = true;
        writer.write(91);
        for (Object obj : list) {
            if (z) {
                z = false;
            } else {
                writer.write(44);
            }
            if (obj == null) {
                writer.write("null");
            } else {
                JSONValue.writeJSONString(obj, writer);
            }
        }
        writer.write(93);
    }

    @Override // org.json.simple.JSONAware
    public String toJSONString() {
        return toJSONString(this);
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return toJSONString();
    }

    @Override // org.json.simple.JSONStreamAware
    public void writeJSONString(Writer writer) throws IOException {
        writeJSONString(this, writer);
    }
}
