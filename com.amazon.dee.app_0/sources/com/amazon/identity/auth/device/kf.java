package com.amazon.identity.auth.device;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class kf {
    public List<ke> b(Element element) {
        ArrayList arrayList = new ArrayList();
        if (element == null) {
            return arrayList;
        }
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                Element element2 = (Element) item;
                Element a = ml.a(element2, "url");
                Element a2 = ml.a(element2, "value");
                String f = ml.f(a);
                String f2 = ml.f(a2);
                ke keVar = new ke();
                keVar.dM(f);
                keVar.setValue(f2);
                arrayList.add(keVar);
            }
        }
        return arrayList;
    }
}
