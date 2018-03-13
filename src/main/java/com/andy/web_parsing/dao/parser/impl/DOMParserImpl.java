package com.andy.web_parsing.dao.parser.impl;

import com.andy.web_parsing.dao.parser.Parser;
import com.andy.web_parsing.dao.parser.impl.handler.BookDomHandler;
import com.andy.web_parsing.model.Book;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

public class DOMParserImpl implements Parser {

    public List<Book> getBooks(InputSource is) throws IOException, SAXException {

        DOMParser parser = new DOMParser();
        parser.parse(is);

        Document document = parser.getDocument();
        BookDomHandler domHandler = new BookDomHandler();

        return domHandler.process(document);
    }
}
