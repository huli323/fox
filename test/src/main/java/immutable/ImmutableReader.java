package immutable;

public class ImmutableReader {
    private final MutableBook book;
    private final int page;

    public MutableBook getBook() {
        return book;
    }

    public int getPage() {
        return page;
    }

    public ImmutableReader(MutableBook book, int page) throws CloneNotSupportedException {
        this.book = (MutableBook) book.clone();
        this.page = page;
    }

    @Override
    public String toString() {
        return "ImmutableReader{" +
                "book=" + book +
                ", page=" + page +
                '}';
    }
}
