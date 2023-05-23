package Book;

import java.math.BigDecimal;
import java.sql.*;
//import java.sql.Timestamp;

public class Book {
    private int bookId;
    private int category1;
    private int category2;
    private String bookName;
    private String summary;
    private String author;
    private String publisher;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private int qty;
    private String img;
    private int page;
    private int edition;
    private BigDecimal discountRate;
    private Timestamp createdTs;
    private char isDeleted;
    private Timestamp deletedTs;

    // 생성자, getter/setter 메서드 등 생략

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCategory1() {
        return category1;
    }

    public void setCategory1(int category1) {
        this.category1 = category1;
    }

    public int getCategory2() {
        return category2;
    }

    public void setCategory2(int category2) {
        this.category2 = category2;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public Timestamp getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }

    public char getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(char isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getDeletedTs() {
        return deletedTs;
    }

    public void setDeletedTs(Timestamp deletedTs) {
        this.deletedTs = deletedTs;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", category1=" + category1 +
                ", category2=" + category2 +
                ", bookName='" + bookName + '\'' +
                ", summary='" + summary + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", sellingPrice=" + sellingPrice +
                ", qty=" + qty +
                ", img='" + img + '\'' +
                ", page=" + page +
                ", edition=" + edition +
                ", discountRate=" + discountRate +
                ", createdTs=" + createdTs +
                ", isDeleted=" + isDeleted +
                ", deletedTs=" + deletedTs +
                '}';
    }
}
