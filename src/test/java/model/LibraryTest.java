package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.InvalidLevelException;
import utils.Levels;
import utils.Priority;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    Library.Librarian librarian;
    Library.Librarian librarian1;
    Book gameOfThronesBook;
    Book thinkAndGrowRichBook;
    User juniorStudent;
    User seniorStudent;
    User teacher;

    @BeforeEach
    void setUp() throws InvalidLevelException {
        librarian = new Library.Librarian(Priority.HIGH);
        librarian1 = new Library.Librarian();
        gameOfThronesBook = new Book("Game of Thrones", 1);
        thinkAndGrowRichBook = new Book("Think And Grow Rich",1);
        juniorStudent = new Student("Junior Student", Levels.JUNIOR_STUDENT);
        seniorStudent = new Student("Senior Student", Levels.SENIOR_STUDENT);
        teacher = new Teacher("Teacher Thomas");
    }

    void addNewBookToTheLibrary(Book ...books) {
        for (Book book : Arrays.asList(books)) {
            librarian.addBook(book, librarian::checkIfBookExists);
        }
    }

    void addUsersToLibrary(User ...users) throws InvalidLevelException {
        for (User user : Arrays.asList(users)) {
            librarian.registerUser(user);
        }
    }

    @Test
    void test_to_add_new_book_to_the_library() {
        addNewBookToTheLibrary(gameOfThronesBook, thinkAndGrowRichBook);
        assertEquals(2, librarian.getBookList().size());
    }
    @Test
    void test_to_increase_book_quantity_instead_of_adding_new_book_to_the_list() {
        Book book3 = new Book("Grass to Grace", 1);
        addNewBookToTheLibrary(gameOfThronesBook,gameOfThronesBook,thinkAndGrowRichBook,thinkAndGrowRichBook, book3);

        assertEquals(2, librarian.getBookDetails(gameOfThronesBook).getQuantity());
        assertEquals(2, librarian.getBookDetails(thinkAndGrowRichBook).getQuantity());
        assertEquals(1, librarian.getBookDetails(book3).getQuantity());
    }

    @Test
    void test_to_register_or_add_users_to_the_library() throws InvalidLevelException {
        addUsersToLibrary(juniorStudent, seniorStudent, teacher);
        assertEquals(3, librarian.getUsersInQueue().size());
    }

    @Test
    void test_to_allow_users_to_borrow_books_from_the_library_based_on_priority() throws InvalidLevelException{
        addUsersToLibrary(juniorStudent, seniorStudent, teacher);
        addNewBookToTheLibrary(gameOfThronesBook, thinkAndGrowRichBook);

        assertEquals("Hello, Teacher Thomas here is the \"" + gameOfThronesBook.getName() + "\""
                + " book you are borrowing", librarian.borrowBookToUser(gameOfThronesBook));
    }

    @Test
    void test_to_borrow_book_that_has_already_been_borrowed() throws InvalidLevelException {
        addNewBookToTheLibrary(gameOfThronesBook);
        addUsersToLibrary(teacher);
        librarian.borrowBookToUser(gameOfThronesBook);
        assertEquals("Book Taken!", librarian.borrowBookToUser(gameOfThronesBook));
    }

    @Test
    void test_to_borrow_book_that_is_not_in_the_library() {
        Book book3 = new Book("Powers", 3);
        assertEquals("When don't have Powers book in our Library.", librarian.borrowBookToUser(book3));
    }

    @Test
    void test_to_see_if_a_user_can_borrow_book_more_than_ones() throws InvalidLevelException{
        addUsersToLibrary(teacher, teacher);
        addNewBookToTheLibrary(gameOfThronesBook);
        addNewBookToTheLibrary(thinkAndGrowRichBook);
        librarian.borrowBookToUser(gameOfThronesBook);
        assertEquals("Teacher Thomas you cannot borrow new book " +
                "because you have not returned \"Game of Thrones\" book you borrowed.", librarian.borrowBookToUser(thinkAndGrowRichBook));
    }

    @Test
    void test_to_check_if_user_can_return_book_back_to_the_library() throws InvalidLevelException{
        addUsersToLibrary(teacher);
        addNewBookToTheLibrary(gameOfThronesBook);
        addNewBookToTheLibrary(thinkAndGrowRichBook);
        librarian.borrowBookToUser(gameOfThronesBook);

        assertEquals("Thank you Teacher Thomas for returning Game of Thrones.",
                librarian.returnBorrowedBook(teacher, gameOfThronesBook, librarian));
    }

    @Test
    void test_to_test_user_that_wants_to_borrow_books_based_on_first_come_first_server_basic() throws InvalidLevelException{
        librarian1.registerUser(juniorStudent);
        librarian1.registerUser(seniorStudent);
        librarian1.registerUser(teacher);

        librarian1.addBook(gameOfThronesBook, librarian::checkIfBookExists);
        librarian1.addBook(thinkAndGrowRichBook, librarian::checkIfBookExists);

        assertEquals("Hello, Junior Student here is the \"Game of Thrones\" book you are borrowing",
                librarian1.borrowBookToUser(gameOfThronesBook));


    }


}