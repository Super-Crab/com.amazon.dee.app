package com.amazon.clouddrive.cdasdk.cdus;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import retrofit2.HttpException;
/* loaded from: classes11.dex */
public class CDUSException extends CloudDriveException {
    private final CDUSError cdusError;

    public CDUSException(@NonNull HttpException httpException, @NonNull CDUSError cDUSError) {
        this(httpException.code(), httpException.message(), cDUSError);
    }

    @Override // com.amazon.clouddrive.cdasdk.CloudDriveException
    protected boolean canEqual(Object obj) {
        return obj instanceof CDUSException;
    }

    @Override // com.amazon.clouddrive.cdasdk.CloudDriveException
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CDUSException)) {
            return false;
        }
        CDUSException cDUSException = (CDUSException) obj;
        if (!cDUSException.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        CDUSError cdusError = getCdusError();
        CDUSError cdusError2 = cDUSException.getCdusError();
        return cdusError != null ? cdusError.equals(cdusError2) : cdusError2 == null;
    }

    public CDUSError getCdusError() {
        return this.cdusError;
    }

    @Override // com.amazon.clouddrive.cdasdk.CloudDriveException
    public int hashCode() {
        int hashCode = super.hashCode();
        CDUSError cdusError = getCdusError();
        return (hashCode * 59) + (cdusError == null ? 43 : cdusError.hashCode());
    }

    @Override // com.amazon.clouddrive.cdasdk.CloudDriveException, java.lang.Throwable
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CDUSException(cdusError=");
        outline107.append(getCdusError());
        outline107.append(")");
        return outline107.toString();
    }

    public CDUSException(int i, String str, @NonNull CDUSError cDUSError) {
        super(i, str);
        this.cdusError = cDUSError;
    }
}
