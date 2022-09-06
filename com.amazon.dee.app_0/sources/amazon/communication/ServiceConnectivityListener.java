package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface ServiceConnectivityListener {
    @FireOsSdk
    void onServiceConnected();

    @FireOsSdk
    void onServiceDisconnected();
}
