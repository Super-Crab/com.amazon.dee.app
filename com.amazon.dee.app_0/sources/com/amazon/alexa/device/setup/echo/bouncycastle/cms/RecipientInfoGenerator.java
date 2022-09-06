package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
/* loaded from: classes.dex */
public interface RecipientInfoGenerator {
    RecipientInfo generate(GenericKey genericKey) throws CMSException;
}
