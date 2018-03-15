package com.andy.web_parsing.dao;

import com.andy.web_parsing.dao.exception.DAOException;
import com.andy.web_parsing.model.Book;

import java.util.List;

public interface DAO {
    int getPagesCount();
    List<Book> getBooksPart(int currentPage, String parserType) throws DAOException;
}
