package org.bouncycastle.openssl.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.pkix.jcajce.JcaPKIXIdentity;
/* loaded from: classes5.dex */
public class JcaPKIXIdentityBuilder {
    private JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();
    private JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();

    private void checkFile(File file) throws IOException {
        if (file.canRead()) {
            if (file.exists()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to open file ");
                outline107.append(file.getPath());
                outline107.append(" for reading.");
                throw new IOException(outline107.toString());
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unable to open ");
            outline1072.append(file.getPath());
            outline1072.append(": it does not exist.");
            throw new FileNotFoundException(outline1072.toString());
        }
    }

    public JcaPKIXIdentity build(File file, File file2) throws IOException, CertificateException {
        checkFile(file);
        checkFile(file2);
        FileInputStream fileInputStream = new FileInputStream(file);
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        JcaPKIXIdentity build = build(fileInputStream, fileInputStream2);
        fileInputStream.close();
        fileInputStream2.close();
        return build;
    }

    public JcaPKIXIdentity build(InputStream inputStream, InputStream inputStream2) throws IOException, CertificateException {
        JcaPEMKeyConverter jcaPEMKeyConverter;
        PrivateKeyInfo privateKeyInfo;
        Object readObject = new PEMParser(new InputStreamReader(inputStream)).readObject();
        if (readObject instanceof PEMKeyPair) {
            jcaPEMKeyConverter = this.keyConverter;
            privateKeyInfo = ((PEMKeyPair) readObject).getPrivateKeyInfo();
        } else if (!(readObject instanceof PrivateKeyInfo)) {
            throw new IOException("unrecognised private key file");
        } else {
            jcaPEMKeyConverter = this.keyConverter;
            privateKeyInfo = (PrivateKeyInfo) readObject;
        }
        PrivateKey privateKey = jcaPEMKeyConverter.getPrivateKey(privateKeyInfo);
        PEMParser pEMParser = new PEMParser(new InputStreamReader(inputStream2));
        ArrayList arrayList = new ArrayList();
        while (true) {
            Object readObject2 = pEMParser.readObject();
            if (readObject2 != null) {
                arrayList.add(this.certConverter.getCertificate((X509CertificateHolder) readObject2));
            } else {
                return new JcaPKIXIdentity(privateKey, (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]));
            }
        }
    }

    public JcaPKIXIdentityBuilder setProvider(String str) {
        this.keyConverter = this.keyConverter.setProvider(str);
        this.certConverter = this.certConverter.setProvider(str);
        return this;
    }

    public JcaPKIXIdentityBuilder setProvider(Provider provider) {
        this.keyConverter = this.keyConverter.setProvider(provider);
        this.certConverter = this.certConverter.setProvider(provider);
        return this;
    }
}
