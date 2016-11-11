package com.fuicuiedu.idedemo.interview_demo.xml;


import android.util.Log;
import android.widget.Toast;

import com.fuicuiedu.idedemo.interview_demo.entity.Book;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2016/11/10 0010.
 */

//用SaxParserFactory取得SaxParser的实例，用自定义事件处理逻辑来解析数据
public class SaxBookParser {

    public List<Book> parser(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        //取得SaxParserFactory
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        //用SaxParserFactory取得SaxParser的实例
        SAXParser saxParser = saxParserFactory.newSAXParser();
        //实例化自定的Handler（自定义事件处理逻辑）
        MyHandler myHandler = new MyHandler();
        //根据西丁一Handler的规则解析输入流
        saxParser.parse(inputStream,myHandler);

        return myHandler.getBooks();

    }

    //自定义Handler继承DefaultHandler（自定义事件处理逻辑）
    private class MyHandler extends DefaultHandler{
        private List<Book> books;
        private Book book;
        private StringBuilder builder;


        //返回解析后得到的Book对象集合
        public List<Book> getBooks(){
            return books;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            books = new ArrayList<>();
            builder = new StringBuilder();
        }

        //当执行文档时遇到起始节点，startElement将会被调用，我们可以获取起始节点的相关信息
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equals("book")){
                book = new Book();
                //for循环找到节点内所有属性
                for (int i = 0;i<attributes.getLength();i++){
                    //判断属性的名称
                    if(attributes.getQName(i).equals("id")){
                        //拿到id的值
                        String id = attributes.getValue(i);
                        book.setId(Integer.parseInt(id));
                    }
                }
            }

            //将字符长度设置为0，以便重新开始读取元素内的字符节点
            builder.setLength(0);
        }

        //然后characters方法被调用，我们可以获取节点内的文本信息
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            //将读取到的字符数组追加到builder中
            builder.append(ch,start,length);
        }

        //最后endElement方法被调用，我们可以做收尾的相关工作
        @Override
        public void endElement(String uri, String calName, String qName) throws SAXException {
            super.endElement(uri, calName, qName);
            if (calName.equals("id")){
                book.setId(Integer.parseInt(builder.toString()));
            }else if (calName.equals("name")){
                book.setName(builder.toString());
            }else if (calName.equals("price")){
                book.setPrice(Float.parseFloat(builder.toString()));
            }else if (calName.equals("book")){
                //book构建好后添加到我的集合中
                books.add(book);
            }
        }
    }
}
