package com.crihexe;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
	
	private static long lines = 0;

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Usage: <folderPath>");
			return;
		}
		
		String sDir = args[0];
		try {
			Files.find(Paths.get(sDir), 999, (p, bfa) -> bfa.isRegularFile()).forEach(Main::addLines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(lines);
	}
	
	public static void addLines(Path path) {
		long lineCount = 0;
		try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
		  lineCount = stream.count();
		  System.out.println(path + ": " + lineCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
		lines += lineCount;
	}

}
