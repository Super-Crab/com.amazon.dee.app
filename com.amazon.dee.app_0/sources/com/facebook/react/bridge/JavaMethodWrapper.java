package com.facebook.react.bridge;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.NativeModule;
import com.facebook.systrace.SystraceMessage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes2.dex */
public class JavaMethodWrapper implements NativeModule.NativeMethod {
    @Nullable
    private ArgumentExtractor[] mArgumentExtractors;
    @Nullable
    private Object[] mArguments;
    private boolean mArgumentsProcessed = false;
    @Nullable
    private int mJSArgumentsNeeded;
    private final Method mMethod;
    private final JavaModuleWrapper mModuleWrapper;
    private final int mParamLength;
    private final Class[] mParameterTypes;
    @Nullable
    private String mSignature;
    private String mType;
    private static final ArgumentExtractor<Boolean> ARGUMENT_EXTRACTOR_BOOLEAN = new ArgumentExtractor<Boolean>() { // from class: com.facebook.react.bridge.JavaMethodWrapper.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        /* renamed from: extractArgument */
        public Boolean mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return Boolean.valueOf(readableArray.getBoolean(i));
        }
    };
    private static final ArgumentExtractor<Double> ARGUMENT_EXTRACTOR_DOUBLE = new ArgumentExtractor<Double>() { // from class: com.facebook.react.bridge.JavaMethodWrapper.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        /* renamed from: extractArgument */
        public Double mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return Double.valueOf(readableArray.getDouble(i));
        }
    };
    private static final ArgumentExtractor<Float> ARGUMENT_EXTRACTOR_FLOAT = new ArgumentExtractor<Float>() { // from class: com.facebook.react.bridge.JavaMethodWrapper.3
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        /* renamed from: extractArgument */
        public Float mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return Float.valueOf((float) readableArray.getDouble(i));
        }
    };
    private static final ArgumentExtractor<Integer> ARGUMENT_EXTRACTOR_INTEGER = new ArgumentExtractor<Integer>() { // from class: com.facebook.react.bridge.JavaMethodWrapper.4
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        /* renamed from: extractArgument */
        public Integer mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return Integer.valueOf((int) readableArray.getDouble(i));
        }
    };
    private static final ArgumentExtractor<String> ARGUMENT_EXTRACTOR_STRING = new ArgumentExtractor<String>() { // from class: com.facebook.react.bridge.JavaMethodWrapper.5
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        /* renamed from: extractArgument  reason: collision with other method in class */
        public String mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return readableArray.getString(i);
        }
    };
    private static final ArgumentExtractor<ReadableArray> ARGUMENT_EXTRACTOR_ARRAY = new ArgumentExtractor<ReadableArray>() { // from class: com.facebook.react.bridge.JavaMethodWrapper.6
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        /* renamed from: extractArgument */
        public ReadableArray mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return readableArray.mo6943getArray(i);
        }
    };
    private static final ArgumentExtractor<Dynamic> ARGUMENT_EXTRACTOR_DYNAMIC = new ArgumentExtractor<Dynamic>() { // from class: com.facebook.react.bridge.JavaMethodWrapper.7
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        /* renamed from: extractArgument */
        public Dynamic mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return DynamicFromArray.create(readableArray, i);
        }
    };
    private static final ArgumentExtractor<ReadableMap> ARGUMENT_EXTRACTOR_MAP = new ArgumentExtractor<ReadableMap>() { // from class: com.facebook.react.bridge.JavaMethodWrapper.8
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        /* renamed from: extractArgument */
        public ReadableMap mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return readableArray.mo6944getMap(i);
        }
    };
    private static final ArgumentExtractor<Callback> ARGUMENT_EXTRACTOR_CALLBACK = new ArgumentExtractor<Callback>() { // from class: com.facebook.react.bridge.JavaMethodWrapper.9
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        @Nullable
        /* renamed from: extractArgument */
        public Callback mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            if (readableArray.isNull(i)) {
                return null;
            }
            return new CallbackImpl(jSInstance, (int) readableArray.getDouble(i));
        }
    };
    private static final ArgumentExtractor<Promise> ARGUMENT_EXTRACTOR_PROMISE = new ArgumentExtractor<Promise>() { // from class: com.facebook.react.bridge.JavaMethodWrapper.10
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        public int getJSArgumentsNeeded() {
            return 2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.facebook.react.bridge.JavaMethodWrapper.ArgumentExtractor
        /* renamed from: extractArgument */
        public Promise mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return new PromiseImpl((Callback) JavaMethodWrapper.ARGUMENT_EXTRACTOR_CALLBACK.mo6942extractArgument(jSInstance, readableArray, i), (Callback) JavaMethodWrapper.ARGUMENT_EXTRACTOR_CALLBACK.mo6942extractArgument(jSInstance, readableArray, i + 1));
        }
    };
    private static final boolean DEBUG = PrinterHolder.getPrinter().shouldDisplayLogMessage(ReactDebugOverlayTags.BRIDGE_CALLS);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class ArgumentExtractor<T> {
        private ArgumentExtractor() {
        }

        @Nullable
        /* renamed from: extractArgument */
        public abstract T mo6942extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i);

        public int getJSArgumentsNeeded() {
            return 1;
        }
    }

    public JavaMethodWrapper(JavaModuleWrapper javaModuleWrapper, Method method, boolean z) {
        this.mType = BaseJavaModule.METHOD_TYPE_ASYNC;
        this.mModuleWrapper = javaModuleWrapper;
        this.mMethod = method;
        this.mMethod.setAccessible(true);
        this.mParameterTypes = this.mMethod.getParameterTypes();
        Class[] clsArr = this.mParameterTypes;
        this.mParamLength = clsArr.length;
        if (z) {
            this.mType = BaseJavaModule.METHOD_TYPE_SYNC;
            return;
        }
        int i = this.mParamLength;
        if (i <= 0 || clsArr[i - 1] != Promise.class) {
            return;
        }
        this.mType = BaseJavaModule.METHOD_TYPE_PROMISE;
    }

    private ArgumentExtractor[] buildArgumentExtractors(Class[] clsArr) {
        ArgumentExtractor[] argumentExtractorArr = new ArgumentExtractor[clsArr.length];
        for (int i = 0; i < clsArr.length; i += argumentExtractorArr[i].getJSArgumentsNeeded()) {
            Class cls = clsArr[i];
            if (cls != Boolean.class && cls != Boolean.TYPE) {
                if (cls != Integer.class && cls != Integer.TYPE) {
                    if (cls != Double.class && cls != Double.TYPE) {
                        if (cls != Float.class && cls != Float.TYPE) {
                            if (cls == String.class) {
                                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_STRING;
                            } else if (cls == Callback.class) {
                                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_CALLBACK;
                            } else if (cls == Promise.class) {
                                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_PROMISE;
                                boolean z = true;
                                if (i != clsArr.length - 1) {
                                    z = false;
                                }
                                Assertions.assertCondition(z, "Promise must be used as last parameter only");
                            } else if (cls == ReadableMap.class) {
                                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_MAP;
                            } else if (cls == ReadableArray.class) {
                                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_ARRAY;
                            } else if (cls == Dynamic.class) {
                                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_DYNAMIC;
                            } else {
                                throw new RuntimeException(GeneratedOutlineSupport1.outline39(cls, GeneratedOutlineSupport1.outline107("Got unknown argument class: ")));
                            }
                        } else {
                            argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_FLOAT;
                        }
                    } else {
                        argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_DOUBLE;
                    }
                } else {
                    argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_INTEGER;
                }
            } else {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_BOOLEAN;
            }
        }
        return argumentExtractorArr;
    }

    private String buildSignature(Method method, Class[] clsArr, boolean z) {
        StringBuilder sb = new StringBuilder(clsArr.length + 2);
        if (z) {
            sb.append(returnTypeToChar(method.getReturnType()));
            sb.append('.');
        } else {
            sb.append("v.");
        }
        for (int i = 0; i < clsArr.length; i++) {
            Class cls = clsArr[i];
            if (cls == Promise.class) {
                boolean z2 = true;
                if (i != clsArr.length - 1) {
                    z2 = false;
                }
                Assertions.assertCondition(z2, "Promise must be used as last parameter only");
            }
            sb.append(paramTypeToChar(cls));
        }
        return sb.toString();
    }

    private int calculateJSArgumentsNeeded() {
        int i = 0;
        for (ArgumentExtractor argumentExtractor : (ArgumentExtractor[]) Assertions.assertNotNull(this.mArgumentExtractors)) {
            i += argumentExtractor.getJSArgumentsNeeded();
        }
        return i;
    }

    private static char commonTypeToChar(Class cls) {
        if (cls == Boolean.TYPE) {
            return 'z';
        }
        if (cls == Boolean.class) {
            return Matrix.MATRIX_TYPE_ZERO;
        }
        if (cls == Integer.TYPE) {
            return 'i';
        }
        if (cls == Integer.class) {
            return 'I';
        }
        if (cls == Double.TYPE) {
            return 'd';
        }
        if (cls == Double.class) {
            return 'D';
        }
        if (cls == Float.TYPE) {
            return 'f';
        }
        if (cls == Float.class) {
            return 'F';
        }
        return cls == String.class ? 'S' : (char) 0;
    }

    private String getAffectedRange(int i, int i2) {
        if (i2 > 1) {
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("", i, ProcessIdUtil.DEFAULT_PROCESSID);
            outline109.append((i + i2) - 1);
            return outline109.toString();
        }
        return GeneratedOutlineSupport1.outline49("", i);
    }

    private static char paramTypeToChar(Class cls) {
        char commonTypeToChar = commonTypeToChar(cls);
        if (commonTypeToChar != 0) {
            return commonTypeToChar;
        }
        if (cls == Callback.class) {
            return 'X';
        }
        if (cls == Promise.class) {
            return 'P';
        }
        if (cls == ReadableMap.class) {
            return 'M';
        }
        if (cls == ReadableArray.class) {
            return 'A';
        }
        if (cls != Dynamic.class) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline39(cls, GeneratedOutlineSupport1.outline107("Got unknown param class: ")));
        }
        return 'Y';
    }

    private void processArguments() {
        if (this.mArgumentsProcessed) {
            return;
        }
        SystraceMessage.Builder beginSection = SystraceMessage.beginSection(0L, "processArguments");
        beginSection.arg(MetricsConstants.NativeFetch.METHOD, this.mModuleWrapper.getName() + "." + this.mMethod.getName()).flush();
        try {
            this.mArgumentsProcessed = true;
            this.mArgumentExtractors = buildArgumentExtractors(this.mParameterTypes);
            this.mSignature = buildSignature(this.mMethod, this.mParameterTypes, this.mType.equals(BaseJavaModule.METHOD_TYPE_SYNC));
            this.mArguments = new Object[this.mParameterTypes.length];
            this.mJSArgumentsNeeded = calculateJSArgumentsNeeded();
        } finally {
            SystraceMessage.endSection(0L).flush();
        }
    }

    private static char returnTypeToChar(Class cls) {
        char commonTypeToChar = commonTypeToChar(cls);
        if (commonTypeToChar != 0) {
            return commonTypeToChar;
        }
        if (cls == Void.TYPE) {
            return 'v';
        }
        if (cls == WritableMap.class) {
            return 'M';
        }
        if (cls != WritableArray.class) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline39(cls, GeneratedOutlineSupport1.outline107("Got unknown return class: ")));
        }
        return 'A';
    }

    public Method getMethod() {
        return this.mMethod;
    }

    public String getSignature() {
        if (!this.mArgumentsProcessed) {
            processArguments();
        }
        return (String) Assertions.assertNotNull(this.mSignature);
    }

    @Override // com.facebook.react.bridge.NativeModule.NativeMethod
    public String getType() {
        return this.mType;
    }

    @Override // com.facebook.react.bridge.NativeModule.NativeMethod
    public void invoke(JSInstance jSInstance, ReadableArray readableArray) {
        String str = this.mModuleWrapper.getName() + "." + this.mMethod.getName();
        SystraceMessage.beginSection(0L, "callJavaModuleMethod").arg(MetricsConstants.NativeFetch.METHOD, str).flush();
        if (DEBUG) {
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.BRIDGE_CALLS, "JS->Java: %s.%s()", this.mModuleWrapper.getName(), this.mMethod.getName());
        }
        try {
            if (!this.mArgumentsProcessed) {
                processArguments();
            }
            if (this.mArguments != null && this.mArgumentExtractors != null) {
                if (this.mJSArgumentsNeeded == readableArray.size()) {
                    int i = 0;
                    for (int i2 = 0; i2 < this.mArgumentExtractors.length; i2++) {
                        try {
                            this.mArguments[i2] = this.mArgumentExtractors[i2].mo6942extractArgument(jSInstance, readableArray, i);
                            i += this.mArgumentExtractors[i2].getJSArgumentsNeeded();
                        } catch (UnexpectedNativeTypeException e) {
                            throw new NativeArgumentsParseException(e.getMessage() + " (constructing arguments for " + str + " at argument index " + getAffectedRange(i, this.mArgumentExtractors[i2].getJSArgumentsNeeded()) + ")", e);
                        }
                    }
                    try {
                        this.mMethod.invoke(this.mModuleWrapper.getModule(), this.mArguments);
                        return;
                    } catch (IllegalAccessException e2) {
                        throw new RuntimeException("Could not invoke " + str, e2);
                    } catch (IllegalArgumentException e3) {
                        throw new RuntimeException("Could not invoke " + str, e3);
                    } catch (InvocationTargetException e4) {
                        if (e4.getCause() instanceof RuntimeException) {
                            throw ((RuntimeException) e4.getCause());
                        }
                        throw new RuntimeException("Could not invoke " + str, e4);
                    }
                }
                throw new NativeArgumentsParseException(str + " got " + readableArray.size() + " arguments, expected " + this.mJSArgumentsNeeded);
            }
            throw new Error("processArguments failed");
        } finally {
            SystraceMessage.endSection(0L).flush();
        }
    }
}
