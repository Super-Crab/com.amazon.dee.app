package kotlin.reflect.jvm.internal.impl.load.kotlin.header;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmBytecodeBinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KotlinClassHeader.kt */
/* loaded from: classes3.dex */
public final class KotlinClassHeader {
    @NotNull
    private final JvmBytecodeBinaryVersion bytecodeVersion;
    @Nullable
    private final String[] data;
    private final int extraInt;
    private final String extraString;
    @Nullable
    private final String[] incompatibleData;
    @NotNull
    private final Kind kind;
    @NotNull
    private final JvmMetadataVersion metadataVersion;
    @Nullable
    private final String packageName;
    @Nullable
    private final String[] strings;

    /* compiled from: KotlinClassHeader.kt */
    /* loaded from: classes3.dex */
    public enum Kind {
        UNKNOWN(0),
        CLASS(1),
        FILE_FACADE(2),
        SYNTHETIC_CLASS(3),
        MULTIFILE_CLASS(4),
        MULTIFILE_CLASS_PART(5);
        
        public static final Companion Companion = new Companion(null);
        private static final Map<Integer, Kind> entryById;
        private final int id;

        /* compiled from: KotlinClassHeader.kt */
        /* loaded from: classes3.dex */
        public static final class Companion {
            private Companion() {
            }

            @JvmStatic
            @NotNull
            public final Kind getById(int i) {
                Kind kind = (Kind) Kind.entryById.get(Integer.valueOf(i));
                return kind != null ? kind : Kind.UNKNOWN;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        static {
            int mapCapacity;
            int coerceAtLeast;
            Kind[] values = values();
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(values.length);
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
            LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
            for (Kind kind : values) {
                linkedHashMap.put(Integer.valueOf(kind.id), kind);
            }
            entryById = linkedHashMap;
        }

        Kind(int i) {
            this.id = i;
        }

        @JvmStatic
        @NotNull
        public static final Kind getById(int i) {
            return Companion.getById(i);
        }
    }

    public KotlinClassHeader(@NotNull Kind kind, @NotNull JvmMetadataVersion metadataVersion, @NotNull JvmBytecodeBinaryVersion bytecodeVersion, @Nullable String[] strArr, @Nullable String[] strArr2, @Nullable String[] strArr3, @Nullable String str, int i, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(metadataVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(bytecodeVersion, "bytecodeVersion");
        this.kind = kind;
        this.metadataVersion = metadataVersion;
        this.bytecodeVersion = bytecodeVersion;
        this.data = strArr;
        this.incompatibleData = strArr2;
        this.strings = strArr3;
        this.extraString = str;
        this.extraInt = i;
        this.packageName = str2;
    }

    @Nullable
    public final String[] getData() {
        return this.data;
    }

    @Nullable
    public final String[] getIncompatibleData() {
        return this.incompatibleData;
    }

    @NotNull
    public final Kind getKind() {
        return this.kind;
    }

    @NotNull
    public final JvmMetadataVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    @Nullable
    public final String getMultifileClassName() {
        String str = this.extraString;
        if (this.kind == Kind.MULTIFILE_CLASS_PART) {
            return str;
        }
        return null;
    }

    @NotNull
    public final List<String> getMultifilePartNames() {
        List<String> emptyList;
        String[] strArr = this.data;
        List<String> list = null;
        if (!(this.kind == Kind.MULTIFILE_CLASS)) {
            strArr = null;
        }
        if (strArr != null) {
            list = ArraysKt___ArraysJvmKt.asList(strArr);
        }
        if (list != null) {
            return list;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Nullable
    public final String[] getStrings() {
        return this.strings;
    }

    public final boolean isPreRelease() {
        return (this.extraInt & 2) != 0;
    }

    @NotNull
    public String toString() {
        return this.kind + " version=" + this.metadataVersion;
    }
}
