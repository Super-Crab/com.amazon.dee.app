package org.bouncycastle.jsse.provider;

import com.amazon.whispercloak.KeyUtils;
import java.security.AlgorithmParameters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Logger;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
import org.bouncycastle.tls.NamedGroup;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class NamedGroupInfo {
    private static final String PROPERTY_NAMED_GROUPS = "jdk.tls.namedGroups";
    private final AlgorithmParameters algorithmParameters;
    private final All all;
    private final boolean enabled;
    private static final Logger LOG = Logger.getLogger(NamedGroupInfo.class.getName());
    private static final int[] CANDIDATES_DEFAULT = {29, 30, 23, 24, 25, 256, 257, 258};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum All {
        sect163k1(1, KeyUtils.ALGORITHM_EC),
        sect163r1(2, KeyUtils.ALGORITHM_EC),
        sect163r2(3, KeyUtils.ALGORITHM_EC),
        sect193r1(4, KeyUtils.ALGORITHM_EC),
        sect193r2(5, KeyUtils.ALGORITHM_EC),
        sect233k1(6, KeyUtils.ALGORITHM_EC),
        sect233r1(7, KeyUtils.ALGORITHM_EC),
        sect239k1(8, KeyUtils.ALGORITHM_EC),
        sect283k1(9, KeyUtils.ALGORITHM_EC),
        sect283r1(10, KeyUtils.ALGORITHM_EC),
        sect409k1(11, KeyUtils.ALGORITHM_EC),
        sect409r1(12, KeyUtils.ALGORITHM_EC),
        sect571k1(13, KeyUtils.ALGORITHM_EC),
        sect571r1(14, KeyUtils.ALGORITHM_EC),
        secp160k1(15, KeyUtils.ALGORITHM_EC),
        secp160r1(16, KeyUtils.ALGORITHM_EC),
        secp160r2(17, KeyUtils.ALGORITHM_EC),
        secp192k1(18, KeyUtils.ALGORITHM_EC),
        secp192r1(19, KeyUtils.ALGORITHM_EC),
        secp224k1(20, KeyUtils.ALGORITHM_EC),
        secp224r1(21, KeyUtils.ALGORITHM_EC),
        secp256k1(22, KeyUtils.ALGORITHM_EC),
        secp256r1(23, KeyUtils.ALGORITHM_EC),
        secp384r1(24, KeyUtils.ALGORITHM_EC),
        secp521r1(25, KeyUtils.ALGORITHM_EC),
        brainpoolP256r1(26, KeyUtils.ALGORITHM_EC),
        brainpoolP384r1(27, KeyUtils.ALGORITHM_EC),
        brainpoolP512r1(28, KeyUtils.ALGORITHM_EC),
        x25519(29, "XDH"),
        x448(30, "XDH"),
        ffdhe2048(256, "DiffieHellman"),
        ffdhe3072(257, "DiffieHellman"),
        ffdhe4096(258, "DiffieHellman"),
        ffdhe6144(259, "DiffieHellman"),
        ffdhe8192(260, "DiffieHellman");
        
        private final int bitsECDH;
        private final int bitsFFDHE;
        private final boolean char2;
        private final String jcaAlgorithm;
        private final String name;
        private final int namedGroup;
        private final boolean supported13;
        private final String text;

        All(int i, String str) {
            this.namedGroup = i;
            this.name = NamedGroup.getName(i);
            this.text = this.name + "(" + i + ")";
            this.jcaAlgorithm = str;
            this.supported13 = NamedGroup.canBeNegotiated(i, ProtocolVersion.TLSv13);
            this.char2 = NamedGroup.isChar2Curve(i);
            this.bitsECDH = NamedGroup.getCurveBits(i);
            this.bitsFFDHE = NamedGroup.getFiniteFieldBits(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class PerConnection {
        private final Map<Integer, NamedGroupInfo> local;
        private final boolean localECDSA;
        private List<NamedGroupInfo> peer = null;

        PerConnection(Map<Integer, NamedGroupInfo> map, boolean z) {
            this.local = map;
            this.localECDSA = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized void setPeer(List<NamedGroupInfo> list) {
            this.peer = list;
        }

        public synchronized List<NamedGroupInfo> getPeer() {
            return this.peer;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class PerContext {
        private final int[] candidates;
        private final Map<Integer, NamedGroupInfo> index;

        PerContext(Map<Integer, NamedGroupInfo> map, int[] iArr) {
            this.index = map;
            this.candidates = iArr;
        }
    }

    NamedGroupInfo(All all, AlgorithmParameters algorithmParameters, boolean z) {
        this.all = all;
        this.algorithmParameters = algorithmParameters;
        this.enabled = z;
    }

    private static void addNamedGroup(boolean z, JcaTlsCrypto jcaTlsCrypto, boolean z2, boolean z3, Map<Integer, NamedGroupInfo> map, All all) {
        int i = all.namedGroup;
        if (!z || FipsUtils.isFipsNamedGroup(i)) {
            boolean z4 = true;
            if (((z2 && all.char2) || (z3 && all.bitsFFDHE > 0)) || !jcaTlsCrypto.hasNamedGroup(i)) {
                z4 = false;
            }
            AlgorithmParameters algorithmParameters = null;
            if (z4) {
                try {
                    algorithmParameters = jcaTlsCrypto.getNamedGroupAlgorithmParameters(i);
                } catch (Exception unused) {
                    z4 = false;
                }
            }
            if (map.put(Integer.valueOf(i), new NamedGroupInfo(all, algorithmParameters, z4)) != null) {
                throw new IllegalStateException("Duplicate entries for NamedGroupInfo");
            }
        }
    }

    private static int[] createCandidates(Map<Integer, NamedGroupInfo> map) {
        Logger logger;
        StringBuilder sb;
        String str;
        String[] stringArraySystemProperty = PropertyUtils.getStringArraySystemProperty(PROPERTY_NAMED_GROUPS);
        if (stringArraySystemProperty == null) {
            return CANDIDATES_DEFAULT;
        }
        int[] iArr = new int[stringArraySystemProperty.length];
        int i = 0;
        for (String str2 : stringArraySystemProperty) {
            int namedGroupByName = getNamedGroupByName(str2);
            if (namedGroupByName < 0) {
                logger = LOG;
                sb = new StringBuilder();
                str = "'jdk.tls.namedGroups' contains unrecognised NamedGroup: ";
            } else {
                NamedGroupInfo namedGroupInfo = map.get(Integer.valueOf(namedGroupByName));
                if (namedGroupInfo == null) {
                    logger = LOG;
                    sb = new StringBuilder();
                    str = "'jdk.tls.namedGroups' contains unsupported NamedGroup: ";
                } else if (!namedGroupInfo.isEnabled()) {
                    logger = LOG;
                    sb = new StringBuilder();
                    str = "'jdk.tls.namedGroups' contains disabled NamedGroup: ";
                } else {
                    iArr[i] = namedGroupByName;
                    i++;
                }
            }
            sb.append(str);
            sb.append(str2);
            logger.warning(sb.toString());
        }
        if (i < iArr.length) {
            iArr = Arrays.copyOf(iArr, i);
        }
        if (iArr.length < 1) {
            LOG.severe("'jdk.tls.namedGroups' contained no usable NamedGroup values");
        }
        return iArr;
    }

    private static Map<Integer, NamedGroupInfo> createIndex(boolean z, JcaTlsCrypto jcaTlsCrypto) {
        TreeMap treeMap = new TreeMap();
        boolean z2 = PropertyUtils.getBooleanSystemProperty("org.bouncycastle.jsse.ec.disableChar2", false) || PropertyUtils.getBooleanSystemProperty("org.bouncycastle.ec.disable_f2m", false);
        boolean z3 = !PropertyUtils.getBooleanSystemProperty("jsse.enableFFDHE", true);
        for (All all : All.values()) {
            addNamedGroup(z, jcaTlsCrypto, z2, z3, treeMap, all);
        }
        return treeMap;
    }

    private static Map<Integer, NamedGroupInfo> createLocal(PerContext perContext, ProvSSLParameters provSSLParameters, ProtocolVersion[] protocolVersionArr) {
        ProtocolVersion latestTLS = ProtocolVersion.getLatestTLS(protocolVersionArr);
        ProtocolVersion earliestTLS = ProtocolVersion.getEarliestTLS(protocolVersionArr);
        BCAlgorithmConstraints algorithmConstraints = provSSLParameters.getAlgorithmConstraints();
        boolean isTLSv13 = TlsUtils.isTLSv13(latestTLS);
        boolean z = !TlsUtils.isTLSv13(earliestTLS);
        int length = perContext.candidates.length;
        LinkedHashMap linkedHashMap = new LinkedHashMap(length);
        for (int i = 0; i < length; i++) {
            Integer valueOf = Integers.valueOf(perContext.candidates[i]);
            NamedGroupInfo namedGroupInfo = (NamedGroupInfo) perContext.index.get(valueOf);
            if (namedGroupInfo != null && namedGroupInfo.isActive(algorithmConstraints, z, isTLSv13)) {
                linkedHashMap.put(valueOf, namedGroupInfo);
            }
        }
        return linkedHashMap;
    }

    private static boolean createLocalECDSA(Map<Integer, NamedGroupInfo> map) {
        for (NamedGroupInfo namedGroupInfo : map.values()) {
            if (NamedGroup.refersToAnECDSACurve(namedGroupInfo.getNamedGroup())) {
                return true;
            }
        }
        return false;
    }

    private static List<NamedGroupInfo> createPeer(PerConnection perConnection, int[] iArr) {
        return getNamedGroupInfos(perConnection.local, iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PerConnection createPerConnection(PerContext perContext, ProvSSLParameters provSSLParameters, ProtocolVersion[] protocolVersionArr) {
        Map<Integer, NamedGroupInfo> createLocal = createLocal(perContext, provSSLParameters, protocolVersionArr);
        return new PerConnection(createLocal, createLocalECDSA(createLocal));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PerContext createPerContext(boolean z, JcaTlsCrypto jcaTlsCrypto) {
        Map<Integer, NamedGroupInfo> createIndex = createIndex(z, jcaTlsCrypto);
        return new PerContext(createIndex, createCandidates(createIndex));
    }

    private static Collection<NamedGroupInfo> getEffectivePeer(PerConnection perConnection) {
        List<NamedGroupInfo> peer = perConnection.getPeer();
        return !peer.isEmpty() ? peer : perConnection.local.values();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getMaximumBitsServerECDH(PerConnection perConnection) {
        int i = 0;
        for (NamedGroupInfo namedGroupInfo : getEffectivePeer(perConnection)) {
            i = Math.max(i, namedGroupInfo.getBitsECDH());
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getMaximumBitsServerFFDHE(PerConnection perConnection) {
        int i = 0;
        for (NamedGroupInfo namedGroupInfo : getEffectivePeer(perConnection)) {
            i = Math.max(i, namedGroupInfo.getBitsFFDHE());
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NamedGroupInfo getNamedGroup(PerContext perContext, int i) {
        return (NamedGroupInfo) perContext.index.get(Integer.valueOf(i));
    }

    private static int getNamedGroupByName(String str) {
        All[] values;
        for (All all : All.values()) {
            if (all.name.equalsIgnoreCase(str)) {
                return all.namedGroup;
            }
        }
        return -1;
    }

    private static List<NamedGroupInfo> getNamedGroupInfos(Map<Integer, NamedGroupInfo> map, int[] iArr) {
        if (iArr == null || iArr.length < 1) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            NamedGroupInfo namedGroupInfo = map.get(Integer.valueOf(i));
            if (namedGroupInfo != null) {
                arrayList.add(namedGroupInfo);
            }
        }
        if (arrayList.isEmpty()) {
            return Collections.emptyList();
        }
        arrayList.trimToSize();
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Vector<Integer> getSupportedGroupsLocalClient(PerConnection perConnection) {
        return new Vector<>(perConnection.local.keySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] getSupportedGroupsLocalServer(PerConnection perConnection) {
        Set<Integer> keySet = perConnection.local.keySet();
        int[] iArr = new int[keySet.size()];
        int i = 0;
        for (Integer num : keySet) {
            iArr[i] = num.intValue();
            i++;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasAnyECDSALocal(PerConnection perConnection) {
        return perConnection.localECDSA;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasLocal(PerConnection perConnection, int i) {
        return perConnection.local.containsKey(Integer.valueOf(i));
    }

    private boolean isPermittedBy(BCAlgorithmConstraints bCAlgorithmConstraints) {
        Set<BCCryptoPrimitive> set = JsseUtils.KEY_AGREEMENT_CRYPTO_PRIMITIVES_BC;
        return bCAlgorithmConstraints.permits(set, this.all.name, null) && bCAlgorithmConstraints.permits(set, this.all.jcaAlgorithm, this.algorithmParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void notifyPeer(PerConnection perConnection, int[] iArr) {
        perConnection.setPeer(createPeer(perConnection, iArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int selectServerECDH(PerConnection perConnection, int i) {
        for (NamedGroupInfo namedGroupInfo : getEffectivePeer(perConnection)) {
            if (namedGroupInfo.getBitsECDH() >= i) {
                return namedGroupInfo.getNamedGroup();
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int selectServerFFDHE(PerConnection perConnection, int i) {
        for (NamedGroupInfo namedGroupInfo : getEffectivePeer(perConnection)) {
            if (namedGroupInfo.getBitsFFDHE() >= i) {
                return namedGroupInfo.getNamedGroup();
            }
        }
        return -1;
    }

    int getBitsECDH() {
        return this.all.bitsECDH;
    }

    int getBitsFFDHE() {
        return this.all.bitsFFDHE;
    }

    String getName() {
        return this.all.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNamedGroup() {
        return this.all.namedGroup;
    }

    boolean isActive(BCAlgorithmConstraints bCAlgorithmConstraints, boolean z, boolean z2) {
        return this.enabled && (z || (z2 && isSupported13())) && isPermittedBy(bCAlgorithmConstraints);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEnabled() {
        return this.enabled;
    }

    boolean isSupported13() {
        return this.all.supported13;
    }

    public String toString() {
        return this.all.text;
    }
}
