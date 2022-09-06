package com.amazon.identity.auth.device;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ml {
    public static Element a(Element element, String str) {
        if (element == null) {
            return null;
        }
        NodeList elementsByTagName = element.getElementsByTagName(str);
        if (elementsByTagName.getLength() != 0) {
            return (Element) elementsByTagName.item(0);
        }
        return null;
    }

    public static Element b(Element element, String str) {
        if (element == null) {
            return null;
        }
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                Element element2 = (Element) item;
                if (str.equalsIgnoreCase(element2.getTagName())) {
                    return element2;
                }
            }
        }
        return null;
    }

    public static boolean c(Element element, String str) {
        return element != null && element.getElementsByTagName(str).getLength() > 0;
    }

    public static Map<String, String> d(Element element) {
        if (element == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Element element2 = (Element) childNodes.item(i);
            if (element2 != null) {
                hashMap.put(element2.getTagName(), f(element2));
            }
        }
        if (!hashMap.isEmpty()) {
            return hashMap;
        }
        return null;
    }

    public static String e(Element element) {
        if (element == null) {
            return null;
        }
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 3) {
                return ((Text) item).getNodeValue();
            }
        }
        return null;
    }

    public static String f(Element element) {
        if (element == null) {
            return null;
        }
        return element.getTextContent();
    }
}
