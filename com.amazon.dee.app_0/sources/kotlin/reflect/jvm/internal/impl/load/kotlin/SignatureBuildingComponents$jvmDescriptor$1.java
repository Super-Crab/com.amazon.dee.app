package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: methodSignatureBuilding.kt */
/* loaded from: classes3.dex */
public final class SignatureBuildingComponents$jvmDescriptor$1 extends Lambda implements Function1<String, String> {
    public static final SignatureBuildingComponents$jvmDescriptor$1 INSTANCE = new SignatureBuildingComponents$jvmDescriptor$1();

    SignatureBuildingComponents$jvmDescriptor$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12165invoke(@NotNull String it2) {
        String escapeClassName;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        escapeClassName = SignatureBuildingComponents.INSTANCE.escapeClassName(it2);
        return escapeClassName;
    }
}
