package Parser;

import java.awt.List;
import java.util.ArrayList;

public class Tree<String> {
	    public nod<String> root;

	    public Tree(String rootData) {
	        root = new nod<String>();
	        root.data = rootData;
	        root.children = new ArrayList<nod<String>>();
	    }

	    public static class nod<String> {
	        public String data;
	        public nod<String> parent;
	        public ArrayList<nod<String>> children;
	        
	    }
	}
