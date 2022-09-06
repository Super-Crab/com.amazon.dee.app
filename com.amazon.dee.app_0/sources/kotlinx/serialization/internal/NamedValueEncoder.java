package kotlinx.serialization.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: Tagged.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b'\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0018\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0002H\u0004J\u0014\u0010\u0011\u001a\u00020\u0002*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0004R\u0011\u0010\u0003\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lkotlinx/serialization/internal/NamedValueEncoder;", "Lkotlinx/serialization/internal/TaggedEncoder;", "", "rootName", "(Ljava/lang/String;)V", "getRootName", "()Ljava/lang/String;", "composeName", "parentName", "childName", "elementName", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "index", "", "nested", "nestedName", "getTag", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public abstract class NamedValueEncoder extends TaggedEncoder<String> {
    @NotNull
    private final String rootName;

    public NamedValueEncoder() {
        this(null, 1, null);
    }

    public NamedValueEncoder(@NotNull String rootName) {
        Intrinsics.checkParameterIsNotNull(rootName, "rootName");
        this.rootName = rootName;
    }

    @NotNull
    public String composeName(@NotNull String parentName, @NotNull String childName) {
        Intrinsics.checkParameterIsNotNull(parentName, "parentName");
        Intrinsics.checkParameterIsNotNull(childName, "childName");
        return parentName.length() == 0 ? childName : GeneratedOutlineSupport1.outline48(parentName, '.', childName);
    }

    @NotNull
    public String elementName(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return descriptor.getElementName(i);
    }

    @NotNull
    public final String getRootName() {
        return this.rootName;
    }

    @NotNull
    protected final String nested(@NotNull String nestedName) {
        Intrinsics.checkParameterIsNotNull(nestedName, "nestedName");
        String currentTagOrNull = getCurrentTagOrNull();
        if (currentTagOrNull == null) {
            currentTagOrNull = this.rootName;
        }
        return composeName(currentTagOrNull, nestedName);
    }

    public /* synthetic */ NamedValueEncoder(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    @NotNull
    public final String getTag(@NotNull SerialDescriptor getTag, int i) {
        Intrinsics.checkParameterIsNotNull(getTag, "$this$getTag");
        return nested(elementName(getTag, i));
    }
}
