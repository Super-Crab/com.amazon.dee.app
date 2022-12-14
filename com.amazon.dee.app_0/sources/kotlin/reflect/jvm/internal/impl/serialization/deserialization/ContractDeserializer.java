package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ContractDeserializer.kt */
/* loaded from: classes4.dex */
public interface ContractDeserializer {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: ContractDeserializer.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        private static final ContractDeserializer DEFAULT = new ContractDeserializer() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer$Companion$DEFAULT$1
            @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer
            @Nullable
            public Pair deserializeContractFromFunction(@NotNull ProtoBuf.Function proto, @NotNull FunctionDescriptor ownerFunction, @NotNull TypeTable typeTable, @NotNull TypeDeserializer typeDeserializer) {
                Intrinsics.checkParameterIsNotNull(proto, "proto");
                Intrinsics.checkParameterIsNotNull(ownerFunction, "ownerFunction");
                Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
                Intrinsics.checkParameterIsNotNull(typeDeserializer, "typeDeserializer");
                return null;
            }
        };

        private Companion() {
        }

        @NotNull
        public final ContractDeserializer getDEFAULT() {
            return DEFAULT;
        }
    }

    @Nullable
    Pair<CallableDescriptor.UserDataKey<?>, Object> deserializeContractFromFunction(@NotNull ProtoBuf.Function function, @NotNull FunctionDescriptor functionDescriptor, @NotNull TypeTable typeTable, @NotNull TypeDeserializer typeDeserializer);
}
