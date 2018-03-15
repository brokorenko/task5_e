package com.andy.web_parsing.dao;

import com.andy.web_parsing.dao.exception.DAOException;
import com.andy.web_parsing.dao.parser.*;
import com.andy.web_parsing.dao.util.FilenameReader;
import com.andy.web_parsing.model.Book;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.List;

public class DAOImpl implements DAO {

    private ParserFactory parserFactory = ParserFactory.getInstance();
    private File file = new File(FilenameReader.readFilename());
    private int pagesCount = 0;

    private InputSource inputSource;
    private Parser parser;

    private static final int RECORDS_ON_PAGE = 10;

    public List<Book> getBooksPart(int currentPage, String parserType) throws DAOException {

        List<Book> books;

        if (currentPage > pagesCount){ //вынести
            currentPage = pagesCount;
        }

        try {
            inputSource = new InputSource(new FileInputStream(file));
            parser = parserFactory.getParser(parserType);

            books = parser.getBooks(inputSource);
            pagesCount = books.size()/RECORDS_ON_PAGE;
        } catch (IOException | XMLStreamException | SAXException e) {
            throw new DAOException(e.getMessage()+ "in DAO method parseBookFile");
        }

        return books.subList(getStartElement(currentPage), getEndElement(currentPage, books.size()));
    }

    public int getPagesCount() {
        return pagesCount;
    }

    private int getStartElement(int currentPage) {
        return currentPage*RECORDS_ON_PAGE;
    }

    private int getEndElement(int currentPage, int booksSize) {
        return currentPage*RECORDS_ON_PAGE + RECORDS_ON_PAGE > booksSize ? booksSize : currentPage*RECORDS_ON_PAGE + RECORDS_ON_PAGE;
    }

}
