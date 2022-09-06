package com.facebook.react.uimanager.events;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
/* loaded from: classes2.dex */
public class BlackHoleEventDispatcher implements EventDispatcher {
    private static final EventDispatcher sEventDispatcher = new BlackHoleEventDispatcher();

    private BlackHoleEventDispatcher() {
    }

    public static EventDispatcher get() {
        return sEventDispatcher;
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void addBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void addListener(EventDispatcherListener eventDispatcherListener) {
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchAllEvents() {
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchEvent(Event event) {
        String simpleName = BlackHoleEventDispatcher.class.getSimpleName();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Trying to emit event to JS, but the React instance isn't ready. Event: ");
        outline107.append(event.getEventName());
        FLog.d(simpleName, outline107.toString());
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void onCatalystInstanceDestroyed() {
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void registerEventEmitter(int i, RCTEventEmitter rCTEventEmitter) {
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void removeBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void removeListener(EventDispatcherListener eventDispatcherListener) {
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void unregisterEventEmitter(int i) {
    }
}
