package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.InputDecryptor;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.MacCalculator;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.io.TeeInputStream;
import java.io.InputStream;
/* loaded from: classes.dex */
public class RecipientOperator {
    private final AlgorithmIdentifier algorithmIdentifier;
    private final Object operator;

    public RecipientOperator(InputDecryptor inputDecryptor) {
        this.algorithmIdentifier = inputDecryptor.getAlgorithmIdentifier();
        this.operator = inputDecryptor;
    }

    public InputStream getInputStream(InputStream inputStream) {
        Object obj = this.operator;
        if (obj instanceof InputDecryptor) {
            return ((InputDecryptor) obj).getInputStream(inputStream);
        }
        return new TeeInputStream(inputStream, ((MacCalculator) obj).getOutputStream());
    }

    public byte[] getMac() {
        return ((MacCalculator) this.operator).getMac();
    }

    public boolean isMacBased() {
        return this.operator instanceof MacCalculator;
    }

    public RecipientOperator(MacCalculator macCalculator) {
        this.algorithmIdentifier = macCalculator.getAlgorithmIdentifier();
        this.operator = macCalculator;
    }
}
