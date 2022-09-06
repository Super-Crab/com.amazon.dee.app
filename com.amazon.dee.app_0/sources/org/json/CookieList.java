package org.json;

import com.amazon.alexa.mobilytics.configuration.Config;
import java.util.Iterator;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes5.dex */
public class CookieList {
    public static JSONObject toJSONObject(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONTokener jSONTokener = new JSONTokener(str);
        while (jSONTokener.more()) {
            String unescape = Cookie.unescape(jSONTokener.nextTo(Chars.EQ));
            jSONTokener.next(Chars.EQ);
            jSONObject.put(unescape, Cookie.unescape(jSONTokener.nextTo(';')));
            jSONTokener.next();
        }
        return jSONObject;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = false;
        while (keys.hasNext()) {
            String obj = keys.next().toString();
            if (!jSONObject.isNull(obj)) {
                if (z) {
                    stringBuffer.append(';');
                }
                stringBuffer.append(Cookie.escape(obj));
                stringBuffer.append(Config.Compare.EQUAL_TO);
                stringBuffer.append(Cookie.escape(jSONObject.getString(obj)));
                z = true;
            }
        }
        return stringBuffer.toString();
    }
}
