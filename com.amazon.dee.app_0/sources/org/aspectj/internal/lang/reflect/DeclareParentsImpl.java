package org.aspectj.internal.lang.reflect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Type;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.DeclareParents;
import org.aspectj.lang.reflect.TypePattern;
/* loaded from: classes4.dex */
public class DeclareParentsImpl implements DeclareParents {
    private AjType<?> declaringType;
    private String firstMissingTypeName;
    private boolean isExtends;
    private Type[] parents;
    private boolean parentsError;
    private String parentsString;
    private TypePattern targetTypesPattern;

    public DeclareParentsImpl(String str, String str2, boolean z, AjType<?> ajType) {
        this.parentsError = false;
        this.targetTypesPattern = new TypePatternImpl(str);
        this.isExtends = z;
        this.declaringType = ajType;
        this.parentsString = str2;
        try {
            this.parents = StringToType.commaSeparatedListToTypeArray(str2, ajType.getJavaClass());
        } catch (ClassNotFoundException e) {
            this.parentsError = true;
            this.firstMissingTypeName = e.getMessage();
        }
    }

    @Override // org.aspectj.lang.reflect.DeclareParents
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // org.aspectj.lang.reflect.DeclareParents
    public Type[] getParentTypes() throws ClassNotFoundException {
        if (!this.parentsError) {
            return this.parents;
        }
        throw new ClassNotFoundException(this.firstMissingTypeName);
    }

    @Override // org.aspectj.lang.reflect.DeclareParents
    public TypePattern getTargetTypesPattern() {
        return this.targetTypesPattern;
    }

    @Override // org.aspectj.lang.reflect.DeclareParents
    public boolean isExtends() {
        return this.isExtends;
    }

    @Override // org.aspectj.lang.reflect.DeclareParents
    public boolean isImplements() {
        return !this.isExtends;
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("declare parents : ");
        outline103.append(getTargetTypesPattern().asString());
        outline103.append(isExtends() ? " extends " : " implements ");
        outline103.append(this.parentsString);
        return outline103.toString();
    }
}
