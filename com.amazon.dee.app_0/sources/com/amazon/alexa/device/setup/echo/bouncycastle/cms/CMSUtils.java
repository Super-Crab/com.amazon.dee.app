package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1InputStream;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.ContentInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.io.Streams;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
/* loaded from: classes.dex */
class CMSUtils {
    CMSUtils() {
    }

    public static Provider getProvider(String str) throws NoSuchProviderException {
        if (str != null) {
            Provider provider = Security.getProvider(str);
            if (provider == null) {
                throw new NoSuchProviderException(GeneratedOutlineSupport1.outline75("provider ", str, " not found."));
            }
            return provider;
        }
        return null;
    }

    static OutputStream getSafeOutputStream(OutputStream outputStream) {
        return outputStream == null ? new NullOutputStream() : outputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ContentInfo readContentInfo(byte[] bArr) throws CMSException {
        return readContentInfo(new ASN1InputStream(bArr));
    }

    public static byte[] streamToByteArray(InputStream inputStream) throws IOException {
        return Streams.readAll(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ContentInfo readContentInfo(InputStream inputStream) throws CMSException {
        return readContentInfo(new ASN1InputStream(inputStream));
    }

    public static byte[] streamToByteArray(InputStream inputStream, int i) throws IOException {
        return Streams.readAllLimited(inputStream, i);
    }

    private static ContentInfo readContentInfo(ASN1InputStream aSN1InputStream) throws CMSException {
        try {
            return ContentInfo.getInstance(aSN1InputStream.readObject());
        } catch (IOException e) {
            throw new CMSException("IOException reading content.", e);
        } catch (ClassCastException e2) {
            throw new CMSException("Malformed content.", e2);
        } catch (IllegalArgumentException e3) {
            throw new CMSException("Malformed content.", e3);
        }
    }
}
