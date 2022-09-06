package org.aspectj.internal.lang.reflect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.DeclareSoft;
import org.aspectj.lang.reflect.PointcutExpression;
/* loaded from: classes4.dex */
public class DeclareSoftImpl implements DeclareSoft {
    private AjType<?> declaringType;
    private AjType<?> exceptionType;
    private String missingTypeName;
    private PointcutExpression pointcut;

    public DeclareSoftImpl(AjType<?> ajType, String str, String str2) {
        this.declaringType = ajType;
        this.pointcut = new PointcutExpressionImpl(str);
        try {
            this.exceptionType = AjTypeSystem.getAjType(Class.forName(str2, false, ajType.getJavaClass().getClassLoader()));
        } catch (ClassNotFoundException unused) {
            this.missingTypeName = str2;
        }
    }

    @Override // org.aspectj.lang.reflect.DeclareSoft
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // org.aspectj.lang.reflect.DeclareSoft
    public PointcutExpression getPointcutExpression() {
        return this.pointcut;
    }

    @Override // org.aspectj.lang.reflect.DeclareSoft
    public AjType getSoftenedExceptionType() throws ClassNotFoundException {
        String str = this.missingTypeName;
        if (str == null) {
            return this.exceptionType;
        }
        throw new ClassNotFoundException(str);
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("declare soft : ");
        String str = this.missingTypeName;
        if (str != null) {
            outline103.append(this.exceptionType.getName());
        } else {
            outline103.append(str);
        }
        outline103.append(" : ");
        outline103.append(getPointcutExpression().asString());
        return outline103.toString();
    }
}
