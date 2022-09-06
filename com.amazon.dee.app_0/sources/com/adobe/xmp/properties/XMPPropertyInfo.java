package com.adobe.xmp.properties;

import com.adobe.xmp.options.PropertyOptions;
/* loaded from: classes.dex */
public interface XMPPropertyInfo extends XMPProperty {
    String getNamespace();

    @Override // com.adobe.xmp.properties.XMPProperty
    PropertyOptions getOptions();

    String getPath();

    @Override // com.adobe.xmp.properties.XMPProperty
    String getValue();
}
