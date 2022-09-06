package com.fasterxml.jackson.databind;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes2.dex */
public abstract class DatabindContext {
    private static final int MAX_ERROR_STR_LEN = 500;

    private JavaType _resolveAndValidateGeneric(JavaType javaType, String str, PolymorphicTypeValidator polymorphicTypeValidator, int i) throws JsonMappingException {
        MapperConfig<?> mo7051getConfig = mo7051getConfig();
        PolymorphicTypeValidator.Validity validateSubClassName = polymorphicTypeValidator.validateSubClassName(mo7051getConfig, javaType, str.substring(0, i));
        if (validateSubClassName == PolymorphicTypeValidator.Validity.DENIED) {
            return (JavaType) _throwSubtypeNameNotAllowed(javaType, str, polymorphicTypeValidator);
        }
        JavaType constructFromCanonical = getTypeFactory().constructFromCanonical(str);
        if (!constructFromCanonical.isTypeOrSubTypeOf(javaType.getRawClass())) {
            return (JavaType) _throwNotASubtype(javaType, str);
        }
        return (validateSubClassName == PolymorphicTypeValidator.Validity.ALLOWED || polymorphicTypeValidator.validateSubType(mo7051getConfig, javaType, constructFromCanonical) == PolymorphicTypeValidator.Validity.ALLOWED) ? constructFromCanonical : (JavaType) _throwSubtypeClassNotAllowed(javaType, str, polymorphicTypeValidator);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String _colonConcat(String str, String str2) {
        return str2 == null ? str : GeneratedOutlineSupport1.outline75(str, RealTimeTextConstants.COLON_SPACE, str2);
    }

    protected String _desc(String str) {
        return str == null ? "[N/A]" : _truncate(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String _format(String str, Object... objArr) {
        return objArr.length > 0 ? String.format(str, objArr) : str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String _quotedString(String str) {
        return str == null ? "[N/A]" : String.format("\"%s\"", _truncate(str));
    }

    protected <T> T _throwNotASubtype(JavaType javaType, String str) throws JsonMappingException {
        throw invalidTypeIdException(javaType, str, "Not a subtype");
    }

    protected <T> T _throwSubtypeClassNotAllowed(JavaType javaType, String str, PolymorphicTypeValidator polymorphicTypeValidator) throws JsonMappingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Configured `PolymorphicTypeValidator` (of type ");
        outline107.append(ClassUtil.classNameOf(polymorphicTypeValidator));
        outline107.append(") denied resolution");
        throw invalidTypeIdException(javaType, str, outline107.toString());
    }

    protected <T> T _throwSubtypeNameNotAllowed(JavaType javaType, String str, PolymorphicTypeValidator polymorphicTypeValidator) throws JsonMappingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Configured `PolymorphicTypeValidator` (of type ");
        outline107.append(ClassUtil.classNameOf(polymorphicTypeValidator));
        outline107.append(") denied resolution");
        throw invalidTypeIdException(javaType, str, outline107.toString());
    }

    protected final String _truncate(String str) {
        if (str == null) {
            return "";
        }
        if (str.length() <= 500) {
            return str;
        }
        return str.substring(0, 500) + "]...[" + str.substring(str.length() - 500);
    }

    public abstract boolean canOverrideAccessModifiers();

    public abstract JavaType constructSpecializedType(JavaType javaType, Class<?> cls);

    public JavaType constructType(Type type) {
        if (type == null) {
            return null;
        }
        return getTypeFactory().constructType(type);
    }

    public Converter<Object, Object> converterInstance(Annotated annotated, Object obj) throws JsonMappingException {
        Converter<?, ?> converter = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Converter) {
            return (Converter) obj;
        }
        if (obj instanceof Class) {
            Class<?> cls = (Class) obj;
            if (cls == Converter.None.class || ClassUtil.isBogusClass(cls)) {
                return null;
            }
            if (Converter.class.isAssignableFrom(cls)) {
                MapperConfig<?> mo7051getConfig = mo7051getConfig();
                HandlerInstantiator handlerInstantiator = mo7051getConfig.getHandlerInstantiator();
                if (handlerInstantiator != null) {
                    converter = handlerInstantiator.converterInstance(mo7051getConfig, annotated, cls);
                }
                return converter == null ? (Converter) ClassUtil.createInstance(cls, mo7051getConfig.canOverrideAccessModifiers()) : converter;
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned Class "), "; expected Class<Converter>"));
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline46(obj, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned Converter definition of type "), "; expected type Converter or Class<Converter> instead"));
    }

    public abstract Class<?> getActiveView();

    public abstract AnnotationIntrospector getAnnotationIntrospector();

    public abstract Object getAttribute(Object obj);

    /* renamed from: getConfig */
    public abstract MapperConfig<?> mo7051getConfig();

    public abstract JsonFormat.Value getDefaultPropertyFormat(Class<?> cls);

    public abstract Locale getLocale();

    public abstract TimeZone getTimeZone();

    public abstract TypeFactory getTypeFactory();

    protected abstract JsonMappingException invalidTypeIdException(JavaType javaType, String str, String str2);

    public abstract boolean isEnabled(MapperFeature mapperFeature);

    public ObjectIdGenerator<?> objectIdGeneratorInstance(Annotated annotated, ObjectIdInfo objectIdInfo) throws JsonMappingException {
        Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
        MapperConfig<?> mo7051getConfig = mo7051getConfig();
        HandlerInstantiator handlerInstantiator = mo7051getConfig.getHandlerInstantiator();
        ObjectIdGenerator<?> objectIdGeneratorInstance = handlerInstantiator == null ? null : handlerInstantiator.objectIdGeneratorInstance(mo7051getConfig, annotated, generatorType);
        if (objectIdGeneratorInstance == null) {
            objectIdGeneratorInstance = (ObjectIdGenerator) ClassUtil.createInstance(generatorType, mo7051getConfig.canOverrideAccessModifiers());
        }
        return objectIdGeneratorInstance.forScope(objectIdInfo.getScope());
    }

    public ObjectIdResolver objectIdResolverInstance(Annotated annotated, ObjectIdInfo objectIdInfo) {
        Class<? extends ObjectIdResolver> resolverType = objectIdInfo.getResolverType();
        MapperConfig<?> mo7051getConfig = mo7051getConfig();
        HandlerInstantiator handlerInstantiator = mo7051getConfig.getHandlerInstantiator();
        ObjectIdResolver resolverIdGeneratorInstance = handlerInstantiator == null ? null : handlerInstantiator.resolverIdGeneratorInstance(mo7051getConfig, annotated, resolverType);
        return resolverIdGeneratorInstance == null ? (ObjectIdResolver) ClassUtil.createInstance(resolverType, mo7051getConfig.canOverrideAccessModifiers()) : resolverIdGeneratorInstance;
    }

    public abstract <T> T reportBadDefinition(JavaType javaType, String str) throws JsonMappingException;

    public <T> T reportBadDefinition(Class<?> cls, String str) throws JsonMappingException {
        return (T) reportBadDefinition(constructType(cls), str);
    }

    public JavaType resolveAndValidateSubType(JavaType javaType, String str, PolymorphicTypeValidator polymorphicTypeValidator) throws JsonMappingException {
        int indexOf = str.indexOf(60);
        if (indexOf > 0) {
            return _resolveAndValidateGeneric(javaType, str, polymorphicTypeValidator, indexOf);
        }
        MapperConfig<?> mo7051getConfig = mo7051getConfig();
        PolymorphicTypeValidator.Validity validateSubClassName = polymorphicTypeValidator.validateSubClassName(mo7051getConfig, javaType, str);
        if (validateSubClassName == PolymorphicTypeValidator.Validity.DENIED) {
            return (JavaType) _throwSubtypeNameNotAllowed(javaType, str, polymorphicTypeValidator);
        }
        try {
            Class<?> findClass = getTypeFactory().findClass(str);
            if (!javaType.isTypeOrSuperTypeOf(findClass)) {
                return (JavaType) _throwNotASubtype(javaType, str);
            }
            JavaType constructSpecializedType = mo7051getConfig.getTypeFactory().constructSpecializedType(javaType, findClass);
            return (validateSubClassName != PolymorphicTypeValidator.Validity.INDETERMINATE || polymorphicTypeValidator.validateSubType(mo7051getConfig, javaType, constructSpecializedType) == PolymorphicTypeValidator.Validity.ALLOWED) ? constructSpecializedType : (JavaType) _throwSubtypeClassNotAllowed(javaType, str, polymorphicTypeValidator);
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Exception e) {
            throw invalidTypeIdException(javaType, str, String.format("problem: (%s) %s", e.getClass().getName(), ClassUtil.exceptionMessage(e)));
        }
    }

    public JavaType resolveSubType(JavaType javaType, String str) throws JsonMappingException {
        if (str.indexOf(60) > 0) {
            JavaType constructFromCanonical = getTypeFactory().constructFromCanonical(str);
            if (constructFromCanonical.isTypeOrSubTypeOf(javaType.getRawClass())) {
                return constructFromCanonical;
            }
        } else {
            try {
                Class<?> findClass = getTypeFactory().findClass(str);
                if (javaType.isTypeOrSuperTypeOf(findClass)) {
                    return getTypeFactory().constructSpecializedType(javaType, findClass);
                }
            } catch (ClassNotFoundException unused) {
                return null;
            } catch (Exception e) {
                throw invalidTypeIdException(javaType, str, String.format("problem: (%s) %s", e.getClass().getName(), ClassUtil.exceptionMessage(e)));
            }
        }
        throw invalidTypeIdException(javaType, str, "Not a subtype");
    }

    /* renamed from: setAttribute */
    public abstract DatabindContext mo7052setAttribute(Object obj, Object obj2);
}
