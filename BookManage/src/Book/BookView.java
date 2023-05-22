package Book;

public class BookView {

    final static BookView bv = new BookView();

    int view_count;
    int rent_count;

    public BookView() {

    }

    BookView getInstance(){
        return bv;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        bv.view_count = view_count;
    }

    public int getRent_count() {
        return rent_count;
    }

    public void setRent_count(int rent_count) {
        this.rent_count = rent_count;
    }
}
