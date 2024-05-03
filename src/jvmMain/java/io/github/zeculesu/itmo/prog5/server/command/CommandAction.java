package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.data.SpaceMarineCollection;
import io.github.zeculesu.itmo.prog5.models.Response;
import io.github.zeculesu.itmo.prog5.models.SpaceMarine;
import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * Возможности команд
 */
public interface CommandAction {
    public Response execute(SpaceMarineCollection collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element);

    public @NotNull String getName();
    public @NotNull String getDescription();

    public boolean isAcceptsElement();

    public boolean isAcceptsArg();
}
