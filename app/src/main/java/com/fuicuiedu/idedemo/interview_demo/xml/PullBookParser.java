package com.fuicuiedu.idedemo.interview_demo.xml;

import android.util.Xml;

import com.fuicuiedu.idedemo.interview_demo.entity.Book;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class PullBookParser {

    public List<Book> parse(InputStream inputStream) throws XmlPullParserException, IOException {
        List<Book> books = null;
        Book book = null;

        //直接用XMl创建一个XMlPullParser实例
        XmlPullParser parser = Xml.newPullParser();
        //设置输入流，并且指明编码方式
        parser.setInput(inputStream,"UTF-8");

        //我们需要自己获取产生的事件然后做出相应的操作
        int eventType = parser.getEventType();
        //判断事件的类型做出相应操作
        while (eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType){
                //文件开始
                case XmlPullParser.START_DOCUMENT:
                    books = new ArrayList<>();
                    break;
                //开始解析
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("book")){
                        book = new Book();
                    }else if (parser.getName().equals("id")){
                        eventType = parser.next();
                        book.setId(Integer.parseInt(parser.getText()));
                    }else if (parser.getName().equals("name")){
                        eventType = parser.next();
                        book.setName(parser.getText());
                    }else if (parser.getName().equals("price")){
                        eventType = parser.next();
                        book.setPrice(Float.parseFloat(parser.getText()));
                    }
                    break;
                //结束解析
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("book")){
                        books.add(book);
                        book = null;
                    }
                    break;
            }
            //判断下一个事件是否是文件结束，有则循环
            eventType = parser.next();
        }
        return books;
    }
}
