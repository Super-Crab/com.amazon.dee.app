package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeclaredMemberIndex.kt */
/* loaded from: classes3.dex */
public class ClassDeclaredMemberIndex implements DeclaredMemberIndex {
    private final Map<Name, JavaField> fields;
    @NotNull
    private final JavaClass jClass;
    private final Function1<JavaMember, Boolean> memberFilter;
    private final Function1<JavaMethod, Boolean> methodFilter;
    private final Map<Name, List<JavaMethod>> methods;

    /* JADX WARN: Multi-variable type inference failed */
    public ClassDeclaredMemberIndex(@NotNull JavaClass jClass, @NotNull Function1<? super JavaMember, Boolean> memberFilter) {
        Sequence asSequence;
        Sequence filter;
        Sequence asSequence2;
        Sequence filter2;
        Intrinsics.checkParameterIsNotNull(jClass, "jClass");
        Intrinsics.checkParameterIsNotNull(memberFilter, "memberFilter");
        this.jClass = jClass;
        this.memberFilter = memberFilter;
        this.methodFilter = new ClassDeclaredMemberIndex$methodFilter$1(this);
        asSequence = CollectionsKt___CollectionsKt.asSequence(this.jClass.mo11623getMethods());
        filter = SequencesKt___SequencesKt.filter(asSequence, this.methodFilter);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : filter) {
            Name name = ((JavaMethod) obj).getName();
            Object obj2 = linkedHashMap.get(name);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(name, obj2);
            }
            ((List) obj2).add(obj);
        }
        this.methods = linkedHashMap;
        asSequence2 = CollectionsKt___CollectionsKt.asSequence(this.jClass.mo11621getFields());
        filter2 = SequencesKt___SequencesKt.filter(asSequence2, this.memberFilter);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj3 : filter2) {
            linkedHashMap2.put(((JavaField) obj3).getName(), obj3);
        }
        this.fields = linkedHashMap2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    @Nullable
    public JavaField findFieldByName(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return this.fields.get(name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    @NotNull
    /* renamed from: findMethodsByName */
    public Collection<JavaMethod> mo11656findMethodsByName(@NotNull Name name) {
        List emptyList;
        Intrinsics.checkParameterIsNotNull(name, "name");
        List<JavaMethod> list = this.methods.get(name);
        if (list != null) {
            return list;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    @NotNull
    public Set<Name> getFieldNames() {
        Sequence asSequence;
        Sequence<JavaField> filter;
        asSequence = CollectionsKt___CollectionsKt.asSequence(this.jClass.mo11621getFields());
        filter = SequencesKt___SequencesKt.filter(asSequence, this.memberFilter);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (JavaField javaField : filter) {
            linkedHashSet.add(javaField.getName());
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    @NotNull
    public Set<Name> getMethodNames() {
        Sequence asSequence;
        Sequence<JavaMethod> filter;
        asSequence = CollectionsKt___CollectionsKt.asSequence(this.jClass.mo11623getMethods());
        filter = SequencesKt___SequencesKt.filter(asSequence, this.methodFilter);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (JavaMethod javaMethod : filter) {
            linkedHashSet.add(javaMethod.getName());
        }
        return linkedHashSet;
    }
}
