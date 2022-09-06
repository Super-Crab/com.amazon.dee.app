package com.facebook.react.uimanager.util;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.facebook.react.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
public class ReactFindViewUtil {
    private static final List<OnViewFoundListener> mOnViewFoundListeners = new ArrayList();
    private static final Map<OnMultipleViewsFoundListener, Set<String>> mOnMultipleViewsFoundListener = new HashMap();

    /* loaded from: classes2.dex */
    public interface OnMultipleViewsFoundListener {
        void onViewFound(View view, String str);
    }

    /* loaded from: classes2.dex */
    public interface OnViewFoundListener {
        String getNativeId();

        void onViewFound(View view);
    }

    public static void addViewListener(OnViewFoundListener onViewFoundListener) {
        mOnViewFoundListeners.add(onViewFoundListener);
    }

    public static void addViewsListener(OnMultipleViewsFoundListener onMultipleViewsFoundListener, Set<String> set) {
        mOnMultipleViewsFoundListener.put(onMultipleViewsFoundListener, set);
    }

    @Nullable
    public static View findView(View view, String str) {
        String nativeId = getNativeId(view);
        if (nativeId == null || !nativeId.equals(str)) {
            if (!(view instanceof ViewGroup)) {
                return null;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View findView = findView(viewGroup.getChildAt(i), str);
                if (findView != null) {
                    return findView;
                }
            }
            return null;
        }
        return view;
    }

    @Nullable
    private static String getNativeId(View view) {
        Object tag = view.getTag(R.id.view_tag_native_id);
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }

    public static void notifyViewRendered(View view) {
        String nativeId = getNativeId(view);
        if (nativeId == null) {
            return;
        }
        Iterator<OnViewFoundListener> it2 = mOnViewFoundListeners.iterator();
        while (it2.hasNext()) {
            OnViewFoundListener next = it2.next();
            if (nativeId.equals(next.getNativeId())) {
                next.onViewFound(view);
                it2.remove();
            }
        }
        for (Map.Entry<OnMultipleViewsFoundListener, Set<String>> entry : mOnMultipleViewsFoundListener.entrySet()) {
            Set<String> value = entry.getValue();
            if (value != null && value.contains(nativeId)) {
                entry.getKey().onViewFound(view, nativeId);
            }
        }
    }

    public static void removeViewListener(OnViewFoundListener onViewFoundListener) {
        mOnViewFoundListeners.remove(onViewFoundListener);
    }

    public static void removeViewsListener(OnMultipleViewsFoundListener onMultipleViewsFoundListener) {
        mOnMultipleViewsFoundListener.remove(onMultipleViewsFoundListener);
    }

    public static void findView(View view, OnViewFoundListener onViewFoundListener) {
        View findView = findView(view, onViewFoundListener.getNativeId());
        if (findView != null) {
            onViewFoundListener.onViewFound(findView);
        }
        addViewListener(onViewFoundListener);
    }
}
