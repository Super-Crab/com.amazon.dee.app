package com.amazon.whispercloak;

import com.amazon.whispercloak.cipher.AesGcmCipher;
import com.amazon.whispercloak.error.SecureChannelInitializationError;
import com.amazon.whispercloak.keygen.ECSharedSecretGenerator;
import com.amazon.whispercloak.keygen.PemPublicKeyCoder;
import com.amazon.whispercloak.keygen.provider.EcdhKeyFactoryProvider;
import com.amazon.whispercloak.keygen.provider.EcdhKeyPairProvider;
import com.amazon.whispercloak.keygen.provider.KeyPairProvider;
import com.amazon.whispercloak.tlv.TLV;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import javax.crypto.SecretKey;
/* loaded from: classes13.dex */
public class SecureChannelImpl implements SecureChannel {
    private final KeyFactory keyFactory;
    private final AesGcmCipher mCipher;
    private final KeyPair mKeyPair;
    private final HashSet mNonceSet;
    private final PemPublicKeyCoder mPemPublicKeyCoder;
    private SecretKey mSharedSecret;

    static {
        CryptoInitializer.initialize();
    }

    public SecureChannelImpl() {
        this(new EcdhKeyPairProvider());
    }

    private void assertChannelInitialized() {
        if (this.mSharedSecret != null) {
            return;
        }
        throw new IllegalStateException("Channel not properly initialized");
    }

    @Override // com.amazon.whispercloak.SecureChannel
    @Deprecated
    public Payload decrypt(byte[] bArr) {
        assertChannelInitialized();
        SecureMessage decode = TLV.decode(bArr);
        if (!this.mNonceSet.contains(ByteBuffer.wrap(decode.getIv()))) {
            this.mNonceSet.add(ByteBuffer.wrap(decode.getIv()));
            return new Payload(this.mCipher.decrypt(this.mSharedSecret.getEncoded(), decode), decode.getAad());
        }
        throw new IllegalStateException("TLV could not be decrypted due to repeated nonce/IV");
    }

    @Override // com.amazon.whispercloak.SecureChannel
    @Deprecated
    public byte[] encrypt(Payload payload) {
        assertChannelInitialized();
        return TLV.encode(this.mCipher.encrypt(this.mSharedSecret.getEncoded(), payload.getData(), payload.getAad()));
    }

    @Override // com.amazon.whispercloak.SecureChannel
    public AesGcmCipher getCipher() {
        return this.mCipher;
    }

    @Override // com.amazon.whispercloak.SecureChannel
    public byte[] getDerEncodedPublicKey() {
        return this.mKeyPair.getPublic().getEncoded();
    }

    @Override // com.amazon.whispercloak.SecureChannel
    public String getPemEncodedPublicKey() {
        return this.mPemPublicKeyCoder.encode(this.mKeyPair.getPublic());
    }

    @Override // com.amazon.whispercloak.SecureChannel
    public byte[] getSymmetricKey() {
        assertChannelInitialized();
        return this.mSharedSecret.getEncoded();
    }

    @Override // com.amazon.whispercloak.SecureChannel
    public void init(String str) throws SecureChannelInitializationError {
        try {
            init(this.mPemPublicKeyCoder.decode(str));
        } catch (InvalidKeySpecException e) {
            throw new SecureChannelInitializationError(e);
        }
    }

    public SecureChannelImpl(KeyPairProvider keyPairProvider) {
        this.keyFactory = new EcdhKeyFactoryProvider().getKeyFactory();
        this.mSharedSecret = null;
        this.mKeyPair = keyPairProvider.createNewKeyPair();
        this.mPemPublicKeyCoder = new PemPublicKeyCoder();
        this.mCipher = new AesGcmCipher();
        this.mNonceSet = new HashSet();
    }

    @Override // com.amazon.whispercloak.SecureChannel
    public void init(byte[] bArr) throws SecureChannelInitializationError {
        try {
            init(this.keyFactory.generatePublic(new X509EncodedKeySpec(bArr)));
        } catch (InvalidKeySpecException e) {
            throw new SecureChannelInitializationError(e);
        }
    }

    private void init(PublicKey publicKey) throws SecureChannelInitializationError {
        try {
            this.mSharedSecret = new ECSharedSecretGenerator().getSharedSecret(this.mKeyPair.getPrivate(), publicKey);
        } catch (InvalidKeyException e) {
            throw new SecureChannelInitializationError(e);
        }
    }
}
