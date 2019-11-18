
public class TreeElement {
	String word;
	TreeElement left, right;
	static int alternativeCount = 0;
	public TreeElement(String s) {
		word = s;
		left = null;
		right = null;
	}
	public void ClearState() {
		alternativeCount = 0;
	}
	public void AddWord(String w) {
		TreeElement p = this;
		TreeElement cur = null;
		if(word.compareTo(w) > 0) {
			cur = p.right;
		}
		else if(word.compareTo(w)<0){
			cur = p.left;
		}
		else
			return;
		//find the parent node for the new word
		while(cur != null) {
			if(cur.word.compareTo(w) > 0) {
				p = cur;
				cur = cur.right;
			}
			else if(cur.word.compareTo(w) < 0) {
				p = cur;
				cur = cur.left;
			}
			else {
				return;
			}
		}
		cur = new TreeElement(w);
		if(p.word.compareTo(w)>0) {
			p.right = cur;
		}
		else {
			p.left = cur;
		}
	}
	public TreeElement CheckWord(String w) {
		TreeElement p = this;
		TreeElement cur = null;
		if(word.compareTo(w) > 0) {
			cur = p.right;
		}
		else if(word.compareTo(w)<0){
			cur = p.left;
		}
		else
			return this;
		//find the closest node according to the given word.
		while(cur != null) {
			if(cur.word.compareTo(w) > 0) {
				p = cur;
				cur = cur.right;
			}
			else if(cur.word.compareTo(w) < 0) {
				p = cur;
				cur = cur.left;
			}
			else {
				return cur;
			}
		}
		return p;
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
		res = res + word + " ";
		alternativeCount++;
		if(alternativeCount >= 3)
			return res;
		if(left != null)
			res = res + left.GetAlternatives(w);
		if(right != null)
			res = res + right.GetAlternatives(w);
		return res;
	}
}
