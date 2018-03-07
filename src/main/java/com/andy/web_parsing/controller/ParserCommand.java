package com.andy.web_parsing.controller;

import com.andy.web_parsing.service.ReadXMLService;
import com.andy.web_parsing.service.ReadXMLServiceImpl;
import com.andy.web_parsing.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ParserCommand implements ICommand {

    private ReadXMLService readXMLService = new ReadXMLServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            readXMLService.readXML(req.getParameter("type"));
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            throw new ServletException();
        }

        resp.sendRedirect("/FrontController?command=parsing_result");
    }
}
