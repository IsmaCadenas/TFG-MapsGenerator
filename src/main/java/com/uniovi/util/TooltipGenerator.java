package com.uniovi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
public class TooltipGenerator {
	
	public static void main(String ...args) {
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      int areanum=2378;
	      int mapid=42;
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File ("C:\\Users\\isma1\\Downloads\\datos.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         List<String> lineas = new ArrayList<String>();
	         while((linea=br.readLine())!=null) {
	        	 lineas.add(linea);
	        	 
	         }
        	 for(String s : lineas) {
        		 String[] tooltips;
        		 tooltips = s.split(":");
        		 String insert = " INSERT INTO `database-1`.`master_area` (`id`, `code`, `tooltip`) VALUES ('" + areanum + "', '"+tooltips[0].replace("\"", "").replace(" ", "".replace("\t", ""))+"', '"+tooltips[1].replace("\"", "").replace(",", "")+"');\n"
        		 		+ "INSERT INTO `database-1`.`mmap_mareas` (`mmap_id`, `marea_id`) VALUES ('" + mapid + "', '" + areanum + "');";
        		 System.out.println(insert);
        		 areanum++;
        	 }
//        	 for(int i= areanum;i<1750;i++) {
//        		 String inserta="INSERT INTO `spring_aplicacion`.`mmap_mareas` (`mmap_id`, `marea_id`) VALUES ('" + mapid + "', '" + i + "');";
//        		 System.out.println(inserta);
//        	 }
        		 
	          
	      }
	      catch(Exception e){
//	         e.printStackTrace();
	      }finally{
//	         try{                    
//	            if( null != fr ){   
//	               fr.close();     
//	            }                  
//	         }catch (Exception e2){ 
//	            e2.printStackTrace();
//	         }
	      }
	}
}
