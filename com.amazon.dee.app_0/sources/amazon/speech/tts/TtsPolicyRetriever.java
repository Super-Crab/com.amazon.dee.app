package amazon.speech.tts;

import amazon.speech.util.RuntimeDeviceTypeHelper;
import android.content.Context;
/* loaded from: classes.dex */
public class TtsPolicyRetriever {
    public int getTtsStreamTypeForSystem(Context context) {
        return RuntimeDeviceTypeHelper.getTtsStreamType(context);
    }
}
