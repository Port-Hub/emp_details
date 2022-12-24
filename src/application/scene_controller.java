package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class scene_controller {
	
	@FXML
	private Button LI_loginButton;
	@FXML
	private Label LI_passwordcheck;
	@FXML
	private TextField LI_userName;
	@FXML
	private TextField LI_password;
	@FXML
	private Button snd_createprofile;
	@FXML
	private TextField snd_fn,snd_ln,snd_dob,snd_gnd,snd_email,snd_contact,snd_un,snd_pass,snd_add,snd_city,snd_pin;
	@FXML
	private Label snd_qq,fetch_f,fetch_ln,fetch_DOB,fetch_gender,fetch_email,fetch_contact,fetch_address,fetch_city;
	@FXML
	private Label fetch_pincode;
	private Stage stage;
	private Scene scene;
	
	
	
	
	public void loginbuttononaction(ActionEvent event)throws IOException {
		if(LI_userName.getText().isBlank()==false && LI_password.getText().isBlank()==false) {
			LI_passwordcheck.setText("Tried to login");		
			if(validatelogin()) {
				
				Parent root = FXMLLoader.load(getClass().getResource("View Details.fxml"));
				
				stage=(Stage)((Node)event.getSource()).getScene().getWindow();
				scene= new Scene(root);
				displayuser();
				stage.setScene(scene);
				stage.show();
				
			}
		}
		else {
			LI_passwordcheck.setText("Username/password fields cannot be blank");
		}
		
		
		
	}
	
	public void accountbuttononact(ActionEvent e) {
		if(snd_fn.getText().isBlank()==false && snd_ln.getText().isBlank()==false&& snd_dob.getText().isBlank()==false&& snd_gnd.getText().isBlank()==false&& snd_email.getText().isBlank()==false&& snd_contact.getText().isBlank()==false && snd_un.getText().isBlank()==false&& snd_pass.getText().isBlank()==false && snd_add.getText().isBlank()==false&& snd_pin.getText().isBlank()==false && snd_city.getText().isBlank()==false) {
			
			adduser();
		}
		else {
			System.out.println("button clicked");
			
		}
		
	}

	public void switchtocreate(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LI_fxml.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	public void switchtodetails(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("user_details.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene= new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void adduser() {
		databaseconnection connectnow = new databaseconnection();
		Connection connectDB =connectnow.getConncetion();
		String useradd = "insert into new_table (FN,LN,DOB,Address,Contact,UN,PWD,Gender,Email,City,Pincode) values ('"+snd_fn.getText()+"','"+snd_ln.getText()+"','"+snd_dob.getText()+"','"+snd_add.getText()+"','"+snd_contact.getText()+"','"+snd_un.getText()+"','"+snd_pass.getText()+"','"+snd_gnd.getText()+"','"+snd_email.getText()+"','"+snd_city.getText()+"','"+snd_pin.getText()+"')" ; 
		try {
			
			Statement statement = connectDB.createStatement();
			statement.executeUpdate(useradd);
			snd_qq.setText("User added");
			System.out.println("added");
			}

		catch(Exception e) {
			System.out.print(e);
		}	
		
		
		
	}
	
	public boolean validatelogin() {
		
		databaseconnection connectnow = new databaseconnection();
		Connection connectDB =connectnow.getConncetion();
		String verifyLogin = "SELECT count(1) FROM new_table WHERE un='"+LI_userName.getText()+"' AND PWD = '"+LI_password.getText()+"';";
		try {
			
			Statement statement = connectDB.createStatement();
			ResultSet queryResult= statement.executeQuery(verifyLogin);
			while(queryResult.next()) {
				if(queryResult.getInt(1)==1) {
					LI_passwordcheck.setText("Correct password");
					return true;
					
				}
				else {
					LI_passwordcheck.setText("Wrong password");
					return false;
				}
				
			}
			
			
			
		} catch(Exception e) {
			return false;
		}	
		return false;
	}
	
	
	
	
	public void displayuser() {
		databaseconnection connectnow = new databaseconnection();
		Connection connectDB =connectnow.getConncetion();
		String verifyLogin = "SELECT * FROM new_table WHERE UN='"+LI_userName.getText()+"';";
		try {
			
			Statement statement = connectDB.createStatement();
			ResultSet queryResult= statement.executeQuery(verifyLogin);
			
			while(queryResult.next()) {
				
				System.out.println(((Object)queryResult.getString("DOB")).getClass().getSimpleName()); 
				
				System.out.println(queryResult.getString(2));
				
				fetch_f.setText(queryResult.getString(2));
				fetch_ln.setText(queryResult.getString("LN")); 
				fetch_DOB.setText(queryResult.getString("DOB"));
				fetch_address.setText(queryResult.getString("Address"));
				fetch_gender.setText(queryResult.getString("Gender"));
				fetch_email.setText(queryResult.getString("Email"));
				fetch_contact.setText(queryResult.getString("Contact"));
				
				
				fetch_city.setText(queryResult.getString("City"));
				fetch_pincode.setText(queryResult.getString("Pincode")); 
				}
			}catch(Exception E) {
				System.out.print(E);
			}
			
	}	
}
