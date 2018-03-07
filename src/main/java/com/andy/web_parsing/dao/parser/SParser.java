package com.andy.web_parsing.dao.parser;

import com.andy.web_parsing.model.Book;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SParser {
    public static List<Book> getBooks(InputSource is) throws SAXException, IOException {

        Handler handler = new Handler();

        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(is);

        return handler.getBooks();
    }
}
