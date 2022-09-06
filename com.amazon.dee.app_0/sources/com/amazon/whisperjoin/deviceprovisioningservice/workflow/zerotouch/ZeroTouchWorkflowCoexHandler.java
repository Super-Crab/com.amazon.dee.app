package com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch;

import android.content.Context;
import android.content.Intent;
import com.amazon.whisperjoin.common.sharedtypes.setup.SetupIntentConstants;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.ZeroTouchWorkflowUpdate;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes13.dex */
public class ZeroTouchWorkflowCoexHandler implements Consumer<ZeroTouchWorkflowUpdate> {
    private static final String TAG = "ZeroTouchWorkflowCoexHandler";
    private final Context mContext;

    public ZeroTouchWorkflowCoexHandler(Context context) {
        this.mContext = context;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public void accept(ZeroTouchWorkflowUpdate zeroTouchWorkflowUpdate) throws Exception {
        ZeroTouchWorkflowUpdate.State state = zeroTouchWorkflowUpdate.getState();
        ZeroTouchWorkflowUpdate.Radio radio = zeroTouchWorkflowUpdate.getRadio();
        if (ZeroTouchWorkflowUpdate.State.CONNECTING.equals(state) && ZeroTouchWorkflowUpdate.Radio.BLE.equals(radio)) {
            WJLog.d(TAG, "Sending broadcast that BLE setup has started.");
            this.mContext.sendBroadcast(new Intent(SetupIntentConstants.BLE.STARTED_INTENT_ACTION), SetupIntentConstants.BLE.PERMISSION);
            return;
        }
        if (!(ZeroTouchWorkflowUpdate.State.SUCCESS.equals(state) || ZeroTouchWorkflowUpdate.State.FAILURE.equals(state)) || !ZeroTouchWorkflowUpdate.Radio.BLE.equals(radio)) {
            return;
        }
        WJLog.d(TAG, "Sending broadcast that BLE setup has stopped.");
        this.mContext.sendBroadcast(new Intent(SetupIntentConstants.BLE.STOPPED_INTENT_ACTION), SetupIntentConstants.BLE.PERMISSION);
    }
}
