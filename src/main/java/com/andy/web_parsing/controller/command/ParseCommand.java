package com.andy.web_parsing.controller.command;

import com.andy.web_parsing.service.ReadXMLService;
import com.andy.web_parsing.service.ReadXMLServiceImpl;
import com.andy.web_parsing.service.exception.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;

public class ParseCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException, ServiceException {
        ReadXMLService service = new ReadXMLServiceImpl();
        service.readXML(request.getParameter("type"));
        sendRedirect("/FrontController?command=ParseResult");
    }
}
