package com.subakcinedemo.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface SubakAction {
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
