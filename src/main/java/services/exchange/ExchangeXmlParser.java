package services.exchange;

public class ExchangeXmlParser {
    public double getUsdToRub(){
        NodeList nodeList = document.getElementsByTagName("elementName");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String value = element.getTextContent();
            System.out.println("Value: " + value);
        }
    }
}
