package org.bouncycastle.asn1.x509;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes4.dex */
public interface X509ObjectIdentifiers {
    public static final ASN1ObjectIdentifier commonName = GeneratedOutlineSupport1.outline138("2.5.4.3");
    public static final ASN1ObjectIdentifier countryName = GeneratedOutlineSupport1.outline138("2.5.4.6");
    public static final ASN1ObjectIdentifier localityName = GeneratedOutlineSupport1.outline138("2.5.4.7");
    public static final ASN1ObjectIdentifier stateOrProvinceName = GeneratedOutlineSupport1.outline138("2.5.4.8");
    public static final ASN1ObjectIdentifier organization = GeneratedOutlineSupport1.outline138("2.5.4.10");
    public static final ASN1ObjectIdentifier organizationalUnitName = GeneratedOutlineSupport1.outline138("2.5.4.11");
    public static final ASN1ObjectIdentifier id_at_telephoneNumber = GeneratedOutlineSupport1.outline138("2.5.4.20");
    public static final ASN1ObjectIdentifier id_at_name = GeneratedOutlineSupport1.outline138("2.5.4.41");
    public static final ASN1ObjectIdentifier id_at_organizationIdentifier = GeneratedOutlineSupport1.outline138("2.5.4.97");
    public static final ASN1ObjectIdentifier id_SHA1 = GeneratedOutlineSupport1.outline138("1.3.14.3.2.26");
    public static final ASN1ObjectIdentifier ripemd160 = GeneratedOutlineSupport1.outline138("1.3.36.3.2.1");
    public static final ASN1ObjectIdentifier ripemd160WithRSAEncryption = GeneratedOutlineSupport1.outline138("1.3.36.3.3.1.2");
    public static final ASN1ObjectIdentifier id_ea_rsa = GeneratedOutlineSupport1.outline138("2.5.8.1.1");
    public static final ASN1ObjectIdentifier id_pkix = new ASN1ObjectIdentifier("1.3.6.1.5.5.7");
    public static final ASN1ObjectIdentifier id_rsassa_pss_shake128 = id_pkix.branch("6.30");
    public static final ASN1ObjectIdentifier id_rsassa_pss_shake256 = id_pkix.branch("6.31");
    public static final ASN1ObjectIdentifier id_ecdsa_with_shake128 = id_pkix.branch("6.32");
    public static final ASN1ObjectIdentifier id_ecdsa_with_shake256 = id_pkix.branch("6.33");
    public static final ASN1ObjectIdentifier id_pe = id_pkix.branch("1");
    public static final ASN1ObjectIdentifier id_ce = new ASN1ObjectIdentifier("2.5.29");
    public static final ASN1ObjectIdentifier id_ad = id_pkix.branch("48");
    public static final ASN1ObjectIdentifier id_ad_caIssuers = id_ad.branch("2").intern();
    public static final ASN1ObjectIdentifier id_ad_ocsp = id_ad.branch("1").intern();
    public static final ASN1ObjectIdentifier ocspAccessMethod = id_ad_ocsp;
    public static final ASN1ObjectIdentifier crlAccessMethod = id_ad_caIssuers;
}
