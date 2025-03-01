package asse;


import java.util.ArrayList;
import java.util.List;


import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.BackgroundFill;
/**
 * 
 * A class to set up the GUI Components
 * 
 */
public class GuiClass extends GridPane{
    
	 
	   // Create a new instance of chain
	   Chain<String> chain = new Chain<String>();
	
	
	
	    //The TextField controls of GUI
		private TextField ID = new TextField(); 
		private TextField Name = new TextField(); 
		private TextField Surname = new TextField(); 
		private ComboBox<String> Gender = new ComboBox<>(); 		
		private TextField Procedure = new TextField(); 
		private TextField Diagnosis = new TextField(); 
	
	
		
		//TheTextArea controls of GUI
		private TextArea txtDisplay= new TextArea();
		
		//The Label controls of GUI
		private Label IlbID;
		private Label lblName;
		private Label lblUsername;
		private Label lblGender;		
		private Label lblProcedure;
		private Label lblDiagnosis;
		
		//Button variabLes for GUI components
		private Button Capture;
		private Button View;
		private Button Update;
		private Button Delete;
		private Button Reset;
		
		// The TableView for displaying medical records
	    private TableView<PatientsMedicalRecords<String>> tableView = new TableView<>();
		
	    
	 
	    /**
	     *  A GuiClass Constructor 
	     * 
	     */
	   
		 public GuiClass() {
		
	    	//Calling the GUI method
			  GUI();
			 
			  
			  
			 //capturing patients details into a block chain
				Capture.setOnAction(e -> {
		            
					
					String lD = ID.getText();
		            String name = Name.getText();
		            String surname = Surname.getText();
		            String gender = Gender.getValue();;
		            String procedure = Procedure.getText();
		            String diagnosis = Diagnosis.getText();
		            
		           
		            // clear the txtDisplay 
		            txtDisplay.clear();
		           
		            
		            //Checking if all the text fields are  not empty
		            
		            if(lD.isEmpty()|| name.isEmpty()|| surname.isEmpty()|| gender.isEmpty()|| procedure.isEmpty()|| diagnosis.isEmpty()) {
		            	// Create a new alert
			              Alert alert = new Alert(AlertType.INFORMATION);
			              alert.setTitle("Patients informaion");
			              alert.setHeaderText("");
			              alert.setContentText("Please Enter all Text Field.");

			              // Show the alert
			              alert.showAndWait();
		            }
		            else
		            {

			            // Create a new health record block
			             PatientsMedicalRecords<String> PatientsDetails = new PatientsMedicalRecords<String>(lD,name,surname,gender,procedure, diagnosis);
			            
			              
			             // Add the transaction data to the block chain
			             chain.AddToBlock(PatientsDetails);
			             
			            
			             
			             
			             
			             //
			             txtDisplay.appendText("Blockchain Information" + "\r\n");
				         txtDisplay.appendText("\r\n");
				         //   
			             txtDisplay.appendText(chain.displayBlockchain() + "\r\n");
			              
			            
			            
			             
			            
			             // creating a list of Patients
			             List<PatientsMedicalRecords<String>> transactions = new ArrayList<>();
					      
			             
			             List<PatientsMedicalRecords<String>> allRecords = chain.patientsMedicalRecordsTransactions();

			             // Iterate over each Patients Medical record and add it to the transactions list tableView
			             for (PatientsMedicalRecords<String> Patients : allRecords) {
			                 transactions.add(Patients);
			             }
					      
					      
					      
					      
					      
					      // Adding the  Patients Medical Records to the table
			  	          tableView.setItems(FXCollections.observableArrayList(transactions));
			             

			             // Create a new alert
			              Alert alert = new Alert(AlertType.INFORMATION);
			              alert.setTitle("Patients informaion");
			              alert.setHeaderText(null);
			              alert.setContentText("You have successful captured the Patients informaion.");

			              // Show the alert
			              alert.showAndWait();
			          
			             // Clear TextField after the data has been captured
			             ID.clear();
						 Name.clear();
						 Surname.clear();
						 Gender.setValue(null);
						 Procedure.clear();
				         Diagnosis.clear();
				        
		            }
		            
		            
		            
			            
		        }); 
			 
				
				
				
				 //A button to View
				View.setOnAction( e->{ 
						
					chain.patientsMedicalRecordsTransactions(); 
					 
				});
				 
				 
				 
				 //A button to Update
				Update.setOnAction( e->{ 
						
					 
					
					
					 
				});
				 
				 
				 
				 //A button to Delete
				Delete.setOnAction( e->{ 
						
					
					
					
					
				}); 
				 
				 
				
				
				 //A button to reset
				 Reset.setOnAction( e->{ 
						
					 ID.clear();
					 Name.clear();
					 Surname.clear();
					 Gender.setValue(null);
					 Procedure.clear();
			         Diagnosis.clear();
			         txtDisplay.clear();   
					 
				});
			
				
				
				
	    }
	
	    /**
	     * 
	     * A method to set up the GUI
	     * 
	     */
	  	
		  @SuppressWarnings("unchecked")
		private void GUI(){
	  		
	        
		    // Set background color
	        setBackground(new Background(new BackgroundFill(Color.DARKSLATEGREY, null, null)));
		 
	        // Add options to the ComboBox
	        Gender.getItems().addAll("Male", "Female");
	        
	        //Setting the width
	        Gender.setPrefWidth(150);
	  		
	        
	        
	  		//LABEL components
	  		IlbID = new Label("Patient lD ");
	  		lblName = new Label("Patient Name");
	  		lblUsername = new Label("Patient Surname");
	  		lblGender = new Label("Patient  Gender");	  		
	  		lblProcedure	 = new Label("Medical Procedure");
	  		lblDiagnosis = new Label("Diagnosis ");
	  		
	  				
	  		
	  		//The Display for the user
	  		txtDisplay = new TextArea();
	  		txtDisplay.setPrefWidth(30);
	  		txtDisplay.setPrefHeight(200);;
	  		
	  		
	  		
	  		
	  		//CRUD buttons and the reset
	  		Capture = new Button("Capture ");
	  		View = new Button("View  ");
	  		Update = new Button("Update ");
	  		Delete = new Button("Delete  ");
	  		Reset = new Button("Clear ");
	  		
	  		
	  		
	  	   //Setting the color for Labels
	  		IlbID.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");
	  		lblName.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");
	  		lblUsername.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");
	  		lblGender.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");
	  		lblProcedure.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");
	  		lblDiagnosis.setStyle("-fx-text-fill: white;-fx-font-size: 14px;");
	  		
	  		
	  		
	  	   //Setting the color for buttons
	  	   Capture.setStyle("-fx-background-color: green; -fx-text-fill: white;-fx-font-size: 18px;");
	  	   View.setStyle("-fx-background-color: darkorange; -fx-text-fill: white;-fx-font-size: 18px;");
	  	   Update.setStyle("-fx-background-color: blue;-fx-text-fill: white;-fx-font-size: 18px;");
	  	   Delete.setStyle("-fx-background-color: #ff0000;-fx-text-fill: white;-fx-font-size: 18px;");
	  	   Reset.setStyle("-fx-background-color:  yellow;-fx-text-fill: black;-fx-font-size: 18px;");
	  		
	  	 
	        // Define columns for TableView
	        TableColumn<PatientsMedicalRecords<String>, String> id = new TableColumn<>("ID");
	        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
   
	        TableColumn<PatientsMedicalRecords<String>, String> name = new TableColumn<>("Name");
	        name.setCellValueFactory(new PropertyValueFactory<>("name"));

	        TableColumn<PatientsMedicalRecords<String>, String> surname = new TableColumn<>("Surname");
	        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));

	        TableColumn<PatientsMedicalRecords<String>, String> gender = new TableColumn<>("Gender");
	        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

	        TableColumn<PatientsMedicalRecords<String>, String> procedure = new TableColumn<>("Procedure");
	        procedure.setCellValueFactory(new PropertyValueFactory<>("procedure"));

	        TableColumn<PatientsMedicalRecords<String>, String> diagnosis = new TableColumn<>("Diagnosis");
	        diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
	       
	       
	        
	        
	        
	         //setting the width
	         id.setPrefWidth(150);
	         name.setPrefWidth(150);
	         surname.setPrefWidth(150);
	         gender.setPrefWidth(150);
	         procedure.setPrefWidth(150);
	         diagnosis.setPrefWidth(150);
	        
	        
	         
	        // Adding the columns to the TableView into the GUI
  	        tableView.getColumns().addAll(id,name,surname,gender,procedure, diagnosis);
	        
         
	      
	       
	  	   
	  		
			// setting the vertical gap, horizontal gap and  set the Alignment
	  		setPadding(new Insets(10, 10, 10, 10));
	  		setVgap(30);
			setHgap(70);
			setAlignment(Pos.BASELINE_LEFT);
			
			
			//adding components to the GUI
			add(lblName, 0,0);
			add(Name, 1,0);
			add(lblUsername, 2,0);
			add(Surname, 3,0);
			add(IlbID, 4,0);
			add(ID, 5,0);
			add(lblGender, 0,1);
			add(Gender, 1,1);
			add(lblProcedure, 2,1);
			add(Procedure, 3,1);
			add(lblDiagnosis, 4,1);
			add(Diagnosis, 5,1);			
			add(tableView, 1, 2, 5, 2);
			add(txtDisplay, 1,4,5,2);
			
			
			
			
			
			
			//Adding the buttons to the GUI
			add(Capture, 1,6);			
			add(View , 2,6);
			add(Update, 3,6);
			add(Delete, 4,6);
			add(Reset, 5,6);
				
			
	  	}
	
	  	
	
	
	
}
