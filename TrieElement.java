import java.util.*;

public class TrieElement {
	String word; // string saved in this element
	boolean isValidWord;
	Map<String,TrieElement> children;
	static int alternativeCount = 0;
	public TrieElement(String s) {
		word = s;
		children = new HashMap<String, TrieElement>();
	}
	public void ClearState() {
		alternativeCount = 0;
	}
	public void AddWord(String w) {
		if(word.compareTo(w) == 0) {
			//current word is a valid word
			isValidWord = true;
			return;
		}
		else {
			//try to find the children
			String sub = w.substring(0, word.length()+1);
			//there is a key
			if(!children.containsKey(sub)) {
				//generate a new element
				TrieElement e = new TrieElement(sub);
				children.put(sub, e);
			}
			children.get(sub).AddWord(w);
			return;
		}
	}
	public TrieElement CheckWord(String w) {
		if(word.compareTo(w) == 0) {
			//current word
			return this;
		}
		else {
			//try to find the children with a sub string
			String sub = w.substring(0, word.length()+1);
			//there is a key
			if(!children.containsKey(sub)) { //no sub 
				return this;
			}
			else {
				//try to find the word in the sub tries
				return children.get(sub).CheckWord(w);
			}
		}
	}
	/**
	 * get the alternatives according to the word
	 * @param w
	 * @return
	 */
	public String GetAlternatives(String w) {
		String res = "";
		//only return at most 3 alternatives
		if(alternativeCount >= 3)
			return res;
		//check current node, take it as one alternative if it is a valid word
		if(isValidWord) {
			res = res + word + " ";
			alternativeCount++;
			if(alternativeCount >= 3)
				return res;
		}
		//try to find more alternatives in the children nodes
		for (TrieElement value : children.values()) {
			res = res + value.GetAlternatives(w);
			if(alternativeCount >= 3)
				break;
		}
		return res;
	}
}
