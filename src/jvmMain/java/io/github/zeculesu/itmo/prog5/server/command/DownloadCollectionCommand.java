package io.github.zeculesu.itmo.prog5.server.command;

import io.github.zeculesu.itmo.prog5.client.ConsoleCommandEnvironment;
import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import io.github.zeculesu.itmo.prog5.data.Response;
import io.github.zeculesu.itmo.prog5.data.SpaceMarine;
import io.github.zeculesu.itmo.prog5.server.parseFile.ParseFileXML;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;


public class DownloadCollectionCommand implements CommandAction {
    @Override
    public Response execute(CollectionAction collectionSpaceMarine, ConsoleCommandEnvironment env, String[] args, SpaceMarine... element) {
        Response response = new Response();
        if (env.getFileNameCollection() == null) {
            response.setError("Имя файла с коллекцией не указано, оно должно храниться в переменной окружения FILENAME");
        } else {
            response.addLineOutput("Файл с коллекцией: " + env.getFileNameCollection());
            try {
                ParseFileXML.parseFile(env.getFileNameCollection(), collectionSpaceMarine);
                collectionSpaceMarine.setNewMaxId();
                response.addLineOutput("Элементы из коллекции загружены");
            } catch (FileNotFoundException | ParserConfigurationException | SAXException e) {
                response.setError(e.getMessage());
            }
        }
        return response;
    }

    @NotNull
    @Override
    public String getName() {
        return "download collection from file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isAcceptsElement() {
        return false;
    }
}
