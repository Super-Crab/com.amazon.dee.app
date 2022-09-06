package amazon.speech.model;

import android.content.Context;
import android.content.Intent;
/* loaded from: classes.dex */
public class AlexaTtsAlert {
    public static final String ALEXA_SPEAK_ACTION = "com.amazon.speech.ALEXA_SPEAK";
    public static final String EXTRA_ANALYZER_STATES = "com.amazon.speech.EXTRA_ANALYZER_STATES";
    public static final String EXTRA_CONTENT_TOKEN = "com.amazon.speech.EXTRA_CONTENT_TOKEN";
    public static final String EXTRA_IS_SPEAKING = "com.amazon.speech.EXTRA_IS_SPEAKING";
    private final Context mContext;

    public AlexaTtsAlert(Context context) {
        if (context != null) {
            this.mContext = context;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void alertTts(boolean z, String str, String str2) {
        Intent intent = new Intent("com.amazon.speech.ALEXA_SPEAK");
        intent.putExtra("com.amazon.speech.EXTRA_IS_SPEAKING", z);
        intent.putExtra("com.amazon.speech.EXTRA_CONTENT_TOKEN", str);
        intent.putExtra("com.amazon.speech.EXTRA_ANALYZER_STATES", str2);
        this.mContext.sendBroadcast(intent);
    }
}
