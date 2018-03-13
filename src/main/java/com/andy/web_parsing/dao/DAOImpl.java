package com.andy.web_parsing.dao;

import com.andy.web_parsing.dao.exception.DAOException;
import com.andy.web_parsing.dao.parser.*;
import com.andy.web_parsing.dao.util.FilenameReader;
import com.andy.web_parsing.model.Book;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements DAO {

    private static List<Book> books = new ArrayList<>();
    private static File file = new File(FilenameReader.readFilename());

    private static long fileLastModified;

    private static final int BOOKS_ON_PAGE_COUNT = 10;

    public void parseBookFile(String parserType) throws DAOException {

        fileLastModified = file.lastModified();

        InputSource inputSource;
        ParserFactory parserFactory = ParserFactory.getInstance();
        Parser parser = parserFactory.getParser(parserType);

        try {
            inputSource = new InputSource(new FileInputStream(file));
            books = parser.getBooks(inputSource);
        } catch (IOException | XMLStreamException | SAXException e) {
            throw new DAOException(e.getMessage()+ "in DAO method parseBookFile");
        }

    }

    public int getPagesCount() throws DAOException {
        int pageCount;

        if (!fileIsValid()) {
            parseBookFile("dom");
        }

        pageCount = books.size() / BOOKS_ON_PAGE_COUNT;

        return pageCount;
    }

    public List<Book> getBooksPart(int currentPage) throws DAOException {
        if (!fileIsValid()) {
            parseBookFile("dom");
        }
        if (currentPage > books.size()/BOOKS_ON_PAGE_COUNT){
            currentPage = books.size()/BOOKS_ON_PAGE_COUNT;
        }
        int startElement = currentPage*BOOKS_ON_PAGE_COUNT;
        int endElement = currentPage*BOOKS_ON_PAGE_COUNT + BOOKS_ON_PAGE_COUNT > books.size() ? books.size() : currentPage*10 + 10;
        return books.subList(startElement, endElement);

    }

    private boolean fileIsValid() {
        return file.lastModified() == fileLastModified;
    }
}
