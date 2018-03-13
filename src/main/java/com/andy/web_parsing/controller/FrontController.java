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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

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
            Class type = Class.forName(String.format("com.andy.web_parsing.controller.command.%sCommand", request.getParameter("command")));
            return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            return new UnknownCommand();
        }
    }
}
