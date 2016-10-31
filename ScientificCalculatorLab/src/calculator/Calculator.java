package calculator;

import java.io.File;

/* Dylan Fox, Atharva Baht, Rachel Walker, Nelson Le, and Camilo Carvo*/

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

@SuppressWarnings("restriction")
public class Calculator extends Application {
	
	private double var1, var2, tempDouble;
	private String temp = "", op;
	private boolean contOper;

	
	private TextField displayField;
	
	CalcState rfo = new RFO();
	CalcState rso = new RSO();
	CalcState err = new ERR();
	
	CalcState currentState = rfo;

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage primaryStage){
		int sceneHeight = 650, sceneWidth = 550;
		displayField = new TextField();
		displayField.setEditable(false);
		displayField.setPrefHeight(162);
		displayField.setPrefWidth(sceneWidth);
		displayField.setText("Hi Ladan :)");
		displayField.setStyle("-fx-font: 60 arial; -fx-control-inner-background: #46abd6; -fx-text-fill: white;"
				+ "-fx-alignment: center-right");

		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(0);
		gridPane.setVgap(0);
		gridPane.setPadding(new Insets(0, 0, 
					    0,0));
		gridPane.setStyle("-fx-background-color: #356aa3");
		
		Button pi = new Button("π");
		pi.setPrefHeight(100);
		pi.setPrefWidth(100);
		gridPane.add(pi, 0, 0);
		pi.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		pi.setOnAction(e -> {
			throw new UnsupportedOperationException("Write a method for power function");
		});
		
		Button one = new Button("1");
		one.setPrefHeight(100);
		one.setPrefWidth(100);
		gridPane.add(one,1,0);
		one.setStyle("-fx-font: 30 arial; -fx-base: #d0dcef;");
		one.setOnAction(e -> {
			currentState.numberInput("1");
		});
		
		Button two = new Button("2");
		two.setPrefHeight(100);
		two.setPrefWidth(100);
		gridPane.add(two,2,0);
		two.setStyle("-fx-font: 30 arial; -fx-base: #d0dcef;");
		two.setOnAction(e -> {
			currentState.numberInput("2");
		});
		
		Button three = new Button("3");
		three.setPrefHeight(100);
		three.setPrefWidth(100);
		gridPane.add(three,3,0);
		three.setStyle("-fx-font: 30 arial; -fx-base: #d0dcef;");
		three.setOnAction(e -> {
			currentState.numberInput("3");
		});
		
		Button four = new Button("4");
		four.setPrefHeight(100);
		four.setPrefWidth(100);
		gridPane.add(four,1,1);
		four.setStyle("-fx-font: 30 arial; -fx-base: #d0dcef;");
		four.setOnAction(e -> {
			currentState.numberInput("4");
		});
		
		Button five = new Button("5");
		five.setPrefHeight(100);
		five.setPrefWidth(100);
		gridPane.add(five,2,1);
		five.setStyle("-fx-font: 30 arial; -fx-base: #d0dcef;");
		five.setOnAction(e -> {
			currentState.numberInput("5");
		});
		
		Button six = new Button("6");
		six.setPrefHeight(100);
		six.setPrefWidth(100);
		gridPane.add(six,3,1);
		six.setStyle("-fx-font: 30 arial; -fx-base: #d0dcef;");
		six.setOnAction(e -> {
			currentState.numberInput("6");
		});
		
		Button seven = new Button("7");
		seven.setPrefHeight(100);
		seven.setPrefWidth(100);
		gridPane.add(seven,1,2);
		seven.setStyle("-fx-font: 30 arial; -fx-base: #d0dcef;");
		seven.setOnAction(e -> {
			currentState.numberInput("7");
		});
		
		Button eight = new Button("8");
		eight.setPrefHeight(100);
		eight.setPrefWidth(100);
		gridPane.add(eight,2,2);
		eight.setStyle("-fx-font: 30 arial; -fx-base: #d0dcef;");
		eight.setOnAction(e -> {
			currentState.numberInput("8");
		});
		
		Button nine = new Button("9");
		nine.setPrefHeight(100);
		nine.setPrefWidth(100);
		gridPane.add(nine,3,2);
		nine.setStyle("-fx-font: 30 arial; -fx-base: #d0dcef;");
		nine.setOnAction(e -> {
			currentState.numberInput("9");
		});
		
		Button zero = new Button("0");
		zero.setPrefHeight(100);
		zero.setPrefWidth(100);
		gridPane.add(zero,2,3);
		zero.setStyle("-fx-font: 30 arial; -fx-base: #d0dcef;");
		zero.setOnAction(e -> {
			currentState.numberInput("0");
		});
		
		Button equals = new Button("=");
		equals.setPrefHeight(100);
		equals.setPrefWidth(100);
		gridPane.add(equals,3,3);
		equals.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		equals.setOnAction(e -> {
			currentState.equals(true);
		});
		
		Button dec = new Button(".");
		dec.setPrefHeight(100);
		dec.setPrefWidth(100);
		gridPane.add(dec,1,3);
		dec.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		dec.setOnAction(e -> {
			currentState.numberInput(".");
		});
		
		Button back = new Button("<--");
		back.setPrefHeight(100);
		back.setPrefWidth(100);
		gridPane.add(back,5,0);
		back.setStyle("-fx-font: 25 arial; -fx-base: #0b50a0;");
		back.setOnAction(e -> {
			currentState.backspace();
		});
		
		Button psms = new Button("+/-");
		psms.setPrefHeight(100);
		psms.setPrefWidth(100);
		gridPane.add(psms,4,0);
		psms.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		psms.setOnAction(e -> {
			currentState.unaryOp("+/-");
		});
		
		Button ce = new Button("CE");
		ce.setPrefHeight(100);
		ce.setPrefWidth(100);
		gridPane.add(ce,6,0);
		ce.setStyle("-fx-font: 28 arial; -fx-base: #0b50a0;");
		ce.setOnAction(e -> {
			currentState.clear(false);
		});
		
		Button c = new Button("C");
		c.setPrefHeight(100);
		c.setPrefWidth(100);
		gridPane.add(c,6,1);
		c.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		c.setOnAction(e -> {
			currentState.clear(true);
		});
		
		Button power = new Button("x^y");
		power.setPrefHeight(100);
		power.setPrefWidth(100);
		gridPane.add(power, 6, 2);
		power.setStyle("-fx-font: 28 arial; -fx-base: #0b50a0;");
		power.setOnAction(e -> {
			throw new UnsupportedOperationException("Write a method for power function");
		});
		
		Button factorial = new Button("X! ");
		factorial.setPrefHeight(100);
		power.setPrefWidth(100);
		gridPane.add(factorial, 6, 3);
		factorial.setStyle("-fx-font: 31 arial; -fx-base: #0b50a0;");
		factorial.setOnAction(e -> {
			throw new UnsupportedOperationException("Write a method for factorial function");
		});
		
		Button plus = new Button("+");
		plus.setPrefHeight(100);
		plus.setPrefWidth(100);
		gridPane.add(plus,4,1);
		plus.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		plus.setOnAction(e -> {
			currentState.operatorInput("+");
		});
		
		Button minus = new Button("-");
		minus.setPrefHeight(100);
		minus.setPrefWidth(100);
		gridPane.add(minus,5,1);
		minus.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		minus.setOnAction(e -> {
			currentState.operatorInput("-");
		});
		
		Button mult = new Button("*");
		mult.setPrefHeight(100);
		mult.setPrefWidth(100);
		gridPane.add(mult,4,2);
		mult.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		mult.setOnAction(e -> {
			currentState.operatorInput("*");
		});
		
		Button div = new Button("/");
		div.setPrefHeight(100);
		div.setPrefWidth(100);
		gridPane.add(div,5,2);
		div.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		div.setOnAction(e -> {
			currentState.operatorInput("/");
		});
		
		Button euler = new Button("e");
		euler.setPrefHeight(100);
		euler.setPrefWidth(100);
		gridPane.add(euler, 0, 1);
		euler.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		euler.setOnAction(e -> {
			throw new UnsupportedOperationException("Implement euler's constant");
		});
		
		Button sin = new Button("sin");
		sin.setPrefHeight(100);
		sin.setPrefWidth(100);
		gridPane.add(sin, 0, 2);
		sin.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		sin.setOnAction(e -> {
			throw new UnsupportedOperationException("Implement Sine function");
		});
		
		Button cos = new Button("cos");
		cos.setPrefHeight(100);
		cos.setPrefWidth(100);
		gridPane.add(cos, 0, 3);
		cos.setStyle("-fx-font: 26 arial; -fx-base: #0b50a0;");
		cos.setOnAction(e -> {
			throw new UnsupportedOperationException("Implement Cosine function");
		});
		
		Button tan = new Button("tan");
		tan.setPrefHeight(100);
		tan.setPrefWidth(100);
		gridPane.add(tan, 0, 4);
		tan.setStyle("-fx-font: 26 arial; -fx-base: #0b50a0;");
		tan.setOnAction(e -> {
			throw new UnsupportedOperationException("Implement Tan function");
		});
		
		Button inv = new Button("1/x");
		inv.setPrefHeight(100);
		inv.setPrefWidth(100);
		gridPane.add(inv,4,3);
		inv.setStyle("-fx-font: 28 arial; -fx-base: #0b50a0;");
		inv.setOnAction(e -> {
			currentState.unaryOp("1/x");
		});
		
		Button sqrt = new Button("√");
		sqrt.setPrefHeight(100);
		sqrt.setPrefWidth(100);
		gridPane.add(sqrt,5,3);
		sqrt.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		sqrt.setOnAction(e -> {
			currentState.unaryOp("^.5");
		});
		
		final ComboBox<String> memoryCombBox = new ComboBox<String>();
		memoryCombBox.setPrefHeight(100);
		memoryCombBox.setPrefWidth(300);
		memoryCombBox.setValue("Memory Operations");
		memoryCombBox.getItems().addAll(
				"Memory Store",
				"Memory Retrieve"
				);
		memoryCombBox.setStyle("-fx-font: 18 arial; -fx-base: #0b50a0;");
		gridPane.add(memoryCombBox, 1, 4, 3, 1);
		
		final ToggleGroup radioGroup = new ToggleGroup();
		RadioButton radianButton = new RadioButton("Radians");
		RadioButton degreeButton = new RadioButton("Degrees");
		radianButton.setPrefHeight(100);
		radianButton.setPrefWidth(100);
		degreeButton.setPrefHeight(100);
		degreeButton.setPrefWidth(100);
		radianButton.setStyle("-fx-font: 14 arial; -fx-text-fill: white;");
		degreeButton.setStyle("-fx-font: 14 arial; -fx-text-fill: white;");
		radianButton.setOnAction(e -> {
			throw new UnsupportedOperationException("Implement toggle Radians");
		});
		degreeButton.setOnAction(e -> {
			throw new UnsupportedOperationException("Implement toggle Degrees");
		});
		radianButton.setToggleGroup(radioGroup);
		degreeButton.setToggleGroup(radioGroup);
		gridPane.add(radianButton, 5, 4);
		gridPane.add(degreeButton, 6, 4);
		
		Button musicButton = new Button("♫");
		musicButton.setPrefHeight(100);
		musicButton.setPrefWidth(100);
		musicButton.setStyle("-fx-font: 30 arial; -fx-base: #0b50a0;");
		musicButton.setOnAction(e -> {
			Media song = new Media(new File("Song.mp3").toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(song);
			mediaPlayer.play();
		});
		gridPane.add(musicButton, 4, 4);
		
		BorderPane bp = new BorderPane();
		bp.setTop(displayField);
		bp.setBottom(gridPane);
		BorderPane.setAlignment(gridPane, Pos.CENTER);
		
		Scene scene = new Scene(bp, sceneWidth, sceneHeight);
		primaryStage.setTitle("Proware Studios® Calculator 2016");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	private String truncateZeros(String str){
		int x  = str.length() - 1;
		
		if(str.charAt(x) != '0'){
			return str;
		}
		while(str.charAt(x-1) != '.'){
			if(str.charAt(x) == '0' && str.charAt(x-1) != '0'){
				return str.substring(0, x);
			}
			x--;
		}
		return str.substring(0,--x);
	}

	private int numDecimals(String str){
		int numDec = 0;
		
		for(int x = 0; x < str.length(); x++){
			if(str.charAt(x) == '.'){numDec++;}
		}
		return numDec;
	}
	public class RFO implements CalcState{
		public void numberInput(String num){
			if(temp.length() < 10){
				if(num.equals(".") && numDecimals(temp) == 1){
					displayField.setText("ERR");
					currentState = err;
					return;
				}else temp += num;
				if(temp.equals(".")){
					tempDouble = 0.0;
				}else tempDouble = Double.parseDouble(temp);
				displayField.setText(temp);
			}	
		}
		
		public void operatorInput(String oper){
			if(!temp.equals("") || contOper){
				op = oper;
				var1 = tempDouble;
				var2 = 0;
				temp = "";
				tempDouble = 0.0;
				displayField.setText(oper);
				currentState = rso;
			}
			
		}
		
		public double binaryOp(String oper,double v1,double v2){
			return 0.0;
		}
		
		public void unaryOp(String oper){
			if(oper.equals("1/x")){
				if(tempDouble == 0.0){
					displayField.setText("ERR");
					currentState = err;
				}else {
					tempDouble = 1/tempDouble;
					temp = truncateZeros(String.format("%.9f", tempDouble));
					displayField.setText(temp);
				}
			}else if(oper.equals("+/-")){
				tempDouble *= -1;
				temp = truncateZeros(String.format("%.9f", tempDouble));
				displayField.setText(temp);
			}else if(oper.equals("^.5")){
				if(tempDouble < 0.0){
					displayField.setText("ERR");
					currentState = err;
				}else{
					tempDouble = Math.pow(tempDouble, 0.5);
					temp = truncateZeros(String.format("%.9f", tempDouble));
					displayField.setText(temp);
				}
			}
		}
		
		public void equals(boolean changeState){
		}
		
		public void backspace(){
			if(temp.length() > 0){
				temp = temp.substring(0,temp.length()-1);
			} else temp = "";
			displayField.setText(temp);
			if(temp.length() > 0){
				tempDouble = Double.parseDouble(temp);
			} else tempDouble = 0.0;
		}
		
		public void clear(boolean clearAll){
			tempDouble = 0.0;
			temp = "";
			var1 = 0.0;
			var2 = 0.0;
			displayField.setText(temp);
		}
		
		
	}
	
	public class RSO implements CalcState{
		public void numberInput(String num){
			if(temp.length() < 10){
				if(num.equals(".") && numDecimals(temp) == 1){
					displayField.setText("ERR");
					currentState = err;
					return;
				}else temp += num;
				
				if(temp.equals(".")){
					tempDouble = 0.0;
				}else tempDouble = Double.parseDouble(temp);
				displayField.setText(temp);
			}	
		}
		
		public void operatorInput(String oper){
			if(!temp.equals("")){
				equals(false);
				op = oper;
			}
			
		}
		
		public double binaryOp(String oper, double v1, double v2){
			if(oper.equals("+")){
				return v1 + v2;
			} else if(oper.equals("-")){
				return v1 - v2;
			} else if(oper.equals("*")){
				return v1*v2;
			} else if(oper.equals("/")){
				return v1/v2;
			} else return 0.0;
		}
		
		public void unaryOp(String oper){
			if(oper.equals("1/x")){
				if(tempDouble == 0.0){
					displayField.setText("ERR");
					currentState = err;
				}else {
					tempDouble = 1/tempDouble;
					temp = truncateZeros(String.format("%.9f", tempDouble));
					displayField.setText(temp);
				}
			}else if(oper.equals("+/-")){
				tempDouble *= -1;
				temp = truncateZeros(String.format("%.9f", tempDouble));
				displayField.setText(temp);
			}else if(oper.equals("^.5")){
				if(tempDouble < 0.0){
					displayField.setText("ERR");
					currentState = err;
				}else{
					tempDouble = Math.pow(tempDouble, 0.5);
					temp = truncateZeros(String.format("%.9f", tempDouble));
					displayField.setText(temp);
				}
			}
		}
		
		public void equals(boolean changeState){
			var2 = tempDouble;
			if(var2 != 0.0){
				var1 = binaryOp(op, var1, var2);
			}else{
				displayField.setText("ERR");
				currentState = err;
				return;
			}
			
			var2 = 0.0;
			String displayText = String.format("%.9f",var1);
			displayField.setText(truncateZeros(displayText));
			temp = "";
			tempDouble = var1;
			if(changeState){
				currentState = rfo;
			}
			contOper = true;
		}
		
		public void backspace(){
			if(temp.length() > 0){
				temp = temp.substring(0,temp.length()-1);
			} else temp = "";
			displayField.setText(temp);
			if(temp.length() > 0){
				tempDouble = Double.parseDouble(temp);
			} else tempDouble = 0.0;
		}
		
		public void clear(boolean clearAll){
			if(clearAll){
				tempDouble = 0.0;
				temp = "";
				var1 = 0.0;
				var2 = 0.0;
				currentState = rfo;
			} else{
				tempDouble = 0.0;
				temp = "";
				var2 = 0.0;
			}
			displayField.setText(temp);
			contOper = false;
		}
	}
	
	public class ERR implements CalcState{
		public void numberInput(String num){
			
		}
		
		public void operatorInput(String oper){
			
		}
		
		public double binaryOp(String oper, double v1, double v2){
			return 0.0;
		}
		
		public void unaryOp(String oper){

		}
		
		public void equals(boolean changeState){
			
		}
		
		public void backspace(){
			
		}
		
		public void clear(boolean clearAll){
			tempDouble = 0.0;
			temp = "";
			var1 = 0.0;
			var2 = 0.0;
			displayField.setText(temp);
			currentState = rfo;
		}
	}

}
