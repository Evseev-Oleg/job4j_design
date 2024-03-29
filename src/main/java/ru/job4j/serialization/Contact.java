package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;
import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "contact")
public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = -706562685481916198L;
    @XmlAttribute
    private int zipCode;
    @XmlAttribute
    private String phone;

    public Contact() {
    }

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{" + "zipCode="
                + zipCode + ", phone='"
                + phone + '\'' + '}';
    }

    public static void main(String[] args) throws IOException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        File tempFile = Files.createTempFile(null, null).toFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile))) {
            oos.writeObject(contact);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile))) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
