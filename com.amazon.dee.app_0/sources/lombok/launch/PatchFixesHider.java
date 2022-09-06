package lombok.launch;

import com.facebook.react.uimanager.ViewProps;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import lombok.eclipse.EclipseAugments;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IAnnotatable;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.ast.Expression;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;
import org.eclipse.jdt.internal.compiler.ast.LocalDeclaration;
import org.eclipse.jdt.internal.compiler.ast.MessageSend;
import org.eclipse.jdt.internal.compiler.lookup.BlockScope;
import org.eclipse.jdt.internal.compiler.lookup.MethodBinding;
import org.eclipse.jdt.internal.compiler.lookup.Scope;
import org.eclipse.jdt.internal.compiler.lookup.TypeBinding;
import org.eclipse.jdt.internal.compiler.parser.Parser;
import org.eclipse.jdt.internal.compiler.problem.ProblemReporter;
import org.eclipse.jdt.internal.core.SourceField;
import org.eclipse.jdt.internal.core.dom.rewrite.NodeRewriteEvent;
import org.eclipse.jdt.internal.core.dom.rewrite.RewriteEvent;
import org.eclipse.jdt.internal.core.dom.rewrite.TokenScanner;
import org.eclipse.jdt.internal.corext.refactoring.SearchResultGroup;
import org.eclipse.jdt.internal.corext.refactoring.structure.ASTNodeSearchUtil;
/* loaded from: classes4.dex */
final class PatchFixesHider {

    /* loaded from: classes4.dex */
    public static final class Delegate {
        private static final Method HANDLE_DELEGATE_FOR_TYPE = Util.findMethod(Util.shadowLoadClass("lombok.eclipse.agent.PatchDelegatePortal"), "handleDelegateForType", Object.class);

        public static boolean handleDelegateForType(Object obj) {
            return ((Boolean) Util.invokeMethod(HANDLE_DELEGATE_FOR_TYPE, obj)).booleanValue();
        }
    }

    /* loaded from: classes4.dex */
    public static final class ExtensionMethod {
        private static final Method ERROR_NO_METHOD_FOR;
        private static final Method INVALID_METHOD;
        private static final Method INVALID_METHOD2;
        private static final Method RESOLVE_TYPE;

        static {
            Class<?> shadowLoadClass = Util.shadowLoadClass("lombok.eclipse.agent.PatchExtensionMethod");
            RESOLVE_TYPE = Util.findMethod(shadowLoadClass, "resolveType", TypeBinding.class, MessageSend.class, BlockScope.class);
            ERROR_NO_METHOD_FOR = Util.findMethod(shadowLoadClass, "errorNoMethodFor", ProblemReporter.class, MessageSend.class, TypeBinding.class, TypeBinding[].class);
            INVALID_METHOD = Util.findMethod(shadowLoadClass, "invalidMethod", ProblemReporter.class, MessageSend.class, MethodBinding.class);
            INVALID_METHOD2 = Util.findMethod(shadowLoadClass, "invalidMethod", ProblemReporter.class, MessageSend.class, MethodBinding.class, Scope.class);
        }

        public static void errorNoMethodFor(ProblemReporter problemReporter, MessageSend messageSend, TypeBinding typeBinding, TypeBinding[] typeBindingArr) {
            Util.invokeMethod(ERROR_NO_METHOD_FOR, problemReporter, messageSend, typeBinding, typeBindingArr);
        }

        public static void invalidMethod(ProblemReporter problemReporter, MessageSend messageSend, MethodBinding methodBinding) {
            Util.invokeMethod(INVALID_METHOD, problemReporter, messageSend, methodBinding);
        }

        public static TypeBinding resolveType(TypeBinding typeBinding, MessageSend messageSend, BlockScope blockScope) {
            return (TypeBinding) Util.invokeMethod(RESOLVE_TYPE, typeBinding, messageSend, blockScope);
        }

        public static void invalidMethod(ProblemReporter problemReporter, MessageSend messageSend, MethodBinding methodBinding, Scope scope) {
            Util.invokeMethod(INVALID_METHOD2, problemReporter, messageSend, methodBinding, scope);
        }
    }

    /* loaded from: classes4.dex */
    public static final class LombokDeps {
        public static final Method ADD_LOMBOK_NOTES;
        public static final Method POST_COMPILER_BUFFEREDOUTPUTSTREAM_STRING_STRING;
        public static final Method POST_COMPILER_BYTES_STRING;
        public static final Method POST_COMPILER_OUTPUTSTREAM;

        static {
            Class<?> shadowLoadClass = Util.shadowLoadClass("lombok.eclipse.agent.PatchFixesShadowLoaded");
            ADD_LOMBOK_NOTES = Util.findMethod(shadowLoadClass, "addLombokNotesToEclipseAboutDialog", String.class, String.class);
            POST_COMPILER_BYTES_STRING = Util.findMethod(shadowLoadClass, "runPostCompiler", byte[].class, String.class);
            POST_COMPILER_OUTPUTSTREAM = Util.findMethod(shadowLoadClass, "runPostCompiler", OutputStream.class);
            POST_COMPILER_BUFFEREDOUTPUTSTREAM_STRING_STRING = Util.findMethod(shadowLoadClass, "runPostCompiler", BufferedOutputStream.class, String.class, String.class);
        }

        public static String addLombokNotesToEclipseAboutDialog(String str, String str2) {
            try {
                return (String) Util.invokeMethod(ADD_LOMBOK_NOTES, str, str2);
            } catch (Throwable unused) {
                return str;
            }
        }

        public static byte[] runPostCompiler(byte[] bArr, String str) {
            return (byte[]) Util.invokeMethod(POST_COMPILER_BYTES_STRING, bArr, str);
        }

        public static OutputStream runPostCompiler(OutputStream outputStream) throws IOException {
            return (OutputStream) Util.invokeMethod(POST_COMPILER_OUTPUTSTREAM, outputStream);
        }

        public static BufferedOutputStream runPostCompiler(BufferedOutputStream bufferedOutputStream, String str, String str2) throws IOException {
            return (BufferedOutputStream) Util.invokeMethod(POST_COMPILER_BUFFEREDOUTPUTSTREAM_STRING_STRING, bufferedOutputStream, str, str2);
        }
    }

    /* loaded from: classes4.dex */
    public static final class Transform {
        private static final Method TRANSFORM;
        private static final Method TRANSFORM_SWAPPED;

        static {
            Class<?> shadowLoadClass = Util.shadowLoadClass("lombok.eclipse.TransformEclipseAST");
            TRANSFORM = Util.findMethod(shadowLoadClass, ViewProps.TRANSFORM, Parser.class, CompilationUnitDeclaration.class);
            TRANSFORM_SWAPPED = Util.findMethod(shadowLoadClass, "transform_swapped", CompilationUnitDeclaration.class, Parser.class);
        }

        public static void transform(Parser parser, CompilationUnitDeclaration compilationUnitDeclaration) throws IOException {
            Util.invokeMethod(TRANSFORM, parser, compilationUnitDeclaration);
        }

        public static void transform_swapped(CompilationUnitDeclaration compilationUnitDeclaration, Parser parser) throws IOException {
            Util.invokeMethod(TRANSFORM_SWAPPED, compilationUnitDeclaration, parser);
        }
    }

    /* loaded from: classes4.dex */
    public static final class Util {
        private static ClassLoader shadowLoader;

        public static Method findMethod(Class<?> cls, String str, Class<?>... clsArr) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                throw sneakyThrow(e);
            }
        }

        public static Object invokeMethod(Method method, Object... objArr) {
            try {
                return method.invoke(null, objArr);
            } catch (IllegalAccessException e) {
                throw sneakyThrow(e);
            } catch (InvocationTargetException e2) {
                throw sneakyThrow(e2.getCause());
            }
        }

        public static Class<?> shadowLoadClass(String str) {
            try {
                if (shadowLoader == null) {
                    try {
                        Class.forName("lombok.core.LombokNode");
                        shadowLoader = Util.class.getClassLoader();
                    } catch (ClassNotFoundException unused) {
                        shadowLoader = Main.getShadowClassLoader();
                    }
                }
                return Class.forName(str, true, shadowLoader);
            } catch (ClassNotFoundException e) {
                throw sneakyThrow(e);
            }
        }

        private static RuntimeException sneakyThrow(Throwable th) {
            if (th != null) {
                sneakyThrow0(th);
                return null;
            }
            throw new NullPointerException("t");
        }

        private static <T extends Throwable> void sneakyThrow0(Throwable th) throws Throwable {
            throw th;
        }
    }

    /* loaded from: classes4.dex */
    public static final class Val {
        private static final Method HANDLE_VAL_FOR_FOR_EACH;
        private static final Method HANDLE_VAL_FOR_LOCAL_DECLARATION;
        private static final Method SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED;
        private static final Method SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED2;

        static {
            Class<?> shadowLoadClass = Util.shadowLoadClass("lombok.eclipse.agent.PatchVal");
            SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED = Util.findMethod(shadowLoadClass, "skipResolveInitializerIfAlreadyCalled", Expression.class, BlockScope.class);
            SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED2 = Util.findMethod(shadowLoadClass, "skipResolveInitializerIfAlreadyCalled2", Expression.class, BlockScope.class, LocalDeclaration.class);
            HANDLE_VAL_FOR_LOCAL_DECLARATION = Util.findMethod(shadowLoadClass, "handleValForLocalDeclaration", LocalDeclaration.class, BlockScope.class);
            HANDLE_VAL_FOR_FOR_EACH = Util.findMethod(shadowLoadClass, "handleValForForEach", ForeachStatement.class, BlockScope.class);
        }

        public static boolean handleValForForEach(ForeachStatement foreachStatement, BlockScope blockScope) {
            return ((Boolean) Util.invokeMethod(HANDLE_VAL_FOR_FOR_EACH, foreachStatement, blockScope)).booleanValue();
        }

        public static boolean handleValForLocalDeclaration(LocalDeclaration localDeclaration, BlockScope blockScope) {
            return ((Boolean) Util.invokeMethod(HANDLE_VAL_FOR_LOCAL_DECLARATION, localDeclaration, blockScope)).booleanValue();
        }

        public static TypeBinding skipResolveInitializerIfAlreadyCalled(Expression expression, BlockScope blockScope) {
            return (TypeBinding) Util.invokeMethod(SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED, expression, blockScope);
        }

        public static TypeBinding skipResolveInitializerIfAlreadyCalled2(Expression expression, BlockScope blockScope, LocalDeclaration localDeclaration) {
            return (TypeBinding) Util.invokeMethod(SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED2, expression, blockScope, localDeclaration);
        }
    }

    /* loaded from: classes4.dex */
    public static final class ValPortal {
        private static final Method ADD_FINAL_AND_VAL_ANNOTATION_TO_SINGLE_VARIABLE_DECLARATION;
        private static final Method ADD_FINAL_AND_VAL_ANNOTATION_TO_VARIABLE_DECLARATION_STATEMENT;
        private static final Method COPY_INITIALIZATION_OF_FOR_EACH_ITERABLE;
        private static final Method COPY_INITIALIZATION_OF_LOCAL_DECLARATION;

        static {
            Class<?> shadowLoadClass = Util.shadowLoadClass("lombok.eclipse.agent.PatchValEclipsePortal");
            COPY_INITIALIZATION_OF_FOR_EACH_ITERABLE = Util.findMethod(shadowLoadClass, "copyInitializationOfForEachIterable", Object.class);
            COPY_INITIALIZATION_OF_LOCAL_DECLARATION = Util.findMethod(shadowLoadClass, "copyInitializationOfLocalDeclaration", Object.class);
            ADD_FINAL_AND_VAL_ANNOTATION_TO_VARIABLE_DECLARATION_STATEMENT = Util.findMethod(shadowLoadClass, "addFinalAndValAnnotationToVariableDeclarationStatement", Object.class, Object.class, Object.class);
            ADD_FINAL_AND_VAL_ANNOTATION_TO_SINGLE_VARIABLE_DECLARATION = Util.findMethod(shadowLoadClass, "addFinalAndValAnnotationToSingleVariableDeclaration", Object.class, Object.class, Object.class);
        }

        public static void addFinalAndValAnnotationToSingleVariableDeclaration(Object obj, Object obj2, Object obj3) {
            Util.invokeMethod(ADD_FINAL_AND_VAL_ANNOTATION_TO_SINGLE_VARIABLE_DECLARATION, obj, obj2, obj3);
        }

        public static void addFinalAndValAnnotationToVariableDeclarationStatement(Object obj, Object obj2, Object obj3) {
            Util.invokeMethod(ADD_FINAL_AND_VAL_ANNOTATION_TO_VARIABLE_DECLARATION_STATEMENT, obj, obj2, obj3);
        }

        public static void copyInitializationOfForEachIterable(Object obj) {
            Util.invokeMethod(COPY_INITIALIZATION_OF_FOR_EACH_ITERABLE, obj);
        }

        public static void copyInitializationOfLocalDeclaration(Object obj) {
            Util.invokeMethod(COPY_INITIALIZATION_OF_LOCAL_DECLARATION, obj);
        }
    }

    PatchFixesHider() {
    }

    /* loaded from: classes4.dex */
    public static final class PatchFixes {
        public static final int ALREADY_PROCESSED_FLAG = 8388608;

        public static void addAnnotations(List<Annotation> list, StringBuilder sb) {
            Iterator<Annotation> it2 = list.iterator();
            while (it2.hasNext()) {
                SingleMemberAnnotation singleMemberAnnotation = (Annotation) it2.next();
                ArrayList<String> arrayList = new ArrayList();
                if (singleMemberAnnotation.isSingleMemberAnnotation()) {
                    arrayList.add(singleMemberAnnotation.getValue().toString());
                } else if (singleMemberAnnotation.isNormalAnnotation()) {
                    for (Object obj : ((NormalAnnotation) singleMemberAnnotation).values()) {
                        arrayList.add(obj.toString());
                    }
                }
                sb.append("@");
                sb.append(singleMemberAnnotation.resolveTypeBinding().getQualifiedName());
                if (!arrayList.isEmpty()) {
                    sb.append("(");
                    boolean z = true;
                    for (String str : arrayList) {
                        if (!z) {
                            sb.append(", ");
                        }
                        z = false;
                        sb.append('\"');
                        sb.append(str);
                        sb.append('\"');
                    }
                    sb.append(")");
                }
                sb.append(" ");
            }
        }

        public static boolean checkBit24(Object obj) throws Exception {
            return (((Integer) obj.getClass().getField("bits").get(obj)).intValue() & 8388608) != 0;
        }

        public static org.eclipse.jdt.internal.compiler.ast.Annotation[] convertAnnotations(org.eclipse.jdt.internal.compiler.ast.Annotation[] annotationArr, IAnnotatable iAnnotatable) {
            boolean z;
            try {
                IAnnotation[] annotations = iAnnotatable.getAnnotations();
                if (annotationArr == null) {
                    return null;
                }
                int i = 0;
                for (int i2 = 0; i2 < annotationArr.length; i2++) {
                    String str = new String(annotationArr[i2].type.getLastToken());
                    int length = annotations.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            z = false;
                            break;
                        }
                        String elementName = annotations[i3].getElementName();
                        int lastIndexOf = elementName.lastIndexOf(46);
                        if (lastIndexOf > -1) {
                            elementName = elementName.substring(lastIndexOf + 1);
                        }
                        if (elementName.equals(str)) {
                            z = true;
                            break;
                        }
                        i3++;
                    }
                    if (!z) {
                        annotationArr[i2] = null;
                    } else {
                        i++;
                    }
                }
                if (i >= annotationArr.length) {
                    return annotationArr;
                }
                org.eclipse.jdt.internal.compiler.ast.Annotation[] annotationArr2 = new org.eclipse.jdt.internal.compiler.ast.Annotation[i];
                int i4 = 0;
                for (int i5 = 0; i5 < annotationArr.length; i5++) {
                    if (annotationArr[i5] != null) {
                        annotationArr2[i4] = annotationArr[i5];
                        i4++;
                    }
                }
                return annotationArr2;
            } catch (Exception unused) {
                return annotationArr;
            }
        }

        public static SearchResultGroup[] createFakeSearchResult(SearchResultGroup[] searchResultGroupArr, Object obj) throws Exception {
            Field declaredField;
            if ((searchResultGroupArr == null || searchResultGroupArr.length == 0) && (declaredField = obj.getClass().getDeclaredField("fField")) != null) {
                declaredField.setAccessible(true);
                return ((SourceField) declaredField.get(obj)).getDeclaringType().getAnnotation("Data") != null ? new SearchResultGroup[]{new SearchResultGroup((IResource) null, new SearchMatch[1])} : searchResultGroupArr;
            }
            return searchResultGroupArr;
        }

        public static AbstractTypeDeclaration findTypeDeclaration(IType iType, List<?> list) {
            for (Object obj : list) {
                if (obj instanceof AbstractTypeDeclaration) {
                    AbstractTypeDeclaration abstractTypeDeclaration = (AbstractTypeDeclaration) obj;
                    if (abstractTypeDeclaration.getName().toString().equals(iType.getElementName())) {
                        return abstractTypeDeclaration;
                    }
                }
            }
            return null;
        }

        public static int fixRetrieveEllipsisStartPosition(int i, int i2) {
            return i == -1 ? i2 : i;
        }

        public static int fixRetrieveIdentifierEndPosition(int i, int i2, int i3) {
            return (i != -1 && i >= i2) ? i : i3;
        }

        public static int fixRetrieveRightBraceOrSemiColonPosition(int i, int i2) {
            return i == -1 ? i2 : i;
        }

        public static int fixRetrieveRightBraceOrSemiColonPosition(int i, AbstractMethodDeclaration abstractMethodDeclaration) {
            if (i != -1 || abstractMethodDeclaration == null) {
                return i;
            }
            if (!(EclipseAugments.ASTNode_generatedBy.get(abstractMethodDeclaration) != null)) {
                return -1;
            }
            return abstractMethodDeclaration.declarationSourceEnd;
        }

        public static int fixRetrieveStartingCatchPosition(int i, int i2) {
            return i == -1 ? i2 : i;
        }

        public static MethodDeclaration getRealMethodDeclarationNode(IMethod iMethod, CompilationUnit compilationUnit) throws JavaModelException {
            MethodDeclaration methodDeclarationNode = ASTNodeSearchUtil.getMethodDeclarationNode(iMethod, compilationUnit);
            if (isGenerated((ASTNode) methodDeclarationNode)) {
                Stack stack = new Stack();
                for (IType declaringType = iMethod.getDeclaringType(); declaringType != null; declaringType = declaringType.getDeclaringType()) {
                    stack.push(declaringType);
                }
                AbstractTypeDeclaration findTypeDeclaration = findTypeDeclaration((IType) stack.pop(), compilationUnit.types());
                while (!stack.isEmpty() && findTypeDeclaration != null) {
                    findTypeDeclaration = findTypeDeclaration((IType) stack.pop(), findTypeDeclaration.bodyDeclarations());
                }
                if (stack.isEmpty() && findTypeDeclaration != null) {
                    String elementName = iMethod.getElementName();
                    for (Object obj : findTypeDeclaration.bodyDeclarations()) {
                        if (obj instanceof MethodDeclaration) {
                            MethodDeclaration methodDeclaration = (MethodDeclaration) obj;
                            if (methodDeclaration.getName().toString().equals(elementName)) {
                                return methodDeclaration;
                            }
                        }
                    }
                }
            }
            return methodDeclarationNode;
        }

        public static String getRealMethodDeclarationSource(String str, Object obj, MethodDeclaration methodDeclaration) throws Exception {
            if (!isGenerated((ASTNode) methodDeclaration)) {
                return str;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : methodDeclaration.modifiers()) {
                if (obj2 instanceof Annotation) {
                    Annotation annotation = (Annotation) obj2;
                    String qualifiedName = annotation.resolveTypeBinding().getQualifiedName();
                    if (!"java.lang.Override".equals(qualifiedName) && !"java.lang.SuppressWarnings".equals(qualifiedName)) {
                        arrayList.add(annotation);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            addAnnotations(arrayList, sb);
            if (((Boolean) obj.getClass().getDeclaredField("fPublic").get(obj)).booleanValue()) {
                sb.append("public ");
            }
            if (((Boolean) obj.getClass().getDeclaredField("fAbstract").get(obj)).booleanValue()) {
                sb.append("abstract ");
            }
            sb.append(methodDeclaration.getReturnType2().toString());
            sb.append(" ");
            sb.append(methodDeclaration.getName().getFullyQualifiedName());
            sb.append("(");
            boolean z = true;
            for (Object obj3 : methodDeclaration.parameters()) {
                if (!z) {
                    sb.append(", ");
                }
                z = false;
                sb.append(obj3);
            }
            sb.append(");");
            return sb.toString();
        }

        public static int getSourceEndFixed(int i, org.eclipse.jdt.internal.compiler.ast.ASTNode aSTNode) throws Exception {
            org.eclipse.jdt.internal.compiler.ast.ASTNode aSTNode2;
            return (i != -1 || (aSTNode2 = (org.eclipse.jdt.internal.compiler.ast.ASTNode) aSTNode.getClass().getField("$generatedBy").get(aSTNode)) == null) ? i : aSTNode2.sourceEnd;
        }

        public static int getTokenEndOffsetFixed(TokenScanner tokenScanner, int i, int i2, Object obj) throws CoreException {
            boolean z;
            try {
                z = ((Boolean) obj.getClass().getField("$isGenerated").get(obj)).booleanValue();
            } catch (Exception unused) {
                z = false;
            }
            if (z) {
                return -1;
            }
            return tokenScanner.getTokenEndOffset(i, i2);
        }

        public static boolean isGenerated(ASTNode aSTNode) {
            boolean z;
            try {
                boolean booleanValue = ((Boolean) aSTNode.getClass().getField("$isGenerated").get(aSTNode)).booleanValue();
                if (booleanValue) {
                    return booleanValue;
                }
                try {
                    return (aSTNode.getParent() == null || !(aSTNode.getParent() instanceof QualifiedName)) ? booleanValue : isGenerated(aSTNode.getParent());
                } catch (Exception unused) {
                    return z;
                }
            } catch (Exception unused2) {
                return false;
            }
        }

        public static boolean isListRewriteOnGeneratedNode(ListRewrite listRewrite) {
            return isGenerated(listRewrite.getParent());
        }

        public static RewriteEvent[] listRewriteHandleGeneratedMethods(RewriteEvent rewriteEvent) {
            RewriteEvent[] children = rewriteEvent.getChildren();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (RewriteEvent rewriteEvent2 : children) {
                if (isGenerated((ASTNode) rewriteEvent2.getOriginalValue())) {
                    boolean z = rewriteEvent2.getChangeKind() == 4 || rewriteEvent2.getChangeKind() == 2;
                    boolean z2 = rewriteEvent2.getOriginalValue() instanceof MethodDeclaration;
                    if (z && z2 && rewriteEvent2.getNewValue() != null) {
                        arrayList2.add(new NodeRewriteEvent((Object) null, rewriteEvent2.getNewValue()));
                    }
                } else {
                    arrayList.add(rewriteEvent2);
                }
            }
            arrayList.addAll(arrayList2);
            return (RewriteEvent[]) arrayList.toArray(new RewriteEvent[0]);
        }

        public static SearchMatch[] removeGenerated(SearchMatch[] searchMatchArr) {
            ArrayList arrayList = new ArrayList();
            for (SearchMatch searchMatch : searchMatchArr) {
                if (!(searchMatch.getElement() instanceof IField) || ((IField) searchMatch.getElement()).getAnnotation("Generated") == null) {
                    arrayList.add(searchMatch);
                }
            }
            return (SearchMatch[]) arrayList.toArray(new SearchMatch[0]);
        }

        public static IMethod[] removeGeneratedMethods(IMethod[] iMethodArr) throws Exception {
            ArrayList arrayList = new ArrayList();
            for (IMethod iMethod : iMethodArr) {
                if (iMethod.getNameRange().getLength() > 0 && !iMethod.getNameRange().equals(iMethod.getSourceRange())) {
                    arrayList.add(iMethod);
                }
            }
            return arrayList.size() == iMethodArr.length ? iMethodArr : (IMethod[]) arrayList.toArray(new IMethod[0]);
        }

        public static List removeGeneratedNodes(List list) {
            try {
                ArrayList arrayList = new ArrayList(list.size());
                for (Object obj : list) {
                    if (!isGenerated((ASTNode) obj)) {
                        arrayList.add(obj);
                    }
                }
                return arrayList;
            } catch (Exception unused) {
                return list;
            }
        }

        public static SimpleName[] removeGeneratedSimpleNames(SimpleName[] simpleNameArr) throws Exception {
            Field field = SimpleName.class.getField("$isGenerated");
            int i = 0;
            for (int i2 = 0; i2 < simpleNameArr.length; i2++) {
                if (simpleNameArr[i2] == null || !((Boolean) field.get(simpleNameArr[i2])).booleanValue()) {
                    i++;
                }
            }
            if (i == simpleNameArr.length) {
                return simpleNameArr;
            }
            SimpleName[] simpleNameArr2 = new SimpleName[i];
            int i3 = 0;
            for (int i4 = 0; i4 < simpleNameArr.length; i4++) {
                if (simpleNameArr[i4] == null || !((Boolean) field.get(simpleNameArr[i4])).booleanValue()) {
                    simpleNameArr2[i3] = simpleNameArr[i4];
                    i3++;
                }
            }
            return simpleNameArr2;
        }

        public static boolean returnFalse(Object obj) {
            return false;
        }

        public static boolean returnTrue(Object obj) {
            return true;
        }

        public static void setIsGeneratedFlag(ASTNode aSTNode, org.eclipse.jdt.internal.compiler.ast.ASTNode aSTNode2) throws Exception {
            if (aSTNode2 == null || aSTNode == null) {
                return;
            }
            if (!(EclipseAugments.ASTNode_generatedBy.get(aSTNode2) != null)) {
                return;
            }
            aSTNode.getClass().getField("$isGenerated").set(aSTNode, true);
        }

        public static void setIsGeneratedFlagForName(Name name, Object obj) throws Exception {
            if (obj instanceof org.eclipse.jdt.internal.compiler.ast.ASTNode) {
                if (!(EclipseAugments.ASTNode_generatedBy.get((org.eclipse.jdt.internal.compiler.ast.ASTNode) obj) != null)) {
                    return;
                }
                name.getClass().getField("$isGenerated").set(name, true);
            }
        }

        public static boolean skipRewritingGeneratedNodes(ASTNode aSTNode) throws Exception {
            return ((Boolean) aSTNode.getClass().getField("$isGenerated").get(aSTNode)).booleanValue();
        }

        public static int fixRetrieveRightBraceOrSemiColonPosition(int i, FieldDeclaration fieldDeclaration) {
            if (i != -1 || fieldDeclaration == null) {
                return i;
            }
            if (!(EclipseAugments.ASTNode_generatedBy.get(fieldDeclaration) != null)) {
                return -1;
            }
            return fieldDeclaration.declarationSourceEnd;
        }

        public static boolean isGenerated(org.eclipse.jdt.internal.compiler.ast.ASTNode aSTNode) {
            try {
                return aSTNode.getClass().getField("$generatedBy").get(aSTNode) != null;
            } catch (Exception unused) {
                return false;
            }
        }
    }
}
