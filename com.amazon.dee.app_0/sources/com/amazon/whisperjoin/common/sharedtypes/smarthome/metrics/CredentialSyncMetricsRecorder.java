package com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics;

import com.amazon.whisperjoin.common.sharedtypes.smarthome.data.BluetoothFFSEntry;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.data.ZigbeeFFSEntry;
import com.amazon.whisperjoin.metrics.MetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
/* loaded from: classes13.dex */
public class CredentialSyncMetricsRecorder {
    private static final String FFS_CRED_SYNC_PREFIX = "FFS.SyncCredentials.";
    private static final String JSON_EXCEPTION_COUNT = "JSONExceptionCount";
    private static final String SET_CREDENTIALS_ZIGBEE_BLE_CALL_COUNT = "SetCredentialsZigbeeBLECallCount";
    private static final String SET_CREDENTIALS_ZIGBEE_BLE_ENTRY_COUNT_BLE = "SetCredentialsZigbeeBLEEntryCountBLE";
    private static final String SET_CREDENTIALS_ZIGBEE_BLE_ENTRY_COUNT_ZIGBEE = "SetCredentialsZigbeeBLEEntryCountZigbee";
    private static final String SET_CREDENTIALS_ZIGBEE_CALL_COUNT = "SetCredentialsZigbeeCallCount";
    private static final String SET_CREDENTIALS_ZIGBEE_ENTRY_COUNT_ZIGBEE = "SetCredentialsZigbeeEntryCountZigbee";
    private static final String SYNC_FAILURE_COUNT = "FFS.SyncCredentials.FailureCount";
    private static final String SYNC_REQUEST_DSHS = "FFS.SyncCredentials.RequestCount-DSHS";
    private static final String SYNC_REQUEST_MIDDLEWARE = "FFS.SyncCredentials.RequestCount-Middleware";
    private static final String SYNC_REQUEST_S2DM = "FFS.SyncCredentials.RequestCount-S2DM";
    private static final String SYNC_REQUEST_UNKNOWN = "FFS.SyncCredentials.RequestCount-UNKNOWN";
    private static final String SYNC_REQUEST_UNRECOGNIZED_SOURCE = "FFS.SyncCredentials.RequestCount-UnrecognizedSource";
    private static final String SYNC_START_COUNT = "FFS.SyncCredentials.StartCount";
    private static final String SYNC_SUCCESS_COUNT = "FFS.SyncCredentials.SuccessCount";
    private final MetricsRecorderProvider mMetricsRecorderProvider;

    public CredentialSyncMetricsRecorder(MetricsRecorderProvider metricsRecorderProvider) {
        this.mMetricsRecorderProvider = metricsRecorderProvider;
    }

    private MetricsRecorder getNewRecorder() {
        return this.mMetricsRecorderProvider.getMetricsRecorder(WhisperJoinMetricSourceName.DSHS);
    }

    private void recordCount(String str) {
        MetricsRecorder newRecorder = getNewRecorder();
        newRecorder.incrementCounter(str);
        newRecorder.close();
    }

    public void onJSONSerializationError() {
        recordCount(JSON_EXCEPTION_COUNT);
    }

    public void onRequestSyncCredentialsDSHS() {
        recordCount(SYNC_REQUEST_DSHS);
    }

    public void onRequestSyncCredentialsMiddleware() {
        recordCount(SYNC_REQUEST_MIDDLEWARE);
    }

    public void onRequestSyncCredentialsS2DM() {
        recordCount(SYNC_REQUEST_S2DM);
    }

    public void onRequestSyncCredentialsUnknown() {
        recordCount(SYNC_REQUEST_UNKNOWN);
    }

    public void onRequestSyncCredentialsUnrecognizedSource() {
        recordCount(SYNC_REQUEST_UNRECOGNIZED_SOURCE);
    }

    public void onSetCredentials(ZigbeeFFSEntry zigbeeFFSEntry) {
        MetricsRecorder newRecorder = getNewRecorder();
        newRecorder.incrementCounter(SET_CREDENTIALS_ZIGBEE_CALL_COUNT);
        newRecorder.recordCounter(SET_CREDENTIALS_ZIGBEE_ENTRY_COUNT_ZIGBEE, zigbeeFFSEntry.getAuthMaterials().size());
        newRecorder.close();
    }

    public void onSyncCredentialsFailure() {
        recordCount(SYNC_FAILURE_COUNT);
    }

    public void onSyncCredentialsStart() {
        recordCount(SYNC_START_COUNT);
    }

    public void onSyncCredentialsSuccess() {
        recordCount(SYNC_SUCCESS_COUNT);
    }

    public void onSetCredentials(ZigbeeFFSEntry zigbeeFFSEntry, BluetoothFFSEntry bluetoothFFSEntry) {
        MetricsRecorder newRecorder = getNewRecorder();
        newRecorder.incrementCounter(SET_CREDENTIALS_ZIGBEE_BLE_CALL_COUNT);
        newRecorder.recordCounter(SET_CREDENTIALS_ZIGBEE_BLE_ENTRY_COUNT_ZIGBEE, zigbeeFFSEntry.getAuthMaterials().size());
        newRecorder.recordCounter(SET_CREDENTIALS_ZIGBEE_BLE_ENTRY_COUNT_BLE, bluetoothFFSEntry.getAuthMaterials().size());
        newRecorder.close();
    }
}
