package com.amazon.whisperjoin.common.sharedtypes.smarthome.intent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.constant.DSHSConstants;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.data.BluetoothFFSEntry;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.data.ZigbeeFFSEntry;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics.CredentialSyncMetricsRecorder;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import java.util.Locale;
import org.apache.commons.lang.Validate;
import org.json.JSONException;
/* loaded from: classes13.dex */
public class DSHSSetCredentialsAPIImpl implements DSHSSetCredentialsAPI {
    private static final String TAG = "DSHSSetCredentialsAPIImpl";
    private Context mContext;
    private CredentialSyncMetricsRecorder mCredentialSyncMetricsRecorder;

    public DSHSSetCredentialsAPIImpl(Context context, CredentialSyncMetricsRecorder credentialSyncMetricsRecorder) {
        Validate.notNull(context, "Context cannot be null");
        Validate.notNull(credentialSyncMetricsRecorder, "CredentialSyncMetricsRecorder cannot be null");
        this.mContext = context;
        this.mCredentialSyncMetricsRecorder = credentialSyncMetricsRecorder;
    }

    private void logIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String str : extras.keySet()) {
                Object obj = extras.get(str);
                WJLog.d(TAG, String.format(Locale.ENGLISH, "%s %s (%s)", str, obj.toString(), obj.getClass().getName()));
            }
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI
    public void setCredentials(ZigbeeFFSEntry zigbeeFFSEntry) {
        WJLog.i(TAG, "Broadcast Zigbee Credential");
        Validate.notNull(zigbeeFFSEntry, "ZigbeeFFSEntry cannot be null");
        Intent intent = new Intent(DSHSConstants.SET_CREDENTIALS_INTENT_ACTION);
        intent.putExtra("version", "1.0");
        try {
            String jSONObject = zigbeeFFSEntry.toJSON().toString();
            String str = TAG;
            WJLog.d(str, "ZigbeeFFSEntry: " + jSONObject);
            intent.putExtra("zigbeeFFSEntry", jSONObject);
            logIntent(intent);
            this.mCredentialSyncMetricsRecorder.onSetCredentials(zigbeeFFSEntry);
            this.mContext.sendBroadcast(intent, DSHSConstants.ZIGBEE_CREDENTIALS_SYNC_PERMISSION);
            WJLog.d(TAG, "Intent Sent");
        } catch (JSONException e) {
            WJLog.e(TAG, "An exception occurred serializing ffs entry", e);
            this.mCredentialSyncMetricsRecorder.onJSONSerializationError();
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI
    public void setCredentialsV2(String str) {
        WJLog.i(TAG, "Set Device Credentials V2");
        Validate.notNull(str, "jsonPayload cannot be null");
        Intent intent = new Intent(DSHSConstants.SET_CREDENTIALS_INTENT_ACTION);
        intent.putExtra("payload", str);
        logIntent(intent);
        this.mContext.sendBroadcast(intent, DSHSConstants.ZIGBEE_CREDENTIALS_SYNC_PERMISSION);
        WJLog.d(TAG, "Intent Sent");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI
    public void setCredentials(ZigbeeFFSEntry zigbeeFFSEntry, BluetoothFFSEntry bluetoothFFSEntry) {
        WJLog.i(TAG, "Trying to broadcast Set credentials with zigbee and ble");
        Validate.notNull(zigbeeFFSEntry, "Zigbee Device cannot be null");
        Validate.notNull(bluetoothFFSEntry, "Bluetooth Device cannot be null");
        Validate.notNull(this.mContext, "Context cannot be null");
        Intent intent = new Intent(DSHSConstants.SET_CREDENTIALS_INTENT_ACTION);
        intent.putExtra("version", "1.0");
        try {
            String jSONObject = zigbeeFFSEntry.toJSON().toString();
            String jSONObject2 = bluetoothFFSEntry.toJSON().toString();
            String str = TAG;
            WJLog.d(str, "ZigbeeFFSEntry: " + jSONObject);
            String str2 = TAG;
            WJLog.d(str2, "BluetoothFFSEntry: " + jSONObject2);
            intent.putExtra("zigbeeFFSEntry", jSONObject);
            intent.putExtra("bluetoothFFSEntry", jSONObject2);
            logIntent(intent);
            this.mCredentialSyncMetricsRecorder.onSetCredentials(zigbeeFFSEntry, bluetoothFFSEntry);
            this.mContext.sendBroadcast(intent, DSHSConstants.ZIGBEE_CREDENTIALS_SYNC_PERMISSION);
            WJLog.d(TAG, "Intent sent");
        } catch (JSONException e) {
            WJLog.e(TAG, "An exception occurred while serializing the entries for DSHS", e);
            this.mCredentialSyncMetricsRecorder.onJSONSerializationError();
        }
    }
}
