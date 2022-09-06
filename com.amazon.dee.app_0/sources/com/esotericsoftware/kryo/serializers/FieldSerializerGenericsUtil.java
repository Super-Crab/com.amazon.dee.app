package com.esotericsoftware.kryo.serializers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.Generics;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.minlog.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class FieldSerializerGenericsUtil {
    private Kryo kryo;
    private FieldSerializer serializer;

    public FieldSerializerGenericsUtil(FieldSerializer fieldSerializer) {
        this.serializer = fieldSerializer;
        this.kryo = fieldSerializer.getKryo();
    }

    public static Class[] getGenerics(Type type, Kryo kryo) {
        Class concreteClass;
        Class concreteClass2;
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            return genericComponentType instanceof Class ? new Class[]{(Class) genericComponentType} : getGenerics(genericComponentType, kryo);
        } else if (!(type instanceof ParameterizedType)) {
            return null;
        } else {
            if (Log.TRACE) {
                Log.trace("kryo", "Processing generic type " + type);
            }
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            Class[] clsArr = new Class[actualTypeArguments.length];
            int length = actualTypeArguments.length;
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                Type type2 = actualTypeArguments[i2];
                if (Log.TRACE) {
                    Log.trace("kryo", "Processing actual type " + type2 + " (" + type2.getClass().getName() + ")");
                }
                clsArr[i2] = Object.class;
                if (type2 instanceof Class) {
                    clsArr[i2] = (Class) type2;
                } else if (type2 instanceof ParameterizedType) {
                    clsArr[i2] = (Class) ((ParameterizedType) type2).getRawType();
                } else if (type2 instanceof TypeVariable) {
                    Generics genericsScope = kryo.getGenericsScope();
                    if (genericsScope != null && (concreteClass2 = genericsScope.getConcreteClass(((TypeVariable) type2).getName())) != null) {
                        clsArr[i2] = concreteClass2;
                    }
                } else if (type2 instanceof GenericArrayType) {
                    Type genericComponentType2 = ((GenericArrayType) type2).getGenericComponentType();
                    if (genericComponentType2 instanceof Class) {
                        clsArr[i2] = Array.newInstance((Class) genericComponentType2, 0).getClass();
                    } else if (genericComponentType2 instanceof TypeVariable) {
                        Generics genericsScope2 = kryo.getGenericsScope();
                        if (genericsScope2 != null && (concreteClass = genericsScope2.getConcreteClass(((TypeVariable) genericComponentType2).getName())) != null) {
                            clsArr[i2] = Array.newInstance(concreteClass, 0).getClass();
                        }
                    } else {
                        Class[] generics = getGenerics(genericComponentType2, kryo);
                        if (generics != null) {
                            clsArr[i2] = generics[0];
                        }
                    }
                }
                i++;
            }
            if (i != 0) {
                return clsArr;
            }
            return null;
        }
    }

    private Class<?> getTypeVarConcreteClass(Class[] clsArr, int i, String str) {
        if (clsArr != null && clsArr.length > i) {
            return clsArr[i];
        }
        if (Log.TRACE) {
            Log.trace("kryo", "Trying to use kryo.getGenericScope");
        }
        Generics genericsScope = this.kryo.getGenericsScope();
        if (genericsScope == null) {
            return null;
        }
        return genericsScope.getConcreteClass(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Generics buildGenericsScope(Class cls, Class[] clsArr) {
        TypeVariable[] typeVariableArr = null;
        for (Class cls2 = cls; cls2 != null; cls2 = cls2.getComponentType()) {
            typeVariableArr = cls2.getTypeParameters();
            if (typeVariableArr != null && typeVariableArr.length != 0) {
                break;
            }
        }
        if (typeVariableArr == null || typeVariableArr.length <= 0) {
            return null;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class ");
        outline107.append(cls.getName());
        outline107.append(" has generic type parameters");
        Log.trace("kryo", outline107.toString());
        HashMap hashMap = new HashMap();
        int i = 0;
        for (TypeVariable typeVariable : typeVariableArr) {
            String name = typeVariable.getName();
            if (Log.TRACE) {
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Type parameter variable: name=", name, " type bounds=");
                outline115.append(Arrays.toString(typeVariable.getBounds()));
                Log.trace("kryo", outline115.toString());
            }
            Class<?> typeVarConcreteClass = getTypeVarConcreteClass(clsArr, i, name);
            if (typeVarConcreteClass != null) {
                hashMap.put(name, typeVarConcreteClass);
                if (Log.TRACE) {
                    StringBuilder outline1152 = GeneratedOutlineSupport1.outline115("Concrete type used for ", name, " is: ");
                    outline1152.append(typeVarConcreteClass.getName());
                    Log.trace("kryo", outline1152.toString());
                }
            }
            i++;
        }
        return new Generics(hashMap);
    }

    Class[] computeFieldGenerics(Type type, Field field, Class[] clsArr) {
        Generics genericsScope;
        Class concreteClass;
        if (type != null) {
            if ((type instanceof TypeVariable) && this.serializer.getGenericsScope() != null) {
                Class concreteClass2 = this.serializer.getGenericsScope().getConcreteClass(((TypeVariable) type).getName());
                if (concreteClass2 == null) {
                    return null;
                }
                clsArr[0] = concreteClass2;
                Class[] clsArr2 = {clsArr[0]};
                if (!Log.TRACE) {
                    return clsArr2;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Determined concrete class of '");
                outline107.append(field.getName());
                outline107.append("' to be ");
                outline107.append(clsArr[0].getName());
                Log.trace("kryo", outline107.toString());
                return clsArr2;
            } else if (type instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                if (actualTypeArguments == null) {
                    return null;
                }
                Class[] clsArr3 = new Class[actualTypeArguments.length];
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    Type type2 = actualTypeArguments[i];
                    if (type2 instanceof Class) {
                        clsArr3[i] = (Class) type2;
                    } else if (type2 instanceof ParameterizedType) {
                        clsArr3[i] = (Class) ((ParameterizedType) type2).getRawType();
                    } else if ((type2 instanceof TypeVariable) && this.serializer.getGenericsScope() != null) {
                        clsArr3[i] = this.serializer.getGenericsScope().getConcreteClass(((TypeVariable) type2).getName());
                    } else if (type2 instanceof WildcardType) {
                        clsArr3[i] = Object.class;
                    } else if (type2 instanceof GenericArrayType) {
                        Type genericComponentType = ((GenericArrayType) type2).getGenericComponentType();
                        if (genericComponentType instanceof Class) {
                            clsArr3[i] = Array.newInstance((Class) genericComponentType, 0).getClass();
                        } else if ((genericComponentType instanceof TypeVariable) && (genericsScope = this.serializer.getGenericsScope()) != null && (concreteClass = genericsScope.getConcreteClass(((TypeVariable) genericComponentType).getName())) != null) {
                            clsArr3[i] = Array.newInstance(concreteClass, 0).getClass();
                        }
                    } else {
                        clsArr3[i] = null;
                    }
                }
                if (Log.TRACE) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Determined concrete class of parametrized '");
                    outline1072.append(field.getName());
                    outline1072.append("' to be ");
                    outline1072.append(type);
                    outline1072.append(" where type parameters are ");
                    outline1072.append(Arrays.toString(clsArr3));
                    Log.trace("kryo", outline1072.toString());
                }
                return clsArr3;
            } else if (!(type instanceof GenericArrayType)) {
                return null;
            } else {
                Class[] computeFieldGenerics = computeFieldGenerics(((GenericArrayType) type).getGenericComponentType(), field, new Class[]{clsArr[0]});
                if (Log.TRACE && computeFieldGenerics != null) {
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Determined concrete class of a generic array '");
                    outline1073.append(field.getName());
                    outline1073.append("' to be ");
                    outline1073.append(type);
                    outline1073.append(" where type parameters are ");
                    outline1073.append(Arrays.toString(computeFieldGenerics));
                    Log.trace("kryo", outline1073.toString());
                    return computeFieldGenerics;
                } else if (!Log.TRACE) {
                    return computeFieldGenerics;
                } else {
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Determined concrete class of '");
                    outline1074.append(field.getName());
                    outline1074.append("' to be ");
                    outline1074.append(type);
                    Log.trace("kryo", outline1074.toString());
                    return computeFieldGenerics;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldSerializer.CachedField newCachedFieldOfGenericType(Field field, int i, Class[] clsArr, Type type) {
        if (Log.TRACE) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Field '");
            outline107.append(field.getName());
            outline107.append("' of type ");
            outline107.append(clsArr[0]);
            outline107.append(" of generic type ");
            outline107.append(type);
            Log.trace("kryo", outline107.toString());
        }
        if (Log.TRACE && type != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Field generic type is of class ");
            outline1072.append(type.getClass().getName());
            Log.trace("kryo", outline1072.toString());
        }
        Generics buildGenericsScope = buildGenericsScope(clsArr[0], getGenerics(type, this.kryo));
        if (clsArr[0] == Object.class && (type instanceof TypeVariable) && this.serializer.getGenericsScope() != null) {
            TypeVariable typeVariable = (TypeVariable) type;
            Class concreteClass = this.serializer.getGenericsScope().getConcreteClass(typeVariable.getName());
            if (concreteClass != null) {
                buildGenericsScope = new Generics();
                buildGenericsScope.add(typeVariable.getName(), concreteClass);
            }
        }
        if (Log.TRACE) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Generics scope of field '");
            outline1073.append(field.getName());
            outline1073.append("' of class ");
            outline1073.append(type);
            outline1073.append(" is ");
            outline1073.append(buildGenericsScope);
            Log.trace("kryo", outline1073.toString());
        }
        Class[] computeFieldGenerics = computeFieldGenerics(type, field, clsArr);
        FieldSerializer.CachedField newMatchingCachedField = this.serializer.newMatchingCachedField(field, i, clsArr[0], type, computeFieldGenerics);
        if (computeFieldGenerics != null && (newMatchingCachedField instanceof ObjectField) && computeFieldGenerics.length > 0 && computeFieldGenerics[0] != null) {
            ((ObjectField) newMatchingCachedField).generics = computeFieldGenerics;
            if (Log.TRACE) {
                StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Field generics: ");
                outline1074.append(Arrays.toString(computeFieldGenerics));
                Log.trace("kryo", outline1074.toString());
            }
        }
        return newMatchingCachedField;
    }
}
