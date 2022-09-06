package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.builtins.CollectionSerializersKt;
import kotlinx.serialization.builtins.PrimitiveSerializersKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: JvmResolving.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0004\"\u0006\b\u0000\u0010\u0006\u0018\u0001H\u0087\b¨\u0006\u0007"}, d2 = {"serializerByTypeToken", "Lkotlinx/serialization/KSerializer;", "", "type", "Ljava/lang/reflect/Type;", "typeTokenOf", ExifInterface.GPS_DIRECTION_TRUE, "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class JvmResolvingKt {
    @NotNull
    public static final KSerializer<Object> serializerByTypeToken(@NotNull Type type) {
        KClass kClass;
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (type instanceof GenericArrayType) {
            Type eType = ((GenericArrayType) type).getGenericComponentType();
            if (eType instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) eType).getUpperBounds();
                Intrinsics.checkExpressionValueIsNotNull(upperBounds, "it.upperBounds");
                eType = (Type) ArraysKt.first(upperBounds);
            }
            Intrinsics.checkExpressionValueIsNotNull(eType, "eType");
            KSerializer<Object> serializerByTypeToken = serializerByTypeToken(eType);
            if (eType instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) eType).getRawType();
                if (rawType == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<*>");
                }
                kClass = JvmClassMappingKt.getKotlinClass((Class) rawType);
            } else if (!(eType instanceof KClass)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unsupported type in GenericArray: ");
                outline107.append(Reflection.getOrCreateKotlinClass(eType.getClass()));
                throw new IllegalStateException(outline107.toString());
            } else {
                kClass = (KClass) eType;
            }
            if (kClass != null) {
                KSerializer<Object> ArraySerializer = PrimitiveSerializersKt.ArraySerializer(kClass, serializerByTypeToken);
                if (ArraySerializer == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any>");
                }
                return ArraySerializer;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
        } else if (type instanceof Class) {
            Class cls = (Class) type;
            if (!cls.isArray()) {
                KClass kotlinClass = JvmClassMappingKt.getKotlinClass(cls);
                if (kotlinClass == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                }
                KSerializer<Object> serializer = PlatformUtilsKt.serializer(kotlinClass);
                if (serializer == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                return serializer;
            }
            Class<?> componentType = cls.getComponentType();
            Intrinsics.checkExpressionValueIsNotNull(componentType, "type.componentType");
            KSerializer<Object> serializerByTypeToken2 = serializerByTypeToken(componentType);
            KClass kotlinClass2 = JvmClassMappingKt.getKotlinClass(componentType);
            if (kotlinClass2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
            }
            KSerializer<Object> ArraySerializer2 = PrimitiveSerializersKt.ArraySerializer(kotlinClass2, serializerByTypeToken2);
            if (ArraySerializer2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any>");
            }
            return ArraySerializer2;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType2 = parameterizedType.getRawType();
            if (rawType2 != null) {
                Class cls2 = (Class) rawType2;
                Type[] args = parameterizedType.getActualTypeArguments();
                if (List.class.isAssignableFrom(cls2)) {
                    Type type2 = args[0];
                    Intrinsics.checkExpressionValueIsNotNull(type2, "args[0]");
                    KSerializer<Object> ListSerializer = CollectionSerializersKt.ListSerializer(serializerByTypeToken(type2));
                    if (ListSerializer == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any>");
                    }
                    return ListSerializer;
                } else if (Set.class.isAssignableFrom(cls2)) {
                    Type type3 = args[0];
                    Intrinsics.checkExpressionValueIsNotNull(type3, "args[0]");
                    KSerializer<Object> SetSerializer = CollectionSerializersKt.SetSerializer(serializerByTypeToken(type3));
                    if (SetSerializer == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any>");
                    }
                    return SetSerializer;
                } else if (Map.class.isAssignableFrom(cls2)) {
                    Type type4 = args[0];
                    Intrinsics.checkExpressionValueIsNotNull(type4, "args[0]");
                    KSerializer<Object> serializerByTypeToken3 = serializerByTypeToken(type4);
                    Type type5 = args[1];
                    Intrinsics.checkExpressionValueIsNotNull(type5, "args[1]");
                    KSerializer<Object> MapSerializer = CollectionSerializersKt.MapSerializer(serializerByTypeToken3, serializerByTypeToken(type5));
                    if (MapSerializer == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any>");
                    }
                    return MapSerializer;
                } else if (Map.Entry.class.isAssignableFrom(cls2)) {
                    Type type6 = args[0];
                    Intrinsics.checkExpressionValueIsNotNull(type6, "args[0]");
                    KSerializer<Object> serializerByTypeToken4 = serializerByTypeToken(type6);
                    Type type7 = args[1];
                    Intrinsics.checkExpressionValueIsNotNull(type7, "args[1]");
                    KSerializer<Object> MapEntrySerializer = BuiltinSerializersKt.MapEntrySerializer(serializerByTypeToken4, serializerByTypeToken(type7));
                    if (MapEntrySerializer == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any>");
                    }
                    return MapEntrySerializer;
                } else {
                    Intrinsics.checkExpressionValueIsNotNull(args, "args");
                    ArrayList arrayList = new ArrayList(args.length);
                    for (Type it2 : args) {
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        KSerializer<Object> serializerByTypeToken5 = serializerByTypeToken(it2);
                        if (serializerByTypeToken5 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any?>");
                        }
                        arrayList.add(serializerByTypeToken5);
                    }
                    Object[] array = arrayList.toArray(new KSerializer[0]);
                    if (array != null) {
                        KSerializer[] kSerializerArr = (KSerializer[]) array;
                        KSerializer<Object> constructSerializerForGivenTypeArgs = SerializationKt.constructSerializerForGivenTypeArgs(JvmClassMappingKt.getKotlinClass(cls2), (KSerializer[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
                        if (!(constructSerializerForGivenTypeArgs instanceof KSerializer)) {
                            constructSerializerForGivenTypeArgs = null;
                        }
                        if (constructSerializerForGivenTypeArgs != null) {
                            return constructSerializerForGivenTypeArgs;
                        }
                        KClass kotlinClass3 = JvmClassMappingKt.getKotlinClass(cls2);
                        if (kotlinClass3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                        }
                        KSerializer<Object> serializer2 = PlatformUtilsKt.serializer(kotlinClass3);
                        if (serializer2 == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        return serializer2;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<*>");
        } else if (!(type instanceof WildcardType)) {
            throw new IllegalArgumentException("typeToken should be an instance of Class<?>, GenericArray, ParametrizedType or WildcardType, but actual type is " + type + Chars.SPACE + Reflection.getOrCreateKotlinClass(type.getClass()));
        } else {
            Type[] upperBounds2 = ((WildcardType) type).getUpperBounds();
            Intrinsics.checkExpressionValueIsNotNull(upperBounds2, "type.upperBounds");
            Object first = ArraysKt.first(upperBounds2);
            Intrinsics.checkExpressionValueIsNotNull(first, "type.upperBounds.first()");
            return serializerByTypeToken((Type) first);
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Consider using Kotlin type token instead", replaceWith = @ReplaceWith(expression = "typeOf()", imports = {}))
    @NotNull
    public static final /* synthetic */ <T> Type typeTokenOf() {
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: kotlinx.serialization.JvmResolvingKt$typeTokenOf$base$1
        };
        Type genericSuperclass = JvmResolvingKt$typeTokenOf$base$1.class.getGenericSuperclass();
        if (genericSuperclass == null) {
            Intrinsics.throwNpe();
        }
        if (genericSuperclass != null) {
            Object outline23 = GeneratedOutlineSupport1.outline23((ParameterizedType) genericSuperclass, "(superType as Parameteri…Type).actualTypeArguments");
            if (outline23 == null) {
                Intrinsics.throwNpe();
            }
            return (Type) outline23;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.reflect.ParameterizedType");
    }
}
