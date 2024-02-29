package io.github.zeculesu.itmo.prog5.manager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Список команд
 */
public interface CommandSet extends Iterable<CommandAction> {
    @Nullable
    CommandAction findCommand(@NotNull String comm);
}