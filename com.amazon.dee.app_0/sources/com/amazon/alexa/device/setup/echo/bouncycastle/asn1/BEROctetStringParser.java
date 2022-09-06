package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import com.amazon.alexa.device.setup.echo.bouncycastle.util.io.Streams;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class BEROctetStringParser implements ASN1OctetStringParser {
    private final ASN1StreamParser _parser;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BEROctetStringParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() throws IOException {
        return new BEROctetString(Streams.readAll(getOctetStream()));
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetStringParser
    public InputStream getOctetStream() {
        return new ConstructedOctetStream(this._parser);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e) {
            throw new ASN1ParsingException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("IOException converting stream to byte array: ")), e);
        }
    }
}
