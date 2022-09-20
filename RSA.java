package rsa;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import static javafx.application.Platform.exit;

public class RSA {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("input the the p");
        int p = input.nextInt();
        if(check_prime(p)==false){
            System.out.println("the p not prime");
            exit();
        }
        
        System.out.println("input the the q");
        int q = input.nextInt();
        
        if(check_prime(q)==false){
            System.out.println("the q not prime");
            exit();
        }
        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int e=getrelativeprime(phi);
//        int e = 7;
        int d = get_inverse(e, phi);
        String plaintext = "dog";
        System.out.println("the public key : " + "{e,n}" + "{" + e + "," + n + "}");
        System.out.println("the private key : " + "{d,n}" + "{" + d + "," + n + "}");

//         String c2= encrypt(plaintext, e, n);
//         decrypt(c2, d, n);
        int a[] = arr_encrypt(plaintext, e, n);
        int b[] = arr_decrypt(a, d, n);
        char a_c[] = new char[a.length];
        char b_c[] = new char[a.length];
        


    }

    public static int getrelativeprime(int phi) {
        for (int i = 2; i < phi; i++) {
            if (phi % i != 0) {
                return i;
            }

//               int ran=1;
//             while(phi%ran==0){
//          ran= 2+ (int)(Math.random() * (phi));
        }

//        return ran;
        return 0;
    }

    private static int get_inverse(int a, int phi) {
        int inverse_a = 0;
        for (int i = 1; i < 10000000; i++) {
//            System.out.println(i);
            if (((a * i) - 1) % phi == 0) {
                System.out.println(i);
                return i;
            }
        }
        return inverse_a;

    }

    

    private static boolean check_prime(int x) {
        for (int i = 2; i < Math.sqrt(x)+1; i++) {
            if (x%i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int[] arr_encrypt(String plaintext, int e, int n) {

        int cipher[] = new int[plaintext.length()];
        for (int i = 0; i < plaintext.length(); i++) {
            int c = Character.valueOf(plaintext.charAt(i));
            BigInteger C = BigDecimal.valueOf(c).toBigInteger();
            BigInteger N = BigInteger.valueOf(n);
            BigInteger ed = (C.pow(e)).mod(N);
            cipher[i] = ed.intValue();
            int x = (int)('a'+cipher[i]%26);
            System.out.println(plaintext.charAt(i) + " :" + c + " => "+cipher[i]+ " : " + x);

        }

        return cipher;
    }

    private static int[] arr_decrypt(int ciphertext[], int d, int n) {

        int plain[] = new int[ciphertext.length];
        for (int i = 0; i < ciphertext.length; i++) {
            int c = ciphertext[i];
            BigInteger C = BigDecimal.valueOf(c).toBigInteger();
            BigInteger N = BigInteger.valueOf(n);
            BigInteger ed = (C.pow(d)).mod(N);
            plain[i] = ed.intValue();
            
       
            System.out.println(ciphertext[i] + " :" + c + " => " + (char)plain[i]);
        }

        return plain;
    }
    
    //        for (int i = 0; i < a.length; i++) {
//            a_c[i] = (char) ('a'+a[i]%26);
//            System.out.println(a_c[i]);
//        }
//        for (int i = 0; i < a.length; i++) {
//          
//            b_c[i] = (char)(b[i]);
//            System.out.println(b_c[i]);
//        }
    
//    private static String encrypt(String plaintext, int e, int n) {
//        String ciphertext = "";
//
//        for (int i = 0; i < plaintext.length(); i++) {
//            int c = (int) plaintext.charAt(i) - 'a';
//            ciphertext += (char) ('a' + ((Math.pow(c, e) % n)) % (26));
//
////                System.out.println((int)(Math.pow(88, e)%n));
//        }
//        System.out.println(ciphertext);
//        return ciphertext;
//    }
//
//    private static String decrypt(String ciphertext, int d, int n) {
//        String plaintext = "";
//        for (int i = 0; i < ciphertext.length(); i++) {
//            int c = (int) ciphertext.charAt(i) - 'a';
//            BigInteger C = BigDecimal.valueOf(c).toBigInteger();
//            BigInteger N = BigInteger.valueOf(n);
//            BigInteger ed = (C.pow(d)).mod(N);
//            BigInteger A = BigInteger.valueOf(26);
//            ed = ed.mod(A);
////            int css= ed.intValue()+ Character.valueOf('a');
////            String sd += (char)(Integer.toString(css));
//
//            plaintext += ed.toString() + 'a';
//////             ciphertext += (char) (((Math.pow(c, e)%n)+'a')%26);
////                System.out.println((int)(Math.pow(88, d)%n));
//            plaintext += (char) ('a' + ((Math.pow(c, d) % n) % (26)));
//        }
//        System.out.println(plaintext);
//        return plaintext;
//    }

}
