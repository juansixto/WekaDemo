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

import Object.Team;

public class TeamLoader {
	
	private List<Team> teams;
	
	public TeamLoader(){
	this.teams = new ArrayList<>();	
	}
	
	public List<Team> Load(String file){
		this.teams = new ArrayList<>();
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(file);
		try {
			//Se crea el documento a traves del archivo
	        Document document = (Document) builder.build(xmlFile);
	 
	        //Se obtiene la raiz 'tables'
	        Element rootNode = document.getRootElement();
	 
	        //Se obtiene la lista de hijos de la raiz 'Item'
	        List list = rootNode.getChildren( "team" );
	        for ( int i = 0; i < list.size(); i++ )
	        {
	            //Se obtiene el elemento 'tabla'
	            Element item = (Element) list.get(i);
	            String n = (item.getChild("name").getValue());
	            teams.add(new Team(i+1,n));
	        }
	    }catch ( IOException io ) {
	        System.out.println( io.getMessage() );
	    }catch ( JDOMException jdomex ) {
	        System.out.println( jdomex.getMessage() );
	    }	
		
		
		return teams;
	}
	

}
