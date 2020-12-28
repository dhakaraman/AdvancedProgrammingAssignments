package mypackage;

import java.util.ArrayList;

public class User<T>{
    User(ArrayList<T> list, String player){
        System.out.print("The "+player+" were: ");
       for(T p:list){
          System.out.print("Player"+p+", ");
      }       System.out.println();
    }

}