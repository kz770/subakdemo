package com.subakcinedemo.controller;

import com.subakcinedemo.action.InsertAction;
import com.subakcinedemo.action.MypageAction;
import com.subakcinedemo.action.SubakAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

@WebServlet("*.do")
public class MyPageController extends HttpServlet {
    HashMap<String,SubakAction> map;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        map=new HashMap<String,SubakAction>();
        String path = servletConfig.getServletContext().getRealPath("/WEB-INF");
        System.out.println(path);
        try {
            Reader reader=new FileReader(path+"/subakcine.properties");
            Properties prop = new Properties();
            prop.load(reader);
            Set keys = prop.keySet();
            Iterator iter = keys.iterator();
            while(iter.hasNext()){
                String key=(String)iter.next();
                String clsName = prop.getProperty(key);
                Object obj=Class.forName(clsName).newInstance();
                map.put(key, (SubakAction) obj);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        String path=request.getRequestURI();
        String uri=path.substring(path.lastIndexOf("/")+1);
        System.out.println(uri);
        SubakAction action=null;
        request.setCharacterEncoding("UTF-8");
        action = map.get(uri);

        String viewPage="/views/"+action.pro(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
        dispatcher.forward(request,response);
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
