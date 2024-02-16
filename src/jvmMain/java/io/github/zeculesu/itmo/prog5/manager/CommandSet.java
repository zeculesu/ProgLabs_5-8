package io.github.zeculesu.itmo.prog5.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandSet extends Iterable<CommandAction> {
    @Nullable
    public CommandAction findCommand(@NotNull String comm);
}