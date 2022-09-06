package amazon.speech.simclient;
/* loaded from: classes.dex */
interface ITtsSpeechMarksEmitter {
    void clear();

    void errorParsingSpeechmarks(Throwable th);

    void scheduleSpeechMark(String str);

    void start();
}
