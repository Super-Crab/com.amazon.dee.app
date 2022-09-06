package com.facebook.react.uimanager;

import android.content.Context;
import android.view.View;
import androidx.annotation.Nullable;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ViewManagersPropertyCache {
    private static final Map<Class, Map<String, PropSetter>> CLASS_PROPS_CACHE = new HashMap();
    private static final Map<String, PropSetter> EMPTY_PROPS_MAP = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ArrayPropSetter extends PropSetter {
        public ArrayPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "Array", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object getValueOrDefault(Object obj, Context context) {
            return (ReadableArray) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class BooleanPropSetter extends PropSetter {
        private final boolean mDefaultValue;

        public BooleanPropSetter(ReactProp reactProp, Method method, boolean z) {
            super(reactProp, "boolean", method);
            this.mDefaultValue = z;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return obj == null ? this.mDefaultValue : ((Boolean) obj).booleanValue() ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class BoxedBooleanPropSetter extends PropSetter {
        public BoxedBooleanPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "boolean", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object getValueOrDefault(Object obj, Context context) {
            if (obj != null) {
                return ((Boolean) obj).booleanValue() ? Boolean.TRUE : Boolean.FALSE;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class BoxedColorPropSetter extends PropSetter {
        public BoxedColorPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "mixed", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object getValueOrDefault(Object obj, Context context) {
            if (obj != null) {
                return ColorPropConverter.getColor(obj, context);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class BoxedIntPropSetter extends PropSetter {
        public BoxedIntPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "number", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object getValueOrDefault(Object obj, Context context) {
            if (obj != null) {
                if (obj instanceof Double) {
                    return Integer.valueOf(((Double) obj).intValue());
                }
                return (Integer) obj;
            }
            return null;
        }

        public BoxedIntPropSetter(ReactPropGroup reactPropGroup, Method method, int i) {
            super(reactPropGroup, "number", method, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ColorPropSetter extends PropSetter {
        private final int mDefaultValue;

        public ColorPropSetter(ReactProp reactProp, Method method) {
            this(reactProp, method, 0);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            if (obj == null) {
                return Integer.valueOf(this.mDefaultValue);
            }
            return ColorPropConverter.getColor(obj, context);
        }

        public ColorPropSetter(ReactProp reactProp, Method method, int i) {
            super(reactProp, "mixed", method);
            this.mDefaultValue = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DynamicPropSetter extends PropSetter {
        public DynamicPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "mixed", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return obj instanceof Dynamic ? obj : new DynamicFromObject(obj);
        }

        public DynamicPropSetter(ReactPropGroup reactPropGroup, Method method, int i) {
            super(reactPropGroup, "mixed", method, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MapPropSetter extends PropSetter {
        public MapPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "Map", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object getValueOrDefault(Object obj, Context context) {
            return (ReadableMap) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class StringPropSetter extends PropSetter {
        public StringPropSetter(ReactProp reactProp, Method method) {
            super(reactProp, "String", method);
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        @Nullable
        protected Object getValueOrDefault(Object obj, Context context) {
            return (String) obj;
        }
    }

    ViewManagersPropertyCache() {
    }

    public static void clear() {
        CLASS_PROPS_CACHE.clear();
        EMPTY_PROPS_MAP.clear();
    }

    private static PropSetter createPropSetter(ReactProp reactProp, Method method, Class<?> cls) {
        if (cls == Dynamic.class) {
            return new DynamicPropSetter(reactProp, method);
        }
        if (cls == Boolean.TYPE) {
            return new BooleanPropSetter(reactProp, method, reactProp.defaultBoolean());
        }
        if (cls == Integer.TYPE) {
            if ("Color".equals(reactProp.customType())) {
                return new ColorPropSetter(reactProp, method, reactProp.defaultInt());
            }
            return new IntPropSetter(reactProp, method, reactProp.defaultInt());
        } else if (cls == Float.TYPE) {
            return new FloatPropSetter(reactProp, method, reactProp.defaultFloat());
        } else {
            if (cls == Double.TYPE) {
                return new DoublePropSetter(reactProp, method, reactProp.defaultDouble());
            }
            if (cls == String.class) {
                return new StringPropSetter(reactProp, method);
            }
            if (cls == Boolean.class) {
                return new BoxedBooleanPropSetter(reactProp, method);
            }
            if (cls == Integer.class) {
                if ("Color".equals(reactProp.customType())) {
                    return new BoxedColorPropSetter(reactProp, method);
                }
                return new BoxedIntPropSetter(reactProp, method);
            } else if (cls == ReadableArray.class) {
                return new ArrayPropSetter(reactProp, method);
            } else {
                if (cls == ReadableMap.class) {
                    return new MapPropSetter(reactProp, method);
                }
                throw new RuntimeException("Unrecognized type: " + cls + " for method: " + method.getDeclaringClass().getName() + MqttTopic.MULTI_LEVEL_WILDCARD + method.getName());
            }
        }
    }

    private static void createPropSetters(ReactPropGroup reactPropGroup, Method method, Class<?> cls, Map<String, PropSetter> map) {
        String[] names = reactPropGroup.names();
        int i = 0;
        if (cls == Dynamic.class) {
            while (i < names.length) {
                map.put(names[i], new DynamicPropSetter(reactPropGroup, method, i));
                i++;
            }
        } else if (cls == Integer.TYPE) {
            while (i < names.length) {
                map.put(names[i], new IntPropSetter(reactPropGroup, method, i, reactPropGroup.defaultInt()));
                i++;
            }
        } else if (cls == Float.TYPE) {
            while (i < names.length) {
                map.put(names[i], new FloatPropSetter(reactPropGroup, method, i, reactPropGroup.defaultFloat()));
                i++;
            }
        } else if (cls == Double.TYPE) {
            while (i < names.length) {
                map.put(names[i], new DoublePropSetter(reactPropGroup, method, i, reactPropGroup.defaultDouble()));
                i++;
            }
        } else if (cls == Integer.class) {
            while (i < names.length) {
                map.put(names[i], new BoxedIntPropSetter(reactPropGroup, method, i));
                i++;
            }
        } else {
            throw new RuntimeException("Unrecognized type: " + cls + " for method: " + method.getDeclaringClass().getName() + MqttTopic.MULTI_LEVEL_WILDCARD + method.getName());
        }
    }

    private static void extractPropSettersFromShadowNodeClassDefinition(Class<? extends ReactShadowNode> cls, Map<String, PropSetter> map) {
        Method[] declaredMethods;
        for (Method method : cls.getDeclaredMethods()) {
            ReactProp reactProp = (ReactProp) method.getAnnotation(ReactProp.class);
            if (reactProp != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    map.put(reactProp.name(), createPropSetter(reactProp, method, parameterTypes[0]));
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Wrong number of args for prop setter: ");
                    outline107.append(cls.getName());
                    outline107.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                    outline107.append(method.getName());
                    throw new RuntimeException(outline107.toString());
                }
            }
            ReactPropGroup reactPropGroup = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (reactPropGroup != null) {
                Class<?>[] parameterTypes2 = method.getParameterTypes();
                if (parameterTypes2.length == 2) {
                    if (parameterTypes2[0] == Integer.TYPE) {
                        createPropSetters(reactPropGroup, method, parameterTypes2[1], map);
                    } else {
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Second argument should be property index: ");
                        outline1072.append(cls.getName());
                        outline1072.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                        outline1072.append(method.getName());
                        throw new RuntimeException(outline1072.toString());
                    }
                } else {
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Wrong number of args for group prop setter: ");
                    outline1073.append(cls.getName());
                    outline1073.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                    outline1073.append(method.getName());
                    throw new RuntimeException(outline1073.toString());
                }
            }
        }
    }

    private static void extractPropSettersFromViewManagerClassDefinition(Class<? extends ViewManager> cls, Map<String, PropSetter> map) {
        Method[] declaredMethods;
        for (Method method : cls.getDeclaredMethods()) {
            ReactProp reactProp = (ReactProp) method.getAnnotation(ReactProp.class);
            if (reactProp != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 2) {
                    if (View.class.isAssignableFrom(parameterTypes[0])) {
                        map.put(reactProp.name(), createPropSetter(reactProp, method, parameterTypes[1]));
                    } else {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("First param should be a view subclass to be updated: ");
                        outline107.append(cls.getName());
                        outline107.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                        outline107.append(method.getName());
                        throw new RuntimeException(outline107.toString());
                    }
                } else {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Wrong number of args for prop setter: ");
                    outline1072.append(cls.getName());
                    outline1072.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                    outline1072.append(method.getName());
                    throw new RuntimeException(outline1072.toString());
                }
            }
            ReactPropGroup reactPropGroup = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (reactPropGroup != null) {
                Class<?>[] parameterTypes2 = method.getParameterTypes();
                if (parameterTypes2.length == 3) {
                    if (View.class.isAssignableFrom(parameterTypes2[0])) {
                        if (parameterTypes2[1] == Integer.TYPE) {
                            createPropSetters(reactPropGroup, method, parameterTypes2[2], map);
                        } else {
                            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Second argument should be property index: ");
                            outline1073.append(cls.getName());
                            outline1073.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                            outline1073.append(method.getName());
                            throw new RuntimeException(outline1073.toString());
                        }
                    } else {
                        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("First param should be a view subclass to be updated: ");
                        outline1074.append(cls.getName());
                        outline1074.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                        outline1074.append(method.getName());
                        throw new RuntimeException(outline1074.toString());
                    }
                } else {
                    StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("Wrong number of args for group prop setter: ");
                    outline1075.append(cls.getName());
                    outline1075.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                    outline1075.append(method.getName());
                    throw new RuntimeException(outline1075.toString());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, PropSetter> getNativePropSettersForShadowNodeClass(Class<? extends ReactShadowNode> cls) {
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (cls2 == ReactShadowNode.class) {
                return EMPTY_PROPS_MAP;
            }
        }
        Map<String, PropSetter> map = CLASS_PROPS_CACHE.get(cls);
        if (map != null) {
            return map;
        }
        HashMap hashMap = new HashMap(getNativePropSettersForShadowNodeClass(cls.getSuperclass()));
        extractPropSettersFromShadowNodeClassDefinition(cls, hashMap);
        CLASS_PROPS_CACHE.put(cls, hashMap);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, PropSetter> getNativePropSettersForViewManagerClass(Class<? extends ViewManager> cls) {
        if (cls == ViewManager.class) {
            return EMPTY_PROPS_MAP;
        }
        Map<String, PropSetter> map = CLASS_PROPS_CACHE.get(cls);
        if (map != null) {
            return map;
        }
        HashMap hashMap = new HashMap(getNativePropSettersForViewManagerClass(cls.getSuperclass()));
        extractPropSettersFromViewManagerClassDefinition(cls, hashMap);
        CLASS_PROPS_CACHE.put(cls, hashMap);
        return hashMap;
    }

    static Map<String, String> getNativePropsForView(Class<? extends ViewManager> cls, Class<? extends ReactShadowNode> cls2) {
        HashMap hashMap = new HashMap();
        for (PropSetter propSetter : getNativePropSettersForViewManagerClass(cls).values()) {
            hashMap.put(propSetter.getPropName(), propSetter.getPropType());
        }
        for (PropSetter propSetter2 : getNativePropSettersForShadowNodeClass(cls2).values()) {
            hashMap.put(propSetter2.getPropName(), propSetter2.getPropType());
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DoublePropSetter extends PropSetter {
        private final double mDefaultValue;

        public DoublePropSetter(ReactProp reactProp, Method method, double d) {
            super(reactProp, "number", method);
            this.mDefaultValue = d;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return Double.valueOf(obj == null ? this.mDefaultValue : ((Double) obj).doubleValue());
        }

        public DoublePropSetter(ReactPropGroup reactPropGroup, Method method, int i, double d) {
            super(reactPropGroup, "number", method, i);
            this.mDefaultValue = d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FloatPropSetter extends PropSetter {
        private final float mDefaultValue;

        public FloatPropSetter(ReactProp reactProp, Method method, float f) {
            super(reactProp, "number", method);
            this.mDefaultValue = f;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return Float.valueOf(obj == null ? this.mDefaultValue : Float.valueOf(((Double) obj).floatValue()).floatValue());
        }

        public FloatPropSetter(ReactPropGroup reactPropGroup, Method method, int i, float f) {
            super(reactPropGroup, "number", method, i);
            this.mDefaultValue = f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class IntPropSetter extends PropSetter {
        private final int mDefaultValue;

        public IntPropSetter(ReactProp reactProp, Method method, int i) {
            super(reactProp, "number", method);
            this.mDefaultValue = i;
        }

        @Override // com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter
        protected Object getValueOrDefault(Object obj, Context context) {
            return Integer.valueOf(obj == null ? this.mDefaultValue : Integer.valueOf(((Double) obj).intValue()).intValue());
        }

        public IntPropSetter(ReactPropGroup reactPropGroup, Method method, int i, int i2) {
            super(reactPropGroup, "number", method, i);
            this.mDefaultValue = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class PropSetter {
        @Nullable
        protected final Integer mIndex;
        protected final String mPropName;
        protected final String mPropType;
        protected final Method mSetter;
        private static final Object[] VIEW_MGR_ARGS = new Object[2];
        private static final Object[] VIEW_MGR_GROUP_ARGS = new Object[3];
        private static final Object[] SHADOW_ARGS = new Object[1];
        private static final Object[] SHADOW_GROUP_ARGS = new Object[2];

        public String getPropName() {
            return this.mPropName;
        }

        public String getPropType() {
            return this.mPropType;
        }

        @Nullable
        protected abstract Object getValueOrDefault(Object obj, Context context);

        public void updateShadowNodeProp(ReactShadowNode reactShadowNode, Object obj) {
            try {
                if (this.mIndex == null) {
                    SHADOW_ARGS[0] = getValueOrDefault(obj, reactShadowNode.getThemedContext());
                    this.mSetter.invoke(reactShadowNode, SHADOW_ARGS);
                    Arrays.fill(SHADOW_ARGS, (Object) null);
                    return;
                }
                SHADOW_GROUP_ARGS[0] = this.mIndex;
                SHADOW_GROUP_ARGS[1] = getValueOrDefault(obj, reactShadowNode.getThemedContext());
                this.mSetter.invoke(reactShadowNode, SHADOW_GROUP_ARGS);
                Arrays.fill(SHADOW_GROUP_ARGS, (Object) null);
            } catch (Throwable th) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error while updating prop ");
                outline107.append(this.mPropName);
                FLog.e(ViewManager.class, outline107.toString(), th);
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Error while updating property '");
                outline1072.append(this.mPropName);
                outline1072.append("' in shadow node of type: ");
                outline1072.append(reactShadowNode.getViewClass());
                throw new JSApplicationIllegalArgumentException(outline1072.toString(), th);
            }
        }

        public void updateViewProp(ViewManager viewManager, View view, Object obj) {
            try {
                if (this.mIndex == null) {
                    VIEW_MGR_ARGS[0] = view;
                    VIEW_MGR_ARGS[1] = getValueOrDefault(obj, view.getContext());
                    this.mSetter.invoke(viewManager, VIEW_MGR_ARGS);
                    Arrays.fill(VIEW_MGR_ARGS, (Object) null);
                    return;
                }
                VIEW_MGR_GROUP_ARGS[0] = view;
                VIEW_MGR_GROUP_ARGS[1] = this.mIndex;
                VIEW_MGR_GROUP_ARGS[2] = getValueOrDefault(obj, view.getContext());
                this.mSetter.invoke(viewManager, VIEW_MGR_GROUP_ARGS);
                Arrays.fill(VIEW_MGR_GROUP_ARGS, (Object) null);
            } catch (Throwable th) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error while updating prop ");
                outline107.append(this.mPropName);
                FLog.e(ViewManager.class, outline107.toString(), th);
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Error while updating property '");
                outline1072.append(this.mPropName);
                outline1072.append("' of a view managed by: ");
                outline1072.append(viewManager.getName());
                throw new JSApplicationIllegalArgumentException(outline1072.toString(), th);
            }
        }

        private PropSetter(ReactProp reactProp, String str, Method method) {
            this.mPropName = reactProp.name();
            this.mPropType = !"__default_type__".equals(reactProp.customType()) ? reactProp.customType() : str;
            this.mSetter = method;
            this.mIndex = null;
        }

        private PropSetter(ReactPropGroup reactPropGroup, String str, Method method, int i) {
            this.mPropName = reactPropGroup.names()[i];
            this.mPropType = !"__default_type__".equals(reactPropGroup.customType()) ? reactPropGroup.customType() : str;
            this.mSetter = method;
            this.mIndex = Integer.valueOf(i);
        }
    }
}
