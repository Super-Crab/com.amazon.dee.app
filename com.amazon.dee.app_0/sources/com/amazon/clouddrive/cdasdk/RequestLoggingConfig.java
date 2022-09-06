package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class RequestLoggingConfig {
    @NonNull
    private final DetailLevel detailLevel;
    @NonNull
    private final Verbosity verbosity;

    /* loaded from: classes11.dex */
    public enum DetailLevel {
        None,
        Basic,
        Full
    }

    /* loaded from: classes11.dex */
    public enum Verbosity {
        Debug,
        Verbose,
        Info
    }

    public RequestLoggingConfig() {
        this.detailLevel = DetailLevel.Basic;
        this.verbosity = Verbosity.Debug;
    }

    public DetailLevel getDetailLevel() {
        return this.detailLevel;
    }

    public Verbosity getVerbosity() {
        return this.verbosity;
    }

    public RequestLoggingConfig(@NonNull DetailLevel detailLevel, @NonNull Verbosity verbosity) {
        this.detailLevel = detailLevel;
        this.verbosity = verbosity;
    }
}
