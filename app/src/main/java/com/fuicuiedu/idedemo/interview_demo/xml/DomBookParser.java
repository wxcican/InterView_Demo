package com.fuicuiedu.idedemo.interview_demo.xml;

import android.util.Log;

import com.fuicuiedu.idedemo.interview_demo.entity.Book;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class DomBookParser {

    public List<Book> parse(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        List<Book> books = new ArrayList<>();

        //取得DocumentBuliderFactory实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //获取DocumentBuilder实例
        DocumentBuilder builder = factory.newDocumentBuilder();
        //得到Document实例
        Document doc = builder.parse(inputStream);
        //获取Document中所有元素
        Element rootElement = doc.getDocumentElement();
        //获取元素中的节点列表
        NodeList items = rootElement.getElementsByTagName("book");

        for (int i = 0;i<items.getLength();i++){
            Book book = new Book();
            //拿到单个的一个节点“book”
            Node item = items.item(i);

            for (int k = 0;k<item.getAttributes().getLength();k++){
                if (item.getAttributes().item(k).getNodeName().equals("aaa")){
                    Log.e("aaa","aaa = " + item.getAttributes().item(k).getNodeValue());
                }
            }

            //获取节点的子节点列表
            NodeList properties = item.getChildNodes();
            for (int j = 0;j<properties.getLength();j++){
                //拿到一个子节点
                Node property = properties.item(j);
                //获取子节点名称
                String nodeName = property.getNodeName();
                if (nodeName.equals("id")){
                    book.setId(Integer.parseInt(property.getFirstChild().getNodeValue()));
                }else if (nodeName.equals("name")){
                    book.setName(property.getFirstChild().getNodeValue());
                }else if (nodeName.equals("price")){
                    book.setPrice(Float.parseFloat(property.getFirstChild().getNodeValue()));
                }

            }
            books.add(book);
        }
        return books;
    }
}
