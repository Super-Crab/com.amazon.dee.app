package org.bouncycastle.tsp.cms;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.bouncycastle.asn1.cms.TimeStampedDataParser;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
class TimeStampDataUtil {
    private final MetaDataUtil metaDataUtil;
    private final TimeStampAndCRL[] timeStamps;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeStampDataUtil(TimeStampedData timeStampedData) {
        this.metaDataUtil = new MetaDataUtil(timeStampedData.getMetaData());
        this.timeStamps = timeStampedData.getTemporalEvidence().getTstEvidence().toTimeStampAndCRLArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeStampDataUtil(TimeStampedDataParser timeStampedDataParser) throws IOException {
        this.metaDataUtil = new MetaDataUtil(timeStampedDataParser.getMetaData());
        this.timeStamps = timeStampedDataParser.getTemporalEvidence().getTstEvidence().toTimeStampAndCRLArray();
    }

    private void compareDigest(TimeStampToken timeStampToken, byte[] bArr) throws ImprintDigestInvalidException {
        if (Arrays.areEqual(bArr, timeStampToken.getTimeStampInfo().getMessageImprintDigest())) {
            return;
        }
        throw new ImprintDigestInvalidException("hash calculated is different from MessageImprintDigest found in TimeStampToken", timeStampToken);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] calculateNextHash(DigestCalculator digestCalculator) throws CMSException {
        TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
        TimeStampAndCRL timeStampAndCRL = timeStampAndCRLArr[timeStampAndCRLArr.length - 1];
        OutputStream outputStream = digestCalculator.getOutputStream();
        try {
            outputStream.write(timeStampAndCRL.getEncoded("DER"));
            outputStream.close();
            return digestCalculator.getDigest();
        } catch (IOException e) {
            throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("exception calculating hash: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getFileName() {
        return this.metaDataUtil.getFileName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getMediaType() {
        return this.metaDataUtil.getMediaType();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestCalculator getMessageImprintDigestCalculator(DigestCalculatorProvider digestCalculatorProvider) throws OperatorCreationException {
        try {
            DigestCalculator digestCalculator = digestCalculatorProvider.get(new AlgorithmIdentifier(getTimeStampToken(this.timeStamps[0]).getTimeStampInfo().getMessageImprintAlgOID()));
            initialiseMessageImprintDigestCalculator(digestCalculator);
            return digestCalculator;
        } catch (CMSException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unable to extract algorithm ID: ");
            outline107.append(e.getMessage());
            throw new OperatorCreationException(outline107.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AttributeTable getOtherMetaData() {
        return new AttributeTable(this.metaDataUtil.getOtherMetaData());
    }

    TimeStampToken getTimeStampToken(TimeStampAndCRL timeStampAndCRL) throws CMSException {
        try {
            return new TimeStampToken(timeStampAndCRL.getTimeStampToken());
        } catch (IOException e) {
            throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to parse token data: ")), e);
        } catch (IllegalArgumentException e2) {
            throw new CMSException(GeneratedOutlineSupport1.outline43(e2, GeneratedOutlineSupport1.outline107("token data invalid: ")), e2);
        } catch (TSPException e3) {
            if (e3.getCause() instanceof CMSException) {
                throw ((CMSException) e3.getCause());
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("token data invalid: ");
            outline107.append(e3.getMessage());
            throw new CMSException(outline107.toString(), e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeStampToken[] getTimeStampTokens() throws CMSException {
        TimeStampToken[] timeStampTokenArr = new TimeStampToken[this.timeStamps.length];
        int i = 0;
        while (true) {
            TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
            if (i < timeStampAndCRLArr.length) {
                timeStampTokenArr[i] = getTimeStampToken(timeStampAndCRLArr[i]);
                i++;
            } else {
                return timeStampTokenArr;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeStampAndCRL[] getTimeStamps() {
        return this.timeStamps;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        this.metaDataUtil.initialiseMessageImprintDigestCalculator(digestCalculator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr) throws ImprintDigestInvalidException, CMSException {
        int i = 0;
        while (true) {
            TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
            if (i < timeStampAndCRLArr.length) {
                try {
                    TimeStampToken timeStampToken = getTimeStampToken(timeStampAndCRLArr[i]);
                    if (i > 0) {
                        DigestCalculator digestCalculator = digestCalculatorProvider.get(timeStampToken.getTimeStampInfo().getHashAlgorithm());
                        digestCalculator.getOutputStream().write(this.timeStamps[i - 1].getEncoded("DER"));
                        bArr = digestCalculator.getDigest();
                    }
                    compareDigest(timeStampToken, bArr);
                    i++;
                } catch (IOException e) {
                    throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("exception calculating hash: ")), e);
                } catch (OperatorCreationException e2) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot create digest: ");
                    outline107.append(e2.getMessage());
                    throw new CMSException(outline107.toString(), e2);
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr, TimeStampToken timeStampToken) throws ImprintDigestInvalidException, CMSException {
        try {
            byte[] encoded = timeStampToken.getEncoded();
            int i = 0;
            while (true) {
                TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
                if (i >= timeStampAndCRLArr.length) {
                    throw new ImprintDigestInvalidException("passed in token not associated with timestamps present", timeStampToken);
                }
                try {
                    TimeStampToken timeStampToken2 = getTimeStampToken(timeStampAndCRLArr[i]);
                    if (i > 0) {
                        DigestCalculator digestCalculator = digestCalculatorProvider.get(timeStampToken2.getTimeStampInfo().getHashAlgorithm());
                        digestCalculator.getOutputStream().write(this.timeStamps[i - 1].getEncoded("DER"));
                        bArr = digestCalculator.getDigest();
                    }
                    compareDigest(timeStampToken2, bArr);
                    if (Arrays.areEqual(timeStampToken2.getEncoded(), encoded)) {
                        return;
                    }
                    i++;
                } catch (IOException e) {
                    throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("exception calculating hash: ")), e);
                } catch (OperatorCreationException e2) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot create digest: ");
                    outline107.append(e2.getMessage());
                    throw new CMSException(outline107.toString(), e2);
                }
            }
        } catch (IOException e3) {
            throw new CMSException(GeneratedOutlineSupport1.outline37(e3, GeneratedOutlineSupport1.outline107("exception encoding timeStampToken: ")), e3);
        }
    }
}
