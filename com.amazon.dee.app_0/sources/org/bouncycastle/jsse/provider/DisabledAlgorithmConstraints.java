package org.bouncycastle.jsse.provider;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.interfaces.DSAKey;
import java.security.interfaces.DSAParams;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHKey;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class DisabledAlgorithmConstraints extends AbstractAlgorithmConstraints {
    private static final String INCLUDE_PREFIX = "include ";
    private static final String KEYWORD_KEYSIZE = "keySize";
    private static final Logger LOG = Logger.getLogger(DisabledAlgorithmConstraints.class.getName());
    private final Map<String, List<Constraint>> constraintsMap;
    private final Set<String> disabledAlgorithms;

    /* renamed from: org.bouncycastle.jsse.provider.DisabledAlgorithmConstraints$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$jsse$provider$DisabledAlgorithmConstraints$BinOp = new int[BinOp.values().length];

        static {
            try {
                $SwitchMap$org$bouncycastle$jsse$provider$DisabledAlgorithmConstraints$BinOp[BinOp.EQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$jsse$provider$DisabledAlgorithmConstraints$BinOp[BinOp.GE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$bouncycastle$jsse$provider$DisabledAlgorithmConstraints$BinOp[BinOp.GT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$bouncycastle$jsse$provider$DisabledAlgorithmConstraints$BinOp[BinOp.LE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$bouncycastle$jsse$provider$DisabledAlgorithmConstraints$BinOp[BinOp.LT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$bouncycastle$jsse$provider$DisabledAlgorithmConstraints$BinOp[BinOp.NE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public enum BinOp {
        EQ("=="),
        GE(">="),
        GT(Config.Compare.GREATER_THAN),
        LE("<="),
        LT(Config.Compare.LESS_THAN),
        NE("!=");
        
        private final String s;

        BinOp(String str) {
            this.s = str;
        }

        static boolean eval(BinOp binOp, int i, int i2) {
            int ordinal = binOp.ordinal();
            return ordinal != 0 ? ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? ordinal != 4 ? (ordinal == 5 && i == i2) ? false : true : i < i2 : i <= i2 : i > i2 : i >= i2 : i == i2;
        }

        static BinOp parse(String str) {
            BinOp[] values;
            for (BinOp binOp : values()) {
                if (binOp.s.equals(str)) {
                    return binOp;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("'s' is not a valid operator: ", str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static abstract class Constraint {
        private Constraint() {
        }

        /* synthetic */ Constraint(AnonymousClass1 anonymousClass1) {
            this();
        }

        boolean permits(AlgorithmParameters algorithmParameters) {
            return true;
        }

        boolean permits(Key key) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class DisabledConstraint extends Constraint {
        static final DisabledConstraint INSTANCE = new DisabledConstraint();

        private DisabledConstraint() {
            super(null);
        }

        @Override // org.bouncycastle.jsse.provider.DisabledAlgorithmConstraints.Constraint
        public boolean permits(Key key) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class KeySizeConstraint extends Constraint {
        private final int constraint;
        private final BinOp op;

        KeySizeConstraint(BinOp binOp, int i) {
            super(null);
            this.op = binOp;
            this.constraint = i;
        }

        private boolean checkKeySize(int i) {
            return i < 1 ? i < 0 : !BinOp.eval(this.op, i, this.constraint);
        }

        private static int getKeySize(AlgorithmParameters algorithmParameters) {
            DHParameterSpec dHParameterSpec;
            String algorithm = algorithmParameters.getAlgorithm();
            try {
                if (KeyUtils.ALGORITHM_EC.equals(algorithm)) {
                    ECParameterSpec eCParameterSpec = (ECParameterSpec) algorithmParameters.getParameterSpec(ECParameterSpec.class);
                    if (eCParameterSpec == null) {
                        return -1;
                    }
                    return eCParameterSpec.getOrder().bitLength();
                } else if ("DiffieHellman".equals(algorithm) && (dHParameterSpec = (DHParameterSpec) algorithmParameters.getParameterSpec(DHParameterSpec.class)) != null) {
                    return dHParameterSpec.getP().bitLength();
                } else {
                    return -1;
                }
            } catch (InvalidParameterSpecException unused) {
                return -1;
            }
        }

        private static int getKeySize(Key key) {
            byte[] encoded;
            BigInteger p;
            if (key instanceof RSAKey) {
                p = ((RSAKey) key).getModulus();
            } else if (key instanceof ECKey) {
                p = ((ECKey) key).getParams().getOrder();
            } else if (key instanceof DSAKey) {
                DSAParams params = ((DSAKey) key).getParams();
                if (params == null) {
                    return -1;
                }
                p = params.getP();
            } else if (!(key instanceof DHKey)) {
                if (!(key instanceof SecretKey)) {
                    return -1;
                }
                SecretKey secretKey = (SecretKey) key;
                if (!"RAW".equals(secretKey.getFormat()) || (encoded = secretKey.getEncoded()) == null) {
                    return -1;
                }
                if (encoded.length <= 268435455) {
                    return encoded.length * 8;
                }
                return 0;
            } else {
                p = ((DHKey) key).getParams().getP();
            }
            return p.bitLength();
        }

        @Override // org.bouncycastle.jsse.provider.DisabledAlgorithmConstraints.Constraint
        boolean permits(AlgorithmParameters algorithmParameters) {
            return checkKeySize(getKeySize(algorithmParameters));
        }

        @Override // org.bouncycastle.jsse.provider.DisabledAlgorithmConstraints.Constraint
        boolean permits(Key key) {
            return checkKeySize(getKeySize(key));
        }
    }

    private DisabledAlgorithmConstraints(AlgorithmDecomposer algorithmDecomposer, Set<String> set, Map<String, List<Constraint>> map) {
        super(algorithmDecomposer);
        this.disabledAlgorithms = set;
        this.constraintsMap = map;
    }

    private static void addConstraint(Map<String, List<Constraint>> map, String str, Constraint constraint) {
        List<Constraint> list = map.get(str);
        if (list == null) {
            list = new ArrayList<>(1);
            map.put(str, list);
        }
        list.add(constraint);
    }

    private static boolean addConstraint(Set<String> set, Map<String, List<Constraint>> map, String str) {
        if (str.regionMatches(true, 0, INCLUDE_PREFIX, 0, 8)) {
            return false;
        }
        int indexOf = str.indexOf(32);
        if (indexOf < 0) {
            String canonicalAlgorithm = getCanonicalAlgorithm(str);
            set.add(canonicalAlgorithm);
            addConstraint(map, canonicalAlgorithm, DisabledConstraint.INSTANCE);
            return true;
        }
        String canonicalAlgorithm2 = getCanonicalAlgorithm(str.substring(0, indexOf));
        String trim = str.substring(indexOf + 1).trim();
        if (trim.indexOf(38) >= 0 || !trim.startsWith(KEYWORD_KEYSIZE)) {
            return false;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(trim);
        if (!KEYWORD_KEYSIZE.equals(stringTokenizer.nextToken())) {
            return false;
        }
        BinOp parse = BinOp.parse(stringTokenizer.nextToken());
        int parseInt = Integer.parseInt(stringTokenizer.nextToken());
        if (stringTokenizer.hasMoreTokens()) {
            return false;
        }
        addConstraint(map, canonicalAlgorithm2, new KeySizeConstraint(parse, parseInt));
        return true;
    }

    private boolean checkConstraints(Set<BCCryptoPrimitive> set, String str, Key key, AlgorithmParameters algorithmParameters) {
        checkPrimitives(set);
        checkKey(key);
        if ((!JsseUtils.isNameSpecified(str) || permits(set, str, algorithmParameters)) && permits(set, JsseUtils.getKeyAlgorithm(key), null)) {
            for (Constraint constraint : getConstraints(getConstraintsAlgorithm(key))) {
                if (!constraint.permits(key)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DisabledAlgorithmConstraints create(AlgorithmDecomposer algorithmDecomposer, String str, String str2) {
        String[] stringArraySecurityProperty = PropertyUtils.getStringArraySecurityProperty(str, str2);
        if (stringArraySecurityProperty == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < stringArraySecurityProperty.length; i++) {
            if (!addConstraint(hashSet, hashMap, stringArraySecurityProperty[i])) {
                Logger logger = LOG;
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Ignoring unsupported entry in '", str, "': ");
                outline115.append(stringArraySecurityProperty[i]);
                logger.warning(outline115.toString());
            }
        }
        return new DisabledAlgorithmConstraints(algorithmDecomposer, Collections.unmodifiableSet(hashSet), Collections.unmodifiableMap(hashMap));
    }

    private static String getCanonicalAlgorithm(String str) {
        return "DiffieHellman".equalsIgnoreCase(str) ? "DH" : str.toUpperCase(Locale.ENGLISH).replace("SHA-", "SHA");
    }

    private List<Constraint> getConstraints(String str) {
        List<Constraint> list;
        return (str == null || (list = this.constraintsMap.get(str)) == null) ? Collections.emptyList() : list;
    }

    private static String getConstraintsAlgorithm(String str, AlgorithmParameters algorithmParameters) {
        String algorithm;
        if (algorithmParameters == null || (algorithm = algorithmParameters.getAlgorithm()) == null) {
            return null;
        }
        String canonicalAlgorithm = getCanonicalAlgorithm(str);
        if (!canonicalAlgorithm.equalsIgnoreCase(getCanonicalAlgorithm(algorithm))) {
            return null;
        }
        return canonicalAlgorithm;
    }

    private static String getConstraintsAlgorithm(Key key) {
        String keyAlgorithm;
        if (key == null || (keyAlgorithm = JsseUtils.getKeyAlgorithm(key)) == null) {
            return null;
        }
        return getCanonicalAlgorithm(keyAlgorithm);
    }

    @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
    public final boolean permits(Set<BCCryptoPrimitive> set, String str, AlgorithmParameters algorithmParameters) {
        checkPrimitives(set);
        checkAlgorithmName(str);
        if (containsAnyPartIgnoreCase(this.disabledAlgorithms, str)) {
            return false;
        }
        for (Constraint constraint : getConstraints(getConstraintsAlgorithm(str, algorithmParameters))) {
            if (!constraint.permits(algorithmParameters)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
    public final boolean permits(Set<BCCryptoPrimitive> set, String str, Key key, AlgorithmParameters algorithmParameters) {
        checkAlgorithmName(str);
        return checkConstraints(set, str, key, algorithmParameters);
    }

    @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
    public final boolean permits(Set<BCCryptoPrimitive> set, Key key) {
        return checkConstraints(set, null, key, null);
    }
}
