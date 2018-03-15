package com.andy.web_parsing.service;

import com.andy.web_parsing.model.Book;
import com.andy.web_parsing.service.exception.ServiceException;

import java.util.List;

public interface ReadXMLService {
    List<Book> getBooksPart(int currentPage, String parserType) throws ServiceException;
    int getPagesCount();
}
