package com.example.sshriwas.samplemethodreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Comparator;

/**
 * The purpose of this code is to understand method references.
 * You must be aware of Lambda expressions before you proceed here.
 */
public class MainActivity extends AppCompatActivity {

    /*
    What we want to do here is demonstrate how to use method references to replace Lambda
    expressions when the latter simply invokes an existing method.
     */

    //First we define a functional interface
    //We then use lambda expression to implement this interface. Making sure that the
    //method internally calls another existing method.

    Person personA, personB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personA = new Person();
        personA.age = 20;
        personA.name = "Sandy";

        personB = new Person();
        personB.age = 32;
        personB.name = "Marko";

        //Now we will compare the age of the 2 people

        //Approach 1. Implementing interface via Anonymous class
/*
        MyComparator myComparator = new MyComparator() {
            @Override
            public Person CompareAge(Person p1, Person p2) {
                return p1.age> p2.age? p1:p2;
            }
        };
*/
        //Approach 2: using Lambda expressions instead of anonymous class
        //MyComparator myComparator = (Person p1, Person p2) -> p1.age> p2.age? p1:p2;

        //Now Person class has its own compare method. So replacing that in
        //our lambda expression
        //MyComparator myComparator = (Person p1, Person p2) -> Person.getOlder(p1, p2);

        //Now this is where method reference comes into the picture. Instead of
        //having a lambda expression with a single method call, we will replace it
        //with a method reference. In case of Method reference we do not explicitly
        //state the parameters. They are implicit.
        MyComparator myComparator = Person::getOlder;

        Person olderGuy = myComparator.CompareAge(personA, personB);
        Log.v("TAG", "Older Guy: "+olderGuy.name);

    }


}
//functional interface - just figured out that single method class may not be ideal
//for implementing lambda expressions. Because, the single method in a class will require
// its own implementation (body). Which can ofcourse be overriden but its not ideal. Other than that
// you may think the method can be declared as abstract. But then the class also needs to be
//defined as an abstract class. An abstract class can be inherited by another implementing class
//but it cannot be instantiated on its own. So to keep things clear i will be using
//interface for this example.
interface MyComparator{
    public Person CompareAge(Person p1, Person p2);
}

class Person {
    String name;
    int age;

    //Static method will return person with larger age
    static Person getOlder(Person p1, Person p2){
        return p1.age > p2.age? p1: p2;
    }
}
