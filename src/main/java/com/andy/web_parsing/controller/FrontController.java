package com.andy.web_parsing.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String command = req.getParameter("command");
        ICommand iCommand = null;

        switch (CommandType.valueOf(command.toUpperCase())){
            case PARSER:
                iCommand = new ParserCommand();
                break;
            case PARSING_RESULT:
                iCommand = new ParserResultCommand();
                break;
        }

        iCommand.execute(req, resp);
    }
}
