package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.error.ElementNotFound;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

public class UpdateCommand implements CommandAction {

    boolean acceptsElement = true;

    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, ElementFormConsole... element) {
        Response response = new Response();
        if (args.length == 0) {
            response.setMessage("Не введен аргумент - id элемента");
            return response;
        }
        ElementFormConsole elem = element[0];
        try {
            int id = ElementFormConsole.check_id(args[0]);
            collectionSpaceMarine.update(id, elem.getName(), elem.getCoordinates(), elem.getHealth(),
                    elem.getCategory(), elem.getWeaponType(), elem.getMeleeWeapon(), elem.getChapter());
        } catch (NamingEnumException | InputFormException | ElementNotFound e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "update";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "update id : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}