package com.facebook.react.uimanager.events;
/* loaded from: classes2.dex */
public enum TouchEventType {
    START,
    END,
    MOVE,
    CANCEL;

    /* renamed from: com.facebook.react.uimanager.events.TouchEventType$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$events$TouchEventType = new int[TouchEventType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$uimanager$events$TouchEventType[TouchEventType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$events$TouchEventType[TouchEventType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$events$TouchEventType[TouchEventType.MOVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$events$TouchEventType[TouchEventType.CANCEL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static String getJSEventName(TouchEventType touchEventType) {
        int ordinal = touchEventType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return TouchesHelper.TOP_TOUCH_END_KEY;
            }
            if (ordinal == 2) {
                return "topTouchMove";
            }
            if (ordinal == 3) {
                return TouchesHelper.TOP_TOUCH_CANCEL_KEY;
            }
            throw new IllegalArgumentException("Unexpected type " + touchEventType);
        }
        return "topTouchStart";
    }
}
