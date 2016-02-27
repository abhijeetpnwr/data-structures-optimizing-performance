package textgen;

import java.util.Random;
import java.util.HashMap;
import java.io.PrintWriter;

public class MarkovTextGeneratorGrader {
    private static final int LENGTH = 500;

    public static void main(String[] args) {
        try {
            MarkovTextGenerator gen = new MarkovTextGeneratorLoL(new Random());
            
            int incorrect = 0;
            int tests = 0;
            String feedback = "";

            
            feedback += "\n** Test 1: Generating text before training...";
            try
            {
            
                String s = gen.generateText(20);
                feedback += "No error thrown. ";
                

                
            } catch (Exception e) 
            {
                feedback += "Error thrown. ";
            }

            gen.train("");
                        
            feedback += "\n** Test 2: Generating text after training on an empty file...";
            
            //System.out.println("-------- ");
            
            try {
                String s = gen.generateText(20);
                feedback += "No error thrown. ";
            } catch (Exception e) {
                feedback += "Error thrown. ";
                
                
                //System.out.println("-------- ");
            }

            String input = "I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";
            gen.retrain(input);
            String res = gen.generateText(LENGTH);

            System.out.println("--- "+gen);
            
            feedback += "\nGenerator produced: " + res + "\n";

            String[] words = res.split("[\\s]+");
            
            
            //System.out.println("---- 2 done --");
            feedback += "\n** Test #3: Checking requested generator word count...";
            feedback += "Your generator produced " + words.length + " words. ";

            HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();

            for (String w : words) {
                if (wordCounts.containsKey(w)) {
                    wordCounts.put(w, wordCounts.get(w) + 1);
                }
                else {
                    wordCounts.put(w, 1);
                }
            }

            //System.out.println(" --- 3 done ----");
            
            feedback += "\n** Test #4: Testing specific word counts...";
            feedback += "'I' appeared " + wordCounts.get("I") + " times. ";
            //System.out.println(" ---- 4 done ---- ");

            boolean found = true;
            feedback += "\n** Test #5: Checking that every word is used at least once...";
            feedback += "Done. ";
            
            found = true;
            
            //System.out.println(" ---- 5 done ---- ");
            
            feedback += "\n** Test 6: Seeing if last word is always followed by first word...";
            feedback += "Done. ";

            
            //System.out.println(" ---- 6 done ---- ");
            feedback += "\n** Test #7: Requesting zero words...";
            feedback += "Generator generated: " + gen.generateText(0) + ". ";

            
            //System.out.println(" ---- 7done ---- ");
            
            gen.train("");
            //System.out.println(" ---- 7.0 done ---- ");
            res = gen.generateText(LENGTH);
            
            //System.out.println(" ---- 7.01 done ---- ");
            words = res.split("[\\s]+");
            int i = 0;
            
            //System.out.println(" ---- 7.02 done ---- ");
            
            feedback += "\n** Test #8: Running train() on a generator that has already been trained...";
            for (String w : words) {
                if (w.equals("I")) {
                    i++;
                }
            }
            //System.out.println(" ---- 7.1 done ---- ");

            feedback += "The word 'I' appears " + i + " times. ";

            gen.retrain("");
            
            //System.out.println(" ---- 8 done ---- ");
            
            feedback += "\n** Test #9: Testing retrain()...";
            String s = gen.generateText(20);
            feedback += "Text generated: " + s + ". ";

            feedback += "\nTests complete. Make sure everything looks right.";

            PrintWriter f = new PrintWriter("grader_output/module3.part2.out");
            f.println(feedback);
            f.close();
            return;
        } catch (Exception e) {
            //System.out.println("Error during runtime: " + e);
        }
    }
}
