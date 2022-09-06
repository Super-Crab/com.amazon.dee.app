package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_PlayerError;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.google.common.collect.ImmutableMap;
/* compiled from: Errors.java */
/* loaded from: classes.dex */
public final class DGE {
    public static final pfe zZm = pfe.zZm(Iof.OPERATION_UNSUPPORTED, "The operation was attempted, but it is currently not supported.", 1000, true);
    public static final pfe BIo = pfe.zZm(Iof.OPERATION_REJECTED_END_OF_QUEUE, "The requested operation would go beyond the bounds of the current play queue.", 1100, true);
    public static final pfe zQM = pfe.zZm(Iof.OPERATION_REJECTED_SKIP_LIMIT, "This account has reached the allowed limit of skips.", 1200, true);
    public static final pfe zyO = pfe.zZm(Iof.OPERATION_REJECTED_UNINTERRUPTIBLE, "The current media is not interruptible.", 1300, true);
    public static final pfe jiA = new AutoValue_PlayerError(Iof.UNPLAYABLE_BY_AUTHORIZATION, 2000, true, true, "An account must be logged in to play the requested content.");
    public static final pfe Qle = pfe.zZm(Iof.UNPLAYABLE_BY_ACCOUNT, "The requested content is not available on this account.", 2100, true);
    public static final pfe JTe = pfe.zZm(Iof.UNPLAYABLE_BY_SUBSCRIPTION, "The requested content is not available on this account's subscription level.", 2300, true);
    public static final pfe LPk = pfe.zZm(Iof.UNPLAYABLE_BY_STREAM_CONCURRENCY, "This account has reached the allowed limit of concurrent streams.", 2400, true);
    public static final pfe yPL = pfe.zZm(Iof.UNPLAYABLE_BY_REGION, "The requested content is not available in the current region.", 2500, true);
    public static final pfe Mlj = pfe.zZm(Iof.UNPLAYABLE_BY_PARENTAL_CONTROL, "The requested content is not allowed due to the parental controls that are currently set.", 2600, true);
    public static final pfe zzR = pfe.zZm(Iof.INTERNAL_ERROR, "An error occurred in the player's application.", DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS, true);
    public static final pfe lOf = pfe.zZm(Iof.UNKNOWN_ERROR, "An unknown error occurred.", 3100, true);
    public static final ImmutableMap<Integer, pfe> dMe = ImmutableMap.builder().mo7828put(10, Qle).mo7828put(1, zzR).mo7828put(3, jiA).mo7828put(5, LPk).mo7828put(8, zyO).mo7828put(11, BIo).mo7828put(7, yPL).mo7828put(2, zZm).mo7828put(6, Mlj).mo7828put(4, JTe).mo7828put(9, zQM).mo7828put(0, lOf).mo7826build();
}
