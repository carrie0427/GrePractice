import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class StoreResult {
    final static String pathname = "result.txt";

    public static List<Result> getResult() throws IOException{
        List<Result> results = new ArrayList<>();
        List<Result> blankResults = new ArrayList<>();
        List<Result> readingResults = new ArrayList<>();
        File filename = new File(pathname);
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            if(!filename.exists()){
                filename.createNewFile();
            }
            isr = new InputStreamReader(
                new FileInputStream(filename));
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                List<Score> scores= new ArrayList<>();
                String[] temp = line.split(" ");
                int index = 3;
                int time = Integer.parseInt(temp[2]);
                for(int i = 0; i < time; i++){
                    Score score = new Score(temp[index], temp[index+1]);
                    scores.add(score);
                    index += 2;
                }
                Collections.sort(scores);
                Result result = new Result(temp[0], Integer.parseInt(temp[1]), time, scores);
                if(temp[0].equalsIgnoreCase("Blank")){
                    blankResults.add(result);
                    Collections.sort(blankResults);
                }else if(temp[0].equalsIgnoreCase("Reading")){
                    readingResults.add(result);
                    Collections.sort(readingResults);
                }
            }
            results.addAll(blankResults);
            results.addAll(readingResults);
            return results;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
        }
        return null;
    }

    public static void storeResult(String type, String number, String correctness) throws IOException{
        List<Result> results = getResult();
        Iterator<Result> it = results.iterator();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Score score = new Score(correctness, df.format(new Date()));
        boolean flag = false;
        while (it.hasNext()) {
            Result temp = it.next();
            if(temp.getName().equalsIgnoreCase(type+number)){
                temp.addScore(score);
                Collections.sort(temp.getScore());
                temp.addTimes();
                flag = true;
                break;
            }
        }
        if(!flag){
            List<Score> scoreList = new ArrayList<>();
            scoreList.add(score);
            Collections.sort(scoreList);
            Result newResult = new Result(type, Integer.parseInt(number), 1, scoreList);
            results.add(newResult);
        }

        File filename = new File(pathname);
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            if (!filename.exists()) {
                filename.createNewFile();
            }

            fos = new FileOutputStream(filename);
            pw = new PrintWriter(fos);
            Iterator<Result> sub = results.iterator();
            while (sub.hasNext()) {
                pw.write(sub.next().print() + "\n");
            }
            System.out.println();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    public static void printResult() throws IOException{
        List<Result> results = getResult();
        System.out.println();
        System.out.println("Results for all the exercises:");
        System.out.println();
        System.out.println("  Title\t\t Correctness\t\tDate");
        System.out.println();
        Iterator<Result> it = results.iterator();
        while (it.hasNext()) {
            Result temp = it.next();
            System.out.println(" " + temp.getName());
            temp.getScore().forEach((x) -> System.out.println("\t\t\t\t" + x.getCorrectness()
                    + "\t\t " + x.getDate()));
            System.out.println();
        }
        System.out.println();
    }

    public static void printResult(String type, String number) throws IOException{
        List<Result> results = getResult();
        Iterator<Result> it = results.iterator();
        while (it.hasNext()) {
            Result temp = it.next();
            if(temp.getName().equalsIgnoreCase(type + number)){
                System.out.println();
                System.out.println("Previous results for " + type + " " + number + ":");
                System.out.println("  Correctness\t\tDate");
                temp.getScore().forEach((x) -> System.out.println("    " + x.getCorrectness()
                                                + "\t\t " + x.getDate()));
                System.out.println();
                return;
            }
        }
        System.out.println("No such record.");
    }
}
