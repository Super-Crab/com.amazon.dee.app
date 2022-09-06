package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.impl.AbstractConfigValue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ResolveSource {
    final Node<Container> pathFromRoot;
    final AbstractConfigObject root;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ResultWithPath {
        final Node<Container> pathFromRoot;
        final ResolveResult<? extends AbstractConfigValue> result;

        ResultWithPath(ResolveResult<? extends AbstractConfigValue> resolveResult, Node<Container> node) {
            this.result = resolveResult;
            this.pathFromRoot = node;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ResultWithPath(result=");
            outline107.append(this.result);
            outline107.append(", pathFromRoot=");
            outline107.append(this.pathFromRoot);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ValueWithPath {
        final Node<Container> pathFromRoot;
        final AbstractConfigValue value;

        ValueWithPath(AbstractConfigValue abstractConfigValue, Node<Container> node) {
            this.value = abstractConfigValue;
            this.pathFromRoot = node;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ValueWithPath(value=");
            outline107.append(this.value);
            outline107.append(", pathFromRoot=");
            outline107.append(this.pathFromRoot);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveSource(AbstractConfigObject abstractConfigObject, Node<Container> node) {
        this.root = abstractConfigObject;
        this.pathFromRoot = node;
    }

    private static ResultWithPath findInObject(AbstractConfigObject abstractConfigObject, ResolveContext resolveContext, Path path) throws AbstractConfigValue.NotPossibleToResolve {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            ConfigImpl.trace("*** finding '" + path + "' in " + abstractConfigObject);
        }
        Path restrictToChild = resolveContext.restrictToChild();
        ResolveResult<? extends AbstractConfigValue> resolve = resolveContext.restrict(path).resolve(abstractConfigObject, new ResolveSource(abstractConfigObject));
        ResolveContext restrict = resolve.context.restrict(restrictToChild);
        V v = resolve.value;
        if (v instanceof AbstractConfigObject) {
            ValueWithPath findInObject = findInObject((AbstractConfigObject) v, path);
            return new ResultWithPath(ResolveResult.make(restrict, findInObject.value), findInObject.pathFromRoot);
        }
        throw new ConfigException.BugOrBroken("resolved object to non-object " + abstractConfigObject + " to " + resolve);
    }

    private static Node<Container> replace(Node<Container> node, Container container, AbstractConfigValue abstractConfigValue) {
        Container head = node.head();
        if (head == container) {
            Container head2 = node.tail() == null ? null : node.tail().head();
            if (abstractConfigValue == null || !(abstractConfigValue instanceof Container)) {
                if (head2 == null) {
                    return null;
                }
                return replace(node.tail(), head2, head2.mo10252replaceChild((AbstractConfigValue) container, null));
            } else if (head2 == null) {
                return new Node<>((Container) abstractConfigValue);
            } else {
                Node<Container> replace = replace(node.tail(), head2, head2.mo10252replaceChild((AbstractConfigValue) container, abstractConfigValue));
                if (replace != null) {
                    return replace.prepend((Container) abstractConfigValue);
                }
                return new Node<>((Container) abstractConfigValue);
            }
        }
        throw new ConfigException.BugOrBroken("Can only replace() the top node we're resolving; had " + head + " on top and tried to replace " + container + " overall list was " + node);
    }

    private AbstractConfigObject rootMustBeObj(Container container) {
        if (container instanceof AbstractConfigObject) {
            return (AbstractConfigObject) container;
        }
        return SimpleConfigObject.empty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultWithPath lookupSubst(ResolveContext resolveContext, SubstitutionExpression substitutionExpression, int i) throws AbstractConfigValue.NotPossibleToResolve {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth = resolveContext.depth();
            ConfigImpl.trace(depth, "searching for " + substitutionExpression);
        }
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth2 = resolveContext.depth();
            ConfigImpl.trace(depth2, substitutionExpression + " - looking up relative to file it occurred in");
        }
        ResultWithPath findInObject = findInObject(this.root, resolveContext, substitutionExpression.path());
        if (findInObject.result.value == 0) {
            Path subPath = substitutionExpression.path().subPath(i);
            if (i > 0) {
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth3 = findInObject.result.context.depth();
                    ConfigImpl.trace(depth3, subPath + " - looking up relative to parent file");
                }
                findInObject = findInObject(this.root, findInObject.result.context, subPath);
            }
            ResolveResult<? extends AbstractConfigValue> resolveResult = findInObject.result;
            if (resolveResult.value == 0 && resolveResult.context.options().getUseSystemEnvironment()) {
                if (ConfigImpl.traceSubstitutionsEnabled()) {
                    int depth4 = findInObject.result.context.depth();
                    ConfigImpl.trace(depth4, subPath + " - looking up in system environment");
                }
                findInObject = findInObject(ConfigImpl.envVariablesAsConfigObject(), resolveContext, subPath);
            }
        }
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            int depth5 = findInObject.result.context.depth();
            ConfigImpl.trace(depth5, "resolved to " + findInObject);
        }
        return findInObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveSource pushParent(Container container) {
        if (container != null) {
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append("pushing parent ");
                sb.append(container);
                sb.append(" ==root ");
                sb.append(container == this.root);
                sb.append(" onto ");
                sb.append(this);
                ConfigImpl.trace(sb.toString());
            }
            Node<Container> node = this.pathFromRoot;
            if (node == null) {
                AbstractConfigObject abstractConfigObject = this.root;
                if (container == abstractConfigObject) {
                    return new ResolveSource(abstractConfigObject, new Node(container));
                }
                if (ConfigImpl.traceSubstitutionsEnabled() && this.root.hasDescendant((AbstractConfigValue) container)) {
                    ConfigImpl.trace("***** BUG ***** tried to push parent " + container + " without having a path to it in " + this);
                }
                return this;
            }
            Container head = node.head();
            if (ConfigImpl.traceSubstitutionsEnabled() && head != null && !head.hasDescendant((AbstractConfigValue) container)) {
                ConfigImpl.trace("***** BUG ***** trying to push non-child of " + head + ", non-child was " + container);
            }
            return new ResolveSource(this.root, this.pathFromRoot.prepend(container));
        }
        throw new ConfigException.BugOrBroken("can't push null parent");
    }

    ResolveSource replaceCurrentParent(Container container, Container container2) {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            ConfigImpl.trace("replaceCurrentParent old " + container + "@" + System.identityHashCode(container) + " replacement " + container2 + "@" + System.identityHashCode(container) + " in " + this);
        }
        if (container == container2) {
            return this;
        }
        Node<Container> node = this.pathFromRoot;
        if (node != null) {
            Node<Container> replace = replace(node, container, (AbstractConfigValue) container2);
            if (ConfigImpl.traceSubstitutionsEnabled()) {
                ConfigImpl.trace("replaced " + container + " with " + container2 + " in " + this);
                StringBuilder sb = new StringBuilder();
                sb.append("path was: ");
                sb.append(this.pathFromRoot);
                sb.append(" is now ");
                sb.append(replace);
                ConfigImpl.trace(sb.toString());
            }
            if (replace != null) {
                return new ResolveSource((AbstractConfigObject) replace.last(), replace);
            }
            return new ResolveSource(SimpleConfigObject.empty());
        } else if (container == this.root) {
            return new ResolveSource(rootMustBeObj(container2));
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("attempt to replace root ");
            outline107.append(this.root);
            outline107.append(" with ");
            outline107.append(container2);
            throw new ConfigException.BugOrBroken(outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveSource replaceWithinCurrentParent(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2) {
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            ConfigImpl.trace("replaceWithinCurrentParent old " + abstractConfigValue + "@" + System.identityHashCode(abstractConfigValue) + " replacement " + abstractConfigValue2 + "@" + System.identityHashCode(abstractConfigValue) + " in " + this);
        }
        if (abstractConfigValue == abstractConfigValue2) {
            return this;
        }
        Node<Container> node = this.pathFromRoot;
        if (node != null) {
            Container head = node.head();
            AbstractConfigValue mo10252replaceChild = head.mo10252replaceChild(abstractConfigValue, abstractConfigValue2);
            return replaceCurrentParent(head, mo10252replaceChild instanceof Container ? (Container) mo10252replaceChild : null);
        } else if (abstractConfigValue == this.root && (abstractConfigValue2 instanceof Container)) {
            return new ResolveSource(rootMustBeObj((Container) abstractConfigValue2));
        } else {
            throw new ConfigException.BugOrBroken("replace in parent not possible " + abstractConfigValue + " with " + abstractConfigValue2 + " in " + this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveSource resetParents() {
        return this.pathFromRoot == null ? this : new ResolveSource(this.root);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ResolveSource(root=");
        outline107.append(this.root);
        outline107.append(", pathFromRoot=");
        outline107.append(this.pathFromRoot);
        outline107.append(")");
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class Node<T> {
        final Node<T> next;
        final T value;

        Node(T t, Node<T> node) {
            this.value = t;
            this.next = node;
        }

        T head() {
            return this.value;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public T last() {
            Node<T> node = this;
            while (true) {
                Node<T> node2 = node.next;
                if (node2 != null) {
                    node = node2;
                } else {
                    return node.value;
                }
            }
        }

        Node<T> prepend(T t) {
            return new Node<>(t, this);
        }

        Node<T> reverse() {
            if (this.next == null) {
                return this;
            }
            Node<T> node = new Node<>(this.value);
            for (Node<T> node2 = this.next; node2 != null; node2 = node2.next) {
                node = node.prepend(node2.value);
            }
            return node;
        }

        Node<T> tail() {
            return this.next;
        }

        public String toString() {
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("[");
            for (Node<T> reverse = reverse(); reverse != null; reverse = reverse.next) {
                outline103.append(reverse.value.toString());
                if (reverse.next != null) {
                    outline103.append(" <= ");
                }
            }
            outline103.append("]");
            return outline103.toString();
        }

        Node(T t) {
            this(t, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveSource(AbstractConfigObject abstractConfigObject) {
        this.root = abstractConfigObject;
        this.pathFromRoot = null;
    }

    private static ValueWithPath findInObject(AbstractConfigObject abstractConfigObject, Path path) {
        try {
            return findInObject(abstractConfigObject, path, (Node<Container>) null);
        } catch (ConfigException.NotResolved e) {
            throw ConfigImpl.improveNotResolved(path, e);
        }
    }

    private static ValueWithPath findInObject(AbstractConfigObject abstractConfigObject, Path path, Node<Container> node) {
        String first = path.first();
        Path remainder = path.remainder();
        if (ConfigImpl.traceSubstitutionsEnabled()) {
            ConfigImpl.trace("*** looking up '" + first + "' in " + abstractConfigObject);
        }
        AbstractConfigValue attemptPeekWithPartialResolve = abstractConfigObject.attemptPeekWithPartialResolve(first);
        Node<Container> node2 = node == null ? new Node<>(abstractConfigObject) : node.prepend(abstractConfigObject);
        if (remainder == null) {
            return new ValueWithPath(attemptPeekWithPartialResolve, node2);
        }
        if (attemptPeekWithPartialResolve instanceof AbstractConfigObject) {
            return findInObject((AbstractConfigObject) attemptPeekWithPartialResolve, remainder, node2);
        }
        return new ValueWithPath(null, node2);
    }
}
