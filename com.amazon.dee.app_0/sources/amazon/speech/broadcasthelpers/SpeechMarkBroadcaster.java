package amazon.speech.broadcasthelpers;

import android.content.Context;
import android.content.Intent;
import com.amazon.communication.support.CsmPackageChangedBroadcastReceiver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class SpeechMarkBroadcaster {
    public static final String SPEECH_MARK = "com.amazon.speech.SPEECH_MARK";
    public static final String SPEECH_MARKS_RECEIVER_PERMISSION = "amazon.speech.permission.RECEIVE_SPEECH_MARKS";
    public static final String SPEECH_MARK_ERROR = "com.amazon.speech.SPEECH_MARK_ERROR";
    public static final String SPEECH_MARK_STRING = "com.amazon.speech.SPEECH_MARK_STRING";
    private static final String TAG = "SpeechMarkBroadcaster";
    private List<String> mBroadcastPackages = new ArrayList(Arrays.asList("amazon.speech.wakewordservice", "com.amazon.device.gadgetscontrolmanager", "com.amazon.speechui", CsmPackageChangedBroadcastReceiver.CSM_PACKAGE_NAME, "com.amazon.glorialist", "com.amazon.ftv.glorialist", "com.amazon.alexa.multimodal.gloria"));
    final Context mContext;

    public SpeechMarkBroadcaster(Context context) {
        if (context != null) {
            this.mContext = context;
            this.mBroadcastPackages.add(this.mContext.getPackageName());
            return;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public void errorParsingSpeechmarks(String str) {
        for (String str2 : this.mBroadcastPackages) {
            Intent intent = new Intent(SPEECH_MARK);
            intent.putExtra(SPEECH_MARK_ERROR, str);
            intent.setPackage(str2);
            this.mContext.sendBroadcast(intent, SPEECH_MARKS_RECEIVER_PERMISSION);
        }
    }

    public void processSpeechMark(String str) {
        for (String str2 : this.mBroadcastPackages) {
            Intent intent = new Intent(SPEECH_MARK);
            intent.putExtra(SPEECH_MARK_STRING, str);
            intent.setPackage(str2);
            this.mContext.sendBroadcast(intent, SPEECH_MARKS_RECEIVER_PERMISSION);
        }
    }
}
