package com.ramitechs.sentimentanalysis.arabic.msa.utils;

public class Transliterator {
	
	public static String romanize(String text) {
		text = text.replaceAll("\u0621", "'"); //\u0621 : ARABIC LETTER HAMZA
		text = text.replaceAll("\u0622", "|"); //\u0622 : ARABIC LETTER ALEF WITH MADDA ABOVE
		text = text.replaceAll("\u0623", ">"); //\u0623 : ARABIC LETTER ALEF WITH HAMZA ABOVE
		text = text.replaceAll("\u0624", "&"); //\u0624 : ARABIC LETTER WAW WITH HAMZA ABOVE
		text = text.replaceAll("\u0625", "<"); //\u0625 : ARABIC LETTER ALEF WITH HAMZA BELOW
		text = text.replaceAll("\u0626", "}"); //\u0626 : ARABIC LETTER YEH WITH HAMZA ABOVE
		text = text.replaceAll("\u0627", "A"); //\u0627 : ARABIC LETTER ALEF
		text = text.replaceAll("\u0628", "b"); //\u0628 : ARABIC LETTER BEH
		text = text.replaceAll("\u0629", "p"); //\u0629 : ARABIC LETTER TEH MARBUTA
		text = text.replaceAll("\u062A", "t"); //\u062A : ARABIC LETTER TEH
		text = text.replaceAll("\u062B", "v"); //\u062B : ARABIC LETTER THEH
		text = text.replaceAll("\u062C", "j"); //\u062C : ARABIC LETTER JEEM
		text = text.replaceAll("\u062D", "H"); //\u062D : ARABIC LETTER HAH
		text = text.replaceAll("\u062E", "x"); //\u062E : ARABIC LETTER KHAH
		text = text.replaceAll("\u062F", "d"); //\u062F : ARABIC LETTER DAL
		text = text.replaceAll("\u0630", "*"); //\u0630 : ARABIC LETTER THAL
		text = text.replaceAll("\u0631", "r"); //\u0631 : ARABIC LETTER REH
		text = text.replaceAll("\u0632", "z"); //\u0632 : ARABIC LETTER ZAIN
		text = text.replaceAll("\u0633", "s"); //\u0633 : ARABIC LETTER SEEN
		text = text.replaceAll("\u0634", "\\$"); //\u0634 : ARABIC LETTER SHEEN
		text = text.replaceAll("\u0635", "S"); //\u0635 : ARABIC LETTER SAD
		text = text.replaceAll("\u0636", "D"); //\u0636 : ARABIC LETTER DAD
		text = text.replaceAll("\u0637", "T"); //\u0637 : ARABIC LETTER TAH
		text = text.replaceAll("\u0638", "Z"); //\u0638 : ARABIC LETTER ZAH
		text = text.replaceAll("\u0639", "E"); //\u0639 : ARABIC LETTER AIN
		text = text.replaceAll("\u063A", "g"); //\u063A : ARABIC LETTER GHAIN		
		text = text.replaceAll("\u0640", "_"); //\u0640 : ARABIC TATWEEL
		text = text.replaceAll("\u0641", "f"); //\u0641 : ARABIC LETTER FEH
		text = text.replaceAll("\u0642", "q"); //\u0642 : ARABIC LETTER QAF
		text = text.replaceAll("\u0643", "k"); //\u0643 : ARABIC LETTER KAF
		text = text.replaceAll("\u0644", "l"); //\u0644 : ARABIC LETTER LAM
		text = text.replaceAll("\u0645", "m"); //\u0645 : ARABIC LETTER MEEM
		text = text.replaceAll("\u0646", "n"); //\u0646 : ARABIC LETTER NOON
		text = text.replaceAll("\u0647", "h"); //\u0647 : ARABIC LETTER HEH
		text = text.replaceAll("\u0648", "w"); //\u0648 : ARABIC LETTER WAW
		text = text.replaceAll("\u0649", "Y"); //\u0649 : ARABIC LETTER ALEF MAKSURA
		text = text.replaceAll("\u064A", "y"); //\u064A : ARABIC LETTER YEH
		text = text.replaceAll("\u064B", "F"); //\u064B : ARABIC FATHATAN
		text = text.replaceAll("\u064C", "N"); //\u064C : ARABIC DAMMATAN
		text = text.replaceAll("\u064D", "K"); //\u064D : ARABIC KASRATAN
		text = text.replaceAll("\u064E", "a"); //\u064E : ARABIC FATHA
		text = text.replaceAll("\u064F", "u"); //\u064F : ARABIC DAMMA
		text = text.replaceAll("\u0650", "i"); //\u0650 : ARABIC KASRA
		text = text.replaceAll("\u0651", "~"); //\u0651 : ARABIC SHADDA
		text = text.replaceAll("\u0652", "o"); //\u0652 : ARABIC SUKUN		
		text = text.replaceAll("\u0670", "`"); //\u0670 : ARABIC LETTER SUPERSCRIPT ALEF
		text = text.replaceAll("\u0671", "{"); //\u0671 : ARABIC LETTER ALEF WASLA
		text = text.replaceAll("\u067E", "P"); //\u067E : ARABIC LETTER PEH
		text = text.replaceAll("\u0686", "J"); //\u0686 : ARABIC LETTER TCHEH
		text = text.replaceAll("\u06A4", "V"); //\u06A4 : ARABIC LETTER VEH
		text = text.replaceAll("\u06AF", "G"); //\u06AF : ARABIC LETTER GAF
		text = text.replaceAll("\u0698", "R"); //\u0698 : ARABIC LETTER JEH (no more in Buckwalter system)
		//Not in Buckwalter system \u0679 : ARABIC LETTER TTEH
		//Not in Buckwalter system \u0688 : ARABIC LETTER DDAL
		//Not in Buckwalter system \u06A9 : ARABIC LETTER KEHEH
		//Not in Buckwalter system \u0691 : ARABIC LETTER RREH
		//Not in Buckwalter system \u06BA : ARABIC LETTER NOON GHUNNA
		//Not in Buckwalter system \u06BE : ARABIC LETTER HEH DOACHASHMEE
		//Not in Buckwalter system \u06C1 : ARABIC LETTER HEH GOAL
		//Not in Buckwalter system \u06D2 : ARABIC LETTER YEH BARREE
		text = text.replaceAll("\u060C", ","); //\u060C : ARABIC COMMA
		text = text.replaceAll("\u061B", ";"); //\u061B : ARABIC SEMICOLON
		text = text.replaceAll("\u061F", "?"); //\u061F : ARABIC QUESTION MARK
		//Not significant for morphological analysis
		text = text.replaceAll("\u0640", ""); //\u0640 : ARABIC TATWEEL
		//Not suitable for morphological analysis : remove all vowels/diacritics, i.e. undo the job !
		text = text.replaceAll("[FNKaui~o]", "");
		//TODO : how to handle ARABIC LETTER SUPERSCRIPT ALEF and ARABIC LETTER ALEF WASLA ?		
		//text = text.replaceAll("[`\\{]", ""); //strip them for now
		return text;
	}
	
	public static String arabize(String text) {
		// convert to transliteration
		text = text.replaceAll("'", "\u0621"); //\u0621 : ARABIC LETTER HAMZA
		text = text.replaceAll("\\|", "\u0622"); //\u0622 : ARABIC LETTER ALEF WITH MADDA ABOVE
		text = text.replaceAll(">", "\u0623"); //\u0623 : ARABIC LETTER ALEF WITH HAMZA ABOVE
		text = text.replaceAll("&", "\u0624"); //\u0624 : ARABIC LETTER WAW WITH HAMZA ABOVE
		text = text.replaceAll("<", "\u0625"); //\u0625 : ARABIC LETTER ALEF WITH HAMZA BELOW
		text = text.replaceAll("}", "\u0626"); //\u0626 : ARABIC LETTER YEH WITH HAMZA ABOVE
		text = text.replaceAll("A", "\u0627"); //\u0627 : ARABIC LETTER ALEF
		text = text.replaceAll("b", "\u0628"); //\u0628 : ARABIC LETTER BEH
		text = text.replaceAll("p", "\u0629"); //\u0629 : ARABIC LETTER TEH MARBUTA
		text = text.replaceAll("t", "\u062A"); //\u062A : ARABIC LETTER TEH
		text = text.replaceAll("v", "\u062B"); //\u062B : ARABIC LETTER THEH
		text = text.replaceAll("j", "\u062C"); //\u062C : ARABIC LETTER JEEM
		text = text.replaceAll("H", "\u062D"); //\u062D : ARABIC LETTER HAH
		text = text.replaceAll("x", "\u062E"); //\u062E : ARABIC LETTER KHAH
		text = text.replaceAll("d", "\u062F"); //\u062F : ARABIC LETTER DAL
		text = text.replaceAll("\\*", "\u0630"); //\u0630 : ARABIC LETTER THAL
		text = text.replaceAll("r", "\u0631"); //\u0631 : ARABIC LETTER REH
		text = text.replaceAll("z", "\u0632"); //\u0632 : ARABIC LETTER ZAIN
		text = text.replaceAll("s", "\u0633" ); //\u0633 : ARABIC LETTER SEEN
		text = text.replaceAll("\\$", "\u0634"); //\u0634 : ARABIC LETTER SHEEN
		text = text.replaceAll("S", "\u0635"); //\u0635 : ARABIC LETTER SAD
		text = text.replaceAll("D", "\u0636"); //\u0636 : ARABIC LETTER DAD
		text = text.replaceAll("T", "\u0637"); //\u0637 : ARABIC LETTER TAH
		text = text.replaceAll("Z", "\u0638"); //\u0638 : ARABIC LETTER ZAH
		text = text.replaceAll("E", "\u0639"); //\u0639 : ARABIC LETTER AIN
		text = text.replaceAll("g", "\u063A"); //\u063A : ARABIC LETTER GHAIN
		text = text.replaceAll("_", "\u0640"); //\u0640 : ARABIC TATWEEL
		text = text.replaceAll("f", "\u0641"); //\u0641 : ARABIC LETTER FEH
		text = text.replaceAll("q", "\u0642"); //\u0642 : ARABIC LETTER QAF
		text = text.replaceAll("k", "\u0643"); //\u0643 : ARABIC LETTER KAF
		text = text.replaceAll("l", "\u0644"); //\u0644 : ARABIC LETTER LAM
		text = text.replaceAll("m", "\u0645"); //\u0645 : ARABIC LETTER MEEM
		text = text.replaceAll("n", "\u0646"); //\u0646 : ARABIC LETTER NOON
		text = text.replaceAll("h", "\u0647"); //\u0647 : ARABIC LETTER HEH
		text = text.replaceAll("w", "\u0648"); //\u0648 : ARABIC LETTER WAW
		text = text.replaceAll("Y", "\u0649"); //\u0649 : ARABIC LETTER ALEF MAKSURA
		text = text.replaceAll("y", "\u064A"); //\u064A : ARABIC LETTER YEH
		text = text.replaceAll("F", "\u064B"); //\u064B : ARABIC FATHATAN
		text = text.replaceAll("N", "\u064C"); //\u064C : ARABIC DAMMATAN
		text = text.replaceAll("K", "\u064D"); //\u064D : ARABIC KASRATAN
		text = text.replaceAll("a", "\u064E"); //\u064E : ARABIC FATHA
		text = text.replaceAll("u", "\u064F"); //\u064F : ARABIC DAMMA
		text = text.replaceAll("i", "\u0650"); //\u0650 : ARABIC KASRA
		text = text.replaceAll("~", "\u0651"); //\u0651 : ARABIC SHADDA
		text = text.replaceAll("o", "\u0652"); //\u0652 : ARABIC SUKUN
		text = text.replaceAll("`", "\u0670"); //\u0670 : ARABIC LETTER SUPERSCRIPT ALEF
		text = text.replaceAll("\\{", "\u0671"); //\u0671 : ARABIC LETTER ALEF WASLA
		text = text.replaceAll("P", "\u067E"); //\u067E : ARABIC LETTER PEH
		text = text.replaceAll("J", "\u0686"); //\u0686 : ARABIC LETTER TCHEH
		text = text.replaceAll("V", "\u06A4"); //\u06A4 : ARABIC LETTER VEH
		text = text.replaceAll("G", "\u06AF"); //\u06AF : ARABIC LETTER GAF
		text = text.replaceAll("R", "\u0698"); //\u0698 : ARABIC LETTER JEH (no more in Buckwalter system)
		//Not in Buckwalter system \u0679 : ARABIC LETTER TTEH
		//Not in Buckwalter system \u0688 : ARABIC LETTER DDAL
		//Not in Buckwalter system \u06A9 : ARABIC LETTER KEHEH
		//Not in Buckwalter system \u0691 : ARABIC LETTER RREH
		//Not in Buckwalter system \u06BA : ARABIC LETTER NOON GHUNNA
		//Not in Buckwalter system \u06BE : ARABIC LETTER HEH DOACHASHMEE
		//Not in Buckwalter system \u06C1 : ARABIC LETTER HEH GOAL
		//Not in Buckwalter system \u06D2 : ARABIC LETTER YEH BARREE
		text = text.replaceAll(",", "\u060C" ); //\u060C : ARABIC COMMA
		text = text.replaceAll(";", "\u061B"); //\u061B : ARABIC SEMICOLON
		text = text.replaceAll("\\?", "\u061F"); //\u061F : ARABIC QUESTION MARK
		return text;
	}

}
