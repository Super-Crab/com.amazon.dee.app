package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class AnnotatedCreatorCollector extends CollectorBase {
    private final boolean _collectAnnotations;
    private AnnotatedConstructor _defaultConstructor;
    private final TypeResolutionContext _typeContext;

    AnnotatedCreatorCollector(AnnotationIntrospector annotationIntrospector, TypeResolutionContext typeResolutionContext, boolean z) {
        super(annotationIntrospector);
        this._typeContext = typeResolutionContext;
        this._collectAnnotations = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private List<AnnotatedConstructor> _findPotentialConstructors(JavaType javaType, Class<?> cls) {
        ClassUtil.Ctor ctor;
        ArrayList arrayList;
        int size;
        List arrayList2;
        ClassUtil.Ctor[] constructors;
        ClassUtil.Ctor[] constructors2;
        if (!javaType.isEnumType()) {
            ctor = null;
            arrayList = null;
            for (ClassUtil.Ctor ctor2 : ClassUtil.getConstructors(javaType.getRawClass())) {
                if (isIncludableConstructor(ctor2.getConstructor())) {
                    if (ctor2.getParamCount() == 0) {
                        ctor = ctor2;
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(ctor2);
                    }
                }
            }
        } else {
            ctor = null;
            arrayList = null;
        }
        if (arrayList == null) {
            List emptyList = Collections.emptyList();
            if (ctor == null) {
                return emptyList;
            }
            arrayList2 = emptyList;
            size = 0;
        } else {
            size = arrayList.size();
            arrayList2 = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList2.add(null);
            }
        }
        if (cls != null) {
            MemberKey[] memberKeyArr = null;
            ClassUtil.Ctor ctor3 = ctor;
            for (ClassUtil.Ctor ctor4 : ClassUtil.getConstructors(cls)) {
                if (ctor4.getParamCount() == 0) {
                    if (ctor3 != null) {
                        this._defaultConstructor = constructDefaultConstructor(ctor3, ctor4);
                        ctor3 = null;
                    }
                } else if (arrayList != null) {
                    if (memberKeyArr == null) {
                        memberKeyArr = new MemberKey[size];
                        for (int i2 = 0; i2 < size; i2++) {
                            memberKeyArr[i2] = new MemberKey(((ClassUtil.Ctor) arrayList.get(i2)).getConstructor());
                        }
                    }
                    MemberKey memberKey = new MemberKey(ctor4.getConstructor());
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size) {
                            break;
                        } else if (memberKey.equals(memberKeyArr[i3])) {
                            arrayList2.set(i3, constructNonDefaultConstructor((ClassUtil.Ctor) arrayList.get(i3), ctor4));
                            break;
                        } else {
                            i3++;
                        }
                    }
                }
            }
            ctor = ctor3;
        }
        if (ctor != null) {
            this._defaultConstructor = constructDefaultConstructor(ctor, null);
        }
        for (int i4 = 0; i4 < size; i4++) {
            if (((AnnotatedConstructor) arrayList2.get(i4)) == null) {
                arrayList2.set(i4, constructNonDefaultConstructor((ClassUtil.Ctor) arrayList.get(i4), null));
            }
        }
        return arrayList2;
    }

    private List<AnnotatedMethod> _findPotentialFactories(TypeFactory typeFactory, JavaType javaType, Class<?> cls) {
        Method[] classMethods;
        Method[] declaredMethods;
        ArrayList arrayList = null;
        for (Method method : ClassUtil.getClassMethods(javaType.getRawClass())) {
            if (_isIncludableFactoryMethod(method)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(method);
            }
        }
        if (arrayList == null) {
            return Collections.emptyList();
        }
        TypeResolutionContext.Empty empty = new TypeResolutionContext.Empty(typeFactory);
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList2.add(null);
        }
        if (cls != null) {
            MemberKey[] memberKeyArr = null;
            for (Method method2 : cls.getDeclaredMethods()) {
                if (_isIncludableFactoryMethod(method2)) {
                    if (memberKeyArr == null) {
                        memberKeyArr = new MemberKey[size];
                        for (int i2 = 0; i2 < size; i2++) {
                            memberKeyArr[i2] = new MemberKey((Method) arrayList.get(i2));
                        }
                    }
                    MemberKey memberKey = new MemberKey(method2);
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size) {
                            break;
                        } else if (memberKey.equals(memberKeyArr[i3])) {
                            arrayList2.set(i3, constructFactoryCreator((Method) arrayList.get(i3), empty, method2));
                            break;
                        } else {
                            i3++;
                        }
                    }
                }
            }
        }
        for (int i4 = 0; i4 < size; i4++) {
            if (((AnnotatedMethod) arrayList2.get(i4)) == null) {
                Method method3 = (Method) arrayList.get(i4);
                arrayList2.set(i4, constructFactoryCreator(method3, MethodGenericTypeResolver.narrowMethodTypeParameters(method3, javaType, typeFactory, empty), null));
            }
        }
        return arrayList2;
    }

    private static boolean _isIncludableFactoryMethod(Method method) {
        return Modifier.isStatic(method.getModifiers()) && !method.isSynthetic();
    }

    private AnnotationMap[] collectAnnotations(Annotation[][] annotationArr, Annotation[][] annotationArr2) {
        if (this._collectAnnotations) {
            int length = annotationArr.length;
            AnnotationMap[] annotationMapArr = new AnnotationMap[length];
            for (int i = 0; i < length; i++) {
                AnnotationCollector collectAnnotations = collectAnnotations(AnnotationCollector.emptyCollector(), annotationArr[i]);
                if (annotationArr2 != null) {
                    collectAnnotations = collectAnnotations(collectAnnotations, annotationArr2[i]);
                }
                annotationMapArr[i] = collectAnnotations.asAnnotationMap();
            }
            return annotationMapArr;
        }
        return CollectorBase.NO_ANNOTATION_MAPS;
    }

    public static AnnotatedClass.Creators collectCreators(AnnotationIntrospector annotationIntrospector, TypeFactory typeFactory, TypeResolutionContext typeResolutionContext, JavaType javaType, Class<?> cls, boolean z) {
        return new AnnotatedCreatorCollector(annotationIntrospector, typeResolutionContext, z | (cls != null)).collect(typeFactory, javaType, cls);
    }

    private static boolean isIncludableConstructor(Constructor<?> constructor) {
        return !constructor.isSynthetic();
    }

    AnnotatedClass.Creators collect(TypeFactory typeFactory, JavaType javaType, Class<?> cls) {
        List<AnnotatedConstructor> _findPotentialConstructors = _findPotentialConstructors(javaType, cls);
        List<AnnotatedMethod> _findPotentialFactories = _findPotentialFactories(typeFactory, javaType, cls);
        if (this._collectAnnotations) {
            AnnotatedConstructor annotatedConstructor = this._defaultConstructor;
            if (annotatedConstructor != null && this._intr.hasIgnoreMarker(annotatedConstructor)) {
                this._defaultConstructor = null;
            }
            int size = _findPotentialConstructors.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else if (this._intr.hasIgnoreMarker(_findPotentialConstructors.get(size))) {
                    _findPotentialConstructors.remove(size);
                }
            }
            int size2 = _findPotentialFactories.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    break;
                } else if (this._intr.hasIgnoreMarker(_findPotentialFactories.get(size2))) {
                    _findPotentialFactories.remove(size2);
                }
            }
        }
        return new AnnotatedClass.Creators(this._defaultConstructor, _findPotentialConstructors, _findPotentialFactories);
    }

    protected AnnotatedConstructor constructDefaultConstructor(ClassUtil.Ctor ctor, ClassUtil.Ctor ctor2) {
        return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), collectAnnotations(ctor, ctor2), CollectorBase.NO_ANNOTATION_MAPS);
    }

    protected AnnotatedMethod constructFactoryCreator(Method method, TypeResolutionContext typeResolutionContext, Method method2) {
        int length = method.getParameterTypes().length;
        if (this._intr == null) {
            return new AnnotatedMethod(typeResolutionContext, method, CollectorBase._emptyAnnotationMap(), CollectorBase._emptyAnnotationMaps(length));
        }
        if (length == 0) {
            return new AnnotatedMethod(typeResolutionContext, method, collectAnnotations(method, method2), CollectorBase.NO_ANNOTATION_MAPS);
        }
        return new AnnotatedMethod(typeResolutionContext, method, collectAnnotations(method, method2), collectAnnotations(method.getParameterAnnotations(), method2 == null ? null : method2.getParameterAnnotations()));
    }

    protected AnnotatedConstructor constructNonDefaultConstructor(ClassUtil.Ctor ctor, ClassUtil.Ctor ctor2) {
        Annotation[][] annotationArr;
        int paramCount = ctor.getParamCount();
        if (this._intr == null) {
            return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), CollectorBase._emptyAnnotationMap(), CollectorBase._emptyAnnotationMaps(paramCount));
        }
        if (paramCount == 0) {
            return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), collectAnnotations(ctor, ctor2), CollectorBase.NO_ANNOTATION_MAPS);
        }
        Annotation[][] parameterAnnotations = ctor.getParameterAnnotations();
        Annotation[][] annotationArr2 = null;
        AnnotationMap[] collectAnnotations = null;
        if (paramCount != parameterAnnotations.length) {
            Class<?> declaringClass = ctor.getDeclaringClass();
            if (ClassUtil.isEnumType(declaringClass) && paramCount == parameterAnnotations.length + 2) {
                annotationArr = new Annotation[parameterAnnotations.length + 2];
                System.arraycopy(parameterAnnotations, 0, annotationArr, 2, parameterAnnotations.length);
                collectAnnotations = collectAnnotations(annotationArr, (Annotation[][]) null);
            } else if (!declaringClass.isMemberClass() || paramCount != parameterAnnotations.length + 1) {
                annotationArr = parameterAnnotations;
            } else {
                annotationArr = new Annotation[parameterAnnotations.length + 1];
                System.arraycopy(parameterAnnotations, 0, annotationArr, 1, parameterAnnotations.length);
                annotationArr[0] = CollectorBase.NO_ANNOTATIONS;
                collectAnnotations = collectAnnotations(annotationArr, (Annotation[][]) null);
            }
            if (collectAnnotations == null) {
                throw new IllegalStateException(String.format("Internal error: constructor for %s has mismatch: %d parameters; %d sets of annotations", ctor.getDeclaringClass().getName(), Integer.valueOf(paramCount), Integer.valueOf(annotationArr.length)));
            }
        } else {
            if (ctor2 != null) {
                annotationArr2 = ctor2.getParameterAnnotations();
            }
            collectAnnotations = collectAnnotations(parameterAnnotations, annotationArr2);
        }
        return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), collectAnnotations(ctor, ctor2), collectAnnotations);
    }

    private AnnotationMap collectAnnotations(ClassUtil.Ctor ctor, ClassUtil.Ctor ctor2) {
        if (this._collectAnnotations) {
            AnnotationCollector collectAnnotations = collectAnnotations(ctor.getDeclaredAnnotations());
            if (ctor2 != null) {
                collectAnnotations = collectAnnotations(collectAnnotations, ctor2.getDeclaredAnnotations());
            }
            return collectAnnotations.asAnnotationMap();
        }
        return CollectorBase._emptyAnnotationMap();
    }

    private final AnnotationMap collectAnnotations(AnnotatedElement annotatedElement, AnnotatedElement annotatedElement2) {
        AnnotationCollector collectAnnotations = collectAnnotations(annotatedElement.getDeclaredAnnotations());
        if (annotatedElement2 != null) {
            collectAnnotations = collectAnnotations(collectAnnotations, annotatedElement2.getDeclaredAnnotations());
        }
        return collectAnnotations.asAnnotationMap();
    }
}
