package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.regex.Pattern;


public class GameDealer {
    public static String Str_Problem_Num = "";
    public static void PRINT_START_MSG(){
        System.out.println("숫자 야구 게임을 시작합니다.");
    }
    public static String GEN_PROBLEM(){
        String result = "";

        while(result.length() < 3){
            String tmp =  String.valueOf(Randoms.pickNumberInRange(1, 9));
            if(!result.contains(tmp)){
                result += tmp;
            }
        }
        return result;
    }
    public static boolean CHECK_AND_PRINT_INFO(String User_Try){
        boolean result = true;
        int Cnt_Ball = 0;
        int Cnt_Strike = 0;
        for(int i = 0; i < User_Try.length(); i++){
            String tmp = User_Try.substring(i, i+1);
            if(Str_Problem_Num.contains(tmp)){
                Cnt_Ball += 1;
            }
        }
        for(int i = 0; i < User_Try.length(); i++){
            if(User_Try.charAt(i) == Str_Problem_Num.charAt(i)){
                Cnt_Strike += 1;
                Cnt_Ball -= 1;
            }
        }

        if(Cnt_Strike < 3){
            if((Cnt_Ball == 0) && (Cnt_Strike == 0)){
                System.out.println("낫싱");
            } else {
                System.out.println(String.format("%d볼 %d스트라이크", Cnt_Ball, Cnt_Strike));
            }
            result = false;
        } else {
            System.out.println(String.format("%d스트라이크", Cnt_Strike));
        }


        return result;
    }
    public static boolean ONE_GAME_LOOP() throws IllegalArgumentException{
        boolean result = true;
        boolean Check_Ans = false;
        while(!Check_Ans){
            PRINT_PROMPT();
            String User_Try = Console.readLine();
            System.out.println(User_Try);
            if(!CHECK_VALID_INPUT(User_Try)){
                throw new IllegalArgumentException();
            }
            Check_Ans = CHECK_AND_PRINT_INFO(User_Try);
            if(Check_Ans){
                PRINT_GAME_CLEAR_MSG();
                result = CHECK_GAME_RETRY();
            }

        }
        return result;
    }
    public static boolean ONE_GAME(String[] args) throws IllegalArgumentException{
        boolean result = true;
        Str_Problem_Num = GEN_PROBLEM();
        PRINT_START_MSG();

        try {
            result = ONE_GAME_LOOP();
        } catch (Exception e){
//            System.out.println(e);
            result = false;
            throw new IllegalArgumentException();
        }
        return result;
    }
}
