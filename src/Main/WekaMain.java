package Main;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesSimple;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SimpleLogistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import Object.Journey;
import Object.Seasson;

/**
 * This example trains NaiveBayes incrementally on data obtained
 * from the ArffLoader.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class WekaMain {
	static Seasson season = new Seasson();

  /**
   * Expects an ARFF file as first argument (class attribute is assumed
   * to be the last attribute).
   *
   * @param args        the commandline arguments
   * @throws Exception  if something goes wrong
   */
  public static void main(String[] args) throws Exception {
	  	season.LoadTeams();
		season.LoadNext();
		season.LoadMatches();

		season.generateWeka();
		season.generateUnlabeledWeka();
	  
	  // load data
	String s = "/home/juan/workspace/WekaDemo/data/Results2.arff";
	DataSource source = new DataSource(s);
	Instances data = source.getDataSet();
	 if (data.classIndex() == -1)
		   data.setClassIndex(data.numAttributes() - 1);
   

    // train NaiveBayes
    Logistic nb = new Logistic();
    nb.buildClassifier(data);
    Instance current;


    // output generated model
    System.out.println(nb);
    
    Instances unlabeled = new Instances(new BufferedReader(new FileReader("/home/juan/workspace/WekaDemo/data/ResultsUnlabeled.arff")));
 // set class attribute
    unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
    
    // create copy
    Instances labeled = new Instances(unlabeled);
    
    Journey next = season.getNext();
    
    // label instances
    int ls = 0;
    for (int i = 0; i < unlabeled.numInstances(); i+=2) {
      double clsLabel = nb.classifyInstance(unlabeled.instance(i));
      labeled.instance(i).setClassValue(clsLabel);
      System.out.println(next.getMatches().get(ls).getHomeTeam().getName() + " - " + next.getMatches().get(ls).getVisitTeam().getName());
      System.out.println(clsLabel + " -> " + unlabeled.classAttribute().value((int) clsLabel));
      ls++;
    }

    
  }
}
