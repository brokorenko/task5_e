package com.andy.web_parsing.controller;

import com.andy.web_parsing.service.ReadXMLService;
import com.andy.web_parsing.service.ReadXMLServiceImpl;
import com.andy.web_parsing.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ParserResultCommand implements ICommand {

    private ReadXMLService readXMLService = new ReadXMLServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage  = 0;
        if (req.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }

        try {
            req.setAttribute("books", readXMLService.getBooksPart(currentPage));
            req.setAttribute("pageCount", readXMLService.getPagesCount());
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            throw new ServletException();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/parser_responce.jsp");
        dispatcher.forward(req, resp);
    }
}
