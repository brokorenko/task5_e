package com.andy.web_parsing.dao.parser;

import com.andy.web_parsing.model.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {

    private Book book;
    private StringBuilder content;
    private List<Book> books = new ArrayList<Book>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        content = new StringBuilder();
        if (qName.equals("book")){
            book = new Book();
            book.setCategory(attributes.getValue("category"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (BookTagName.valueOf(qName.toUpperCase())){
            case TITLE:
                book.setTitle(String.valueOf(content));
                break;
            case YEAR:
                book.setYear(Integer.parseInt(content.toString()));
                break;
            case PRICE:
                book.setPrice(Double.valueOf(content.toString()));
                break;
            case AUTHOR:
                book.setAuthor(String.valueOf(content));
                break;
            case BOOK:
                books.add(book);
                book = null;
                break;
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}
