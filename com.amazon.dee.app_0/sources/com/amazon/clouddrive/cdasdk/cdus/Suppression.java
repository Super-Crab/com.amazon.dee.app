package com.amazon.clouddrive.cdasdk.cdus;

import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes11.dex */
public enum Suppression {
    NONE,
    DEDUPLICATION,
    PROCESSING,
    TRANSCODING,
    ALL;

    static String serializeList(@NonNull List<Suppression> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(list.get(i).name());
        }
        return sb.toString();
    }
}
