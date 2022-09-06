package amazon.speech.tts;

import amazon.speech.tts.Configuration;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import java.io.InputStream;
/* loaded from: classes.dex */
public class TtsPlayer implements ITtsMediaPlayer {
    private final ITtsMediaPlayer mTtsMediaPlayer;

    public TtsPlayer(Context context) {
        this(context, new Configuration.Builder().build());
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void acquireAudioFocusLock() {
        this.mTtsMediaPlayer.acquireAudioFocusLock();
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void cancel() {
        this.mTtsMediaPlayer.cancel();
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void interrupt() {
        this.mTtsMediaPlayer.interrupt();
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public boolean isSpeaking() {
        return this.mTtsMediaPlayer.isSpeaking();
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void prepare() {
        this.mTtsMediaPlayer.prepare();
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void releaseAudioFocusLock() {
        this.mTtsMediaPlayer.releaseAudioFocusLock();
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void setVolume(float f) {
        this.mTtsMediaPlayer.setVolume(f);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakFromInputStream(InputStream inputStream, String str, TtsListener ttsListener, String str2) {
        this.mTtsMediaPlayer.speakFromInputStream(inputStream, str, ttsListener, str2);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakResource(int i, int i2, TtsListener ttsListener) {
        this.mTtsMediaPlayer.speakResource(i, i2, ttsListener);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener) {
        this.mTtsMediaPlayer.speakUri(uri, str, ttsListener);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void teardown() {
        this.mTtsMediaPlayer.teardown();
    }

    public TtsPlayer(Context context, Configuration configuration) {
        this(new TtsMediaPlayer(context, configuration));
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakFromInputStream(InputStream inputStream, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2) {
        this.mTtsMediaPlayer.speakFromInputStream(inputStream, str, ttsListener, ttsSpeechMarksListener, str2);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakResource(int i, int i2, TtsListener ttsListener, String str) {
        this.mTtsMediaPlayer.speakResource(i, i2, ttsListener, str);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener) {
        this.mTtsMediaPlayer.speakUri(uri, str, ttsListener, ttsSpeechMarksListener);
    }

    TtsPlayer(ITtsMediaPlayer iTtsMediaPlayer) {
        this.mTtsMediaPlayer = iTtsMediaPlayer;
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakResource(Resources resources, int i, int i2, TtsListener ttsListener) {
        this.mTtsMediaPlayer.speakResource(resources, i, i2, ttsListener);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, String str2) {
        this.mTtsMediaPlayer.speakUri(uri, str, ttsListener, str2);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakResource(Resources resources, int i, int i2, TtsListener ttsListener, String str) {
        this.mTtsMediaPlayer.speakResource(resources, i, i2, ttsListener, str);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2) {
        this.mTtsMediaPlayer.speakUri(uri, str, ttsListener, ttsSpeechMarksListener, str2);
    }

    @Override // amazon.speech.tts.ITtsMediaPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2, String str3) {
        this.mTtsMediaPlayer.speakUri(uri, str, ttsListener, ttsSpeechMarksListener, str2, str3);
    }
}
