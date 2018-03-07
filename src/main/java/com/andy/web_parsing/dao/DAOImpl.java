package com.andy.web_parsing.dao;

import com.andy.web_parsing.dao.exception.DAOException;
import com.andy.web_parsing.dao.parser.DParser;
import com.andy.web_parsing.dao.parser.SParser;
import com.andy.web_parsing.dao.parser.StParser;
import com.andy.web_parsing.dao.util.FilenameReader;
import com.andy.web_parsing.model.Book;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements DAO {

    private enum ParserType{SAX, STAX, DOM}

    private static List<Book> books = new ArrayList<>();
    private static File file = new File(FilenameReader.readFilename());

    private static long fileLastModified;

    public void parseBookFile(String parserType) throws DAOException {

        fileLastModified = file.lastModified();

        InputSource inputSource = null;

        try {
            inputSource = new InputSource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new DAOException(e.getMessage() + "in DAO");
        }

        switch (ParserType.valueOf(parserType.toUpperCase())){
            case SAX:
                try {
                    books = SParser.getBooks(inputSource);
                } catch (SAXException | IOException e) {
                    throw new DAOException(e.getMessage()+ "in SAX parser");
                }
                break;
            case STAX:
                try {
                    books = StParser.getBooks(inputSource);
                } catch (FileNotFoundException | XMLStreamException e) {
                    throw new DAOException(e.getMessage() + "in STAX parser");
                }
                break;
            case DOM:
                try {
                    books = DParser.getBooks(inputSource);
                } catch (IOException | SAXException e) {
                    throw new DAOException(e.getMessage()+ "in DOM parser");
                }
                break;
        }
    }

    public int getPagesCount() throws DAOException {
        int pageCount;

        if (!fileIsValid()) {
            parseBookFile("dom");
        }

        pageCount = books.size() / 10;

        return pageCount;
    }

    public List<Book> getBooksPart(int currentPage) throws DAOException {
        if (!fileIsValid()) {
            parseBookFile("dom");
        }
        if (currentPage > books.size()/10){
            currentPage = books.size()/10;
        }
        int startElement = currentPage*10;
        int endElement = currentPage*10 + 10 > books.size() ? books.size() : currentPage*10 + 10;
        return books.subList(startElement, endElement);

    }

    private boolean fileIsValid() {
        return file.lastModified() == fileLastModified;
    }
}
