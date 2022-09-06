package amazon.speech.config.util;

import java.util.Objects;
/* loaded from: classes.dex */
public class NamespaceNameIdentifier {
    private final String mName;
    private final String mNamespace;

    public NamespaceNameIdentifier(String str, String str2) {
        this.mNamespace = str;
        this.mName = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NamespaceNameIdentifier)) {
            return false;
        }
        NamespaceNameIdentifier namespaceNameIdentifier = (NamespaceNameIdentifier) obj;
        return getName().equals(namespaceNameIdentifier.getName()) & getNamespace().equals(namespaceNameIdentifier.getNamespace()) & true;
    }

    public String getName() {
        return this.mName;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public int hashCode() {
        return Objects.hash(getNamespace(), getName());
    }

    public String toString() {
        return String.format("%s_%s", this.mNamespace, this.mName);
    }
}
