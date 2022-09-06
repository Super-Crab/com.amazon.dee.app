package amazon.speech.tts.internal;

import android.util.JsonWriter;
import android.util.Log;
import java.io.IOException;
import java.io.StringWriter;
/* loaded from: classes.dex */
public class Payload {
    private static final String TAG = "Payload";

    public String getPayload() {
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            writeContents(jsonWriter);
            jsonWriter.flush();
            return stringWriter.toString();
        } catch (IOException e) {
            String str = TAG;
            Log.e(str, "Exception generating JSON from payload: " + e);
            return null;
        }
    }

    @Deprecated
    protected void writeContents(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.endObject();
    }

    public void writeJsonContents(JsonWriter jsonWriter) throws IOException {
        writeContents(jsonWriter);
    }
}
