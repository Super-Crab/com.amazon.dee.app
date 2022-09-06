package com.amazon.org.codehaus.jackson.node;

import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonStreamContext;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public abstract class NodeCursor extends JsonStreamContext {
    final NodeCursor _parent;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes13.dex */
    public static final class Array extends NodeCursor {
        Iterator<JsonNode> _contents;
        JsonNode _currentNode;

        public Array(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(1, nodeCursor);
            this._contents = jsonNode.getElements();
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return ((ContainerNode) currentNode()).size() > 0;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            return this._currentNode;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return JsonToken.END_ARRAY;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor, com.amazon.org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor, com.amazon.org.codehaus.jackson.JsonStreamContext
        /* renamed from: getParent */
        public /* bridge */ /* synthetic */ JsonStreamContext mo4257getParent() {
            return super.mo4257getParent();
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (!this._contents.hasNext()) {
                this._currentNode = null;
                return null;
            }
            this._currentNode = this._contents.next();
            return this._currentNode.asToken();
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            return nextToken();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes13.dex */
    public static final class Object extends NodeCursor {
        Iterator<Map.Entry<String, JsonNode>> _contents;
        Map.Entry<String, JsonNode> _current;
        boolean _needEntry;

        public Object(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(2, nodeCursor);
            this._contents = ((ObjectNode) jsonNode).getFields();
            this._needEntry = true;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return ((ContainerNode) currentNode()).size() > 0;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            Map.Entry<String, JsonNode> entry = this._current;
            if (entry == null) {
                return null;
            }
            return entry.getValue();
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return JsonToken.END_OBJECT;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor, com.amazon.org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            Map.Entry<String, JsonNode> entry = this._current;
            if (entry == null) {
                return null;
            }
            return entry.getKey();
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor, com.amazon.org.codehaus.jackson.JsonStreamContext
        /* renamed from: getParent */
        public /* bridge */ /* synthetic */ JsonStreamContext mo4257getParent() {
            return super.mo4257getParent();
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (this._needEntry) {
                if (!this._contents.hasNext()) {
                    this._current = null;
                    return null;
                }
                this._needEntry = false;
                this._current = this._contents.next();
                return JsonToken.FIELD_NAME;
            }
            this._needEntry = true;
            return this._current.getValue().asToken();
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            JsonToken nextToken = nextToken();
            return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes13.dex */
    public static final class RootValue extends NodeCursor {
        protected boolean _done;
        JsonNode _node;

        public RootValue(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(0, nodeCursor);
            this._done = false;
            this._node = jsonNode;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return false;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            return this._node;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor, com.amazon.org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor, com.amazon.org.codehaus.jackson.JsonStreamContext
        /* renamed from: getParent */
        public /* bridge */ /* synthetic */ JsonStreamContext mo4257getParent() {
            return super.mo4257getParent();
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (!this._done) {
                this._done = true;
                return this._node.asToken();
            }
            this._node = null;
            return null;
        }

        @Override // com.amazon.org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            return nextToken();
        }
    }

    public NodeCursor(int i, NodeCursor nodeCursor) {
        this._type = i;
        this._index = -1;
        this._parent = nodeCursor;
    }

    public abstract boolean currentHasChildren();

    public abstract JsonNode currentNode();

    public abstract JsonToken endToken();

    @Override // com.amazon.org.codehaus.jackson.JsonStreamContext
    public abstract String getCurrentName();

    public final NodeCursor iterateChildren() {
        JsonNode currentNode = currentNode();
        if (currentNode != null) {
            if (currentNode.isArray()) {
                return new Array(currentNode, this);
            }
            if (currentNode.isObject()) {
                return new Object(currentNode, this);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Current node of type ");
            outline107.append(currentNode.getClass().getName());
            throw new IllegalStateException(outline107.toString());
        }
        throw new IllegalStateException("No current node");
    }

    public abstract JsonToken nextToken();

    public abstract JsonToken nextValue();

    @Override // com.amazon.org.codehaus.jackson.JsonStreamContext
    /* renamed from: getParent  reason: collision with other method in class */
    public final NodeCursor mo4257getParent() {
        return this._parent;
    }
}
