package com.amazon.identity.auth.device;

import org.w3c.dom.Element;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mf extends mj {
    private final String mKey;
    private final String mValue;

    public mf(String str, String str2) {
        this.mKey = str;
        this.mValue = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.identity.auth.device.mj
    public void c(Element element) {
        element.setAttribute(this.mKey, this.mValue);
    }
}
