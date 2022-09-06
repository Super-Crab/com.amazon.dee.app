package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: CollectionSerializers.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "Use SetSerializer() instead", replaceWith = @ReplaceWith(expression = "SetSerializer(eSerializer)", imports = {"kotlinx.serialization.builtins.SetSerializer"}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\b\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012*\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u0002H\u00010\u0004j\b\u0012\u0004\u0012\u0002H\u0001`\u00050\u0002B\u0013\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005H\u0014J\u001c\u0010\u000e\u001a\u00020\u000f*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005H\u0014J$\u0010\u0010\u001a\u00020\u0011*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u00052\u0006\u0010\u0012\u001a\u00020\u000fH\u0014J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014*\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0014J\u0012\u0010\u0015\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0014J1\u0010\u0016\u001a\u00020\u0011*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u00052\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0019J\"\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005*\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0014J\"\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005H\u0014R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001c"}, d2 = {"Lkotlinx/serialization/internal/HashSetSerializer;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/serialization/internal/ListLikeSerializer;", "", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "eSerializer", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "builder", "builderSize", "", "checkCapacity", "", "size", "collectionIterator", "", "collectionSize", "insert", "index", "element", "(Ljava/util/HashSet;ILjava/lang/Object;)V", "toBuilder", "toResult", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public final class HashSetSerializer<E> extends ListLikeSerializer<E, Set<? extends E>, HashSet<E>> {
    @NotNull
    private final SerialDescriptor descriptor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashSetSerializer(@NotNull KSerializer<E> eSerializer) {
        super(eSerializer, null);
        Intrinsics.checkParameterIsNotNull(eSerializer, "eSerializer");
        this.descriptor = new HashSetClassDesc(eSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ int builderSize(Object obj) {
        return builderSize((HashSet) ((HashSet) obj));
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ void checkCapacity(Object obj, int i) {
        checkCapacity((HashSet) ((HashSet) obj), i);
    }

    protected void checkCapacity(@NotNull HashSet<E> checkCapacity, int i) {
        Intrinsics.checkParameterIsNotNull(checkCapacity, "$this$checkCapacity");
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ Iterator collectionIterator(Object obj) {
        return collectionIterator((Set) ((Set) obj));
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ int collectionSize(Object obj) {
        return collectionSize((Set) ((Set) obj));
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.ListLikeSerializer
    public /* bridge */ /* synthetic */ void insert(Object obj, int i, Object obj2) {
        insert((HashSet<int>) obj, i, (int) obj2);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ Object toBuilder(Object obj) {
        return toBuilder((Set) ((Set) obj));
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ Object toResult(Object obj) {
        return toResult((HashSet) ((HashSet) obj));
    }

    @NotNull
    protected Set<E> toResult(@NotNull HashSet<E> toResult) {
        Intrinsics.checkParameterIsNotNull(toResult, "$this$toResult");
        return toResult;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    /* renamed from: builder  reason: collision with other method in class */
    public HashSet<E> mo12398builder() {
        return new HashSet<>();
    }

    protected int builderSize(@NotNull HashSet<E> builderSize) {
        Intrinsics.checkParameterIsNotNull(builderSize, "$this$builderSize");
        return builderSize.size();
    }

    @NotNull
    protected Iterator<E> collectionIterator(@NotNull Set<? extends E> collectionIterator) {
        Intrinsics.checkParameterIsNotNull(collectionIterator, "$this$collectionIterator");
        return (Iterator<? extends E>) collectionIterator.iterator();
    }

    protected int collectionSize(@NotNull Set<? extends E> collectionSize) {
        Intrinsics.checkParameterIsNotNull(collectionSize, "$this$collectionSize");
        return collectionSize.size();
    }

    public void insert(@NotNull HashSet<E> insert, int i, E e) {
        Intrinsics.checkParameterIsNotNull(insert, "$this$insert");
        insert.add(e);
    }

    @NotNull
    protected HashSet<E> toBuilder(@NotNull Set<? extends E> toBuilder) {
        Intrinsics.checkParameterIsNotNull(toBuilder, "$this$toBuilder");
        HashSet<E> hashSet = (HashSet) (!(toBuilder instanceof HashSet) ? null : toBuilder);
        return hashSet != null ? hashSet : new HashSet<>(toBuilder);
    }
}
