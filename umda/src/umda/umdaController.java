/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umda;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
// graph imports
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
// custom imports
import java.util.ArrayList;


/**
 *
 * @author fernando-dlz
 */
public class umdaController implements Initializable {
    
    // GUI controls defined in FXML and used by the controller's code
    @FXML
    private TextField individualsTextField;
    @FXML
    private TextField indvSelectionTextField;
    @FXML
    private TextField iterationsTextField;
    @FXML
    private TextField dimentionsTextField;
    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    private LineChart<Number, Number> lineChart2;
    
    // calculates and displays the tip and total amounts
    @FXML
    private void initializeButtonPressed(ActionEvent event) {
        int N = -1, a = -1, n = -1, iterations = -1;
        
        try {
            N = Integer.parseInt(individualsTextField.getText());
            if (N < 0) 
                throw new ArithmeticException();  
        }
        catch (NumberFormatException ex) {            
            individualsTextField.setText("Ingresa un numero");
        }
        catch (ArithmeticException ex) {
             individualsTextField.setText("Solo numeros positivos");
        }

        try {
            a = Integer.parseInt(indvSelectionTextField.getText());
            if (a < 0) 
                throw new ArithmeticException();  
        }
        catch (NumberFormatException ex) {
            indvSelectionTextField.setText("Ingresa un numero");
        }
        catch (ArithmeticException ex) {
             indvSelectionTextField.setText("Solo numeros positivos");
        }
        
        try {
            n = Integer.parseInt(dimentionsTextField.getText());
            if (n < 0) 
                throw new ArithmeticException();  
        }
        catch (NumberFormatException ex) {
            dimentionsTextField.setText("Ingresa un numero");
        }
        catch (ArithmeticException ex) {
             dimentionsTextField.setText("Solo numeros positivos");
        }

        try {
            iterations = Integer.parseInt(iterationsTextField.getText());
            if (iterations < 0) 
                throw new ArithmeticException();  
        }
        catch (NumberFormatException ex) {
            iterationsTextField.setText("Ingresa un numero");
        }
        catch (ArithmeticException ex) {
             iterationsTextField.setText("Solo numeros positivos");
        }

        if (N > 0 && a > 0 && iterations > 0 && n > 0) {
                objectiveFunction sphere = new Sphere();
                umdaAlgorithm umda = new umdaAlgorithm(N, a, n, iterations, sphere);
                ArrayList<i> history = umda.execute();
                for (i ind : history)
                    System.out.printf(" %.4f -", ind.getFitness());


                // ******** graph 1 generation ********
                lineChart.getData().clear();
        
                // defining a series
                ArrayList<XYChart.Series> series = new ArrayList<>();
                
                // populating the series with data
                for (int j = 0; j < n; j++) {
                    XYChart.Series serie = new XYChart.Series();
                    for (int i = 0; i < history.size(); i ++)
                        serie.getData().add(new XYChart.Data(i, history.get(i).values[j]));
                    series.add(serie);
                }
                
                for (XYChart.Series serie : series)
                    lineChart.getData().add(serie);
                
                // ******** graph 2 generation ********
                lineChart2.getData().clear();
        
                // populating the series with data
                XYChart.Series serie = new XYChart.Series();
                for (int i = 0; i < history.size(); i++)
                    serie.getData().add(new XYChart.Data(i, history.get(i).getFitness()));

                lineChart2.getData().add(serie);
                
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
}
