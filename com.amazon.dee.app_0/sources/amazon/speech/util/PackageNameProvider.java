package amazon.speech.util;

import android.content.pm.PackageManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class PackageNameProvider {
    public static String getNameForUid(PackageManager packageManager, int i) {
        String nameForUid = packageManager.getNameForUid(i);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(":");
        outline107.append(String.valueOf(i));
        return nameForUid.contains(outline107.toString()) ? nameForUid.split(":")[0] : nameForUid;
    }
}
