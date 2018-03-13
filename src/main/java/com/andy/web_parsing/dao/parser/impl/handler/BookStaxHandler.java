package com.andy.web_parsing.dao.parser.impl.handler;

import com.andy.web_parsing.dao.parser.impl.BookTagName;
import com.andy.web_parsing.model.Book;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BookStaxHandler {
   public List<Book> process(XMLStreamReader reader) throws XMLStreamException {

        Book book = null;
        BookTagName elementName = null;
        List<Book> books = new ArrayList<>();

        while (reader.hasNext()){

            int type = reader.next();

            switch (type){
                case XMLStreamReader.START_ELEMENT:
                    elementName = BookTagName.valueOf(reader.getLocalName().toUpperCase());
                    switch (elementName){
                        case BOOK:
                            book = new Book();
                            book.setCategory(reader.getAttributeValue(null, "category"));
                            break;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()){
                        break;
                    }
                    switch (elementName){
                        case TITLE:
                            book.setTitle(text);
                            break;
                        case YEAR:
                            book.setYear(Integer.parseInt(text));
                            break;
                        case PRICE:
                            book.setPrice(Double.valueOf(text));
                            break;
                        case AUTHOR:
                            book.setAuthor(text);
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    elementName = BookTagName.valueOf(reader.getLocalName().toUpperCase());
                    switch (elementName){
                        case BOOK:
                            books.add(book);
                            break;
                    }
                    break;
            }
        }
        return books;
    }
}
