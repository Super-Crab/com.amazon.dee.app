package org.bouncycastle.asn1.x500.style;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Hashtable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
/* loaded from: classes4.dex */
public class BCStyle extends AbstractX500NameStyle {
    public static final X500NameStyle INSTANCE;
    public static final ASN1ObjectIdentifier C = GeneratedOutlineSupport1.outline138("2.5.4.6");
    public static final ASN1ObjectIdentifier O = GeneratedOutlineSupport1.outline138("2.5.4.10");
    public static final ASN1ObjectIdentifier OU = GeneratedOutlineSupport1.outline138("2.5.4.11");
    public static final ASN1ObjectIdentifier T = GeneratedOutlineSupport1.outline138("2.5.4.12");
    public static final ASN1ObjectIdentifier CN = GeneratedOutlineSupport1.outline138("2.5.4.3");
    public static final ASN1ObjectIdentifier SN = GeneratedOutlineSupport1.outline138("2.5.4.5");
    public static final ASN1ObjectIdentifier STREET = GeneratedOutlineSupport1.outline138("2.5.4.9");
    public static final ASN1ObjectIdentifier SERIALNUMBER = GeneratedOutlineSupport1.outline138("2.5.4.5");
    public static final ASN1ObjectIdentifier L = GeneratedOutlineSupport1.outline138("2.5.4.7");
    public static final ASN1ObjectIdentifier ST = GeneratedOutlineSupport1.outline138("2.5.4.8");
    public static final ASN1ObjectIdentifier SURNAME = GeneratedOutlineSupport1.outline138("2.5.4.4");
    public static final ASN1ObjectIdentifier GIVENNAME = GeneratedOutlineSupport1.outline138("2.5.4.42");
    public static final ASN1ObjectIdentifier INITIALS = GeneratedOutlineSupport1.outline138("2.5.4.43");
    public static final ASN1ObjectIdentifier GENERATION = GeneratedOutlineSupport1.outline138("2.5.4.44");
    public static final ASN1ObjectIdentifier UNIQUE_IDENTIFIER = GeneratedOutlineSupport1.outline138("2.5.4.45");
    public static final ASN1ObjectIdentifier BUSINESS_CATEGORY = GeneratedOutlineSupport1.outline138("2.5.4.15");
    public static final ASN1ObjectIdentifier POSTAL_CODE = GeneratedOutlineSupport1.outline138("2.5.4.17");
    public static final ASN1ObjectIdentifier DN_QUALIFIER = GeneratedOutlineSupport1.outline138("2.5.4.46");
    public static final ASN1ObjectIdentifier PSEUDONYM = GeneratedOutlineSupport1.outline138("2.5.4.65");
    public static final ASN1ObjectIdentifier DATE_OF_BIRTH = GeneratedOutlineSupport1.outline138("1.3.6.1.5.5.7.9.1");
    public static final ASN1ObjectIdentifier PLACE_OF_BIRTH = GeneratedOutlineSupport1.outline138("1.3.6.1.5.5.7.9.2");
    public static final ASN1ObjectIdentifier GENDER = GeneratedOutlineSupport1.outline138("1.3.6.1.5.5.7.9.3");
    public static final ASN1ObjectIdentifier COUNTRY_OF_CITIZENSHIP = GeneratedOutlineSupport1.outline138("1.3.6.1.5.5.7.9.4");
    public static final ASN1ObjectIdentifier COUNTRY_OF_RESIDENCE = GeneratedOutlineSupport1.outline138("1.3.6.1.5.5.7.9.5");
    public static final ASN1ObjectIdentifier NAME_AT_BIRTH = GeneratedOutlineSupport1.outline138("1.3.36.8.3.14");
    public static final ASN1ObjectIdentifier POSTAL_ADDRESS = GeneratedOutlineSupport1.outline138("2.5.4.16");
    public static final ASN1ObjectIdentifier DMD_NAME = GeneratedOutlineSupport1.outline138("2.5.4.54");
    public static final ASN1ObjectIdentifier TELEPHONE_NUMBER = X509ObjectIdentifiers.id_at_telephoneNumber;
    public static final ASN1ObjectIdentifier NAME = X509ObjectIdentifiers.id_at_name;
    public static final ASN1ObjectIdentifier ORGANIZATION_IDENTIFIER = X509ObjectIdentifiers.id_at_organizationIdentifier;
    public static final ASN1ObjectIdentifier EmailAddress = PKCSObjectIdentifiers.pkcs_9_at_emailAddress;
    public static final ASN1ObjectIdentifier UnstructuredName = PKCSObjectIdentifiers.pkcs_9_at_unstructuredName;
    public static final ASN1ObjectIdentifier UnstructuredAddress = PKCSObjectIdentifiers.pkcs_9_at_unstructuredAddress;
    public static final ASN1ObjectIdentifier E = EmailAddress;
    public static final ASN1ObjectIdentifier DC = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
    public static final ASN1ObjectIdentifier UID = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
    private static final Hashtable DefaultSymbols = new Hashtable();
    private static final Hashtable DefaultLookUp = new Hashtable();
    protected final Hashtable defaultSymbols = AbstractX500NameStyle.copyHashTable(DefaultSymbols);
    protected final Hashtable defaultLookUp = AbstractX500NameStyle.copyHashTable(DefaultLookUp);

    static {
        DefaultSymbols.put(C, "C");
        DefaultSymbols.put(O, "O");
        DefaultSymbols.put(T, ExifInterface.GPS_DIRECTION_TRUE);
        DefaultSymbols.put(OU, "OU");
        DefaultSymbols.put(CN, "CN");
        DefaultSymbols.put(L, "L");
        DefaultSymbols.put(ST, "ST");
        DefaultSymbols.put(SERIALNUMBER, "SERIALNUMBER");
        DefaultSymbols.put(EmailAddress, ExifInterface.LONGITUDE_EAST);
        DefaultSymbols.put(DC, "DC");
        DefaultSymbols.put(UID, "UID");
        DefaultSymbols.put(STREET, "STREET");
        DefaultSymbols.put(SURNAME, "SURNAME");
        DefaultSymbols.put(GIVENNAME, "GIVENNAME");
        DefaultSymbols.put(INITIALS, "INITIALS");
        DefaultSymbols.put(GENERATION, "GENERATION");
        DefaultSymbols.put(UnstructuredAddress, "unstructuredAddress");
        DefaultSymbols.put(UnstructuredName, "unstructuredName");
        DefaultSymbols.put(UNIQUE_IDENTIFIER, "UniqueIdentifier");
        DefaultSymbols.put(DN_QUALIFIER, "DN");
        DefaultSymbols.put(PSEUDONYM, "Pseudonym");
        DefaultSymbols.put(POSTAL_ADDRESS, "PostalAddress");
        DefaultSymbols.put(NAME_AT_BIRTH, "NameAtBirth");
        DefaultSymbols.put(COUNTRY_OF_CITIZENSHIP, "CountryOfCitizenship");
        DefaultSymbols.put(COUNTRY_OF_RESIDENCE, "CountryOfResidence");
        DefaultSymbols.put(GENDER, "Gender");
        DefaultSymbols.put(PLACE_OF_BIRTH, "PlaceOfBirth");
        DefaultSymbols.put(DATE_OF_BIRTH, "DateOfBirth");
        DefaultSymbols.put(POSTAL_CODE, "PostalCode");
        DefaultSymbols.put(BUSINESS_CATEGORY, "BusinessCategory");
        DefaultSymbols.put(TELEPHONE_NUMBER, "TelephoneNumber");
        DefaultSymbols.put(NAME, MAPCookie.KEY_NAME);
        DefaultSymbols.put(ORGANIZATION_IDENTIFIER, "organizationIdentifier");
        DefaultLookUp.put("c", C);
        DefaultLookUp.put("o", O);
        DefaultLookUp.put("t", T);
        DefaultLookUp.put("ou", OU);
        DefaultLookUp.put("cn", CN);
        DefaultLookUp.put("l", L);
        DefaultLookUp.put(Constants.TIMELINE_START_TIME_KEY, ST);
        DefaultLookUp.put("sn", SURNAME);
        DefaultLookUp.put("serialnumber", SERIALNUMBER);
        DefaultLookUp.put("street", STREET);
        DefaultLookUp.put("emailaddress", E);
        DefaultLookUp.put("dc", DC);
        DefaultLookUp.put("e", E);
        DefaultLookUp.put("uid", UID);
        DefaultLookUp.put("surname", SURNAME);
        DefaultLookUp.put("givenname", GIVENNAME);
        DefaultLookUp.put("initials", INITIALS);
        DefaultLookUp.put("generation", GENERATION);
        DefaultLookUp.put("unstructuredaddress", UnstructuredAddress);
        DefaultLookUp.put("unstructuredname", UnstructuredName);
        DefaultLookUp.put("uniqueidentifier", UNIQUE_IDENTIFIER);
        DefaultLookUp.put("dn", DN_QUALIFIER);
        DefaultLookUp.put("pseudonym", PSEUDONYM);
        DefaultLookUp.put("postaladdress", POSTAL_ADDRESS);
        DefaultLookUp.put("nameatbirth", NAME_AT_BIRTH);
        DefaultLookUp.put("countryofcitizenship", COUNTRY_OF_CITIZENSHIP);
        DefaultLookUp.put("countryofresidence", COUNTRY_OF_RESIDENCE);
        DefaultLookUp.put("gender", GENDER);
        DefaultLookUp.put("placeofbirth", PLACE_OF_BIRTH);
        DefaultLookUp.put("dateofbirth", DATE_OF_BIRTH);
        DefaultLookUp.put("postalcode", POSTAL_CODE);
        DefaultLookUp.put("businesscategory", BUSINESS_CATEGORY);
        DefaultLookUp.put("telephonenumber", TELEPHONE_NUMBER);
        DefaultLookUp.put("name", NAME);
        DefaultLookUp.put("organizationidentifier", ORGANIZATION_IDENTIFIER);
        INSTANCE = new BCStyle();
    }

    @Override // org.bouncycastle.asn1.x500.X500NameStyle
    public ASN1ObjectIdentifier attrNameToOID(String str) {
        return IETFUtils.decodeAttrName(str, this.defaultLookUp);
    }

    @Override // org.bouncycastle.asn1.x500.style.AbstractX500NameStyle
    protected ASN1Encodable encodeStringValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        return (aSN1ObjectIdentifier.equals((ASN1Primitive) EmailAddress) || aSN1ObjectIdentifier.equals((ASN1Primitive) DC)) ? new DERIA5String(str) : aSN1ObjectIdentifier.equals((ASN1Primitive) DATE_OF_BIRTH) ? new ASN1GeneralizedTime(str) : (aSN1ObjectIdentifier.equals((ASN1Primitive) C) || aSN1ObjectIdentifier.equals((ASN1Primitive) SN) || aSN1ObjectIdentifier.equals((ASN1Primitive) DN_QUALIFIER) || aSN1ObjectIdentifier.equals((ASN1Primitive) TELEPHONE_NUMBER)) ? new DERPrintableString(str) : super.encodeStringValue(aSN1ObjectIdentifier, str);
    }

    @Override // org.bouncycastle.asn1.x500.X500NameStyle
    public RDN[] fromString(String str) {
        return IETFUtils.rDNsFromString(str, this);
    }

    @Override // org.bouncycastle.asn1.x500.X500NameStyle
    public String[] oidToAttrNames(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return IETFUtils.findAttrNamesForOID(aSN1ObjectIdentifier, this.defaultLookUp);
    }

    @Override // org.bouncycastle.asn1.x500.X500NameStyle
    public String oidToDisplayName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) DefaultSymbols.get(aSN1ObjectIdentifier);
    }

    @Override // org.bouncycastle.asn1.x500.X500NameStyle
    public String toString(X500Name x500Name) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        for (RDN rdn : x500Name.getRDNs()) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(JsonReaderKt.COMMA);
            }
            IETFUtils.appendRDN(stringBuffer, rdn, this.defaultSymbols);
        }
        return stringBuffer.toString();
    }
}
