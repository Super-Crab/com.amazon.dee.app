package amazon.speech.simclient;

import android.content.res.Resources;
import android.net.Uri;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface ITtsPlayer {
    void acquireAudioFocusLock();

    void cancel();

    boolean isSpeaking();

    void prepare();

    void releaseAudioFocusLock();

    void setSimClientForTts(SimClient simClient);

    void setVolume(float f);

    void speakResource(int i, int i2, TtsListener ttsListener);

    void speakResource(int i, int i2, TtsListener ttsListener, String str);

    void speakResource(Resources resources, int i, int i2, TtsListener ttsListener);

    void speakResource(Resources resources, int i, int i2, TtsListener ttsListener, String str);

    void speakUri(Uri uri, String str, TtsListener ttsListener);

    void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener);

    void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2);

    void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2, String str3);

    void speakUri(Uri uri, String str, TtsListener ttsListener, String str2);
}
