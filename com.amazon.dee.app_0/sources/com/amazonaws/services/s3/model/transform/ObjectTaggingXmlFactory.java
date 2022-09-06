package com.amazonaws.services.s3.model.transform;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.amazonaws.services.s3.internal.XmlWriter;
import com.amazonaws.services.s3.model.ObjectTagging;
import com.amazonaws.services.s3.model.Tag;
/* loaded from: classes13.dex */
public class ObjectTaggingXmlFactory {
    public byte[] convertToXmlByteArray(ObjectTagging objectTagging) {
        XmlWriter xmlWriter = new XmlWriter();
        xmlWriter.start("Tagging").start("TagSet");
        for (Tag tag : objectTagging.getTagSet()) {
            xmlWriter.start("Tag");
            xmlWriter.start("Key").value(tag.getKey()).end();
            xmlWriter.start(MAPCookie.KEY_VALUE).value(tag.getValue()).end();
            xmlWriter.end();
        }
        xmlWriter.end();
        xmlWriter.end();
        return xmlWriter.getBytes();
    }
}
