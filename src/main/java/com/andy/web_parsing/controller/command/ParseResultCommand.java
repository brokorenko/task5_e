package com.andy.web_parsing.controller.command;

import com.andy.web_parsing.service.ReadXMLService;
import com.andy.web_parsing.service.ReadXMLServiceImpl;
import com.andy.web_parsing.service.exception.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;

public class ParseResultCommand extends FrontCommand {
    @Override
    public void process() throws IOException, ServletException, ServiceException {

        ReadXMLService readXMLService = new ReadXMLServiceImpl();

        int currentPage  = 0;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        request.setAttribute("books", readXMLService.getBooksPart(currentPage));
        request.setAttribute("pageCount", readXMLService.getPagesCount());
        forward("/view/parser_responce.jsp");
    }
}
