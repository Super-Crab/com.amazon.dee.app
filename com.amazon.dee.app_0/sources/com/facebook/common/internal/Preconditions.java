package com.facebook.common.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes2.dex */
public final class Preconditions {
    private Preconditions() {
    }

    private static String badElementIndex(int index, int size, @Nullable String desc) {
        if (index < 0) {
            return format("%s (%s) must not be negative", desc, Integer.valueOf(index));
        }
        if (size < 0) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("negative size: ", size));
        }
        return format("%s (%s) must be less than size (%s)", desc, Integer.valueOf(index), Integer.valueOf(size));
    }

    private static String badPositionIndex(int index, int size, @Nullable String desc) {
        if (index < 0) {
            return format("%s (%s) must not be negative", desc, Integer.valueOf(index));
        }
        if (size < 0) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("negative size: ", size));
        }
        return format("%s (%s) must not be greater than size (%s)", desc, Integer.valueOf(index), Integer.valueOf(size));
    }

    private static String badPositionIndexes(int start, int end, int size) {
        if (start < 0 || start > size) {
            return badPositionIndex(start, size, "start index");
        }
        return (end < 0 || end > size) ? badPositionIndex(end, size, "end index") : format("end index (%s) must not be less than start index (%s)", Integer.valueOf(end), Integer.valueOf(start));
    }

    public static void checkArgument(@Nullable Boolean expression) {
        if (expression == null || expression.booleanValue()) {
            return;
        }
        throw new IllegalArgumentException();
    }

    public static int checkElementIndex(int index, int size) {
        return checkElementIndex(index, size, "index");
    }

    public static <T> T checkNotNull(@Nullable T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }

    public static int checkPositionIndex(int index, int size) {
        return checkPositionIndex(index, size, "index");
    }

    public static void checkPositionIndexes(int start, int end, int size) {
        if (start < 0 || end < start || end > size) {
            throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size));
        }
    }

    public static void checkState(boolean expression) {
        if (expression) {
            return;
        }
        throw new IllegalStateException();
    }

    static String format(@Nullable String template, Object... args) {
        int indexOf;
        String valueOf = String.valueOf(template);
        StringBuilder sb = new StringBuilder((args.length * 16) + valueOf.length());
        int i = 0;
        int i2 = 0;
        while (i < args.length && (indexOf = valueOf.indexOf("%s", i2)) != -1) {
            sb.append(valueOf.substring(i2, indexOf));
            sb.append(args[i]);
            i2 = indexOf + 2;
            i++;
        }
        sb.append(valueOf.substring(i2));
        if (i < args.length) {
            sb.append(" [");
            sb.append(args[i]);
            for (int i3 = i + 1; i3 < args.length; i3++) {
                sb.append(", ");
                sb.append(args[i3]);
            }
            sb.append(JsonReaderKt.END_LIST);
        }
        return sb.toString();
    }

    public static int checkElementIndex(int index, int size, @Nullable String desc) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(badElementIndex(index, size, desc));
        }
        return index;
    }

    public static <T> T checkNotNull(@Nullable T reference, @Nullable Object errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(String.valueOf(errorMessage));
    }

    public static int checkPositionIndex(int index, int size, @Nullable String desc) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(badPositionIndex(index, size, desc));
        }
        return index;
    }

    public static void checkState(boolean expression, @Nullable Object errorMessage) {
        if (expression) {
            return;
        }
        throw new IllegalStateException(String.valueOf(errorMessage));
    }

    public static void checkArgument(boolean expression, @Nullable Object errorMessage) {
        if (expression) {
            return;
        }
        throw new IllegalArgumentException(String.valueOf(errorMessage));
    }

    public static <T> T checkNotNull(@Nullable T reference, @Nullable String errorMessageTemplate, Object... errorMessageArgs) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs));
    }

    public static void checkState(boolean expression, @Nullable String errorMessageTemplate, Object... errorMessageArgs) {
        if (expression) {
            return;
        }
        throw new IllegalStateException(format(errorMessageTemplate, errorMessageArgs));
    }

    public static void checkArgument(boolean expression, @Nullable String errorMessageTemplate, Object... errorMessageArgs) {
        if (expression) {
            return;
        }
        throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
    }
}
