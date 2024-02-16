package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import org.jetbrains.annotations.NotNull;

public interface CommandAction {
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args);

    public @NotNull String getName();
    public @NotNull String getDescription();
}
