/**
 * Problem Statement
 * 
 * 
 * 
 * 
 * 
 @author Sri
 */
package matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matching {

	public static int BEST_COUNT = 3;
	
	// java matching.Matching "matching/data.txt" "matching/data2.txt" 4
	// java main class dataset1 dataset1 bestcount
	
	public static void main(String[] args) {
		if (args.length<=1) {
			args = new String[2];
			args[0]="matching/data.txt";
			args[1]="matching/data2.txt";			
		}else {
			BEST_COUNT = Integer.parseInt(args[2]);
		}
		
		System.out.println("MATCHING PROBLEM\n");
		for(int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		System.out.println("\n");
		
		Matching matching = new Matching();
		
		//load first data set
		ArrayList<DataSet> arrDataSet1 = matching.loadData(args[0]);
		//load second data set to match against
		ArrayList<DataSet> arrDataSet2 = matching.loadData(args[1]);
		//match both sets
		HashMap<DataSet, ArrayList<Matches>> matchedData = matching.matchData(arrDataSet1,arrDataSet2);
		//sort both sets
		matching.sortData(matchedData);
		
		System.out.println("\n##########################################\n");
		//display top best matches 
		System.out.println(matching.displayBestMatches(matchedData));
		
	}//main ends
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public ArrayList<DataSet> loadData(String fileName) {
		ArrayList<DataSet> arraySet = new ArrayList<DataSet>();
		try {
			File file = new File(fileName);
			Scanner reader = new Scanner(file);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] data = line.split(":");
				String setObjName = data[0].trim();
				String[] skillSet = data[1].split(",");
				Set<String> setObjSkillSet = new HashSet<String>();
				
				for(String str : skillSet ) {
					setObjSkillSet.add(str.trim());
				}
				
				arraySet.add(new DataSet(setObjName, setObjSkillSet));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error reading file.");
			e.printStackTrace();
		}
		
		return arraySet;
	}

	/**
	 * 
	 * @param arrDataSet1
	 * @param arrDataSet2
	 * @return
	 */
	public HashMap<DataSet, ArrayList<Matches>> matchData(ArrayList<DataSet> arrDataSet1, ArrayList<DataSet> arrDataSet2)
	{
		HashMap<DataSet, ArrayList<Matches>> hashmapMatches = new HashMap<DataSet, ArrayList<Matches>>();
		ArrayList<Matches> arrayMatches = new ArrayList<Matches>();

		DataSet tempDataSet;
		Set<String> intersectionSet;
		boolean tempBool;
		Matches matches;
		
		for (int i = 0; i < arrDataSet1.size(); i++) {
			for (int j = 0; j < arrDataSet2.size(); j++) {
				tempDataSet = arrDataSet2.get(j);
				intersectionSet = arrDataSet1.get(i).getSkills();

				tempBool = intersectionSet.retainAll(arrDataSet2.get(j).getSkills());

				if (hashmapMatches.get(tempDataSet) == null) {
					hashmapMatches.put(tempDataSet, new ArrayList<Matches>());
				}
				arrayMatches = hashmapMatches.get(tempDataSet);

				matches = new Matches(arrDataSet2.get(j), arrDataSet1.get(i), intersectionSet);
				arrayMatches.add(matches);
				hashmapMatches.put(tempDataSet, arrayMatches);
			}
		}
		return hashmapMatches;
	}
	
	/**
	 * 
	 * @param hashmapMatches
	 */
	public void sortData(HashMap<DataSet, ArrayList<Matches>> hashmapMatches) {
		Iterator<Entry<DataSet, ArrayList<Matches>>> hmIterator = hashmapMatches.entrySet().iterator();
		ArrayList<Matches> tempMatchs;
		while (hmIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) hmIterator.next();
			tempMatchs = ((ArrayList<Matches>) mapElement.getValue());
			Collections.sort(tempMatchs, new MatchComparator().reversed());

		}
		display(hashmapMatches);
	}
	
	
	
	/**
	 * 
	 * @param hashmapMatches
	 */
	public void display(HashMap<DataSet, ArrayList<Matches>> hashmapMatches) {
		Iterator<Entry<DataSet, ArrayList<Matches>>> hmIterator = hashmapMatches.entrySet().iterator();
		ArrayList<Matches> tempMatchs;
	
		while (hmIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) hmIterator.next();
			tempMatchs = ((ArrayList<Matches>) mapElement.getValue());
			System.out.println(tempMatchs);
		}
	}
	
	/**
	 * 
	 * @param matchedData
	 */
	private String displayBestMatches(HashMap<DataSet, ArrayList<Matches>> matchedData) {
		Iterator<Entry<DataSet, ArrayList<Matches>>> hmIterator = matchedData.entrySet().iterator();
		ArrayList<Matches> tempMatchs;
		DataSet tempDataSet;
		String str = "Best Matches: \n";
		//System.out.println("Best Matches: \n");
		while (hmIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) hmIterator.next();
			tempMatchs = ((ArrayList<Matches>) mapElement.getValue());
			tempDataSet = (DataSet) mapElement.getKey();
			str += "\n" + tempDataSet.getName() + " :\n";
			for(int i = 0; i < BEST_COUNT; i++) {
				tempMatchs.get(i);
				if(tempMatchs.get(i).matchSet.size() != 0) {
					str += tempMatchs.get(i).getSet2().getName() + " ---- " + tempMatchs.get(i).getMatchSet() + "\n";
				}
			}
		}
		
		return str;
	}
}//class ends