package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BinaryVersion.kt */
/* loaded from: classes4.dex */
public abstract class BinaryVersion {
    public static final Companion Companion = new Companion(null);
    private final int major;
    private final int minor;
    private final int[] numbers;
    private final int patch;
    @NotNull
    private final List<Integer> rest;

    /* compiled from: BinaryVersion.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BinaryVersion(@NotNull int... numbers) {
        Integer orNull;
        Integer orNull2;
        Integer orNull3;
        List<Integer> emptyList;
        List<Integer> asList;
        Intrinsics.checkParameterIsNotNull(numbers, "numbers");
        this.numbers = numbers;
        orNull = ArraysKt___ArraysKt.getOrNull(this.numbers, 0);
        int i = -1;
        this.major = orNull != null ? orNull.intValue() : -1;
        orNull2 = ArraysKt___ArraysKt.getOrNull(this.numbers, 1);
        this.minor = orNull2 != null ? orNull2.intValue() : -1;
        orNull3 = ArraysKt___ArraysKt.getOrNull(this.numbers, 2);
        this.patch = orNull3 != null ? orNull3.intValue() : i;
        int[] iArr = this.numbers;
        if (iArr.length > 3) {
            asList = ArraysKt___ArraysJvmKt.asList(iArr);
            emptyList = CollectionsKt___CollectionsKt.toList(asList.subList(3, this.numbers.length));
        } else {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        this.rest = emptyList;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj != null && Intrinsics.areEqual(getClass(), obj.getClass())) {
            BinaryVersion binaryVersion = (BinaryVersion) obj;
            if (this.major == binaryVersion.major && this.minor == binaryVersion.minor && this.patch == binaryVersion.patch && Intrinsics.areEqual(this.rest, binaryVersion.rest)) {
                return true;
            }
        }
        return false;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public int hashCode() {
        int i = this.major;
        int i2 = (i * 31) + this.minor + i;
        int i3 = (i2 * 31) + this.patch + i2;
        return this.rest.hashCode() + (i3 * 31) + i3;
    }

    public final boolean isAtLeast(@NotNull BinaryVersion version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        return isAtLeast(version.major, version.minor, version.patch);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isCompatibleTo(@NotNull BinaryVersion ourVersion) {
        Intrinsics.checkParameterIsNotNull(ourVersion, "ourVersion");
        int i = this.major;
        if (i == 0) {
            if (ourVersion.major == 0 && this.minor == ourVersion.minor) {
                return true;
            }
        } else if (i == ourVersion.major && this.minor <= ourVersion.minor) {
            return true;
        }
        return false;
    }

    @NotNull
    public final int[] toArray() {
        return this.numbers;
    }

    @NotNull
    public String toString() {
        String joinToString$default;
        int[] array = toArray();
        ArrayList arrayList = new ArrayList();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int i2 = array[i];
            if (!(i2 != -1)) {
                break;
            }
            arrayList.add(Integer.valueOf(i2));
        }
        if (arrayList.isEmpty()) {
            return "unknown";
        }
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, ".", null, null, 0, null, null, 62, null);
        return joinToString$default;
    }

    public final boolean isAtLeast(int i, int i2, int i3) {
        int i4 = this.major;
        if (i4 > i) {
            return true;
        }
        if (i4 < i) {
            return false;
        }
        int i5 = this.minor;
        if (i5 > i2) {
            return true;
        }
        return i5 >= i2 && this.patch >= i3;
    }
}
