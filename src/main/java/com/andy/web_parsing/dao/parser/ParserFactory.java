package com.andy.web_parsing.dao.parser;

import com.andy.web_parsing.dao.parser.impl.DOMParserImpl;
import com.andy.web_parsing.dao.parser.impl.SaxParserImpl;
import com.andy.web_parsing.dao.parser.impl.StaxParserImpl;

public class ParserFactory {

    private enum ParserType{SAX, STAX, DOM}
    private static ParserFactory instance = new ParserFactory();

    private ParserFactory() {
    }

    public static ParserFactory getInstance(){
        return instance;
    }

    public Parser getParser(String parserType){

        switch (ParserType.valueOf(parserType.toUpperCase())){
            case SAX:
                return new SaxParserImpl();
            case STAX:
                return new StaxParserImpl();
            case DOM:
                return new DOMParserImpl();
            default:
                return new SaxParserImpl();

        }
    }
}
