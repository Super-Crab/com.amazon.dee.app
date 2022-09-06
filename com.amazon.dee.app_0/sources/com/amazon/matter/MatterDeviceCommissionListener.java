package com.amazon.matter;

import android.content.Context;
import android.util.Log;
import chip.devicecontroller.ChipDeviceController;
import com.amazon.matter.data.MatterError;
import com.amazon.matter.data.MatterErrorType;
import com.amazon.matter.data.PairDeviceResponse;
import com.amazon.matter.data.PairDeviceStatus;
import com.amazon.matter.eventbus.EventBusHelper;
import com.amazon.matter.eventbus.MatterEventType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
/* loaded from: classes8.dex */
public class MatterDeviceCommissionListener implements ChipDeviceController.CompletionListener {
    private static final Gson GSON = new Gson();
    private static final int PAIR_DEVICE_SUCCESS = 0;
    private static final String TAG = "MatterDeviceCommissionListener";
    private Context mContext;
    private EventBusHelper mEventBusHelper;
    private long mNodeId;

    public MatterDeviceCommissionListener(Context context, long j, EventBusHelper eventBusHelper) {
        this.mContext = context;
        this.mNodeId = j;
        this.mEventBusHelper = eventBusHelper;
    }

    @Override // chip.devicecontroller.ChipDeviceController.CompletionListener
    public void onCloseBleComplete() {
        Log.i(TAG, "onCloseBleComplete");
    }

    @Override // chip.devicecontroller.ChipDeviceController.CompletionListener
    public void onCommissioningComplete(long j, int i) {
        String str = TAG;
        Log.i(str, "onCommissioningComplete: " + j + ":" + i);
    }

    @Override // chip.devicecontroller.ChipDeviceController.CompletionListener
    public void onConnectDeviceComplete() {
        Log.i(TAG, "onConnectDeviceComplete");
    }

    @Override // chip.devicecontroller.ChipDeviceController.CompletionListener
    public void onError(Throwable th) {
        Log.i(TAG, "onError");
    }

    @Override // chip.devicecontroller.ChipDeviceController.CompletionListener
    public void onNotifyChipConnectionClosed() {
        Log.i(TAG, "onNotifyChipConnectionClosed");
    }

    @Override // chip.devicecontroller.ChipDeviceController.CompletionListener
    public void onOpCSRGenerationComplete(byte[] bArr) {
        Log.i(TAG, "onOpCSRGenerationComplete: ");
    }

    @Override // chip.devicecontroller.ChipDeviceController.CompletionListener
    public void onPairingComplete(int i) {
        String str = TAG;
        Log.i(str, "onPairingComplete: " + i);
        if (i == 0) {
            this.mEventBusHelper.sendMessageToEventBus(MatterEventType.PAIR_DEVICE_RESPONSE_SUCCESS, GSON.toJson(new PairDeviceResponse(PairDeviceStatus.DEVICE_PAIRED)));
        } else {
            this.mEventBusHelper.sendMessageToEventBus(MatterEventType.PAIR_DEVICE_RESPONSE_ERROR, GSON.toJson(new MatterError(MatterErrorType.PAIR_DEVICE_ON_COMPLETE_ERROR, String.valueOf(i))));
        }
    }

    @Override // chip.devicecontroller.ChipDeviceController.CompletionListener
    public void onPairingDeleted(int i) {
        String str = TAG;
        Log.i(str, "onPairingDeleted: " + i);
    }

    @Override // chip.devicecontroller.ChipDeviceController.CompletionListener
    public void onSendMessageComplete(String str) {
        GeneratedOutlineSupport1.outline163("onSendMessageComplete: ", str, TAG);
    }

    @Override // chip.devicecontroller.ChipDeviceController.CompletionListener
    public void onStatusUpdate(int i) {
        String str = TAG;
        Log.i(str, "onStatusUpdate: " + i);
    }
}
