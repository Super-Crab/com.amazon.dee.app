package com.amazon.deecomms.common.database;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes12.dex */
public final class DatabaseUtils {
    private static final String SELECTION_NOT_NULL_AND_EMPTY = "%s IS NOT NULL AND LENGTH(%s) > 0";

    private DatabaseUtils() {
    }

    @NonNull
    public static String appendSelection(@NonNull String str, @Nullable String str2, @NonNull List<String> list) {
        if (str2 != null) {
            list.add(str2);
            return str + " = ?";
        }
        return GeneratedOutlineSupport.outline0(str, " IS NULL");
    }

    public static String createPartialIndex(@NonNull String str, @NonNull String str2, @NonNull String str3, String... strArr) {
        String createUniqueIndex = createUniqueIndex(str, str2, strArr);
        if (TextUtils.isEmpty(str3)) {
            return createUniqueIndex;
        }
        while (createUniqueIndex.charAt(createUniqueIndex.length() - 1) == ';') {
            createUniqueIndex = GeneratedOutlineSupport1.outline50(createUniqueIndex, -1, 0);
        }
        return GeneratedOutlineSupport1.outline76(createUniqueIndex, " WHERE ", str3, ";");
    }

    public static String createUniqueIndex(String str, String str2, String... strArr) {
        String outline77 = GeneratedOutlineSupport1.outline77("CREATE UNIQUE INDEX IF NOT EXISTS ", str2, " ON ", str, " (");
        StringBuilder sb = new StringBuilder();
        for (String str3 : strArr) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(str3);
        }
        return outline77 + ((Object) sb) + ");";
    }

    public static String dropIndex(String str) {
        return GeneratedOutlineSupport1.outline75("DROP INDEX ", str, ";");
    }

    @NonNull
    public static String generateNonNullAndEmptySelection(@NonNull String str) {
        return TextUtils.isEmpty(str) ? "" : String.format(SELECTION_NOT_NULL_AND_EMPTY, str, str);
    }

    public static String getInListPlaceholder(int i) {
        if (i < 1) {
            return null;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(WebConstants.UriConstants.QUESTIONMARK_KEY);
        for (int i2 = 1; i2 < i; i2++) {
            outline107.append(",?");
        }
        return outline107.toString();
    }
}
