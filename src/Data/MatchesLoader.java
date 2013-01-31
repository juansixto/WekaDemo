package Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import Object.Journey;
import Object.Match;
import Object.Team;

public class MatchesLoader {
	
	private List<Journey> calendar;
	private List<Team> teams;
	

	public MatchesLoader(List<Team> teams){
		this.calendar = new ArrayList<>();
		this.teams = teams;
		
	}
	
	public List<Journey> Load(String file, int division){
		this.calendar = new ArrayList<>();
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(file);
		try {
			//Se crea el documento a traves del archivo
	        Document document = (Document) builder.build(xmlFile);
	 
	        //Se obtiene la raiz 'tables'
	        Element rootNode = document.getRootElement();
	 
	        //Se obtiene la lista de hijos de la raiz 'Item'
	        List list = rootNode.getChildren( "season" );
	        for ( int i = 0; i < list.size(); i++ )
	        {
	            //Se obtiene el elemento 'tabla'
	            Element item = (Element) list.get(i);
	            
	 
	            //Se obtiene el atributo 'nombre' que esta en el tag 'tabla'
	            String nameItem = item.getAttributeValue("num");
	 
	            System.out.println( "Jornada numero : " + nameItem );
	            Journey journey = new Journey(Integer.parseInt(nameItem), teams);
	 
	            //Se obtiene la lista de hijos del tag 'tabla'
	            List list_features = item.getChildren();
	            System.out.println(list_features.size());
	 
	            //Se recorre la lista de campos
	    
	            for ( int j = 0; j < list_features.size(); j++ )
	            {
	            	Element resu = (Element) list_features.get(j);
	  	            String home = (resu.getChild("home").getValue());
	  	            String visitor = (resu.getChild("visitor").getValue());
	  	            String result = (resu.getChild("result").getValue());
	  	            String[] h = result.split("-");
	  	            Match match;
	  	            if(division == 1){
	  	            match = new Match(teams.get(Integer.parseInt(home)-1), teams.get(Integer.parseInt(visitor)-1),Integer.parseInt(h[0]), Integer.parseInt(h[1]));
	  	            } else {
	  	            match = new Match(teams.get(Integer.parseInt(home)-21), teams.get(Integer.parseInt(visitor)-21),Integer.parseInt(h[0]), Integer.parseInt(h[1]));
		  	            	
	  	            }
	  	    
	  	            journey.addMatch(match);
	               }
	            journey.RefreshResults();
	            calendar.add(journey);
	            //feats.add(myList);
	        }
	    }catch ( IOException io ) {
	        System.out.println( io.getMessage() );
	    }catch ( JDOMException jdomex ) {
	        System.out.println( jdomex.getMessage() );
	    }
	
		
		return calendar;
	}
	
	public List<Journey> LoadUnlabeled(String file, int division){
		this.calendar = new ArrayList<>();
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(file);
		try {
			//Se crea el documento a traves del archivo
	        Document document = (Document) builder.build(xmlFile);
	 
	        //Se obtiene la raiz 'tables'
	        Element rootNode = document.getRootElement();
	 
	        //Se obtiene la lista de hijos de la raiz 'Item'
	        List list = rootNode.getChildren( "season" );
	        for ( int i = 0; i < list.size(); i++ )
	        {
	            //Se obtiene el elemento 'tabla'
	            Element item = (Element) list.get(i);
	            
	 
	            //Se obtiene el atributo 'nombre' que esta en el tag 'tabla'
	            String nameItem = item.getAttributeValue("num");
	 
	            System.out.println( "Jornada numero : " + nameItem );
	            Journey journey = new Journey(Integer.parseInt(nameItem), teams);
	 
	            //Se obtiene la lista de hijos del tag 'tabla'
	            List list_features = item.getChildren();
	            System.out.println(list_features.size());
	 
	            //Se recorre la lista de campos
	    
	            for ( int j = 0; j < list_features.size(); j++ )
	            {
	            	Element resu = (Element) list_features.get(j);
	  	            String home = (resu.getChild("home").getValue());
	  	            String visitor = (resu.getChild("visitor").getValue());
	  	            String result = "0-0";
	  	            String[] h = result.split("-");
	  	            Match match;
	  	            if(Integer.parseInt(home)>0 && Integer.parseInt(visitor) > 0){
		  	            if(division == 1){
		  	            match = new Match(teams.get(Integer.parseInt(home)-1), teams.get(Integer.parseInt(visitor)-1),Integer.parseInt(h[0]), Integer.parseInt(h[1]));
		  	            } else {
		  	            match = new Match(teams.get(Integer.parseInt(home)-21), teams.get(Integer.parseInt(visitor)-21),Integer.parseInt(h[0]), Integer.parseInt(h[1]));			  	            	
		  	            }
	  	            journey.addMatch(match);
	  	            }
	               }
	            journey.RefreshResults();
	            calendar.add(journey);
	            //feats.add(myList);
	        }
	    }catch ( IOException io ) {
	        System.out.println( io.getMessage() );
	    }catch ( JDOMException jdomex ) {
	        System.out.println( jdomex.getMessage() );
	    }
	
		
		return calendar;
	}
}
