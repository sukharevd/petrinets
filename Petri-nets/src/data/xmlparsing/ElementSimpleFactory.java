package data.xmlparsing;

import java.util.ArrayList;
import java.util.Properties;

import org.xml.sax.Attributes;

import data.elements.Arc;
import data.elements.Element;
import data.elements.Place;
import data.elements.Transition;
import exceptions.MissedXmlArgumentException;
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
            throws XmlArgumentException, arcXYSizeException,
            WrongQNameException, MissedXmlArgumentException {

        Properties pr = new java.util.Properties();
        pr.setProperty("name", qName);
        if (attrs != null) {
            for (int i = 0; i < attrs.getLength(); i++) {
                pr.setProperty(attrs.getQName(i), attrs.getValue(i));
            }
        }

        if (qName == "Place") {
            try {
                int x = Integer.valueOf(pr.getProperty("x"));
                int y = Integer.valueOf(pr.getProperty("y"));
                int no = Integer.valueOf(pr.getProperty("no"));
                int numTokens = Integer.valueOf(pr.getProperty("numTokens"));
                return new Place(numTokens, no, x, y);
            } catch (NullPointerException e) {
                throw new MissedXmlArgumentException();
            }
        } else {
            if (qName == "Transition") {
                try {
                    int x = Integer.valueOf(pr.getProperty("x"));
                    int y = Integer.valueOf(pr.getProperty("y"));
                    int no = Integer.valueOf(pr.getProperty("no"));
                    double lyambda = Double.valueOf(pr.getProperty("lyambda"));
                    double g = Double.valueOf(pr.getProperty("g"));
                    double r = Double.valueOf(pr.getProperty("r"));
                    // TODO: somevar
                    // double somevar = Double.valueOf(pr.getProperty("somevar"));
                    // TODO: low
                    // String low = pr.getProperty("low"); 
                    return new Transition(lyambda, g, r, null, no, x, y);
                } catch (NullPointerException e) {
                    throw new MissedXmlArgumentException();
                }
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
                                    throw new XmlArgumentException();
                                }
                            }
                        }
                    }
                    if (xseq.size() != yseq.size()) {
                        throw new arcXYSizeException();
                    }
                    String toType;
                    if (prevElement instanceof Place) {
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