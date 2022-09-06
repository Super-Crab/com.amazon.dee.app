package com.amazon.scxml.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SimpleXMLElement.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007HÆ\u0003J9\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/amazon/scxml/internal/SimpleXMLElement;", "", "name", "", "attributes", "", "children", "", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/List;)V", "getAttributes", "()Ljava/util/Map;", "getChildren", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SimpleXMLElement {
    @NotNull
    private final Map<String, String> attributes;
    @NotNull
    private final List<SimpleXMLElement> children;
    @NotNull
    private final String name;

    public SimpleXMLElement(@NotNull String name, @NotNull Map<String, String> attributes, @NotNull List<SimpleXMLElement> children) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(attributes, "attributes");
        Intrinsics.checkParameterIsNotNull(children, "children");
        this.name = name;
        this.attributes = attributes;
        this.children = children;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SimpleXMLElement copy$default(SimpleXMLElement simpleXMLElement, String str, Map map, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = simpleXMLElement.name;
        }
        if ((i & 2) != 0) {
            map = simpleXMLElement.attributes;
        }
        if ((i & 4) != 0) {
            list = simpleXMLElement.children;
        }
        return simpleXMLElement.copy(str, map, list);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final Map<String, String> component2() {
        return this.attributes;
    }

    @NotNull
    public final List<SimpleXMLElement> component3() {
        return this.children;
    }

    @NotNull
    public final SimpleXMLElement copy(@NotNull String name, @NotNull Map<String, String> attributes, @NotNull List<SimpleXMLElement> children) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(attributes, "attributes");
        Intrinsics.checkParameterIsNotNull(children, "children");
        return new SimpleXMLElement(name, attributes, children);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SimpleXMLElement)) {
                return false;
            }
            SimpleXMLElement simpleXMLElement = (SimpleXMLElement) obj;
            return Intrinsics.areEqual(this.name, simpleXMLElement.name) && Intrinsics.areEqual(this.attributes, simpleXMLElement.attributes) && Intrinsics.areEqual(this.children, simpleXMLElement.children);
        }
        return true;
    }

    @NotNull
    public final Map<String, String> getAttributes() {
        return this.attributes;
    }

    @NotNull
    public final List<SimpleXMLElement> getChildren() {
        return this.children;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Map<String, String> map = this.attributes;
        int hashCode2 = (hashCode + (map != null ? map.hashCode() : 0)) * 31;
        List<SimpleXMLElement> list = this.children;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SimpleXMLElement(name=");
        outline107.append(this.name);
        outline107.append(", attributes=");
        outline107.append(this.attributes);
        outline107.append(", children=");
        return GeneratedOutlineSupport1.outline95(outline107, this.children, ")");
    }
}
