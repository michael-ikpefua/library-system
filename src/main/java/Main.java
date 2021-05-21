import model.*;
import utils.InvalidLevelException;
import utils.Levels;
import utils.Priority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static utils.Levels.*;

public class Main {

    public static void main(String[] args) throws InvalidLevelException {
//        normalQueueImplementation(); //This is normal queue implementation.
        Library.Librarian librarian = new Library.Librarian(Priority.HIGH);

        /**
         * Use Case 1: Instantiate books that would be added to the library.
         */
        Book book1 = new Book("Game of Thrones", 1);
        Book book2 = new Book("Think And Grow Rich",1);
        Book book3 = new Book("How to make money",1);

        List<Book> bookList = Arrays.asList(book1, book1, book2, book3);

        /**
         * Use Case 1.1: Add book to the library
         *
         * In this case: I used Consumer and Predicate to add book to the library.
         */
        bookList.forEach((book) -> librarian.addBook(book, librarian::checkIfBookExists));

        librarian.printBookListInLibrary(); //Use Case 1.1: Shows list of books. I used streams.
//
//        /**
//         * Use Case 2.0: List of users wants to access book from the library
//         */
//        User juniorStudent1 = new Student("Junior Richie", Levels.TEACHER); //throws an exception
        User juniorStudent = new Student("Junior Student", JUNIOR_STUDENT);
        User seniorStudent = new Student("Senior Student", SENIOR_STUDENT);
        User teacher = new Teacher("Teacher Thomas");

//        System.out.println(librarian.borrowBookToUser(book2)); //Use Case 2.0: When teacher or student who are not registered in the library tries to borrow book.

        /**
         * Use Case 2.1: Users that wants to borrow book from the Library.
         */
        List <User> userList = Arrays.asList(juniorStudent, seniorStudent, teacher);

        userList.forEach((librarian::registerUser)); // I used Consumer Interface and method reference to add users to library

        System.out.println(librarian.borrowBookToUser(book2));
//        System.out.println(librarian.borrowBookToUser(book2)); //someone tried to borrow book that is already borrowed.
//        System.out.println(librarian.borrowBookToUser(book1));

        /**
         * Use Case: 2.2: When teacher or students tries to borrow book that is not in the library.
         */
//        Book bookNotInLibrary = new Book("Powers", 3);
//        System.out.println(librarian.borrowBookToUser(bookNotInLibrary));

        /**
         * Use Case: 2.3: When defaulter tries to borrow new book from the system.
         */
//        System.out.println(librarian.borrowBookToUser(book3));

        /**
         * Use Case: 3: When User return the book that they borrowed, and also when they tries to borrow new book.
         */
//        System.out.println(librarian.returnBorrowedBook(teacher, book2, librarian));
//
//        System.out.println(librarian.borrowBookToUser(book2));

    }


    public static void normalQueueImplementation() throws InvalidLevelException {
        Library.Librarian librarian = new Library.Librarian();

        /**
         * Use Case 1: Librarian added books to the library
         */
        Book book1 = new Book("Game of Thrones", 1);
        Book book2 = new Book("Think And Grow Rich",1);
        for (Book book : Arrays.asList(book1,book1, book2)) {
//            librarian.addBook(book);
        }

        /**
         * Use Case 2: List of students and teachers that are registered in the library.
         */
        User juniorStudent = new Student("Junior1 Student", JUNIOR_STUDENT);
        User seniorStudent = new Student("Senior1 Student", SENIOR_STUDENT);
        User teacher = new Teacher("Teacher Grace");

        /**
         * Use Case 2.1: Users that wants to borrow book from the Library.
         */
        for (User user : Arrays.asList(juniorStudent, seniorStudent, teacher)) {
//            librarian.registerUser(user);
        }
        System.out.println(librarian.borrowBookToUser(book1));


    }


}
