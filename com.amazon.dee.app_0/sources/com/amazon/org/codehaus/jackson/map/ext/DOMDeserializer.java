package com.amazon.org.codehaus.jackson.map.ext;

import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
/* loaded from: classes13.dex */
public abstract class DOMDeserializer<T> extends FromStringDeserializer<T> {
    static final DocumentBuilderFactory _parserFactory = DocumentBuilderFactory.newInstance();

    /* loaded from: classes13.dex */
    public static class DocumentDeserializer extends DOMDeserializer<Document> {
        public DocumentDeserializer() {
            super(Document.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ext.DOMDeserializer, com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: collision with other method in class */
        public Document mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return parse(str);
        }
    }

    /* loaded from: classes13.dex */
    public static class NodeDeserializer extends DOMDeserializer<Node> {
        public NodeDeserializer() {
            super(Node.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ext.DOMDeserializer, com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: collision with other method in class */
        public Node mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return parse(str);
        }
    }

    static {
        _parserFactory.setNamespaceAware(true);
    }

    protected DOMDeserializer(Class<T> cls) {
        super(cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
    /* renamed from: _deserialize */
    public abstract T mo4201_deserialize(String str, DeserializationContext deserializationContext);

    protected final Document parse(String str) throws IllegalArgumentException {
        try {
            return _parserFactory.newDocumentBuilder().parse(new InputSource(new StringReader(str)));
        } catch (Exception e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Failed to parse JSON String as XML: ")), e);
        }
    }
}
