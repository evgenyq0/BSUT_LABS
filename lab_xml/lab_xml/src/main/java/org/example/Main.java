package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Загрузка и парсинг XML-файла
            File xmlFile = new File("library.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // 2. Создание списка книг
            List<Book> books = new ArrayList<>();
            NodeList nodeList = doc.getElementsByTagName("book");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getAttribute("id");
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String author = element.getElementsByTagName("author").item(0).getTextContent();
                    int year = Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent());
                    String genre = element.getElementsByTagName("genre").item(0).getTextContent();
                    BigDecimal price = new BigDecimal(element.getElementsByTagName("price").item(0).getTextContent());

                    books.add(new Book(id, title, author, year, genre, price));
                }
            }

            // 3. Вывод всех книг
            System.out.println("Все книги в библиотеке:");
            books.forEach(System.out::println);

            // 4. Вычисление средней цены
            BigDecimal averagePrice = books.stream()
                    .map(Book::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(BigDecimal.valueOf(books.size()), 2, BigDecimal.ROUND_HALF_UP);
            System.out.printf("\nСредняя цена книг: %.2f\n", averagePrice);

            // 5. Фильтрация книг
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nФильтрация книг:");
            System.out.println("1 - по жанру, 2 - по году");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.print("Введите жанр: ");
                String genreFilter = scanner.nextLine();
                System.out.println("Книги в жанре '" + genreFilter + "':");
                books.stream()
                        .filter(book -> book.getGenre().equalsIgnoreCase(genreFilter))
                        .forEach(System.out::println);
            } else if (choice == 2) {
                System.out.print("Введите год: ");
                int yearFilter = scanner.nextInt();
                System.out.println("Книги, изданные в " + yearFilter + " году:");
                books.stream()
                        .filter(book -> book.getYear() == yearFilter)
                        .forEach(System.out::println);
            }

        } catch (Exception e) {
            System.err.println("Ошибка при обработке XML-файла: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

