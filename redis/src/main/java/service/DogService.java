package service;

import pojo.Dog;

import java.text.ParseException;

public interface DogService {
   //string
    public void setString() throws ParseException;

    public void getString();
    //hash
    public void add(Dog dog);
    //list
    public void listAdd();
    public Object listRange(long pageNo,long pageSize);
}
