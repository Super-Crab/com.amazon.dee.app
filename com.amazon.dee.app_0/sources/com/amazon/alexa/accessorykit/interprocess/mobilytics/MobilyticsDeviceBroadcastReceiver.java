package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import com.amazon.alexa.accessorykit.utils.RedactionUtil;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import io.reactivex.rxjava3.functions.Consumer;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class MobilyticsDeviceBroadcastReceiver extends BroadcastReceiver {
    static final String MOBILYTICS_DEVICE_PAYLOAD_KEY = "mobilyticsDevicePayload";
    private static final String TAG = "MobilyticsDeviceBroadcastReceiver:";
    static final String MOBILYTICS_DEVICE_CHANGED_INTENT_ACTION = "com.amazon.alexa.accessorykit.action.mobilyticsdevicechanged";
    static final Intent MOBILYTICS_DEVICE_CHANGED_BASE_INTENT = new Intent(MOBILYTICS_DEVICE_CHANGED_INTENT_ACTION).setPackage(AccessoriesFactory.getAppName());

    @VisibleForTesting
    static AccessoryMobilyticsDevice getMobilyticsDeviceFromIntent(@NonNull Intent intent) {
        String stringExtra = intent.getStringExtra(MOBILYTICS_DEVICE_PAYLOAD_KEY);
        if (stringExtra != null) {
            try {
                return AccessoryMobilyticsDevice.FACTORY.mo1239create(new JSONObject(stringExtra));
            } catch (JSONException e) {
                Logger.e("Could not parse mobilytics Device", e, TAG);
                return AccessoryMobilyticsDevice.UNKNOWN;
            }
        }
        throw new IllegalStateException("Could not parse mobilytics Device, string payload was null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$persistDevice$0(BroadcastReceiver.PendingResult pendingResult, AccessoryMobilyticsDevice accessoryMobilyticsDevice) throws Throwable {
        Logger.d("%s successfully received and persisted mobilytics device %s", TAG, accessoryMobilyticsDevice);
        pendingResult.finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$persistDevice$1(AccessoryMobilyticsDevice accessoryMobilyticsDevice, BroadcastReceiver.PendingResult pendingResult, Throwable th) throws Throwable {
        Logger.d("%s ERROR: failed to persist mobilytics device %s", th, TAG, accessoryMobilyticsDevice);
        Logger.e("%s failed to persist mobilytics device %s", th, TAG, RedactionUtil.redact(accessoryMobilyticsDevice));
        pendingResult.finish();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!MOBILYTICS_DEVICE_CHANGED_INTENT_ACTION.equals(intent.getAction())) {
            return;
        }
        persistDevice(intent, goAsync(), context);
    }

    @SuppressLint({"CheckResult"})
    @VisibleForTesting
    void persistDevice(@NonNull Intent intent, @NonNull final BroadcastReceiver.PendingResult pendingResult, @NonNull Context context) {
        Preconditions.notNull(intent, MAPAccountManager.KEY_INTENT);
        Preconditions.notNull(pendingResult, "pendingResult");
        Preconditions.notNull(context, "context");
        final AccessoryMobilyticsDevice mobilyticsDeviceFromIntent = getMobilyticsDeviceFromIntent(intent);
        FileBackedMobilyticsDeviceSupplier.commitMobityticsDevice(context, mobilyticsDeviceFromIntent).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$MobilyticsDeviceBroadcastReceiver$uRi4jjLT1sClOmIyk-9hyD5tbo8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MobilyticsDeviceBroadcastReceiver.lambda$persistDevice$0(pendingResult, (AccessoryMobilyticsDevice) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$MobilyticsDeviceBroadcastReceiver$bipAf8natr7iklDN20lxxpeedGQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MobilyticsDeviceBroadcastReceiver.lambda$persistDevice$1(AccessoryMobilyticsDevice.this, pendingResult, (Throwable) obj);
            }
        });
    }
}
