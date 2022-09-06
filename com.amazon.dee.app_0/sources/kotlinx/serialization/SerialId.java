package kotlinx.serialization;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.annotation.AnnotationTarget;
/* compiled from: Migrations.kt */
@Target({})
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\u0002\u0018\u00002\u00020\u0001:\u0001\u0005B\n\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lkotlinx/serialization/SerialId;", "", "id", "", "()I", "Impl", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@SerialInfo
@Deprecated(level = DeprecationLevel.ERROR, message = "SerialId is renamed to ProtoId to better reflect its semantics and extracted to separate artifact kotlinx-serialization-protobuf", replaceWith = @ReplaceWith(expression = "ProtoId", imports = {"kotlinx.serialization.protobuf.*"}))
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.PROPERTY})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes4.dex */
public @interface SerialId {

    /* compiled from: Migrations.kt */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\t\u0010\u0003\u001a\u00020\u0004XÆ\u0005¨\u0006\u0005"}, d2 = {"Lkotlinx/serialization/SerialId$Impl;", "Lkotlinx/serialization/SerialId;", "()V", "id", "", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Impl implements SerialId {
        private final /* synthetic */ int _id;

        private Impl() {
        }

        public Impl(int i) {
            this._id = i;
        }

        @Override // kotlinx.serialization.SerialId
        public final int id() {
            return this._id;
        }
    }

    int id();
}
