package amazon.speech.simclient;
/* loaded from: classes.dex */
public interface ISimListenCallback {
    void onListenError(int i);

    void onListenFinished();

    void onListenStarted();
}
