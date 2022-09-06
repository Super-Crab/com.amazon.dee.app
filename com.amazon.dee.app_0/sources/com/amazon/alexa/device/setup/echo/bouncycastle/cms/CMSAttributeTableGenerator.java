package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.AttributeTable;
import java.util.Map;
/* loaded from: classes.dex */
public interface CMSAttributeTableGenerator {
    public static final String CONTENT_TYPE = "contentType";
    public static final String DIGEST = "digest";
    public static final String DIGEST_ALGORITHM_IDENTIFIER = "digestAlgID";
    public static final String SIGNATURE = "encryptedDigest";

    AttributeTable getAttributes(Map map) throws CMSAttributeTableGenerationException;
}
