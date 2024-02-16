package io.github.zeculesu.itmo.prog5.manager;

import io.github.zeculesu.itmo.prog5.data.CollectionAction;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ParseFileXML implements ParseFileCollection {
    private static CollectionAction collection;

    public static CollectionAction parseFile(String filePath, CollectionAction collection) throws ParserConfigurationException, SAXException, IOException {
        ParseFileXML.collection = collection;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        AdvancedXMLHandler handler = new AdvancedXMLHandler();
        parser.parse(new File(filePath), handler);
        return ParseFileXML.collection;
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
        public void endElement(String uri, String localName, String qName) throws SAXException {
            ArrayList<String> keys = new ArrayList<>(params.keySet());
            ArrayList<String> valuesNull = new ArrayList<>();
            for (String key : keys) {
                if (params.get(key) == null) {
                    valuesNull.add(key);
                }
            }
            boolean fill = valuesNull.isEmpty();
            if (fill && qName.equals("element")) {
                //int id = Integer.parseInt(params.get("id"));
                //todo доделать преобразование данных
                System.out.println(params);
                clear_params();
            }
            else if (!fill && qName.equals("element")){
                System.out.println("Неправильный ввод параметров коллекции");
                for (String val :valuesNull){
                    System.out.println("Отсутствует поле : " + val);
                }
            }
        }
    }
}
