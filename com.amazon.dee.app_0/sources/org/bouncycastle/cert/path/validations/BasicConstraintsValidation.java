package org.bouncycastle.cert.path.validations;

import java.math.BigInteger;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.util.Memoable;
/* loaded from: classes4.dex */
public class BasicConstraintsValidation implements CertPathValidation {
    private BasicConstraints bc;
    private boolean isMandatory;
    private BigInteger maxPathLength;
    private int pathLengthRemaining;

    public BasicConstraintsValidation() {
        this(true);
    }

    public BasicConstraintsValidation(boolean z) {
        this.isMandatory = z;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        BasicConstraintsValidation basicConstraintsValidation = new BasicConstraintsValidation(this.isMandatory);
        basicConstraintsValidation.bc = this.bc;
        basicConstraintsValidation.pathLengthRemaining = this.pathLengthRemaining;
        return basicConstraintsValidation;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        BasicConstraintsValidation basicConstraintsValidation = (BasicConstraintsValidation) memoable;
        this.isMandatory = basicConstraintsValidation.isMandatory;
        this.bc = basicConstraintsValidation.bc;
        this.pathLengthRemaining = basicConstraintsValidation.pathLengthRemaining;
    }

    @Override // org.bouncycastle.cert.path.CertPathValidation
    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        int i;
        BigInteger pathLenConstraint;
        int intValue;
        if (this.maxPathLength == null || this.pathLengthRemaining >= 0) {
            certPathValidationContext.addHandledExtension(Extension.basicConstraints);
            BasicConstraints fromExtensions = BasicConstraints.fromExtensions(x509CertificateHolder.getExtensions());
            if (fromExtensions != null) {
                if (this.bc == null) {
                    this.bc = fromExtensions;
                    if (fromExtensions.isCA()) {
                        this.maxPathLength = fromExtensions.getPathLenConstraint();
                        BigInteger bigInteger = this.maxPathLength;
                        if (bigInteger != null) {
                            i = bigInteger.intValue();
                            this.pathLengthRemaining = i;
                        }
                    }
                } else if (fromExtensions.isCA() && (pathLenConstraint = fromExtensions.getPathLenConstraint()) != null && (intValue = pathLenConstraint.intValue()) < this.pathLengthRemaining) {
                    this.pathLengthRemaining = intValue;
                    this.bc = fromExtensions;
                }
            } else if (this.bc != null) {
                i = this.pathLengthRemaining - 1;
                this.pathLengthRemaining = i;
            }
            if (this.isMandatory && this.bc == null) {
                throw new CertPathValidationException("BasicConstraints not present in path");
            }
            return;
        }
        throw new CertPathValidationException("BasicConstraints path length exceeded");
    }
}
