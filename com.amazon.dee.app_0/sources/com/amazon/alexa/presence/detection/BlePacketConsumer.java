package com.amazon.alexa.presence.detection;

import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.util.Log;
import android.util.SparseArray;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.reporter.PresenceBeaconResolverClient;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsServiceV2;
/* loaded from: classes9.dex */
public class BlePacketConsumer {
    private final BeaconFormatLogic mBeaconFormatLogic;
    private final PresenceBeaconResolverClient mClient;
    private final MetricsServiceV2 mMetricsServiceV2;
    private static final String TAG = Presence.tag();
    private static final SparseArray<String> ERROR_MESSAGE_MAP = new SparseArray<>();

    static {
        ERROR_MESSAGE_MAP.put(1, "SCAN_FAILED_ALREADY_STARTED");
        ERROR_MESSAGE_MAP.put(2, "SCAN_FAILED_APPLICATION_REGISTRATION_FAILED");
        ERROR_MESSAGE_MAP.put(3, "SCAN_FAILED_INTERNAL_ERROR");
        ERROR_MESSAGE_MAP.put(4, "SCAN_FAILED_FEATURE_UNSUPPORTED");
    }

    public BlePacketConsumer(MetricsServiceV2 metricsServiceV2, BeaconFormatLogic beaconFormatLogic, PresenceBeaconResolverClient presenceBeaconResolverClient) {
        this.mMetricsServiceV2 = metricsServiceV2;
        this.mBeaconFormatLogic = beaconFormatLogic;
        this.mClient = presenceBeaconResolverClient;
    }

    private boolean isInvalidScanRecord(ScanRecord scanRecord) {
        return scanRecord == null || scanRecord.getServiceData() == null || scanRecord.getServiceUuids() == null || scanRecord.getServiceUuids().size() == 0;
    }

    public void executeOnScanDetection(int i, ScanResult scanResult) {
        ScanRecord scanRecord = scanResult.getScanRecord();
        int rssi = scanResult.getRssi();
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.DUPLICATE_BEACON, "unique_beacon");
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, "unique_beacon", "unique_beacon");
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.VALID_SCAN_RECORD, MetricsUtil.Method.ON_SCAN_RESULT);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.INVALID_SCAN_RECORD, MetricsUtil.Method.ON_SCAN_RESULT);
        MetricsUtil.recordZeroCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NULL_BEACON_PAYLOAD, MetricsUtil.Method.ON_SCAN_RESULT);
        String epochToIso8601 = this.mBeaconFormatLogic.epochToIso8601(System.currentTimeMillis());
        if (isInvalidScanRecord(scanRecord)) {
            Log.w(TAG, "Scanner received an invalid scan record.");
            MetricsUtil.recordFailure(this.mMetricsServiceV2, MetricsUtil.MetricsId.INVALID_SCAN_RECORD, MetricsUtil.Method.ON_SCAN_RESULT, "Scanner received an invalid scan record");
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.INVALID_SCAN_RECORD, MetricsUtil.Method.ON_SCAN_RESULT);
            return;
        }
        if (Log.isLoggable(TAG, 2)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scanner received data. ");
            outline107.append(scanRecord.toString());
            outline107.toString();
        }
        byte[] bArr = scanRecord.getServiceData().get(scanRecord.getServiceUuids().get(0));
        if (bArr == null) {
            Log.w(TAG, "Beacon received with empty payload. Nothing to report.");
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NULL_BEACON_PAYLOAD, MetricsUtil.Method.ON_SCAN_RESULT);
            return;
        }
        try {
            BeaconDetection beaconDetection = new BeaconDetection(this.mBeaconFormatLogic.bytesToHexString(bArr), rssi, epochToIso8601);
            MetricsUtil.recordSuccess(this.mMetricsServiceV2, MetricsUtil.MetricsId.VALID_SCAN_RECORD, MetricsUtil.Method.ON_SCAN_RESULT);
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.VALID_SCAN_RECORD, MetricsUtil.Method.ON_SCAN_RESULT);
            this.mClient.resolve(beaconDetection);
        } catch (IllegalArgumentException e) {
            Log.w(TAG, "Null beacon payload detected. Nothing to report.", e);
            MetricsUtil.recordCount(this.mMetricsServiceV2, MetricsUtil.MetricsId.NULL_BEACON_PAYLOAD, MetricsUtil.Method.ON_SCAN_RESULT);
        }
    }

    public void executeOnScanFailed(int i) {
        String str = ERROR_MESSAGE_MAP.get(i);
        if (str == null) {
            str = "SCAN_FAILED_REASON_UNKNOWN";
        }
        GeneratedOutlineSupport1.outline162("Scanner failed : ", str, TAG);
        MetricsUtil.recordFailure(this.mMetricsServiceV2, str, MetricsUtil.Method.BLE_SCAN_FAILED, String.format("BLE Scan failed %s", str));
        MetricsUtil.recordCount(this.mMetricsServiceV2, str, MetricsUtil.Method.BLE_SCAN_FAILED);
    }
}
