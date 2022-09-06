package org.bouncycastle.pqc.crypto.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.bouncycastle.util.Integers;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class Utils {
    static final AlgorithmIdentifier AlgID_qTESLA_p_I = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_I);
    static final AlgorithmIdentifier AlgID_qTESLA_p_III = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_III);
    static final AlgorithmIdentifier SPHINCS_SHA3_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha3_256);
    static final AlgorithmIdentifier SPHINCS_SHA512_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256);
    static final AlgorithmIdentifier XMSS_SHA256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
    static final AlgorithmIdentifier XMSS_SHA512 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
    static final AlgorithmIdentifier XMSS_SHAKE128 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake128);
    static final AlgorithmIdentifier XMSS_SHAKE256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake256);
    static final Map categories = new HashMap();

    static {
        categories.put(PQCObjectIdentifiers.qTESLA_p_I, Integers.valueOf(5));
        categories.put(PQCObjectIdentifiers.qTESLA_p_III, Integers.valueOf(6));
    }

    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Digest getDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha256)) {
            return new SHA256Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha512)) {
            return new SHA512Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake128)) {
            return new SHAKEDigest(128);
        }
        if (!aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake256)) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline79("unrecognized digest OID: ", aSN1ObjectIdentifier));
        }
        return new SHAKEDigest(256);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmIdentifier qTeslaLookupAlgID(int i) {
        if (i != 5) {
            if (i != 6) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unknown security category: ", i));
            }
            return AlgID_qTESLA_p_III;
        }
        return AlgID_qTESLA_p_I;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int qTeslaLookupSecurityCategory(AlgorithmIdentifier algorithmIdentifier) {
        return ((Integer) categories.get(algorithmIdentifier.getAlgorithm())).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmIdentifier sphincs256LookupTreeAlgID(String str) {
        if (str.equals("SHA3-256")) {
            return SPHINCS_SHA3_256;
        }
        if (!str.equals(SPHINCSKeyParameters.SHA512_256)) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("unknown tree digest: ", str));
        }
        return SPHINCS_SHA512_256;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String sphincs256LookupTreeAlgName(SPHINCS256KeyParams sPHINCS256KeyParams) {
        AlgorithmIdentifier treeDigest = sPHINCS256KeyParams.getTreeDigest();
        if (treeDigest.getAlgorithm().equals((ASN1Primitive) SPHINCS_SHA3_256.getAlgorithm())) {
            return "SHA3-256";
        }
        if (treeDigest.getAlgorithm().equals((ASN1Primitive) SPHINCS_SHA512_256.getAlgorithm())) {
            return SPHINCSKeyParameters.SHA512_256;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown tree digest: ");
        outline107.append(treeDigest.getAlgorithm());
        throw new IllegalArgumentException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmIdentifier xmssLookupTreeAlgID(String str) {
        if (str.equals("SHA-256")) {
            return XMSS_SHA256;
        }
        if (str.equals("SHA-512")) {
            return XMSS_SHA512;
        }
        if (str.equals("SHAKE128")) {
            return XMSS_SHAKE128;
        }
        if (!str.equals("SHAKE256")) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("unknown tree digest: ", str));
        }
        return XMSS_SHAKE256;
    }
}
