package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.bridge.UiThreadUtil;
import com.swmansion.gesturehandler.GestureHandler;
import java.util.Arrays;
/* loaded from: classes3.dex */
public class GestureHandler<T extends GestureHandler> {
    public static final int DIRECTION_DOWN = 8;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = 4;
    private static final int HIT_SLOP_BOTTOM_IDX = 3;
    private static final int HIT_SLOP_HEIGHT_IDX = 5;
    private static final int HIT_SLOP_LEFT_IDX = 0;
    public static final float HIT_SLOP_NONE = Float.NaN;
    private static final int HIT_SLOP_RIGHT_IDX = 2;
    private static final int HIT_SLOP_TOP_IDX = 1;
    private static final int HIT_SLOP_WIDTH_IDX = 4;
    private static int MAX_POINTERS_COUNT = 12;
    public static final int STATE_ACTIVE = 4;
    public static final int STATE_BEGAN = 2;
    public static final int STATE_CANCELLED = 3;
    public static final int STATE_END = 5;
    public static final int STATE_FAILED = 1;
    public static final int STATE_UNDETERMINED = 0;
    private static short sNextEventCoalescingKey;
    private static MotionEvent.PointerCoords[] sPointerCoords;
    private static MotionEvent.PointerProperties[] sPointerProps;
    int mActivationIndex;
    private short mEventCoalescingKey;
    private float[] mHitSlop;
    private GestureHandlerInteractionController mInteractionController;
    boolean mIsActive;
    boolean mIsAwaiting;
    private float mLastEventOffsetX;
    private float mLastEventOffsetY;
    private float mLastX;
    private float mLastY;
    private OnTouchEventListener<T> mListener;
    private GestureHandlerOrchestrator mOrchestrator;
    private boolean mShouldCancelWhenOutside;
    private int mTag;
    private View mView;
    private boolean mWithinBounds;
    private float mX;
    private float mY;
    private final int[] mTrackedPointerIDs = new int[MAX_POINTERS_COUNT];
    private int mTrackedPointersCount = 0;
    private int mState = 0;
    private boolean mEnabled = true;
    private int mNumberOfPointers = 0;

    private MotionEvent adaptEvent(MotionEvent motionEvent) {
        int actionIndex;
        if (!needAdapt(motionEvent)) {
            return motionEvent;
        }
        int actionMasked = motionEvent.getActionMasked();
        int i = 2;
        int i2 = 5;
        if (actionMasked == 0 || actionMasked == 5) {
            actionIndex = motionEvent.getActionIndex();
            if (this.mTrackedPointerIDs[motionEvent.getPointerId(actionIndex)] != -1) {
                if (this.mTrackedPointersCount == 1) {
                    i2 = 0;
                }
                i = i2;
            }
        } else {
            i2 = 6;
            if (actionMasked == 1 || actionMasked == 6) {
                actionIndex = motionEvent.getActionIndex();
                if (this.mTrackedPointerIDs[motionEvent.getPointerId(actionIndex)] != -1) {
                    if (this.mTrackedPointersCount == 1) {
                        i2 = 1;
                    }
                    i = i2;
                }
            } else {
                i = actionMasked;
                actionIndex = -1;
            }
        }
        initPointerProps(this.mTrackedPointersCount);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        motionEvent.setLocation(motionEvent.getRawX(), motionEvent.getRawY());
        int pointerCount = motionEvent.getPointerCount();
        int i3 = i;
        int i4 = 0;
        for (int i5 = 0; i5 < pointerCount; i5++) {
            int pointerId = motionEvent.getPointerId(i5);
            if (this.mTrackedPointerIDs[pointerId] != -1) {
                motionEvent.getPointerProperties(i5, sPointerProps[i4]);
                sPointerProps[i4].id = this.mTrackedPointerIDs[pointerId];
                motionEvent.getPointerCoords(i5, sPointerCoords[i4]);
                if (i5 == actionIndex) {
                    i3 |= i4 << 8;
                }
                i4++;
            }
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), i3, i4, sPointerProps, sPointerCoords, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        motionEvent.setLocation(x, y);
        obtain.setLocation(x, y);
        return obtain;
    }

    private int findNextLocalPointerId() {
        int i = 0;
        while (i < this.mTrackedPointersCount) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.mTrackedPointerIDs;
                if (i2 >= iArr.length || iArr[i2] == i) {
                    break;
                }
                i2++;
            }
            if (i2 == this.mTrackedPointerIDs.length) {
                return i;
            }
            i++;
        }
        return i;
    }

    private static boolean hitSlopSet(float f) {
        return !Float.isNaN(f);
    }

    private static void initPointerProps(int i) {
        if (sPointerProps == null) {
            int i2 = MAX_POINTERS_COUNT;
            sPointerProps = new MotionEvent.PointerProperties[i2];
            sPointerCoords = new MotionEvent.PointerCoords[i2];
        }
        while (i > 0) {
            MotionEvent.PointerProperties[] pointerPropertiesArr = sPointerProps;
            int i3 = i - 1;
            if (pointerPropertiesArr[i3] != null) {
                return;
            }
            pointerPropertiesArr[i3] = new MotionEvent.PointerProperties();
            sPointerCoords[i3] = new MotionEvent.PointerCoords();
            i--;
        }
    }

    private void moveToState(int i) {
        UiThreadUtil.assertOnUiThread();
        int i2 = this.mState;
        if (i2 == i) {
            return;
        }
        this.mState = i;
        if (this.mState == 4) {
            short s = sNextEventCoalescingKey;
            sNextEventCoalescingKey = (short) (s + 1);
            this.mEventCoalescingKey = s;
        }
        this.mOrchestrator.onHandlerStateChange(this, i, i2);
        onStateChange(i, i2);
    }

    private boolean needAdapt(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != this.mTrackedPointersCount) {
            return true;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.mTrackedPointerIDs;
            if (i >= iArr.length) {
                return false;
            }
            if (iArr[i] != -1 && iArr[i] != i) {
                return true;
            }
            i++;
        }
    }

    public static String stateToString(int i) {
        if (i != 0) {
            if (i == 1) {
                return "FAILED";
            }
            if (i == 2) {
                return "BEGIN";
            }
            if (i == 3) {
                return "CANCELLED";
            }
            if (i == 4) {
                return "ACTIVE";
            }
            if (i == 5) {
                return "END";
            }
            return null;
        }
        return "UNDETERMINED";
    }

    public final void activate() {
        int i = this.mState;
        if (i == 0 || i == 2) {
            moveToState(4);
        }
    }

    public final void begin() {
        if (this.mState == 0) {
            moveToState(2);
        }
    }

    public final void cancel() {
        int i = this.mState;
        if (i == 4 || i == 0 || i == 2) {
            onCancel();
            moveToState(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchStateChange(int i, int i2) {
        OnTouchEventListener<T> onTouchEventListener = this.mListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onStateChange(this, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchTouchEvent(MotionEvent motionEvent) {
        OnTouchEventListener<T> onTouchEventListener = this.mListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onTouchEvent(this, motionEvent);
        }
    }

    public final void end() {
        int i = this.mState;
        if (i == 2 || i == 4) {
            moveToState(5);
        }
    }

    public final void fail() {
        int i = this.mState;
        if (i == 4 || i == 0 || i == 2) {
            moveToState(1);
        }
    }

    public short getEventCoalescingKey() {
        return this.mEventCoalescingKey;
    }

    public float getLastAbsolutePositionX() {
        return this.mLastX;
    }

    public float getLastAbsolutePositionY() {
        return this.mLastY;
    }

    public float getLastRelativePositionX() {
        return this.mLastX - this.mLastEventOffsetX;
    }

    public float getLastRelativePositionY() {
        return this.mLastY - this.mLastEventOffsetY;
    }

    public int getNumberOfPointers() {
        return this.mNumberOfPointers;
    }

    public int getState() {
        return this.mState;
    }

    public int getTag() {
        return this.mTag;
    }

    public View getView() {
        return this.mView;
    }

    public float getX() {
        return this.mX;
    }

    public float getY() {
        return this.mY;
    }

    public final void handle(MotionEvent motionEvent) {
        int i;
        if (!this.mEnabled || (i = this.mState) == 3 || i == 1 || i == 5 || this.mTrackedPointersCount < 1) {
            return;
        }
        MotionEvent adaptEvent = adaptEvent(motionEvent);
        this.mX = adaptEvent.getX();
        this.mY = adaptEvent.getY();
        this.mNumberOfPointers = adaptEvent.getPointerCount();
        this.mWithinBounds = isWithinBounds(this.mView, this.mX, this.mY);
        if (this.mShouldCancelWhenOutside && !this.mWithinBounds) {
            int i2 = this.mState;
            if (i2 == 4) {
                cancel();
                return;
            } else if (i2 != 2) {
                return;
            } else {
                fail();
                return;
            }
        }
        this.mLastX = GestureUtils.getLastPointerX(adaptEvent, true);
        this.mLastY = GestureUtils.getLastPointerY(adaptEvent, true);
        this.mLastEventOffsetX = adaptEvent.getRawX() - adaptEvent.getX();
        this.mLastEventOffsetY = adaptEvent.getRawY() - adaptEvent.getY();
        onHandle(adaptEvent);
        if (adaptEvent == motionEvent) {
            return;
        }
        adaptEvent.recycle();
    }

    public boolean hasCommonPointers(GestureHandler gestureHandler) {
        int i = 0;
        while (true) {
            int[] iArr = this.mTrackedPointerIDs;
            if (i < iArr.length) {
                if (iArr[i] != -1 && gestureHandler.mTrackedPointerIDs[i] != -1) {
                    return true;
                }
                i++;
            } else {
                return false;
            }
        }
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isWithinBounds() {
        return this.mWithinBounds;
    }

    protected void onCancel() {
    }

    protected void onHandle(MotionEvent motionEvent) {
        moveToState(1);
    }

    protected void onReset() {
    }

    protected void onStateChange(int i, int i2) {
    }

    public final void prepare(View view, GestureHandlerOrchestrator gestureHandlerOrchestrator) {
        if (this.mView == null && this.mOrchestrator == null) {
            Arrays.fill(this.mTrackedPointerIDs, -1);
            this.mTrackedPointersCount = 0;
            this.mState = 0;
            this.mView = view;
            this.mOrchestrator = gestureHandlerOrchestrator;
            return;
        }
        throw new IllegalStateException("Already prepared or hasn't been reset");
    }

    public final void reset() {
        this.mView = null;
        this.mOrchestrator = null;
        Arrays.fill(this.mTrackedPointerIDs, -1);
        this.mTrackedPointersCount = 0;
        onReset();
    }

    public T setEnabled(boolean z) {
        if (this.mView != null) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.gesturehandler.GestureHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    GestureHandler.this.cancel();
                }
            });
        }
        this.mEnabled = z;
        return this;
    }

    public T setHitSlop(float f, float f2, float f3, float f4, float f5, float f6) {
        if (this.mHitSlop == null) {
            this.mHitSlop = new float[6];
        }
        float[] fArr = this.mHitSlop;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
        fArr[4] = f5;
        fArr[5] = f6;
        if (hitSlopSet(f5) && hitSlopSet(f) && hitSlopSet(f3)) {
            throw new IllegalArgumentException("Cannot have all of left, right and width defined");
        }
        if (hitSlopSet(f5) && !hitSlopSet(f) && !hitSlopSet(f3)) {
            throw new IllegalArgumentException("When width is set one of left or right pads need to be defined");
        }
        if (hitSlopSet(f6) && hitSlopSet(f4) && hitSlopSet(f2)) {
            throw new IllegalArgumentException("Cannot have all of top, bottom and height defined");
        }
        if (hitSlopSet(f6) && !hitSlopSet(f4) && !hitSlopSet(f2)) {
            throw new IllegalArgumentException("When height is set one of top or bottom pads need to be defined");
        }
        return this;
    }

    public T setInteractionController(GestureHandlerInteractionController gestureHandlerInteractionController) {
        this.mInteractionController = gestureHandlerInteractionController;
        return this;
    }

    public GestureHandler setOnTouchEventListener(OnTouchEventListener<T> onTouchEventListener) {
        this.mListener = onTouchEventListener;
        return this;
    }

    public T setShouldCancelWhenOutside(boolean z) {
        this.mShouldCancelWhenOutside = z;
        return this;
    }

    public void setTag(int i) {
        this.mTag = i;
    }

    public boolean shouldBeCancelledBy(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        if (gestureHandler == this || (gestureHandlerInteractionController = this.mInteractionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldHandlerBeCancelledBy(this, gestureHandler);
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
        if (gestureHandler == this) {
            return true;
        }
        GestureHandlerInteractionController gestureHandlerInteractionController = this.mInteractionController;
        if (gestureHandlerInteractionController == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldRecognizeSimultaneously(this, gestureHandler);
    }

    public boolean shouldRequireToWaitForFailure(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        if (gestureHandler == this || (gestureHandlerInteractionController = this.mInteractionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldRequireHandlerToWaitForFailure(this, gestureHandler);
    }

    public boolean shouldWaitForHandlerFailure(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        if (gestureHandler == this || (gestureHandlerInteractionController = this.mInteractionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldWaitForHandlerFailure(this, gestureHandler);
    }

    public void startTrackingPointer(int i) {
        int[] iArr = this.mTrackedPointerIDs;
        if (iArr[i] == -1) {
            iArr[i] = findNextLocalPointerId();
            this.mTrackedPointersCount++;
        }
    }

    public void stopTrackingPointer(int i) {
        int[] iArr = this.mTrackedPointerIDs;
        if (iArr[i] != -1) {
            iArr[i] = -1;
            this.mTrackedPointersCount--;
        }
    }

    public String toString() {
        View view = this.mView;
        String simpleName = view == null ? null : view.getClass().getSimpleName();
        return getClass().getSimpleName() + "@[" + this.mTag + "]:" + simpleName;
    }

    public boolean wantEvents() {
        int i;
        return (!this.mEnabled || (i = this.mState) == 1 || i == 3 || i == 5 || this.mTrackedPointersCount <= 0) ? false : true;
    }

    public boolean isWithinBounds(View view, float f, float f2) {
        float f3;
        float width = view.getWidth();
        float height = view.getHeight();
        float[] fArr = this.mHitSlop;
        float f4 = 0.0f;
        if (fArr != null) {
            float f5 = fArr[0];
            float f6 = fArr[1];
            float f7 = fArr[2];
            float f8 = fArr[3];
            f3 = hitSlopSet(f5) ? 0.0f - f5 : 0.0f;
            if (hitSlopSet(f6)) {
                f4 = 0.0f - f6;
            }
            if (hitSlopSet(f7)) {
                width += f7;
            }
            if (hitSlopSet(f8)) {
                height += f8;
            }
            float[] fArr2 = this.mHitSlop;
            float f9 = fArr2[4];
            float f10 = fArr2[5];
            if (hitSlopSet(f9)) {
                if (!hitSlopSet(f5)) {
                    f3 = width - f9;
                } else if (!hitSlopSet(f7)) {
                    width = f9 + f3;
                }
            }
            if (hitSlopSet(f10)) {
                if (!hitSlopSet(f6)) {
                    f4 = height - f10;
                } else if (!hitSlopSet(f8)) {
                    height = f4 + f10;
                }
            }
        } else {
            f3 = 0.0f;
        }
        return f >= f3 && f <= width && f2 >= f4 && f2 <= height;
    }

    public T setHitSlop(float f) {
        return setHitSlop(f, f, f, f, Float.NaN, Float.NaN);
    }
}
