/**
 * @author Sri Nandury
 */

package matching;
import java.util.HashSet;
import java.util.Set;

public class DataSet {
	
	private String name;
	private Set<String> attributes;

	public DataSet(String name, Set<String> set) {
		this.name = name;
		this.attributes = set;
	}

	public String getName() {
		return name;
	}
	
	public Set<String> getSkillSet(){
		return attributes;
	}
	
	public String toString() {
		return "\nName : " + name + "   Set: " + attributes;
	}
	
	public Set getSkills() {	 
		Set<String> copySet = new HashSet<>(getSkillSet());
		return copySet;
	}
}
