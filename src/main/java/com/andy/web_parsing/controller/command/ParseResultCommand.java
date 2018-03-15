package com.andy.web_parsing.controller.command;

import com.andy.web_parsing.model.Book;
import com.andy.web_parsing.service.ReadXMLService;
import com.andy.web_parsing.service.ReadXMLServiceImpl;
import com.andy.web_parsing.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ParseResultCommand extends FrontCommand {

    private ReadXMLService readXMLService = new ReadXMLServiceImpl();

    @Override
    public void process() throws ServiceException, ServletException, IOException {

        request.setAttribute("books", getBooksForPage());
        request.setAttribute("pageCount", readXMLService.getPagesCount());

        forward("/view/parser_response.jsp");
    }

    private List<Book> getBooksForPage() throws ServiceException {

        HttpSession session = request.getSession(false);
        String parserType = (String) session.getAttribute("type");
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        return readXMLService.getBooksPart(currentPage, parserType);
    }
}
