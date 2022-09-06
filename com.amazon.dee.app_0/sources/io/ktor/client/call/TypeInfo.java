package io.ktor.client.call;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\r\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\r\u0010\r\u001a\u00060\u0005j\u0002`\u0006HÆ\u0003J%\u0010\u000e\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lio/ktor/client/call/TypeInfo;", "", "type", "Lkotlin/reflect/KClass;", "reifiedType", "Ljava/lang/reflect/Type;", "Lio/ktor/client/call/Type;", "(Lkotlin/reflect/KClass;Ljava/lang/reflect/Type;)V", "getReifiedType", "()Ljava/lang/reflect/Type;", "getType", "()Lkotlin/reflect/KClass;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class TypeInfo {
    @NotNull
    private final Type reifiedType;
    @NotNull
    private final KClass<?> type;

    public TypeInfo(@NotNull KClass<?> type, @NotNull Type reifiedType) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(reifiedType, "reifiedType");
        this.type = type;
        this.reifiedType = reifiedType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ TypeInfo copy$default(TypeInfo typeInfo, KClass kClass, Type type, int i, Object obj) {
        if ((i & 1) != 0) {
            kClass = typeInfo.type;
        }
        if ((i & 2) != 0) {
            type = typeInfo.reifiedType;
        }
        return typeInfo.copy(kClass, type);
    }

    @NotNull
    public final KClass<?> component1() {
        return this.type;
    }

    @NotNull
    public final Type component2() {
        return this.reifiedType;
    }

    @NotNull
    public final TypeInfo copy(@NotNull KClass<?> type, @NotNull Type reifiedType) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(reifiedType, "reifiedType");
        return new TypeInfo(type, reifiedType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof TypeInfo)) {
                return false;
            }
            TypeInfo typeInfo = (TypeInfo) obj;
            return Intrinsics.areEqual(this.type, typeInfo.type) && Intrinsics.areEqual(this.reifiedType, typeInfo.reifiedType);
        }
        return true;
    }

    @NotNull
    public final Type getReifiedType() {
        return this.reifiedType;
    }

    @NotNull
    public final KClass<?> getType() {
        return this.type;
    }

    public int hashCode() {
        KClass<?> kClass = this.type;
        int i = 0;
        int hashCode = (kClass != null ? kClass.hashCode() : 0) * 31;
        Type type = this.reifiedType;
        if (type != null) {
            i = type.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TypeInfo(type=");
        outline107.append(this.type);
        outline107.append(", reifiedType=");
        outline107.append(this.reifiedType);
        outline107.append(")");
        return outline107.toString();
    }
}
