package model;

import utils.InvalidLevelException;
import utils.Levels;
import utils.Priority;

public abstract class User implements Comparable<User> {

    private String fullName;
    private int level;

    public User(String fullName, Levels level) throws InvalidLevelException
    {
        this.fullName = fullName;
        this.level = getLevel(level);
    }

    public int getLevel(Levels level)  {
        try {
            if (this.getClass() == Student.class) {
                if (level.equals(Levels.JUNIOR_STUDENT)) {
                    return 3;
                }else if(level.equals(Levels.SENIOR_STUDENT)) {
                    return 2;
                } else {
                    throw new InvalidLevelException("Invalid Student Level Argument Supplied for Student!!!");
                }
            }
            else {
                if (this.getClass() == Teacher.class) {
                    if (level.equals(Levels.TEACHER)) {
                        return 1;
                    } else {
                        throw new InvalidLevelException("Invalid Teacher Level Argument Supplied for Teacher");
                    }
                }
            }

        } catch (InvalidLevelException ex) {
            ex.printStackTrace();
        }

        return -1;
    }


    public String getFullName() {
        return fullName;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int compareTo(User user) {
        return this.getLevel()-user.getLevel();
    }
}
