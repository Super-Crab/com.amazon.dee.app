package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable<POJOPropertyBuilder> {
    protected Node<AnnotatedParameter> _ctorParameters;
    protected Node<AnnotatedField> _fields;
    protected Node<AnnotatedMethod> _getters;
    protected final String _internalName;
    protected final String _name;
    protected Node<AnnotatedMethod> _setters;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class Node<T> {
        public final String explicitName;
        public final boolean isMarkedIgnored;
        public final boolean isVisible;
        public final Node<T> next;
        public final T value;

        public Node(T t, Node<T> node, String str, boolean z, boolean z2) {
            this.value = t;
            this.next = node;
            String str2 = null;
            if (str == null) {
                this.explicitName = null;
            } else {
                this.explicitName = str.length() != 0 ? str : str2;
            }
            this.isVisible = z;
            this.isMarkedIgnored = z2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Node<T> append(Node<T> node) {
            Node<T> node2 = this.next;
            if (node2 == null) {
                return withNext(node);
            }
            return withNext(node2.append(node));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.value.toString());
            sb.append("[visible=");
            String outline97 = GeneratedOutlineSupport1.outline97(sb, this.isVisible, "]");
            if (this.next != null) {
                StringBuilder outline113 = GeneratedOutlineSupport1.outline113(outline97, ", ");
                outline113.append(this.next.toString());
                return outline113.toString();
            }
            return outline97;
        }

        public Node<T> trimByVisibility() {
            Node<T> node = this.next;
            if (node == null) {
                return this;
            }
            Node<T> trimByVisibility = node.trimByVisibility();
            if (this.explicitName != null) {
                if (trimByVisibility.explicitName == null) {
                    return withNext(null);
                }
                return withNext(trimByVisibility);
            } else if (trimByVisibility.explicitName != null) {
                return trimByVisibility;
            } else {
                boolean z = this.isVisible;
                if (z == trimByVisibility.isVisible) {
                    return withNext(trimByVisibility);
                }
                return z ? withNext(null) : trimByVisibility;
            }
        }

        public Node<T> withNext(Node<T> node) {
            return node == this.next ? this : new Node<>(this.value, node, this.explicitName, this.isVisible, this.isMarkedIgnored);
        }

        public Node<T> withValue(T t) {
            return t == this.value ? this : new Node<>(t, this.next, this.explicitName, this.isVisible, this.isMarkedIgnored);
        }

        public Node<T> withoutIgnored() {
            Node<T> withoutIgnored;
            if (this.isMarkedIgnored) {
                Node<T> node = this.next;
                if (node != null) {
                    return node.withoutIgnored();
                }
                return null;
            }
            Node<T> node2 = this.next;
            return (node2 == null || (withoutIgnored = node2.withoutIgnored()) == this.next) ? this : withNext(withoutIgnored);
        }

        public Node<T> withoutNonVisible() {
            Node<T> node = this.next;
            Node<T> withoutNonVisible = node == null ? null : node.withoutNonVisible();
            return this.isVisible ? withNext(withoutNonVisible) : withoutNonVisible;
        }
    }

    public POJOPropertyBuilder(String str) {
        this._internalName = str;
        this._name = str;
    }

    private <T> boolean _anyExplicitNames(Node<T> node) {
        while (node != null) {
            String str = node.explicitName;
            if (str != null && str.length() > 0) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private <T> boolean _anyIgnorals(Node<T> node) {
        while (node != null) {
            if (node.isMarkedIgnored) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private <T> boolean _anyVisible(Node<T> node) {
        while (node != null) {
            if (node.isVisible) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private AnnotationMap _mergeAnnotations(int i, Node<? extends AnnotatedMember>... nodeArr) {
        AnnotationMap allAnnotations = ((AnnotatedMember) nodeArr[i].value).getAllAnnotations();
        do {
            i++;
            if (i >= nodeArr.length) {
                return allAnnotations;
            }
        } while (nodeArr[i] == null);
        return AnnotationMap.merge(allAnnotations, _mergeAnnotations(i, nodeArr));
    }

    private <T> Node<T> _removeIgnored(Node<T> node) {
        return node == null ? node : node.withoutIgnored();
    }

    private <T> Node<T> _removeNonVisible(Node<T> node) {
        return node == null ? node : node.withoutNonVisible();
    }

    private <T> Node<T> _trimByVisibility(Node<T> node) {
        return node == null ? node : node.trimByVisibility();
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r4 = r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amazon.org.codehaus.jackson.map.introspect.POJOPropertyBuilder.Node<? extends com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember> findRenamed(com.amazon.org.codehaus.jackson.map.introspect.POJOPropertyBuilder.Node<? extends com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember> r4, com.amazon.org.codehaus.jackson.map.introspect.POJOPropertyBuilder.Node<? extends com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember> r5) {
        /*
            r3 = this;
        L0:
            if (r4 == 0) goto L4f
            java.lang.String r0 = r4.explicitName
            if (r0 != 0) goto L7
            goto L1c
        L7:
            java.lang.String r1 = r3._name
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L10
            goto L1c
        L10:
            if (r5 != 0) goto L14
            r5 = r4
            goto L1c
        L14:
            java.lang.String r1 = r5.explicitName
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L1f
        L1c:
            com.amazon.org.codehaus.jackson.map.introspect.POJOPropertyBuilder$Node<T> r4 = r4.next
            goto L0
        L1f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Conflicting property name definitions: '"
            java.lang.StringBuilder r1 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r1)
            java.lang.String r2 = r5.explicitName
            r1.append(r2)
            java.lang.String r2 = "' (for "
            r1.append(r2)
            T r5 = r5.value
            r1.append(r5)
            java.lang.String r5 = ") vs '"
            r1.append(r5)
            java.lang.String r5 = r4.explicitName
            r1.append(r5)
            r1.append(r2)
            T r4 = r4.value
            java.lang.String r5 = ")"
            java.lang.String r4 = com.android.tools.r8.GeneratedOutlineSupport1.outline88(r1, r4, r5)
            r0.<init>(r4)
            throw r0
        L4f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.map.introspect.POJOPropertyBuilder.findRenamed(com.amazon.org.codehaus.jackson.map.introspect.POJOPropertyBuilder$Node, com.amazon.org.codehaus.jackson.map.introspect.POJOPropertyBuilder$Node):com.amazon.org.codehaus.jackson.map.introspect.POJOPropertyBuilder$Node");
    }

    private static <T> Node<T> merge(Node<T> node, Node<T> node2) {
        return node == null ? node2 : node2 == null ? node : node.append(node2);
    }

    public void addAll(POJOPropertyBuilder pOJOPropertyBuilder) {
        this._fields = merge(this._fields, pOJOPropertyBuilder._fields);
        this._ctorParameters = merge(this._ctorParameters, pOJOPropertyBuilder._ctorParameters);
        this._getters = merge(this._getters, pOJOPropertyBuilder._getters);
        this._setters = merge(this._setters, pOJOPropertyBuilder._setters);
    }

    public void addCtor(AnnotatedParameter annotatedParameter, String str, boolean z, boolean z2) {
        this._ctorParameters = new Node<>(annotatedParameter, this._ctorParameters, str, z, z2);
    }

    public void addField(AnnotatedField annotatedField, String str, boolean z, boolean z2) {
        this._fields = new Node<>(annotatedField, this._fields, str, z, z2);
    }

    public void addGetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        this._getters = new Node<>(annotatedMethod, this._getters, str, z, z2);
    }

    public void addSetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        this._setters = new Node<>(annotatedMethod, this._setters, str, z, z2);
    }

    public boolean anyDeserializeIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    public boolean anyExplicitNames() {
        return _anyExplicitNames(this._fields) || _anyExplicitNames(this._getters) || _anyExplicitNames(this._setters) || _anyExplicitNames(this._ctorParameters);
    }

    public boolean anyIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    public boolean anySerializeIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters);
    }

    public boolean anyVisible() {
        return _anyVisible(this._fields) || _anyVisible(this._getters) || _anyVisible(this._setters) || _anyVisible(this._ctorParameters);
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean couldSerialize() {
        return (this._getters == null && this._fields == null) ? false : true;
    }

    public String findNewName() {
        Node<? extends AnnotatedMember> findRenamed = findRenamed(this._ctorParameters, findRenamed(this._setters, findRenamed(this._getters, findRenamed(this._fields, null))));
        if (findRenamed == null) {
            return null;
        }
        return findRenamed.explicitName;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMember getAccessor() {
        AnnotatedMethod getter = getGetter();
        return getter == null ? getField() : getter;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedParameter getConstructorParameter() {
        Node node = this._ctorParameters;
        if (node == null) {
            return null;
        }
        while (!(((AnnotatedParameter) node.value).getOwner() instanceof AnnotatedConstructor)) {
            node = node.next;
            if (node == null) {
                return this._ctorParameters.value;
            }
        }
        return (AnnotatedParameter) node.value;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedField getField() {
        Node<AnnotatedField> node = this._fields;
        if (node == null) {
            return null;
        }
        AnnotatedField annotatedField = node.value;
        for (Node node2 = node.next; node2 != null; node2 = node2.next) {
            AnnotatedField annotatedField2 = (AnnotatedField) node2.value;
            Class<?> declaringClass = annotatedField.getDeclaringClass();
            Class<?> declaringClass2 = annotatedField2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    annotatedField = annotatedField2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Multiple fields representing property \"");
            outline107.append(getName());
            outline107.append("\": ");
            outline107.append(annotatedField.getFullName());
            outline107.append(" vs ");
            outline107.append(annotatedField2.getFullName());
            throw new IllegalArgumentException(outline107.toString());
        }
        return annotatedField;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMethod getGetter() {
        Node<AnnotatedMethod> node = this._getters;
        if (node == null) {
            return null;
        }
        AnnotatedMethod annotatedMethod = node.value;
        for (Node node2 = node.next; node2 != null; node2 = node2.next) {
            AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) node2.value;
            Class<?> declaringClass = annotatedMethod.getDeclaringClass();
            Class<?> declaringClass2 = annotatedMethod2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    annotatedMethod = annotatedMethod2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Conflicting getter definitions for property \"");
            outline107.append(getName());
            outline107.append("\": ");
            outline107.append(annotatedMethod.getFullName());
            outline107.append(" vs ");
            outline107.append(annotatedMethod2.getFullName());
            throw new IllegalArgumentException(outline107.toString());
        }
        return annotatedMethod;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public String getInternalName() {
        return this._internalName;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMember getMutator() {
        AnnotatedParameter constructorParameter = getConstructorParameter();
        if (constructorParameter == null) {
            AnnotatedMethod setter = getSetter();
            return setter == null ? getField() : setter;
        }
        return constructorParameter;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition, com.amazon.org.codehaus.jackson.map.util.Named
    public String getName() {
        return this._name;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMethod getSetter() {
        Node<AnnotatedMethod> node = this._setters;
        if (node == null) {
            return null;
        }
        AnnotatedMethod annotatedMethod = node.value;
        for (Node node2 = node.next; node2 != null; node2 = node2.next) {
            AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) node2.value;
            Class<?> declaringClass = annotatedMethod.getDeclaringClass();
            Class<?> declaringClass2 = annotatedMethod2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    annotatedMethod = annotatedMethod2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Conflicting setter definitions for property \"");
            outline107.append(getName());
            outline107.append("\": ");
            outline107.append(annotatedMethod.getFullName());
            outline107.append(" vs ");
            outline107.append(annotatedMethod2.getFullName());
            throw new IllegalArgumentException(outline107.toString());
        }
        return annotatedMethod;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasConstructorParameter() {
        return this._ctorParameters != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasField() {
        return this._fields != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasGetter() {
        return this._getters != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasSetter() {
        return this._setters != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean isExplicitlyIncluded() {
        return anyExplicitNames();
    }

    public void mergeAnnotations(boolean z) {
        if (z) {
            Node<AnnotatedMethod> node = this._getters;
            if (node != null) {
                AnnotationMap _mergeAnnotations = _mergeAnnotations(0, node, this._fields, this._ctorParameters, this._setters);
                Node<AnnotatedMethod> node2 = this._getters;
                this._getters = node2.withValue(node2.value.mo4215withAnnotations(_mergeAnnotations));
                return;
            }
            Node<AnnotatedField> node3 = this._fields;
            if (node3 == null) {
                return;
            }
            AnnotationMap _mergeAnnotations2 = _mergeAnnotations(0, node3, this._ctorParameters, this._setters);
            Node<AnnotatedField> node4 = this._fields;
            this._fields = node4.withValue(node4.value.mo4215withAnnotations(_mergeAnnotations2));
            return;
        }
        Node<AnnotatedParameter> node5 = this._ctorParameters;
        if (node5 != null) {
            AnnotationMap _mergeAnnotations3 = _mergeAnnotations(0, node5, this._setters, this._fields, this._getters);
            Node<AnnotatedParameter> node6 = this._ctorParameters;
            this._ctorParameters = node6.withValue(node6.value.mo4215withAnnotations(_mergeAnnotations3));
            return;
        }
        Node<AnnotatedMethod> node7 = this._setters;
        if (node7 != null) {
            AnnotationMap _mergeAnnotations4 = _mergeAnnotations(0, node7, this._fields, this._getters);
            Node<AnnotatedMethod> node8 = this._setters;
            this._setters = node8.withValue(node8.value.mo4215withAnnotations(_mergeAnnotations4));
            return;
        }
        Node<AnnotatedField> node9 = this._fields;
        if (node9 == null) {
            return;
        }
        AnnotationMap _mergeAnnotations5 = _mergeAnnotations(0, node9, this._getters);
        Node<AnnotatedField> node10 = this._fields;
        this._fields = node10.withValue(node10.value.mo4215withAnnotations(_mergeAnnotations5));
    }

    public void removeIgnored() {
        this._fields = _removeIgnored(this._fields);
        this._getters = _removeIgnored(this._getters);
        this._setters = _removeIgnored(this._setters);
        this._ctorParameters = _removeIgnored(this._ctorParameters);
    }

    public void removeNonVisible() {
        this._getters = _removeNonVisible(this._getters);
        this._ctorParameters = _removeNonVisible(this._ctorParameters);
        if (this._getters == null) {
            this._fields = _removeNonVisible(this._fields);
            this._setters = _removeNonVisible(this._setters);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Property '");
        outline107.append(this._name);
        outline107.append("'; ctors: ");
        outline107.append(this._ctorParameters);
        outline107.append(", field(s): ");
        outline107.append(this._fields);
        outline107.append(", getter(s): ");
        outline107.append(this._getters);
        outline107.append(", setter(s): ");
        outline107.append(this._setters);
        outline107.append("]");
        return outline107.toString();
    }

    public void trimByVisibility() {
        this._fields = _trimByVisibility(this._fields);
        this._getters = _trimByVisibility(this._getters);
        this._setters = _trimByVisibility(this._setters);
        this._ctorParameters = _trimByVisibility(this._ctorParameters);
    }

    public POJOPropertyBuilder withName(String str) {
        return new POJOPropertyBuilder(this, str);
    }

    @Override // java.lang.Comparable
    public int compareTo(POJOPropertyBuilder pOJOPropertyBuilder) {
        if (this._ctorParameters != null) {
            if (pOJOPropertyBuilder._ctorParameters == null) {
                return -1;
            }
        } else if (pOJOPropertyBuilder._ctorParameters != null) {
            return 1;
        }
        return getName().compareTo(pOJOPropertyBuilder.getName());
    }

    public POJOPropertyBuilder(POJOPropertyBuilder pOJOPropertyBuilder, String str) {
        this._internalName = pOJOPropertyBuilder._internalName;
        this._name = str;
        this._fields = pOJOPropertyBuilder._fields;
        this._ctorParameters = pOJOPropertyBuilder._ctorParameters;
        this._getters = pOJOPropertyBuilder._getters;
        this._setters = pOJOPropertyBuilder._setters;
    }
}
