/**
 * @author Sri Nandury
 */

package matching;
import java.util.Set;

public class Matches{
	
	
	DataSet setObj1;
	DataSet setObj2;
	Set<String> matchSet;
	
	public Matches(DataSet setObj1, DataSet setObj2, Set<String> matches) {
		this.setObj1 = setObj1;
		this.setObj2 = setObj2;
		matchSet=matches;
		// TODO Auto-generated constructor stub
	}

	public DataSet getSet1() {
		return setObj1;
	}
	
	public void setSet1(DataSet setObj1) {
		this.setObj1 = setObj1;
	}

	public DataSet getSet2() {
		return setObj2;
	}

	public void setSet2(DataSet setObj2) {
		this.setObj2 = setObj2;
	}

	public Set<String> getMatchSet() {
		return matchSet;
	}
	
	public int getMatchCount() {
		return matchSet !=null? matchSet.size():0;
	}
	
	public void setMatchSet(Set<String> matchSet) {
		this.matchSet = matchSet;
	}
	
	public String toString() {
		return "Matching : " + setObj1.toString() + setObj2.toString() + "\n"+ matchSet.toString() + " --- " + matchSet.size() + "\n";
	}
	
}

class MatchComparator implements java.util.Comparator<Matches> {
    @Override
    
    public int compare(Matches a, Matches b) {
        return a.getMatchCount() - b.getMatchCount();
    }
}