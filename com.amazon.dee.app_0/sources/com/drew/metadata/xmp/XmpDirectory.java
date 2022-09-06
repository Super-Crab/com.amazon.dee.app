package com.drew.metadata.xmp;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPIterator;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.impl.XMPMetaImpl;
import com.adobe.xmp.properties.XMPPropertyInfo;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class XmpDirectory extends Directory {
    public static final int TAG_XMP_VALUE_COUNT = 65535;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();
    @Nullable
    private XMPMeta _xmpMeta;

    static {
        _tagNameMap.put(65535, "XMP Value Count");
    }

    public XmpDirectory() {
        setDescriptor(new XmpDescriptor(this));
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "XMP";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    @NotNull
    public XMPMeta getXMPMeta() {
        if (this._xmpMeta == null) {
            this._xmpMeta = new XMPMetaImpl();
        }
        return this._xmpMeta;
    }

    @NotNull
    public Map<String, String> getXmpProperties() {
        HashMap hashMap = new HashMap();
        XMPMeta xMPMeta = this._xmpMeta;
        if (xMPMeta != null) {
            try {
                XMPIterator it2 = xMPMeta.iterator();
                while (it2.hasNext()) {
                    XMPPropertyInfo xMPPropertyInfo = (XMPPropertyInfo) it2.next();
                    String path = xMPPropertyInfo.getPath();
                    String value = xMPPropertyInfo.getValue();
                    if (path != null && value != null) {
                        hashMap.put(path, value);
                    }
                }
            } catch (XMPException unused) {
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public void setXMPMeta(@NotNull XMPMeta xMPMeta) {
        this._xmpMeta = xMPMeta;
        int i = 0;
        try {
            XMPIterator it2 = this._xmpMeta.iterator();
            while (it2.hasNext()) {
                if (((XMPPropertyInfo) it2.next()).getPath() != null) {
                    i++;
                }
            }
            setInt(65535, i);
        } catch (XMPException unused) {
        }
    }
}
