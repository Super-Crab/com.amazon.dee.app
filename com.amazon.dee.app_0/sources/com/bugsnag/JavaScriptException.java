package com.bugsnag;

import androidx.annotation.NonNull;
import com.bugsnag.android.JsonStream;
import com.dee.app.metrics.MetricsConstants;
import com.facebook.react.devsupport.StackTraceHelper;
import java.io.IOException;
/* compiled from: BugsnagReactNative.java */
/* loaded from: classes11.dex */
class JavaScriptException extends Exception implements JsonStream.Streamable {
    private static final String EXCEPTION_TYPE = "browserjs";
    private static final long serialVersionUID = 1175784680140218622L;
    private final String name;
    private final String rawStacktrace;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JavaScriptException(String str, String str2, String str3) {
        super(str2);
        this.name = str;
        this.rawStacktrace = str3;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        String[] split;
        jsonStream.beginObject();
        jsonStream.mo6745name("errorClass").value(this.name);
        jsonStream.mo6745name("message").value(getLocalizedMessage());
        jsonStream.mo6745name("type").value(EXCEPTION_TYPE);
        jsonStream.mo6745name("stacktrace");
        jsonStream.beginArray();
        for (String str : this.rawStacktrace.split("\\n")) {
            jsonStream.beginObject();
            String[] split2 = str.split("@", 2);
            String str2 = split2[0];
            if (split2.length == 2) {
                jsonStream.mo6745name(MetricsConstants.NativeFetch.METHOD).value(split2[0]);
                str2 = split2[1];
            }
            int lastIndexOf = str2.lastIndexOf(":");
            if (lastIndexOf != -1) {
                String substring = str2.substring(lastIndexOf + 1, str2.length());
                try {
                    jsonStream.mo6745name("columnNumber").value(Integer.parseInt(substring));
                } catch (NumberFormatException unused) {
                    BugsnagReactNative.logger.info(String.format("Failed to parse column: '%s'", substring));
                }
                str2 = str2.substring(0, lastIndexOf);
            }
            int lastIndexOf2 = str2.lastIndexOf(":");
            if (lastIndexOf2 != -1) {
                String substring2 = str2.substring(lastIndexOf2 + 1, str2.length());
                try {
                    jsonStream.mo6745name(StackTraceHelper.LINE_NUMBER_KEY).value(Integer.parseInt(substring2));
                } catch (NumberFormatException unused2) {
                    BugsnagReactNative.logger.info(String.format("Failed to parse lineNumber: '%s'", substring2));
                }
                str2 = str2.substring(0, lastIndexOf2);
            }
            jsonStream.mo6745name("file").value(str2);
            jsonStream.endObject();
        }
        jsonStream.endArray();
        jsonStream.endObject();
    }
}
