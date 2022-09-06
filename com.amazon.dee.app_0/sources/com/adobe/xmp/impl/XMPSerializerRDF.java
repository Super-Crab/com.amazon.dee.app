package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.XMPSchemaRegistry;
import com.adobe.xmp.options.SerializeOptions;
import com.amazon.alexa.Bag;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public class XMPSerializerRDF {
    private static final int DEFAULT_PAD = 2048;
    private static final String PACKET_HEADER = "<?xpacket begin=\"\ufeff\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>";
    private static final String PACKET_TRAILER = "<?xpacket end=\"";
    private static final String PACKET_TRAILER2 = "\"?>";
    static final Set RDF_ATTR_QUALIFIER = new HashSet(Arrays.asList(XMPConst.XML_LANG, "rdf:resource", "rdf:ID", "rdf:bagID", "rdf:nodeID"));
    private static final String RDF_EMPTY_STRUCT = "<rdf:Description/>";
    private static final String RDF_RDF_END = "</rdf:RDF>";
    private static final String RDF_RDF_START = "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">";
    private static final String RDF_SCHEMA_END = "</rdf:Description>";
    private static final String RDF_SCHEMA_START = "<rdf:Description rdf:about=";
    private static final String RDF_STRUCT_END = "</rdf:Description>";
    private static final String RDF_STRUCT_START = "<rdf:Description";
    private static final String RDF_XMPMETA_END = "</x:xmpmeta>";
    private static final String RDF_XMPMETA_START = "<x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"";
    private SerializeOptions options;
    private CountOutputStream outputStream;
    private int padding;
    private int unicodeSize = 1;
    private OutputStreamWriter writer;
    private XMPMetaImpl xmp;

    private void addPadding(int i) throws XMPException, IOException {
        if (this.options.getExactPacketLength()) {
            int bytesWritten = (i * this.unicodeSize) + this.outputStream.getBytesWritten();
            int i2 = this.padding;
            if (bytesWritten > i2) {
                throw new XMPException("Can't fit into specified packet size", 107);
            }
            this.padding = i2 - bytesWritten;
        }
        this.padding /= this.unicodeSize;
        int length = this.options.getNewline().length();
        int i3 = this.padding;
        if (i3 < length) {
            writeChars(i3, Chars.SPACE);
            return;
        }
        int i4 = i3 - length;
        while (true) {
            this.padding = i4;
            int i5 = this.padding;
            int i6 = length + 100;
            if (i5 < i6) {
                writeChars(i5, Chars.SPACE);
                writeNewline();
                return;
            }
            writeChars(100, Chars.SPACE);
            writeNewline();
            i4 = this.padding - i6;
        }
    }

    private void appendNodeValue(String str, boolean z) throws IOException {
        if (str == null) {
            str = "";
        }
        write(Utils.escapeXML(str, z, true));
    }

    private boolean canBeRDFAttrProp(XMPNode xMPNode) {
        return !xMPNode.hasQualifier() && !xMPNode.getOptions().isURI() && !xMPNode.getOptions().isCompositeProperty() && !"[]".equals(xMPNode.getName());
    }

    private void declareNamespace(String str, String str2, Set set, int i) throws IOException {
        if (str2 == null) {
            QName qName = new QName(str);
            if (!qName.hasPrefix()) {
                return;
            }
            str = qName.getPrefix();
            XMPSchemaRegistry schemaRegistry = XMPMetaFactory.getSchemaRegistry();
            str2 = schemaRegistry.getNamespaceURI(str + ":");
            declareNamespace(str, str2, set, i);
        }
        if (!set.contains(str)) {
            writeNewline();
            writeIndent(i);
            write("xmlns:");
            write(str);
            write("=\"");
            write(str2);
            write(34);
            set.add(str);
        }
    }

    private void declareUsedNamespaces(XMPNode xMPNode, Set set, int i) throws IOException {
        if (xMPNode.getOptions().isSchemaNode()) {
            declareNamespace(xMPNode.getValue().substring(0, xMPNode.getValue().length() - 1), xMPNode.getName(), set, i);
        } else if (xMPNode.getOptions().isStruct()) {
            Iterator iterateChildren = xMPNode.iterateChildren();
            while (iterateChildren.hasNext()) {
                declareNamespace(((XMPNode) iterateChildren.next()).getName(), null, set, i);
            }
        }
        Iterator iterateChildren2 = xMPNode.iterateChildren();
        while (iterateChildren2.hasNext()) {
            declareUsedNamespaces((XMPNode) iterateChildren2.next(), set, i);
        }
        Iterator iterateQualifier = xMPNode.iterateQualifier();
        while (iterateQualifier.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) iterateQualifier.next();
            declareNamespace(xMPNode2.getName(), null, set, i);
            declareUsedNamespaces(xMPNode2, set, i);
        }
    }

    private void emitRDFArrayTag(XMPNode xMPNode, boolean z, int i) throws IOException {
        if (z || xMPNode.hasChildren()) {
            writeIndent(i);
            write(z ? "<rdf:" : "</rdf:");
            write(xMPNode.getOptions().isArrayAlternate() ? "Alt" : xMPNode.getOptions().isArrayOrdered() ? "Seq" : Bag.zZm);
            write((!z || xMPNode.hasChildren()) ? Config.Compare.GREATER_THAN : "/>");
            writeNewline();
        }
    }

    private void endOuterRDFDescription(int i) throws IOException {
        writeIndent(i + 1);
        write("</rdf:Description>");
        writeNewline();
    }

    private String serializeAsRDF() throws IOException, XMPException {
        int i = 0;
        if (!this.options.getOmitPacketWrapper()) {
            writeIndent(0);
            write(PACKET_HEADER);
            writeNewline();
        }
        if (!this.options.getOmitXmpMetaElement()) {
            writeIndent(0);
            write(RDF_XMPMETA_START);
            if (!this.options.getOmitVersionAttribute()) {
                write(XMPMetaFactory.getVersionInfo().getMessage());
            }
            write("\">");
            writeNewline();
            i = 1;
        }
        writeIndent(i);
        write(RDF_RDF_START);
        writeNewline();
        if (this.options.getUseCanonicalFormat()) {
            serializeCanonicalRDFSchemas(i);
        } else {
            serializeCompactRDFSchemas(i);
        }
        writeIndent(i);
        write(RDF_RDF_END);
        writeNewline();
        if (!this.options.getOmitXmpMetaElement()) {
            writeIndent(i - 1);
            write(RDF_XMPMETA_END);
            writeNewline();
        }
        String str = "";
        if (!this.options.getOmitPacketWrapper()) {
            for (int baseIndent = this.options.getBaseIndent(); baseIndent > 0; baseIndent--) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
                outline107.append(this.options.getIndent());
                str = outline107.toString();
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(GeneratedOutlineSupport1.outline72(str, PACKET_TRAILER));
            outline1072.append(this.options.getReadOnlyPacket() ? 'r' : 'w');
            return GeneratedOutlineSupport1.outline72(outline1072.toString(), PACKET_TRAILER2);
        }
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c6, code lost:
        if (r19 != false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c8, code lost:
        writeIndent(r3);
        write("</rdf:Description>");
        writeNewline();
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01c1, code lost:
        if (r19 != false) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void serializeCanonicalRDFProperty(com.adobe.xmp.impl.XMPNode r18, boolean r19, boolean r20, int r21) throws java.io.IOException, com.adobe.xmp.XMPException {
        /*
            Method dump skipped, instructions count: 543
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPSerializerRDF.serializeCanonicalRDFProperty(com.adobe.xmp.impl.XMPNode, boolean, boolean, int):void");
    }

    private void serializeCanonicalRDFSchema(XMPNode xMPNode, int i) throws IOException, XMPException {
        Iterator iterateChildren = xMPNode.iterateChildren();
        while (iterateChildren.hasNext()) {
            serializeCanonicalRDFProperty((XMPNode) iterateChildren.next(), this.options.getUseCanonicalFormat(), false, i + 2);
        }
    }

    private void serializeCanonicalRDFSchemas(int i) throws IOException, XMPException {
        if (this.xmp.getRoot().getChildrenLength() > 0) {
            startOuterRDFDescription(this.xmp.getRoot(), i);
            Iterator iterateChildren = this.xmp.getRoot().iterateChildren();
            while (iterateChildren.hasNext()) {
                serializeCanonicalRDFSchema((XMPNode) iterateChildren.next(), i);
            }
            endOuterRDFDescription(i);
            return;
        }
        writeIndent(i + 1);
        write(RDF_SCHEMA_START);
        writeTreeName();
        write("/>");
        writeNewline();
    }

    private void serializeCompactRDFArrayProp(XMPNode xMPNode, int i) throws IOException, XMPException {
        write(62);
        writeNewline();
        int i2 = i + 1;
        emitRDFArrayTag(xMPNode, true, i2);
        if (xMPNode.getOptions().isArrayAltText()) {
            XMPNodeUtils.normalizeLangArray(xMPNode);
        }
        serializeCompactRDFElementProps(xMPNode, i + 2);
        emitRDFArrayTag(xMPNode, false, i2);
    }

    private boolean serializeCompactRDFAttrProps(XMPNode xMPNode, int i) throws IOException {
        Iterator iterateChildren = xMPNode.iterateChildren();
        boolean z = true;
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) iterateChildren.next();
            if (canBeRDFAttrProp(xMPNode2)) {
                writeNewline();
                writeIndent(i);
                write(xMPNode2.getName());
                write("=\"");
                appendNodeValue(xMPNode2.getValue(), true);
                write(34);
            } else {
                z = false;
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00b7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0004 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void serializeCompactRDFElementProps(com.adobe.xmp.impl.XMPNode r11, int r12) throws java.io.IOException, com.adobe.xmp.XMPException {
        /*
            r10 = this;
            java.util.Iterator r11 = r11.iterateChildren()
        L4:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto Lce
            java.lang.Object r0 = r11.next()
            com.adobe.xmp.impl.XMPNode r0 = (com.adobe.xmp.impl.XMPNode) r0
            boolean r1 = r10.canBeRDFAttrProp(r0)
            if (r1 == 0) goto L17
            goto L4
        L17:
            java.lang.String r1 = r0.getName()
            java.lang.String r2 = "[]"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L26
            java.lang.String r1 = "rdf:li"
        L26:
            r10.writeIndent(r12)
            r2 = 60
            r10.write(r2)
            r10.write(r1)
            java.util.Iterator r2 = r0.iterateQualifier()
            r3 = 0
            r4 = r3
            r5 = r4
        L38:
            boolean r6 = r2.hasNext()
            r7 = 1
            if (r6 == 0) goto L7c
            java.lang.Object r6 = r2.next()
            com.adobe.xmp.impl.XMPNode r6 = (com.adobe.xmp.impl.XMPNode) r6
            java.util.Set r8 = com.adobe.xmp.impl.XMPSerializerRDF.RDF_ATTR_QUALIFIER
            java.lang.String r9 = r6.getName()
            boolean r8 = r8.contains(r9)
            if (r8 != 0) goto L53
            r4 = r7
            goto L38
        L53:
            java.lang.String r5 = r6.getName()
            java.lang.String r8 = "rdf:resource"
            boolean r5 = r8.equals(r5)
            r8 = 32
            r10.write(r8)
            java.lang.String r8 = r6.getName()
            r10.write(r8)
            java.lang.String r8 = "=\""
            r10.write(r8)
            java.lang.String r6 = r6.getValue()
            r10.appendNodeValue(r6, r7)
            r6 = 34
            r10.write(r6)
            goto L38
        L7c:
            if (r4 == 0) goto L82
            r10.serializeCompactRDFGeneralQualifier(r12, r0)
            goto Laf
        L82:
            com.adobe.xmp.options.PropertyOptions r2 = r0.getOptions()
            boolean r2 = r2.isCompositeProperty()
            if (r2 != 0) goto La2
            java.lang.Object[] r0 = r10.serializeCompactRDFSimpleProp(r0)
            r2 = r0[r3]
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            r0 = r0[r7]
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r7 = r0.booleanValue()
            r0 = r2
            goto Lb5
        La2:
            com.adobe.xmp.options.PropertyOptions r2 = r0.getOptions()
            boolean r2 = r2.isArray()
            if (r2 == 0) goto Lb1
            r10.serializeCompactRDFArrayProp(r0, r12)
        Laf:
            r0 = r7
            goto Lb5
        Lb1:
            boolean r0 = r10.serializeCompactRDFStructProp(r0, r12, r5)
        Lb5:
            if (r0 == 0) goto L4
            if (r7 == 0) goto Lbc
            r10.writeIndent(r12)
        Lbc:
            java.lang.String r0 = "</"
            r10.write(r0)
            r10.write(r1)
            r0 = 62
            r10.write(r0)
            r10.writeNewline()
            goto L4
        Lce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPSerializerRDF.serializeCompactRDFElementProps(com.adobe.xmp.impl.XMPNode, int):void");
    }

    private void serializeCompactRDFGeneralQualifier(int i, XMPNode xMPNode) throws IOException, XMPException {
        write(" rdf:parseType=\"Resource\">");
        writeNewline();
        int i2 = i + 1;
        serializeCanonicalRDFProperty(xMPNode, false, true, i2);
        Iterator iterateQualifier = xMPNode.iterateQualifier();
        while (iterateQualifier.hasNext()) {
            serializeCanonicalRDFProperty((XMPNode) iterateQualifier.next(), false, false, i2);
        }
    }

    private void serializeCompactRDFSchemas(int i) throws IOException, XMPException {
        String str;
        int i2 = i + 1;
        writeIndent(i2);
        write(RDF_SCHEMA_START);
        writeTreeName();
        HashSet hashSet = new HashSet();
        hashSet.add("xml");
        hashSet.add("rdf");
        Iterator iterateChildren = this.xmp.getRoot().iterateChildren();
        while (iterateChildren.hasNext()) {
            declareUsedNamespaces((XMPNode) iterateChildren.next(), hashSet, i + 3);
        }
        Iterator iterateChildren2 = this.xmp.getRoot().iterateChildren();
        boolean z = true;
        while (iterateChildren2.hasNext()) {
            z &= serializeCompactRDFAttrProps((XMPNode) iterateChildren2.next(), i + 2);
        }
        if (!z) {
            write(62);
            writeNewline();
            Iterator iterateChildren3 = this.xmp.getRoot().iterateChildren();
            while (iterateChildren3.hasNext()) {
                serializeCompactRDFElementProps((XMPNode) iterateChildren3.next(), i + 2);
            }
            writeIndent(i2);
            str = "</rdf:Description>";
        } else {
            str = "/>";
        }
        write(str);
        writeNewline();
    }

    private Object[] serializeCompactRDFSimpleProp(XMPNode xMPNode) throws IOException {
        String str;
        Boolean bool;
        Boolean bool2 = Boolean.TRUE;
        if (xMPNode.getOptions().isURI()) {
            write(" rdf:resource=\"");
            appendNodeValue(xMPNode.getValue(), true);
            str = "\"/>";
        } else if (xMPNode.getValue() != null && xMPNode.getValue().length() != 0) {
            write(62);
            appendNodeValue(xMPNode.getValue(), false);
            bool2 = Boolean.FALSE;
            bool = bool2;
            return new Object[]{bool, bool2};
        } else {
            str = "/>";
        }
        write(str);
        writeNewline();
        bool = Boolean.FALSE;
        return new Object[]{bool, bool2};
    }

    private boolean serializeCompactRDFStructProp(XMPNode xMPNode, int i, boolean z) throws XMPException, IOException {
        String str;
        Iterator iterateChildren = xMPNode.iterateChildren();
        boolean z2 = false;
        boolean z3 = false;
        while (iterateChildren.hasNext()) {
            if (canBeRDFAttrProp((XMPNode) iterateChildren.next())) {
                z2 = true;
            } else {
                z3 = true;
            }
            if (z2 && z3) {
                break;
            }
        }
        if (!z || !z3) {
            if (!xMPNode.hasChildren()) {
                str = " rdf:parseType=\"Resource\"/>";
            } else if (z3) {
                if (!z2) {
                    write(" rdf:parseType=\"Resource\">");
                    writeNewline();
                    serializeCompactRDFElementProps(xMPNode, i + 1);
                } else {
                    write(62);
                    writeNewline();
                    int i2 = i + 1;
                    writeIndent(i2);
                    write(RDF_STRUCT_START);
                    serializeCompactRDFAttrProps(xMPNode, i + 2);
                    write(Config.Compare.GREATER_THAN);
                    writeNewline();
                    serializeCompactRDFElementProps(xMPNode, i2);
                    writeIndent(i2);
                    write("</rdf:Description>");
                    writeNewline();
                }
                return true;
            } else {
                serializeCompactRDFAttrProps(xMPNode, i + 1);
                str = "/>";
            }
            write(str);
            writeNewline();
            return false;
        }
        throw new XMPException("Can't mix rdf:resource qualifier and element fields", 202);
    }

    private void startOuterRDFDescription(XMPNode xMPNode, int i) throws IOException {
        writeIndent(i + 1);
        write(RDF_SCHEMA_START);
        writeTreeName();
        HashSet hashSet = new HashSet();
        hashSet.add("xml");
        hashSet.add("rdf");
        declareUsedNamespaces(xMPNode, hashSet, i + 3);
        write(62);
        writeNewline();
    }

    private void write(int i) throws IOException {
        this.writer.write(i);
    }

    private void write(String str) throws IOException {
        this.writer.write(str);
    }

    private void writeChars(int i, char c) throws IOException {
        while (i > 0) {
            this.writer.write(c);
            i--;
        }
    }

    private void writeIndent(int i) throws IOException {
        for (int baseIndent = this.options.getBaseIndent() + i; baseIndent > 0; baseIndent--) {
            this.writer.write(this.options.getIndent());
        }
    }

    private void writeNewline() throws IOException {
        this.writer.write(this.options.getNewline());
    }

    private void writeTreeName() throws IOException {
        write(34);
        String name = this.xmp.getRoot().getName();
        if (name != null) {
            appendNodeValue(name, true);
        }
        write(34);
    }

    protected void checkOptionsConsistence() throws XMPException {
        if (this.options.getEncodeUTF16BE() | this.options.getEncodeUTF16LE()) {
            this.unicodeSize = 2;
        }
        if (this.options.getExactPacketLength()) {
            if (this.options.getOmitPacketWrapper() || this.options.getIncludeThumbnailPad()) {
                throw new XMPException("Inconsistent options for exact size serialize", 103);
            }
            if ((this.options.getPadding() & (this.unicodeSize - 1)) != 0) {
                throw new XMPException("Exact size must be a multiple of the Unicode element", 103);
            }
            return;
        }
        if (this.options.getReadOnlyPacket()) {
            if (this.options.getOmitPacketWrapper() | this.options.getIncludeThumbnailPad()) {
                throw new XMPException("Inconsistent options for read-only packet", 103);
            }
        } else if (!this.options.getOmitPacketWrapper()) {
            if (this.padding == 0) {
                this.padding = this.unicodeSize * 2048;
            }
            if (!this.options.getIncludeThumbnailPad() || this.xmp.doesPropertyExist("http://ns.adobe.com/xap/1.0/", "Thumbnails")) {
                return;
            }
            this.padding = (this.unicodeSize * 10000) + this.padding;
            return;
        } else if (this.options.getIncludeThumbnailPad()) {
            throw new XMPException("Inconsistent options for non-packet serialize", 103);
        }
        this.padding = 0;
    }

    public void serialize(XMPMeta xMPMeta, OutputStream outputStream, SerializeOptions serializeOptions) throws XMPException {
        try {
            this.outputStream = new CountOutputStream(outputStream);
            this.writer = new OutputStreamWriter(this.outputStream, serializeOptions.getEncoding());
            this.xmp = (XMPMetaImpl) xMPMeta;
            this.options = serializeOptions;
            this.padding = serializeOptions.getPadding();
            this.writer = new OutputStreamWriter(this.outputStream, serializeOptions.getEncoding());
            checkOptionsConsistence();
            String serializeAsRDF = serializeAsRDF();
            this.writer.flush();
            addPadding(serializeAsRDF.length());
            write(serializeAsRDF);
            this.writer.flush();
            this.outputStream.close();
        } catch (IOException unused) {
            throw new XMPException("Error writing to the OutputStream", 0);
        }
    }
}
