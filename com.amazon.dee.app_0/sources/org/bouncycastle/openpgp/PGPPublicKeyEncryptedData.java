package org.bouncycastle.openpgp;

import java.io.EOFException;
import java.io.InputStream;
import org.bouncycastle.bcpg.BCPGInputStream;
import org.bouncycastle.bcpg.InputStreamPacket;
import org.bouncycastle.bcpg.PublicKeyEncSessionPacket;
import org.bouncycastle.bcpg.SymmetricEncIntegrityPacket;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.operator.PGPDataDecryptor;
import org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory;
import org.bouncycastle.util.io.TeeInputStream;
/* loaded from: classes5.dex */
public class PGPPublicKeyEncryptedData extends PGPEncryptedData {
    PublicKeyEncSessionPacket keyData;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PGPPublicKeyEncryptedData(PublicKeyEncSessionPacket publicKeyEncSessionPacket, InputStreamPacket inputStreamPacket) {
        super(inputStreamPacket);
        this.keyData = publicKeyEncSessionPacket;
    }

    private boolean confirmCheckSum(byte[] bArr) {
        int i = 0;
        for (int i2 = 1; i2 != bArr.length - 2; i2++) {
            i += bArr[i2] & 255;
        }
        return bArr[bArr.length + (-2)] == ((byte) (i >> 8)) && bArr[bArr.length - 1] == ((byte) i);
    }

    public InputStream getDataStream(PublicKeyDataDecryptorFactory publicKeyDataDecryptorFactory) throws PGPException {
        byte[] recoverSessionData = publicKeyDataDecryptorFactory.recoverSessionData(this.keyData.getAlgorithm(), this.keyData.getEncSessionKey());
        if (confirmCheckSum(recoverSessionData)) {
            if (recoverSessionData[0] == 0) {
                return this.encData.getInputStream();
            }
            try {
                boolean z = this.encData instanceof SymmetricEncIntegrityPacket;
                byte[] bArr = new byte[recoverSessionData.length - 3];
                System.arraycopy(recoverSessionData, 1, bArr, 0, bArr.length);
                PGPDataDecryptor createDataDecryptor = publicKeyDataDecryptorFactory.createDataDecryptor(z, recoverSessionData[0] & 255, bArr);
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
                if (read2 >= 0 && read3 >= 0) {
                    return this.encStream;
                }
                throw new EOFException("unexpected end of stream.");
            } catch (PGPException e) {
                throw e;
            } catch (Exception e2) {
                throw new PGPException("Exception starting decryption", e2);
            }
        }
        throw new PGPKeyValidationException("key checksum failed");
    }

    public long getKeyID() {
        return this.keyData.getKeyID();
    }

    public int getSymmetricAlgorithm(PublicKeyDataDecryptorFactory publicKeyDataDecryptorFactory) throws PGPException {
        return publicKeyDataDecryptorFactory.recoverSessionData(this.keyData.getAlgorithm(), this.keyData.getEncSessionKey())[0];
    }
}
