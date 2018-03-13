package com.andy.web_parsing.dao.parser.impl;

import com.andy.web_parsing.dao.parser.Parser;
import com.andy.web_parsing.dao.parser.impl.handler.BookStaxHandler;
import com.andy.web_parsing.model.Book;
import org.xml.sax.InputSource;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class StaxParserImpl implements Parser {

    public List<Book> getBooks(InputSource is) throws FileNotFoundException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = is.getByteStream();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);

        BookStaxHandler staxHandler = new BookStaxHandler();

        return staxHandler.process(xmlStreamReader);
    }



}
