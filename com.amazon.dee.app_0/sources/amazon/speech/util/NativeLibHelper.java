package amazon.speech.util;

import amazon.speech.util.DebugUtil;
/* loaded from: classes.dex */
public class NativeLibHelper {
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.UTL, NativeLibHelper.class);

    public static void loadLibrary(String str) {
        try {
            System.loadLibrary(str);
        } catch (UnsatisfiedLinkError unused) {
            String str2 = "Failed to load " + str;
            System.loadLibrary(str + ".AMAZON");
            String str3 = "Loaded fallback native lib: " + str + ".AMAZON";
        }
    }
}
