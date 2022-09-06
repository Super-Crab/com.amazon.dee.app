package io.ktor.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.auth.BuildConfig;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AttributesJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J3\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eR&\u0010\u0003\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lio/ktor/util/ConcurrentSafeAttributes;", "Lio/ktor/util/AttributesJvmBase;", "()V", BuildConfig.FLAVOR_authtype, "Ljava/util/concurrent/ConcurrentHashMap;", "Lio/ktor/util/AttributeKey;", "", "getMap", "()Ljava/util/concurrent/ConcurrentHashMap;", "computeIfAbsent", ExifInterface.GPS_DIRECTION_TRUE, "key", "block", "Lkotlin/Function0;", "(Lio/ktor/util/AttributeKey;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class ConcurrentSafeAttributes extends AttributesJvmBase {
    @NotNull
    private final ConcurrentHashMap<AttributeKey<?>, Object> map = new ConcurrentHashMap<>();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.ktor.util.Attributes
    @NotNull
    public <T> T computeIfAbsent(@NotNull AttributeKey<T> key, @NotNull Function0<? extends T> block) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(block, "block");
        T t = (T) mo10323getMap().get(key);
        if (t != null) {
            return t;
        }
        T mo12560invoke = block.mo12560invoke();
        T t2 = (T) mo10323getMap().putIfAbsent(key, mo12560invoke);
        if (t2 == null) {
            t2 = mo12560invoke;
        }
        if (t2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        return t2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.ktor.util.AttributesJvmBase
    @NotNull
    /* renamed from: getMap  reason: collision with other method in class */
    public ConcurrentHashMap<AttributeKey<?>, Object> mo10323getMap() {
        return this.map;
    }
}
