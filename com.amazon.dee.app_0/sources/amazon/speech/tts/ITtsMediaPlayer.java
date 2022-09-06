package amazon.speech.tts;

import android.content.res.Resources;
import android.net.Uri;
import java.io.InputStream;
/* loaded from: classes.dex */
public interface ITtsMediaPlayer {
    void acquireAudioFocusLock();

    void cancel();

    void interrupt();

    boolean isSpeaking();

    void prepare();

    void releaseAudioFocusLock();

    void setVolume(float f);

    void speakFromInputStream(InputStream inputStream, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2);

    void speakFromInputStream(InputStream inputStream, String str, TtsListener ttsListener, String str2);

    void speakResource(int i, int i2, TtsListener ttsListener);

    void speakResource(int i, int i2, TtsListener ttsListener, String str);

    void speakResource(Resources resources, int i, int i2, TtsListener ttsListener);

    void speakResource(Resources resources, int i, int i2, TtsListener ttsListener, String str);

    void speakUri(Uri uri, String str, TtsListener ttsListener);

    void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener);

    void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2);

    void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2, String str3);

    void speakUri(Uri uri, String str, TtsListener ttsListener, String str2);

    void teardown();
}
