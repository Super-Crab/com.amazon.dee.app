package com.amazon.comms.metrics;

import com.amazon.comms.metrics.annotation.Count;
import com.amazon.comms.metrics.annotation.Counts;
import com.amazon.comms.metrics.annotation.Metadata;
import com.amazon.comms.metrics.annotation.Time;
import com.amazon.comms.metrics.annotation.Times;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;
@Aspect
/* loaded from: classes11.dex */
public class MetricsManagerAspect {
    private static /* synthetic */ Throwable ajc$initFailureCause;
    public static final /* synthetic */ MetricsManagerAspect ajc$perSingletonInstance = null;
    private MetricsManager mMetricsManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class MissingMetricsManagerException extends RuntimeException {
        MissingMetricsManagerException() {
        }
    }

    static {
        try {
            ajc$postClinit();
        } catch (Throwable th) {
            ajc$initFailureCause = th;
        }
    }

    private void addMetricMetadata(String str, JoinPoint joinPoint) {
        for (Map.Entry<String, String> entry : getMetadataFromJoinPoint(joinPoint).entrySet()) {
            this.mMetricsManager.addMetadata(str, entry.getKey(), entry.getValue());
        }
    }

    private static /* synthetic */ void ajc$postClinit() {
        ajc$perSingletonInstance = new MetricsManagerAspect();
    }

    public static MetricsManagerAspect aspectOf() {
        MetricsManagerAspect metricsManagerAspect = ajc$perSingletonInstance;
        if (metricsManagerAspect != null) {
            return metricsManagerAspect;
        }
        throw new NoAspectBoundException("com.amazon.comms.metrics.MetricsManagerAspect", ajc$initFailureCause);
    }

    private boolean bothStartAndStopParametersAreFalse(Time time) {
        return !time.start() && !time.stop();
    }

    private Map<String, String> getMetadataFromJoinPoint(JoinPoint joinPoint) {
        Annotation[][] parameterAnnotations;
        HashMap hashMap = new HashMap();
        Object[] args = joinPoint.getArgs();
        if (joinPoint.getSignature() instanceof ConstructorSignature) {
            parameterAnnotations = ((ConstructorSignature) joinPoint.getSignature()).getConstructor().getParameterAnnotations();
        } else {
            parameterAnnotations = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterAnnotations();
        }
        for (int i = 0; i < args.length; i++) {
            Object obj = args[i];
            Annotation[] annotationArr = parameterAnnotations[i];
            for (int i2 = 0; i2 < annotationArr.length; i2++) {
                if (annotationArr[i2] instanceof Metadata) {
                    Metadata metadata = (Metadata) annotationArr[i2];
                    try {
                        hashMap.put(metadata.key(), obj.getClass().getMethod(metadata.method(), new Class[0]).invoke(obj, new Object[0]).toString());
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return hashMap;
    }

    private MetricsMetadataProvider getMetricMetadataProvider(Object obj) {
        Field[] declaredFields;
        if (obj instanceof MetricsMetadataProvider) {
            return (MetricsMetadataProvider) obj;
        }
        if (obj.getClass().isAnonymousClass()) {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object obj2 = field.get(obj);
                    if (obj2 instanceof MetricsMetadataProvider) {
                        return (MetricsMetadataProvider) obj2;
                    }
                } catch (IllegalAccessException unused) {
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void guardAgainstMissingMetricsManager() {
        if (this.mMetricsManager != null) {
            return;
        }
        throw new MissingMetricsManagerException();
    }

    public static boolean hasAspect() {
        return ajc$perSingletonInstance != null;
    }

    @Pointcut("if()")
    public static boolean isNotJUnitTest() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (stackTraceElement.getClassName().startsWith("org.junit.")) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMetric(String str, JoinPoint joinPoint, MetricsDestination metricsDestination) {
        MetricsMetadataProvider metricMetadataProvider = getMetricMetadataProvider(joinPoint.getTarget());
        addMetricMetadata(str, joinPoint);
        if (metricMetadataProvider == null) {
            this.mMetricsManager.record(str, metricsDestination);
        } else {
            this.mMetricsManager.record(str, metricMetadataProvider, metricsDestination);
        }
    }

    private void recordMetrics(Set<String> set, JoinPoint joinPoint, MetricsDestination metricsDestination) {
        MetricsMetadataProvider metricMetadataProvider = getMetricMetadataProvider(joinPoint.getTarget());
        for (String str : set) {
            addMetricMetadata(str, joinPoint);
            if (metricMetadataProvider == null) {
                this.mMetricsManager.record(str, metricsDestination);
            } else {
                this.mMetricsManager.record(str, metricMetadataProvider, metricsDestination);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldStartTimer(Time time) {
        return time.start() || bothStartAndStopParametersAreFalse(time);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldStopTimer(Time time) {
        return time.stop() || bothStartAndStopParametersAreFalse(time);
    }

    @Pointcut("execution(com.amazon.comms..*.new(..))")
    public void constructorsWithinCommsPackage() {
    }

    @Before("@annotation(metric) && isNotJUnitTest() && (methodsWithinCommsPackage() || constructorsWithinCommsPackage())")
    public void handleCount(JoinPoint joinPoint, Count count) {
        guardAgainstMissingMetricsManager();
        guardAgainstMissingMetricsManager();
        this.mMetricsManager.incrementCounter(count.source(), count.name(), count.increment());
        recordMetric(count.source(), joinPoint, count.metricsDestination());
    }

    @Before("@annotation(metric) && isNotJUnitTest() && (methodsWithinCommsPackage() || constructorsWithinCommsPackage())")
    public void handleCounts(JoinPoint joinPoint, Counts counts) {
        Count[] value;
        guardAgainstMissingMetricsManager();
        HashSet hashSet = new HashSet();
        for (Count count : counts.value()) {
            hashSet.add(count.source());
            this.mMetricsManager.incrementCounter(count.source(), count.name(), count.increment());
        }
        recordMetrics(hashSet, joinPoint, counts.metricsDestination());
    }

    @Around("@annotation(metric) && isNotJUnitTest() && (methodsWithinCommsPackage() || constructorsWithinCommsPackage())")
    public Object handleTime(ProceedingJoinPoint proceedingJoinPoint, Time time) throws Throwable {
        guardAgainstMissingMetricsManager();
        if (shouldStartTimer(time)) {
            this.mMetricsManager.startTimer(time.source(), time.name());
        }
        try {
            Object proceed = proceedingJoinPoint.proceed();
            if (shouldStopTimer(time)) {
                this.mMetricsManager.stopTimer(time.source(), time.name());
                recordMetric(time.source(), proceedingJoinPoint, time.metricsDestination());
            }
            return proceed;
        } catch (Throwable th) {
            this.mMetricsManager.removeTimer(time.source(), time.name());
            throw th;
        }
    }

    @Around("@annotation(metric) && isNotJUnitTest() && (methodsWithinCommsPackage() || constructorsWithinCommsPackage())")
    public Object handleTimes(ProceedingJoinPoint proceedingJoinPoint, Times times) throws Throwable {
        Time[] value;
        Time[] value2;
        Time[] value3;
        guardAgainstMissingMetricsManager();
        for (Time time : times.value()) {
            if (shouldStartTimer(time)) {
                this.mMetricsManager.startTimer(time.source(), time.name());
            }
        }
        try {
            Object proceed = proceedingJoinPoint.proceed();
            HashSet hashSet = new HashSet();
            for (Time time2 : times.value()) {
                if (shouldStopTimer(time2)) {
                    hashSet.add(time2.source());
                    this.mMetricsManager.stopTimer(time2.source(), time2.name());
                }
            }
            recordMetrics(hashSet, proceedingJoinPoint, times.metricsDestination());
            return proceed;
        } catch (Throwable th) {
            for (Time time3 : times.value()) {
                this.mMetricsManager.removeTimer(time3.source(), time3.name());
            }
            throw th;
        }
    }

    @Pointcut("execution(* com.amazon.comms..*.*(..))")
    public void methodsWithinCommsPackage() {
    }

    @After("execution(com.amazon.comms.metrics.MetricsManager.new(..))")
    public void setMetricsManager(JoinPoint joinPoint) {
        if (this.mMetricsManager == null) {
            this.mMetricsManager = (MetricsManager) joinPoint.getTarget();
        }
    }
}
