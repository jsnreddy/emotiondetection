package emotiondetection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.core.tokenizers.NGramTokenizer;

/* Emotion Detection
 * Author : J Surendranath Reddy
 * Date : 18th January, 2016
 * */

public class EmotionDetector {

	public static void tokenizeSentences(File sentences) {
		NGramTokenizer tokenizer = new NGramTokenizer();
		//		String delimiters = " ";
		System.out.println(tokenizer.getNGramMaxSize());
		System.out.println(tokenizer.getNGramMinSize());
		//		String[] tokenizerOptions = null;
		//		tokenizer.setDelimiters(delimiters);
		//		Tokenizer.tokenize(tokenizer, [delimiters, "3", "1"]);
	}

	public static void preProcess(File raw_data, File sentences, File input_data) {
		//		input/Emotion_Data_Train_and_Test.txt

		try {
			BufferedReader br = new BufferedReader(new FileReader(raw_data));
			BufferedWriter bw_sentence = new BufferedWriter(new FileWriter(sentences));
			BufferedWriter bw = new BufferedWriter(new FileWriter(input_data));

			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] input = line.split("\t");
				String sentence = input[1].toLowerCase();
				List<Integer> emoScores = new ArrayList<Integer>();
				int largestEmotionScore = 0;
				int largestEmotionIndex = 2;
				for (int i = 2; i < 8; ++i) {
					int presentElement = Integer.parseInt(input[i]);
					emoScores.add(presentElement);
					if (presentElement >= largestEmotionScore) {
						largestEmotionScore = presentElement;
						largestEmotionIndex = i;
					}
				}
				largestEmotionIndex -= 1;
				//				Collections.sort(emoScores);
				//				int maxEmotionScore = emoScores.get(emoScores.size() - 1);
				bw_sentence.write(sentence + "\n");
				bw.write(sentence + "," + largestEmotionIndex + "\n");

			}
			br.close();
			bw_sentence.close();
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tokenizeSentences(sentences);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File raw_data = new File("input/Emotion_Data_Train_and_Test.txt");
		File sentences = new File("input/sentences.txt");
		File input_data = new File("input/input_data.txt");

		preProcess(raw_data, sentences, input_data);

	}

}
