package com.google.common.base;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes12.dex */
public final class Preconditions {
    private Preconditions() {
    }

    private static String badElementIndex(int i, int i2, @NullableDecl String str) {
        if (i < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 < 0) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(26, "negative size: ", i2));
        }
        return Strings.lenientFormat("%s (%s) must be less than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }

    private static String badPositionIndex(int i, int i2, @NullableDecl String str) {
        if (i < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 < 0) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(26, "negative size: ", i2));
        }
        return Strings.lenientFormat("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }

    private static String badPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i > i3) {
            return badPositionIndex(i, i3, "start index");
        }
        return (i2 < 0 || i2 > i3) ? badPositionIndex(i2, i3, "end index") : Strings.lenientFormat("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
    }

    public static void checkArgument(boolean z) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException();
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i, int i2) {
        return checkElementIndex(i, i2, "index");
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int i, int i2) {
        return checkPositionIndex(i, i2, "index");
    }

    public static void checkPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i2 > i3) {
            throw new IndexOutOfBoundsException(badPositionIndexes(i, i2, i3));
        }
    }

    public static void checkState(boolean z) {
        if (z) {
            return;
        }
        throw new IllegalStateException();
    }

    public static void checkArgument(boolean z, @NullableDecl Object obj) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i, int i2, @NullableDecl String str) {
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(badElementIndex(i, i2, str));
        }
        return i;
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int i, int i2, @NullableDecl String str) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(badPositionIndex(i, i2, str));
        }
        return i;
    }

    public static void checkState(boolean z, @NullableDecl Object obj) {
        if (z) {
            return;
        }
        throw new IllegalStateException(String.valueOf(obj));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object... objArr) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, objArr));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, @NullableDecl Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, objArr));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object... objArr) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, objArr));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c)));
    }

    public static void checkState(boolean z, @NullableDecl String str, char c) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i)));
    }

    public static void checkState(boolean z, @NullableDecl String str, int i) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j)));
    }

    public static void checkState(boolean z, @NullableDecl String str, long j) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, obj));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, obj));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c, char c2) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c), Character.valueOf(c2)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, char c, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c), Character.valueOf(c2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, char c, char c2) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c), Character.valueOf(c2)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c, int i) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c), Integer.valueOf(i)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, char c, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c), Integer.valueOf(i)));
    }

    public static void checkState(boolean z, @NullableDecl String str, char c, int i) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c), Integer.valueOf(i)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c, long j) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c), Long.valueOf(j)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, char c, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c), Long.valueOf(j)));
    }

    public static void checkState(boolean z, @NullableDecl String str, char c, long j) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c), Long.valueOf(j)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c, @NullableDecl Object obj) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c), obj));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, char c, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c), obj));
    }

    public static void checkState(boolean z, @NullableDecl String str, char c, @NullableDecl Object obj) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c), obj));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i, char c) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i), Character.valueOf(c)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, int i, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i), Character.valueOf(c)));
    }

    public static void checkState(boolean z, @NullableDecl String str, int i, char c) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i), Character.valueOf(c)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i, int i2) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, int i, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, int i, int i2) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i, long j) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i), Long.valueOf(j)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, int i, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i), Long.valueOf(j)));
    }

    public static void checkState(boolean z, @NullableDecl String str, int i, long j) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i), Long.valueOf(j)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i, @NullableDecl Object obj) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i), obj));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, int i, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i), obj));
    }

    public static void checkState(boolean z, @NullableDecl String str, int i, @NullableDecl Object obj) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i), obj));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j, char c) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, long j, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c)));
    }

    public static void checkState(boolean z, @NullableDecl String str, long j, char c) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j, int i) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(i)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, long j, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(i)));
    }

    public static void checkState(boolean z, @NullableDecl String str, long j, int i) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(i)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j, long j2) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, long j, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, long j, long j2) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j, @NullableDecl Object obj) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), obj));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, long j, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), obj));
    }

    public static void checkState(boolean z, @NullableDecl String str, long j, @NullableDecl Object obj) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), obj));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, char c) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Character.valueOf(c)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, @NullableDecl Object obj, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Character.valueOf(c)));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, char c) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, obj, Character.valueOf(c)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, int i) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Integer.valueOf(i)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, @NullableDecl Object obj, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Integer.valueOf(i)));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, int i) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, obj, Integer.valueOf(i)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, long j) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Long.valueOf(j)));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, @NullableDecl Object obj, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Long.valueOf(j)));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, long j) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, obj, Long.valueOf(j)));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2, obj3));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2, obj3));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2, obj3));
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3, @NullableDecl Object obj4) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
    }

    @CanIgnoreReturnValue
    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3, @NullableDecl Object obj4) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3, @NullableDecl Object obj4) {
        if (z) {
            return;
        }
        throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
    }
}
