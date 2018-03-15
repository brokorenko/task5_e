package com.andy.web_parsing.controller;

import com.andy.web_parsing.controller.command.FrontCommand;
import com.andy.web_parsing.controller.command.UnknownCommand;
import com.andy.web_parsing.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    private static final String COMMAND_PATH_PATTERN = "com.andy.web_parsing.controller.command.%sCommand";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FrontCommand frontCommand = getCommand(req);
        frontCommand.init(getServletContext(), req, resp);

        try {
            frontCommand.process();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            throw new ServletException();
        }
    }

    private FrontCommand getCommand(ServletRequest request){

        try {
            Class type = Class.forName(String.format(COMMAND_PATH_PATTERN, request.getParameter("command")));
            return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            return new UnknownCommand();
        }
    }
}
