package org.apache.commons.collections4.sequence;
/* loaded from: classes4.dex */
public abstract class EditCommand<T> {
    private final T object;

    /* JADX INFO: Access modifiers changed from: protected */
    public EditCommand(T t) {
        this.object = t;
    }

    public abstract void accept(CommandVisitor<T> commandVisitor);

    /* JADX INFO: Access modifiers changed from: protected */
    public T getObject() {
        return this.object;
    }
}
