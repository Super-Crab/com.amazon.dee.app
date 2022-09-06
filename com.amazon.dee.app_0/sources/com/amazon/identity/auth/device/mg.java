package com.amazon.identity.auth.device;

import org.w3c.dom.Element;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mg extends mj {
    private final String ve;

    public mg(String str) {
        this.ve = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.identity.auth.device.mj
    public void c(Element element) {
        element.appendChild(element.getOwnerDocument().createCDATASection(this.ve));
    }
}
