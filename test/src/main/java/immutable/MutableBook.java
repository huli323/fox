package immutable;

import java.util.Arrays;

public class MutableBook implements Cloneable {
    private String bookName;
    private String bookAuthor;
    private String[] arr;

    public String[] getArr() {
        return Arrays.copyOf(arr, arr.length);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookName() {

        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    @Override
    public String toString() {
        return "MutableBook{" +
                "bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", arr=" + Arrays.toString(arr) +
                '}';
    }

    public MutableBook(String bookName, String bookAuthor, String[] arr) {
        this.arr = Arrays.copyOf(arr, arr.length);
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }
}
