package io.github.zeculesu.itmo.prog5.server.parseFile;

import io.github.zeculesu.itmo.prog5.data.*;

import io.github.zeculesu.itmo.prog5.error.IdException;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.client.ElementFormConsole;
import io.github.zeculesu.itmo.prog5.models.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Парсинг из .xml
 */

public class ReadFileXML {
    private static SpaceMarineCollection collection;

    public static void parseFile(String filePath, SpaceMarineCollection collection) throws FileNotFoundException, ParserConfigurationException, SAXException {
        ReadFileXML.collection = collection;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        AdvancedXMLHandler handler = new AdvancedXMLHandler();
        try {
            parser.parse(new File(filePath), handler);

        } catch (SAXException | FileNotFoundException e) {
            throw new FileNotFoundException("Файл не найден");
        } catch (IOException e) {
            throw new FileNotFoundException("Проблема с чтением файла");
        }
    }


    private static class AdvancedXMLHandler extends DefaultHandler {

        private HashMap<String, String> params = new HashMap<>();

        {
            clear_params();
        }

        private String lastElementName;

        public void clear_params() {
            params.put("name", null);
            params.put("x", null);
            params.put("y", null);
            params.put("creationDate", null);
            params.put("health", null);
            params.put("category", null);
            params.put("weaponType", null);
            params.put("meleeWeapon", null);
            params.put("chapter_name", null);
            params.put("parentLegion", null);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
            if (lastElementName.equals("element")) {
                params.put("id", attributes.getValue("id"));
            }
            if (lastElementName.equals("coordinates")) {
                params.put("x", attributes.getValue("x"));
                params.put("y", attributes.getValue("y"));
            }
            if (lastElementName.equals("chapter")) {
                params.put("chapter_name", attributes.getValue("name"));
                params.put("parentLegion", attributes.getValue("parentLegion"));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                switch (lastElementName) {
                    case "name": {
                        params.put("name", information);
                        break;
                    }
                    case "creationDate": {
                        params.put("creationDate", information);
                        break;
                    }
                    case "health": {
                        params.put("health", information);
                        break;
                    }
                    case "category": {
                        params.put("category", information);
                        break;
                    }
                    case "weaponType": {
                        params.put("weaponType", information);
                        break;
                    }
                    case "meleeWeapon": {
                        params.put("meleeWeapon", information);
                        break;
                    }
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws InputFormException {
            ArrayList<String> keys = new ArrayList<>(params.keySet());
            ArrayList<String> valuesNull = new ArrayList<>();
            for (String key : keys) {
                if (params.get(key) == null) {
                    valuesNull.add(key);
                }
            }
            boolean fill = valuesNull.isEmpty();
            if (fill && qName.equals("element")) {
                try {
                    int id = ElementFormConsole.checkId(params.get("id"));
                    String name = ElementFormConsole.checkName(params.get("name"));
                    Coordinates coordinates = ElementFormConsole.checkCoordinates(params.get("x") + " " + params.get("y"));
                    int health = ElementFormConsole.checkHealth(params.get("health"));
                    AstartesCategory category = ElementFormConsole.checkCategory(params.get("category"));
                    Date creationDate = ElementFormConsole.checkCreationDate(params.get("creationDate"));
                    Weapon weaponType = ElementFormConsole.checkWeaponType(params.get("weaponType"));
                    MeleeWeapon meleeWeapon = ElementFormConsole.checkMeleeWeapon(params.get("meleeWeapon"));
                    Chapter chapter = ElementFormConsole.checkChapter(params.get("chapter_name") + " " + params.get("parentLegion"));
                    collection.addFromFile(id, name, coordinates, creationDate, health,
                            category, weaponType, meleeWeapon, chapter);
                    clear_params();
                } catch (InputFormException | IdException e) {
                    System.out.println(e.getMessage());
                }
            } else if (!fill && qName.equals("element")) {
                System.out.println("Неправильный ввод параметров коллекции");
                for (String val : valuesNull) {
                    System.out.println("Отсутствует поле : " + val);
                }
            }
        }
    }
}
