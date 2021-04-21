import model.*;
import utils.InvalidLevelException;
import utils.Levels;
import utils.Priority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) throws InvalidLevelException {
//        normalQueueImplementation(); //This is normal queue implementation.
        Library.Librarian librarian = new Library.Librarian(Priority.HIGH);
//
//        /**
//         * Use Case 1: Librarian added books to the library
//         */
        Book book1 = new Book("Game of Thrones", 1);
        Book book2 = new Book("Think And Grow Rich",1);
        Book book3 = new Book("How to make money",1);

//        librarian.addBook(book1);
//        librarian.addBook(book1);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        bookList.forEach(librarian::addBook);

//        for (Book book : Arrays.asList(book1,book1, book2,book3)) {
//            librarian.addBook(book);
//        }
//        System.out.println("Book name: " + librarian.getBookDetails(book1).getName() + ", has " + librarian.getBookDetails(book1).getQuantity() + " quantity(ies)");
        librarian.printBookListInLibrary();
//
//        /**
//         * Use Case 2: List of students and teachers that are registered in the library.
//         */
////        User juniorStudent = new Student("Junior Richie", Levels.TEACHER); //throws an exception
        User juniorStudent = new Student("Junior Student", Levels.JUNIOR_STUDENT);
        User seniorStudent = new Student("Senior Student", Levels.SENIOR_STUDENT);
        User teacher = new Teacher("Teacher Thomas");
//        /**
//         * Use Case 2.0: When teacher or student who are not registered in the library tries to borrow book.
//         */

//        /**
//         * Use Case 2.1: Users that wants to borrow book from the Library.
//         */
        List <User> userList = new ArrayList<>();
        userList.add(juniorStudent);
        userList.add(seniorStudent);
        userList.add(teacher);

        Consumer<User> userConsumer = (librarian::registerUser);
        userList.forEach(userConsumer);
//        for (User user : Arrays.asList(juniorStudent, seniorStudent, teacher)) {
//            librarian.registerUser(user);
//        }
        System.out.println(librarian.borrowBookToUser(book2));
//        System.out.println(librarian.borrowBookToUser(book1));
//        System.out.println(librarian.borrowBookToUser(book3));
//
//        /**
//         * Use Case: 2.2: When teacher or students tries to access book that is already borrowed,
//         * or no more copies available.
//         */
//        System.out.println(librarian.borrowBookToUser(book2));
//
//        /**
//         * Use Case: 2.3: When teacher or students tries to borrow book that is not in the library.
//         */
//        Book book3 = new Book("Powers", 3);
////        System.out.println(librarian.borrowBookToUser(book3));
//
//        /**
//         * Use Case: 2.4: When defaulter tries to borrow new book from the system.
//         */
//        for (User user : Arrays.asList(teacher, teacher)) {
//            librarian.registerUser(user);
//        }
//        System.out.println(librarian.borrowBookToUser(book1));
//
//        /**
//         * Use Case: 3: When User return the book that they borrowed, and also when they tries to borrow new book.
//         */
//        System.out.println(librarian.returnBorrowedBook(teacher, book1));
//
//        System.out.println(librarian.borrowBookToUser(book2));
//        System.out.println(librarian.borrowBookToUser(book1));
//
//        /**
//         * Use Case: 4: When user tries to return book
//         */
//        User newStudent = new Student("James Laz", Levels.JUNIOR_STUDENT);
//        System.out.println(librarian.returnBorrowedBook(newStudent, book1));

    }


    public static void normalQueueImplementation() throws InvalidLevelException {
        Library.Librarian librarian = new Library.Librarian();

        /**
         * Use Case 1: Librarian added books to the library
         */
        Book book1 = new Book("Game of Thrones", 1);
        Book book2 = new Book("Think And Grow Rich",1);
        for (Book book : Arrays.asList(book1,book1, book2)) {
            librarian.addBook(book);
        }
//        librarian.printBookListInLibrary();

        /**
         * Use Case 2: List of students and teachers that are registered in the library.
         */
        User juniorStudent = new Student("Junior1 Student", Levels.JUNIOR_STUDENT);
        User seniorStudent = new Student("Senior1 Student", Levels.SENIOR_STUDENT);
        User teacher = new Teacher("Teacher Grace");

        /**
         * Use Case 2.1: Users that wants to borrow book from the Library.
         */
        for (User user : Arrays.asList(juniorStudent, seniorStudent, teacher)) {
            librarian.registerUser(user);
        }
        System.out.println(librarian.borrowBookToUser(book1));


    }


}
