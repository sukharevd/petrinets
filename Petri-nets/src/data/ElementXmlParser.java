package data;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import exceptions.WrongQNameException;
import exceptions.XmlArgumentException;
import exceptions.arcXYSizeException;

/**
 * Parses XML file to Data object.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ElementXmlParser extends DefaultHandler {
    private ElementSimpleFactory factory;

    private Data product;

    private Element curElement;

    public ElementXmlParser() {
        factory = new ElementSimpleFactory();
        ArrayList<Element> list = new ArrayList<Element>();
        product = new Data(list);
        curElement = null;
    }

    public Data build(final String path) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            SAXParser sp = spf.newSAXParser();
            sp.parse("file:" + path, this);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return product;
    }

    public void startElement(String namespaceURI, String localName,
            String qName, Attributes attrs) {
        if (qName == "project") {
            return;
        }

        Element el = null;
        try {
            el = factory.createElement(localName, qName, attrs, curElement);
        } catch (XmlArgumentException e) {
            JOptionPane.showMessageDialog(null, "Wrong argument of " + qName
                    + " node while parsing.", "Error of parsing",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } catch (arcXYSizeException e) {
            JOptionPane.showMessageDialog(null,
                    "Wrong set of X and Y in Arc node while parsing.",
                    "Error of parsing", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (WrongQNameException e) {
            JOptionPane.showMessageDialog(null, "Wrong node (" + qName
                    + ") while parsing.", "Error of parsing",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (qName == "Arc") {
            curElement.getOutputArcs().add((Arc) el);
        } else {
            product.add(el);
            curElement = el;
        }
    }

    public void endElement(String namespaceURI, String localName, String qName) {
        // if (qName != "Arc") {
        // fbuilder.endCreateBuiltFigure();
        // }
    }
}