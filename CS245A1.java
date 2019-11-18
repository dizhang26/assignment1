import java.io.*;
import java.util.Properties;


public class CS245A1 {
	public static void main(String[] args) throws IOException {
		int storageSetting = 0; // 0 for trie 1 for tree
		//firstly, try to load the properties
		Properties prop = new Properties();
		try {
			InputStream fin = new BufferedInputStream (new FileInputStream("a1properties.txt"));  
		 	prop.load(fin);
		 	String value = prop.getProperty("storage");
		 	if(value.compareTo("trie") == 0) {
		 		storageSetting = 0;
		 		System.out.println("Storage setting: trie");
		 	}
		 	else if(value.compareTo("tree") == 0) {
		 		storageSetting = 1;
		 		System.out.println("Storage setting: tree");
		 	}
		}catch (Exception e) {
			System.out.println("Use default storage setting: trie");
		}
		//create a trie element
		TrieElement trieHead = new TrieElement("");
		//create a tree element
		TreeElement treeHead = new TreeElement("");
		
		//try to load the English dictionary
		FileReader fr= new FileReader("english.0");
        BufferedReader br=new BufferedReader(fr);
        String line="";
        while ((line=br.readLine())!=null) {
            if(line.length()>0) {
            	if(storageSetting == 1)
            		treeHead.AddWord(line);
            	else
            		trieHead.AddWord(line);
            }
        }
        br.close();
        fr.close();
        
        //load the input file by line and process each line
        fr = new FileReader(args[0]);
        br = new BufferedReader(fr);
        //open the output file
        FileWriter fw=new FileWriter(new File(args[1]));
        BufferedWriter bw=new BufferedWriter(fw);
        
        TrieElement e;
        TreeElement e1;
        //read by line
        while ((line=br.readLine())!=null) {
            if(line.length()>0) {
            	if(storageSetting == 1) {
            		e1 = treeHead.CheckWord(line);
            		if(e1.word.compareTo(line) == 0) {
            			//this is a valid word and directly print it
                		bw.write(line + "\n");
            		}
            		else {
            			e1.ClearState();
                		bw.write(e1.GetAlternatives(line) + "\n");
            		}
            	}
            	else {
            		e = trieHead.CheckWord(line);
            		if(e.isValidWord) {
                		//this is a valid word and directly print it
                		bw.write(line + "\n");
                	}
                	else {
                		// find the suggested alternatives
                		e.ClearState();
                		bw.write(e.GetAlternatives(line) + "\n");
                	}
            	}
            	
            }
        }
        br.close();
        fr.close();
        bw.close();
        fw.close();
	}

}
