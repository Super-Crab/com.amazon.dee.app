package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPIterator;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.IteratorOptions;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPPropertyInfo;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes.dex */
public class XMPIteratorImpl implements XMPIterator {
    private String baseNS;
    private Iterator nodeIterator;
    private IteratorOptions options;
    protected boolean skipSiblings = false;
    protected boolean skipSubtree = false;

    /* loaded from: classes.dex */
    private class NodeIterator implements Iterator {
        protected static final int ITERATE_CHILDREN = 1;
        protected static final int ITERATE_NODE = 0;
        protected static final int ITERATE_QUALIFIER = 2;
        private Iterator childrenIterator;
        private int index;
        private String path;
        private XMPPropertyInfo returnProperty;
        private int state;
        private Iterator subIterator;
        private XMPNode visitedNode;

        public NodeIterator() {
            this.state = 0;
            this.childrenIterator = null;
            this.index = 0;
            this.subIterator = Collections.EMPTY_LIST.iterator();
            this.returnProperty = null;
        }

        public NodeIterator(XMPNode xMPNode, String str, int i) {
            this.state = 0;
            this.childrenIterator = null;
            this.index = 0;
            this.subIterator = Collections.EMPTY_LIST.iterator();
            this.returnProperty = null;
            this.visitedNode = xMPNode;
            this.state = 0;
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(xMPNode.getName());
            }
            this.path = accumulatePath(xMPNode, str, i);
        }

        private boolean iterateChildren(Iterator it2) {
            XMPIteratorImpl xMPIteratorImpl = XMPIteratorImpl.this;
            if (xMPIteratorImpl.skipSiblings) {
                xMPIteratorImpl.skipSiblings = false;
                this.subIterator = Collections.EMPTY_LIST.iterator();
            }
            if (!this.subIterator.hasNext() && it2.hasNext()) {
                this.index++;
                this.subIterator = new NodeIterator((XMPNode) it2.next(), this.path, this.index);
            }
            if (this.subIterator.hasNext()) {
                this.returnProperty = (XMPPropertyInfo) this.subIterator.next();
                return true;
            }
            return false;
        }

        protected String accumulatePath(XMPNode xMPNode, String str, int i) {
            String name;
            String str2;
            if (xMPNode.getParent() == null || xMPNode.getOptions().isSchemaNode()) {
                return null;
            }
            if (xMPNode.getParent().getOptions().isArray()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
                outline107.append(String.valueOf(i));
                outline107.append("]");
                name = outline107.toString();
                str2 = "";
            } else {
                name = xMPNode.getName();
                str2 = "/";
            }
            return (str == null || str.length() == 0) ? name : XMPIteratorImpl.this.getOptions().isJustLeafname() ? !name.startsWith(WebConstants.UriConstants.QUESTIONMARK_KEY) ? name : name.substring(1) : GeneratedOutlineSupport1.outline75(str, str2, name);
        }

        protected XMPPropertyInfo createPropertyInfo(final XMPNode xMPNode, final String str, final String str2) {
            final String value = xMPNode.getOptions().isSchemaNode() ? null : xMPNode.getValue();
            return new XMPPropertyInfo() { // from class: com.adobe.xmp.impl.XMPIteratorImpl.NodeIterator.1
                @Override // com.adobe.xmp.properties.XMPProperty
                public String getLanguage() {
                    return null;
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo
                public String getNamespace() {
                    if (!xMPNode.getOptions().isSchemaNode()) {
                        return XMPMetaFactory.getSchemaRegistry().getNamespaceURI(new QName(xMPNode.getName()).getPrefix());
                    }
                    return str;
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo, com.adobe.xmp.properties.XMPProperty
                public PropertyOptions getOptions() {
                    return xMPNode.getOptions();
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo
                public String getPath() {
                    return str2;
                }

                @Override // com.adobe.xmp.properties.XMPPropertyInfo, com.adobe.xmp.properties.XMPProperty
                public String getValue() {
                    return value;
                }
            };
        }

        protected Iterator getChildrenIterator() {
            return this.childrenIterator;
        }

        protected XMPPropertyInfo getReturnProperty() {
            return this.returnProperty;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.returnProperty != null) {
                return true;
            }
            int i = this.state;
            if (i == 0) {
                return reportNode();
            }
            if (i != 1) {
                if (this.childrenIterator == null) {
                    this.childrenIterator = this.visitedNode.iterateQualifier();
                }
                return iterateChildren(this.childrenIterator);
            }
            if (this.childrenIterator == null) {
                this.childrenIterator = this.visitedNode.iterateChildren();
            }
            boolean iterateChildren = iterateChildren(this.childrenIterator);
            if (iterateChildren || !this.visitedNode.hasQualifier() || XMPIteratorImpl.this.getOptions().isOmitQualifiers()) {
                return iterateChildren;
            }
            this.state = 2;
            this.childrenIterator = null;
            return hasNext();
        }

        @Override // java.util.Iterator
        public Object next() {
            if (hasNext()) {
                XMPPropertyInfo xMPPropertyInfo = this.returnProperty;
                this.returnProperty = null;
                return xMPPropertyInfo;
            }
            throw new NoSuchElementException("There are no more nodes to return");
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        protected boolean reportNode() {
            this.state = 1;
            if (this.visitedNode.getParent() == null || (XMPIteratorImpl.this.getOptions().isJustLeafnodes() && this.visitedNode.hasChildren())) {
                return hasNext();
            }
            this.returnProperty = createPropertyInfo(this.visitedNode, XMPIteratorImpl.this.getBaseNS(), this.path);
            return true;
        }

        protected void setChildrenIterator(Iterator it2) {
            this.childrenIterator = it2;
        }

        protected void setReturnProperty(XMPPropertyInfo xMPPropertyInfo) {
            this.returnProperty = xMPPropertyInfo;
        }
    }

    /* loaded from: classes.dex */
    private class NodeIteratorChildren extends NodeIterator {
        private Iterator childrenIterator;
        private int index;
        private String parentPath;

        public NodeIteratorChildren(XMPNode xMPNode, String str) {
            super();
            this.index = 0;
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(xMPNode.getName());
            }
            this.parentPath = accumulatePath(xMPNode, str, 1);
            this.childrenIterator = xMPNode.iterateChildren();
        }

        @Override // com.adobe.xmp.impl.XMPIteratorImpl.NodeIterator, java.util.Iterator
        public boolean hasNext() {
            if (getReturnProperty() != null) {
                return true;
            }
            if (XMPIteratorImpl.this.skipSiblings || !this.childrenIterator.hasNext()) {
                return false;
            }
            XMPNode xMPNode = (XMPNode) this.childrenIterator.next();
            this.index++;
            String str = null;
            if (xMPNode.getOptions().isSchemaNode()) {
                XMPIteratorImpl.this.setBaseNS(xMPNode.getName());
            } else if (xMPNode.getParent() != null) {
                str = accumulatePath(xMPNode, this.parentPath, this.index);
            }
            if (XMPIteratorImpl.this.getOptions().isJustLeafnodes() && xMPNode.hasChildren()) {
                return hasNext();
            }
            setReturnProperty(createPropertyInfo(xMPNode, XMPIteratorImpl.this.getBaseNS(), str));
            return true;
        }
    }

    public XMPIteratorImpl(XMPMetaImpl xMPMetaImpl, String str, String str2, IteratorOptions iteratorOptions) throws XMPException {
        XMPNode findSchemaNode;
        String str3 = null;
        this.baseNS = null;
        this.nodeIterator = null;
        this.options = iteratorOptions == null ? new IteratorOptions() : iteratorOptions;
        boolean z = str != null && str.length() > 0;
        boolean z2 = str2 != null && str2.length() > 0;
        if (!z && !z2) {
            findSchemaNode = xMPMetaImpl.getRoot();
        } else if (z && z2) {
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPPath xMPPath = new XMPPath();
            for (int i = 0; i < expandXPath.size() - 1; i++) {
                xMPPath.add(expandXPath.getSegment(i));
            }
            findSchemaNode = XMPNodeUtils.findNode(xMPMetaImpl.getRoot(), expandXPath, false, null);
            this.baseNS = str;
            str3 = xMPPath.toString();
        } else if (!z || z2) {
            throw new XMPException("Schema namespace URI is required", 101);
        } else {
            findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.getRoot(), str, false);
        }
        if (findSchemaNode != null) {
            this.nodeIterator = !this.options.isJustChildren() ? new NodeIterator(findSchemaNode, str3, 1) : new NodeIteratorChildren(findSchemaNode, str3);
        } else {
            this.nodeIterator = Collections.EMPTY_LIST.iterator();
        }
    }

    protected String getBaseNS() {
        return this.baseNS;
    }

    protected IteratorOptions getOptions() {
        return this.options;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.nodeIterator.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return this.nodeIterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("The XMPIterator does not support remove().");
    }

    protected void setBaseNS(String str) {
        this.baseNS = str;
    }

    @Override // com.adobe.xmp.XMPIterator
    public void skipSiblings() {
        skipSubtree();
        this.skipSiblings = true;
    }

    @Override // com.adobe.xmp.XMPIterator
    public void skipSubtree() {
        this.skipSubtree = true;
    }
}
