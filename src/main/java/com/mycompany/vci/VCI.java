/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.vci;

/**
 *
 * @author omar0
 */    
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import com.mycompany.arreglopila.Pila;
import com.mycompany.arreglocola.Cola;
import java.io.*;

public class VCI {

	public static void main(String[] args) throws Exception {
                File file = new File("C:\\Users\\omar0\\OneDrive\\Documentos\\NetBeansProjects\\VCI\\src\\main\\java\\com\\mycompany\\vci\\entrada.txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                
		String options;
		String string;
                
		//string = JOptionPane.showInputDialog(null, "Enter the String");

                while ((string = br.readLine()) != null){
		vci(string);
		do {

			options = JOptionPane.showInputDialog(null, "Continuar ejecutando : \n" + "1 Continuar \n" + "0 Salir");
			if (!"0".equals(options)) {

				switch (options) {
				case "1":

					string = JOptionPane.showInputDialog(null, "Enter the String");
					vci(string);
					break;

				default:
					JOptionPane.showMessageDialog(null, "Unknown option, type another");
					break;
				}
			}
		} while (!"0".equals(options));

	}
      }

	public static void vci(String cadena) {
		StringTokenizer st = new StringTokenizer(cadena);
		Pila stack = new Pila();
		Pila fals = new Pila();
                Pila estatus = new Pila();
		Cola vci = new Cola();
                Cola posicion = new Cola();
                int i=0;

		while (st.hasMoreTokens()) {

			String token;
			token = st.nextToken();

			if (token.equals("not") || token.equals("or") || token.equals("and")) {
			} else {
				if (token.matches((".*[a-z].*")) || isNumeric(token) || token.matches((".*[A-Z].*"))) {
					vci.enqueue(token);
                                        i++;
                                        posicion.enqueue(i);

				}
			}

			if (token.equals("=")) {
				if (stack.isEmpty()) {
					stack.push(token);
					fals.push(0);
				}
			}

			if (token.equals("+") || token.equals("-")) {

				if ((int) fals.top() == 50) {

					do {

						vci.enqueue(stack.top());
                                                i++;
                                                posicion.enqueue(i);
						stack.pop();
						fals.pop();

					} while ((int) fals.top() != 0 || (int) fals.top() > 40);

				}
				if ((int) fals.top() == 0 || (int) fals.top() <= 40) {
					stack.push(token);
					fals.push(50);
				}
			}
			if (token.equals("*") || token.equals("/")) {

				if ((int) fals.top() == 60) {
					do {

						vci.enqueue(stack.top());
                                                i++;
                                                posicion.enqueue(i);
						stack.pop();
						fals.pop();
					} while ((int) fals.top() != 0 && (int) fals.top() == 60);
				}
				if ((int) fals.top() == 0 || (int) fals.top() <= 50) {
					stack.push(token);
					fals.push(60);
				}

			}

			if (token.equals(";")) {
				while (!stack.isEmpty()) {
					vci.enqueue(stack.top());
					stack.pop();
					fals.pop();
                                        i++;
                                        posicion.enqueue(i);
				}
			}

			if (token.equals("(")) {
				stack.push(token);
				fals.push(0);
			}

			if (token.equals(")")) {
				while ((int) fals.top() != 0) {
					vci.enqueue(stack.top());
					fals.pop();
					stack.pop();
                                        i++;
                                        posicion.enqueue(i);

				}
				stack.pop();
				fals.pop();
                                i++;
                                posicion.enqueue(i);

			}

			stack.Mostrar();
			fals.Mostrar();
			System.out.println("VCI " + vci.toString() + "\n" + posicion.toString());

		}
		JOptionPane.showMessageDialog(null, "VCI \n" + vci.toString() + "\n" + posicion.toString());

	}

	public static boolean isNumeric(String string) {

		boolean result;

		try {
			Integer.parseInt(string);
			result = true;
		} catch (NumberFormatException excepcion) {
			result = false;
		}

		return result;
	}
}
        

