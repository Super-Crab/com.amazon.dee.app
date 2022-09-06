package com.ryanharter.auto.value.gson.internal;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Util {
    static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    /* renamed from: com.ryanharter.auto.value.gson.internal.Util$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$FieldNamingPolicy = new int[FieldNamingPolicy.values().length];

        static {
            try {
                $SwitchMap$com$google$gson$FieldNamingPolicy[FieldNamingPolicy.UPPER_CAMEL_CASE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$FieldNamingPolicy[FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$FieldNamingPolicy[FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$FieldNamingPolicy[FieldNamingPolicy.LOWER_CASE_WITH_DASHES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$FieldNamingPolicy[FieldNamingPolicy.LOWER_CASE_WITH_DOTS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class GenericArrayTypeImpl implements GenericArrayType {
        private final Type componentType;

        GenericArrayTypeImpl(Type type) {
            this.componentType = Util.canonicalize(type);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && WildcardUtil.equals(this, (GenericArrayType) obj);
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline91(new StringBuilder(), Util.typeToString(this.componentType), "[]");
        }
    }

    /* loaded from: classes3.dex */
    public static final class ParameterizedTypeImpl implements ParameterizedType {
        @Nullable
        private final Type ownerType;
        private final Type rawType;
        final Type[] typeArguments;

        ParameterizedTypeImpl(@Nullable Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Class<?> enclosingClass = ((Class) type2).getEnclosingClass();
                if (type != null) {
                    if (enclosingClass == null || WildcardUtil.getRawType(type) != enclosingClass) {
                        throw new IllegalArgumentException("unexpected owner type for " + type2 + RealTimeTextConstants.COLON_SPACE + type);
                    }
                } else if (enclosingClass != null) {
                    throw new IllegalArgumentException("unexpected owner type for " + type2 + ": null");
                }
            }
            this.ownerType = type == null ? null : Util.canonicalize(type);
            this.rawType = Util.canonicalize(type2);
            this.typeArguments = (Type[]) typeArr.clone();
            int i = 0;
            while (true) {
                Type[] typeArr2 = this.typeArguments;
                if (i < typeArr2.length) {
                    if (typeArr2[i] != null) {
                        Util.checkNotPrimitive(typeArr2[i]);
                        Type[] typeArr3 = this.typeArguments;
                        typeArr3[i] = Util.canonicalize(typeArr3[i]);
                        i++;
                    } else {
                        throw new NullPointerException();
                    }
                } else {
                    return;
                }
            }
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && WildcardUtil.equals(this, (ParameterizedType) obj);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        @Nullable
        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ Util.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((this.typeArguments.length + 1) * 30);
            sb.append(Util.typeToString(this.rawType));
            if (this.typeArguments.length == 0) {
                return sb.toString();
            }
            sb.append(Config.Compare.LESS_THAN);
            sb.append(Util.typeToString(this.typeArguments[0]));
            for (int i = 1; i < this.typeArguments.length; i++) {
                sb.append(", ");
                sb.append(Util.typeToString(this.typeArguments[i]));
            }
            sb.append(Config.Compare.GREATER_THAN);
            return sb.toString();
        }
    }

    /* loaded from: classes3.dex */
    public static final class WildcardTypeImpl implements WildcardType {
        @Nullable
        private final Type lowerBound;
        private final Type upperBound;

        /* JADX INFO: Access modifiers changed from: package-private */
        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            if (typeArr2.length <= 1) {
                if (typeArr.length == 1) {
                    if (typeArr2.length == 1) {
                        if (typeArr2[0] != null) {
                            Util.checkNotPrimitive(typeArr2[0]);
                            if (typeArr[0] == Object.class) {
                                this.lowerBound = Util.canonicalize(typeArr2[0]);
                                this.upperBound = Object.class;
                                return;
                            }
                            throw new IllegalArgumentException();
                        }
                        throw new NullPointerException();
                    } else if (typeArr[0] != null) {
                        Util.checkNotPrimitive(typeArr[0]);
                        this.lowerBound = null;
                        this.upperBound = Util.canonicalize(typeArr[0]);
                        return;
                    } else {
                        throw new NullPointerException();
                    }
                }
                throw new IllegalArgumentException();
            }
            throw new IllegalArgumentException();
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && WildcardUtil.equals(this, (WildcardType) obj);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type type = this.lowerBound;
            return type != null ? new Type[]{type} : Util.EMPTY_TYPE_ARRAY;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public int hashCode() {
            Type type = this.lowerBound;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            if (this.lowerBound != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("? super ");
                outline107.append(Util.typeToString(this.lowerBound));
                return outline107.toString();
            } else if (this.upperBound == Object.class) {
                return WebConstants.UriConstants.QUESTIONMARK_KEY;
            } else {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("? extends ");
                outline1072.append(Util.typeToString(this.upperBound));
                return outline1072.toString();
            }
        }
    }

    private Util() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new GenericArrayTypeImpl(canonicalize(cls.getComponentType())) : cls;
        } else if (type instanceof ParameterizedType) {
            if (type instanceof ParameterizedTypeImpl) {
                return type;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return type instanceof GenericArrayTypeImpl ? type : new GenericArrayTypeImpl(((GenericArrayType) type).getGenericComponentType());
        } else if (!(type instanceof WildcardType) || (type instanceof WildcardTypeImpl)) {
            return type;
        } else {
            WildcardType wildcardType = (WildcardType) type;
            return new WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkNotPrimitive(Type type) {
        if (!(type instanceof Class) || !((Class) type).isPrimitive()) {
            return;
        }
        throw new IllegalArgumentException("Unexpected primitive " + type + ". Use the boxed type.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int hashCodeOrZero(@Nullable Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static String modifyString(char c, String str, int i) {
        return i < str.length() ? GeneratedOutlineSupport1.outline55(str, i, GeneratedOutlineSupport1.outline104(c)) : String.valueOf(c);
    }

    public static Map<String, String> renameFields(Class<?> cls, List<String> list, FieldNamingStrategy fieldNamingStrategy) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : list) {
            if (fieldNamingStrategy instanceof FieldNamingPolicy) {
                int ordinal = ((FieldNamingPolicy) fieldNamingStrategy).ordinal();
                if (ordinal == 1) {
                    linkedHashMap.put(str, upperCaseFirstLetter(str));
                } else if (ordinal == 2) {
                    linkedHashMap.put(str, upperCaseFirstLetter(separateCamelCase(str, " ")));
                } else if (ordinal == 3) {
                    linkedHashMap.put(str, separateCamelCase(str, "_").toLowerCase(Locale.ENGLISH));
                } else if (ordinal == 4) {
                    linkedHashMap.put(str, separateCamelCase(str, ProcessIdUtil.DEFAULT_PROCESSID).toLowerCase(Locale.ENGLISH));
                } else if (ordinal != 5) {
                    linkedHashMap.put(str, str);
                } else {
                    linkedHashMap.put(str, separateCamelCase(str, ".").toLowerCase(Locale.ENGLISH));
                }
            } else {
                try {
                    linkedHashMap.put(str, fieldNamingStrategy.translateName(cls.getDeclaredField(str)));
                } catch (NoSuchFieldException unused) {
                    linkedHashMap.put(str, str);
                }
            }
        }
        return linkedHashMap;
    }

    private static String separateCamelCase(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    private static String upperCaseFirstLetter(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char charAt = str.charAt(0);
        int length = str.length();
        while (i < length - 1 && !Character.isLetter(charAt)) {
            sb.append(charAt);
            i++;
            charAt = str.charAt(i);
        }
        if (!Character.isUpperCase(charAt)) {
            sb.append(modifyString(Character.toUpperCase(charAt), str, i + 1));
            return sb.toString();
        }
        return str;
    }
}
