package validation;

import java.util.List;
import java.util.Scanner;
import Object.*;

public class Validation {
    private static final Scanner sc = new Scanner(System.in);
    
    public static int getInt(String msg, int min ,int max){
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        
        while (true) {            
            try {
                System.out.print(msg);
                int n = Integer.parseInt(sc.nextLine());
                if (min <= n && n <= max) {
                    return n;
                }
                System.err.println("PLEASE INPUT A NUMBER IN RANGE " + min + " -> " + max);
            } catch (NumberFormatException e) {
                System.err.println("WRONG FORMAT!!");
                System.out.println("Enter again!!!");
            }
        }
    }
    
    public static double getDouble(String msg, double min , double max){
        if (min > max) {
            double temp = min;
            min = max;
            max = temp;
        }
        
        while (true) {            
            try {
                System.out.print(msg);
                double n = Integer.parseInt(sc.nextLine());
                if (min <= n && n <= max) {
                    return n;
                }
                System.err.println("PLEASE INPUT A REAL NUMBER IN RANGE " + min + " -> " + max);
            } catch (NumberFormatException e) {
                System.err.println("WRONG FORMAT!!");
                System.out.println("Enter again!!!");
            }
        }
    }
    
    public static String getString(String msg, String err, String pattern) {

        while (true) {            
            System.out.print(msg);
            String s = sc.nextLine();    
            if (s.matches(pattern) || s.isEmpty()) {
                return s;
            }
            System.err.println(err);
            System.out.println("Enter again!!!");
        }
    }
    
    public static String getStatus(int mode) {
        //Mode : 1- created , 2- update: allow to input null;
        while (true) {            
            System.out.println("Enter book's status (only Y/N (Y = Available, N = Not Available)): ");
            String s = sc.nextLine();
            if(s.equalsIgnoreCase("Y")){
                return "Available";
            } else if (s.equalsIgnoreCase("N")) {
                return "Not Available";
            }
            //use in update book check uses is not inputted
            if(s.isEmpty() && mode == 2) return "";
            System.err.println("PLEASE INPUT ONLY Y-N!!");
            System.out.println("Enter again!!!");
        }
    }
    
    public static boolean getYN(String msg){
        while (true) {            
            System.out.print(msg);
            String s = sc.nextLine();
            if (s.equalsIgnoreCase("y")) {
                return true;
            }else if (s.equalsIgnoreCase("n")) {
                return false;
            }
            System.err.println("PLEASE INPUT ONLY Y-N!!");
            System.out.println("Enter again!!");
        }
    }
    
    public static String getPublisherCode(String msg, String err, List<Publisher> listP, int mode) {
        while (true) {            
            System.out.print(msg);
            String s = sc.nextLine().toUpperCase();
            if(s.matches("^P\\d{5}$")) {
                //Mode:
                //1 : create
                //2 : delete
                if((!checkAllCode(s, "publisher", listP, null) && mode == 1) || (checkAllCode(s, "publisher", listP, null) && mode == 2)) {
                    return s;
                }
            }
            System.err.println(err);
        }
    }
   
    public static String getBookCode(String msg, String err, List<Book> listB, int mode) {
        while (true) {            
            System.out.print(msg);
            String s = sc.nextLine().toUpperCase();
            if(s.matches("^B\\d{5}$")) {
                //Mode:
                //1 : create
                //2 : delete
                if((!checkAllCode(s, "book", null, listB) && mode == 1) || (checkAllCode(s, "book", null, listB) && mode == 2)) {
                    return s;
                }
            }
            System.err.println(err);
        }
    }
         
    public static boolean checkAllCode(String id, String op, List<Publisher> listP, List<Book> listB) {
        if (op.equalsIgnoreCase("publisher")) {
            for (Publisher o : listP) {
                if (o.getPublisherID().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        } else if (op.equalsIgnoreCase("book")) {
            for (Book o : listB) {
                if (o.getBookID().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }
        return false;
    }
    
//    private static boolean isValidString(String name, String pat) {
//        // Regex to check valid username.
//        String regex = pat;
//  
//        // Compile the ReGex
//        Pattern p = Pattern.compile(regex);
//        
//        
//        // Pattern class contains matcher() method
//        // to find matching between given username
//        // and regular expression.
//        Matcher m = p.matcher(name);
//  
//        // Return if the username
//        // matched the ReGex        
//        // If the username is empty
//        // return false
//        if (name == null || m.matches()) {
//            return false;
//        }
//  
//    return true;
//    }
}
