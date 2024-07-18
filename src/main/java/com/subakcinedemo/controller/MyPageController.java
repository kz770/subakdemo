package com.subakcinedemo.controller;

import com.subakcinedemo.action.InsertAction;
import com.subakcinedemo.action.SubakAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("*.do")
public class MyPageController extends HttpServlet {
    public void pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        String path=request.getRequestURI();
        String uri=path.substring(path.lastIndexOf("/")+1);
        System.out.println(uri);

        SubakAction action=null;
        if (uri.equals("signup.do")){
            action=new InsertAction();
        }else{

        }
        String viewPage=action.pro(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pro(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pro(request,response);

    }
}
