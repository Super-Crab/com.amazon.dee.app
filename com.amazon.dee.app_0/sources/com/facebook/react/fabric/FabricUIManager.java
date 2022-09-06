package com.facebook.react.fabric;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.SystemClock;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.infer.annotation.ThreadConfined;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.events.FabricEventEmitter;
import com.facebook.react.fabric.mounting.LayoutMetricsConversions;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.mountitems.BatchMountItem;
import com.facebook.react.fabric.mounting.mountitems.CreateMountItem;
import com.facebook.react.fabric.mounting.mountitems.DispatchCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.DispatchIntCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.DispatchStringCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.InsertMountItem;
import com.facebook.react.fabric.mounting.mountitems.IntBufferBatchMountItem;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.fabric.mounting.mountitems.PreAllocateViewMountItem;
import com.facebook.react.fabric.mounting.mountitems.RemoveDeleteMultiMountItem;
import com.facebook.react.fabric.mounting.mountitems.SendAccessibilityEvent;
import com.facebook.react.fabric.mounting.mountitems.UpdateEventEmitterMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateLayoutMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdatePaddingMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdatePropsMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateStateMountItem;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.ReactRootViewTagGenerator;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewManagerPropertyUpdater;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.text.TextLayoutManager;
import com.facebook.systrace.Systrace;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
@SuppressLint({"MissingNativeLoadLibrary"})
/* loaded from: classes2.dex */
public class FabricUIManager implements UIManager, LifecycleEventListener {
    public static final boolean ENABLE_FABRIC_LOGS;
    private static final int FRAME_TIME_MS = 16;
    public static final boolean IS_DEVELOPMENT_ENVIRONMENT = false;
    private static final int MAX_TIME_IN_FRAME_FOR_NON_BATCHED_OPERATIONS_MS = 8;
    private static final int PRE_MOUNT_ITEMS_INITIAL_SIZE_ARRAY = 250;
    public static final String TAG = "FabricUIManager";
    @Nullable
    private Binding mBinding;
    @NonNull
    @ThreadConfined(ThreadConfined.UI)
    private final DispatchUIFrameCallback mDispatchUIFrameCallback;
    @NonNull
    private final EventBeatManager mEventBeatManager;
    @NonNull
    private final EventDispatcher mEventDispatcher;
    @NonNull
    private final MountingManager mMountingManager;
    @NonNull
    private final ReactApplicationContext mReactApplicationContext;
    @NonNull
    private final ConcurrentHashMap<Integer, ThemedReactContext> mReactContextForRootTag = new ConcurrentHashMap<>();
    @NonNull
    private final Object mViewCommandMountItemsLock = new Object();
    @NonNull
    private final Object mMountItemsLock = new Object();
    @NonNull
    private final Object mPreMountItemsLock = new Object();
    private boolean mInDispatch = false;
    private int mReDispatchCounter = 0;
    @NonNull
    @GuardedBy("mViewCommandMountItemsLock")
    private List<DispatchCommandMountItem> mViewCommandMountItems = new ArrayList();
    @NonNull
    private final CopyOnWriteArrayList<UIManagerListener> mListeners = new CopyOnWriteArrayList<>();
    @NonNull
    @GuardedBy("mMountItemsLock")
    private List<MountItem> mMountItems = new ArrayList();
    @NonNull
    @GuardedBy("mPreMountItemsLock")
    private ArrayDeque<PreAllocateViewMountItem> mPreMountItems = new ArrayDeque<>(250);
    @ThreadConfined(ThreadConfined.UI)
    private int mLastExecutedMountItemSurfaceId = -1;
    @ThreadConfined(ThreadConfined.UI)
    private boolean mLastExecutedMountItemSurfaceIdActive = false;
    private volatile boolean mDestroyed = false;
    private boolean mDriveCxxAnimations = false;
    private long mRunStartTime = 0;
    private long mBatchedExecutionTime = 0;
    private long mDispatchViewUpdatesTime = 0;
    private long mCommitStartTime = 0;
    private long mLayoutTime = 0;
    private long mFinishTransactionTime = 0;
    private long mFinishTransactionCPPTime = 0;
    private int mCurrentSynchronousCommitNumber = 10000;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DispatchUIFrameCallback extends GuardedFrameCallback {
        private volatile boolean mIsMountingEnabled;

        @Override // com.facebook.react.fabric.GuardedFrameCallback
        @ThreadConfined(ThreadConfined.UI)
        @UiThread
        public void doFrameGuarded(long j) {
            if (this.mIsMountingEnabled && !FabricUIManager.this.mDestroyed) {
                if (FabricUIManager.this.mDriveCxxAnimations && FabricUIManager.this.mBinding != null) {
                    FabricUIManager.this.mBinding.driveCxxAnimations();
                }
                try {
                    try {
                        FabricUIManager.this.dispatchPreMountItems(j);
                        FabricUIManager.this.tryDispatchMountItems();
                        return;
                    } catch (Exception e) {
                        FLog.e(FabricUIManager.TAG, "Exception thrown when executing UIFrameGuarded", e);
                        stop();
                        throw e;
                    }
                } finally {
                    ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, FabricUIManager.this.mDispatchUIFrameCallback);
                }
            }
            FLog.w(FabricUIManager.TAG, "Not flushing pending UI operations because of previously thrown Exception");
        }

        @AnyThread
        void stop() {
            this.mIsMountingEnabled = false;
        }

        private DispatchUIFrameCallback(@NonNull ReactContext reactContext) {
            super(reactContext);
            this.mIsMountingEnabled = true;
        }
    }

    static {
        ENABLE_FABRIC_LOGS = ReactFeatureFlags.enableFabricLogs || PrinterHolder.getPrinter().shouldDisplayLogMessage(ReactDebugOverlayTags.FABRIC_UI_MANAGER);
        FabricSoLoader.staticInit();
    }

    public FabricUIManager(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, EventBeatManager eventBeatManager) {
        this.mDispatchUIFrameCallback = new DispatchUIFrameCallback(reactApplicationContext);
        this.mReactApplicationContext = reactApplicationContext;
        this.mMountingManager = new MountingManager(viewManagerRegistry);
        this.mEventDispatcher = eventDispatcher;
        this.mEventBeatManager = eventBeatManager;
        this.mReactApplicationContext.addLifecycleEventListener(this);
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private MountItem createBatchMountItem(int i, MountItem[] mountItemArr, int i2, int i3) {
        return new BatchMountItem(i, mountItemArr, i2, i3);
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private MountItem createIntBufferBatchMountItem(int i, int[] iArr, Object[] objArr, int i2) {
        return new IntBufferBatchMountItem(i, this.mReactContextForRootTag.get(Integer.valueOf(i)), iArr, objArr, i2);
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private MountItem createMountItem(String str, @Nullable ReadableMap readableMap, @Nullable Object obj, int i, int i2, boolean z) {
        return new CreateMountItem(this.mReactContextForRootTag.get(Integer.valueOf(i)), i, i2, FabricComponents.getFabricComponentName(str), readableMap, (StateWrapper) obj, z);
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private void dispatchCommandMountItem(DispatchCommandMountItem dispatchCommandMountItem) {
        synchronized (this.mViewCommandMountItemsLock) {
            this.mViewCommandMountItems.add(dispatchCommandMountItem);
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    private boolean dispatchMountItems() {
        boolean isIgnorable;
        if (this.mReDispatchCounter == 0) {
            this.mBatchedExecutionTime = 0L;
        }
        this.mRunStartTime = SystemClock.uptimeMillis();
        List<DispatchCommandMountItem> andResetViewCommandMountItems = getAndResetViewCommandMountItems();
        List<MountItem> andResetMountItems = getAndResetMountItems();
        if (andResetMountItems == null && andResetViewCommandMountItems == null) {
            return false;
        }
        if (andResetViewCommandMountItems != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FabricUIManager::mountViews viewCommandMountItems to execute: ");
            outline107.append(andResetViewCommandMountItems.size());
            Systrace.beginSection(0L, outline107.toString());
            for (DispatchCommandMountItem dispatchCommandMountItem : andResetViewCommandMountItems) {
                if (ENABLE_FABRIC_LOGS) {
                    printMountItem(dispatchCommandMountItem, "dispatchMountItems: Executing viewCommandMountItem");
                }
                try {
                    dispatchCommandMountItem.execute(this.mMountingManager);
                } catch (RetryableMountingLayerException e) {
                    if (dispatchCommandMountItem.getRetries() == 0) {
                        dispatchCommandMountItem.incrementRetries();
                        dispatchCommandMountItem(dispatchCommandMountItem);
                    } else {
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Caught exception executing ViewCommand: ");
                        outline1072.append(dispatchCommandMountItem.toString());
                        ReactSoftException.logSoftException(TAG, new ReactNoCrashSoftException(outline1072.toString(), e));
                    }
                } catch (Throwable th) {
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Caught exception executing ViewCommand: ");
                    outline1073.append(dispatchCommandMountItem.toString());
                    ReactSoftException.logSoftException(TAG, new RuntimeException(outline1073.toString(), th));
                }
            }
            Systrace.endSection(0L);
        }
        ArrayDeque<PreAllocateViewMountItem> andResetPreMountItems = getAndResetPreMountItems();
        if (andResetPreMountItems != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("FabricUIManager::mountViews preMountItems to execute: ");
            outline1074.append(andResetPreMountItems.size());
            Systrace.beginSection(0L, outline1074.toString());
            while (!andResetPreMountItems.isEmpty()) {
                PreAllocateViewMountItem pollFirst = andResetPreMountItems.pollFirst();
                if (surfaceActiveForExecution(pollFirst.getRootTag(), "dispatchMountItems PreAllocateViewMountItem")) {
                    pollFirst.execute(this.mMountingManager);
                }
            }
            Systrace.endSection(0L);
        }
        if (andResetMountItems != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("FabricUIManager::mountViews mountItems to execute: ");
            outline1075.append(andResetMountItems.size());
            Systrace.beginSection(0L, outline1075.toString());
            long uptimeMillis = SystemClock.uptimeMillis();
            for (MountItem mountItem : andResetMountItems) {
                if (ENABLE_FABRIC_LOGS) {
                    printMountItem(mountItem, "dispatchMountItems: Executing mountItem");
                }
                try {
                    if (!(mountItem instanceof BatchMountItem) || surfaceActiveForExecution(((BatchMountItem) mountItem).getRootTag(), "dispatchMountItems BatchMountItem")) {
                        mountItem.execute(this.mMountingManager);
                    }
                } finally {
                    if (isIgnorable) {
                    }
                }
            }
            this.mBatchedExecutionTime = (SystemClock.uptimeMillis() - uptimeMillis) + this.mBatchedExecutionTime;
        }
        Systrace.endSection(0L);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0046, code lost:
        if (surfaceActiveForExecution(r3.getRootTag(), "dispatchPreMountItems") == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0048, code lost:
        r3.execute(r10.mMountingManager);
     */
    @com.facebook.infer.annotation.ThreadConfined(com.facebook.infer.annotation.ThreadConfined.UI)
    @androidx.annotation.UiThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void dispatchPreMountItems(long r11) {
        /*
            r10 = this;
            r0 = 0
            java.lang.String r2 = "FabricUIManager::premountViews"
            com.facebook.systrace.Systrace.beginSection(r0, r2)
            r2 = 1
            r10.mInDispatch = r2
        La:
            r2 = 16
            r4 = -1
            r5 = 0
            long r6 = java.lang.System.nanoTime()     // Catch: java.lang.Throwable -> L51
            long r6 = r6 - r11
            r8 = 1000000(0xf4240, double:4.940656E-318)
            long r6 = r6 / r8
            long r2 = r2 - r6
            r6 = 8
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 >= 0) goto L1f
            goto L2b
        L1f:
            java.lang.Object r2 = r10.mPreMountItemsLock     // Catch: java.lang.Throwable -> L51
            monitor-enter(r2)     // Catch: java.lang.Throwable -> L51
            java.util.ArrayDeque<com.facebook.react.fabric.mounting.mountitems.PreAllocateViewMountItem> r3 = r10.mPreMountItems     // Catch: java.lang.Throwable -> L4e
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L4e
            if (r3 == 0) goto L33
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4e
        L2b:
            r10.mInDispatch = r5
            r10.mLastExecutedMountItemSurfaceId = r4
            com.facebook.systrace.Systrace.endSection(r0)
            return
        L33:
            java.util.ArrayDeque<com.facebook.react.fabric.mounting.mountitems.PreAllocateViewMountItem> r3 = r10.mPreMountItems     // Catch: java.lang.Throwable -> L4e
            java.lang.Object r3 = r3.pollFirst()     // Catch: java.lang.Throwable -> L4e
            com.facebook.react.fabric.mounting.mountitems.PreAllocateViewMountItem r3 = (com.facebook.react.fabric.mounting.mountitems.PreAllocateViewMountItem) r3     // Catch: java.lang.Throwable -> L4e
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4e
            int r2 = r3.getRootTag()     // Catch: java.lang.Throwable -> L51
            java.lang.String r6 = "dispatchPreMountItems"
            boolean r2 = r10.surfaceActiveForExecution(r2, r6)     // Catch: java.lang.Throwable -> L51
            if (r2 == 0) goto La
            com.facebook.react.fabric.mounting.MountingManager r2 = r10.mMountingManager     // Catch: java.lang.Throwable -> L51
            r3.execute(r2)     // Catch: java.lang.Throwable -> L51
            goto La
        L4e:
            r11 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4e
            throw r11     // Catch: java.lang.Throwable -> L51
        L51:
            r11 = move-exception
            r10.mInDispatch = r5
            r10.mLastExecutedMountItemSurfaceId = r4
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.fabric.FabricUIManager.dispatchPreMountItems(long):void");
    }

    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    private List<MountItem> getAndResetMountItems() {
        synchronized (this.mMountItemsLock) {
            List<MountItem> list = this.mMountItems;
            if (list.isEmpty()) {
                return null;
            }
            this.mMountItems = new ArrayList();
            return list;
        }
    }

    private ArrayDeque<PreAllocateViewMountItem> getAndResetPreMountItems() {
        synchronized (this.mPreMountItemsLock) {
            ArrayDeque<PreAllocateViewMountItem> arrayDeque = this.mPreMountItems;
            if (arrayDeque.isEmpty()) {
                return null;
            }
            this.mPreMountItems = new ArrayDeque<>(250);
            return arrayDeque;
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    private List<DispatchCommandMountItem> getAndResetViewCommandMountItems() {
        synchronized (this.mViewCommandMountItemsLock) {
            List<DispatchCommandMountItem> list = this.mViewCommandMountItems;
            if (list.isEmpty()) {
                return null;
            }
            this.mViewCommandMountItems = new ArrayList();
            return list;
        }
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private MountItem insertMountItem(int i, int i2, int i3) {
        return new InsertMountItem(i, i2, i3);
    }

    private long measure(int i, String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, float f2, float f3, float f4) {
        return measure(i, str, readableMap, readableMap2, readableMap3, f, f2, f3, f4, null);
    }

    private NativeArray measureLines(ReadableMap readableMap, ReadableMap readableMap2, float f, float f2) {
        return (NativeArray) TextLayoutManager.measureLines(this.mReactApplicationContext, readableMap, readableMap2, PixelUtil.toPixelFromDIP(f));
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private void preallocateView(int i, int i2, String str, @Nullable ReadableMap readableMap, @Nullable Object obj, boolean z) {
        ThemedReactContext themedReactContext = this.mReactContextForRootTag.get(Integer.valueOf(i));
        String fabricComponentName = FabricComponents.getFabricComponentName(str);
        synchronized (this.mPreMountItemsLock) {
            this.mPreMountItems.add(new PreAllocateViewMountItem(themedReactContext, i, i2, fabricComponentName, readableMap, (StateWrapper) obj, z));
        }
    }

    private static void printMountItem(MountItem mountItem, String str) {
        String[] split;
        for (String str2 : mountItem.toString().split("\n")) {
            FLog.e(TAG, str + RealTimeTextConstants.COLON_SPACE + str2);
        }
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private MountItem removeDeleteMultiMountItem(int[] iArr) {
        return new RemoveDeleteMultiMountItem(iArr);
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private void scheduleMountItem(@Nullable MountItem mountItem, int i, long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        boolean z = mountItem instanceof BatchMountItem;
        boolean z2 = mountItem instanceof IntBufferBatchMountItem;
        boolean z3 = false;
        boolean z4 = z || z2;
        if ((z && ((BatchMountItem) mountItem).shouldSchedule()) || ((z2 && ((IntBufferBatchMountItem) mountItem).shouldSchedule()) || (!z4 && mountItem != null))) {
            z3 = true;
        }
        Iterator<UIManagerListener> it2 = this.mListeners.iterator();
        while (it2.hasNext()) {
            it2.next().didScheduleMountItems(this);
        }
        if (z4) {
            this.mCommitStartTime = j;
            this.mLayoutTime = j5 - j4;
            this.mFinishTransactionCPPTime = j7 - j6;
            this.mFinishTransactionTime = SystemClock.uptimeMillis() - j6;
            this.mDispatchViewUpdatesTime = SystemClock.uptimeMillis();
        }
        if (z3 && mountItem != null) {
            synchronized (this.mMountItemsLock) {
                this.mMountItems.add(mountItem);
            }
            if (UiThreadUtil.isOnUiThread()) {
                tryDispatchMountItems();
            }
        }
        if (z4) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_COMMIT_START, null, i, j);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_FINISH_TRANSACTION_START, null, i, j6);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_FINISH_TRANSACTION_END, null, i, j7);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_DIFF_START, null, i, j2);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_DIFF_END, null, i, j3);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_LAYOUT_START, null, i, j4);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_LAYOUT_END, null, i, j5);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_COMMIT_END, null, i);
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    private boolean surfaceActiveForExecution(int i, String str) {
        if (this.mLastExecutedMountItemSurfaceId != i) {
            this.mLastExecutedMountItemSurfaceId = i;
            this.mLastExecutedMountItemSurfaceIdActive = this.mReactContextForRootTag.get(Integer.valueOf(i)) != null;
            if (!this.mLastExecutedMountItemSurfaceIdActive) {
                ReactSoftException.logSoftException(TAG, new ReactNoCrashSoftException("dispatchMountItems: skipping " + str + ", because surface not available: " + i));
            }
        }
        return this.mLastExecutedMountItemSurfaceIdActive;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    public void tryDispatchMountItems() {
        if (this.mInDispatch) {
            return;
        }
        try {
            boolean dispatchMountItems = dispatchMountItems();
            this.mInDispatch = false;
            Iterator<UIManagerListener> it2 = this.mListeners.iterator();
            while (it2.hasNext()) {
                it2.next().didDispatchMountItems(this);
            }
            int i = this.mReDispatchCounter;
            if (i < 10 && dispatchMountItems) {
                if (i > 2) {
                    ReactSoftException.logSoftException(TAG, new ReactNoCrashSoftException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Re-dispatched "), this.mReDispatchCounter, " times. This indicates setState (?) is likely being called too many times during mounting.")));
                }
                this.mReDispatchCounter++;
                tryDispatchMountItems();
            }
            this.mReDispatchCounter = 0;
            this.mLastExecutedMountItemSurfaceId = -1;
        } finally {
        }
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private MountItem updateEventEmitterMountItem(int i, Object obj) {
        return new UpdateEventEmitterMountItem(i, (EventEmitterWrapper) obj);
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private MountItem updateLayoutMountItem(int i, int i2, int i3, int i4, int i5, int i6) {
        return new UpdateLayoutMountItem(i, i2, i3, i4, i5, i6);
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private MountItem updatePaddingMountItem(int i, int i2, int i3, int i4, int i5) {
        return new UpdatePaddingMountItem(i, i2, i3, i4, i5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    public MountItem updatePropsMountItem(int i, ReadableMap readableMap) {
        return new UpdatePropsMountItem(i, readableMap);
    }

    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    private MountItem updateStateMountItem(int i, @Nullable Object obj) {
        return new UpdateStateMountItem(i, (StateWrapper) obj);
    }

    @Override // com.facebook.react.bridge.UIManager
    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    public <T extends View> int addRootView(T t, WritableMap writableMap, @Nullable String str) {
        int nextRootViewTag = ReactRootViewTagGenerator.getNextRootViewTag();
        ReactRoot reactRoot = (ReactRoot) t;
        ThemedReactContext themedReactContext = new ThemedReactContext(this.mReactApplicationContext, t.getContext(), reactRoot.getSurfaceID());
        this.mMountingManager.addRootView(nextRootViewTag, t);
        String jSModuleName = reactRoot.getJSModuleName();
        this.mReactContextForRootTag.put(Integer.valueOf(nextRootViewTag), themedReactContext);
        if (ENABLE_FABRIC_LOGS) {
            FLog.d(TAG, "Starting surface for module: %s and reactTag: %d", jSModuleName, Integer.valueOf(nextRootViewTag));
        }
        this.mBinding.startSurface(nextRootViewTag, jSModuleName, (NativeMap) writableMap);
        if (str != null) {
            this.mBinding.renderTemplateToSurface(nextRootViewTag, str);
        }
        return nextRootViewTag;
    }

    @Override // com.facebook.react.bridge.UIManager
    public void addUIManagerEventListener(UIManagerListener uIManagerListener) {
        this.mListeners.add(uIManagerListener);
    }

    public void clearJSResponder() {
        synchronized (this.mMountItemsLock) {
            this.mMountItems.add(new MountItem() { // from class: com.facebook.react.fabric.FabricUIManager.4
                @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
                public void execute(MountingManager mountingManager) {
                    mountingManager.clearJSResponder();
                }
            });
        }
    }

    @Override // com.facebook.react.bridge.UIManager
    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    @Deprecated
    public void dispatchCommand(int i, int i2, @Nullable ReadableArray readableArray) {
        dispatchCommandMountItem(new DispatchIntCommandMountItem(i, i2, readableArray));
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public Map<String, Long> getPerformanceCounters() {
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(this.mCommitStartTime));
        hashMap.put("LayoutTime", Long.valueOf(this.mLayoutTime));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.mDispatchViewUpdatesTime));
        hashMap.put("RunStartTime", Long.valueOf(this.mRunStartTime));
        hashMap.put("BatchedExecutionTime", Long.valueOf(this.mBatchedExecutionTime));
        hashMap.put("FinishFabricTransactionTime", Long.valueOf(this.mFinishTransactionTime));
        hashMap.put("FinishFabricTransactionCPPTime", Long.valueOf(this.mFinishTransactionCPPTime));
        return hashMap;
    }

    public boolean getThemeData(int i, float[] fArr) {
        ThemedReactContext themedReactContext = this.mReactContextForRootTag.get(Integer.valueOf(i));
        if (themedReactContext == null) {
            ReactSoftException.logSoftException(TAG, new ReactNoCrashSoftException(GeneratedOutlineSupport1.outline49("Unable to find ThemedReactContext associated to surfaceID: ", i)));
            return false;
        }
        float[] defaultTextInputPadding = UIManagerHelper.getDefaultTextInputPadding(themedReactContext);
        fArr[0] = defaultTextInputPadding[0];
        fArr[1] = defaultTextInputPadding[1];
        fArr[2] = defaultTextInputPadding[2];
        fArr[3] = defaultTextInputPadding[3];
        return true;
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void initialize() {
        this.mEventDispatcher.registerEventEmitter(2, new FabricEventEmitter(this));
        this.mEventDispatcher.addBatchEventDispatchedListener(this.mEventBeatManager);
    }

    @AnyThread
    public void onAllAnimationsComplete() {
        this.mDriveCxxAnimations = false;
    }

    @AnyThread
    public void onAnimationStarted() {
        this.mDriveCxxAnimations = true;
    }

    @Override // com.facebook.react.bridge.JSIModule
    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    public void onCatalystInstanceDestroy() {
        FLog.i(TAG, "FabricUIManager.onCatalystInstanceDestroy");
        if (this.mDestroyed) {
            ReactSoftException.logSoftException(TAG, new IllegalStateException("Cannot double-destroy FabricUIManager"));
            return;
        }
        this.mDestroyed = true;
        this.mDispatchUIFrameCallback.stop();
        this.mEventDispatcher.removeBatchEventDispatchedListener(this.mEventBeatManager);
        this.mEventDispatcher.unregisterEventEmitter(2);
        this.mReactApplicationContext.removeLifecycleEventListener(this);
        onHostPause();
        this.mDispatchUIFrameCallback.stop();
        this.mBinding.unregister();
        this.mBinding = null;
        ViewManagerPropertyUpdater.clear();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        ReactChoreographer.getInstance().removeFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
    }

    public void onRequestEventBeat() {
        this.mEventDispatcher.dispatchAllEvents();
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public void profileNextBatch() {
    }

    @Override // com.facebook.react.bridge.UIManager
    public void receiveEvent(int i, String str, @Nullable WritableMap writableMap) {
        EventEmitterWrapper eventEmitter = this.mMountingManager.getEventEmitter(i);
        if (eventEmitter == null) {
            FLog.d(TAG, "Unable to invoke event: " + str + " for reactTag: " + i);
            return;
        }
        eventEmitter.invoke(str, writableMap);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void removeUIManagerEventListener(UIManagerListener uIManagerListener) {
        this.mListeners.remove(uIManagerListener);
    }

    @Override // com.facebook.react.bridge.UIManager
    @Nullable
    @Deprecated
    public String resolveCustomDirectEventName(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return str.substring(0, 3).equals(ViewProps.TOP) ? GeneratedOutlineSupport1.outline55(str, 3, GeneratedOutlineSupport1.outline107("on")) : str;
    }

    @Override // com.facebook.react.bridge.UIManager
    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    public void sendAccessibilityEvent(int i, int i2) {
        synchronized (this.mMountItemsLock) {
            this.mMountItems.add(new SendAccessibilityEvent(i, i2));
        }
    }

    public void setBinding(Binding binding) {
        this.mBinding = binding;
    }

    public void setJSResponder(final int i, final int i2, final boolean z) {
        synchronized (this.mMountItemsLock) {
            this.mMountItems.add(new MountItem() { // from class: com.facebook.react.fabric.FabricUIManager.3
                @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
                public void execute(MountingManager mountingManager) {
                    mountingManager.setJSResponder(i, i2, z);
                }
            });
        }
    }

    @Override // com.facebook.react.bridge.UIManager
    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    public <T extends View> int startSurface(T t, String str, WritableMap writableMap, int i, int i2) {
        int nextRootViewTag = ReactRootViewTagGenerator.getNextRootViewTag();
        Context context = t.getContext();
        ThemedReactContext themedReactContext = new ThemedReactContext(this.mReactApplicationContext, context, str);
        if (ENABLE_FABRIC_LOGS) {
            FLog.d(TAG, "Starting surface for module: %s and reactTag: %d", str, Integer.valueOf(nextRootViewTag));
        }
        this.mMountingManager.addRootView(nextRootViewTag, t);
        this.mReactContextForRootTag.put(Integer.valueOf(nextRootViewTag), themedReactContext);
        Point viewportOffset = ReactRootView.getViewportOffset(t);
        this.mBinding.startSurfaceWithConstraints(nextRootViewTag, str, (NativeMap) writableMap, LayoutMetricsConversions.getMinSize(i), LayoutMetricsConversions.getMaxSize(i), LayoutMetricsConversions.getMinSize(i2), LayoutMetricsConversions.getMaxSize(i2), viewportOffset.x, viewportOffset.y, I18nUtil.getInstance().isRTL(context), I18nUtil.getInstance().doLeftAndRightSwapInRTL(context));
        return nextRootViewTag;
    }

    @Override // com.facebook.react.bridge.UIManager
    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    public void stopSurface(final int i) {
        this.mReactContextForRootTag.remove(Integer.valueOf(i));
        this.mBinding.stopSurface(i);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.fabric.FabricUIManager.1
            @Override // java.lang.Runnable
            public void run() {
                FabricUIManager.this.mMountingManager.deleteRootView(i);
            }
        });
    }

    @Override // com.facebook.react.bridge.UIManager
    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    public void synchronouslyUpdateViewOnUIThread(final int i, @NonNull final ReadableMap readableMap) {
        UiThreadUtil.assertOnUiThread();
        int i2 = this.mCurrentSynchronousCommitNumber;
        this.mCurrentSynchronousCommitNumber = i2 + 1;
        if (!ReactFeatureFlags.enableDrawMutationFix) {
            tryDispatchMountItems();
        }
        MountItem mountItem = new MountItem() { // from class: com.facebook.react.fabric.FabricUIManager.2
            @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
            public void execute(@NonNull MountingManager mountingManager) {
                try {
                    FabricUIManager.this.updatePropsMountItem(i, readableMap).execute(mountingManager);
                } catch (Exception e) {
                    ReactSoftException.logSoftException(FabricUIManager.TAG, new ReactNoCrashSoftException("Caught exception in synchronouslyUpdateViewOnUIThread", e));
                }
            }
        };
        if (!this.mMountingManager.getViewExists(i)) {
            synchronized (this.mMountItemsLock) {
                this.mMountItems.add(mountItem);
            }
            return;
        }
        ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_START, null, i2);
        if (ENABLE_FABRIC_LOGS) {
            FLog.d(TAG, "SynchronouslyUpdateViewOnUIThread for tag %d: %s", Integer.valueOf(i), "<hidden>");
        }
        mountItem.execute(this.mMountingManager);
        ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_END, null, i2);
    }

    @Override // com.facebook.react.bridge.UIManager
    @ThreadConfined(ThreadConfined.UI)
    @UiThread
    public void updateRootLayoutSpecs(int i, int i2, int i3, int i4, int i5) {
        boolean z;
        boolean z2;
        if (ENABLE_FABRIC_LOGS) {
            FLog.d(TAG, "Updating Root Layout Specs");
        }
        ThemedReactContext themedReactContext = this.mReactContextForRootTag.get(Integer.valueOf(i));
        if (themedReactContext != null) {
            boolean isRTL = I18nUtil.getInstance().isRTL(themedReactContext);
            z2 = I18nUtil.getInstance().doLeftAndRightSwapInRTL(themedReactContext);
            z = isRTL;
        } else {
            z = false;
            z2 = false;
        }
        this.mBinding.setConstraints(i, LayoutMetricsConversions.getMinSize(i2), LayoutMetricsConversions.getMaxSize(i2), LayoutMetricsConversions.getMinSize(i3), LayoutMetricsConversions.getMaxSize(i3), i4, i5, z, z2);
    }

    private long measure(int i, String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, float f2, float f3, float f4, @Nullable float[] fArr) {
        ThemedReactContext themedReactContext;
        if (i < 0) {
            themedReactContext = this.mReactApplicationContext;
        } else {
            themedReactContext = this.mReactContextForRootTag.get(Integer.valueOf(i));
        }
        Context context = themedReactContext;
        if (context == null) {
            return 0L;
        }
        return this.mMountingManager.measure(context, str, readableMap, readableMap2, readableMap3, LayoutMetricsConversions.getYogaSize(f, f2), LayoutMetricsConversions.getYogaMeasureMode(f, f2), LayoutMetricsConversions.getYogaSize(f3, f4), LayoutMetricsConversions.getYogaMeasureMode(f3, f4), fArr);
    }

    @Override // com.facebook.react.bridge.UIManager
    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    public void dispatchCommand(int i, String str, @Nullable ReadableArray readableArray) {
        dispatchCommandMountItem(new DispatchStringCommandMountItem(i, str, readableArray));
    }

    @Override // com.facebook.react.bridge.UIManager
    @NonNull
    /* renamed from: getEventDispatcher */
    public EventDispatcher mo6949getEventDispatcher() {
        return this.mEventDispatcher;
    }
}
