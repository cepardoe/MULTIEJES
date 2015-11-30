/*Esta es la clase principal desde la cual se debe ejecutar el programa. Se requieren instalar las librerias 
 * core.jar, controlP5.jar
 */

package controller;//paquete principal del programa principal. Se debe ejecutar siempre Aplicacion
import processing.core.*;//importacion de librerias de processing
import java.io.PrintWriter;

import controlP5.*;
//importacion de modelos de figuras. Si se crea una figura nueva es neceario importarla del paquete model.
import model.Arc;
import model.Drawing;
import model.Ellipse;
import model.Line;
import model.Rectangle;
//PrintWriter output se necesita esa funcion de processing pero no supe importarla
public class Aplicacion extends PApplet{
	
	//iniciacion coordenadas
	private int x1=0;
	private int y1=0;
	private int x2=x1;
	private int y2=y1;
	private int x_2=x1;
	private int y_2=y1;
	
	// atributos para la matriz de figuras
	private final int column=5;
	private int row;
	private int[][] matriz;
	private int tool=0;

	//iniciacion valores de texto
	private Textfield X1;
	private Textfield Y1;
	private Textfield X2;
	private Textfield Y2;
	private Textlabel mouseXYtext;
	private String TF1="X1";
	private String TF2="Y1";
	private String TF3="X2";
	private String TF4="Y2";

// 
	private ControlP5 cp5;
	private PFont font;
// declaracion de tipo Drawing	
	private Drawing drawing;
	
// Metodo para declarar tamano de ventana en eclipse
	  public void settings(){
		  size(700, 700);
	  }
	  
// Metodo setup de Processing que se ejecuta una sola vez
	public void setup() {

	  cp5 = new ControlP5(this);
	  drawing = new Drawing();
	  font = createFont("arial",8);
		
	  
	  ButtonBar b = cp5.addButtonBar("bar")
	     .setPosition(0, 0)
	     .setSize(400, 20)
	     .addItems(split("a b c d"," "))
	     ;
	     println(b.getItem("a"));
	  b.changeItem("a","text","rectangulo");
	  b.changeItem("b","text","circulo");
	  b.changeItem("c","text","linea");
	  b.changeItem("d","text","arco");
	    b.onChange(new CallbackListener(){
	    public void controlEvent(CallbackEvent ev) {
	      ButtonBar bar = (ButtonBar)ev.getController();
	      println(bar.hover());
	      tool=bar.hover();
	    }
	  });
  
	    		
	   
	    		
	    		
	    		
	  X1=cp5.addTextfield(TF1)
	  .setColorCaptionLabel(0)
	     .setPosition(20,650)
	     .setSize(60,20)
	     .setFont(createFont("arial",20))
	     .setAutoClear(false)
	     .setValue(x1);
	     
	  Y1=cp5.addTextfield(TF2)
	  .setColorCaptionLabel(0)
	     .setPosition(120,650)
	     .setSize(60,20)
	     .setFont(createFont("arial",20))
	     .setAutoClear(false)
	     .setValue(y1)
	     ;
	     
	  X2=cp5.addTextfield(TF3)
	  .setColorCaptionLabel(0)
	     .setPosition(220,650)
	     .setSize(60,20)
	     .setFont(createFont("arial",20))
	     .setAutoClear(false)
	     .setValue(x2)
	     ;
	     
	   Y2=cp5.addTextfield(TF4)
	     .setColorCaptionLabel(0)
	     .setPosition(320,650)
	     .setSize(60,20)
	     .setFont(createFont("arial",20))
	     .setAutoClear(false)
	     .setValue(y2)
	     ;
	   
/*	   int Xpos=mouseX;
	   int Ypos=mouseY;
	   mouseXYtext = new Textlabel(cp5,Xpos.toString()+Ypos.toString(),100,100,400,200);
*/
	}

	//Metodo draw de Processing que se ejecuta ciclicamente
	public void draw() {
		
      X1.setValue(x1);
	  Y1.setValue(y1);
	  X2.setValue(x2);
	  Y2.setValue(y2);
	  
	  
	  background(255); 
    for (int i = 0; i <= width; i +=18) { 
      fill(0, 0, 0);
      textSize(9);
      text((i/(height/40))*35/2, i+2, height-1);
      stroke(200); line(i, height, i, 0); 
      } 
    for (int j = 0; j < height; j += 18) { 
      fill(0, 0, 0); 
      text((40-j/(width/40))*35/2, 2, j+15);
      stroke(200); 
      line(0, j, width, j); 
      } 
    stroke(0);

	if (tool==0){
	recttool(x1,y1,x2,y2);
	recttool2(x1,y1,x_2,y_2);
	
	}

	if (tool==1){
	  ellitool(x1, y1,x2, y2);
	  ellitool2(x1, y1,x_2, y_2);
	}
	if (tool==2){
	  linetool(x1, y1,x2, y2);
	  linetool2(x1, y1,x_2, y_2);
	}
	if (tool==3){
	  arctool(x1, y1,x2, y2);
	  arctool2(x1, y1,x_2, y_2);
	}
	drawing.doPaint(g);
	}

	/*herramientas para crear las figuras. Figuratool crea la figura a partir del punto de Pressed y punto de
	Released. Figuratool2 crea la figura y muestra el proceso mientras mouseDragged. Si se crea una figura 
	adicional es necesario declarar Figuratool y Figuratool2 para que se visualice.
	*/
	void recttool(int a, int b, int c, int d){
	 Rectangle rectangle=new Rectangle();
		rectangle.update(a,b,c,d);
		rectangle.doPaint(g);
		drawing.addFigure(rectangle);
	}
	void recttool2(int a, int b, int c, int d){
		 Rectangle rectangle=new Rectangle();
			rectangle.update(a,b,c,d);
			rectangle.doPaint(g);
		}
	void ellitool(int a, int b, int c, int d){
		 Ellipse ellipse=new Ellipse();
		 ellipse.update(a,b,c,d);
		 ellipse.doPaint(g);
		 drawing.addFigure(ellipse);
	}
	void ellitool2(int a, int b, int c, int d){
		 Ellipse ellipse=new Ellipse();
		 ellipse.update(a,b,c,d);
		 ellipse.doPaint(g);
	}
	void linetool(int a, int b, int c, int d){
		 Line line=new Line();
		 line.update(a,b,c,d);
		 line.doPaint(g);
		 drawing.addFigure(line);
	}
	void linetool2(int a, int b, int c, int d){
		 Line line=new Line();
		 line.update(a,b,c,d);
		 line.doPaint(g);
	}
	void arctool(int a, int b, int c, int d){
		 Arc arc=new Arc();
		 arc.update(a,b,c,d);
		 arc.doPaint(g);
		 drawing.addFigure(arc);
	}
	void arctool2(int a, int b, int c, int d){
		 Arc arc=new Arc();
		 arc.update(a,b,c,d);
		 arc.doPaint(g);
	}
	void norm(float x){
	 if (x>=0){

	 } else{
	 }
	}
	
/*Declaracion de los metodos para obtener los eventos del mouse
 * (non-Javadoc)
 * @see processing.core.PApplet#mousePressed()
 */
	public void mousePressed() {
	  if (tool==0){
	    x1=mouseX;
	    y1=mouseY;
	    x2=0;
	    y2=0;
	    x_2=0;
	    y_2=0;
	    		
	  }
	    if (tool==1){
	    x1=mouseX;
	    y1=mouseY;
	    x2=0;
	    y2=0;
	    x_2=0;
	    y_2=0;
	  }
	  
	    if (tool==2){
	    x1=mouseX;
	    y1=mouseY;
	    x2=x1;
	    y2=y1;
	    x_2=x1;
	    y_2=y1;
	  }
	  
	    if (tool==3){
	    x1=mouseX;
	    y1=mouseY;
	    x2=0;
	    y2=0;
	    x_2=0;
	    y_2=0;
	  }
	  
	  
	}
	public void mouseDragged() 
	{
	    if (tool==0){
	    x_2=mouseX-x1;
	    y_2=mouseY-y1;
	  }
	    if (tool==1){
	    x_2=2*(mouseX-x1);
	    y_2=2*(mouseY-y1);
	  }
	  
	    if (tool==2){
	    x_2=mouseX;
	    y_2=mouseY;
	  }
	  
	  
	    if (tool==3){
	    x_2=2*(mouseX-x1);
	    y_2=2*(mouseY-y1);
	  }
	  
	}
	public void mouseReleased() {
	    if (tool==0){
	    x2=mouseX-x1;
	    y2=mouseY-y1;
	  }
	  
	    if (tool==1){
	    x2=2*(mouseX-x1);
	    y2=2*(mouseY-y1);
	  }
	  
	      if (tool==2){
	    x2=mouseX;
	    y2=mouseY;
	  }
	  
	    if (tool==3){
	    x2=2*(mouseX-x1);
	    y2=2*(mouseY-y1);
	  }

	}	 	
	
	/* Declaracion del metodo para ejecutar PApplet desde App de eclipse. La aplicacion principal se llama 
	 * Aplicacion y se encuentra dentro del paquete Controller
	 */
	public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "controller.Aplicacion" });
	  }
}
