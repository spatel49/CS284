package hw4;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class HuffmanTreeTest {

	@Test
	public void test() {
		String s = "Some string you want to encode";
		String l = "xxeeeeaaaaaaaaa";
		Boolean[] test = {true, false,false,false, true, true, true};
		String encodethis = "xaeeaxe";
		HuffmanTree t = new HuffmanTree(l); // Creates specific Huffman Tree for "s"
		// Now you can use encode, decode, and toString to interact with your specific Huffman Tree
		//System.out.println(t.toString());

        //String Representation of Tree
        assertEquals(t.toString(), "(freq= 15)\r\n" + 
        		"	(freq= 6)\r\n" + 
        		"		[value= x,freq= 2]\r\n" + 
        		"		[value= e,freq= 4]\r\n" + 
        		"	[value= a,freq= 9]");

        //Encoding/Decoding
        Boolean[] test1 = {true, false,false,false, true, true, true};
        assertEquals(t.decode(test1), "axea");
        assertEquals(Arrays.toString(t.encode("xaeeaxe")), "[false, false, true, false, true, false, true, true, false, false, false, true");
        assertEquals(Arrays.toString(t.efficientEncode("xaeeaxe")), "[false, false, false, true, false, true, false, true, false, true, false, false, false, true]"
);
	}}
