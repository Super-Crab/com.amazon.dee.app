package com.amazon.comms.log;

import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.annotation.Log;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;
@Aspect
/* loaded from: classes11.dex */
public class CommsLoggerAspect {
    private static /* synthetic */ Throwable ajc$initFailureCause;
    public static final /* synthetic */ CommsLoggerAspect ajc$perSingletonInstance = null;
    private final CommsLogger.CommsLoggerFactory mCommsLoggerFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.comms.log.CommsLoggerAspect$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$log$LogLevel = new int[LogLevel.values().length];

        static {
            try {
                $SwitchMap$com$amazon$comms$log$LogLevel[LogLevel.Error.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$comms$log$LogLevel[LogLevel.Warning.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$comms$log$LogLevel[LogLevel.Debug.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$comms$log$LogLevel[LogLevel.Verbose.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$comms$log$LogLevel[LogLevel.Info.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class LogDebugStrategy extends LogStrategy {
        protected LogDebugStrategy(CommsLogger commsLogger, String str, JoinPoint joinPoint) {
            super(commsLogger, str, joinPoint);
        }

        @Override // com.amazon.comms.log.CommsLoggerAspect.LogStrategy
        protected void log(String str) {
            this.mLogger.d(str);
        }

        @Override // com.amazon.comms.log.CommsLoggerAspect.LogStrategy
        protected void log(String str, Throwable th) {
            this.mLogger.d(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class LogErrorStrategy extends LogStrategy {
        protected LogErrorStrategy(CommsLoggerAspect commsLoggerAspect, CommsLogger commsLogger, String str, JoinPoint joinPoint) {
            this(commsLogger, str, joinPoint, null);
        }

        @Override // com.amazon.comms.log.CommsLoggerAspect.LogStrategy
        protected void log(String str) {
            this.mLogger.e(str);
        }

        protected LogErrorStrategy(CommsLogger commsLogger, String str, JoinPoint joinPoint, Throwable th) {
            super(commsLogger, str, joinPoint);
            this.mThrowable = th;
        }

        @Override // com.amazon.comms.log.CommsLoggerAspect.LogStrategy
        protected void log(String str, Throwable th) {
            this.mLogger.e(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class LogInfoStrategy extends LogStrategy {
        protected LogInfoStrategy(CommsLogger commsLogger, String str, JoinPoint joinPoint) {
            super(commsLogger, str, joinPoint);
        }

        @Override // com.amazon.comms.log.CommsLoggerAspect.LogStrategy
        protected void log(String str) {
            this.mLogger.i(str);
        }

        @Override // com.amazon.comms.log.CommsLoggerAspect.LogStrategy
        protected void log(String str, Throwable th) {
            this.mLogger.i(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public abstract class LogStrategy {
        private final JoinPoint mJoinPoint;
        protected final CommsLogger mLogger;
        private final String mMessage;
        protected Throwable mThrowable;

        protected LogStrategy(CommsLogger commsLogger, String str, JoinPoint joinPoint) {
            this.mLogger = commsLogger;
            this.mMessage = str;
            this.mJoinPoint = joinPoint;
        }

        private String getAnnotatedParamsAsAString() {
            Object[] args = this.mJoinPoint.getArgs();
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if (paramIsAnnotatedAtIndex(i)) {
                    if (args[i] instanceof Throwable) {
                        this.mThrowable = (Throwable) args[i];
                    } else {
                        arrayList.add(args[i]);
                    }
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(arrayList.get(i2));
            }
            return sb.toString();
        }

        private String getMessage() {
            String simpleName;
            StringBuilder sb = new StringBuilder();
            String annotatedParamsAsAString = getAnnotatedParamsAsAString();
            if (!this.mMessage.isEmpty()) {
                sb.append(this.mMessage);
                if (!annotatedParamsAsAString.isEmpty()) {
                    sb.append(RealTimeTextConstants.COLON_SPACE);
                    sb.append(annotatedParamsAsAString);
                }
            } else {
                Class<?> cls = this.mJoinPoint.getTarget().getClass();
                if (cls.isAnonymousClass()) {
                    simpleName = cls.getEnclosingClass().getSimpleName();
                } else {
                    simpleName = cls.getSimpleName();
                }
                String name = this.mJoinPoint.getSignature().getName();
                sb.append(simpleName);
                sb.append(".");
                sb.append(name);
                sb.append("(");
                sb.append(annotatedParamsAsAString);
                sb.append(")");
            }
            return sb.toString();
        }

        private boolean paramIsAnnotatedAtIndex(int i) {
            Annotation[] annotationArr;
            if (this.mJoinPoint.getSignature() instanceof ConstructorSignature) {
                annotationArr = ((ConstructorSignature) this.mJoinPoint.getSignature()).getConstructor().getParameterAnnotations()[i];
            } else {
                annotationArr = ((MethodSignature) this.mJoinPoint.getSignature()).getMethod().getParameterAnnotations()[i];
            }
            for (Annotation annotation : annotationArr) {
                if (annotation instanceof Log) {
                    return true;
                }
            }
            return false;
        }

        void apply() {
            String message = getMessage();
            Throwable th = this.mThrowable;
            if (th != null) {
                log(message, th);
            } else {
                log(message);
            }
        }

        protected abstract void log(String str);

        protected abstract void log(String str, Throwable th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class LogVerboseStrategy extends LogStrategy {
        protected LogVerboseStrategy(CommsLogger commsLogger, String str, JoinPoint joinPoint) {
            super(commsLogger, str, joinPoint);
        }

        @Override // com.amazon.comms.log.CommsLoggerAspect.LogStrategy
        protected void log(String str) {
            this.mLogger.v(str);
        }

        @Override // com.amazon.comms.log.CommsLoggerAspect.LogStrategy
        protected void log(String str, Throwable th) {
            this.mLogger.v(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class LogWarningStrategy extends LogStrategy {
        protected LogWarningStrategy(CommsLogger commsLogger, String str, JoinPoint joinPoint) {
            super(commsLogger, str, joinPoint);
        }

        @Override // com.amazon.comms.log.CommsLoggerAspect.LogStrategy
        protected void log(String str) {
            this.mLogger.w(str);
        }

        @Override // com.amazon.comms.log.CommsLoggerAspect.LogStrategy
        protected void log(String str, Throwable th) {
            this.mLogger.w(str, th);
        }
    }

    static {
        try {
            ajc$postClinit();
        } catch (Throwable th) {
            ajc$initFailureCause = th;
        }
    }

    public CommsLoggerAspect() {
        this(new CommsLogger.CommsLoggerFactoryImpl());
    }

    private static /* synthetic */ void ajc$postClinit() {
        ajc$perSingletonInstance = new CommsLoggerAspect();
    }

    public static CommsLoggerAspect aspectOf() {
        CommsLoggerAspect commsLoggerAspect = ajc$perSingletonInstance;
        if (commsLoggerAspect != null) {
            return commsLoggerAspect;
        }
        throw new NoAspectBoundException("com.amazon.comms.log.CommsLoggerAspect", ajc$initFailureCause);
    }

    public static boolean hasAspect() {
        return ajc$perSingletonInstance != null;
    }

    private void logMessage(LogStrategy logStrategy) {
        logStrategy.apply();
    }

    @Pointcut("execution(com.amazon.comms..*.new(..))")
    public void constructorsWithinCommsPackage() {
    }

    @Around("@annotation(log) && (methodsWithinCommsPackage() || constructorsWithinCommsPackage())")
    public Object log(ProceedingJoinPoint proceedingJoinPoint, Log log) throws Throwable {
        CommsLogger logger = this.mCommsLoggerFactory.getLogger(proceedingJoinPoint.getTarget());
        if (logger.isLoggable(log.level())) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$comms$log$LogLevel[log.level().ordinal()];
            if (i == 1) {
                logMessage(new LogErrorStrategy(this, logger, log.message(), proceedingJoinPoint));
            } else if (i == 2) {
                logMessage(new LogWarningStrategy(logger, log.message(), proceedingJoinPoint));
            } else if (i == 3) {
                logMessage(new LogDebugStrategy(logger, log.message(), proceedingJoinPoint));
            } else if (i != 4) {
                logMessage(new LogInfoStrategy(logger, log.message(), proceedingJoinPoint));
            } else {
                logMessage(new LogVerboseStrategy(logger, log.message(), proceedingJoinPoint));
            }
        }
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable th) {
            if (logger.isLoggable(LogLevel.Error)) {
                logMessage(new LogErrorStrategy(logger, log.message(), proceedingJoinPoint, th));
            }
            throw th;
        }
    }

    @Pointcut("execution(* com.amazon.comms..*.*(..))")
    public void methodsWithinCommsPackage() {
    }

    CommsLoggerAspect(CommsLogger.CommsLoggerFactory commsLoggerFactory) {
        this.mCommsLoggerFactory = commsLoggerFactory;
    }
}
