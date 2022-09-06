package com.amazonaws.services.s3.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class ExtraMaterialsDescription implements Serializable {
    public static final ExtraMaterialsDescription NONE = new ExtraMaterialsDescription(Collections.EMPTY_MAP);
    private final Map<String, String> extra;
    private final ConflictResolution resolve;

    /* renamed from: com.amazonaws.services.s3.model.ExtraMaterialsDescription$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$services$s3$model$ExtraMaterialsDescription$ConflictResolution = new int[ConflictResolution.values().length];

        static {
            try {
                $SwitchMap$com$amazonaws$services$s3$model$ExtraMaterialsDescription$ConflictResolution[ConflictResolution.FAIL_FAST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazonaws$services$s3$model$ExtraMaterialsDescription$ConflictResolution[ConflictResolution.OVERRIDDEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazonaws$services$s3$model$ExtraMaterialsDescription$ConflictResolution[ConflictResolution.OVERRIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public enum ConflictResolution {
        FAIL_FAST,
        OVERRIDE,
        OVERRIDDEN
    }

    public ExtraMaterialsDescription(Map<String, String> map) {
        this(map, ConflictResolution.FAIL_FAST);
    }

    public ConflictResolution getConflictResolution() {
        return this.resolve;
    }

    public Map<String, String> getMaterialDescription() {
        return this.extra;
    }

    public Map<String, String> mergeInto(Map<String, String> map) {
        if (this.extra.size() == 0) {
            return map;
        }
        if (map != null && map.size() != 0) {
            int ordinal = this.resolve.ordinal();
            if (ordinal == 0) {
                int size = this.extra.size() + map.size();
                HashMap hashMap = new HashMap(map);
                hashMap.putAll(this.extra);
                if (size == hashMap.size()) {
                    return Collections.unmodifiableMap(hashMap);
                }
                throw new IllegalArgumentException("The supplemental material descriptions contains conflicting entries");
            } else if (ordinal == 1) {
                HashMap hashMap2 = new HashMap(map);
                hashMap2.putAll(this.extra);
                return Collections.unmodifiableMap(hashMap2);
            } else if (ordinal == 2) {
                HashMap hashMap3 = new HashMap(this.extra);
                hashMap3.putAll(map);
                return Collections.unmodifiableMap(hashMap3);
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return this.extra;
    }

    public ExtraMaterialsDescription(Map<String, String> map, ConflictResolution conflictResolution) {
        if (map != null && conflictResolution != null) {
            this.extra = Collections.unmodifiableMap(new HashMap(map));
            this.resolve = conflictResolution;
            return;
        }
        throw new IllegalArgumentException();
    }
}
