package amazon.communication;
/* loaded from: classes.dex */
public interface RemoteCommunicationManagerBase extends ICommunicationManager {
    void deregisterServiceConnectivityListener(ServiceConnectivityListener serviceConnectivityListener);

    void registerServiceConnectedHandler(ServiceConnectedHandler serviceConnectedHandler);

    void registerServiceConnectivityListener(ServiceConnectivityListener serviceConnectivityListener);
}
