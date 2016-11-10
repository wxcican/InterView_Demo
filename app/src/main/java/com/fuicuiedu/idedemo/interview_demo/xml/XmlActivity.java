package com.fuicuiedu.idedemo.interview_demo.xml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fuicuiedu.idedemo.interview_demo.R;
import com.fuicuiedu.idedemo.interview_demo.entity.Book;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class XmlActivity extends AppCompatActivity {
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick({R.id.xml_sax_btn,R.id.xml_dom_btn,R.id.xml_pull_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.xml_sax_btn:
                SAXparser();
                break;
            case R.id.xml_dom_btn:
                Toast.makeText(getApplicationContext(),"domBtn",Toast.LENGTH_SHORT).show();
                break;
            case R.id.xml_pull_btn:
                Toast.makeText(getApplicationContext(),"pullBtn",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //Sax解析XMl
    private void SAXparser(){
        try {
            InputStream inputStream = getAssets().open("Books.xml");
            SaxBookParser saxBookParser = new SaxBookParser();
            List<Book> books = saxBookParser.parser(inputStream);
            for (Book book : books){
                Log.e("SAXparser",book.toString());
            }
        } catch (Exception e) {
        }
    }


}
