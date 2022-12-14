package org.json;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.util.Iterator;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes5.dex */
public class HTTP {
    public static final String CRLF = "\r\n";

    public static JSONObject toJSONObject(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        HTTPTokener hTTPTokener = new HTTPTokener(str);
        String nextToken = hTTPTokener.nextToken();
        if (nextToken.toUpperCase().startsWith("HTTP")) {
            jSONObject.put("HTTP-Version", nextToken);
            jSONObject.put("Status-Code", hTTPTokener.nextToken());
            jSONObject.put("Reason-Phrase", hTTPTokener.nextTo((char) 0));
            hTTPTokener.next();
        } else {
            jSONObject.put("Method", nextToken);
            jSONObject.put("Request-URI", hTTPTokener.nextToken());
            jSONObject.put("HTTP-Version", hTTPTokener.nextToken());
        }
        while (hTTPTokener.more()) {
            String nextTo = hTTPTokener.nextTo(JsonReaderKt.COLON);
            hTTPTokener.next(JsonReaderKt.COLON);
            jSONObject.put(nextTo, hTTPTokener.nextTo((char) 0));
            hTTPTokener.next();
        }
        return jSONObject;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        StringBuffer stringBuffer = new StringBuffer();
        if (jSONObject.has("Status-Code") && jSONObject.has("Reason-Phrase")) {
            stringBuffer.append(jSONObject.getString("HTTP-Version"));
            stringBuffer.append(Chars.SPACE);
            stringBuffer.append(jSONObject.getString("Status-Code"));
            stringBuffer.append(Chars.SPACE);
            stringBuffer.append(jSONObject.getString("Reason-Phrase"));
        } else if (jSONObject.has("Method") && jSONObject.has("Request-URI")) {
            stringBuffer.append(jSONObject.getString("Method"));
            stringBuffer.append(Chars.SPACE);
            stringBuffer.append('\"');
            stringBuffer.append(jSONObject.getString("Request-URI"));
            stringBuffer.append('\"');
            stringBuffer.append(Chars.SPACE);
            stringBuffer.append(jSONObject.getString("HTTP-Version"));
        } else {
            throw new JSONException("Not enough material for an HTTP header.");
        }
        stringBuffer.append("\r\n");
        while (keys.hasNext()) {
            String obj = keys.next().toString();
            if (!obj.equals("HTTP-Version") && !obj.equals("Status-Code") && !obj.equals("Reason-Phrase") && !obj.equals("Method") && !obj.equals("Request-URI") && !jSONObject.isNull(obj)) {
                stringBuffer.append(obj);
                stringBuffer.append(RealTimeTextConstants.COLON_SPACE);
                stringBuffer.append(jSONObject.getString(obj));
                stringBuffer.append("\r\n");
            }
        }
        stringBuffer.append("\r\n");
        return stringBuffer.toString();
    }
}
