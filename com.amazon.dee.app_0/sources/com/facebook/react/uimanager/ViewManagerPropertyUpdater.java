package com.facebook.react.uimanager;

import android.view.View;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.uimanager.ViewManagersPropertyCache;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
public class ViewManagerPropertyUpdater {
    private static final String TAG = "ViewManagerPropertyUpdater";
    private static final Map<Class<?>, ViewManagerSetter<?, ?>> VIEW_MANAGER_SETTER_MAP = new HashMap();
    private static final Map<Class<?>, ShadowNodeSetter<?>> SHADOW_NODE_SETTER_MAP = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FallbackShadowNodeSetter<T extends ReactShadowNode> implements ShadowNodeSetter<T> {
        private final Map<String, ViewManagersPropertyCache.PropSetter> mPropSetters;

        @Override // com.facebook.react.uimanager.ViewManagerPropertyUpdater.Settable
        public void getProperties(Map<String, String> map) {
            for (ViewManagersPropertyCache.PropSetter propSetter : this.mPropSetters.values()) {
                map.put(propSetter.getPropName(), propSetter.getPropType());
            }
        }

        @Override // com.facebook.react.uimanager.ViewManagerPropertyUpdater.ShadowNodeSetter
        public void setProperty(ReactShadowNode reactShadowNode, String str, Object obj) {
            ViewManagersPropertyCache.PropSetter propSetter = this.mPropSetters.get(str);
            if (propSetter != null) {
                propSetter.updateShadowNodeProp(reactShadowNode, obj);
            }
        }

        private FallbackShadowNodeSetter(Class<? extends ReactShadowNode> cls) {
            this.mPropSetters = ViewManagersPropertyCache.getNativePropSettersForShadowNodeClass(cls);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FallbackViewManagerSetter<T extends ViewManager, V extends View> implements ViewManagerSetter<T, V> {
        private final Map<String, ViewManagersPropertyCache.PropSetter> mPropSetters;

        @Override // com.facebook.react.uimanager.ViewManagerPropertyUpdater.Settable
        public void getProperties(Map<String, String> map) {
            for (ViewManagersPropertyCache.PropSetter propSetter : this.mPropSetters.values()) {
                map.put(propSetter.getPropName(), propSetter.getPropType());
            }
        }

        @Override // com.facebook.react.uimanager.ViewManagerPropertyUpdater.ViewManagerSetter
        public void setProperty(T t, V v, String str, Object obj) {
            ViewManagersPropertyCache.PropSetter propSetter = this.mPropSetters.get(str);
            if (propSetter != null) {
                propSetter.updateViewProp(t, v, obj);
            }
        }

        private FallbackViewManagerSetter(Class<? extends ViewManager> cls) {
            this.mPropSetters = ViewManagersPropertyCache.getNativePropSettersForViewManagerClass(cls);
        }
    }

    /* loaded from: classes2.dex */
    public interface Settable {
        void getProperties(Map<String, String> map);
    }

    /* loaded from: classes2.dex */
    public interface ShadowNodeSetter<T extends ReactShadowNode> extends Settable {
        void setProperty(T t, String str, Object obj);
    }

    /* loaded from: classes2.dex */
    public interface ViewManagerSetter<T extends ViewManager, V extends View> extends Settable {
        void setProperty(T t, V v, String str, Object obj);
    }

    public static void clear() {
        ViewManagersPropertyCache.clear();
        VIEW_MANAGER_SETTER_MAP.clear();
        SHADOW_NODE_SETTER_MAP.clear();
    }

    private static <T> T findGeneratedSetter(Class<?> cls) {
        String name = cls.getName();
        try {
            return (T) Class.forName(name + "$$PropsSetter").newInstance();
        } catch (ClassNotFoundException unused) {
            FLog.w(TAG, "Could not find generated setter for " + cls);
            return null;
        } catch (IllegalAccessException e) {
            e = e;
            throw new RuntimeException(GeneratedOutlineSupport1.outline72("Unable to instantiate methods getter for ", name), e);
        } catch (InstantiationException e2) {
            e = e2;
            throw new RuntimeException(GeneratedOutlineSupport1.outline72("Unable to instantiate methods getter for ", name), e);
        }
    }

    private static <T extends ViewManager, V extends View> ViewManagerSetter<T, V> findManagerSetter(Class<? extends ViewManager> cls) {
        FallbackViewManagerSetter fallbackViewManagerSetter = (ViewManagerSetter<T, V>) VIEW_MANAGER_SETTER_MAP.get(cls);
        if (fallbackViewManagerSetter == null) {
            fallbackViewManagerSetter = (ViewManagerSetter) findGeneratedSetter(cls);
            if (fallbackViewManagerSetter == null) {
                fallbackViewManagerSetter = new FallbackViewManagerSetter(cls);
            }
            VIEW_MANAGER_SETTER_MAP.put(cls, fallbackViewManagerSetter);
        }
        return fallbackViewManagerSetter;
    }

    private static <T extends ReactShadowNode> ShadowNodeSetter<T> findNodeSetter(Class<? extends ReactShadowNode> cls) {
        FallbackShadowNodeSetter fallbackShadowNodeSetter = (ShadowNodeSetter<T>) SHADOW_NODE_SETTER_MAP.get(cls);
        if (fallbackShadowNodeSetter == null) {
            fallbackShadowNodeSetter = (ShadowNodeSetter) findGeneratedSetter(cls);
            if (fallbackShadowNodeSetter == null) {
                fallbackShadowNodeSetter = new FallbackShadowNodeSetter(cls);
            }
            SHADOW_NODE_SETTER_MAP.put(cls, fallbackShadowNodeSetter);
        }
        return fallbackShadowNodeSetter;
    }

    public static Map<String, String> getNativeProps(Class<? extends ViewManager> cls, Class<? extends ReactShadowNode> cls2) {
        HashMap hashMap = new HashMap();
        findManagerSetter(cls).getProperties(hashMap);
        findNodeSetter(cls2).getProperties(hashMap);
        return hashMap;
    }

    public static <T extends ViewManagerDelegate<V>, V extends View> void updateProps(T t, V v, ReactStylesDiffMap reactStylesDiffMap) {
        Iterator<Map.Entry<String, Object>> entryIterator = reactStylesDiffMap.mBackingMap.getEntryIterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Object> next = entryIterator.next();
            t.setProperty(v, next.getKey(), next.getValue());
        }
    }

    public static <T extends ViewManager, V extends View> void updateProps(T t, V v, ReactStylesDiffMap reactStylesDiffMap) {
        ViewManagerSetter findManagerSetter = findManagerSetter(t.getClass());
        Iterator<Map.Entry<String, Object>> entryIterator = reactStylesDiffMap.mBackingMap.getEntryIterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Object> next = entryIterator.next();
            findManagerSetter.setProperty(t, v, next.getKey(), next.getValue());
        }
    }

    public static <T extends ReactShadowNode> void updateProps(T t, ReactStylesDiffMap reactStylesDiffMap) {
        ShadowNodeSetter findNodeSetter = findNodeSetter(t.getClass());
        Iterator<Map.Entry<String, Object>> entryIterator = reactStylesDiffMap.mBackingMap.getEntryIterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Object> next = entryIterator.next();
            findNodeSetter.setProperty(t, next.getKey(), next.getValue());
        }
    }
}
