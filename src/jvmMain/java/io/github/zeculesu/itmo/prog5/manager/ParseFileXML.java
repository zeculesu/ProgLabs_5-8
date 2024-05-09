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

/**
 * Парсинг из .xml
 */

public class ParseFileXML implements ParseFileCollection {

    public static void writeFile(String filePath, SpaceMarineCollection collection) throws FileCollectionException {
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        try {
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(filePath), "UTF-8");
            writeCollection(writer, collection);
        } catch (FileNotFoundException e) {
            throw new FileCollectionException("Файл не найден");
        } catch (XMLStreamException e) {
            throw new FileCollectionException("Ошибка с файлом");
        } catch (NullPointerException e) {
            throw new FileCollectionException("Имя файла для записи не указано в переменной окружения FILENAME");
        }
        catch (Exception e){
            throw new FileCollectionException("Непредвиденная ошибка при записи в файл");
        }
    }

    public static void writeCollection(XMLStreamWriter writer, SpaceMarineCollection collection) throws XMLStreamException {
        writer.writeStartDocument("UTF-8", "1.0");

        writer.writeCharacters("\n");
        writer.writeStartElement("collection");
        for (SpaceMarine o : collection) {
            writer.writeCharacters("\n");

            writer.writeCharacters("\t");
            writer.writeStartElement("element");
            writer.writeAttribute("id", Integer.toString(o.getId()));
            writer.writeCharacters("\n");


            writer.writeCharacters("\t\t");
            writer.writeStartElement("name");
            writer.writeCharacters(o.getName());
            writer.writeEndElement();
            writer.writeCharacters("\n");

            writer.writeCharacters("\t\t");
            writer.writeEmptyElement("coordinates");
            writer.writeAttribute("x", Long.toString(o.getCoordinates().getX()));
            writer.writeAttribute("y", Float.toString(o.getCoordinates().getY()));
            writer.writeCharacters("\n");

            writer.writeCharacters("\t\t");
            writer.writeStartElement("creationDate");
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:ss");
            writer.writeCharacters(df.format(o.getCreationDate()));
            writer.writeEndElement();
            writer.writeCharacters("\n");

            writer.writeCharacters("\t\t");
            writer.writeStartElement("health");
            writer.writeCharacters(Integer.toString(o.getHealth()));
            writer.writeEndElement();
            writer.writeCharacters("\n");

            writer.writeCharacters("\t\t");
            writer.writeStartElement("category");
            writer.writeCharacters(o.getCategory().toString());
            writer.writeEndElement();
            writer.writeCharacters("\n");

            writer.writeCharacters("\t\t");
            writer.writeStartElement("weaponType");
            writer.writeCharacters(o.getWeaponType().toString());
            writer.writeEndElement();
            writer.writeCharacters("\n");

            writer.writeCharacters("\t\t");
            writer.writeStartElement("meleeWeapon");
            writer.writeCharacters(o.getMeleeWeapon().toString());
            writer.writeEndElement();
            writer.writeCharacters("\n");

            writer.writeCharacters("\t\t");
            writer.writeEmptyElement("chapter");
            writer.writeAttribute("name", o.getChapter().getName());
            writer.writeAttribute("parentLegion", o.getChapter().getParentLegion());
            writer.writeCharacters("\n");

            writer.writeCharacters("\t");
            writer.writeEndElement();

        }
        writer.writeEndElement();

        writer.writeEndDocument();
    }

    public static void parseFile(String filePath, SpaceMarineCollection collection) throws FileNotFoundException, ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        AdvancedXMLHandler handler = new AdvancedXMLHandler(collection);
        try {
            parser.parse(new File(filePath), handler);

        } catch (SAXException | FileNotFoundException e) {
            throw new FileNotFoundException("Файл не найден");
        } catch (IOException e) {
            throw new FileNotFoundException("Проблема с чтением файла");
        }
    }

    private static class AdvancedXMLHandler extends DefaultHandler {
        SpaceMarineCollection collection;

        public AdvancedXMLHandler(SpaceMarineCollection collection) {
            this.collection = collection;
        }

        private HashMap<String, String> params = new HashMap<>();

        {
            clear_params();
        }

        private String lastElementName;

        public void clear_params() {
            this.params.put("name", null);
            this.params.put("x", null);
            this.params.put("y", null);
            this.params.put("creationDate", null);
            this.params.put("health", null);
            this.params.put("category", null);
            this.params.put("weaponType", null);
            this.params.put("meleeWeapon", null);
            this.params.put("chapter_name", null);
            this.params.put("parentLegion", null);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            this.lastElementName = qName;
            if (this.lastElementName.equals("element")) {
                this.params.put("id", attributes.getValue("id"));
            }
            if (this.lastElementName.equals("coordinates")) {
                this.params.put("x", attributes.getValue("x"));
                this.params.put("y", attributes.getValue("y"));
            }
            if (this.lastElementName.equals("chapter")) {
                this.params.put("chapter_name", attributes.getValue("name"));
                this.params.put("parentLegion", attributes.getValue("parentLegion"));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                switch (this.lastElementName) {
                    case "name": {
                        this.params.put("name", information);
                        break;
                    }
                    case "creationDate": {
                        this.params.put("creationDate", information);
                        break;
                    }
                    case "health": {
                        this.params.put("health", information);
                        break;
                    }
                    case "category": {
                        this.params.put("category", information);
                        break;
                    }
                    case "weaponType": {
                        this.params.put("weaponType", information);
                        break;
                    }
                    case "meleeWeapon": {
                        this.params.put("meleeWeapon", information);
                        break;
                    }
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws InputFormException {
            ArrayList<String> keys = new ArrayList<>(this.params.keySet());
            ArrayList<String> valuesNull = new ArrayList<>();
            for (String key : keys) {
                if (this.params.get(key) == null) {
                    valuesNull.add(key);
                }
            }
            boolean fill = valuesNull.isEmpty();
            if (fill && qName.equals("element")) {
                try {
                    int id = ElementFormConsole.checkId(this.params.get("id"));
                    String name = ElementFormConsole.checkName(this.params.get("name"));
                    Coordinates coordinates = ElementFormConsole.checkCoordinates(this.params.get("x") + " " + this.params.get("y"));
                    int health = ElementFormConsole.checkHealth(this.params.get("health"));
                    AstartesCategory category = ElementFormConsole.checkCategory(this.params.get("category"));
                    Date creationDate = ElementFormConsole.checkCreationDate(this.params.get("creationDate"));
                    Weapon weaponType = ElementFormConsole.checkWeaponType(this.params.get("weaponType"));
                    MeleeWeapon meleeWeapon = ElementFormConsole.checkMeleeWeapon(this.params.get("meleeWeapon"));
                    Chapter chapter = ElementFormConsole.checkChapter(this.params.get("chapter_name") + " " + this.params.get("parentLegion"));
                    this.collection.addFromFile(id, name, coordinates, creationDate, health,
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
