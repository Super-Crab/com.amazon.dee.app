package org.bouncycastle.jsse.provider;

import java.security.cert.CertPathBuilder;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXRevocationChecker;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIMatcher;
import javax.net.ssl.SNIServerName;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCSNIMatcher;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.jsse.provider.JsseUtils;
/* loaded from: classes4.dex */
abstract class JsseUtils_8 extends JsseUtils_7 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class ExportSNIMatcher extends SNIMatcher {
        private final BCSNIMatcher matcher;

        ExportSNIMatcher(BCSNIMatcher bCSNIMatcher) {
            super(bCSNIMatcher.getType());
            this.matcher = bCSNIMatcher;
        }

        @Override // javax.net.ssl.SNIMatcher
        public boolean matches(SNIServerName sNIServerName) {
            return this.matcher.matches(JsseUtils_8.importSNIServerName(sNIServerName));
        }

        BCSNIMatcher unwrap() {
            return this.matcher;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class ImportSNIMatcher extends BCSNIMatcher {
        private final SNIMatcher matcher;

        ImportSNIMatcher(SNIMatcher sNIMatcher) {
            super(sNIMatcher.getType());
            this.matcher = sNIMatcher;
        }

        @Override // org.bouncycastle.jsse.BCSNIMatcher
        public boolean matches(BCSNIServerName bCSNIServerName) {
            return this.matcher.matches(JsseUtils_8.exportSNIServerName(bCSNIServerName));
        }

        SNIMatcher unwrap() {
            return this.matcher;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class UnknownServerName extends SNIServerName {
        UnknownServerName(int i, byte[] bArr) {
            super(i, bArr);
        }
    }

    JsseUtils_8() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addStatusResponses(CertPathBuilder certPathBuilder, PKIXBuilderParameters pKIXBuilderParameters, Map<X509Certificate, byte[]> map) {
        if (map.isEmpty()) {
            return;
        }
        List<PKIXCertPathChecker> certPathCheckers = pKIXBuilderParameters.getCertPathCheckers();
        PKIXRevocationChecker firstRevocationChecker = getFirstRevocationChecker(certPathCheckers);
        if (firstRevocationChecker != null) {
            Map<X509Certificate, byte[]> ocspResponses = firstRevocationChecker.getOcspResponses();
            if (putAnyAbsent(ocspResponses, map) <= 0) {
                return;
            }
            firstRevocationChecker.setOcspResponses(ocspResponses);
            pKIXBuilderParameters.setCertPathCheckers(certPathCheckers);
        } else if (!pKIXBuilderParameters.isRevocationEnabled()) {
        } else {
            PKIXRevocationChecker pKIXRevocationChecker = (PKIXRevocationChecker) certPathBuilder.getRevocationChecker();
            pKIXRevocationChecker.setOcspResponses(map);
            pKIXBuilderParameters.addCertPathChecker(pKIXRevocationChecker);
        }
    }

    static SNIMatcher exportSNIMatcher(BCSNIMatcher bCSNIMatcher) {
        if (bCSNIMatcher == null) {
            return null;
        }
        return bCSNIMatcher instanceof ImportSNIMatcher ? ((ImportSNIMatcher) bCSNIMatcher).unwrap() : new ExportSNIMatcher(bCSNIMatcher);
    }

    static List<SNIMatcher> exportSNIMatchers(Collection<BCSNIMatcher> collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (BCSNIMatcher bCSNIMatcher : collection) {
            arrayList.add(exportSNIMatcher(bCSNIMatcher));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object exportSNIMatchersDynamic(Collection<BCSNIMatcher> collection) {
        return exportSNIMatchers(collection);
    }

    static SNIServerName exportSNIServerName(BCSNIServerName bCSNIServerName) {
        if (bCSNIServerName == null) {
            return null;
        }
        int type = bCSNIServerName.getType();
        byte[] encoded = bCSNIServerName.getEncoded();
        return type != 0 ? new UnknownServerName(type, encoded) : new SNIHostName(encoded);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<SNIServerName> exportSNIServerNames(Collection<BCSNIServerName> collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (BCSNIServerName bCSNIServerName : collection) {
            arrayList.add(exportSNIServerName(bCSNIServerName));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object exportSNIServerNamesDynamic(Collection<BCSNIServerName> collection) {
        return exportSNIServerNames(collection);
    }

    static PKIXRevocationChecker getFirstRevocationChecker(List<PKIXCertPathChecker> list) {
        for (PKIXCertPathChecker pKIXCertPathChecker : list) {
            if (pKIXCertPathChecker instanceof PKIXRevocationChecker) {
                return (PKIXRevocationChecker) pKIXCertPathChecker;
            }
        }
        return null;
    }

    static BCSNIMatcher importSNIMatcher(SNIMatcher sNIMatcher) {
        if (sNIMatcher == null) {
            return null;
        }
        return sNIMatcher instanceof ExportSNIMatcher ? ((ExportSNIMatcher) sNIMatcher).unwrap() : new ImportSNIMatcher(sNIMatcher);
    }

    static List<BCSNIMatcher> importSNIMatchers(Collection<SNIMatcher> collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (SNIMatcher sNIMatcher : collection) {
            arrayList.add(importSNIMatcher(sNIMatcher));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<BCSNIMatcher> importSNIMatchersDynamic(Object obj) {
        return importSNIMatchers((Collection) obj);
    }

    static BCSNIServerName importSNIServerName(SNIServerName sNIServerName) {
        if (sNIServerName == null) {
            return null;
        }
        int type = sNIServerName.getType();
        byte[] encoded = sNIServerName.getEncoded();
        return type != 0 ? new JsseUtils.BCUnknownServerName(type, encoded) : new BCSNIHostName(encoded);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<BCSNIServerName> importSNIServerNames(Collection<SNIServerName> collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (SNIServerName sNIServerName : collection) {
            arrayList.add(importSNIServerName(sNIServerName));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<BCSNIServerName> importSNIServerNamesDynamic(Object obj) {
        return importSNIServerNames((Collection) obj);
    }

    static <K, V> int putAnyAbsent(Map<K, V> map, Map<K, V> map2) {
        int i = 0;
        for (Map.Entry<K, V> entry : map2.entrySet()) {
            if (map.putIfAbsent(entry.getKey(), entry.getValue()) == null) {
                i++;
            }
        }
        return i;
    }
}
