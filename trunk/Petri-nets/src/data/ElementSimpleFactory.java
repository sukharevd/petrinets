package data;

import java.util.ArrayList;
import java.util.Properties;

import org.xml.sax.Attributes;


import exceptions.WrongQNameException;
import exceptions.XmlArgumentException;
import exceptions.arcXYSizeException;

/**
 * Generate new Element from XML file tag.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ElementSimpleFactory {
    // protected BuiltElement curBuiltFigure;

    // public ElementSimpleFactory() {
    // throw new UnsupportedOperationException();
    // }

    public Element createElement(java.lang.String localName,
            java.lang.String qName, Attributes attrs, Element prevElement)
            throws XmlArgumentException, arcXYSizeException, WrongQNameException {

        Properties pr = new java.util.Properties();
        pr.setProperty("name", qName);
        if (attrs != null) {
            for (int i = 0; i < attrs.getLength(); i++) {
                pr.setProperty(attrs.getQName(i), attrs.getValue(i));
            }
        }

        if (qName == "Place") {
            int x = Integer.valueOf(pr.getProperty("x"));
            int y = Integer.valueOf(pr.getProperty("y"));
            int no = Integer.valueOf(pr.getProperty("no"));
            int numTokens = Integer.valueOf(pr.getProperty("numTokens"));
            return new Place(numTokens, no, x, y);
        } else {
            if (qName == "Transition") {
                int x = Integer.valueOf(pr.getProperty("x"));
                int y = Integer.valueOf(pr.getProperty("y"));
                int no = Integer.valueOf(pr.getProperty("no"));
                double lyambda = Double.valueOf(pr.getProperty("lyambda"));
                double g = Double.valueOf(pr.getProperty("g"));
                return new Transition(lyambda, g, null, no, x, y);
            } else {
                if (qName == "Arc") {
                    ArrayList<Integer> xseq = new ArrayList<Integer>();
                    ArrayList<Integer> yseq = new ArrayList<Integer>();
                    int to = 0;

                    for (int i = 0; i < attrs.getLength(); i++) {
                        if (attrs.getQName(i).charAt(0) == 'x') {
                            xseq.add(Integer.valueOf(attrs.getValue(i)));
                        } else {
                            if (attrs.getQName(i).charAt(0) == 'y') {
                                yseq.add(Integer.valueOf(attrs.getValue(i)));
                            } else {
                                if (attrs.getQName(i) == "to") {
                                    to = Integer.valueOf(attrs.getValue(i));
                                } else {
                                    throw new exceptions.XmlArgumentException();
                                }
                            }
                        }
                    }
                    if (xseq.size() != yseq.size()) {
                        throw new exceptions.arcXYSizeException();
                    }
                    String toType;
                    if (prevElement.getType() == "P") {
                        toType = "T";
                    } else {
                        toType = "P";
                    }
                    return new Arc(xseq, yseq, to, toType);
                }

            }
        }

        throw new WrongQNameException();
    }
}