package com.amazon.alexa.accessorykit.inputevents;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEvent;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventHandler;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventsHandler;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventsUtil;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes6.dex */
public final class BroadcastInputEventsHandler implements InputEventsHandler {
    @VisibleForTesting
    public static final String ACTION_INPUT_EVENT_TRIGGERED = "com.amazon.alexa.accessory.inputevent.triggered";
    @VisibleForTesting
    public static final String KEY_INPUT_DEVICE_ADDRESS = "deviceAddress";
    @VisibleForTesting
    public static final String KEY_INPUT_DEVICE_TYPE = "deviceType";
    private static final String TAG = "BroadcastInputEventsHandler:";
    private final Context context;

    public BroadcastInputEventsHandler(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
    }

    private void broadcastInputEvent(String str, String str2, InputEvent inputEvent) {
        Logger.d("BroadcastInputEventsHandler: sending BroadCast for accessory: %s with event: %s ", str2, inputEvent);
        this.context.sendBroadcast(getBaseIntent(str, str2, inputEvent).setAction(ACTION_INPUT_EVENT_TRIGGERED));
    }

    private Intent getBaseIntent(String str, String str2, InputEvent inputEvent) {
        return new Intent().putExtras(InputEventsUtil.getInputEventBundle(inputEvent)).putExtra("deviceType", str).putExtra("deviceAddress", str2).setPackage(AccessoriesFactory.getAppName()).setFlags(268435456);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onInputEventTriggered$1(AccessorySession accessorySession, InputEventHandler.Callback callback, Throwable th) throws Throwable {
        Logger.d("ERROR: Failed to handle input event for session %s", th, accessorySession.getConnectedAccessory());
        Logger.e("Failed to handle input event for session %s", th, RedactionUtil.redact(accessorySession.getConnectedAccessory()));
        callback.onResult(InputEventHandler.Result.UNKNOWN);
    }

    @VisibleForTesting
    void broadcastInputEventForDeviceType(String str, String str2, InputEvent inputEvent) {
        Logger.d("BroadcastInputEventsHandler: sending deviceType based BroadCast for accessory: %s with event: %s", str2, inputEvent);
        this.context.sendBroadcast(getBaseIntent(str, str2, inputEvent).setAction("com.amazon.alexa.accessory.inputevent.triggered." + str));
    }

    @VisibleForTesting
    void broadcastInputEventForDeviceTypeAndAction(String str, String str2, InputEvent inputEvent) {
        Logger.d("BroadcastInputEventsHandler: sending deviceType + InputAction based BroadCast for accessory: %s with event: ", str2, inputEvent);
        this.context.sendBroadcast(getBaseIntent(str, str2, inputEvent).setAction("com.amazon.alexa.accessory.inputevent.triggered." + str + "." + inputEvent.getInputAction()));
    }

    public /* synthetic */ void lambda$onInputEventTriggered$0$BroadcastInputEventsHandler(AccessorySession accessorySession, InputEvent inputEvent, InputEventHandler.Callback callback, String str) throws Throwable {
        String address = accessorySession.getAddress();
        broadcastInputEvent(str, address, inputEvent);
        broadcastInputEventForDeviceType(str, address, inputEvent);
        broadcastInputEventForDeviceTypeAndAction(str, address, inputEvent);
        callback.onResult(InputEventHandler.Result.SUCCESS);
    }

    @Override // com.amazon.alexa.accessory.capabilities.inputevents.InputEventsHandler
    public void onInputEventTriggered(final AccessorySession accessorySession, final InputEvent inputEvent, final InputEventHandler.Callback callback) {
        Preconditions.mainThread();
        Preconditions.notNull(accessorySession, "accessorySession");
        Preconditions.notNull(inputEvent, "inputEvent");
        Preconditions.notNull(callback, "callback");
        if (!accessorySession.isConnected()) {
            Logger.d("BroadcastInputEventsHandler: ERROR:Accessory session for %s not connected. Cannot handle input event %s", accessorySession.getConnectedAccessory(), inputEvent);
            Logger.e("BroadcastInputEventsHandler: Accessory session for %s not connected. Cannot handle input event %s", RedactionUtil.redact(accessorySession.getConnectedAccessory()), inputEvent);
            callback.onResult(InputEventHandler.Result.UNSUPPORTED);
            return;
        }
        accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).map($$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.inputevents.-$$Lambda$BroadcastInputEventsHandler$K9YIZpjFRZC_QGhbYHx1Rc9B1f0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                BroadcastInputEventsHandler.this.lambda$onInputEventTriggered$0$BroadcastInputEventsHandler(accessorySession, inputEvent, callback, (String) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.inputevents.-$$Lambda$BroadcastInputEventsHandler$7U-6h5PKYNW_q709S54aWTXu5yI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                BroadcastInputEventsHandler.lambda$onInputEventTriggered$1(AccessorySession.this, callback, (Throwable) obj);
            }
        });
    }
}
