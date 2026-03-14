import java.sql.*;

public class BookService {

    public void addBook(String title, String author, int quantity) {

        if(quantity < 0){
            System.out.println("Quantity cannot be negative");
            return;
        }

        try {

            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO books(title, author, quantity) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,title);
            ps.setString(2,author);
            ps.setInt(3,quantity);

            ps.executeUpdate();

            System.out.println("Book added successfully");

        } catch(Exception e){
            e.printStackTrace();
        }
    }


    public void viewBooks(){

        try{

            Connection conn = DBConnection.getConnection();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            System.out.println("\nBookID | Title | Author | Quantity");

            while(rs.next()){

                System.out.println(
                        rs.getInt("book_id")+" | "+
                        rs.getString("title")+" | "+
                        rs.getString("author")+" | "+
                        rs.getInt("quantity")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void updateBook(int id,String title,String author,int qty){

        try{

            Connection conn = DBConnection.getConnection();

            String sql="UPDATE books SET title=?, author=?, quantity=? WHERE book_id=?";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setString(1,title);
            ps.setString(2,author);
            ps.setInt(3,qty);
            ps.setInt(4,id);

            int rows=ps.executeUpdate();

            if(rows>0)
                System.out.println("Book updated successfully");
            else
                System.out.println("Book not found");

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void deleteBook(int id){

        try{

            Connection conn = DBConnection.getConnection();

            String sql="DELETE FROM books WHERE book_id=?";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setInt(1,id);

            ps.executeUpdate();

            System.out.println("Book deleted successfully");

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void issueBook(int bookId,int studentId){

        try{

            Connection conn = DBConnection.getConnection();

            String check="SELECT quantity FROM books WHERE book_id=?";

            PreparedStatement ps=conn.prepareStatement(check);

            ps.setInt(1,bookId);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                int qty=rs.getInt("quantity");

                if(qty<=0){
                    System.out.println("Book not available");
                    return;
                }
            }

            String sql="INSERT INTO issued_books(book_id,student_id,issue_date) VALUES(?,?,CURDATE())";

            PreparedStatement ps2=conn.prepareStatement(sql);

            ps2.setInt(1,bookId);
            ps2.setInt(2,studentId);

            ps2.executeUpdate();

            String update="UPDATE books SET quantity=quantity-1 WHERE book_id=?";

            PreparedStatement ps3=conn.prepareStatement(update);

            ps3.setInt(1,bookId);

            ps3.executeUpdate();

            System.out.println("Book issued successfully");

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void returnBook(int issueId){

        try{

            Connection conn = DBConnection.getConnection();

            String getBook="SELECT book_id FROM issued_books WHERE issue_id=?";

            PreparedStatement ps=conn.prepareStatement(getBook);

            ps.setInt(1,issueId);

            ResultSet rs=ps.executeQuery();

            int bookId=0;

            if(rs.next()){
                bookId=rs.getInt("book_id");
            }

            String sql="UPDATE issued_books SET return_date=CURDATE() WHERE issue_id=?";

            PreparedStatement ps2=conn.prepareStatement(sql);

            ps2.setInt(1,issueId);

            ps2.executeUpdate();

            String update="UPDATE books SET quantity=quantity+1 WHERE book_id=?";

            PreparedStatement ps3=conn.prepareStatement(update);

            ps3.setInt(1,bookId);

            ps3.executeUpdate();

            System.out.println("Book returned successfully");

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void searchBook(String title){

        try{

            Connection conn = DBConnection.getConnection();

            String sql="SELECT * FROM books WHERE title LIKE ?";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setString(1,"%"+title+"%");

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                System.out.println(
                        rs.getInt("book_id")+" | "+
                        rs.getString("title")+" | "+
                        rs.getString("author")+" | "+
                        rs.getInt("quantity")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void viewIssuedBooks(){

        try{

            Connection conn = DBConnection.getConnection();

            Statement stmt=conn.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT * FROM issued_books");

            System.out.println("\nIssueID | BookID | StudentID | IssueDate | ReturnDate");

            while(rs.next()){

                System.out.println(
                        rs.getInt("issue_id")+" | "+
                        rs.getInt("book_id")+" | "+
                        rs.getInt("student_id")+" | "+
                        rs.getDate("issue_date")+" | "+
                        rs.getDate("return_date")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void studentHistory(int studentId){

        try{

            Connection conn = DBConnection.getConnection();

            String sql="SELECT * FROM issued_books WHERE student_id=?";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setInt(1,studentId);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                System.out.println(
                        rs.getInt("issue_id")+" | "+
                        rs.getInt("book_id")+" | "+
                        rs.getDate("issue_date")+" | "+
                        rs.getDate("return_date")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void overdueBooks(){

        try{

            Connection conn = DBConnection.getConnection();

            String sql="""
                    SELECT * FROM issued_books
                    WHERE return_date IS NULL
                    AND issue_date < DATE_SUB(CURDATE(), INTERVAL 7 DAY)
                    """;

            Statement stmt=conn.createStatement();

            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()){

                System.out.println(
                        rs.getInt("issue_id")+" | "+
                        rs.getInt("book_id")+" | "+
                        rs.getInt("student_id")+" | "+
                        rs.getDate("issue_date")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}