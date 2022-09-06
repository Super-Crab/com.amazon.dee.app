package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.X500Name;
import com.amazon.alexa.device.setup.echo.bouncycastle.cert.selector.X509CertificateHolderSelector;
import java.math.BigInteger;
/* loaded from: classes.dex */
public class KeyAgreeRecipientId extends RecipientId {
    private final X509CertificateHolderSelector baseSelector;

    private KeyAgreeRecipientId(X509CertificateHolderSelector x509CertificateHolderSelector) {
        super(2);
        this.baseSelector = x509CertificateHolderSelector;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientId, com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public Object clone() {
        return new KeyAgreeRecipientId(this.baseSelector);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof KeyAgreeRecipientId)) {
            return false;
        }
        return this.baseSelector.equals(((KeyAgreeRecipientId) obj).baseSelector);
    }

    public BigInteger getSerialNumber() {
        return this.baseSelector.getSerialNumber();
    }

    public byte[] getSubjectKeyIdentifier() {
        return this.baseSelector.getSubjectKeyIdentifier();
    }

    public int hashCode() {
        return this.baseSelector.hashCode();
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof KeyAgreeRecipientInformation) {
            return ((KeyAgreeRecipientInformation) obj).getRID().equals(this);
        }
        return this.baseSelector.match(obj);
    }

    public KeyAgreeRecipientId(byte[] bArr) {
        this(null, null, bArr);
    }

    public KeyAgreeRecipientId(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public KeyAgreeRecipientId(X500Name x500Name, BigInteger bigInteger, byte[] bArr) {
        this(new X509CertificateHolderSelector(x500Name, bigInteger, bArr));
    }
}
