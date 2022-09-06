package org.bouncycastle.asn1.util;

import com.amazon.alexa.client.metrics.core.AppInformation;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1External;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.BERApplicationSpecific;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERGraphicString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERT61String;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DERVideotexString;
import org.bouncycastle.asn1.DERVisibleString;
import org.bouncycastle.asn1.DLApplicationSpecific;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes4.dex */
public class ASN1Dump {
    private static final int SAMPLE_SIZE = 32;
    private static final String TAB = "    ";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void _dumpAsString(String str, boolean z, ASN1Primitive aSN1Primitive, StringBuffer stringBuffer) {
        StringBuilder outline107;
        BigInteger value;
        String str2;
        String outputApplicationSpecific;
        String time;
        StringBuilder outline113;
        int length;
        String lineSeparator = Strings.lineSeparator();
        if (aSN1Primitive instanceof ASN1Sequence) {
            Enumeration objects = ((ASN1Sequence) aSN1Primitive).getObjects();
            String str3 = str + TAB;
            stringBuffer.append(str);
            stringBuffer.append(aSN1Primitive instanceof BERSequence ? "BER Sequence" : aSN1Primitive instanceof DERSequence ? "DER Sequence" : "Sequence");
            while (true) {
                stringBuffer.append(lineSeparator);
                while (objects.hasMoreElements()) {
                    Object nextElement = objects.nextElement();
                    if (nextElement == null || nextElement.equals(DERNull.INSTANCE)) {
                        stringBuffer.append(str3);
                        stringBuffer.append(AppInformation.NULL);
                    } else {
                        _dumpAsString(str3, z, nextElement instanceof ASN1Primitive ? (ASN1Primitive) nextElement : ((ASN1Encodable) nextElement).toASN1Primitive(), stringBuffer);
                    }
                }
                return;
            }
        } else if (aSN1Primitive instanceof ASN1TaggedObject) {
            String str4 = str + TAB;
            stringBuffer.append(str);
            stringBuffer.append(aSN1Primitive instanceof BERTaggedObject ? "BER Tagged [" : "Tagged [");
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
            stringBuffer.append(Integer.toString(aSN1TaggedObject.getTagNo()));
            stringBuffer.append(JsonReaderKt.END_LIST);
            if (!aSN1TaggedObject.isExplicit()) {
                stringBuffer.append(" IMPLICIT ");
            }
            stringBuffer.append(lineSeparator);
            _dumpAsString(str4, z, aSN1TaggedObject.getObject(), stringBuffer);
        } else if (!(aSN1Primitive instanceof ASN1Set)) {
            if (aSN1Primitive instanceof ASN1OctetString) {
                ASN1OctetString aSN1OctetString = (ASN1OctetString) aSN1Primitive;
                if (aSN1Primitive instanceof BEROctetString) {
                    outline113 = GeneratedOutlineSupport1.outline113(str, "BER Constructed Octet String[");
                    length = aSN1OctetString.getOctets().length;
                } else {
                    outline113 = GeneratedOutlineSupport1.outline113(str, "DER Octet String[");
                    length = aSN1OctetString.getOctets().length;
                }
                outline113.append(length);
                outline113.append("] ");
                stringBuffer.append(outline113.toString());
                if (z) {
                    outputApplicationSpecific = dumpBinaryDataAsString(str, aSN1OctetString.getOctets());
                    stringBuffer.append(outputApplicationSpecific);
                }
                stringBuffer.append(lineSeparator);
                return;
            }
            if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                outline107 = GeneratedOutlineSupport1.outline113(str, "ObjectIdentifier(");
                outline107.append(((ASN1ObjectIdentifier) aSN1Primitive).getId());
            } else if (aSN1Primitive instanceof ASN1Boolean) {
                outline107 = GeneratedOutlineSupport1.outline113(str, "Boolean(");
                outline107.append(((ASN1Boolean) aSN1Primitive).isTrue());
            } else {
                if (!(aSN1Primitive instanceof ASN1Integer)) {
                    if (aSN1Primitive instanceof DERBitString) {
                        DERBitString dERBitString = (DERBitString) aSN1Primitive;
                        StringBuilder outline1132 = GeneratedOutlineSupport1.outline113(str, "DER Bit String[");
                        outline1132.append(dERBitString.getBytes().length);
                        outline1132.append(", ");
                        outline1132.append(dERBitString.getPadBits());
                        outline1132.append("] ");
                        stringBuffer.append(outline1132.toString());
                        if (z) {
                            outputApplicationSpecific = dumpBinaryDataAsString(str, dERBitString.getBytes());
                        }
                        stringBuffer.append(lineSeparator);
                        return;
                    }
                    if (aSN1Primitive instanceof DERIA5String) {
                        outline107 = GeneratedOutlineSupport1.outline113(str, "IA5String(");
                        time = ((DERIA5String) aSN1Primitive).getString();
                    } else if (aSN1Primitive instanceof DERUTF8String) {
                        outline107 = GeneratedOutlineSupport1.outline113(str, "UTF8String(");
                        time = ((DERUTF8String) aSN1Primitive).getString();
                    } else if (aSN1Primitive instanceof DERPrintableString) {
                        outline107 = GeneratedOutlineSupport1.outline113(str, "PrintableString(");
                        time = ((DERPrintableString) aSN1Primitive).getString();
                    } else if (aSN1Primitive instanceof DERVisibleString) {
                        outline107 = GeneratedOutlineSupport1.outline113(str, "VisibleString(");
                        time = ((DERVisibleString) aSN1Primitive).getString();
                    } else if (aSN1Primitive instanceof DERBMPString) {
                        outline107 = GeneratedOutlineSupport1.outline113(str, "BMPString(");
                        time = ((DERBMPString) aSN1Primitive).getString();
                    } else if (aSN1Primitive instanceof DERT61String) {
                        outline107 = GeneratedOutlineSupport1.outline113(str, "T61String(");
                        time = ((DERT61String) aSN1Primitive).getString();
                    } else if (aSN1Primitive instanceof DERGraphicString) {
                        outline107 = GeneratedOutlineSupport1.outline113(str, "GraphicString(");
                        time = ((DERGraphicString) aSN1Primitive).getString();
                    } else if (aSN1Primitive instanceof DERVideotexString) {
                        outline107 = GeneratedOutlineSupport1.outline113(str, "VideotexString(");
                        time = ((DERVideotexString) aSN1Primitive).getString();
                    } else if (aSN1Primitive instanceof ASN1UTCTime) {
                        outline107 = GeneratedOutlineSupport1.outline113(str, "UTCTime(");
                        time = ((ASN1UTCTime) aSN1Primitive).getTime();
                    } else if (aSN1Primitive instanceof ASN1GeneralizedTime) {
                        outline107 = GeneratedOutlineSupport1.outline113(str, "GeneralizedTime(");
                        time = ((ASN1GeneralizedTime) aSN1Primitive).getTime();
                    } else {
                        if (aSN1Primitive instanceof BERApplicationSpecific) {
                            str2 = "BER";
                        } else if (aSN1Primitive instanceof DERApplicationSpecific) {
                            str2 = "DER";
                        } else if (aSN1Primitive instanceof DLApplicationSpecific) {
                            str2 = "";
                        } else if (aSN1Primitive instanceof ASN1Enumerated) {
                            outline107 = GeneratedOutlineSupport1.outline113(str, "DER Enumerated(");
                            value = ((ASN1Enumerated) aSN1Primitive).getValue();
                        } else if (aSN1Primitive instanceof ASN1External) {
                            ASN1External aSN1External = (ASN1External) aSN1Primitive;
                            stringBuffer.append(str + "External " + lineSeparator);
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            sb.append(TAB);
                            String sb2 = sb.toString();
                            if (aSN1External.getDirectReference() != null) {
                                StringBuilder outline1133 = GeneratedOutlineSupport1.outline113(sb2, "Direct Reference: ");
                                outline1133.append(aSN1External.getDirectReference().getId());
                                outline1133.append(lineSeparator);
                                stringBuffer.append(outline1133.toString());
                            }
                            if (aSN1External.getIndirectReference() != null) {
                                StringBuilder outline1134 = GeneratedOutlineSupport1.outline113(sb2, "Indirect Reference: ");
                                outline1134.append(aSN1External.getIndirectReference().toString());
                                outline1134.append(lineSeparator);
                                stringBuffer.append(outline1134.toString());
                            }
                            if (aSN1External.getDataValueDescriptor() != null) {
                                _dumpAsString(sb2, z, aSN1External.getDataValueDescriptor(), stringBuffer);
                            }
                            StringBuilder outline1135 = GeneratedOutlineSupport1.outline113(sb2, "Encoding: ");
                            outline1135.append(aSN1External.getEncoding());
                            outline1135.append(lineSeparator);
                            stringBuffer.append(outline1135.toString());
                            _dumpAsString(sb2, z, aSN1External.getExternalContent(), stringBuffer);
                            return;
                        } else {
                            outline107 = GeneratedOutlineSupport1.outline107(str);
                            outline107.append(aSN1Primitive.toString());
                            outline107.append(lineSeparator);
                            outputApplicationSpecific = outline107.toString();
                        }
                        outputApplicationSpecific = outputApplicationSpecific(str2, str, z, aSN1Primitive, lineSeparator);
                    }
                    outline107.append(time);
                    outline107.append(") ");
                    outline107.append(lineSeparator);
                    outputApplicationSpecific = outline107.toString();
                    stringBuffer.append(outputApplicationSpecific);
                }
                outline107 = GeneratedOutlineSupport1.outline113(str, "Integer(");
                value = ((ASN1Integer) aSN1Primitive).getValue();
                outline107.append(value);
            }
            outline107.append(")");
            outline107.append(lineSeparator);
            outputApplicationSpecific = outline107.toString();
            stringBuffer.append(outputApplicationSpecific);
        } else {
            Enumeration objects2 = ((ASN1Set) aSN1Primitive).getObjects();
            String str5 = str + TAB;
            stringBuffer.append(str);
            stringBuffer.append(aSN1Primitive instanceof BERSet ? "BER Set" : aSN1Primitive instanceof DERSet ? "DER Set" : "Set");
            while (true) {
                stringBuffer.append(lineSeparator);
                while (objects2.hasMoreElements()) {
                    Object nextElement2 = objects2.nextElement();
                    if (nextElement2 == null) {
                        break;
                    }
                    _dumpAsString(str5, z, nextElement2 instanceof ASN1Primitive ? (ASN1Primitive) nextElement2 : ((ASN1Encodable) nextElement2).toASN1Primitive(), stringBuffer);
                }
                return;
                stringBuffer.append(str5);
                stringBuffer.append(AppInformation.NULL);
            }
        }
    }

    private static String calculateAscString(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 != i + i2; i3++) {
            if (bArr[i3] >= 32 && bArr[i3] <= 126) {
                stringBuffer.append((char) bArr[i3]);
            }
        }
        return stringBuffer.toString();
    }

    public static String dumpAsString(Object obj) {
        return dumpAsString(obj, false);
    }

    public static String dumpAsString(Object obj, boolean z) {
        ASN1Primitive aSN1Primitive;
        StringBuffer stringBuffer = new StringBuffer();
        if (obj instanceof ASN1Primitive) {
            aSN1Primitive = (ASN1Primitive) obj;
        } else if (!(obj instanceof ASN1Encodable)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown object type ");
            outline107.append(obj.toString());
            return outline107.toString();
        } else {
            aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
        }
        _dumpAsString("", z, aSN1Primitive, stringBuffer);
        return stringBuffer.toString();
    }

    private static String dumpBinaryDataAsString(String str, byte[] bArr) {
        String calculateAscString;
        String lineSeparator = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = str + TAB;
        stringBuffer.append(lineSeparator);
        for (int i = 0; i < bArr.length; i += 32) {
            int length = bArr.length - i;
            stringBuffer.append(str2);
            if (length > 32) {
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, 32)));
                stringBuffer.append(TAB);
                calculateAscString = calculateAscString(bArr, i, 32);
            } else {
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, bArr.length - i)));
                for (int length2 = bArr.length - i; length2 != 32; length2++) {
                    stringBuffer.append("  ");
                }
                stringBuffer.append(TAB);
                calculateAscString = calculateAscString(bArr, i, bArr.length - i);
            }
            stringBuffer.append(calculateAscString);
            stringBuffer.append(lineSeparator);
        }
        return stringBuffer.toString();
    }

    private static String outputApplicationSpecific(String str, String str2, boolean z, ASN1Primitive aSN1Primitive, String str3) {
        ASN1ApplicationSpecific aSN1ApplicationSpecific = ASN1ApplicationSpecific.getInstance(aSN1Primitive);
        StringBuffer stringBuffer = new StringBuffer();
        if (!aSN1ApplicationSpecific.isConstructed()) {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115(str2, str, " ApplicationSpecific[");
            outline115.append(aSN1ApplicationSpecific.getApplicationTag());
            outline115.append("] (");
            outline115.append(Strings.fromByteArray(Hex.encode(aSN1ApplicationSpecific.getContents())));
            outline115.append(")");
            outline115.append(str3);
            return outline115.toString();
        }
        try {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(aSN1ApplicationSpecific.getObject(16));
            stringBuffer.append(str2 + str + " ApplicationSpecific[" + aSN1ApplicationSpecific.getApplicationTag() + "]" + str3);
            Enumeration objects = aSN1Sequence.getObjects();
            while (objects.hasMoreElements()) {
                _dumpAsString(str2 + TAB, z, (ASN1Primitive) objects.nextElement(), stringBuffer);
            }
        } catch (IOException e) {
            stringBuffer.append(e);
        }
        return stringBuffer.toString();
    }
}
