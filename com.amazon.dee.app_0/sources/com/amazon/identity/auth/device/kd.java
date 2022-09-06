package com.amazon.identity.auth.device;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class kd {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static final class a {
        private final String bk;
        private final String si;
        private final String sj;

        public a(String str, String str2, String str3) {
            this.bk = str;
            this.si = str2;
            this.sj = str3;
        }

        public String getDeviceType() {
            return this.bk;
        }

        public String hk() {
            return this.si;
        }

        public String hl() {
            return this.sj;
        }
    }

    public static Map<String, Map<String, String>> a(Element element) {
        Map map;
        HashMap hashMap = new HashMap();
        LinkedList<a> linkedList = new LinkedList();
        if (element == null) {
            return hashMap;
        }
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                Element element2 = (Element) item;
                if (element2.hasAttribute("deviceType")) {
                    String attribute = element2.getAttribute("deviceType");
                    if (!hashMap.containsKey(attribute)) {
                        map = new HashMap();
                        hashMap.put(attribute, map);
                    } else {
                        map = (Map) hashMap.get(attribute);
                    }
                    NodeList childNodes2 = item.getChildNodes();
                    for (int i2 = 0; i2 < childNodes2.getLength(); i2++) {
                        Node item2 = childNodes2.item(i2);
                        if (item2.getNodeType() == 1) {
                            Element element3 = (Element) item2;
                            if (element3.hasAttribute("refDeviceType")) {
                                linkedList.add(new a(attribute, item2.getNodeName(), element3.getAttribute("refDeviceType")));
                            } else {
                                map.put(item2.getNodeName(), ml.f(element3));
                            }
                        }
                    }
                }
            }
        }
        for (a aVar : linkedList) {
            ((Map) hashMap.get(aVar.getDeviceType())).put(aVar.hk(), ((Map) hashMap.get(aVar.hl())).get(aVar.hk()));
        }
        return hashMap;
    }
}
