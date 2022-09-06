package com.amazon.identity.auth.device;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mi extends mj {
    private static final String TAG = "com.amazon.identity.auth.device.mi";
    private static final DocumentBuilderFactory vf = DocumentBuilderFactory.newInstance();
    private final String mName;
    private final String vg;
    private List<mj> vh;

    public mi(String str, String str2, mf... mfVarArr) {
        this(str, str2, (mj[]) mfVarArr);
    }

    private Element f(Document document) {
        Element createElement = document.createElement(this.mName);
        String str = this.vg;
        if (str != null) {
            createElement.setTextContent(str);
        }
        for (mj mjVar : this.vh) {
            mjVar.c(createElement);
        }
        return createElement;
    }

    public boolean a(mj mjVar) {
        return this.vh.add(mjVar);
    }

    public mi aB(String str, String str2) {
        a(new mi(str, str2, new mf[0]));
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.identity.auth.device.mj
    public void c(Element element) {
        element.appendChild(f(element.getOwnerDocument()));
    }

    public String iH() {
        try {
            Document newDocument = vf.newDocumentBuilder().newDocument();
            newDocument.appendChild(f(newDocument));
            return a(newDocument);
        } catch (ParserConfigurationException e) {
            String str = TAG;
            io.e(str, "convertToString: Unable to construct an XML Document since the document factory could not be constructed. Error: " + e.getMessage());
            return null;
        }
    }

    public mi(String str, mj... mjVarArr) {
        this(str, (String) null, mjVarArr);
    }

    public static String a(Node node) {
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", node instanceof Document ? "no" : "yes");
            StringWriter stringWriter = new StringWriter();
            newTransformer.transform(new DOMSource(node), new StreamResult(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (TransformerConfigurationException e) {
            String str = TAG;
            io.e(str, "convertDocumentToString: Unable to convert XML Document to text since the transformer could not be constructed. Error: " + e.getMessage());
            return null;
        } catch (TransformerException e2) {
            String str2 = TAG;
            io.e(str2, "convertDocumentToString: Unable to convert XML Document to text. Error: " + e2.getMessage());
            return null;
        }
    }

    private mi(String str, String str2, mj... mjVarArr) {
        this.mName = str;
        this.vg = str2;
        this.vh = new LinkedList();
        this.vh.addAll(Arrays.asList(mjVarArr));
    }
}
