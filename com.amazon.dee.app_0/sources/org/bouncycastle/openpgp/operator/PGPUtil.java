package org.bouncycastle.openpgp.operator;

import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.bcpg.S2K;
import org.bouncycastle.openpgp.PGPException;
/* loaded from: classes5.dex */
class PGPUtil implements HashAlgorithmTags {
    PGPUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d4 A[Catch: IOException -> 0x00ee, TryCatch #0 {IOException -> 0x00ee, blocks: (B:19:0x0048, B:24:0x0050, B:25:0x0056, B:30:0x0065, B:33:0x007a, B:35:0x0080, B:36:0x0085, B:38:0x0091, B:39:0x0097, B:46:0x00c8, B:48:0x00d4, B:50:0x00de, B:49:0x00da, B:40:0x009e, B:41:0x00b8, B:42:0x00b9, B:45:0x00c5, B:44:0x00bf), top: B:62:0x0048 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00da A[Catch: IOException -> 0x00ee, TryCatch #0 {IOException -> 0x00ee, blocks: (B:19:0x0048, B:24:0x0050, B:25:0x0056, B:30:0x0065, B:33:0x007a, B:35:0x0080, B:36:0x0085, B:38:0x0091, B:39:0x0097, B:46:0x00c8, B:48:0x00d4, B:50:0x00de, B:49:0x00da, B:40:0x009e, B:41:0x00b8, B:42:0x00b9, B:45:0x00c5, B:44:0x00bf), top: B:62:0x0048 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] makeKeyFromPassPhrase(org.bouncycastle.openpgp.operator.PGPDigestCalculator r12, int r13, org.bouncycastle.bcpg.S2K r14, char[] r15) throws org.bouncycastle.openpgp.PGPException {
        /*
            Method dump skipped, instructions count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.openpgp.operator.PGPUtil.makeKeyFromPassPhrase(org.bouncycastle.openpgp.operator.PGPDigestCalculator, int, org.bouncycastle.bcpg.S2K, char[]):byte[]");
    }

    public static byte[] makeKeyFromPassPhrase(PGPDigestCalculatorProvider pGPDigestCalculatorProvider, int i, S2K s2k, char[] cArr) throws PGPException {
        return makeKeyFromPassPhrase(pGPDigestCalculatorProvider.get(s2k != null ? s2k.getHashAlgorithm() : 1), i, s2k, cArr);
    }
}
