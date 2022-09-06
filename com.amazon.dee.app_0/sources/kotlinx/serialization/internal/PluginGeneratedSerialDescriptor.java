package kotlinx.serialization.internal;

import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorKt;
import kotlinx.serialization.SerialKind;
import kotlinx.serialization.StructureKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PluginGeneratedSerialDescriptor.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "Should not be used in general code")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\r\b\u0017\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020.J\u0014\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0016H\u0002J\u0013\u00100\u001a\u00020.2\b\u00101\u001a\u0004\u0018\u000102H\u0096\u0002J\u0016\u00103\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u00104\u001a\u00020\u0007H\u0016J\u0010\u00105\u001a\u00020\u00012\u0006\u00104\u001a\u00020\u0007H\u0016J\u0010\u00106\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0003H\u0016J\u0010\u00107\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u0007H\u0016J\b\u00108\u001a\u00020\u0007H\u0016J\u0010\u00109\u001a\u00020.2\u0006\u00104\u001a\u00020\u0007H\u0016J\u000e\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020\fJ\u000e\u0010<\u001a\u00020+2\u0006\u0010=\u001a\u00020\fJ\b\u0010>\u001a\u00020\u0003H\u0016R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030#8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001e\u0010&\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00100 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010'R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)¨\u0006?"}, d2 = {"Lkotlinx/serialization/internal/PluginGeneratedSerialDescriptor;", "Lkotlinx/serialization/SerialDescriptor;", "serialName", "", "generatedSerializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "elementsCount", "", "(Ljava/lang/String;Lkotlinx/serialization/internal/GeneratedSerializer;I)V", "added", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "classAnnotations", "", "getElementsCount", "()I", "flags", "", "indices", "", "getIndices", "()Ljava/util/Map;", "indices$delegate", "Lkotlin/Lazy;", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/SerialKind;", "getKind", "()Lkotlinx/serialization/SerialKind;", "names", "", "[Ljava/lang/String;", "namesSet", "", "getNamesSet$kotlinx_serialization_runtime", "()Ljava/util/Set;", "propertiesAnnotations", "[Ljava/util/List;", "getSerialName", "()Ljava/lang/String;", "addElement", "", "name", "isOptional", "", "buildIndices", "equals", "other", "", "getElementAnnotations", "index", "getElementDescriptor", "getElementIndex", "getElementName", "hashCode", "isElementOptional", "pushAnnotation", "annotation", "pushClassAnnotation", "a", "toString", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public class PluginGeneratedSerialDescriptor implements SerialDescriptor {
    private int added;
    private List<Annotation> classAnnotations;
    private final int elementsCount;
    private boolean[] flags;
    private final GeneratedSerializer<?> generatedSerializer;
    private final Lazy indices$delegate;
    private final String[] names;
    private final List<Annotation>[] propertiesAnnotations;
    @NotNull
    private final String serialName;

    public PluginGeneratedSerialDescriptor(@NotNull String serialName, @Nullable GeneratedSerializer<?> generatedSerializer, int i) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(serialName, "serialName");
        this.serialName = serialName;
        this.generatedSerializer = generatedSerializer;
        this.elementsCount = i;
        this.added = -1;
        int i2 = this.elementsCount;
        String[] strArr = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr[i3] = "[UNINITIALIZED]";
        }
        this.names = strArr;
        int i4 = this.elementsCount;
        this.propertiesAnnotations = new List[i4];
        this.flags = new boolean[i4];
        lazy = LazyKt__LazyJVMKt.lazy(new PluginGeneratedSerialDescriptor$indices$2(this));
        this.indices$delegate = lazy;
    }

    public static /* synthetic */ void addElement$default(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor, String str, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            pluginGeneratedSerialDescriptor.addElement(str, z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addElement");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, Integer> buildIndices() {
        HashMap hashMap = new HashMap();
        int length = this.names.length;
        for (int i = 0; i < length; i++) {
            hashMap.put(this.names[i], Integer.valueOf(i));
        }
        return hashMap;
    }

    private final Map<String, Integer> getIndices() {
        return (Map) this.indices$delegate.getValue();
    }

    public final void addElement(@NotNull String name, boolean z) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String[] strArr = this.names;
        this.added++;
        int i = this.added;
        strArr[i] = name;
        this.flags[i] = z;
        this.propertiesAnnotations[i] = null;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerialDescriptor)) {
            return false;
        }
        SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
        return !(Intrinsics.areEqual(getSerialName(), serialDescriptor.getSerialName()) ^ true) && !(Intrinsics.areEqual(SerialDescriptorKt.elementDescriptors(this), SerialDescriptorKt.elementDescriptors(serialDescriptor)) ^ true);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public List<Annotation> getAnnotations() {
        List<Annotation> emptyList;
        List<Annotation> list = this.classAnnotations;
        if (list != null) {
            return list;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public List<Annotation> getElementAnnotations(int i) {
        List<Annotation> emptyList;
        List<Annotation> list = this.propertiesAnnotations[i];
        if (list != null) {
            return list;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public SerialDescriptor getElementDescriptor(int i) {
        KSerializer<?>[] childSerializers;
        KSerializer<?> kSerializer;
        SerialDescriptor descriptor;
        GeneratedSerializer<?> generatedSerializer = this.generatedSerializer;
        if (generatedSerializer == null || (childSerializers = generatedSerializer.childSerializers()) == null || (kSerializer = childSerializers[i]) == null || (descriptor = kSerializer.getDescriptor()) == null) {
            throw new IndexOutOfBoundsException(getSerialName() + " descriptor has only " + this.elementsCount + " elements, index: " + i);
        }
        return descriptor;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public int getElementIndex(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Integer num = getIndices().get(name);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getElementName(int i) {
        return this.names[i];
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public final int getElementsCount() {
        return this.elementsCount;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @Deprecated(message = "Deprecated in the favour of 'annotations' property", replaceWith = @ReplaceWith(expression = "annotations", imports = {}))
    @NotNull
    public List<Annotation> getEntityAnnotations() {
        return SerialDescriptor.DefaultImpls.getEntityAnnotations(this);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    /* renamed from: getKind */
    public SerialKind mo12397getKind() {
        return StructureKind.CLASS.INSTANCE;
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getName() {
        return SerialDescriptor.DefaultImpls.getName(this);
    }

    @NotNull
    public final Set<String> getNamesSet$kotlinx_serialization_runtime() {
        return getIndices().keySet();
    }

    @Override // kotlinx.serialization.SerialDescriptor
    @NotNull
    public String getSerialName() {
        return this.serialName;
    }

    public int hashCode() {
        return SerialDescriptorKt.elementDescriptors(this).hashCode() + (getSerialName().hashCode() * 31);
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isElementOptional(int i) {
        return this.flags[i];
    }

    @Override // kotlinx.serialization.SerialDescriptor
    public boolean isNullable() {
        return SerialDescriptor.DefaultImpls.isNullable(this);
    }

    public final void pushAnnotation(@NotNull Annotation annotation) {
        Intrinsics.checkParameterIsNotNull(annotation, "annotation");
        List<Annotation> list = this.propertiesAnnotations[this.added];
        if (list == null) {
            list = new ArrayList<>(1);
            this.propertiesAnnotations[this.added] = list;
        }
        list.add(annotation);
    }

    public final void pushClassAnnotation(@NotNull Annotation a) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        if (this.classAnnotations == null) {
            this.classAnnotations = new ArrayList(1);
        }
        List<Annotation> list = this.classAnnotations;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        list.add(a);
    }

    @NotNull
    public String toString() {
        String joinToString$default;
        Set<Map.Entry<String, Integer>> entrySet = getIndices().entrySet();
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(entrySet, ", ", getSerialName() + '(', ")", 0, null, new PluginGeneratedSerialDescriptor$toString$1(this), 24, null);
        return joinToString$default;
    }

    public /* synthetic */ PluginGeneratedSerialDescriptor(String str, GeneratedSerializer generatedSerializer, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? null : generatedSerializer, i);
    }
}
