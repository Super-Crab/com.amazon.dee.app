package amazon.speech.util;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Locale;
/* loaded from: classes.dex */
public class Preconditions {
    public static void checkNotEmpty(Collection<?>... collectionArr) {
        checkNotNull(collectionArr);
        for (Collection<?> collection : collectionArr) {
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("collection " + collection + " is empty");
            }
        }
    }

    public static void checkNotNull(Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] == null) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline91(new StringBuilder(), toOrdinalString(i + 1), " argument is null"));
                }
            }
            return;
        }
        throw new IllegalArgumentException("No arguments");
    }

    public static void checkStrNotNullOrEmpty(String str, String str2) {
        checkVarNotNull(str, str2);
        if (!str.isEmpty()) {
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72(str2, " cannot be empty"));
    }

    public static void checkVarNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72(str, " cannot be null"));
    }

    private static String toOrdinalString(int i) {
        int abs = Math.abs(i);
        return String.format(Locale.US, "%d%s", Integer.valueOf(i), abs != 1 ? abs != 2 ? abs != 3 ? "th" : "rd" : "nd" : Constants.TIMELINE_START_TIME_KEY);
    }
}
