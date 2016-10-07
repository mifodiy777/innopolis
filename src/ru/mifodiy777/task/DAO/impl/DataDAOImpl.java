package ru.mifodiy777.task.DAO.impl;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import ru.mifodiy777.task.DAO.DataDAO;
import ru.mifodiy777.task.entity.Data;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by innopolis on 04.10.16.
 */
public class DataDAOImpl implements DataDAO {


    public Data read(String src) throws IOException {
        try {
            File inputFile = new File(src);
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputFile);
            Element classElement = document.getRootElement();
            List<Node> nodes = document.selectNodes("/class/student");
            for (Node node : nodes) {

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void write(String data) throws IOException {
        try {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement( "cars" );
            Element supercarElement= root.addElement("supercars")
                    .addAttribute("company", "Ferrai");

            supercarElement.addElement("carname")
                    .addAttribute("type", "Ferrari 101")
                    .addText("Ferrari 101");

            supercarElement.addElement("carname")
                    .addAttribute("type", "sports")
                    .addText("Ferrari 202");

            // Pretty print the document to System.out
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer;
            writer = new XMLWriter( System.out, format );
            writer.write( document );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
