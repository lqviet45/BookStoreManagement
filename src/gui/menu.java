package gui;

import validation.Validation;
import manage.*;

public class menu {
    //use to print the Book Store management program
    private static final String[] storeOps = {
        "Publisher's management",
        "Books management",
        "Quit"
    };
    
    //use to print all the publisher menu choice
    private static final String[] publisherOps = {
        "Create a Publisher",
        "Delete the Publisher",
        "Save the Publishers list to file",
        "Print the Publisher list from the file",
        "Go back to Book Store Manage program menu."
    };
    
    //use to print all the book menu choice
    private static final String[] bookOps = {
        "Create a Book",
        "Search the Book",
        "Update a Book",
        "Delete the Book",
        "Save the Books list to file",
        "Print the Books list from the file",
        "Go back to Book Store Manage program menu"
    };
    
    public static void display() {
        PublisherManagement pm = (PublisherManagement) ManageFactory.getManagement(ManagementType.PUBLISHER);
        BookManagement bm = (BookManagement) ManageFactory.getManagement(ManagementType.BOOK);
        
        int choice, POps, BOps;
        
        //load data that is store from file
        PublisherManagement.loadData(PublisherManagement.getListP());
        BookManagement.loadData(BookManagement.getListB());
        
        do {            
            System.out.println("============ Book Store management program ==============");
            for (int i = 0; i < storeOps.length; i++) {
                System.out.println((i + 1) + ". " + storeOps[i]);               
            }
            choice = Validation.getInt("Enter management program you want to use: ", 1, storeOps.length);
            switch (choice) {
                case 1:
                    do{
                        System.out.println("============ Publisher management program ==============");
                        for (int i = 0; i < publisherOps.length; i++) {
                            System.out.println((i + 1) + ". " + publisherOps[i]);                
                        }
                        boolean isContinue = true;
                        POps = Validation.getInt("Enter your choice: ", 1, publisherOps.length);
                        switch (POps) {
                            case 1:
                                while (isContinue) {                                    
                                    pm.create();
                                    isContinue = Validation.getYN("You want to continue: ");
                                }
                                break;
                            case 2:
                                while (isContinue) {                                    
                                pm.delete();
                                isContinue = Validation.getYN("You want to continue: ");
                                }
                                break;
                            case 3:
                                pm.saveToFile();
                                System.out.println("SAVED!!!");
                                break;
                            case 4:
                                pm.printFromFile();
                                break;
                            case 5:
                                break;
                        }
                    }while (POps < publisherOps.length);
                    break;
                case 2:
                    do {                        
                        System.out.println("============ Publisher management program ==============");
                        for (int i = 0; i < bookOps.length; i++) {
                            System.out.println((i + 1) + ". " + bookOps[i]);                            
                        }
                        boolean isContinue = true;
                        BOps = Validation.getInt("Enter your choice: ", 1, bookOps.length);
                        switch (BOps) {
                            case 1:
                                while (isContinue) {                                    
                                    bm.create();
                                    isContinue = Validation.getYN("You want to continue: ");
                                }
                                break;
                            case 2:
                                bm.searchBook();
                                break;
                            case 3:
                                while (isContinue) {                                    
                                    bm.updateBook();
                                    isContinue = Validation.getYN("You want to continue: ");
                                }
                                break;
                            case 4:
                                while (isContinue) {                                    
                                    bm.delete();
                                    isContinue = Validation.getYN("You want to continue: ");
                                }
                                break;
                            case 5:
                                bm.saveToFile();
                                System.out.println("SAVED!!!");
                                break;
                            case 6:
                                bm.printFromFile();
                                break;
                            case 7:
                                break;
                        }
                    } while (BOps < bookOps.length); 
                    break;
            }
        } while (choice < storeOps.length);
    }
}
