package amazon.speech.simclient.directive;

import amazon.speech.simclient.directive.IDirectiveHolder;
import java.lang.ref.SoftReference;
/* loaded from: classes.dex */
public class DirectiveHolder extends IDirectiveHolder.Stub {
    private Directive mHardRef;
    private SoftReference<Directive> mSoftRef;

    public DirectiveHolder(Directive directive) {
        this.mHardRef = directive;
    }

    @Override // amazon.speech.simclient.directive.IDirectiveHolder
    public Directive getDirective() {
        Directive directive = this.mHardRef;
        if (directive != null) {
            this.mSoftRef = new SoftReference<>(directive);
            this.mHardRef = null;
        }
        return this.mSoftRef.get();
    }
}
