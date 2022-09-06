package com.amazon.regulator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.regulator.internal.Preconditions;
import java.lang.reflect.Constructor;
import java.util.UUID;
/* loaded from: classes13.dex */
public abstract class ViewController {
    private static final String EXTRA_ARGUMENTS = "arguments";
    private static final String EXTRA_CHILD_ROUTER_KEYS = "childRouterKeys";
    private static final String EXTRA_CHILD_ROUTER_VALUES = "childRouterValues";
    private static final String EXTRA_ID = "id";
    private static final String EXTRA_KEEP_VIEW_IN_BACK_STACK = "keepViewWhenInBackStack";
    private static final String EXTRA_VIEW = "view";
    private static final String EXTRA_VIEW_HIERARCHY = "viewHierarchy";
    private Bundle arguments;
    private boolean hasAttached;
    private boolean hasCreated;
    private boolean keepViewWhenInBackStack;
    private Bundle pendingViewStateToRestore;
    private Router router;
    private SparseArray<Parcelable> savedViewHierarchyState;
    private int themeResId;
    private View view;
    private final ArrayMap<String, Router> childRouters = new ArrayMap<>();
    private final ArrayMap<String, Bundle> childRouteStateToRestore = new ArrayMap<>();
    private String id = UUID.randomUUID().toString();

    /* JADX INFO: Access modifiers changed from: protected */
    public ViewController() {
        ensureRequiredConstructor();
    }

    private void ensureRequiredConstructor() {
        for (Constructor<?> constructor : getClass().getConstructors()) {
            if (constructor.getParameterTypes().length == 0) {
                return;
            }
        }
        throw new RuntimeException(getClass() + " has to have a public constructor without any parameters");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void attach(ViewGroup viewGroup) {
        attach(viewGroup, -1);
    }

    protected final int checkSelfPermission(String str) {
        Router router = this.router;
        if (router != null) {
            return router.checkSelfPermission(str);
        }
        throw new IllegalStateException("Cannot check permission before a controller is created");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void create(Router router) {
        Preconditions.nonNull(router, "router == null");
        this.router = router;
        if (this.hasCreated) {
            return;
        }
        this.hasCreated = true;
        onCreate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void destroy() {
        if (!this.hasCreated) {
            return;
        }
        destroyView();
        for (Router router : this.childRouters.values()) {
            router.destroy();
        }
        onDestroy();
        this.hasCreated = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void destroyView() {
        if (this.view == null) {
            return;
        }
        detach();
        this.savedViewHierarchyState = new SparseArray<>();
        this.view.saveHierarchyState(this.savedViewHierarchyState);
        for (Router router : this.childRouters.values()) {
            router.detach();
        }
        onDestroyView(this.view);
        this.view = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void detach() {
        if (!this.hasAttached) {
            return;
        }
        this.hasAttached = false;
        ViewGroup viewGroup = (ViewGroup) this.view.getParent();
        if (viewGroup == null) {
            return;
        }
        onDetach(this.view);
        viewGroup.removeView(this.view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ViewController findControllerById(String str) {
        if (str.equals(this.id)) {
            return this;
        }
        for (Router router : this.childRouters.values()) {
            ViewController findControllerById = router.findControllerById(str);
            if (findControllerById != null) {
                return findControllerById;
            }
        }
        return null;
    }

    public final Bundle getArguments() {
        return this.arguments;
    }

    public final Router getChildRouter(String str) {
        Preconditions.nonNull(str, "name == null");
        if (this.router != null) {
            Router router = this.childRouters.get(str);
            if (router != null) {
                return router;
            }
            Router router2 = new Router(this.router.getContext(), this.router.getComponent(), this.childRouteStateToRestore.get(str));
            router2.setParentController(this);
            this.childRouters.put(str, router2);
            return router2;
        }
        throw new IllegalStateException("Cannot obtain child router if not a controller is not created");
    }

    public final Component getComponent() {
        Router router = this.router;
        if (router != null) {
            return router.getComponent();
        }
        throw new IllegalStateException("Cannot get component before a controller is created");
    }

    public final Context getContext() {
        Router router = this.router;
        if (router != null) {
            return router.getContext();
        }
        throw new IllegalStateException("Cannot get context before a controller is created");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getId() {
        return this.id;
    }

    public ViewController getParentController() {
        Router router = this.router;
        if (router != null) {
            return router.getParentController();
        }
        throw new IllegalStateException("Cannot obtain child router if a controller is not created");
    }

    public final Router getRouter() {
        return this.router;
    }

    public final View getView() {
        return this.view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean handleBack() {
        return onHandleBack();
    }

    public boolean isAttached() {
        return this.hasAttached;
    }

    public final boolean isCreated() {
        return this.hasCreated;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean keepViewWhenInBackStack() {
        return this.keepViewWhenInBackStack;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAttach(View view) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCreate() {
    }

    protected abstract View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDestroy() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDestroyView(View view) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDetach(View view) {
    }

    protected boolean onHandleBack() {
        for (Router router : this.childRouters.values()) {
            if (router.handleBack()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRestoreViewState(View view, Bundle bundle) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSaveViewState(View view, Bundle bundle) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void overrideTheme(int i) {
        this.themeResId = i;
    }

    public final boolean removeChildRouter(Router router) {
        Preconditions.nonNull(router, "router == null");
        int size = this.childRouters.size();
        for (int i = 0; i < size; i++) {
            if (this.childRouters.valueAt(i) == router) {
                router.destroy();
                this.childRouters.removeAt(i);
                this.childRouteStateToRestore.remove(this.childRouters.keyAt(i));
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void requestPermissions(String[] strArr, int i) {
        Router router = this.router;
        if (router != null) {
            router.requestPermissions(this, strArr, i);
            return;
        }
        throw new IllegalStateException("Cannot request permissions before a controller is created");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void restoreState(Bundle bundle) {
        Preconditions.nonNull(bundle, "state == null");
        this.id = bundle.getString("id");
        this.arguments = bundle.getBundle("arguments");
        this.keepViewWhenInBackStack = bundle.getBoolean(EXTRA_KEEP_VIEW_IN_BACK_STACK);
        Bundle bundle2 = bundle.getBundle("view");
        if (bundle2 != null) {
            bundle2.setClassLoader(getClass().getClassLoader());
        }
        String[] stringArray = bundle.getStringArray(EXTRA_CHILD_ROUTER_KEYS);
        Parcelable[] parcelableArray = bundle.getParcelableArray(EXTRA_CHILD_ROUTER_VALUES);
        if (stringArray != null && parcelableArray != null) {
            int length = stringArray.length;
            for (int i = 0; i < length; i++) {
                String str = stringArray[i];
                Bundle bundle3 = (Bundle) parcelableArray[i];
                bundle3.setClassLoader(getClass().getClassLoader());
                this.childRouteStateToRestore.put(str, bundle3);
                Router router = this.childRouters.get(str);
                if (router != null) {
                    router.restoreInstanceState(bundle3);
                }
            }
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(EXTRA_VIEW_HIERARCHY);
        View view = this.view;
        if (view == null) {
            this.pendingViewStateToRestore = bundle2;
            this.savedViewHierarchyState = sparseParcelableArray;
            return;
        }
        view.restoreHierarchyState(sparseParcelableArray);
        if (bundle2 == null) {
            return;
        }
        onRestoreViewState(this.view, bundle2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Bundle saveState() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.id);
        bundle.putBundle("arguments", this.arguments);
        bundle.putBoolean(EXTRA_KEEP_VIEW_IN_BACK_STACK, this.keepViewWhenInBackStack);
        if (!this.childRouters.isEmpty()) {
            String[] strArr = new String[this.childRouters.size()];
            Bundle[] bundleArr = new Bundle[this.childRouters.size()];
            int size = this.childRouters.size();
            for (int i = 0; i < size; i++) {
                strArr[i] = this.childRouters.keyAt(i);
                bundleArr[i] = new Bundle(getClass().getClassLoader());
                this.childRouters.valueAt(i).saveInstanceState(bundleArr[i]);
            }
            bundle.putStringArray(EXTRA_CHILD_ROUTER_KEYS, strArr);
            bundle.putParcelableArray(EXTRA_CHILD_ROUTER_VALUES, bundleArr);
        }
        SparseArray<Parcelable> sparseArray = this.savedViewHierarchyState;
        if (this.view != null) {
            Bundle bundle2 = new Bundle(getClass().getClassLoader());
            onSaveViewState(this.view, bundle2);
            bundle.putBundle("view", bundle2);
            sparseArray = new SparseArray<>();
            this.view.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray(EXTRA_VIEW_HIERARCHY, sparseArray);
        return bundle;
    }

    public final void setArguments(Bundle bundle) {
        this.arguments = bundle;
    }

    protected void setKeepViewWhenInBackStack(boolean z) {
        this.keepViewWhenInBackStack = z;
    }

    protected final boolean shouldShowRequestPermissionRationale(String str) {
        Router router = this.router;
        if (router != null) {
            return router.shouldShowRequestPermissionRationale(str);
        }
        throw new IllegalStateException("Cannot check to show request permissions rationale before a controller is created");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void startActivityForResult(Intent intent, int i) {
        startActivityForResult(intent, i, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void attach(ViewGroup viewGroup, int i) {
        Preconditions.nonNull(viewGroup, "container == null");
        View view = this.view;
        if (view != null && (view.getParent() == null || this.view.getParent() != viewGroup)) {
            detach();
        }
        if (this.view == null) {
            Context context = viewGroup.getContext();
            if (this.themeResId != 0) {
                context = new ContextThemeWrapper(viewGroup.getContext(), this.themeResId);
            }
            this.view = onCreateView(LayoutInflater.from(context), viewGroup);
            Preconditions.nonNull(this.view, "view == null");
            this.view.setSaveFromParentEnabled(false);
            SparseArray<Parcelable> sparseArray = this.savedViewHierarchyState;
            if (sparseArray != null) {
                this.view.restoreHierarchyState(sparseArray);
                this.savedViewHierarchyState = null;
            }
            Bundle bundle = this.pendingViewStateToRestore;
            if (bundle != null) {
                onRestoreViewState(this.view, bundle);
                this.pendingViewStateToRestore = null;
            }
        }
        if (!this.hasAttached) {
            this.hasAttached = true;
            if (i == -1) {
                viewGroup.addView(this.view);
            } else {
                viewGroup.addView(this.view, i);
            }
            onAttach(this.view);
        }
    }

    protected final void startActivityForResult(Intent intent, int i, Bundle bundle) {
        Router router = this.router;
        if (router != null) {
            router.startActivityForResult(this, intent, i, bundle);
            return;
        }
        throw new IllegalStateException("Cannot start an activity before a controller is created");
    }
}
