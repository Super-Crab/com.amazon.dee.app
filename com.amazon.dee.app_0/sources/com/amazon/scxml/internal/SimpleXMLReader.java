package com.amazon.scxml.internal;

import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xml.sax.helpers.DefaultHandler;
/* compiled from: SimpleXMLReader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/amazon/scxml/internal/SimpleXMLReader;", "Lcom/amazon/scxml/logging/Loggable;", "()V", "process", "Lcom/amazon/scxml/internal/SimpleXMLElement;", "inputStream", "Ljava/io/InputStream;", "xml", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SimpleXMLReader extends Loggable {
    @Nullable
    public final SimpleXMLElement process(@NotNull String xml) {
        Intrinsics.checkParameterIsNotNull(xml, "xml");
        byte[] bytes = xml.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return process(new ByteArrayInputStream(bytes));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.Stack] */
    @Nullable
    public final SimpleXMLElement process(@NotNull InputStream inputStream) {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new Stack();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = null;
        try {
            SAXParserFactory newInstance = SAXParserFactory.newInstance();
            Intrinsics.checkExpressionValueIsNotNull(newInstance, "SAXParserFactory.newInstance()");
            SAXParser newSAXParser = newInstance.newSAXParser();
            Intrinsics.checkExpressionValueIsNotNull(newSAXParser, "parserFactory.newSAXParser()");
            newSAXParser.parse(inputStream, new DefaultHandler() { // from class: com.amazon.scxml.internal.SimpleXMLReader$process$defaultHandler$1
                /* JADX WARN: Type inference failed for: r2v3, types: [T, com.amazon.scxml.internal.SimpleXMLElement] */
                @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
                public void endDocument() {
                    String tag;
                    String tag2;
                    if (((Stack) objectRef.element).size() != 1) {
                        Logger log = Loggable.Companion.getLog();
                        tag2 = SimpleXMLReader.this.getTAG();
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SimpleXMLReader error - elementStack should have 1 element; found [");
                        outline107.append(((Stack) objectRef.element).size());
                        outline107.append("] elements");
                        log.e(tag2, outline107.toString());
                        return;
                    }
                    Object pop = ((Stack) objectRef.element).pop();
                    objectRef2.element = (SimpleXMLElement) (!(pop instanceof SimpleXMLElement) ? null : pop);
                    if (((SimpleXMLElement) objectRef2.element) != null) {
                        return;
                    }
                    Logger log2 = Loggable.Companion.getLog();
                    tag = SimpleXMLReader.this.getTAG();
                    log2.e(tag, "SimpleXMLReader error - expected top element type to be [SimpleXMLElement] - found  [" + pop + JsonReaderKt.END_LIST);
                }

                @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
                public void endElement(@NotNull String uri, @NotNull String localName, @NotNull String qName) {
                    List reversed;
                    List list;
                    Intrinsics.checkParameterIsNotNull(uri, "uri");
                    Intrinsics.checkParameterIsNotNull(localName, "localName");
                    Intrinsics.checkParameterIsNotNull(qName, "qName");
                    ArrayList arrayList = new ArrayList();
                    while (!((Stack) objectRef.element).isEmpty()) {
                        Object pop = ((Stack) objectRef.element).pop();
                        if (pop instanceof SimpleXMLElement) {
                            arrayList.add(pop);
                        } else if (pop instanceof SimpleXMLElementStart) {
                            reversed = CollectionsKt___CollectionsKt.reversed(arrayList);
                            list = CollectionsKt___CollectionsKt.toList(reversed);
                            SimpleXMLElementStart simpleXMLElementStart = (SimpleXMLElementStart) pop;
                            ((Stack) objectRef.element).push(new SimpleXMLElement(simpleXMLElementStart.getName(), simpleXMLElementStart.getAttributes(), list));
                            return;
                        } else {
                            throw new InvalidObjectException("expected SimpleXMLElement or SimpleXMLElementStart - found [" + pop + JsonReaderKt.END_LIST);
                        }
                    }
                }

                /* JADX WARN: Code restructure failed: missing block: B:6:0x0007, code lost:
                    r1 = com.amazon.scxml.internal.SimpleXMLReaderKt.toMap(r4);
                 */
                @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void startElement(@org.jetbrains.annotations.Nullable java.lang.String r1, @org.jetbrains.annotations.Nullable java.lang.String r2, @org.jetbrains.annotations.Nullable java.lang.String r3, @org.jetbrains.annotations.Nullable org.xml.sax.Attributes r4) {
                    /*
                        r0 = this;
                        if (r3 == 0) goto L3
                        goto L5
                    L3:
                        java.lang.String r3 = "UNKNOWN"
                    L5:
                        if (r4 == 0) goto Le
                        java.util.Map r1 = com.amazon.scxml.internal.SimpleXMLReaderKt.access$toMap(r4)
                        if (r1 == 0) goto Le
                        goto L12
                    Le:
                        java.util.Map r1 = kotlin.collections.MapsKt.emptyMap()
                    L12:
                        com.amazon.scxml.internal.SimpleXMLElementStart r2 = new com.amazon.scxml.internal.SimpleXMLElementStart
                        r2.<init>(r3, r1)
                        kotlin.jvm.internal.Ref$ObjectRef r1 = r2
                        T r1 = r1.element
                        java.util.Stack r1 = (java.util.Stack) r1
                        r1.push(r2)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.scxml.internal.SimpleXMLReader$process$defaultHandler$1.startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes):void");
                }
            });
            return (SimpleXMLElement) objectRef2.element;
        } catch (Exception e) {
            Logger log = Loggable.Companion.getLog();
            String tag = getTAG();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("e - ");
            String message = e.getMessage();
            if (message == null) {
                message = "NO_ERROR_MESSAGE";
            }
            outline107.append(message);
            log.e(tag, outline107.toString());
            return null;
        }
    }
}
