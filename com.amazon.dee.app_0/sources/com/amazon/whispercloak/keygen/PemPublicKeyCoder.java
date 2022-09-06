package com.amazon.whispercloak.keygen;

import com.amazon.whispercloak.keygen.provider.EcdhKeyFactoryProvider;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;
/* loaded from: classes13.dex */
public class PemPublicKeyCoder {
    private KeyFactory mKeyFactory;

    public PemPublicKeyCoder() {
        this(new EcdhKeyFactoryProvider().getKeyFactory());
    }

    private void closeQuitely(Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException unused) {
                }
            }
        }
    }

    public PublicKey decode(String str) throws InvalidKeySpecException {
        StringReader stringReader;
        Closeable closeable;
        IOException e;
        try {
            try {
                stringReader = new StringReader(str);
            } catch (Throwable th) {
                th = th;
            }
            try {
                PemReader pemReader = new PemReader(stringReader);
                try {
                    PemObject readPemObject = pemReader.readPemObject();
                    if (readPemObject != null) {
                        PublicKey generatePublic = this.mKeyFactory.generatePublic(new X509EncodedKeySpec(readPemObject.getContent()));
                        closeQuitely(stringReader, pemReader);
                        return generatePublic;
                    }
                    throw new InvalidKeySpecException("Key decode error. Not a valid PEM-coded key");
                } catch (IOException e2) {
                    e = e2;
                    throw new InvalidKeySpecException(String.format("Failed to read encoded key (%s)", str), e);
                }
            } catch (IOException e3) {
                e = e3;
            } catch (Throwable th2) {
                th = th2;
                closeable = null;
                closeQuitely(stringReader, closeable);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
        } catch (Throwable th3) {
            th = th3;
            stringReader = null;
            closeable = null;
        }
    }

    public String encode(PublicKey publicKey) {
        StringWriter stringWriter;
        PemWriter pemWriter;
        StringWriter stringWriter2 = null;
        try {
            stringWriter = new StringWriter();
            try {
                pemWriter = new PemWriter(stringWriter);
            } catch (IOException e) {
                e = e;
                pemWriter = null;
            } catch (Throwable th) {
                th = th;
                pemWriter = null;
            }
        } catch (IOException e2) {
            e = e2;
            pemWriter = null;
        } catch (Throwable th2) {
            th = th2;
            stringWriter = null;
            pemWriter = null;
        }
        try {
            pemWriter.writeObject(new PemObject("PUBLIC KEY", publicKey.getEncoded()));
            pemWriter.flush();
            String stringWriter3 = stringWriter.toString();
            closeQuitely(stringWriter, pemWriter);
            return stringWriter3;
        } catch (IOException e3) {
            e = e3;
            stringWriter2 = stringWriter;
            try {
                throw new RuntimeException("Can not PEM-encode Public key", e);
            } catch (Throwable th3) {
                th = th3;
                stringWriter = stringWriter2;
                closeQuitely(stringWriter, pemWriter);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            closeQuitely(stringWriter, pemWriter);
            throw th;
        }
    }

    PemPublicKeyCoder(KeyFactory keyFactory) {
        this.mKeyFactory = keyFactory;
    }
}
