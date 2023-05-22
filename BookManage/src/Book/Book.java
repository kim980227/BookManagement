package Book;

import java.math.BigDecimal;
import java.sql.*;
//import java.sql.Timestamp;

public class Book {
    private int bookId;
    private int category;
    private String bookName;
    private String summary;
    private String author;
    private String publisher;
    private Date inDate;
    private char isIn;
    private Date outDate;
    private char isOut;
    private Date rentDate;
    private char isRent;
    private Date rentReservationDate;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private Timestamp saleTimestamp;
    private int edition;
    private BigDecimal discountRate;
    private Timestamp createdTimestamp;
    private char isDeleted;
    private Timestamp deletedTimestamp;

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public char getIsIn() {
        return isIn;
    }

    public void setIsIn(char isIn) {
        this.isIn = isIn;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public char getIsOut() {
        return isOut;
    }

    public void setIsOut(char isOut) {
        this.isOut = isOut;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public char getIsRent() {
        return isRent;
    }

    public void setIsRent(char isRent) {
        this.isRent = isRent;
    }

    public Date getRentReservationDate() {
        return rentReservationDate;
    }

    public void setRentReservationDate(Date rentReservationDate) {
        this.rentReservationDate = rentReservationDate;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Timestamp getSaleTimestamp() {
        return saleTimestamp;
    }

    public void setSaleTimestamp(Timestamp saleTimestamp) {
        this.saleTimestamp = saleTimestamp;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public char getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(char isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getDeletedTimestamp() {
        return deletedTimestamp;
    }

    public void setDeletedTimestamp(Timestamp deletedTimestamp) {
        this.deletedTimestamp = deletedTimestamp;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", category=" + category +
                ", bookName='" + bookName + '\'' +
                ", summary='" + summary + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", inDate=" + inDate +
                ", isIn=" + isIn +
                ", outDate=" + outDate +
                ", isOut=" + isOut +
                ", rentDate=" + rentDate +
                ", isRent=" + isRent +
                ", rentReservationDate=" + rentReservationDate +
                ", purchasePrice=" + purchasePrice +
                ", sellingPrice=" + sellingPrice +
                ", saleTimestamp=" + saleTimestamp +
                ", edition=" + edition +
                ", discountRate=" + discountRate +
                ", createdTimestamp=" + createdTimestamp +
                ", isDeleted=" + isDeleted +
                ", deletedTimestamp=" + deletedTimestamp +
                '}';
    }
}
