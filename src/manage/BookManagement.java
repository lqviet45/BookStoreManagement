package manage;
import validation.Validation;
import Object.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class BookManagement implements ManagementI{
    private static final List<Book> listB = new ArrayList<>();
    private String bookID, bookName, status;
    private double bookPrice;
    private int bookQuantity;
    private String publisherBookID;

    public static List<Book> getListB() {
        return listB;
    }

      
    @Override
    public void create() {   
        bookID = Validation.getBookCode("Enter book's ID: ", "Please input id with format: 'Bxxxxx' and ID must not duplicate!!", listB, 1);
        bookName = Validation.getString("Enter book's name: ", "PLEASE INPUT A NAME WITH 5 -> 30 CHARACTERS!!", "[A-Za-z 0-9]{5,30}");
        bookPrice = Validation.getDouble("Enter book's price: ", 0, Double.MAX_VALUE);
        bookQuantity = Validation.getInt("Enter book's quantity: ", 0, Integer.MAX_VALUE);
        //method getStatus(1) is not allow input null
        status = Validation.getStatus(1);
        
        //call method from Validation use Publisher list to check id.
        publisherBookID = Validation.getPublisherCode("Enter publisher's Id of the book: ", "Publisher’s Id is not found", PublisherManagement.getListP(), 2);
     
        listB.add(new Book(bookID, bookName, bookPrice, bookQuantity, status, publisherBookID));
        saveToFile();
        System.out.println("ADD SUCCESS");
    }
    
    public void searchBook() {
        if(listB.isEmpty()) {
            System.out.println("Have no any Book");
        }
        int choice;
        String s;
        do {            
            System.out.println("1 - Sreach by Book's name.");
            System.out.println("2 - Sreach by publisher's ID.");
            System.out.println("3 - Exit");
            choice = Validation.getInt("Enter the way you want to search: ", 1, 3);     
            boolean hasBook = false;
            boolean isContinue = true;
            switch (choice) {
                case 1:                    
                    while (isContinue) {                        
                        s = Validation.getString("Enter a part of book's name: ", "PLEASE INPUT A NAME WITH 0 -> 30 CHARACTERS!!", "[A-Za-z 0-9]{0,30}");
                        for (Book b : listB) {
                            if (b.getBookName().contains(s)) {
                                System.out.println(b);
                                hasBook = true;
                            }
                        }
                        if(hasBook == false) System.err.println("Book’s Name does not exist");
                        isContinue = Validation.getYN("You want to continue: ");
                    }                                         
                    break;
                case 2:
                    while(isContinue) {
                        s = Validation.getPublisherCode("Enter the publisher's id of the book: ", "The publisher code does not exist!!!", PublisherManagement.getListP(), 2);
                        for (Book b : listB) {
                            if (b.getPublisherID().equals(s)) {
                                System.out.println(b);
                                hasBook = true;
                            }
                        }
                        if(hasBook == false) System.err.println("Book’s Name does not exist");
                        isContinue = Validation.getYN("You want to continue: ");
                    }
                    break;
                case 3:                   
                    break;   
            }
        } while (choice < 3);
    }
    
    public void updateBook() {
        bookID = Validation.getBookCode("Enter the book's Id you want to update: ", "Book’s Name does not exist", listB, 2);
        
        String priceCheck, quantityCheck;
        for (int i = 0; i < listB.size(); i++) {
            if (listB.get(i).getBookID().equals(bookID)) {
                bookName = Validation.getString("Updete book's name: ", "PLEASE INPUT A NAME WITH 5 -> 30 CHARACTERS!!", "[A-Za-z 0-9]{5,30}");
                //check if user don't input, it will return the old book name
                if(bookName.isEmpty()) bookName = listB.get(i).getBookName();
                
                while(true) {
                    priceCheck = Validation.getString("Update book's price: ", "PLEASE INPUT A REAL NUMBER!!!!(> 0)", "[0-9]");
                    if(priceCheck.isEmpty()){
                        bookPrice = listB.get(i).getBookPrice();
                        break;
                    } else {
                        bookPrice = Double.parseDouble(priceCheck);
                        if(bookPrice > 0) break;
                    }                       
                }
                
                while(true) {
                    quantityCheck = Validation.getString("Update book's quantity: ", "PLEASE INPUT A INTERGER!!!(> 0)","[0-9]");
                    if(priceCheck.isEmpty()){
                        bookPrice = listB.get(i).getBookPrice();
                        break;
                    } else {
                        bookQuantity = Integer.parseInt(quantityCheck);
                        if(bookQuantity > 0) break;
                    }                       
                }
                
                //method getStatus(1) is allow input null
                status = Validation.getStatus(2);
                if(status.isEmpty()) status = listB.get(i).getStatus();
                
                //input publisher id
                while(true) {
                    publisherBookID = Validation.getString("Update publisher's Id of the book: ", "Publisher’s Id is not found", "^P\\d{5}");
                    if(Validation.checkAllCode(publisherBookID, "publisher", PublisherManagement.getListP(), null) && !publisherBookID.isEmpty()) break;
                    else {
                        publisherBookID = listB.get(i).getPublisherID();
                        break;
                    }
                }
                Book b = new Book(bookID, bookName, bookPrice, bookQuantity, status, publisherBookID);
                if(!listB.get(i).equals(b)) {                   
                    listB.set(i, b);
                    saveToFile();
                    System.out.println("UPDATE SUCCESS");
                } else {
                    //if user don't change any thing it will show update fail
                    System.out.println("UPDATE FAIL");
                }
            }
        }
    }

    @Override
    public void delete() {
        bookID = Validation.getBookCode("Enter book's Id you want to delete: ", "Book’s Name does not exist", listB, 2);
        for (int i = 0; i < listB.size(); i++) {
            if (listB.get(i).getBookID().equals(bookID)) {
                listB.remove(i);
                System.out.println("DELETE SUCCESS");
                saveToFile();
                return;
            }
        }
        System.out.println("DELETE FAIL");
    }

    @Override
    public void saveToFile() {
        try {
            File f = new File("Book.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Book b : listB) {
                bw.write(b.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {}
    }

    @Override
    public void printFromFile() {
        ArrayList<Book> listBookTemp = new ArrayList<>();
        loadData(listBookTemp);
        PublisherManagement.loadData(PublisherManagement.getListP());
        sortByQuantity(listBookTemp);
        for (Book b : listBookTemp) {
            for (Publisher p : PublisherManagement.getListP()) {
                //use to change publisher's id to publisher's name and print it.
                if(b.getPublisherID().equals(p.getPublisherID())) {
                    //print all format : Id - Name - Price - Quantity - Subtotal - Publisher’s Name - Status
                    System.out.println(b.getBookID() + " - " + b.getBookName() + " - " + b.getBookPrice() + " - " + b.getBookQuantity() + " - " + b.getSubTotal() + " - " + p.getPublisherName() + " - " + b.getStatus());
                    break;
                }
            }
        }
    }
        
    //load data from Book.txt
    public static void loadData(List<Book> listB) {
        try {
            FileReader fr = new FileReader("Book.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {            
                String line = br.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] words = line.split("[-]");
                String id = words[0].trim();
                String name = words[1].trim();
                double price = Double.parseDouble(words[2].trim());
                int quantity = Integer.parseInt(words[3].trim());
                String publisherID = words[4].trim();
                String status1 = words[5].trim();
                listB.add(new Book(id, name, price, quantity, status1, publisherID));
            }        
            br.close();
            fr.close();
        } catch (IOException e) {}
    }
    
    //sort the book list bay quantity but if books have same quantity then ordered by Book’s Price ascending
    private void sortByQuantity(List<Book> listB) {
        Collections.sort(listB, (Book b1, Book b2) -> {
            if(b1.getBookQuantity() == b2.getBookQuantity()) {
                //rephace books
                if(b1.getBookPrice() > b2.getBookPrice()) {
                    return 1;
                    //do not thing
                } else if(b1.getBookPrice() < b2.getBookPrice()){
                    return -1;
                } else return 0;
            }
            return b2.getBookQuantity() - b1.getBookQuantity();                
        });
    }
}
