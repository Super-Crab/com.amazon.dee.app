package it.innove;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import android.os.ParcelUuid;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.ArrayList;
import java.util.List;
@RequiresApi(21)
/* loaded from: classes3.dex */
public class LollipopScanManager extends ScanManager {
    private ScanCallback mScanCallback;

    public LollipopScanManager(ReactApplicationContext reactApplicationContext, BleManager bleManager) {
        super(reactApplicationContext, bleManager);
        this.mScanCallback = new ScanCallback() { // from class: it.innove.LollipopScanManager.2
            @Override // android.bluetooth.le.ScanCallback
            public void onBatchScanResults(List<ScanResult> list) {
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int i) {
                LollipopScanManager.this.bleManager.sendEvent("BleManagerStopScan", Arguments.createMap());
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int i, final ScanResult scanResult) {
                UiThreadUtil.runOnUiThread(new Runnable() { // from class: it.innove.LollipopScanManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BleManager bleManager2 = LollipopScanManager.this.bleManager;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DiscoverPeripheral: ");
                        outline107.append(scanResult.getDevice().getName());
                        Log.i(BleManager.LOG_TAG, outline107.toString());
                        LollipopPeripheral lollipopPeripheral = (LollipopPeripheral) LollipopScanManager.this.bleManager.getPeripheral(scanResult.getDevice());
                        if (lollipopPeripheral == null) {
                            lollipopPeripheral = new LollipopPeripheral(LollipopScanManager.this.bleManager.getReactContext(), scanResult);
                        } else {
                            lollipopPeripheral.updateData(scanResult.getScanRecord());
                            lollipopPeripheral.updateRssi(scanResult.getRssi());
                        }
                        LollipopScanManager.this.bleManager.savePeripheral(lollipopPeripheral);
                        LollipopScanManager.this.bleManager.sendEvent("BleManagerDiscoverPeripheral", lollipopPeripheral.asWritableMap());
                    }
                });
            }
        };
    }

    @Override // it.innove.ScanManager
    public void scan(ReadableArray readableArray, final int i, ReadableMap readableMap, Callback callback) {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        ArrayList arrayList = new ArrayList();
        if (readableMap.hasKey("scanMode")) {
            builder.setScanMode(readableMap.getInt("scanMode"));
        }
        int i2 = Build.VERSION.SDK_INT;
        if (readableMap.hasKey("numberOfMatches")) {
            builder.setNumOfMatches(readableMap.getInt("numberOfMatches"));
        }
        if (readableMap.hasKey("matchMode")) {
            builder.setMatchMode(readableMap.getInt("matchMode"));
        }
        if (readableMap.hasKey("reportDelay")) {
            builder.setReportDelay(readableMap.getInt("reportDelay"));
        }
        if (readableArray.size() > 0) {
            for (int i3 = 0; i3 < readableArray.size(); i3++) {
                arrayList.add(new ScanFilter.Builder().setServiceUuid(new ParcelUuid(UUIDHelper.uuidFromString(readableArray.getString(i3)))).build());
                String str = "Filter service: " + readableArray.getString(i3);
            }
        }
        getBluetoothAdapter().getBluetoothLeScanner().startScan(arrayList, builder.build(), this.mScanCallback);
        if (i > 0) {
            new Thread() { // from class: it.innove.LollipopScanManager.1
                private int currentScanSession;

                {
                    this.currentScanSession = LollipopScanManager.this.scanSessionId.incrementAndGet();
                }

                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(i * 1000);
                    } catch (InterruptedException unused) {
                    }
                    UiThreadUtil.runOnUiThread(new Runnable() { // from class: it.innove.LollipopScanManager.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BluetoothAdapter bluetoothAdapter = LollipopScanManager.this.getBluetoothAdapter();
                            if (LollipopScanManager.this.scanSessionId.intValue() == AnonymousClass1.this.currentScanSession) {
                                if (bluetoothAdapter.getState() == 12) {
                                    bluetoothAdapter.getBluetoothLeScanner().stopScan(LollipopScanManager.this.mScanCallback);
                                }
                                LollipopScanManager.this.bleManager.sendEvent("BleManagerStopScan", Arguments.createMap());
                            }
                        }
                    });
                }
            }.start();
        }
        callback.invoke(new Object[0]);
    }

    @Override // it.innove.ScanManager
    public void stopScan(Callback callback) {
        this.scanSessionId.incrementAndGet();
        getBluetoothAdapter().getBluetoothLeScanner().stopScan(this.mScanCallback);
        callback.invoke(new Object[0]);
    }
}
