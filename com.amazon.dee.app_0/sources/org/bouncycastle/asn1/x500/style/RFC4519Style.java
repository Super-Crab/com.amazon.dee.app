package org.bouncycastle.asn1.x500.style;

import com.amazon.deecomms.common.Constants;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Hashtable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;
/* loaded from: classes4.dex */
public class RFC4519Style extends AbstractX500NameStyle {
    public static final X500NameStyle INSTANCE;
    public static final ASN1ObjectIdentifier businessCategory = GeneratedOutlineSupport1.outline138("2.5.4.15");
    public static final ASN1ObjectIdentifier c = GeneratedOutlineSupport1.outline138("2.5.4.6");
    public static final ASN1ObjectIdentifier cn = GeneratedOutlineSupport1.outline138("2.5.4.3");
    public static final ASN1ObjectIdentifier dc = GeneratedOutlineSupport1.outline138("0.9.2342.19200300.100.1.25");
    public static final ASN1ObjectIdentifier description = GeneratedOutlineSupport1.outline138("2.5.4.13");
    public static final ASN1ObjectIdentifier destinationIndicator = GeneratedOutlineSupport1.outline138("2.5.4.27");
    public static final ASN1ObjectIdentifier distinguishedName = GeneratedOutlineSupport1.outline138("2.5.4.49");
    public static final ASN1ObjectIdentifier dnQualifier = GeneratedOutlineSupport1.outline138("2.5.4.46");
    public static final ASN1ObjectIdentifier enhancedSearchGuide = GeneratedOutlineSupport1.outline138("2.5.4.47");
    public static final ASN1ObjectIdentifier facsimileTelephoneNumber = GeneratedOutlineSupport1.outline138("2.5.4.23");
    public static final ASN1ObjectIdentifier generationQualifier = GeneratedOutlineSupport1.outline138("2.5.4.44");
    public static final ASN1ObjectIdentifier givenName = GeneratedOutlineSupport1.outline138("2.5.4.42");
    public static final ASN1ObjectIdentifier houseIdentifier = GeneratedOutlineSupport1.outline138("2.5.4.51");
    public static final ASN1ObjectIdentifier initials = GeneratedOutlineSupport1.outline138("2.5.4.43");
    public static final ASN1ObjectIdentifier internationalISDNNumber = GeneratedOutlineSupport1.outline138("2.5.4.25");
    public static final ASN1ObjectIdentifier l = GeneratedOutlineSupport1.outline138("2.5.4.7");
    public static final ASN1ObjectIdentifier member = GeneratedOutlineSupport1.outline138("2.5.4.31");
    public static final ASN1ObjectIdentifier name = GeneratedOutlineSupport1.outline138("2.5.4.41");
    public static final ASN1ObjectIdentifier o = GeneratedOutlineSupport1.outline138("2.5.4.10");
    public static final ASN1ObjectIdentifier ou = GeneratedOutlineSupport1.outline138("2.5.4.11");
    public static final ASN1ObjectIdentifier owner = GeneratedOutlineSupport1.outline138("2.5.4.32");
    public static final ASN1ObjectIdentifier physicalDeliveryOfficeName = GeneratedOutlineSupport1.outline138("2.5.4.19");
    public static final ASN1ObjectIdentifier postalAddress = GeneratedOutlineSupport1.outline138("2.5.4.16");
    public static final ASN1ObjectIdentifier postalCode = GeneratedOutlineSupport1.outline138("2.5.4.17");
    public static final ASN1ObjectIdentifier postOfficeBox = GeneratedOutlineSupport1.outline138("2.5.4.18");
    public static final ASN1ObjectIdentifier preferredDeliveryMethod = GeneratedOutlineSupport1.outline138("2.5.4.28");
    public static final ASN1ObjectIdentifier registeredAddress = GeneratedOutlineSupport1.outline138("2.5.4.26");
    public static final ASN1ObjectIdentifier roleOccupant = GeneratedOutlineSupport1.outline138("2.5.4.33");
    public static final ASN1ObjectIdentifier searchGuide = GeneratedOutlineSupport1.outline138("2.5.4.14");
    public static final ASN1ObjectIdentifier seeAlso = GeneratedOutlineSupport1.outline138("2.5.4.34");
    public static final ASN1ObjectIdentifier serialNumber = GeneratedOutlineSupport1.outline138("2.5.4.5");
    public static final ASN1ObjectIdentifier sn = GeneratedOutlineSupport1.outline138("2.5.4.4");
    public static final ASN1ObjectIdentifier st = GeneratedOutlineSupport1.outline138("2.5.4.8");
    public static final ASN1ObjectIdentifier street = GeneratedOutlineSupport1.outline138("2.5.4.9");
    public static final ASN1ObjectIdentifier telephoneNumber = GeneratedOutlineSupport1.outline138("2.5.4.20");
    public static final ASN1ObjectIdentifier teletexTerminalIdentifier = GeneratedOutlineSupport1.outline138("2.5.4.22");
    public static final ASN1ObjectIdentifier telexNumber = GeneratedOutlineSupport1.outline138("2.5.4.21");
    public static final ASN1ObjectIdentifier title = GeneratedOutlineSupport1.outline138("2.5.4.12");
    public static final ASN1ObjectIdentifier uid = GeneratedOutlineSupport1.outline138("0.9.2342.19200300.100.1.1");
    public static final ASN1ObjectIdentifier uniqueMember = GeneratedOutlineSupport1.outline138("2.5.4.50");
    public static final ASN1ObjectIdentifier userPassword = GeneratedOutlineSupport1.outline138("2.5.4.35");
    public static final ASN1ObjectIdentifier x121Address = GeneratedOutlineSupport1.outline138("2.5.4.24");
    public static final ASN1ObjectIdentifier x500UniqueIdentifier = GeneratedOutlineSupport1.outline138("2.5.4.45");
    private static final Hashtable DefaultSymbols = new Hashtable();
    private static final Hashtable DefaultLookUp = new Hashtable();
    protected final Hashtable defaultSymbols = AbstractX500NameStyle.copyHashTable(DefaultSymbols);
    protected final Hashtable defaultLookUp = AbstractX500NameStyle.copyHashTable(DefaultLookUp);

    static {
        DefaultSymbols.put(businessCategory, "businessCategory");
        DefaultSymbols.put(c, "c");
        DefaultSymbols.put(cn, "cn");
        DefaultSymbols.put(dc, "dc");
        DefaultSymbols.put(description, "description");
        DefaultSymbols.put(destinationIndicator, "destinationIndicator");
        DefaultSymbols.put(distinguishedName, "distinguishedName");
        DefaultSymbols.put(dnQualifier, "dnQualifier");
        DefaultSymbols.put(enhancedSearchGuide, "enhancedSearchGuide");
        DefaultSymbols.put(facsimileTelephoneNumber, "facsimileTelephoneNumber");
        DefaultSymbols.put(generationQualifier, "generationQualifier");
        DefaultSymbols.put(givenName, "givenName");
        DefaultSymbols.put(houseIdentifier, "houseIdentifier");
        DefaultSymbols.put(initials, "initials");
        DefaultSymbols.put(internationalISDNNumber, "internationalISDNNumber");
        DefaultSymbols.put(l, "l");
        DefaultSymbols.put(member, "member");
        DefaultSymbols.put(name, "name");
        DefaultSymbols.put(o, "o");
        DefaultSymbols.put(ou, "ou");
        DefaultSymbols.put(owner, MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER);
        DefaultSymbols.put(physicalDeliveryOfficeName, "physicalDeliveryOfficeName");
        DefaultSymbols.put(postalAddress, "postalAddress");
        DefaultSymbols.put(postalCode, "postalCode");
        DefaultSymbols.put(postOfficeBox, "postOfficeBox");
        DefaultSymbols.put(preferredDeliveryMethod, "preferredDeliveryMethod");
        DefaultSymbols.put(registeredAddress, "registeredAddress");
        DefaultSymbols.put(roleOccupant, "roleOccupant");
        DefaultSymbols.put(searchGuide, "searchGuide");
        DefaultSymbols.put(seeAlso, "seeAlso");
        DefaultSymbols.put(serialNumber, Constants.BUNDLE_SERIAL_NUMBER);
        DefaultSymbols.put(sn, "sn");
        DefaultSymbols.put(st, com.amazon.alexa.mobilytics.configuration.Constants.TIMELINE_START_TIME_KEY);
        DefaultSymbols.put(street, "street");
        DefaultSymbols.put(telephoneNumber, "telephoneNumber");
        DefaultSymbols.put(teletexTerminalIdentifier, "teletexTerminalIdentifier");
        DefaultSymbols.put(telexNumber, "telexNumber");
        DefaultSymbols.put(title, "title");
        DefaultSymbols.put(uid, "uid");
        DefaultSymbols.put(uniqueMember, "uniqueMember");
        DefaultSymbols.put(userPassword, "userPassword");
        DefaultSymbols.put(x121Address, "x121Address");
        DefaultSymbols.put(x500UniqueIdentifier, "x500UniqueIdentifier");
        DefaultLookUp.put("businesscategory", businessCategory);
        DefaultLookUp.put("c", c);
        DefaultLookUp.put("cn", cn);
        DefaultLookUp.put("dc", dc);
        DefaultLookUp.put("description", description);
        DefaultLookUp.put("destinationindicator", destinationIndicator);
        DefaultLookUp.put("distinguishedname", distinguishedName);
        DefaultLookUp.put("dnqualifier", dnQualifier);
        DefaultLookUp.put("enhancedsearchguide", enhancedSearchGuide);
        DefaultLookUp.put("facsimiletelephonenumber", facsimileTelephoneNumber);
        DefaultLookUp.put("generationqualifier", generationQualifier);
        DefaultLookUp.put("givenname", givenName);
        DefaultLookUp.put("houseidentifier", houseIdentifier);
        DefaultLookUp.put("initials", initials);
        DefaultLookUp.put("internationalisdnnumber", internationalISDNNumber);
        DefaultLookUp.put("l", l);
        DefaultLookUp.put("member", member);
        DefaultLookUp.put("name", name);
        DefaultLookUp.put("o", o);
        DefaultLookUp.put("ou", ou);
        DefaultLookUp.put(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, owner);
        DefaultLookUp.put("physicaldeliveryofficename", physicalDeliveryOfficeName);
        DefaultLookUp.put("postaladdress", postalAddress);
        DefaultLookUp.put("postalcode", postalCode);
        DefaultLookUp.put("postofficebox", postOfficeBox);
        DefaultLookUp.put("preferreddeliverymethod", preferredDeliveryMethod);
        DefaultLookUp.put("registeredaddress", registeredAddress);
        DefaultLookUp.put("roleoccupant", roleOccupant);
        DefaultLookUp.put("searchguide", searchGuide);
        DefaultLookUp.put("seealso", seeAlso);
        DefaultLookUp.put("serialnumber", serialNumber);
        DefaultLookUp.put("sn", sn);
        DefaultLookUp.put(com.amazon.alexa.mobilytics.configuration.Constants.TIMELINE_START_TIME_KEY, st);
        DefaultLookUp.put("street", street);
        DefaultLookUp.put("telephonenumber", telephoneNumber);
        DefaultLookUp.put("teletexterminalidentifier", teletexTerminalIdentifier);
        DefaultLookUp.put("telexnumber", telexNumber);
        DefaultLookUp.put("title", title);
        DefaultLookUp.put("uid", uid);
        DefaultLookUp.put("uniquemember", uniqueMember);
        DefaultLookUp.put("userpassword", userPassword);
        DefaultLookUp.put("x121address", x121Address);
        DefaultLookUp.put("x500uniqueidentifier", x500UniqueIdentifier);
        INSTANCE = new RFC4519Style();
    }

    protected RFC4519Style() {
    }

    @Override // org.bouncycastle.asn1.x500.X500NameStyle
    public ASN1ObjectIdentifier attrNameToOID(String str) {
        return IETFUtils.decodeAttrName(str, this.defaultLookUp);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.asn1.x500.style.AbstractX500NameStyle
    public ASN1Encodable encodeStringValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        return aSN1ObjectIdentifier.equals((ASN1Primitive) dc) ? new DERIA5String(str) : (aSN1ObjectIdentifier.equals((ASN1Primitive) c) || aSN1ObjectIdentifier.equals((ASN1Primitive) serialNumber) || aSN1ObjectIdentifier.equals((ASN1Primitive) dnQualifier) || aSN1ObjectIdentifier.equals((ASN1Primitive) telephoneNumber)) ? new DERPrintableString(str) : super.encodeStringValue(aSN1ObjectIdentifier, str);
    }

    @Override // org.bouncycastle.asn1.x500.X500NameStyle
    public RDN[] fromString(String str) {
        RDN[] rDNsFromString = IETFUtils.rDNsFromString(str, this);
        RDN[] rdnArr = new RDN[rDNsFromString.length];
        for (int i = 0; i != rDNsFromString.length; i++) {
            rdnArr[(rdnArr.length - i) - 1] = rDNsFromString[i];
        }
        return rdnArr;
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
        RDN[] rDNs = x500Name.getRDNs();
        boolean z = true;
        for (int length = rDNs.length - 1; length >= 0; length--) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(JsonReaderKt.COMMA);
            }
            IETFUtils.appendRDN(stringBuffer, rDNs[length], this.defaultSymbols);
        }
        return stringBuffer.toString();
    }
}
