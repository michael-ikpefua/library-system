package model;

import utils.Priority;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Library class stores the activities that occurs in the system.
 */
public class Library {
    ArrayList <Book> bookList = new ArrayList<>();
    Queue<User> queue;
    Map<User,Book> usersThatBorrowedBooks = new HashMap<>();

    /**
     * Librarian class interacts with the Library class to perform actions such as registering user, etc.
     */
    public static class Librarian {

//        private Priority priority;
        Library library = new Library();

        public Librarian(Priority priority) {
//            this.priority = priority;

            library.queue = new PriorityQueue<>();
        }

        public Librarian() {
            library.queue = new ConcurrentLinkedQueue<>();
        }

        /**
         * List of books that are in the library
         *
         * @return arraylist of books
         */
        public ArrayList<Book> getBookList()
        {
            return library.bookList;
        }

        public Map<User, Book> getUsersThatBorrowedBooks() {
            return library.usersThatBorrowedBooks;
        }

        /**
         * Allows Admin to add book to the library.
         *
         * @param book object
         */
        public void addBook(Book book) {
            if (getBookList().size() > 0 && checkIfBookExists(book)) {
                int bookInBookListIndex = getBookList().indexOf(book);
                Book bookInBookList = getBookList().get(bookInBookListIndex);
                bookInBookList.updateQuantity((bookInBookList.getQuantity() + book.getQuantity()));
                getBookList().set(bookInBookListIndex, bookInBookList);
            } else {
                getBookList().add(book);
            }
        }

        /**
         * Print List of Books in the library.
         */
        public void printBookListInLibrary() {
            for (Book bookInLibrary: getBookList()) {
                System.out.println("BookName: " + bookInLibrary.getName() + " Quantity: " + bookInLibrary.getQuantity());
            }

        }

        /**
         * Check if book exists in the library
         *
         * @param book object to check
         * @return boolean, either true or false.
         */
        private boolean checkIfBookExists(Book book)
        {
            return getBookList().contains(book);
        }

        /**
         * Add User to the queue
         *
         * @param user object that is added to the queue.
         */
        public void registerUser(User user) {
            library.queue.add(user);
        }

        /**
         * Users can borrow book from the library
         *
         * @param book object that has book information
         * @return book  to borrower
         */
        public String borrowBookToUser(Book book) {

            if (!getBookList().contains(book)) {
                return String.format("When don't have %s book in our Library.", book.getName());
            }
            Book bookInBookList = getBookList().get(getBookList().indexOf(book));

            if (bookInBookList.getQuantity() < 1 ) {
                return "Book Taken!";
            }
            else if (bookInBookList.getQuantity() >= 1) {
                bookInBookList.updateQuantity((bookInBookList.getQuantity()-1));
            }

            if (library.queue.isEmpty()) {
                return "No User is registered in the library!!!";
            }
            User borrower = library.queue.poll();

            if (!getUsersThatBorrowedBooks().containsKey(borrower)) {
                getUsersThatBorrowedBooks().put(borrower, book);
                return String.format("Hello, %s here is the \"%s\" book you are borrowing", borrower.getFullName(), book.getName());
            }
            else {
                Book borrowedBook = getUsersThatBorrowedBooks().get(borrower);
                return String.format("%s you cannot borrow new book because you have not returned \"%s\" book you borrowed.", borrower.getFullName(), borrowedBook.getName());
            }
        }

        /**
         * Allow user to return borrowed book
         *
         * @param user object
         * @param book object
         * @return acknowledged message when the book is returned.
         */
        public String returnBorrowedBook(User user, Book book) {
            if (!getUsersThatBorrowedBooks().containsKey(user)) {
                return String.format("Hello, %s, you details is not in our system!", user.getFullName());
            } else {
                Book bookInBookList = getBookList().get(getBookList().indexOf(book));
                bookInBookList.updateQuantity((bookInBookList.getQuantity()+1));
                getUsersThatBorrowedBooks().remove(user);

                return String.format("Thank you %s for returning %s.",user.getFullName(),book.getName());
            }

        }

        /**
         * Display Book Information in Queue
         * @param book object to check if its in queue
         * @return book in queue details
         */
        public Book getBookDetails(Book book) {
            if (!checkIfBookExists(book)) return null;
            return getBookList().get(getBookList().indexOf(book));
        }

        public Queue getUsersInQueue() {
            return library.queue;
        }

    }


}
