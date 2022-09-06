package com.amazon.alexa.accessorykit.finishsetup.bridge;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import com.amazon.alexa.accessorykit.finishsetup.FinishSetupEvent;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import java.text.ParseException;
/* loaded from: classes6.dex */
public final class FinishSetupModule {
    public static final String EVENT_ON_BLACKLISTED = "onBlackListed";
    public static final String EVENT_ON_CONTINUE = "onContinue";
    public static final String EVENT_ON_DISMISS = "onDismiss";
    public static final String INTENT_FINISH_SETUP_ACTION = "com.amazon.alexa.accessorykit.action.finish.setup";
    public static final String KEY_EVENT_TYPE = "event_type";
    public static final String KEY_FINISH_SETUP_EVENT = "finish_setup_event";
    private final Context context;
    private final FinishSetupEvent.Transformer transformer;

    public FinishSetupModule(FinishSetupEvent.Transformer transformer, Context context) {
        Preconditions.notNull(transformer, "transformer");
        Preconditions.notNull(context, "context");
        this.transformer = transformer;
        this.context = context;
    }

    private void publishEvent(String str, FinishSetupEvent finishSetupEvent) {
        this.context.sendBroadcast(new Intent(INTENT_FINISH_SETUP_ACTION).setPackage(AccessoriesFactory.getAppName()).setFlags(268435456).putExtra("event_type", str).putExtra("finish_setup_event", finishSetupEvent.toBundle()), "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
    }

    public void onBlacklisted(ReadableMap readableMap, Promise promise) {
        try {
            publishEvent(EVENT_ON_BLACKLISTED, this.transformer.mo626transform(readableMap));
            promise.resolve(null);
        } catch (ParseException e) {
            promise.reject("FinishSetupModule: ParseException encountered while trying to report onDismiss.", e);
        }
    }

    public void onContinue(ReadableMap readableMap, Promise promise) {
        try {
            publishEvent(EVENT_ON_CONTINUE, this.transformer.mo626transform(readableMap));
            promise.resolve(null);
        } catch (ParseException e) {
            promise.reject("FinishSetupModule: ParseException encountered while trying to report onContinue.", e);
        }
    }

    public void onDismiss(ReadableMap readableMap, Promise promise) {
        try {
            publishEvent(EVENT_ON_DISMISS, this.transformer.mo626transform(readableMap));
            promise.resolve(null);
        } catch (ParseException e) {
            promise.reject("FinishSetupModule: ParseException encountered while trying to report onDismiss.", e);
        }
    }
}
