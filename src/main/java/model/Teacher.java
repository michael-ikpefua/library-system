package model;

import utils.InvalidLevelException;
import utils.Levels;

public class Teacher extends User{

    public Teacher(String name) throws InvalidLevelException {
        super(name,Levels.TEACHER);
    }

    public Teacher(String name,Levels level) throws InvalidLevelException {
        super(name, level);
    }

    public String getName() {
        return this.getFullName();
    }

}
