package com.amazon.alexa.accessory.internal.bluetooth.spp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothUtils;
import com.amazon.alexa.accessory.internal.bluetooth.spp.SppServer;
import com.amazon.alexa.accessory.internal.monitor.SppSocketConnectionMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes.dex */
public class DefaultSppSocketSupplier implements SppSocketSupplier, SppServer.Listener {
    private final AccessoryMetricsService accessoryMetricsService;
    private final Map<String, BluetoothSocket> bluetoothSocketsByAddressMap;
    private final Context context;
    private SppSocketConnectionMonitor.Listener listener;
    private final SppServer sppServer;

    public DefaultSppSocketSupplier(Context context, SppServer sppServer) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(sppServer, "sppServer");
        this.context = context;
        this.sppServer = sppServer;
        this.bluetoothSocketsByAddressMap = new HashMap();
        this.listener = null;
        this.sppServer.setListener(this);
        this.accessoryMetricsService = AccessoryMetricsServiceHolder.getInstance().get();
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.spp.SppSocketProducer
    public BluetoothSocket createAndConnectSocket(String str, UUID uuid) throws IOException {
        if (((BluetoothManager) this.context.getSystemService("bluetooth")) != null) {
            BluetoothAdapter bluetoothAdapter = BluetoothUtils.getBluetoothAdapter(this.context);
            if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str);
                Logger.d("Creating RFComm socket.");
                BluetoothSocket createRfcommSocketToServiceRecord = remoteDevice.createRfcommSocketToServiceRecord(uuid);
                Logger.d("Connecting socket");
                createRfcommSocketToServiceRecord.connect();
                return createRfcommSocketToServiceRecord;
            }
            this.accessoryMetricsService.recordCounter(MetricsConstants.Session.CREATE_AND_CONNECT_SOCKET_ADAPTER_UNAVAILABLE_ERROR, MetricsConstants.Session.SESSION_CONNECTION, 1.0d, null);
            throw new IOException("Failed to create socket as BluetoothAdapter is unavailable.");
        }
        this.accessoryMetricsService.recordCounter(MetricsConstants.Session.CREATE_AND_CONNECT_SOCKET_BLUETOOTH_UNSUPPORTED_ERROR, MetricsConstants.Session.SESSION_CONNECTION, 1.0d, null);
        throw new IOException("Failed to create socket as device doesn't support Bluetooth.");
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.spp.SppSocketProducer
    public BluetoothSocket getAndRemoveCachedConnectedSocket(String str) {
        return this.bluetoothSocketsByAddressMap.remove(str);
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.spp.SppServer.Listener
    public void onSppClientConnected(BluetoothSocket bluetoothSocket) {
        BluetoothDevice remoteDevice = bluetoothSocket.getRemoteDevice();
        String address = remoteDevice.getAddress();
        PeripheralDevice peripheralDevice = new PeripheralDevice(address, PeripheralDevice.Type.BLUETOOTH_CLASSIC, remoteDevice.getName());
        this.bluetoothSocketsByAddressMap.put(address, bluetoothSocket);
        SppSocketConnectionMonitor.Listener listener = this.listener;
        if (listener != null) {
            listener.onSocketConnected(peripheralDevice, bluetoothSocket);
        }
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.spp.SppServer.Listener
    public void onSppServerFailed(Throwable th) {
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.spp.SppServer.Listener
    public void onSppServerStarted() {
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.spp.SppServer.Listener
    public void onSppServerStopped() {
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.SppSocketConnectionMonitor
    public void setListener(SppSocketConnectionMonitor.Listener listener) {
        if (this.listener == null) {
            Preconditions.notNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.listener = listener;
            Logger.d("A listener is set on DefaultSppSocketSupplier, starting SPP server");
            this.sppServer.start();
            return;
        }
        throw new IllegalStateException("A listener has already been set.");
    }

    @Override // com.amazon.alexa.accessory.internal.monitor.SppSocketConnectionMonitor
    public void unsetListener(SppSocketConnectionMonitor.Listener listener) {
        if (this.listener == listener) {
            this.listener = null;
            Logger.d("The listener is unset on DefaultSppSocketSupplier, stopping SPP server");
            this.sppServer.stop();
            return;
        }
        throw new RuntimeException("Listener to unset is different from the current listener!");
    }
}
