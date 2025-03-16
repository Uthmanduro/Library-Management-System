import DAOTest.*;

public class Main {

    // Main meethod to run all the DAO implementation test cases centrally
    public static void main(String[] args) {
        System.out.println("=== Running BookDAO Tests ===");
        TestBook.main(args);

        System.out.println("\n=== Running MemberDAO Tests ===");
        TestMember.main(args);

        System.out.println("\n=== Running BorrowingDAO Tests ===");
        TestBorrowingRecords.main(args);

        System.out.println("\n✅ All DAO Tests Completed Successfully!");
    }
}

