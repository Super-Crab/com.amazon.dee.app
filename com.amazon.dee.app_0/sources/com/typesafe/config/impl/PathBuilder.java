package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.Stack;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class PathBuilder {
    private final Stack<String> keys = new Stack<>();
    private Path result;

    private void checkCanAppend() {
        if (this.result == null) {
            return;
        }
        throw new ConfigException.BugOrBroken("Adding to PathBuilder after getting result");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appendKey(String str) {
        checkCanAppend();
        this.keys.push(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appendPath(Path path) {
        checkCanAppend();
        String first = path.first();
        Path remainder = path.remainder();
        while (true) {
            this.keys.push(first);
            if (remainder != null) {
                first = remainder.first();
                remainder = remainder.remainder();
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path result() {
        if (this.result == null) {
            Path path = null;
            while (!this.keys.isEmpty()) {
                path = new Path(this.keys.pop(), path);
            }
            this.result = path;
        }
        return this.result;
    }
}
