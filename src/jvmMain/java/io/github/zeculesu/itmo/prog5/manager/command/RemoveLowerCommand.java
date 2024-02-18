package io.github.zeculesu.itmo.prog5.manager.command;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.error.NamingEnumException;
import io.github.zeculesu.itmo.prog5.manager.CommandAction;
import io.github.zeculesu.itmo.prog5.manager.Response;
import io.github.zeculesu.itmo.prog5.user_interface.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.jetbrains.annotations.NotNull;

public class RemoveLowerCommand implements CommandAction {

    boolean acceptsElement = true;
    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, ElementFormConsole... element) {
        Response response = new Response();
        ElementFormConsole elem = element[0];
        try {

            SpaceMarine o = new SpaceMarine(0, elem.getName(), elem.getCoordinates(), elem.getHealth(),
                    elem.getCategory(), elem.getWeaponType(), elem.getMeleeWeapon(), elem.getChapter());
            int startSize = collectionSpaceMarine.size();

            collectionSpaceMarine.remove_lower(o);

            int endSize = collectionSpaceMarine.size();
            if (startSize == endSize) {
                response.setMessage("Элементов с меньше данного в коллекции не найдено");
            }
            response.setMessage("Удаление произошло успешно");
        } catch (NamingEnumException | InputFormException e) {
            response.setMessage(e.getMessage());
        }
        return response;
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

    @Override
    public boolean isAcceptsElement() {
        return acceptsElement;
    }
}
