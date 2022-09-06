package org.bouncycastle.jce.provider;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x509.CertificatePair;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
/* loaded from: classes4.dex */
public class X509LDAPCertStoreSpi extends CertStoreSpi {
    private static String LDAP_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String REFERRALS_IGNORE = "ignore";
    private static final String SEARCH_SECURITY_LEVEL = "none";
    private static final String URL_CONTEXT_PREFIX = "com.sun.jndi.url";
    private X509LDAPCertStoreParameters params;

    public X509LDAPCertStoreSpi(CertStoreParameters certStoreParameters) throws InvalidAlgorithmParameterException {
        super(certStoreParameters);
        if (certStoreParameters instanceof X509LDAPCertStoreParameters) {
            this.params = (X509LDAPCertStoreParameters) certStoreParameters;
            return;
        }
        throw new InvalidAlgorithmParameterException(X509LDAPCertStoreSpi.class.getName() + ": parameter must be a " + X509LDAPCertStoreParameters.class.getName() + " object\n" + certStoreParameters.toString());
    }

    private Set certSubjectSerialSearch(X509CertSelector x509CertSelector, String[] strArr, String str, String str2) throws CertStoreException {
        String name;
        Set search;
        HashSet hashSet = new HashSet();
        try {
            if (x509CertSelector.getSubjectAsBytes() == null && x509CertSelector.getSubjectAsString() == null && x509CertSelector.getCertificate() == null) {
                search = search(str, "*", strArr);
                hashSet.addAll(search);
                return hashSet;
            }
            String str3 = null;
            if (x509CertSelector.getCertificate() != null) {
                String name2 = x509CertSelector.getCertificate().getSubjectX500Principal().getName("RFC1779");
                str3 = x509CertSelector.getCertificate().getSerialNumber().toString();
                name = name2;
            } else {
                name = x509CertSelector.getSubjectAsBytes() != null ? new X500Principal(x509CertSelector.getSubjectAsBytes()).getName("RFC1779") : x509CertSelector.getSubjectAsString();
            }
            String parseDN = parseDN(name, str2);
            hashSet.addAll(search(str, "*" + parseDN + "*", strArr));
            if (str3 != null && this.params.getSearchForSerialNumberIn() != null) {
                String searchForSerialNumberIn = this.params.getSearchForSerialNumberIn();
                search = search(searchForSerialNumberIn, "*" + str3 + "*", strArr);
                hashSet.addAll(search);
            }
            return hashSet;
        } catch (IOException e) {
            throw new CertStoreException(GeneratedOutlineSupport1.outline65("exception processing selector: ", e));
        }
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

    private Set getCACertificates(X509CertSelector x509CertSelector) throws CertStoreException {
        String[] strArr = {this.params.getCACertificateAttribute()};
        Set certSubjectSerialSearch = certSubjectSerialSearch(x509CertSelector, strArr, this.params.getLdapCACertificateAttributeName(), this.params.getCACertificateSubjectAttributeName());
        if (certSubjectSerialSearch.isEmpty()) {
            certSubjectSerialSearch.addAll(search(null, "*", strArr));
        }
        return certSubjectSerialSearch;
    }

    private Set getCrossCertificates(X509CertSelector x509CertSelector) throws CertStoreException {
        String[] strArr = {this.params.getCrossCertificateAttribute()};
        Set certSubjectSerialSearch = certSubjectSerialSearch(x509CertSelector, strArr, this.params.getLdapCrossCertificateAttributeName(), this.params.getCrossCertificateSubjectAttributeName());
        if (certSubjectSerialSearch.isEmpty()) {
            certSubjectSerialSearch.addAll(search(null, "*", strArr));
        }
        return certSubjectSerialSearch;
    }

    private Set getEndCertificates(X509CertSelector x509CertSelector) throws CertStoreException {
        return certSubjectSerialSearch(x509CertSelector, new String[]{this.params.getUserCertificateAttribute()}, this.params.getLdapUserCertificateAttributeName(), this.params.getUserCertificateSubjectAttributeName());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x0031 -> B:5:0x001f). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String parseDN(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = r5.toLowerCase()
            java.lang.String r1 = r6.toLowerCase()
            int r0 = r0.indexOf(r1)
            int r6 = r6.length()
            int r6 = r6 + r0
            java.lang.String r5 = r5.substring(r6)
            r6 = 44
            int r0 = r5.indexOf(r6)
            r1 = -1
            if (r0 != r1) goto L1f
            goto L31
        L1f:
            int r2 = r0 + (-1)
            char r2 = r5.charAt(r2)
            r3 = 92
            if (r2 != r3) goto L36
            int r0 = r0 + 1
            int r0 = r5.indexOf(r6, r0)
            if (r0 != r1) goto L1f
        L31:
            int r0 = r5.length()
            goto L1f
        L36:
            r6 = 0
            java.lang.String r5 = r5.substring(r6, r0)
            r0 = 61
            int r0 = r5.indexOf(r0)
            r1 = 1
            int r0 = r0 + r1
            java.lang.String r5 = r5.substring(r0)
            char r0 = r5.charAt(r6)
            r2 = 32
            if (r0 != r2) goto L53
            java.lang.String r5 = r5.substring(r1)
        L53:
            java.lang.String r0 = "\""
            boolean r2 = r5.startsWith(r0)
            if (r2 == 0) goto L5f
            java.lang.String r5 = r5.substring(r1)
        L5f:
            boolean r0 = r5.endsWith(r0)
            if (r0 == 0) goto L69
            java.lang.String r5 = com.android.tools.r8.GeneratedOutlineSupport1.outline51(r5, r1, r6)
        L69:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.X509LDAPCertStoreSpi.parseDN(java.lang.String, java.lang.String):java.lang.String");
    }

    private Set search(String str, String str2, String[] strArr) throws CertStoreException {
        String[] strArr2;
        String outline75 = GeneratedOutlineSupport1.outline75(str, Config.Compare.EQUAL_TO, str2);
        DirContext dirContext = null;
        if (str == null) {
            outline75 = null;
        }
        HashSet hashSet = new HashSet();
        try {
            try {
                dirContext = connectLDAP();
                SearchControls searchControls = new SearchControls();
                searchControls.setSearchScope(2);
                searchControls.setCountLimit(0L);
                for (int i = 0; i < strArr.length; i++) {
                    searchControls.setReturningAttributes(new String[]{strArr[i]});
                    String str3 = "(&(" + outline75 + ")(" + strArr2[0] + "=*))";
                    if (outline75 == null) {
                        str3 = "(" + strArr2[0] + "=*)";
                    }
                    NamingEnumeration search = dirContext.search(this.params.getBaseDN(), str3, searchControls);
                    while (search.hasMoreElements()) {
                        NamingEnumeration all = ((Attribute) ((SearchResult) search.next()).getAttributes().getAll().next()).getAll();
                        while (all.hasMore()) {
                            hashSet.add(all.next());
                        }
                    }
                }
                if (dirContext != null) {
                    try {
                        dirContext.close();
                    } catch (Exception unused) {
                    }
                }
                return hashSet;
            } catch (Exception e) {
                throw new CertStoreException("Error getting results from LDAP directory " + e);
            }
        } catch (Throwable th) {
            if (dirContext != null) {
                try {
                    dirContext.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    @Override // java.security.cert.CertStoreSpi
    public Collection engineGetCRLs(CRLSelector cRLSelector) throws CertStoreException {
        String certificateRevocationListIssuerAttributeName;
        String name;
        String[] strArr = {this.params.getCertificateRevocationListAttribute()};
        if (cRLSelector instanceof X509CRLSelector) {
            X509CRLSelector x509CRLSelector = (X509CRLSelector) cRLSelector;
            HashSet hashSet = new HashSet();
            String ldapCertificateRevocationListAttributeName = this.params.getLdapCertificateRevocationListAttributeName();
            HashSet<byte[]> hashSet2 = new HashSet();
            if (x509CRLSelector.getIssuerNames() != null) {
                for (Object obj : x509CRLSelector.getIssuerNames()) {
                    if (obj instanceof String) {
                        certificateRevocationListIssuerAttributeName = this.params.getCertificateRevocationListIssuerAttributeName();
                        name = (String) obj;
                    } else {
                        certificateRevocationListIssuerAttributeName = this.params.getCertificateRevocationListIssuerAttributeName();
                        name = new X500Principal((byte[]) obj).getName("RFC1779");
                    }
                    hashSet2.addAll(search(ldapCertificateRevocationListAttributeName, GeneratedOutlineSupport1.outline75("*", parseDN(name, certificateRevocationListIssuerAttributeName), "*"), strArr));
                }
            } else {
                hashSet2.addAll(search(ldapCertificateRevocationListAttributeName, "*", strArr));
            }
            hashSet2.addAll(search(null, "*", strArr));
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance(KeyUtils.X509_CERITIFATE_FACTORY, BouncyCastleProvider.PROVIDER_NAME);
                for (byte[] bArr : hashSet2) {
                    CRL generateCRL = certificateFactory.generateCRL(new ByteArrayInputStream(bArr));
                    if (x509CRLSelector.match(generateCRL)) {
                        hashSet.add(generateCRL);
                    }
                }
                return hashSet;
            } catch (Exception e) {
                throw new CertStoreException(GeneratedOutlineSupport1.outline68("CRL cannot be constructed from LDAP result ", e));
            }
        }
        throw new CertStoreException("selector is not a X509CRLSelector");
    }

    @Override // java.security.cert.CertStoreSpi
    public Collection engineGetCertificates(CertSelector certSelector) throws CertStoreException {
        if (certSelector instanceof X509CertSelector) {
            X509CertSelector x509CertSelector = (X509CertSelector) certSelector;
            HashSet hashSet = new HashSet();
            Set<byte[]> endCertificates = getEndCertificates(x509CertSelector);
            endCertificates.addAll(getCACertificates(x509CertSelector));
            endCertificates.addAll(getCrossCertificates(x509CertSelector));
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance(KeyUtils.X509_CERITIFATE_FACTORY, BouncyCastleProvider.PROVIDER_NAME);
                for (byte[] bArr : endCertificates) {
                    if (bArr != null && bArr.length != 0) {
                        ArrayList<byte[]> arrayList = new ArrayList();
                        arrayList.add(bArr);
                        try {
                            CertificatePair certificatePair = CertificatePair.getInstance(new ASN1InputStream(bArr).readObject());
                            arrayList.clear();
                            if (certificatePair.getForward() != null) {
                                arrayList.add(certificatePair.getForward().getEncoded());
                            }
                            if (certificatePair.getReverse() != null) {
                                arrayList.add(certificatePair.getReverse().getEncoded());
                            }
                        } catch (IOException | IllegalArgumentException unused) {
                        }
                        for (byte[] bArr2 : arrayList) {
                            try {
                                Certificate generateCertificate = certificateFactory.generateCertificate(new ByteArrayInputStream(bArr2));
                                if (x509CertSelector.match(generateCertificate)) {
                                    hashSet.add(generateCertificate);
                                }
                            } catch (Exception unused2) {
                            }
                        }
                    }
                }
                return hashSet;
            } catch (Exception e) {
                throw new CertStoreException(GeneratedOutlineSupport1.outline68("certificate cannot be constructed from LDAP result: ", e));
            }
        }
        throw new CertStoreException("selector is not a X509CertSelector");
    }
}
