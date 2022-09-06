package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import androidx.annotation.Nullable;
import androidx.core.util.Pools;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.SoftAssertions;
/* loaded from: classes2.dex */
public class TouchEvent extends Event<TouchEvent> {
    private static final Pools.SynchronizedPool<TouchEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private static final int TOUCH_EVENTS_POOL_SIZE = 3;
    public static final long UNSET = Long.MIN_VALUE;
    private short mCoalescingKey;
    @Nullable
    private MotionEvent mMotionEvent;
    @Nullable
    private TouchEventType mTouchEventType;
    private float mViewX;
    private float mViewY;

    /* renamed from: com.facebook.react.uimanager.events.TouchEvent$1  reason: invalid class name */
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
                $SwitchMap$com$facebook$react$uimanager$events$TouchEventType[TouchEventType.CANCEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$events$TouchEventType[TouchEventType.MOVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private TouchEvent() {
    }

    private void init(int i, TouchEventType touchEventType, MotionEvent motionEvent, long j, float f, float f2, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        super.init(i);
        short s = 0;
        SoftAssertions.assertCondition(j != Long.MIN_VALUE, "Gesture start time must be initialized");
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            touchEventCoalescingKeyHelper.addCoalescingKey(j);
        } else if (action == 1) {
            touchEventCoalescingKeyHelper.removeCoalescingKey(j);
        } else if (action == 2) {
            s = touchEventCoalescingKeyHelper.getCoalescingKey(j);
        } else if (action == 3) {
            touchEventCoalescingKeyHelper.removeCoalescingKey(j);
        } else if (action != 5 && action != 6) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline49("Unhandled MotionEvent action: ", action));
        } else {
            touchEventCoalescingKeyHelper.incrementCoalescingKey(j);
        }
        this.mTouchEventType = touchEventType;
        this.mMotionEvent = MotionEvent.obtain(motionEvent);
        this.mCoalescingKey = s;
        this.mViewX = f;
        this.mViewY = f2;
    }

    public static TouchEvent obtain(int i, TouchEventType touchEventType, MotionEvent motionEvent, long j, float f, float f2, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        TouchEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new TouchEvent();
        }
        acquire.init(i, touchEventType, motionEvent, j, f, f2, touchEventCoalescingKeyHelper);
        return acquire;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        int ordinal = ((TouchEventType) Assertions.assertNotNull(this.mTouchEventType)).ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return false;
        }
        if (ordinal == 2) {
            return true;
        }
        if (ordinal == 3) {
            return false;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown touch event type: ");
        outline107.append(this.mTouchEventType);
        throw new RuntimeException(outline107.toString());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        TouchesHelper.sendTouchEvent(rCTEventEmitter, (TouchEventType) Assertions.assertNotNull(this.mTouchEventType), getViewTag(), this);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return this.mCoalescingKey;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return TouchEventType.getJSEventName((TouchEventType) Assertions.assertNotNull(this.mTouchEventType));
    }

    public MotionEvent getMotionEvent() {
        Assertions.assertNotNull(this.mMotionEvent);
        return this.mMotionEvent;
    }

    public float getViewX() {
        return this.mViewX;
    }

    public float getViewY() {
        return this.mViewY;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        ((MotionEvent) Assertions.assertNotNull(this.mMotionEvent)).recycle();
        this.mMotionEvent = null;
        EVENTS_POOL.release(this);
    }
}
