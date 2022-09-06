package amazon.speech.simclient;
/* loaded from: classes.dex */
public interface IConnectionListener {
    void onConnect(SimClient simClient);

    void onDisconnect();
}
