package com.andy.web_parsing.controller.command;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ParseCommand extends FrontCommand {

    @Override
    public void process() throws IOException {

        addParserTypeInSession();

        sendRedirect("/FrontController?command=ParseResult&currentPage=0");
    }

    private void addParserTypeInSession() {

        String parserType = request.getParameter("type");
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("type", parserType);
    }
}
