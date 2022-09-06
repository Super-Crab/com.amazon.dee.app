package amazon.speech.csmshark;

import amazon.speech.util.DebugUtil;
import amazon.speech.util.Log;
import android.util.Base64;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
class SerializeSharkable {
    private static final String LONG_LOG_MESSAGE_HEADER = "Message %s part %s of %s - ";
    private static final Pattern stringChunker;
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.SHARK, SerializeSharkable.class);
    static final int MAX_CHARS_PER_LOG = 3947;

    static {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(".{1,");
        outline107.append(MAX_CHARS_PER_LOG);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        stringChunker = Pattern.compile(outline107.toString());
    }

    SerializeSharkable() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> convertToLoggable(Sharkable sharkable) {
        String jSONObject = serializeSharkable(sharkable).toString();
        ArrayList arrayList = new ArrayList();
        if (jSONObject != null) {
            try {
                String shortUUID = shortUUID(UUID.randomUUID());
                int length = (jSONObject.length() / MAX_CHARS_PER_LOG) + (jSONObject.length() % MAX_CHARS_PER_LOG > 0 ? 1 : 0);
                String str = TAG;
                Log.d(str, "converting sharkable into parts: " + length);
                Matcher matcher = stringChunker.matcher(jSONObject);
                int i = 1;
                while (matcher.find()) {
                    StringBuilder sb = new StringBuilder();
                    int i2 = i + 1;
                    sb.append(String.format(LONG_LOG_MESSAGE_HEADER, shortUUID, Integer.valueOf(i), Integer.valueOf(length)));
                    sb.append(jSONObject.substring(matcher.start(), matcher.end()));
                    arrayList.add(sb.toString());
                    i = i2;
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to convert sharkable to log messages", e);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject serializeSharkable(Sharkable sharkable) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", sharkable.type);
            jSONObject.put("contents", sharkable.contents);
            jSONObject.put("timestamp_ms", sharkable.timeStamp);
        } catch (JSONException e) {
            Log.e(TAG, "Error populating JSON", e);
        }
        return jSONObject;
    }

    private static String shortUUID(UUID uuid) {
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.putLong(uuid.getMostSignificantBits());
        allocate.putLong(uuid.getLeastSignificantBits());
        return Base64.encodeToString(allocate.array(), 11);
    }
}
