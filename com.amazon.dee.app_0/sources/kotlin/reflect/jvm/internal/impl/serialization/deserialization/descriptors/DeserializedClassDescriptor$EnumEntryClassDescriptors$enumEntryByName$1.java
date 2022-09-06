package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeserializedClassDescriptor.kt */
/* loaded from: classes4.dex */
public final class DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1 extends Lambda implements Function1<Name, EnumEntrySyntheticClassDescriptor> {
    final /* synthetic */ DeserializedClassDescriptor.EnumEntryClassDescriptors this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1(DeserializedClassDescriptor.EnumEntryClassDescriptors enumEntryClassDescriptors) {
        super(1);
        this.this$0 = enumEntryClassDescriptors;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final EnumEntrySyntheticClassDescriptor mo12165invoke(@NotNull Name name) {
        Map map;
        NotNullLazyValue notNullLazyValue;
        Intrinsics.checkParameterIsNotNull(name, "name");
        map = this.this$0.enumEntryProtos;
        ProtoBuf.EnumEntry enumEntry = (ProtoBuf.EnumEntry) map.get(name);
        if (enumEntry != null) {
            StorageManager storageManager = DeserializedClassDescriptor.this.getC().getStorageManager();
            DeserializedClassDescriptor.EnumEntryClassDescriptors enumEntryClassDescriptors = this.this$0;
            DeserializedClassDescriptor deserializedClassDescriptor = DeserializedClassDescriptor.this;
            notNullLazyValue = enumEntryClassDescriptors.enumMemberNames;
            return EnumEntrySyntheticClassDescriptor.create(storageManager, deserializedClassDescriptor, name, notNullLazyValue, new DeserializedAnnotations(DeserializedClassDescriptor.this.getC().getStorageManager(), new DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1$$special$$inlined$let$lambda$1(enumEntry, this, name)), SourceElement.NO_SOURCE);
        }
        return null;
    }
}
