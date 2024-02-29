package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

/**
 * Возможности команд
 */
public interface CommandAction {
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, ElementFormConsole... element);

    public @NotNull String getName();
    public @NotNull String getDescription();

    public boolean isAcceptsElement();
}
