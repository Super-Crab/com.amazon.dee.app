package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes.dex */
public class BERTaggedObjectParser implements ASN1TaggedObjectParser {
    private final boolean _constructed;
    private final ASN1StreamParser _parser;
    private final int _tagNumber;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BERTaggedObjectParser(boolean z, int i, ASN1StreamParser aSN1StreamParser) {
        this._constructed = z;
        this._tagNumber = i;
        this._parser = aSN1StreamParser;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() throws IOException {
        return this._parser.readTaggedObject(this._constructed, this._tagNumber);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable getObjectParser(int i, boolean z) throws IOException {
        if (z) {
            if (this._constructed) {
                return this._parser.readObject();
            }
            throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
        }
        return this._parser.readImplicit(this._constructed, i);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this._tagNumber;
    }

    public boolean isConstructed() {
        return this._constructed;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage());
        }
    }
}
