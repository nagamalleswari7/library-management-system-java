import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        BookService service=new BookService();

        while(true){

            System.out.println("\n===== Library Management System =====");
            System.out.println("1 Add Book");
            System.out.println("2 View Books");
            System.out.println("3 Update Book");
            System.out.println("4 Delete Book");
            System.out.println("5 Issue Book");
            System.out.println("6 Return Book");
            System.out.println("7 Search Book");
            System.out.println("8 View Issued Books");
            System.out.println("9 Student Borrow History");
            System.out.println("10 Show Overdue Books");
            System.out.println("11 Exit");

            System.out.print("Enter choice: ");

            int choice=sc.nextInt();

            sc.nextLine();

            switch(choice){

                case 1:

                    System.out.print("Title: ");
                    String title=sc.nextLine();

                    System.out.print("Author: ");
                    String author=sc.nextLine();

                    System.out.print("Quantity: ");
                    int qty=sc.nextInt();

                    service.addBook(title,author,qty);
                    break;

                case 2:
                    service.viewBooks();
                    break;

                case 3:

                    System.out.print("Book ID: ");
                    int id=sc.nextInt();

                    sc.nextLine();

                    System.out.print("New Title: ");
                    String t=sc.nextLine();

                    System.out.print("New Author: ");
                    String a=sc.nextLine();

                    System.out.print("New Quantity: ");
                    int q=sc.nextInt();

                    service.updateBook(id,t,a,q);
                    break;

                case 4:

                    System.out.print("Book ID: ");
                    int delete=sc.nextInt();

                    service.deleteBook(delete);
                    break;

                case 5:

                    System.out.print("Book ID: ");
                    int bookId=sc.nextInt();

                    System.out.print("Student ID: ");
                    int studentId=sc.nextInt();

                    service.issueBook(bookId,studentId);
                    break;

                case 6:

                    System.out.print("Issue ID: ");
                    int issueId=sc.nextInt();

                    service.returnBook(issueId);
                    break;

                case 7:

                    sc.nextLine();

                    System.out.print("Title: ");
                    String search=sc.nextLine();

                    service.searchBook(search);
                    break;

                case 8:
                    service.viewIssuedBooks();
                    break;

                case 9:

                    System.out.print("Student ID: ");
                    int sid=sc.nextInt();

                    service.studentHistory(sid);
                    break;

                case 10:
                    service.overdueBooks();
                    break;

                case 11:

                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}