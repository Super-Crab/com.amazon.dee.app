package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.X500Name;
import com.amazon.alexa.device.setup.echo.bouncycastle.cert.selector.X509CertificateHolderSelector;
import java.math.BigInteger;
/* loaded from: classes.dex */
public class KeyTransRecipientId extends RecipientId {
    private final X509CertificateHolderSelector baseSelector;

    private KeyTransRecipientId(X509CertificateHolderSelector x509CertificateHolderSelector) {
        super(0);
        this.baseSelector = x509CertificateHolderSelector;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientId, com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public Object clone() {
        return new KeyTransRecipientId(this.baseSelector);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof KeyTransRecipientId)) {
            return false;
        }
        return this.baseSelector.equals(((KeyTransRecipientId) obj).baseSelector);
    }

    public X500Name getIssuer() {
        return this.baseSelector.getIssuer();
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
        if (obj instanceof KeyTransRecipientInformation) {
            return ((KeyTransRecipientInformation) obj).getRID().equals(this);
        }
        return this.baseSelector.match(obj);
    }

    public KeyTransRecipientId(byte[] bArr) {
        this(null, null, bArr);
    }

    public KeyTransRecipientId(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public KeyTransRecipientId(X500Name x500Name, BigInteger bigInteger, byte[] bArr) {
        this(new X509CertificateHolderSelector(x500Name, bigInteger, bArr));
    }
}
