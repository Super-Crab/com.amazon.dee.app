package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigMemorySize;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.Optional;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes3.dex */
public class ConfigBeanImpl {
    public static <T> T createInternal(Config config, Class<T> cls) {
        PropertyDescriptor[] propertyDescriptors;
        if (((SimpleConfig) config).mo10232root().resolveStatus() == ResolveStatus.RESOLVED) {
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            for (Map.Entry<String, ConfigValue> entry : config.mo10232root().entrySet()) {
                String key = entry.getKey();
                String camelCase = ConfigImplUtil.toCamelCase(key);
                if (!hashMap2.containsKey(camelCase) || key.equals(camelCase)) {
                    hashMap.put(camelCase, (AbstractConfigValue) entry.getValue());
                    hashMap2.put(camelCase, key);
                }
            }
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(cls);
                try {
                    ArrayList<PropertyDescriptor> arrayList = new ArrayList();
                    for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                        if (propertyDescriptor.getReadMethod() != null && propertyDescriptor.getWriteMethod() != null) {
                            arrayList.add(propertyDescriptor);
                        }
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (PropertyDescriptor propertyDescriptor2 : arrayList) {
                        ConfigValueType valueTypeOrNull = getValueTypeOrNull(propertyDescriptor2.getWriteMethod().getParameterTypes()[0]);
                        if (valueTypeOrNull != null) {
                            String str = (String) hashMap2.get(propertyDescriptor2.getName());
                            if (str == null) {
                                str = propertyDescriptor2.getName();
                            }
                            Path newKey = Path.newKey(str);
                            AbstractConfigValue abstractConfigValue = (AbstractConfigValue) hashMap.get(propertyDescriptor2.getName());
                            if (abstractConfigValue != null) {
                                SimpleConfig.checkValid(newKey, valueTypeOrNull, abstractConfigValue, arrayList2);
                            } else if (!isOptionalProperty(cls, propertyDescriptor2)) {
                                SimpleConfig.addMissing(arrayList2, valueTypeOrNull, newKey, config.origin());
                            }
                        }
                    }
                    if (arrayList2.isEmpty()) {
                        T newInstance = cls.newInstance();
                        for (PropertyDescriptor propertyDescriptor3 : arrayList) {
                            Method writeMethod = propertyDescriptor3.getWriteMethod();
                            Type type = writeMethod.getGenericParameterTypes()[0];
                            Class<?> cls2 = writeMethod.getParameterTypes()[0];
                            String str2 = (String) hashMap2.get(propertyDescriptor3.getName());
                            if (str2 == null) {
                                if (!isOptionalProperty(cls, propertyDescriptor3)) {
                                    throw new ConfigException.Missing(propertyDescriptor3.getName());
                                }
                            } else {
                                writeMethod.invoke(newInstance, getValue(cls, type, cls2, config, str2));
                            }
                        }
                        return newInstance;
                    }
                    throw new ConfigException.ValidationFailed(arrayList2);
                } catch (IllegalAccessException e) {
                    throw new ConfigException.BadBean(GeneratedOutlineSupport1.outline40(cls, new StringBuilder(), " getters and setters are not accessible, they must be for use as a bean"), e);
                } catch (InstantiationException e2) {
                    throw new ConfigException.BadBean(GeneratedOutlineSupport1.outline40(cls, new StringBuilder(), " needs a public no-args constructor to be used as a bean"), e2);
                } catch (InvocationTargetException e3) {
                    throw new ConfigException.BadBean(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Calling bean method on "), " caused an exception"), e3);
                }
            } catch (IntrospectionException e4) {
                throw new ConfigException.BadBean(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("Could not get bean information for class ")), e4);
            }
        }
        throw new ConfigException.NotResolved("need to Config#resolve() a config before using it to initialize a bean, see the API docs for Config#resolve()");
    }

    private static Field getField(Class cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (NoSuchFieldException unused) {
            Class superclass = cls.getSuperclass();
            if (superclass != null) {
                return getField(superclass, str);
            }
            return null;
        }
    }

    private static Object getListValue(Class<?> cls, Type type, Class<?> cls2, Config config, String str) {
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 == Boolean.class) {
            return config.getBooleanList(str);
        }
        if (type2 == Integer.class) {
            return config.getIntList(str);
        }
        if (type2 == Double.class) {
            return config.getDoubleList(str);
        }
        if (type2 == Long.class) {
            return config.getLongList(str);
        }
        if (type2 == String.class) {
            return config.getStringList(str);
        }
        if (type2 == Duration.class) {
            return config.getDurationList(str);
        }
        if (type2 == ConfigMemorySize.class) {
            return config.getMemorySizeList(str);
        }
        if (type2 == Object.class) {
            return config.getAnyRefList(str);
        }
        if (type2 == Config.class) {
            return config.getConfigList(str);
        }
        if (type2 == ConfigObject.class) {
            return config.getObjectList(str);
        }
        if (type2 == ConfigValue.class) {
            return config.getList(str);
        }
        Class cls3 = (Class) type2;
        if (cls3.isEnum()) {
            return config.getEnumList(cls3, str);
        }
        if (hasAtLeastOneBeanProperty(cls3)) {
            ArrayList arrayList = new ArrayList();
            for (Config config2 : config.getConfigList(str)) {
                arrayList.add(createInternal(config2, cls3));
            }
            return arrayList;
        }
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Bean property '", str, "' of class ");
        outline115.append(cls.getName());
        outline115.append(" has unsupported list element type ");
        outline115.append(type2);
        throw new ConfigException.BadBean(outline115.toString());
    }

    private static Object getSetValue(Class<?> cls, Type type, Class<?> cls2, Config config, String str) {
        return new HashSet((List) getListValue(cls, type, cls2, config, str));
    }

    private static Object getValue(Class<?> cls, Type type, Class<?> cls2, Config config, String str) {
        if (cls2 != Boolean.class && cls2 != Boolean.TYPE) {
            if (cls2 != Integer.class && cls2 != Integer.TYPE) {
                if (cls2 != Double.class && cls2 != Double.TYPE) {
                    if (cls2 != Long.class && cls2 != Long.TYPE) {
                        if (cls2 == String.class) {
                            return config.getString(str);
                        }
                        if (cls2 == Duration.class) {
                            return config.getDuration(str);
                        }
                        if (cls2 != ConfigMemorySize.class) {
                            if (cls2 == Object.class) {
                                return config.getAnyRef(str);
                            }
                            if (cls2 == List.class) {
                                return getListValue(cls, type, cls2, config, str);
                            }
                            if (cls2 == Set.class) {
                                return getSetValue(cls, type, cls2, config, str);
                            }
                            if (cls2 == Map.class) {
                                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                                if (actualTypeArguments[0] == String.class && actualTypeArguments[1] == Object.class) {
                                    return config.mo10226getObject(str).mo10253unwrapped();
                                }
                                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Bean property '", str, "' of class ");
                                GeneratedOutlineSupport1.outline146(cls, outline115, " has unsupported Map<");
                                outline115.append(actualTypeArguments[0]);
                                outline115.append(",");
                                outline115.append(actualTypeArguments[1]);
                                outline115.append(">, only Map<String,Object> is supported right now");
                                throw new ConfigException.BadBean(outline115.toString());
                            } else if (cls2 == Config.class) {
                                return config.mo10225getConfig(str);
                            } else {
                                if (cls2 == ConfigObject.class) {
                                    return config.mo10226getObject(str);
                                }
                                if (cls2 == ConfigValue.class) {
                                    return config.mo10227getValue(str);
                                }
                                if (cls2 == ConfigList.class) {
                                    return config.getList(str);
                                }
                                if (cls2.isEnum()) {
                                    return config.getEnum(cls2, str);
                                }
                                if (hasAtLeastOneBeanProperty(cls2)) {
                                    return createInternal(config.mo10225getConfig(str), cls2);
                                }
                                StringBuilder outline1152 = GeneratedOutlineSupport1.outline115("Bean property ", str, " of class ");
                                outline1152.append(cls.getName());
                                outline1152.append(" has unsupported type ");
                                outline1152.append(type);
                                throw new ConfigException.BadBean(outline1152.toString());
                            }
                        }
                        return config.getMemorySize(str);
                    }
                    return Long.valueOf(config.getLong(str));
                }
                return Double.valueOf(config.getDouble(str));
            }
            return Integer.valueOf(config.getInt(str));
        }
        return Boolean.valueOf(config.getBoolean(str));
    }

    private static ConfigValueType getValueTypeOrNull(Class<?> cls) {
        if (cls != Boolean.class && cls != Boolean.TYPE) {
            if (cls != Integer.class && cls != Integer.TYPE) {
                if (cls != Double.class && cls != Double.TYPE) {
                    if (cls != Long.class && cls != Long.TYPE) {
                        if (cls == String.class) {
                            return ConfigValueType.STRING;
                        }
                        if (cls == Duration.class || cls == ConfigMemorySize.class) {
                            return null;
                        }
                        if (cls == List.class) {
                            return ConfigValueType.LIST;
                        }
                        if (cls == Map.class) {
                            return ConfigValueType.OBJECT;
                        }
                        if (cls == Config.class) {
                            return ConfigValueType.OBJECT;
                        }
                        if (cls == ConfigObject.class) {
                            return ConfigValueType.OBJECT;
                        }
                        if (cls != ConfigList.class) {
                            return null;
                        }
                        return ConfigValueType.LIST;
                    }
                    return ConfigValueType.NUMBER;
                }
                return ConfigValueType.NUMBER;
            }
            return ConfigValueType.NUMBER;
        }
        return ConfigValueType.BOOLEAN;
    }

    private static boolean hasAtLeastOneBeanProperty(Class<?> cls) {
        PropertyDescriptor[] propertyDescriptors;
        try {
            for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(cls).getPropertyDescriptors()) {
                if (propertyDescriptor.getReadMethod() != null && propertyDescriptor.getWriteMethod() != null) {
                    return true;
                }
            }
        } catch (IntrospectionException unused) {
        }
        return false;
    }

    private static boolean isOptionalProperty(Class cls, PropertyDescriptor propertyDescriptor) {
        Field field = getField(cls, propertyDescriptor.getName());
        return field != null && ((Optional[]) field.getAnnotationsByType(Optional.class)).length > 0;
    }
}
