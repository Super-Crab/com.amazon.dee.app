package amazon.speech.broadcasthelpers;

import android.content.Context;
import android.content.Intent;
import com.amazon.communication.support.CsmPackageChangedBroadcastReceiver;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class AudioCaptionsBroadcaster {
    public static final String AUDIO_CAPTIONS = "com.amazon.speech.AUDIO_CAPTIONS";
    public static final String AUDIO_CAPTIONS_ERROR = "com.amazon.speech.AUDIO_CAPTIONS_ERROR";
    public static final String AUDIO_CAPTIONS_RECEIVER_PERMISSION = "amazon.speech.permission.RECEIVE_AUDIO_CAPTIONS";
    public static final String AUDIO_CAPTIONS_STRING = "com.amazon.speech.AUDIO_CAPTIONS_STRING";
    static final List<String> BROADCAST_PACKAGES = Arrays.asList("com.amazon.speechui", CsmPackageChangedBroadcastReceiver.CSM_PACKAGE_NAME);
    private static final String TAG = "AudioCaptionsBroadcaster";
    private final Context mContext;

    public AudioCaptionsBroadcaster(Context context) {
        if (context != null) {
            this.mContext = context;
            return;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public void broadcastAudioCaptions(String str) {
        for (String str2 : BROADCAST_PACKAGES) {
            Intent intent = new Intent(AUDIO_CAPTIONS);
            intent.putExtra(AUDIO_CAPTIONS_STRING, str);
            intent.setPackage(str2);
            this.mContext.sendBroadcast(intent, AUDIO_CAPTIONS_RECEIVER_PERMISSION);
        }
    }

    public void errorParsingAudioCaptions(String str) {
        for (String str2 : BROADCAST_PACKAGES) {
            Intent intent = new Intent(AUDIO_CAPTIONS);
            intent.putExtra(AUDIO_CAPTIONS_ERROR, str);
            intent.setPackage(str2);
            this.mContext.sendBroadcast(intent, AUDIO_CAPTIONS_RECEIVER_PERMISSION);
        }
    }
}
