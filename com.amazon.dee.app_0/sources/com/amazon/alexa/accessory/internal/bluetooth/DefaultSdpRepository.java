package com.amazon.alexa.accessory.internal.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelUuid;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.bluetooth.SdpRepository;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultSdpRepository implements SdpRepository {
    private final BluetoothManager bluetoothManager;
    private final Context context;
    private final SDPResultReceiver resultReceiver;
    private final Map<BluetoothDevice, Set<SdpRepository.FetchListener>> sdpRequests;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SDPResultReceiver extends BroadcastReceiver {
        private SDPResultReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.device.action.UUID".equals(intent.getAction())) {
                DefaultSdpRepository.this.notifySdpResult((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
            }
        }
    }

    public DefaultSdpRepository(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.sdpRequests = new HashMap();
        this.resultReceiver = new SDPResultReceiver();
        this.bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
    }

    private void cancelFetchRequestForListener(BluetoothDevice bluetoothDevice, SdpRepository.FetchListener fetchListener) {
        Set<SdpRepository.FetchListener> set = this.sdpRequests.get(bluetoothDevice);
        if (set == null) {
            return;
        }
        set.remove(fetchListener);
        if (set.isEmpty()) {
            stopListeningForSdp(bluetoothDevice);
        }
        if (!this.sdpRequests.isEmpty()) {
            return;
        }
        this.context.unregisterReceiver(this.resultReceiver);
    }

    private BluetoothDevice getBluetoothDevice(PeripheralDevice peripheralDevice) {
        Preconditions.notNull(peripheralDevice, "peripheral");
        BluetoothAdapter adapter = this.bluetoothManager.getAdapter();
        if (adapter == null || !adapter.isEnabled()) {
            return null;
        }
        return adapter.getRemoteDevice(peripheralDevice.getAddress());
    }

    private static void notifyListenersOfError(PeripheralDevice peripheralDevice, IOException iOException, Set<SdpRepository.FetchListener> set) {
        for (SdpRepository.FetchListener fetchListener : set) {
            fetchListener.onError(peripheralDevice, iOException);
        }
    }

    private static void notifyListenersOfSuccess(PeripheralDevice peripheralDevice, SdpRecords sdpRecords, Set<SdpRepository.FetchListener> set) {
        for (SdpRepository.FetchListener fetchListener : set) {
            fetchListener.onSuccess(peripheralDevice, sdpRecords);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySdpResult(BluetoothDevice bluetoothDevice) {
        Set<SdpRepository.FetchListener> set;
        Preconditions.mainThread();
        if (bluetoothDevice == null || (set = this.sdpRequests.get(bluetoothDevice)) == null) {
            return;
        }
        HashSet hashSet = new HashSet(set);
        PeripheralDevice peripheralDevice = new PeripheralDevice(bluetoothDevice);
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (uuids == null) {
            notifyListenersOfError(peripheralDevice, new IOException("UUIDs returned were null"), hashSet);
            return;
        }
        notifyListenersOfSuccess(peripheralDevice, new SdpRecords(uuids), hashSet);
        stopListeningForSdp(bluetoothDevice);
    }

    private void stopListeningForSdp(BluetoothDevice bluetoothDevice) {
        this.sdpRequests.remove(bluetoothDevice);
        if (this.sdpRequests.isEmpty()) {
            this.context.unregisterReceiver(this.resultReceiver);
        }
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.SdpRepository
    public void cancelFetch(SdpRepository.FetchListener fetchListener) {
        Preconditions.mainThread();
        for (BluetoothDevice bluetoothDevice : this.sdpRequests.keySet()) {
            cancelFetchRequestForListener(bluetoothDevice, fetchListener);
        }
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.SdpRepository
    public void fetch(PeripheralDevice peripheralDevice, SdpRepository.FetchListener fetchListener) {
        Preconditions.mainThread();
        Preconditions.notNull(peripheralDevice, "peripheral");
        Preconditions.notNull(fetchListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        BluetoothDevice bluetoothDevice = getBluetoothDevice(peripheralDevice);
        if (bluetoothDevice == null) {
            fetchListener.onError(peripheralDevice, new IllegalStateException("Bluetooth adapter not available"));
            return;
        }
        if (this.sdpRequests.isEmpty()) {
            this.context.registerReceiver(this.resultReceiver, new IntentFilter("android.bluetooth.device.action.UUID"));
        }
        Set<SdpRepository.FetchListener> set = this.sdpRequests.get(bluetoothDevice);
        if (set == null) {
            Logger.d("SDP Requester fetching SDP record for peripheral %s", peripheralDevice);
            if (!bluetoothDevice.fetchUuidsWithSdp()) {
                stopListeningForSdp(bluetoothDevice);
                fetchListener.onError(peripheralDevice, new IOException("Failed to start SDP query"));
                return;
            }
            set = new HashSet<>();
            this.sdpRequests.put(bluetoothDevice, set);
        }
        set.add(fetchListener);
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.SdpRepository
    public SdpRecords getLocal(PeripheralDevice peripheralDevice) {
        Preconditions.notNull(peripheralDevice, "peripheral");
        BluetoothAdapter adapter = this.bluetoothManager.getAdapter();
        if (adapter != null && adapter.isEnabled()) {
            ParcelUuid[] uuids = adapter.getRemoteDevice(peripheralDevice.getAddress()).getUuids();
            if (uuids == null) {
                Logger.d("SDP Requester returning empty SDP record for peripheral %s", peripheralDevice);
                return new SdpRecords();
            }
            SdpRecords sdpRecords = new SdpRecords(uuids);
            Logger.d("SDP Requester returning local SDP for peripheral %s: %s", peripheralDevice, sdpRecords);
            return sdpRecords;
        }
        Logger.d("Bluetooth adapter not available. Returning empty SDP records for %s", peripheralDevice);
        return new SdpRecords();
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.SdpRepository
    public void getOrFetch(PeripheralDevice peripheralDevice, SdpRepository.FetchListener fetchListener, ParcelUuid parcelUuid) {
        Preconditions.mainThread();
        Preconditions.notNull(peripheralDevice, "peripheral");
        Preconditions.notNull(fetchListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        BluetoothDevice bluetoothDevice = getBluetoothDevice(peripheralDevice);
        if (bluetoothDevice == null) {
            fetchListener.onError(peripheralDevice, new IllegalStateException("Bluetooth adapter not available"));
        } else if (bluetoothDevice.getType() == 2) {
            Logger.d("Cannot fetch SDP record on a BLE device");
            fetchListener.onError(peripheralDevice, new UnsupportedOperationException("SDP record not applicable to BLE"));
        } else {
            ParcelUuid[] uuids = bluetoothDevice.getUuids();
            if (uuids != null && new HashSet(Arrays.asList(uuids)).contains(parcelUuid)) {
                Logger.d("No need to fetch UUIDs, already populated. %s", Arrays.toString(uuids));
                fetchListener.onSuccess(new PeripheralDevice(bluetoothDevice), new SdpRecords(uuids));
                return;
            }
            Logger.d("getOrFetch SDP did not have desired uuid %s will fetch from accessory with fetch method", parcelUuid);
            fetch(peripheralDevice, fetchListener);
        }
    }
}
