package androidx.databinding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
/* loaded from: classes.dex */
public class DataBindingUtil {
    private static DataBinderMapper sMapper = new DataBinderMapperImpl();
    private static DataBindingComponent sDefaultComponent = null;

    private DataBindingUtil() {
    }

    @Nullable
    public static <T extends ViewDataBinding> T bind(@NonNull View view) {
        return (T) bind(view, sDefaultComponent);
    }

    private static <T extends ViewDataBinding> T bindToAddedViews(DataBindingComponent dataBindingComponent, ViewGroup viewGroup, int i, int i2) {
        int childCount = viewGroup.getChildCount();
        int i3 = childCount - i;
        if (i3 == 1) {
            return (T) bind(dataBindingComponent, viewGroup.getChildAt(childCount - 1), i2);
        }
        View[] viewArr = new View[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            viewArr[i4] = viewGroup.getChildAt(i4 + i);
        }
        return (T) bind(dataBindingComponent, viewArr, i2);
    }

    @Nullable
    public static String convertBrIdToString(int i) {
        return sMapper.convertBrIdToString(i);
    }

    @Nullable
    public static <T extends ViewDataBinding> T findBinding(@NonNull View view) {
        while (view != null) {
            T t = (T) ViewDataBinding.getBinding(view);
            if (t != null) {
                return t;
            }
            Object tag = view.getTag();
            if (tag instanceof String) {
                String str = (String) tag;
                if (str.startsWith(TtmlNode.TAG_LAYOUT) && str.endsWith("_0")) {
                    char charAt = str.charAt(6);
                    int indexOf = str.indexOf(47, 7);
                    boolean z = false;
                    if (charAt != '/' ? !(charAt != '-' || indexOf == -1 || str.indexOf(47, indexOf + 1) != -1) : indexOf == -1) {
                        z = true;
                    }
                    if (z) {
                        return null;
                    }
                }
            }
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    @Nullable
    public static <T extends ViewDataBinding> T getBinding(@NonNull View view) {
        return (T) ViewDataBinding.getBinding(view);
    }

    @Nullable
    public static DataBindingComponent getDefaultComponent() {
        return sDefaultComponent;
    }

    public static <T extends ViewDataBinding> T inflate(@NonNull LayoutInflater layoutInflater, int i, @Nullable ViewGroup viewGroup, boolean z) {
        return (T) inflate(layoutInflater, i, viewGroup, z, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T setContentView(@NonNull Activity activity, int i) {
        return (T) setContentView(activity, i, sDefaultComponent);
    }

    public static void setDefaultComponent(@Nullable DataBindingComponent dataBindingComponent) {
        sDefaultComponent = dataBindingComponent;
    }

    @Nullable
    public static <T extends ViewDataBinding> T bind(@NonNull View view, DataBindingComponent dataBindingComponent) {
        T t = (T) getBinding(view);
        if (t != null) {
            return t;
        }
        Object tag = view.getTag();
        if (tag instanceof String) {
            int layoutId = sMapper.getLayoutId((String) tag);
            if (layoutId != 0) {
                return (T) sMapper.getDataBinder(dataBindingComponent, view, layoutId);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("View is not a binding layout. Tag: ", tag));
        }
        throw new IllegalArgumentException("View is not a binding layout");
    }

    public static <T extends ViewDataBinding> T inflate(@NonNull LayoutInflater layoutInflater, int i, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        int i2 = 0;
        boolean z2 = viewGroup != null && z;
        if (z2) {
            i2 = viewGroup.getChildCount();
        }
        View inflate = layoutInflater.inflate(i, viewGroup, z);
        if (z2) {
            return (T) bindToAddedViews(dataBindingComponent, viewGroup, i2, i);
        }
        return (T) bind(dataBindingComponent, inflate, i);
    }

    public static <T extends ViewDataBinding> T setContentView(@NonNull Activity activity, int i, @Nullable DataBindingComponent dataBindingComponent) {
        activity.setContentView(i);
        return (T) bindToAddedViews(dataBindingComponent, (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290), 0, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends ViewDataBinding> T bind(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        return (T) sMapper.getDataBinder(dataBindingComponent, viewArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends ViewDataBinding> T bind(DataBindingComponent dataBindingComponent, View view, int i) {
        return (T) sMapper.getDataBinder(dataBindingComponent, view, i);
    }
}
