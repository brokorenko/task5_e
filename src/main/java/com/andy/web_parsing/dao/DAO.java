package com.andy.web_parsing.dao;

import com.andy.web_parsing.dao.exception.DAOException;
import com.andy.web_parsing.model.Book;

import java.util.List;

public interface DAO {
    void parseBookFile(String parserType) throws DAOException;
    int getPagesCount() throws DAOException;
    List<Book> getBooksPart(int currentPage) throws DAOException;
}
