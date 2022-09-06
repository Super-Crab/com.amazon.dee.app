package org.bouncycastle.crypto.modes;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.ParametersWithSBox;
/* loaded from: classes4.dex */
public class GCFBBlockCipher extends StreamBlockCipher {
    private static final byte[] C = {105, 0, 114, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 100, -55, 4, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, -115, 58, -37, -106, 70, -23, GenericAccessProfile.MESH_MESSAGE, -60, 24, -2, -84, -108, 0, -19, 7, 18, -64, -122, -36, -62, ByteSourceJsonBootstrapper.UTF8_BOM_1, 76, -87, GenericAccessProfile.MESH_BEACON};
    private final CFBBlockCipher cfbEngine;
    private long counter;
    private boolean forEncryption;
    private KeyParameter key;

    public GCFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.counter = 0L;
        this.cfbEngine = new CFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8);
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    protected byte calculateByte(byte b) {
        long j = this.counter;
        if (j > 0 && j % 1024 == 0) {
            BlockCipher underlyingCipher = this.cfbEngine.getUnderlyingCipher();
            underlyingCipher.init(false, this.key);
            byte[] bArr = new byte[32];
            underlyingCipher.processBlock(C, 0, bArr, 0);
            underlyingCipher.processBlock(C, 8, bArr, 8);
            underlyingCipher.processBlock(C, 16, bArr, 16);
            underlyingCipher.processBlock(C, 24, bArr, 24);
            this.key = new KeyParameter(bArr);
            underlyingCipher.init(true, this.key);
            byte[] currentIV = this.cfbEngine.getCurrentIV();
            underlyingCipher.processBlock(currentIV, 0, currentIV, 0);
            this.cfbEngine.init(this.forEncryption, new ParametersWithIV(this.key, currentIV));
        }
        this.counter++;
        return this.cfbEngine.calculateByte(b);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        String algorithmName = this.cfbEngine.getAlgorithmName();
        return algorithmName.substring(0, algorithmName.indexOf(47)) + "/G" + algorithmName.substring(algorithmName.indexOf(47) + 1);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cfbEngine.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.counter = 0L;
        this.cfbEngine.init(z, cipherParameters);
        this.forEncryption = z;
        if (cipherParameters instanceof ParametersWithIV) {
            cipherParameters = ((ParametersWithIV) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof ParametersWithSBox) {
            cipherParameters = ((ParametersWithSBox) cipherParameters).getParameters();
        }
        this.key = (KeyParameter) cipherParameters;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.cfbEngine.getBlockSize(), bArr2, i2);
        return this.cfbEngine.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.counter = 0L;
        this.cfbEngine.reset();
    }
}
