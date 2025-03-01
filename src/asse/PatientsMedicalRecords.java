package asse;


import java.util.ArrayList;
import java.util.List;


import acsse.csc03a3.Transaction;

/**
 * 
 * A Patients Medical Records class  which add patients details into a transaction
 * @param <T>
 */
public class PatientsMedicalRecords<T>  {

	
	
	//Patients Variables
	private String ID ;
	private String name ;
	private String surname ;
	private String gender ;
	private String procedure ;
	private String diagnosis ;
   
    
	//Declaring the list of Transactions
    private List<Transaction<T>> transactions;
   
    
    
	
    /**
     *  A PatientsMedicalRecords Constructor
     * @param id
     * @param name
     * @param surname
     * @param gender
     * @param contact
     * @param address
     * @param procedure
     * @param diagnosis
     */
	public PatientsMedicalRecords(String id, String name, String surname, String gender, String procedure, String diagnosis) {
		
		this.setID(id);
		this.setName(name);
		this.setSurname(surname);
		this.setGender(gender);
		this.setProcedure(procedure);
		this.setDiagnosis(diagnosis);
	
		this.transactions = new ArrayList<>();
		
		
	}
	
	
	
	

	
	
	/**
	 * this method returns the list of transaction
	 * @return transactions
	 */
    public List<Transaction<T>> RerieveTransactions() {
        return transactions;
    }
	
    
    
    /* a method to  get latest transaction
     * @return the latest transaction
     */
    public Transaction<T> RerieveLatestTransaction() {
        if (transactions.isEmpty()) {
            return null;
        }
        return transactions.get(transactions.size() - 1);
    }

 
    
 
    
    /** 
     * A method to  Update patient information
     * 
     * @param name
     * @param surname
     * @param gender
     *
     */
    public void updatePatientInformation(String name, String surname, String gender, String procedure, String diagnosis) {
    	
    	
    	
    	 if (name != null && !name.isEmpty()) {
    	        setName(name);
    	    }
    	    if (surname != null && !surname.isEmpty()) {
    	        setSurname(surname);
    	    }
    	    if (gender != null && !gender.isEmpty()) {
    	        setGender(gender);
    	    }
    	    if (procedure != null && !procedure.isEmpty()) {
    	        setProcedure(procedure);
    	    }
    	    if (diagnosis != null && !diagnosis.isEmpty()) {
    	        setDiagnosis(diagnosis);
    	    }
    	
    }

    
   
    /**
     * A method to retrieve patient details.
     * @return A string containing patient details.
     */
    public String readPatientDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Patient ID: ").append(ID).append("\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Surname: ").append(surname).append("\n");
        details.append("Gender: ").append(gender).append("\n");
        details.append("Procedure: ").append(procedure).append("\n");
        details.append("Diagnosis: ").append(diagnosis).append("\n");
        return details.toString();
    }
   

    /**
     * 
     * A function that will create the Patients medical transaction
     * 
     * @return Transactions
     */
    
	public Transaction<PatientsMedicalRecords<T>> createTransaction() {
         
    	 
		
	// Create a new instance of PatientsMedicalRecords
    PatientsMedicalRecords<T> medicalRecords = new PatientsMedicalRecords<>(ID, name, surname, gender, procedure, diagnosis);
     
     
    
      // Create a new Transaction with PatientsMedicalRecords as data
      return new Transaction<>(ID, name, medicalRecords);
		  

    	 
      }


	/**
	 * A method to delete specific patient details by ID.
	 * @param patientID The ID of the patient whose details are to be deleted.
	 * @return true if the patient details were found and deleted, false otherwise.
	 */
	public boolean deletePatientDetails(String patientID) {
	    // Check if the patient with the given ID exists
	    if (getID().equals(patientID)) {
	        // Reset patient details to null or empty strings
	        setID(null);
	        setName(null);
	        setSurname(null);
	        setGender(null);
	        setProcedure(null);
	        setDiagnosis(null);
	        
	        // You can also reset any other relevant fields here
	        
	        return true; // Patient details deleted successfully
	    } else {
	        return false; // Patient with the given ID not found
	    }
	}
	
	
	
     /**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}



	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}



	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}



	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}



	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}




	/**
	 * @return the procedure
	 */
	public String getProcedure() {
		return procedure;
	}



	/**
	 * @param procedure the procedure to set
	 */
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}



	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}



	/**
	 * @param diagnosis the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}


   

}
