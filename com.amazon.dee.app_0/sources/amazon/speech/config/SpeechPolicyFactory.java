package amazon.speech.config;

import amazon.speech.options.client.ClientPolicyProvider;
import android.content.Context;
import android.os.Build;
/* loaded from: classes.dex */
public class SpeechPolicyFactory {
    private static BaseSpeechClientPolicy sInstance;

    protected SpeechPolicyFactory() {
    }

    public static BaseSpeechClientPolicy getPolicy(Context context) {
        if (sInstance == null) {
            synchronized (BaseSpeechClientPolicy.class) {
                if (sInstance == null) {
                    sInstance = ((ClientPolicyProvider) context.getApplicationContext()).getPolicy();
                }
            }
        }
        return sInstance;
    }

    public static void setPolicy(BaseSpeechClientPolicy baseSpeechClientPolicy) {
        if ("user".equals(Build.TYPE)) {
            return;
        }
        synchronized (BaseSpeechClientPolicy.class) {
            sInstance = baseSpeechClientPolicy;
        }
    }
}
