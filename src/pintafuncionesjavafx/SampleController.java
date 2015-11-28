package pintafuncionesjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * @author Richard
 */
public class SampleController implements Initializable {
    
    @FXML private TextField rangoMin;
    @FXML private TextField rangoMax;
    @FXML private ChoiceBox choiceFun;
    @FXML private Button button;
    
    // Declaramos el "LineChart" donde pintaremos la funcion
    @FXML private LineChart<Double, Double> graph;
    @FXML private NumberAxis x;
    @FXML private NumberAxis y;
    
    
    /**
     * Método que se ejecuta al pulsar el boton "Dibujar Funcion"
     * @param event 
     */
    @FXML private void AccionPintar(ActionEvent event) {
        int grado = getGradoFuncion(choiceFun.getValue().toString());
        pintarGrafica(Integer.parseInt(rangoMin.getText()), Integer.parseInt(rangoMax.getText()), grado);  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializamos el rango sobre el que pintaremos la funcion.
        rangoMin.setText("-10");
        rangoMax.setText("10");
        choiceFun.setValue("x");
    }
    
    /**
     * Método que devuelve el grado de la función a dibujar
     * @param funcion
     * @return grado de la funcion
     */
    private static int getGradoFuncion(String funcion){
        if (funcion.equals("x"))
            return 1;
        else if(funcion.equals("x^2"))
            return 2;
        else if (funcion.equals("x^3"))
            return 3;
        else if (funcion.equals("x^4"))
            return 4;
        else
            return 5;
    }
    
      
    /**
     * Método para pintar la gráfica
     * @param min
     * @param max
     * @param grado 
     */
    private void pintarGrafica (int min, int max, int grado){
        
        // Creamos un ObservableList para guardar los puntos que pintaremos en la gráfica
        ObservableList<XYChart.Series<Double, Double>> lineChartData = FXCollections.observableArrayList();
        
        // Instanciamos un punto a pintar
        LineChart.Series<Double, Double> series = new LineChart.Series<Double, Double>();
        
        // Imprimimos la función que vamos a pintar como etiqueta
        series.setName("f(x^"+grado+")");
        
        // obtenemos los puntos a pintar. Daros cuenta que los puntos a pintar estan definidos
        // por el valor de 'x' y el resultado de 'f(x)', siendo f(x)=Math.pow(x, grado) = x^grado
        for (double i = min; i<max; i=i+0.1){
            series.getData().add(new XYChart.Data<Double, Double>(i, Math.pow(i, grado)));
        }
        
        // Guardamos todos los puntos de la función que hemos obtenido
        lineChartData.add(series);

        // Si No quereis que se pinten los puntos, poner a false
        graph.setCreateSymbols(true);
        
        
        // Ponemos los puntos en la gráfica
        graph.setData(lineChartData);
        graph.createSymbolsProperty();
    }
}