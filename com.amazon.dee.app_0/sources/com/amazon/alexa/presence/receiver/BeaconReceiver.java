package com.amazon.alexa.presence.receiver;

import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.dagger.PresenceComponentSingleton;
import com.amazon.alexa.presence.detection.BlePacketConsumer;
import com.amazon.alexa.presence.utils.DevicePlatformUtil;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.dee.app.metrics.MetricsServiceV2;
import java.util.ArrayList;
import java.util.Iterator;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class BeaconReceiver extends BroadcastReceiver {
    private static final String TAG = Presence.tag();
    @Inject
    BlePacketConsumer mBlePacketConsumer;
    @Inject
    MetricsServiceV2 mMetricsServiceV2;

    public BeaconReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        PresenceComponentSingleton.getInstance(context).inject(this);
        try {
            onReceivingPendingIntent(intent);
            MetricsUtil.recordCount(this.mMetricsServiceV2, "beacons", "processed");
        } catch (Throwable th) {
            MetricsUtil.recordCount(this.mMetricsServiceV2, "beacons", "failed_discarded");
            Log.i(TAG, "Unable to handle received intent/beacon", th);
        }
    }

    void onReceivingPendingIntent(Intent intent) {
        if (DevicePlatformUtil.isAndroidOreoOrLater()) {
            int intExtra = intent.getIntExtra("android.bluetooth.le.extra.ERROR_CODE", -1);
            if (intExtra != -1) {
                this.mBlePacketConsumer.executeOnScanFailed(intExtra);
                return;
            }
            int intExtra2 = intent.getIntExtra("android.bluetooth.le.extra.CALLBACK_TYPE", -1);
            ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.bluetooth.le.extra.LIST_SCAN_RESULT");
            if (parcelableArrayListExtra == null) {
                Log.w(TAG, "Received null scan result array from the intent");
                return;
            }
            Iterator it2 = parcelableArrayListExtra.iterator();
            while (it2.hasNext()) {
                try {
                    this.mBlePacketConsumer.executeOnScanDetection(intExtra2, (ScanResult) it2.next());
                } catch (Exception unused) {
                    Log.w(TAG, "Failed to execute on detection logic for a scanResult. Will continue executing remaining scan results.");
                }
            }
        }
    }

    public BeaconReceiver(BlePacketConsumer blePacketConsumer) {
        this.mBlePacketConsumer = blePacketConsumer;
    }
}
