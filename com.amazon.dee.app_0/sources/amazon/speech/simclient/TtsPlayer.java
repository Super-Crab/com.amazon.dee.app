package amazon.speech.simclient;

import amazon.speech.util.RuntimeDeviceTypeHelper;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
/* loaded from: classes.dex */
public class TtsPlayer implements ITtsPlayer {
    private static final boolean DEBUG = true;
    private static final String TAG = "TtsPlayer";
    ITtsMediaPlayer mMediaPlayer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TtsPlayer(Context context, SimClient simClient) {
        this(context, simClient, null);
        this.mMediaPlayer = new TtsMediaPlayer(context, simClient);
        this.mMediaPlayer.setAudioFocusChangeListener(new TtsAudioFocusChangeListener(this));
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void acquireAudioFocusLock() {
        this.mMediaPlayer.acquireAudioFocusLock();
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void cancel() {
        if (this.mMediaPlayer.isSpeaking()) {
            this.mMediaPlayer.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void interrupt() {
        if (this.mMediaPlayer.isSpeaking()) {
            this.mMediaPlayer.interrupt();
        }
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public boolean isSpeaking() {
        return this.mMediaPlayer.isSpeaking();
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void prepare() {
        this.mMediaPlayer.prepare();
    }

    public void processEventsOnTestThread() {
        ((TtsMediaPlayer) this.mMediaPlayer).processEventsOnTestThread();
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void releaseAudioFocusLock() {
        this.mMediaPlayer.releaseAudioFocusLock();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAudioFocusChangeListener(TtsAudioFocusChangeListener ttsAudioFocusChangeListener) {
        ITtsMediaPlayer iTtsMediaPlayer = this.mMediaPlayer;
        if (iTtsMediaPlayer != null) {
            iTtsMediaPlayer.setAudioFocusChangeListener(ttsAudioFocusChangeListener);
        }
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void setSimClientForTts(SimClient simClient) {
        this.mMediaPlayer.setSimClientForTts(simClient);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void setVolume(float f) {
        if (RuntimeDeviceTypeHelper.isDeviceATablet()) {
            this.mMediaPlayer.setVolume(f);
            return;
        }
        throw new UnsupportedOperationException();
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakResource(int i, int i2, TtsListener ttsListener) {
        this.mMediaPlayer.speakResource(i, i2, ttsListener, (String) null);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener) {
        this.mMediaPlayer.speakUri(uri, str, ttsListener, null, null, null);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakResource(int i, int i2, TtsListener ttsListener, String str) {
        this.mMediaPlayer.speakResource(i, i2, ttsListener, str);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, String str2) {
        this.mMediaPlayer.speakUri(uri, str, ttsListener, null, str2, null);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakResource(Resources resources, int i, int i2, TtsListener ttsListener) {
        this.mMediaPlayer.speakResource(resources, i, i2, ttsListener, null);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener) {
        this.mMediaPlayer.speakUri(uri, str, ttsListener, ttsSpeechMarksListener, null, null);
    }

    TtsPlayer(Context context, SimClient simClient, ITtsMediaPlayer iTtsMediaPlayer) {
        this.mMediaPlayer = iTtsMediaPlayer;
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakResource(Resources resources, int i, int i2, TtsListener ttsListener, String str) {
        this.mMediaPlayer.speakResource(resources, i, i2, ttsListener, str);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2) {
        this.mMediaPlayer.speakUri(uri, str, ttsListener, ttsSpeechMarksListener, str2, null);
    }

    @Override // amazon.speech.simclient.ITtsPlayer
    public void speakUri(Uri uri, String str, TtsListener ttsListener, TtsSpeechMarksListener ttsSpeechMarksListener, String str2, String str3) {
        this.mMediaPlayer.speakUri(uri, str, ttsListener, ttsSpeechMarksListener, str2, str3);
    }
}
