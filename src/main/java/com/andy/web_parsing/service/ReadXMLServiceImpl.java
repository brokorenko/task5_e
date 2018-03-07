package com.andy.web_parsing.service;

import com.andy.web_parsing.dao.DAO;
import com.andy.web_parsing.dao.DAOImpl;
import com.andy.web_parsing.dao.exception.DAOException;
import com.andy.web_parsing.model.Book;
import com.andy.web_parsing.service.exception.ServiceException;

import java.util.List;

public class ReadXMLServiceImpl implements ReadXMLService {

    private DAO dao = new DAOImpl();

    public void readXML(String parserType) throws ServiceException {
        try {
            dao.parseBookFile(parserType);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Book> getBooksPart(int currentPage) throws ServiceException {
        try {
            return dao.getBooksPart(currentPage);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }


    public int getPagesCount() throws ServiceException {
        try {
            return dao.getPagesCount();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
