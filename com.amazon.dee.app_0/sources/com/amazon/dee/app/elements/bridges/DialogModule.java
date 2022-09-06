package com.amazon.dee.app.elements.bridges;

import com.amazon.alexa.dialog.api.Dialog;
import com.amazon.alexa.dialog.api.DialogBuilderProvider;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.dee.app.services.logging.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.google.common.base.Optional;
@ReactModule(name = DialogModule.MODULE_NAME)
/* loaded from: classes12.dex */
public class DialogModule extends ReactContextBaseJavaModule {
    static final String CANCELABLE = "cancelable";
    static final String CANCEL_ACTION = "cancelAction";
    static final String MESSAGE = "message";
    private static final String MODULE_NAME = "Dialog";
    static final String NEGATIVE = "negativeButtonText";
    static final String NEGATIVE_ACTION = "negativeAction";
    static final String NEUTRAL = "neutralButtonText";
    static final String NEUTRAL_ACTION = "neutralAction";
    static final String POSITIVE = "positiveButtonText";
    static final String POSITIVE_ACTION = "positiveAction";
    private static final String TAG = Log.tag(PermissionModule.class);
    static final String TITLE = "title";

    public DialogModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private Runnable fulfillPromise(final String str, final Promise promise) {
        return new Runnable() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$DialogModule$tBn__oA2hVIqLVMbr9PtpD0HlGI
            @Override // java.lang.Runnable
            public final void run() {
                Promise.this.resolve(str);
            }
        };
    }

    private Optional<DialogBuilderProvider> getDialogBuilder() {
        return ComponentRegistry.getInstance().get(DialogBuilderProvider.class);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void showDialog(ReadableMap readableMap, Promise promise) {
        Optional<DialogBuilderProvider> dialogBuilder = getDialogBuilder();
        if (dialogBuilder.isPresent()) {
            Dialog.Builder mo1165createBuilder = dialogBuilder.get().mo1165createBuilder();
            if (readableMap.hasKey(CANCELABLE)) {
                Runnable fulfillPromise = fulfillPromise(CANCEL_ACTION, promise);
                mo1165createBuilder.mo1157setCancelable(readableMap.getBoolean(CANCELABLE));
                mo1165createBuilder.mo1162setOnCancelAction(fulfillPromise);
            }
            if (readableMap.hasKey("message")) {
                mo1165createBuilder.mo1159setMessage(readableMap.getString("message"));
            }
            if (readableMap.hasKey(NEGATIVE)) {
                mo1165createBuilder.mo1160setNegativeButton(readableMap.getString(NEGATIVE), fulfillPromise(NEGATIVE_ACTION, promise));
            }
            if (readableMap.hasKey(NEUTRAL)) {
                mo1165createBuilder.mo1161setNeutralButton(readableMap.getString(NEUTRAL), fulfillPromise(NEUTRAL_ACTION, promise));
            }
            if (readableMap.hasKey(POSITIVE)) {
                mo1165createBuilder.mo1163setPositiveButton(readableMap.getString(POSITIVE), fulfillPromise(POSITIVE_ACTION, promise));
            }
            if (readableMap.hasKey("title")) {
                mo1165createBuilder.mo1164setTitle(readableMap.getString("title"));
            }
            mo1165createBuilder.show();
            return;
        }
        promise.reject(new Exception("DialogBuilder not available."));
    }
}
