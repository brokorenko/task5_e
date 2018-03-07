package com.andy.web_parsing.service;

import com.andy.web_parsing.model.Book;
import com.andy.web_parsing.service.exception.ServiceException;

import java.util.List;

public interface ReadXMLService {
    void readXML(String parserType) throws ServiceException;
    List<Book> getBooksPart(int currentPage) throws ServiceException;
    int getPagesCount() throws ServiceException;
}
