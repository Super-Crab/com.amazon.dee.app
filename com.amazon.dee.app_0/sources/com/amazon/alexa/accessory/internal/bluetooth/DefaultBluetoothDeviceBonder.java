package com.amazon.alexa.accessory.internal.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothBondStateChangedReceiver;
import com.amazon.alexa.accessory.internal.bluetooth.DefaultBluetoothDeviceBonder;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class DefaultBluetoothDeviceBonder implements BluetoothDeviceBonder {
    private static final int BOND_RETRY_COUNT = 4;
    private static final int BOND_RETRY_INTERVAL = 2000;
    private static final long BOND_STATE_CHECK_INTERVAL_MS = 20000;
    private static final String TAG = "DefaultBluetoothDeviceBonder:";
    private final Context context;
    private final Map<BluetoothDevice, BondingDevice> devices;
    private final Handler handler;
    private boolean isActive;
    private final BluetoothBondStateChangedReceiver receiver;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ActiveBondStateMonitor {
        private static final String MTAG = "ActiveBondStateMonitor:";
        private final Runnable bondStateChecker;
        private final Handler handler;

        /* loaded from: classes.dex */
        public interface Callback {
            void onDeviceBonded();

            void onDeviceNotBonded();
        }

        public ActiveBondStateMonitor(final BluetoothDevice bluetoothDevice, Handler handler, final Callback callback) {
            this.handler = handler;
            this.bondStateChecker = new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultBluetoothDeviceBonder$ActiveBondStateMonitor$idBKArUgyufFOCuAXIr9hBWgYnI
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultBluetoothDeviceBonder.ActiveBondStateMonitor.lambda$new$0(bluetoothDevice, callback);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$new$0(BluetoothDevice bluetoothDevice, Callback callback) {
            Logger.d("%s actively checking bonding status for %s", MTAG, bluetoothDevice);
            int bondState = bluetoothDevice.getBondState();
            Logger.d("%s bondState: %s", MTAG, Integer.valueOf(bondState));
            if (bondState == 12) {
                Logger.d("%s device %s is bonded", MTAG, bluetoothDevice);
                callback.onDeviceBonded();
                return;
            }
            Logger.d("%s device %s has not bonded", MTAG, bluetoothDevice);
            callback.onDeviceNotBonded();
        }

        public void check(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            Preconditions.precondition(i >= 0, "delayInMilliseconds cannot be negative");
            Logger.d("%s bond status check posted", MTAG);
            if (i == 0) {
                this.handler.post(this.bondStateChecker);
            } else {
                this.handler.postDelayed(this.bondStateChecker, j);
            }
        }

        public void stop() {
            Logger.d("%s bond status check stopped", MTAG);
            this.handler.removeCallbacks(this.bondStateChecker);
        }
    }

    public DefaultBluetoothDeviceBonder(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.devices = new HashMap();
        this.handler = new Handler(Looper.getMainLooper());
        this.receiver = new BluetoothBondStateChangedReceiver(new BluetoothBondStateChangedReceiver.Listener() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultBluetoothDeviceBonder$kHgJaEMfbSqv5OBkxqQXgJWqMSE
            @Override // com.amazon.alexa.accessory.internal.bluetooth.BluetoothBondStateChangedReceiver.Listener
            public final void onBondStateChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
                DefaultBluetoothDeviceBonder.this.lambda$new$0$DefaultBluetoothDeviceBonder(bluetoothDevice, i, i2);
            }
        });
    }

    private void attemptDeactivate() {
        if (!this.devices.isEmpty() || !this.isActive) {
            return;
        }
        this.isActive = false;
        this.context.unregisterReceiver(this.receiver);
    }

    synchronized void bondResult(BluetoothDevice bluetoothDevice, boolean z) {
        BondingDevice bondingDevice = this.devices.get(bluetoothDevice);
        if (bondingDevice == null) {
            return;
        }
        if (z) {
            this.devices.remove(bluetoothDevice);
            bondingDevice.notifyCompleted();
        } else if (!bondingDevice.retryCreateBond()) {
            this.devices.remove(bluetoothDevice);
            bondingDevice.notifyFailed();
        }
        attemptDeactivate();
    }

    @Override // com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder
    public synchronized void cancel() {
        for (BondingDevice bondingDevice : this.devices.values()) {
            bondingDevice.cancel();
        }
        this.devices.clear();
        if (this.isActive) {
            this.isActive = false;
            this.context.unregisterReceiver(this.receiver);
        }
    }

    @Override // com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder
    public synchronized void createBond(BluetoothDevice bluetoothDevice, int i, BluetoothDeviceBonder.Callback callback) {
        Preconditions.notNull(bluetoothDevice, "device");
        Preconditions.notNull(callback, "callback");
        BondingDevice bondingDevice = this.devices.get(bluetoothDevice);
        if (bondingDevice == null) {
            bondingDevice = new BondingDevice(bluetoothDevice, i);
            this.devices.put(bluetoothDevice, bondingDevice);
        }
        if (!this.isActive) {
            this.isActive = true;
            this.context.registerReceiver(this.receiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        }
        if (!bondingDevice.createBond(callback)) {
            this.devices.remove(bluetoothDevice);
            attemptDeactivate();
        }
    }

    @VisibleForTesting
    Map<BluetoothDevice, BondingDevice> getDevicesMapForTesting() {
        return this.devices;
    }

    public /* synthetic */ void lambda$new$0$DefaultBluetoothDeviceBonder(BluetoothDevice bluetoothDevice, int i, int i2) {
        if (i2 == 12) {
            Logger.d("%s Bonded %s", TAG, bluetoothDevice);
            bondResult(bluetoothDevice, true);
        } else if (i2 == 10) {
            Logger.d("%s Failed to bond %s", TAG, bluetoothDevice);
            bondResult(bluetoothDevice, false);
        } else {
            Logger.d("%s Bonding %s", TAG, bluetoothDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class BondingDevice {
        private final Runnable bondRunnable;
        private final BluetoothDevice device;
        private int failureCount;
        private final int transport;
        private final List<BluetoothDeviceBonder.Callback> callbackList = new ArrayList();
        private final Object lock = new Object();
        private ActiveBondStateMonitor bondStateMonitor = null;

        public BondingDevice(final BluetoothDevice bluetoothDevice, final int i) {
            this.device = bluetoothDevice;
            this.transport = i;
            this.bondRunnable = new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultBluetoothDeviceBonder$BondingDevice$g6W-lotuoY3_NUU8H7GDrYY8MGA
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultBluetoothDeviceBonder.BondingDevice.this.lambda$new$0$DefaultBluetoothDeviceBonder$BondingDevice(bluetoothDevice, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBondSuccessMonitor() {
            Logger.d("%s in clearBondSuccessMonitor", DefaultBluetoothDeviceBonder.TAG);
            synchronized (this.lock) {
                if (this.bondStateMonitor != null) {
                    this.bondStateMonitor.stop();
                    this.bondStateMonitor = null;
                }
            }
        }

        private void startMonitoringForBondSuccess(final BluetoothDevice bluetoothDevice, Handler handler) {
            Logger.d("%s in startMonitoringForBondSuccess", DefaultBluetoothDeviceBonder.TAG);
            synchronized (this.lock) {
                clearBondSuccessMonitor();
                Logger.d("%s creating new monitor", DefaultBluetoothDeviceBonder.TAG);
                this.bondStateMonitor = new ActiveBondStateMonitor(bluetoothDevice, handler, new ActiveBondStateMonitor.Callback() { // from class: com.amazon.alexa.accessory.internal.bluetooth.DefaultBluetoothDeviceBonder.BondingDevice.1
                    @Override // com.amazon.alexa.accessory.internal.bluetooth.DefaultBluetoothDeviceBonder.ActiveBondStateMonitor.Callback
                    public void onDeviceBonded() {
                        Logger.d("%s onDeviceBonded", DefaultBluetoothDeviceBonder.TAG);
                        BondingDevice.this.clearBondSuccessMonitor();
                        for (BluetoothDeviceBonder.Callback callback : BondingDevice.this.callbackList) {
                            callback.onBondCompleted(bluetoothDevice);
                        }
                    }

                    @Override // com.amazon.alexa.accessory.internal.bluetooth.DefaultBluetoothDeviceBonder.ActiveBondStateMonitor.Callback
                    public void onDeviceNotBonded() {
                        Logger.d("%s onDeviceNotBonded");
                        synchronized (BondingDevice.this.lock) {
                            if (BondingDevice.this.bondStateMonitor != null) {
                                BondingDevice.this.bondStateMonitor.check(20000L);
                            }
                        }
                    }
                });
                this.bondStateMonitor.check(20000L);
            }
        }

        public void cancel() {
            if (this.callbackList.isEmpty()) {
                return;
            }
            Logger.d("%s Cancelling bonding for %s", DefaultBluetoothDeviceBonder.TAG, this.device);
            clearBondSuccessMonitor();
            DefaultBluetoothDeviceBonder.this.handler.removeCallbacks(this.bondRunnable);
            BluetoothUtils.cancelBondProcess(this.device);
            notifyFailed();
        }

        public boolean createBond(final BluetoothDeviceBonder.Callback callback) {
            int bondState = this.device.getBondState();
            Logger.d("%s Bond state %d for %s", DefaultBluetoothDeviceBonder.TAG, Integer.valueOf(bondState), this.device);
            if (bondState == 12) {
                DefaultBluetoothDeviceBonder.this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultBluetoothDeviceBonder$BondingDevice$97yGj3WDCmEa3UavLn7JY_Mefd4
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultBluetoothDeviceBonder.BondingDevice.this.lambda$createBond$1$DefaultBluetoothDeviceBonder$BondingDevice(callback);
                    }
                });
                return false;
            }
            if (!this.callbackList.isEmpty()) {
                Logger.d("%s A bond was already requested with device %s", DefaultBluetoothDeviceBonder.TAG, this.device);
            } else if (bondState == 10 && !BluetoothUtils.createBond(this.device, this.transport)) {
                DefaultBluetoothDeviceBonder.this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultBluetoothDeviceBonder$BondingDevice$Kb2_blHzE9g3RLxTqurU7y5ZY1s
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultBluetoothDeviceBonder.BondingDevice.this.lambda$createBond$2$DefaultBluetoothDeviceBonder$BondingDevice(callback);
                    }
                });
                return false;
            }
            this.callbackList.add(callback);
            startMonitoringForBondSuccess(this.device, DefaultBluetoothDeviceBonder.this.handler);
            return true;
        }

        public BluetoothDevice getDevice() {
            return this.device;
        }

        public boolean isBonded() {
            return this.device.getBondState() == 12;
        }

        public /* synthetic */ void lambda$createBond$1$DefaultBluetoothDeviceBonder$BondingDevice(BluetoothDeviceBonder.Callback callback) {
            callback.onBondCompleted(this.device);
        }

        public /* synthetic */ void lambda$createBond$2$DefaultBluetoothDeviceBonder$BondingDevice(BluetoothDeviceBonder.Callback callback) {
            callback.onBondFailed(this.device);
        }

        public /* synthetic */ void lambda$new$0$DefaultBluetoothDeviceBonder$BondingDevice(BluetoothDevice bluetoothDevice, int i) {
            int bondState = bluetoothDevice.getBondState();
            if (bondState != 10 || BluetoothUtils.createBond(bluetoothDevice, i)) {
                Logger.d("%s Bond state %d for %s, retrying to bond...", DefaultBluetoothDeviceBonder.TAG, Integer.valueOf(bondState), bluetoothDevice);
                return;
            }
            Logger.d("%s Failed to create bond for %s again!", DefaultBluetoothDeviceBonder.TAG, bluetoothDevice);
            synchronized (DefaultBluetoothDeviceBonder.this) {
                DefaultBluetoothDeviceBonder.this.devices.remove(bluetoothDevice);
            }
            notifyFailed();
        }

        public /* synthetic */ void lambda$notifyCompleted$4$DefaultBluetoothDeviceBonder$BondingDevice(List list) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                ((BluetoothDeviceBonder.Callback) it2.next()).onBondCompleted(this.device);
            }
        }

        public /* synthetic */ void lambda$notifyFailed$3$DefaultBluetoothDeviceBonder$BondingDevice(List list) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                ((BluetoothDeviceBonder.Callback) it2.next()).onBondFailed(this.device);
            }
        }

        public void notifyCompleted() {
            final ArrayList arrayList = new ArrayList(this.callbackList);
            clearBondSuccessMonitor();
            this.callbackList.clear();
            DefaultBluetoothDeviceBonder.this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultBluetoothDeviceBonder$BondingDevice$3Mz7JJQb31KKDYGs2gzArLPu4LA
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultBluetoothDeviceBonder.BondingDevice.this.lambda$notifyCompleted$4$DefaultBluetoothDeviceBonder$BondingDevice(arrayList);
                }
            });
        }

        public void notifyFailed() {
            final ArrayList arrayList = new ArrayList(this.callbackList);
            clearBondSuccessMonitor();
            this.callbackList.clear();
            DefaultBluetoothDeviceBonder.this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultBluetoothDeviceBonder$BondingDevice$GXRGfSAIrMWlx2amzEX27ZNQJK4
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultBluetoothDeviceBonder.BondingDevice.this.lambda$notifyFailed$3$DefaultBluetoothDeviceBonder$BondingDevice(arrayList);
                }
            });
        }

        public boolean retryCreateBond() {
            DefaultBluetoothDeviceBonder.this.handler.removeCallbacks(this.bondRunnable);
            if (this.device.getBondState() == 12) {
                Logger.d("%s Won't retry to bond, already bonded!", DefaultBluetoothDeviceBonder.TAG);
                return false;
            }
            int i = this.failureCount;
            if (i >= 4) {
                Logger.d("%s Exceeded maximum retry count for %s", DefaultBluetoothDeviceBonder.TAG, this.device);
                return false;
            }
            this.failureCount = i + 1;
            DefaultBluetoothDeviceBonder.this.handler.postDelayed(this.bondRunnable, 2000L);
            startMonitoringForBondSuccess(this.device, DefaultBluetoothDeviceBonder.this.handler);
            return true;
        }

        public void cancel(BluetoothDeviceBonder.Callback callback) {
            if (!this.callbackList.remove(callback) || !this.callbackList.isEmpty()) {
                return;
            }
            Logger.d("%s Cancelling bonding for %s", DefaultBluetoothDeviceBonder.TAG, this.device);
            clearBondSuccessMonitor();
            DefaultBluetoothDeviceBonder.this.handler.removeCallbacks(this.bondRunnable);
            BluetoothUtils.cancelBondProcess(this.device);
        }
    }

    @Override // com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder
    public synchronized void cancel(BluetoothDevice bluetoothDevice) {
        BondingDevice remove = this.devices.remove(bluetoothDevice);
        if (remove != null) {
            AccessoryMetricsServiceHolder.getInstance().get().recordCounter(MetricsConstants.Session.BONDING_CANCELLED, MetricsConstants.Session.SESSION_CONNECTION, 1.0d, bluetoothDevice.getName() != null ? Collections.singletonMap("firmware_accessory_1", bluetoothDevice.getName()) : null);
            remove.cancel();
        }
        attemptDeactivate();
    }
}
