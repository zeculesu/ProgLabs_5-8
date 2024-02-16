package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.*;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.CommandIO;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementForm;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.Element;


public class AddCommand implements CommandAction {

    @Override
    public String execute(CollectionAction collectionSpaceMarine, CommandIO cns, ConsoleCommandEnvironment env, String[] args) {
        ElementForm form = new ElementForm();
        try {
            form.formElementIO(cns);
        } catch (NamingEnumException | InputFormException e) {
            return e.getMessage();
        }
        collectionSpaceMarine.add(form.getName(), form.getCoordinates(), form.getHealth(), form.getCategory(), form.getWeaponType(), form.getMeleeWeapon(), form.getChapter());
        return "Новый элемент добавлен";
    }

    @NotNull
    @Override
    public String getName() {
        return "add";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
}
