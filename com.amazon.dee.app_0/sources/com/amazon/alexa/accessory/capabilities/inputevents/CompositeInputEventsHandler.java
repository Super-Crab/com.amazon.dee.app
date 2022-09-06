package com.amazon.alexa.accessory.capabilities.inputevents;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventHandler;
import com.amazon.alexa.accessory.internal.UnavailableInputEventsHandler;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class CompositeInputEventsHandler implements InputEventsHandler {
    private final InputEventsHandler defaultHandler;
    private final Map<String, InputEventsHandler> handlers;

    /* loaded from: classes.dex */
    public static final class Builder {
        private InputEventsHandler defaultHandler;
        private final Map<String, InputEventsHandler> handlers = new HashMap();

        public Builder addHandler(String str, InputEventsHandler inputEventsHandler) {
            Preconditions.notNull(str, "deviceType");
            Preconditions.notNull(inputEventsHandler, "handler");
            Preconditions.mainThread();
            this.handlers.put(str, inputEventsHandler);
            return this;
        }

        public CompositeInputEventsHandler build() {
            Preconditions.mainThread();
            if (this.defaultHandler == null) {
                this.defaultHandler = new UnavailableInputEventsHandler();
            }
            return new CompositeInputEventsHandler(this);
        }

        public Builder defaultHandler(InputEventsHandler inputEventsHandler) {
            this.defaultHandler = inputEventsHandler;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onInputEventTriggered$1(AccessorySession accessorySession, InputEventHandler.Callback callback, Throwable th) throws Throwable {
        Logger.d("ERROR: Failed to handle input event for session %s", th, accessorySession.getConnectedAccessory());
        Logger.e("Failed to handle input event for session %s", th, RedactionUtil.redact(accessorySession.getConnectedAccessory()));
        callback.onResult(InputEventHandler.Result.UNKNOWN);
    }

    @Override // com.amazon.alexa.accessory.capabilities.inputevents.InputEventsHandler
    public void onInputEventTriggered(final AccessorySession accessorySession, final InputEvent inputEvent, final InputEventHandler.Callback callback) {
        Preconditions.mainThread();
        Preconditions.notNull(accessorySession, "accessorySession");
        Preconditions.notNull(inputEvent, "inputEvent");
        Preconditions.notNull(callback, "callback");
        if (!accessorySession.isConnected()) {
            Logger.d("DefaultInputEventsHandler: ERROR: Accessory session for %s not connected. Cannot handle input event %s", accessorySession.getConnectedAccessory(), inputEvent);
            Logger.e("DefaultInputEventsHandler: Accessory session for %s not connected. Cannot handle input event %s", RedactionUtil.redact(accessorySession.getConnectedAccessory()), inputEvent);
            callback.onResult(InputEventHandler.Result.UNSUPPORTED);
            return;
        }
        accessorySession.getDeviceRepository().queryDeviceInformation().firstOrError().map($$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.inputevents.-$$Lambda$CompositeInputEventsHandler$ZZ18CN9NAyX0fvJJ38B5z04KjF8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CompositeInputEventsHandler.this.lambda$onInputEventTriggered$0$CompositeInputEventsHandler(accessorySession, inputEvent, callback, (String) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.inputevents.-$$Lambda$CompositeInputEventsHandler$cScbiYT5EP9-3zOf3aOYMgeeYuY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CompositeInputEventsHandler.lambda$onInputEventTriggered$1(AccessorySession.this, callback, (Throwable) obj);
            }
        });
    }

    private CompositeInputEventsHandler(Builder builder) {
        Preconditions.notNull(builder, "builder");
        this.handlers = builder.handlers;
        this.defaultHandler = builder.defaultHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onInputEventTriggered */
    public void lambda$onInputEventTriggered$0$CompositeInputEventsHandler(String str, AccessorySession accessorySession, InputEvent inputEvent, InputEventHandler.Callback callback) {
        if (this.handlers.containsKey(str)) {
            this.handlers.get(str).onInputEventTriggered(accessorySession, inputEvent, callback);
        } else {
            this.defaultHandler.onInputEventTriggered(accessorySession, inputEvent, callback);
        }
    }
}
