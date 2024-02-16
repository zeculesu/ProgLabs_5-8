package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementForm;
import org.jetbrains.annotations.NotNull;

public class UpdateCommand implements CommandAction {
    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO console, ConsoleCommandEnvironment env, String[] args) {
        if (args.length == 0) {
            return "Не введен аргумент - id элемента для обновления";
        }
        ElementForm form = new ElementForm();
        try {
            int id = ElementForm.check_id(args[0]);
            form.formElementIO(console);
            return collectionSpaceMarine.update(id, form.getName(), form.getCoordinates(), form.getHealth(),
                    form.getCategory(), form.getWeaponType(), form.getMeleeWeapon(), form.getChapter());
        } catch (NamingEnumException | InputFormException e) {
            return e.getMessage();
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "update";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "update : обновить значение элемента коллекции, id которого равен заданному";
    }
}
