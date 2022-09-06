package kotlin.reflect.full;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KClasses.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\r\u001a+\u0010S\u001a\u0002H\u001d\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00022\b\u0010T\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0002\u0010U\u001a!\u0010V\u001a\u0002H\u001d\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u0002H\u0007¢\u0006\u0002\u0010\u0013\u001a\u001c\u0010W\u001a\u000203*\u0006\u0012\u0002\b\u00030\u00022\n\u0010X\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0007\u001a\u001c\u0010Y\u001a\u000203*\u0006\u0012\u0002\b\u00030\u00022\n\u0010Z\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0007\u001a-\u0010[\u001a\u0004\u0018\u0001H\u001d\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00022\b\u0010T\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0002\u0010U\",\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"(\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006\"(\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u000e\"$\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0013\",\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0004\u001a\u0004\b\u0017\u0010\u0006\",\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u0006\"B\u0010\u001b\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001c0\u0001\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\u0004\u001a\u0004\b\u001f\u0010\u0006\",\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\u0004\u001a\u0004\b\"\u0010\u0006\">\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u00030$0\u0001\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b%\u0010\u0004\u001a\u0004\b&\u0010\u0006\",\u0010'\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030(0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b)\u0010\u0004\u001a\u0004\b*\u0010\u0006\"\"\u0010+\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b,\u0010\u0004\u001a\u0004\b-\u0010.\",\u0010/\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b0\u0010\u0004\u001a\u0004\b1\u0010\u0006\"\u001c\u00102\u001a\u000203*\u0006\u0012\u0002\b\u0003048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00105\"\u001c\u00106\u001a\u000203*\u0006\u0012\u0002\b\u0003048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b6\u00105\",\u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b8\u0010\u0004\u001a\u0004\b9\u0010\u0006\"B\u0010:\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001c0\u0001\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b;\u0010\u0004\u001a\u0004\b<\u0010\u0006\",\u0010=\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b>\u0010\u0004\u001a\u0004\b?\u0010\u0006\">\u0010@\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002H\u001d\u0012\u0002\b\u00030$0\u0001\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bA\u0010\u0004\u001a\u0004\bB\u0010\u0006\"6\u0010C\u001a\n\u0012\u0004\u0012\u0002H\u001d\u0018\u00010\u0015\"\b\b\u0000\u0010\u001d*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001d0\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bD\u0010\u0004\u001a\u0004\bE\u0010F\",\u0010G\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bH\u0010\u0004\u001a\u0004\bI\u0010\u0006\",\u0010J\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030K0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bL\u0010\u0004\u001a\u0004\bM\u0010\u0006\",\u0010N\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020O*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\bP\u0010\u0004\u001a\u0004\bQ\u0010R¨\u0006\\"}, d2 = {"allSuperclasses", "", "Lkotlin/reflect/KClass;", "allSuperclasses$annotations", "(Lkotlin/reflect/KClass;)V", "getAllSuperclasses", "(Lkotlin/reflect/KClass;)Ljava/util/Collection;", "allSupertypes", "Lkotlin/reflect/KType;", "allSupertypes$annotations", "getAllSupertypes", "companionObject", "companionObject$annotations", "getCompanionObject", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KClass;", "companionObjectInstance", "", "companionObjectInstance$annotations", "getCompanionObjectInstance", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "declaredFunctions", "Lkotlin/reflect/KFunction;", "declaredFunctions$annotations", "getDeclaredFunctions", "declaredMemberExtensionFunctions", "declaredMemberExtensionFunctions$annotations", "getDeclaredMemberExtensionFunctions", "declaredMemberExtensionProperties", "Lkotlin/reflect/KProperty2;", ExifInterface.GPS_DIRECTION_TRUE, "declaredMemberExtensionProperties$annotations", "getDeclaredMemberExtensionProperties", "declaredMemberFunctions", "declaredMemberFunctions$annotations", "getDeclaredMemberFunctions", "declaredMemberProperties", "Lkotlin/reflect/KProperty1;", "declaredMemberProperties$annotations", "getDeclaredMemberProperties", "declaredMembers", "Lkotlin/reflect/KCallable;", "declaredMembers$annotations", "getDeclaredMembers", "defaultType", "defaultType$annotations", "getDefaultType", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KType;", "functions", "functions$annotations", "getFunctions", "isExtension", "", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "(Lkotlin/reflect/jvm/internal/KCallableImpl;)Z", "isNotExtension", "memberExtensionFunctions", "memberExtensionFunctions$annotations", "getMemberExtensionFunctions", "memberExtensionProperties", "memberExtensionProperties$annotations", "getMemberExtensionProperties", "memberFunctions", "memberFunctions$annotations", "getMemberFunctions", "memberProperties", "memberProperties$annotations", "getMemberProperties", "primaryConstructor", "primaryConstructor$annotations", "getPrimaryConstructor", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KFunction;", "staticFunctions", "staticFunctions$annotations", "getStaticFunctions", "staticProperties", "Lkotlin/reflect/KProperty0;", "staticProperties$annotations", "getStaticProperties", "superclasses", "", "superclasses$annotations", "getSuperclasses", "(Lkotlin/reflect/KClass;)Ljava/util/List;", "cast", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "createInstance", "isSubclassOf", TtmlNode.RUBY_BASE, "isSuperclassOf", "derived", "safeCast", "kotlin-reflection"}, k = 2, mv = {1, 1, 16})
@JvmName(name = "KClasses")
/* loaded from: classes2.dex */
public final class KClasses {
    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void allSuperclasses$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void allSupertypes$annotations(KClass kClass) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T> T cast(@NotNull KClass<T> cast, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(cast, "$this$cast");
        if (cast.isInstance(obj)) {
            if (obj == 0) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            return obj;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Value cannot be cast to ");
        outline107.append(cast.getQualifiedName());
        throw new TypeCastException(outline107.toString());
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void companionObject$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void companionObjectInstance$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T> T createInstance(@NotNull KClass<T> createInstance) {
        Map<KParameter, ? extends Object> emptyMap;
        boolean z;
        Intrinsics.checkParameterIsNotNull(createInstance, "$this$createInstance");
        Iterator<T> it2 = createInstance.getConstructors().iterator();
        KFunction kFunction = null;
        boolean z2 = false;
        KFunction kFunction2 = null;
        while (true) {
            if (it2.hasNext()) {
                T next = it2.next();
                List<KParameter> parameters = ((KFunction) next).getParameters();
                if (!(parameters instanceof Collection) || !parameters.isEmpty()) {
                    for (KParameter kParameter : parameters) {
                        if (!kParameter.isOptional()) {
                            z = false;
                            break;
                        }
                    }
                }
                z = true;
                if (z) {
                    if (z2) {
                        break;
                    }
                    kFunction2 = next;
                    z2 = true;
                }
            } else if (z2) {
                kFunction = kFunction2;
            }
        }
        KFunction kFunction3 = kFunction;
        if (kFunction3 != null) {
            emptyMap = MapsKt__MapsKt.emptyMap();
            return (T) kFunction3.callBy(emptyMap);
        }
        throw new IllegalArgumentException("Class should have a single no-arg constructor: " + createInstance);
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void declaredFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void declaredMemberExtensionFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void declaredMemberExtensionProperties$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void declaredMemberFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void declaredMemberProperties$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void declaredMembers$annotations(KClass kClass) {
    }

    @Deprecated(message = "This function creates a type which rarely makes sense for generic classes. For example, such type can only be used in signatures of members of that class. Use starProjectedType or createType() for clearer semantics.")
    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void defaultType$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void functions$annotations(KClass kClass) {
    }

    @NotNull
    public static final Collection<KClass<?>> getAllSuperclasses(@NotNull KClass<?> allSuperclasses) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(allSuperclasses, "$this$allSuperclasses");
        Collection<KType> allSupertypes = getAllSupertypes(allSuperclasses);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(allSupertypes, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (KType kType : allSupertypes) {
            KClassifier classifier = kType.getClassifier();
            if (!(classifier instanceof KClass)) {
                classifier = null;
            }
            KClass kClass = (KClass) classifier;
            if (kClass == null) {
                throw new KotlinReflectionInternalError("Supertype not a class: " + kType);
            }
            arrayList.add(kClass);
        }
        return arrayList;
    }

    @NotNull
    public static final Collection<KType> getAllSupertypes(@NotNull KClass<?> allSupertypes) {
        Intrinsics.checkParameterIsNotNull(allSupertypes, "$this$allSupertypes");
        Object dfs = DFS.dfs(allSupertypes.getSupertypes(), KClasses$allSupertypes$1.INSTANCE, new DFS.VisitedWithSet(), new DFS.NodeHandlerWithListResult<KType, KType>() { // from class: kotlin.reflect.full.KClasses$allSupertypes$2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(@NotNull KType current) {
                Intrinsics.checkParameterIsNotNull(current, "current");
                ((LinkedList) this.result).add(current);
                return true;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(dfs, "DFS.dfs(\n            sup…    }\n            }\n    )");
        return (Collection) dfs;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
        return (kotlin.reflect.KClass) r0;
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.reflect.KClass<?> getCompanionObject(@org.jetbrains.annotations.NotNull kotlin.reflect.KClass<?> r2) {
        /*
            java.lang.String r0 = "$this$companionObject"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.util.Collection r2 = r2.getNestedClasses()
            java.util.Iterator r2 = r2.iterator()
        Ld:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L31
            java.lang.Object r0 = r2.next()
            r1 = r0
            kotlin.reflect.KClass r1 = (kotlin.reflect.KClass) r1
            if (r1 == 0) goto L29
            kotlin.reflect.jvm.internal.KClassImpl r1 = (kotlin.reflect.jvm.internal.KClassImpl) r1
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r1 = r1.mo11491getDescriptor()
            boolean r1 = r1.isCompanionObject()
            if (r1 == 0) goto Ld
            goto L32
        L29:
            kotlin.TypeCastException r2 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>"
            r2.<init>(r0)
            throw r2
        L31:
            r0 = 0
        L32:
            kotlin.reflect.KClass r0 = (kotlin.reflect.KClass) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.full.KClasses.getCompanionObject(kotlin.reflect.KClass):kotlin.reflect.KClass");
    }

    @Nullable
    public static final Object getCompanionObjectInstance(@NotNull KClass<?> companionObjectInstance) {
        Intrinsics.checkParameterIsNotNull(companionObjectInstance, "$this$companionObjectInstance");
        KClass<?> companionObject = getCompanionObject(companionObjectInstance);
        if (companionObject != null) {
            return companionObject.getObjectInstance();
        }
        return null;
    }

    @NotNull
    public static final Collection<KFunction<?>> getDeclaredFunctions(@NotNull KClass<?> declaredFunctions) {
        Intrinsics.checkParameterIsNotNull(declaredFunctions, "$this$declaredFunctions");
        Collection<KCallableImpl<?>> declaredMembers = ((KClassImpl.Data) ((KClassImpl) declaredFunctions).getData().invoke()).getDeclaredMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declaredMembers) {
            if (obj instanceof KFunction) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final Collection<KFunction<?>> getDeclaredMemberExtensionFunctions(@NotNull KClass<?> declaredMemberExtensionFunctions) {
        Intrinsics.checkParameterIsNotNull(declaredMemberExtensionFunctions, "$this$declaredMemberExtensionFunctions");
        Collection<KCallableImpl<?>> declaredNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) declaredMemberExtensionFunctions).getData().invoke()).getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declaredNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) obj;
            if (isExtension(kCallableImpl) && (kCallableImpl instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> Collection<KProperty2<T, ?, ?>> getDeclaredMemberExtensionProperties(@NotNull KClass<T> declaredMemberExtensionProperties) {
        Intrinsics.checkParameterIsNotNull(declaredMemberExtensionProperties, "$this$declaredMemberExtensionProperties");
        Collection<KCallableImpl<?>> declaredNonStaticMembers = ((KClassImpl) declaredMemberExtensionProperties).getData().invoke().getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : declaredNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) t;
            if (isExtension(kCallableImpl) && (kCallableImpl instanceof KProperty2)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final Collection<KFunction<?>> getDeclaredMemberFunctions(@NotNull KClass<?> declaredMemberFunctions) {
        Intrinsics.checkParameterIsNotNull(declaredMemberFunctions, "$this$declaredMemberFunctions");
        Collection<KCallableImpl<?>> declaredNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) declaredMemberFunctions).getData().invoke()).getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declaredNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) obj;
            if (isNotExtension(kCallableImpl) && (kCallableImpl instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> Collection<KProperty1<T, ?>> getDeclaredMemberProperties(@NotNull KClass<T> declaredMemberProperties) {
        Intrinsics.checkParameterIsNotNull(declaredMemberProperties, "$this$declaredMemberProperties");
        Collection<KCallableImpl<?>> declaredNonStaticMembers = ((KClassImpl) declaredMemberProperties).getData().invoke().getDeclaredNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : declaredNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) t;
            if (isNotExtension(kCallableImpl) && (kCallableImpl instanceof KProperty1)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final Collection<KCallable<?>> getDeclaredMembers(@NotNull KClass<?> declaredMembers) {
        Intrinsics.checkParameterIsNotNull(declaredMembers, "$this$declaredMembers");
        return ((KClassImpl.Data) ((KClassImpl) declaredMembers).getData().invoke()).getDeclaredMembers();
    }

    @NotNull
    public static final KType getDefaultType(@NotNull KClass<?> defaultType) {
        Intrinsics.checkParameterIsNotNull(defaultType, "$this$defaultType");
        SimpleType defaultType2 = ((KClassImpl) defaultType).mo11491getDescriptor().getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType2, "(this as KClassImpl<*>).descriptor.defaultType");
        return new KTypeImpl(defaultType2, new KClasses$defaultType$1(defaultType));
    }

    @NotNull
    public static final Collection<KFunction<?>> getFunctions(@NotNull KClass<?> functions) {
        Intrinsics.checkParameterIsNotNull(functions, "$this$functions");
        Collection<KCallable<?>> members = functions.getMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : members) {
            if (obj instanceof KFunction) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final Collection<KFunction<?>> getMemberExtensionFunctions(@NotNull KClass<?> memberExtensionFunctions) {
        Intrinsics.checkParameterIsNotNull(memberExtensionFunctions, "$this$memberExtensionFunctions");
        Collection<KCallableImpl<?>> allNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) memberExtensionFunctions).getData().invoke()).getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) obj;
            if (isExtension(kCallableImpl) && (kCallableImpl instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> Collection<KProperty2<T, ?, ?>> getMemberExtensionProperties(@NotNull KClass<T> memberExtensionProperties) {
        Intrinsics.checkParameterIsNotNull(memberExtensionProperties, "$this$memberExtensionProperties");
        Collection<KCallableImpl<?>> allNonStaticMembers = ((KClassImpl) memberExtensionProperties).getData().invoke().getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : allNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) t;
            if (isExtension(kCallableImpl) && (kCallableImpl instanceof KProperty2)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final Collection<KFunction<?>> getMemberFunctions(@NotNull KClass<?> memberFunctions) {
        Intrinsics.checkParameterIsNotNull(memberFunctions, "$this$memberFunctions");
        Collection<KCallableImpl<?>> allNonStaticMembers = ((KClassImpl.Data) ((KClassImpl) memberFunctions).getData().invoke()).getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) obj;
            if (isNotExtension(kCallableImpl) && (kCallableImpl instanceof KFunction)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> Collection<KProperty1<T, ?>> getMemberProperties(@NotNull KClass<T> memberProperties) {
        Intrinsics.checkParameterIsNotNull(memberProperties, "$this$memberProperties");
        Collection<KCallableImpl<?>> allNonStaticMembers = ((KClassImpl) memberProperties).getData().invoke().getAllNonStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (T t : allNonStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) t;
            if (isNotExtension(kCallableImpl) && (kCallableImpl instanceof KProperty1)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
        return (kotlin.reflect.KFunction) r0;
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> kotlin.reflect.KFunction<T> getPrimaryConstructor(@org.jetbrains.annotations.NotNull kotlin.reflect.KClass<T> r2) {
        /*
            java.lang.String r0 = "$this$primaryConstructor"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            kotlin.reflect.jvm.internal.KClassImpl r2 = (kotlin.reflect.jvm.internal.KClassImpl) r2
            java.util.Collection r2 = r2.getConstructors()
            java.util.Iterator r2 = r2.iterator()
        Lf:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L3f
            java.lang.Object r0 = r2.next()
            r1 = r0
            kotlin.reflect.KFunction r1 = (kotlin.reflect.KFunction) r1
            if (r1 == 0) goto L37
            kotlin.reflect.jvm.internal.KFunctionImpl r1 = (kotlin.reflect.jvm.internal.KFunctionImpl) r1
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r1 = r1.mo11480getDescriptor()
            if (r1 == 0) goto L2f
            kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor) r1
            boolean r1 = r1.isPrimary()
            if (r1 == 0) goto Lf
            goto L40
        L2f:
            kotlin.TypeCastException r2 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ConstructorDescriptor"
            r2.<init>(r0)
            throw r2
        L37:
            kotlin.TypeCastException r2 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KFunctionImpl"
            r2.<init>(r0)
            throw r2
        L3f:
            r0 = 0
        L40:
            kotlin.reflect.KFunction r0 = (kotlin.reflect.KFunction) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.full.KClasses.getPrimaryConstructor(kotlin.reflect.KClass):kotlin.reflect.KFunction");
    }

    @NotNull
    public static final Collection<KFunction<?>> getStaticFunctions(@NotNull KClass<?> staticFunctions) {
        Intrinsics.checkParameterIsNotNull(staticFunctions, "$this$staticFunctions");
        Collection<KCallableImpl<?>> allStaticMembers = ((KClassImpl.Data) ((KClassImpl) staticFunctions).getData().invoke()).getAllStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allStaticMembers) {
            if (obj instanceof KFunction) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final Collection<KProperty0<?>> getStaticProperties(@NotNull KClass<?> staticProperties) {
        Intrinsics.checkParameterIsNotNull(staticProperties, "$this$staticProperties");
        Collection<KCallableImpl<?>> allStaticMembers = ((KClassImpl.Data) ((KClassImpl) staticProperties).getData().invoke()).getAllStaticMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allStaticMembers) {
            KCallableImpl kCallableImpl = (KCallableImpl) obj;
            if (isNotExtension(kCallableImpl) && (kCallableImpl instanceof KProperty0)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<KClass<?>> getSuperclasses(@NotNull KClass<?> superclasses) {
        Intrinsics.checkParameterIsNotNull(superclasses, "$this$superclasses");
        List<KType> supertypes = superclasses.getSupertypes();
        ArrayList arrayList = new ArrayList();
        for (KType kType : supertypes) {
            KClassifier classifier = kType.getClassifier();
            if (!(classifier instanceof KClass)) {
                classifier = null;
            }
            KClass kClass = (KClass) classifier;
            if (kClass != null) {
                arrayList.add(kClass);
            }
        }
        return arrayList;
    }

    private static final boolean isExtension(@NotNull KCallableImpl<?> kCallableImpl) {
        return kCallableImpl.mo11480getDescriptor().getExtensionReceiverParameter() != null;
    }

    private static final boolean isNotExtension(@NotNull KCallableImpl<?> kCallableImpl) {
        return !isExtension(kCallableImpl);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.reflect.full.KClasses$sam$org_jetbrains_kotlin_utils_DFS_Neighbors$0] */
    @SinceKotlin(version = "1.1")
    public static final boolean isSubclassOf(@NotNull KClass<?> isSubclassOf, @NotNull KClass<?> base) {
        List listOf;
        Intrinsics.checkParameterIsNotNull(isSubclassOf, "$this$isSubclassOf");
        Intrinsics.checkParameterIsNotNull(base, "base");
        if (!Intrinsics.areEqual(isSubclassOf, base)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(isSubclassOf);
            final KProperty1 kProperty1 = KClasses$isSubclassOf$1.INSTANCE;
            if (kProperty1 != null) {
                kProperty1 = new DFS.Neighbors() { // from class: kotlin.reflect.full.KClasses$sam$org_jetbrains_kotlin_utils_DFS_Neighbors$0
                    @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
                    @NotNull
                    public final /* synthetic */ Iterable getNeighbors(Object obj) {
                        return (Iterable) Function1.this.mo12165invoke(obj);
                    }
                };
            }
            Boolean ifAny = DFS.ifAny(listOf, (DFS.Neighbors) kProperty1, new KClasses$isSubclassOf$2(base));
            Intrinsics.checkExpressionValueIsNotNull(ifAny, "DFS.ifAny(listOf(this), …erclasses) { it == base }");
            if (!ifAny.booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.1")
    public static final boolean isSuperclassOf(@NotNull KClass<?> isSuperclassOf, @NotNull KClass<?> derived) {
        Intrinsics.checkParameterIsNotNull(isSuperclassOf, "$this$isSuperclassOf");
        Intrinsics.checkParameterIsNotNull(derived, "derived");
        return isSubclassOf(derived, isSuperclassOf);
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void memberExtensionFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void memberExtensionProperties$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void memberFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void memberProperties$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void primaryConstructor$annotations(KClass kClass) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.1")
    @Nullable
    public static final <T> T safeCast(@NotNull KClass<T> safeCast, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(safeCast, "$this$safeCast");
        if (safeCast.isInstance(obj)) {
            if (obj == 0) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            return obj;
        }
        return null;
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void staticFunctions$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void staticProperties$annotations(KClass kClass) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void superclasses$annotations(KClass kClass) {
    }
}
