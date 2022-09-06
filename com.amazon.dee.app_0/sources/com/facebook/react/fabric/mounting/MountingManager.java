package com.facebook.react.fabric.mounting;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.ThreadConfined;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.yoga.YogaMeasureMode;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class MountingManager {
    private static final boolean SHOW_CHANGED_VIEW_HIERARCHIES = false;
    public static final String TAG = "MountingManager";
    @NonNull
    private final JSResponderHandler mJSResponderHandler = new JSResponderHandler();
    @NonNull
    private final RootViewManager mRootViewManager = new RootViewManager();
    @NonNull
    private final ConcurrentHashMap<Integer, ViewState> mTagToViewState = new ConcurrentHashMap<>();
    @NonNull
    private final ViewManagerRegistry mViewManagerRegistry;

    public MountingManager(@NonNull ViewManagerRegistry viewManagerRegistry) {
        this.mViewManagerRegistry = viewManagerRegistry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public void dropChildren(int i, @NonNull ViewGroup viewGroup, @NonNull ViewGroupManager<ViewGroup> viewGroupManager) {
        for (int childCount = viewGroupManager.getChildCount(viewGroup) - 1; childCount >= 0; childCount--) {
            View childAt = viewGroupManager.getChildAt(viewGroup, childCount);
            if (getNullableViewState(childAt.getId()) != null) {
                ViewParent parent = childAt.getParent();
                if (parent != null && parent.equals(viewGroup)) {
                    dropView(childAt, true);
                } else {
                    int i2 = -1;
                    if (parent != null && (parent instanceof ViewGroup)) {
                        i2 = ((ViewGroup) parent).getId();
                    }
                    String str = TAG;
                    StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Recursively deleting children of [", i, "] but parent of child [");
                    outline109.append(childAt.getId());
                    outline109.append("] is [");
                    outline109.append(i2);
                    outline109.append("]");
                    FLog.e(str, outline109.toString());
                }
            }
            viewGroupManager.removeViewAt(viewGroup, childCount);
        }
    }

    @UiThread
    private void dropView(@NonNull View view, boolean z) {
        UiThreadUtil.assertOnUiThread();
        final int id = view.getId();
        ViewState viewState = getViewState(id);
        ViewManager viewManager = viewState.mViewManager;
        if (!viewState.mIsRoot && viewManager != null) {
            viewManager.onDropViewInstance(view);
        }
        if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
            final ViewGroup viewGroup = (ViewGroup) view;
            final ViewGroupManager<ViewGroup> viewGroupManager = getViewGroupManager(viewState);
            if (z) {
                dropChildren(id, viewGroup, viewGroupManager);
            } else {
                UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.fabric.mounting.MountingManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MountingManager.this.dropChildren(id, viewGroup, viewGroupManager);
                    }
                });
            }
        }
        this.mTagToViewState.remove(Integer.valueOf(id));
    }

    @Nullable
    private ViewState getNullableViewState(int i) {
        return this.mTagToViewState.get(Integer.valueOf(i));
    }

    @NonNull
    private static ViewGroupManager<ViewGroup> getViewGroupManager(@NonNull ViewState viewState) {
        ViewManager viewManager = viewState.mViewManager;
        if (viewManager != null) {
            return (ViewGroupManager) viewManager;
        }
        throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
    }

    @NonNull
    private ViewState getViewState(int i) {
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(i));
        if (viewState != null) {
            return viewState;
        }
        throw new RetryableMountingLayerException(GeneratedOutlineSupport1.outline49("Unable to find viewState view for tag ", i));
    }

    private static void logViewHierarchy(ViewGroup viewGroup, boolean z) {
        int id = viewGroup.getId();
        String str = TAG;
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("  <ViewGroup tag=", id, " class=");
        outline109.append(viewGroup.getClass().toString());
        outline109.append(Config.Compare.GREATER_THAN);
        FLog.e(str, outline109.toString());
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            String str2 = TAG;
            StringBuilder outline1092 = GeneratedOutlineSupport1.outline109("     <View idx=", i, " tag=");
            outline1092.append(viewGroup.getChildAt(i).getId());
            outline1092.append(" class=");
            outline1092.append(viewGroup.getChildAt(i).getClass().toString());
            outline1092.append(Config.Compare.GREATER_THAN);
            FLog.e(str2, outline1092.toString());
        }
        String str3 = TAG;
        FLog.e(str3, "  </ViewGroup tag=" + id + Config.Compare.GREATER_THAN);
        if (z) {
            FLog.e(TAG, "Displaying Ancestors:");
            for (ViewParent parent = viewGroup.getParent(); parent != null; parent = parent.getParent()) {
                ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                int id2 = viewGroup2 == null ? -1 : viewGroup2.getId();
                String str4 = TAG;
                StringBuilder outline1093 = GeneratedOutlineSupport1.outline109("<ViewParent tag=", id2, " class=");
                outline1093.append(parent.getClass().toString());
                outline1093.append(Config.Compare.GREATER_THAN);
                FLog.e(str4, outline1093.toString());
            }
        }
    }

    @ThreadConfined(ThreadConfined.UI)
    public void addRootView(int i, @NonNull View view) {
        if (view.getId() == -1) {
            this.mTagToViewState.put(Integer.valueOf(i), new ViewState(i, view, this.mRootViewManager, true));
            view.setId(i);
            return;
        }
        throw new IllegalViewOperationException("Trying to add a root view with an explicit id already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
    }

    @UiThread
    public void addViewAt(int i, int i2, int i3) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i);
        View view = viewState.mView;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            ViewState viewState2 = getViewState(i2);
            View view2 = viewState2.mView;
            if (view2 != null) {
                try {
                    getViewGroupManager(viewState).addView(viewGroup, view2, i3);
                    return;
                } catch (IllegalStateException e) {
                    StringBuilder outline110 = GeneratedOutlineSupport1.outline110("addViewAt: failed to insert view [", i2, "] into parent [", i, "] at index ");
                    outline110.append(i3);
                    throw new IllegalStateException(outline110.toString(), e);
                }
            }
            throw new IllegalStateException("Unable to find view for viewState " + viewState2 + " and tag " + i2);
        }
        StringBuilder outline1102 = GeneratedOutlineSupport1.outline110("Unable to add a view into a view that is not a ViewGroup. ParentTag: ", i, " - Tag: ", i2, " - Index: ");
        outline1102.append(i3);
        String sb = outline1102.toString();
        FLog.e(TAG, sb);
        throw new IllegalStateException(sb);
    }

    @UiThread
    public void clearJSResponder() {
        this.mJSResponderHandler.clearJSResponder();
    }

    @UiThread
    public void createView(@NonNull ThemedReactContext themedReactContext, @NonNull String str, int i, @Nullable ReadableMap readableMap, @Nullable StateWrapper stateWrapper, boolean z) {
        View view;
        ViewManager viewManager;
        if (getNullableViewState(i) != null) {
            return;
        }
        ReadableNativeMap readableNativeMap = null;
        ReactStylesDiffMap reactStylesDiffMap = readableMap != null ? new ReactStylesDiffMap(readableMap) : null;
        if (z) {
            viewManager = this.mViewManagerRegistry.get(str);
            view = viewManager.createView(themedReactContext, reactStylesDiffMap, stateWrapper, this.mJSResponderHandler);
            view.setId(i);
        } else {
            view = null;
            viewManager = null;
        }
        ViewState viewState = new ViewState(i, view, viewManager);
        viewState.mCurrentProps = reactStylesDiffMap;
        if (stateWrapper != null) {
            readableNativeMap = stateWrapper.getState();
        }
        viewState.mCurrentState = readableNativeMap;
        this.mTagToViewState.put(Integer.valueOf(i), viewState);
    }

    @UiThread
    public void deleteRootView(int i) {
        if (this.mTagToViewState.containsKey(Integer.valueOf(i))) {
            dropView(this.mTagToViewState.get(Integer.valueOf(i)).mView, true);
        }
    }

    @UiThread
    public void deleteView(int i) {
        UiThreadUtil.assertOnUiThread();
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState == null) {
            ReactSoftException.logSoftException(TAG, new IllegalStateException(GeneratedOutlineSupport1.outline52("Unable to find viewState for tag: ", i, " for deleteView")));
            return;
        }
        this.mTagToViewState.remove(Integer.valueOf(i));
        ViewManager viewManager = nullableViewState.mViewManager;
        if (nullableViewState.mIsRoot || viewManager == null) {
            return;
        }
        viewManager.onDropViewInstance(nullableViewState.mView);
    }

    @Nullable
    @AnyThread
    @ThreadConfined(ThreadConfined.ANY)
    public EventEmitterWrapper getEventEmitter(int i) {
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState == null) {
            return null;
        }
        return nullableViewState.mEventEmitter;
    }

    public boolean getViewExists(int i) {
        return this.mTagToViewState.get(Integer.valueOf(i)) != null;
    }

    @AnyThread
    public long measure(@NonNull Context context, @NonNull String str, @NonNull ReadableMap readableMap, @NonNull ReadableMap readableMap2, @NonNull ReadableMap readableMap3, float f, @NonNull YogaMeasureMode yogaMeasureMode, float f2, @NonNull YogaMeasureMode yogaMeasureMode2, @Nullable float[] fArr) {
        return this.mViewManagerRegistry.get(str).measure(context, readableMap, readableMap2, readableMap3, f, yogaMeasureMode, f2, yogaMeasureMode2, fArr);
    }

    @UiThread
    public void preallocateView(@NonNull ThemedReactContext themedReactContext, String str, int i, @Nullable ReadableMap readableMap, @Nullable StateWrapper stateWrapper, boolean z) {
        if (getNullableViewState(i) == null) {
            createView(themedReactContext, str, i, readableMap, stateWrapper, z);
            return;
        }
        throw new IllegalStateException("View for component " + str + " with tag " + i + " already exists.");
    }

    @Deprecated
    public void receiveCommand(int i, int i2, @Nullable ReadableArray readableArray) {
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState != null) {
            ViewManager viewManager = nullableViewState.mViewManager;
            if (viewManager != null) {
                View view = nullableViewState.mView;
                if (view != null) {
                    viewManager.receiveCommand((ViewManager) view, i2, readableArray);
                    return;
                }
                throw new RetryableMountingLayerException(GeneratedOutlineSupport1.outline49("Unable to find viewState view for tag ", i));
            }
            throw new RetryableMountingLayerException(GeneratedOutlineSupport1.outline49("Unable to find viewManager for tag ", i));
        }
        throw new RetryableMountingLayerException(GeneratedOutlineSupport1.outline53("Unable to find viewState for tag: ", i, " for commandId: ", i2));
    }

    @UiThread
    public void removeViewAt(int i, int i2, int i3) {
        UiThreadUtil.assertOnUiThread();
        ViewState nullableViewState = getNullableViewState(i2);
        if (nullableViewState == null) {
            ReactSoftException.logSoftException(TAG, new IllegalStateException(GeneratedOutlineSupport1.outline52("Unable to find viewState for tag: ", i2, " for removeViewAt")));
            return;
        }
        ViewGroup viewGroup = (ViewGroup) nullableViewState.mView;
        if (viewGroup != null) {
            ViewGroupManager<ViewGroup> viewGroupManager = getViewGroupManager(nullableViewState);
            View childAt = viewGroupManager.getChildAt(viewGroup, i3);
            int id = childAt != null ? childAt.getId() : -1;
            if (id != i) {
                int childCount = viewGroup.getChildCount();
                int i4 = 0;
                while (true) {
                    if (i4 >= childCount) {
                        i4 = -1;
                        break;
                    } else if (viewGroup.getChildAt(i4).getId() == i) {
                        break;
                    } else {
                        i4++;
                    }
                }
                if (i4 == -1) {
                    String str = TAG;
                    StringBuilder outline110 = GeneratedOutlineSupport1.outline110("removeViewAt: [", i, "] -> [", i2, "] @");
                    outline110.append(i3);
                    outline110.append(": view already removed from parent! Children in parent: ");
                    outline110.append(childCount);
                    FLog.e(str, outline110.toString());
                    return;
                }
                logViewHierarchy(viewGroup, true);
                String str2 = TAG;
                StringBuilder outline1102 = GeneratedOutlineSupport1.outline110("Tried to remove view [", i, "] of parent [", i2, "] at index ");
                GeneratedOutlineSupport1.outline175(outline1102, i3, ", but got view tag ", id, " - actual index of view: ");
                outline1102.append(i4);
                ReactSoftException.logSoftException(str2, new IllegalStateException(outline1102.toString()));
                i3 = i4;
            }
            try {
                viewGroupManager.removeViewAt(viewGroup, i3);
                return;
            } catch (RuntimeException e) {
                int childCount2 = viewGroupManager.getChildCount(viewGroup);
                logViewHierarchy(viewGroup, true);
                StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Cannot remove child at index ", i3, " from parent ViewGroup [");
                outline109.append(viewGroup.getId());
                outline109.append("], only ");
                outline109.append(childCount2);
                outline109.append(" children in parent. Warning: childCount may be incorrect!");
                throw new IllegalStateException(outline109.toString(), e);
            }
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unable to find view for tag ", i2));
    }

    public void sendAccessibilityEvent(int i, int i2) {
        ViewState viewState = getViewState(i);
        if (viewState.mViewManager != null) {
            View view = viewState.mView;
            if (view != null) {
                view.sendAccessibilityEvent(i2);
                return;
            }
            throw new RetryableMountingLayerException(GeneratedOutlineSupport1.outline49("Unable to find viewState view for tag ", i));
        }
        throw new RetryableMountingLayerException(GeneratedOutlineSupport1.outline49("Unable to find viewState manager for tag ", i));
    }

    @UiThread
    public synchronized void setJSResponder(int i, int i2, boolean z) {
        if (!z) {
            this.mJSResponderHandler.setJSResponder(i2, null);
            return;
        }
        ViewState viewState = getViewState(i);
        View view = viewState.mView;
        if (i2 != i && (view instanceof ViewParent)) {
            this.mJSResponderHandler.setJSResponder(i2, (ViewParent) view);
        } else if (view == null) {
            SoftAssertions.assertUnreachable("Cannot find view for tag " + i + ".");
        } else {
            if (viewState.mIsRoot) {
                SoftAssertions.assertUnreachable("Cannot block native responder on " + i + " that is a root view");
            }
            this.mJSResponderHandler.setJSResponder(i2, view.getParent());
        }
    }

    @UiThread
    public void updateEventEmitter(int i, @NonNull EventEmitterWrapper eventEmitterWrapper) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(i));
        if (viewState == null) {
            viewState = new ViewState(i, (View) null, (ViewManager) null);
            this.mTagToViewState.put(Integer.valueOf(i), viewState);
        }
        viewState.mEventEmitter = eventEmitterWrapper;
    }

    @UiThread
    public void updateLayout(int i, int i2, int i3, int i4, int i5) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i);
        if (viewState.mIsRoot) {
            return;
        }
        View view = viewState.mView;
        if (view != null) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i5, 1073741824));
            ViewParent parent = view.getParent();
            if (parent instanceof RootView) {
                parent.requestLayout();
            }
            view.layout(i2, i3, i4 + i2, i5 + i3);
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unable to find View for tag: ", i));
    }

    @UiThread
    public void updatePadding(int i, int i2, int i3, int i4, int i5) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i);
        if (viewState.mIsRoot) {
            return;
        }
        View view = viewState.mView;
        if (view != null) {
            ViewManager viewManager = viewState.mViewManager;
            if (viewManager != null) {
                viewManager.setPadding(view, i2, i3, i4, i5);
                return;
            }
            throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unable to find View for tag: ", i));
    }

    @UiThread
    public void updateProps(int i, @Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i);
        viewState.mCurrentProps = new ReactStylesDiffMap(readableMap);
        View view = viewState.mView;
        if (view != null) {
            ((ViewManager) Assertions.assertNotNull(viewState.mViewManager)).updateProperties(view, viewState.mCurrentProps);
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unable to find view for tag ", i));
    }

    @UiThread
    public void updateState(int i, @Nullable StateWrapper stateWrapper) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i);
        viewState.mCurrentState = stateWrapper == null ? null : stateWrapper.getState();
        ViewManager viewManager = viewState.mViewManager;
        if (viewManager != null) {
            Object updateState = viewManager.updateState(viewState.mView, viewState.mCurrentProps, stateWrapper);
            if (updateState == null) {
                return;
            }
            viewManager.updateExtraData(viewState.mView, updateState);
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unable to find ViewManager for tag: ", i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ViewState {
        @Nullable
        public ReadableMap mCurrentLocalData;
        @Nullable
        public ReactStylesDiffMap mCurrentProps;
        @Nullable
        public ReadableMap mCurrentState;
        @Nullable
        public EventEmitterWrapper mEventEmitter;
        final boolean mIsRoot;
        final int mReactTag;
        @Nullable
        final View mView;
        @Nullable
        final ViewManager mViewManager;

        public String toString() {
            boolean z = this.mViewManager == null;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ViewState [");
            outline107.append(this.mReactTag);
            outline107.append("] - isRoot: ");
            outline107.append(this.mIsRoot);
            outline107.append(" - props: ");
            outline107.append(this.mCurrentProps);
            outline107.append(" - localData: ");
            outline107.append(this.mCurrentLocalData);
            outline107.append(" - viewManager: ");
            outline107.append(this.mViewManager);
            outline107.append(" - isLayoutOnly: ");
            outline107.append(z);
            return outline107.toString();
        }

        private ViewState(int i, @Nullable View view, @Nullable ViewManager viewManager) {
            this(i, view, viewManager, false);
        }

        private ViewState(int i, @Nullable View view, ViewManager viewManager, boolean z) {
            this.mCurrentProps = null;
            this.mCurrentLocalData = null;
            this.mCurrentState = null;
            this.mEventEmitter = null;
            this.mReactTag = i;
            this.mView = view;
            this.mIsRoot = z;
            this.mViewManager = viewManager;
        }
    }

    public void receiveCommand(int i, @NonNull String str, @Nullable ReadableArray readableArray) {
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState != null) {
            ViewManager viewManager = nullableViewState.mViewManager;
            if (viewManager != null) {
                View view = nullableViewState.mView;
                if (view != null) {
                    viewManager.receiveCommand((ViewManager) view, str, readableArray);
                    return;
                }
                throw new RetryableMountingLayerException(GeneratedOutlineSupport1.outline49("Unable to find viewState view for tag ", i));
            }
            throw new RetryableMountingLayerException(GeneratedOutlineSupport1.outline49("Unable to find viewState manager for tag ", i));
        }
        throw new RetryableMountingLayerException("Unable to find viewState for tag: " + i + " for commandId: " + str);
    }
}
