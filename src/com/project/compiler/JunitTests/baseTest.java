package com.project.compiler.JunitTests;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class baseTest {

	protected boolean sameContent(Path file1, Path file2) throws IOException {
	    final long size = Files.size(file1);
	    if (size != Files.size(file2))
	        return false;

	    if (size < 4096)
	        return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));

	    try (InputStream is1 = Files.newInputStream(file1);
	         InputStream is2 = Files.newInputStream(file2)) {
	        // Compare byte-by-byte.
	        // Note that this can be sped up drastically by reading large chunks
	        // (e.g. 16 KBs) but care must be taken as InputStream.read(byte[])
	        // does not neccessarily read a whole array!
	        int data;
	        while ((data = is1.read()) != -1)
	            if (data != is2.read())
	                return false;
	    }

	    return true;
	}
}
