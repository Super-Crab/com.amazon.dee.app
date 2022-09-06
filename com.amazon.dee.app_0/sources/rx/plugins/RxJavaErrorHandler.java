package rx.plugins;

import com.android.tools.r8.GeneratedOutlineSupport1;
import rx.annotations.Beta;
import rx.exceptions.Exceptions;
/* loaded from: classes5.dex */
public abstract class RxJavaErrorHandler {
    protected static final String ERROR_IN_RENDERING_SUFFIX = ".errorRendering";

    @Deprecated
    public void handleError(Throwable th) {
    }

    @Beta
    public final String handleOnNextValueRendering(Object obj) {
        try {
            return render(obj);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return GeneratedOutlineSupport1.outline46(obj, new StringBuilder(), ERROR_IN_RENDERING_SUFFIX);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            return GeneratedOutlineSupport1.outline46(obj, new StringBuilder(), ERROR_IN_RENDERING_SUFFIX);
        }
    }

    @Beta
    protected String render(Object obj) throws InterruptedException {
        return null;
    }
}
