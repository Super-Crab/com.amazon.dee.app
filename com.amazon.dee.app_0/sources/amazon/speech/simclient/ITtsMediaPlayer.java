package amazon.speech.simclient;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface ITtsMediaPlayer extends ITtsPlayer {
    void interrupt();

    void setAudioFocusChangeListener(TtsAudioFocusChangeListener ttsAudioFocusChangeListener);
}
