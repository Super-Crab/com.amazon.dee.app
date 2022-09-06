package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes.dex */
public class BERSetParser implements ASN1SetParser {
    private final ASN1StreamParser _parser;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BERSetParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() throws IOException {
        return new BERSet(this._parser.readVector());
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1SetParser
    public ASN1Encodable readObject() throws IOException {
        return this._parser.readObject();
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage(), e);
        }
    }
}
