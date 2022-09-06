package org.webrtc;

import android.content.Context;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.webrtc.NetworkMonitorAutoDetect;
/* loaded from: classes5.dex */
public class NetworkMonitor {
    private static final String TAG = "NetworkMonitor";
    private static NetworkMonitor instance;
    private NetworkMonitorAutoDetect autoDetector;
    private NetworkMonitorAutoDetect.ConnectionType currentConnectionType = NetworkMonitorAutoDetect.ConnectionType.CONNECTION_UNKNOWN;
    private final ArrayList<Long> nativeNetworkObservers = new ArrayList<>();
    private final ArrayList<NetworkObserver> networkObservers = new ArrayList<>();

    /* loaded from: classes5.dex */
    public interface NetworkObserver {
        void onConnectionTypeChanged(NetworkMonitorAutoDetect.ConnectionType connectionType);
    }

    private NetworkMonitor() {
    }

    public static void addNetworkObserver(NetworkObserver networkObserver) {
        getInstance().addNetworkObserverInternal(networkObserver);
    }

    private void addNetworkObserverInternal(NetworkObserver networkObserver) {
        this.networkObservers.add(networkObserver);
    }

    private static int androidSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    private static void assertIsTrue(boolean z) {
        if (z) {
            return;
        }
        throw new AssertionError("Expected to be true");
    }

    private void createAutoDetector() {
        this.autoDetector = new NetworkMonitorAutoDetect(new NetworkMonitorAutoDetect.Observer() { // from class: org.webrtc.NetworkMonitor.1
            @Override // org.webrtc.NetworkMonitorAutoDetect.Observer
            public void onConnectionTypeChanged(NetworkMonitorAutoDetect.ConnectionType connectionType) {
                NetworkMonitor.this.updateCurrentConnectionType(connectionType);
            }

            @Override // org.webrtc.NetworkMonitorAutoDetect.Observer
            public void onNetworkConnect(NetworkMonitorAutoDetect.NetworkInformation networkInformation) {
                NetworkMonitor.this.notifyObserversOfNetworkConnect(networkInformation);
            }

            @Override // org.webrtc.NetworkMonitorAutoDetect.Observer
            public void onNetworkDisconnect(long j) {
                NetworkMonitor.this.notifyObserversOfNetworkDisconnect(j);
            }
        }, ContextUtils.getApplicationContext());
    }

    static void createAutoDetectorForTest() {
        getInstance().createAutoDetector();
    }

    static NetworkMonitorAutoDetect getAutoDetectorForTest() {
        return getInstance().autoDetector;
    }

    private NetworkMonitorAutoDetect.ConnectionType getCurrentConnectionType() {
        return this.currentConnectionType;
    }

    private long getCurrentDefaultNetId() {
        NetworkMonitorAutoDetect networkMonitorAutoDetect = this.autoDetector;
        if (networkMonitorAutoDetect == null) {
            return -1L;
        }
        return networkMonitorAutoDetect.getDefaultNetId();
    }

    public static NetworkMonitor getInstance() {
        if (instance == null) {
            instance = new NetworkMonitor();
        }
        return instance;
    }

    @Deprecated
    public static void init(Context context) {
    }

    public static boolean isOnline() {
        return getInstance().getCurrentConnectionType() != NetworkMonitorAutoDetect.ConnectionType.CONNECTION_NONE;
    }

    private native void nativeNotifyConnectionTypeChanged(long j);

    private native void nativeNotifyOfActiveNetworkList(long j, NetworkMonitorAutoDetect.NetworkInformation[] networkInformationArr);

    private native void nativeNotifyOfNetworkConnect(long j, NetworkMonitorAutoDetect.NetworkInformation networkInformation);

    private native void nativeNotifyOfNetworkDisconnect(long j, long j2);

    private boolean networkBindingSupported() {
        NetworkMonitorAutoDetect networkMonitorAutoDetect = this.autoDetector;
        return networkMonitorAutoDetect != null && networkMonitorAutoDetect.supportNetworkCallback();
    }

    private void notifyObserversOfConnectionTypeChange(NetworkMonitorAutoDetect.ConnectionType connectionType) {
        Iterator<Long> it2 = this.nativeNetworkObservers.iterator();
        while (it2.hasNext()) {
            nativeNotifyConnectionTypeChanged(it2.next().longValue());
        }
        Iterator<NetworkObserver> it3 = this.networkObservers.iterator();
        while (it3.hasNext()) {
            it3.next().onConnectionTypeChanged(connectionType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObserversOfNetworkConnect(NetworkMonitorAutoDetect.NetworkInformation networkInformation) {
        Iterator<Long> it2 = this.nativeNetworkObservers.iterator();
        while (it2.hasNext()) {
            nativeNotifyOfNetworkConnect(it2.next().longValue(), networkInformation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObserversOfNetworkDisconnect(long j) {
        Iterator<Long> it2 = this.nativeNetworkObservers.iterator();
        while (it2.hasNext()) {
            nativeNotifyOfNetworkDisconnect(it2.next().longValue(), j);
        }
    }

    public static void removeNetworkObserver(NetworkObserver networkObserver) {
        getInstance().removeNetworkObserverInternal(networkObserver);
    }

    private void removeNetworkObserverInternal(NetworkObserver networkObserver) {
        this.networkObservers.remove(networkObserver);
    }

    static void resetInstanceForTests() {
        instance = new NetworkMonitor();
    }

    private void startMonitoring(long j) {
        Logging.d(TAG, "Start monitoring from native observer " + j);
        this.nativeNetworkObservers.add(Long.valueOf(j));
        if (this.autoDetector == null) {
            createAutoDetector();
        }
        updateCurrentConnectionType(NetworkMonitorAutoDetect.getConnectionType(this.autoDetector.getCurrentNetworkState()));
        updateObserverActiveNetworkList(j);
    }

    private void stopMonitoring(long j) {
        Logging.d(TAG, "Stop monitoring from native observer " + j);
        this.nativeNetworkObservers.remove(Long.valueOf(j));
        if (this.nativeNetworkObservers.isEmpty()) {
            this.autoDetector.destroy();
            this.autoDetector = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentConnectionType(NetworkMonitorAutoDetect.ConnectionType connectionType) {
        this.currentConnectionType = connectionType;
        notifyObserversOfConnectionTypeChange(connectionType);
    }

    private void updateObserverActiveNetworkList(long j) {
        List<NetworkMonitorAutoDetect.NetworkInformation> activeNetworkList = this.autoDetector.getActiveNetworkList();
        if (activeNetworkList == null || activeNetworkList.size() == 0) {
            return;
        }
        nativeNotifyOfActiveNetworkList(j, (NetworkMonitorAutoDetect.NetworkInformation[]) activeNetworkList.toArray(new NetworkMonitorAutoDetect.NetworkInformation[activeNetworkList.size()]));
    }
}
