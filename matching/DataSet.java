/**
 * @author Sri
 */

package matching;
import java.util.HashSet;
import java.util.Set;

public class DataSet {
	
	/**
	 * 
	 */
	private String name;
	private Set<String> attributes;

	/**
	 * 
	 * @param name
	 * @param set
	 */
	public DataSet(String name, Set<String> set) {
		this.name = name;
		this.attributes = set;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<String> getSkillSet(){
		return attributes;
	}
	
	/**
	 * 
	 */
	public String toString() {
		return "\nName : " + name + "   Set: " + attributes;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set getSkills() {	 
		Set<String> copySet = new HashSet<>(getSkillSet());
		return copySet;
	}
}
