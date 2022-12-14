package com.swmansion.gesturehandler;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
/* loaded from: classes3.dex */
public class GestureHandlerOrchestrator {
    private static final float DEFAULT_MIN_ALPHA_FOR_TRAVERSAL = 0.0f;
    private static final int SIMULTANEOUS_GESTURE_HANDLER_LIMIT = 20;
    private final GestureHandlerRegistry mHandlerRegistry;
    private final ViewConfigurationHelper mViewConfigHelper;
    private final ViewGroup mWrapperView;
    private static final PointF sTempPoint = new PointF();
    private static final float[] sMatrixTransformCoords = new float[2];
    private static final Matrix sInverseMatrix = new Matrix();
    private static final float[] sTempCoords = new float[2];
    private static final Comparator<GestureHandler> sHandlersComparator = new Comparator<GestureHandler>() { // from class: com.swmansion.gesturehandler.GestureHandlerOrchestrator.1
        @Override // java.util.Comparator
        public int compare(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
            if ((gestureHandler.mIsActive && gestureHandler2.mIsActive) || (gestureHandler.mIsAwaiting && gestureHandler2.mIsAwaiting)) {
                return Integer.signum(gestureHandler2.mActivationIndex - gestureHandler.mActivationIndex);
            }
            if (gestureHandler.mIsActive) {
                return -1;
            }
            if (gestureHandler2.mIsActive) {
                return 1;
            }
            if (gestureHandler.mIsAwaiting) {
                return -1;
            }
            return gestureHandler2.mIsAwaiting ? 1 : 0;
        }
    };
    private final GestureHandler[] mGestureHandlers = new GestureHandler[20];
    private final GestureHandler[] mAwaitingHandlers = new GestureHandler[20];
    private final GestureHandler[] mPreparedHandlers = new GestureHandler[20];
    private final GestureHandler[] mHandlersToCancel = new GestureHandler[20];
    private int mGestureHandlersCount = 0;
    private int mAwaitingHandlersCount = 0;
    private boolean mIsHandlingTouch = false;
    private int mHandlingChangeSemaphore = 0;
    private boolean mFinishedHandlersCleanupScheduled = false;
    private int mActivationIndex = 0;
    private float mMinAlphaForTraversal = 0.0f;

    public GestureHandlerOrchestrator(ViewGroup viewGroup, GestureHandlerRegistry gestureHandlerRegistry, ViewConfigurationHelper viewConfigurationHelper) {
        this.mWrapperView = viewGroup;
        this.mHandlerRegistry = gestureHandlerRegistry;
        this.mViewConfigHelper = viewConfigurationHelper;
    }

    private void addAwaitingHandler(GestureHandler gestureHandler) {
        int i = 0;
        while (true) {
            int i2 = this.mAwaitingHandlersCount;
            if (i < i2) {
                if (this.mAwaitingHandlers[i] == gestureHandler) {
                    return;
                }
                i++;
            } else {
                GestureHandler[] gestureHandlerArr = this.mAwaitingHandlers;
                if (i2 < gestureHandlerArr.length) {
                    this.mAwaitingHandlersCount = i2 + 1;
                    gestureHandlerArr[i2] = gestureHandler;
                    gestureHandler.mIsAwaiting = true;
                    int i3 = this.mActivationIndex;
                    this.mActivationIndex = i3 + 1;
                    gestureHandler.mActivationIndex = i3;
                    return;
                }
                throw new IllegalStateException("Too many recognizers");
            }
        }
    }

    private boolean canReceiveEvents(View view) {
        return view.getVisibility() == 0 && view.getAlpha() >= this.mMinAlphaForTraversal;
    }

    private static boolean canRunSimultaneously(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return gestureHandler == gestureHandler2 || gestureHandler.shouldRecognizeSimultaneously(gestureHandler2) || gestureHandler2.shouldRecognizeSimultaneously(gestureHandler);
    }

    private void cancelAll() {
        for (int i = this.mAwaitingHandlersCount - 1; i >= 0; i--) {
            this.mAwaitingHandlers[i].cancel();
        }
        int i2 = this.mGestureHandlersCount;
        for (int i3 = 0; i3 < i2; i3++) {
            this.mPreparedHandlers[i3] = this.mGestureHandlers[i3];
        }
        for (int i4 = i2 - 1; i4 >= 0; i4--) {
            this.mPreparedHandlers[i4].cancel();
        }
    }

    private void cleanupAwaitingHandlers() {
        int i = 0;
        for (int i2 = 0; i2 < this.mAwaitingHandlersCount; i2++) {
            GestureHandler[] gestureHandlerArr = this.mAwaitingHandlers;
            if (gestureHandlerArr[i2].mIsAwaiting) {
                gestureHandlerArr[i] = gestureHandlerArr[i2];
                i++;
            }
        }
        this.mAwaitingHandlersCount = i;
    }

    private void cleanupFinishedHandlers() {
        boolean z = false;
        for (int i = this.mGestureHandlersCount - 1; i >= 0; i--) {
            GestureHandler gestureHandler = this.mGestureHandlers[i];
            if (isFinished(gestureHandler.getState()) && !gestureHandler.mIsAwaiting) {
                this.mGestureHandlers[i] = null;
                gestureHandler.reset();
                gestureHandler.mIsActive = false;
                gestureHandler.mIsAwaiting = false;
                gestureHandler.mActivationIndex = Integer.MAX_VALUE;
                z = true;
            }
        }
        if (z) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.mGestureHandlersCount; i3++) {
                GestureHandler[] gestureHandlerArr = this.mGestureHandlers;
                if (gestureHandlerArr[i3] != null) {
                    gestureHandlerArr[i2] = gestureHandlerArr[i3];
                    i2++;
                }
            }
            this.mGestureHandlersCount = i2;
        }
        this.mFinishedHandlersCleanupScheduled = false;
    }

    private void deliverEventToGestureHandler(GestureHandler gestureHandler, MotionEvent motionEvent) {
        if (!isViewAttachedUnderWrapper(gestureHandler.getView())) {
            gestureHandler.cancel();
        } else if (!gestureHandler.wantEvents()) {
        } else {
            int actionMasked = motionEvent.getActionMasked();
            if (gestureHandler.mIsAwaiting && actionMasked == 2) {
                return;
            }
            float[] fArr = sTempCoords;
            extractCoordsForView(gestureHandler.getView(), motionEvent, fArr);
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            motionEvent.setLocation(fArr[0], fArr[1]);
            gestureHandler.handle(motionEvent);
            if (gestureHandler.mIsActive) {
                gestureHandler.dispatchTouchEvent(motionEvent);
            }
            motionEvent.setLocation(x, y);
            if (actionMasked != 1 && actionMasked != 6) {
                return;
            }
            gestureHandler.stopTrackingPointer(motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
    }

    private void extractCoordsForView(View view, MotionEvent motionEvent, float[] fArr) {
        if (view == this.mWrapperView) {
            fArr[0] = motionEvent.getX();
            fArr[1] = motionEvent.getY();
        } else if (view != null && (view.getParent() instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            extractCoordsForView(viewGroup, motionEvent, fArr);
            PointF pointF = sTempPoint;
            transformTouchPointToViewCoords(fArr[0], fArr[1], viewGroup, view, pointF);
            fArr[0] = pointF.x;
            fArr[1] = pointF.y;
        } else {
            throw new IllegalArgumentException("Parent is null? View is no longer in the tree");
        }
    }

    private void extractGestureHandlers(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        sTempCoords[0] = motionEvent.getX(actionIndex);
        sTempCoords[1] = motionEvent.getY(actionIndex);
        traverseWithPointerEvents(this.mWrapperView, sTempCoords, pointerId);
        extractGestureHandlers(this.mWrapperView, sTempCoords, pointerId);
    }

    private boolean hasOtherHandlerToWaitFor(GestureHandler gestureHandler) {
        for (int i = 0; i < this.mGestureHandlersCount; i++) {
            GestureHandler gestureHandler2 = this.mGestureHandlers[i];
            if (!isFinished(gestureHandler2.getState()) && shouldHandlerWaitForOther(gestureHandler, gestureHandler2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isClipping(View view) {
        return !(view instanceof ViewGroup) || this.mViewConfigHelper.isViewClippingChildren((ViewGroup) view);
    }

    private static boolean isFinished(int i) {
        return i == 3 || i == 1 || i == 5;
    }

    private static boolean isTransformedTouchPointInView(float f, float f2, View view) {
        return f >= 0.0f && f <= ((float) view.getWidth()) && f2 >= 0.0f && f2 < ((float) view.getHeight());
    }

    private boolean isViewAttachedUnderWrapper(@Nullable View view) {
        if (view == null) {
            return false;
        }
        if (view == this.mWrapperView) {
            return true;
        }
        ViewParent parent = view.getParent();
        while (parent != null && parent != this.mWrapperView) {
            parent = parent.getParent();
        }
        return parent == this.mWrapperView;
    }

    private void makeActive(GestureHandler gestureHandler) {
        int state = gestureHandler.getState();
        gestureHandler.mIsAwaiting = false;
        gestureHandler.mIsActive = true;
        int i = this.mActivationIndex;
        this.mActivationIndex = i + 1;
        gestureHandler.mActivationIndex = i;
        int i2 = 0;
        for (int i3 = 0; i3 < this.mGestureHandlersCount; i3++) {
            GestureHandler gestureHandler2 = this.mGestureHandlers[i3];
            if (shouldHandlerBeCancelledBy(gestureHandler2, gestureHandler)) {
                this.mHandlersToCancel[i2] = gestureHandler2;
                i2++;
            }
        }
        for (int i4 = i2 - 1; i4 >= 0; i4--) {
            this.mHandlersToCancel[i4].cancel();
        }
        for (int i5 = this.mAwaitingHandlersCount - 1; i5 >= 0; i5--) {
            GestureHandler gestureHandler3 = this.mAwaitingHandlers[i5];
            if (shouldHandlerBeCancelledBy(gestureHandler3, gestureHandler)) {
                gestureHandler3.cancel();
                gestureHandler3.mIsAwaiting = false;
            }
        }
        cleanupAwaitingHandlers();
        gestureHandler.dispatchStateChange(4, 2);
        if (state != 4) {
            gestureHandler.dispatchStateChange(5, 4);
            if (state == 5) {
                return;
            }
            gestureHandler.dispatchStateChange(0, 5);
        }
    }

    private void recordHandlerIfNotPresent(GestureHandler gestureHandler, View view) {
        int i = 0;
        while (true) {
            int i2 = this.mGestureHandlersCount;
            if (i < i2) {
                if (this.mGestureHandlers[i] == gestureHandler) {
                    return;
                }
                i++;
            } else {
                GestureHandler[] gestureHandlerArr = this.mGestureHandlers;
                if (i2 < gestureHandlerArr.length) {
                    this.mGestureHandlersCount = i2 + 1;
                    gestureHandlerArr[i2] = gestureHandler;
                    gestureHandler.mIsActive = false;
                    gestureHandler.mIsAwaiting = false;
                    gestureHandler.mActivationIndex = Integer.MAX_VALUE;
                    gestureHandler.prepare(view, this);
                    return;
                }
                throw new IllegalStateException("Too many recognizers");
            }
        }
    }

    private boolean recordViewHandlersForPointer(View view, float[] fArr, int i) {
        ArrayList<GestureHandler> handlersForView = this.mHandlerRegistry.getHandlersForView(view);
        if (handlersForView != null) {
            int size = handlersForView.size();
            boolean z = false;
            for (int i2 = 0; i2 < size; i2++) {
                GestureHandler gestureHandler = handlersForView.get(i2);
                if (gestureHandler.isEnabled() && gestureHandler.isWithinBounds(view, fArr[0], fArr[1])) {
                    recordHandlerIfNotPresent(gestureHandler, view);
                    gestureHandler.startTrackingPointer(i);
                    z = true;
                }
            }
            return z;
        }
        return false;
    }

    private void scheduleFinishedHandlersCleanup() {
        if (!this.mIsHandlingTouch && this.mHandlingChangeSemaphore == 0) {
            cleanupFinishedHandlers();
        } else {
            this.mFinishedHandlersCleanupScheduled = true;
        }
    }

    private static boolean shouldHandlerBeCancelledBy(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        if (gestureHandler.hasCommonPointers(gestureHandler2) && !canRunSimultaneously(gestureHandler, gestureHandler2)) {
            if (gestureHandler == gestureHandler2) {
                return true;
            }
            if (!gestureHandler.mIsAwaiting && gestureHandler.getState() != 4) {
                return true;
            }
            return gestureHandler.shouldBeCancelledBy(gestureHandler2);
        }
        return false;
    }

    private static boolean shouldHandlerWaitForOther(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return gestureHandler != gestureHandler2 && (gestureHandler.shouldWaitForHandlerFailure(gestureHandler2) || gestureHandler2.shouldRequireToWaitForFailure(gestureHandler));
    }

    private static boolean shouldHandlerlessViewBecomeTouchTarget(View view, float[] fArr) {
        return (!(view instanceof ViewGroup) || view.getBackground() != null) && isTransformedTouchPointInView(fArr[0], fArr[1], view);
    }

    private static void transformTouchPointToViewCoords(float f, float f2, ViewGroup viewGroup, View view, PointF pointF) {
        float scrollX = (f + viewGroup.getScrollX()) - view.getLeft();
        float scrollY = (f2 + viewGroup.getScrollY()) - view.getTop();
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            float[] fArr = sMatrixTransformCoords;
            fArr[0] = scrollX;
            fArr[1] = scrollY;
            Matrix matrix2 = sInverseMatrix;
            matrix.invert(matrix2);
            matrix2.mapPoints(fArr);
            float f3 = fArr[0];
            scrollY = fArr[1];
            scrollX = f3;
        }
        pointF.set(scrollX, scrollY);
    }

    private boolean traverseWithPointerEvents(View view, float[] fArr, int i) {
        PointerEventsConfig pointerEventsConfigForView = this.mViewConfigHelper.getPointerEventsConfigForView(view);
        if (pointerEventsConfigForView == PointerEventsConfig.NONE) {
            return false;
        }
        if (pointerEventsConfigForView == PointerEventsConfig.BOX_ONLY) {
            return recordViewHandlersForPointer(view, fArr, i) || shouldHandlerlessViewBecomeTouchTarget(view, fArr);
        } else if (pointerEventsConfigForView == PointerEventsConfig.BOX_NONE) {
            if (!(view instanceof ViewGroup)) {
                return false;
            }
            return extractGestureHandlers((ViewGroup) view, fArr, i);
        } else if (pointerEventsConfigForView == PointerEventsConfig.AUTO) {
            return recordViewHandlersForPointer(view, fArr, i) || (view instanceof ViewGroup ? extractGestureHandlers((ViewGroup) view, fArr, i) : false) || shouldHandlerlessViewBecomeTouchTarget(view, fArr);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown pointer event type: ");
            outline107.append(pointerEventsConfigForView.toString());
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    private void tryActivate(GestureHandler gestureHandler) {
        if (hasOtherHandlerToWaitFor(gestureHandler)) {
            addAwaitingHandler(gestureHandler);
            return;
        }
        makeActive(gestureHandler);
        gestureHandler.mIsAwaiting = false;
    }

    public void deliverEventToGestureHandlers(MotionEvent motionEvent) {
        int i = this.mGestureHandlersCount;
        System.arraycopy(this.mGestureHandlers, 0, this.mPreparedHandlers, 0, i);
        Arrays.sort(this.mPreparedHandlers, 0, i, sHandlersComparator);
        for (int i2 = 0; i2 < i; i2++) {
            deliverEventToGestureHandler(this.mPreparedHandlers[i2], motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onHandlerStateChange(GestureHandler gestureHandler, int i, int i2) {
        this.mHandlingChangeSemaphore++;
        if (isFinished(i)) {
            for (int i3 = 0; i3 < this.mAwaitingHandlersCount; i3++) {
                GestureHandler gestureHandler2 = this.mAwaitingHandlers[i3];
                if (shouldHandlerWaitForOther(gestureHandler2, gestureHandler)) {
                    if (i == 5) {
                        gestureHandler2.cancel();
                        gestureHandler2.mIsAwaiting = false;
                    } else {
                        tryActivate(gestureHandler2);
                    }
                }
            }
            cleanupAwaitingHandlers();
        }
        if (i == 4) {
            tryActivate(gestureHandler);
        } else if (i2 != 4 && i2 != 5) {
            gestureHandler.dispatchStateChange(i, i2);
        } else if (gestureHandler.mIsActive) {
            gestureHandler.dispatchStateChange(i, i2);
        }
        this.mHandlingChangeSemaphore--;
        scheduleFinishedHandlersCleanup();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mIsHandlingTouch = true;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 5) {
            extractGestureHandlers(motionEvent);
        } else if (actionMasked == 3) {
            cancelAll();
        }
        deliverEventToGestureHandlers(motionEvent);
        this.mIsHandlingTouch = false;
        if (this.mFinishedHandlersCleanupScheduled && this.mHandlingChangeSemaphore == 0) {
            cleanupFinishedHandlers();
        }
        return true;
    }

    public void setMinimumAlphaForTraversal(float f) {
        this.mMinAlphaForTraversal = f;
    }

    private boolean extractGestureHandlers(ViewGroup viewGroup, float[] fArr, int i) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childInDrawingOrderAtIndex = this.mViewConfigHelper.getChildInDrawingOrderAtIndex(viewGroup, childCount);
            if (canReceiveEvents(childInDrawingOrderAtIndex)) {
                PointF pointF = sTempPoint;
                transformTouchPointToViewCoords(fArr[0], fArr[1], viewGroup, childInDrawingOrderAtIndex, pointF);
                float f = fArr[0];
                float f2 = fArr[1];
                fArr[0] = pointF.x;
                fArr[1] = pointF.y;
                boolean traverseWithPointerEvents = (!isClipping(childInDrawingOrderAtIndex) || isTransformedTouchPointInView(fArr[0], fArr[1], childInDrawingOrderAtIndex)) ? traverseWithPointerEvents(childInDrawingOrderAtIndex, fArr, i) : false;
                fArr[0] = f;
                fArr[1] = f2;
                if (traverseWithPointerEvents) {
                    return true;
                }
            }
        }
        return false;
    }
}
