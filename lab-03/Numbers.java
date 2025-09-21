
public class Numbers {
    public static void main(String[] args) {
        System.out.println("Program has started.");
        if (args.length == 0){
            System.out.println("Please provide a hexadecimal value as a command-line argument.");
            return;
        }
        
        String hexVal = args[0];

        int hexDigit = 0;
        int answer = 0;
        int counter = 0;
        for(int i = hexVal.length() - 1; i >= 0; i--){
            char c = hexVal.charAt(i);

            switch(c){
                case '0': 
                    hexDigit = 0; break;
                case '1': 
                    hexDigit = 1; break;
                case '2': 
                    hexDigit = 2; break;
                case '3': 
                    hexDigit = 3; break;
                case '4': 
                    hexDigit = 4; break;
                case '5': 
                    hexDigit = 5; break;
                case '6': 
                    hexDigit = 6; break;
                case '7': 
                    hexDigit = 7; break;
                case '8': 
                    hexDigit = 8; break;
                case '9': 
                    hexDigit = 9; break;
                case 'A': 
                    hexDigit = 10; break;
                case 'B': 
                    hexDigit = 11; break;
                case 'C': 
                    hexDigit = 12; break;
                case 'D':  
                    hexDigit = 13; break;
                case 'E': 
                    hexDigit = 14; break;
                case 'F': 
                    hexDigit = 15; break;
            }
            System.out.println(hexDigit);
            answer = answer + (hexDigit * (int)Math.pow(16,counter));
            counter++;
        }
        System.out.println("Answer is: " + answer);
        System.out.println("Program has ended.");
    }
}
