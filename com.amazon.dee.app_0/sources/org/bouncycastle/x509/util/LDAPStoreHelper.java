package org.bouncycastle.x509.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificatePair;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.bouncycastle.jce.provider.X509AttrCertParser;
import org.bouncycastle.jce.provider.X509CRLParser;
import org.bouncycastle.jce.provider.X509CertPairParser;
import org.bouncycastle.jce.provider.X509CertParser;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509CRLStoreSelector;
import org.bouncycastle.x509.X509CertPairStoreSelector;
import org.bouncycastle.x509.X509CertStoreSelector;
import org.bouncycastle.x509.X509CertificatePair;
/* loaded from: classes5.dex */
public class LDAPStoreHelper {
    private static String LDAP_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String REFERRALS_IGNORE = "ignore";
    private static final String SEARCH_SECURITY_LEVEL = "none";
    private static final String URL_CONTEXT_PREFIX = "com.sun.jndi.url";
    private static int cacheSize = 32;
    private static long lifeTime = 60000;
    private Map cacheMap = new HashMap(cacheSize);
    private X509LDAPCertStoreParameters params;

    public LDAPStoreHelper(X509LDAPCertStoreParameters x509LDAPCertStoreParameters) {
        this.params = x509LDAPCertStoreParameters;
    }

    private synchronized void addToCache(String str, List list) {
        Map map;
        Date date = new Date(System.currentTimeMillis());
        ArrayList arrayList = new ArrayList();
        arrayList.add(date);
        arrayList.add(list);
        if (this.cacheMap.containsKey(str)) {
            map = this.cacheMap;
        } else {
            if (this.cacheMap.size() >= cacheSize) {
                long time = date.getTime();
                Object obj = null;
                for (Map.Entry entry : this.cacheMap.entrySet()) {
                    long time2 = ((Date) ((List) entry.getValue()).get(0)).getTime();
                    if (time2 < time) {
                        obj = entry.getKey();
                        time = time2;
                    }
                }
                this.cacheMap.remove(obj);
            }
            map = this.cacheMap;
        }
        map.put(str, arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009c A[LOOP:0: B:28:0x009c->B:30:0x009f, LOOP_START, PHI: r4 
      PHI: (r4v5 int) = (r4v1 int), (r4v6 int) binds: [B:27:0x009a, B:30:0x009f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00cb A[LOOP:1: B:36:0x00c5->B:38:0x00cb, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List attrCertSubjectSerialSearch(org.bouncycastle.x509.X509AttributeCertStoreSelector r6, java.lang.String[] r7, java.lang.String[] r8, java.lang.String[] r9) throws org.bouncycastle.util.StoreException {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r6.getHolder()
            r3 = 0
            if (r2 == 0) goto L3d
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r6.getHolder()
            java.math.BigInteger r2 = r2.getSerialNumber()
            if (r2 == 0) goto L2a
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r6.getHolder()
            java.math.BigInteger r2 = r2.getSerialNumber()
            java.lang.String r2 = r2.toString()
            r1.add(r2)
        L2a:
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r6.getHolder()
            java.security.Principal[] r2 = r2.getEntityNames()
            if (r2 == 0) goto L3d
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r6.getHolder()
            java.security.Principal[] r2 = r2.getEntityNames()
            goto L3e
        L3d:
            r2 = r3
        L3e:
            org.bouncycastle.x509.X509AttributeCertificate r4 = r6.getAttributeCert()
            if (r4 == 0) goto L6d
            org.bouncycastle.x509.X509AttributeCertificate r4 = r6.getAttributeCert()
            org.bouncycastle.x509.AttributeCertificateHolder r4 = r4.getHolder()
            java.security.Principal[] r4 = r4.getEntityNames()
            if (r4 == 0) goto L5e
            org.bouncycastle.x509.X509AttributeCertificate r2 = r6.getAttributeCert()
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r2.getHolder()
            java.security.Principal[] r2 = r2.getEntityNames()
        L5e:
            org.bouncycastle.x509.X509AttributeCertificate r4 = r6.getAttributeCert()
            java.math.BigInteger r4 = r4.getSerialNumber()
            java.lang.String r4 = r4.toString()
            r1.add(r4)
        L6d:
            r4 = 0
            if (r2 == 0) goto L87
            r3 = r2[r4]
            boolean r3 = r3 instanceof javax.security.auth.x500.X500Principal
            if (r3 == 0) goto L81
            r2 = r2[r4]
            javax.security.auth.x500.X500Principal r2 = (javax.security.auth.x500.X500Principal) r2
            java.lang.String r3 = "RFC1779"
            java.lang.String r3 = r2.getName(r3)
            goto L87
        L81:
            r2 = r2[r4]
            java.lang.String r3 = r2.getName()
        L87:
            java.math.BigInteger r2 = r6.getSerialNumber()
            if (r2 == 0) goto L98
            java.math.BigInteger r6 = r6.getSerialNumber()
            java.lang.String r6 = r6.toString()
            r1.add(r6)
        L98:
            java.lang.String r6 = "*"
            if (r3 == 0) goto Lb3
        L9c:
            int r2 = r9.length
            if (r4 >= r2) goto Lb3
            r2 = r9[r4]
            java.lang.String r2 = r5.parseDN(r3, r2)
            java.lang.String r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r6, r2, r6)
            java.util.List r2 = r5.search(r8, r2, r7)
            r0.addAll(r2)
            int r4 = r4 + 1
            goto L9c
        Lb3:
            int r9 = r1.size()
            if (r9 <= 0) goto Le3
            org.bouncycastle.jce.X509LDAPCertStoreParameters r9 = r5.params
            java.lang.String r9 = r9.getSearchForSerialNumberIn()
            if (r9 == 0) goto Le3
            java.util.Iterator r9 = r1.iterator()
        Lc5:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto Le3
            java.lang.Object r2 = r9.next()
            java.lang.String r2 = (java.lang.String) r2
            org.bouncycastle.jce.X509LDAPCertStoreParameters r4 = r5.params
            java.lang.String r4 = r4.getSearchForSerialNumberIn()
            java.lang.String[] r4 = r5.splitString(r4)
            java.util.List r2 = r5.search(r4, r2, r7)
            r0.addAll(r2)
            goto Lc5
        Le3:
            int r9 = r1.size()
            if (r9 != 0) goto Lf2
            if (r3 != 0) goto Lf2
            java.util.List r6 = r5.search(r8, r6, r7)
            r0.addAll(r6)
        Lf2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.util.LDAPStoreHelper.attrCertSubjectSerialSearch(org.bouncycastle.x509.X509AttributeCertStoreSelector, java.lang.String[], java.lang.String[], java.lang.String[]):java.util.List");
    }

    private List cRLIssuerSearch(X509CRLStoreSelector x509CRLStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        HashSet<X500Principal> hashSet = new HashSet();
        if (x509CRLStoreSelector.getIssuers() != null) {
            hashSet.addAll(x509CRLStoreSelector.getIssuers());
        }
        if (x509CRLStoreSelector.getCertificateChecking() != null) {
            hashSet.add(getCertificateIssuer(x509CRLStoreSelector.getCertificateChecking()));
        }
        if (x509CRLStoreSelector.getAttrCertificateChecking() != null) {
            Principal[] principals = x509CRLStoreSelector.getAttrCertificateChecking().getIssuer().getPrincipals();
            for (int i = 0; i < principals.length; i++) {
                if (principals[i] instanceof X500Principal) {
                    hashSet.add(principals[i]);
                }
            }
        }
        String str = null;
        for (X500Principal x500Principal : hashSet) {
            str = x500Principal.getName("RFC1779");
            for (String str2 : strArr3) {
                arrayList.addAll(search(strArr2, GeneratedOutlineSupport1.outline75("*", parseDN(str, str2), "*"), strArr));
            }
        }
        if (str == null) {
            arrayList.addAll(search(strArr2, "*", strArr));
        }
        return arrayList;
    }

    private List certSubjectSerialSearch(X509CertStoreSelector x509CertStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        String subjectAsString = getSubjectAsString(x509CertStoreSelector);
        String bigInteger = x509CertStoreSelector.getSerialNumber() != null ? x509CertStoreSelector.getSerialNumber().toString() : null;
        if (x509CertStoreSelector.getCertificate() != null) {
            subjectAsString = x509CertStoreSelector.getCertificate().getSubjectX500Principal().getName("RFC1779");
            bigInteger = x509CertStoreSelector.getCertificate().getSerialNumber().toString();
        }
        if (subjectAsString != null) {
            for (String str : strArr3) {
                arrayList.addAll(search(strArr2, GeneratedOutlineSupport1.outline75("*", parseDN(subjectAsString, str), "*"), strArr));
            }
        }
        if (bigInteger != null && this.params.getSearchForSerialNumberIn() != null) {
            arrayList.addAll(search(splitString(this.params.getSearchForSerialNumberIn()), bigInteger, strArr));
        }
        if (bigInteger == null && subjectAsString == null) {
            arrayList.addAll(search(strArr2, "*", strArr));
        }
        return arrayList;
    }

    private DirContext connectLDAP() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", LDAP_PROVIDER);
        properties.setProperty("java.naming.batchsize", "0");
        properties.setProperty("java.naming.provider.url", this.params.getLdapURL());
        properties.setProperty("java.naming.factory.url.pkgs", URL_CONTEXT_PREFIX);
        properties.setProperty("java.naming.referral", REFERRALS_IGNORE);
        properties.setProperty("java.naming.security.authentication", "none");
        return new InitialDirContext(properties);
    }

    private Set createAttributeCertificates(List list, X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        Iterator it2 = list.iterator();
        X509AttrCertParser x509AttrCertParser = new X509AttrCertParser();
        while (it2.hasNext()) {
            try {
                x509AttrCertParser.engineInit(new ByteArrayInputStream((byte[]) it2.next()));
                X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) x509AttrCertParser.engineRead();
                if (x509AttributeCertStoreSelector.match(x509AttributeCertificate)) {
                    hashSet.add(x509AttributeCertificate);
                }
            } catch (StreamParsingException unused) {
            }
        }
        return hashSet;
    }

    private Set createCRLs(List list, X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        X509CRLParser x509CRLParser = new X509CRLParser();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            try {
                x509CRLParser.engineInit(new ByteArrayInputStream((byte[]) it2.next()));
                X509CRL x509crl = (X509CRL) x509CRLParser.engineRead();
                if (x509CRLStoreSelector.match((Object) x509crl)) {
                    hashSet.add(x509crl);
                }
            } catch (StreamParsingException unused) {
            }
        }
        return hashSet;
    }

    private Set createCerts(List list, X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        Iterator it2 = list.iterator();
        X509CertParser x509CertParser = new X509CertParser();
        while (it2.hasNext()) {
            try {
                x509CertParser.engineInit(new ByteArrayInputStream((byte[]) it2.next()));
                X509Certificate x509Certificate = (X509Certificate) x509CertParser.engineRead();
                if (x509CertStoreSelector.match((Object) x509Certificate)) {
                    hashSet.add(x509Certificate);
                }
            } catch (Exception unused) {
            }
        }
        return hashSet;
    }

    private Set createCrossCertificatePairs(List list, X509CertPairStoreSelector x509CertPairStoreSelector) throws StoreException {
        X509CertificatePair x509CertificatePair;
        HashSet hashSet = new HashSet();
        int i = 0;
        while (i < list.size()) {
            try {
                try {
                    X509CertPairParser x509CertPairParser = new X509CertPairParser();
                    x509CertPairParser.engineInit(new ByteArrayInputStream((byte[]) list.get(i)));
                    x509CertificatePair = (X509CertificatePair) x509CertPairParser.engineRead();
                } catch (StreamParsingException unused) {
                    int i2 = i + 1;
                    i = i2;
                    x509CertificatePair = new X509CertificatePair(new CertificatePair(Certificate.getInstance(new ASN1InputStream((byte[]) list.get(i)).readObject()), Certificate.getInstance(new ASN1InputStream((byte[]) list.get(i2)).readObject())));
                }
                if (x509CertPairStoreSelector.match(x509CertificatePair)) {
                    hashSet.add(x509CertificatePair);
                }
            } catch (IOException | CertificateParsingException unused2) {
            }
            i++;
        }
        return hashSet;
    }

    private List crossCertificatePairSubjectSearch(X509CertPairStoreSelector x509CertPairStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        String subjectAsString = x509CertPairStoreSelector.getForwardSelector() != null ? getSubjectAsString(x509CertPairStoreSelector.getForwardSelector()) : null;
        if (x509CertPairStoreSelector.getCertPair() != null && x509CertPairStoreSelector.getCertPair().getForward() != null) {
            subjectAsString = x509CertPairStoreSelector.getCertPair().getForward().getSubjectX500Principal().getName("RFC1779");
        }
        if (subjectAsString != null) {
            for (String str : strArr3) {
                arrayList.addAll(search(strArr2, GeneratedOutlineSupport1.outline75("*", parseDN(subjectAsString, str), "*"), strArr));
            }
        }
        if (subjectAsString == null) {
            arrayList.addAll(search(strArr2, "*", strArr));
        }
        return arrayList;
    }

    private X500Principal getCertificateIssuer(X509Certificate x509Certificate) {
        return x509Certificate.getIssuerX500Principal();
    }

    private List getFromCache(String str) {
        List list = (List) this.cacheMap.get(str);
        long currentTimeMillis = System.currentTimeMillis();
        if (list == null || ((Date) list.get(0)).getTime() < currentTimeMillis - lifeTime) {
            return null;
        }
        return (List) list.get(1);
    }

    private String getSubjectAsString(X509CertStoreSelector x509CertStoreSelector) {
        try {
            byte[] subjectAsBytes = x509CertStoreSelector.getSubjectAsBytes();
            if (subjectAsBytes == null) {
                return null;
            }
            return new X500Principal(subjectAsBytes).getName("RFC1779");
        } catch (IOException e) {
            throw new StoreException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("exception processing name: ")), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0047 -> B:9:0x0035). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String parseDN(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = r5.toLowerCase()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r6.toLowerCase()
            r1.append(r2)
            java.lang.String r2 = "="
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            int r0 = r0.indexOf(r1)
            r1 = -1
            if (r0 != r1) goto L23
            java.lang.String r5 = ""
            return r5
        L23:
            int r6 = r6.length()
            int r6 = r6 + r0
            java.lang.String r5 = r5.substring(r6)
            r6 = 44
            int r0 = r5.indexOf(r6)
            if (r0 != r1) goto L35
            goto L47
        L35:
            int r2 = r0 + (-1)
            char r2 = r5.charAt(r2)
            r3 = 92
            if (r2 != r3) goto L4c
            int r0 = r0 + 1
            int r0 = r5.indexOf(r6, r0)
            if (r0 != r1) goto L35
        L47:
            int r0 = r5.length()
            goto L35
        L4c:
            r6 = 0
            java.lang.String r5 = r5.substring(r6, r0)
            r0 = 61
            int r0 = r5.indexOf(r0)
            r1 = 1
            int r0 = r0 + r1
            java.lang.String r5 = r5.substring(r0)
            char r0 = r5.charAt(r6)
            r2 = 32
            if (r0 != r2) goto L69
            java.lang.String r5 = r5.substring(r1)
        L69:
            java.lang.String r0 = "\""
            boolean r2 = r5.startsWith(r0)
            if (r2 == 0) goto L75
            java.lang.String r5 = r5.substring(r1)
        L75:
            boolean r0 = r5.endsWith(r0)
            if (r0 == 0) goto L7f
            java.lang.String r5 = com.android.tools.r8.GeneratedOutlineSupport1.outline51(r5, r1, r6)
        L7f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.util.LDAPStoreHelper.parseDN(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b3, code lost:
        if (r3 != null) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List search(java.lang.String[] r11, java.lang.String r12, java.lang.String[] r13) throws org.bouncycastle.util.StoreException {
        /*
            r10 = this;
            java.lang.String r0 = "("
            java.lang.String r1 = "(|"
            r2 = 0
            r3 = 0
            java.lang.String r4 = ""
            java.lang.String r5 = ")"
            if (r11 != 0) goto Le
            r11 = r3
            goto L30
        Le:
            java.lang.String r6 = "**"
            boolean r6 = r12.equals(r6)
            if (r6 == 0) goto L18
            java.lang.String r12 = "*"
        L18:
            r6 = r2
            r7 = r4
        L1a:
            int r8 = r11.length
            if (r6 >= r8) goto L2c
            java.lang.StringBuilder r7 = com.android.tools.r8.GeneratedOutlineSupport1.outline113(r7, r0)
            r8 = r11[r6]
            java.lang.String r9 = "="
            java.lang.String r7 = com.android.tools.r8.GeneratedOutlineSupport1.outline93(r7, r8, r9, r12, r5)
            int r6 = r6 + 1
            goto L1a
        L2c:
            java.lang.String r11 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r1, r7, r5)
        L30:
            r12 = r4
        L31:
            int r6 = r13.length
            if (r2 >= r6) goto L43
            java.lang.StringBuilder r12 = com.android.tools.r8.GeneratedOutlineSupport1.outline113(r12, r0)
            r6 = r13[r2]
            java.lang.String r7 = "=*)"
            java.lang.String r12 = com.android.tools.r8.GeneratedOutlineSupport1.outline91(r12, r6, r7)
            int r2 = r2 + 1
            goto L31
        L43:
            java.lang.String r12 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r1, r12, r5)
            java.lang.String r0 = "(&"
            java.lang.String r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline77(r0, r11, r4, r12, r5)
            if (r11 != 0) goto L50
            goto L51
        L50:
            r12 = r0
        L51:
            java.util.List r11 = r10.getFromCache(r12)
            if (r11 == 0) goto L58
            return r11
        L58:
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            javax.naming.directory.DirContext r3 = r10.connectLDAP()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            javax.naming.directory.SearchControls r0 = new javax.naming.directory.SearchControls     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            r0.<init>()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            r1 = 2
            r0.setSearchScope(r1)     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            r1 = 0
            r0.setCountLimit(r1)     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            r0.setReturningAttributes(r13)     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            org.bouncycastle.jce.X509LDAPCertStoreParameters r13 = r10.params     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            java.lang.String r13 = r13.getBaseDN()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            javax.naming.NamingEnumeration r13 = r3.search(r13, r12, r0)     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
        L7c:
            boolean r0 = r13.hasMoreElements()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            if (r0 == 0) goto La8
            java.lang.Object r0 = r13.next()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            javax.naming.directory.SearchResult r0 = (javax.naming.directory.SearchResult) r0     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            javax.naming.directory.Attributes r0 = r0.getAttributes()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            javax.naming.NamingEnumeration r0 = r0.getAll()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            javax.naming.directory.Attribute r0 = (javax.naming.directory.Attribute) r0     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            javax.naming.NamingEnumeration r0 = r0.getAll()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
        L9a:
            boolean r1 = r0.hasMore()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            if (r1 == 0) goto L7c
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            r11.add(r1)     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            goto L9a
        La8:
            r10.addToCache(r12, r11)     // Catch: java.lang.Throwable -> Lac javax.naming.NamingException -> Lb3
            goto Lb5
        Lac:
            r11 = move-exception
            if (r3 == 0) goto Lb2
            r3.close()     // Catch: java.lang.Exception -> Lb2
        Lb2:
            throw r11
        Lb3:
            if (r3 == 0) goto Lb8
        Lb5:
            r3.close()     // Catch: java.lang.Exception -> Lb8
        Lb8:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.util.LDAPStoreHelper.search(java.lang.String[], java.lang.String, java.lang.String[]):java.util.List");
    }

    private String[] splitString(String str) {
        return str.split("\\s+");
    }

    public Collection getAACertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAACertificateAttribute());
        String[] splitString2 = splitString(this.params.getLdapAACertificateAttributeName());
        String[] splitString3 = splitString(this.params.getAACertificateSubjectAttributeName());
        Set createAttributeCertificates = createAttributeCertificates(attrCertSubjectSerialSearch(x509AttributeCertStoreSelector, splitString, splitString2, splitString3), x509AttributeCertStoreSelector);
        if (createAttributeCertificates.size() == 0) {
            createAttributeCertificates.addAll(createAttributeCertificates(attrCertSubjectSerialSearch(new X509AttributeCertStoreSelector(), splitString, splitString2, splitString3), x509AttributeCertStoreSelector));
        }
        return createAttributeCertificates;
    }

    public Collection getAttributeAuthorityRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAttributeAuthorityRevocationListAttribute());
        String[] splitString2 = splitString(this.params.getLdapAttributeAuthorityRevocationListAttributeName());
        String[] splitString3 = splitString(this.params.getAttributeAuthorityRevocationListIssuerAttributeName());
        Set createCRLs = createCRLs(cRLIssuerSearch(x509CRLStoreSelector, splitString, splitString2, splitString3), x509CRLStoreSelector);
        if (createCRLs.size() == 0) {
            createCRLs.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), splitString, splitString2, splitString3), x509CRLStoreSelector));
        }
        return createCRLs;
    }

    public Collection getAttributeCertificateAttributes(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAttributeCertificateAttributeAttribute());
        String[] splitString2 = splitString(this.params.getLdapAttributeCertificateAttributeAttributeName());
        String[] splitString3 = splitString(this.params.getAttributeCertificateAttributeSubjectAttributeName());
        Set createAttributeCertificates = createAttributeCertificates(attrCertSubjectSerialSearch(x509AttributeCertStoreSelector, splitString, splitString2, splitString3), x509AttributeCertStoreSelector);
        if (createAttributeCertificates.size() == 0) {
            createAttributeCertificates.addAll(createAttributeCertificates(attrCertSubjectSerialSearch(new X509AttributeCertStoreSelector(), splitString, splitString2, splitString3), x509AttributeCertStoreSelector));
        }
        return createAttributeCertificates;
    }

    public Collection getAttributeCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAttributeCertificateRevocationListAttribute());
        String[] splitString2 = splitString(this.params.getLdapAttributeCertificateRevocationListAttributeName());
        String[] splitString3 = splitString(this.params.getAttributeCertificateRevocationListIssuerAttributeName());
        Set createCRLs = createCRLs(cRLIssuerSearch(x509CRLStoreSelector, splitString, splitString2, splitString3), x509CRLStoreSelector);
        if (createCRLs.size() == 0) {
            createCRLs.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), splitString, splitString2, splitString3), x509CRLStoreSelector));
        }
        return createCRLs;
    }

    public Collection getAttributeDescriptorCertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAttributeDescriptorCertificateAttribute());
        String[] splitString2 = splitString(this.params.getLdapAttributeDescriptorCertificateAttributeName());
        String[] splitString3 = splitString(this.params.getAttributeDescriptorCertificateSubjectAttributeName());
        Set createAttributeCertificates = createAttributeCertificates(attrCertSubjectSerialSearch(x509AttributeCertStoreSelector, splitString, splitString2, splitString3), x509AttributeCertStoreSelector);
        if (createAttributeCertificates.size() == 0) {
            createAttributeCertificates.addAll(createAttributeCertificates(attrCertSubjectSerialSearch(new X509AttributeCertStoreSelector(), splitString, splitString2, splitString3), x509AttributeCertStoreSelector));
        }
        return createAttributeCertificates;
    }

    public Collection getAuthorityRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getAuthorityRevocationListAttribute());
        String[] splitString2 = splitString(this.params.getLdapAuthorityRevocationListAttributeName());
        String[] splitString3 = splitString(this.params.getAuthorityRevocationListIssuerAttributeName());
        Set createCRLs = createCRLs(cRLIssuerSearch(x509CRLStoreSelector, splitString, splitString2, splitString3), x509CRLStoreSelector);
        if (createCRLs.size() == 0) {
            createCRLs.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), splitString, splitString2, splitString3), x509CRLStoreSelector));
        }
        return createCRLs;
    }

    public Collection getCACertificates(X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getCACertificateAttribute());
        String[] splitString2 = splitString(this.params.getLdapCACertificateAttributeName());
        String[] splitString3 = splitString(this.params.getCACertificateSubjectAttributeName());
        Set createCerts = createCerts(certSubjectSerialSearch(x509CertStoreSelector, splitString, splitString2, splitString3), x509CertStoreSelector);
        if (createCerts.size() == 0) {
            createCerts.addAll(createCerts(certSubjectSerialSearch(new X509CertStoreSelector(), splitString, splitString2, splitString3), x509CertStoreSelector));
        }
        return createCerts;
    }

    public Collection getCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getCertificateRevocationListAttribute());
        String[] splitString2 = splitString(this.params.getLdapCertificateRevocationListAttributeName());
        String[] splitString3 = splitString(this.params.getCertificateRevocationListIssuerAttributeName());
        Set createCRLs = createCRLs(cRLIssuerSearch(x509CRLStoreSelector, splitString, splitString2, splitString3), x509CRLStoreSelector);
        if (createCRLs.size() == 0) {
            createCRLs.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), splitString, splitString2, splitString3), x509CRLStoreSelector));
        }
        return createCRLs;
    }

    public Collection getCrossCertificatePairs(X509CertPairStoreSelector x509CertPairStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getCrossCertificateAttribute());
        String[] splitString2 = splitString(this.params.getLdapCrossCertificateAttributeName());
        String[] splitString3 = splitString(this.params.getCrossCertificateSubjectAttributeName());
        Set createCrossCertificatePairs = createCrossCertificatePairs(crossCertificatePairSubjectSearch(x509CertPairStoreSelector, splitString, splitString2, splitString3), x509CertPairStoreSelector);
        if (createCrossCertificatePairs.size() == 0) {
            X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
            X509CertPairStoreSelector x509CertPairStoreSelector2 = new X509CertPairStoreSelector();
            x509CertPairStoreSelector2.setForwardSelector(x509CertStoreSelector);
            x509CertPairStoreSelector2.setReverseSelector(x509CertStoreSelector);
            createCrossCertificatePairs.addAll(createCrossCertificatePairs(crossCertificatePairSubjectSearch(x509CertPairStoreSelector2, splitString, splitString2, splitString3), x509CertPairStoreSelector));
        }
        return createCrossCertificatePairs;
    }

    public Collection getDeltaCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getDeltaRevocationListAttribute());
        String[] splitString2 = splitString(this.params.getLdapDeltaRevocationListAttributeName());
        String[] splitString3 = splitString(this.params.getDeltaRevocationListIssuerAttributeName());
        Set createCRLs = createCRLs(cRLIssuerSearch(x509CRLStoreSelector, splitString, splitString2, splitString3), x509CRLStoreSelector);
        if (createCRLs.size() == 0) {
            createCRLs.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), splitString, splitString2, splitString3), x509CRLStoreSelector));
        }
        return createCRLs;
    }

    public Collection getUserCertificates(X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        String[] splitString = splitString(this.params.getUserCertificateAttribute());
        String[] splitString2 = splitString(this.params.getLdapUserCertificateAttributeName());
        String[] splitString3 = splitString(this.params.getUserCertificateSubjectAttributeName());
        Set createCerts = createCerts(certSubjectSerialSearch(x509CertStoreSelector, splitString, splitString2, splitString3), x509CertStoreSelector);
        if (createCerts.size() == 0) {
            createCerts.addAll(createCerts(certSubjectSerialSearch(new X509CertStoreSelector(), splitString, splitString2, splitString3), x509CertStoreSelector));
        }
        return createCerts;
    }
}
