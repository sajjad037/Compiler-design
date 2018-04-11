package com.project.compiler.Semantic;
import java.util.ArrayList;

public class SymTab {
	public String m_name = null;
	public ArrayList<SymTabEntry> m_symlist = null; 
    public static int tablelevel = 0;
	
	public SymTab(){
		m_name = null;
		m_symlist = new ArrayList<SymTabEntry>();
	}
	
	public SymTab(String p_name){
		m_name = p_name;
		m_symlist = new ArrayList<SymTabEntry>();
	}
	
	public void addEntry(SymTabEntry p_entry){
		m_symlist.add(p_entry);
	}
	
	public String toString(){
		String stringtoreturn = null;
		String toindent = "   ";
		for (int i = 0; i < SymTab.tablelevel; i++)
			stringtoreturn += toindent;
		stringtoreturn = "===== begin " + m_name + " =====\r\n";  
		for (int i = 0; i < m_symlist.size(); i++){
			for (int j = 0; j < SymTab.tablelevel; j++)
				stringtoreturn += toindent;
			stringtoreturn += m_symlist.get(i).m_entry + "\r\n"; 
			if (m_symlist.get(i).m_subtable != null){
				SymTab.tablelevel++;
//				System.out.print(SymTab.tablelevel);
				for (int k = 0; k < SymTab.tablelevel; k++)
					stringtoreturn += toindent;
				stringtoreturn += m_symlist.get(i).m_subtable.toString() + "\r\n"; 
			}
		}
		for (int i = 0; i < SymTab.tablelevel; i++)
			stringtoreturn += toindent;
		stringtoreturn += "===== end " + m_name + " =====";  
		SymTab.tablelevel--;
		return stringtoreturn;
	}
	
	public void addEntry(String string, SymTab symtab) {
		m_symlist.add(new SymTabEntry(string, symtab));
	}
}
