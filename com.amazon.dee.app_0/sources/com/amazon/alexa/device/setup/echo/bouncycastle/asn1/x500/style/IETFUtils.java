package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.style;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1String;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERUniversalString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.AttributeTypeAndValue;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.RDN;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.X500NameBuilder;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.X500NameStyle;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.Strings;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.encoders.Hex;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public class IETFUtils {
    public static void appendTypeAndValue(StringBuffer stringBuffer, AttributeTypeAndValue attributeTypeAndValue, Hashtable hashtable) {
        String str = (String) hashtable.get(attributeTypeAndValue.getType());
        if (str != null) {
            stringBuffer.append(str);
        } else {
            stringBuffer.append(attributeTypeAndValue.getType().getId());
        }
        stringBuffer.append(Chars.EQ);
        stringBuffer.append(valueToString(attributeTypeAndValue.getValue()));
    }

    private static String bytesToString(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return new String(cArr);
    }

    public static String canonicalize(String str) {
        String lowerCase = Strings.toLowerCase(str.trim());
        if (lowerCase.length() > 0 && lowerCase.charAt(0) == '#') {
            ASN1Primitive decodeObject = decodeObject(lowerCase);
            if (decodeObject instanceof ASN1String) {
                lowerCase = Strings.toLowerCase(((ASN1String) decodeObject).getString().trim());
            }
        }
        return stripInternalSpaces(lowerCase);
    }

    public static ASN1ObjectIdentifier decodeAttrName(String str, Hashtable hashtable) {
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

    private static ASN1Primitive decodeObject(String str) {
        try {
            return ASN1Primitive.fromByteArray(Hex.decode(str.substring(1)));
        } catch (IOException e) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline65("unknown encoding in name: ", e));
        }
    }

    public static RDN[] rDNsFromString(String str, X500NameStyle x500NameStyle) {
        X500NameTokenizer x500NameTokenizer = new X500NameTokenizer(str);
        X500NameBuilder x500NameBuilder = new X500NameBuilder(x500NameStyle);
        while (x500NameTokenizer.hasMoreTokens()) {
            String nextToken = x500NameTokenizer.nextToken();
            int indexOf = nextToken.indexOf(61);
            if (indexOf != -1) {
                String substring = nextToken.substring(0, indexOf);
                String substring2 = nextToken.substring(indexOf + 1);
                ASN1ObjectIdentifier attrNameToOID = x500NameStyle.attrNameToOID(substring);
                if (substring2.indexOf(43) > 0) {
                    X500NameTokenizer x500NameTokenizer2 = new X500NameTokenizer(substring2, '+');
                    String nextToken2 = x500NameTokenizer2.nextToken();
                    Vector vector = new Vector();
                    Vector vector2 = new Vector();
                    vector.addElement(attrNameToOID);
                    vector2.addElement(nextToken2);
                    while (x500NameTokenizer2.hasMoreTokens()) {
                        String nextToken3 = x500NameTokenizer2.nextToken();
                        int indexOf2 = nextToken3.indexOf(61);
                        String substring3 = nextToken3.substring(0, indexOf2);
                        String substring4 = nextToken3.substring(indexOf2 + 1);
                        vector.addElement(x500NameStyle.attrNameToOID(substring3));
                        vector2.addElement(substring4);
                    }
                    x500NameBuilder.addMultiValuedRDN(toOIDArray(vector), toValueArray(vector2));
                } else {
                    x500NameBuilder.addRDN(attrNameToOID, substring2);
                }
            } else {
                throw new IllegalArgumentException("badly formated directory string");
            }
        }
        return x500NameBuilder.build().getRDNs();
    }

    public static String stripInternalSpaces(String str) {
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

    private static ASN1ObjectIdentifier[] toOIDArray(Vector vector) {
        ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = new ASN1ObjectIdentifier[vector.size()];
        for (int i = 0; i != aSN1ObjectIdentifierArr.length; i++) {
            aSN1ObjectIdentifierArr[i] = (ASN1ObjectIdentifier) vector.elementAt(i);
        }
        return aSN1ObjectIdentifierArr;
    }

    private static String[] toValueArray(Vector vector) {
        String[] strArr = new String[vector.size()];
        for (int i = 0; i != strArr.length; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    public static ASN1Encodable valueFromHexString(String str, int i) throws IOException {
        String lowerCase = Strings.toLowerCase(str);
        byte[] bArr = new byte[(lowerCase.length() - i) / 2];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            int i3 = (i2 * 2) + i;
            char charAt = lowerCase.charAt(i3);
            char charAt2 = lowerCase.charAt(i3 + 1);
            if (charAt < 'a') {
                bArr[i2] = (byte) ((charAt - '0') << 4);
            } else {
                bArr[i2] = (byte) (((charAt - 'a') + 10) << 4);
            }
            if (charAt2 < 'a') {
                bArr[i2] = (byte) (((byte) (charAt2 - '0')) | bArr[i2]);
            } else {
                bArr[i2] = (byte) (((byte) ((charAt2 - 'a') + 10)) | bArr[i2]);
            }
        }
        return ASN1Primitive.fromByteArray(bArr);
    }

    public static String valueToString(ASN1Encodable aSN1Encodable) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        if ((aSN1Encodable instanceof ASN1String) && !(aSN1Encodable instanceof DERUniversalString)) {
            String string = ((ASN1String) aSN1Encodable).getString();
            if (string.length() > 0 && string.charAt(0) == '#') {
                stringBuffer.append("\\" + string);
            } else {
                stringBuffer.append(string);
            }
        } else {
            try {
                stringBuffer.append(MqttTopic.MULTI_LEVEL_WILDCARD + bytesToString(Hex.encode(aSN1Encodable.toASN1Primitive().getEncoded("DER"))));
            } catch (IOException unused) {
                throw new IllegalArgumentException("Other value has no encoded form");
            }
        }
        int length = stringBuffer.length();
        if (stringBuffer.length() >= 2 && stringBuffer.charAt(0) == '\\' && stringBuffer.charAt(1) == '#') {
            i = 2;
        }
        while (i != length) {
            if (stringBuffer.charAt(i) == ',' || stringBuffer.charAt(i) == '\"' || stringBuffer.charAt(i) == '\\' || stringBuffer.charAt(i) == '+' || stringBuffer.charAt(i) == '=' || stringBuffer.charAt(i) == '<' || stringBuffer.charAt(i) == '>' || stringBuffer.charAt(i) == ';') {
                stringBuffer.insert(i, "\\");
                i++;
                length++;
            }
            i++;
        }
        return stringBuffer.toString();
    }
}
