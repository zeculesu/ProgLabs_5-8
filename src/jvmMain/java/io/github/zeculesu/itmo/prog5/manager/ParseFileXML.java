package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.*;
import io.github.zeculesu.itmo.prog5.error.FileCollectionException;
import io.github.zeculesu.itmo.prog5.error.IdException;
import io.github.zeculesu.itmo.prog5.error.InputFormException;
import io.github.zeculesu.itmo.prog5.user_interface.ElementFormConsole;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class ParseFileXML implements ParseFileCollection {
    private static CollectionAction collection;

    public static void writeFile(String filePath, CollectionAction collection) throws FileCollectionException {
        ParseFileXML.collection = collection;
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        try {
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(filePath), "UTF-8");
            writeCollection(writer);
        } catch (FileNotFoundException e) {
            throw new FileCollectionException("Файл не найден");
        } catch (XMLStreamException e) {
            throw new FileCollectionException("Ошибка с файлом");
        }
    }

    public static void writeCollection(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeStartDocument("UTF-8", "1.0");

        writer.writeStartElement("collection");
        for (SpaceMarine o : ParseFileXML.collection) {
            writer.writeStartElement("element");
            writer.writeAttribute("id", Integer.toString(o.getId()));

            writer.writeStartElement("name");
            writer.writeCharacters(o.getName());
            writer.writeEndElement();

            writer.writeEmptyElement("coordinates");
            writer.writeAttribute("x", Long.toString(o.getCoordinates().getX()));
            writer.writeAttribute("y", Float.toString(o.getCoordinates().getY()));

            writer.writeStartElement("creationDate");
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:ss");
            writer.writeCharacters(df.format(o.getCreationDate()));
            writer.writeEndElement();

            writer.writeStartElement("health");
            writer.writeCharacters(Integer.toString(o.getHealth()));
            writer.writeEndElement();

            writer.writeStartElement("category");
            writer.writeCharacters(o.getCategory().toString());
            writer.writeEndElement();

            writer.writeStartElement("weaponType");
            writer.writeCharacters(o.getWeaponType().toString());
            writer.writeEndElement();

            writer.writeStartElement("meleeWeapon");
            writer.writeCharacters(o.getMeleeWeapon().toString());
            writer.writeEndElement();

            writer.writeEmptyElement("chapter");
            writer.writeAttribute("name", o.getChapter().getName());
            writer.writeAttribute("parentLegion", o.getChapter().getParentLegion());

            writer.writeEndElement();

        }
        writer.writeEndElement();

        writer.writeEndDocument();
    }

    public static CollectionAction parseFile(String filePath, CollectionAction collection) throws FileNotFoundException, ParserConfigurationException, SAXException {
        ParseFileXML.collection = collection;
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
        return collection;
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
                    Coordinates coordinates = ElementFormConsole.checkCoordinates(params.get("x") +" "+ params.get("y"));
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
