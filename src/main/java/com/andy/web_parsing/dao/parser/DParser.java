package com.andy.web_parsing.dao.parser;

import com.andy.web_parsing.model.Book;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DParser {

    public static List<Book> getBooks(InputSource is) throws IOException, SAXException {

        DOMParser parser = new DOMParser();
        parser.parse(is);

        Document document = parser.getDocument();
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("book");

        Book book;
        List<Book> books = new ArrayList<Book>();

        for (int i = 0; i < nodeList.getLength(); i++) {

            Element bookElement = (Element) nodeList.item(i);

            book = new Book();
            book.setCategory(bookElement.getAttribute("category"));
            book.setTitle(getSingleChild(bookElement, "title").getTextContent().trim());
            book.setAuthor(getSingleChild(bookElement, "author").getTextContent().trim());
            book.setYear(Integer.parseInt(getSingleChild(bookElement, "year").getTextContent().trim()));
            book.setPrice(Double.parseDouble(getSingleChild(bookElement, "price").getTextContent().trim()));

            books.add(book);
        }

        return books;
    }

    private static Element getSingleChild(Element bookElement, String tagName){
        NodeList nodeList = bookElement.getElementsByTagName(tagName);
        return (Element) nodeList.item(0);
    }
}
