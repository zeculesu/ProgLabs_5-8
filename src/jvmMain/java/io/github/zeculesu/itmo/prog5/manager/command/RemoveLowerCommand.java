package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementForm;
import org.jetbrains.annotations.NotNull;

public class RemoveLowerCommand implements CommandAction {
    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        ElementForm form = new ElementForm();
        try {
            form.formElementIO(console);
            SpaceMarine o = new SpaceMarine(0, form.getName(), form.getCoordinates(), form.getHealth(),
                    form.getCategory(), form.getWeaponType(), form.getMeleeWeapon(), form.getChapter());
            return collectionSpaceMarine.remove_lower(o);
        } catch (NamingEnumException | InputFormException e) {
            return e.getMessage();
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "remove_lower";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
