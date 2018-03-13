package com.andy.web_parsing.dao.parser;

import com.andy.web_parsing.model.Book;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface Parser {
    List<Book> getBooks(InputSource is) throws IOException, SAXException, XMLStreamException;
}
