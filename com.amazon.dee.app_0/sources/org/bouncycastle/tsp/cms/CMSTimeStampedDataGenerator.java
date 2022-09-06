package org.bouncycastle.tsp.cms;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.Evidence;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;
import org.bouncycastle.asn1.cms.TimeStampTokenEvidence;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes5.dex */
public class CMSTimeStampedDataGenerator extends CMSTimeStampedGenerator {
    public CMSTimeStampedData generate(TimeStampToken timeStampToken) throws CMSException {
        return generate(timeStampToken, (InputStream) null);
    }

    public CMSTimeStampedData generate(TimeStampToken timeStampToken, InputStream inputStream) throws CMSException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (inputStream != null) {
            try {
                Streams.pipeAll(inputStream, byteArrayOutputStream);
            } catch (IOException e) {
                throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("exception encapsulating content: ")), e);
            }
        }
        DERIA5String dERIA5String = null;
        BEROctetString bEROctetString = byteArrayOutputStream.size() != 0 ? new BEROctetString(byteArrayOutputStream.toByteArray()) : null;
        TimeStampAndCRL timeStampAndCRL = new TimeStampAndCRL(timeStampToken.toCMSSignedData().toASN1Structure());
        URI uri = this.dataUri;
        if (uri != null) {
            dERIA5String = new DERIA5String(uri.toString());
        }
        return new CMSTimeStampedData(new ContentInfo(CMSObjectIdentifiers.timestampedData, new TimeStampedData(dERIA5String, this.metaData, bEROctetString, new Evidence(new TimeStampTokenEvidence(timeStampAndCRL)))));
    }

    public CMSTimeStampedData generate(TimeStampToken timeStampToken, byte[] bArr) throws CMSException {
        return generate(timeStampToken, new ByteArrayInputStream(bArr));
    }
}
