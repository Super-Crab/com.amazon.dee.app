package org.bouncycastle.pqc.crypto.qtesla;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public class QTESLASecurityCategory {
    public static final int PROVABLY_SECURE_I = 5;
    public static final int PROVABLY_SECURE_III = 6;

    private QTESLASecurityCategory() {
    }

    public static String getName(int i) {
        if (i != 5) {
            if (i != 6) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unknown security category: ", i));
            }
            return "qTESLA-p-III";
        }
        return "qTESLA-p-I";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getPrivateSize(int i) {
        if (i != 5) {
            if (i != 6) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unknown security category: ", i));
            }
            return 12392;
        }
        return 5224;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getPublicSize(int i) {
        if (i != 5) {
            if (i != 6) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unknown security category: ", i));
            }
            return 38432;
        }
        return 14880;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getSignatureSize(int i) {
        if (i != 5) {
            if (i != 6) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unknown security category: ", i));
            }
            return 5664;
        }
        return 2592;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validate(int i) {
        if (i == 5 || i == 6) {
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unknown security category: ", i));
    }
}
