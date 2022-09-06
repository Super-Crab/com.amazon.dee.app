package amazon.speech.nexusclient;

import android.content.Intent;
import android.text.TextUtils;
/* loaded from: classes.dex */
public class NexusIntentDecorator {
    public static void decorate(Intent intent, String str) {
        if (intent != null && !TextUtils.isEmpty(str)) {
            intent.putExtra(NexusConstant.INTENT_KEY_SESSION_ID, str);
        }
    }
}
