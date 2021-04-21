package model;

import utils.InvalidLevelException;
import utils.Levels;
import utils.Priority;

public class Student extends User{

    public Student(String name, Levels level)  throws InvalidLevelException
    {
        super(name, level);
    }
}
