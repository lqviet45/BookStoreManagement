package manage;

import validation.Validation;
import Object.Publisher;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PublisherManagement implements ManagementI{
    private static final List<Publisher> listP = new ArrayList<>();
    private String publisherID, publisherName, publisherPhone;

    public static List<Publisher> getListP() {
        return listP;
    }
    
    @Override
    public void create() {
        publisherID = Validation.getPublisherCode("Enter publisher's ID: ", "Please input id with format: 'Pxxxxx' and ID must not duplicate!!", listP, 1);
        publisherName = Validation.getString("Enter publisher' name: ", "PLEASE INPUT A NAME WITH 5 -> 30 CHARACTERS!!", "[A-Z a-z]{5,30}");
        publisherPhone = Validation.getString("Enter publisher's phone number: ", "PLEASE INPUT A NAME WITH 10 -> 12 CHARACTERS!!","\\d{10,12}");
        
        Publisher p1 = new Publisher(publisherID, publisherName, publisherPhone);
        listP.add(p1);
        saveToFile();
        System.out.println("ADD SUCCESS");
    }

    @Override
    public void delete() {
        publisherID = Validation.getString("Enter publisher's id you want to delete: ", "Wrong id type!!", publisherID);
        for (int i = 0; i < listP.size(); i++) {
            if (listP.get(i).getPublisherID().equals(publisherID)) {
                listP.remove(i);
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
            File f = new File("Publisher.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Publisher o : listP) {
                bw.write(o.toString());
                bw.newLine();
            }      

            bw.close();
            fw.close();
//            System.out.println("SAVE SUCCESS");
        } catch (IOException e) {}
    }

    @Override
    public void printFromFile() {
        ArrayList<Publisher> listTemp = new ArrayList<>();
        loadData(listTemp);
        sortByName(listTemp);
        for (Publisher o : listTemp) {
            System.out.println(o);
        }
    }
    
    
    
    //load data from file Publisher.txt
    public static void loadData(List<Publisher> listP) {
        try{
            FileReader fr = new FileReader("Publisher.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {            
                String line = br.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] words = line.split("[-]");
                String id = words[0].trim(), name = words[1].trim(), phoneNum = words[2].trim();
                listP.add(new Publisher(id, name, phoneNum));
            }        
            br.close();
            fr.close();
        } catch (IOException e){}                 
    }
    
    private void sortByName(List<Publisher> listP) {
        Collections.sort(listP, (Publisher p1, Publisher p2) -> p1.getPublisherName().compareTo(p2.getPublisherName()));      
    }
}