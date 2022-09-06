package org.bouncycastle.openpgp;

import java.io.EOFException;
import java.io.InputStream;
import org.bouncycastle.bcpg.BCPGInputStream;
import org.bouncycastle.bcpg.InputStreamPacket;
import org.bouncycastle.bcpg.SymmetricEncIntegrityPacket;
import org.bouncycastle.bcpg.SymmetricKeyEncSessionPacket;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.operator.PBEDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.PGPDataDecryptor;
import org.bouncycastle.util.io.TeeInputStream;
/* loaded from: classes5.dex */
public class PGPPBEEncryptedData extends PGPEncryptedData {
    SymmetricKeyEncSessionPacket keyData;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PGPPBEEncryptedData(SymmetricKeyEncSessionPacket symmetricKeyEncSessionPacket, InputStreamPacket inputStreamPacket) {
        super(inputStreamPacket);
        this.keyData = symmetricKeyEncSessionPacket;
    }

    public InputStream getDataStream(PBEDataDecryptorFactory pBEDataDecryptorFactory) throws PGPException {
        try {
            byte[] makeKeyFromPassPhrase = pBEDataDecryptorFactory.makeKeyFromPassPhrase(this.keyData.getEncAlgorithm(), this.keyData.getS2K());
            boolean z = this.encData instanceof SymmetricEncIntegrityPacket;
            byte[] recoverSessionData = pBEDataDecryptorFactory.recoverSessionData(this.keyData.getEncAlgorithm(), makeKeyFromPassPhrase, this.keyData.getSecKeyData());
            boolean z2 = true;
            byte[] bArr = new byte[recoverSessionData.length - 1];
            System.arraycopy(recoverSessionData, 1, bArr, 0, bArr.length);
            PGPDataDecryptor createDataDecryptor = pBEDataDecryptorFactory.createDataDecryptor(z, recoverSessionData[0] & 255, bArr);
            this.encStream = new BCPGInputStream(createDataDecryptor.getInputStream(this.encData.getInputStream()));
            if (z) {
                this.truncStream = new PGPEncryptedData.TruncatedStream(this.encStream);
                this.integrityCalculator = createDataDecryptor.getIntegrityCalculator();
                this.encStream = new TeeInputStream(this.truncStream, this.integrityCalculator.getOutputStream());
            }
            byte[] bArr2 = new byte[createDataDecryptor.getBlockSize()];
            for (int i = 0; i != bArr2.length; i++) {
                int read = this.encStream.read();
                if (read < 0) {
                    throw new EOFException("unexpected end of stream.");
                }
                bArr2[i] = (byte) read;
            }
            int read2 = this.encStream.read();
            int read3 = this.encStream.read();
            if (read2 < 0 || read3 < 0) {
                throw new EOFException("unexpected end of stream.");
            }
            boolean z3 = bArr2[bArr2.length + (-2)] == ((byte) read2) && bArr2[bArr2.length - 1] == ((byte) read3);
            if (read2 != 0 || read3 != 0) {
                z2 = false;
            }
            if (!z3 && !z2) {
                throw new PGPDataValidationException("data check failed.");
            }
            return this.encStream;
        } catch (PGPException e) {
            throw e;
        } catch (Exception e2) {
            throw new PGPException("Exception creating cipher", e2);
        }
    }

    public int getSymmetricAlgorithm(PBEDataDecryptorFactory pBEDataDecryptorFactory) throws PGPException {
        return pBEDataDecryptorFactory.recoverSessionData(this.keyData.getEncAlgorithm(), pBEDataDecryptorFactory.makeKeyFromPassPhrase(this.keyData.getEncAlgorithm(), this.keyData.getS2K()), this.keyData.getSecKeyData())[0];
    }
}
