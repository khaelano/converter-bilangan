import java.util.Scanner;
import java.lang.Math;

public class App {
    public static void main(String[] args) throws Exception {
        while (true) {
            Scanner S = new Scanner(System.in);

            System.out.println("Pilih basis target:");
            System.out.println("1. Biner\n2. Desimal\n3. Hexadesimal\n0. Keluar");

            System.out.print("Masukkan input (0-3) > ");
            int target = S.nextInt();
            if (target == 0) {
                System.out.println("Keluar dari program...");
                break;
            } else if (target > 3 || target < 0) {
                System.out.println("Keluar dari jangkauan, ulang lagi...");
                continue;
            }

            System.out.println("Masukkan bilangan asal dengan basis bilangannya (bin, dec, hex)");
            System.out.print("Contoh: \"23345 dec\" > ");
            String bilangan = S.next();
            String asal = S.next();

            // Mekanisme Konversi
            String hasilStr = "";
            int decimal = toDecimal(bilangan, asal);
            switch (target) {
                case 1:
                    while (decimal != 0) {
                        hasilStr = (char)(decimal % 2 + '0') + hasilStr;
                        decimal = decimal / 2;
                    }
                    break;
                case 2:
                    hasilStr = String.valueOf(decimal);
                    break;
                case 3:
                    int nilaiDigit;
                    char digit;
                    while (decimal != 0) {
                        int modulo = decimal % 16;
                        if (modulo > 9) {
                            nilaiDigit = 'a' + modulo - 10;
                            digit =(char)nilaiDigit;
                        } else {
                            digit = (char)(modulo + '0');
                        }
                        hasilStr = digit + hasilStr;
                        decimal = decimal / 16;
                    }
                    break;
                default:
                    System.out.println("Error...");
                    break;
            }
            System.out.printf("Hasil konversi adalah: %s\n", hasilStr);
            S.close();
        }
    }

    static int toDecimal(String bilanganRaw, String asal) {
        String bilangan = bilanganRaw.toLowerCase();
        int bilanganMaxIndex = bilangan.length()-1;
        int nilai = 0;
        switch (asal) {
            case "hex":
            for (int i = bilanganMaxIndex ; i >= 0 ; i--) {
                    int digit;
                    if (Character.isAlphabetic(bilangan.charAt(i))) {
                        digit = bilangan.charAt(i) - 'a' + 10;
                    } else {
                        digit = bilangan.charAt(i) - '0';
                    }
                    nilai += (int)(digit*Math.pow(16, bilanganMaxIndex-i));
                }
                break;

            case "bin":
                for (int i = bilanganMaxIndex ; i >= 0 ; i--) {
                    if (bilangan.charAt(i) == '1') {
                        nilai += (int)(1*Math.pow(2, bilanganMaxIndex-i));
                    }
                }
                break;

            case "dec":
                nilai = Integer.parseInt(bilangan);
                break;
        
            default:
                System.out.println("Error. Pilih jenis bilangan yang valid");
                break;
        }
        return nilai; 
    }
}
