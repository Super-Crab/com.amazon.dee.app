package org.bouncycastle.mail.smime.validator;

import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.LocalizedException;
/* loaded from: classes4.dex */
public class SignedMailValidatorException extends LocalizedException {
    public SignedMailValidatorException(ErrorBundle errorBundle) {
        super(errorBundle);
    }

    public SignedMailValidatorException(ErrorBundle errorBundle, Throwable th) {
        super(errorBundle, th);
    }
}
