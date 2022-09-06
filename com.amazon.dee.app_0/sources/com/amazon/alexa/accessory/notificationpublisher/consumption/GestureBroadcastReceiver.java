package com.amazon.alexa.accessory.notificationpublisher.consumption;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class GestureBroadcastReceiver extends BroadcastReceiver {
    private static final String KEY_DEVICE_ADDRESS = "deviceAddress";
    private static final String KEY_DEVICE_TYPE = "deviceType";
    private static final String KEY_INPUT_ACTION = "action";
    private static final String KEY_INPUT_BEHAVIOR = "behavior";
    private static final String KEY_INPUT_SOURCE = "source";
    private static final String TAG = GestureBroadcastReceiver.class.getSimpleName();

    private GestureEventPayload getGestureEventPayload(Intent intent) {
        int intExtra = intent.getIntExtra("source", -1);
        int intExtra2 = intent.getIntExtra("action", -1);
        int intExtra3 = intent.getIntExtra("behavior", 0);
        String stringExtra = intent.getStringExtra("deviceType");
        return GestureEventPayload.builder().setInputSource(intExtra).setInputAction(intExtra2).setInputBehavior(intExtra3).setDeviceType(stringExtra).setDeviceAddress(intent.getStringExtra("deviceAddress")).build();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onReceive - broadcast intent for action: ");
        outline107.append(intent.getAction());
        Log.d(str, outline107.toString());
        try {
            GestureManager.getInstance().onGestureEventReceived(getGestureEventPayload(intent));
        } catch (Exception e) {
            Log.e(TAG, String.format("Error in dispatching gesture broadcast for actionType: %s with error as:", intent.getAction(), e.getMessage()));
        }
    }
}
