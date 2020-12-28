package covid;

import java.util.*;

class HealthCareInstitute{
	private String name;
	private float oxygenCriteria;
	private float tempratureCriteria;
	private int numberOfBeds;
	String status;
	
	HealthCareInstitute(String name, float oxygenCriteria, float tempratureCriteria, int numberOfBeds){
		this.name = name;
		this.oxygenCriteria = oxygenCriteria;
		this.tempratureCriteria = tempratureCriteria;
		this.numberOfBeds = numberOfBeds;
	}
	
	public void setName(String n) {
		name = n;
	}
	public void setOxygenCriteria(int o) {
		oxygenCriteria = o;
	}
	public void setTempratureCriteria(int t) {
		tempratureCriteria = t;
	}
	public void setNumberOfBeds(int b) {
		numberOfBeds = b;
	}
	public String getName() {
		return name;
	}
	public float getOxygenCriteria() {
		return oxygenCriteria;
	}
	public float getTempratureCriteria() {
		return tempratureCriteria;
	}
	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void addInstitute(ArrayList<Patients> objP, int numberOfPatients){
		Scanner sc = new Scanner(System.in);
		
		System.out.println(name);
		System.out.println("Temperature should be <= "+tempratureCriteria);
		System.out.println("Oxygen levels should be >= "+oxygenCriteria);
		System.out.println("Number of Available beds – "+numberOfBeds);
		if(numberOfBeds>0)
			System.out.println("Admission Status – OPEN");
		else
			System.out.println("Admission Status – CLOSED");
		
		for(int i=0;i<numberOfPatients;i++) {
			if(!objP.get(i).addmissionStatus && objP.get(i).getOxygenLevel()>=oxygenCriteria && numberOfBeds>0) {
				System.out.print("Recovery days for admitted patient ID "+ objP.get(i).getID()+"- ");
				objP.get(i).recoveryDays = sc.nextInt();
				objP.get(i).addmissionStatus = true;
				objP.get(i).nameOfHealthcareInstitute = name;
				numberOfBeds--;
			}
		}
		for(int i=0;i<numberOfPatients;i++){
			if(!objP.get(i).addmissionStatus && objP.get(i).getTemprature()<=tempratureCriteria && numberOfBeds>0) {
				System.out.print("Recovery days for admitted patient ID -"+ objP.get(i).getID()+"- ");
				objP.get(i).recoveryDays = sc.nextInt();
				objP.get(i).addmissionStatus = true;
				objP.get(i).nameOfHealthcareInstitute = name;
				numberOfBeds--;
			}
		}
		
		if(numberOfBeds<=0) {
			status = "CLOSED";
		}
		else {
			status = "OPEN";
		}
		
	}
	
	public Boolean removeInstitute() {
		if(status == "CLOSED") {
			System.out.println(name);
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean isAdmitting() {
		if(status=="OPEN") {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void Display() {
		
			System.out.println(name);
			System.out.println("Temperature should be <= "+tempratureCriteria);
			System.out.println("Oxygen levels should be >= "+oxygenCriteria);
			System.out.println("Number of Available beds – "+numberOfBeds);
			System.out.println("Admission Status – "+status);
		
	}
	
}

class Patients{
	
	private String name;
	private float temprature;
	private float oxygenLevel;
	private int age;
	private int ID;
	int recoveryDays;
	Boolean addmissionStatus;
	String nameOfHealthcareInstitute;
	
	Patients(String name,float temprature, float oxygenLevel, int age, int ID){
		this.name = name;
		this.temprature = temprature;
		this.oxygenLevel = oxygenLevel;
		this.age = age;
		this.ID = ID;
		this.addmissionStatus = false;
	}
	
	public void setName(String n) {
		name=n;
	}
	public void setTemprature(float t) {
		temprature=t;
	}
	public void setOxygen(float o) {
		oxygenLevel = o;
	}
	public void setAge(int a) {
		age = a;
	}
	public void setID(int i) {
		ID = i;
	}
	public String getName() {
		return name;
	}
	public float getTemprature() {
		return temprature;
	}
	public float getOxygenLevel() {
		return oxygenLevel;
	}
	public int getAge() {
		return age;
	}
	public int getID() {
		return ID;
	}
	
	public Boolean removeAccount() {
		
		if(addmissionStatus) {
			System.out.println(ID);
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean isAdmitted() {
		if(addmissionStatus) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void Display() {
		System.out.println(name);
		System.out.println("Temprature is "+ temprature);
		System.out.println("Oxygen Level is "+ oxygenLevel);
		if(addmissionStatus)
			System.out.println("Admission Status is- Admitted");
		else
			System.out.println("Admission status- Not Admitted");
		System.out.println("Admitting Institute- "+nameOfHealthcareInstitute);
	}
	
	public void DisplayID() {
		System.out.println(ID+" "+name);
	}
}

public class CovidData {
	
	public static void main(String args[]) {
		
		//System.out.println();
		
		Scanner sc = new Scanner(System.in);
		
		int numberOfPatients = sc.nextInt();
		
		ArrayList<Patients> objP = new ArrayList<Patients>();
		for(int i=0;i<numberOfPatients;i++) {
			String name = sc.next();
			float temp = sc.nextFloat();
			float oxygen = sc.nextFloat();
			int age = sc.nextInt();
			Patients obj = new Patients(name,temp,oxygen,age,i+1);
			objP.add(obj);
			
		}
		
		ArrayList<HealthCareInstitute> objH = new ArrayList<HealthCareInstitute>(); 
		
		while(true){
			
			int q = sc.nextInt();
			
			
			if(q==1) {
				
				String name = sc.next();
				System.out.print("Temperature Criteria - ");
				int temprature = sc.nextInt();
				System.out.print("Oxygen Levels - ");
				int oxygen = sc.nextInt();
				System.out.print("Number of Available beds - ");
				int beds = sc.nextInt();
				
				HealthCareInstitute obj = new HealthCareInstitute(name,oxygen,temprature,beds);
				obj.addInstitute(objP,objP.size());
				objH.add(obj);
			}
			else if(q==2) {
				System.out.println("Account ID removed of admitted patients");
				for(int i=0;i<objP.size();) {
					if(objP.get(i).removeAccount()) {
						objP.remove(i);
					}
					else {
						i++;
					}
				}
			}
			else if(q==3) {
				System.out.println("Accounts removed of Institute whose admission is closed");
				int n = objH.size();
				for(int i=0;i<n;) {
					if(objH.get(i).removeInstitute()) {
						objH.remove(i);
						n--;
					}
					else {
						i++;
					}
				}
			}
			else if(q==4) {
				int countP=0;
				for(int i=0;i<objP.size();i++) {
					if(!objP.get(i).isAdmitted()) {
						countP++;
					}
				}
				System.out.println(countP+" patients");
			}
			else if(q==5)
			{	
				int countH=0;
				for(int i=0;i<objH.size();i++) {
					if(objH.get(i).isAdmitting()) {
						countH++;
					}
				}
				System.out.println(countH+" institutes are admitting patients currently");
			}	
			else if(q==6) {
				String inst = sc.next();
				for(int i=0;i<objH.size();i++) {
					if(objH.get(i).getName().equals(inst)) {
						objH.get(i).Display();
					}
				}
			}
			else if(q==7) {
				int ID = sc.nextInt();
				for(int i=0;i<objP.size();i++) {
					if(objP.get(i).getID()==ID) {
						objP.get(i).Display();
					}
				}
			}
			else if(q==8) {
				for(int i=0;i<objP.size();i++) {
					objP.get(i).DisplayID();
				}
			}
			else if(q==9) {
				String inst = sc.next();
				for(int i=0;i<objH.size();i++) {
					if(objH.get(i).getName().equals(inst)){
						for(int j=0;j<objP.size();j++) {
							if(objP.get(j).addmissionStatus && objP.get(j).nameOfHealthcareInstitute.equals(inst)) {
								System.out.println(objP.get(j).getName()+", "+" recovery time is "+objP.get(j).recoveryDays+" days");
							}
						}
					}
				}
			}
		}
	}
}