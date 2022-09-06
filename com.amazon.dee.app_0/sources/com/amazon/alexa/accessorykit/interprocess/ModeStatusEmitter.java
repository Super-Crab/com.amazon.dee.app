package com.amazon.alexa.accessorykit.interprocess;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.avsclient.mode.ModeStatusChecker;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.mode.ModeConstants;
/* loaded from: classes6.dex */
public class ModeStatusEmitter {
    public static final String INTENT_ACTION_DRIVE_MODE_STATUS_REQUEST = "com.amazon.alexa.accessorykit.action.drivemode.status_request";
    private static final String TAG = "ModeStatusEmitter:";
    private boolean active;
    private final Context context;
    private final Intent emitModeStatusBaseIntent;
    private final EventBus eventBus;
    private final ModeStatusChecker modeStatusChecker;
    private final IntentFilter modeStatusRequestIntentFilter;

    public ModeStatusEmitter(Context context, EventBus eventBus, ModeStatusChecker modeStatusChecker) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(eventBus, "eventBus");
        Preconditions.notNull(modeStatusChecker, "modeStatusChecker");
        this.context = context;
        this.eventBus = eventBus;
        this.modeStatusChecker = modeStatusChecker;
        this.modeStatusRequestIntentFilter = new IntentFilter(INTENT_ACTION_DRIVE_MODE_STATUS_REQUEST);
        this.emitModeStatusBaseIntent = new Intent(InterprocessModeStatusChecker.INTENT_ACTION_DRIVE_MODE_STATUS).setPackage(AccessoriesFactory.getAppName()).setFlags(268435456);
    }

    private BroadcastReceiver createModeStatusRequestBroadcastReceiver() {
        return new BroadcastReceiver() { // from class: com.amazon.alexa.accessorykit.interprocess.ModeStatusEmitter.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (!ModeStatusEmitter.INTENT_ACTION_DRIVE_MODE_STATUS_REQUEST.equals(intent.getAction())) {
                    return;
                }
                Logger.d("%s received request to emit current mode status", ModeStatusEmitter.TAG);
                ModeStatusEmitter.this.emitCurrentMode();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitCurrentMode() {
        this.context.sendBroadcast(createIsDriveModeForegroundIntent(this.modeStatusChecker.isDriveModeForeground()), "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$observeModeServiceChanges$0(Message message) {
        return ModeConstants.MODE_SWITCHED_EVENT.equals(message.getEventType()) || ModeConstants.MODE_STATE_UPDATE_EVENT.equals(message.getEventType());
    }

    private void observeModeServiceChanges() {
        this.eventBus.getSubscriber().subscribe($$Lambda$ModeStatusEmitter$0GeEvaPueaSaRYJ9j8QnqyJ_g.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$ModeStatusEmitter$8SGgyAc9f63wo6h-UpXSKdm34xo
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                ModeStatusEmitter.this.lambda$observeModeServiceChanges$1$ModeStatusEmitter(message);
            }
        });
    }

    private void observeModeStatusRequests() {
        this.context.registerReceiver(createModeStatusRequestBroadcastReceiver(), this.modeStatusRequestIntentFilter, "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION", null);
    }

    public synchronized ModeStatusEmitter activate() {
        if (this.active) {
            return this;
        }
        this.active = true;
        observeModeStatusRequests();
        observeModeServiceChanges();
        emitCurrentMode();
        return this;
    }

    @VisibleForTesting
    Intent createIsDriveModeForegroundIntent(boolean z) {
        return new Intent(this.emitModeStatusBaseIntent).putExtra("active", z);
    }

    public /* synthetic */ void lambda$observeModeServiceChanges$1$ModeStatusEmitter(Message message) {
        Logger.d("%s mode changed, emitting drive mode state", TAG);
        emitCurrentMode();
    }
}
