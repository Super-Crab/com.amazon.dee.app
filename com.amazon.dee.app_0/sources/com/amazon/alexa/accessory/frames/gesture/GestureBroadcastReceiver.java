package com.amazon.alexa.accessory.frames.gesture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class GestureBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = GestureBroadcastReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onReceive - broadcast intent for action: ");
        outline107.append(intent.getAction());
        Log.d(str, outline107.toString());
        try {
            GestureManager.getInstance().onGestureEventReceived(intent);
        } catch (Exception e) {
            Log.e(TAG, String.format("Error in dispatching gesture broadcast for actionType: %s with error as:", intent.getAction(), e.getMessage()));
        }
    }
}
