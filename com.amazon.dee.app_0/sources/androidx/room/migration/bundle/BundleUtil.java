package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class BundleUtil {
    public static final String TABLE_NAME_PLACEHOLDER = "${TABLE_NAME}";
    public static final String VIEW_NAME_PLACEHOLDER = "${VIEW_NAME}";

    private BundleUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String replaceTableName(String str, String str2) {
        return str.replace(TABLE_NAME_PLACEHOLDER, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String replaceViewName(String str, String str2) {
        return str.replace(VIEW_NAME_PLACEHOLDER, str2);
    }
}
