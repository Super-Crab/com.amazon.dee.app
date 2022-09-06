package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Source;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes6.dex */
public abstract class DigestFirmwareComponentSupplier implements FirmwareComponentSupplier {
    private byte[] buffer;
    private final MessageDigest digest;

    public DigestFirmwareComponentSupplier(String str) {
        Preconditions.notNull(str, "algorithm");
        try {
            this.digest = MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline75("Invalid environment (unsupported algorithm: ", str, ")"), e);
        }
    }

    private synchronized String calculateSignature(Source source) throws IOException {
        if (this.buffer == null) {
            this.buffer = new byte[4096];
        }
        this.digest.reset();
        while (true) {
            int read = source.read(this.buffer, 0, this.buffer.length);
            if (read == -1) {
            } else {
                this.digest.update(this.buffer, 0, read);
            }
        }
        return IOUtils.byteArrayToBase64(this.digest.digest());
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public final String getSignature() throws IOException {
        Source open = open();
        try {
            return calculateSignature(open);
        } finally {
            IOUtils.closeQuietly(open);
        }
    }

    public final String getSignatureAlgorithm() {
        return this.digest.getAlgorithm();
    }

    @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier
    public final String getSignature(int i, int i2) throws IOException {
        Source open = open(i, i2);
        try {
            return calculateSignature(open);
        } finally {
            IOUtils.closeQuietly(open);
        }
    }
}
