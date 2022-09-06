package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Set;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1String;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSet;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERUniversalString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.X500Name;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.Strings;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.encoders.Hex;
import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public class X509Name extends ASN1Object {
    private Vector added;
    private X509NameEntryConverter converter;
    private int hashCodeValue;
    private boolean isHashCodeCalculated;
    private Vector ordering;
    private ASN1Sequence seq;
    private Vector values;
    public static final ASN1ObjectIdentifier C = new ASN1ObjectIdentifier("2.5.4.6");
    public static final ASN1ObjectIdentifier O = new ASN1ObjectIdentifier("2.5.4.10");
    public static final ASN1ObjectIdentifier OU = new ASN1ObjectIdentifier("2.5.4.11");
    public static final ASN1ObjectIdentifier T = new ASN1ObjectIdentifier("2.5.4.12");
    public static final ASN1ObjectIdentifier CN = new ASN1ObjectIdentifier("2.5.4.3");
    public static final ASN1ObjectIdentifier SN = new ASN1ObjectIdentifier("2.5.4.5");
    public static final ASN1ObjectIdentifier STREET = new ASN1ObjectIdentifier("2.5.4.9");
    public static final ASN1ObjectIdentifier SERIALNUMBER = SN;
    public static final ASN1ObjectIdentifier L = new ASN1ObjectIdentifier("2.5.4.7");
    public static final ASN1ObjectIdentifier ST = new ASN1ObjectIdentifier("2.5.4.8");
    public static final ASN1ObjectIdentifier SURNAME = new ASN1ObjectIdentifier("2.5.4.4");
    public static final ASN1ObjectIdentifier GIVENNAME = new ASN1ObjectIdentifier("2.5.4.42");
    public static final ASN1ObjectIdentifier INITIALS = new ASN1ObjectIdentifier("2.5.4.43");
    public static final ASN1ObjectIdentifier GENERATION = new ASN1ObjectIdentifier("2.5.4.44");
    public static final ASN1ObjectIdentifier UNIQUE_IDENTIFIER = new ASN1ObjectIdentifier("2.5.4.45");
    public static final ASN1ObjectIdentifier BUSINESS_CATEGORY = new ASN1ObjectIdentifier("2.5.4.15");
    public static final ASN1ObjectIdentifier POSTAL_CODE = new ASN1ObjectIdentifier("2.5.4.17");
    public static final ASN1ObjectIdentifier DN_QUALIFIER = new ASN1ObjectIdentifier("2.5.4.46");
    public static final ASN1ObjectIdentifier PSEUDONYM = new ASN1ObjectIdentifier("2.5.4.65");
    public static final ASN1ObjectIdentifier DATE_OF_BIRTH = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.1");
    public static final ASN1ObjectIdentifier PLACE_OF_BIRTH = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.2");
    public static final ASN1ObjectIdentifier GENDER = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.3");
    public static final ASN1ObjectIdentifier COUNTRY_OF_CITIZENSHIP = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.4");
    public static final ASN1ObjectIdentifier COUNTRY_OF_RESIDENCE = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.5");
    public static final ASN1ObjectIdentifier NAME_AT_BIRTH = new ASN1ObjectIdentifier("1.3.36.8.3.14");
    public static final ASN1ObjectIdentifier POSTAL_ADDRESS = new ASN1ObjectIdentifier("2.5.4.16");
    public static final ASN1ObjectIdentifier DMD_NAME = new ASN1ObjectIdentifier("2.5.4.54");
    public static final ASN1ObjectIdentifier TELEPHONE_NUMBER = X509ObjectIdentifiers.id_at_telephoneNumber;
    public static final ASN1ObjectIdentifier NAME = X509ObjectIdentifiers.id_at_name;
    public static final ASN1ObjectIdentifier EmailAddress = PKCSObjectIdentifiers.pkcs_9_at_emailAddress;
    public static final ASN1ObjectIdentifier UnstructuredName = PKCSObjectIdentifiers.pkcs_9_at_unstructuredName;
    public static final ASN1ObjectIdentifier UnstructuredAddress = PKCSObjectIdentifiers.pkcs_9_at_unstructuredAddress;
    public static final ASN1ObjectIdentifier E = EmailAddress;
    public static final ASN1ObjectIdentifier DC = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
    public static final ASN1ObjectIdentifier UID = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
    public static final Hashtable DefaultSymbols = new Hashtable();
    public static final Hashtable RFC2253Symbols = new Hashtable();
    public static final Hashtable RFC1779Symbols = new Hashtable();
    public static final Hashtable DefaultLookUp = new Hashtable();
    public static final Hashtable OIDLookUp = DefaultSymbols;
    public static final Hashtable SymbolLookUp = DefaultLookUp;
    private static final Boolean TRUE = new Boolean(true);
    private static final Boolean FALSE = new Boolean(false);
    public static boolean DefaultReverse = false;

    static {
        DefaultSymbols.put(C, "C");
        DefaultSymbols.put(O, "O");
        DefaultSymbols.put(T, ExifInterface.GPS_DIRECTION_TRUE);
        DefaultSymbols.put(OU, "OU");
        DefaultSymbols.put(CN, "CN");
        DefaultSymbols.put(L, "L");
        DefaultSymbols.put(ST, "ST");
        DefaultSymbols.put(SN, "SERIALNUMBER");
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
        RFC2253Symbols.put(C, "C");
        RFC2253Symbols.put(O, "O");
        RFC2253Symbols.put(OU, "OU");
        RFC2253Symbols.put(CN, "CN");
        RFC2253Symbols.put(L, "L");
        RFC2253Symbols.put(ST, "ST");
        RFC2253Symbols.put(STREET, "STREET");
        RFC2253Symbols.put(DC, "DC");
        RFC2253Symbols.put(UID, "UID");
        RFC1779Symbols.put(C, "C");
        RFC1779Symbols.put(O, "O");
        RFC1779Symbols.put(OU, "OU");
        RFC1779Symbols.put(CN, "CN");
        RFC1779Symbols.put(L, "L");
        RFC1779Symbols.put(ST, "ST");
        RFC1779Symbols.put(STREET, "STREET");
        DefaultLookUp.put("c", C);
        DefaultLookUp.put("o", O);
        DefaultLookUp.put("t", T);
        DefaultLookUp.put("ou", OU);
        DefaultLookUp.put("cn", CN);
        DefaultLookUp.put("l", L);
        DefaultLookUp.put(Constants.TIMELINE_START_TIME_KEY, ST);
        DefaultLookUp.put("sn", SN);
        DefaultLookUp.put("serialnumber", SN);
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
        DefaultLookUp.put("nameofbirth", NAME_AT_BIRTH);
        DefaultLookUp.put("countryofcitizenship", COUNTRY_OF_CITIZENSHIP);
        DefaultLookUp.put("countryofresidence", COUNTRY_OF_RESIDENCE);
        DefaultLookUp.put("gender", GENDER);
        DefaultLookUp.put("placeofbirth", PLACE_OF_BIRTH);
        DefaultLookUp.put("dateofbirth", DATE_OF_BIRTH);
        DefaultLookUp.put("postalcode", POSTAL_CODE);
        DefaultLookUp.put("businesscategory", BUSINESS_CATEGORY);
        DefaultLookUp.put("telephonenumber", TELEPHONE_NUMBER);
        DefaultLookUp.put("name", NAME);
    }

    protected X509Name() {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
    }

    private void appendValue(StringBuffer stringBuffer, Hashtable hashtable, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        String str2 = (String) hashtable.get(aSN1ObjectIdentifier);
        if (str2 != null) {
            stringBuffer.append(str2);
        } else {
            stringBuffer.append(aSN1ObjectIdentifier.getId());
        }
        stringBuffer.append(Chars.EQ);
        int length = stringBuffer.length();
        stringBuffer.append(str);
        int length2 = stringBuffer.length();
        if (str.length() >= 2 && str.charAt(0) == '\\' && str.charAt(1) == '#') {
            length += 2;
        }
        while (length != length2) {
            if (stringBuffer.charAt(length) == ',' || stringBuffer.charAt(length) == '\"' || stringBuffer.charAt(length) == '\\' || stringBuffer.charAt(length) == '+' || stringBuffer.charAt(length) == '=' || stringBuffer.charAt(length) == '<' || stringBuffer.charAt(length) == '>' || stringBuffer.charAt(length) == ';') {
                stringBuffer.insert(length, "\\");
                length++;
                length2++;
            }
            length++;
        }
    }

    private String bytesToString(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return new String(cArr);
    }

    private String canonicalize(String str) {
        String lowerCase = Strings.toLowerCase(str.trim());
        if (lowerCase.length() <= 0 || lowerCase.charAt(0) != '#') {
            return lowerCase;
        }
        ASN1Primitive decodeObject = decodeObject(lowerCase);
        return decodeObject instanceof ASN1String ? Strings.toLowerCase(((ASN1String) decodeObject).getString().trim()) : lowerCase;
    }

    private ASN1ObjectIdentifier decodeOID(String str, Hashtable hashtable) {
        if (Strings.toUpperCase(str).startsWith("OID.")) {
            return new ASN1ObjectIdentifier(str.substring(4));
        }
        if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {
            return new ASN1ObjectIdentifier(str);
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) hashtable.get(Strings.toLowerCase(str));
        if (aSN1ObjectIdentifier == null) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Unknown object id - ", str, " - passed to distinguished name"));
        }
        return aSN1ObjectIdentifier;
    }

    private ASN1Primitive decodeObject(String str) {
        try {
            return ASN1Primitive.fromByteArray(Hex.decode(str.substring(1)));
        } catch (IOException e) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline65("unknown encoding in name: ", e));
        }
    }

    private boolean equivalentStrings(String str, String str2) {
        String canonicalize = canonicalize(str);
        String canonicalize2 = canonicalize(str2);
        if (!canonicalize.equals(canonicalize2)) {
            return stripInternalSpaces(canonicalize).equals(stripInternalSpaces(canonicalize2));
        }
        return true;
    }

    public static X509Name getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    private String stripInternalSpaces(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str.length() != 0) {
            char charAt = str.charAt(0);
            stringBuffer.append(charAt);
            int i = 1;
            while (i < str.length()) {
                char charAt2 = str.charAt(i);
                if (charAt != ' ' || charAt2 != ' ') {
                    stringBuffer.append(charAt2);
                }
                i++;
                charAt = charAt2;
            }
        }
        return stringBuffer.toString();
    }

    public boolean equals(Object obj, boolean z) {
        if (!z) {
            return equals(obj);
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof X509Name) && !(obj instanceof ASN1Sequence)) {
            return false;
        }
        if (toASN1Primitive().equals(((ASN1Encodable) obj).toASN1Primitive())) {
            return true;
        }
        try {
            X509Name x509Name = getInstance(obj);
            int size = this.ordering.size();
            if (size != x509Name.ordering.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!((ASN1ObjectIdentifier) this.ordering.elementAt(i)).equals((ASN1ObjectIdentifier) x509Name.ordering.elementAt(i)) || !equivalentStrings((String) this.values.elementAt(i), (String) x509Name.values.elementAt(i))) {
                    return false;
                }
            }
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public Vector getOIDs() {
        Vector vector = new Vector();
        for (int i = 0; i != this.ordering.size(); i++) {
            vector.addElement(this.ordering.elementAt(i));
        }
        return vector;
    }

    public Vector getValues() {
        Vector vector = new Vector();
        for (int i = 0; i != this.values.size(); i++) {
            vector.addElement(this.values.elementAt(i));
        }
        return vector;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        if (this.isHashCodeCalculated) {
            return this.hashCodeValue;
        }
        this.isHashCodeCalculated = true;
        for (int i = 0; i != this.ordering.size(); i++) {
            String stripInternalSpaces = stripInternalSpaces(canonicalize((String) this.values.elementAt(i)));
            this.hashCodeValue ^= this.ordering.elementAt(i).hashCode();
            this.hashCodeValue = stripInternalSpaces.hashCode() ^ this.hashCodeValue;
        }
        return this.hashCodeValue;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        if (this.seq == null) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            ASN1ObjectIdentifier aSN1ObjectIdentifier = null;
            int i = 0;
            while (i != this.ordering.size()) {
                ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
                ASN1ObjectIdentifier aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier) this.ordering.elementAt(i);
                aSN1EncodableVector3.add(aSN1ObjectIdentifier2);
                aSN1EncodableVector3.add(this.converter.getConvertedValue(aSN1ObjectIdentifier2, (String) this.values.elementAt(i)));
                if (aSN1ObjectIdentifier != null && !((Boolean) this.added.elementAt(i)).booleanValue()) {
                    aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
                    aSN1EncodableVector2 = new ASN1EncodableVector();
                    aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector3));
                } else {
                    aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector3));
                }
                i++;
                aSN1ObjectIdentifier = aSN1ObjectIdentifier2;
            }
            aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
            this.seq = new DERSequence(aSN1EncodableVector);
        }
        return this.seq;
    }

    public String toString(boolean z, Hashtable hashtable) {
        StringBuffer stringBuffer = new StringBuffer();
        Vector vector = new Vector();
        StringBuffer stringBuffer2 = null;
        for (int i = 0; i < this.ordering.size(); i++) {
            if (((Boolean) this.added.elementAt(i)).booleanValue()) {
                stringBuffer2.append('+');
                appendValue(stringBuffer2, hashtable, (ASN1ObjectIdentifier) this.ordering.elementAt(i), (String) this.values.elementAt(i));
            } else {
                stringBuffer2 = new StringBuffer();
                appendValue(stringBuffer2, hashtable, (ASN1ObjectIdentifier) this.ordering.elementAt(i), (String) this.values.elementAt(i));
                vector.addElement(stringBuffer2);
            }
        }
        boolean z2 = true;
        if (z) {
            for (int size = vector.size() - 1; size >= 0; size--) {
                if (z2) {
                    z2 = false;
                } else {
                    stringBuffer.append(JsonReaderKt.COMMA);
                }
                stringBuffer.append(vector.elementAt(size).toString());
            }
        } else {
            for (int i2 = 0; i2 < vector.size(); i2++) {
                if (z2) {
                    z2 = false;
                } else {
                    stringBuffer.append(JsonReaderKt.COMMA);
                }
                stringBuffer.append(vector.elementAt(i2).toString());
            }
        }
        return stringBuffer.toString();
    }

    public static X509Name getInstance(Object obj) {
        if (obj != null && !(obj instanceof X509Name)) {
            if (obj instanceof X500Name) {
                return new X509Name(ASN1Sequence.getInstance(((X500Name) obj).toASN1Primitive()));
            }
            return new X509Name(ASN1Sequence.getInstance(obj));
        }
        return (X509Name) obj;
    }

    public Vector getValues(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Vector vector = new Vector();
        for (int i = 0; i != this.values.size(); i++) {
            if (this.ordering.elementAt(i).equals(aSN1ObjectIdentifier)) {
                String str = (String) this.values.elementAt(i);
                if (str.length() > 2 && str.charAt(0) == '\\' && str.charAt(1) == '#') {
                    vector.addElement(str.substring(1));
                } else {
                    vector.addElement(str);
                }
            }
        }
        return vector;
    }

    public X509Name(ASN1Sequence aSN1Sequence) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.seq = aSN1Sequence;
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1Set aSN1Set = ASN1Set.getInstance(((ASN1Encodable) objects.nextElement()).toASN1Primitive());
            int i = 0;
            while (i < aSN1Set.size()) {
                ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Set.getObjectAt(i).toASN1Primitive());
                if (aSN1Sequence2.size() == 2) {
                    this.ordering.addElement(DERObjectIdentifier.getInstance(aSN1Sequence2.getObjectAt(0)));
                    ASN1Encodable objectAt = aSN1Sequence2.getObjectAt(1);
                    if ((objectAt instanceof ASN1String) && !(objectAt instanceof DERUniversalString)) {
                        String string = ((ASN1String) objectAt).getString();
                        if (string.length() > 0 && string.charAt(0) == '#') {
                            this.values.addElement("\\" + string);
                        } else {
                            this.values.addElement(string);
                        }
                    } else {
                        try {
                            this.values.addElement(MqttTopic.MULTI_LEVEL_WILDCARD + bytesToString(Hex.encode(objectAt.toASN1Primitive().getEncoded("DER"))));
                        } catch (IOException unused) {
                            throw new IllegalArgumentException("cannot encode value");
                        }
                    }
                    this.added.addElement(i != 0 ? TRUE : FALSE);
                    i++;
                } else {
                    throw new IllegalArgumentException("badly sized pair");
                }
            }
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object
    public boolean equals(Object obj) {
        int i;
        int i2;
        boolean z;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof X509Name) && !(obj instanceof ASN1Sequence)) {
            return false;
        }
        if (toASN1Primitive().equals(((ASN1Encodable) obj).toASN1Primitive())) {
            return true;
        }
        try {
            X509Name x509Name = getInstance(obj);
            int size = this.ordering.size();
            if (size != x509Name.ordering.size()) {
                return false;
            }
            boolean[] zArr = new boolean[size];
            int i3 = -1;
            if (this.ordering.elementAt(0).equals(x509Name.ordering.elementAt(0))) {
                i2 = 1;
                i3 = size;
                i = 0;
            } else {
                i = size - 1;
                i2 = -1;
            }
            while (i != i3) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) this.ordering.elementAt(i);
                String str = (String) this.values.elementAt(i);
                int i4 = 0;
                while (true) {
                    if (i4 >= size) {
                        z = false;
                        break;
                    } else if (!zArr[i4] && aSN1ObjectIdentifier.equals((ASN1ObjectIdentifier) x509Name.ordering.elementAt(i4)) && equivalentStrings(str, (String) x509Name.values.elementAt(i4))) {
                        zArr[i4] = true;
                        z = true;
                        break;
                    } else {
                        i4++;
                    }
                }
                if (!z) {
                    return false;
                }
                i += i2;
            }
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public String toString() {
        return toString(DefaultReverse, DefaultSymbols);
    }

    public X509Name(Hashtable hashtable) {
        this((Vector) null, hashtable);
    }

    public X509Name(Vector vector, Hashtable hashtable) {
        this(vector, hashtable, new X509DefaultEntryConverter());
    }

    public X509Name(Vector vector, Hashtable hashtable, X509NameEntryConverter x509NameEntryConverter) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.converter = x509NameEntryConverter;
        if (vector != null) {
            for (int i = 0; i != vector.size(); i++) {
                this.ordering.addElement(vector.elementAt(i));
                this.added.addElement(FALSE);
            }
        } else {
            Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                this.ordering.addElement(keys.nextElement());
                this.added.addElement(FALSE);
            }
        }
        for (int i2 = 0; i2 != this.ordering.size(); i2++) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) this.ordering.elementAt(i2);
            if (hashtable.get(aSN1ObjectIdentifier) != null) {
                this.values.addElement(hashtable.get(aSN1ObjectIdentifier));
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No attribute for object id - ");
                outline107.append(aSN1ObjectIdentifier.getId());
                outline107.append(" - passed to distinguished name");
                throw new IllegalArgumentException(outline107.toString());
            }
        }
    }

    public X509Name(Vector vector, Vector vector2) {
        this(vector, vector2, new X509DefaultEntryConverter());
    }

    public X509Name(Vector vector, Vector vector2, X509NameEntryConverter x509NameEntryConverter) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.converter = x509NameEntryConverter;
        if (vector.size() == vector2.size()) {
            for (int i = 0; i < vector.size(); i++) {
                this.ordering.addElement(vector.elementAt(i));
                this.values.addElement(vector2.elementAt(i));
                this.added.addElement(FALSE);
            }
            return;
        }
        throw new IllegalArgumentException("oids vector must be same length as values.");
    }

    public X509Name(String str) {
        this(DefaultReverse, DefaultLookUp, str);
    }

    public X509Name(String str, X509NameEntryConverter x509NameEntryConverter) {
        this(DefaultReverse, DefaultLookUp, str, x509NameEntryConverter);
    }

    public X509Name(boolean z, String str) {
        this(z, DefaultLookUp, str);
    }

    public X509Name(boolean z, String str, X509NameEntryConverter x509NameEntryConverter) {
        this(z, DefaultLookUp, str, x509NameEntryConverter);
    }

    public X509Name(boolean z, Hashtable hashtable, String str) {
        this(z, hashtable, str, new X509DefaultEntryConverter());
    }

    public X509Name(boolean z, Hashtable hashtable, String str, X509NameEntryConverter x509NameEntryConverter) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.converter = x509NameEntryConverter;
        X509NameTokenizer x509NameTokenizer = new X509NameTokenizer(str);
        while (x509NameTokenizer.hasMoreTokens()) {
            String nextToken = x509NameTokenizer.nextToken();
            int indexOf = nextToken.indexOf(61);
            if (indexOf != -1) {
                String substring = nextToken.substring(0, indexOf);
                String substring2 = nextToken.substring(indexOf + 1);
                ASN1ObjectIdentifier decodeOID = decodeOID(substring, hashtable);
                if (substring2.indexOf(43) > 0) {
                    X509NameTokenizer x509NameTokenizer2 = new X509NameTokenizer(substring2, '+');
                    String nextToken2 = x509NameTokenizer2.nextToken();
                    this.ordering.addElement(decodeOID);
                    this.values.addElement(nextToken2);
                    this.added.addElement(FALSE);
                    while (x509NameTokenizer2.hasMoreTokens()) {
                        String nextToken3 = x509NameTokenizer2.nextToken();
                        int indexOf2 = nextToken3.indexOf(61);
                        String substring3 = nextToken3.substring(0, indexOf2);
                        String substring4 = nextToken3.substring(indexOf2 + 1);
                        this.ordering.addElement(decodeOID(substring3, hashtable));
                        this.values.addElement(substring4);
                        this.added.addElement(TRUE);
                    }
                } else {
                    this.ordering.addElement(decodeOID);
                    this.values.addElement(substring2);
                    this.added.addElement(FALSE);
                }
            } else {
                throw new IllegalArgumentException("badly formated directory string");
            }
        }
        if (z) {
            Vector vector = new Vector();
            Vector vector2 = new Vector();
            Vector vector3 = new Vector();
            int i = 1;
            for (int i2 = 0; i2 < this.ordering.size(); i2++) {
                if (((Boolean) this.added.elementAt(i2)).booleanValue()) {
                    vector.insertElementAt(this.ordering.elementAt(i2), i);
                    vector2.insertElementAt(this.values.elementAt(i2), i);
                    vector3.insertElementAt(this.added.elementAt(i2), i);
                    i++;
                } else {
                    vector.insertElementAt(this.ordering.elementAt(i2), 0);
                    vector2.insertElementAt(this.values.elementAt(i2), 0);
                    vector3.insertElementAt(this.added.elementAt(i2), 0);
                    i = 1;
                }
            }
            this.ordering = vector;
            this.values = vector2;
            this.added = vector3;
        }
    }
}
