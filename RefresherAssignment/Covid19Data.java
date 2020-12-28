import jdk.jfr.consumer.RecordedClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Covid19Data {

    public static class Patient{
        String name;
        char tower;
        int age;
        String date;
        String dateOfCure;

        Patient(String name, char tower, int age, String date, String dateOfCure){
            this.name = name;
            this.tower = tower;
            this.age = age;
            this.date = date;
            this.dateOfCure = dateOfCure;
        }
    }

    public static void main(String[] args){

        CovidUpdates obj1 = new CovidUpdates();

    }

    public static int Recovered(Patient obj[], String date){
        int count=0;
        for(int i=0;i<20;i++){

            String d1 = date;
            String d2 = obj[i].date;

            String dateBeforeString = d1.substring(6)+"-"+d1.substring(3,5)+"-"+d1.substring(0,2);
            String dateAfterString = d2.substring(6)+"-"+d2.substring(3,5)+"-"+d2.substring(0,2);

            //Parsing the date
            LocalDate dateBefore = LocalDate.parse(dateBeforeString);
            LocalDate dateAfter = LocalDate.parse(dateAfterString);

            //calculating number of days in between
            long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
            if(noOfDaysBetween>=21){
                count++;
            }

        }
        return count;
    }

    public static Boolean check1(String date){
        String d1 = date;
        String d2 = "01/04/2020";
        String dateBeforeString = d1.substring(6)+"-"+d1.substring(3,5)+"-"+d1.substring(0,2);
        String dateAfterString = d2.substring(6)+"-"+d2.substring(3,5)+"-"+d2.substring(0,2);

        //Parsing the date
        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);

        //calculating number of days in between
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        if(noOfDaysBetween>0){
            return false;
        }
        else{
            return true;
        }
    }
    public static Boolean check2(String date){
        String d1 = date;
        String d2 = "31/08/2020";
        String dateBeforeString = d1.substring(6)+"-"+d1.substring(3,5)+"-"+d1.substring(0,2);
        String dateAfterString = d2.substring(6)+"-"+d2.substring(3,5)+"-"+d2.substring(0,2);

        //Parsing the date
        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);

        //calculating number of days in between
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        if(noOfDaysBetween<0){
            return false;
        }
        else{
            return true;
        }
    }

    public static int noOfDays(String date1, String date2){
        String d1 = date2;
        String d2 = date1;

        String dateBeforeString = d1.substring(6)+"-"+d1.substring(3,5)+"-"+d1.substring(0,2);
        String dateAfterString = d2.substring(6)+"-"+d2.substring(3,5)+"-"+d2.substring(0,2);

        //Parsing the date
        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);

        //calculating number of days in between
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        return (int) noOfDaysBetween;
    }

}

class CovidUpdates<obj> extends JFrame implements ActionListener {

    JTextField d;
    JCheckBox t1,t2,t3,t4;
    JButton b;
    JTextArea l1,l2;
    JLabel l;

    public CovidUpdates(){

        setTitle("Covid Updates");

        d = new JTextField(40);
        d.setBounds( 100, 50, 60, 20 );
        t1 = new JCheckBox("A");
        t2 = new JCheckBox("B");
        t3 = new JCheckBox("C");
        t4 = new JCheckBox("D");
        b = new JButton("ok");
        l = new JLabel("Date: ");
        b.setBounds(80,300,120,40);
        l1 = new JTextArea(40,65);
        l2 = new JTextArea(1,5);

        add(l);
        add(d);
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(b);
        add(l1);

        b.addActionListener(this);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(850,750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void actionPerformed(ActionEvent ae) {
        l1.setText(null);

        Covid19Data.Patient[] obj = new Covid19Data.Patient[20];

        obj[0] = new Covid19Data.Patient("Flora", 'A',6,"01/04/2020","22/04/2020");
        obj[1] = new Covid19Data.Patient("Denys", 'B',24,"01/04/2020", "22/04/2020");
        obj[2] = new Covid19Data.Patient("Jim", 'C',42,"18/05/2020", "08/06/2020");
        obj[3] = new Covid19Data.Patient("Hazel", 'D',87,"23/06/2020", "14/07/2020");
        obj[4] = new Covid19Data.Patient("Caery", 'A',72,"01/06/2020", "22/06/2020");
        obj[5] = new Covid19Data.Patient("David", 'B',7,"14/06/2020", "05/07/2020");
        obj[6] = new Covid19Data.Patient("Kevim", 'D',37,"05/06/2020", "26/06/2020");
        obj[7] = new Covid19Data.Patient("Tom", 'D',67,"20/06/2020", "11/07/2020");
        obj[8] = new Covid19Data.Patient("Bob", 'A',74,"04/07/2020", "25/07/2020");
        obj[9] = new Covid19Data.Patient("Rachel", 'C',48,"24/07/2020", "14/08/2020");
        obj[10] = new Covid19Data.Patient("Thomas", 'C',21,"11/06/2020", "02/07/2020");
        obj[11] = new Covid19Data.Patient("Mary", 'D',17,"21/06/2020", "12/07/2020");
        obj[12] = new Covid19Data.Patient("Smith", 'A',89,"07/08/2020", "28/08/2020");
        obj[13] = new Covid19Data.Patient("Pearson", 'B',47,"04/06/2020", "25/06/2020");
        obj[14] = new Covid19Data.Patient("Anderson", 'B',62,"27/07/2020", "17/08/2020");
        obj[15] = new Covid19Data.Patient("Johnson", 'D',10,"01/08/2020", "22/08/2020");
        obj[16] = new Covid19Data.Patient("Robertz", 'A',50,"09/08/2020", "30/08/2020");
        obj[17] = new Covid19Data.Patient("Julie", 'B',86,"02/05/2020", "23/05/2020");
        obj[18] = new Covid19Data.Patient("Edith", 'D',42,"07/06/2020", "28/06/2020");
        obj[19] = new Covid19Data.Patient("John", 'D',95,"01/06/2020", "22/06/2020");

        String date = d.getText();


        int totalCases=0;
        int activeCases=0;
        int recoveredCases=0;
        if(!d.getText().equals("")){
            if(Covid19Data.check1(date) && Covid19Data.check2(date)){
                if(t1.isSelected()==true){
                    l1.append("\t\t\tTOWER A\n");
                    l1.append("Name\tAge\tTower\tDate of Reporting\tDate of Recovery\tStatus\n\n");

                    for(int i=0;i<20;i++){

                        int noOfDaysBetween = Covid19Data.noOfDays(date,obj[i].date);

                        if(noOfDaysBetween>=21 && obj[i].tower=='A'){
                            l1.append(obj[i].name+"\t"+obj[i].age+"\t"+obj[i].tower+"\t"+obj[i].date+"\t\t"+obj[i].dateOfCure+"\t\t"+"Recovered"+"\n");
                            totalCases++;
                            recoveredCases++;
                        }
                        else if(noOfDaysBetween<21 && noOfDaysBetween>=0 && obj[i].tower=='A'){
                            l1.append(obj[i].name+"\t"+obj[i].age+"\t"+obj[i].tower+"\t"+obj[i].date+"\t\t"+obj[i].dateOfCure+"\t\t"+"Active"+"\n");
                            totalCases++;
                            activeCases++;
                        }
                    }
                    l1.append("\n");
                }

                if(t2.isSelected()==true){
                    l1.append("\t\t\tTOWER B\n");
                    l1.append("Name\tAge\tTower\tDate of Reporting\tDate of Recovery\tStatus\n\n");
                    for(int i=0;i<20;i++){

                        int noOfDaysBetween = Covid19Data.noOfDays(date,obj[i].date);

                        if(noOfDaysBetween>=21 && obj[i].tower=='B'){
                            l1.append(obj[i].name+"\t"+obj[i].age+"\t"+obj[i].tower+"\t"+obj[i].date+"\t\t"+obj[i].dateOfCure+"\t\t"+"Recovered"+"\n");
                            totalCases++;
                            recoveredCases++;
                        }
                        else if(noOfDaysBetween<21 && noOfDaysBetween>=0 && obj[i].tower=='B'){
                            l1.append(obj[i].name+"\t"+obj[i].age+"\t"+obj[i].tower+"\t"+obj[i].date+"\t\t"+obj[i].dateOfCure+"\t\t"+"Active"+"\n");
                            totalCases++;
                            activeCases++;
                        }
                    }
                    l1.append("\n");
                }

                if(t3.isSelected()==true){
                    l1.append("\t\t\tTOWER C\n");
                    l1.append("Name\tAge\tTower\tDate of Reporting\tDate of Recovery\tStatus\n\n");
                    for(int i=0;i<20;i++){

                        int noOfDaysBetween = Covid19Data.noOfDays(date,obj[i].date);

                        if(noOfDaysBetween>=21 && obj[i].tower=='C'){
                            l1.append(obj[i].name+"\t"+obj[i].age+"\t"+obj[i].tower+"\t"+obj[i].date+"\t\t"+obj[i].dateOfCure+"\t\t"+"Recovered"+"\n");
                            totalCases++;
                            recoveredCases++;
                        }
                        else if(noOfDaysBetween<21 && noOfDaysBetween>=0 && obj[i].tower=='C'){
                            l1.append(obj[i].name+"\t"+obj[i].age+"\t"+obj[i].tower+"\t"+obj[i].date+"\t\t"+obj[i].dateOfCure+"\t\t"+"Active"+"\n");
                            totalCases++;
                            activeCases++;
                        }
                    }
                    l1.append("\n");
                }

                if(t4.isSelected()==true){
                    l1.append("\t\t\tTOWER D\n");
                    l1.append("Name\tAge\tTower\tDate of Reporting\tDate of Recovery\tStatus\n\n");
                    for(int i=0;i<20;i++){

                        int noOfDaysBetween = Covid19Data.noOfDays(date,obj[i].date);

                        if(noOfDaysBetween>=21 && obj[i].tower=='D'){
                            l1.append(obj[i].name+"\t"+obj[i].age+"\t"+obj[i].tower+"\t"+obj[i].date+"\t\t"+obj[i].dateOfCure+"\t\t"+"Recovered"+"\n");
                            totalCases++;
                            recoveredCases++;
                        }
                        else if(noOfDaysBetween<21 && noOfDaysBetween>=0 && obj[i].tower=='D'){
                            l1.append(obj[i].name+"\t"+obj[i].age+"\t"+obj[i].tower+"\t"+obj[i].date+"\t\t"+obj[i].dateOfCure+"\t\t"+"Active"+"\n");
                            totalCases++;
                            activeCases++;
                        }
                    }
                    l1.append("\n");
                }
            }
        }
        l1.append("\n\nTotal cases reported so far  : "+totalCases+"\n");
        l1.append("Recovered cases  : "+recoveredCases+"\n");
        l1.append("Active cases  : "+activeCases+"\n");
    }
}
