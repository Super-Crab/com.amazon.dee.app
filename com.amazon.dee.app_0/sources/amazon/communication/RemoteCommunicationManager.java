package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface RemoteCommunicationManager extends CommunicationManager {
    @FireOsSdk
    void deregisterServiceConnectivityListener(ServiceConnectivityListener serviceConnectivityListener);

    @FireOsSdk
    @Deprecated
    void registerServiceConnectedHandler(ServiceConnectedHandler serviceConnectedHandler);

    @FireOsSdk
    void registerServiceConnectivityListener(ServiceConnectivityListener serviceConnectivityListener);
}
