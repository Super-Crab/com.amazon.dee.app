package com.amazon.whisperjoin.provisionerSDK.radios.error;

import com.amazon.whisperbridge.constants.Command;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class ProvisionerCommandError extends Exception {
    private final Command mCommand;
    private final Throwable mFailureCause;

    public ProvisionerCommandError(Throwable th, Command command) {
        this.mFailureCause = th;
        this.mCommand = command;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisionerCommandError.class != obj.getClass()) {
            return false;
        }
        ProvisionerCommandError provisionerCommandError = (ProvisionerCommandError) obj;
        return Objects.equal(this.mFailureCause, provisionerCommandError.mFailureCause) && Objects.equal(this.mCommand, provisionerCommandError.mCommand);
    }

    public Command getCommand() {
        return this.mCommand;
    }

    public Throwable getFailureCause() {
        return this.mFailureCause;
    }

    public int hashCode() {
        return Objects.hashCode(this.mFailureCause, this.mCommand);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mFailureCause", this.mFailureCause).add("mCommandMessage", this.mCommand).toString();
    }
}
