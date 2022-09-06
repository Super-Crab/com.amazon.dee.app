package com.amazon.alexa.accessory.internal.bluetooth.spp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothUtils;
import com.amazon.alexa.accessory.internal.bluetooth.spp.SppServer;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.monitor.BluetoothStateMonitor;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.IOException;
import java.util.UUID;
/* loaded from: classes.dex */
public class DefaultSppServer implements SppServer, BluetoothStateMonitor.Observer {
    @VisibleForTesting
    static final int SERVER_STATE_RUNNING = 2;
    @VisibleForTesting
    static final int SERVER_STATE_STARTING = 1;
    @VisibleForTesting
    static final int SERVER_STATE_STOPPED = 0;
    private static final String SERVICE_NAME = "AMA SPP Server";
    private BluetoothServerSocket bluetoothServerSocket;
    private final BluetoothStateMonitor bluetoothStateMonitor;
    private final Context context;
    private volatile boolean isStopIntentional;
    private SppServer.Listener listener;
    private final Object lock = new Object();
    private final UUID rfcommChannelId;
    private volatile int serverState;
    private Thread serverThread;

    public DefaultSppServer(Context context, BluetoothStateMonitor bluetoothStateMonitor, UUID uuid) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(bluetoothStateMonitor, "bluetoothStateMonitor");
        Preconditions.notNull(uuid, "rfcommChannelId");
        this.context = context;
        this.bluetoothStateMonitor = bluetoothStateMonitor;
        this.rfcommChannelId = uuid;
        this.bluetoothServerSocket = null;
        this.listener = null;
        this.serverThread = null;
        this.serverState = 0;
        this.isStopIntentional = false;
    }

    private void startSppServer() {
        synchronized (this.lock) {
            if (this.serverState != 0) {
                Logger.d("SPP Server is already running");
                return;
            }
            this.serverState = 1;
            try {
                this.bluetoothServerSocket = BluetoothUtils.getBluetoothAdapter(this.context).listenUsingRfcommWithServiceRecord(SERVICE_NAME, this.rfcommChannelId);
                this.serverThread = new Thread(new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.spp.-$$Lambda$DefaultSppServer$81Y1DiCtYGPl_rlGmtK_k5ppqxU
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultSppServer.this.waitForSppClient();
                    }
                });
                this.serverThread.start();
            } catch (IOException | SecurityException e) {
                this.bluetoothServerSocket = null;
                this.serverThread = null;
                this.serverState = 0;
                this.isStopIntentional = false;
                Logger.e("Failed to start SPP server", e);
                if (this.listener != null) {
                    this.listener.onSppServerFailed(e);
                }
            }
        }
    }

    private void stopSppServer() {
        synchronized (this.lock) {
            if (this.serverState == 0) {
                Logger.d("SPP server is already stopped");
                return;
            }
            this.isStopIntentional = true;
            BluetoothServerSocket bluetoothServerSocket = this.bluetoothServerSocket;
            if (bluetoothServerSocket == null) {
                return;
            }
            try {
                bluetoothServerSocket.close();
            } catch (IOException e) {
                Logger.e("Failed to close SPP server socket", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForSppClient() {
        this.serverState = 2;
        SppServer.Listener listener = this.listener;
        if (listener != null) {
            listener.onSppServerStarted();
        }
        while (true) {
            try {
                Logger.d("SPP server listening for clients...");
                BluetoothSocket accept = this.bluetoothServerSocket.accept();
                Logger.d("An SPP client has connected " + accept.toString());
                if (this.listener != null) {
                    this.listener.onSppClientConnected(accept);
                }
            } catch (Exception e) {
                this.bluetoothServerSocket = null;
                this.serverState = 0;
                if (this.isStopIntentional) {
                    this.isStopIntentional = false;
                    Logger.d("SPP server socket stopped accepting clients");
                    SppServer.Listener listener2 = this.listener;
                    if (listener2 == null) {
                        return;
                    }
                    listener2.onSppServerStopped();
                    return;
                }
                Logger.e("SPP server socket failed to accept", e);
                SppServer.Listener listener3 = this.listener;
                if (listener3 == null) {
                    return;
                }
                listener3.onSppServerFailed(e);
                return;
            }
        }
    }

    @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor.Observer
    public void onBluetoothDisabled() {
        Logger.d("Bluetooth is disabled, stopping SPP server");
        stopSppServer();
    }

    @Override // com.amazon.alexa.accessory.monitor.BluetoothStateMonitor.Observer
    public void onBluetoothEnabled() {
        Logger.d("Bluetooth is enabled, starting SPP server");
        startSppServer();
    }

    @VisibleForTesting(otherwise = 5)
    int peekAtIServerState() {
        return this.serverState;
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.spp.SppServer
    public void setListener(SppServer.Listener listener) {
        Preconditions.notNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = listener;
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.spp.SppServer
    public void start() {
        if (((BluetoothManager) this.context.getSystemService("bluetooth")) == null) {
            Logger.w("Device doesn't support Bluetooth. Cannot start SPP server.");
            return;
        }
        this.bluetoothStateMonitor.addObserver(this);
        BluetoothAdapter bluetoothAdapter = BluetoothUtils.getBluetoothAdapter(this.context);
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            Logger.d("Starting SPP server");
            startSppServer();
            return;
        }
        Logger.w("Bluetooth is currently disabled, cannot start SPP server.");
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.spp.SppServer
    public void stop() {
        this.bluetoothStateMonitor.removeObserver(this);
        stopSppServer();
    }
}
