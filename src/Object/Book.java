/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Book {
    private String bookID, bookName;
    private double bookPrice;
    private int bookQuantity;
    private String status;
    private String publisherID;

    public Book() {
    }

    public Book(String bookID, String bookName, double bookPrice, int bookQuantity, String status, String publisherID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookQuantity = bookQuantity;
        this.status = status;
        this.publisherID = publisherID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public double getSubTotal() {
        return bookPrice * bookQuantity;
    }

    @Override
    public String toString() {
        return getBookID() + " - " + getBookName() + " - " + getBookPrice() + " - " + getBookQuantity() + " - " + getPublisherID() + " - " + getStatus();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (Double.doubleToLongBits(this.bookPrice) != Double.doubleToLongBits(other.bookPrice)) {
            return false;
        }
        if (this.bookQuantity != other.bookQuantity) {
            return false;
        }
        if (!Objects.equals(this.bookID, other.bookID)) {
            return false;
        }
        if (!Objects.equals(this.bookName, other.bookName)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return Objects.equals(this.publisherID, other.publisherID);
    }
     
}
