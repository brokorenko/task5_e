package com.andy.web_parsing.dao.parser.impl;

import com.andy.web_parsing.dao.parser.Parser;
import com.andy.web_parsing.dao.parser.impl.handler.BookSaxHandler;
import com.andy.web_parsing.model.Book;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParserImpl implements Parser {

    public List<Book> getBooks(InputSource is) throws SAXException, IOException {

        BookSaxHandler bookSaxHandler = new BookSaxHandler();

        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(bookSaxHandler);
        xmlReader.parse(is);

        return bookSaxHandler.getBooks();
    }
}
