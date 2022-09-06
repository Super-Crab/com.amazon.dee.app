package com.facebook.react.views.scroll;
/* loaded from: classes2.dex */
public enum ScrollEventType {
    BEGIN_DRAG,
    END_DRAG,
    SCROLL,
    MOMENTUM_BEGIN,
    MOMENTUM_END;

    /* renamed from: com.facebook.react.views.scroll.ScrollEventType$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$views$scroll$ScrollEventType = new int[ScrollEventType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$views$scroll$ScrollEventType[ScrollEventType.BEGIN_DRAG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$views$scroll$ScrollEventType[ScrollEventType.END_DRAG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$views$scroll$ScrollEventType[ScrollEventType.SCROLL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$views$scroll$ScrollEventType[ScrollEventType.MOMENTUM_BEGIN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$views$scroll$ScrollEventType[ScrollEventType.MOMENTUM_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static String getJSEventName(ScrollEventType scrollEventType) {
        int ordinal = scrollEventType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return "topScrollEndDrag";
            }
            if (ordinal == 2) {
                return "topScroll";
            }
            if (ordinal == 3) {
                return "topMomentumScrollBegin";
            }
            if (ordinal == 4) {
                return "topMomentumScrollEnd";
            }
            throw new IllegalArgumentException("Unsupported ScrollEventType: " + scrollEventType);
        }
        return "topScrollBeginDrag";
    }
}
