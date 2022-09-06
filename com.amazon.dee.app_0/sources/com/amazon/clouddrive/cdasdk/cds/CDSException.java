package com.amazon.clouddrive.cdasdk.cds;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import retrofit2.HttpException;
/* loaded from: classes11.dex */
public class CDSException extends CloudDriveException {
    private final CDSError cdsError;

    public CDSException(@NonNull HttpException httpException, @NonNull CDSError cDSError) {
        super(httpException);
        this.cdsError = cDSError;
    }

    @Override // com.amazon.clouddrive.cdasdk.CloudDriveException
    protected boolean canEqual(Object obj) {
        return obj instanceof CDSException;
    }

    @Override // com.amazon.clouddrive.cdasdk.CloudDriveException
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CDSException)) {
            return false;
        }
        CDSException cDSException = (CDSException) obj;
        if (!cDSException.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        CDSError cdsError = getCdsError();
        CDSError cdsError2 = cDSException.getCdsError();
        return cdsError != null ? cdsError.equals(cdsError2) : cdsError2 == null;
    }

    public CDSError getCdsError() {
        return this.cdsError;
    }

    @Override // com.amazon.clouddrive.cdasdk.CloudDriveException
    public int hashCode() {
        int hashCode = super.hashCode();
        CDSError cdsError = getCdsError();
        return (hashCode * 59) + (cdsError == null ? 43 : cdsError.hashCode());
    }

    @Override // com.amazon.clouddrive.cdasdk.CloudDriveException, java.lang.Throwable
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CDSException(cdsError=");
        outline107.append(getCdsError());
        outline107.append(")");
        return outline107.toString();
    }
}
