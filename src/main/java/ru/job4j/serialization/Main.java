package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        Book book = new Book(false, 30, "Winter",
                new Contact(3412, "11-111"), "Free", "Busy");
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Book.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            try {
                marshaller.marshal(book, writer);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }


        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            Book result = null;
            try {
                result = (Book) unmarshaller.unmarshal(reader);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        }
    }

}
