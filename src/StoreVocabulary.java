import java.io.*;
import java.util.*;

public class StoreVocabulary {
    public static void storeWords(String type, String number) throws IOException{
        List<String> words = new ArrayList<>();
        String filenameTemp = "Vocabulary/" + type + number + ".txt";
        File filename = new File(filenameTemp);
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            if (!filename.exists()) {
                filename.createNewFile();
            }
            isr = new InputStreamReader(new FileInputStream(filename));
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.equals(""))
                    words.add(line);
            }

            System.out.println("\nPlease insert new words. Input END if finished.");
            while(true) {
                Scanner scanner = new Scanner(System.in);
                String temp = scanner.nextLine();
                if(temp.equals("")){
                    System.out.println("Please input a word.");
                    continue;
                }
                if (temp.equalsIgnoreCase("END")) {
                    fos = new FileOutputStream(filename);
                    pw = new PrintWriter(fos);
                    Iterator<String> it = words.iterator();
                    while (it.hasNext()) {
                        pw.write(it.next() + "\n");
                    }
                    System.out.println();
                    return;
                }
                if (!words.contains(temp)) {
                    words.add(temp);
                    Collections.sort(words, (o1, o2) -> o1.compareToIgnoreCase(o2));
                } else {
                    System.out.println("This word already exists!");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
        }
    }

    public static void print(String type, String number) throws IOException{
        String filenameTemp = "Vocabulary/" + type + number + ".txt";
        File filename = new File(filenameTemp);
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            if (!filename.exists()) {
                System.out.println("\nNo words!\n");
                filename.createNewFile();
            }else{
                isr = new InputStreamReader(new FileInputStream(filename));
                br = new BufferedReader(isr);
                boolean flag = false;
                String line;
                System.out.println();
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    flag = true;
                }
                if(!flag){
                    System.out.println("No words!");
                }
                System.out.println();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
        }
    }
}