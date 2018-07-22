package annotations;

import java.util.List;

/**
 * Created by zhangchunhui on 2018/7/22.
 */
public class PasswordUtils {

    @UseCase(id = 47, description = "Passwords must contain at least one numeric")
    public boolean validayePassword(String password) {
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 49, description = "New passwords can't equals prebiously used ones")
    public boolean checkForNewPassword(List<String> prePasswords, String password) {
        return !prePasswords.contains(password);
    }

}
