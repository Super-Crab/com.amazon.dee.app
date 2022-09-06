package org.bouncycastle.gpg;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.bcpg.DSAPublicBCPGKey;
import org.bouncycastle.bcpg.DSASecretBCPGKey;
import org.bouncycastle.bcpg.ECDSAPublicBCPGKey;
import org.bouncycastle.bcpg.ECPublicBCPGKey;
import org.bouncycastle.bcpg.ECSecretBCPGKey;
import org.bouncycastle.bcpg.ElGamalPublicBCPGKey;
import org.bouncycastle.bcpg.ElGamalSecretBCPGKey;
import org.bouncycastle.bcpg.PublicKeyPacket;
import org.bouncycastle.bcpg.RSAPublicBCPGKey;
import org.bouncycastle.bcpg.RSASecretBCPGKey;
import org.bouncycastle.bcpg.S2K;
import org.bouncycastle.bcpg.SecretKeyPacket;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
import org.bouncycastle.openpgp.operator.PBEProtectionRemoverFactory;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.PGPDigestCalculatorProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class SExprParser {
    private final PGPDigestCalculatorProvider digestProvider;

    public SExprParser(PGPDigestCalculatorProvider pGPDigestCalculatorProvider) {
        this.digestProvider = pGPDigestCalculatorProvider;
    }

    private static byte[][] extractData(InputStream inputStream, PBEProtectionRemoverFactory pBEProtectionRemoverFactory) throws PGPException, IOException {
        SXprUtils.skipOpenParenthesis(inputStream);
        String readString = SXprUtils.readString(inputStream, inputStream.read());
        byte[] bArr = null;
        if (!readString.equals("protected")) {
            if (!readString.equals("d")) {
                throw new PGPException("protected block not found");
            }
            return null;
        }
        String readString2 = SXprUtils.readString(inputStream, inputStream.read());
        SXprUtils.skipOpenParenthesis(inputStream);
        S2K parseS2K = SXprUtils.parseS2K(inputStream);
        byte[] readBytes = SXprUtils.readBytes(inputStream, inputStream.read());
        SXprUtils.skipCloseParenthesis(inputStream);
        byte[] readBytes2 = SXprUtils.readBytes(inputStream, inputStream.read());
        SXprUtils.skipCloseParenthesis(inputStream);
        PBESecretKeyDecryptor createDecryptor = pBEProtectionRemoverFactory.createDecryptor(readString2);
        byte[] recoverKeyData = createDecryptor.recoverKeyData(7, createDecryptor.makeKeyFromPassPhrase(7, parseS2K), readBytes, readBytes2, 0, readBytes2.length);
        int i = 40;
        if (inputStream.read() == 40) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            do {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
                if (i < 0) {
                    break;
                }
            } while (i != 41);
            if (i != 41) {
                throw new IOException("unexpected end to SExpr");
            }
            byteArrayOutputStream.write(41);
            bArr = byteArrayOutputStream.toByteArray();
        }
        SXprUtils.skipCloseParenthesis(inputStream);
        SXprUtils.skipCloseParenthesis(inputStream);
        return new byte[][]{recoverKeyData, bArr};
    }

    private BigInteger processDSASecretKey(InputStream inputStream, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, PBEProtectionRemoverFactory pBEProtectionRemoverFactory) throws IOException, PGPException {
        byte[][] extractData = extractData(inputStream, pBEProtectionRemoverFactory);
        byte[] bArr = extractData[0];
        byte[] bArr2 = extractData[1];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        SXprUtils.skipOpenParenthesis(byteArrayInputStream);
        SXprUtils.skipOpenParenthesis(byteArrayInputStream);
        BigInteger readBigInteger = readBigInteger(ReactProperties.HereMapMarker.X, byteArrayInputStream);
        SXprUtils.skipCloseParenthesis(byteArrayInputStream);
        SXprUtils.skipOpenParenthesis(byteArrayInputStream);
        if (SXprUtils.readString(byteArrayInputStream, byteArrayInputStream.read()).equals("hash")) {
            if (!SXprUtils.readString(byteArrayInputStream, byteArrayInputStream.read()).equals("sha1")) {
                throw new PGPException("hash keyword expected");
            }
            byte[] readBytes = SXprUtils.readBytes(byteArrayInputStream, byteArrayInputStream.read());
            SXprUtils.skipCloseParenthesis(byteArrayInputStream);
            PGPDigestCalculatorProvider pGPDigestCalculatorProvider = this.digestProvider;
            if (pGPDigestCalculatorProvider != null) {
                PGPDigestCalculator pGPDigestCalculator = pGPDigestCalculatorProvider.get(2);
                OutputStream outputStream = pGPDigestCalculator.getOutputStream();
                outputStream.write(Strings.toByteArray("(3:dsa"));
                writeCanonical(outputStream, "p", bigInteger);
                writeCanonical(outputStream, "q", bigInteger2);
                writeCanonical(outputStream, "g", bigInteger3);
                writeCanonical(outputStream, ReactProperties.HereMapMarker.Y, bigInteger4);
                writeCanonical(outputStream, ReactProperties.HereMapMarker.X, readBigInteger);
                if (bArr2 != null) {
                    outputStream.write(bArr2);
                }
                outputStream.write(Strings.toByteArray(")"));
                if (!Arrays.constantTimeAreEqual(pGPDigestCalculator.getDigest(), readBytes)) {
                    throw new PGPException("checksum on protected data failed in SExpr");
                }
            }
            return readBigInteger;
        }
        throw new PGPException("hash keyword expected");
    }

    private BigInteger processECSecretKey(InputStream inputStream, String str, String str2, byte[] bArr, PBEProtectionRemoverFactory pBEProtectionRemoverFactory) throws IOException, PGPException {
        byte[][] extractData = extractData(inputStream, pBEProtectionRemoverFactory);
        byte[] bArr2 = extractData[0];
        byte[] bArr3 = extractData[1];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr2);
        SXprUtils.skipOpenParenthesis(byteArrayInputStream);
        SXprUtils.skipOpenParenthesis(byteArrayInputStream);
        BigInteger readBigInteger = readBigInteger("d", byteArrayInputStream);
        SXprUtils.skipCloseParenthesis(byteArrayInputStream);
        SXprUtils.skipOpenParenthesis(byteArrayInputStream);
        if (SXprUtils.readString(byteArrayInputStream, byteArrayInputStream.read()).equals("hash")) {
            if (!SXprUtils.readString(byteArrayInputStream, byteArrayInputStream.read()).equals("sha1")) {
                throw new PGPException("hash keyword expected");
            }
            byte[] readBytes = SXprUtils.readBytes(byteArrayInputStream, byteArrayInputStream.read());
            SXprUtils.skipCloseParenthesis(byteArrayInputStream);
            PGPDigestCalculatorProvider pGPDigestCalculatorProvider = this.digestProvider;
            if (pGPDigestCalculatorProvider != null) {
                PGPDigestCalculator pGPDigestCalculator = pGPDigestCalculatorProvider.get(2);
                OutputStream outputStream = pGPDigestCalculator.getOutputStream();
                outputStream.write(Strings.toByteArray("(3:ecc"));
                outputStream.write(Strings.toByteArray("(" + str.length() + ":" + str + str2.length() + ":" + str2 + ")"));
                writeCanonical(outputStream, "q", bArr);
                writeCanonical(outputStream, "d", readBigInteger);
                if (bArr3 != null) {
                    outputStream.write(bArr3);
                }
                outputStream.write(Strings.toByteArray(")"));
                if (!Arrays.constantTimeAreEqual(pGPDigestCalculator.getDigest(), readBytes)) {
                    throw new PGPException("checksum on protected data failed in SExpr");
                }
            }
            return readBigInteger;
        }
        throw new PGPException("hash keyword expected");
    }

    private BigInteger processElGamalSecretKey(InputStream inputStream, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, PBEProtectionRemoverFactory pBEProtectionRemoverFactory) throws IOException, PGPException {
        byte[][] extractData = extractData(inputStream, pBEProtectionRemoverFactory);
        byte[] bArr = extractData[0];
        byte[] bArr2 = extractData[1];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        SXprUtils.skipOpenParenthesis(byteArrayInputStream);
        SXprUtils.skipOpenParenthesis(byteArrayInputStream);
        BigInteger readBigInteger = readBigInteger(ReactProperties.HereMapMarker.X, byteArrayInputStream);
        SXprUtils.skipCloseParenthesis(byteArrayInputStream);
        SXprUtils.skipOpenParenthesis(byteArrayInputStream);
        if (SXprUtils.readString(byteArrayInputStream, byteArrayInputStream.read()).equals("hash")) {
            if (!SXprUtils.readString(byteArrayInputStream, byteArrayInputStream.read()).equals("sha1")) {
                throw new PGPException("hash keyword expected");
            }
            byte[] readBytes = SXprUtils.readBytes(byteArrayInputStream, byteArrayInputStream.read());
            SXprUtils.skipCloseParenthesis(byteArrayInputStream);
            PGPDigestCalculatorProvider pGPDigestCalculatorProvider = this.digestProvider;
            if (pGPDigestCalculatorProvider != null) {
                PGPDigestCalculator pGPDigestCalculator = pGPDigestCalculatorProvider.get(2);
                OutputStream outputStream = pGPDigestCalculator.getOutputStream();
                outputStream.write(Strings.toByteArray("(3:elg"));
                writeCanonical(outputStream, "p", bigInteger);
                writeCanonical(outputStream, "g", bigInteger2);
                writeCanonical(outputStream, ReactProperties.HereMapMarker.Y, bigInteger3);
                writeCanonical(outputStream, ReactProperties.HereMapMarker.X, readBigInteger);
                if (bArr2 != null) {
                    outputStream.write(bArr2);
                }
                outputStream.write(Strings.toByteArray(")"));
                if (!Arrays.constantTimeAreEqual(pGPDigestCalculator.getDigest(), readBytes)) {
                    throw new PGPException("checksum on protected data failed in SExpr");
                }
            }
            return readBigInteger;
        }
        throw new PGPException("hash keyword expected");
    }

    private BigInteger[] processRSASecretKey(InputStream inputStream, BigInteger bigInteger, BigInteger bigInteger2, PBEProtectionRemoverFactory pBEProtectionRemoverFactory) throws IOException, PGPException {
        byte[] bArr;
        BigInteger readBigInteger;
        ByteArrayInputStream byteArrayInputStream = inputStream;
        byte[][] extractData = extractData(byteArrayInputStream, pBEProtectionRemoverFactory);
        if (extractData == null) {
            readBigInteger = new BigInteger(1, SXprUtils.readBytes(byteArrayInputStream, inputStream.read()));
            SXprUtils.skipCloseParenthesis(inputStream);
            bArr = null;
        } else {
            byte[] bArr2 = extractData[0];
            bArr = extractData[1];
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr2);
            SXprUtils.skipOpenParenthesis(byteArrayInputStream2);
            SXprUtils.skipOpenParenthesis(byteArrayInputStream2);
            readBigInteger = readBigInteger("d", byteArrayInputStream2);
            byteArrayInputStream = byteArrayInputStream2;
        }
        BigInteger readBigInteger2 = readBigInteger("p", byteArrayInputStream);
        BigInteger readBigInteger3 = readBigInteger("q", byteArrayInputStream);
        BigInteger readBigInteger4 = readBigInteger("u", byteArrayInputStream);
        if (extractData == null) {
            return new BigInteger[]{readBigInteger, readBigInteger2, readBigInteger3, readBigInteger4};
        }
        SXprUtils.skipCloseParenthesis(byteArrayInputStream);
        SXprUtils.skipOpenParenthesis(byteArrayInputStream);
        if (!SXprUtils.readString(byteArrayInputStream, byteArrayInputStream.read()).equals("hash")) {
            throw new PGPException("hash keyword expected");
        }
        if (!SXprUtils.readString(byteArrayInputStream, byteArrayInputStream.read()).equals("sha1")) {
            throw new PGPException("hash keyword expected");
        }
        byte[] readBytes = SXprUtils.readBytes(byteArrayInputStream, byteArrayInputStream.read());
        SXprUtils.skipCloseParenthesis(byteArrayInputStream);
        PGPDigestCalculatorProvider pGPDigestCalculatorProvider = this.digestProvider;
        if (pGPDigestCalculatorProvider != null) {
            PGPDigestCalculator pGPDigestCalculator = pGPDigestCalculatorProvider.get(2);
            OutputStream outputStream = pGPDigestCalculator.getOutputStream();
            outputStream.write(Strings.toByteArray("(3:rsa"));
            writeCanonical(outputStream, JsonReportFormat.COUNT, bigInteger);
            writeCanonical(outputStream, "e", bigInteger2);
            writeCanonical(outputStream, "d", readBigInteger);
            writeCanonical(outputStream, "p", readBigInteger2);
            writeCanonical(outputStream, "q", readBigInteger3);
            writeCanonical(outputStream, "u", readBigInteger4);
            if (bArr != null) {
                outputStream.write(bArr);
            }
            outputStream.write(Strings.toByteArray(")"));
            if (!Arrays.constantTimeAreEqual(pGPDigestCalculator.getDigest(), readBytes)) {
                throw new PGPException("checksum on protected data failed in SExpr");
            }
        }
        return new BigInteger[]{readBigInteger, readBigInteger2, readBigInteger3, readBigInteger4};
    }

    private BigInteger readBigInteger(String str, InputStream inputStream) throws IOException, PGPException {
        SXprUtils.skipOpenParenthesis(inputStream);
        if (SXprUtils.readString(inputStream, inputStream.read()).equals(str)) {
            BigInteger bigInteger = new BigInteger(1, SXprUtils.readBytes(inputStream, inputStream.read()));
            SXprUtils.skipCloseParenthesis(inputStream);
            return bigInteger;
        }
        throw new PGPException(GeneratedOutlineSupport1.outline72(str, " value expected"));
    }

    private void writeCanonical(OutputStream outputStream, String str, BigInteger bigInteger) throws IOException {
        writeCanonical(outputStream, str, bigInteger.toByteArray());
    }

    private void writeCanonical(OutputStream outputStream, String str, byte[] bArr) throws IOException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("(");
        outline107.append(str.length());
        outline107.append(":");
        outline107.append(str);
        outline107.append(bArr.length);
        outline107.append(":");
        outputStream.write(Strings.toByteArray(outline107.toString()));
        outputStream.write(bArr);
        outputStream.write(Strings.toByteArray(")"));
    }

    public PGPSecretKey parseSecretKey(InputStream inputStream, PBEProtectionRemoverFactory pBEProtectionRemoverFactory, PGPPublicKey pGPPublicKey) throws IOException, PGPException {
        SXprUtils.skipOpenParenthesis(inputStream);
        String readString = SXprUtils.readString(inputStream, inputStream.read());
        if (readString.equals("protected-private-key") || readString.equals("private-key")) {
            SXprUtils.skipOpenParenthesis(inputStream);
            String readString2 = SXprUtils.readString(inputStream, inputStream.read());
            if (readString2.equals("ecc")) {
                SXprUtils.skipOpenParenthesis(inputStream);
                String readString3 = SXprUtils.readString(inputStream, inputStream.read());
                String readString4 = SXprUtils.readString(inputStream, inputStream.read());
                SXprUtils.skipCloseParenthesis(inputStream);
                SXprUtils.skipOpenParenthesis(inputStream);
                if (!SXprUtils.readString(inputStream, inputStream.read()).equals("q")) {
                    throw new PGPException("no q value found");
                }
                byte[] readBytes = SXprUtils.readBytes(inputStream, inputStream.read());
                SXprUtils.skipCloseParenthesis(inputStream);
                BigInteger processECSecretKey = processECSecretKey(inputStream, readString3, readString4, readBytes, pBEProtectionRemoverFactory);
                if (readString4.startsWith("NIST ")) {
                    readString4 = readString4.substring(5);
                }
                ECDSAPublicBCPGKey eCDSAPublicBCPGKey = new ECDSAPublicBCPGKey(ECNamedCurveTable.getOID(readString4), new BigInteger(1, readBytes));
                ECPublicBCPGKey eCPublicBCPGKey = (ECPublicBCPGKey) pGPPublicKey.getPublicKeyPacket().getKey();
                if (eCDSAPublicBCPGKey.getCurveOID().equals((ASN1Primitive) eCPublicBCPGKey.getCurveOID()) && eCDSAPublicBCPGKey.getEncodedPoint().equals(eCPublicBCPGKey.getEncodedPoint())) {
                    return new PGPSecretKey(new SecretKeyPacket(pGPPublicKey.getPublicKeyPacket(), 0, null, null, new ECSecretBCPGKey(processECSecretKey).getEncoded()), pGPPublicKey);
                }
                throw new PGPException("passed in public key does not match secret key");
            } else if (readString2.equals("dsa")) {
                BigInteger readBigInteger = readBigInteger("p", inputStream);
                BigInteger readBigInteger2 = readBigInteger("q", inputStream);
                BigInteger readBigInteger3 = readBigInteger("g", inputStream);
                BigInteger readBigInteger4 = readBigInteger(ReactProperties.HereMapMarker.Y, inputStream);
                BigInteger processDSASecretKey = processDSASecretKey(inputStream, readBigInteger, readBigInteger2, readBigInteger3, readBigInteger4, pBEProtectionRemoverFactory);
                DSAPublicBCPGKey dSAPublicBCPGKey = new DSAPublicBCPGKey(readBigInteger, readBigInteger2, readBigInteger3, readBigInteger4);
                DSAPublicBCPGKey dSAPublicBCPGKey2 = (DSAPublicBCPGKey) pGPPublicKey.getPublicKeyPacket().getKey();
                if (dSAPublicBCPGKey.getP().equals(dSAPublicBCPGKey2.getP()) && dSAPublicBCPGKey.getQ().equals(dSAPublicBCPGKey2.getQ()) && dSAPublicBCPGKey.getG().equals(dSAPublicBCPGKey2.getG()) && dSAPublicBCPGKey.getY().equals(dSAPublicBCPGKey2.getY())) {
                    return new PGPSecretKey(new SecretKeyPacket(pGPPublicKey.getPublicKeyPacket(), 0, null, null, new DSASecretBCPGKey(processDSASecretKey).getEncoded()), pGPPublicKey);
                }
                throw new PGPException("passed in public key does not match secret key");
            } else if (readString2.equals("elg")) {
                BigInteger readBigInteger5 = readBigInteger("p", inputStream);
                BigInteger readBigInteger6 = readBigInteger("g", inputStream);
                BigInteger readBigInteger7 = readBigInteger(ReactProperties.HereMapMarker.Y, inputStream);
                BigInteger processElGamalSecretKey = processElGamalSecretKey(inputStream, readBigInteger5, readBigInteger6, readBigInteger7, pBEProtectionRemoverFactory);
                ElGamalPublicBCPGKey elGamalPublicBCPGKey = new ElGamalPublicBCPGKey(readBigInteger5, readBigInteger6, readBigInteger7);
                ElGamalPublicBCPGKey elGamalPublicBCPGKey2 = (ElGamalPublicBCPGKey) pGPPublicKey.getPublicKeyPacket().getKey();
                if (elGamalPublicBCPGKey.getP().equals(elGamalPublicBCPGKey2.getP()) && elGamalPublicBCPGKey.getG().equals(elGamalPublicBCPGKey2.getG()) && elGamalPublicBCPGKey.getY().equals(elGamalPublicBCPGKey2.getY())) {
                    return new PGPSecretKey(new SecretKeyPacket(pGPPublicKey.getPublicKeyPacket(), 0, null, null, new ElGamalSecretBCPGKey(processElGamalSecretKey).getEncoded()), pGPPublicKey);
                }
                throw new PGPException("passed in public key does not match secret key");
            } else if (!readString2.equals("rsa")) {
                throw new PGPException(GeneratedOutlineSupport1.outline72("unknown key type: ", readString2));
            } else {
                BigInteger readBigInteger8 = readBigInteger(JsonReportFormat.COUNT, inputStream);
                BigInteger readBigInteger9 = readBigInteger("e", inputStream);
                BigInteger[] processRSASecretKey = processRSASecretKey(inputStream, readBigInteger8, readBigInteger9, pBEProtectionRemoverFactory);
                RSAPublicBCPGKey rSAPublicBCPGKey = new RSAPublicBCPGKey(readBigInteger8, readBigInteger9);
                RSAPublicBCPGKey rSAPublicBCPGKey2 = (RSAPublicBCPGKey) pGPPublicKey.getPublicKeyPacket().getKey();
                if (rSAPublicBCPGKey.getModulus().equals(rSAPublicBCPGKey2.getModulus()) && rSAPublicBCPGKey.getPublicExponent().equals(rSAPublicBCPGKey2.getPublicExponent())) {
                    return new PGPSecretKey(new SecretKeyPacket(pGPPublicKey.getPublicKeyPacket(), 0, null, null, new RSASecretBCPGKey(processRSASecretKey[0], processRSASecretKey[1], processRSASecretKey[2]).getEncoded()), pGPPublicKey);
                }
                throw new PGPException("passed in public key does not match secret key");
            }
        }
        throw new PGPException("unknown key type found");
    }

    public PGPSecretKey parseSecretKey(InputStream inputStream, PBEProtectionRemoverFactory pBEProtectionRemoverFactory, KeyFingerPrintCalculator keyFingerPrintCalculator) throws IOException, PGPException {
        SXprUtils.skipOpenParenthesis(inputStream);
        String readString = SXprUtils.readString(inputStream, inputStream.read());
        if (readString.equals("protected-private-key") || readString.equals("private-key")) {
            SXprUtils.skipOpenParenthesis(inputStream);
            String readString2 = SXprUtils.readString(inputStream, inputStream.read());
            if (readString2.equals("ecc")) {
                SXprUtils.skipOpenParenthesis(inputStream);
                String readString3 = SXprUtils.readString(inputStream, inputStream.read());
                String readString4 = SXprUtils.readString(inputStream, inputStream.read());
                if (readString4.startsWith("NIST ")) {
                    readString4 = readString4.substring(5);
                }
                String str = readString4;
                SXprUtils.skipCloseParenthesis(inputStream);
                SXprUtils.skipOpenParenthesis(inputStream);
                if (!SXprUtils.readString(inputStream, inputStream.read()).equals("q")) {
                    throw new PGPException("no q value found");
                }
                byte[] readBytes = SXprUtils.readBytes(inputStream, inputStream.read());
                PublicKeyPacket publicKeyPacket = new PublicKeyPacket(19, new Date(), new ECDSAPublicBCPGKey(ECNamedCurveTable.getOID(str), new BigInteger(1, readBytes)));
                SXprUtils.skipCloseParenthesis(inputStream);
                return new PGPSecretKey(new SecretKeyPacket(publicKeyPacket, 0, null, null, new ECSecretBCPGKey(processECSecretKey(inputStream, readString3, str, readBytes, pBEProtectionRemoverFactory)).getEncoded()), new PGPPublicKey(publicKeyPacket, keyFingerPrintCalculator));
            } else if (readString2.equals("dsa")) {
                BigInteger readBigInteger = readBigInteger("p", inputStream);
                BigInteger readBigInteger2 = readBigInteger("q", inputStream);
                BigInteger readBigInteger3 = readBigInteger("g", inputStream);
                BigInteger readBigInteger4 = readBigInteger(ReactProperties.HereMapMarker.Y, inputStream);
                BigInteger processDSASecretKey = processDSASecretKey(inputStream, readBigInteger, readBigInteger2, readBigInteger3, readBigInteger4, pBEProtectionRemoverFactory);
                PublicKeyPacket publicKeyPacket2 = new PublicKeyPacket(17, new Date(), new DSAPublicBCPGKey(readBigInteger, readBigInteger2, readBigInteger3, readBigInteger4));
                return new PGPSecretKey(new SecretKeyPacket(publicKeyPacket2, 0, null, null, new DSASecretBCPGKey(processDSASecretKey).getEncoded()), new PGPPublicKey(publicKeyPacket2, keyFingerPrintCalculator));
            } else if (readString2.equals("elg")) {
                BigInteger readBigInteger5 = readBigInteger("p", inputStream);
                BigInteger readBigInteger6 = readBigInteger("g", inputStream);
                BigInteger readBigInteger7 = readBigInteger(ReactProperties.HereMapMarker.Y, inputStream);
                BigInteger processElGamalSecretKey = processElGamalSecretKey(inputStream, readBigInteger5, readBigInteger6, readBigInteger7, pBEProtectionRemoverFactory);
                PublicKeyPacket publicKeyPacket3 = new PublicKeyPacket(16, new Date(), new ElGamalPublicBCPGKey(readBigInteger5, readBigInteger6, readBigInteger7));
                return new PGPSecretKey(new SecretKeyPacket(publicKeyPacket3, 0, null, null, new ElGamalSecretBCPGKey(processElGamalSecretKey).getEncoded()), new PGPPublicKey(publicKeyPacket3, keyFingerPrintCalculator));
            } else if (!readString2.equals("rsa")) {
                throw new PGPException(GeneratedOutlineSupport1.outline72("unknown key type: ", readString2));
            } else {
                BigInteger readBigInteger8 = readBigInteger(JsonReportFormat.COUNT, inputStream);
                BigInteger readBigInteger9 = readBigInteger("e", inputStream);
                BigInteger[] processRSASecretKey = processRSASecretKey(inputStream, readBigInteger8, readBigInteger9, pBEProtectionRemoverFactory);
                PublicKeyPacket publicKeyPacket4 = new PublicKeyPacket(1, new Date(), new RSAPublicBCPGKey(readBigInteger8, readBigInteger9));
                return new PGPSecretKey(new SecretKeyPacket(publicKeyPacket4, 0, null, null, new RSASecretBCPGKey(processRSASecretKey[0], processRSASecretKey[1], processRSASecretKey[2]).getEncoded()), new PGPPublicKey(publicKeyPacket4, keyFingerPrintCalculator));
            }
        }
        throw new PGPException("unknown key type found");
    }
}
