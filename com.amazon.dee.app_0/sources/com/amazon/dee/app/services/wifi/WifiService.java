package com.amazon.dee.app.services.wifi;

import android.annotation.TargetApi;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.util.Map;
import okhttp3.Response;
/* loaded from: classes12.dex */
public interface WifiService {

    /* loaded from: classes12.dex */
    public interface ConnectionListener {
        void onCanceled();

        void onConnectFailed(Throwable th);

        void onConnected();
    }

    /* loaded from: classes12.dex */
    public static class NetworkSwitchInformation {
        boolean enabled;
        String settingLabelName;

        /* JADX INFO: Access modifiers changed from: package-private */
        public NetworkSwitchInformation(String str, boolean z) {
            this.settingLabelName = str;
            this.enabled = z;
        }

        public String getSettingLabelName() {
            return this.settingLabelName;
        }

        public boolean isEnabled() {
            return this.enabled;
        }
    }

    void cancelAutoConnect();

    void connect(@NonNull String str);

    void connectToDoppler(@NonNull ConnectionListener connectionListener, long j);

    void disconnectAndReset();

    String getConnectedSSID();

    NetworkSwitchInformation getNetworkSwitchInformation();

    boolean isConnected();

    boolean isConnectedToDoppler();

    @TargetApi(21)
    Response makeRequestToDoppler(String str, Uri uri, Map<String, String> map, String str2);
}
