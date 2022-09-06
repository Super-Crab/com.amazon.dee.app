package com.amazon.alexa;

import java.util.EnumSet;
/* compiled from: DialogUtil.java */
/* loaded from: classes.dex */
public class xrg {
    public static final EnumSet<wSq> zZm = EnumSet.of(wSq.THINKING, wSq.LISTENING, wSq.PREPARING_TO_SPEAK, wSq.REQUEST_PROCESSING);

    public static boolean zZm(vkx vkxVar) {
        return !zZm.contains(vkxVar.zyO());
    }
}
