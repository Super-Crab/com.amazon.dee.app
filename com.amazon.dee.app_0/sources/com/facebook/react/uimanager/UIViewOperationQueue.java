package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.view.View;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
public class UIViewOperationQueue {
    public static final int DEFAULT_MIN_TIME_LEFT_IN_FRAME_FOR_NONBATCHED_OPERATION_MS = 8;
    private static final String TAG = "UIViewOperationQueue";
    private long mCreateViewCount;
    private final DispatchUIFrameCallback mDispatchUIFrameCallback;
    private final NativeViewHierarchyManager mNativeViewHierarchyManager;
    private long mNonBatchedExecutionTotalTime;
    private long mProfiledBatchBatchedExecutionTime;
    private long mProfiledBatchCommitEndTime;
    private long mProfiledBatchCommitStartTime;
    private long mProfiledBatchDispatchViewUpdatesTime;
    private long mProfiledBatchLayoutTime;
    private long mProfiledBatchNonBatchedExecutionTime;
    private long mProfiledBatchRunEndTime;
    private long mProfiledBatchRunStartTime;
    private final ReactApplicationContext mReactApplicationContext;
    private long mThreadCpuTime;
    private long mUpdatePropertiesOperationCount;
    @Nullable
    private NotThreadSafeViewHierarchyUpdateDebugListener mViewHierarchyUpdateDebugListener;
    private final int[] mMeasureBuffer = new int[4];
    private final Object mDispatchRunnablesLock = new Object();
    private final Object mNonBatchedOperationsLock = new Object();
    private ArrayList<DispatchCommandViewOperation> mViewCommandOperations = new ArrayList<>();
    private ArrayList<UIOperation> mOperations = new ArrayList<>();
    @GuardedBy("mDispatchRunnablesLock")
    private ArrayList<Runnable> mDispatchUIRunnables = new ArrayList<>();
    @GuardedBy("mNonBatchedOperationsLock")
    private ArrayDeque<UIOperation> mNonBatchedOperations = new ArrayDeque<>();
    private boolean mIsDispatchUIFrameCallbackEnqueued = false;
    private boolean mIsInIllegalUIState = false;
    private boolean mIsProfilingNextBatch = false;

    /* loaded from: classes2.dex */
    private static abstract class AnimationOperation implements UIOperation {
        protected final int mAnimationID;

        public AnimationOperation(int i) {
            this.mAnimationID = i;
        }
    }

    /* loaded from: classes2.dex */
    private final class ChangeJSResponderOperation extends ViewOperation {
        private final boolean mBlockNativeResponder;
        private final boolean mClearResponder;
        private final int mInitialTag;

        public ChangeJSResponderOperation(int i, int i2, boolean z, boolean z2) {
            super(i);
            this.mInitialTag = i2;
            this.mClearResponder = z;
            this.mBlockNativeResponder = z2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            if (!this.mClearResponder) {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.setJSResponder(this.mTag, this.mInitialTag, this.mBlockNativeResponder);
            } else {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.clearJSResponder();
            }
        }
    }

    /* loaded from: classes2.dex */
    private class ConfigureLayoutAnimationOperation implements UIOperation {
        private final Callback mAnimationComplete;
        private final ReadableMap mConfig;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.configureLayoutAnimation(this.mConfig, this.mAnimationComplete);
        }

        private ConfigureLayoutAnimationOperation(ReadableMap readableMap, Callback callback) {
            this.mConfig = readableMap;
            this.mAnimationComplete = callback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class CreateViewOperation extends ViewOperation {
        private final String mClassName;
        @Nullable
        private final ReactStylesDiffMap mInitialProps;
        private final ThemedReactContext mThemedContext;

        public CreateViewOperation(ThemedReactContext themedReactContext, int i, String str, @Nullable ReactStylesDiffMap reactStylesDiffMap) {
            super(i);
            this.mThemedContext = themedReactContext;
            this.mClassName = str;
            this.mInitialProps = reactStylesDiffMap;
            Systrace.startAsyncFlow(0L, "createView", this.mTag);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            Systrace.endAsyncFlow(0L, "createView", this.mTag);
            UIViewOperationQueue.this.mNativeViewHierarchyManager.createView(this.mThemedContext, this.mTag, this.mClassName, this.mInitialProps);
        }
    }

    /* loaded from: classes2.dex */
    private final class DismissPopupMenuOperation implements UIOperation {
        private DismissPopupMenuOperation() {
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.dismissPopupMenu();
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    private final class DispatchCommandOperation extends ViewOperation implements DispatchCommandViewOperation {
        @Nullable
        private final ReadableArray mArgs;
        private final int mCommand;
        private int numRetries;

        public DispatchCommandOperation(int i, int i2, @Nullable ReadableArray readableArray) {
            super(i);
            this.numRetries = 0;
            this.mCommand = i2;
            this.mArgs = readableArray;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mTag, this.mCommand, this.mArgs);
            } catch (Throwable th) {
                ReactSoftException.logSoftException(UIViewOperationQueue.TAG, new RuntimeException("Error dispatching View Command", th));
            }
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        public void executeWithExceptions() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mTag, this.mCommand, this.mArgs);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        @UiThread
        public int getRetries() {
            return this.numRetries;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        @UiThread
        public void incrementRetries() {
            this.numRetries++;
        }
    }

    /* loaded from: classes2.dex */
    private interface DispatchCommandViewOperation {
        void executeWithExceptions();

        int getRetries();

        void incrementRetries();
    }

    /* loaded from: classes2.dex */
    private final class DispatchStringCommandOperation extends ViewOperation implements DispatchCommandViewOperation {
        @Nullable
        private final ReadableArray mArgs;
        private final String mCommand;
        private int numRetries;

        public DispatchStringCommandOperation(int i, String str, @Nullable ReadableArray readableArray) {
            super(i);
            this.numRetries = 0;
            this.mCommand = str;
            this.mArgs = readableArray;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mTag, this.mCommand, this.mArgs);
            } catch (Throwable th) {
                ReactSoftException.logSoftException(UIViewOperationQueue.TAG, new RuntimeException("Error dispatching View Command", th));
            }
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        @UiThread
        public void executeWithExceptions() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mTag, this.mCommand, this.mArgs);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        public int getRetries() {
            return this.numRetries;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        @UiThread
        public void incrementRetries() {
            this.numRetries++;
        }
    }

    /* loaded from: classes2.dex */
    private class DispatchUIFrameCallback extends GuardedFrameCallback {
        private static final int FRAME_TIME_MS = 16;
        private final int mMinTimeLeftInFrameForNonBatchedOperationMs;

        private void dispatchPendingNonBatchedOperations(long j) {
            UIOperation uIOperation;
            while (16 - ((System.nanoTime() - j) / 1000000) >= this.mMinTimeLeftInFrameForNonBatchedOperationMs) {
                synchronized (UIViewOperationQueue.this.mNonBatchedOperationsLock) {
                    if (UIViewOperationQueue.this.mNonBatchedOperations.isEmpty()) {
                        return;
                    }
                    uIOperation = (UIOperation) UIViewOperationQueue.this.mNonBatchedOperations.pollFirst();
                }
                try {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    uIOperation.execute();
                    UIViewOperationQueue.this.mNonBatchedExecutionTotalTime = (SystemClock.uptimeMillis() - uptimeMillis) + UIViewOperationQueue.this.mNonBatchedExecutionTotalTime;
                } catch (Exception e) {
                    UIViewOperationQueue.this.mIsInIllegalUIState = true;
                    throw e;
                }
            }
        }

        @Override // com.facebook.react.uimanager.GuardedFrameCallback
        public void doFrameGuarded(long j) {
            if (UIViewOperationQueue.this.mIsInIllegalUIState) {
                FLog.w(ReactConstants.TAG, "Not flushing pending UI operations because of previously thrown Exception");
                return;
            }
            Systrace.beginSection(0L, "dispatchNonBatchedUIOperations");
            try {
                dispatchPendingNonBatchedOperations(j);
                Systrace.endSection(0L);
                UIViewOperationQueue.this.flushPendingBatches();
                ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this);
            } catch (Throwable th) {
                Systrace.endSection(0L);
                throw th;
            }
        }

        private DispatchUIFrameCallback(ReactContext reactContext, int i) {
            super(reactContext);
            this.mMinTimeLeftInFrameForNonBatchedOperationMs = i;
        }
    }

    /* loaded from: classes2.dex */
    private final class EmitOnLayoutEventOperation extends ViewOperation {
        private final int mScreenHeight;
        private final int mScreenWidth;
        private final int mScreenX;
        private final int mScreenY;

        public EmitOnLayoutEventOperation(int i, int i2, int i3, int i4, int i5) {
            super(i);
            this.mScreenX = i2;
            this.mScreenY = i3;
            this.mScreenWidth = i4;
            this.mScreenHeight = i5;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIManagerModule uIManagerModule = (UIManagerModule) UIViewOperationQueue.this.mReactApplicationContext.getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.mo6949getEventDispatcher().dispatchEvent(OnLayoutEvent.obtain(this.mTag, this.mScreenX, this.mScreenY, this.mScreenWidth, this.mScreenHeight));
            }
        }
    }

    /* loaded from: classes2.dex */
    private final class FindTargetForTouchOperation implements UIOperation {
        private final Callback mCallback;
        private final int mReactTag;
        private final float mTargetX;
        private final float mTargetY;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.mReactTag, UIViewOperationQueue.this.mMeasureBuffer);
                float f = UIViewOperationQueue.this.mMeasureBuffer[0];
                float f2 = UIViewOperationQueue.this.mMeasureBuffer[1];
                int findTargetTagForTouch = UIViewOperationQueue.this.mNativeViewHierarchyManager.findTargetTagForTouch(this.mReactTag, this.mTargetX, this.mTargetY);
                try {
                    UIViewOperationQueue.this.mNativeViewHierarchyManager.measure(findTargetTagForTouch, UIViewOperationQueue.this.mMeasureBuffer);
                    this.mCallback.invoke(Integer.valueOf(findTargetTagForTouch), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0] - f)), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1] - f2)), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])));
                } catch (IllegalViewOperationException unused) {
                    this.mCallback.invoke(new Object[0]);
                }
            } catch (IllegalViewOperationException unused2) {
                this.mCallback.invoke(new Object[0]);
            }
        }

        private FindTargetForTouchOperation(int i, float f, float f2, Callback callback) {
            this.mReactTag = i;
            this.mTargetX = f;
            this.mTargetY = f2;
            this.mCallback = callback;
        }
    }

    /* loaded from: classes2.dex */
    private final class LayoutUpdateFinishedOperation implements UIOperation {
        private final UIImplementation.LayoutUpdateListener mListener;
        private final ReactShadowNode mNode;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            this.mListener.onLayoutUpdated(this.mNode);
        }

        private LayoutUpdateFinishedOperation(ReactShadowNode reactShadowNode, UIImplementation.LayoutUpdateListener layoutUpdateListener) {
            this.mNode = reactShadowNode;
            this.mListener = layoutUpdateListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class ManageChildrenOperation extends ViewOperation {
        @Nullable
        private final int[] mIndicesToRemove;
        @Nullable
        private final int[] mTagsToDelete;
        @Nullable
        private final ViewAtIndex[] mViewsToAdd;

        public ManageChildrenOperation(int i, @Nullable int[] iArr, @Nullable ViewAtIndex[] viewAtIndexArr, @Nullable int[] iArr2) {
            super(i);
            this.mIndicesToRemove = iArr;
            this.mViewsToAdd = viewAtIndexArr;
            this.mTagsToDelete = iArr2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.manageChildren(this.mTag, this.mIndicesToRemove, this.mViewsToAdd, this.mTagsToDelete);
        }
    }

    /* loaded from: classes2.dex */
    private final class MeasureInWindowOperation implements UIOperation {
        private final Callback mCallback;
        private final int mReactTag;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.measureInWindow(this.mReactTag, UIViewOperationQueue.this.mMeasureBuffer);
                this.mCallback.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])));
            } catch (NoSuchNativeViewException unused) {
                this.mCallback.invoke(new Object[0]);
            }
        }

        private MeasureInWindowOperation(int i, Callback callback) {
            this.mReactTag = i;
            this.mCallback = callback;
        }
    }

    /* loaded from: classes2.dex */
    private final class MeasureOperation implements UIOperation {
        private final Callback mCallback;
        private final int mReactTag;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.mReactTag, UIViewOperationQueue.this.mMeasureBuffer);
                float dIPFromPixel = PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0]);
                float dIPFromPixel2 = PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1]);
                this.mCallback.invoke(0, 0, Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])), Float.valueOf(dIPFromPixel), Float.valueOf(dIPFromPixel2));
            } catch (NoSuchNativeViewException unused) {
                this.mCallback.invoke(new Object[0]);
            }
        }

        private MeasureOperation(int i, Callback callback) {
            this.mReactTag = i;
            this.mCallback = callback;
        }
    }

    /* loaded from: classes2.dex */
    private final class RemoveRootViewOperation extends ViewOperation {
        public RemoveRootViewOperation(int i) {
            super(i);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.removeRootView(this.mTag);
        }
    }

    /* loaded from: classes2.dex */
    private final class SendAccessibilityEvent extends ViewOperation {
        private final int mEventType;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.sendAccessibilityEvent(this.mTag, this.mEventType);
        }

        private SendAccessibilityEvent(int i, int i2) {
            super(i);
            this.mEventType = i2;
        }
    }

    /* loaded from: classes2.dex */
    private final class SetChildrenOperation extends ViewOperation {
        private final ReadableArray mChildrenTags;

        public SetChildrenOperation(int i, ReadableArray readableArray) {
            super(i);
            this.mChildrenTags = readableArray;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.setChildren(this.mTag, this.mChildrenTags);
        }
    }

    /* loaded from: classes2.dex */
    private class SetLayoutAnimationEnabledOperation implements UIOperation {
        private final boolean mEnabled;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.setLayoutAnimationEnabled(this.mEnabled);
        }

        private SetLayoutAnimationEnabledOperation(boolean z) {
            this.mEnabled = z;
        }
    }

    /* loaded from: classes2.dex */
    private final class ShowPopupMenuOperation extends ViewOperation {
        private final Callback mError;
        private final ReadableArray mItems;
        private final Callback mSuccess;

        public ShowPopupMenuOperation(int i, ReadableArray readableArray, Callback callback, Callback callback2) {
            super(i);
            this.mItems = readableArray;
            this.mError = callback;
            this.mSuccess = callback2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.showPopupMenu(this.mTag, this.mItems, this.mSuccess, this.mError);
        }
    }

    /* loaded from: classes2.dex */
    private class UIBlockOperation implements UIOperation {
        private final UIBlock mBlock;

        public UIBlockOperation(UIBlock uIBlock) {
            this.mBlock = uIBlock;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            this.mBlock.execute(UIViewOperationQueue.this.mNativeViewHierarchyManager);
        }
    }

    /* loaded from: classes2.dex */
    public interface UIOperation {
        void execute();
    }

    /* loaded from: classes2.dex */
    private final class UpdateInstanceHandleOperation extends ViewOperation {
        private final long mInstanceHandle;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateInstanceHandle(this.mTag, this.mInstanceHandle);
        }

        private UpdateInstanceHandleOperation(int i, long j) {
            super(i);
            this.mInstanceHandle = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class UpdateLayoutOperation extends ViewOperation {
        private final int mHeight;
        private final int mParentTag;
        private final int mWidth;
        private final int mX;
        private final int mY;

        public UpdateLayoutOperation(int i, int i2, int i3, int i4, int i5, int i6) {
            super(i2);
            this.mParentTag = i;
            this.mX = i3;
            this.mY = i4;
            this.mWidth = i5;
            this.mHeight = i6;
            Systrace.startAsyncFlow(0L, "updateLayout", this.mTag);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            Systrace.endAsyncFlow(0L, "updateLayout", this.mTag);
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateLayout(this.mParentTag, this.mTag, this.mX, this.mY, this.mWidth, this.mHeight);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class UpdatePropertiesOperation extends ViewOperation {
        private final ReactStylesDiffMap mProps;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateProperties(this.mTag, this.mProps);
        }

        private UpdatePropertiesOperation(int i, ReactStylesDiffMap reactStylesDiffMap) {
            super(i);
            this.mProps = reactStylesDiffMap;
        }
    }

    /* loaded from: classes2.dex */
    private final class UpdateViewExtraData extends ViewOperation {
        private final Object mExtraData;

        public UpdateViewExtraData(int i, Object obj) {
            super(i);
            this.mExtraData = obj;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateViewExtraData(this.mTag, this.mExtraData);
        }
    }

    /* loaded from: classes2.dex */
    private abstract class ViewOperation implements UIOperation {
        public int mTag;

        public ViewOperation(int i) {
            this.mTag = i;
        }
    }

    public UIViewOperationQueue(ReactApplicationContext reactApplicationContext, NativeViewHierarchyManager nativeViewHierarchyManager, int i) {
        this.mNativeViewHierarchyManager = nativeViewHierarchyManager;
        this.mDispatchUIFrameCallback = new DispatchUIFrameCallback(reactApplicationContext, i == -1 ? 8 : i);
        this.mReactApplicationContext = reactApplicationContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flushPendingBatches() {
        if (this.mIsInIllegalUIState) {
            FLog.w(ReactConstants.TAG, "Not flushing pending UI operations because of previously thrown Exception");
            return;
        }
        synchronized (this.mDispatchRunnablesLock) {
            if (this.mDispatchUIRunnables.isEmpty()) {
                return;
            }
            ArrayList<Runnable> arrayList = this.mDispatchUIRunnables;
            this.mDispatchUIRunnables = new ArrayList<>();
            long uptimeMillis = SystemClock.uptimeMillis();
            Iterator<Runnable> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                it2.next().run();
            }
            if (this.mIsProfilingNextBatch) {
                this.mProfiledBatchBatchedExecutionTime = SystemClock.uptimeMillis() - uptimeMillis;
                this.mProfiledBatchNonBatchedExecutionTime = this.mNonBatchedExecutionTotalTime;
                this.mIsProfilingNextBatch = false;
                Systrace.beginAsyncSection(0L, "batchedExecutionTime", 0, 1000000 * uptimeMillis);
                Systrace.endAsyncSection(0L, "batchedExecutionTime", 0);
            }
            this.mNonBatchedExecutionTotalTime = 0L;
        }
    }

    public void addRootView(int i, View view) {
        this.mNativeViewHierarchyManager.addRootView(i, view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v8 */
    public void dispatchViewUpdates(final int i, final long j, final long j2) {
        long j3;
        final long uptimeMillis;
        final long currentThreadTimeMillis;
        final ArrayList<DispatchCommandViewOperation> arrayList;
        final ArrayList<UIOperation> arrayList2;
        final ArrayDeque<UIOperation> arrayDeque;
        SystraceMessage.beginSection(0L, "UIViewOperationQueue.dispatchViewUpdates").arg("batchId", i).flush();
        try {
            uptimeMillis = SystemClock.uptimeMillis();
            currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
            j3 = 0;
            ArrayDeque<UIOperation> arrayDeque2 = null;
            if (!this.mViewCommandOperations.isEmpty()) {
                ArrayList<DispatchCommandViewOperation> arrayList3 = this.mViewCommandOperations;
                this.mViewCommandOperations = new ArrayList<>();
                arrayList = arrayList3;
            } else {
                arrayList = null;
            }
            if (!this.mOperations.isEmpty()) {
                ArrayList<UIOperation> arrayList4 = this.mOperations;
                this.mOperations = new ArrayList<>();
                arrayList2 = arrayList4;
            } else {
                arrayList2 = null;
            }
            synchronized (this.mNonBatchedOperationsLock) {
                try {
                    if (!this.mNonBatchedOperations.isEmpty()) {
                        arrayDeque2 = this.mNonBatchedOperations;
                        this.mNonBatchedOperations = new ArrayDeque<>();
                    }
                    arrayDeque = arrayDeque2;
                } catch (Throwable th) {
                    th = th;
                }
            }
            if (this.mViewHierarchyUpdateDebugListener != null) {
                this.mViewHierarchyUpdateDebugListener.onViewHierarchyUpdateEnqueued();
            }
        } catch (Throwable th2) {
            th = th2;
            j3 = 0;
        }
        try {
            Runnable runnable = new Runnable() { // from class: com.facebook.react.uimanager.UIViewOperationQueue.1
                @Override // java.lang.Runnable
                public void run() {
                    SystraceMessage.beginSection(0L, "DispatchUI").arg("BatchId", i).flush();
                    try {
                        try {
                            long uptimeMillis2 = SystemClock.uptimeMillis();
                            if (arrayList != null) {
                                Iterator it2 = arrayList.iterator();
                                while (it2.hasNext()) {
                                    DispatchCommandViewOperation dispatchCommandViewOperation = (DispatchCommandViewOperation) it2.next();
                                    try {
                                        dispatchCommandViewOperation.executeWithExceptions();
                                    } catch (RetryableMountingLayerException e) {
                                        if (dispatchCommandViewOperation.getRetries() != 0) {
                                            ReactSoftException.logSoftException(UIViewOperationQueue.TAG, new ReactNoCrashSoftException(e));
                                        } else {
                                            dispatchCommandViewOperation.incrementRetries();
                                            UIViewOperationQueue.this.mViewCommandOperations.add(dispatchCommandViewOperation);
                                        }
                                    } catch (Throwable th3) {
                                        ReactSoftException.logSoftException(UIViewOperationQueue.TAG, th3);
                                    }
                                }
                            }
                            if (arrayDeque != null) {
                                Iterator it3 = arrayDeque.iterator();
                                while (it3.hasNext()) {
                                    ((UIOperation) it3.next()).execute();
                                }
                            }
                            if (arrayList2 != null) {
                                Iterator it4 = arrayList2.iterator();
                                while (it4.hasNext()) {
                                    ((UIOperation) it4.next()).execute();
                                }
                            }
                            if (UIViewOperationQueue.this.mIsProfilingNextBatch && UIViewOperationQueue.this.mProfiledBatchCommitStartTime == 0) {
                                UIViewOperationQueue.this.mProfiledBatchCommitStartTime = j;
                                UIViewOperationQueue.this.mProfiledBatchCommitEndTime = SystemClock.uptimeMillis();
                                UIViewOperationQueue.this.mProfiledBatchLayoutTime = j2;
                                UIViewOperationQueue.this.mProfiledBatchDispatchViewUpdatesTime = uptimeMillis;
                                UIViewOperationQueue.this.mProfiledBatchRunStartTime = uptimeMillis2;
                                UIViewOperationQueue.this.mProfiledBatchRunEndTime = UIViewOperationQueue.this.mProfiledBatchCommitEndTime;
                                UIViewOperationQueue.this.mThreadCpuTime = currentThreadTimeMillis;
                                Systrace.beginAsyncSection(0L, "delayBeforeDispatchViewUpdates", 0, UIViewOperationQueue.this.mProfiledBatchCommitStartTime * 1000000);
                                Systrace.endAsyncSection(0L, "delayBeforeDispatchViewUpdates", 0, UIViewOperationQueue.this.mProfiledBatchDispatchViewUpdatesTime * 1000000);
                                Systrace.beginAsyncSection(0L, "delayBeforeBatchRunStart", 0, UIViewOperationQueue.this.mProfiledBatchDispatchViewUpdatesTime * 1000000);
                                Systrace.endAsyncSection(0L, "delayBeforeBatchRunStart", 0, UIViewOperationQueue.this.mProfiledBatchRunStartTime * 1000000);
                            }
                            UIViewOperationQueue.this.mNativeViewHierarchyManager.clearLayoutAnimation();
                            if (UIViewOperationQueue.this.mViewHierarchyUpdateDebugListener != null) {
                                UIViewOperationQueue.this.mViewHierarchyUpdateDebugListener.onViewHierarchyUpdateFinished();
                            }
                        } catch (Exception e2) {
                            UIViewOperationQueue.this.mIsInIllegalUIState = true;
                            throw e2;
                        }
                    } finally {
                        Systrace.endSection(0L);
                    }
                }
            };
            j3 = 0;
            SystraceMessage.beginSection(0L, "acquiring mDispatchRunnablesLock").arg("batchId", i).flush();
            synchronized (this.mDispatchRunnablesLock) {
                Systrace.endSection(0L);
                this.mDispatchUIRunnables.add(runnable);
            }
            if (!this.mIsDispatchUIFrameCallbackEnqueued) {
                UiThreadUtil.runOnUiThread(new GuardedRunnable(this.mReactApplicationContext) { // from class: com.facebook.react.uimanager.UIViewOperationQueue.2
                    @Override // com.facebook.react.bridge.GuardedRunnable
                    public void runGuarded() {
                        UIViewOperationQueue.this.flushPendingBatches();
                    }
                });
            }
            Systrace.endSection(0L);
        } catch (Throwable th3) {
            th = th3;
            j3 = 0;
            Systrace.endSection(j3);
            throw th;
        }
    }

    public void enqueueClearJSResponder() {
        this.mOperations.add(new ChangeJSResponderOperation(0, 0, true, false));
    }

    public void enqueueConfigureLayoutAnimation(ReadableMap readableMap, Callback callback) {
        this.mOperations.add(new ConfigureLayoutAnimationOperation(readableMap, callback));
    }

    public void enqueueCreateView(ThemedReactContext themedReactContext, int i, String str, @Nullable ReactStylesDiffMap reactStylesDiffMap) {
        synchronized (this.mNonBatchedOperationsLock) {
            this.mCreateViewCount++;
            this.mNonBatchedOperations.addLast(new CreateViewOperation(themedReactContext, i, str, reactStylesDiffMap));
        }
    }

    public void enqueueDismissPopupMenu() {
        this.mOperations.add(new DismissPopupMenuOperation());
    }

    @Deprecated
    public void enqueueDispatchCommand(int i, int i2, @Nullable ReadableArray readableArray) {
        this.mViewCommandOperations.add(new DispatchCommandOperation(i, i2, readableArray));
    }

    public void enqueueFindTargetForTouch(int i, float f, float f2, Callback callback) {
        this.mOperations.add(new FindTargetForTouchOperation(i, f, f2, callback));
    }

    public void enqueueLayoutUpdateFinished(ReactShadowNode reactShadowNode, UIImplementation.LayoutUpdateListener layoutUpdateListener) {
        this.mOperations.add(new LayoutUpdateFinishedOperation(reactShadowNode, layoutUpdateListener));
    }

    public void enqueueManageChildren(int i, @Nullable int[] iArr, @Nullable ViewAtIndex[] viewAtIndexArr, @Nullable int[] iArr2) {
        this.mOperations.add(new ManageChildrenOperation(i, iArr, viewAtIndexArr, iArr2));
    }

    public void enqueueMeasure(int i, Callback callback) {
        this.mOperations.add(new MeasureOperation(i, callback));
    }

    public void enqueueMeasureInWindow(int i, Callback callback) {
        this.mOperations.add(new MeasureInWindowOperation(i, callback));
    }

    public void enqueueOnLayoutEvent(int i, int i2, int i3, int i4, int i5) {
        this.mOperations.add(new EmitOnLayoutEventOperation(i, i2, i3, i4, i5));
    }

    public void enqueueRemoveRootView(int i) {
        this.mOperations.add(new RemoveRootViewOperation(i));
    }

    public void enqueueSendAccessibilityEvent(int i, int i2) {
        this.mOperations.add(new SendAccessibilityEvent(i, i2));
    }

    public void enqueueSetChildren(int i, ReadableArray readableArray) {
        this.mOperations.add(new SetChildrenOperation(i, readableArray));
    }

    public void enqueueSetJSResponder(int i, int i2, boolean z) {
        this.mOperations.add(new ChangeJSResponderOperation(i, i2, false, z));
    }

    public void enqueueSetLayoutAnimationEnabled(boolean z) {
        this.mOperations.add(new SetLayoutAnimationEnabledOperation(z));
    }

    public void enqueueShowPopupMenu(int i, ReadableArray readableArray, Callback callback, Callback callback2) {
        this.mOperations.add(new ShowPopupMenuOperation(i, readableArray, callback, callback2));
    }

    public void enqueueUIBlock(UIBlock uIBlock) {
        this.mOperations.add(new UIBlockOperation(uIBlock));
    }

    protected void enqueueUIOperation(UIOperation uIOperation) {
        SoftAssertions.assertNotNull(uIOperation);
        this.mOperations.add(uIOperation);
    }

    public void enqueueUpdateExtraData(int i, Object obj) {
        this.mOperations.add(new UpdateViewExtraData(i, obj));
    }

    public void enqueueUpdateInstanceHandle(int i, long j) {
        this.mOperations.add(new UpdateInstanceHandleOperation(i, j));
    }

    public void enqueueUpdateLayout(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mOperations.add(new UpdateLayoutOperation(i, i2, i3, i4, i5, i6));
    }

    public void enqueueUpdateProperties(int i, String str, ReactStylesDiffMap reactStylesDiffMap) {
        this.mUpdatePropertiesOperationCount++;
        this.mOperations.add(new UpdatePropertiesOperation(i, reactStylesDiffMap));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeViewHierarchyManager getNativeViewHierarchyManager() {
        return this.mNativeViewHierarchyManager;
    }

    public Map<String, Long> getProfiledBatchPerfCounters() {
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(this.mProfiledBatchCommitStartTime));
        hashMap.put("CommitEndTime", Long.valueOf(this.mProfiledBatchCommitEndTime));
        hashMap.put("LayoutTime", Long.valueOf(this.mProfiledBatchLayoutTime));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.mProfiledBatchDispatchViewUpdatesTime));
        hashMap.put("RunStartTime", Long.valueOf(this.mProfiledBatchRunStartTime));
        hashMap.put("RunEndTime", Long.valueOf(this.mProfiledBatchRunEndTime));
        hashMap.put("BatchedExecutionTime", Long.valueOf(this.mProfiledBatchBatchedExecutionTime));
        hashMap.put("NonBatchedExecutionTime", Long.valueOf(this.mProfiledBatchNonBatchedExecutionTime));
        hashMap.put("NativeModulesThreadCpuTime", Long.valueOf(this.mThreadCpuTime));
        hashMap.put("CreateViewCount", Long.valueOf(this.mCreateViewCount));
        hashMap.put("UpdatePropsCount", Long.valueOf(this.mUpdatePropertiesOperationCount));
        return hashMap;
    }

    public boolean isEmpty() {
        return this.mOperations.isEmpty() && this.mViewCommandOperations.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pauseFrameCallback() {
        this.mIsDispatchUIFrameCallbackEnqueued = false;
        ReactChoreographer.getInstance().removeFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
        flushPendingBatches();
    }

    public void prependUIBlock(UIBlock uIBlock) {
        this.mOperations.add(0, new UIBlockOperation(uIBlock));
    }

    public void profileNextBatch() {
        this.mIsProfilingNextBatch = true;
        this.mProfiledBatchCommitStartTime = 0L;
        this.mCreateViewCount = 0L;
        this.mUpdatePropertiesOperationCount = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resumeFrameCallback() {
        this.mIsDispatchUIFrameCallbackEnqueued = true;
        ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
    }

    public void setViewHierarchyUpdateDebugListener(@Nullable NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener) {
        this.mViewHierarchyUpdateDebugListener = notThreadSafeViewHierarchyUpdateDebugListener;
    }

    public void enqueueDispatchCommand(int i, String str, @Nullable ReadableArray readableArray) {
        this.mViewCommandOperations.add(new DispatchStringCommandOperation(i, str, readableArray));
    }
}
