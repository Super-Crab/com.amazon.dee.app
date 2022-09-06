package org.junit.internal.runners;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.TestTimedOutException;
@Deprecated
/* loaded from: classes5.dex */
public class MethodRoadie {
    private final Description description;
    private final RunNotifier notifier;
    private final Object test;
    private TestMethod testMethod;

    public MethodRoadie(Object obj, TestMethod testMethod, RunNotifier runNotifier, Description description) {
        this.test = obj;
        this.notifier = runNotifier;
        this.description = description;
        this.testMethod = testMethod;
    }

    private void runAfters() {
        for (Method method : this.testMethod.getAfters()) {
            try {
                method.invoke(this.test, new Object[0]);
            } catch (InvocationTargetException e) {
                addFailure(e.getTargetException());
            } catch (Throwable th) {
                addFailure(th);
            }
        }
    }

    private void runBefores() throws FailedBefore {
        try {
            try {
                for (Method method : this.testMethod.getBefores()) {
                    method.invoke(this.test, new Object[0]);
                }
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
        } catch (AssumptionViolatedException unused) {
            throw new FailedBefore();
        } catch (Throwable th) {
            addFailure(th);
            throw new FailedBefore();
        }
    }

    private void runWithTimeout(final long j) {
        runBeforesThenTestThenAfters(new Runnable() { // from class: org.junit.internal.runners.MethodRoadie.1
            @Override // java.lang.Runnable
            public void run() {
                ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
                Future submit = newSingleThreadExecutor.submit(new Callable<Object>() { // from class: org.junit.internal.runners.MethodRoadie.1.1
                    @Override // java.util.concurrent.Callable
                    public Object call() throws Exception {
                        MethodRoadie.this.runTestMethod();
                        return null;
                    }
                });
                newSingleThreadExecutor.shutdown();
                try {
                    if (!newSingleThreadExecutor.awaitTermination(j, TimeUnit.MILLISECONDS)) {
                        newSingleThreadExecutor.shutdownNow();
                    }
                    submit.get(0L, TimeUnit.MILLISECONDS);
                } catch (TimeoutException unused) {
                    MethodRoadie.this.addFailure(new TestTimedOutException(j, TimeUnit.MILLISECONDS));
                } catch (Exception e) {
                    MethodRoadie.this.addFailure(e);
                }
            }
        });
    }

    protected void addFailure(Throwable th) {
        this.notifier.fireTestFailure(new Failure(this.description, th));
    }

    public void run() {
        if (this.testMethod.isIgnored()) {
            this.notifier.fireTestIgnored(this.description);
            return;
        }
        this.notifier.fireTestStarted(this.description);
        try {
            long timeout = this.testMethod.getTimeout();
            if (timeout > 0) {
                runWithTimeout(timeout);
            } else {
                runTest();
            }
        } finally {
            this.notifier.fireTestFinished(this.description);
        }
    }

    public void runBeforesThenTestThenAfters(Runnable runnable) {
        try {
            try {
                runBefores();
                runnable.run();
            } catch (FailedBefore unused) {
            } catch (Exception unused2) {
                throw new RuntimeException("test should never throw an exception to this level");
            }
        } finally {
            runAfters();
        }
    }

    public void runTest() {
        runBeforesThenTestThenAfters(new Runnable() { // from class: org.junit.internal.runners.MethodRoadie.2
            @Override // java.lang.Runnable
            public void run() {
                MethodRoadie.this.runTestMethod();
            }
        });
    }

    protected void runTestMethod() {
        try {
            this.testMethod.invoke(this.test);
            if (!this.testMethod.expectsException()) {
                return;
            }
            addFailure(new AssertionError("Expected exception: " + this.testMethod.getExpectedException().getName()));
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof AssumptionViolatedException) {
                return;
            }
            if (!this.testMethod.expectsException()) {
                addFailure(targetException);
            } else if (!this.testMethod.isUnexpected(targetException)) {
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected exception, expected<");
                outline107.append(this.testMethod.getExpectedException().getName());
                outline107.append("> but was<");
                outline107.append(targetException.getClass().getName());
                outline107.append(Config.Compare.GREATER_THAN);
                addFailure(new Exception(outline107.toString(), targetException));
            }
        } catch (Throwable th) {
            addFailure(th);
        }
    }
}
