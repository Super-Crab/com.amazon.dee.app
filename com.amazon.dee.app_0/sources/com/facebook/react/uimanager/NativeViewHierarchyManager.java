package com.facebook.react.uimanager;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.PopupMenu;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationController;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.concurrent.NotThreadSafe;
@NotThreadSafe
/* loaded from: classes2.dex */
public class NativeViewHierarchyManager {
    private static final String TAG = "NativeViewHierarchyManager";
    private final boolean DEBUG_MODE;
    private final RectF mBoundingBox;
    private final JSResponderHandler mJSResponderHandler;
    private boolean mLayoutAnimationEnabled;
    private final LayoutAnimationController mLayoutAnimator;
    private HashMap<Integer, Set<Integer>> mPendingDeletionsForTag;
    private PopupMenu mPopupMenu;
    private final SparseBooleanArray mRootTags;
    private final RootViewManager mRootViewManager;
    private final SparseArray<ViewManager> mTagsToViewManagers;
    private final SparseArray<View> mTagsToViews;
    private final ViewManagerRegistry mViewManagers;

    /* loaded from: classes2.dex */
    private static class PopupMenuCallbackHandler implements PopupMenu.OnMenuItemClickListener, PopupMenu.OnDismissListener {
        boolean mConsumed;
        final Callback mSuccess;

        @Override // android.widget.PopupMenu.OnDismissListener
        public void onDismiss(PopupMenu popupMenu) {
            if (!this.mConsumed) {
                this.mSuccess.invoke("dismissed");
                this.mConsumed = true;
            }
        }

        @Override // android.widget.PopupMenu.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (!this.mConsumed) {
                this.mSuccess.invoke(UIManagerModuleConstants.ACTION_ITEM_SELECTED, Integer.valueOf(menuItem.getOrder()));
                this.mConsumed = true;
                return true;
            }
            return false;
        }

        private PopupMenuCallbackHandler(Callback callback) {
            this.mConsumed = false;
            this.mSuccess = callback;
        }
    }

    public NativeViewHierarchyManager(ViewManagerRegistry viewManagerRegistry) {
        this(viewManagerRegistry, new RootViewManager());
    }

    private boolean arrayContains(@Nullable int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private void computeBoundingBox(View view, int[] iArr) {
        this.mBoundingBox.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        mapRectFromViewToWindowCoords(view, this.mBoundingBox);
        iArr[0] = Math.round(this.mBoundingBox.left);
        iArr[1] = Math.round(this.mBoundingBox.top);
        RectF rectF = this.mBoundingBox;
        iArr[2] = Math.round(rectF.right - rectF.left);
        RectF rectF2 = this.mBoundingBox;
        iArr[3] = Math.round(rectF2.bottom - rectF2.top);
    }

    private static String constructManageChildrenErrorMessage(ViewGroup viewGroup, ViewGroupManager viewGroupManager, @Nullable int[] iArr, @Nullable ViewAtIndex[] viewAtIndexArr, @Nullable int[] iArr2) {
        int i;
        int i2;
        int i3;
        StringBuilder sb = new StringBuilder();
        if (viewGroup != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("View tag:");
            outline107.append(viewGroup.getId());
            outline107.append(" View Type:");
            outline107.append(viewGroup.getClass().toString());
            outline107.append("\n");
            sb.append(outline107.toString());
            sb.append("  children(" + viewGroupManager.getChildCount(viewGroup) + "): [\n");
            for (int i4 = 0; viewGroupManager.getChildAt(viewGroup, i4) != null; i4 += 16) {
                int i5 = 0;
                while (true) {
                    if (viewGroupManager.getChildAt(viewGroup, i4 + i5) != null && i5 < 16) {
                        sb.append(viewGroupManager.getChildAt(viewGroup, i3).getId() + ",");
                        i5++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (iArr != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("  indicesToRemove(");
            outline1072.append(iArr.length);
            outline1072.append("): [\n");
            sb.append(outline1072.toString());
            for (int i6 = 0; i6 < iArr.length; i6 += 16) {
                int i7 = 0;
                while (true) {
                    if (i6 + i7 < iArr.length && i7 < 16) {
                        sb.append(iArr[i2] + ",");
                        i7++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (viewAtIndexArr != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("  viewsToAdd(");
            outline1073.append(viewAtIndexArr.length);
            outline1073.append("): [\n");
            sb.append(outline1073.toString());
            for (int i8 = 0; i8 < viewAtIndexArr.length; i8 += 16) {
                int i9 = 0;
                while (true) {
                    int i10 = i8 + i9;
                    if (i10 < viewAtIndexArr.length && i9 < 16) {
                        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("[");
                        outline1074.append(viewAtIndexArr[i10].mIndex);
                        outline1074.append(",");
                        outline1074.append(viewAtIndexArr[i10].mTag);
                        outline1074.append("],");
                        sb.append(outline1074.toString());
                        i9++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ],\n");
        }
        if (iArr2 != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("  tagsToDelete(");
            outline1075.append(iArr2.length);
            outline1075.append("): [\n");
            sb.append(outline1075.toString());
            for (int i11 = 0; i11 < iArr2.length; i11 += 16) {
                int i12 = 0;
                while (true) {
                    if (i11 + i12 < iArr2.length && i12 < 16) {
                        sb.append(iArr2[i] + ",");
                        i12++;
                    }
                }
                sb.append("\n");
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }

    private static String constructSetChildrenErrorMessage(ViewGroup viewGroup, ViewGroupManager viewGroupManager, ReadableArray readableArray) {
        ViewAtIndex[] viewAtIndexArr = new ViewAtIndex[readableArray.size()];
        for (int i = 0; i < readableArray.size(); i++) {
            viewAtIndexArr[i] = new ViewAtIndex(readableArray.getInt(i), i);
        }
        return constructManageChildrenErrorMessage(viewGroup, viewGroupManager, null, viewAtIndexArr, null);
    }

    private Set<Integer> getPendingDeletionsForTag(int i) {
        if (this.mPendingDeletionsForTag == null) {
            this.mPendingDeletionsForTag = new HashMap<>();
        }
        if (!this.mPendingDeletionsForTag.containsKey(Integer.valueOf(i))) {
            this.mPendingDeletionsForTag.put(Integer.valueOf(i), new HashSet());
        }
        return this.mPendingDeletionsForTag.get(Integer.valueOf(i));
    }

    private ThemedReactContext getReactContextForView(int i) {
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            return (ThemedReactContext) view.getContext();
        }
        throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline49("Could not find view with tag ", i));
    }

    private void mapRectFromViewToWindowCoords(View view, RectF rectF) {
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            matrix.mapRect(rectF);
        }
        rectF.offset(view.getLeft(), view.getTop());
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            View view2 = (View) parent;
            rectF.offset(-view2.getScrollX(), -view2.getScrollY());
            Matrix matrix2 = view2.getMatrix();
            if (!matrix2.isIdentity()) {
                matrix2.mapRect(rectF);
            }
            rectF.offset(view2.getLeft(), view2.getTop());
            parent = view2.getParent();
        }
    }

    public synchronized void addRootView(int i, View view) {
        addRootViewGroup(i, view);
    }

    protected final synchronized void addRootViewGroup(int i, View view) {
        if (view.getId() != -1) {
            String str = TAG;
            FLog.e(str, "Trying to add a root view with an explicit id (" + view.getId() + ") already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
        }
        this.mTagsToViews.put(i, view);
        this.mTagsToViewManagers.put(i, this.mRootViewManager);
        this.mRootTags.put(i, true);
        view.setId(i);
    }

    public void clearJSResponder() {
        this.mJSResponderHandler.clearJSResponder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearLayoutAnimation() {
        this.mLayoutAnimator.reset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void configureLayoutAnimation(ReadableMap readableMap, Callback callback) {
        this.mLayoutAnimator.initializeFromConfig(readableMap, callback);
    }

    public synchronized void createView(ThemedReactContext themedReactContext, int i, String str, @Nullable ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        SystraceMessage.beginSection(0L, "NativeViewHierarchyManager_createView").arg("tag", i).arg("className", str).flush();
        ViewManager viewManager = this.mViewManagers.get(str);
        View createView = viewManager.createView(themedReactContext, null, null, this.mJSResponderHandler);
        this.mTagsToViews.put(i, createView);
        this.mTagsToViewManagers.put(i, viewManager);
        createView.setId(i);
        if (reactStylesDiffMap != null) {
            viewManager.updateProperties(createView, reactStylesDiffMap);
        }
        Systrace.endSection(0L);
    }

    public void dismissPopupMenu() {
        PopupMenu popupMenu = this.mPopupMenu;
        if (popupMenu != null) {
            popupMenu.dismiss();
        }
    }

    @Deprecated
    public synchronized void dispatchCommand(int i, int i2, @Nullable ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            resolveViewManager(i).receiveCommand((ViewManager) view, i2, readableArray);
        } else {
            throw new RetryableMountingLayerException("Trying to send command to a non-existing view with tag [" + i + "] and command " + i2);
        }
    }

    protected synchronized void dropView(View view) {
        UiThreadUtil.assertOnUiThread();
        if (view == null) {
            return;
        }
        if (this.mTagsToViewManagers.get(view.getId()) == null) {
            return;
        }
        if (!this.mRootTags.get(view.getId())) {
            resolveViewManager(view.getId()).onDropViewInstance(view);
        }
        ViewManager viewManager = this.mTagsToViewManagers.get(view.getId());
        if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
            ViewGroup viewGroup = (ViewGroup) view;
            ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
            for (int childCount = viewGroupManager.getChildCount(viewGroup) - 1; childCount >= 0; childCount--) {
                View childAt = viewGroupManager.getChildAt(viewGroup, childCount);
                if (childAt == null) {
                    FLog.e(TAG, "Unable to drop null child view");
                } else if (this.mTagsToViews.get(childAt.getId()) != null) {
                    dropView(childAt);
                }
            }
            viewGroupManager.removeAllViews(viewGroup);
        }
        this.mTagsToViews.remove(view.getId());
        this.mTagsToViewManagers.remove(view.getId());
    }

    public synchronized int findTargetTagForTouch(int i, float f, float f2) {
        View view;
        UiThreadUtil.assertOnUiThread();
        view = this.mTagsToViews.get(i);
        if (view != null) {
        } else {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i);
        }
        return TouchTargetHelper.findTargetTagForTouch(f, f2, (ViewGroup) view);
    }

    @Nullable
    public long getInstanceHandle(int i) {
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            Long l = (Long) view.getTag(R.id.view_tag_instance_handle);
            if (l != null) {
                return l.longValue();
            }
            throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline49("Unable to find instanceHandle for tag: ", i));
        }
        throw new IllegalViewOperationException(GeneratedOutlineSupport1.outline49("Unable to find view for tag: ", i));
    }

    public synchronized void manageChildren(final int i, @Nullable int[] iArr, @Nullable ViewAtIndex[] viewAtIndexArr, @Nullable int[] iArr2) {
        int i2;
        int[] iArr3 = iArr;
        synchronized (this) {
            UiThreadUtil.assertOnUiThread();
            final Set<Integer> pendingDeletionsForTag = getPendingDeletionsForTag(i);
            final ViewGroup viewGroup = (ViewGroup) this.mTagsToViews.get(i);
            final ViewGroupManager viewGroupManager = (ViewGroupManager) resolveViewManager(i);
            if (viewGroup != null) {
                int childCount = viewGroupManager.getChildCount(viewGroup);
                if (iArr3 != null) {
                    int length = iArr3.length - 1;
                    while (length >= 0) {
                        int i3 = iArr3[length];
                        if (i3 >= 0) {
                            if (viewGroupManager.getChildAt(viewGroup, i3) == null) {
                                if (this.mRootTags.get(i) && viewGroupManager.getChildCount(viewGroup) == 0) {
                                    return;
                                }
                                throw new IllegalViewOperationException("Trying to remove a view index above child count " + i3 + " view tag: " + i + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr3, viewAtIndexArr, iArr2));
                            } else if (i3 < childCount) {
                                View childAt = viewGroupManager.getChildAt(viewGroup, i3);
                                if (!this.mLayoutAnimationEnabled || !this.mLayoutAnimator.shouldAnimateLayout(childAt) || !arrayContains(iArr2, childAt.getId())) {
                                    viewGroupManager.removeViewAt(viewGroup, i3);
                                }
                                length--;
                                childCount = i3;
                            } else {
                                throw new IllegalViewOperationException("Trying to remove an out of order view index:" + i3 + " view tag: " + i + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr3, viewAtIndexArr, iArr2));
                            }
                        } else {
                            throw new IllegalViewOperationException("Trying to remove a negative view index:" + i3 + " view tag: " + i + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr3, viewAtIndexArr, iArr2));
                        }
                    }
                }
                if (iArr2 != null) {
                    int i4 = 0;
                    while (i4 < iArr2.length) {
                        int i5 = iArr2[i4];
                        final View view = this.mTagsToViews.get(i5);
                        if (view != null) {
                            if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(view)) {
                                pendingDeletionsForTag.add(Integer.valueOf(i5));
                                i2 = i4;
                                this.mLayoutAnimator.deleteView(view, new LayoutAnimationListener() { // from class: com.facebook.react.uimanager.NativeViewHierarchyManager.1
                                    @Override // com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener
                                    public void onAnimationEnd() {
                                        UiThreadUtil.assertOnUiThread();
                                        viewGroupManager.removeView(viewGroup, view);
                                        NativeViewHierarchyManager.this.dropView(view);
                                        pendingDeletionsForTag.remove(Integer.valueOf(view.getId()));
                                        if (pendingDeletionsForTag.isEmpty()) {
                                            NativeViewHierarchyManager.this.mPendingDeletionsForTag.remove(Integer.valueOf(i));
                                        }
                                    }
                                });
                            } else {
                                i2 = i4;
                                dropView(view);
                            }
                            i4 = i2 + 1;
                            iArr3 = iArr;
                        } else {
                            throw new IllegalViewOperationException("Trying to destroy unknown view tag: " + i5 + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr, viewAtIndexArr, iArr2));
                        }
                    }
                }
                int[] iArr4 = iArr3;
                if (viewAtIndexArr != null) {
                    for (ViewAtIndex viewAtIndex : viewAtIndexArr) {
                        View view2 = this.mTagsToViews.get(viewAtIndex.mTag);
                        if (view2 != null) {
                            int i6 = viewAtIndex.mIndex;
                            if (!pendingDeletionsForTag.isEmpty()) {
                                i6 = 0;
                                int i7 = 0;
                                while (i6 < viewGroup.getChildCount() && i7 != viewAtIndex.mIndex) {
                                    if (!pendingDeletionsForTag.contains(Integer.valueOf(viewGroup.getChildAt(i6).getId()))) {
                                        i7++;
                                    }
                                    i6++;
                                }
                            }
                            viewGroupManager.addView(viewGroup, view2, i6);
                        } else {
                            throw new IllegalViewOperationException("Trying to add unknown view tag: " + viewAtIndex.mTag + "\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr4, viewAtIndexArr, iArr2));
                        }
                    }
                }
                if (pendingDeletionsForTag.isEmpty()) {
                    this.mPendingDeletionsForTag.remove(Integer.valueOf(i));
                }
                return;
            }
            throw new IllegalViewOperationException("Trying to manageChildren view with tag " + i + " which doesn't exist\n detail: " + constructManageChildrenErrorMessage(viewGroup, viewGroupManager, iArr3, viewAtIndexArr, iArr2));
        }
    }

    public synchronized void measure(int i, int[] iArr) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            View view2 = (View) RootViewUtil.getRootView(view);
            if (view2 != null) {
                computeBoundingBox(view2, iArr);
                int i2 = iArr[0];
                int i3 = iArr[1];
                computeBoundingBox(view, iArr);
                iArr[0] = iArr[0] - i2;
                iArr[1] = iArr[1] - i3;
            } else {
                throw new NoSuchNativeViewException("Native view " + i + " is no longer on screen");
            }
        } else {
            throw new NoSuchNativeViewException("No native view for " + i + " currently exists");
        }
    }

    public synchronized void measureInWindow(int i, int[] iArr) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            view.getLocationOnScreen(iArr);
            Rect rect = new Rect();
            view.getWindowVisibleDisplayFrame(rect);
            iArr[0] = iArr[0] - rect.left;
            iArr[1] = iArr[1] - rect.top;
            iArr[2] = view.getWidth();
            iArr[3] = view.getHeight();
        } else {
            throw new NoSuchNativeViewException("No native view for " + i + " currently exists");
        }
    }

    public synchronized void removeRootView(int i) {
        UiThreadUtil.assertOnUiThread();
        if (!this.mRootTags.get(i)) {
            SoftAssertions.assertUnreachable("View with tag " + i + " is not registered as a root view");
        }
        dropView(this.mTagsToViews.get(i));
        this.mRootTags.delete(i);
    }

    public final synchronized View resolveView(int i) {
        View view;
        view = this.mTagsToViews.get(i);
        if (view == null) {
            throw new IllegalViewOperationException("Trying to resolve view with tag " + i + " which doesn't exist");
        }
        return view;
    }

    public final synchronized ViewManager resolveViewManager(int i) {
        ViewManager viewManager;
        viewManager = this.mTagsToViewManagers.get(i);
        if (viewManager == null) {
            throw new IllegalViewOperationException("ViewManager for tag " + i + " could not be found.\n");
        }
        return viewManager;
    }

    public void sendAccessibilityEvent(int i, int i2) {
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            view.sendAccessibilityEvent(i2);
            return;
        }
        throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline49("Could not find view with tag ", i));
    }

    public synchronized void setChildren(int i, ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        ViewGroup viewGroup = (ViewGroup) this.mTagsToViews.get(i);
        ViewGroupManager viewGroupManager = (ViewGroupManager) resolveViewManager(i);
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            View view = this.mTagsToViews.get(readableArray.getInt(i2));
            if (view != null) {
                viewGroupManager.addView(viewGroup, view, i2);
            } else {
                throw new IllegalViewOperationException("Trying to add unknown view tag: " + readableArray.getInt(i2) + "\n detail: " + constructSetChildrenErrorMessage(viewGroup, viewGroupManager, readableArray));
            }
        }
    }

    public synchronized void setJSResponder(int i, int i2, boolean z) {
        if (!z) {
            this.mJSResponderHandler.setJSResponder(i2, null);
            return;
        }
        View view = this.mTagsToViews.get(i);
        if (i2 != i && (view instanceof ViewParent)) {
            this.mJSResponderHandler.setJSResponder(i2, (ViewParent) view);
            return;
        }
        if (this.mRootTags.get(i)) {
            SoftAssertions.assertUnreachable("Cannot block native responder on " + i + " that is a root view");
        }
        this.mJSResponderHandler.setJSResponder(i2, view.getParent());
    }

    public void setLayoutAnimationEnabled(boolean z) {
        this.mLayoutAnimationEnabled = z;
    }

    public synchronized void showPopupMenu(int i, ReadableArray readableArray, Callback callback, Callback callback2) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i);
        if (view == null) {
            callback2.invoke("Can't display popup. Could not find view with tag " + i);
            return;
        }
        this.mPopupMenu = new PopupMenu(getReactContextForView(i), view);
        Menu menu = this.mPopupMenu.getMenu();
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            menu.add(0, 0, i2, readableArray.getString(i2));
        }
        PopupMenuCallbackHandler popupMenuCallbackHandler = new PopupMenuCallbackHandler(callback);
        this.mPopupMenu.setOnMenuItemClickListener(popupMenuCallbackHandler);
        this.mPopupMenu.setOnDismissListener(popupMenuCallbackHandler);
        this.mPopupMenu.show();
    }

    public synchronized void updateInstanceHandle(int i, long j) {
        UiThreadUtil.assertOnUiThread();
        try {
            updateInstanceHandle(resolveView(i), j);
        } catch (IllegalViewOperationException e) {
            String str = TAG;
            FLog.e(str, "Unable to update properties for view tag " + i, e);
        }
    }

    public synchronized void updateLayout(int i, int i2, int i3, int i4, int i5, int i6) {
        UiThreadUtil.assertOnUiThread();
        SystraceMessage.beginSection(0L, "NativeViewHierarchyManager_updateLayout").arg("parentTag", i).arg("tag", i2).flush();
        View resolveView = resolveView(i2);
        resolveView.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(i6, 1073741824));
        ViewParent parent = resolveView.getParent();
        if (parent instanceof RootView) {
            parent.requestLayout();
        }
        if (!this.mRootTags.get(i)) {
            ViewManager viewManager = this.mTagsToViewManagers.get(i);
            if (viewManager instanceof IViewManagerWithChildren) {
                IViewManagerWithChildren iViewManagerWithChildren = (IViewManagerWithChildren) viewManager;
                if (iViewManagerWithChildren != null && !iViewManagerWithChildren.needsCustomLayoutForChildren()) {
                    updateLayout(resolveView, i3, i4, i5, i6);
                }
            } else {
                throw new IllegalViewOperationException("Trying to use view with tag " + i + " as a parent, but its Manager doesn't implement IViewManagerWithChildren");
            }
        } else {
            updateLayout(resolveView, i3, i4, i5, i6);
        }
        Systrace.endSection(0L);
    }

    public synchronized void updateProperties(int i, ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        try {
            ViewManager resolveViewManager = resolveViewManager(i);
            View resolveView = resolveView(i);
            if (reactStylesDiffMap != null) {
                resolveViewManager.updateProperties(resolveView, reactStylesDiffMap);
            }
        } catch (IllegalViewOperationException e) {
            String str = TAG;
            FLog.e(str, "Unable to update properties for view tag " + i, e);
        }
    }

    public synchronized void updateViewExtraData(int i, Object obj) {
        UiThreadUtil.assertOnUiThread();
        resolveViewManager(i).updateExtraData(resolveView(i), obj);
    }

    public NativeViewHierarchyManager(ViewManagerRegistry viewManagerRegistry, RootViewManager rootViewManager) {
        this.DEBUG_MODE = false;
        this.mJSResponderHandler = new JSResponderHandler();
        this.mLayoutAnimator = new LayoutAnimationController();
        this.mBoundingBox = new RectF();
        this.mViewManagers = viewManagerRegistry;
        this.mTagsToViews = new SparseArray<>();
        this.mTagsToViewManagers = new SparseArray<>();
        this.mRootTags = new SparseBooleanArray();
        this.mRootViewManager = rootViewManager;
    }

    private void updateInstanceHandle(View view, long j) {
        UiThreadUtil.assertOnUiThread();
        view.setTag(R.id.view_tag_instance_handle, Long.valueOf(j));
    }

    public synchronized void dispatchCommand(int i, String str, @Nullable ReadableArray readableArray) {
        ViewManagerDelegate delegate;
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i);
        if (view != null) {
            ViewManager resolveViewManager = resolveViewManager(i);
            if (ReactFeatureFlags.useViewManagerDelegatesForCommands && (delegate = resolveViewManager.getDelegate()) != null) {
                delegate.receiveCommand(view, str, readableArray);
            } else {
                resolveViewManager.receiveCommand((ViewManager) view, str, readableArray);
            }
        } else {
            throw new RetryableMountingLayerException("Trying to send command to a non-existing view with tag [" + i + "] and command " + str);
        }
    }

    private void updateLayout(View view, int i, int i2, int i3, int i4) {
        if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(view)) {
            this.mLayoutAnimator.applyLayoutUpdate(view, i, i2, i3, i4);
        } else {
            view.layout(i, i2, i3 + i, i4 + i2);
        }
    }
}
