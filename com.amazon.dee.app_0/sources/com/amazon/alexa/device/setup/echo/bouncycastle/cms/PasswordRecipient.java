package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes.dex */
public interface PasswordRecipient extends Recipient {
    public static final int PKCS5_SCHEME2 = 0;
    public static final int PKCS5_SCHEME2_UTF8 = 1;

    char[] getPassword();

    int getPasswordConversionScheme();

    RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr, byte[] bArr2) throws CMSException;
}
