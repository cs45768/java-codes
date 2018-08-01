package annotations.database;

import java.util.Date;

/**
 * Created by zhangchunhui on 2018/7/23.
 */
@DBTable(name = "MEMBER")
public class Member {

    @SQLString(30)
    String firstName;
    @SQLString(50)
    String lastName;
    @SQLInteger
    Integer age;

    @SQLDouble(size = 10,d=6)
    float weight;
    @SQLDate
    Date birthDay;
    @SQLString(value = 30, contraints = @Constraints(primaryKey = true))
    String handle;
    static int memberCount;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getHandle() {
        return handle;
    }

    public static int getMemberCount() {
        return memberCount;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", handle='" + handle + '\'' +
                '}';
    }
}
