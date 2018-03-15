package com.andy.web_parsing.controller.command;

import com.andy.web_parsing.service.exception.ServiceException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        this.context = context;
        this.request = request;
        this.response = response;
    }

    public abstract void process() throws ServletException, IOException, ServiceException;

    public void forward(String target) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(target);
        requestDispatcher.forward(request, response);
    }

    public void sendRedirect(String target) throws IOException {
        response.sendRedirect(target);
    }
}
