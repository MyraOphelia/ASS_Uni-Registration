import javax.swing.*;

public class Gui{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gui().runGui());
    }

    public interface LoginListener {
        void onLogin(String username, String userType);
    }
    

    public void runGui() {
        LoginApp loginApp = new LoginApp();

        // Add a callback for successful student login
        loginApp.setLoginCallback((username, userType) -> {
            if ("Student".equals(userType)) {
                openStudentPage(username);
            }
            else if ("Lecturer".equals(userType)){
                openLecturerPage(username);
            }
        });

        loginApp.setVisible(true);
    }
    

    private void openStudentPage(String username) {
        JFrame studentFrame = new StudentPage(username);
        studentFrame.setVisible(true);
    }

    private void openLecturerPage(String username) {
        JFrame lecturerFrame = new LecturerPage(username);
        lecturerFrame.setVisible(true);
    }
}
