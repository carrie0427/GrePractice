import java.util.ArrayList;
import java.util.List;

public class GetInformation {
    static int[] blankEasy = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 91, 92, 93, 101, 121};
    static int[] blankMedium = {16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 63, 64,
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 94, 95, 96, 97, 102, 103,
            107, 109, 110, 111, 112, 114, 116, 117, 120, 122, 124, 125};
    static int[] blankHard = {36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 79, 80, 81, 82, 83, 84, 85,
            86, 87, 88, 89, 90, 98, 99, 100, 104, 105, 106, 108, 113, 115, 118, 119, 123};

    public static void getSingleNumber(int type, int number) {
        int medium = number / 2;
        int easy = number / 4;
        int hard = number - medium - easy;

        List<Integer> easyResult = getRandom(blankEasy.length, easy);
        List<Integer> mediumResult = getRandom(blankMedium.length, medium);
        List<Integer> hardResult = getRandom(blankHard.length, hard);
        List<Integer> readingResult = getRandom(260, number);

        System.out.println();
        if(type == 1) {
            printBlank(easyResult, mediumResult, hardResult);
        }else if(type == 2) {
            printReading(readingResult);
        }else{
            System.out.print("Type Error!\n ");
        }
    }

    public static void getBothNumber(int blankFilling, int reading){
        int medium = blankFilling / 2;
        int easy = blankFilling / 4;
        int hard = blankFilling - medium - easy;

        List<Integer> easyResult = getRandom(blankEasy.length, easy);
        List<Integer> mediumResult = getRandom(blankMedium.length, medium);
        List<Integer> hardResult = getRandom(blankHard.length, hard);
        List<Integer> readingResult = getRandom(260, reading);

        printBlank(easyResult, mediumResult, hardResult);
        printReading(readingResult);
    }

    private static List<Integer> getRandom(int total, int desired){
        List<Integer> temp = new ArrayList<>();
        for(int i = 0; i < desired; i++){
            temp.add((int)Math.round(Math.random() * total));
        }
        return temp;
    }

    private static void printBlank(List<Integer> easy, List<Integer> medium, List<Integer> hard){
        System.out.println("Blank Filling: ");
        System.out.print("      Easy: ");
        easy.forEach((x) -> System.out.print(blankEasy[x] + " "));
        System.out.println();
        System.out.print("      Medium: ");
        medium.forEach((x) -> System.out.print(blankMedium[x] + " "));
        System.out.println();
        System.out.print("      Hard: ");
        hard.forEach((x) -> System.out.print(blankHard[x] + " "));
        System.out.println("\n");
    }
    private static void printReading(List<Integer> reading){
        System.out.print("Reading: ");
        reading.forEach((x) -> System.out.print(x + " "));
        System.out.println("\n");
    }
}