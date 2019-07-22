import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] initialPrint = {
                "",
                "Welcome to use this program to help you practice GRE!\n",
                "1. GET",
                "       Get serial number of blank filling and reading for exercise.",
                "       Format: GET NEW [Blank/Reading] [Number].",
                "               GET NEW [Number of blank filling] [Number of reading].\n",
                "2. STORE",
                "       Store the result of practice with date.",
                "       Format: STORE [Blank/Reading] [Number] [Result].\n",
                "3. VOCABULARY",
                "       Store words to a txt file.",
                "       Input END when you finish.",
                "       Format: VOCABULARY [Blank/Reading] [Number].\n",
                "4. PRINT",
                "       Format: PRINT RESULT",
                "               PRINT RESULT [Blank/Reading] [Number]",
                "               PRINT VOCABULARY [Blank/Reading] [Number]\n",
                "5. QUIT",
                "       Quit the program.\n"
        };

        // Print initial message
        for(int i = 0; i < initialPrint.length; i++) {
            System.out.println(initialPrint[i]);
        }

        while(true){
            System.out.println("Your Command:");
            Scanner scanner = new Scanner(System.in);
            String temp = scanner.nextLine();
            if(temp.contains(" ")) {
                String[] result = temp.split(" ");
                switch (result[0].toUpperCase()){
                    case "GET":
                        if(result.length == 4) {
                            int type = 0;
                            if(result[2].equalsIgnoreCase("Blank")){
                                type = 1;
                            }else if(result[2].equalsIgnoreCase("Reading")){
                                type = 2;
                            }
                            if(result[1].equalsIgnoreCase("NEW")) {
                                if(type == 0){
                                    GetInformation.getBothNumber(Integer.parseInt(result[2]), Integer.parseInt(result[3]));
                                }else if(type == 1 || type == 2){
                                    GetInformation.getSingleNumber(type, Integer.parseInt(result[3]));
                                }
                                break;
                            }
                        }
                        System.out.println("Wrong command. Please try again.\n");
                        continue;
                    case "STORE":
                        if(result.length == 4) {
                            StoreResult.storeResult(result[1], result[2], result[3]);
                            break;
                        }
                        System.out.println("Wrong command. Please try again.\n");
                        continue;
                    case "VOCABULARY":
                        if(result.length == 3) {
                            StoreVocabulary.storeWords(result[1], result[2]);
                            break;
                        }
                        System.out.println("Wrong command. Please try again.\n");
                        continue;
                    case "PRINT":
                        if (result[1].equalsIgnoreCase("RESULT")) {
                            if(result.length == 2){
                                StoreResult.printResult();
                                break;
                            }else if(result.length == 4){
                                StoreResult.printResult(result[2], result[3]);
                                break;
                            }else{
                                System.out.println("Wrong command. Please try again.\n");
                                continue;
                            }
                        }else if(result[1].equalsIgnoreCase("VOCABULARY") && result.length == 4){
                            StoreVocabulary.print(result[2], result[3]);
                            break;
                        }
                    default:
                        System.out.println("Wrong command. Please try again.\n");
                        continue;
                }
            }else{
                if(temp.equalsIgnoreCase("QUIT")){
                    System.out.println("Goodbye! Keep working!");
                    return;
                }
            }
        }
    }
}