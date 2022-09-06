package org.bouncycastle.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes5.dex */
public class OCSPStatusRequest {
    protected Extensions requestExtensions;
    protected Vector responderIDList;

    public OCSPStatusRequest(Vector vector, Extensions extensions) {
        this.responderIDList = vector;
        this.requestExtensions = extensions;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.bouncycastle.tls.OCSPStatusRequest parse(java.io.InputStream r3) throws java.io.IOException {
        /*
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
            byte[] r1 = org.bouncycastle.tls.TlsUtils.readOpaque16(r3)
            int r2 = r1.length
            if (r2 <= 0) goto L27
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r1)
        L11:
            r1 = 1
            byte[] r1 = org.bouncycastle.tls.TlsUtils.readOpaque16(r2, r1)
            org.bouncycastle.asn1.ASN1Primitive r1 = org.bouncycastle.tls.TlsUtils.readDERObject(r1)
            org.bouncycastle.asn1.ocsp.ResponderID r1 = org.bouncycastle.asn1.ocsp.ResponderID.getInstance(r1)
            r0.addElement(r1)
            int r1 = r2.available()
            if (r1 > 0) goto L11
        L27:
            r1 = 0
            byte[] r3 = org.bouncycastle.tls.TlsUtils.readOpaque16(r3)
            int r2 = r3.length
            if (r2 <= 0) goto L37
            org.bouncycastle.asn1.ASN1Primitive r3 = org.bouncycastle.tls.TlsUtils.readDERObject(r3)
            org.bouncycastle.asn1.x509.Extensions r1 = org.bouncycastle.asn1.x509.Extensions.getInstance(r3)
        L37:
            org.bouncycastle.tls.OCSPStatusRequest r3 = new org.bouncycastle.tls.OCSPStatusRequest
            r3.<init>(r0, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.OCSPStatusRequest.parse(java.io.InputStream):org.bouncycastle.tls.OCSPStatusRequest");
    }

    public void encode(OutputStream outputStream) throws IOException {
        Vector vector = this.responderIDList;
        if (vector == null || vector.isEmpty()) {
            TlsUtils.writeUint16(0, outputStream);
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = 0; i < this.responderIDList.size(); i++) {
                TlsUtils.writeOpaque16(((ResponderID) this.responderIDList.elementAt(i)).getEncoded("DER"), byteArrayOutputStream);
            }
            TlsUtils.checkUint16(byteArrayOutputStream.size());
            TlsUtils.writeUint16(byteArrayOutputStream.size(), outputStream);
            Streams.writeBufTo(byteArrayOutputStream, outputStream);
        }
        Extensions extensions = this.requestExtensions;
        if (extensions == null) {
            TlsUtils.writeUint16(0, outputStream);
            return;
        }
        byte[] encoded = extensions.getEncoded("DER");
        TlsUtils.checkUint16(encoded.length);
        TlsUtils.writeUint16(encoded.length, outputStream);
        outputStream.write(encoded);
    }

    public Extensions getRequestExtensions() {
        return this.requestExtensions;
    }

    public Vector getResponderIDList() {
        return this.responderIDList;
    }
}
