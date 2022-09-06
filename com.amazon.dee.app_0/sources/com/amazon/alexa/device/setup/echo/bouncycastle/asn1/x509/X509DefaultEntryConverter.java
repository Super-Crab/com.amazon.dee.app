package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERGeneralizedTime;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERIA5String;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERPrintableString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERUTF8String;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes.dex */
public class X509DefaultEntryConverter extends X509NameEntryConverter {
    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.X509NameEntryConverter
    public ASN1Primitive getConvertedValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (str.length() != 0 && str.charAt(0) == '#') {
            try {
                return convertHexEncoded(str, 1);
            } catch (IOException unused) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("can't recode value for oid ");
                outline107.append(aSN1ObjectIdentifier.getId());
                throw new RuntimeException(outline107.toString());
            }
        }
        if (str.length() != 0 && str.charAt(0) == '\\') {
            str = str.substring(1);
        }
        if (!aSN1ObjectIdentifier.equals(X509Name.EmailAddress) && !aSN1ObjectIdentifier.equals(X509Name.DC)) {
            if (aSN1ObjectIdentifier.equals(X509Name.DATE_OF_BIRTH)) {
                return new DERGeneralizedTime(str);
            }
            if (!aSN1ObjectIdentifier.equals(X509Name.C) && !aSN1ObjectIdentifier.equals(X509Name.SN) && !aSN1ObjectIdentifier.equals(X509Name.DN_QUALIFIER) && !aSN1ObjectIdentifier.equals(X509Name.TELEPHONE_NUMBER)) {
                return new DERUTF8String(str);
            }
            return new DERPrintableString(str);
        }
        return new DERIA5String(str);
    }
}
