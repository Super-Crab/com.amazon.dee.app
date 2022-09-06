package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.view.View;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.yoga.YogaDirection;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class UIImplementation {
    protected final EventDispatcher mEventDispatcher;
    private long mLastCalculateLayoutTime;
    @Nullable
    protected LayoutUpdateListener mLayoutUpdateListener;
    private final int[] mMeasureBuffer;
    private final NativeViewHierarchyOptimizer mNativeViewHierarchyOptimizer;
    private final UIViewOperationQueue mOperationsQueue;
    protected final ReactApplicationContext mReactContext;
    protected final ShadowNodeRegistry mShadowNodeRegistry;
    private final ViewManagerRegistry mViewManagers;
    protected Object uiImplementationThreadLock;

    /* loaded from: classes2.dex */
    public interface LayoutUpdateListener {
        void onLayoutUpdated(ReactShadowNode reactShadowNode);
    }

    public UIImplementation(ReactApplicationContext reactApplicationContext, UIManagerModule.ViewManagerResolver viewManagerResolver, EventDispatcher eventDispatcher, int i) {
        this(reactApplicationContext, new ViewManagerRegistry(viewManagerResolver), eventDispatcher, i);
    }

    private void assertNodeDoesNotNeedCustomLayoutForChildren(ReactShadowNode reactShadowNode) {
        ViewManager viewManager = (ViewManager) Assertions.assertNotNull(this.mViewManagers.get(reactShadowNode.getViewClass()));
        if (viewManager instanceof IViewManagerWithChildren) {
            IViewManagerWithChildren iViewManagerWithChildren = (IViewManagerWithChildren) viewManager;
            if (iViewManagerWithChildren == null || !iViewManagerWithChildren.needsCustomLayoutForChildren()) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Trying to measure a view using measureLayout/measureLayoutRelativeToParent relative to an ancestor that requires custom layout for it's children (");
            outline107.append(reactShadowNode.getViewClass());
            outline107.append("). Use measure instead.");
            throw new IllegalViewOperationException(outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Trying to use view ");
        outline1072.append(reactShadowNode.getViewClass());
        outline1072.append(" as a parent, but its Manager doesn't extends ViewGroupManager");
        throw new IllegalViewOperationException(outline1072.toString());
    }

    private void assertViewExists(int i, String str) {
        if (this.mShadowNodeRegistry.getNode(i) != null) {
            return;
        }
        throw new IllegalViewOperationException("Unable to execute operation " + str + " on view with tag: " + i + ", since the view does not exists");
    }

    private void dispatchViewUpdatesIfNeeded() {
        if (this.mOperationsQueue.isEmpty()) {
            dispatchViewUpdates(-1);
        }
    }

    private void measureLayoutRelativeToVerifiedAncestor(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int[] iArr) {
        int i;
        int i2;
        if (reactShadowNode == reactShadowNode2 || reactShadowNode.isVirtual()) {
            i = 0;
            i2 = 0;
        } else {
            i = Math.round(reactShadowNode.getLayoutX());
            i2 = Math.round(reactShadowNode.getLayoutY());
            for (ReactShadowNode parent = reactShadowNode.getParent(); parent != reactShadowNode2; parent = parent.getParent()) {
                Assertions.assertNotNull(parent);
                assertNodeDoesNotNeedCustomLayoutForChildren(parent);
                i += Math.round(parent.getLayoutX());
                i2 += Math.round(parent.getLayoutY());
            }
            assertNodeDoesNotNeedCustomLayoutForChildren(reactShadowNode2);
        }
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = reactShadowNode.getScreenWidth();
        iArr[3] = reactShadowNode.getScreenHeight();
    }

    private void notifyOnBeforeLayoutRecursive(ReactShadowNode reactShadowNode) {
        if (!reactShadowNode.hasUpdates()) {
            return;
        }
        for (int i = 0; i < reactShadowNode.getChildCount(); i++) {
            notifyOnBeforeLayoutRecursive(reactShadowNode.getChildAt(i));
        }
        reactShadowNode.onBeforeLayout(this.mNativeViewHierarchyOptimizer);
    }

    private void removeShadowNodeRecursive(ReactShadowNode reactShadowNode) {
        NativeViewHierarchyOptimizer.handleRemoveNode(reactShadowNode);
        this.mShadowNodeRegistry.removeNode(reactShadowNode.getReactTag());
        for (int childCount = reactShadowNode.getChildCount() - 1; childCount >= 0; childCount--) {
            removeShadowNodeRecursive(reactShadowNode.getChildAt(childCount));
        }
        reactShadowNode.removeAndDisposeAllChildren();
    }

    public void addUIBlock(UIBlock uIBlock) {
        this.mOperationsQueue.enqueueUIBlock(uIBlock);
    }

    protected void applyUpdatesRecursive(ReactShadowNode reactShadowNode, float f, float f2) {
        if (!reactShadowNode.hasUpdates()) {
            return;
        }
        Iterable<? extends ReactShadowNode> calculateLayoutOnChildren = reactShadowNode.calculateLayoutOnChildren();
        if (calculateLayoutOnChildren != null) {
            for (ReactShadowNode reactShadowNode2 : calculateLayoutOnChildren) {
                applyUpdatesRecursive(reactShadowNode2, reactShadowNode.getLayoutX() + f, reactShadowNode.getLayoutY() + f2);
            }
        }
        int reactTag = reactShadowNode.getReactTag();
        if (!this.mShadowNodeRegistry.isRootNode(reactTag) && reactShadowNode.dispatchUpdates(f, f2, this.mOperationsQueue, this.mNativeViewHierarchyOptimizer) && reactShadowNode.shouldNotifyOnLayout()) {
            this.mEventDispatcher.dispatchEvent(OnLayoutEvent.obtain(reactTag, reactShadowNode.getScreenX(), reactShadowNode.getScreenY(), reactShadowNode.getScreenWidth(), reactShadowNode.getScreenHeight()));
        }
        reactShadowNode.markUpdateSeen();
        if (!ReactFeatureFlags.enableTransitionLayoutOnlyViewCleanup) {
            return;
        }
        this.mNativeViewHierarchyOptimizer.onViewUpdatesCompleted(reactShadowNode);
    }

    protected void calculateRootLayout(ReactShadowNode reactShadowNode) {
        SystraceMessage.beginSection(0L, "cssRoot.calculateLayout").arg("rootTag", reactShadowNode.getReactTag()).flush();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            int intValue = reactShadowNode.getWidthMeasureSpec().intValue();
            int intValue2 = reactShadowNode.getHeightMeasureSpec().intValue();
            float f = Float.NaN;
            float size = View.MeasureSpec.getMode(intValue) == 0 ? Float.NaN : View.MeasureSpec.getSize(intValue);
            if (View.MeasureSpec.getMode(intValue2) != 0) {
                f = View.MeasureSpec.getSize(intValue2);
            }
            reactShadowNode.calculateLayout(size, f);
        } finally {
            Systrace.endSection(0L);
            this.mLastCalculateLayoutTime = SystemClock.uptimeMillis() - uptimeMillis;
        }
    }

    public void clearJSResponder() {
        this.mOperationsQueue.enqueueClearJSResponder();
    }

    public void configureNextLayoutAnimation(ReadableMap readableMap, Callback callback) {
        this.mOperationsQueue.enqueueConfigureLayoutAnimation(readableMap, callback);
    }

    protected ReactShadowNode createRootShadowNode() {
        ReactShadowNodeImpl reactShadowNodeImpl = new ReactShadowNodeImpl();
        if (I18nUtil.getInstance().isRTL(this.mReactContext)) {
            reactShadowNodeImpl.setLayoutDirection(YogaDirection.RTL);
        }
        reactShadowNodeImpl.setViewClassName("Root");
        return reactShadowNodeImpl;
    }

    protected ReactShadowNode createShadowNode(String str) {
        return this.mViewManagers.get(str).createShadowNodeInstance(this.mReactContext);
    }

    public void createView(int i, String str, int i2, ReadableMap readableMap) {
        synchronized (this.uiImplementationThreadLock) {
            ReactShadowNode createShadowNode = createShadowNode(str);
            ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
            Assertions.assertNotNull(node, "Root node with tag " + i2 + " doesn't exist");
            createShadowNode.setReactTag(i);
            createShadowNode.setViewClassName(str);
            createShadowNode.setRootTag(node.getReactTag());
            createShadowNode.setThemedContext(node.getThemedContext());
            this.mShadowNodeRegistry.addNode(createShadowNode);
            ReactStylesDiffMap reactStylesDiffMap = null;
            if (readableMap != null) {
                reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
                createShadowNode.updateProperties(reactStylesDiffMap);
            }
            handleCreateView(createShadowNode, i2, reactStylesDiffMap);
        }
    }

    public void dismissPopupMenu() {
        this.mOperationsQueue.enqueueDismissPopupMenu();
    }

    @Deprecated
    public void dispatchViewManagerCommand(int i, int i2, @Nullable ReadableArray readableArray) {
        assertViewExists(i, GeneratedOutlineSupport1.outline49("dispatchViewManagerCommand: ", i2));
        this.mOperationsQueue.enqueueDispatchCommand(i, i2, readableArray);
    }

    public void dispatchViewUpdates(int i) {
        SystraceMessage.beginSection(0L, "UIImplementation.dispatchViewUpdates").arg("batchId", i).flush();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            updateViewHierarchy();
            this.mNativeViewHierarchyOptimizer.onBatchComplete();
            this.mOperationsQueue.dispatchViewUpdates(i, uptimeMillis, this.mLastCalculateLayoutTime);
        } finally {
            Systrace.endSection(0L);
        }
    }

    public void findSubviewIn(int i, float f, float f2, Callback callback) {
        this.mOperationsQueue.enqueueFindTargetForTouch(i, f, f2, callback);
    }

    public Map<String, Long> getProfiledBatchPerfCounters() {
        return this.mOperationsQueue.getProfiledBatchPerfCounters();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UIViewOperationQueue getUIViewOperationQueue() {
        return this.mOperationsQueue;
    }

    protected void handleCreateView(ReactShadowNode reactShadowNode, int i, @Nullable ReactStylesDiffMap reactStylesDiffMap) {
        if (!reactShadowNode.isVirtual()) {
            this.mNativeViewHierarchyOptimizer.handleCreateView(reactShadowNode, reactShadowNode.getThemedContext(), reactStylesDiffMap);
        }
    }

    protected void handleUpdateView(ReactShadowNode reactShadowNode, String str, ReactStylesDiffMap reactStylesDiffMap) {
        if (!reactShadowNode.isVirtual()) {
            this.mNativeViewHierarchyOptimizer.handleUpdateView(reactShadowNode, str, reactStylesDiffMap);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0044, code lost:
        if (r23 == null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004a, code lost:
        if (r11 != r23.size()) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0054, code lost:
        throw new com.facebook.react.uimanager.IllegalViewOperationException("Size of addChildTags != size of addAtIndices!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void manageChildren(int r19, @androidx.annotation.Nullable com.facebook.react.bridge.ReadableArray r20, @androidx.annotation.Nullable com.facebook.react.bridge.ReadableArray r21, @androidx.annotation.Nullable com.facebook.react.bridge.ReadableArray r22, @androidx.annotation.Nullable com.facebook.react.bridge.ReadableArray r23, @androidx.annotation.Nullable com.facebook.react.bridge.ReadableArray r24) {
        /*
            Method dump skipped, instructions count: 379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIImplementation.manageChildren(int, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.ReadableArray):void");
    }

    public void measure(int i, Callback callback) {
        this.mOperationsQueue.enqueueMeasure(i, callback);
    }

    public void measureInWindow(int i, Callback callback) {
        this.mOperationsQueue.enqueueMeasureInWindow(i, callback);
    }

    public void measureLayout(int i, int i2, Callback callback, Callback callback2) {
        try {
            measureLayout(i, i2, this.mMeasureBuffer);
            callback2.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[3])));
        } catch (IllegalViewOperationException e) {
            callback.invoke(e.getMessage());
        }
    }

    public void measureLayoutRelativeToParent(int i, Callback callback, Callback callback2) {
        try {
            measureLayoutRelativeToParent(i, this.mMeasureBuffer);
            callback2.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[3])));
        } catch (IllegalViewOperationException e) {
            callback.invoke(e.getMessage());
        }
    }

    public void onHostDestroy() {
    }

    public void onHostPause() {
        this.mOperationsQueue.pauseFrameCallback();
    }

    public void onHostResume() {
        this.mOperationsQueue.resumeFrameCallback();
    }

    public void prependUIBlock(UIBlock uIBlock) {
        this.mOperationsQueue.prependUIBlock(uIBlock);
    }

    public void profileNextBatch() {
        this.mOperationsQueue.profileNextBatch();
    }

    public <T extends View> void registerRootView(T t, int i, ThemedReactContext themedReactContext) {
        synchronized (this.uiImplementationThreadLock) {
            final ReactShadowNode createRootShadowNode = createRootShadowNode();
            createRootShadowNode.setReactTag(i);
            createRootShadowNode.setThemedContext(themedReactContext);
            themedReactContext.runOnNativeModulesQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.UIImplementation.1
                @Override // java.lang.Runnable
                public void run() {
                    UIImplementation.this.mShadowNodeRegistry.addRootNode(createRootShadowNode);
                }
            });
            this.mOperationsQueue.addRootView(i, t);
        }
    }

    public void removeLayoutUpdateListener() {
        this.mLayoutUpdateListener = null;
    }

    public void removeRootShadowNode(int i) {
        synchronized (this.uiImplementationThreadLock) {
            this.mShadowNodeRegistry.removeRootNode(i);
        }
    }

    public void removeRootView(int i) {
        removeRootShadowNode(i);
        this.mOperationsQueue.enqueueRemoveRootView(i);
    }

    protected final void removeShadowNode(ReactShadowNode reactShadowNode) {
        removeShadowNodeRecursive(reactShadowNode);
        reactShadowNode.dispose();
    }

    public void removeSubviewsFromContainerWithID(int i) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node != null) {
            WritableArray createArray = Arguments.createArray();
            for (int i2 = 0; i2 < node.getChildCount(); i2++) {
                createArray.pushInt(i2);
            }
            manageChildren(i, null, null, null, null, createArray);
            return;
        }
        throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline49("Trying to remove subviews of an unknown view tag: ", i));
    }

    public void replaceExistingNonRootView(int i, int i2) {
        if (!this.mShadowNodeRegistry.isRootNode(i) && !this.mShadowNodeRegistry.isRootNode(i2)) {
            ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
            if (node != null) {
                ReactShadowNode parent = node.getParent();
                if (parent != null) {
                    int indexOf = parent.indexOf(node);
                    if (indexOf >= 0) {
                        WritableArray createArray = Arguments.createArray();
                        createArray.pushInt(i2);
                        WritableArray createArray2 = Arguments.createArray();
                        createArray2.pushInt(indexOf);
                        WritableArray createArray3 = Arguments.createArray();
                        createArray3.pushInt(indexOf);
                        manageChildren(parent.getReactTag(), null, null, createArray, createArray2, createArray3);
                        return;
                    }
                    throw new IllegalStateException("Didn't find child tag in parent");
                }
                throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline49("Node is not attached to a parent: ", i));
            }
            throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline49("Trying to replace unknown view tag: ", i));
        }
        throw new IllegalViewOperationException("Trying to add or replace a root tag!");
    }

    public int resolveRootTagFromReactTag(int i) {
        if (this.mShadowNodeRegistry.isRootNode(i)) {
            return i;
        }
        ReactShadowNode resolveShadowNode = resolveShadowNode(i);
        if (resolveShadowNode != null) {
            return resolveShadowNode.getRootTag();
        }
        FLog.w(ReactConstants.TAG, "Warning : attempted to resolve a non-existent react shadow node. reactTag=" + i);
        return 0;
    }

    public final ReactShadowNode resolveShadowNode(int i) {
        return this.mShadowNodeRegistry.getNode(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final ViewManager resolveViewManager(String str) {
        return this.mViewManagers.getViewManagerIfExists(str);
    }

    public void sendAccessibilityEvent(int i, int i2) {
        this.mOperationsQueue.enqueueSendAccessibilityEvent(i, i2);
    }

    public void setChildren(int i, ReadableArray readableArray) {
        synchronized (this.uiImplementationThreadLock) {
            ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(readableArray.getInt(i2));
                if (node2 != null) {
                    node.addChildAt(node2, i2);
                } else {
                    throw new IllegalViewOperationException("Trying to add unknown view tag: " + readableArray.getInt(i2));
                }
            }
            this.mNativeViewHierarchyOptimizer.handleSetChildren(node, readableArray);
        }
    }

    public void setJSResponder(int i, boolean z) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node == null) {
            return;
        }
        while (node.getNativeKind() == NativeKind.NONE) {
            node = node.getParent();
        }
        this.mOperationsQueue.enqueueSetJSResponder(node.getReactTag(), i, z);
    }

    public void setLayoutAnimationEnabledExperimental(boolean z) {
        this.mOperationsQueue.enqueueSetLayoutAnimationEnabled(z);
    }

    public void setLayoutUpdateListener(LayoutUpdateListener layoutUpdateListener) {
        this.mLayoutUpdateListener = layoutUpdateListener;
    }

    public void setViewHierarchyUpdateDebugListener(@Nullable NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener) {
        this.mOperationsQueue.setViewHierarchyUpdateDebugListener(notThreadSafeViewHierarchyUpdateDebugListener);
    }

    public void setViewLocalData(int i, Object obj) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node == null) {
            FLog.w(ReactConstants.TAG, "Attempt to set local data for view with unknown tag: " + i);
            return;
        }
        node.setLocalData(obj);
        dispatchViewUpdatesIfNeeded();
    }

    public void showPopupMenu(int i, ReadableArray readableArray, Callback callback, Callback callback2) {
        assertViewExists(i, "showPopupMenu");
        this.mOperationsQueue.enqueueShowPopupMenu(i, readableArray, callback, callback2);
    }

    public void synchronouslyUpdateViewOnUIThread(int i, ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        this.mOperationsQueue.getNativeViewHierarchyManager().updateProperties(i, reactStylesDiffMap);
    }

    public void updateNodeSize(int i, int i2, int i3) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node == null) {
            FLog.w(ReactConstants.TAG, "Tried to update size of non-existent tag: " + i);
            return;
        }
        node.setStyleWidth(i2);
        node.setStyleHeight(i3);
        dispatchViewUpdatesIfNeeded();
    }

    public void updateRootView(int i, int i2, int i3) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node == null) {
            FLog.w(ReactConstants.TAG, "Tried to update non-existent root tag: " + i);
            return;
        }
        updateRootView(node, i2, i3);
    }

    public void updateView(int i, String str, ReadableMap readableMap) {
        if (this.mViewManagers.get(str) != null) {
            ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
            if (node == null) {
                throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline49("Trying to update non-existent view with tag ", i));
            }
            if (readableMap == null) {
                return;
            }
            ReactStylesDiffMap reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
            node.updateProperties(reactStylesDiffMap);
            handleUpdateView(node, str, reactStylesDiffMap);
            return;
        }
        throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline72("Got unknown view type: ", str));
    }

    protected void updateViewHierarchy() {
        Systrace.beginSection(0L, "UIImplementation.updateViewHierarchy");
        for (int i = 0; i < this.mShadowNodeRegistry.getRootNodeCount(); i++) {
            try {
                ReactShadowNode node = this.mShadowNodeRegistry.getNode(this.mShadowNodeRegistry.getRootTag(i));
                if (node.getWidthMeasureSpec() != null && node.getHeightMeasureSpec() != null) {
                    SystraceMessage.beginSection(0L, "UIImplementation.notifyOnBeforeLayoutRecursive").arg("rootTag", node.getReactTag()).flush();
                    notifyOnBeforeLayoutRecursive(node);
                    Systrace.endSection(0L);
                    calculateRootLayout(node);
                    SystraceMessage.beginSection(0L, "UIImplementation.applyUpdatesRecursive").arg("rootTag", node.getReactTag()).flush();
                    applyUpdatesRecursive(node, 0.0f, 0.0f);
                    Systrace.endSection(0L);
                    if (this.mLayoutUpdateListener != null) {
                        this.mOperationsQueue.enqueueLayoutUpdateFinished(node, this.mLayoutUpdateListener);
                    }
                }
            } finally {
                Systrace.endSection(0L);
            }
        }
    }

    @Deprecated
    public void viewIsDescendantOf(int i, int i2, Callback callback) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(i2);
        if (node == null || node2 == null) {
            callback.invoke(false);
        } else {
            callback.invoke(Boolean.valueOf(node.isDescendantOf(node2)));
        }
    }

    public UIImplementation(ReactApplicationContext reactApplicationContext, List<ViewManager> list, EventDispatcher eventDispatcher, int i) {
        this(reactApplicationContext, new ViewManagerRegistry(list), eventDispatcher, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, int i) {
        this(reactApplicationContext, viewManagerRegistry, new UIViewOperationQueue(reactApplicationContext, new NativeViewHierarchyManager(viewManagerRegistry), i), eventDispatcher);
    }

    public void dispatchViewManagerCommand(int i, String str, @Nullable ReadableArray readableArray) {
        assertViewExists(i, GeneratedOutlineSupport1.outline72("dispatchViewManagerCommand: ", str));
        this.mOperationsQueue.enqueueDispatchCommand(i, str, readableArray);
    }

    protected UIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, UIViewOperationQueue uIViewOperationQueue, EventDispatcher eventDispatcher) {
        this.uiImplementationThreadLock = new Object();
        this.mShadowNodeRegistry = new ShadowNodeRegistry();
        this.mMeasureBuffer = new int[4];
        this.mLastCalculateLayoutTime = 0L;
        this.mReactContext = reactApplicationContext;
        this.mViewManagers = viewManagerRegistry;
        this.mOperationsQueue = uIViewOperationQueue;
        this.mNativeViewHierarchyOptimizer = new NativeViewHierarchyOptimizer(this.mOperationsQueue, this.mShadowNodeRegistry);
        this.mEventDispatcher = eventDispatcher;
    }

    public void updateRootView(ReactShadowNode reactShadowNode, int i, int i2) {
        reactShadowNode.setMeasureSpecs(i, i2);
    }

    private void measureLayout(int i, int i2, int[] iArr) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(i2);
        if (node != null && node2 != null) {
            if (node != node2) {
                for (ReactShadowNode parent = node.getParent(); parent != node2; parent = parent.getParent()) {
                    if (parent == null) {
                        throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline53("Tag ", i2, " is not an ancestor of tag ", i));
                    }
                }
            }
            measureLayoutRelativeToVerifiedAncestor(node, node2, iArr);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Tag ");
        if (node != null) {
            i = i2;
        }
        throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline86(outline107, i, " does not exist"));
    }

    private void measureLayoutRelativeToParent(int i, int[] iArr) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i);
        if (node != null) {
            ReactShadowNode parent = node.getParent();
            if (parent != null) {
                measureLayoutRelativeToVerifiedAncestor(node, parent, iArr);
                return;
            }
            throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline52("View with tag ", i, " doesn't have a parent!"));
        }
        throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline52("No native view for tag ", i, " exists!"));
    }
}
